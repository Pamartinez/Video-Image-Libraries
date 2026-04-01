package A6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StorySlideShowCover;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StorySlideShowCover e;

    public /* synthetic */ b(StorySlideShowCover storySlideShowCover, int i2) {
        this.d = i2;
        this.e = storySlideShowCover;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.onMoveNext((MediaItem) obj);
                return;
            case 1:
                this.e.lambda$new$0((GalleryAppBarLayout) obj);
                return;
            default:
                this.e.onCoverVisibilityChanged(((Boolean) obj).booleanValue());
                return;
        }
    }
}
