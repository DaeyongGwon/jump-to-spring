package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    Optional<QuestionSubject> findSubjectById(Integer i);
    List<Question> findBySubjectLike(String subject);

}