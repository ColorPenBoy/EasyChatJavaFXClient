package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

/**
 * @author Zed
 * date: 2019/08/19.
 * description:
 */
@Data
public class LoginReq extends Packet {
    /** 用户id **/
    private Long userId;

    /** 车型id **/
    private String carVersion;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RRQ;
    }
}
