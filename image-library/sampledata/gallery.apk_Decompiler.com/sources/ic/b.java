package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerDelegate e;

    public /* synthetic */ b(MediaPlayerDelegate mediaPlayerDelegate, int i2) {
        this.d = i2;
        this.e = mediaPlayerDelegate;
    }

    public final void run() {
        int i2 = this.d;
        MediaPlayerDelegate mediaPlayerDelegate = this.e;
        switch (i2) {
            case 0:
                mediaPlayerDelegate.prepare();
                return;
            case 1:
                mediaPlayerDelegate.lambda$destroyMediaPlayer$8();
                return;
            case 2:
                mediaPlayerDelegate.lambda$seekFinish$16();
                return;
            case 3:
                mediaPlayerDelegate.pauseInternal();
                return;
            case 4:
                mediaPlayerDelegate.onVideoPlay();
                return;
            case 5:
                mediaPlayerDelegate.setSurfaceHolder();
                return;
            case 6:
                mediaPlayerDelegate.lambda$setRenderingPosition$4();
                return;
            case 7:
                mediaPlayerDelegate.lambda$play$1();
                return;
            case 8:
                mediaPlayerDelegate.start();
                return;
            case 9:
                mediaPlayerDelegate.lambda$seekBegin$14();
                return;
            case 10:
                mediaPlayerDelegate.play();
                return;
            case 11:
                mediaPlayerDelegate.beginInstantSlowMoPlay();
                return;
            default:
                mediaPlayerDelegate.endInstantSlowMoPlay();
                return;
        }
    }
}
