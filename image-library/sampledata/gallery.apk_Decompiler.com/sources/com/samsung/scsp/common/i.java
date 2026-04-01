package com.samsung.scsp.common;

import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.HashUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;

    public /* synthetic */ i(String str, String str2, int i2) {
        this.d = i2;
        this.e = str;
        this.f = str2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return HashUtil.getStringSHA256(this.e + NumericEnum.SEP + this.f);
            default:
                return HashUtil.getStringSHA256(this.e + NumericEnum.SEP + this.f);
        }
    }
}
