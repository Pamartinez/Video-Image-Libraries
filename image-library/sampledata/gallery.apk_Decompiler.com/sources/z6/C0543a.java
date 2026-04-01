package z6;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: z6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0543a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryPicturesBasePresenter e;

    public /* synthetic */ C0543a(StoryPicturesBasePresenter storyPicturesBasePresenter, int i2) {
        this.d = i2;
        this.e = storyPicturesBasePresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        StoryPicturesBasePresenter storyPicturesBasePresenter = this.e;
        switch (i2) {
            case 0:
                storyPicturesBasePresenter.onViewChanged(obj, bundle);
                return;
            default:
                storyPicturesBasePresenter.onAddItemFromPhotoEditor(obj, bundle);
                return;
        }
    }
}
