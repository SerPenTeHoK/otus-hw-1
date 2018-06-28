package ru.sergey_gusarov.hw1.service.testing;

import ru.sergey_gusarov.hw1.domain.IntervieweeResult;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtil;

public interface TestingService {

    /**
     * Функция тестирования пользователя
     *
     * @param dataForTesting - данные для тестирования
     * @return Результат прошёли пользователь тест
     * @throws BizLogicException
     */
    IntervieweeResult startTest(DataForTestingUtil dataForTesting) throws BizLogicException;


}
