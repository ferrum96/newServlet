package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    public Model() {
        model = new HashMap<>();

        model.put(1, new User("Вася", "Пупкин", 65000));
        model.put(2, new User("Denis", "Denisov", 70000));
        model.put(3, new User("Bogdan", "Titomir", 35000));
        model.put(4, new User("Жора", "Крыжовников", 35020));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
