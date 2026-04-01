package srib.vizinsight.dvs;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DVS {
    public Boolean isPamir = Boolean.FALSE;
    private SegmentAsyncTask segmentAsyncTask;

    static {
        try {
            System.load("/system/lib64/libdvs.camera.samsung.so");
        } catch (UnsatisfiedLinkError e) {
            Log.d("DVS", e.toString());
        }
    }

    private native boolean checkLinkJni();

    private native boolean deInitJni();

    private native boolean detectCheckJni(Bitmap bitmap, int i2, int i7);

    private native ArrayList<BoxInfo> detectJni(Bitmap bitmap);

    private native byte[] detectSegmentJni(Bitmap bitmap, int i2, int i7, int[] iArr, int[] iArr2);

    private native byte[] detectSegmentJni(Bitmap bitmap, int i2, int i7, int[] iArr, int[] iArr2, int i8);

    private native boolean generateGIFJni(String str, int i2);

    private native UNF_Object_Segment_Info getSegmentInfoJni(Bitmap bitmap);

    private native boolean initJni(String str, String str2, int i2, int i7, int i8, int i10);

    private native boolean initJni(String str, String str2, String str3, int i2, int i7, int i8, int i10);

    private native boolean resetFrameCounterJni(boolean z);

    private native boolean segmentJni(Bitmap bitmap, Bitmap bitmap2, int i2, int i7, boolean z);

    private native boolean setMaxGIFResolutionJni(int i2);

    private native boolean setThresholdsJni(int i2, int i7, int i8, int i10);

    public Bitmap DVSAPKGetSegment(Bitmap bitmap, int i2, int i7, boolean z) {
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (segmentJni(bitmap, copy, i2, i7, z)) {
            return copy;
        }
        return null;
    }

    public void DVSAbort() {
        SegmentAsyncTask segmentAsyncTask2 = this.segmentAsyncTask;
        if (segmentAsyncTask2 != null) {
            segmentAsyncTask2.cancel(false);
        }
        Log.d("DVS", "DVSAbort: Task cancelled");
    }

    public boolean DVSCheckConfig(DVSConfig dVSConfig) {
        boolean z;
        try {
            z = checkLinkJni();
        } catch (UnsatisfiedLinkError unused) {
            Log.d("DVS", "DVSCheckConfig UnsatisfiedLinkError, Failed to link library at /system/lib64/libdvs.camera.samsung.so");
            z = false;
        }
        if (!Files.isReadable(new File(dVSConfig.odModelPath).toPath())) {
            Log.d("DVS", "DVSCheckConfig OD model not readable at: " + dVSConfig.odModelPath);
            z = false;
        }
        if (Files.isReadable(new File(dVSConfig.segmenterModelPath).toPath())) {
            return z;
        }
        Log.d("DVS", "DVSCheckConfig Segmenter model not readable at: " + dVSConfig.segmenterModelPath);
        return false;
    }

    public boolean DVSDeInit() {
        return deInitJni();
    }

    public ArrayList<BoxInfo> DVSDetect(Bitmap bitmap) {
        return detectJni(bitmap);
    }

    public boolean DVSDetectCheck(Bitmap bitmap, int i2, int i7, int i8, int i10) {
        int width = (int) ((((float) i2) / ((float) i8)) * ((float) bitmap.getWidth()));
        int height = (int) ((((float) i7) / ((float) i10)) * ((float) bitmap.getHeight()));
        if (i2 == -1 && i7 == -1) {
            width = -1;
            height = -1;
        }
        return detectCheckJni(bitmap, width, height);
    }

    public int[] DVSDetectSegment(Bitmap bitmap, int i2, int i7, int i8, int i10, int[] iArr) {
        int i11;
        int i12;
        long currentTimeMillis = System.currentTimeMillis();
        int width = (int) ((((float) i2) / ((float) i8)) * ((float) bitmap.getWidth()));
        int height = (int) ((((float) i7) / ((float) i10)) * ((float) bitmap.getHeight()));
        if (i2 == -1 && i7 == -1) {
            i12 = -1;
            i11 = -1;
        } else {
            i12 = width;
            i11 = height;
        }
        int[] iArr2 = new int[2];
        int height2 = bitmap.getHeight() * bitmap.getWidth();
        int[] iArr3 = new int[height2];
        byte[] detectSegmentJni = detectSegmentJni(bitmap, i12, i11, iArr2, iArr);
        StringBuilder sb2 = new StringBuilder("DVSDetectSegment Length of byte array = ");
        sb2.append(detectSegmentJni.length);
        sb2.append(", H, W = ");
        sb2.append(iArr2[1]);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(iArr2[0]);
        Log.d("DVS", sb2.toString());
        if (detectSegmentJni.length == 0 || iArr2[0] == -1 || iArr2[1] == -1) {
            Log.d("DVS", "DVSDetectSegment No segmentation mask for touch point");
            return iArr3;
        }
        for (int i13 = 0; i13 < height2; i13++) {
            iArr3[i13] = detectSegmentJni[i13];
        }
        Log.d("DVS", "DVSDetectSegment time : " + (System.currentTimeMillis() - currentTimeMillis));
        return iArr3;
    }

    public void DVSGenerateGIF(Context context, Uri uri, int i2, int i7, int i8, SegmentResultFileCreared segmentResultFileCreared) {
        Log.d("DVS", "DVSGenerateGIF URL : " + uri + " touchX: " + i2 + " touchY :" + i7 + " currentFrameIndex : " + i8);
        SegmentAsyncTask segmentAsyncTask2 = new SegmentAsyncTask(context, new DVSGIFConfig(-1, 15, i2, i7, i8, uri), this.isPamir, segmentResultFileCreared);
        this.segmentAsyncTask = segmentAsyncTask2;
        segmentAsyncTask2.execute(new String[0]);
    }

    public boolean DVSGetSegment(Bitmap bitmap, int i2, int i7, boolean z) {
        return segmentJni(bitmap, bitmap.copy(bitmap.getConfig(), true), i2, i7, z);
    }

    public boolean DVSInit(String str, String str2, int i2, int i7, int i8, int i10) {
        Log.d("DVS", "segmentationModelPath : " + str + " detectionModelPath : " + str2);
        return initJni(str, str2, i2, i7, i8, i10);
    }

    public boolean DVSQuramGenerateGIF(String str, int i2) {
        return generateGIFJni(str, i2);
    }

    public void DVSSetMaxResolution(int i2) {
        setMaxGIFResolutionJni(i2);
    }

    public boolean DVSSetThresholds(int i2, int i7, int i8, int i10) {
        return setThresholdsJni(i2, i7, i8, i10);
    }

    public boolean DVSTaskStatus() {
        SegmentAsyncTask segmentAsyncTask2 = this.segmentAsyncTask;
        if (segmentAsyncTask2 != null && segmentAsyncTask2.getStatus() == AsyncTask.Status.RUNNING) {
            return true;
        }
        return false;
    }

    public void release() {
        deInitJni();
    }

    public boolean reset_frame_counter() {
        return resetFrameCounterJni(true);
    }

    public UNF_Object_Segment_Info segmentInfoJNI(Bitmap bitmap) {
        return getSegmentInfoJni(bitmap);
    }

    public boolean reset_frame_counter(boolean z) {
        return resetFrameCounterJni(z);
    }

    public boolean DVSInit(String str, String str2, String str3, int i2, int i7, int i8, int i10) {
        StringBuilder q = C0086a.q("segmentationModelPath : ", str, " detectionModelPath : ", str2, " refinerModelPath : ");
        q.append(str3);
        Log.d("DVS", q.toString());
        return initJni(str, str2, str3, i2, i7, i8, i10);
    }

    public void DVSGenerateGIF(Context context, DVSGIFConfig dVSGIFConfig, SegmentResultFileCreared segmentResultFileCreared) {
        Log.d("DVS", "DVSGenerateGIF URL : " + dVSGIFConfig.getUrl() + " touchX: " + dVSGIFConfig.getTouchX() + " touchY :" + dVSGIFConfig.getTouchY() + " currentFrameIndex : " + dVSGIFConfig.getCurrentIndex());
        SegmentAsyncTask segmentAsyncTask2 = new SegmentAsyncTask(context, dVSGIFConfig, this.isPamir, segmentResultFileCreared);
        this.segmentAsyncTask = segmentAsyncTask2;
        segmentAsyncTask2.execute(new String[0]);
    }

    public byte[] DVSDetectSegment(Bitmap bitmap, int i2, int i7, int i8, int i10, int[] iArr, int i11) {
        int i12;
        int i13;
        int i14 = i2;
        int i15 = i7;
        long currentTimeMillis = System.currentTimeMillis();
        int width = (int) ((((float) i14) / ((float) i8)) * ((float) bitmap.getWidth()));
        int height = (int) ((((float) i15) / ((float) i10)) * ((float) bitmap.getHeight()));
        if (i14 == -1 && i15 == -1) {
            i13 = -1;
            i12 = -1;
        } else {
            i13 = width;
            i12 = height;
        }
        int[] iArr2 = new int[2];
        int i16 = i11;
        byte[] detectSegmentJni = detectSegmentJni(bitmap, i13, i12, iArr2, iArr, i16);
        Log.d("DVS", "DVSDetectSegment Length of byte array = " + detectSegmentJni.length + ", H, W = " + iArr2[1] + ArcCommonLog.TAG_COMMA + iArr2[0] + " mode:" + i16);
        StringBuilder sb2 = new StringBuilder("DVSDetectSegment time : ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d("DVS", sb2.toString());
        return detectSegmentJni;
    }
}
