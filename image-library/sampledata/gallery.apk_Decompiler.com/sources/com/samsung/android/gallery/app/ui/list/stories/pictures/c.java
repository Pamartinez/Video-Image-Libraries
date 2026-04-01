package com.samsung.android.gallery.app.ui.list.stories.pictures;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesPresenter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ StoryPicturesPresenter.StoryPicturesMenuUpdater d;

    public /* synthetic */ c(StoryPicturesPresenter.StoryPicturesMenuUpdater storyPicturesMenuUpdater) {
        this.d = storyPicturesMenuUpdater;
    }

    public final void accept(Object obj) {
        this.d.lambda$updateOptionsMenuAction$0((MenuItem) obj);
    }
}
