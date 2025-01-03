package observer;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorNotifier {
    private static ValidationErrorNotifier instance;
    private final List<ValidationErrorObserver> observers = new ArrayList<>();

    private ValidationErrorNotifier() {}

    public static synchronized ValidationErrorNotifier getInstance() {
        if (instance == null) {
            instance = new ValidationErrorNotifier();
        }
        return instance;
    }

    public void addObserver(ValidationErrorObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ValidationErrorObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String errorMessage) {
        for (ValidationErrorObserver observer : observers) {
            observer.onValidationError(errorMessage);
        }
    }
}
