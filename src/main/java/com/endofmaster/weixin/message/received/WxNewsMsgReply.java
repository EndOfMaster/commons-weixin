package com.endofmaster.weixin.message.received;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZM.Wang
 */
public class WxNewsMsgReply extends WxMsgReply {

    private List<Item> items;

    public WxNewsMsgReply(WxMsg msg) {
        super(msg, "news");
    }

    @Override
    public String getContent() {
        StringBuilder content = new StringBuilder("<xml>\n" +
                "<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>\n" +
                "<CreateTime>" + createTime + "</CreateTime>\n" +
                "<MsgType><![CDATA[" + msgType + "]]></MsgType>\n" +
                "<ArticleCount>" + items.size() + "</ArticleCount>\n" +
                "<Articles>\n");
        for (Item item : items) {
            content.append("<item>\n")
                    .append("<Title><![CDATA[").append(item.title).append("]]></Title>\n")
                    .append("<Description><![CDATA[").append(item.description).append("]]></Description>\n")
                    .append("<PicUrl><![CDATA[").append(item.picUrl).append("]]></PicUrl>\n")
                    .append("<Url><![CDATA[").append(item.url).append("]]></Url>\n")
                    .append("</item>\n");
        }
        content.append("</Articles>\n</xml>");
        return content.toString();
    }

    static class Item {
        String title;
        String description;
        String picUrl;
        String url;
    }

    public WxNewsMsgReply addItem(String title, String description, String picUrl, String url) {
        if (items == null) {
            items = new ArrayList<>();
        }
        Item item = new Item();
        item.title = title;
        item.description = description;
        item.picUrl = picUrl;
        item.url = url;
        items.add(item);
        return this;
    }

}
