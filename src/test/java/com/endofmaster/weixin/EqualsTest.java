package com.endofmaster.weixin;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author ZM.Wang
 */
public class EqualsTest {

    @Test
    public void test() {
        Assertions.assertTrue("json".equalsIgnoreCase("JSON"));
    }

//    @Test
//    public void test2() {
//        List<WxNewsMsgReply.Item> list = new ArrayList<>();
//        WxNewsMsgReply.Item item = new WxNewsMsgReply.Item();
//        item.title = "title";
//        item.description = "description";
//        item.picUrl = "picUrl";
//        item.url = "url";
//        list.add(item);
//        StringBuilder content = new StringBuilder("<xml>\n" +
//                "<ToUserName><![CDATA[" + "toUserName" + "]]></ToUserName>\n" +
//                "<FromUserName><![CDATA[" + "fromUserName" + "]]></FromUserName>\n" +
//                "<CreateTime>" + "createTime" + "</CreateTime>\n" +
//                "<MsgType><![CDATA[" + "msgType" + "]]></MsgType>\n" +
//                "<ArticleCount>" + list.size() + "</ArticleCount>\n" +
//                "<Articles>\n");
//        for (WxNewsMsgReply.Item item2 : list) {
//            content.append("<item>\n")
//                    .append("<Title><![CDATA[").append(item2.title).append("]]></Title>\n")
//                    .append("<Description><![CDATA[").append(item2.description).append("]]></Description>\n")
//                    .append("<PicUrl><![CDATA[").append(item2.picUrl).append("]]></PicUrl>\n")
//                    .append("<Url><![CDATA[").append(item2.url).append("]]></Url>\n")
//                    .append("</item>\n");
//        }
//        content.append("</Articles>\n</xml>");
//        System.err.println(content.toString());
//    }
}
