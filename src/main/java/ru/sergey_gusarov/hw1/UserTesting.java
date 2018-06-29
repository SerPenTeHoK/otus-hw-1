package ru.sergey_gusarov.hw1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtilPrompt;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.login.LoginService;
import ru.sergey_gusarov.hw1.service.read.file.ReadQuestionFileService;
import ru.sergey_gusarov.hw1.service.testing.TestingService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UserTesting {
    public static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("src/main/resources/app.property"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String csvFileForTest = properties.getProperty("testing.question.file");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Person interviewee;
        LoginService loginService = context.getBean(LoginService.class);
        try {
            interviewee = loginService.createNewUser(System.in);
        } catch (BizLogicException e) {
            e.printStackTrace();
            e.printMessage();
            return;
        }
        ReadQuestionFileService readQuestionFileService = context.getBean(ReadQuestionFileService.class);
        List<Question> questions = new ArrayList<Question>();
        try {
            questions = readQuestionFileService.loadFile(csvFileForTest);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        TestingService testingService = context.getBean(TestingService.class);
        DataForTestingUtilPrompt dsPrompt = new DataForTestingUtilPrompt(questions, interviewee, System.in);
        try {
            testingService.startTest(dsPrompt);
        } catch (BizLogicException e) {
            e.printStackTrace();
            e.printMessage();
            return;
        }

        System.out.println("\nТестирование окончено");
    }
}
