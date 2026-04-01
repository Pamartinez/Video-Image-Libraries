package j6;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmListView;

/* renamed from: j6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0472a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmListView e;
    public final /* synthetic */ ViewGroup.LayoutParams f;

    public /* synthetic */ C0472a(BgmListView bgmListView, ViewGroup.LayoutParams layoutParams, int i2) {
        this.d = i2;
        this.e = bgmListView;
        this.f = layoutParams;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateHeight$1(this.f);
                return;
            default:
                this.e.lambda$updateHeight$0(this.f);
                return;
        }
    }
}
