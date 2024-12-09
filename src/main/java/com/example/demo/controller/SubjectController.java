package com.example.demo.controller;


import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/top-common-letters")
    public List<Subject> getTopSubjects(@RequestParam(defaultValue = "10") int limit) {
        return subjectService.findTopSubjectsByCommonLetters(limit);
    }

}
