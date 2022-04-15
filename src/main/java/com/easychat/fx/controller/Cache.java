package com.easychat.fx.controller;

import com.easychat.fx.bean.AcceptCache;
import com.easychat.fx.bean.Group;
import com.easychat.fx.bean.MessageCache;
import com.easychat.fx.bean.User;
import com.easychat.fx.support.Packet;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public interface Cache {
    Map<String, Stage> ControllerMap = new HashMap<>();
    
    Map<Long, LinkedList<Packet>> userMessageMap = new HashMap<>();
    Map<String, LinkedList<Packet>> groupMessageMap = new HashMap<>();

    Map<Long, Integer> userMessageNumMap = new HashMap<>();
    Map<String, Integer> groupMessageNumMap = new HashMap<>();
    AtomicInteger allMessageNum = new AtomicInteger(0);

    User currentUser = new User();
    User system = new User(1000L, "C0.0101", "系统消息", "", "系统消息", "", "有事没事常聊聊", "", "");


    MessageCache messageCache = new MessageCache();
    
    List<User> cacheUsers = new ArrayList<>();
    Map<Long, User>  cacheUserMap = new HashMap<>();

    List<Group> cacheGroups = new ArrayList<>();
    Map<String, Group>  cacheGroupMap = new HashMap<>();

    AcceptCache acceptCache = new AcceptCache();
}
