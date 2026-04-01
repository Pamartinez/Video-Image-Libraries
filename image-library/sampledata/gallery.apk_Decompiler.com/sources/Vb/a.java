package Vb;

import android.view.WindowInsets;
import com.samsung.android.gallery.widget.search.searchbar.NewSearchToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ NewSearchToolbar e;
    public final /* synthetic */ WindowInsets f;

    public /* synthetic */ a(NewSearchToolbar newSearchToolbar, WindowInsets windowInsets, int i2) {
        this.d = i2;
        this.e = newSearchToolbar;
        this.f = windowInsets;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateInsets$5(this.f);
                return;
            default:
                this.e.lambda$initSyncWithInsetsAnimation$1(this.f);
                return;
        }
    }
}
