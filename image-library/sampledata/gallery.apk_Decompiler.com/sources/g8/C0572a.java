package G8;

import com.samsung.android.gallery.module.bgm.bgmdata.BgmData;

/* renamed from: G8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0572a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmData e;

    public /* synthetic */ C0572a(BgmData bgmData, int i2) {
        this.d = i2;
        this.e = bgmData;
    }

    public final void run() {
        int i2 = this.d;
        BgmData bgmData = this.e;
        switch (i2) {
            case 0:
                bgmData.loadData();
                return;
            default:
                bgmData.notifyDataChange();
                return;
        }
    }
}
