package com.samsung.android.sdk.sgpl.content.story;

import android.content.Context;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryApi {
    public static int createStoryAlbum(Context context, List<String> list, String str, HashMap<String, String> hashMap) {
        return new StoryApiCompat(context).createStoryAlbum(context, list, str, hashMap);
    }
}
