package com.easychat.fx.support.response;

import com.easychat.fx.util.AES;
import com.easychat.fx.util.DateUtils;
import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import lombok.Data;

@Data
public class MessageResp extends Packet {
    private Long sender;
    private String senderName;
    private String message;
    /**
     * 消息类型
     */
    private int messageType = 0;
    @Override
    public byte getCommand() {
        return Command.SEND_MESSAGE_RESP;
    }
    @Override
    public String toString(){
        return senderName + " " + DateUtils.convertShowTime(dateTime) + ": \n    " + AES.decrypt(message, dateTime+"ab");
    }
}
