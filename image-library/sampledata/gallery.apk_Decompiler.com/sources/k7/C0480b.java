package k7;

import android.net.Uri;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;

/* renamed from: k7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0480b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BixbyDelegate e;
    public final /* synthetic */ Uri f;

    public /* synthetic */ C0480b(BixbyDelegate bixbyDelegate, Uri uri, int i2) {
        this.d = i2;
        this.e = bixbyDelegate;
        this.f = uri;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$executeCommand$0(this.f);
                return;
            default:
                this.e.lambda$handleEdit$4(this.f);
                return;
        }
    }
}
