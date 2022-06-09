package com.coderunning.enums;

public enum OrderStateEnum {

    WAIT_PAY(0, "待支付"),
    WAIT_DELIVER(1, "待发货"),
    WAIT_RECEIVE(2, "待收货"),
    REFUNDING(3, "退款中"),

    FINISH(10, "已完成"),
    REFUNDED(11, "已退款"),
            ;

    private Integer code;
    private String desc;

    public static OrderStateEnum getEnumByCode(Integer code) {
        for (OrderStateEnum stateEnum : values()) {
            if (stateEnum.getCode().equals(code)) {
                return stateEnum;
            }
        }
        throw new RuntimeException("code非法");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    OrderStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
