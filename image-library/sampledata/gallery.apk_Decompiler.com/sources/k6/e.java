package k6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Presenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHighlightListV2Presenter e;

    public /* synthetic */ e(StoryHighlightListV2Presenter storyHighlightListV2Presenter, int i2) {
        this.d = i2;
        this.e = storyHighlightListV2Presenter;
    }

    public final void run() {
        int i2 = this.d;
        StoryHighlightListV2Presenter storyHighlightListV2Presenter = this.e;
        switch (i2) {
            case 0:
                storyHighlightListV2Presenter.lambda$onExitSelectionMode$1();
                return;
            default:
                storyHighlightListV2Presenter.lambda$handleEditCurationDone$2();
                return;
        }
    }
}
