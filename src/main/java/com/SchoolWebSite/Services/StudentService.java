package com.SchoolWebSite.Services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
import org.springframework.web.multipart.MultipartFile;

import com.SchoolWebSite.Models.Actualites;
import com.SchoolWebSite.Models.Bill;
import com.SchoolWebSite.Models.Certification;
import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Etat;
import com.SchoolWebSite.Models.Genre;
import com.SchoolWebSite.Models.MatResponse;
import com.SchoolWebSite.Models.Matiere;
import com.SchoolWebSite.Models.NoteFinale;
import com.SchoolWebSite.Models.ResetPassword;
import com.SchoolWebSite.Models.Semestre;
import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Repository.StudentRepo;
import com.SchoolWebSite.Security.SignInRequest;

import jakarta.mail.MessagingException;

@Service
public class StudentService implements UserDetailsService{
	
	@Autowired
	StudentRepo repo;
	
	@Autowired 
    private ClasseService classeService;

	@Autowired 
    private EmailService emServ;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	private String generatePassword() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?";

        String allChars = upperCase + lowerCase + digits + specialChars;

        Random random = new Random();
        StringBuilder password = new StringBuilder();

    
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        return password.toString();
    }
    
	
	
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public void saveStudent(String fullName, String fatherName, String motherName, LocalDate birthDate, String tel, String guardianTel, String address, Genre genre, String email, MultipartFile profilFile) throws IOException, MessagingException {

        Student student = new Student();
        student.setFullName(fullName);
        student.setFatherName(fatherName);
        student.setMotherName(motherName);
        //Date d=new Date(birthDate);		
        student.setBirthDate(birthDate);
        student.setTel(tel);
        student.setGuardianTel(guardianTel);
        student.setAddress(address);
        student.setGenre(genre);
        student.setGmail(email);
        student.setEmail(generatePassword());

        // Enregistrer la photo de profil si elle existe
        if (profilFile != null && !profilFile.isEmpty()) {
            // Utiliser un service comme Cloudinary pour sauvegarder l'image et obtenir l'URL
            String profilUrl = cloudinaryService.uploadImage(profilFile);
            student.setProfil(profilUrl);
        }
System.out.println(student.toString());
        repo.save(student);
        emServ.sendInscriptionEmail(email, fullName);
    }
	
	public Student saveStudent(Student std) {
		//System.out.println("password dayz :"+std.getPassword());
		std.setPassword(passwordEncoder().encode(std.getPassword()));
		return repo.save(std);
	}
	
	public boolean forgotPwd(String std,String gmail,String tel) throws MessagingException {
		Student st=repo.findByEmail(std);
		if(st!=null) {
			if((st.getTel().equals(tel) || st.getGuardianTel().equals(tel)) && st.getGmail().equals(gmail)){
				String gp=generatePassword();
				st.setPassword(passwordEncoder().encode(gp));
				 repo.save(st);
				 emServ.sendNewPasswordEmail(st.getGmail(), gp);
				 return true;
			}
		}
		
		return false;
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
		//System.out.println(yearSchool);
		//System.out.println(semestre);
		
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
	
	public boolean StudentUpdate(ResetPassword sn) {
		// System.out.println("email "+sn.getEmail());
		// System.out.println("old "+sn.getOldPassword());
		// System.out.println("new "+sn.getNewPassword());
		Student std=repo.findByEmail(sn.getEmail());
		if(passwordEncoder().matches(sn.getOldPassword(), std.getPassword())) {
			std.setPassword(passwordEncoder().encode(sn.getNewPassword()));
			 repo.save(std);
			
			 return true;
			
		}
		return false;
		
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
					//System.out.println(mat.getMatName());
					//System.out.println(note);
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
	
	public List<Certification> getAllcertification(String email){
		return repo.findCertificationByStudent(email);
		
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
