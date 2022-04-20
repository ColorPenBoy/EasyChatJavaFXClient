package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class GroupCreateReq extends Packet {
    /** 群名 **/
    private String groupName;

    /** 群类型：car - 车关联，normal - 普通群 **/
    private String groupType;

    /** 车型 **/
    private String carVersion;

    @Override
    public byte getCommand() {
        return Command.GROUP_CREATE_REQ;
    }
}
