package com.samsung.android.gallery.module.search.autocomplete;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AutoCompleteDataLoader {
    protected final String TAG = getClass().getSimpleName();
    protected String mLastRequestedKeyword;

    public abstract void cancelLastRequest();

    public abstract void initMediaData(Blackboard blackboard);

    public abstract void load(String str, String str2, boolean z, BiConsumer<ArrayList<AutoCompleteItem>, String> biConsumer);

    public void release() {
    }
}
