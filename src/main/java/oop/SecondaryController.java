package oop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import oop.plugin.PluginInterface;
import oop.plugin.PluginLoader;
import oop.plugin.SaveLoadTXT;
import oop.saveload.SaveLoad;

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
    private ComboBox<String> saveFormatComboBox;

    @FXML
    private Label chooseSaveFolderLabel;

    @FXML
    private ComboBox<String> loadFormatComboBox;

    @FXML
    private Label chooseLoadFolderLabel;


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

        chooseSaveFolderLabel.setOnMouseClicked(event -> {
            handleFolderChoose();
        });

        chooseLoadFolderLabel.setOnMouseClicked(event -> {
            handleFolderChoose();
        });

        saveFormatComboBox.getItems().addAll("txt");
        loadFormatComboBox.getItems().addAll("txt");

        save_button.setOnMouseClicked(event -> handleSave());
        load_button.setOnMouseClicked(event -> handleLoad());
        plugin_button.setOnMouseClicked(event -> handlePlugin());
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
        saveFormatComboBox.setVisible(false);
        chooseSaveFolderLabel.setVisible(false);
        loadFormatComboBox.setVisible(false);
        chooseLoadFolderLabel.setVisible(false);
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
        loadFormatComboBox.setVisible(visible);
        chooseLoadFolderLabel.setVisible(visible);
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
        saveFormatComboBox.setVisible(visible);
        chooseSaveFolderLabel.setVisible(visible);
    }

    @FXML
    private void handleFileChoose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Plugin File");

        // Filter jar files
        FileChooser.ExtensionFilter jarFilter = new FileChooser.ExtensionFilter("JAR files (*.jar)", "*.jar");
        fileChooser.getExtensionFilters().addAll(jarFilter);

        Stage stage = (Stage) chooseFilePluginLabel.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            chooseFilePluginLabel.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleFolderChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");

        Stage stage = (Stage) chooseSaveFolderLabel.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            chooseSaveFolderLabel.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void handlePlugin() {
        String pluginFileName = chooseFilePluginLabel.getText();
        if (pluginFileName == null || pluginFileName.isEmpty()) {
            // TO-DO: Handle error - no file chosen
            return;
        }

        File file = new File(pluginFileName);
        if (!file.exists()) {
            // TO-DO: Handle error: file does not exist
            return;
        }

        try {
            // Load the plugin
            PluginLoader pluginLoader = new PluginLoader();
            SaveLoad saveLoad = new SaveLoad();
            pluginLoader.loadPlugin(file.getAbsolutePath(), saveLoad);

            // Update the format combo boxes
            saveFormatComboBox.getItems().clear();
            loadFormatComboBox.getItems().clear();
            saveFormatComboBox.getItems().addAll("txt");
            loadFormatComboBox.getItems().addAll("txt");

            for (PluginInterface plugin : saveLoad.getSaveLoaders()) {
                String type = plugin.getType();
                if (!saveFormatComboBox.getItems().contains(type)) {
                    saveFormatComboBox.getItems().add(type);
                    loadFormatComboBox.getItems().add(type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    @FXML
    private void handleSave() {
        String format = saveFormatComboBox.getValue();
        if (format == null || format.isEmpty()) {
            // TO-DO: handle error
            return;
        }
        // save operation
        try {
            SaveLoad saveLoad = new SaveLoad();
            String foldername = chooseSaveFolderLabel.getText() + "/saved_file." + format;
            // nanti fetch currentTurns dan shopItems
            int currentTurn = 0;
            List<String> shopItems = new ArrayList<>();
            saveLoad.saveGame(foldername, currentTurn, shopItems, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoad() {
        String format = loadFormatComboBox.getValue();
        if (format == null || format.isEmpty()) {
            // TO-DO: Handle the error, format not selected
            return;
        }
        // load
        try {
            SaveLoad saveLoad = new SaveLoad();
            String foldername = chooseLoadFolderLabel.getText() + "/saved_file." + format;
            List<String> shopItems = new ArrayList<>();
            int currentTurn = saveLoad.loadGame(foldername, format, shopItems);
            // Handle the loaded data (currentTurn and shopItems)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}