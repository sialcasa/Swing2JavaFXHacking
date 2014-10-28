package com.github.twitterswingsample.view.migration.logindatadialog;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.github.twitterswingsample.view.frames.MainFrame;
import com.github.twitterswingsample.view.listener.AddAccountListenerFX;

public class LoginDataDialogFX extends Stage implements LoginDataDialogInterface {

    @FXML
    private VBox root;

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

    private MainFrame frame;

    public LoginDataDialogFX() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginDataDialogFX.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void initialize() {
        this.setScene(new Scene(root));
    }

    @FXML
    void addButtonPressed(ActionEvent event) {
        new AddAccountListenerFX(getFrame(), this).handle(event);
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

    public MainFrame getFrame() {
        return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

}
