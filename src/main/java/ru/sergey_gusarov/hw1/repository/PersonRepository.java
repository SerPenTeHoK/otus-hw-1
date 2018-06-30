package ru.sergey_gusarov.hw1.repository;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public interface PersonRepository {
    Person findByNameAndSurname(String name, String surname) throws BizLogicException;
}
