package com.example.demo.repository;

import com.example.demo.model.Subject;

import java.util.List;

public interface SqlSubjectRepository {
    List<Subject> findTopSubjectsByCommonLetters(int limit);
}