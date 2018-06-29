package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions()
            throws IOException, BizLogicException;
}
