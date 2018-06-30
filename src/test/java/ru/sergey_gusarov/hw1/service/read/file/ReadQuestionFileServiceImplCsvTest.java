package ru.sergey_gusarov.hw1.service.read.file;

import org.junit.jupiter.api.DisplayName;
import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadQuestionFileServiceImplCsvTest {

    @org.junit.jupiter.api.Test
    @DisplayName("Чтение файла с вопросами")
    void loadFile() {
        String fileName = "src/test/resources/testQuestions.csv";
        ReadQuestionFileServiceImplCsv readQuestionFileServiceImplCsv = new ReadQuestionFileServiceImplCsv();
        List<Question> questions = null;
        try {
            questions = readQuestionFileServiceImplCsv.loadFile(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            assertTrue(false, "IOException");
        } catch (BizLogicException ex) {
            ex.printStackTrace();
            assertTrue(false, "BizLogicException");
        }
        assertNotNull(questions, "Объект с вопросами пустой");
        assertEquals("Мне и так норм", questions.get(1).getAnswers().get(0).getAnswerText(), "Чтение из файла текста ответа");
    }
}