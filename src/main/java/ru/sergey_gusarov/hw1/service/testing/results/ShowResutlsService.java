package ru.sergey_gusarov.hw1.service.testing.results;

import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public interface ShowResutlsService {
    void showTestingResult(IntervieweeResultBase intervieweeResult) throws BizLogicException;
}
