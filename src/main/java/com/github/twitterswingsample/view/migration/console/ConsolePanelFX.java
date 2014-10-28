package com.github.twitterswingsample.view.migration.console;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ConsolePanelFX extends AnchorPane implements ConsoleInterface {

    private static ConsolePanelFX SINGLETON;

    @FXML
    private AnchorPane root;

    @FXML
    private TextFlow consoleTextFlow;

    public static synchronized ConsolePanelFX getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new ConsolePanelFX();
        }

        return SINGLETON;
    }

    private ConsolePanelFX() {
        loadFxml();
        String text = createInformationText();
        Text text1 = new Text(text);
        text1.setFill(Color.RED);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        consoleTextFlow.getChildren().add(text1);
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConsolePanelFX.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private String createInformationText() {
        Properties properties = new Properties();
        BufferedInputStream stream;
        String text = "Welcome to Twitter4J Swing Sample \n";
        try {
            stream = new BufferedInputStream(getClass().getResourceAsStream("/version.properties"));
            properties.load(stream);
            stream.close();

            text += "maintained by " + properties.getProperty("maintainer_name") + " ("
                    + properties.getProperty("maintainer_email") + ") \n" + "Version: "
                    + properties.getProperty("version");
        } catch (IOException e) {
            text += "\ncouldn't find version information";
        }
        return text;
    }

    @Override
    public void printMessage(String[] message) {
        // area.setVisible(false);
        String tmp = "\n\n" + new Date() + ":";
        for (int i = 0; i < message.length; i++) {
            tmp += "\n" + message[i];
        }
        Text text1 = new Text(tmp);
        text1.setFill(Color.GRAY);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        Platform.runLater(() -> consoleTextFlow.getChildren().add(text1));
        // area.insert(tmp, area.getText().length());
        // area.setVisible(true);
    }

}
