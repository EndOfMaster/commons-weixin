package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.validate.InvalidParamException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;

/**
 * @author YQ.Huang
 */
public class WxpayChargeCallback extends WxpayCallback {

    public WxpayChargeCallback(String key, HttpServletRequest request) {
        super(key, request);
    }

    public void validate(long amount) {
        validateTotalFee(amount);
    }

    private void validateTotalFee(long amount) {
        String param = "total_fee";
        String actual = findParam(params, param);
        if (!StringUtils.equals(param, actual))
            throw new InvalidParamException(param, amount, actual);
    }

    @Override
    public Map<String, String> buildResultParams() {
        String param = "transaction_id";
        return Collections.singletonMap(param, findParam(params, param));
    }


    public String getTradeNo() {
        return buildResultParams().get("transaction_id");
    }

}
