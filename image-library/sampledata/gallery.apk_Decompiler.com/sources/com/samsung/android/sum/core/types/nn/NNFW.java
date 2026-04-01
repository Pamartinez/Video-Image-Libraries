package com.samsung.android.sum.core.types.nn;

import C3.C0392b;
import com.samsung.android.motionphoto.utils.v2.h;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.android.sum.core.types.SocVendor;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum NNFW implements NumericEnum {
    NONE(0, new SocVendor[]{SocVendor.NONE}),
    SNPE(1, new SocVendor[]{r1}),
    EDEN(2, new SocVendor[]{r2}),
    TFLITE(3, new SocVendor[]{r2, r1}),
    SNAP(4, new SocVendor[]{r2, r1});
    
    private final SocVendor[] socVendor;
    private final int value;

    private NNFW(int i2, SocVendor[] socVendorArr) {
        this.value = i2;
        this.socVendor = socVendorArr;
    }

    public static NNFW fromExtension(String str) {
        return (NNFW) Collections.unmodifiableMap(new HashMap<String, NNFW>() {
            {
                put("dlc", NNFW.SNPE);
                put("tflite", NNFW.TFLITE);
                NNFW nnfw = NNFW.SNAP;
                put("tf", nnfw);
                put("pb", nnfw);
            }
        }).entrySet().stream().filter(new C0392b(str, 20)).map(new n(12)).findFirst().orElseThrow(new h(str, 1));
    }

    public SocVendor[] getSupportedVendors() {
        return this.socVendor;
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
