package com.easychat.fx.support.request;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class LogoutReq extends Packet {
    @Override
    public byte getCommand() {
        return Command.LOGIN_RESP;
    }
}
