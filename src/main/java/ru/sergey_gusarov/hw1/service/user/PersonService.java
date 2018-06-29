package ru.sergey_gusarov.hw1.service.user;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public interface PersonService {

    Person getByNameAndSurname(String name, String surname) throws BizLogicException;
}
