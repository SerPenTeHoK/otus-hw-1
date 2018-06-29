package ru.sergey_gusarov.hw1.domain.results;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;

import java.util.ArrayList;
import java.util.List;

public abstract class IntervieweeResultBase {
    Person person;
    List<Question> questions = new ArrayList<Question>();

    public IntervieweeResultBase(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
