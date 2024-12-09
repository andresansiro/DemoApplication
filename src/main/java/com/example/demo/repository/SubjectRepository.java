package com.example.demo.repository;

import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SubjectRepository extends SqlSubjectRepository, JpaRepository<Subject, Integer> {
    /**
     * Finds the top subjects based on the number of common letters between the subject name
     * and the lecturer's full name.
     * <p>
     * This method executes a custom SQL query to calculate the number of common letters
     * between the subject's name and the lecturer's full name. The query compares the subject's
     * name and lecturer's full name by counting how many distinct letters appear in both names,
     * regardless of the order. The result is ordered by the score (number of common letters) in descending
     * order. If there is a tie in the score, the subjects are sorted by their name alphabetically.
     * <p>
     * The number of results returned is limited by the specified {@code limit}.
     *
     * @param limit the maximum number of subjects to return
     * @return a list of {@link Subject} objects that match the criteria
     */
    @Query(value = """
            WITH letter_counts AS (
                SELECT s.id,
                       s.name,
                       s.lecturer_full_name,
                       SUM(
                               LEAST(
                                           LENGTH(REPLACE(LOWER(s.name), ' ', '')) - LENGTH(REPLACE(REPLACE(LOWER(s.name), ' ', ''), letter, '')),
                                           LENGTH(REPLACE(LOWER(s.lecturer_full_name), ' ', '')) - LENGTH(REPLACE(REPLACE(LOWER(s.lecturer_full_name), ' ', ''), letter, ''))
                                   )
                           ) AS lecturer_full_name_score
                FROM subject s
                         CROSS JOIN LATERAL (
                    SELECT DISTINCT unnest(string_to_array(REPLACE(LOWER(s.lecturer_full_name), ' ', ''), NULL)) AS letter
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
