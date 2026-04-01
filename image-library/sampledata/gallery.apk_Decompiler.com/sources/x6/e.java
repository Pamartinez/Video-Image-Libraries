package x6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHighlightViewPagerV2 e;

    public /* synthetic */ e(StoryHighlightViewPagerV2 storyHighlightViewPagerV2, int i2) {
        this.d = i2;
        this.e = storyHighlightViewPagerV2;
    }

    public final void run() {
        int i2 = this.d;
        StoryHighlightViewPagerV2 storyHighlightViewPagerV2 = this.e;
        switch (i2) {
            case 0:
                storyHighlightViewPagerV2.lambda$updateEndAnimatorView$0();
                return;
            case 1:
                storyHighlightViewPagerV2.onEndProgressEnd();
                return;
            default:
                storyHighlightViewPagerV2.onEndProgressCancel();
                return;
        }
    }
}
