/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package hotel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

/**
 *
 * @author jayia
 */
public class Main extends Application {

    private List<Hotel> hotelList = new ArrayList<>();
    private ListView<String> view = new ListView<>();

    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) {
//        read hotels from file
        getHotels("Hotels.txt");
//        display list of hotels
        updateView(hotelList);

        Button sortByRatingButton = new Button("Sort by Star Rating");
        Button sortByPriceButton = new Button("Sort by Lowest Price");
        Button saveButton = new Button("Save Sorted List");

        
        sortByRatingButton.setOnAction(e -> {
//            sort hotels by rating / name
            Collections.sort(hotelList);
//            update display
            updateView(hotelList);
        });

        
//        usign comparator double comparer to compare prices
        sortByPriceButton.setOnAction(e -> {
            hotelList.sort(Comparator.comparingDouble(Hotel::getPricePerNight));
//            update display
            updateView(hotelList);
        });

//        save sorted to file
        saveButton.setOnAction(e -> saveHotels("Sorted.txt"));

        VBox layout = new VBox(10, view, sortByRatingButton, sortByPriceButton, saveButton);
        
//        lets add some stylign :)
        layout.setStyle("-fx-background-color: #1e1e1e;" +"-fx-padding: 20px;" +"-fx-spacing: 15px;");
        view.setStyle("-fx-background-color: #2d2d2d;" +"-fx-text-fill: #ffffff;" +"-fx-padding: 10px;" );
        sortByRatingButton.setStyle("-fx-background-color: #0d47a1;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;");
        sortByPriceButton.setStyle("-fx-background-color: #0d47a1;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;");
        saveButton.setStyle("-fx-background-color: #0d47a1;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" );
        sortByRatingButton.setOnMouseEntered(e -> sortByRatingButton.setStyle("-fx-background-color: #1976d2;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" ));
        sortByRatingButton.setOnMouseExited(e -> sortByRatingButton.setStyle("-fx-background-color: #0d47a1;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" ));
        sortByPriceButton.setOnMouseEntered(e -> sortByPriceButton.setStyle("-fx-background-color: #1976d2;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" ));
        sortByPriceButton.setOnMouseExited(e -> sortByPriceButton.setStyle("-fx-background-color: #0d47a1;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" ));
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-background-color: #1976d2;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" ));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-background-color: #0d47a1;" +"-fx-text-fill: #ffffff;" +"-fx-font-size: 14px;" +"-fx-padding: 10px;" ));
        
        Scene scene = new Scene(layout, 400, 400);

        primaryStage.setTitle("Hotel Sorting App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getHotels(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.split(", ");

                
                String name = parts[0].trim();
                int rating = Integer.parseInt(parts[1].split(" ")[0]); 
                double price = Double.parseDouble(parts[2].split(" ")[0]); 

                hotelList.add(new Hotel(name, rating, price));
            }

            for (Hotel hotel : hotelList) {
                System.out.println(hotel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHotels(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Hotel hotel : hotelList) {
                writer.write(hotel.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
    private void updateView(List<Hotel> hotels) {
        view.getItems().clear();
        for (Hotel hotel : hotels) {
            view.getItems().add(hotel.toString());
        }
    }
}
