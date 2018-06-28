package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.service.read.file.ReadQuestionFileService;

import java.io.IOException;
import java.util.List;

public class QuestionDaoFileSource implements QuestionDao {
    ReadQuestionFileService readQuestionFileService;

    public void setReadQuestionFileService(ReadQuestionFileService readQuestionFileService) {
        this.readQuestionFileService = readQuestionFileService;
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        return readQuestionFileService.loadFile("/src/main/resources/questionsSample.csv");
    }
}
