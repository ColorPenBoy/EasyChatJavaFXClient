package com.easychat.fx.support.response;

import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class LogoutResp extends Packet {
    @Override
    public byte getCommand() {
        return 0;
    }
}
