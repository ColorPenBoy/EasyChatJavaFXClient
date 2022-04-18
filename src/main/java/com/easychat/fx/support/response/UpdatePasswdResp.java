package com.easychat.fx.support.response;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class UpdatePasswdResp extends Packet {
    private String password;
    private boolean success;

    private String reason;
    @Override
    public byte getCommand() {
        return Command.UPDATE_PASSWD_RESP;
    }
}
