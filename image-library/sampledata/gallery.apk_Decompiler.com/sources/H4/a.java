package H4;

import androidx.appcompat.widget.Toolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Toolbar e;

    public /* synthetic */ a(Toolbar toolbar, int i2) {
        this.d = i2;
        this.e = toolbar;
    }

    public final void run() {
        int i2 = this.d;
        Toolbar toolbar = this.e;
        switch (i2) {
            case 0:
                toolbar.seslSetTitleAlpha(1.0f);
                return;
            case 1:
                toolbar.collapseActionView();
                return;
            default:
                toolbar.invalidateMenu();
                return;
        }
    }
}
