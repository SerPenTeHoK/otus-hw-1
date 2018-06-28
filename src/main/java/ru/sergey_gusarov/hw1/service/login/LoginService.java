package ru.sergey_gusarov.hw1.service.login;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;


import java.io.InputStream;

public interface LoginService {
    Person createNewUser(InputStream inputStream) throws BizLogicException;
}
