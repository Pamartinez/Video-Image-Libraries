package com.samsung.android.sdk.scs.ai.text.unit;

import java.math.BigDecimal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Quantity {
    private String name;
    private String symbol;
    private String type;
    private BigDecimal value;

    public Quantity() {
        this.type = "";
        this.name = "";
        this.symbol = "";
        this.value = BigDecimal.ZERO;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getType() {
        return this.type;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValue(BigDecimal bigDecimal) {
        this.value = bigDecimal;
    }

    public String toString() {
        return this.value.toPlainString() + this.symbol;
    }

    public Quantity(String str, String str2, String str3, BigDecimal bigDecimal) {
        this.type = "";
        this.name = "";
        this.symbol = "";
        BigDecimal bigDecimal2 = BigDecimal.ZERO;
        this.type = str;
        this.name = str2;
        this.symbol = str3;
        this.value = bigDecimal;
    }
}
