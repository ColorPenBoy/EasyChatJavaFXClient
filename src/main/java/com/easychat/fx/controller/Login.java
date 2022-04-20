package com.easychat.fx.controller;

import com.easychat.fx.client.Client;
import com.easychat.fx.util.DateUtils;
import com.easychat.fx.support.request.LoginReq;
import io.netty.channel.Channel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Login extends AbstractController {
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameLabel.setText("userId");
        passwordLabel.setText("carVersion");
        userName.setText("1000000000");
        userName.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ENTER==event.getCode()) {
                login();
            }
        });
        password.setText("C0.0101");
        password.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ENTER==event.getCode()) {
                login();
            }
        });
    }

    public void login(){
        LoginReq req = new LoginReq();
        req.setUserId(Long.parseLong(userName.getText()));
        req.setCarVersion(password.getText());
        Cache.currentUser.setPassword(password.getText());
        req.setDateTime(DateUtils.now());
        Channel channel = Client.channelCache;
        channel.writeAndFlush(req);
    }
}
