package learn.osman.stackoverflowclone.controller;

import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.service.QuestionService;
import learn.osman.stackoverflowclone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    private QuestionService questionService;

    private TagService tagService;

    @Autowired
    public QuestionController(QuestionService questionService, TagService tagService) {
        this.questionService = questionService;
        this.tagService = tagService;
    }

    @GetMapping("/get-all-question")
    public String showAllQuestions(Model model) {
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questions", questionList);
        return "question-list";
    }

    @GetMapping("/ask-new-question")
    public String showQuestionForm(@ModelAttribute("questionObj") Question question, Model model) {
//        List<String> tags = Arrays.asList("Item 1", "Item 2", "Item 3");
        List<Tag> tags = tagService.findAllTags();

        model.addAttribute("tags", tags);
        return "ask-question-form";
    }

    @PostMapping("/create-new-question")
    public String processQuestionForm(@ModelAttribute("questionObj") Question question, Model model) {
//        System.out.println(question);
        List<Tag> tagList = question.getTagList();
        System.out.println(tagList);
        return "redirect:/questions/get-all-question";
    }
}
