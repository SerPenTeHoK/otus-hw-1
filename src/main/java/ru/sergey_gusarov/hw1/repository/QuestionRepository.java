package ru.sergey_gusarov.hw1.repository;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.io.IOException;
import java.util.List;

public interface QuestionRepository {
    List<Question> findAll()
            throws IOException, BizLogicException;
}
