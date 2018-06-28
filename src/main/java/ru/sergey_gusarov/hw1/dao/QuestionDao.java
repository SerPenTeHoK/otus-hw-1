package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws IOException;
}
