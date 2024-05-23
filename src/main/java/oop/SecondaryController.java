package oop;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML
    private ImageView background;

    @FXML
    private ImageView close;

    @FXML
    private ImageView load_button;

    @FXML
    private ImageView load_folder;

    @FXML
    private ImageView load_folder_field;

    @FXML
    private ImageView load_format;

    @FXML
    private ImageView load_format_field;

    @FXML
    private ImageView load_title;

    @FXML
    private ImageView plugin_button;

    @FXML
    private ImageView plugin_file;

    @FXML
    private ImageView plugin_file_field;

    @FXML
    private ImageView plugin_title;

    @FXML
    private ImageView save_button;

    @FXML
    private ImageView save_folder;

    @FXML
    private ImageView save_folder_field;

    @FXML
    private ImageView save_format;

    @FXML
    private ImageView save_format_field;

    @FXML
    private ImageView save_title;

    @FXML
    private Label chooseFilePluginLabel;

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private Label chooseFolderLabel;


    private GlowButtonMaker glowButtonMaker = new GlowButtonMaker();

    @FXML
    private void initialize() {
        // Initially hide all elements
        hideAll();

        glowButtonMaker.setGlow(close);
        glowButtonMaker.setGlow(load_button);
        glowButtonMaker.setGlow(plugin_button);
        glowButtonMaker.setGlow(save_button);

        close.setOnMouseClicked(event -> {
            try {
                switchToPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        chooseFilePluginLabel.setOnMouseClicked(event -> {
            handleFileChoose();
        });

        chooseFolderLabel.setOnMouseClicked(event -> {
            handleFolderChoose();
        });

        // Initialize formatComboBox
        formatComboBox.getItems().addAll("txt", "docs");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Field.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) close.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setState(String state) {
        hideAll();
        switch (state) {
            case "LoadPlugin":
                setLoadPluginVisible(true);
                break;
            case "LoadState":
                setLoadStateVisible(true);
                break;
            case "SaveState":
                setSaveStateVisible(true);
                break;
        }
    }

    private void hideAll() {
        background.setVisible(false);
        close.setVisible(false);
        load_button.setVisible(false);
        load_folder.setVisible(false);
        load_folder_field.setVisible(false);
        load_format.setVisible(false);
        load_format_field.setVisible(false);
        load_title.setVisible(false);
        plugin_button.setVisible(false);
        plugin_file.setVisible(false);
        plugin_file_field.setVisible(false);
        plugin_title.setVisible(false);
        save_button.setVisible(false);
        save_folder.setVisible(false);
        save_folder_field.setVisible(false);
        save_format.setVisible(false);
        save_format_field.setVisible(false);
        save_title.setVisible(false);
        close.setVisible(false);
        chooseFilePluginLabel.setVisible(false);
        formatComboBox.setVisible(false);
        chooseFolderLabel.setVisible(false);
    }

    private void setLoadPluginVisible(boolean visible) {
        background.setVisible(visible);
        plugin_button.setVisible(visible);
        plugin_file.setVisible(visible);
        plugin_file_field.setVisible(visible);
        plugin_title.setVisible(visible);
        close.setVisible(visible);
        chooseFilePluginLabel.setVisible(visible);
    }

    private void setLoadStateVisible(boolean visible) {
        background.setVisible(visible);
        load_button.setVisible(visible);
        load_folder.setVisible(visible);
        load_folder_field.setVisible(visible);
        load_format.setVisible(visible);
        load_format_field.setVisible(visible);
        load_title.setVisible(visible);
        close.setVisible(visible);
    }

    private void setSaveStateVisible(boolean visible) {
        background.setVisible(visible);
        save_button.setVisible(visible);
        save_folder.setVisible(visible);
        save_folder_field.setVisible(visible);
        save_format.setVisible(visible);
        save_format_field.setVisible(visible);
        save_title.setVisible(visible);
        close.setVisible(visible);
        formatComboBox.setVisible(visible);
        chooseFolderLabel.setVisible(visible);
    }

    @FXML
    private void handleFileChoose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        
        // filter json dan yaml aja
        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        FileChooser.ExtensionFilter yamlFilter = new FileChooser.ExtensionFilter("YAML files (*.yaml, *.yml)", "*.yaml", "*.yml");
        fileChooser.getExtensionFilters().addAll(jsonFilter, yamlFilter);
        
        Stage stage = (Stage) chooseFilePluginLabel.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            chooseFilePluginLabel.setText(file.getName());
        }
    }

    @FXML
    private void handleFolderChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");

        Stage stage = (Stage) chooseFolderLabel.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            chooseFolderLabel.setText(selectedDirectory.getAbsolutePath());
        }
    }
}