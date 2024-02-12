package learn.osman.stackoverflowclone.controller;

import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/get-all-tag")
    public String showAllTags(@ModelAttribute("tagObj") Tag tagObj, Model model) {
        List<Tag> tagList = tagService.findAllTags();
        model.addAttribute("tags", tagList);
        return "tag-list";
    }

    @PostMapping("/create-tag")
    public String createTag(@ModelAttribute("tagObj") Tag tagObj, Model model) {
        tagService.addNewTag(tagObj);

        List<Tag> tagList = tagService.findAllTags();
        model.addAttribute("tags", tagList);
        return "tag-list";
    }

    @PostMapping("/update-tag")
    public String updateTag(@ModelAttribute("tagObj") Tag tagObj, Model model) {
        tagService.updateTag(tagObj);
        List<Tag> tagList = tagService.findAllTags();
        model.addAttribute("tags", tagList);
        return "tag-list";
    }
}
