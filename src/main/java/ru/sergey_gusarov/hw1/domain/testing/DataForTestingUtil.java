package ru.sergey_gusarov.hw1.domain.testing;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;

import java.util.List;

public interface DataForTestingUtil {
    List<Question> getQuestion();

    Person getPerson();
}
