package com.mysite.sbb;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    // save
    @Test
//    @Disabled
    void testSave() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    // findAll
    @Test
    @Disabled
    void testFindAll() {
        List<Question> all = this.questionRepository.findAll();

        assertEquals(2, all.size());

        Question q = all.getFirst();
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    // findById
    @Test
    @Disabled
    void testFindByID() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }


    // findBySubject
    @Test
    void testFindSub() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(9, q.getId());
    }

    // Optional 사용
    @Test
    void testFindSubjectByID(){
        Optional<QuestionSubject> oq = this.questionRepository.findSubjectById(9);
        if(oq.isPresent()){
            String subject = oq.get().getSubject();
            String content = oq.get().getContent();
            assertEquals("sbb가 무엇인가요?", subject);
            assertEquals("sbb에 대해서 알고 싶습니다.", content);
        }
    }

    // and 검색
    @Test
    void testFindBySubjectAndContent() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(9, q.getId());
    }
    // like 검색
    @Test
    void testFindBySubjectLike() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.getFirst();
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    // 업데이트
    @Test
    void testUpdateQuestion() {
        Optional<Question> oq = this.questionRepository.findById(9);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    // 삭제
    @Test
    void testDeleteQuestion() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(9);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }


}
