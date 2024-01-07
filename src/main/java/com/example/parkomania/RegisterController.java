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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.parkomania.HelloApplication.sceneManager;

public class RegisterController {

    @FXML
    private PasswordField checkPassword;

    @FXML
    private PasswordField checkPasswordAgain;

    @FXML
    private TextField checkPhoneNumber;

    @FXML
    private Label tooShortPassInfo;

    @FXML
    private Label secondPassWrongInfo;

    @FXML
    private Label uncorrectNumberFormat;

    @FXML
    private Label existingNumberDetected;

    @FXML
    private Button goBack;

    @FXML
    private Button subimtNewUser;


    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("loginAndRegister");
        checkPhoneNumber.clear();
        checkPassword.clear();
        checkPasswordAgain.clear();
        existingNumberDetected.setVisible(false);
        uncorrectNumberFormat.setVisible(false);
        tooShortPassInfo.setVisible(false);
    }

    @FXML
    void submitNewUser(ActionEvent event) {

        //if fields are in db and if they are in correct format

        if (!checkPhoneField(checkPhoneNumber.getText())){
            return;
        }
        if (!checkPasswordFields(checkPassword.getText(), checkPasswordAgain.getText())){
            return;
        }

        //user to submit via hibernate
        User newUser = new User(0, checkPhoneNumber.getText(), checkPassword.getText(), UserType.USER);

        //create session factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        //try to send transaction to db
        try {
            transaction = session.beginTransaction();
            session.persist(newUser); //send user using jakarta
            transaction.commit(); //end transaction
            System.out.println("Debug: New user added");

            //if user is added successfully - switch scene
            sceneManager.switchScene("mainMenu");
        } catch (Exception e) {
            //check if transaction went wrong and if so dont commit user to db
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Debug: Something went wrong");
            e.printStackTrace();
        } finally {
            //close session and session factory. then set labels to not visible
            session.close();
            sessionFactory.close();
            System.out.println("Debug: Session close");

            uncorrectNumberFormat.setVisible(false);
            existingNumberDetected.setVisible(false);
            uncorrectNumberFormat.setVisible(false);
            tooShortPassInfo.setVisible(false);
        }
    }

    //if true - this phone number is not in db
    boolean checkPhoneField(String phoneNumber){
        //check if phone number is a number (doesnt contain any chars)

        //regular expression for phone number
        String phoneRegex = "^[0-9\\-\\s]+$";

        //compile regular expression
        Pattern pattern = Pattern.compile(phoneRegex);

        //check if it matches - if not show message and clear phone field
        if (!pattern.matcher(phoneNumber).matches()){
            checkPhoneNumber.clear();
            uncorrectNumberFormat.setVisible(true);
            return false;
        }else {
            uncorrectNumberFormat.setVisible(false);
        }

        //check if we have same number in database
        String checkPhoneNumInDBQuery = "SELECT phone_number FROM user WHERE phone_numer=" + phoneNumber;
//        System.out.println("Debug query: " + checkPhoneNumInDBQuery);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        //try to send transaction to db
        try {
            transaction = session.beginTransaction();
            List<String> phoneNumbers = session.createQuery(checkPhoneNumInDBQuery)
                    .setParameter("phoneNumber", phoneNumber)
                    .getResultList();

            if (!phoneNumbers.isEmpty()) {
                System.out.println("Debug: Found phone numbers: ");
                for (String phone : phoneNumbers) {
                    System.out.println(phone);
                }
                existingNumberDetected.setVisible(true);
                session.close();
                sessionFactory.close();
                return false;
            } else {
                System.out.println("Debug: No phone numbers found.");
                existingNumberDetected.setVisible(false);
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
            System.out.println("Debug: Session close");
        }
        sessionFactory.close();

        return true;
    }
    boolean checkPasswordFields(String password, String secondPasswordField){

        //regular expression
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";

        //compile expression to pattern
        Pattern pattern = Pattern.compile(regex);

        //check pattern
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()){
            if (password.equals(secondPasswordField)){
                System.out.println("Debug: Passwords match! Send it");
                return true;
            }
            secondPassWrongInfo.setVisible(true);
            System.out.println("Debug: Passwords dont match!");
            checkPasswordAgain.clear();
            return false;
        } else {
            tooShortPassInfo.setVisible(true);
            checkPassword.clear();
            checkPasswordAgain.clear();
            return false;
        }
    }
}
