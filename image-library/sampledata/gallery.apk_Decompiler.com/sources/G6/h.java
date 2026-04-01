package g6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHighlightPresenter e;

    public /* synthetic */ h(StoryHighlightPresenter storyHighlightPresenter, int i2) {
        this.d = i2;
        this.e = storyHighlightPresenter;
    }

    public final void run() {
        int i2 = this.d;
        StoryHighlightPresenter storyHighlightPresenter = this.e;
        switch (i2) {
            case 0:
                storyHighlightPresenter.saveHistory();
                return;
            case 1:
                storyHighlightPresenter.lambda$onDataChangedOnUi$0();
                return;
            default:
                storyHighlightPresenter.onDataChangedOnUi();
                return;
        }
    }
}
