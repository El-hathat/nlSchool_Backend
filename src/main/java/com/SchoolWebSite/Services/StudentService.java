package com.SchoolWebSite.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.SchoolWebSite.Models.Actualites;
import com.SchoolWebSite.Models.Bill;
import com.SchoolWebSite.Models.Certification;
import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Etat;
import com.SchoolWebSite.Models.MatResponse;
import com.SchoolWebSite.Models.Matiere;
import com.SchoolWebSite.Models.NoteFinale;
import com.SchoolWebSite.Models.Semestre;
import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Repository.StudentRepo;

@Service
public class StudentService implements UserDetailsService{
	
	@Autowired
	StudentRepo repo;
	
	@Autowired 
    private ClasseService classeService;

	
    
	
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public Student saveStudent(Student std) {
		std.setPassword(passwordEncoder().encode(std.getPassword()));
		return repo.save(std);
	}
	
	public Object[] findStudent(String email) {
		//System.out.println(repo.findByEmail(email).getAddress());
		return repo.findStudentDetailsByEmail(email);
	}
	public Student getByEmail(String email) {
		return repo.findByEmail(email);
	}
	public NoteFinale getNote(String student,String year,Semestre semestre) {
		return repo.getNoteFinale(repo.findByEmail(student),year,semestre);
	}
	
	 int getNoteSort(String email) {
		int month=new Date().getMonth();
		String yearSchool=repo.findClasseByStudent(email).getSchoolYear();
		Semestre semestre=month>=9 && month<=12 ?Semestre.S1:Semestre.S2;
		System.out.println(yearSchool);
		System.out.println(semestre);
		
		int i=0;
		int range=0;
		for (NoteFinale item : repo.getNoteFinaleSort(yearSchool,semestre,repo.findClasseByStudent(email))) {
			
			if(item.getStudent().getEmail().equals(email)) {
				i=i+1;
				range= i;
				
			}else {
			i++;}
		}
		return range;
	}
	
	public List<List<Object[]>> getbulletin(String schoolYear,Semestre semestre,Long classID,String email) {
		List<List<Object[]>>lst=new ArrayList<>();
		
		for (Matiere mat : classeService.getMatieresForClasse(classID)) {
			if (repo.findExamNotes(schoolYear,semestre,mat.getMatName(),email).size()>0) {
				lst.add(repo.findExamNotes(schoolYear,semestre,mat.getMatName(),email));
				//System.out.println(repo.findExamNotes(schoolYear,semestre,mat.getMatName()));
			}
			
		}
		//System.out.println(repo.findExamNotes(schoolYear,semestre,matName));
		return lst;
	}
	
	
	
	public List<MatResponse> statistique(String email) {
		MatResponse lst ;
		List<MatResponse> lst2=new ArrayList<>();
		int month=new Date().getMonth();
		String year=month>=9 && month<=12 ?(new Date().getYear()+1900)+"-"+(new Date().getYear()+1901):(new Date().getYear()+1899)+"-"+(new Date().getYear()+1900);
		Semestre semestre=month>=9 && month<=12 ?Semestre.S1:Semestre.S2;
		for (Matiere mat : classeService.getMatieresForClasse(repo.findClasseByStudentAndSchoolYear(email,year).getClassID())) {
			if (repo.findExamNotes(year,semestre,mat.getMatName(),email).size()>0) {
				int i=0;
				String matiere="";
				double note=0.0;
				for (Object[] item : (repo.findExamNotes(year,Semestre.S1,mat.getMatName(),email))) {
					note=note+Double.parseDouble(item[0]+"");
					i++;
					System.out.println(mat.getMatName());
					System.out.println(note);
				}
				
				lst=new MatResponse(mat.getMatName(),note/i);
				//lst.put("mat",mat.getMatName());
				//lst.put("note",);
				
				lst2.add(lst);
				
				
				
				//System.out.println(repo.findExamNotes(schoolYear,semestre,mat.getMatName()));
			}
			
		}
		//System.out.println(repo.findExamNotes(schoolYear,semestre,matName));
		return lst2;
	}
	
	
	public   List<Object[]> getClassInfo(String email, String year){
		return repo.findClassInfoByStudentEmail(email,year);
	}
	
	public   List<Bill> getBills(Etat etat,String email){
		return repo.findAllBillsByStudent(etat,repo.findByEmail(email));
	}
	
	
	
	int getDevoirNonRemis(String email) {
		return repo.devoirNonRemis(email);
	}
	
	int getSessionAbsente(String email) {
		return repo.getCountOfSessionAbsenceByStudent(repo.findByEmail(email));
	}
	
	int getCountCertificats(String email) {
		return repo.findCertificatsByStudent(repo.findByEmail(email)).size();
	}
	
	
	public List<Integer> studentScore(String email){
		List<Integer> lst=new ArrayList<>();
		lst.add(this.getNoteSort(email));
		lst.add(this.getCountCertificats(email));
		lst.add(this.getDevoirNonRemis(email));
		lst.add(this.getSessionAbsente(email));
		return lst;
	}
	
	List<Certification> getAllcertifications(String email){
		return repo.findCertificatsByStudent(repo.findByEmail(email));
	}
	
	
	public List<Actualites> getAllActualitesByClasse(Long classid){
		return repo.findActualitesByClassId(classid);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Student student = repo.findByEmail(username);
	    if (student == null) {
	        throw new UsernameNotFoundException("User not found with email: " + username);
	    }
	    return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), student.getAuthorities());
	}

	


}
