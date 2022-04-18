package com.easychat.fx.support;

import lombok.Data;

@Data
public abstract class Packet {
    private transient byte version = 1;
    /** 时间*/
    protected String dateTime;
    
    public abstract byte getCommand();
}
