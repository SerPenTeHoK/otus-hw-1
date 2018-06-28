package ru.sergey_gusarov.hw1.dao;

import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PersonDaoSimple implements PersonDao {
    List<Person> people = new ArrayList<Person>();

    @Override
    public Person findByNameAndSurname(String name, String surname) throws BizLogicException {
        Boolean checkName = (name == null) || (name.length() == 0);
        Boolean checkSurname = (surname == null) || (surname.length() == 0);
        if(checkName && checkSurname)
            throw new BizLogicException("Пустое значение имени и фамилии пользователя");
        if (checkName)
            throw new BizLogicException("Пустое значение имени пользователя");
        if (checkSurname)
            throw new BizLogicException("Пустое значение фамилии пользователя");
        Person person;
        try {
            person = people.stream()
                    .filter(p -> p.getName().equals(name) && p.getSurname().equals(surname))
                    .findFirst().get();
        } catch (NoSuchElementException ex) {
            person = new Person(name, surname);
            people.add(person);
            return person;
        }
        return person;
    }
}
