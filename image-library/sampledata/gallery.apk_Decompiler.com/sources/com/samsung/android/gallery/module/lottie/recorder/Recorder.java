package com.samsung.android.gallery.module.lottie.recorder;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Recorder implements Closeable {
    public static boolean PRINT_FRAME_NUMBER = false;
    private long fakePts;
    private int frameRate;
    private Surface inputSurface;
    private Paint mDebugTextPaintFill;
    private Paint mDebugTextPaintStroke;
    Matrix matrix;
    private MediaMuxer muxer;
    private boolean muxerStarted;
    private int trackIndex;
    private final MediaCodec.BufferInfo videoBufferInfo;
    private MediaCodec videoEncoder;
    private long videoLengthInMs;

    public Recorder(File file, int i2, float f, float f5) {
        this("video/hevc", 4000000, 1, i2, 1080, getHeight(f, f5), file);
        createPaints();
    }

    private void createMatrix(Canvas canvas, Drawable drawable) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float min = Math.min(((float) width) / ((float) drawable.getIntrinsicWidth()), ((float) height) / ((float) drawable.getIntrinsicHeight()));
        Matrix matrix2 = new Matrix();
        this.matrix = matrix2;
        matrix2.postScale(min, min);
    }

    private MediaFormat createMediaFormat(String str, int i2, int i7, int i8, int i10) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i2, i7);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", i8);
        createVideoFormat.setInteger("frame-rate", this.frameRate);
        createVideoFormat.setInteger("i-frame-interval", i10);
        return createVideoFormat;
    }

    private void createMediaMuxer(File file) {
        this.muxer = new MediaMuxer(file.toString(), 0);
        this.trackIndex = -1;
        this.muxerStarted = false;
    }

    private void drainEncoder(boolean z) {
        if (z) {
            Log.d("TAG", "sending end of stream to videoEncoder");
            this.videoEncoder.signalEndOfInputStream();
        }
        drainEncoderPostLollipop(z);
    }

    private void drainEncoderPostLollipop(boolean z) {
        while (true) {
            int dequeueOutputBuffer = this.videoEncoder.dequeueOutputBuffer(this.videoBufferInfo, 10000);
            if (dequeueOutputBuffer == -2) {
                startMuxer();
            } else if (dequeueOutputBuffer != -1) {
                if (dequeueOutputBuffer <= 0) {
                    Log.w("TAG", "unexpected result from videoEncoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                } else if (encodeVideoData(this.videoEncoder.getOutputBuffer(dequeueOutputBuffer), dequeueOutputBuffer, z)) {
                    return;
                }
            } else if (!z) {
                return;
            }
        }
    }

    private boolean encodeVideoData(ByteBuffer byteBuffer, int i2, boolean z) {
        MediaCodec.BufferInfo bufferInfo = this.videoBufferInfo;
        if ((bufferInfo.flags & 2) != 0) {
            bufferInfo.size = 0;
        }
        if (bufferInfo.size != 0) {
            if (this.muxerStarted) {
                byteBuffer.position(bufferInfo.offset);
                MediaCodec.BufferInfo bufferInfo2 = this.videoBufferInfo;
                byteBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
                MediaCodec.BufferInfo bufferInfo3 = this.videoBufferInfo;
                long j2 = this.fakePts;
                bufferInfo3.presentationTimeUs = j2;
                if (z) {
                    this.videoLengthInMs = j2;
                }
                this.fakePts = (1000000 / ((long) this.frameRate)) + j2;
                this.muxer.writeSampleData(this.trackIndex, byteBuffer, bufferInfo3);
            } else {
                throw new RuntimeException("muxer hasn't started");
            }
        }
        this.videoEncoder.releaseOutputBuffer(i2, false);
        if ((this.videoBufferInfo.flags & 4) == 0) {
            return false;
        }
        if (z) {
            return true;
        }
        Log.w("TAG", "reached end of stream unexpectedly");
        return true;
    }

    private static int getHeight(float f, float f5) {
        int i2 = (int) ((f5 / f) * 1080.0f);
        return i2 - (i2 % 4);
    }

    private void startMuxer() {
        if (!this.muxerStarted) {
            MediaFormat outputFormat = this.videoEncoder.getOutputFormat();
            Log.d("TAG", "videoEncoder inputSurface format changed: " + outputFormat);
            this.trackIndex = this.muxer.addTrack(outputFormat);
            this.muxer.start();
            this.muxerStarted = true;
            return;
        }
        throw new RuntimeException("format changed twice");
    }

    private void startVideoEncoder(String str, MediaFormat mediaFormat) {
        try {
            MediaCodec createEncoderByType = MediaCodec.createEncoderByType(str);
            this.videoEncoder = createEncoderByType;
            createEncoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
            this.inputSurface = this.videoEncoder.createInputSurface();
            this.videoEncoder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        this.videoEncoder.stop();
        this.videoEncoder.release();
        this.inputSurface.release();
        this.muxer.stop();
        this.muxer.release();
    }

    public void createPaints() {
        if (this.mDebugTextPaintFill == null) {
            Paint paint = new Paint();
            this.mDebugTextPaintFill = paint;
            paint.setTextSize(300.0f);
            this.mDebugTextPaintFill.setColor(-16777216);
            this.mDebugTextPaintFill.setStyle(Paint.Style.FILL);
            Paint paint2 = new Paint();
            this.mDebugTextPaintStroke = paint2;
            paint2.setTextSize(300.0f);
            this.mDebugTextPaintStroke.setStrokeMiter(10.0f);
            this.mDebugTextPaintStroke.setStrokeJoin(Paint.Join.ROUND);
            this.mDebugTextPaintStroke.setStrokeWidth(20.0f);
            this.mDebugTextPaintStroke.setColor(-1);
            this.mDebugTextPaintStroke.setStyle(Paint.Style.STROKE);
        }
    }

    public void end() {
        drainEncoder(true);
        close();
    }

    public void nextFrame(Drawable drawable, int i2) {
        drainEncoder(false);
        Canvas lockHardwareCanvas = this.inputSurface.lockHardwareCanvas();
        try {
            if (this.matrix == null) {
                createMatrix(lockHardwareCanvas, drawable);
            }
            lockHardwareCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            lockHardwareCanvas.setMatrix(this.matrix);
            drawable.draw(lockHardwareCanvas);
            if (PRINT_FRAME_NUMBER) {
                lockHardwareCanvas.drawText("Frame #" + i2, 500.0f, 500.0f, this.mDebugTextPaintStroke);
                lockHardwareCanvas.drawText("Frame #" + i2, 500.0f, 500.0f, this.mDebugTextPaintFill);
            }
            this.inputSurface.unlockCanvasAndPost(lockHardwareCanvas);
        } catch (Throwable th) {
            this.inputSurface.unlockCanvasAndPost(lockHardwareCanvas);
            throw th;
        }
    }

    public Recorder(String str, int i2, int i7, int i8, int i10, int i11, File file) {
        this.videoBufferInfo = new MediaCodec.BufferInfo();
        this.trackIndex = -1;
        this.muxerStarted = false;
        this.fakePts = 0;
        this.videoLengthInMs = 0;
        this.frameRate = 30;
        if (i10 < 0) {
            throw new IllegalArgumentException("You must set a positive width");
        } else if (i11 < 0) {
            throw new IllegalArgumentException("You must set a positive height");
        } else if (i8 >= 0) {
            this.frameRate = i8;
            int i12 = i2;
            startVideoEncoder(str, createMediaFormat(str, i10, i11, i12, i7));
            try {
                createMediaMuxer(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("You must set a positive number of frames per second");
        }
    }
}
