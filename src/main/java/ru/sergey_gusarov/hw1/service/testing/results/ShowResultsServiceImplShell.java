package ru.sergey_gusarov.hw1.service.testing.results;

import ru.sergey_gusarov.hw1.domain.results.IntervieweeResultBase;
import ru.sergey_gusarov.hw1.exception.BizLogicException;
import ru.sergey_gusarov.hw1.util.ResultCheckHelper;

public class ShowResultsServiceImplShell implements ShowResutlsService {

    @Override
    public void showTestingResult(IntervieweeResultBase intervieweeResult) throws BizLogicException {
        if (intervieweeResult == null) {
            throw new BizLogicException("Не указан пользователь или по нему нет данных");
        }
        boolean isTestPass;
        Integer sum;
        try {
            isTestPass = ResultCheckHelper.isTestPass(intervieweeResult.getQuestions());
            sum = ResultCheckHelper.getSumScore(intervieweeResult.getQuestions());
        } catch (BizLogicException ex) {
            throw ex;
        }

        StringBuilder sb = new StringBuilder(140);
        sb.append("Результаты:\n");
        sb.append("Пользователь: ").append(intervieweeResult.getPerson().getFullName());
        if (isTestPass)
            sb.append("\nТест пройден! Поздравлем!\n");
        else
            sb.append("\nТест вами не пройден. Поробуйте в следующий раз.\n");
        sb.append("\nНабранные баллы: ").append(sum);
        System.out.println(sb.toString());
    }
}
