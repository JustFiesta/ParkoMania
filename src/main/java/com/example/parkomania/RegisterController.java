package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static com.example.parkomania.HelloApplication.sceneManager;

public class RegisterController {

    @FXML
    private TextField checkPassword;

    @FXML
    private TextField checkPasswordAgain;

    @FXML
    private TextField checkPhoneNumber;

    @FXML
    private Button goBack;

    @FXML
    private Button subimtNewUser;

    @FXML
    void checkPassword(ActionEvent event) {

    }

    @FXML
    void checkPasswordAgain(ActionEvent event) {

    }

    @FXML
    void checkPhoneNumber(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("loginAndRegister");
    }

    @FXML
    void submittNewUser(ActionEvent event) {
        User newUser = new User(0, "543534543", "chuj@chuj.gmail.com", "easweasbgera2q3", UserType.USER);

        //create session factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(newUser);
            transaction.commit();
            System.out.println("New user added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Something went wrong");
            e.printStackTrace();
        } finally {
            session.close();
            System.out.println("Close");
        }
        sessionFactory.close();
    }
}
