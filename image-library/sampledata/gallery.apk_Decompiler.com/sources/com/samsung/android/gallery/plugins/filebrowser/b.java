package com.samsung.android.gallery.plugins.filebrowser;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BitmapLoader e;
    public final /* synthetic */ String f;

    public /* synthetic */ b(BitmapLoader bitmapLoader, String str, int i2) {
        this.d = i2;
        this.e = bitmapLoader;
        this.f = str;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$decodeThumbnail$4(this.f, (BitmapHolder) obj);
                return;
            default:
                this.e.lambda$decodeThumbnail$1(this.f, (BitmapHolder) obj);
                return;
        }
    }
}
