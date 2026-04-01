package z6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryPicturesBasePresenter e;

    public /* synthetic */ b(StoryPicturesBasePresenter storyPicturesBasePresenter, int i2) {
        this.d = i2;
        this.e = storyPicturesBasePresenter;
    }

    public final void run() {
        int i2 = this.d;
        StoryPicturesBasePresenter storyPicturesBasePresenter = this.e;
        switch (i2) {
            case 0:
                storyPicturesBasePresenter.onTransitionEnd();
                return;
            case 1:
                storyPicturesBasePresenter.lambda$onHeaderItemLoadingCompleted$3();
                return;
            default:
                storyPicturesBasePresenter.onStoriesDataChangedOnUi();
                return;
        }
    }
}
