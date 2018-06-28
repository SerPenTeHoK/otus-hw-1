package ru.sergey_gusarov.hw1.service.read.file;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.sergey_gusarov.hw1.domain.Answer;
import ru.sergey_gusarov.hw1.domain.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class ReadQuestionFileServiceImplCsv implements ReadQuestionFileService {
    final private int QUESTION_START_NUM = 1;
    final private int COUNT_QUESTION_IN_FILE = 5;

    @Override
    public List<Question> loadFile(String fileName) throws FileNotFoundException, IOException {
        List<Question> questionList = new ArrayList<Question>();

        Reader in = null;
        try {
            in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
            Integer countQuestions = QUESTION_START_NUM;
            for (CSVRecord record : records) {
                List<Answer> answers = new ArrayList<Answer>();
                for (Integer i = QUESTION_START_NUM; i < COUNT_QUESTION_IN_FILE; i++) {
                    String colName = "Answer" + i.toString();
                    String answerStr = record.get(colName);
                    Integer score = Integer.valueOf(record.get("Score" + i.toString()));
                    answers.add(new Answer(answerStr, score));
                }
                Integer idQuestion = Integer.valueOf(record.get("Id"));
                String questionStr = record.get("Question");
                Integer checkScore = Integer.valueOf(record.get("CheckScore"));
                Question question = new Question(idQuestion, countQuestions++, questionStr, checkScore, answers);
                questionList.add(question);
            }
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        }
        return questionList;
    }
}
