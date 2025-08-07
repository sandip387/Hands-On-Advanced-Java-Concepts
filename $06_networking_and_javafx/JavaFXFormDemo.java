package $06_networking_and_javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFXFormDemo extends Application {

    // Declare controls at the class level so they can be accessed by event handlers
    private TextField nameField;
    private TextField emailField;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private CheckBox sportsCheck;
    private CheckBox musicCheck;
    private CheckBox readingCheck;
    private TextArea resultArea;

    // The 'start' method is the main entry point for all JavaFX applications.
    // The 'Stage' is the main window, provided by the JavaFX runtime.
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Complete Form ");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // --- Create UI Controls ---

        // Title Label
        Label sceneTitle = new Label("Welcome to JavaFX!");
        sceneTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        grid.add(sceneTitle, 0, 0, 2, 1); // Add to grid at (col 0, row 0), spanning 2 cols, 1 row

        // Name Field
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 1);
        nameField = new TextField();
        grid.add(nameField, 1, 1);

        // Email Field
        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 2);
        emailField = new TextField();
        grid.add(emailField, 1, 2);

        // Radio buttons for gender
        grid.add(new Label("Gender:"), 0, 3);
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        grid.add(genderBox, 1, 3);

        // CheckBoxes for hobbies
        grid.add(new Label("Hobbies:"), 0, 4);
        sportsCheck = new CheckBox("Sports");
        musicCheck = new CheckBox("Music");
        readingCheck = new CheckBox("Reading");
        HBox hobbyBox = new HBox(10, sportsCheck, musicCheck, readingCheck);
        grid.add(hobbyBox, 1, 4);

        // Buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");
        HBox buttonBox = new HBox(10, clearButton, submitButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(buttonBox, 1, 5);

        // Result area
        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(100);
        grid.add(resultArea, 0, 6, 2, 1); // Span 2 columns

        // Event handling
        submitButton.setOnAction(event -> handleSubmit());
        clearButton.setOnAction(event -> handleClear());

        // Create scene and show
        Scene scene = new Scene(grid, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSubmit() {
        StringBuilder result = new StringBuilder();
        result.append("--- Form Submitted ---\n");
        result.append("Name: ").append(nameField.getText()).append("\n");
        result.append("Email: ").append(emailField.getText()).append("\n");

        if (maleRadio.isSelected()) {
            result.append("Gender: Male\n");
        } else if (femaleRadio.isSelected()) {
            result.append("Gender: Female\n");
        }

        result.append("Hobbies: ");
        if (sportsCheck.isSelected())
            result.append("Sports ");
        if (musicCheck.isSelected())
            result.append("Music ");
        if (readingCheck.isSelected())
            result.append("Reading ");
        result.append("\n");

        resultArea.setText(result.toString());
    }

    private void handleClear() {
        nameField.clear();
        emailField.clear();
        maleRadio.getToggleGroup().selectToggle(null);
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        resultArea.clear();
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
