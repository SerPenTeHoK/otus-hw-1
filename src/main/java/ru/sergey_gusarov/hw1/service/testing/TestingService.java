package ru.sergey_gusarov.hw1.service.testing;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.util.List;

public interface TestingService {

    IntervieweeResultBase startTest(List<Question> questions, Person interviewee) throws BizLogicException;


}
