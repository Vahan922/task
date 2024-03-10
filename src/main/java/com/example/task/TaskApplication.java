package com.example.task;


import com.example.task.dao.model.Message;
import com.example.task.dao.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class TaskApplication implements CommandLineRunner {

    @Autowired
    private MessageRepository messageRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<Message> messages = messageRepository.findAll();

        messages.forEach(System.out::println);

    }
}
