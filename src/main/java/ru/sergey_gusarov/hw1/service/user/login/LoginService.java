package ru.sergey_gusarov.hw1.service.user.login;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public interface LoginService {
    Person getUser() throws BizLogicException;
}
