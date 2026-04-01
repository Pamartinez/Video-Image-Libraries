package com.samsung.android.gallery.module.story.ondemand;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface StoryGenApi {
    static StoryGenApi create() {
        return new OnDemandStoryProvider();
    }

    void cancel();

    void generate(Bundle bundle, Consumer<OnDemandResultData> consumer);

    List<String> getQuerySuggestion(int i2);

    boolean isOnDemandSupportLanguage();

    void setBlockListAtTrip(ArrayList<Integer> arrayList, ArrayList<Long> arrayList2);
}
