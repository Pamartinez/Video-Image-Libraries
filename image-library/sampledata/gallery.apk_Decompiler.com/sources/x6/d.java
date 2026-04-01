package x6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHighlightViewPagerAdapter e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ ViewPagerHolder g;

    public /* synthetic */ d(StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter, MediaItem mediaItem, ViewPagerHolder viewPagerHolder, int i2) {
        this.d = i2;
        this.e = storyHighlightViewPagerAdapter;
        this.f = mediaItem;
        this.g = viewPagerHolder;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onBindViewHolder$5(this.f, this.g);
                return;
            default:
                this.e.lambda$validateOriginalImage$7(this.f, this.g);
                return;
        }
    }
}
