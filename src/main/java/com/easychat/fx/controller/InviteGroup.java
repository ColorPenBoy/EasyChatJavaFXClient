package com.easychat.fx.controller;

import com.easychat.fx.bean.Group;
import com.easychat.fx.client.Client;
import com.easychat.fx.support.request.GroupAddUserReq;
import com.easychat.fx.util.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InviteGroup extends AbstractController {


    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private TextField userIdTextField;
    @FXML
    private Label userIdLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userIdTextField.setText("1000000000");
    }

    public void commit() {

        int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            errorMsg.setText("请选择群聊");
            return;
        }
        Group group = (Group) choiceBox.getItems().get(selectedIndex);

        GroupAddUserReq req = new GroupAddUserReq();
        req.setGroupId(group.getGroupId());
        req.setUserId(Long.parseLong(userIdTextField.getText()));
        req.setDateTime(DateUtils.now());

        Client.channelCache.writeAndFlush(req);
    }
}
