package com.samsung.android.gallery.app.activity.external;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShortcutViewNavigatorController e;

    public /* synthetic */ q(ShortcutViewNavigatorController shortcutViewNavigatorController, int i2) {
        this.d = i2;
        this.e = shortcutViewNavigatorController;
    }

    public final void run() {
        int i2 = this.d;
        ShortcutViewNavigatorController shortcutViewNavigatorController = this.e;
        switch (i2) {
            case 0:
                shortcutViewNavigatorController.lambda$startStoryPicturesFromBixby$3();
                return;
            default:
                shortcutViewNavigatorController.lambda$moveToTrash$4();
                return;
        }
    }
}
