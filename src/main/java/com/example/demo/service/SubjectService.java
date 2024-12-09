package com.example.demo.service;

import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public List<Subject> findTopSubjectsByCommonLetters(int limit) {
        return repository.findTopSubjectsByCommonLetters(limit);
    }
}
