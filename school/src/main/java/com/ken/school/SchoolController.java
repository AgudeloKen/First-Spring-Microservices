package com.ken.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/schools")
@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    public School save(@RequestBody School school){
        return schoolService.saveStudent(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAll(){
        return ResponseEntity.ok(schoolService.findAll());
    }

    @GetMapping("/students/{schoolId}")
    public ResponseEntity<FullSchoolResponse> findWithStudents(@PathVariable Long schoolId){
        return ResponseEntity.ok(schoolService.findSchoolsWithStudents(schoolId));
    }
}
