package ru.sergey_gusarov.hw1.service.testing;

import ru.sergey_gusarov.hw1.domain.Answer;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultSimple;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtil;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtilShell;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestingServiceImplFile implements TestingService {

    private final String ANSWER_SEPARATOR_SYMBOL = ",";
    private final String HOW_TYPE_ANSWER = "Введите номер ответа. Если несколько, то через запятую, например 2,3 : ";

    @Override
    public IntervieweeResultBase startTest(DataForTestingUtil dataForTesting) throws BizLogicException {
        if (!(dataForTesting instanceof DataForTestingUtilShell))
            throw new BizLogicException("Неверный тип данных для начала теста");
        IntervieweeResultBase intervieweeResultBase = new IntervieweeResultSimple(dataForTesting.getPerson());
        Integer countQuestion;
        List<Question> intervieweeQuestions = new ArrayList<Question>();
        countQuestion = dataForTesting.getQuestion().size();

        Scanner inScanner = new Scanner(((DataForTestingUtilShell) dataForTesting).getInputStream());
        System.out.println("Начинаем тестирование. Всего вопросов: " + countQuestion.toString());
        try {
            for (int i = 0; i < countQuestion; i++) {
                Question question = dataForTesting.getQuestion().get(i);
                printQuestionAndAnswers(question);
                String answerStr = getShellAnswer(inScanner);
                Question intervieweeQuestion = getParseAnswerAndSetQuestion(question, answerStr);
                intervieweeQuestions.add(intervieweeQuestion);
            }
        } catch (BizLogicException ex) {
            ex.printStackTrace();
            ex.printMessage();
            throw new BizLogicException("Ошибка в логике провередении тестирования.");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BizLogicException("Серёзная ошибка при провередении тестирования.");
        }
        intervieweeResultBase.setQuestions(intervieweeQuestions);
        return intervieweeResultBase;
    }


    private String getShellAnswer(Scanner scanner) {
        System.out.println(HOW_TYPE_ANSWER);
        return scanner.nextLine();
    }

    private void printQuestionAndAnswers(Question question) {
        System.out.println("Вопрос №" + question.getOrdinalNum());
        System.out.println(question.getQuestionText());
        System.out.println("Варианты ответа:");
        Integer numAnswer = 1;
        for (Answer ans : question.getAnswers()) {
            System.out.println(numAnswer.toString() + " " + ans.getAnswerText());
            numAnswer++;
        }
    }

    private Question getParseAnswerAndSetQuestion(Question question, String answerStr) throws BizLogicException {
        List<Answer> resultAnswers = new ArrayList<Answer>();
        try {
            if (answerStr.contains(ANSWER_SEPARATOR_SYMBOL)) {
                String multiAnswer[] = answerStr.split(ANSWER_SEPARATOR_SYMBOL);
                for (String strToPacer : multiAnswer)
                    setIntervieweeAnswers(question, resultAnswers, strToPacer);
            } else {
                setIntervieweeAnswers(question, resultAnswers, answerStr);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new BizLogicException("Не удалось распознать, что вы ввели.");
        }
        return new Question(question.getId(), question.getCheckScore(), resultAnswers);
    }

    private void setIntervieweeAnswers(Question question, List<Answer> resultAnswers, String strToPacer) throws NumberFormatException {
        Integer answerNum = Integer.valueOf(strToPacer) - 1;
        Answer resultAnswer = new Answer(question.getAnswers().get(answerNum).getAnswerText(),
                question.getAnswers().get(answerNum).getScore());
        resultAnswers.add(resultAnswer);
    }

}
