package com.samsung.android.gallery.module.clip.objectcapture;

import K.a;
import N2.j;
import N8.d;
import N8.e;
import N8.f;
import N8.g;
import O3.l;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Size;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.MaskedObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCapture;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.DeviceType;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMData;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMPanelString;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemLog;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureHelper {
    private Consumer<String[]> mAnalyticsDetailLogCallback;
    private ObjectCaptureDrawHelper mCaptureDrawHelper;
    private ObjectCaptureFileHandler mCaptureFileHelper;
    private final IClipInfo mClipInfo;
    private Dialog mDialog;
    boolean mHasData;
    private float mInitX = Float.MIN_VALUE;
    private float mInitY = Float.MIN_VALUE;
    boolean mInitialized;
    private boolean mIsCaptured;
    private MaskedObjectResult mMaskedResult;
    private ObjectCapture mObjectCapture;
    private Runnable mOnDragStartCallback;
    private Consumer<Runnable> mRequestDismissKeyGuardCallback;
    private ObjectResult mResult;
    private int mSourceHeight;
    private int mSourceWidth;
    private float mStartX = -1.0f;
    private float mStartY = -1.0f;

    /* renamed from: com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$clip$objectcapture$ObjectCaptureEraseInfo$EraseType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo$EraseType[] r0 = com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo.EraseType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$clip$objectcapture$ObjectCaptureEraseInfo$EraseType = r0
                com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo$EraseType r1 = com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo.EraseType.IMAGE_FROM_FILE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$clip$objectcapture$ObjectCaptureEraseInfo$EraseType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo$EraseType r1 = com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo.EraseType.VIDEO_FROM_FILE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        NONE,
        CAPTURING,
        CAPTURED
    }

    public ObjectCaptureHelper(IClipInfo iClipInfo) {
        MemLog memLog = new MemLog("OC");
        long currentTimeMillis = System.currentTimeMillis();
        this.mClipInfo = iClipInfo;
        this.mObjectCapture = DeepSkyHelper.getObjectCapture();
        this.mCaptureFileHelper = new ObjectCaptureFileHandler(iClipInfo);
        initHelper();
        Log.d("ObjectCaptureHelper", "construct +" + (System.currentTimeMillis() - currentTimeMillis));
        memLog.check();
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.view.MenuItem$OnMenuItemClickListener, java.lang.Object] */
    private void clearCaptureDrawHelper() {
        ObjectCaptureDrawHelper objectCaptureDrawHelper = this.mCaptureDrawHelper;
        if (objectCaptureDrawHelper != null) {
            objectCaptureDrawHelper.setOnStartDragListener(new a(10));
            this.mCaptureDrawHelper.setToolbarOnMenuItemClickListener(new Object());
            this.mCaptureDrawHelper = null;
        }
    }

    private void clearInitPoint() {
        this.mInitX = Float.MIN_VALUE;
        this.mInitY = Float.MIN_VALUE;
    }

    private void clearObjectCapture() {
        if (this.mInitialized) {
            try {
                this.mCaptureDrawHelper.clearObjectCapture();
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("clear object capture failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    private void clearStartPoint() {
        this.mStartX = -1.0f;
        this.mStartY = -1.0f;
    }

    private String eraseImageFromFile(ObjectCaptureEraseInfo objectCaptureEraseInfo, int[] iArr) {
        int removeImageObject = this.mObjectCapture.removeImageObject(objectCaptureEraseInfo.getInputPath(), objectCaptureEraseInfo.getOutputImagePath(), new VexFwkImageObjectRemover.ObjectMask(iArr, new Size(this.mSourceWidth, this.mSourceHeight), new Rect(0, 0, this.mSourceWidth, this.mSourceHeight)), true);
        Log.d("ObjectCaptureHelper", "erase image object from file", Integer.valueOf(removeImageObject), Logger.getEncodedString(objectCaptureEraseInfo.getInputPath()), Logger.getEncodedString(objectCaptureEraseInfo.getOutputImagePath()));
        if (removeImageObject == 1) {
            return objectCaptureEraseInfo.getOutputImagePath();
        }
        return null;
    }

    private String eraseObjectInternal(ObjectCaptureEraseInfo objectCaptureEraseInfo) {
        int[] iArr;
        if (isSegmentSelected()) {
            iArr = this.mMaskedResult.getMask();
        } else {
            iArr = getObjectResultMask();
        }
        if (iArr == null) {
            Log.w("ObjectCaptureHelper", "erase object skipped, invalid mask");
            return null;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$clip$objectcapture$ObjectCaptureEraseInfo$EraseType[objectCaptureEraseInfo.getEraseType().ordinal()];
        if (i2 == 1) {
            return eraseImageFromFile(objectCaptureEraseInfo, iArr);
        }
        if (i2 == 2) {
            return eraseVideoFromFile(objectCaptureEraseInfo, iArr);
        }
        throw new IncompatibleClassChangeError();
    }

    private String eraseVideoFromFile(ObjectCaptureEraseInfo objectCaptureEraseInfo, int[] iArr) {
        int removeVideoObject = this.mObjectCapture.removeVideoObject(objectCaptureEraseInfo.getInputPath(), objectCaptureEraseInfo.getOutputVideoPath(), new VexFwkVideoObjectRemover.ObjectMask(iArr, new Size(this.mSourceWidth, this.mSourceHeight), getMotionPhotoCropRect(objectCaptureEraseInfo), (long) objectCaptureEraseInfo.getFrameIndex()), true);
        Log.d("ObjectCaptureHelper", "erase video object from file", Integer.valueOf(removeVideoObject), Integer.valueOf(objectCaptureEraseInfo.getFrameIndex()), Logger.getEncodedString(objectCaptureEraseInfo.getInputPath()), Logger.getEncodedString(objectCaptureEraseInfo.getOutputVideoPath()));
        if (removeVideoObject == 1) {
            return objectCaptureEraseInfo.getOutputVideoPath();
        }
        return null;
    }

    private Bitmap getBitmapFromResult() {
        ObjectResult objectResult = this.mResult;
        if (objectResult != null) {
            return objectResult.getOutput().getObjBitmap();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public ClipData getClipData() {
        String str;
        String str2;
        if (!isViewReady()) {
            return null;
        }
        try {
            if (this.mCaptureFileHelper == null || this.mCaptureDrawHelper == null || this.mResult == null) {
                StringBuilder sb2 = new StringBuilder("unable to get clip data {");
                String str3 = "";
                if (this.mCaptureFileHelper == null) {
                    str = "F";
                } else {
                    str = str3;
                }
                sb2.append(str);
                if (this.mCaptureDrawHelper == null) {
                    str2 = "D";
                } else {
                    str2 = str3;
                }
                sb2.append(str2);
                if (this.mResult == null) {
                    str3 = "R";
                }
                sb2.append(str3);
                sb2.append("}");
                Log.e("ObjectCaptureHelper", sb2.toString());
                return null;
            }
            sendOnDragStartCallback();
            postDragStartAnalyticsDetailLog();
            return this.mCaptureFileHelper.getClipData(this.mClipInfo.getMediaItem(), getBitmapFromResult());
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("get clip data failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return null;
        }
    }

    private int[] getMaskFromBitmap() {
        try {
            Bitmap bitmapFromResult = getBitmapFromResult();
            if (bitmapFromResult != null) {
                if (!bitmapFromResult.isRecycled()) {
                    int width = bitmapFromResult.getWidth();
                    int height = bitmapFromResult.getHeight();
                    if (width != 0) {
                        if (height != 0) {
                            int[] iArr = new int[(width * height)];
                            bitmapFromResult.getPixels(iArr, 0, width, 0, 0, width, height);
                            return iArr;
                        }
                    }
                }
            }
            return null;
        } catch (Error | Exception e) {
            A.a.z(e, new StringBuilder("get mask from bitmap failed, e="), "ObjectCaptureHelper");
            return null;
        }
    }

    private Consumer<Object> getMenuCallback(int i2) {
        if (i2 == 3 || i2 == 6) {
            return new g(this, 0);
        }
        return null;
    }

    private Rect getMotionPhotoCropRect(ObjectCaptureEraseInfo objectCaptureEraseInfo) {
        try {
            if (objectCaptureEraseInfo.isVideoMode()) {
                return new Rect(0, 0, this.mSourceWidth, this.mSourceHeight);
            }
            MediaItem mediaItem = this.mClipInfo.getMediaItem();
            RectF motionPhotoCropInfo = MotionPhotoUtils.getMotionPhotoCropInfo(new SecureFile(objectCaptureEraseInfo.getInputPath()));
            if (mediaItem == null || motionPhotoCropInfo == null || motionPhotoCropInfo.isEmpty()) {
                Log.e((CharSequence) "ObjectCaptureHelper", "get motion photo crop rect failed, invalid rect or item", motionPhotoCropInfo, MediaItemUtil.getSimpleLog(mediaItem));
                return new Rect(0, 0, this.mSourceWidth, this.mSourceHeight);
            }
            Log.d("ObjectCaptureHelper", "get motion photo crop rect", motionPhotoCropInfo);
            RectF rotatedRectFRatio = RectUtils.getRotatedRectFRatio(motionPhotoCropInfo, mediaItem.getOrientation());
            float f = rotatedRectFRatio.left;
            int i2 = this.mSourceWidth;
            float f5 = rotatedRectFRatio.top;
            int i7 = this.mSourceHeight;
            return new Rect((int) (f * ((float) i2)), (int) (f5 * ((float) i7)), (int) (rotatedRectFRatio.right * ((float) i2)), (int) (rotatedRectFRatio.bottom * ((float) i7)));
        } catch (Error | Exception e) {
            A.a.z(e, new StringBuilder("get motion photo crop rect failed, e="), "ObjectCaptureHelper");
        }
    }

    private int[] getObjectResultMask() {
        int i2;
        try {
            if (this.mResult == null) {
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            int[] maskFromBitmap = getMaskFromBitmap();
            if (maskFromBitmap == null) {
                return null;
            }
            int[] iArr = new int[(this.mSourceWidth * this.mSourceHeight)];
            Rect boundRect = this.mResult.getOutput().getBoundRect();
            int i7 = 0;
            for (int i8 = 0; i8 < this.mSourceHeight; i8++) {
                for (int i10 = 0; i10 < this.mSourceWidth; i10++) {
                    if (boundRect.contains(i10, i8)) {
                        int i11 = (this.mSourceWidth * i8) + i10;
                        if (maskFromBitmap[i7] != 0) {
                            i2 = -1;
                        } else {
                            i2 = 0;
                        }
                        iArr[i11] = i2;
                        i7++;
                    } else {
                        iArr[(this.mSourceWidth * i8) + i10] = 0;
                    }
                }
            }
            Log.d("ObjectCaptureHelper", "get object result mask", Logger.vt(currentTimeMillis));
            return iArr;
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("get object result mask failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleMenu */
    public void lambda$onMenuClick$3(MenuItem menuItem) {
        this.mCaptureFileHelper.handleMenu(menuItem.getItemId(), this.mClipInfo.getMediaItem(), getBitmapFromResult(), getMenuCallback(menuItem.getItemId()));
        updateToolbarVisibilityByMenu(menuItem.getItemId());
        postMenuClickAnalyticsDetailLog(menuItem.getItemId());
    }

    private void hideProgressBar() {
        ThreadUtil.postOnUiThreadDelayed(new d(this, 2), 100);
    }

    private void initHelper() {
        try {
            Context context = this.mClipInfo.getContext();
            if (context != null) {
                ObjectCaptureDrawHelper objectCaptureDrawHelper = this.mObjectCapture.getObjectCaptureDrawHelper(context);
                this.mCaptureDrawHelper = objectCaptureDrawHelper;
                objectCaptureDrawHelper.setOnStartDragListener(new e(this));
                this.mCaptureDrawHelper.setToolbarOnMenuItemClickListener(new f(this));
                setToolbarMenu(context, !isTooLargeMask(getBitmapFromResult()));
                setDeviceType();
            }
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("init helper failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    private boolean isAllSelected() {
        try {
            ObjectCaptureDrawHelper objectCaptureDrawHelper = this.mCaptureDrawHelper;
            if (objectCaptureDrawHelper != null) {
                return objectCaptureDrawHelper.isSelectAll();
            }
            return false;
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("is select all check failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return false;
        }
    }

    private boolean isScreenLocked() {
        if (!LaunchIntent.isFromLockScreen(this.mClipInfo.getBlackboard()) || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    private boolean isTooLargeMask(Bitmap bitmap) {
        if (!Features.isEnabled(Features.SUPPORT_SMART_LASSO_AND_ERASER) || bitmap == null || bitmap.isRecycled()) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        float f = (float) (this.mSourceWidth * this.mSourceHeight);
        float height = (float) (bitmap.getHeight() * bitmap.getWidth());
        float f5 = height / f;
        Log.d("ObjectCaptureHelper", "mask ratio", Logger.vt(Float.valueOf(height), Float.valueOf(f), Float.valueOf(f5), Long.valueOf(currentTimeMillis)));
        if (f5 > 0.1f) {
            return true;
        }
        return false;
    }

    private boolean isViewReady() {
        if (!this.mInitialized || !this.mHasData) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ClipData lambda$clearCaptureDrawHelper$0() {
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$clearCaptureDrawHelper$1(MenuItem menuItem) {
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideProgressBar$2() {
        try {
            Optional.ofNullable(this.mDialog).ifPresent(new M4.d(28));
            this.mDialog = null;
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("hide progress bar failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSaveGif$4(MediaItem mediaItem, String str, Context context) {
        GPPMData gPPMData = new GPPMData(ContentUri.getUri(mediaItem), this.mStartX, this.mStartY, this.mClipInfo.getCurrentFrame(), str, new GPPMPanelString(context.getString(R$string.saving_as_gif), context.getString(R$string.cancel), context.getString(R$string.gif_saved), context.getString(R$string.close), context.getString(R$string.view)));
        Log.d("ObjectCaptureHelper", "save gif request", Logger.getEncodedString(gPPMData.toString()), this);
        this.mCaptureDrawHelper.generateGif(gPPMData, "");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSaveGif$5(Uri uri) {
        this.mClipInfo.getBlackboard().postEvent(EventMessage.obtain(3009, 6, uri));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendFakeUp$6() {
        MotionEvent obtain = MotionEvent.obtain(0, 0, 1, this.mInitX, this.mInitY, 0);
        touch(obtain);
        obtain.recycle();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setToolbarMenu$7() {
        onSaveSticker(this.mClipInfo.getBlackboard());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showProgressBar$8() {
        try {
            Dialog progressDialog = this.mClipInfo.getProgressDialog();
            this.mDialog = progressDialog;
            progressDialog.show();
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("show progress bar failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    private boolean needDismissKeyGuard(int i2) {
        if (i2 != 5) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onMenuClick(MenuItem menuItem) {
        String str;
        String str2;
        if (this.mCaptureFileHelper == null || this.mCaptureDrawHelper == null || this.mResult == null) {
            StringBuilder sb2 = new StringBuilder("unable to handle menu click {");
            String str3 = "";
            if (this.mCaptureFileHelper == null) {
                str = "F";
            } else {
                str = str3;
            }
            sb2.append(str);
            if (this.mCaptureDrawHelper == null) {
                str2 = "D";
            } else {
                str2 = str3;
            }
            sb2.append(str2);
            if (this.mResult == null) {
                str3 = "R";
            }
            sb2.append(str3);
            sb2.append("}");
            Log.e("ObjectCaptureHelper", sb2.toString());
            return false;
        }
        try {
            if (isAllSelected()) {
                clearStartPoint();
            }
            if (!isScreenLocked() || !needDismissKeyGuard(menuItem.getItemId())) {
                lambda$onMenuClick$3(menuItem);
                return true;
            }
            Consumer<Runnable> consumer = this.mRequestDismissKeyGuardCallback;
            if (consumer == null) {
                return true;
            }
            consumer.accept(new M5.a(10, this, menuItem));
            return true;
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("menu handle failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void onSaveGif(Object obj) {
        String str;
        String str2;
        ObjectCaptureHelper objectCaptureHelper;
        MediaItem mediaItem = this.mClipInfo.getMediaItem();
        if (this.mCaptureFileHelper == null || this.mCaptureDrawHelper == null || !supportSaveAsGif(mediaItem)) {
            StringBuilder sb2 = new StringBuilder("unable to save gif {");
            String str3 = "";
            if (this.mCaptureFileHelper == null) {
                str = "F";
            } else {
                str = str3;
            }
            sb2.append(str);
            if (this.mCaptureDrawHelper == null) {
                str2 = "D";
            } else {
                str2 = str3;
            }
            sb2.append(str2);
            if (mediaItem == null) {
                str3 = "M";
            }
            sb2.append(str3);
            sb2.append("}");
            Log.e("ObjectCaptureHelper", sb2.toString());
            return;
        }
        try {
            String gifTargetPath = this.mCaptureFileHelper.getGifTargetPath(mediaItem, UnsafeCast.toBoolean(obj.toString()));
            if (gifTargetPath != null) {
                Context context = this.mClipInfo.getContext();
                if (context != null) {
                    objectCaptureHelper = this;
                    try {
                        new ObjectCaptureGPPMSession().setServiceBoundCallback(new A6.a((Object) objectCaptureHelper, (Object) mediaItem, (Object) gifTargetPath, (Object) context, 11)).setCompleteCallback(new g(objectCaptureHelper, 1)).connect(objectCaptureHelper.mCaptureDrawHelper);
                    } catch (Error | Exception e) {
                        e = e;
                        j.A(e, new StringBuilder("save gif failed, e="), ArcCommonLog.TAG_COMMA, objectCaptureHelper, "ObjectCaptureHelper");
                    }
                }
            }
        } catch (Error | Exception e7) {
            e = e7;
            objectCaptureHelper = this;
            j.A(e, new StringBuilder("save gif failed, e="), ArcCommonLog.TAG_COMMA, objectCaptureHelper, "ObjectCaptureHelper");
        }
    }

    private void onSaveSticker(Blackboard blackboard) {
        blackboard.postEvent(EventMessage.obtain(3009, -1, (Object) null));
        postMenuClickAnalyticsDetailLog(3);
    }

    private void postDragStartAnalyticsDetailLog() {
        Consumer<String[]> consumer = this.mAnalyticsDetailLogCallback;
        if (consumer != null) {
            consumer.accept(new String[]{AnalyticsEventId.EVENT_DETAIL_VIEW_LONG_PRESS_CAPTURE.toString(), AnalyticsDetail.EVENT_DETAIL_CAPTURE_LONG_PRESS_TYPE_DRAG.toString()});
        }
    }

    private void postMenuClickAnalyticsDetailLog(int i2) {
        String str;
        if (this.mAnalyticsDetailLogCallback != null) {
            if (i2 == 0) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_COPY.toString();
            } else if (i2 == 1) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_SHARE.toString();
            } else if (i2 == 2) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_SAVE_AS_IMAGE.toString();
            } else if (i2 == 3) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_SAVE_AS_STICKER.toString();
            } else if (i2 == 4) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_EDIT.toString();
            } else if (i2 == 5) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_SELECT_ALL.toString();
            } else if (i2 == 6) {
                str = AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_SAVE_AS_GIF.toString();
            } else {
                str = null;
            }
            if (str != null) {
                this.mAnalyticsDetailLogCallback.accept(new String[]{str});
            }
        }
    }

    private void segmentCapture(Bitmap bitmap) {
        boolean z;
        try {
            if (supportSegmentCapture()) {
                clearMaskedObjectResult();
                RectF displayRect = this.mClipInfo.getDisplayRect();
                if (displayRect != null) {
                    float width = displayRect.width() / ((float) this.mSourceWidth);
                    int i2 = (int) ((this.mInitX - displayRect.left) / width);
                    int topMargin = (int) (((this.mInitY - displayRect.top) - ((float) this.mClipInfo.getTopMargin())) / width);
                    if (!isObjectAt(i2, topMargin)) {
                        MemLog memLog = new MemLog("OCS");
                        long currentTimeMillis = System.currentTimeMillis();
                        MaskedObjectResult capture = this.mObjectCapture.capture(bitmap, (float) i2, (float) topMargin);
                        this.mMaskedResult = capture;
                        if (isTooLargeMask(capture.getMaskedObject())) {
                            this.mMaskedResult.release();
                            this.mMaskedResult = null;
                            return;
                        }
                        this.mCaptureDrawHelper.setObjectResult(this.mMaskedResult);
                        ObjectResult objectResult = this.mCaptureDrawHelper.getObjectResult();
                        this.mResult = objectResult;
                        if (objectResult == null || !objectResult.isCaptured()) {
                            z = false;
                        } else {
                            z = true;
                        }
                        this.mHasData = z;
                        Log.d("ObjectCaptureHelper", "segment capture done" + Logger.vt(bitmap, Boolean.valueOf(this.mMaskedResult.isCaptured()), Long.valueOf(currentTimeMillis)));
                        memLog.check();
                    }
                }
            }
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("segment capture failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    private void sendFakeDown(float f, float f5) {
        MotionEvent obtain = MotionEvent.obtain(0, 0, 0, f, f5, 0);
        touch(obtain);
        obtain.recycle();
    }

    private void sendFakeUp() {
        ThreadUtil.postOnUiThreadDelayed(new d(this, 0), 500);
    }

    private void sendOnDragStartCallback() {
        Optional.ofNullable(this.mOnDragStartCallback).ifPresent(new l(0));
    }

    private void setToolbarMenu(Context context, boolean z) {
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            ToolbarMenu.Builder createToolbarMenuBuilder = this.mCaptureDrawHelper.createToolbarMenuBuilder();
            if (isSegmentSelected()) {
                createToolbarMenuBuilder.addMenu(100, atomicInteger.getAndIncrement(), context.getString(R$string.erase), false);
            } else {
                if (supportCopy()) {
                    createToolbarMenuBuilder.addCopy(atomicInteger.getAndIncrement(), false);
                }
                createToolbarMenuBuilder.addShare(atomicInteger.getAndIncrement(), false);
                if (supportSaveAsSticker()) {
                    createToolbarMenuBuilder.addSaveAsSticker(atomicInteger.getAndIncrement(), true, "com.sec.android.gallery3d.fileprovider", new e(this));
                }
                if (supportSaveAsGif(this.mClipInfo.getMediaItem())) {
                    createToolbarMenuBuilder.addSaveAsGif(atomicInteger.getAndIncrement(), true);
                }
                createToolbarMenuBuilder.addSaveAsImage(atomicInteger.getAndIncrement(), false);
                if (supportSegmentCapture() && z) {
                    createToolbarMenuBuilder.addMenu(100, atomicInteger.getAndIncrement(), context.getString(R$string.erase), false);
                }
                createToolbarMenuBuilder.addSelectAll(atomicInteger.getAndIncrement(), true);
                if (supportEdit()) {
                    createToolbarMenuBuilder.addEdit(atomicInteger.getAndIncrement(), false, context.getString(R$string.edit));
                }
            }
            this.mCaptureDrawHelper.useDefaultMenu(false);
            this.mCaptureDrawHelper.setToolbarMenu(createToolbarMenuBuilder.build());
            return;
        }
        this.mCaptureDrawHelper.useDefaultMenu(true);
    }

    private void showProgressBar() {
        ThreadUtil.postOnUiThread(new d(this, 1));
    }

    private boolean supportCopy() {
        if (Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_UPSM) || Features.isEnabled(Features.IS_AFW_MODE) || Features.isEnabled(Features.IS_GED)) {
            return false;
        }
        return true;
    }

    private boolean supportEdit() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.ClippedImageEdit);
    }

    private boolean supportSaveAsGif(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_MOTION_CLIPPER) || mediaItem == null || !mediaItem.isMotionPhoto() || mediaItem.isCloudOnly() || mediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    private boolean supportSaveAsSticker() {
        return Features.isEnabled(Features.SUPPORT_SAVE_AS_STICKER);
    }

    private boolean supportSegmentCapture() {
        MediaItem mediaItem = this.mClipInfo.getMediaItem();
        if (!Features.isEnabled(Features.SUPPORT_SMART_LASSO_AND_ERASER) || !this.mObjectCapture.isObjectRemoveSupported() || mediaItem == null || mediaItem.isCloudOnly() || mediaItem.isSharing()) {
            return false;
        }
        if ((mediaItem.isJpeg() || mediaItem.isHeif() || mediaItem.isPng()) && !this.mClipInfo.isMotionPhotoVideoMode()) {
            return true;
        }
        return false;
    }

    private void updateToolbarVisibilityByMenu(int i2) {
        if (i2 == 5) {
            this.mResult = this.mCaptureDrawHelper.getObjectResult();
            setToolbarVisibility(true);
            return;
        }
        setToolbarVisibility(false);
    }

    public void capture(Bitmap bitmap) {
        String str;
        if (this.mCaptureDrawHelper == null || this.mCaptureFileHelper == null) {
            StringBuilder sb2 = new StringBuilder("unable to capture {");
            String str2 = "";
            if (this.mCaptureFileHelper == null) {
                str = "F";
            } else {
                str = str2;
            }
            sb2.append(str);
            if (this.mCaptureDrawHelper == null) {
                str2 = "D";
            }
            sb2.append(str2);
            sb2.append("}");
            Log.e("ObjectCaptureHelper", sb2.toString());
            return;
        }
        if (this.mResult == null) {
            try {
                MemLog memLog = new MemLog("OCC");
                long currentTimeMillis = System.currentTimeMillis();
                this.mSourceWidth = bitmap.getWidth();
                this.mSourceHeight = bitmap.getHeight();
                this.mObjectCapture.init();
                this.mResult = this.mObjectCapture.capture(bitmap);
                this.mObjectCapture.release();
                this.mHasData = this.mResult.isCaptured();
                this.mCaptureDrawHelper.setBitmap(bitmap.copy(Bitmap.Config.ARGB_8888, true));
                this.mCaptureDrawHelper.setObjectResult(this.mResult);
                this.mCaptureDrawHelper.setAnimatedBitmapInfo(supportSaveAsGif(this.mClipInfo.getMediaItem()));
                this.mIsCaptured = true;
                Log.d("ObjectCaptureHelper", "capture done" + Logger.vt(bitmap, this, Long.valueOf(currentTimeMillis)));
                memLog.check();
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("capture failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
        segmentCapture(bitmap);
    }

    public void clear() {
        clearInitPoint();
        clearObjectCapture();
        clearVariables();
        clearCaptureDrawHelper();
        this.mObjectCapture = null;
        this.mCaptureFileHelper = null;
        this.mAnalyticsDetailLogCallback = null;
        this.mRequestDismissKeyGuardCallback = null;
    }

    public void clearMaskedObjectResult() {
        if (this.mInitialized) {
            try {
                this.mCaptureDrawHelper.clearMaskedObjectResult();
                this.mResult = this.mCaptureDrawHelper.getObjectResult();
                Optional.ofNullable(this.mMaskedResult).ifPresent(new M4.d(29));
                this.mMaskedResult = null;
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("clear masked object result failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    public void clearVariables() {
        this.mHasData = false;
        this.mIsCaptured = false;
        this.mResult = null;
        this.mMaskedResult = null;
        clearStartPoint();
    }

    public void dispatchDraw(Canvas canvas) {
        try {
            if (isViewReady()) {
                this.mCaptureDrawHelper.dispatchDraw(canvas);
            }
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("dispatch draw failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    /* JADX INFO: finally extract failed */
    public String eraseObject(ObjectCaptureEraseInfo objectCaptureEraseInfo) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            showProgressBar();
            String eraseObjectInternal = eraseObjectInternal(objectCaptureEraseInfo);
            Log.d("ObjectCaptureHelper", "erase object done", Logger.vt(currentTimeMillis));
            hideProgressBar();
            return eraseObjectInternal;
        } catch (Error | Exception e) {
            Log.e("ObjectCaptureHelper", "erase object failed, e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + this);
            hideProgressBar();
            return null;
        } catch (Throwable th) {
            hideProgressBar();
            throw th;
        }
    }

    public ArrayList<Rect> getBoundList() {
        ArrayList<Rect> arrayList = new ArrayList<>();
        if (hasData()) {
            for (ObjectInfo boundRect : this.mResult.getObjects()) {
                arrayList.add(boundRect.getBoundRect());
            }
        }
        return arrayList;
    }

    public float getInitX() {
        return this.mInitX;
    }

    public float getInitY() {
        return this.mInitY;
    }

    public int getSourceWidth() {
        return this.mSourceWidth;
    }

    public boolean hasData() {
        return this.mHasData;
    }

    public void init(ViewGroup viewGroup) {
        if (!this.mInitialized) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.mCaptureDrawHelper.init(viewGroup);
                this.mInitialized = true;
                Log.d("ObjectCaptureHelper", "init " + this + " +" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("init failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    public boolean isCaptured() {
        return this.mIsCaptured;
    }

    public boolean isObjectAt(int i2, int i7) {
        try {
            int findObjectIndexByPosition = this.mResult.findObjectIndexByPosition(i2, i7);
            Log.d("ObjectCaptureHelper", "is object at" + Logger.v(Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(findObjectIndexByPosition)));
            if (findObjectIndexByPosition >= 0) {
                return true;
            }
            return false;
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("is object at failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return false;
        }
    }

    public boolean isObjectSelected() {
        if (!isViewReady()) {
            return false;
        }
        try {
            return this.mCaptureDrawHelper.isObjectSelected();
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("checking object selection failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return false;
        }
    }

    public boolean isSegmentSelected() {
        MaskedObjectResult maskedObjectResult;
        try {
            if (!(this.mCaptureDrawHelper == null || (maskedObjectResult = this.mMaskedResult) == null)) {
                if (maskedObjectResult.isCaptured()) {
                    List<MaskedObjectResult> maskedObjectResult2 = this.mCaptureDrawHelper.getMaskedObjectResult();
                    if (maskedObjectResult2 != null) {
                        if (!maskedObjectResult2.isEmpty()) {
                            for (MaskedObjectResult maskedObject : maskedObjectResult2) {
                                if (Objects.equals(maskedObject.getMaskedObject(), getBitmapFromResult())) {
                                    return true;
                                }
                            }
                            return false;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            Log.e("ObjectCaptureHelper", "is segment selected check failed, e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + this);
        }
    }

    public void refresh(RectF rectF) {
        if (isViewReady()) {
            try {
                this.mCaptureDrawHelper.updateObjectRect(rectF);
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("refresh failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    public void setAnalyticsDetailLogCallback(Consumer<String[]> consumer) {
        this.mAnalyticsDetailLogCallback = consumer;
    }

    public void setBitmapRect(RectF rectF) {
        if (!this.mInitialized) {
            return;
        }
        if (rectF == null) {
            Log.e("ObjectCaptureHelper", "set bitmap rect failed, null rect");
            return;
        }
        try {
            this.mCaptureDrawHelper.setBitmapRect(rectF);
            Log.d("ObjectCaptureHelper", "set bitmap rect " + this);
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("set bitmap rect failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    public void setDeviceType() {
        DeviceType deviceType;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            FoldStateManager instance = FoldStateManager.getInstance(this.mClipInfo.getBlackboard());
            if (Features.isEnabled(Features.IS_FOLDABLE_TYPE_FOLD)) {
                if (instance == null || instance.getScreen() != FoldableScreen.FOLD_SUB) {
                    deviceType = DeviceType.LARGE_SCREEN;
                } else {
                    deviceType = DeviceType.FOLD_BOOK_TYPE;
                }
            } else if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
                deviceType = DeviceType.LARGE_SCREEN;
            } else {
                deviceType = DeviceType.NORMAL_TYPE;
            }
            this.mCaptureDrawHelper.setDeviceType(deviceType);
            Log.d("ObjectCaptureHelper", "set device type", Logger.vt(deviceType, Long.valueOf(currentTimeMillis)));
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("set device type failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    public void setInitPoint(float f, float f5) {
        this.mInitX = f;
        this.mInitY = f5;
    }

    public void setOnDragStartCallback(Runnable runnable) {
        this.mOnDragStartCallback = runnable;
    }

    public void setRequestDismissKeyGuardCallback(Consumer<Runnable> consumer) {
        this.mRequestDismissKeyGuardCallback = consumer;
    }

    public void setScale(float f) {
        if (isViewReady()) {
            try {
                this.mCaptureDrawHelper.setScaleFactor(f);
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("set scale failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    public void setScaleState(boolean z) {
        if (isViewReady()) {
            try {
                this.mCaptureDrawHelper.setOnScaleState(z ? 1 : 0);
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("set scale state failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    public void setToolbarVisibility(boolean z) {
        try {
            ObjectCaptureDrawHelper objectCaptureDrawHelper = this.mCaptureDrawHelper;
            if (objectCaptureDrawHelper == null) {
                return;
            }
            if (z) {
                objectCaptureDrawHelper.showToolbar();
            } else {
                objectCaptureDrawHelper.dismissToolbar();
            }
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("popup menu visibility change failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
        }
    }

    public void setTranslationState(boolean z) {
        if (isViewReady()) {
            try {
                this.mCaptureDrawHelper.setOnTranslationState(z ? 1 : 0);
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("set translation state failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
        }
    }

    public void start(float f, float f5, boolean z, boolean z3) {
        if (isViewReady()) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (z) {
                    sendFakeDown(f, f5);
                }
                this.mCaptureDrawHelper.setLayerView(LayerType.OUT_GLOW_LAYER);
                this.mCaptureDrawHelper.startObjectCaptureByLongClick(f, f5);
                this.mResult = this.mCaptureDrawHelper.getObjectResult();
                this.mStartX = f;
                this.mStartY = f5;
                setToolbarMenu(this.mClipInfo.getContext(), !isTooLargeMask(getBitmapFromResult()));
                if (z3) {
                    sendFakeUp();
                }
                Log.d("ObjectCaptureHelper", "start [" + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + "]" + Logger.vt(currentTimeMillis));
            } catch (Error | Exception e) {
                j.A(e, new StringBuilder("start failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            }
            clearInitPoint();
        }
    }

    public String toString() {
        char c5;
        char c6;
        char c8;
        StringBuilder sb2 = new StringBuilder("OCH[");
        if (this.mInitialized) {
            c5 = 'I';
        } else {
            c5 = 'i';
        }
        sb2.append(c5);
        if (this.mHasData) {
            c6 = 'H';
        } else {
            c6 = 'h';
        }
        sb2.append(c6);
        if (this.mIsCaptured) {
            c8 = 'C';
        } else {
            c8 = 'c';
        }
        sb2.append(c8);
        sb2.append(this.mSourceWidth);
        sb2.append("x");
        return j.e(sb2, this.mSourceHeight, ']');
    }

    public boolean touch(MotionEvent motionEvent) {
        if (!isViewReady()) {
            return false;
        }
        try {
            return this.mCaptureDrawHelper.handleTouchEvent(motionEvent);
        } catch (Error | Exception e) {
            j.A(e, new StringBuilder("touch event failed, e="), ArcCommonLog.TAG_COMMA, this, "ObjectCaptureHelper");
            return false;
        }
    }
}
