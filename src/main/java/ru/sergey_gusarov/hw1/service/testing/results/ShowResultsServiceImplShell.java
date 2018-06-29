package ru.sergey_gusarov.hw1.service.testing.results;

import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.util.ResultCheckHelper;

public class ShowResultsServiceImplShell implements ShowResutlsService {

    @Override
    public void showTestingResult(IntervieweeResultBase intervieweeResult) {
        if (intervieweeResult == null) {
            System.out.println("Не указан пользователь или по нему нет данных");
            return;
        }
        boolean isTestPass;
        Integer sum;
        try {
            isTestPass = ResultCheckHelper.isTestPass(intervieweeResult.getQuestions());
            sum = ResultCheckHelper.getSumScore(intervieweeResult.getQuestions());
        } catch (BizLogicException ex) {
            ex.printMessage();
            return;
        }

        StringBuilder sb = new StringBuilder(120);
        sb.append("Результаты:\n");
        sb.append("Пользователь: ").append(intervieweeResult.getPerson().getFullName());
        if (isTestPass)
            sb.append("\nТест пройден!\n");
        else
            sb.append("\nТест не пройден.\n");
        sb.append("\nНабранные баллы: ").append(sum);
        System.out.println(sb.toString());


    }
}
