package com.samsung.o3dp.lib3dphotography.video;

import android.content.Context;
import android.net.Uri;
import android.os.PowerManager;
import com.samsung.o3dp.lib3dphotography.O3DPContext;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import com.samsung.o3dp.lib3dphotography.O3DPVideoListener;
import com.samsung.o3dp.lib3dphotography.O3DPhotoConfig;
import com.samsung.o3dp.lib3dphotography.mesh.MeshSefManager;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoEncoderTask implements Runnable {
    private static final String TAG = "VideoEncoderTask";
    private final Context mContext;
    private final int mHeight;
    private final O3DPVideoListener mListener;
    private final O3DPContext mO3DPContext;
    private Uri mResultUri;
    private final VideoEncoder mVideoEncoder;
    private final File mVideoFile;
    private final int mWidth;

    public VideoEncoderTask(Context context, O3DPContext o3DPContext, O3DPhotoConfig o3DPhotoConfig, O3DPVideoListener o3DPVideoListener, byte[] bArr) {
        int i2;
        this.mContext = context;
        this.mO3DPContext = o3DPContext;
        this.mListener = o3DPVideoListener;
        int cropWidth = o3DPContext.getCropWidth();
        if (o3DPhotoConfig.getStereo()) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        int i7 = cropWidth * i2;
        this.mWidth = i7;
        int cropHeight = o3DPContext.getCropHeight();
        this.mHeight = cropHeight;
        this.mVideoFile = new File(o3DPhotoConfig.getFilePath());
        this.mVideoEncoder = new VideoEncoder(new a(this, bArr), i7, cropHeight);
    }

    private void attachMesh(File file, byte[] bArr) {
        LogUtil.i(TAG, "attachMesh()");
        MeshSefManager.mux(file.getAbsolutePath(), bArr, this.mO3DPContext.getAnimationManager().getAnimator().getAnimation().getAnimationScript());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(byte[] bArr, File file) {
        if (bArr != null) {
            attachMesh(file, bArr);
        }
        Uri fromFile = Uri.fromFile(file);
        this.mResultUri = fromFile;
        LogUtil.d(TAG, StringUtil.format("Video encoding completed : path %s, uri %s", file.getPath(), fromFile));
    }

    private boolean produceFrames() {
        float f;
        float f5 = 0.0f;
        boolean z = false;
        while (true) {
            if (this.mO3DPContext.isRecordAbortRequested()) {
                LogUtil.i(TAG, "Requested to abort VideoEncoding");
                break;
            }
            this.mO3DPContext.consumeNextFrame();
            float currentTime = this.mO3DPContext.getAnimationManager().getAnimator().getAnimation().getAnimationTime().getCurrentTime();
            if (z || currentTime - f5 >= 0.0f) {
                if (z && f5 - currentTime < 0.0f) {
                    LogUtil.i(TAG, StringUtil.format("Met animation end : curr %f, prev %f", Float.valueOf(currentTime), Float.valueOf(f5)));
                    break;
                }
            } else {
                LogUtil.i(TAG, StringUtil.format("Met animation half : curr %f, prev %f", Float.valueOf(currentTime), Float.valueOf(f5)));
                z = true;
            }
            this.mO3DPContext.getRenderer().makeCurrent();
            if (!this.mO3DPContext.visualize()) {
                this.mListener.onFailed(O3DPListener.ErrorType.RENDERING_FAIL, "Failed to render");
                return false;
            }
            this.mVideoEncoder.queueFrame(this.mO3DPContext.getVideoFrame());
            if (z) {
                f = 1.0f - (currentTime / 2.0f);
            } else {
                f = currentTime / 2.0f;
            }
            this.mListener.onProgress(f);
            f5 = currentTime;
        }
        return true;
    }

    public void run() {
        try {
            Thread encodingConsumer = this.mVideoEncoder.getEncodingConsumer(this.mVideoFile);
            PowerManager.WakeLock newWakeLock = ((PowerManager) this.mContext.getSystemService("power")).newWakeLock(1, "VideoEncoderTask::wakeLock");
            newWakeLock.acquire(TimeUnit.MINUTES.toMillis(10));
            encodingConsumer.start();
            this.mO3DPContext.getRenderer().makeCurrent();
            this.mO3DPContext.setVideoResolution(this.mWidth, this.mHeight);
            boolean produceFrames = produceFrames();
            if (produceFrames) {
                this.mVideoEncoder.stopEncoding(this.mO3DPContext.isRecordAbortRequested());
            }
            try {
                encodingConsumer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LogUtil.e(TAG, "Interrupted while encodingConsumer.join() : " + e);
            }
            LogUtil.i(TAG, "mWakeLock.release()");
            newWakeLock.release();
            this.mO3DPContext.resetVideoTexture();
            this.mO3DPContext.finishRecording();
            if (this.mO3DPContext.isRecordAbortRequested()) {
                this.mListener.onFailed(O3DPListener.ErrorType.VIDEO_RECORDING_ABORTED, "Aborted by user");
            } else if (!produceFrames) {
                this.mListener.onFailed(O3DPListener.ErrorType.VIDEO_RECORDING_FAIL, "Recording Failed");
            } else {
                this.mListener.onVideoEncoded(this.mResultUri);
            }
        } catch (IOException e7) {
            O3DPVideoListener o3DPVideoListener = this.mListener;
            O3DPListener.ErrorType errorType = O3DPListener.ErrorType.VIDEO_RECORDING_FAIL;
            o3DPVideoListener.onFailed(errorType, "Video Encoder Error " + e7);
            this.mO3DPContext.finishRecording();
        }
    }
}
