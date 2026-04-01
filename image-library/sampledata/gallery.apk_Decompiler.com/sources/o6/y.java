package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.VideoPreviewDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoPreviewDelegate e;

    public /* synthetic */ y(VideoPreviewDelegate videoPreviewDelegate, int i2) {
        this.d = i2;
        this.e = videoPreviewDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        VideoPreviewDelegate videoPreviewDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                videoPreviewDelegate.onKeepPause(objArr);
                return;
            case 1:
                videoPreviewDelegate.requestVideoPreview(objArr);
                return;
            case 2:
                videoPreviewDelegate.stopVideoPreview(objArr);
                return;
            case 3:
                videoPreviewDelegate.onUserMute(objArr);
                return;
            case 4:
                videoPreviewDelegate.onAudioFocus(objArr);
                return;
            case 5:
                videoPreviewDelegate.onFilterChanged(objArr);
                return;
            case 6:
                videoPreviewDelegate.onBottomSheetStateChanged(objArr);
                return;
            default:
                videoPreviewDelegate.lambda$addListenEvent$1(objArr);
                return;
        }
    }
}
