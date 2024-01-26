package com.example.parkomania;

import jakarta.persistence.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class DeleteVehicleController {

    @FXML
    private Label deleteVehicleSuccesful;

    @FXML
    private Label deleteVehicleUnsuccesful;

    @FXML
    private TextField checkVehicle;

    @FXML
    private Button deleteVehicle;

    @FXML
    private Button goBack;

    @FXML
    void deleteVehicle(ActionEvent event) {
        System.out.println("Debug: Given plate: " + checkVehicle.getText());

        if (checkVehicle.getText().equals("") || !isRegistrationValid(checkVehicle.getText())){
            deleteVehicleUnsuccesful.setVisible(true);
            System.out.println("Debug: Blank or invalid plate!");
        }else {
            deleteVehicleUnsuccesful.setVisible(false);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = null;

            //try to send transaction to db
            try {
                transaction = session.beginTransaction();

                Query query = session.createQuery("delete Vehicle where registration = :registrationPlate and user.id = :ownerID");
                query.setParameter("registrationPlate", checkVehicle.getText());
                query.setParameter("ownerID", loggedUser.getUser_id());

                int result = query.executeUpdate();

                if (result > 0) {
                    System.out.println("Debug: Vehicle removed");
                    deleteVehicleSuccesful.setVisible(true);
                }

                transaction.commit();
                checkVehicle.clear();

                sceneManager.loadScene("userData", "templates/userData.fxml");
                sceneManager.loadScene("bookPlace", "templates/bookPlace.fxml");
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
    public static boolean isRegistrationValid (String plateNumber) {
        // Polski standard tablic rejestracyjnych: jedna lub dwie litery, a następnie od 5 do 7 cyfr
        String plateRegex = "^[A-Z][A-Z0-9 ]*$";

        Pattern pattern = Pattern.compile(plateRegex);
        Matcher matcher = pattern.matcher(plateNumber);

        return matcher.matches();
    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("userData");
    }
}
