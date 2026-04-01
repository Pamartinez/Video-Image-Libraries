package com.samsung.android.sdk.scs.ai.asr.tasks;

import com.samsung.android.sdk.scs.ai.asr.DictationType;
import java.io.InputStream;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1649a;

    public /* synthetic */ a(int i2) {
        this.f1649a = i2;
    }

    public final Object apply(Object obj) {
        int ordinal;
        switch (this.f1649a) {
            case 0:
                ordinal = ((DictationType) obj).ordinal();
                break;
            case 1:
                ordinal = ((InputStream) obj).hashCode();
                break;
            default:
                ordinal = ((String) obj).length();
                break;
        }
        return Integer.valueOf(ordinal);
    }
}
