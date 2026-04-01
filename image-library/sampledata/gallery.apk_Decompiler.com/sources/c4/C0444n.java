package c4;

import com.samsung.android.gallery.app.service.VideoConversionService;

/* renamed from: c4.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0444n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoConversionService e;

    public /* synthetic */ C0444n(VideoConversionService videoConversionService, int i2) {
        this.d = i2;
        this.e = videoConversionService;
    }

    public final void run() {
        int i2 = this.d;
        VideoConversionService videoConversionService = this.e;
        switch (i2) {
            case 0:
                videoConversionService.onSuccess();
                return;
            case 1:
                videoConversionService.onFail();
                return;
            case 2:
                videoConversionService.encodingStart();
                return;
            case 3:
                videoConversionService.stopSelf();
                return;
            default:
                videoConversionService.onInterruptInternal();
                return;
        }
    }
}
