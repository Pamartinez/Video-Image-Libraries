package k6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Fragment;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHighlightListV2Fragment e;

    public /* synthetic */ d(StoryHighlightListV2Fragment storyHighlightListV2Fragment, int i2) {
        this.d = i2;
        this.e = storyHighlightListV2Fragment;
    }

    public final Object get() {
        int i2 = this.d;
        StoryHighlightListV2Fragment storyHighlightListV2Fragment = this.e;
        switch (i2) {
            case 0:
                return storyHighlightListV2Fragment.lambda$createReorderHandler$3();
            default:
                return storyHighlightListV2Fragment.lambda$createReorderHandler$4();
        }
    }
}
