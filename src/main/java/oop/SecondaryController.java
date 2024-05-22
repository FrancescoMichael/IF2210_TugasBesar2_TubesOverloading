// package oop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

// public class SecondaryController {

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

//     @FXML
//     private ImageView plugin_button;

//     @FXML
//     private ImageView plugin_file;

//     @FXML
//     private ImageView plugin_file_field;

//     @FXML
//     private ImageView plugin_title;

//     @FXML
//     private ImageView save_button;

//     @FXML
//     private ImageView save_folder;

//     @FXML
//     private ImageView save_folder_field;

//     @FXML
//     private ImageView save_format;

//     @FXML
//     private ImageView save_format_field;

    @FXML
    private ImageView save_title;

    @FXML
    private void initialize() {
        // Initially hide all elements
        hideAll();

        close.setOnMouseClicked(event -> {
            try {
                switchToPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("Field");
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
    }

    private void setLoadPluginVisible(boolean visible) {
        background.setVisible(visible);
        plugin_button.setVisible(visible);
        plugin_file.setVisible(visible);
        plugin_file_field.setVisible(visible);
        plugin_title.setVisible(visible);
        close.setVisible(visible);
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
    }
}