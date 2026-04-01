package y6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryPicturesFragment e;

    public /* synthetic */ b(StoryPicturesFragment storyPicturesFragment, int i2) {
        this.d = i2;
        this.e = storyPicturesFragment;
    }

    public final void run() {
        int i2 = this.d;
        StoryPicturesFragment storyPicturesFragment = this.e;
        switch (i2) {
            case 0:
                storyPicturesFragment.lambda$onViewCreated$1();
                return;
            default:
                storyPicturesFragment.initRelatedView();
                return;
        }
    }
}
