package com.samsung.android.gallery.app.ui.list.stories.pictures.legacy;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyViewHolderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLegacyViewAdapter<V extends IStoryPicturesBaseView> extends StoryPicturesBaseViewAdapter<V> {
    public StoryPicturesLegacyViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new StoryPicturesLegacyViewHolderFactory(context);
    }
}
