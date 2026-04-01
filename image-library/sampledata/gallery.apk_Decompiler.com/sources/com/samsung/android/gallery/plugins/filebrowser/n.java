package com.samsung.android.gallery.plugins.filebrowser;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileViewHolder e;
    public final /* synthetic */ FileInfo f;

    public /* synthetic */ n(FileViewHolder fileViewHolder, FileInfo fileInfo, int i2) {
        this.d = i2;
        this.e = fileViewHolder;
        this.f = fileInfo;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$loadBitmap$2(this.f, (BitmapHolder) obj);
                return;
            case 1:
                this.e.lambda$loadBitmap$3(this.f, (BitmapHolder) obj);
                return;
            default:
                this.e.lambda$loadBitmap$4(this.f, (BitmapHolder) obj);
                return;
        }
    }
}
