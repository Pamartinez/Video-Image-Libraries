package com.samsung.android.sdk.sgpl.content.story;

import com.samsung.android.sdk.sgpl.content.story.CmhStoryApiImpl;
import com.samsung.android.sdk.sgpl.content.story.MpStoryApiImpl;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;

    public /* synthetic */ b(int i2, HashMap hashMap) {
        this.d = i2;
        this.e = hashMap;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HashMap hashMap = this.e;
        switch (i2) {
            case 0:
                hashMap.put(Long.valueOf(((CmhStoryApiImpl.CursorValues) obj).mediaId), (CmhStoryApiImpl.CursorValues) obj);
                return;
            case 1:
                hashMap.put(Long.valueOf(((CmhStoryApiImpl.CursorValues) obj).mediaId), (CmhStoryApiImpl.CursorValues) obj);
                return;
            case 2:
                hashMap.put(Long.valueOf(((MpStoryApiImpl.CursorValues) obj).mediaId), (MpStoryApiImpl.CursorValues) obj);
                return;
            default:
                hashMap.put(Long.valueOf(((MpStoryApiImpl.CursorValues) obj).mediaId), (MpStoryApiImpl.CursorValues) obj);
                return;
        }
    }
}
