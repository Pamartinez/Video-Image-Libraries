package com.samsung.android.sdk.sgpl.content.story;

import com.samsung.android.sdk.sgpl.content.story.CmhStoryApiImpl;
import com.samsung.android.sdk.sgpl.content.story.MpStoryApiImpl;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1668a;

    public /* synthetic */ a(int i2) {
        this.f1668a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f1668a) {
            case 0:
                return ((CmhStoryApiImpl.Content) obj).mediaId;
            case 1:
                return ((CmhStoryApiImpl.Content) obj).mediaId;
            case 2:
                return ((MpStoryApiImpl.Content) obj).mediaId;
            default:
                return ((MpStoryApiImpl.Content) obj).mediaId;
        }
    }
}
