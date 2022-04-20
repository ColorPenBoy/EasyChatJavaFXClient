package com.easychat.fx.client.handle;

import com.easychat.fx.support.response.GroupAddUserResp;
import com.easychat.fx.util.ShowUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupAddUserRespHandler extends SimpleChannelInboundHandler<GroupAddUserResp> {
    private GroupAddUserRespHandler() {}
    public static GroupAddUserRespHandler INSTANCE = new GroupAddUserRespHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupAddUserResp msg) throws Exception {
        ShowUtil.addSystemMessage(msg);
        if (msg.isSuccess()) {
            System.out.println("欢迎 id: " + msg.getUserId() + "加入群聊， 群id： " +msg.getGroupId());
        } else {
            System.out.println("您所邀请的好友 id: " + msg.getUserId() + "拒绝了您的加入群聊邀请， 群id： " +msg.getGroupId());
        }
    }
}
