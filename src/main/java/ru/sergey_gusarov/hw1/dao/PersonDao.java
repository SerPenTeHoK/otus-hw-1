package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public interface PersonDao {
    Person findByNameAndSurname(String name, String surname) throws BizLogicException;
}
