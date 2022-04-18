package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class AddUserReq extends Packet {
    /** 添加的好友 userId*/
    private String addUserId;
    @Override
    public byte getCommand() {
        return Command.ADD_USER_REQ;
    }
}
