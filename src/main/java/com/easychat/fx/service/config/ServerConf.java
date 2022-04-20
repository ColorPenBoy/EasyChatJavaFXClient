package com.easychat.fx.service.config;

public interface ServerConf {
//    String ip = "192.168.1.4";
//    String port = "9004";
//    int netty_port = 8000;
    String ip = "172.20.10.3";
    String port = "9999";
    int netty_port = 10022;

    String users_url = "http://ip:port/users";
    String groups_url = "http://ip:port/groups";

    String user_url = "http://ip:port/user/{userId}";
    String group_url = "http://ip:port/group/{groupId}";
    String upload_image_url = "http://ip:port/image/upload";

}
