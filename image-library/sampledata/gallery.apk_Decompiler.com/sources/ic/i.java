package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ MediaPlayerViewImp d;
    public final /* synthetic */ double e;

    public /* synthetic */ i(MediaPlayerViewImp mediaPlayerViewImp, double d2) {
        this.d = mediaPlayerViewImp;
        this.e = d2;
    }

    public final void run() {
        this.d.lambda$set3dEffectPosition$45(this.e);
    }
}
