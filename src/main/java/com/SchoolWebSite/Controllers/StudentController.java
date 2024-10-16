package com.SchoolWebSite.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Actualites;
import com.SchoolWebSite.Models.Bill;
import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Etat;
import com.SchoolWebSite.Models.MatResponse;
import com.SchoolWebSite.Models.NoteFinale;
import com.SchoolWebSite.Models.Semestre;
import com.SchoolWebSite.Models.SessionAbsence;
import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Repository.AbsenceRepo;
import com.SchoolWebSite.Services.StudentService;

//@CrossOrigin(origins = "localhost:3000")
@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService service;
	
	@Autowired
	AbsenceRepo rep;
	
	@GetMapping("/{email}")
	@ResponseBody
	public Object[] getStudent(@PathVariable String email) {
		System.out.println(email);
		return service.findStudent(email);
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		System.out.println("hello from console");
		return "hello from controller";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Optional<Student> addStudent(@RequestBody Student std) {
		System.out.println(std.getEmail());
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
	
	
	@GetMapping("/registration")
	@ResponseBody
	public String afficher() {
		System.out.println("hello day");
		return "spring security khedam mzn";
	}

}
