package com.samsung.android.gallery.image360.activity.viewer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowInsets;
import android.widget.Toast;
import c0.C0086a;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$string;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.image360.widget.IImage360FastOptionView;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Image360Presenter {
    private static final List<Integer> APPBAR_MENU = Arrays.asList(new Integer[]{Integer.valueOf(R$id.capture_view)});
    /* access modifiers changed from: private */
    public String mCaptureFilePath;
    private final ArrayList<Future<?>> mFutureTaskList = new ArrayList<>();
    private boolean mIsCaptureInProgress = false;
    private boolean mIsSelfie360 = false;
    private final Image360Model mModel;
    private IGallery360Viewer.DefaultPlaybackDirection mPlaybackDirection = null;
    private final Callable<Void> mSaveCompletedListener = new Callable<Void>() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$call$0() {
            Image360Presenter.this.setCaptureInProgress(false);
            Context context = Image360Presenter.this.mView.getContext();
            if (context != null) {
                if (Image360Presenter.this.mView.getSaveStatus(Image360Presenter.this.mCaptureFilePath) == IGallery360Viewer.SaveStatus.SCREEN_CAPTURE_SUCCESS) {
                    Toast.makeText(context, R$string.gallery360viewer_screenshot_captured, 0).show();
                    return;
                }
                Log.d("Image360Presenter", "Screen capture failed: " + Image360Presenter.this.mCaptureFilePath);
                Toast.makeText(context, R$string.gallery360viewer_screenshot_capture_failed, 0).show();
            }
        }

        public Void call() {
            ThreadUtil.postOnUiThread(new b(0, this));
            return null;
        }
    };
    private ThreadPool mThreadPool;
    /* access modifiers changed from: private */
    public final IImage360View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ImageDecodeListener implements FutureListener<Bitmap> {
        private Future<Bitmap> mFuture;

        public /* synthetic */ ImageDecodeListener(Image360Presenter image360Presenter, int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onFutureDone$0() {
            Image360Presenter.this.updateBitmap(this.mFuture);
        }

        public void onFutureDone(Future<Bitmap> future) {
            this.mFuture = future;
            if (!future.isCancelled()) {
                ThreadUtil.postOnUiThread(new b(1, this));
            }
        }

        private ImageDecodeListener() {
        }
    }

    public Image360Presenter(IImage360View iImage360View) {
        this.mView = iImage360View;
        this.mModel = new Image360Model();
    }

    private boolean checkDeviceSensorSupport() {
        SensorManager sensorManager = (SensorManager) this.mView.getContext().getSystemService("sensor");
        if (sensorManager != null && sensorManager.getDefaultSensor(4) != null) {
            return true;
        }
        Log.d("Image360Presenter", "Gyro Sensor Not Supported On Device");
        return false;
    }

    private Intent createGearVRIntent(Uri uri) {
        Intent intent = new Intent();
        PackageMonitorCompat instance = PackageMonitorCompat.getInstance();
        if (instance.isPackageInstalled("com.samsung.android.app.vr.gallery2")) {
            intent.setClassName("com.samsung.android.app.vr.gallery2", "com.samsung.android.app.vr.gallery2.MainActivity");
        } else if (instance.isPackageInstalled("com.samsung.android.app.vr.gallery")) {
            intent.setClassName("com.samsung.android.app.vr.gallery", "com.samsung.android.app.vr.gallery.UnityPlayerNativeActivity");
        } else {
            Log.d("Image360Presenter", "launchGearVRView : No package is available");
            return null;
        }
        intent.setData(uri);
        intent.putExtra("viewmode", 0);
        intent.putExtra("wait_title", R$string.gallery360viewer_vr_wait_title);
        intent.setFlags(268500992);
        return intent;
    }

    private String getCaptureFilePath() {
        Object obj;
        String str;
        StringBuilder sb2 = new StringBuilder();
        int i2 = 1;
        sb2.append(FileUtils.getDirectoryFromPath(getSourceFilePath(), true));
        sb2.append(TimeUtil.getFileTimestamp());
        String sb3 = sb2.toString();
        String A10 = C0212a.A(sb3, ".jpg");
        while (FileUtils.exists(A10)) {
            StringBuilder s = C0212a.s(sb3);
            if (i2 < 10) {
                str = "00";
            } else if (i2 < 100) {
                str = "0";
            } else {
                obj = Integer.valueOf(i2);
                s.append(obj);
                s.append(".jpg");
                A10 = s.toString();
                i2++;
            }
            obj = C0086a.i(i2, str);
            s.append(obj);
            s.append(".jpg");
            A10 = s.toString();
            i2++;
        }
        return A10;
    }

    private int getSamplingSize(int i2, int i7) {
        return (int) Math.ceil(((double) i2) / ((double) (i7 * 2)));
    }

    private ThreadPool getThreadPool() {
        if (this.mThreadPool != null) {
            this.mThreadPool = ThreadPool.getInstance();
        }
        return this.mThreadPool;
    }

    private void initThreadPool() {
        this.mThreadPool = ThreadPool.getInstance();
        startBitmapDecoderTask();
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(this.mView.getScreenId(), analyticsEventId.toString());
    }

    private void postSettingLog() {
        String str;
        AnalyticsDetail analyticsDetail;
        try {
            AnalyticsLogger instance = AnalyticsLogger.getInstance();
            AnalyticsStatusId analyticsStatusId = AnalyticsStatusId.STATUS_360_PHOTO_VIEWER_IMAGE_TYPE;
            if (this.mModel.isSef360Image()) {
                if (isSelfie360()) {
                    analyticsDetail = AnalyticsDetail.IMAGE_360_SAMSUNG_SELFIE;
                } else {
                    analyticsDetail = AnalyticsDetail.IMAGE_360_SAMSUNG;
                }
                str = analyticsDetail.toString();
            } else {
                str = AnalyticsDetail.IMAGE_360_OTHER.toString();
            }
            instance.postSettingLog(analyticsStatusId, str);
        } catch (Exception e) {
            Log.e("Image360Presenter", e.getMessage());
        }
    }

    private void startBitmapDecoderTask() {
        Future submit = getThreadPool().submit(new Gallery360DecodeJob(getSourceFilePath(), 512), new ImageDecodeListener(this, 0));
        if (submit != null) {
            this.mFutureTaskList.add(submit);
        }
        Future submit2 = getThreadPool().submit(new Gallery360DecodeJob(getSourceFilePath(), 4096), new ImageDecodeListener(this, 0));
        if (submit2 != null) {
            this.mFutureTaskList.add(submit2);
        }
    }

    /* access modifiers changed from: private */
    public void updateBitmap(Future<Bitmap> future) {
        Bitmap bitmap = future.get();
        if (bitmap != null) {
            Bitmap bitmap2 = this.mModel.getBitmap();
            if (bitmap2 == null || bitmap2.getWidth() <= bitmap.getWidth() || bitmap2.getHeight() <= bitmap.getHeight()) {
                try {
                    this.mView.update360Viewer(bitmap, getCurrentViewMode());
                    this.mModel.setBitmap(bitmap);
                } catch (IllegalArgumentException e) {
                    Log.e("Image360Presenter", e.getMessage());
                } catch (NullPointerException unused) {
                    Log.e("Image360Presenter", "Viewer Instance is null");
                }
            } else {
                Log.d("Image360Presenter", "mBitmap[" + bitmap2.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap2.getHeight() + "] bitmap[" + bitmap.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap.getHeight() + "]");
            }
        } else {
            this.mView.finishWithToast(R$string.gallery360viewer_error_file_corrupt);
        }
    }

    private ArrayList<MenuItem> updateOptionsMenu(Menu menu) {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < menu.size(); i2++) {
            MenuItem item = menu.getItem(i2);
            if (!APPBAR_MENU.contains(Integer.valueOf(item.getItemId())) && item.isVisible()) {
                item.setVisible(false);
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    private void updateScaledSize(int i2, int i7) {
        if (i2 > 0 && i7 > 0) {
            this.mModel.setSize(i2, i7);
            if (isSelfie360()) {
                int i8 = 0;
                for (int max = (Math.max(i2, i7) / 4096) >> 1; max != 0; max >>= 1) {
                    i8++;
                }
                if (i8 > 0) {
                    this.mModel.setSize(getSamplingSize(i2, i8), getSamplingSize(i7, i8));
                }
            }
        }
    }

    public void captureView() {
        if (!this.mIsCaptureInProgress && this.mView != null) {
            postAnalyticsLog(AnalyticsEventId.EVENT_360_PHOTO_VIEWER_CAPTURE_IMAGE);
            String captureFilePath = getCaptureFilePath();
            this.mCaptureFilePath = captureFilePath;
            this.mView.saveCaptureImage(captureFilePath, this.mSaveCompletedListener);
        }
    }

    public IGallery360Viewer.ViewMode getCurrentViewMode() {
        return this.mModel.getCurrentViewMode();
    }

    public int getHeight() {
        return this.mModel.getHeight();
    }

    public Rect getNavigationBarInsets() {
        return this.mModel.getNavigationBarInsets(this.mView.getContext());
    }

    public IGallery360Viewer.DefaultPlaybackDirection getPlayBackDirection() {
        if (this.mPlaybackDirection == null) {
            String readFromXMP = XmpUtils.readFromXMP(getSourceFilePath(), "DIRECTION");
            if (readFromXMP == null || readFromXMP.isEmpty()) {
                this.mPlaybackDirection = IGallery360Viewer.DefaultPlaybackDirection.FRONT;
            } else {
                this.mPlaybackDirection = IGallery360Viewer.DefaultPlaybackDirection.valueOf(readFromXMP);
            }
        }
        return this.mPlaybackDirection;
    }

    public String getSourceFilePath() {
        return this.mModel.getFilePath();
    }

    public Rect getStatusBarInsets() {
        return this.mModel.getStatusBarInsets(this.mView.getContext());
    }

    public int getWidth() {
        return this.mModel.getWidth();
    }

    public void initSystemBar(WindowInsets windowInsets) {
        this.mModel.setSystemBarInsets(windowInsets);
    }

    public void initViewerInfo() {
        this.mModel.setViewerInfo(isSelfie360() ? IGallery360Viewer.ViewMode.SPREAD : IGallery360Viewer.ViewMode.MIRROR_BALL, checkDeviceSensorSupport());
    }

    public boolean isSelfie360() {
        boolean z;
        try {
            if (Integer.parseInt(XmpUtils.readFromXMP(getSourceFilePath(), "CroppedAreaTopPixels")) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.mIsSelfie360 = z;
        } catch (NumberFormatException e) {
            Log.e("Image360Presenter", e.getMessage());
        }
        return this.mIsSelfie360;
    }

    public boolean isSensorSupported() {
        return this.mModel.isSensorSupported();
    }

    public void launchGearVRView() {
        Log.i("Image360Presenter", "GearVRView launched");
        try {
            Context context = this.mView.getContext();
            Intent createGearVRIntent = createGearVRIntent(Uri.parse("file://" + getSourceFilePath()));
            if (!(context == null || createGearVRIntent == null)) {
                context.startActivity(createGearVRIntent);
            }
            postAnalyticsLog(AnalyticsEventId.EVENT_360_PHOTO_VIEWER_VIEW_ON_GEAR_VR);
        } catch (ActivityNotFoundException e) {
            Log.e("Image360Presenter", e.getMessage());
        }
    }

    public void launchPlaybackOptionsView() {
        this.mView.commitPlayBackFragment(getPlayBackDirection().ordinal());
        postAnalyticsLog(AnalyticsEventId.EVENT_360_PHOTO_VIEWER_DEFAULT_LENS_VIEW);
    }

    public void onDestroy() {
        Iterator<Future<?>> it = this.mFutureTaskList.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
        Bitmap bitmap = this.mModel.getBitmap();
        this.mModel.setBitmap((Bitmap) null);
        BitmapUtils.putBitmap(bitmap);
    }

    public void onMotionViewClicked(boolean z) {
        AnalyticsLogger.getInstance().postLog(this.mView.getScreenId(), AnalyticsEventId.EVENT_360_PHOTO_VIEWER_MOTION_VIEW.toString(), AnalyticsDetail.getOnOff(z));
    }

    public void onResetClicked() {
        postAnalyticsLog(AnalyticsEventId.EVENT_360_PHOTO_VIEWER_RESET_VIEW);
    }

    public void onViewCreated() {
        initThreadPool();
        postSettingLog();
    }

    public void onViewIconClicked(IGallery360Viewer.ViewMode viewMode) {
        this.mModel.setCurrentViewMode(viewMode);
        AnalyticsLogger.getInstance().postLog(this.mView.getScreenId(), AnalyticsEventId.EVENT_360_PHOTO_VIEWER_SELECT_VIEW_TYPE.toString(), (long) this.mModel.getViewModeName());
    }

    public void setCaptureInProgress(boolean z) {
        this.mIsCaptureInProgress = z;
    }

    public void setContentInfo(Bundle bundle) {
        if (bundle != null) {
            this.mModel.setFilePath(bundle.getString("mediaItemPath"));
            updateScaledSize(bundle.getInt("mediaItemWidth"), bundle.getInt("mediaItemHeight"));
            this.mModel.setSef360Image(bundle.getBoolean("sef360Image"));
        }
    }

    public void updateFastOptionViewMoreMenu(Menu menu) {
        IImage360FastOptionView fastOptionView = this.mView.getFastOptionView();
        if (fastOptionView != null) {
            fastOptionView.updateMoreMenu(updateOptionsMenu(menu), false);
        }
    }

    public void updatePlaybackDirection(int i2) {
        this.mPlaybackDirection = IGallery360Viewer.DefaultPlaybackDirection.values()[i2];
    }

    public void initViewerInfo(int i2, int i7, boolean z) {
        this.mModel.setViewerInfo(IGallery360Viewer.ViewMode.values()[i2], z);
        this.mPlaybackDirection = IGallery360Viewer.DefaultPlaybackDirection.values()[i7];
    }
}
