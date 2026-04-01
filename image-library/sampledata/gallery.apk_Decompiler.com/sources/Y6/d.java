package y6;

import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesPresenter;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryPicturesPresenter e;
    public final /* synthetic */ List f;

    public /* synthetic */ d(StoryPicturesPresenter storyPicturesPresenter, List list, int i2) {
        this.d = i2;
        this.e = storyPicturesPresenter;
        this.f = list;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onOptionSaveSelected$3(this.f);
                return;
            default:
                this.e.lambda$onOptionShareSelected$2(this.f);
                return;
        }
    }
}
