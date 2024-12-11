package com.example.demo.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.Subject;
import com.example.demo.repository.SqlSubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class SubjectServiceTest {
    @Mock
    private SqlSubjectRepository repository;

    @InjectMocks
    private SubjectService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindTopSubjectsByCommonLetters_ReturnsCorrectResults() {
        Subject subject1 = new Subject("Matematyka Finansowa", "Jan Kowalski", 1);
        Subject subject2 = new Subject("Sieci Wirtualne", "Adam Nowak", 1);
        List<Subject> mockSubjects = Arrays.asList(subject1, subject2);

        when(repository.findTopSubjectsByCommonLetters(1, 2)).thenReturn(mockSubjects);

        List<Subject> result = service.findTopSubjectsByCommonLetters(1, 2);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Matematyka Finansowa");
        assertThat(result.get(1).getName()).isEqualTo("Sieci Wirtualne");

        verify(repository, times(1)).findTopSubjectsByCommonLetters(1, 2);
    }

    @Test
    void testFindTopSubjectsByCommonLetters_NoResults() {
        when(repository.findTopSubjectsByCommonLetters(1, 3)).thenReturn(List.of());

        List<Subject> result = service.findTopSubjectsByCommonLetters(1, 3);

        assertThat(result).isEmpty();

        verify(repository, times(1)).findTopSubjectsByCommonLetters(1, 3);
    }
}