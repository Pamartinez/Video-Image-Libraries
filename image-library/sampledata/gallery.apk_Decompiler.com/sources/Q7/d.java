package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.GenEditMenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GenEditMenuItem e;

    public /* synthetic */ d(GenEditMenuItem genEditMenuItem, int i2) {
        this.d = i2;
        this.e = genEditMenuItem;
    }

    public final void run() {
        int i2 = this.d;
        GenEditMenuItem genEditMenuItem = this.e;
        switch (i2) {
            case 0:
                genEditMenuItem.prepareEditor();
                return;
            default:
                genEditMenuItem.onFailExecuteVideoEditingApp();
                return;
        }
    }
}
