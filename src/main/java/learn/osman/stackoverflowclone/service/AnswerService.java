package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerService {
    private QuestionService questionService;

    @Autowired
    public AnswerService(QuestionService questionService) {
        this.questionService = questionService;
        initializerAnswerList();
    }

    private List<Answer> answerList;
    private void initializerAnswerList() {
        answerList = new ArrayList<>(Arrays.asList(
            new Answer("git reset is the command responsible for the undo. It will undo your last commit while leaving your working tree (the state of your files on disk) untouched. You'll need to add them again before you can commit them again.",
                    questionService.getAllQuestions().get(0))
        ));
    }

    public List<Answer> findAllAnswers(Long questionId) {
        List<Answer> result = new ArrayList<>();
        for (Answer answer: answerList) {
            if (answer.getQuestion().getQuestionId().equals(questionId)) {
                result.add(answer);
            }
        }
        return result;
    }
}
