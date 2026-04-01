package com.samsung.android.gallery.plugins.portrait;

import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3097a;

    public /* synthetic */ a(int i2) {
        this.f3097a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3097a) {
            case 0:
                return ((LiveEffectBixbyDelegate) obj).getShareComponent();
            default:
                return new FileNameBuilder((String) obj).setExtension(IFormat.FORMAT_MP4).buildUnique();
        }
    }
}
