package com.example.parkomania;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class UserDataController implements Initializable {


    @FXML
    private Button goBack;

    @FXML
    private Button goToAddVehicle;

    @FXML
    private Button goToChangePassword;

    @FXML
    private Button goToDelVehicle;

    @FXML
    private Label nrTel;

    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    private TableColumn<Vehicle, String> registrations;

    private void fetchUserPhone(){
        nrTel.setText(loggedUser.getPhone_number());
    }
    private void fetchUserVehicles(){

        vehicleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        String getAllVehiclesForLoggedUserQuery = "FROM Vehicle WHERE user.id = :user_id";

        List<Vehicle> carList;


        //try to send transaction to db
        try {
            //Debug
            loggedUser.setUser_id(27);
            System.out.println("Debug: Fetching all plate numbers");

            transaction = session.beginTransaction();
            carList = session.createQuery(getAllVehiclesForLoggedUserQuery)
                    .setParameter("user_id", loggedUser.getUser_id())
                    .getResultList();

            ObservableList<Vehicle> registrationList =  FXCollections.observableList(carList);

            System.out.println("Debug: Vehicle information: " + carList.toString());

            registrations.setCellValueFactory(new PropertyValueFactory<>("registration"));

            vehicleTable.setItems(registrationList);

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
        }
    }

    @FXML
    void goToAddVehicle(ActionEvent event) {
        sceneManager.switchScene("addVehicle");
    }
    @FXML
    void goToChangePassword(ActionEvent event) {
        sceneManager.switchScene("changePassword");
    }
    @FXML
    void goToDelVehicle(ActionEvent event) {
        sceneManager.switchScene("deleteVehicle");
    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("account");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchUserPhone();
        fetchUserVehicles();
    }
}
