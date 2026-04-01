package com.samsung.android.gallery.plugins.filebrowser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Runnable {
    public final /* synthetic */ OnBackPressCompat d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ s(OnBackPressCompat onBackPressCompat, boolean z) {
        this.d = onBackPressCompat;
        this.e = z;
    }

    public final void run() {
        this.d.lambda$notifyStateChange$0(this.e);
    }
}
