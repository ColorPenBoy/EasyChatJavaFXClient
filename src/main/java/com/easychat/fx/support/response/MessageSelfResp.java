package com.easychat.fx.support.response;

import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class MessageSelfResp extends Packet {
    private String receiverId;
    private String groupId;
    private boolean success;
    private String reason;
    @Override
    public byte getCommand() {
        return Command.MESSAGE_SELF_RESP;
    }
}
