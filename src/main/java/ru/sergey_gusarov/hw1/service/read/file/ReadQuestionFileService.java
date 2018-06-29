package ru.sergey_gusarov.hw1.service.read.file;


import ru.sergey_gusarov.hw1.domain.Question;
import ru.sergey_gusarov.hw1.exception.BizLogicException;

import java.io.IOException;
import java.util.List;

public interface ReadQuestionFileService {
    List<Question> loadFile(String fileName) throws IOException, BizLogicException;
}
