package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import com.github.twitterswingsample.view.frames.MainFrame;
import com.github.twitterswingsample.view.migration.logindatadialog.LoginDataDialogFX;

public class LoginDialogCreator implements ActionListener {

    private final MainFrame frame;

    public LoginDialogCreator(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFXPanel loginWrapper = new JFXPanel();

        LoginDataDialogFX loginDataDialogFX = new LoginDataDialogFX();
        loginDataDialogFX.setFrame(frame);

        Platform.runLater(() -> {
            loginWrapper.setScene(new Scene(loginDataDialogFX));
            JDialog modalWrapper = new JDialog(frame);
            modalWrapper.setModal(true);
            modalWrapper.add(loginWrapper);
            SwingUtilities.invokeLater(() -> modalWrapper.setVisible(true));
        });
    }
}