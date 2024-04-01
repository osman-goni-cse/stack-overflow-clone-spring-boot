package learn.osman.stackoverflowclone.controller;

import jakarta.validation.Valid;
import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.service.QuestionService;
import learn.osman.stackoverflowclone.service.TagService;
import learn.osman.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tags")
public class TagController {

    private QuestionService questionService;
    private TagService tagService;
    private UserService userService;

    @Autowired
    public TagController(QuestionService questionService, TagService tagService, UserService userService) {
        this.questionService = questionService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping("/get-all-tag")
    public String showAllTags(@ModelAttribute("tagObj") Tag tagObj, Model model) {
        List<Tag> tagList = tagService.findAllTags();
        Map<Tag, Integer> mapTag = questionService.questionCountBasedOnTag();
        Map<Long, Long> mapUserCount = userService.countUsersByTag();

        model.addAttribute("tags", tagList);
        model.addAttribute("mapTag", mapTag);
        model.addAttribute("mapUserCount", mapUserCount);

        return "tag-list";
    }

    @GetMapping("/create-tag")
    public String showTagForm(@ModelAttribute("tagObj") Tag tagObj) {
        return "create-tag-form";
    }

    @PostMapping("/create-tag")
    public String createTag(@ModelAttribute("tagObj") @Valid Tag tagObj, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "create-tag-form";
        }
        tagService.addNewTag(tagObj);

        return "redirect:/tags/get-all-tag";
    }

    @PostMapping("/update-tag")
    public String updateTag(@ModelAttribute("tagObj") @Valid Tag tagObj, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/tags/get-all-tag";
        }
        tagService.updateTag(tagObj);
        return "redirect:/tags/get-all-tag";
    }

    @GetMapping("/delete-tag/{tagId}")
    public String deleteTag(@PathVariable("tagId") Long tagId, @ModelAttribute("tagObj") Tag tagObj) {
        tagService.deleteTag(tagId);
        return "redirect:/tags/get-all-tag";
    }

    @GetMapping("/used-by-user/{userId}")
    public String showTagsUsedByUser(@ModelAttribute("tagObj") Tag tagObj, @PathVariable("userId") Long userId, Model model) {
        List<Tag> tagList = tagService.findTagsUsedByUser(userId);
        model.addAttribute("tags", tagList);
        return "tags-list-page-in-user-profile";
    }
}
