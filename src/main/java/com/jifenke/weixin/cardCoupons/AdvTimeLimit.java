package com.jifenke.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;

/**
 * @author ZM.Wang
 * 卡券接口高级参数中的时间限制列表对象
 */
public class AdvTimeLimit {

    private DayOfWeek type;               //限制类型枚举值：支持填入星期几的枚举值

    @JsonProperty("begin_hour")
    private Integer beginHour;                  //当前type类型下的起始时间（小时），如当前结构体内填写了MONDAY，此处填写了10，则此处表示周一 10:00可用

    @JsonProperty("begin_minute")
    private Integer beginMinute;                //当前type类型下的起始时间(分钟),如当前结构体内填写了MONDAY， begin_hour填写10，此处填写了59,则此处表示周一10:59可用

    @JsonProperty("end_hour")
    private Integer endHour;                    //当前type类型下的结束时间（小时），如当前结构体内填写了MONDAY，此处填写了20，则此处表示周一 10:00-20:00可用

    @JsonProperty("end_minute")
    private Integer endMinute;                  //当前type类型下的结束时间（分钟），如当前结构体内填写了MONDAY，begin_hour填写10，此处填写了59，则此处表示周一 10:59-00:59可用

    AdvTimeLimit() {
    }

    public AdvTimeLimit(DayOfWeek type) {
        this.type = type;
    }

    public DayOfWeek getType() {
        return type;
    }

    public Integer getBeginHour() {
        return beginHour;
    }

    public AdvTimeLimit setBeginHour(Integer beginHour) {
        this.beginHour = beginHour;
        return this;
    }

    public Integer getBeginMinute() {
        return beginMinute;
    }

    public AdvTimeLimit setBeginMinute(Integer beginMinute) {
        this.beginMinute = beginMinute;
        return this;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public AdvTimeLimit setEndHour(Integer endHour) {
        this.endHour = endHour;
        return this;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public AdvTimeLimit setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
        return this;
    }
}
