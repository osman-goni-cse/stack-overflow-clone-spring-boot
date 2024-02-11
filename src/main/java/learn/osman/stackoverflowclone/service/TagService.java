package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TagService {
    List<Tag> tagList = new ArrayList<>(Arrays.asList(
            new Tag("javascript", "For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript)."),
            new Tag("python", "Python is a dynamically typed, multi-purpose programming language. It is designed to be quick to learn, understand, and use, and enforces"),
            new Tag("java", "Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself."),
            new Tag("mysql", "MySQL is a free, open-source Relational Database Management System (RDBMS) that uses Structured Query Language (SQL). DO NOT ")
    ));

    public List<Tag> findAllTags() {
        return tagList;
    }
}
