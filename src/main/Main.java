package src.main;

import src.controller.Controller;
import src.model.Model;
import src.view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View(), new Model());
        controller.start();
    }
}