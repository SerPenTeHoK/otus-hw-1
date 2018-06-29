package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.read.file.ReadQuestionFileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class QuestionDaoFileSource implements QuestionDao {
    private final ReadQuestionFileService readQuestionFileService;

    QuestionDaoFileSource(ReadQuestionFileService readQuestionFileService) {
        this.readQuestionFileService = readQuestionFileService;
    }

    @Override
    public List<Question> getAllQuestions() throws IOException, BizLogicException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/app.property"));
        String questionsFileName = properties.getProperty("testing.question.file",
                "src/main/resources/testQuestions.csv");
        if (questionsFileName != null)
            return readQuestionFileService.loadFile(questionsFileName);
        else
            throw new BizLogicException("Не указан файл из которого необходимо прочитать вопросы");

    }
}
