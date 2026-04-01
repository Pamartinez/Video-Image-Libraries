package com.sec.longexposure;

import android.content.Context;
import android.graphics.YuvImage;
import android.util.Log;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.sec.sshutter.ISlowShutterCallback;
import com.sec.sshutter.SlowShutter;
import com.sec.sshutter.SlowShutterParameters;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureImpl {
    /* access modifiers changed from: private */
    public boolean canceled = false;
    boolean isPhotoInput = false;
    boolean isPhotoOutput = false;
    LongExposureComposer longExposureComposer;
    LongExposureInfo longExposureInfo;
    ILongExposureCallback mCallback;
    Context mContext = null;
    SlowShutter slowShutter;
    SlowShutterParameters slowShutterParameters;
    String ssInputPath = null;
    String ssOutputPath = null;

    public LongExposureImpl() {
        Log.i("LongExposureImpl", "LongExposure 1.2.10");
    }

    /* access modifiers changed from: private */
    public void clearTempFiles() {
        if (this.isPhotoInput) {
            Log.i("LongExposureImpl", "Delete temp input video file");
            File file = new File(this.ssInputPath);
            if (file.exists() && !file.delete()) {
                Log.d("LongExposureImpl", "Failed to delete temp input video file");
            }
        }
        if (this.isPhotoOutput) {
            Log.i("LongExposureImpl", "Delete temp output video file");
            File file2 = new File(this.ssOutputPath);
            if (file2.exists() && !file2.delete()) {
                Log.d("LongExposureImpl", "Failed to delete temp output video file");
            }
        }
    }

    private boolean isPhoto(String str) {
        if (str.toLowerCase().endsWith(".jpg") || str.toLowerCase().endsWith(".jpeg") || str.toLowerCase().endsWith(".heic") || str.toLowerCase().endsWith(".heif")) {
            return true;
        }
        return false;
    }

    private boolean isVideo(String str) {
        if (str.toLowerCase().endsWith(O3DPConstant.MP4_EXTENSION)) {
            return true;
        }
        return false;
    }

    private int prepare(Context context, String str) {
        int retrieveMetadata;
        if (this.isPhotoInput) {
            int extractMotionPhotoInfo = this.longExposureInfo.extractMotionPhotoInfo(str, this.ssInputPath);
            if (extractMotionPhotoInfo != 0) {
                return extractMotionPhotoInfo;
            }
            LongExposureC2paUtil.hasC2paManifest(context.getApplicationContext(), str);
        } else if (isVideo(str)) {
            long retrieveDuration = LongExposureInfo.retrieveDuration(str);
            if (retrieveDuration <= 0) {
                Log.e("LongExposureImpl", "Cannot process with video : duration: " + retrieveDuration);
                return -1;
            }
            SlowShutterParameters slowShutterParameters2 = this.slowShutterParameters;
            slowShutterParameters2.isScanned = false;
            slowShutterParameters2.userMode = 0;
            slowShutterParameters2.startTimeUs = 0;
            Number[] recommendedInfo = new LongExposureDBHandler(context).getRecommendedInfo(str);
            if (recommendedInfo != null && recommendedInfo.length == 2 && recommendedInfo[0].longValue() < retrieveDuration * 1000) {
                SlowShutterParameters slowShutterParameters3 = this.slowShutterParameters;
                slowShutterParameters3.isScanned = true;
                slowShutterParameters3.startTimeUs = recommendedInfo[0].longValue();
                this.slowShutterParameters.userMode = recommendedInfo[1].intValue();
                Log.i("LongExposureImpl", "Recommended, startTimeUs: " + this.slowShutterParameters.startTimeUs + ", userMode: " + this.slowShutterParameters.userMode);
            }
        } else {
            Log.e("LongExposureImpl", "Input path is not valid - " + str);
            return -1;
        }
        if (isVideo(this.ssInputPath) && (retrieveMetadata = this.longExposureInfo.retrieveMetadata(this.ssInputPath)) != 0) {
            return retrieveMetadata;
        }
        if (this.isPhotoOutput) {
            LongExposureComposer longExposureComposer2 = new LongExposureComposer();
            this.longExposureComposer = longExposureComposer2;
            longExposureComposer2.setContext(this.mContext);
            this.longExposureComposer.setLongExposureInfo(this.longExposureInfo);
        }
        return 0;
    }

    private void setTempFilePath(String str, String str2) {
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        if (isPhoto(str)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mContext.getExternalCacheDir());
            sb2.append(str.substring(str.lastIndexOf("/"), str.lastIndexOf(".")));
            sb2.append("_video_" + format + O3DPConstant.MP4_EXTENSION);
            this.ssInputPath = sb2.toString();
            this.isPhotoInput = true;
        } else {
            this.ssInputPath = str;
            this.isPhotoInput = false;
        }
        if (isPhoto(str2)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.mContext.getExternalCacheDir());
            stringBuffer.append(str2.substring(str2.lastIndexOf("/"), str2.lastIndexOf(".")));
            stringBuffer.append("_video_" + format + O3DPConstant.MP4_EXTENSION);
            this.ssOutputPath = stringBuffer.toString();
            this.isPhotoOutput = true;
            return;
        }
        this.ssOutputPath = str2;
        this.isPhotoOutput = false;
    }

    public int cancel() {
        this.canceled = true;
        return this.slowShutter.cancel();
    }

    public int start(Context context, final String str, final String str2, ILongExposureCallback iLongExposureCallback) {
        this.mContext = context;
        this.mCallback = iLongExposureCallback;
        this.slowShutter = new SlowShutter();
        this.slowShutterParameters = new SlowShutterParameters();
        this.longExposureInfo = new LongExposureInfo();
        setTempFilePath(str, str2);
        int prepare = prepare(context, str);
        if (prepare != 0) {
            return prepare;
        }
        AnonymousClass1 r1 = new ISlowShutterCallback() {
            public void onError(int i2) {
                LongExposureImpl.this.mCallback.onError(i2);
            }

            public void onFinish(final int i2) {
                Log.i("LongExposureImpl", "SlowShutter is finished with mode: " + i2);
                if (LongExposureImpl.this.canceled) {
                    Log.w("LongExposureImpl", "SlowShutter is canceled");
                    LongExposureImpl.this.clearTempFiles();
                    LongExposureImpl.this.mCallback.onFinish();
                    return;
                }
                new Thread(new Runnable() {
                    public void run() {
                        Thread.currentThread().setName("LongExposure:Finish");
                        LongExposureImpl.this.slowShutter.release();
                        LongExposureImpl longExposureImpl = LongExposureImpl.this;
                        longExposureImpl.longExposureInfo.writeMetadata(longExposureImpl.ssOutputPath);
                        AnonymousClass1 r0 = AnonymousClass1.this;
                        LongExposureImpl longExposureImpl2 = LongExposureImpl.this;
                        if (!longExposureImpl2.isPhotoOutput) {
                            LongExposureSef.appendSEF(str2, i2);
                        } else if (!longExposureImpl2.longExposureComposer.compose(str2, longExposureImpl2.ssOutputPath, i2)) {
                            LongExposureImpl.this.mCallback.onError(-20);
                            LongExposureImpl.this.clearTempFiles();
                            return;
                        } else {
                            Log.i("LongExposureImpl", "Motion photo compose complete!!");
                            Context applicationContext = LongExposureImpl.this.mContext.getApplicationContext();
                            AnonymousClass1 r1 = AnonymousClass1.this;
                            LongExposureC2paUtil.copyC2paManifest(applicationContext, str2, str);
                        }
                        LongExposureImpl.this.clearTempFiles();
                        LongExposureImpl.this.mCallback.onFinish();
                    }
                }).start();
            }

            public void onLastImage(YuvImage yuvImage, int i2) {
                if (LongExposureImpl.this.isPhotoOutput) {
                    Log.i("LongExposureImpl", "received last image callback");
                    LongExposureImpl.this.longExposureComposer.setYuvImage(yuvImage, i2);
                }
            }

            public void onProgress(int i2) {
                LongExposureImpl.this.mCallback.onProgress(i2);
            }
        };
        this.canceled = false;
        int start = this.slowShutter.start(this.ssInputPath, this.ssOutputPath, this.slowShutterParameters, (ISlowShutterCallback) r1);
        if (start != 0) {
            Log.e("LongExposureImpl", "SlowShutter start error!!");
        }
        return start;
    }
}
