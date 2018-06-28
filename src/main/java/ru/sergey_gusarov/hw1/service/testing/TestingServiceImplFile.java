package ru.sergey_gusarov.hw1.service.testing;

import ru.sergey_gusarov.hw1.domain.Answer;
import ru.sergey_gusarov.hw1.domain.IntervieweeResult;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtil;
import ru.sergey_gusarov.hw1.domain.testing.DataForTestingUtilPrompt;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestingServiceImplFile implements TestingService {

    private final String ANSWER_SEPARATOR_SYMBOL = ",";
    private final String HOW_TYPE_ANSWER = "Введите номер ответа. Если несколько, то через запятую, например 2,3 : ";

    @Override
    public IntervieweeResult startTest(DataForTestingUtil dataForTesting) throws BizLogicException {
        if (!(dataForTesting instanceof DataForTestingUtilPrompt))
            throw new BizLogicException("Неверный тип данных для начала теста");
        IntervieweeResult intervieweeResult = new IntervieweeResult(dataForTesting.getPerson());
        Integer countQuestion;
        List<Question> intervieweeQuestions = new ArrayList<Question>();
        countQuestion = dataForTesting.getQuestion().size();
        Scanner inScanner = new Scanner(((DataForTestingUtilPrompt) dataForTesting).getInputStream());
        System.out.println("Начинаем тестирование. Всего вопросов: " + countQuestion.toString());
        for (int i = 0; i < countQuestion; i++) {
            try {
                Question question = dataForTesting.getQuestion().get(i);
                printQuestionAndAnswers(question);
                String answerStr = getPromtAnswer(inScanner);
                Question intervieweeQuestion = getParseAnswerAndSetQuestion(question, answerStr);
                intervieweeQuestions.add(intervieweeQuestion);
            } catch (BizLogicException ex) {
                ex.printStackTrace();
                ex.printMessage();
                throw new BizLogicException("Ошибка в логике провередении тестирования.");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new BizLogicException("Серёзная ошибка при провередении тестирования.");
            }
        }
        intervieweeResult.setQuestions(intervieweeQuestions);

        printResult(intervieweeResult);
        return intervieweeResult;
    }

    public void printResult(IntervieweeResult intervieweeResult) {
        if (intervieweeResult != null) {
            StringBuilder sb = new StringBuilder(120);
            sb.append("Результаты:\n");
            sb.append("Пользователь: ").append(intervieweeResult.getPerson().getFullName());
            if (intervieweeResult.isTestPass())
                sb.append("\nТест пройден!\n");
            else
                sb.append("\nТест не пройден.\n");
            sb.append("\nНабранные баллы: ").append(intervieweeResult.getSumScore());
            System.out.println(sb.toString());
        } else
            System.out.println("Не указан пользователь или по нему нет данных");
    }


    private String getPromtAnswer(Scanner scanner) {
        System.out.print(HOW_TYPE_ANSWER);
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
