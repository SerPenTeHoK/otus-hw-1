package ru.sergey_gusarov.hw1.domain;

import java.util.ArrayList;
import java.util.List;

public class IntervieweeResult {
    Person person;
    List<Question> questions = new ArrayList<Question>();
    Boolean isTestPass;

    public IntervieweeResult(Person person) {
        this.person = person;
    }

    public Boolean getTestPass() {
        return isTestPass;
    }

    public void setTestPass(Boolean testPass) {
        isTestPass = testPass;
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


    public Integer getSumScore() {
        Integer sumScore = 0;
        for (Question question : questions) {
            for (Answer answer : question.getAnswers()) {
                sumScore += answer.getScore();
            }
        }
        return sumScore;
    }

    public boolean isTestPass() {
        for (Question question : questions) {
            Integer answerScore = 0;
            for (Answer answer : question.getAnswers()) {
                answerScore += answer.getScore();
            }
            if (question.getCheckScore() != answerScore)
                return false;
        }
        return true;
    }

}
