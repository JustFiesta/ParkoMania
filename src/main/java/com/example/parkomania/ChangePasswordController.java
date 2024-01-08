package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class ChangePasswordController {

    @FXML
    private TextField checkPassword;

    @FXML
    private Button goBack;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Label passwordChangeSuccesful;

    @FXML
    private Label passwordChangeSuccesful11;

    @FXML
    private Label passwordChangeUnsuccesful;

    @FXML
    private Button submitNewPassword;
    @FXML
    private Label incorrectPassword;

    @FXML
    void submitNewPassword(ActionEvent event) {
        //check if password is correct

        //if password is empty do not check db
        if (checkPassword.getText().equals("") || checkPassword.getText().equals(newPassword.getText())){
            incorrectPassword.setVisible(true);
            System.out.println("Debug: Wrong password! It is either empty or same as old one");
            newPassword.clear();
        }else {
            incorrectPassword.setVisible(false);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = null;

            //try to send transaction to db
            try {
                transaction = session.beginTransaction();
                loggedUser.setPassword_hash(newPassword.getText());
                session.merge(loggedUser);

                transaction.commit();
                checkPassword.clear();
                newPassword.clear();
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

}
