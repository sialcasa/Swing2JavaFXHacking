package com.github.twitterswingsample.view.listener;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.frames.MainFrame;
import com.github.twitterswingsample.view.migration.console.ConsolePanelFX;
import com.github.twitterswingsample.view.migration.logindatadialog.LoginDataDialogFX;

public class AddAccountListenerFX implements EventHandler<ActionEvent> {

    private final MainFrame frame;
    private final LoginDataDialogFX ldd;

    public AddAccountListenerFX(MainFrame frame, LoginDataDialogFX ldd) {
        this.frame = frame;
        this.ldd = ldd;
    }

    @Override
    public void handle(ActionEvent arg0) {
        try {
            Credentials creds = new Credentials();
            int id = creds.addUser(ldd.getName(), ldd.getAPIKey(), ldd.getAPISecret(), ldd.getAccessToken(),
                    ldd.getAccessTokenSecret());
            frame.addUser(creds, id);
            ConsolePanelFX.getInstance().printMessage(new String[] { "Account successfully added" });
        } catch (ClassNotFoundException | SQLException e1) {
            ConsolePanelFX.getInstance().printMessage(
                    new String[] { "SQL Exception thrown, there is an incorrect SQL statement",
                            "Please report that bug!", e1.getLocalizedMessage() });
        }
    }

}
