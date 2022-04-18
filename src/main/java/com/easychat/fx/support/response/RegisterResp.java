package com.easychat.fx.support.response;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class RegisterResp extends Packet {
    private boolean success;

    private String reason;
    @Override
    public byte getCommand() {
        return Command.REGISTER_RESP;
    }
}
