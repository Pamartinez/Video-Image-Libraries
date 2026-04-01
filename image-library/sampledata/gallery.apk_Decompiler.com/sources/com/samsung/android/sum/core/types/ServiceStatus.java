package com.samsung.android.sum.core.types;

import B8.b;
import com.samsung.android.motionphoto.utils.v2.i;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ServiceStatus implements NumericEnum {
    NONE(0),
    LOADED(1),
    CONNECTED(2),
    DISCONNECTED(3),
    DEAD(-1);
    
    private final int value;

    private ServiceStatus(int i2) {
        this.value = i2;
    }

    public static ServiceStatus from(int i2) {
        return (ServiceStatus) Arrays.stream(values()).filter(new b(i2, 21)).findFirst().orElseThrow(new i(i2, 3));
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
