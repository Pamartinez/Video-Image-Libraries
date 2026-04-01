package Ba;

import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoCtrlView e;

    public /* synthetic */ q(VideoCtrlView videoCtrlView, int i2) {
        this.d = i2;
        this.e = videoCtrlView;
    }

    public final void run() {
        int i2 = this.d;
        VideoCtrlView videoCtrlView = this.e;
        switch (i2) {
            case 0:
                videoCtrlView.lambda$onConfigurationChanged$1();
                return;
            default:
                videoCtrlView.initSeekParams();
                return;
        }
    }
}
