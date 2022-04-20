package com.easychat.fx.support.response;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import com.easychat.fx.util.DateUtils;
import lombok.Data;

@Data
public class GroupAddUserResp extends Packet {

    /** 群id **/
    private Integer groupId;

    /** 用户id **/
    private Long userId;

    /** 进群成功 **/
    private boolean success;

    /** 失败的原因 **/
    private String reason;

    @Override
    public byte getCommand() {
        return Command.GROUP_ADD_USER_RESP;
    }
}
