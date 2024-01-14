package com.example.parkomania;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.parkomania.HelloApplication.loggedUser;
import static com.example.parkomania.HelloApplication.sceneManager;

public class BookPlaceController implements Initializable {

    @FXML
    private Button addTime;

    @FXML
    private Button subtractTime;

    @FXML
    private ChoiceBox<String> chooseVehicle;

    @FXML
    private Label choosingTimer;

    @FXML
    private ImageView startAndStopImg;

    @FXML
    private Button startAndStopCounting;

    @FXML
    private Button goBack;

    private LocalTime baseTime = LocalTime.of(0, 0);
    private LocalTime currentTime = LocalTime.of(0, 0);
    private boolean countingDown;
    private Timeline timeline;
    private LocalTime stopTime;
    private LocalTime startTime;
    public static Vehicle choosedVehicle;
    private List<Vehicle> carList;
    private Reservation newReservation;


    private void fetchUserVehicles(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        String getAllVehiclesForLoggedUserQuery = "FROM Vehicle WHERE user.id = :user_id";

        //try to send transaction to db
        try {
            //Debug
            loggedUser.setUser_id(27);
            System.out.println("Debug: Fetching all plate numbers");

            transaction = session.beginTransaction();
            carList = session.createQuery(getAllVehiclesForLoggedUserQuery)
                    .setParameter("user_id", loggedUser.getUser_id())
                    .getResultList();

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
        System.out.println("Debug: Vehicle information: " + carList.toString());
    }

    private void addCarToChoiceBox(){
        for (Vehicle vehicle:carList) {
            chooseVehicle.getItems().add(vehicle.getRegistration());
        }
        System.out.println("Debug: Registrations in choiceBox: " + chooseVehicle.getItems());
    }

    private boolean checkIfVehicleIsChosen() {
        if (chooseVehicle.getItems().isEmpty()){
            return false;
        }
        return true;
    }
    @FXML
    private void addTime() {
        baseTime = baseTime.plusMinutes(5);
        updateLabel();
//        System.out.println("Debug: Time added - baseTime is: " + baseTime);
    }
    @FXML
    private void subtractTime() {
        baseTime  = baseTime.minusMinutes(5);
        updateLabel();
//        System.out.println("Debug: Time substracted - baseTime is: " + baseTime);
    }
    private void updateLabel() {
        choosingTimer.setText(baseTime.format(DateTimeFormatter.ofPattern("HH:mm")));
    }
    @FXML
    void startAndStopCounting(ActionEvent event) {
        if (timeline.getStatus() == Timeline.Status.RUNNING) {
            stopCounting();
        } else {
            startCounting();
        }
    }

    private void startCounting() {
        System.out.println("Debug: Start odliczania, czas=" + LocalTime.now());
        startAndStopImg.setImage(new Image("file:../images/20231219_161926_0011.png"));


        if (baseTime.equals(LocalTime.of(0, 0))) {
            //count up
            System.out.println("Debug: counting up");
            countingDown = false;
            stopTime = LocalTime.now();
            startTime = null;

            //TODO
            //setting stop time correctly

            sendReservationToDB(ReservationType.STARTSTOP);
        } else {
            //count down

            disableStartButton();

            System.out.println("Debug: counting down");
            countingDown = true;
            stopTime = LocalTime.now().plusHours(baseTime.getHour()).plusMinutes(baseTime.getMinute());
            startTime = LocalTime.now();

            System.out.println("Debug: stopTime: " + stopTime);
            System.out.println("Debug: startTime: " + startTime);


            sendReservationToDB(ReservationType.PER_MIN);
        }

//        updateLabel();
        timeline.play();

        disableAddAndSubtractButtons();
    }

    private void stopCounting() {
        startAndStopImg.setImage(new Image("file:../images/20231219_161926_0011.png"));
        timeline.stop();

        startTime = LocalTime.now();

        LocalTime elapsed = LocalTime.ofSecondOfDay(stopTime.until(startTime, ChronoUnit.SECONDS));

        currentTime = currentTime.plusHours(elapsed.getHour())
                .plusMinutes(elapsed.getMinute())
                .plusSeconds(elapsed.getSecond());

        updateLabel();
        choosingTimer.setText("00:00");

        System.out.println("Debug: Koniec odliczania, czas=" + LocalTime.now());
        System.out.println("Debug: Minuty trwania odliczania: " + elapsed.getMinute() + " minut");
        enableAddAndSubtractButtons();

        baseTime = LocalTime.of(0, 0);
    }
    private void sendReservationToDB(ReservationType type){

        String timeSummary = null;

        if (type.equals(ReservationType.PER_MIN)){
            long hours = ChronoUnit.HOURS.between(startTime, stopTime);
            long minutes = ChronoUnit.MINUTES.between(startTime, stopTime) % 60;

            timeSummary = hours + ":" + minutes;
        }
        System.out.println("Debug: sending time summary: " + timeSummary);

        newReservation = new Reservation(0, startTime, stopTime, LocalDate.now(), timeSummary, choosedVehicle, type);

        //create session factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        //try to send transaction to db
        try {
            transaction = session.beginTransaction();
            session.persist(newReservation); //send reservation using jakarta
            transaction.commit(); //end transaction
            System.out.println("Debug: New reservation added");

        } catch (Exception e) {
            //check if transaction went wrong and if so dont commit
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

    private void initializeTimeline() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    LocalTime now = LocalTime.now();

                    if (countingDown) {
                        // counting down
                        long remainingMillis = now.until(stopTime, ChronoUnit.MILLIS);
                        long hours = TimeUnit.MILLISECONDS.toHours(remainingMillis);
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMillis) % 60;
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingMillis) % 60;
                        choosingTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                        if (remainingMillis <= 1000) {
                            //if time is below 0 stop counting
                            stopCounting();
                        }
                    } else {
                        // count up
                        long elapsedMillis = stopTime.until(now, ChronoUnit.MILLIS);
                        long hours = TimeUnit.MILLISECONDS.toHours(elapsedMillis);
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedMillis) % 60;
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) % 60;
                        choosingTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                        // stop when 24h reached
                        if (elapsedMillis >= TimeUnit.HOURS.toMillis(24)) {
                            stopCounting();
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void disableAddAndSubtractButtons() {
        addTime.setDisable(true);
        subtractTime.setDisable(true);
    }

    private void enableAddAndSubtractButtons() {
        addTime.setDisable(false);
        subtractTime.setDisable(false);
    }

    private void disableStartButton(){
        startAndStopCounting.setDisable(true);
    }

    private void enableStartButton(){
        startAndStopCounting.setDisable(false);
    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("mainMenu");
    }

    //loads contents on scene load and listens for change in choiceBox
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLabel();
        initializeTimeline();
        fetchUserVehicles();
        addCarToChoiceBox();

        disableStartButton();
        disableAddAndSubtractButtons();

        //listen to changes on choice box
        chooseVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (checkIfVehicleIsChosen()) {

                choosedVehicle = carList.get(chooseVehicle.getSelectionModel().getSelectedIndex());
                System.out.println("Debug: choosed vehicle: " + choosedVehicle);

                enableStartButton();
                enableAddAndSubtractButtons();
            } else {

                disableStartButton();
                disableAddAndSubtractButtons();
            }
        });
    }
}
