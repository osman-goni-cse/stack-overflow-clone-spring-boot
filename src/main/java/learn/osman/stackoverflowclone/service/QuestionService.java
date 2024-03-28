package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class QuestionService {
    private UserService userService;
    private TagService tagService;
    @Autowired
    private QuestionRepository questionRepository;
    private List<Question> questionList = new ArrayList<>();

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//
//    }
//    @Autowired
//    public void setTagService(TagService tagService) {
//        this.tagService = tagService;
//        initializeQuestionList();
//
//    }
//
//    public QuestionService() {
//    }

    @Autowired
    public QuestionService(UserService userService, TagService tagService) {
        this.userService = userService;
        this.tagService = tagService;
        // Initialize questionList after userService and tagService have been initialized
//        initializeQuestionList();

    }
//
//    private void initializeQuestionList() {
//        questionList = new ArrayList<>(Arrays.asList(
//                new Question(1L, "How do I undo the most recent local commits in Git?",
//                        "I accidentally committed the wrong files to Git, but didn't push the commit to the server yet. How do I undo those commits from the local repository?",
//                        userService.getUser(1L),
//                        tagService.findAllTags()
//                ),
//                new Question(2L, "What and where are the stack and heap?",
//                        "What are the stack and heap? Where are they located physically in a computer's memory? To what extent are they controlled by the OS or language run-time? What is their scope? What determines their",
//                        userService.getUser(2L),
////                        tagService.findAllTags().subList(1, 3))
//                        new ArrayList<>(tagService.findAllTags().subList(1, 3)))
////
//        ));
//    }
//    private void initializeQuestionList() {
//        Question question1 = new Question(1L,
//                "How do I undo the most recent local commits in Git?",
//                "I accidentally committed the wrong files to Git, but didn't push the commit to the server yet. How do I undo those commits from the local repository?",
//                userService.getUser(1L));
//        List<Tag> listOfTagsForQuestion1 = tagService.findAllTags();
//        question1.addTagToTheQuestions(question1, listOfTagsForQuestion1);
//
//        questionList.add(question1);
//    }
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
//        questionList.add(question);
        System.out.println(question);
        questionRepository.save(question);
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }


    public List<Question> getQuestionAskedByUser(Long userId) {
        return questionRepository.questionAskedByUser(userId);
    }
}