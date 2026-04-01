package com.samsung.android.gallery.module.lottie.recorder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmMixer e;

    public /* synthetic */ a(BgmMixer bgmMixer, int i2) {
        this.d = i2;
        this.e = bgmMixer;
    }

    public final void run() {
        int i2 = this.d;
        BgmMixer bgmMixer = this.e;
        switch (i2) {
            case 0:
                bgmMixer.lambda$mix$1();
                return;
            default:
                bgmMixer.lambda$cancel$3();
                return;
        }
    }
}
