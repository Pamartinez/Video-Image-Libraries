package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.StoryLiveEffectDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryLiveEffectDelegate e;

    public /* synthetic */ x(StoryLiveEffectDelegate storyLiveEffectDelegate, int i2) {
        this.d = i2;
        this.e = storyLiveEffectDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StoryLiveEffectDelegate storyLiveEffectDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                storyLiveEffectDelegate.setVisible(objArr);
                return;
            default:
                storyLiveEffectDelegate.setEnable(objArr);
                return;
        }
    }
}
