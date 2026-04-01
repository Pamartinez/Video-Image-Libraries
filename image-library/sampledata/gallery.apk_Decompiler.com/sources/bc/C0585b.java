package bc;

import com.samsung.android.gallery.widget.story.transitory.StoriesPlayableViewPager;

/* renamed from: bc.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0585b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesPlayableViewPager e;

    public /* synthetic */ C0585b(StoriesPlayableViewPager storiesPlayableViewPager, int i2) {
        this.d = i2;
        this.e = storiesPlayableViewPager;
    }

    public final void run() {
        int i2 = this.d;
        StoriesPlayableViewPager storiesPlayableViewPager = this.e;
        switch (i2) {
            case 0:
                storiesPlayableViewPager.lambda$resetViewProperty$0();
                return;
            default:
                storiesPlayableViewPager.start();
                return;
        }
    }
}
