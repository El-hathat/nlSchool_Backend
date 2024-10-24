package com.SchoolWebSite.Controllers;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SchoolWebSite.Models.Actualites;
import com.SchoolWebSite.Models.Bill;
import com.SchoolWebSite.Models.Certification;
import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Etat;
import com.SchoolWebSite.Models.Genre;
import com.SchoolWebSite.Models.MatResponse;
import com.SchoolWebSite.Models.NoteFinale;
import com.SchoolWebSite.Models.ResetPassword;
import com.SchoolWebSite.Models.Semestre;
import com.SchoolWebSite.Models.SessionAbsence;
import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Repository.AbsenceRepo;
import com.SchoolWebSite.Security.SignInRequest;
import com.SchoolWebSite.Services.CloudinaryService;
import com.SchoolWebSite.Services.StudentService;

import jakarta.mail.MessagingException;

//@CrossOrigin(origins = "localhost:3000")
@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService service;
	@Autowired
    private CloudinaryService cloudinaryService;
	@Autowired
	AbsenceRepo rep;
	
	@GetMapping("/{email}")
	@ResponseBody
	public Object[] getStudent(@PathVariable String email) {
		//System.out.println(email);
		return service.findStudent(email);
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		System.out.println("hello from console");
		return "hello from nl";
	}
	
	@PostMapping("/passwordUpdate")
	@ResponseBody
	public boolean teste(@RequestBody ResetPassword uPd) {
		
		return service.StudentUpdate(uPd);
	}
	
	@PostMapping(value="/registrer", consumes = "multipart/form-data")
    public ResponseEntity<String> addStudents(
            @RequestParam("fullName") String fullName,
            @RequestParam("fatherName") String fatherName,
            @RequestParam("motherName") String motherName,
            @RequestParam("birthDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
            @RequestParam("tel") String tel,
            @RequestParam("guardianTel") String guardianTel,
            @RequestParam("address") String address,
            @RequestParam("genre") Genre genre,
            @RequestParam("email") String email,
            @RequestParam(value = "profil", required = false) MultipartFile profilFile) throws IOException, MessagingException {

       try {
            // Logique pour enregistrer l'étudiant
            service.saveStudent(fullName, fatherName, motherName, birthDate, tel, guardianTel, address, genre, email, profilFile);
            return ResponseEntity.ok("Étudiant ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'étudiant");
        }
    }
    
	
	@PostMapping(value="/add", consumes = "multipart/form-data")
	@ResponseBody
	public Optional<Student> addStudent(@ModelAttribute Student std,
	        @RequestParam("file") MultipartFile file) throws IOException {
		if (file.isEmpty()) {
	        throw new RuntimeException("File is empty");
	    }
	    
	    std.setProfil(cloudinaryService.uploadImage(file));
	   // System.out.println("pwd from ctrl :"+std.getPassword());
	    return Optional.of(service.saveStudent(std));
	}
	
	@GetMapping("/absences/{mounth}/{year}/{student}")
	@ResponseBody
	public List<List<SessionAbsence>> absences(@PathVariable int mounth,@PathVariable int year,@PathVariable String student) {
		List<List<SessionAbsence>> ab=new ArrayList<>();
		for (int i = 1; i <=31; i++) {
			ab.add(rep.findByDayMonthYearAndStudent(i,mounth,year,student));
		}

		return ab;
	}
	
	
	@GetMapping("/forgotpwd/{cne}/{tel}/{gmail}")
	@ResponseBody
	public boolean changePwd(@PathVariable String cne,@PathVariable String tel,@PathVariable String gmail) throws MessagingException {
	//System.out.println(cne +"/"+tel+"/"+gmail);
	
			return service.forgotPwd(cne, gmail, tel);
	
	}
	
	
	@GetMapping("/notes/{schoolyear}/{semestre}/{classId}/{email}")
	@ResponseBody
	public List<List<Object[]>> getBulltein(@PathVariable String schoolyear,@PathVariable Semestre semestre,@PathVariable Long classId,@PathVariable String email) {
		//System.out.println(email);
		return service.getbulletin(schoolyear, semestre, classId,email);
	}
	
	@GetMapping("/statistique/{email}")
	@ResponseBody
	public List<MatResponse> statistique(@PathVariable String email) {
		//System.out.println(email);
		return service.statistique(email);
	}
	
	
	@GetMapping("/certificats/{email}")
	@ResponseBody
	public List<Certification> certifications(@PathVariable String email) {
		//System.out.println(email);
		return service.getAllcertification(email);
	}
	
	@GetMapping("/notefinale/{student}/{year}/{semestre}")
	@ResponseBody
	public NoteFinale getFinaleNote(@PathVariable String student,@PathVariable String year,@PathVariable Semestre semestre) {
		//System.out.println(email);
		return service.getNote(student,year,semestre);
	}
	
	@GetMapping("/score/{student}")
	@ResponseBody
	public List<Integer> getFinaleNoteSort(@PathVariable String student) {
		return service.studentScore(student);
	}
	
	@GetMapping("/classinfo/{student}/{year}")
	@ResponseBody
	public List<Object[]> getClassInfos(@PathVariable String student,@PathVariable  String year) {
		//System.out.println(email);
		return service.getClassInfo(student,year);
	}
	
	@GetMapping("/getAllBills/{student}/{etat}")
	@ResponseBody
	public List<Bill> getAllBills(@PathVariable String student,@PathVariable Etat etat) {
		//System.out.println(email);
		return service.getBills(etat,student);
	}
	
	
	@GetMapping("/actualites/{classid}")
	@ResponseBody
	public List<Actualites> getAllBills(@PathVariable Long classid) {
		//System.out.println(email);
		return service.getAllActualitesByClasse(classid);
	}
	
	
	

}
