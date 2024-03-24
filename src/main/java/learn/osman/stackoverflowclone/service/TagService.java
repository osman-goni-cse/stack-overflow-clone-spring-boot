package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.Tag;
import learn.osman.stackoverflowclone.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;
    private QuestionService questionService;
    List<Tag> tagList = new ArrayList<>();

    public TagService(@Lazy QuestionService questionService) {
        this.questionService = questionService;
    }

    public Tag findTagFromTagId(Long tagId) {
        for (Tag tag : tagList) {
            if (tag.getTagId().equals(tagId)) {
                return tag;
            }
        }
        return null;
    }

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    public void addNewTag(Tag tagObj) {
        tagRepository.save(tagObj);
//        tagList.add(tagObj);
    }

    public void updateTag(Tag updatedTag) {
        int counter = 0;

        Tag currentTag = tagRepository.findById(updatedTag.getTagId()).orElseThrow(
                () -> new NoSuchElementException("Tag Not found.")
        );

        currentTag.setTagName(updatedTag.getTagName());
        currentTag.setTagDetails(updatedTag.getTagDetails());

        tagRepository.save(currentTag);

        for (Question question : questionService.getAllQuestions()) {
            counter = 0;
            for (Tag tag : question.getTagList()) {
                if (tag.getTagId().equals(updatedTag.getTagId())) {
                    question.getTagList().set(counter, updatedTag);
                    break;
                }
                counter++;
            }
        }
    }

    public void deleteTag(Long tagId) {
        Tag currentTag = tagRepository.findById(tagId).orElseThrow(
                () -> new NoSuchElementException("Tag Not found.")
        );
        tagRepository.delete(currentTag);
    }

    public Map<Tag, Integer> questionCountBasedOnTag() {

        Map<Tag, Integer> mapTag = new HashMap<>();

//        for(Tag tag: this.findAllTags()) {
//            mapTag.put(tag, 0);
//        }

        for (Question question : questionService.getAllQuestions()) {
            for (Tag tag : question.getTagList()) {
                mapTag.put(tag, mapTag.getOrDefault(tag, 0) + 1);
            }
        }
        return mapTag;
    }
}
