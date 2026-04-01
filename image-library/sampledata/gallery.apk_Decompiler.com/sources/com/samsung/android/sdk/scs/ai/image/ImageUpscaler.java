package com.samsung.android.sdk.scs.ai.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SharedMemory;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.feature.FeatureStatusCache;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageUpscaler {
    private static final int RGBA_CHANNEL = 4;
    private static final int SEP_VERSION_ONEUI_610 = 150100;
    private static final String TAG = "ScsApi@ImageUpscale";
    private final Context mContext;
    private ImageServiceExecutor mImageServiceExecutor;
    private boolean mIsSessionCreated;
    private Bundle mMetaBundle;
    private ByteBuffer mReadBuffer;
    private final Uri mUri = Uri.parse("content://com.samsung.android.scs.ai.image");
    private ByteBuffer mWriteBuffer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BindRunnable extends TaskRunnable<Void> {
        public /* synthetic */ BindRunnable(int i2) {
            this();
        }

        public void execute() {
            Log.d(ImageUpscaler.TAG, "Connection Successful");
        }

        public String getFeatureName() {
            return Feature.FEATURE_IMAGE_UPSCALE_IMAGE;
        }

        private BindRunnable() {
        }
    }

    public ImageUpscaler(Context context) {
        this.mContext = context;
        this.mImageServiceExecutor = new ImageServiceExecutor(context);
        this.mImageServiceExecutor.execute(new BindRunnable(0));
    }

    private void clearAllBuffer() {
        this.mWriteBuffer.clear();
        this.mReadBuffer.clear();
        this.mMetaBundle.clear();
    }

    private void configureBuffer(Bitmap bitmap, int i2, Rect rect) {
        this.mMetaBundle.putInt("imageWidth", bitmap.getWidth());
        this.mMetaBundle.putInt("imageHeight", bitmap.getHeight());
        this.mMetaBundle.putInt("imageSize", bitmap.getByteCount());
        this.mMetaBundle.putInt("scaleFactor", i2);
        if (rect != null) {
            this.mMetaBundle.putIntArray("imagePadding", new int[]{rect.top, rect.bottom, rect.right, rect.left});
        }
    }

    public boolean createSession(int i2, String str) {
        Log.d(TAG, " createSession, requested capacity: " + i2);
        if (this.mContext == null) {
            Log.d(TAG, "createSession: Context is Null");
            return false;
        }
        int status = FeatureStatusCache.getStatus(Feature.FEATURE_IMAGE_UPSCALE_IMAGE);
        if (status == -1000) {
            status = Feature.checkFeature(this.mContext, Feature.FEATURE_IMAGE_UPSCALE_IMAGE);
        }
        if (status != 0) {
            Log.d(TAG, "createSession: Status is not Success : " + status);
            return false;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("capacity", i2);
            bundle.putString("applicationId", this.mContext.getPackageName());
            bundle.putString("engineType", str);
            Bundle call = this.mContext.getContentResolver().call(this.mUri, "upscaleImage", "createSession", bundle);
            this.mMetaBundle = new Bundle();
            if (call != null) {
                this.mWriteBuffer = ((SharedMemory) call.getParcelable("readMemory")).mapReadWrite();
                this.mReadBuffer = ((SharedMemory) call.getParcelable("writeMemory")).mapReadWrite();
                this.mIsSessionCreated = true;
                return true;
            }
            Log.i(TAG, "createSession :: Failed to create buffer");
            return false;
        } catch (Exception e) {
            Log.e(TAG, " createSession failed", e);
            e.printStackTrace();
            return false;
        }
    }

    public int detectScene(Bitmap bitmap) {
        Log.d(TAG, "detectScene");
        if (this.mContext == null || bitmap == null) {
            Log.e(TAG, " detectScene Error :: Context is null or bitmap is null");
            return -1;
        } else if (!this.mIsSessionCreated) {
            Log.e(TAG, " detectScene Error :: Call createSession() before calling detectScene");
            return -1;
        } else {
            try {
                clearAllBuffer();
                configureBuffer(bitmap, -1, (Rect) null);
                long currentTimeMillis = System.currentTimeMillis();
                bitmap.copyPixelsToBuffer(this.mWriteBuffer);
                Bundle call = this.mContext.getContentResolver().call(this.mUri, "upscaleImage", "performUpscale", this.mMetaBundle);
                clearAllBuffer();
                int i2 = call.getInt("sceneType");
                Log.d(TAG, "detectScene, time taken, scene is " + (System.currentTimeMillis() - currentTimeMillis) + " , " + i2);
                return i2;
            } catch (Exception e) {
                Log.e(TAG, "Exception :: detectScene", e);
                return -1;
            }
        }
    }

    public void endSession() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("applicationId", this.mContext.getPackageName());
            this.mContext.getContentResolver().call(this.mUri, "upscaleImage", "endSession", bundle);
            ByteBuffer byteBuffer = this.mWriteBuffer;
            if (byteBuffer != null) {
                SharedMemory.unmap(byteBuffer);
                this.mWriteBuffer = null;
            }
            ByteBuffer byteBuffer2 = this.mReadBuffer;
            if (byteBuffer2 != null) {
                SharedMemory.unmap(byteBuffer2);
                this.mReadBuffer = null;
            }
            ImageServiceExecutor imageServiceExecutor = this.mImageServiceExecutor;
            if (imageServiceExecutor != null) {
                imageServiceExecutor.deInit();
            }
            Log.d(TAG, " endSession");
        } catch (Exception e) {
            Log.e(TAG, " Exception endSession ", e);
        }
        this.mIsSessionCreated = false;
    }

    public Bitmap upscaleImage(Bitmap bitmap, int i2, Rect rect, int i7) {
        Bitmap bitmap2 = bitmap;
        Rect rect2 = rect;
        String str = " GainMap change error : ";
        String str2 = " GainMap change: result bitmap w ";
        String str3 = " GainMap change: c_bitmap gainmap w ";
        Log.d(TAG, "upscaleImage" + i2);
        if (this.mContext == null || bitmap2 == null) {
            Log.e(TAG, " upscaleImage Error :: Context is null or bitmap is null");
            return null;
        } else if (!this.mIsSessionCreated) {
            Log.e(TAG, " upscaleImage Error :: Please call createSession() before calling upscaleImage");
            return null;
        } else {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                clearAllBuffer();
                configureBuffer(bitmap, i2, rect);
                String str4 = " GainMap change: c_bitmap w ";
                String str5 = " GainMap change: s_bitmap gainmap scaled w ";
                this.mMetaBundle.putInt("sceneType", i7);
                long currentTimeMillis2 = System.currentTimeMillis();
                bitmap2.copyPixelsToBuffer(this.mWriteBuffer);
                Log.d(TAG, " input copyPixelsToBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis2));
                long currentTimeMillis3 = System.currentTimeMillis();
                Bundle call = this.mContext.getContentResolver().call(this.mUri, "upscaleImage", "performUpscale", this.mMetaBundle);
                Log.d(TAG, " perform upscale SDK TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis3));
                clearAllBuffer();
                int i8 = call.getInt("imageWidth");
                int i10 = call.getInt("imageHeight");
                int i11 = call.getInt("imageSize");
                if (i11 == 0) {
                    Log.d(TAG, " received empty buffer");
                    return null;
                }
                Log.d(TAG, " received buffer" + i11);
                this.mReadBuffer.limit(i8 * i10 * 4);
                long currentTimeMillis4 = System.currentTimeMillis();
                Bitmap createBitmap = Bitmap.createBitmap(i8, i10, bitmap2.getConfig(), true, bitmap2.getColorSpace());
                Log.d(TAG, " create scaled bitmap TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis4));
                long currentTimeMillis5 = System.currentTimeMillis();
                createBitmap.copyPixelsFromBuffer(this.mReadBuffer);
                if (Build.VERSION.SDK_INT >= 34) {
                    if (Build.VERSION.SEM_PLATFORM_INT >= SEP_VERSION_ONEUI_610) {
                        try {
                            Log.d(TAG, " GainMap change, SDK above 34 and OneUI6.1 (version doing scale-linear -> crop -> copy gainmap only)");
                            if (!bitmap2.hasGainmap()) {
                                Log.d(TAG, " GainMap change: Base bitmap doesn't have GainMap");
                                Log.d(TAG, " output copyPixelsFromBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis5));
                                Log.d(TAG, " total time by upscaleImage at SDK " + (System.currentTimeMillis() - currentTimeMillis));
                                return createBitmap;
                            }
                            Log.d(TAG, " GainMap change, bitmap has gain map true");
                            int width = bitmap2.getWidth();
                            int height = bitmap2.getHeight();
                            int i12 = (width - rect2.right) - rect2.left;
                            int i13 = width;
                            int i14 = (height - rect2.top) - rect2.bottom;
                            Log.d(TAG, " GainMap change: input bmap w " + Integer.toString(i13) + " & h " + Integer.toString(height));
                            Log.d(TAG, " GainMap change: input bmap gainmap w " + Integer.toString(bitmap2.getGainmap().getGainmapContents().getWidth()) + " & h " + Integer.toString(bitmap2.getGainmap().getGainmapContents().getHeight()));
                            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, i13 * i2, height * i2, true);
                            Log.d(TAG, " GainMap change: s_bitmap scaled w " + Integer.toString(createScaledBitmap.getWidth()) + " & h " + Integer.toString(createScaledBitmap.getHeight()));
                            if (!createScaledBitmap.hasGainmap()) {
                                Log.d(TAG, " GainMap change: s_bitmap doesn't have GainMap");
                                Log.d(TAG, " output copyPixelsFromBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis5));
                                Log.d(TAG, " total time by upscaleImage at SDK " + (System.currentTimeMillis() - currentTimeMillis));
                                return createBitmap;
                            }
                            Log.d(TAG, str5 + Integer.toString(createScaledBitmap.getGainmap().getGainmapContents().getWidth()) + " & h " + Integer.toString(createScaledBitmap.getGainmap().getGainmapContents().getHeight()));
                            Bitmap createBitmap2 = Bitmap.createBitmap(createScaledBitmap, rect2.left * i2, rect2.top * i2, i12 * i2, i14 * i2);
                            Log.d(TAG, str4 + Integer.toString(createBitmap2.getWidth()) + " & h " + Integer.toString(createBitmap2.getHeight()));
                            if (!createBitmap2.hasGainmap()) {
                                Log.d(TAG, " GainMap change: c_bitmap doesn't have GainMap");
                                Log.d(TAG, " output copyPixelsFromBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis5));
                                Log.d(TAG, " total time by upscaleImage at SDK " + (System.currentTimeMillis() - currentTimeMillis));
                                return createBitmap;
                            }
                            Log.d(TAG, str3 + Integer.toString(createBitmap2.getGainmap().getGainmapContents().getWidth()) + " & h " + Integer.toString(createBitmap2.getGainmap().getGainmapContents().getHeight()));
                            Log.d(TAG, str2 + Integer.toString(createBitmap.getWidth()) + " & h " + Integer.toString(createBitmap.getHeight()));
                            Log.d(TAG, " GainMap change, c_bitmap has gain map true");
                            createBitmap.setGainmap(createBitmap2.getGainmap());
                            Log.d(TAG, " output copyPixelsFromBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis5));
                            Log.d(TAG, " total time by upscaleImage at SDK " + (System.currentTimeMillis() - currentTimeMillis));
                            return createBitmap;
                        } catch (Exception e) {
                            Log.d(TAG, str + e.getMessage());
                        }
                    }
                }
                Log.d(TAG, " Gainmap change SDK version < 34");
                Log.d(TAG, " output copyPixelsFromBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis5));
                Log.d(TAG, " total time by upscaleImage at SDK " + (System.currentTimeMillis() - currentTimeMillis));
                return createBitmap;
            } catch (Exception e7) {
                Log.e(TAG, " Exception :: upscale ", e7);
                return null;
            }
        }
    }

    public boolean createSession(int i2) {
        return createSession(i2, (String) null);
    }
}
