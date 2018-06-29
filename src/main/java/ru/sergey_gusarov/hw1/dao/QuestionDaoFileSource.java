package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.read.file.ReadQuestionFileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class QuestionDaoFileSource implements QuestionDao {
    ReadQuestionFileService readQuestionFileService;

    public void setReadQuestionFileService(ReadQuestionFileService readQuestionFileService) {
        this.readQuestionFileService = readQuestionFileService;
    }

    @Override
    public List<Question> getQuestions() throws IOException, BizLogicException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/app.property"));
        String questionsFileName = properties.getProperty("testing.question.file");
        if (questionsFileName != null)
            return readQuestionFileService.loadFile(questionsFileName);
        else
            throw new BizLogicException("Не указан файл из которого необходимо прочитать вопросы");

    }
}
