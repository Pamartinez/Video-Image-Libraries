package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.MediaHelper;
import java.util.function.IntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaHelper.VideoInfo f3192a;

    public /* synthetic */ z(MediaHelper.VideoInfo videoInfo) {
        this.f3192a = videoInfo;
    }

    public final Object apply(int i2) {
        return this.f3192a.lambda$toDebugString$0(i2);
    }
}
