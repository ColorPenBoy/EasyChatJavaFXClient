package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;


@Data
public class GroupAddUserReq extends Packet {
    /** 群id **/
    private Integer groupId;
    /** 用户id **/
    private Long userId;

    @Override
    public byte getCommand() {
        return Command.GROUP_ADD_USER_REQ;
    }
}
