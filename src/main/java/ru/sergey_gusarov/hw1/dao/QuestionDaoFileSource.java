package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.QuestionSourceUtil;
import ru.sergey_gusarov.hw1.domain.QuestionSourceUtilFile;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.read.file.ReadQuestionFileService;

import java.io.IOException;
import java.util.List;

public class QuestionDaoFileSource implements QuestionDao {
    ReadQuestionFileService readQuestionFileService;

    public void setReadQuestionFileService(ReadQuestionFileService readQuestionFileService) {
        this.readQuestionFileService = readQuestionFileService;
    }

    @Override
    public List<Question> getQuestions(QuestionSourceUtil questionSourceUtil) throws IOException, BizLogicException {
        if(questionSourceUtil == null)
            throw new BizLogicException("Передан пустой объект источника вопросов");
        if(questionSourceUtil instanceof QuestionSourceUtilFile) {
            QuestionSourceUtilFile questionSourceUtilFile =(QuestionSourceUtilFile) questionSourceUtil;
            String questionsFileName = questionSourceUtilFile.getFileName();
            if (questionsFileName != null )
                return readQuestionFileService.loadFile(questionsFileName);
            else
                throw new BizLogicException("Не указан файл из которого необходимо прочитать вопросы");
        }
        else
            throw new BizLogicException("Передан не верный тип источника вопросов");

    }
}
