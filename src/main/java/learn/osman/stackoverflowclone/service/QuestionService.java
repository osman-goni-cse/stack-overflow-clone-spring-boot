package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    private UserService userService;
    private TagService tagService;
    @Autowired
    private QuestionRepository questionRepository;
    private List<Question> questionList = new ArrayList<>();


    @Autowired
    public QuestionService(UserService userService, TagService tagService) {
        this.userService = userService;
        this.tagService = tagService;

    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionFromId(Long questionId) {
        for (Question question: questionRepository.findAll()) {
            if (question.getQuestionId().equals(questionId)) {
                return question;
            }
        }
        return null;
    }
    public List<Tag> getTagListFromIds(List<Long> tagIds) {
        List<Tag> tags = new ArrayList<>();
        for (Long id: tagIds) {
            tags.add(tagService.findTagFromTagId(id));
        }
        return tags;
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }

    public List<Question> getQuestionAskedByUser(Long userId) {
        return questionRepository.questionAskedByUser(userId);
    }

    public List<Question> getQuestionWithSpecificTag(Long tagId) {
        return questionRepository.questionWithSpecificTag(tagId);
    }

    public Map<Tag, Integer> questionCountBasedOnTag() {

        Map<Tag, Integer> mapTag = new HashMap<>();

        for (Tag tag: tagService.findAllTags()) {
            mapTag.put(tag, questionRepository.countQuestionBasedOnTag(tag.getTagId()));
        }

        return mapTag;
    }
}