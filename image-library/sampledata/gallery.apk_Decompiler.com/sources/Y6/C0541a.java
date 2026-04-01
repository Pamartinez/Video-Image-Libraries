package y6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import java.util.function.Consumer;

/* renamed from: y6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0541a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryPicturesFragment e;

    public /* synthetic */ C0541a(StoryPicturesFragment storyPicturesFragment, int i2) {
        this.d = i2;
        this.e = storyPicturesFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StoryPicturesFragment storyPicturesFragment = this.e;
        StoryCover storyCover = (StoryCover) obj;
        switch (i2) {
            case 0:
                storyPicturesFragment.lambda$handleResolutionChange$4(storyCover);
                return;
            default:
                storyPicturesFragment.lambda$onViewCreated$0(storyCover);
                return;
        }
    }
}
