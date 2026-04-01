package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.GenEditVideoMenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GenEditVideoMenuItem e;

    public /* synthetic */ f(GenEditVideoMenuItem genEditVideoMenuItem, int i2) {
        this.d = i2;
        this.e = genEditVideoMenuItem;
    }

    public final void run() {
        int i2 = this.d;
        GenEditVideoMenuItem genEditVideoMenuItem = this.e;
        switch (i2) {
            case 0:
                genEditVideoMenuItem.prepareEditor();
                return;
            default:
                genEditVideoMenuItem.onFailExecuteVideoEditingApp();
                return;
        }
    }
}
