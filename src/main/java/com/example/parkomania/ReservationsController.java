package com.example.parkomania;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.parkomania.BookPlaceController.choosedVehicle;
import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class ReservationsController implements Initializable {
    @FXML
    private Button goBack;
    @FXML
    private TableColumn<Reservation, Vehicle> registerCol;

    @FXML
    private TableColumn<Reservation, String> reservationTypeCol;

    @FXML
    private TableColumn<Reservation, String> startTimeCol;

    @FXML
    private TableColumn<Reservation, String> stopTimeCol;

    @FXML
    private TableView<Reservation> currentReservations;

    private void fetchUserReservations(){

        currentReservations.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        String getAllReservationsForLoggedUserQuery = "FROM Reservation WHERE vehicle.user.id = :user_id AND stop IS NULL";

        List<Reservation> reservationListList;

        //try to send transaction to db
        try {
            //Debug
            loggedUser.setUser_id(27);
            System.out.println("Debug: Fetching all reservations");

            transaction = session.beginTransaction();
            reservationListList = session.createQuery(getAllReservationsForLoggedUserQuery)
                    .setParameter("user_id", loggedUser.getUser_id())
                    .getResultList();

            ObservableList<Reservation> registrationList =  FXCollections.observableList(reservationListList);

            System.out.println("Debug: Reservation information: " + reservationListList.toString());

            registerCol.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
            startTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            reservationTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

            currentReservations.setItems(registrationList);

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
    void goBack(ActionEvent event) {
        sceneManager.switchScene("account");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchUserReservations();
    }
}
