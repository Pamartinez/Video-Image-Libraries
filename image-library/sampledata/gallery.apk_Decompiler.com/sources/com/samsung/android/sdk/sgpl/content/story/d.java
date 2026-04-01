package com.samsung.android.sdk.sgpl.content.story;

import com.samsung.android.sdk.sgpl.content.story.CmhStoryApiImpl;
import com.samsung.android.sdk.sgpl.content.story.MpStoryApiImpl;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1670a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ d(boolean z, int i2) {
        this.f1670a = i2;
        this.b = z;
    }

    public final Object apply(Object obj) {
        int i2 = this.f1670a;
        boolean z = this.b;
        switch (i2) {
            case 0:
                return CmhStoryApiImpl.lambda$createStory$3(z, (CmhStoryApiImpl.CursorValues) obj);
            default:
                return MpStoryApiImpl.lambda$createStory$3(z, (MpStoryApiImpl.CursorValues) obj);
        }
    }
}
