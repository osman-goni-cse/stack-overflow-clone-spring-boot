package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class TagService {

    private QuestionService questionService;
    List<Tag> tagList;

//    @Autowired
//    public void setQuestionService(QuestionService questionService) {
//        this.questionService = questionService;
//    }
    public TagService(@Lazy QuestionService questionService) {
        this.questionService = questionService;
        this.tagList = new ArrayList<>(Arrays.asList(
                new Tag(1L, "javascript", "For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript)."),
                new Tag(2L, "python", "Python is a dynamically typed, multi-purpose programming language. It is designed to be quick to learn, understand, and use, and enforces"),
                new Tag(3L, "java", "Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself."),
                new Tag(4L, "mysql", "MySQL is a free, open-source Relational Database Management System (RDBMS) that uses Structured Query Language (SQL). DO NOT ")
        ));
    }
    public Tag findTagFromTagId(Long tagId) {
        for (Tag tag: tagList) {
            if(tag.getTagId().equals(tagId)) {
                return tag;
            }
        }
        return null;
    }
    public List<Tag> findAllTags() {
        return tagList;
    }
    public void addNewTag(Tag tagObj) {

        tagList.add(tagObj);
    }
    public void updateTag(Tag updatedTag) {
        int counter = 0;
        for (Tag tag: tagList) {
            if (tag.getTagId().equals(updatedTag.getTagId())) {
                tagList.set(counter, updatedTag);
            }
            counter++;
        }
        counter = 0;
        for (Question question: questionService.getAllQuestions()) {
            for (Tag tag: question.getTagList()) {
                if (tag.getTagId().equals(updatedTag.getTagId())) {
                    question.getTagList().set(counter, updatedTag);
                }
                counter++;
            }
        }
    }
    public void deleteTag(Long tagId) {
//        This code gives ConcurrentModificationError
//        int counter = 0;
//        for(Tag currentTag: tagList) {
//            if (currentTag.getTagId().equals(tagId)) {
//                tagList.remove(counter);
//            }
//            counter++;
//        }
//        for (Question question: questionService.getAllQuestions()) {
//            for (Tag tag: question.getTagList()) {
//                if (tag.getTagId().equals(tagId)) {
//                    question.removeTag(question, findTagFromTagId(tagId));
//                }
//            }
//        }
        Iterator<Question> questionIterator = questionService.getAllQuestions().iterator();
        while (questionIterator.hasNext()) {
            Question question = questionIterator.next();

            Iterator<Tag> tagIterator = tagList.iterator();
            while (tagIterator.hasNext()) {
                Tag tag = tagIterator.next();

                if (tag.getTagId().equals(tagId)) {
                    // Assuming you have a method to find the tag from tagId in the question
                    question.removeTag(question, findTagFromTagId(tagId));

                    // Remove the tag from the iterator to avoid ConcurrentModificationException
                    tagIterator.remove();
                }
            }

            // If the question's tagList is empty, remove the question
//            if (question.getTagList().isEmpty()) {
//                questionIterator.remove();
//            }
        }
// The below code works perfectly
//        Iterator<Tag> iterator = tagList.iterator();
//        while (iterator.hasNext()) {
//            Tag tag = iterator.next();
//            if (tag.getTagId().equals(tagId)) {
//                iterator.remove();
//            }
//        }
    }

//    public boolean isAllFieldValid() {
//
//    }
}
