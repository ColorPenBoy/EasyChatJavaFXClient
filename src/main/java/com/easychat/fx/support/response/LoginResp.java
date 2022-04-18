package com.easychat.fx.support.response;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class LoginResp extends Packet {
    /** userId*/
    private Long userId;
    private String userName;
    private String userAvatar;
    private String token;


    private boolean success;
    private String reason;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESP;
    }
}
