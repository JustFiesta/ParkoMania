package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class AddVehicleController {

    @FXML
    private Label addVehicleSuccesful;

    @FXML
    private Label addVehicleUnsuccesful;

    @FXML
    private TextField checkVehicle;

    @FXML
    private Button goBack;

    @FXML
    private Button submitNewVehicle;

    @FXML
    void submitNewVehicle(ActionEvent event) {
        System.out.println("Debug: Given plate: " + checkVehicle.getText());

        if (checkVehicle.getText().equals("") || !isRegistrationValid(checkVehicle.getText())){
            addVehicleUnsuccesful.setVisible(true);
            System.out.println("Debug: Blank or invalid plate!");
        }else {
            addVehicleUnsuccesful.setVisible(false);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = null;

            //try to send transaction to db
            try {
                transaction = session.beginTransaction();

                Vehicle newVehicle = new Vehicle(0, checkVehicle.getText(), loggedUser);

                session.save(newVehicle);

                transaction.commit();
                addVehicleSuccesful.setVisible(true);
                checkVehicle.clear();

                sceneManager.loadScene("userData", "templates/userData.fxml");
                sceneManager.switchScene("userData");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Debug: Something went wrong");
                e.printStackTrace();
            } finally {
                session.close();
                sessionFactory.close();
                System.out.println("Debug: Session close");
            }

        }
    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("userData");
    }

    public static boolean isRegistrationValid (String plateNumber) {
        // Polski standard tablic rejestracyjnych: jedna lub dwie litery, a następnie od 5 do 7 cyfr
        String plateRegex = "^[A-Z][A-Z0-9 ]*$";

        Pattern pattern = Pattern.compile(plateRegex);
        Matcher matcher = pattern.matcher(plateNumber);

        return matcher.matches();
    }

}
