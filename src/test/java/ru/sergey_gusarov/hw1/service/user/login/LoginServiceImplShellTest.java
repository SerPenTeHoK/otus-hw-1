package ru.sergey_gusarov.hw1.service.user.login;

import org.junit.jupiter.api.Test;
import ru.sergey_gusarov.hw1.dao.PersonDao;
import ru.sergey_gusarov.hw1.dao.PersonDaoSimple;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.user.PersonService;
import ru.sergey_gusarov.hw1.service.user.PersonServiceImpl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceImplShellTest {

    @Test
    void getUser() {
        PersonDao personDaoSimple = new PersonDaoSimple();
        PersonService personService = new PersonServiceImpl();
        // Через сеттер, чтобы посмотреть как это всё будет выглядить
        ((PersonServiceImpl) personService).setDao(personDaoSimple);
        LoginServiceImplShell loginServiceImplShell = new LoginServiceImplShell(personService);

        Throwable trySetNullForInputStream = assertThrows(BizLogicException.class, () ->
                loginServiceImplShell.setInputStream(null)
        );
        assertEquals("Передан пустоей поток!", trySetNullForInputStream.getMessage(), "Обратботка передачи пустого потока ввода");

        String text = "Name1\nSurname1";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream in1 = new ByteArrayInputStream(buffer);
        BufferedInputStream bis1 = new BufferedInputStream(in1);

        assertDoesNotThrow(() ->
                        loginServiceImplShell.setInputStream(bis1)
                , "Установка тестового потока ввода прошла успешно");

        AtomicReference<Person> atomicReference = new AtomicReference<Person>();
        assertDoesNotThrow(() -> {
                    atomicReference.set(loginServiceImplShell.getUser());
                }
                , "Пользователь поднят успешно");
        Person firstTryGetPerson = atomicReference.get();
        assertEquals("Name1 Surname1", firstTryGetPerson.getFullName());

        // Проверка на повторное взятие персоны
        ByteArrayInputStream in2 = new ByteArrayInputStream(buffer);
        BufferedInputStream bis2 = new BufferedInputStream(in2);
        assertDoesNotThrow(() ->
                        loginServiceImplShell.setInputStream(bis2)
                , "Установка тестового потока ввода прошла успешно");
        //Повторный вызов пользователя
        assertDoesNotThrow(() -> {
                    Person person = loginServiceImplShell.getUser();
                    atomicReference.set(person);
                }
                , "Пользователь поднят успешно");
        Person secondTryGetPerson = atomicReference.get();
        assertEquals(firstTryGetPerson, secondTryGetPerson, "Одина и таже персона");
    }
}