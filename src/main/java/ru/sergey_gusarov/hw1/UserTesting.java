package ru.sergey_gusarov.hw1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sergey_gusarov.hw1.dao.QuestionDao;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtilShell;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.testing.results.ShowResutlsService;
import ru.sergey_gusarov.hw1.service.user.login.LoginService;
import ru.sergey_gusarov.hw1.service.testing.TestingService;

import java.io.IOException;
import java.util.List;


public class UserTesting {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionDao questionDao = context.getBean(QuestionDao.class);
        LoginService loginService = context.getBean(LoginService.class);
        TestingService testingService = context.getBean(TestingService.class);
        ShowResutlsService showResutlsService = context.getBean(ShowResutlsService.class);

        List<Question> questions = null;
        Person interviewee;
        IntervieweeResultBase intervieweeResult;
        try {
            questions = questionDao.getQuestions();
            interviewee = loginService.getUser(System.in);
            DataForTestingUtilShell dsShell = new DataForTestingUtilShell(questions, interviewee, System.in);
            intervieweeResult = testingService.startTest(dsShell);
            showResutlsService.showTestingResult(intervieweeResult);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BizLogicException e) {
            e.printStackTrace();
            e.printMessage();
            return;
        }

        System.out.println("\nТестирование окончено");
    }
}
