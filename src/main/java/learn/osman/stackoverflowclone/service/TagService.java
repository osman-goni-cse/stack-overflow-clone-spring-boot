package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class TagService {
    List<Tag> tagList = new ArrayList<>(Arrays.asList(
            new Tag(1L, "javascript", "For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript)."),
            new Tag(2L, "python", "Python is a dynamically typed, multi-purpose programming language. It is designed to be quick to learn, understand, and use, and enforces"),
            new Tag(3L, "java", "Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself."),
            new Tag(4L, "mysql", "MySQL is a free, open-source Relational Database Management System (RDBMS) that uses Structured Query Language (SQL). DO NOT ")
    ));

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

        Iterator<Tag> iterator = tagList.iterator();
        while (iterator.hasNext()) {
            Tag tag = iterator.next();
            if (tag.getTagId().equals(tagId)) {
                iterator.remove();
            }
        }
    }

//    public boolean isAllFieldValid() {
//
//    }
}
