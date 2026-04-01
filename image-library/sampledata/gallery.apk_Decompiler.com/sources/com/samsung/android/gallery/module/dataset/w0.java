package com.samsung.android.gallery.module.dataset;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataSuggestions e;
    public final /* synthetic */ int f;

    public /* synthetic */ w0(MediaDataSuggestions mediaDataSuggestions, int i2, int i7) {
        this.d = i7;
        this.e = mediaDataSuggestions;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateNewBadgeInfo$1(this.f);
                return;
            default:
                this.e.lambda$updateNewBadgeInfo$0(this.f);
                return;
        }
    }
}
