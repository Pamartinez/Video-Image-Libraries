package com.samsung.android.sdk.scs.ai.asr;

import android.content.SharedPreferences;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1643a;

    public /* synthetic */ g(int i2) {
        this.f1643a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f1643a) {
            case 0:
                return ((Locale) obj).toLanguageTag();
            case 1:
                return ((SharedPreferences) obj).edit();
            default:
                return Boolean.valueOf(((SharedPreferences.Editor) obj).commit());
        }
    }
}
