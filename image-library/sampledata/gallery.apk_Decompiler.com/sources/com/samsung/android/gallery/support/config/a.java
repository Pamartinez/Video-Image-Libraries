package com.samsung.android.gallery.support.config;

import com.samsung.android.gallery.support.config.SysConfig;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3140a;

    public /* synthetic */ a(int i2) {
        this.f3140a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3140a) {
            case 0:
                return SysConfig.ConfigParams.lambda$save$0((Map.Entry) obj);
            default:
                return ((String) obj).split("=", 2);
        }
    }
}
