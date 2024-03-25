package learn.osman.stackoverflowclone.controller;

import jakarta.servlet.http.HttpSession;
import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.service.QuestionService;
import learn.osman.stackoverflowclone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
        for (Question question: questionList) {
            System.out.println(question.getTagList());
        }
        System.out.println(questionList);
        model.addAttribute("questions", questionList);
        return "question-list";
    }

    @GetMapping("/ask-new-question")
    public String showQuestionForm(@ModelAttribute("questionObj") Question question, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if(session.getAttribute("loggedInUser") == null) {
//            RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
            redirectAttributes.addFlashAttribute("userIsNotLoggedIn", "You must be logged in to ask a question on DSi Overflow");
            System.out.println(redirectAttributes);
            return "redirect:/users/login";
        }


        List<Tag> tags = tagService.findAllTags();

        model.addAttribute("tags", tags);
        return "ask-question-form";
    }

    @PostMapping("/create-new-question")
    public String processQuestionForm(@ModelAttribute("questionObj") Question question,
                                      @RequestParam(value = "selectedTags", required = false) List<Long> selectedTags,
                                      Model model,
                                      HttpSession session) {


        boolean isAnyQuestionFieldBlank = false;

//        if(question.getQuestionId() == null) {
//            isAnyQuestionFieldBlank = true;
//            model.addAttribute("questionIdError", "Question ID is required");
//        }

        if(question.getQuestionTitle().trim().isEmpty()) {
            isAnyQuestionFieldBlank = true;
            model.addAttribute("questionTitleError", "Question Title is required");
        }

        if(question.getQuestionDetails().trim().isEmpty()) {
            isAnyQuestionFieldBlank = true;
            model.addAttribute("questionDetailError", "Question Detail is required");
        }

        if (selectedTags == null) {
            isAnyQuestionFieldBlank = true;
            model.addAttribute("tagError", "Tag is required");
        }

        if(isAnyQuestionFieldBlank) {
            List<Tag> tags = tagService.findAllTags();

            model.addAttribute("tags", tags);
            return "ask-question-form";
        }

//        System.out.println(question);
        System.out.println(selectedTags);
        List<Tag> tagList = questionService.getTagListFromIds(selectedTags);
//        List<Tag> tagList =
        System.out.println(tagList);

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        System.out.println("Before setting: " + question.getTagList());
        question.setTagList(tagList);
        System.out.println("After setting: " + question.getTagList());
//        question.addTagToTheQuestions(question, tagList);
        question.setUser(loggedInUser);

        System.out.println(question);

        questionService.addQuestion(question);

        return "redirect:/questions/get-all-question";
    }
}
