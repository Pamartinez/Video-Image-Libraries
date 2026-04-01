package com.samsung.android.sdk.sgpl.content.story;

import com.samsung.android.sdk.sgpl.content.story.CmhStoryApiImpl;
import com.samsung.android.sdk.sgpl.content.story.MpStoryApiImpl;
import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1671a;

    public /* synthetic */ e(int i2) {
        this.f1671a = i2;
    }

    public final int applyAsInt(Object obj) {
        switch (this.f1671a) {
            case 0:
                return ((CmhStoryApiImpl.Content) obj).index;
            default:
                return ((MpStoryApiImpl.Content) obj).index;
        }
    }
}
