package com.easychat.fx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easychat.fx.bean.Group;
import com.easychat.fx.bean.Result;
import com.easychat.fx.bean.User;
import com.easychat.fx.controller.Cache;
import com.easychat.fx.util.OkHttpUtils;
import com.easychat.fx.service.config.ServerConf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zed
 * date: 2019/08/26.
 * description:
 */
public class UserService {
    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
    }
    public List<User> getFriendByUserId(Long userId) {
        return getFriendByUserId(userId,false);
    }
    
    public List<User> getFriendByUserId(Long userId, boolean newData) {
        List<User> users;
        if (Cache.cacheUsers.size() == 0 || newData) {
            String url = ServerConf.users_url.replace("ip", ServerConf.ip).replace("port", ServerConf.port);

            String usersJson = OkHttpUtils.get(url, null);
            Result result = JSON.parseObject(usersJson, Result.class);
            if (result == null) {
                users = new ArrayList<>(Cache.cacheUsers);
            } else {
                JSONArray array = (JSONArray) result.getData();
                users = array.toJavaList(User.class);
                Cache.cacheUsers.clear();
                if (users != null) {
                    Cache.cacheUsers.addAll(users);
                    Cache.cacheUserMap.clear();
                    for (User user : users) {
                        Cache.cacheUserMap.put(user.getUserId(), user);
                    }
                }
            }
        } else {
            users = new ArrayList<>(Cache.cacheUsers);
        }
        return users;
    }

    public List<Group> getGroupByUserId(Long userId) {
        return getGroupByUserId(userId, false);
    }
    public List<Group> getGroupByUserId(Long userId, boolean newData) {
        Group group1 = new Group();
        group1.setGroupId("1");
        group1.setGroupName("测试群1");
        group1.setMainUserId(1000000000L);


        Group group2 = new Group();
        group2.setGroupId("2");
        group2.setGroupName("测试群2");
        group2.setMainUserId(1000000000L);

        Group group3 = new Group();
        group3.setGroupId("3");
        group3.setGroupName("测试群3");
        group3.setMainUserId(1000000000L);

        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

//        if (Cache.cacheGroups.size() == 0 || newData) {
//            String url = ServerConf.groups_url.replace("ip", ServerConf.ip).replace("port", ServerConf.port);
//
//            String groupsJson = OkHttpUtils.get(url, null);
//            Result result = JSON.parseObject(groupsJson, Result.class);
//            JSONArray array = (JSONArray) result.getData();
//            groups = array.toJavaList(Group.class);
//            if (groups!= null) {
//                Cache.cacheGroups.clear();
//                Cache.cacheGroups.addAll(groups);
//                Cache.cacheGroupMap.clear();
//                for (Group group : groups) {
//                    Cache.cacheGroupMap.put(group.getGroupId(), group);
//                }
//            }
//        } else {
//            groups = new ArrayList<>( Cache.cacheGroups);
//        }
        return groups;
    }
    
    public User getUserById(String userId) {
        String url = ServerConf.user_url.replace("ip", ServerConf.ip).replace("port", ServerConf.port).replace("{userId}", userId);
        String usersJson = OkHttpUtils.get(url, null);
        Result result = JSON.parseObject(usersJson, Result.class);
        JSONObject object = (JSONObject) result.getData();
        return object.toJavaObject(User.class);
    }


    public Group getGroupById(String groupId) {
        String url = ServerConf.group_url.replace("ip", ServerConf.ip).replace("port", ServerConf.port).replace("{groupId}", groupId);
        String groupJson = OkHttpUtils.get(url, null);
        Result result = JSON.parseObject(groupJson, Result.class);
        JSONObject object = (JSONObject) result.getData();
        return object.toJavaObject(Group.class);
    }
}
