package ru.sergey_gusarov.hw1.service;

import ru.sergey_gusarov.hw1.dao.PersonDao;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public class PersonServiceImpl implements PersonService {
    private PersonDao dao;

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name, String surname) throws BizLogicException {
        return dao.findByNameAndSurname(name, surname);
    }
}
