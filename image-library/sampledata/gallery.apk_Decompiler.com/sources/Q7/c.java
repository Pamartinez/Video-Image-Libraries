package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditMenuItem e;

    public /* synthetic */ c(EditMenuItem editMenuItem, int i2) {
        this.d = i2;
        this.e = editMenuItem;
    }

    public final void run() {
        int i2 = this.d;
        EditMenuItem editMenuItem = this.e;
        switch (i2) {
            case 0:
                editMenuItem.prepareEditor();
                return;
            default:
                editMenuItem.onFailExecuteVideoEditingApp();
                return;
        }
    }
}
