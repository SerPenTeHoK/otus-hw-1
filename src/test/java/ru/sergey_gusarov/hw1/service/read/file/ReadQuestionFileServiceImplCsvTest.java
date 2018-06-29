package ru.sergey_gusarov.hw1.service.read.file;

import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadQuestionFileServiceImplCsvTest {

    @org.junit.jupiter.api.Test
    void loadFile() {
        String fileName = "src/test/resources/testQuestions.csv";
        ReadQuestionFileServiceImplCsv readQuestionFileServiceImplCsv = new ReadQuestionFileServiceImplCsv();
        List<Question> questions = null;
        try {
            questions = readQuestionFileServiceImplCsv.loadFile(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (BizLogicException ex) {
            ex.printStackTrace();
        }
        assertNotNull(questions);
        assertEquals("Мне и так норм", questions.get(1).getAnswers().get(0).getAnswerText());
    }
}