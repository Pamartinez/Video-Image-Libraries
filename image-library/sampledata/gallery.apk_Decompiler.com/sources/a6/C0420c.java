package a6;

import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandHandler;

/* renamed from: a6.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0420c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ OnDemandHandler e;
    public final /* synthetic */ String f;

    public /* synthetic */ C0420c(OnDemandHandler onDemandHandler, String str, int i2) {
        this.d = i2;
        this.e = onDemandHandler;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$startOnDemandStory$2(this.f);
                return;
            default:
                this.e.lambda$handleRelationConfirmed$3(this.f);
                return;
        }
    }
}
