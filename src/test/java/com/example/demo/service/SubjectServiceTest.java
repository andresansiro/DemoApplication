package com.example.demo.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class SubjectServiceTest {
    @Mock
    private SubjectRepository repository;

    @InjectMocks
    private SubjectService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindTopSubjectsByCommonLetters_ReturnsCorrectResults() {
        Subject subject1 = new Subject( "Kraków", "Jan Kowalski");
        Subject subject2 = new Subject( "Warszawa", "Adam Nowak");
        List<Subject> mockSubjects = Arrays.asList(subject1, subject2);

        when(repository.findTopSubjectsByCommonLetters(2)).thenReturn(mockSubjects);

        List<Subject> result = service.findTopSubjectsByCommonLetters(2);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Kraków");
        assertThat(result.get(1).getName()).isEqualTo("Warszawa");

        verify(repository, times(1)).findTopSubjectsByCommonLetters(2);
    }

    @Test
    void testFindTopSubjectsByCommonLetters_NoResults() {
        when(repository.findTopSubjectsByCommonLetters(3)).thenReturn(List.of());

        List<Subject> result = service.findTopSubjectsByCommonLetters(3);

        assertThat(result).isEmpty();

        verify(repository, times(1)).findTopSubjectsByCommonLetters(3);
    }
}