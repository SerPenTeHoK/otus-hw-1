package ru.sergey_gusarov.hw1.domain.testing;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;

import java.io.InputStream;
import java.util.List;

public class DataForTestingUtilPrompt implements DataForTestingUtil {
    List<Question> questions;
    Person person;
    InputStream inputStream;

    public DataForTestingUtilPrompt(List<Question> questions,
                                    Person person, InputStream inputStream) {
        this.questions = questions;
        this.person = person;
        this.inputStream = inputStream;
    }

    @Override
    public List<Question> getQuestion() {
        return questions;
    }

    public Person getPerson() {
        return person;
    }

    public InputStream getInputStream() {
        return inputStream;
    }


}
