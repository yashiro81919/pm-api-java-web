package com.sty.entity;

public class Crypto {

    private int cmcId;

    private int quantity;

    private String remark;

    public int getCmcId() {
        return cmcId;
    }

    public void setCmcId(int cmcId) {
        this.cmcId = cmcId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "cmcId=" + cmcId +
                ", quantity=" + quantity +
                ", remark='" + remark + '\'' +
                '}';
    }
}
