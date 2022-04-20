package com.easychat.fx.bean;

import com.easychat.fx.controller.Cache;
import lombok.Data;

@Data
public class Group {
    private Integer groupId;
    private String groupName;
    private Long mainUserId;
    
    
    @Override
    public String toString() {
        Integer num = Cache.groupMessageNumMap.get(groupId);
        if (num == null || num == 0) {
            return groupName;
        } else {
            return groupName + "        " + num + "未读";
        }
    }
}
