package k6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Presenter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHighlightListV2Presenter e;

    public /* synthetic */ c(StoryHighlightListV2Presenter storyHighlightListV2Presenter, int i2) {
        this.d = i2;
        this.e = storyHighlightListV2Presenter;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.onItemSelected(((Boolean) obj).booleanValue());
                return;
            default:
                this.e.onHeaderUpdated((MediaItem) obj);
                return;
        }
    }
}
