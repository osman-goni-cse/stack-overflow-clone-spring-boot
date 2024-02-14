package learn.osman.stackoverflowclone.controller;

import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create-tag")
    public String showTagForm(@ModelAttribute("tagObj") Tag tagObj) {
        return "create-tag-form";
    }

    @PostMapping("/create-tag")
    public String createTag(@ModelAttribute("tagObj") Tag tagObj, Model model) {
        boolean isTagObjectBlank = false;
        if (tagObj.getTagId() == null) {
            isTagObjectBlank = true;
            model.addAttribute("tagIdError", "Tag ID is required");
        }
        if (tagObj.getTagName() == null || tagObj.getTagName().trim().isEmpty()) {
            isTagObjectBlank = true;
            model.addAttribute("tagNameError", "Tag Name is required");
        }
        if (tagObj.getTagDetails().trim().isEmpty()) {
            isTagObjectBlank = true;
            model.addAttribute("tagDetailsError", "Tag Details is required");
        }

        if(isTagObjectBlank) {
//            System.out.println("Tag Obejct is null");
            return "create-tag-form";
        }
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
    @GetMapping("/delete-tag/{tagId}")
    public String deleteTag(@PathVariable("tagId") Long tagId, @ModelAttribute("tagObj") Tag tagObj, Model model) {
        System.out.println("invoked controller");
        tagService.deleteTag(tagId);
        List<Tag> tagList = tagService.findAllTags();
        model.addAttribute("tags", tagList);
        return "tag-list";
    }
}
