package com.easychat.fx.controller;

import com.easychat.fx.bean.Group;
import com.easychat.fx.bean.User;
import com.easychat.fx.client.Client;
import com.easychat.fx.util.DateUtils;
import com.easychat.fx.support.request.InviteGroupReq;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        InviteGroupReq req = new InviteGroupReq();
        int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            errorMsg.setText("请选择群聊");
            return;
        }
        Group group = (Group) choiceBox.getItems().get(selectedIndex);
        req.setGroupId(group.getGroupId());
        req.setDateTime(DateUtils.now());

        List<Long> users = new ArrayList<>();
        users.add(Long.parseLong(userIdTextField.getText()));
        req.setUsers(users);

        Client.channelCache.writeAndFlush(req);
    }
}
