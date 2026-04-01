package B6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderMapView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHeaderMapView e;

    public /* synthetic */ b(StoryHeaderMapView storyHeaderMapView, int i2) {
        this.d = i2;
        this.e = storyHeaderMapView;
    }

    public final void run() {
        int i2 = this.d;
        StoryHeaderMapView storyHeaderMapView = this.e;
        switch (i2) {
            case 0:
                storyHeaderMapView.lambda$updateSnapshot$3();
                return;
            default:
                storyHeaderMapView.lambda$onViewAttachedToWindow$0();
                return;
        }
    }
}
