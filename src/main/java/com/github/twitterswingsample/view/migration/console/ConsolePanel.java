package com.github.twitterswingsample.view.migration.console;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Panel displaying messages about all the processes of the client
 * 
 * @author sourcefranke
 */
public class ConsolePanel extends AnchorPane {

    private static ConsolePanel SINGLETON;

    private final TextFlow consoleTextFlow = new TextFlow();

    public static synchronized ConsolePanel getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new ConsolePanel();
        }

        return SINGLETON;
    }

    private ConsolePanel() {
        String text = createInformationText();
        Text text1 = new Text(text);
        text1.setFill(Color.RED);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        consoleTextFlow.getChildren().add(text1);
        getChildren().add(consoleTextFlow);
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

    public void printMessage(String[] message) {
        String tmp = "\n\n" + new Date() + ":";
        for (int i = 0; i < message.length; i++) {
            tmp += "\n" + message[i];
        }
        Text text1 = new Text(tmp);
        text1.setFill(Color.GRAY);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        Platform.runLater(() -> consoleTextFlow.getChildren().add(text1));
    }
}