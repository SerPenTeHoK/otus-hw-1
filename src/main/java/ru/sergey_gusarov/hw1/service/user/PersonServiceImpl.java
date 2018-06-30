package ru.sergey_gusarov.hw1.service.user;

import ru.sergey_gusarov.hw1.repository.PersonRepository;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

public class PersonServiceImpl implements PersonService {
    private PersonRepository dao;

    public void setDao(PersonRepository dao) {
        this.dao = dao;
    }

    public Person getByNameAndSurname(String name, String surname) throws BizLogicException {
        return dao.findByNameAndSurname(name, surname);
    }
}
