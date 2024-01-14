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

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class LoginController {
    @FXML
    private Button goBack;

    @FXML
    private Button logIn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Label uncorrectNumberFormat;

    @FXML
    private Label phoneNotFound;

    @FXML
    private Label incorrectPassword;

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("loginAndRegister");
    }
    private int userId; // for createLoggedUser - i know its not how it should be but time is ticking

    @FXML
    void logIn(ActionEvent event) {
        System.out.println("Debug: Credentails submitted");

        boolean credentialsCheck = checkCredentials(phoneField.getText(), passwordField.getText());

        if (credentialsCheck) {
            System.out.println("Debug: UserId is: " + userId);
            createLoggedUser(credentialsCheck, userId);
            passwordField.clear();
            sceneManager.switchScene("mainMenu");
            try {
                sceneManager.loadScene("bookPlace", "templates/bookPlace.fxml");
                sceneManager.loadScene("userData", "templates/userData.fxml");
                sceneManager.loadScene("history", "templates/history.fxml");
                sceneManager.loadScene("reservations", "templates/reservations.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Debug: currently logged: " + loggedUser.getPhone_number() + " " + loggedUser.getUser_id());
        }else {
            System.out.println("Debug: Phone or password is wrong");
        }
    }

    boolean checkCredentials(String phoneNumber, String password) {
        //check if phone number is a number (doesnt contain any chars)

        //regular expression for phone number
        String phoneRegex = "^[0-9\\-\\s]+$";

        //compile regular expression
        Pattern pattern = Pattern.compile(phoneRegex);

        //check if it matches - if not show message and clear phone field
        if (!pattern.matcher(phoneNumber).matches()) {
            uncorrectNumberFormat.setVisible(true);
            phoneField.clear();
            return false;
        }else {
            uncorrectNumberFormat.setVisible(false);
        }

        //if password is empty do not check db
        if (password.isEmpty()){
            incorrectPassword.setVisible(true);
            return false;
        }else {
            incorrectPassword.setVisible(false);
        }

        //check if we have same number in database - HQL needed. SQL throws exceptions
        String checkPhoneNumInDBQuery = "SELECT phone_number FROM User WHERE phone_number = :phoneNumber";
        String checkPasswordHashInDBQuery = "SELECT password_hash FROM User WHERE phone_number = :phoneNumber";
        String checkIdInDBQuery = "SELECT user_id FROM User WHERE phone_number = :phoneNumber";

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        //try to send transaction to db
        try {
            transaction = session.beginTransaction();
            List<String> phoneNumbers = session.createQuery(checkPhoneNumInDBQuery)
                    .setParameter("phoneNumber", phoneNumber)
                    .getResultList();
            List<String> passwordHashes = session.createQuery(checkPasswordHashInDBQuery)
                    .setParameter("phoneNumber", phoneNumber)  // Ustawienie tego samego parametru 'phoneNumber'
                    .getResultList();
            List<Integer> ids = session.createQuery(checkIdInDBQuery)
                    .setParameter("phoneNumber", phoneNumber)  // Ustawienie tego samego parametru 'phoneNumber'
                    .getResultList();

            if (!phoneNumbers.isEmpty()) {
                //found number, so check if password matches
                String storedPasswordHash = (String) passwordHashes.get(0);
                userId = ids.get(0);

                if (checkPassword(password, storedPasswordHash)) {
                    //password is correct
                    session.close();
                    sessionFactory.close();

                    return true;
                } else {
                    //incorrect password
                    incorrectPassword.setVisible(true);
                }
            }else {
                System.out.println("Debug: No phone numbers found.");
                phoneNotFound.setVisible(true);
            }
            transaction.commit();
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

        return false;
    }

    //hash password and compare it to password hash in db
    boolean checkPassword(String inputPassword, String storedPasswordHash) {
        //TODO hashing
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hashedPassword = digest.digest(inputPassword.getBytes(StandardCharsets.UTF_8));
//            String hashedPasswordHex = DatatypeConverter.printHexBinary(hashedPassword).toLowerCase();
//
//            return hashedPasswordHex.equals(storedPasswordHash.toLowerCase());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return false;
//        }
        System.out.println("Debug: password from db: " + storedPasswordHash);
        System.out.println("Debug: password from user: " + inputPassword);
        if (inputPassword.equals(storedPasswordHash)){

            return true;
        }
        return false;
    }
    User createLoggedUser(boolean areCredentialsCorrect, int userId){
        if (areCredentialsCorrect){

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = null;

            try {
                loggedUser = (User) session.get(User.class, userId);
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Debug: Something went wrong - User not created");
                e.printStackTrace();
            } finally {
                session.close();
                sessionFactory.close();
                System.out.println("Debug: Session close");
            }

            return loggedUser;
        }
        System.out.println("Debug: User is not logged!");
        return null;
    }
}