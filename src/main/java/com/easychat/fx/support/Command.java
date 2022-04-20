package com.easychat.fx.support;

public interface Command {

    byte LOGIN_RRQ = 1;
    byte LOGIN_RESP = 2;

    byte GROUP_CREATE_REQ = 10;
    byte GROUP_CREATE_RESP = 11;

    byte GROUP_ADD_USER_REQ = 12;
    byte GROUP_ADD_USER_RESP = 13;

    byte GROUP_MESSAGE_REQ = 15;
    byte GROUP_MESSAGE_RESP = 16;

    byte MESSAGE_SELF_RESP = 25;

    byte HEAT_BEAT_REQ = 26;
    byte HEAT_BEAT_RESP = 27;
}
