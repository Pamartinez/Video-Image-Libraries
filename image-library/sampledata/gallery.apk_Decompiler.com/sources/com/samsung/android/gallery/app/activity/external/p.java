package com.samsung.android.gallery.app.activity.external;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShortcutViewNavigatorController e;
    public final /* synthetic */ int f;
    public final /* synthetic */ String g;

    public /* synthetic */ p(int i2, ShortcutViewNavigatorController shortcutViewNavigatorController, String str) {
        this.d = 1;
        this.e = shortcutViewNavigatorController;
        this.f = i2;
        this.g = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$moveToAlbumsForLocalDbUpdate$6(this.g, this.f);
                return;
            case 1:
                this.e.lambda$publishAlbumData$5(this.f, this.g);
                return;
            default:
                this.e.lambda$moveToAlbumsForLocalDbUpdate$8(this.g, this.f);
                return;
        }
    }

    public /* synthetic */ p(ShortcutViewNavigatorController shortcutViewNavigatorController, String str, int i2, int i7) {
        this.d = i7;
        this.e = shortcutViewNavigatorController;
        this.g = str;
        this.f = i2;
    }
}
