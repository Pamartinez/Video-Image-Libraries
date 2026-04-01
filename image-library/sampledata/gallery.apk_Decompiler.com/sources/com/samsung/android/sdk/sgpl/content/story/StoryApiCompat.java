package com.samsung.android.sdk.sgpl.content.story;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoryApiCompat {
    private final IStoryApi mImpl;

    public StoryApiCompat(Context context) {
        IStoryApi create = create(context);
        this.mImpl = create;
        Log.i("StoryApi", "init " + create);
    }

    private static IStoryApi create(Context context) {
        if (Utils.supportMpStory(context)) {
            return new MpStoryApiImpl();
        }
        return new CmhStoryApiImpl();
    }

    public int createStoryAlbum(Context context, List<String> list, String str, HashMap<String, String> hashMap) {
        return this.mImpl.createStoryAlbum(context, list, str, hashMap);
    }
}
