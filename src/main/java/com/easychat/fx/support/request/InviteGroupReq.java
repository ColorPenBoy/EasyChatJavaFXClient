package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

import java.util.List;

@Data
public class InviteGroupReq extends Packet {
    /** 群Id*/
    private String groupId;
    /** 邀请的好友*/
    private List<Long> users;
    @Override
    public byte getCommand() {
        return Command.INVITE_GROUP_REQ;
    }
}
