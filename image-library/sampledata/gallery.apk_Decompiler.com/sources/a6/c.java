package A6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryViewPagerCover;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryViewPagerCover e;

    public /* synthetic */ c(StoryViewPagerCover storyViewPagerCover, int i2) {
        this.d = i2;
        this.e = storyViewPagerCover;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$new$0((GalleryAppBarLayout) obj);
                return;
            case 1:
                this.e.requestPreview((MediaItem) obj);
                return;
            default:
                this.e.onCoverVisibilityChanged(((Boolean) obj).booleanValue());
                return;
        }
    }
}
