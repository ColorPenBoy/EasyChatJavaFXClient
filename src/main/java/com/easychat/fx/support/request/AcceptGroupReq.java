package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class AcceptGroupReq extends Packet {
    /** 群id*/
    private String groupId;
    /** 邀请人*/
    private String inviterId;
    /** 是否接受邀请*/
    private boolean accept;
    @Override
    public byte getCommand() {
        return Command.ACCEPT_GROUP_REQ;
    }
}
