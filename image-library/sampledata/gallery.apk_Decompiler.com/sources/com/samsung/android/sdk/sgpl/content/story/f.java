package com.samsung.android.sdk.sgpl.content.story;

import com.samsung.android.sdk.sgpl.content.story.CmhStoryApiImpl;
import com.samsung.android.sdk.sgpl.content.story.MpStoryApiImpl;
import java.util.function.ToLongFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1672a;

    public /* synthetic */ f(int i2) {
        this.f1672a = i2;
    }

    public final long applyAsLong(Object obj) {
        switch (this.f1672a) {
            case 0:
                return Long.parseLong(((CmhStoryApiImpl.Content) obj).mediaId);
            default:
                return Long.parseLong(((MpStoryApiImpl.Content) obj).mediaId);
        }
    }
}
