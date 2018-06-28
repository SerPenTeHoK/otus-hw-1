package ru.sergey_gusarov.hw1.domain;

import java.util.List;

public class Question {
    private Integer id;
    private Integer ordinalNum;
    private String questionText;
    private Integer checkScore;
    private List<Answer> answers;

    public Question(Integer id, Integer ordinalNum, String questionText, Integer checkScore, List<Answer> answers) {
        this.id = id;
        this.ordinalNum = ordinalNum;
        this.questionText = questionText;
        this.checkScore = checkScore;
        this.answers = answers;
    }

    public Question(Integer ordinalNum, String questionText, Integer checkScore, List<Answer> answers) {
        this.id = -1;
        this.ordinalNum = ordinalNum;
        this.questionText = questionText;
        this.checkScore = checkScore;
        this.answers = answers;
    }

    public Question(Integer id, Integer checkScore, List<Answer> answers) {
        this.id = id;
        this.checkScore = checkScore;
        this.answers = answers;
    }

    public Question(Integer id, List<Answer> answers) {
        this.id = id;
        this.answers = answers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckScore() {
        return checkScore;
    }

    public void setCheckScore(Integer checkScore) {
        this.checkScore = checkScore;
    }

    public Integer getOrdinalNum() {
        return ordinalNum;
    }

    public void setOrdinalNum(Integer ordinalNum) {
        this.ordinalNum = ordinalNum;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
