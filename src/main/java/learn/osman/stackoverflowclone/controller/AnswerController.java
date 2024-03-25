package learn.osman.stackoverflowclone.controller;

import learn.osman.stackoverflowclone.entity.Answer;
import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.service.AnswerService;
import learn.osman.stackoverflowclone.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class AnswerController {
    private AnswerService answerService;
    private QuestionService questionService;
    @Autowired
    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping("/{questionId}/answers")
    public String showAnswers(@PathVariable("questionId") Long questionId, Model model) {
        Question question = questionService.getQuestionFromId(questionId);
//        List<Answer> answerList = answerService.findAllAnswers(questionId);
//        model.addAttribute("answers", answerList);
        model.addAttribute("question", question);
        return "answer-list";
    }
}
