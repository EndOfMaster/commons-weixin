package com.endofmaster.weixin.message.received;

import com.endofmaster.weixin.WxException;
import com.endofmaster.weixin.WxServerException;
import com.endofmaster.weixin.log.WxMsgLog;
import com.endofmaster.weixin.log.WxMsgLogRepository;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;
import com.endofmaster.weixin.message.received.msg.WxMsgTypes;
import com.endofmaster.weixin.message.received.msg.WxScanEvent;
import com.endofmaster.weixin.message.received.msg.WxScanSubscribeEvent;
import com.endofmaster.weixin.message.received.msg.WxSubscribeEvent;
import com.endofmaster.weixin.message.received.msg.WxTextMsg;
import com.endofmaster.weixin.message.received.msg.WxUnsubscribeEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.CardMerchantCheckResultEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.CardNotPassCheckEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.CardPassCheckEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.PoiCheckNotifyEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.UserConsumeCardEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.UserDelCardEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.UserGetCardEvent;
import com.endofmaster.weixin.message.received.msg.cardCoupons.UserGiftingCardEvent;
import com.jifenke.commons.util.validate.ParamUtils;
import com.jifenke.weixin.message.received.msg.*;
import com.jifenke.weixin.message.received.msg.cardCoupons.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public class WxMsgService {
    private static final Logger logger = LoggerFactory.getLogger(WxMsgService.class);
    private final Map<Class, WxMsgHandler> handlers = new HashMap<>();
    private WxMsgLogRepository wxMsgLogRepository;

    public void registerHandlers(WxMsgHandler... handlers) {
        Validate.notNull(handlers);
        for (WxMsgHandler handler : handlers) {
            registerHandler(handler);
        }
    }

    public void registerHandler(WxMsgHandler handler) {
        Validate.notNull(handler);
        handlers.put(handler.getMsgClass(), handler);
    }

    public void registerMsgLog(WxMsgLogRepository wxMsgLogRepository) {
        Validate.notNull(wxMsgLogRepository);
        this.wxMsgLogRepository = wxMsgLogRepository;
    }

    @SuppressWarnings("unchecked")
    public WxReply process(String xml) throws WxException {
        WxMsg msg = parse(xml);
        if (msg != null) {
            WxMsgHandler handler = handlers.get(msg.getClass());
            if (handler != null) {
                return handler.process(msg);
            } else {
                logger.debug("No registered handler found for " + msg.getClass());
            }
        }
        return WxReply.empty();
    }

    private WxMsg parse(String xml) throws WxException {
        Element root;
        try {
            if (wxMsgLogRepository != null) {
                Map<String, String> params = ParamUtils.parseXml(xml);
                wxMsgLogRepository.save(new WxMsgLog(params));
            }
            root = DocumentHelper.parseText(xml).getRootElement();
        } catch (DocumentException e) {
            throw new WxServerException("Failed to parse message from xml.", e);
        }
        String msgType = findParam(root, "MsgType");
        String toUserName = findParam(root, "ToUserName");
        String fromUserName = findParam(root, "FromUserName");
        String createTime = findParam(root, "CreateTime");
        if (msgType.equals(WxMsgTypes.EVENT)) {
            String event = findParam(root, "Event");
            String eventKey;
            String ticket;
            String cardId;
            String refuseReason;
            String friendUserName;
            String userCardCode;
            String outerStr;
            switch (event) {
                case WxEventTypes.SUBSCRIBE:
                    eventKey = findParam(root, "EventKey", null);
                    ticket = findParam(root, "Ticket", null);
                    if (StringUtils.isAnyBlank(eventKey, ticket)) {
                        return new WxSubscribeEvent(toUserName, fromUserName, createTime);
                    } else {
                        return new WxScanSubscribeEvent(toUserName, fromUserName, createTime, eventKey, ticket);
                    }
                case WxEventTypes.UNSUBSCRIBE:
                    return new WxUnsubscribeEvent(toUserName, fromUserName, createTime);
                case WxEventTypes.SCAN:
                    eventKey = findParam(root, "EventKey");
                    ticket = findParam(root, "Ticket");
                    return new WxScanEvent(toUserName, fromUserName, createTime, eventKey, ticket);
                case WxEventTypes.POI_CHECK_NOTIFY:
                    String uniqId = findParam(root, "UniqId");
                    String poiId = findParam(root, "PoiId");
                    String result = findParam(root, "result");
                    String msg = findParam(root, "msg", "");
                    return new PoiCheckNotifyEvent(toUserName, fromUserName, createTime, uniqId, poiId, result, msg);
                case WxEventTypes.CARD_MERCHANT_CHECK_RESULT:
                    String merchantId = findParam(root, "MerchantId");
                    String isPass = findParam(root, "IsPass");
                    String reason = findParam(root, "Reason");
                    return new CardMerchantCheckResultEvent(toUserName, fromUserName, createTime, merchantId, isPass, reason);
                case WxEventTypes.CARD_PASS_CHECK:
                    cardId = findParam(root, "CardId");
                    refuseReason = findParam(root, "RefuseReason", null);
                    return new CardPassCheckEvent(toUserName, fromUserName, createTime, cardId, refuseReason);
                case WxEventTypes.CARD_NOT_PASS_CHECK:
                    cardId = findParam(root, "CardId");
                    refuseReason = findParam(root, "RefuseReason", null);
                    return new CardNotPassCheckEvent(toUserName, fromUserName, createTime, cardId, refuseReason);
                case WxEventTypes.USER_GET_CARD:
                    cardId = findParam(root, "CardId");
                    String isGiveByFriend = findParam(root, "IsGiveByFriend");
                    friendUserName = findParam(root, "FriendUserName", null);
                    userCardCode = findParam(root, "UserCardCode");
                    String oldUserCardCode = findParam(root, "OldUserCardCode", null);
                    String isRestoreMemberCard = findParam(root, "IsRestoreMemberCard", null);
                    outerStr = findParam(root, "OuterStr", null);
                    return new UserGetCardEvent(toUserName, fromUserName, createTime, cardId, isGiveByFriend, userCardCode, friendUserName, oldUserCardCode, outerStr, isRestoreMemberCard);
                case WxEventTypes.USER_GIFTING_CARD:
                    cardId = findParam(root, "CardId");
                    friendUserName = findParam(root, "FriendUserName");
                    userCardCode = findParam(root, "UserCardCode");
                    String isReturnBack = findParam(root, "IsReturnBack");
                    String isChatRoom = findParam(root, "IsChatRoom");
                    return new UserGiftingCardEvent(toUserName, fromUserName, createTime, cardId, friendUserName, userCardCode, isReturnBack, isChatRoom);
                case WxEventTypes.USER_DEL_CARD:
                    cardId = findParam(root, "CardId");
                    userCardCode = findParam(root, "UserCardCode");
                    return new UserDelCardEvent(toUserName, fromUserName, createTime, cardId, userCardCode);
                case WxEventTypes.USER_CONSUME_CARD:
                    cardId = findParam(root, "CardId");
                    userCardCode = findParam(root, "UserCardCode");
                    String consumeSource = findParam(root, "ConsumeSource");
                    String locationName = findParam(root, "LocationName", null);
                    String staffOpenId = findParam(root, "StaffOpenId", null);
                    String verifyCode = findParam(root, "VerifyCode", null);
                    String remarkAmount = findParam(root, "RemarkAmount", null);
                    outerStr = findParam(root, "OuterStr", null);
                    return new UserConsumeCardEvent(toUserName, fromUserName, createTime, cardId, userCardCode, consumeSource, locationName, staffOpenId, verifyCode, remarkAmount, outerStr);
                default:
                    logger.debug("Unsupported Event Type: " + event);
                    return null;
            }
        } else {
            switch (msgType) {
                case WxMsgTypes.TEXT:
                    String msgId = findParam(root, "MsgId");
                    String content = findParam(root, "Content");
                    return new WxTextMsg(toUserName, fromUserName, createTime, msgId, content);
                default:
                    logger.debug("Unsupported Msg Type: " + msgType);
                    return null;
            }
        }
    }

    private static String findParam(Element element, String param) throws WxServerException {
        String value = element.elementTextTrim(param);
        if (value != null) {
            return value;
        } else {
            throw new WxServerException("Could not find the parameter: " + param);
        }
    }

    private static String findParam(Element element, String param, String defaultValue) {
        String value = element.elementTextTrim(param);
        return value == null ? defaultValue : value;
    }
}
