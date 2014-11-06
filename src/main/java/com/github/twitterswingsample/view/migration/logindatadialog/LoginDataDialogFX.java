package com.github.twitterswingsample.view.migration.logindatadialog;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.JDialog;

import com.github.twitterswingsample.view.frames.MainFrame;
import com.github.twitterswingsample.view.listener.AddAccountListenerFX;

public class LoginDataDialogFX extends VBox implements LoginDataDialogInterface {

    @FXML
    private TextField apiKeyTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField accessTokenTextField;

    @FXML
    private TextField accessTokenSecretTextField;

    @FXML
    private TextField apiSecretTextField;

    private final MainFrame frame;

    private final JDialog dialog;

    public LoginDataDialogFX(MainFrame frame, JDialog dialog) {
        this.frame = frame;
        this.dialog = dialog;
        loadFxml();
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginDataDialogFX.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void addButtonPressed(ActionEvent event) {
        dialog.dispose();
        new AddAccountListenerFX(frame, this).handle(event);
    }

    @Override
    public String getName() {
        return nameTextField.getText();
    }

    @Override
    public String getAPIKey() {
        return apiKeyTextField.getText();
    }

    @Override
    public String getAPISecret() {
        return apiSecretTextField.getText();
    }

    @Override
    public String getAccessToken() {
        return accessTokenTextField.getText();
    }

    @Override
    public String getAccessTokenSecret() {
        return accessTokenSecretTextField.getText();
    }

}
