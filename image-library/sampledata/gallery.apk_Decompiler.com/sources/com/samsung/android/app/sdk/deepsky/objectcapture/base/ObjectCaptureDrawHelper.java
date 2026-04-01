package com.samsung.android.app.sdk.deepsky.objectcapture.base;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.DeviceType;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMData;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMListener;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0003\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000bH&¢\u0006\u0004\b\u0010\u0010\u000eJ\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H&¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H&¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u0016H&¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001cH&¢\u0006\u0004\b\u0018\u0010\u001eJ\u0017\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001fH&¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0004H&¢\u0006\u0004\b\"\u0010#J\u001f\u0010&\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H&¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u0013H&¢\u0006\u0004\b(\u0010)J\u001f\u0010(\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H&¢\u0006\u0004\b(\u0010'J\u001f\u0010*\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H&¢\u0006\u0004\b*\u0010+J/\u00101\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/H&¢\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0013H&¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\u0013H&¢\u0006\u0004\b6\u0010)J\u001f\u00106\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H&¢\u0006\u0004\b6\u0010+J\u0017\u00109\u001a\u00020\u00042\u0006\u00108\u001a\u000207H&¢\u0006\u0004\b9\u0010:J\u001f\u00109\u001a\u00020\u00042\u0006\u00108\u001a\u0002072\u0006\u0010;\u001a\u00020\u0013H'¢\u0006\u0004\b9\u0010<J\u0017\u0010>\u001a\u00020\u00042\u0006\u00100\u001a\u00020=H&¢\u0006\u0004\b>\u0010?J\u0017\u0010A\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0013H&¢\u0006\u0004\bA\u00105J\u0017\u0010C\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u0013H&¢\u0006\u0004\bC\u00105J\u000f\u0010D\u001a\u000207H&¢\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\u0004H&¢\u0006\u0004\bF\u0010#J\u000f\u0010G\u001a\u00020\u0004H&¢\u0006\u0004\bG\u0010#J\u0017\u0010H\u001a\u00020\u00042\u0006\u00100\u001a\u00020=H&¢\u0006\u0004\bH\u0010?J\u0017\u0010K\u001a\u00020\u00042\u0006\u0010J\u001a\u00020IH'¢\u0006\u0004\bK\u0010LJ\u0017\u0010O\u001a\u00020\u00042\u0006\u0010N\u001a\u00020MH&¢\u0006\u0004\bO\u0010PJ\u000f\u0010Q\u001a\u00020\u0004H&¢\u0006\u0004\bQ\u0010#J\u000f\u0010R\u001a\u00020/H&¢\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020\u0004H&¢\u0006\u0004\bT\u0010#J\u000f\u0010U\u001a\u00020\u0004H&¢\u0006\u0004\bU\u0010#J\u0017\u0010X\u001a\u00020\u00042\u0006\u0010W\u001a\u00020VH&¢\u0006\u0004\bX\u0010YJ\u0017\u0010Z\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0013H&¢\u0006\u0004\bZ\u00105J\u0017\u0010\\\u001a\u00020\u00042\u0006\u0010N\u001a\u00020[H&¢\u0006\u0004\b\\\u0010]J\u000f\u0010_\u001a\u00020^H&¢\u0006\u0004\b_\u0010`J\u0017\u0010c\u001a\u00020\u00042\u0006\u0010b\u001a\u00020aH&¢\u0006\u0004\bc\u0010dJ\u0017\u0010g\u001a\u00020\u00042\u0006\u0010f\u001a\u00020eH&¢\u0006\u0004\bg\u0010hJ\u0017\u0010j\u001a\u00020\u00042\u0006\u0010i\u001a\u00020\u000bH&¢\u0006\u0004\bj\u0010\u000eJ\u000f\u0010k\u001a\u00020\u0013H&¢\u0006\u0004\bk\u0010)J!\u0010p\u001a\u00020\u00042\u0006\u0010m\u001a\u00020l2\b\b\u0002\u0010o\u001a\u00020nH&¢\u0006\u0004\bp\u0010qJ\u0017\u0010s\u001a\u00020\u00042\u0006\u0010N\u001a\u00020rH&¢\u0006\u0004\bs\u0010tJ\u000f\u0010u\u001a\u00020\u0004H&¢\u0006\u0004\bu\u0010#J#\u0010y\u001a\u00020\u00042\u0012\u0010x\u001a\n\u0012\u0006\b\u0001\u0012\u00020w0v\"\u00020wH&¢\u0006\u0004\by\u0010zJ\u000f\u0010{\u001a\u00020\u0013H&¢\u0006\u0004\b{\u0010)J\u0017\u0010}\u001a\u00020\u00042\u0006\u0010N\u001a\u00020|H&¢\u0006\u0004\b}\u0010~¨\u0006\u0001"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "", "Landroid/view/ViewGroup;", "parentView", "Lme/x;", "init", "(Landroid/view/ViewGroup;)V", "", "scale", "setScaleFactor", "(F)V", "", "scaleState", "setOnScaleState", "(I)V", "translationState", "setOnTranslationState", "Landroid/view/MotionEvent;", "event", "", "handleTouchEvent", "(Landroid/view/MotionEvent;)Z", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "objectResult", "setObjectResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;)V", "getObjectResult", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "maskedObjectResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;)V", "", "getMaskedObjectResult", "()Ljava/util/List;", "clearMaskedObjectResult", "()V", "x", "y", "startObjectCaptureByLongClick", "(FF)Z", "startObjectCaptureByButton", "()Z", "startObjectCaptureByTap", "(FF)I", "index", "Landroid/graphics/PointF;", "point", "Landroid/graphics/Rect;", "rect", "startObjectCaptureByResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;ILandroid/graphics/PointF;Landroid/graphics/Rect;)V", "immediately", "setShowToolbarImmediately", "(Z)V", "isObjectSelected", "Landroid/graphics/Bitmap;", "bitmap", "setBitmap", "(Landroid/graphics/Bitmap;)V", "isScale", "(Landroid/graphics/Bitmap;Z)V", "Landroid/graphics/RectF;", "setBitmapRect", "(Landroid/graphics/RectF;)V", "isAnimated", "setAnimatedBitmapInfo", "isFlexMode", "setFlexMode", "getObjectCaptureBitmap", "()Landroid/graphics/Bitmap;", "clearObjectCapture", "clearDimView", "updateObjectRect", "Landroid/graphics/Canvas;", "canvas", "dispatchDraw", "(Landroid/graphics/Canvas;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "listener", "setOnStartDragListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;)V", "showToolbar", "getObjectCaptureViewRect", "()Landroid/graphics/Rect;", "dismissToolbar", "hideToolbar", "Landroid/view/Menu;", "menu", "addToolbarMenu", "(Landroid/view/Menu;)V", "useDefaultMenu", "Landroid/view/MenuItem$OnMenuItemClickListener;", "setToolbarOnMenuItemClickListener", "(Landroid/view/MenuItem$OnMenuItemClickListener;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "createToolbarMenuBuilder", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "toolbarMenu", "setToolbarMenu", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "deviceType", "setDeviceType", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;)V", "width", "setToolbarMaxWidth", "isSelectAll", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;", "data", "", "stickerPath", "generateGif", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;Ljava/lang/String;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;", "connectGPPMSession", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;)V", "disconnectGPPMSession", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/LayerType;", "type", "setLayerView", "([Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/LayerType;)V", "isVideoClippingSupported", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "setOnMoveClipListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;)V", "OnStartDragListener", "OnMoveClipListener", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ObjectCaptureDrawHelper {

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static /* synthetic */ void generateGif$default(ObjectCaptureDrawHelper objectCaptureDrawHelper, GPPMData gPPMData, String str, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    str = "";
                }
                objectCaptureDrawHelper.generateGif(gPPMData, str);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: generateGif");
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "", "Lme/x;", "onMoveClip", "()V", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMoveClipListener {
        void onMoveClip();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "", "onStarDrag", "Landroid/content/ClipData;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnStartDragListener {
        ClipData onStarDrag();
    }

    void addToolbarMenu(Menu menu);

    void clearDimView();

    void clearMaskedObjectResult();

    void clearObjectCapture();

    void connectGPPMSession(GPPMListener gPPMListener);

    ToolbarMenu.Builder createToolbarMenuBuilder();

    void disconnectGPPMSession();

    void dismissToolbar();

    void dispatchDraw(Canvas canvas);

    void generateGif(GPPMData gPPMData, String str);

    List<MaskedObjectResult> getMaskedObjectResult();

    Bitmap getObjectCaptureBitmap();

    Rect getObjectCaptureViewRect();

    ObjectResult getObjectResult();

    boolean handleTouchEvent(MotionEvent motionEvent);

    void hideToolbar();

    void init(ViewGroup viewGroup);

    int isObjectSelected(float f, float f5);

    boolean isObjectSelected();

    boolean isSelectAll();

    boolean isVideoClippingSupported();

    void setAnimatedBitmapInfo(boolean z);

    void setBitmap(Bitmap bitmap);

    void setBitmap(Bitmap bitmap, boolean z);

    void setBitmapRect(RectF rectF);

    void setDeviceType(DeviceType deviceType);

    void setFlexMode(boolean z);

    void setLayerView(LayerType... layerTypeArr);

    void setObjectResult(MaskedObjectResult maskedObjectResult);

    void setObjectResult(ObjectResult objectResult);

    void setOnMoveClipListener(OnMoveClipListener onMoveClipListener);

    void setOnScaleState(int i2);

    void setOnStartDragListener(OnStartDragListener onStartDragListener);

    void setOnTranslationState(int i2);

    void setScaleFactor(float f);

    void setShowToolbarImmediately(boolean z);

    void setToolbarMaxWidth(int i2);

    void setToolbarMenu(ToolbarMenu toolbarMenu);

    void setToolbarOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener);

    void showToolbar();

    boolean startObjectCaptureByButton();

    boolean startObjectCaptureByButton(float f, float f5);

    boolean startObjectCaptureByLongClick(float f, float f5);

    void startObjectCaptureByResult(ObjectResult objectResult, int i2, PointF pointF, Rect rect);

    int startObjectCaptureByTap(float f, float f5);

    void updateObjectRect(RectF rectF);

    void useDefaultMenu(boolean z);
}
