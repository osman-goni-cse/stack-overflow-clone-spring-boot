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

    public TagService(@Lazy QuestionService questionService) {
        this.questionService = questionService;
    }

    public Tag findTagFromTagId(Long tagId) {
        return tagRepository.findByTagId(tagId);
    }

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    public List<Tag> findTagsUsedByUser(Long userId) {
        return tagRepository.tagsUsedByUser(userId);
    }

    public void addNewTag(Tag tagObj) {
        tagRepository.save(tagObj);
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

        for (Question question: questionService.getAllQuestions()) {
            int counter = 0;
            for (Tag tag : question.getTagList()) {
                if (tag.getTagId().equals(currentTag.getTagId())) {
                    question.getTagList().remove(counter);
                    break;
                }
                counter++;
            }
            if (question.getTagList().isEmpty()) {
                questionService.deleteQuestion(question);
            }
        }
        tagRepository.delete(currentTag);
    }

}
