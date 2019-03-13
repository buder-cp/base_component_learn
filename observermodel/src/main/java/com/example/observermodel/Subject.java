package com.example.observermodel;

public interface Subject {
    void addObserver(Observer observer);
    void notifyObserver(MessageBean message);
    void removeObserver(Observer observer);
    void clearObserver();
}
