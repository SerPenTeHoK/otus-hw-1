package ru.sergey_gusarov.hw1.domain;

public class Answer {
    private Integer id;
    private String answerText;
    private Integer score;

    public Answer(Integer id, Integer score) {
        this.id = id;
        this.score = score;
    }

    public Answer(Integer id, String answerText, Integer score) {
        this.id = id;
        this.answerText = answerText;
        this.score = score;
    }

    public Answer(String answerText, Integer score) {

        this.answerText = answerText;
        this.score = score;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerText='" + answerText + '\'' +
                ", score=" + score +
                '}';
    }
}
