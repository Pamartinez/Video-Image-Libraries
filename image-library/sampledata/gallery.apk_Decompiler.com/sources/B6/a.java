package B6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeader;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderBasic;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryHeader e;

    public /* synthetic */ a(StoryHeader storyHeader, int i2) {
        this.d = i2;
        this.e = storyHeader;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StoryHeader storyHeader = this.e;
        StoryHeaderBasic storyHeaderBasic = (StoryHeaderBasic) obj;
        switch (i2) {
            case 0:
                storyHeader.lambda$loadHeaderItems$1(storyHeaderBasic);
                return;
            default:
                storyHeader.lambda$loadHeaderItems$2(storyHeaderBasic);
                return;
        }
    }
}
