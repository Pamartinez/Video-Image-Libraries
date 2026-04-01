package androidx.core.widget;

import android.app.Activity;
import android.view.WindowInsetsController;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(Object obj, boolean z, boolean z3, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = z;
        this.f = z3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((NestedScrollView) this.g).lambda$seslSetFadingEdgeEnabled$2(this.e, this.f);
                return;
            case 1:
                ((RecyclerView) this.g).lambda$seslSetFadingEdgeEnabled$2(this.e, this.f);
                return;
            case 2:
                SystemUi.lambda$setSystemBarBehaviorInGestureBar$1((Activity) this.g, this.e, this.f);
                return;
            case 3:
                boolean z = this.f;
                SystemUi.lambda$setSystemBarBehaviorInGestureBar$0(this.e, (WindowInsetsController) this.g, z);
                return;
            default:
                ((MediaPlayerViewImp) this.g).lambda$setAudioMute$23(this.e, this.f);
                return;
        }
    }

    public /* synthetic */ a(boolean z, WindowInsetsController windowInsetsController, boolean z3) {
        this.d = 3;
        this.e = z;
        this.g = windowInsetsController;
        this.f = z3;
    }
}
