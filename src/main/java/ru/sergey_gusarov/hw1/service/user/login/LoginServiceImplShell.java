package ru.sergey_gusarov.hw1.service.user.login;

import org.springframework.stereotype.Service;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.user.PersonService;


import java.io.InputStream;
import java.util.Scanner;

@Service
public class LoginServiceImplShell implements LoginService {
    PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Person getUser(InputStream inputStream) throws BizLogicException {
        String name;
        String surname;

        Scanner in = new Scanner(inputStream);
        System.out.print("Введите имя: ");
        name = in.nextLine();
        System.out.print("Введите фамилию: ");
        surname = in.nextLine();

        return personService.getByNameAndSurname(name, surname);
    }
}
