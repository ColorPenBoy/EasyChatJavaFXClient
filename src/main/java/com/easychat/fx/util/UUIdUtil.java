package com.easychat.fx.util;

import java.util.UUID;

public class UUIdUtil {
    
    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
