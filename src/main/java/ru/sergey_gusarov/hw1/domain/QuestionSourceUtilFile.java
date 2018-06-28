package ru.sergey_gusarov.hw1.domain;

public class QuestionSourceUtilFile implements QuestionSourceUtil {
    String fileName;

    public QuestionSourceUtilFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }


}
