package com.samsung.android.gallery.settings.activity;

import android.net.Uri;
import android.os.Bundle;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3109a;

    public /* synthetic */ f(int i2) {
        this.f3109a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3109a) {
            case 0:
                return ((Uri) obj).getQueryParameter("position_guide_key");
            case 1:
                return ((Bundle) obj).getString(":settings:fragment_args_key");
            default:
                return ((Bundle) obj).getString("position_guide_key");
        }
    }
}
