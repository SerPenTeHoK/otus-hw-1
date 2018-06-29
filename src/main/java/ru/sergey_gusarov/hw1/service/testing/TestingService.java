package ru.sergey_gusarov.hw1.service.testing;

import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtil;

public interface TestingService {

    /**
     * Функция тестирования пользователя
     *
     * @param dataForTesting - данные для тестирования
     * @return Результат тестирования
     * @throws BizLogicException
     */
    IntervieweeResultBase startTest(DataForTestingUtil dataForTesting) throws BizLogicException;


}
