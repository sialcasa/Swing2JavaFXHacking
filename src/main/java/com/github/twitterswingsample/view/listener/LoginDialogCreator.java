package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.JDialog;
import javax.swing.JFrame;
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
        JDialog dialog = new JDialog((JFrame) frame.getParent());
        dialog.setModal(true);
        dialog.add(loginWrapper);

        LoginDataDialogFX loginDataDialogFX = new LoginDataDialogFX(frame, dialog);

        Platform.runLater(() -> {
            loginWrapper.setScene(new Scene(loginDataDialogFX));
            SwingUtilities.invokeLater(() -> {
                dialog.pack();
                dialog.setVisible(true);
            });
        });
    }
}