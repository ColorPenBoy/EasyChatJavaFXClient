package com.easychat.fx.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easychat.fx.serializer.Serializer;
import com.easychat.fx.support.Command;
import com.easychat.fx.support.Packet;
import com.easychat.fx.support.request.*;
import com.easychat.fx.support.response.*;
import io.netty.buffer.ByteBuf;

public class PacketCode {
    
    public static final PacketCode INSTANCE = new PacketCode();
    
    private PacketCode() {
    }
    public static final int MAGIC_NUMBER = 0x12345678;
    
    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Serializer.Default.serializer(packet);
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.Default.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    
    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte [] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> packetClass = getPacketByCommand(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        Packet packet = serializer.deSerializer(packetClass, bytes);
        return packet;

    }
    
    
    public String encode(Packet packet) {
        return JSON.toJSONString(packet);
    }

    public Packet decode(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        int command = (int) jsonObject.get("command");
        Class<? extends Packet> clazz = getPacketByCommand((byte) command);
        return jsonObject.toJavaObject(clazz);
    }
    
    public Class<? extends Packet> getPacketByCommand(byte command) {
        switch (command) {
            case Command.LOGIN_RRQ :
                return LoginReq.class;
            case Command.LOGIN_RESP :
                return LoginResp.class;

            case Command.GROUP_CREATE_REQ :
                return GroupCreateReq.class;
            case Command.GROUP_CREATE_RESP :
                return GroupCreateResp.class;

            case Command.GROUP_ADD_USER_REQ :
                return GroupAddUserReq.class;
            case Command.GROUP_ADD_USER_RESP:
                return GroupAddUserResp.class;

            case Command.GROUP_MESSAGE_REQ :
                return GroupMessageReq.class;
            case Command.GROUP_MESSAGE_RESP :
                return GroupMessageResp.class;

            case Command.MESSAGE_SELF_RESP :
                return MessageSelfResp.class;
            case Command.HEAT_BEAT_REQ :
                return HertBeatReq.class;
            case Command.HEAT_BEAT_RESP :
                return HertBeatResp.class;
            default:
                return HertBeatReq.class;
        }
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return Serializer.Default;
    }
}
