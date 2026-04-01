package com.samsung.android.sum.core.plugin;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ NativeUniImgpPlugin d;

    public /* synthetic */ g(NativeUniImgpPlugin nativeUniImgpPlugin) {
        this.d = nativeUniImgpPlugin;
    }

    public final void run() {
        this.d.release();
    }
}
