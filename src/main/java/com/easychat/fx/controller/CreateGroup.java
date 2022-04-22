package com.easychat.fx.controller;

import com.easychat.fx.client.Client;
import com.easychat.fx.util.DateUtils;
import com.easychat.fx.support.request.GroupCreateReq;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateGroup extends AbstractController {
    @FXML
    private TextField groupNameTextField;

    @FXML
    private TextField carVersionTextField;

    @FXML
    private TextField groupTypeTextField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void commit() {
        String text = groupNameTextField.getText();
        if (text == null || "".equals(text)) {
            errorMsg.setText("请输入需要创建的群名");
            return;
        }
        GroupCreateReq req = new GroupCreateReq();
        req.setGroupName(groupNameTextField.getText());
        req.setCarVersion(carVersionTextField.getText());
        req.setGroupType(groupTypeTextField.getText());

        req.setDateTime(DateUtils.now());

        Client.channelCache.writeAndFlush(req);
    }
}
