package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Question;
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
    public QuestionService(UserService userService, TagService tagService) {
        this.userService = userService;
        this.tagService = tagService;

        // Initialize questionList after userService and tagService have been initialized
        initializeQuestionList();
    }

    private List<Question> questionList;

    private void initializeQuestionList() {
        questionList = new ArrayList<>(Arrays.asList(
                new Question(1L, "How do I undo the most recent local commits in Git?",
                        "I accidentally committed the wrong files to Git, but didn't push the commit to the server yet. How do I undo those commits from the local repository?",
                        userService.getAllUserList().get(0),
                        tagService.findAllTags()
                ),
                new Question(2L, "What and where are the stack and heap?",
                        "What are the stack and heap? Where are they located physically in a computer's memory? To what extent are they controlled by the OS or language run-time? What is their scope? What determines their",
                        userService.getAllUserList().get(1),
                        tagService.findAllTags().subList(1, 2)
                        )
        ));
    }

    public List<Question> getAllQuestions() {
        return questionList;
    }

    public Question getQuestionFromId(Long questionId) {
        for (Question question: questionList) {
            if (question.getQuestionId().equals(questionId)) {
                return question;
            }
        }
        return null;
    }
}
//@Service
//public class QuestionService {
//    private UserService userService;
//    private TagService tagService;
//    @Autowired
//    public QuestionService(UserService userService, TagService tagService) {
//        this.userService = userService;
//        this.tagService = tagService;
//    }
//
//    List<Question> questionList = new ArrayList<>(Arrays.asList(
//            new Question("How do I undo the most recent local commits in Git?",
//                    "I accidentally committed the wrong files to Git, but didn't push the commit to the server yet. How do I undo those commits from the local repository?",
//                    userService.getAllUserList().get(0),
//                    tagService.findAllTags()
//                    )
//    ));
//
//    public List<Question> getAllQuestions() {
//        return questionList;
//    }
//}
