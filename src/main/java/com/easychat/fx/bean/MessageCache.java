package com.easychat.fx.bean;

import lombok.Data;

@Data
public class MessageCache {
    private String messageType;
    private User messageUser;
    private Group messageGroup;
}
