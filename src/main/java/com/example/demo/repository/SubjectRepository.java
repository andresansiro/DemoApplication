package com.example.demo.repository;

import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    /**
     * Finds the top subjects based on the number of common letters between the subject name
     * and the lecturer's full name.
     * <p>
     * This method executes a custom SQL query to calculate the number of common letters
     * between the subject's name and the lecturer's full name, where the order of the letters
     * does not matter. The query returns the top subjects with the highest number of common letters,
     * sorted by the score in descending order. If there is a tie, the subjects are sorted by their name.
     * <p>
     * The number of results returned is limited by the specified {@code limit}.
     *
     * @param limit the maximum number of subjects to return
     * @return a list of {@link Subject} objects that match the criteria
     */
    @Query(value = """
            WITH letter_counts AS (
                SELECT
                    s.id,
                    s.name,
                    s.lecturer_full_name,
                    SUM(
                        LEAST(
                            LENGTH(s.name) - LENGTH(REPLACE(LOWER(s.name), letter, '')),
                            LENGTH(s.lecturer_full_name) - LENGTH(REPLACE(LOWER(s.lecturer_full_name), letter, ''))
                        )
                    ) AS lecturer_full_name_score
                FROM subject s
                CROSS JOIN LATERAL (
                    SELECT DISTINCT SUBSTRING(LOWER(s.lecturer_full_name) FROM 1 FOR 1) AS letter
                ) letters
                GROUP BY s.id, s.name, s.lecturer_full_name
            )
            SELECT *
            FROM letter_counts
            ORDER BY lecturer_full_name_score DESC, name
            LIMIT :limit;
                """, nativeQuery = true)
    List<Subject> findTopSubjectsByCommonLetters(@Param("limit") int limit);

}
