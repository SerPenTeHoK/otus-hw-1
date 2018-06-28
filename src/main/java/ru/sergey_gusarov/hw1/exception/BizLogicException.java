package ru.sergey_gusarov.hw1.exception;

public class BizLogicException extends Exception implements ITestException {
    public BizLogicException(String message) {
        super(message);
    }

    public void printMessage() {
        System.err.println("Ошибка в логике работы: " + this.getMessage());
    }
}
