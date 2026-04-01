package u7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: u7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0522c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2710a;
    public final /* synthetic */ ContentViewerHolder b;

    public /* synthetic */ C0522c(ContentViewerHolder contentViewerHolder, int i2) {
        this.f2710a = i2;
        this.b = contentViewerHolder;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2710a;
        ContentViewerHolder contentViewerHolder = this.b;
        switch (i2) {
            case 0:
                contentViewerHolder.onMediaPlayerViewStubRequest(objArr);
                return;
            case 1:
                contentViewerHolder.onPreviewLoaded(objArr);
                return;
            case 2:
                contentViewerHolder.onImageLoaded(objArr);
                return;
            case 3:
                contentViewerHolder.onUpdateBitmapAnim(objArr);
                return;
            case 4:
                contentViewerHolder.onLastPreviewData(objArr);
                return;
            case 5:
                contentViewerHolder.resetScaleAndRegionDecodingInfo(objArr);
                return;
            case 6:
                contentViewerHolder.onDlnaButtonViewStubRequest(objArr);
                return;
            case 7:
                contentViewerHolder.onVideoMirroringUiView(objArr);
                return;
            case 8:
                contentViewerHolder.onReturnTransitionStart(objArr);
                return;
            case 9:
                contentViewerHolder.onReturnTransitionEnd(objArr);
                return;
            case 10:
                contentViewerHolder.onUpdateScaleRelative(objArr);
                return;
            case 11:
                contentViewerHolder.onVideoControllerViewStubRequest(objArr);
                return;
            case 12:
                contentViewerHolder.onSetZoomEnabled(objArr);
                return;
            case 13:
                contentViewerHolder.onZoomToMinScale(objArr);
                return;
            case 14:
                contentViewerHolder.regViewLongPressListener(objArr);
                return;
            case 15:
                contentViewerHolder.tryRegionDecoding(objArr);
                return;
            case 16:
                contentViewerHolder.onActionFromExternal(objArr);
                return;
            case 17:
                contentViewerHolder.onMotionPhotoViewModeViewStubRequest(objArr);
                return;
            case 18:
                contentViewerHolder.onVideoSeekControllerViewStubRequest(objArr);
                return;
            case 19:
                contentViewerHolder.onAudioIconViewViewStubRequest(objArr);
                return;
            case 20:
                contentViewerHolder.onAudioEraserIconViewViewStubRequest(objArr);
                return;
            case 21:
                contentViewerHolder.onQuickCropViewStubRequest(objArr);
                return;
            case 22:
                contentViewerHolder.onFlipCoverDecorLayoutViewStubRequest(objArr);
                return;
            case 23:
                contentViewerHolder.onDualShotOptionsViewStubRequest(objArr);
                return;
            case 24:
                contentViewerHolder.onObjectCaptureViewStubRequest(objArr);
                return;
            default:
                contentViewerHolder.onHighlightFrcViewStubRequest(objArr);
                return;
        }
    }
}
