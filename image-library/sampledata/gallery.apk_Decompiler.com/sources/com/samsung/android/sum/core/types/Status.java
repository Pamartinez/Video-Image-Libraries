package com.samsung.android.sum.core.types;

import B8.b;
import com.samsung.android.motionphoto.utils.v2.i;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Status {
    OK(0),
    ERROR_NOT_SUPPORTED(-1),
    ERROR_PROCESS_FAILED(-2),
    ERROR_DECODE_FAILED(-3),
    ERROR_ENCODE_FAILED(-4);
    
    private final int value;

    private Status(int i2) {
        this.value = i2;
    }

    public static Status from(int i2) {
        return (Status) Arrays.stream(values()).filter(new b(i2, 22)).findFirst().orElseThrow(new i(i2, 4));
    }
}
