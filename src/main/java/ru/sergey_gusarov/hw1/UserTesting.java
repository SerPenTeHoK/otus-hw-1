package ru.sergey_gusarov.hw1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sergey_gusarov.hw1.dao.QuestionDao;
import ru.sergey_gusarov.hw1.domain.Person;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.service.testing.TestingService;
import ru.sergey_gusarov.hw1.service.testing.results.ShowResutlsService;
import ru.sergey_gusarov.hw1.service.user.login.LoginService;

import java.io.IOException;
import java.util.List;


public class UserTesting {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionDao questionDao = context.getBean(QuestionDao.class);
        LoginService loginService = context.getBean(LoginService.class);
        TestingService testingService = context.getBean(TestingService.class);
        ShowResutlsService showResutlsService = context.getBean(ShowResutlsService.class);

        try {
            List<Question> questions = questionDao.getAllQuestions();
            Person interviewee = loginService.getUser();
            IntervieweeResultBase intervieweeResult = testingService.startTest(questions, interviewee);
            showResutlsService.showTestingResult(intervieweeResult);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("\nТестирование перервано обратитесь к разработчику, скопировав все текст ошибки");
            return;
        } catch (BizLogicException ex) {
            ex.printStackTrace();
            ex.printMessage();
            return;
        }

        System.out.println("\nТестирование окончено");
    }
}
