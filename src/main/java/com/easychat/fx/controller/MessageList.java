package com.easychat.fx.controller;

import com.easychat.fx.bean.*;
import com.easychat.fx.service.MessageService;
import com.easychat.fx.support.Packet;
import com.easychat.fx.support.request.GroupMessageReq;
import com.easychat.fx.support.response.GroupMessageResp;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageList extends AbstractController {

    @FXML
    private ListView<Object> messageList;
    @FXML
    private Label lable;
    @FXML
    private Button btgt;
    
    private String user;
    private String userTpe;
    /**
     * 总记录数
     */
    private int totalRecord;
    /**
     * 是否有下一页
     */
    private boolean hasNext;

    /**
     * 总页数
     */
    private int pages;

    /** 当前页*/
    private int currentPage;
    
    private MessageService service;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMsg.setTextFill(Color.RED);
        errorMsg.setAlignment(Pos.CENTER);
        MessageCache messageCache = Cache.messageCache;
        String messageType = messageCache.getMessageType();
        lable.setAlignment(Pos.CENTER);
        if (Constants.message_type_user.equals(messageType)) {
            User messageUser = messageCache.getMessageUser();
            user = String.valueOf(messageUser.getUserId());
            userTpe = Constants.message_type_user;
            lable.setText("与" + messageUser.getUserName() + "的聊天记录");
        } else if (Constants.message_type_group.equals(messageType)){
            Group messageGroup = messageCache.getMessageGroup();
            lable.setText("与" + messageGroup.getGroupName() + "群聊的聊天记录");
            user = String.valueOf(messageGroup.getGroupId());
            userTpe = Constants.message_type_group;
        }
        service = MessageService.DEFAULT;
        gtgt();
    }


    /**
     * 下一页
     */
    public void gt() {
        if (!hasNext) {
            errorMsg.setText("最后一页了");
            return;
        }
        clearErrorMsg();
        PageResult<Packet> pageResult;
        if (Constants.message_type_user.equals(userTpe)) {
            pageResult = service.getNextUserMessage(Long.parseLong(user), currentPage + 1, Constants.PAGESIZE);
        } else {
            pageResult = service.getNextGroupMessage(Integer.parseInt(user), currentPage + 1, Constants.PAGESIZE);
        }
        setValues(pageResult);
    }

    private void setValues(PageResult<Packet> pageResult) {
        currentPage = pageResult.getCurrentPage();
        hasNext = pageResult.isHasNext();
        pages = pageResult.getPages();
        messageList.getItems().clear();
        if (pageResult.getRecords() != null && pageResult.getRecords().size() != 0) {
            for (Packet packet : pageResult.getRecords()) {
                if (packet instanceof GroupMessageReq) {
                    GroupMessageReq msg = (GroupMessageReq) packet;
                    String userName = Cache.currentUser.getUserName();
                    Main.addItems(msg, messageList, msg.getMessageType(), msg.getMessage(), userName);
                } else if (packet instanceof GroupMessageResp) {
                    GroupMessageResp msg = (GroupMessageResp) packet;
                    Main.addItems(msg, messageList, msg.getMessageType(), msg.getMessage(), msg.getSenderName());
                } else {
                    messageList.getItems().add(packet);
                }
            }
        }
    }

    /**
     * 上一页
     * */
    public void lt() {
        if (currentPage == 1) {
            errorMsg.setText("最前面一页了");
            return;
        }
        clearErrorMsg();
        PageResult<Packet> pageResult;
        if (Constants.message_type_user.equals(userTpe)) {
            pageResult = service.getNextUserMessage(Long.parseLong(user), currentPage - 1, Constants.PAGESIZE);
        } else {
            pageResult = service.getNextGroupMessage(Integer.parseInt(user), currentPage - 1, Constants.PAGESIZE);
        }
        setValues(pageResult);
    }

    /**
     * 最早记录
     * */
    public void ltlt() {
        clearErrorMsg();
        PageResult<Packet> pageResult;
        if (Constants.message_type_user.equals(userTpe)) {
            pageResult = service.getEarliestUserMessage(Long.parseLong(user), Constants.PAGESIZE);
        } else {
            pageResult = service.getEarliestGroupMessage(Integer.parseInt(user), Constants.PAGESIZE);
        }
        setValues(pageResult);
    }

    /**
     * 最晚记录
     * */
    public void gtgt() {
        clearErrorMsg();
        PageResult<Packet> pageResult;
        if (Constants.message_type_user.equals(userTpe)) {
            pageResult = service.getLatestUserMessage(Long.parseLong(user), Constants.PAGESIZE);
        } else {
            pageResult = service.getLatestGroupMessage(Integer.parseInt(user), Constants.PAGESIZE);
        }
        setValues(pageResult);
    }
    
    public void clearErrorMsg() {
        errorMsg.setText("");
    }

}
