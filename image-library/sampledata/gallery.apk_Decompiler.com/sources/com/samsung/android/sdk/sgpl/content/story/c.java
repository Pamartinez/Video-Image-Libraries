package com.samsung.android.sdk.sgpl.content.story;

import com.samsung.android.sdk.sgpl.content.story.CmhStoryApiImpl;
import com.samsung.android.sdk.sgpl.content.story.MpStoryApiImpl;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1669a;

    public /* synthetic */ c(int i2) {
        this.f1669a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f1669a) {
            case 0:
                return ((CmhStoryApiImpl.CursorValues) obj).useMediaId;
            default:
                return ((MpStoryApiImpl.CursorValues) obj).useMediaId;
        }
    }
}
