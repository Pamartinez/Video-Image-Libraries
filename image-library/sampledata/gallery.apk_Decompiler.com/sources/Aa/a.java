package Aa;

import com.samsung.android.gallery.plugins.mergeplayer.PlayerInstance;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PlayerInstance e;

    public /* synthetic */ a(PlayerInstance playerInstance, int i2) {
        this.d = i2;
        this.e = playerInstance;
    }

    public final void run() {
        int i2 = this.d;
        PlayerInstance playerInstance = this.e;
        switch (i2) {
            case 0:
                playerInstance.lambda$onVideoPrepared$0();
                return;
            case 1:
                playerInstance.lambda$onTimeTickUpdate$2();
                return;
            default:
                playerInstance.lambda$onVideoPlay$1();
                return;
        }
    }
}
