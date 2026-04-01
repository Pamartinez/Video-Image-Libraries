package com.samsung.android.gallery.module.location;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.map.manager.AddressHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import o6.p;
import q6.e;
import s9.C0706a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationUpdater {
    private static final boolean USE_GMP_LOC;
    private static final boolean USE_SEC_MP = Features.isEnabled(Features.USE_SEC_MP);
    private final WeakReference<Context> mContextRef;
    private AddressHelper.GetFormattedAddress mFormattedAddressTask;
    private OnUpdateCompletionListener mListener;
    private double[] mLocation;
    private FileItemInterface mMediaItem;
    private double[] mOldLocation;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnUpdateCompletionListener {
        void onCompleted(boolean z);
    }

    static {
        boolean z;
        if (PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            z = true;
        } else {
            z = false;
        }
        USE_GMP_LOC = z;
    }

    public LocationUpdater(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    private boolean changeIsoLocation(FileItemInterface fileItemInterface, double d, double d2) {
        try {
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(fileItemInterface.getPath());
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LATITUDE, String.valueOf(d));
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LONGITUDE, String.valueOf(d2));
            return mediaMetadataInterface.saveAttributes();
        } catch (Exception e) {
            Log.e("LocationUpdater", e.toString());
            return false;
        }
    }

    private Uri getWritableContentUri(FileItemInterface fileItemInterface) {
        if (USE_GMP_LOC) {
            return GmpCompat.GALLERY_FILES_URI;
        }
        if (fileItemInterface.isCloudOnly() || Features.isEnabled(Features.USE_SEC_MP)) {
            return MediaUri.getInstance().getSecMediaUri();
        }
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    private boolean hasValidLocation() {
        double[] dArr = this.mLocation;
        boolean isValidLocation = MapUtil.isValidLocation(dArr[0], dArr[1]);
        C0212a.x("hasValidLocation=", "LocationUpdater", isValidLocation);
        return isValidLocation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLocation28$1() {
        lambda$onUpdateDatabase$2("");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLocation29$0() {
        lambda$onUpdateDatabase$2((String) null);
    }

    private void notifyLocationUpdated(FileItemInterface fileItemInterface, double[] dArr) {
        if (USE_GMP_LOC && MapUtil.isValidLocation(dArr[0], dArr[1])) {
            GmpCompat.scheduleGmpLocationServiceJob(this.mContextRef.get(), 1, false);
            postGmpDataChangeEvent(String.valueOf(fileItemInterface.getMediaId()));
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateDatabase(Object[] objArr) {
        String str;
        if (objArr == null || objArr.length <= 0) {
            str = null;
        } else {
            str = objArr[0];
        }
        if (TextUtils.isEmpty(str)) {
            Log.w("LocationUpdater", "onUpdateDatabase address text is null or empty");
        }
        SimpleThreadPool.getInstance().execute(new e(8, this, str));
    }

    private void postGmpDataChangeEvent(String str) {
        Blackboard.postGlobal("command://gmp/location/changed", new GmpCompat.GmpDataChangeDetails(3, new ArrayList(Collections.singleton(str))));
    }

    private void startFormattedAddressTask(double d, double d2) {
        AddressHelper.GetFormattedAddress getFormattedAddress = new AddressHelper.GetFormattedAddress(this.mContextRef.get());
        this.mFormattedAddressTask = getFormattedAddress;
        getFormattedAddress.setUpdateListener(new p(11, this));
        this.mFormattedAddressTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[]{Double.valueOf(d), Double.valueOf(d2)});
    }

    private int updateDatabase(Uri uri, long j2, double d, double d2, String str, long j3) {
        return new LocationApi().updateLocation(uri, j2, d, d2);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateFileAndDatabase */
    public void lambda$onUpdateDatabase$2(String str) {
        long j2;
        long j3;
        int i2;
        int i7;
        long currentTimeMillis = System.currentTimeMillis();
        FileItemInterface fileItemInterface = this.mMediaItem;
        boolean z = false;
        double[] dArr = this.mLocation;
        if (changeLocation(fileItemInterface, dArr[0], dArr[1])) {
            j2 = FileUtils.length(this.mMediaItem.getPath());
        } else {
            j2 = 0;
        }
        if (!USE_SEC_MP || !this.mMediaItem.isLocal()) {
            Uri writableContentUri = getWritableContentUri(this.mMediaItem);
            long fileId = this.mMediaItem.getFileId();
            double[] dArr2 = this.mLocation;
            j3 = 0;
            i2 = updateDatabase(writableContentUri, fileId, dArr2[0], dArr2[1], str, j2);
        } else {
            SdkConfig.GED ged = SdkConfig.GED.R;
            if (SdkConfig.atLeast(ged)) {
                long dateTaken = this.mMediaItem.getDateTaken();
                j3 = dateTaken;
                i7 = new FilesApi().reserveDateTime(this.mMediaItem.getFileId(), dateTaken);
            } else {
                j3 = 0;
                i7 = 0;
            }
            Uri locationEditUri = MediaUri.getInstance().getLocationEditUri();
            long fileId2 = this.mMediaItem.getFileId();
            double[] dArr3 = this.mLocation;
            i2 = i7 + updateDatabase(locationEditUri, fileId2, dArr3[0], dArr3[1], str, j2);
            if (SdkConfig.atLeast(ged)) {
                MediaScanner.scanFile(this.mMediaItem.getPath(), (MediaScannerListener) null);
            } else {
                new FilesApi().updateMediaSize(ContentUri.getWritableUri(this.mMediaItem), j2);
            }
        }
        Log.d("LocationUpdater", "update" + Logger.vt(Long.valueOf(this.mMediaItem.getFileId()), Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(currentTimeMillis)) + "");
        if (i2 > 0) {
            if (USE_GMP_LOC) {
                notifyLocationUpdated(this.mMediaItem, this.mLocation);
            }
            DebugLogger editLocationInstance = DebugLogger.getEditLocationInstance();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mMediaItem.getFileId());
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(Logger.getEncodedString("(" + (this.mOldLocation[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mOldLocation[1]) + ") to (" + this.mLocation[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mLocation[1] + ")" + str + ")"));
            editLocationInstance.lambda$apply$0("S", sb2.toString());
        }
        OnUpdateCompletionListener onUpdateCompletionListener = this.mListener;
        if (onUpdateCompletionListener != null) {
            if (i2 > 0) {
                z = true;
            }
            onUpdateCompletionListener.onCompleted(z);
        }
    }

    public boolean changeLocation(FileItemInterface fileItemInterface, double d, double d2) {
        if (isExifUpdatable(fileItemInterface) && ExifUtils.changeLocation(fileItemInterface.getPath(), d, d2)) {
            return true;
        }
        if ((isLocalHeif(fileItemInterface) || isLocalVideo(fileItemInterface)) && changeIsoLocation(fileItemInterface, d, d2)) {
            return true;
        }
        return false;
    }

    public void clearFormattedAddressTask() {
        AddressHelper.GetFormattedAddress getFormattedAddress = this.mFormattedAddressTask;
        if (getFormattedAddress != null) {
            getFormattedAddress.setUpdateListener((AddressHelper.OnAddressUpdateListener) null);
            this.mFormattedAddressTask.cancel(true);
            this.mFormattedAddressTask = null;
        }
    }

    public boolean isExifUpdatable(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return false;
        }
        if ((fileItemInterface.isJpeg() || fileItemInterface.isPng()) && fileItemInterface.getPath() != null && !fileItemInterface.isCloudOnly()) {
            return true;
        }
        return false;
    }

    public boolean isLocalHeif(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isHeif() || fileItemInterface.getPath() == null || fileItemInterface.isCloudOnly()) {
            return false;
        }
        return true;
    }

    public boolean isLocalVideo(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isVideo() || fileItemInterface.getPath() == null || fileItemInterface.isCloudOnly()) {
            return false;
        }
        return true;
    }

    public void setCompletionListener(OnUpdateCompletionListener onUpdateCompletionListener) {
        this.mListener = onUpdateCompletionListener;
    }

    public void updateLocation(FileItemInterface fileItemInterface, double[] dArr) {
        if (fileItemInterface == null || TextUtils.isEmpty(fileItemInterface.getPath())) {
            Log.e("LocationUpdater", "updateLocation failed. null item");
            return;
        }
        this.mMediaItem = fileItemInterface;
        this.mLocation = dArr;
        this.mOldLocation = new double[]{fileItemInterface.getLatitude(), fileItemInterface.getLongitude()};
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            updateLocation29(dArr);
        } else {
            updateLocation28(dArr);
        }
    }

    public void updateLocation28(double[] dArr) {
        if (hasValidLocation()) {
            clearFormattedAddressTask();
            startFormattedAddressTask(dArr[0], dArr[1]);
            return;
        }
        SimpleThreadPool.getInstance().execute(new C0706a(this, 1));
    }

    public void updateLocation29(double[] dArr) {
        SimpleThreadPool.getInstance().execute(new C0706a(this, 0));
    }
}
