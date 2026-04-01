package com.samsung.android.motionphoto.utils.v2.video;

import A.a;
import Ad.C0721b;
import Ae.b;
import Ae.c;
import Ke.v0;
import L1.d;
import Tf.n;
import Tf.w;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageWriter;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SemSystemProperties;
import android.view.Surface;
import bd.h;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.motionphoto.utils.v2.video.VideoDecoderCallback;
import com.samsung.android.motionphoto.utils.v2.video.VideoEncoderCallback;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferAlloc;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.buffer.SharedBufferManager;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.solution.filter.ImgpFilterAdapter;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import me.x;
import ne.C1194l;
import ne.y;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000e\u0018\u0000 e2\u00020\u0001:\u0003fgeB\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0015\u001a\u00020\u00142\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u00112\n\u0010\u0013\u001a\u00060\u0010j\u0002`\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010\"\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017H\u0003¢\u0006\u0004\b\"\u0010#J\u001f\u0010&\u001a\u00020\t2\u0006\u0010%\u001a\u00020$2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b&\u0010'J'\u0010)\u001a\u00020(2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017H\u0002¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\t2\u0006\u0010%\u001a\u00020$H\u0002¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\t2\u0006\u0010-\u001a\u00020(H\u0002¢\u0006\u0004\b.\u0010/J'\u00100\u001a\u00020(2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017H\u0002¢\u0006\u0004\b0\u0010*J5\u00102\u001a\u0010\u0012\u0004\u0012\u00020(\u0012\u0006\u0012\u0004\u0018\u00010(012\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017H\u0003¢\u0006\u0004\b2\u00103J\u0017\u00104\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b4\u0010\u000bJK\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u000208010:2\u0006\u0010\b\u001a\u00020\u00072\u0006\u00105\u001a\u00020(2\u0018\u00109\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002080106H\u0002¢\u0006\u0004\b;\u0010<J/\u0010;\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u00105\u001a\u00020(2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017H\u0002¢\u0006\u0004\b;\u0010=J/\u0010>\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010-\u001a\u00020(2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017H\u0002¢\u0006\u0004\b>\u0010=J\u0017\u0010A\u001a\u00020\t2\u0006\u0010@\u001a\u00020?H\u0002¢\u0006\u0004\bA\u0010BR\u001c\u0010E\u001a\n D*\u0004\u0018\u00010C0C8\u0002X\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u001c8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010J\u001a\u00020I8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010L\u001a\u00020?8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bL\u0010MR\u0018\u0010O\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bO\u0010PR\u0018\u0010Q\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bQ\u0010PR\u0018\u0010R\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bR\u0010PR\u0016\u0010T\u001a\u00020S8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020S8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bV\u0010UR\u0016\u0010W\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bW\u0010XR(\u0010[\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020\t0Y8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b[\u0010\\R\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\r\u0010]R\u0014\u0010`\u001a\u00020N8BX\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0014\u0010b\u001a\u00020N8BX\u0004¢\u0006\u0006\u001a\u0004\ba\u0010_R\u0014\u0010d\u001a\u00020N8BX\u0004¢\u0006\u0006\u001a\u0004\bc\u0010_¨\u0006h"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder;", "", "<init>", "()V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder$Builder;", "builder", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder$Builder;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;", "task", "Lme/x;", "process", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "eventListener", "setOnEventListener", "(Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;)V", "Lcom/samsung/android/sum/core/format/MediaFormat;", "Lcom/samsung/android/motionphoto/utils/v2/video/SumMediaFormat;", "inFormat", "outFormat", "Landroid/graphics/Rect;", "computeCropRect", "(Lcom/samsung/android/sum/core/format/MediaFormat;Lcom/samsung/android/sum/core/format/MediaFormat;)Landroid/graphics/Rect;", "", "src", "dst", "getCorrectionRotation", "(II)I", "Lcom/samsung/android/sum/core/functional/Operator;", "makeConverter", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;)Lcom/samsung/android/sum/core/functional/Operator;", "Landroid/media/MediaExtractor;", "extractor", "trackIndex", "processAudio", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;Landroid/media/MediaExtractor;I)V", "Landroid/media/MediaFormat;", "format", "configVideoEncoderParameters", "(Landroid/media/MediaFormat;Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;)V", "Landroid/media/MediaCodec;", "prepareEncoder", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;Landroid/media/MediaExtractor;I)Landroid/media/MediaCodec;", "configVideoDecoderParameters", "(Landroid/media/MediaFormat;)V", "decoder", "setBoostMode", "(Landroid/media/MediaCodec;)V", "prepareDecoder", "Lme/i;", "processVideo", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;Landroid/media/MediaExtractor;I)Lme/i;", "makeSilentAudioTrack", "encoder", "", "Ljava/nio/ByteBuffer;", "Landroid/media/MediaCodec$BufferInfo;", "samples", "", "executeEncoder", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;Landroid/media/MediaCodec;Ljava/util/List;)Ljava/util/List;", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;Landroid/media/MediaCodec;Landroid/media/MediaExtractor;I)V", "executeDecoder", "Landroid/media/ImageReader;", "reader", "onDecodedImageAvailable", "(Landroid/media/ImageReader;)V", "Lcom/samsung/android/sum/core/functional/CountingLatch;", "kotlin.jvm.PlatformType", "countingLatch", "Lcom/samsung/android/sum/core/functional/CountingLatch;", "converter", "Lcom/samsung/android/sum/core/functional/Operator;", "Landroid/media/ImageWriter;", "imageWriter", "Landroid/media/ImageWriter;", "imageReader", "Landroid/media/ImageReader;", "Landroid/os/HandlerThread;", "_imageReceiveThread", "Landroid/os/HandlerThread;", "_decodeCallbackThread", "_encodeCallbackThread", "", "isUseDecoderCallback", "Z", "isUseEncoderCallback", "numProcessFrames", "I", "Lkotlin/Function2;", "", "sendEOSHint", "LAe/c;", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "getImageReceiveThread", "()Landroid/os/HandlerThread;", "imageReceiveThread", "getDecodeCallbackThread", "decodeCallbackThread", "getEncodeCallbackThread", "encodeCallbackThread", "Companion", "Builder", "ImgpConverter", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoTranscoder {
    private static final long CODEC_POOLING_DURATION_US = 100;
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final f MAX_QUEUED_INPUT$delegate = d.q(new C0721b(9));
    private static final int MAX_TRANSCODING_IMAGES = 16;
    private static final String SEC_AAC_CODEC_ENCODER_NAME = "c2.sec.aac.encoder";
    private static final int SEC_AAC_CODEC_UNIT_NUMBER_OF_SAMPLES = 1024;
    /* access modifiers changed from: private */
    public static final String TAG;
    private static final int TRANSCODE_CODEC_PRIORITY = 1;
    private HandlerThread _decodeCallbackThread;
    private HandlerThread _encodeCallbackThread;
    private HandlerThread _imageReceiveThread;
    private Operator converter;
    private final CountingLatch countingLatch;
    private EventListener eventListener;
    private ImageReader imageReader;
    private ImageWriter imageWriter;
    private boolean isUseDecoderCallback;
    private boolean isUseEncoderCallback;
    private int numProcessFrames;
    private c sendEOSHint;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder$Builder;", "", "<init>", "()V", "isUseDecoderCallback", "", "isUseDecoderCallback$motionphoto_utils_v2_0_17_release", "()Z", "setUseDecoderCallback$motionphoto_utils_v2_0_17_release", "(Z)V", "isUseEncoderCallback", "isUseEncoderCallback$motionphoto_utils_v2_0_17_release", "setUseEncoderCallback$motionphoto_utils_v2_0_17_release", "useDecoderCallback", "useEncoderCallback", "build", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean isUseDecoderCallback = true;
        private boolean isUseEncoderCallback = true;

        public final VideoTranscoder build() {
            return new VideoTranscoder(this);
        }

        public final boolean isUseDecoderCallback$motionphoto_utils_v2_0_17_release() {
            return this.isUseDecoderCallback;
        }

        public final boolean isUseEncoderCallback$motionphoto_utils_v2_0_17_release() {
            return this.isUseEncoderCallback;
        }

        public final void setUseDecoderCallback$motionphoto_utils_v2_0_17_release(boolean z) {
            this.isUseDecoderCallback = z;
        }

        public final void setUseEncoderCallback$motionphoto_utils_v2_0_17_release(boolean z) {
            this.isUseEncoderCallback = z;
        }

        public final Builder useDecoderCallback(boolean z) {
            this.isUseDecoderCallback = z;
            return this;
        }

        public final Builder useEncoderCallback(boolean z) {
            this.isUseEncoderCallback = z;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00108\u0002XT¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\n8\u0002XT¢\u0006\u0006\n\u0004\b\u0013\u0010\fR\u0014\u0010\u0014\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0014\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder$Companion;", "", "<init>", "()V", "", "MAX_QUEUED_INPUT$delegate", "Lme/f;", "getMAX_QUEUED_INPUT", "()I", "MAX_QUEUED_INPUT", "", "TAG", "Ljava/lang/String;", "MAX_TRANSCODING_IMAGES", "I", "TRANSCODE_CODEC_PRIORITY", "", "CODEC_POOLING_DURATION_US", "J", "SEC_AAC_CODEC_ENCODER_NAME", "SEC_AAC_CODEC_UNIT_NUMBER_OF_SAMPLES", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final int getMAX_QUEUED_INPUT() {
            return ((Number) VideoTranscoder.MAX_QUEUED_INPUT$delegate.getValue()).intValue();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder$ImgpConverter;", "Lcom/samsung/android/sum/core/functional/Operator;", "op", "<init>", "(Lcom/samsung/android/sum/core/functional/Operator;)V", "imgp", "Lcom/samsung/android/sum/solution/filter/ImgpFilterAdapter;", "imgpType", "Lcom/samsung/android/sum/core/types/ImgpType;", "kotlin.jvm.PlatformType", "getImgpType", "()Lcom/samsung/android/sum/core/types/ImgpType;", "run", "Lcom/samsung/android/sum/core/buffer/MutableMediaBuffer;", "ibuf", "Lcom/samsung/android/sum/core/buffer/MediaBuffer;", "obuf", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ImgpConverter implements Operator {
        private final ImgpFilterAdapter imgp;

        public ImgpConverter(Operator operator) {
            j.e(operator, "op");
            this.imgp = (ImgpFilterAdapter) operator;
            String access$getTAG$cp = VideoTranscoder.TAG;
            String name = getImgpType().name();
            SLog.i(access$getTAG$cp, "make image converter: " + name);
        }

        public final ImgpType getImgpType() {
            return this.imgp.getImgpType();
        }

        public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
            j.e(mediaBuffer, "ibuf");
            j.e(mutableMediaBuffer, "obuf");
            MutableMediaBuffer run = this.imgp.run(mediaBuffer, mutableMediaBuffer);
            j.d(run, "run(...)");
            return run;
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.samsung.android.sum.core.types.ImgpType[] r0 = com.samsung.android.sum.core.types.ImgpType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.sum.core.types.ImgpType r1 = com.samsung.android.sum.core.types.ImgpType.ROTATE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.sum.core.types.ImgpType r1 = com.samsung.android.sum.core.types.ImgpType.RESIZE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.sum.core.types.ImgpType r1 = com.samsung.android.sum.core.types.ImgpType.CROP     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder.WhenMappings.<clinit>():void");
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoTranscoder.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public VideoTranscoder() {
        this.countingLatch = CountingLatch.downOf(0);
        this.isUseDecoderCallback = true;
        this.isUseEncoderCallback = true;
    }

    /* access modifiers changed from: private */
    public static final int MAX_QUEUED_INPUT_delegate$lambda$38() {
        return SemSystemProperties.getInt("secmm.motionphoto.max-sync-io-queue", 4);
    }

    private final Rect computeCropRect(MediaFormat mediaFormat, MediaFormat mediaFormat2) {
        int cols = (mediaFormat.getCols() - mediaFormat2.getCols()) / 2;
        int i2 = 0;
        if (cols < 0) {
            cols = 0;
        }
        int rows = (mediaFormat.getRows() - mediaFormat2.getRows()) / 2;
        if (rows >= 0) {
            i2 = rows;
        }
        int cols2 = mediaFormat2.getCols();
        int cols3 = mediaFormat.getCols();
        if (cols2 > cols3) {
            cols2 = cols3;
        }
        int rows2 = mediaFormat2.getRows();
        int rows3 = mediaFormat.getRows();
        if (rows2 > rows3) {
            rows2 = rows3;
        }
        String str = TAG;
        int cols4 = mediaFormat.getCols();
        int rows4 = mediaFormat.getRows();
        int cols5 = mediaFormat2.getCols();
        int rows5 = mediaFormat2.getRows();
        StringBuilder h5 = a.h(cols4, rows4, "computeCropRect:\n            | src w/h[", "/", "],\n            | dst w/h[");
        N2.j.x(h5, cols5, "/", rows5, "],\n            | crop x/y[");
        N2.j.x(h5, cols, "/", i2, "), w/h[");
        h5.append(cols2);
        h5.append("/");
        h5.append(rows2);
        h5.append("]]\n        ");
        SLog.i(str, CommonsKt.trimToOneLine(h5.toString()));
        return new Rect(cols, i2, cols2 + cols, rows2 + i2);
    }

    private final void configVideoDecoderParameters(android.media.MediaFormat mediaFormat) {
        mediaFormat.setInteger("priority", 1);
        mediaFormat.setInteger("vendor.qti-ext-dec-forceNonUBWC.value", 1);
        mediaFormat.setLong("vendor.sec-dec-output.buffers.usage.value", 1);
    }

    private final void configVideoEncoderParameters(android.media.MediaFormat mediaFormat, VideoTranscodingTask videoTranscodingTask) {
        mediaFormat.setInteger("width", videoTranscodingTask.getTranscodingVideoWidth());
        mediaFormat.setInteger("height", videoTranscodingTask.getTranscodingVideoHeight());
        mediaFormat.setInteger(Message.KEY_ROTATION, videoTranscodingTask.getTranscodingRotation());
        mediaFormat.setInteger("color-format", 2135033992);
        mediaFormat.setFloat("i-frame-interval", 0.5f);
        mediaFormat.setInteger("bitrate", 10000000);
        mediaFormat.setInteger("priority", 1);
        mediaFormat.setInteger("vendor.qti-ext-enc-content-adaptive-mode.value", 1);
        mediaFormat.setInteger("vendor.qti-ext-enc-linear-color-format.value", 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x025b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void executeDecoder(com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask r23, android.media.MediaCodec r24, android.media.MediaExtractor r25, int r26) {
        /*
            r22 = this;
            r0 = r24
            r7 = r25
            java.lang.String r1 = TAG
            java.lang.String r2 = "executeDecoder E"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r1, (java.lang.String) r2)
            android.media.MediaFormat r1 = r23.getVideoCodecFormat()
            java.lang.String r2 = "max-input-size"
            int r8 = r1.getInteger(r2)
            int r1 = r7.getTrackCount()
            r9 = 0
            Ge.e r1 = B1.a.Z(r9, r1)
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            r10 = 0
            if (r2 != 0) goto L_0x002b
            r3 = r10
            goto L_0x0074
        L_0x002b:
            r2 = r1
            ne.y r2 = (ne.y) r2
            int r3 = r2.nextInt()
            android.media.MediaFormat r3 = r7.getTrackFormat(r3)
            java.lang.String r4 = "mime"
            java.lang.String r3 = r3.getString(r4)
            kotlin.jvm.internal.j.b(r3)
            com.samsung.android.motionphoto.utils.v2.MPFile r5 = r23.getMpFile()
            long r5 = r5.getDistinctLastTimestampUs(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
        L_0x004b:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0074
            int r5 = r2.nextInt()
            android.media.MediaFormat r5 = r7.getTrackFormat(r5)
            java.lang.String r5 = r5.getString(r4)
            kotlin.jvm.internal.j.b(r5)
            com.samsung.android.motionphoto.utils.v2.MPFile r6 = r23.getMpFile()
            long r5 = r6.getDistinctLastTimestampUs(r5)
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            int r6 = r3.compareTo(r5)
            if (r6 <= 0) goto L_0x004b
            r3 = r5
            goto L_0x004b
        L_0x0074:
            r1 = 0
            if (r3 == 0) goto L_0x007e
            long r3 = r3.longValue()
            r11 = r3
            goto L_0x007f
        L_0x007e:
            r11 = r1
        L_0x007f:
            r13 = r1
            r1 = r9
            r2 = r1
            r15 = r2
            r16 = r15
        L_0x0085:
            if (r15 == 0) goto L_0x0089
            if (r16 != 0) goto L_0x008c
        L_0x0089:
            r3 = r22
            goto L_0x00c9
        L_0x008c:
            r3 = r22
            Ae.c r0 = r3.sendEOSHint
            if (r0 == 0) goto L_0x00c2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            java.lang.Long r3 = java.lang.Long.valueOf(r13)
            r0.invoke(r1, r3)
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "executeDecoder X: \n            | numFramesDecoded="
            r1.<init>(r3)
            r1.append(r2)
            java.lang.String r2 = ",\n            | lastDecodedTimestampUs="
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = " us\n        "
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r1)
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r1)
            return
        L_0x00c2:
            java.lang.String r0 = "sendEOSHint"
            kotlin.jvm.internal.j.k(r0)
            throw r10
        L_0x00c9:
            r4 = 100
            r17 = 1
            if (r15 != 0) goto L_0x01a0
            com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder$Companion r18 = Companion
            int r10 = r18.getMAX_QUEUED_INPUT()
            if (r1 > r10) goto L_0x01a0
            r10 = r1
            int r1 = r0.dequeueInputBuffer(r4)
            if (r1 >= 0) goto L_0x00e8
            java.lang.String r1 = TAG
            java.lang.String r4 = "none decoder input buffer available"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r1, (java.lang.String) r4)
            r1 = r10
        L_0x00e6:
            r10 = 0
            goto L_0x0085
        L_0x00e8:
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocateDirect(r8)
            r7.readSampleData(r4, r9)
            r4.rewind()
            android.media.MediaCodec$BufferInfo r5 = new android.media.MediaCodec$BufferInfo
            r5.<init>()
            r23 = 4
            int r6 = r4.limit()
            r5.size = r6
            r5.offset = r9
            r6 = r10
            long r9 = r7.getSampleTime()
            r5.presentationTimeUs = r9
            int r9 = r7.getSampleFlags()
            r9 = r9 & 1
            if (r9 == 0) goto L_0x0116
            int r9 = r5.flags
            r9 = r9 | 1
            r5.flags = r9
        L_0x0116:
            boolean r9 = r7.advance()
            if (r9 == 0) goto L_0x0126
            long r9 = r5.presentationTimeUs
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 < 0) goto L_0x0123
            goto L_0x0126
        L_0x0123:
            r9 = r23
            goto L_0x012d
        L_0x0126:
            int r9 = r5.flags
            r9 = r9 | 4
            r5.flags = r9
            goto L_0x0123
        L_0x012d:
            boolean r10 = com.samsung.android.motionphoto.utils.v2.CommonsKt.containFlag(r5, r9)
            if (r10 == 0) goto L_0x0155
            java.lang.String r4 = TAG
            java.lang.String r10 = "decoder input is reached EOS"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r4, (java.lang.String) r10)
            r10 = r5
            r4 = 0
            r15 = r6
            r6 = 4
            r21 = r2
            r2 = 0
            r3 = 0
            r7 = r15
            r15 = r9
            r9 = r10
            r10 = r7
            r19 = r8
            r7 = 100
            r0.queueInputBuffer(r1, r2, r3, r4, r6)
            r25.unselectTrack(r26)
            r2 = r10
            r15 = r17
            goto L_0x017b
        L_0x0155:
            r21 = r2
            r10 = r6
            r19 = r8
            r2 = r9
            r7 = 100
            r9 = r5
            java.nio.ByteBuffer r3 = r0.getInputBuffer(r1)
            kotlin.jvm.internal.j.b(r3)
            r3.put(r4)
            r3.flip()
            int r3 = r3.limit()
            long r4 = r9.presentationTimeUs
            int r6 = r9.flags
            r20 = r2
            r2 = 0
            r0.queueInputBuffer(r1, r2, r3, r4, r6)
            int r2 = r10 + 1
        L_0x017b:
            java.lang.String r3 = TAG
            java.lang.String r4 = com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r9)
            java.lang.String r5 = ",\n                    | numFramesToDecode="
            java.lang.String r6 = ",\n                    | info="
            java.lang.String r9 = "onInputBufferAvailable: index="
            java.lang.StringBuilder r1 = A.a.h(r1, r2, r9, r5, r6)
            r1.append(r4)
            java.lang.String r4 = "\n                "
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r1)
            com.samsung.android.sum.core.SLog.d((java.lang.String) r3, (java.lang.String) r1)
            r1 = r2
            goto L_0x01c2
        L_0x01a0:
            r10 = r1
            r21 = r2
            r19 = r8
            r7 = r4
            com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder$Companion r1 = Companion
            int r1 = r1.getMAX_QUEUED_INPUT()
            if (r10 <= r1) goto L_0x01c1
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "SKIP to queueInputBuffer: "
            r2.<init>(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r1, (java.lang.String) r2)
        L_0x01c1:
            r1 = r10
        L_0x01c2:
            android.media.MediaCodec$BufferInfo r2 = new android.media.MediaCodec$BufferInfo
            r2.<init>()
            int r3 = r0.dequeueOutputBuffer(r2, r7)
            if (r3 < 0) goto L_0x025b
            long r4 = r2.presentationTimeUs
            r6 = 1000(0x3e8, float:1.401E-42)
            long r6 = (long) r6
            long r4 = r4 * r6
            r0.releaseOutputBuffer(r3, r4)
            r9 = 4
            boolean r4 = com.samsung.android.motionphoto.utils.v2.CommonsKt.containFlag(r2, r9)
            java.lang.String r5 = "\n                            "
            if (r4 == 0) goto L_0x0210
            java.lang.String r3 = TAG
            java.lang.String r2 = com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "decoder output reached EOS: info="
            r4.<init>(r6)
            r4.append(r2)
            java.lang.String r2 = ",\n                                | lastTimestampUs="
            r4.append(r2)
            r4.append(r11)
            r4.append(r5)
            java.lang.String r2 = r4.toString()
            java.lang.String r2 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r2)
            com.samsung.android.sum.core.SLog.i((java.lang.String) r3, (java.lang.String) r2)
            r7 = r25
            r16 = r17
        L_0x0209:
            r8 = r19
            r2 = r21
        L_0x020d:
            r9 = 0
            goto L_0x00e6
        L_0x0210:
            int r1 = r1 + -1
            int r4 = r21 + 1
            long r13 = r2.presentationTimeUs
            java.lang.String r6 = TAG
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "onOutputBufferAvailable: numFramesDecoded="
            r7.<init>(r8)
            r7.append(r4)
            java.lang.String r8 = ", "
            r7.append(r8)
            r7.append(r13)
            java.lang.String r8 = " us"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r6, (java.lang.String) r7)
            java.lang.String r2 = com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r2)
            java.lang.String r7 = ",\n                                | numFramesDecoded="
            java.lang.String r8 = ",\n                                | info="
            java.lang.String r9 = "onOutputBufferAvailable: index="
            java.lang.StringBuilder r3 = A.a.h(r3, r4, r9, r7, r8)
            r3.append(r2)
            r3.append(r5)
            java.lang.String r2 = r3.toString()
            java.lang.String r2 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r2)
            com.samsung.android.sum.core.SLog.d((java.lang.String) r6, (java.lang.String) r2)
            r7 = r25
            r2 = r4
            r8 = r19
            goto L_0x020d
        L_0x025b:
            r2 = -2
            if (r3 != r2) goto L_0x0275
            java.lang.String r2 = TAG
            android.media.MediaFormat r3 = r0.getOutputFormat()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onOutputFormatChanged: "
            r4.<init>(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r2, (java.lang.String) r3)
        L_0x0275:
            r7 = r25
            goto L_0x0209
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder.executeDecoder(com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask, android.media.MediaCodec, android.media.MediaExtractor, int):void");
    }

    private final List<i> executeEncoder(VideoTranscodingTask videoTranscodingTask, MediaCodec mediaCodec, List<? extends i> list) {
        MediaCodec mediaCodec2 = mediaCodec;
        SLog.i(TAG, "executeEncoder: " + list.size());
        ArrayList arrayList = new ArrayList();
        mediaCodec2.start();
        int i2 = 0;
        boolean z = false;
        boolean z3 = false;
        int i7 = 0;
        while (true) {
            if (!z || !z3) {
                if (!z) {
                    int dequeueInputBuffer = mediaCodec2.dequeueInputBuffer(CODEC_POOLING_DURATION_US);
                    if (dequeueInputBuffer < 0) {
                        SLog.w(TAG, "none decoder input buffer available");
                    } else {
                        int i8 = i2 + 1;
                        i iVar = (i) list.get(i2);
                        ByteBuffer byteBuffer = (ByteBuffer) iVar.d;
                        MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) iVar.e;
                        if (CommonsKt.containFlag(bufferInfo, 4)) {
                            SLog.i(TAG, "reached input eos");
                            mediaCodec2.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                            i2 = i8;
                            z = true;
                        } else {
                            ByteBuffer inputBuffer = mediaCodec2.getInputBuffer(dequeueInputBuffer);
                            byteBuffer.rewind();
                            j.b(inputBuffer);
                            inputBuffer.put(byteBuffer);
                            MediaCodec.BufferInfo bufferInfo2 = bufferInfo;
                            int i10 = i8;
                            mediaCodec2.queueInputBuffer(dequeueInputBuffer, 0, inputBuffer.limit(), bufferInfo.presentationTimeUs, bufferInfo.flags);
                            SLog.i(TAG, "encoding buffer[" + i10 + "]: " + CommonsKt.asString(bufferInfo2));
                            i2 = i10;
                        }
                    }
                }
                MediaCodec.BufferInfo bufferInfo3 = new MediaCodec.BufferInfo();
                int dequeueOutputBuffer = mediaCodec2.dequeueOutputBuffer(bufferInfo3, CODEC_POOLING_DURATION_US);
                if (dequeueOutputBuffer >= 0) {
                    ByteBuffer outputBuffer = mediaCodec2.getOutputBuffer(dequeueOutputBuffer);
                    j.b(outputBuffer);
                    i7++;
                    String str = TAG;
                    SLog.i(str, "encoded buffer[" + i7 + "]: " + CommonsKt.asString(bufferInfo3));
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(outputBuffer.limit());
                    allocateDirect.put(outputBuffer);
                    arrayList.add(new i(allocateDirect, bufferInfo3));
                    mediaCodec2.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((bufferInfo3.flags & 4) != 0) {
                        SLog.i(str, "reached output eos");
                        z3 = true;
                    }
                } else if (dequeueOutputBuffer == -2) {
                    SLog.i(TAG, "onOutputFormatChanged: " + mediaCodec2.getOutputFormat());
                } else {
                    SLog.i(TAG, "fail to dequeue " + dequeueOutputBuffer);
                }
            } else {
                mediaCodec2.stop();
                SLog.i(TAG, "outputs: " + arrayList.size());
                return arrayList;
            }
        }
    }

    private final int getCorrectionRotation(int i2, int i7) {
        int i8 = i2 - i7;
        if (i8 < 0) {
            return i8 + 360;
        }
        return i8;
    }

    private final HandlerThread getDecodeCallbackThread() {
        HandlerThread handlerThread = this._decodeCallbackThread;
        j.b(handlerThread);
        return handlerThread;
    }

    private final HandlerThread getEncodeCallbackThread() {
        HandlerThread handlerThread = this._encodeCallbackThread;
        j.b(handlerThread);
        return handlerThread;
    }

    private final HandlerThread getImageReceiveThread() {
        HandlerThread handlerThread = this._imageReceiveThread;
        j.b(handlerThread);
        return handlerThread;
    }

    /* JADX WARNING: type inference failed for: r9v1, types: [com.samsung.android.sum.core.functional.Operator, java.lang.Object] */
    private final Operator makeConverter(VideoTranscodingTask videoTranscodingTask) {
        boolean z;
        i iVar;
        boolean z3;
        boolean z7 = false;
        if (SemSystemProperties.getBoolean("secmm.motionphoto.convert-to-copy", false)) {
            return new Object();
        }
        if (videoTranscodingTask.getRotation() != videoTranscodingTask.getTranscodingRotation()) {
            z = true;
        } else {
            z = false;
        }
        if (videoTranscodingTask.isRatioChangedAfterRotate() && z) {
            iVar = new i(Integer.valueOf(videoTranscodingTask.getVideoHeight()), Integer.valueOf(videoTranscodingTask.getVideoWidth()));
        } else {
            iVar = new i(Integer.valueOf(videoTranscodingTask.getVideoWidth()), Integer.valueOf(videoTranscodingTask.getVideoHeight()));
        }
        float intValue = (float) ((Number) iVar.d).intValue();
        float intValue2 = (float) ((Number) iVar.e).intValue();
        float max = Math.max(((float) videoTranscodingTask.getTranscodingVideoWidth()) / intValue, ((float) videoTranscodingTask.getTranscodingVideoHeight()) / intValue2);
        if (max == 1.0f) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z9 = !z3;
        if (intValue / intValue2 == videoTranscodingTask.getTranscodingVideoRatio()) {
            z7 = true;
        }
        String str = TAG;
        StringBuilder sb2 = new StringBuilder("makeConverter:\n            | isRequiredResize=");
        sb2.append(z9);
        sb2.append(",\n            | isRequiredToRotate=");
        sb2.append(z);
        sb2.append(",\n            | isRequiredToCrop=");
        sb2.append(!z7);
        sb2.append("\n        ");
        SLog.i(str, CommonsKt.trimToOneLine(sb2.toString()));
        ArrayList arrayList = new ArrayList();
        if (z) {
            Operator ofRotate = UniImgp.ofRotate();
            j.d(ofRotate, "ofRotate(...)");
            arrayList.add(new ImgpConverter(ofRotate));
        }
        if (!z3) {
            Operator ofResize = UniImgp.ofResize();
            j.d(ofResize, "ofResize(...)");
            arrayList.add(new ImgpConverter(ofResize));
        }
        if (!z7) {
            Operator ofCrop = UniImgp.ofCrop();
            j.d(ofCrop, "ofCrop(...)");
            arrayList.add(new ImgpConverter(ofCrop));
        }
        return new d(arrayList, this, videoTranscodingTask, max);
    }

    /* access modifiers changed from: private */
    public static final MutableMediaBuffer makeConverter$lambda$0(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        j.e(mediaBuffer, "ibuf");
        j.e(mutableMediaBuffer, "obuf");
        Class cls = HardwareBuffer.class;
        Object typedData = mediaBuffer.getTypedData(cls);
        j.d(typedData, "getTypedData(...)");
        Object typedData2 = mutableMediaBuffer.getTypedData(cls);
        j.d(typedData2, "getTypedData(...)");
        SharedBufferManager.copyHwBufferToHwBuffer((HardwareBuffer) typedData, (HardwareBuffer) typedData2);
        return mutableMediaBuffer;
    }

    /* access modifiers changed from: private */
    public static final MutableMediaBuffer makeConverter$lambda$6(List list, VideoTranscoder videoTranscoder, VideoTranscodingTask videoTranscodingTask, float f, MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        int i2;
        MutableMediaBuffer mutableMediaBuffer2;
        VideoTranscoder videoTranscoder2 = videoTranscoder;
        float f5 = f;
        MutableMediaBuffer mutableMediaBuffer3 = mutableMediaBuffer;
        MediaBuffer mediaBuffer2 = mediaBuffer;
        j.e(mediaBuffer2, "ibuf");
        j.e(mutableMediaBuffer3, "obuf");
        Iterator it = list.iterator();
        MutableMediaBuffer mutableMediaBuffer4 = mutableMediaBuffer3;
        while (it.hasNext()) {
            ImgpConverter imgpConverter = (ImgpConverter) it.next();
            boolean a7 = j.a(imgpConverter, C1194l.T0(list));
            ImgpType imgpType = imgpConverter.getImgpType();
            if (imgpType == null) {
                i2 = -1;
            } else {
                i2 = WhenMappings.$EnumSwitchMapping$0[imgpType.ordinal()];
            }
            Class<HardwareBuffer> cls = HardwareBuffer.class;
            if (i2 == 1) {
                MediaBufferAlloc newImageAlloc = MediaBuffer.newImageAlloc();
                newImageAlloc.setMediaFormat(((MutableMediaFormat) mediaBuffer2.getFormat().toMutableFormat().dupAll()).setCols(mediaBuffer2.getFormat().getRows()).setRows(mediaBuffer2.getFormat().getCols()).setRotation(videoTranscoder2.getCorrectionRotation(videoTranscodingTask.getRotation(), videoTranscodingTask.getTranscodingRotation())));
                if (!a7 || !mutableMediaBuffer3.isNotEmpty()) {
                    newImageAlloc.asSharable();
                    newImageAlloc.setUsage(((HardwareBuffer) mutableMediaBuffer3.getTypedData(cls)).getUsage());
                } else {
                    newImageAlloc.setData(mutableMediaBuffer3.getData());
                }
                mutableMediaBuffer2 = newImageAlloc.allocate().toMutable();
            } else if (i2 == 2) {
                MediaBufferAlloc newImageAlloc2 = MediaBuffer.newImageAlloc();
                MediaFormat format = mediaBuffer2.getFormat();
                String str = TAG;
                SLog.i(str, "org fmt: " + format);
                MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) format.toMutableFormat().dupAll();
                mutableMediaFormat.setCols(CommonsKt.roundToNearestEven(((float) mutableMediaFormat.getCols()) * f5));
                mutableMediaFormat.setRows(CommonsKt.roundToNearestEven(((float) mutableMediaFormat.getRows()) * f5));
                SLog.i(str, "new fmt: " + mutableMediaFormat);
                StringBuilder h5 = a.h(mutableMediaFormat.getCols(), mutableMediaFormat.getRows(), "updated cols/rows: ", "/", ", scale=");
                h5.append(f5);
                SLog.i(str, h5.toString());
                newImageAlloc2.setMediaFormat(mutableMediaFormat);
                if (!a7 || !mutableMediaBuffer3.isNotEmpty()) {
                    newImageAlloc2.asSharable();
                    newImageAlloc2.setUsage(((HardwareBuffer) mutableMediaBuffer3.getTypedData(cls)).getUsage());
                } else {
                    newImageAlloc2.setData(mutableMediaBuffer3.getData());
                }
                mutableMediaBuffer2 = newImageAlloc2.allocateMutable();
            } else if (i2 == 3) {
                MediaBufferAlloc newImageAlloc3 = MediaBuffer.newImageAlloc();
                MediaFormat format2 = mediaBuffer2.getFormat();
                j.d(format2, "getFormat(...)");
                MediaFormat format3 = mutableMediaBuffer3.getFormat();
                j.d(format3, "getFormat(...)");
                newImageAlloc3.setMediaFormat(mutableMediaBuffer3.getFormat().toMutableFormat().setCropRect(videoTranscoder2.computeCropRect(format2, format3)));
                if (!a7 || !mutableMediaBuffer3.isNotEmpty()) {
                    newImageAlloc3.asSharable();
                    newImageAlloc3.setUsage(((HardwareBuffer) mutableMediaBuffer3.getTypedData(cls)).getUsage());
                } else {
                    newImageAlloc3.setData(mutableMediaBuffer3.getData());
                }
                mutableMediaBuffer2 = newImageAlloc3.allocateMutable();
            } else {
                throw new UnsupportedOperationException();
            }
            mediaBuffer2 = imgpConverter.run(mediaBuffer2, mutableMediaBuffer2);
            mutableMediaBuffer4 = mutableMediaBuffer2;
        }
        return mutableMediaBuffer4;
    }

    private final void makeSilentAudioTrack(VideoTranscodingTask videoTranscodingTask) {
        SLog.i(TAG, "makeSilentAudioTrack");
        android.media.MediaFormat transcodingAudioCodecFormat = videoTranscodingTask.getTranscodingAudioCodecFormat();
        int integer = transcodingAudioCodecFormat.getInteger("sample-rate");
        int integer2 = transcodingAudioCodecFormat.getInteger("channel-count");
        int integer3 = transcodingAudioCodecFormat.getInteger("bitrate");
        int i2 = integer2 * 2048;
        int i7 = (1024000000 * integer2) / integer;
        for (Map.Entry entry : videoTranscodingTask.getAllSamples().entrySet()) {
            if (n.u0((CharSequence) entry.getKey(), "video")) {
                List list = (List) entry.getValue();
                Long valueOf = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.L0(list)).e).presentationTimeUs);
                Long valueOf2 = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0(list)).e).presentationTimeUs);
                long longValue = valueOf.longValue();
                long longValue2 = valueOf2.longValue();
                MediaCodec createByCodecName = MediaCodec.createByCodecName(SEC_AAC_CODEC_ENCODER_NAME);
                j.d(createByCodecName, "createByCodecName(...)");
                String str = TAG;
                int i8 = i7;
                StringBuilder h5 = a.h(integer, integer2, "sampleRate=", ", channelCount=", ", firstTimeUs=");
                h5.append(longValue);
                h5.append(", lastTimeUs=");
                h5.append(longValue2);
                h5.append("[");
                h5.append((longValue2 - longValue) / ((long) 1000));
                h5.append("ms]");
                SLog.d(str, h5.toString());
                android.media.MediaFormat createAudioFormat = android.media.MediaFormat.createAudioFormat(MimeType.AUDIO_AAC.getValue(), integer, integer2);
                createAudioFormat.setInteger("aac-profile", 2);
                createAudioFormat.setInteger("bitrate", integer3);
                createByCodecName.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
                ArrayList arrayList = new ArrayList();
                while (longValue <= longValue2) {
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    bufferInfo.size = i2;
                    bufferInfo.offset = 0;
                    bufferInfo.flags = 1;
                    bufferInfo.presentationTimeUs = longValue;
                    arrayList.add(new i(ByteBuffer.allocateDirect(4096), bufferInfo));
                    longValue += (long) i8;
                }
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(0);
                MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
                bufferInfo2.flags = 4;
                arrayList.add(new i(allocateDirect, bufferInfo2));
                VideoTranscodingTask videoTranscodingTask2 = videoTranscodingTask;
                ArrayList arrayList2 = new ArrayList();
                for (Object next : executeEncoder(videoTranscodingTask2, createByCodecName, C1194l.k1(arrayList))) {
                    if ((((MediaCodec.BufferInfo) ((i) next).e).flags & 4) == 0) {
                        arrayList2.add(next);
                    }
                }
                ArrayList m12 = C1194l.m1(arrayList2);
                createByCodecName.release();
                videoTranscodingTask2.addSamples(MimeType.AUDIO_AAC.getValue(), C1194l.k1(m12));
                return;
            }
            VideoTranscodingTask videoTranscodingTask3 = videoTranscodingTask;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    /* access modifiers changed from: private */
    public final void onDecodedImageAvailable(ImageReader imageReader2) {
        String str;
        Throwable th;
        Image image;
        String str2;
        try {
            String str3 = TAG;
            SLog.d(str3, "onDecodedImageAvailable");
            Image acquireNextImage = imageReader2.acquireNextImage();
            if (acquireNextImage != null) {
                ImageWriter imageWriter2 = this.imageWriter;
                if (imageWriter2 != null) {
                    Image dequeueInputImage = imageWriter2.dequeueInputImage();
                    int width = acquireNextImage.getWidth();
                    int height = acquireNextImage.getHeight();
                    HardwareBuffer hardwareBuffer = acquireNextImage.getHardwareBuffer();
                    if (hardwareBuffer != null) {
                        str = Tf.c.a(hardwareBuffer.getUsage());
                    } else {
                        str = null;
                    }
                    int width2 = dequeueInputImage.getWidth();
                    int height2 = dequeueInputImage.getHeight();
                    HardwareBuffer hardwareBuffer2 = dequeueInputImage.getHardwareBuffer();
                    if (hardwareBuffer2 != null) {
                        str2 = Tf.c.a(hardwareBuffer2.getUsage());
                        th = null;
                        image = acquireNextImage;
                    } else {
                        str2 = null;
                        image = acquireNextImage;
                        th = null;
                    }
                    SLog.i(str3, CommonsKt.trimToOneLine("src: \n                    | w/h[" + width + "/" + height + "], usg=0x" + str + ", \n                    | dst:\n                    | w/h[" + width2 + "/" + height2 + "], usg=0x" + str2 + ",\n                "));
                    long currentTimeMillis = System.currentTimeMillis();
                    Operator operator = this.converter;
                    if (operator != null) {
                        operator.run(MediaBuffer.of(image), MediaBuffer.of(dequeueInputImage));
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        SLog.i(str3, "conv ts: " + currentTimeMillis2 + " ms");
                        dequeueInputImage.setDataSpace(image.getDataSpace());
                        dequeueInputImage.setTimestamp(image.getTimestamp());
                        image.close();
                        int i2 = this.numProcessFrames + 1;
                        this.numProcessFrames = i2;
                        SLog.d(str3, "onDecodedImageAvailable[" + i2 + "]: queueInputImage w/ timestamp=" + (dequeueInputImage.getTimestamp() / ((long) 1000)) + " us");
                        ImageWriter imageWriter3 = this.imageWriter;
                        if (imageWriter3 != null) {
                            imageWriter3.queueInputImage(dequeueInputImage);
                        } else {
                            j.k("imageWriter");
                            throw th;
                        }
                    } else {
                        j.k("converter");
                        throw th;
                    }
                } else {
                    j.k("imageWriter");
                    throw null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final MediaCodec prepareDecoder(VideoTranscodingTask videoTranscodingTask, MediaExtractor mediaExtractor, int i2) {
        Long l;
        long j2;
        SLog.i(TAG, "prepareDecoder E");
        android.media.MediaFormat videoCodecFormat = videoTranscodingTask.getVideoCodecFormat();
        configVideoDecoderParameters(videoCodecFormat);
        ImageReader newInstance = ImageReader.newInstance(videoTranscodingTask.getVideoWidth(), videoTranscodingTask.getVideoHeight(), 34, 16);
        j.d(newInstance, "newInstance(...)");
        newInstance.setOnImageAvailableListener(new a(0, this), new Handler(getImageReceiveThread().getLooper()));
        this.imageReader = newInstance;
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(videoTranscodingTask.getVideoCodecType().getValue());
        j.d(createDecoderByType, "createDecoderByType(...)");
        if (this.isUseDecoderCallback) {
            Iterator it = B1.a.Z(0, mediaExtractor.getTrackCount()).iterator();
            Ge.d dVar = (Ge.d) it;
            if (!dVar.f) {
                l = null;
            } else {
                y yVar = (y) it;
                String string = mediaExtractor.getTrackFormat(yVar.nextInt()).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                j.b(string);
                l = Long.valueOf(videoTranscodingTask.getMpFile().getDistinctLastTimestampUs(string));
                while (dVar.f) {
                    String string2 = mediaExtractor.getTrackFormat(yVar.nextInt()).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                    j.b(string2);
                    Long valueOf = Long.valueOf(videoTranscodingTask.getMpFile().getDistinctLastTimestampUs(string2));
                    if (l.compareTo(valueOf) > 0) {
                        l = valueOf;
                    }
                }
            }
            if (l != null) {
                j2 = l.longValue();
            } else {
                j2 = 0;
            }
            createDecoderByType.setCallback(new VideoDecoderCallback.Builder().setExtractor(mediaExtractor).setMediaFormat(videoCodecFormat).setLastTimestampUs(j2).setOnInputReachedEOS((Ae.a) new b(mediaExtractor, i2)).setOnOutputReachedEOS((c) new w(4, this)).build());
        }
        String str = TAG;
        SLog.i(str, "decode-format: " + videoCodecFormat);
        ImageReader imageReader2 = this.imageReader;
        if (imageReader2 != null) {
            createDecoderByType.configure(videoCodecFormat, imageReader2.getSurface(), (MediaCrypto) null, 0);
            setBoostMode(createDecoderByType);
            SLog.i(str, "prepareDecoder X");
            return createDecoderByType;
        }
        j.k("imageReader");
        throw null;
    }

    /* access modifiers changed from: private */
    public static final x prepareDecoder$lambda$25(MediaExtractor mediaExtractor, int i2) {
        mediaExtractor.unselectTrack(i2);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x prepareDecoder$lambda$26(VideoTranscoder videoTranscoder, int i2, long j2) {
        c cVar = videoTranscoder.sendEOSHint;
        if (cVar != null) {
            cVar.invoke(Integer.valueOf(i2), Long.valueOf(j2));
            return x.f4917a;
        }
        j.k("sendEOSHint");
        throw null;
    }

    private final MediaCodec prepareEncoder(VideoTranscodingTask videoTranscodingTask, MediaExtractor mediaExtractor, int i2) {
        Long l;
        long j2;
        String str = TAG;
        SLog.i(str, "prepareEncoder E");
        android.media.MediaFormat transcodingVideoCodecFormat = videoTranscodingTask.getTranscodingVideoCodecFormat();
        MimeType transcodingVideoCodecType = videoTranscodingTask.getTranscodingVideoCodecType();
        configVideoEncoderParameters(transcodingVideoCodecFormat, videoTranscodingTask);
        SLog.i(str, "encoding-format: " + transcodingVideoCodecFormat);
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType(transcodingVideoCodecType.getValue());
        j.d(createEncoderByType, "createEncoderByType(...)");
        if (this.isUseEncoderCallback) {
            Iterator it = B1.a.Z(0, mediaExtractor.getTrackCount()).iterator();
            Ge.d dVar = (Ge.d) it;
            if (!dVar.f) {
                l = null;
            } else {
                y yVar = (y) it;
                String string = mediaExtractor.getTrackFormat(yVar.nextInt()).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                j.b(string);
                l = Long.valueOf(videoTranscodingTask.getMpFile().getDistinctLastTimestampUs(string));
                while (dVar.f) {
                    String string2 = mediaExtractor.getTrackFormat(yVar.nextInt()).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                    j.b(string2);
                    Long valueOf = Long.valueOf(videoTranscodingTask.getMpFile().getDistinctLastTimestampUs(string2));
                    if (l.compareTo(valueOf) > 0) {
                        l = valueOf;
                    }
                }
            }
            if (l != null) {
                j2 = l.longValue();
            } else {
                j2 = 0;
            }
            VideoEncoderCallback build = new VideoEncoderCallback.Builder().setEOSTimestampUs(j2).setOnReachedEOS((b) new h(videoTranscodingTask, transcodingVideoCodecFormat, this, 3)).setOnEachFrameDone((b) new h(this, videoTranscodingTask, transcodingVideoCodecType, 4)).build();
            this.sendEOSHint = new VideoTranscoder$prepareEncoder$1(build);
            createEncoderByType.setCallback(build);
        }
        createEncoderByType.configure(transcodingVideoCodecFormat, (Surface) null, (MediaCrypto) null, 1);
        this.imageWriter = ImageWriter.newInstance(createEncoderByType.createInputSurface(), 16);
        SLog.i(TAG, "prepareEncoder X");
        return createEncoderByType;
    }

    /* access modifiers changed from: private */
    public static final x prepareEncoder$lambda$20(VideoTranscodingTask videoTranscodingTask, android.media.MediaFormat mediaFormat, VideoTranscoder videoTranscoder, List list) {
        j.e(list, "it");
        String string = mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
        j.b(string);
        videoTranscodingTask.addSamples(string, C1194l.k1(list));
        videoTranscoder.countingLatch.down();
        EventListener eventListener2 = videoTranscoder.eventListener;
        if (eventListener2 != null) {
            eventListener2.onTranscodingComplete(videoTranscodingTask.getMpFile());
        }
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x prepareEncoder$lambda$21(VideoTranscoder videoTranscoder, VideoTranscodingTask videoTranscodingTask, MimeType mimeType, MediaCodec.BufferInfo bufferInfo) {
        j.e(bufferInfo, "it");
        EventListener eventListener2 = videoTranscoder.eventListener;
        if (eventListener2 != null) {
            eventListener2.onEachFrameTranscoded(videoTranscodingTask.getMpFile(), mimeType.getValue(), bufferInfo);
        }
        return x.f4917a;
    }

    private final void processAudio(VideoTranscodingTask videoTranscodingTask, MediaExtractor mediaExtractor, int i2) {
        Long l;
        long j2;
        SLog.i(TAG, "processAudio");
        android.media.MediaFormat audioCodecFormat = videoTranscodingTask.getAudioCodecFormat();
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(audioCodecFormat.getInteger("max-input-size"));
        ArrayList arrayList = new ArrayList();
        if (videoTranscodingTask.getTranscodingAudioCodecType() == videoTranscodingTask.getAudioCodecType()) {
            Iterator it = B1.a.Z(0, mediaExtractor.getTrackCount()).iterator();
            if (!it.hasNext()) {
                l = null;
            } else {
                y yVar = (y) it;
                String string = mediaExtractor.getTrackFormat(yVar.nextInt()).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                j.b(string);
                Long valueOf = Long.valueOf(videoTranscodingTask.getMpFile().getDistinctLastTimestampUs(string));
                while (it.hasNext()) {
                    String string2 = mediaExtractor.getTrackFormat(yVar.nextInt()).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                    j.b(string2);
                    Long valueOf2 = Long.valueOf(videoTranscodingTask.getMpFile().getDistinctLastTimestampUs(string2));
                    if (valueOf.compareTo(valueOf2) > 0) {
                        valueOf = valueOf2;
                    }
                }
                l = valueOf;
            }
            if (l != null) {
                j2 = l.longValue();
            } else {
                j2 = 0;
            }
            mediaExtractor.selectTrack(i2);
            while (true) {
                int readSampleData = mediaExtractor.readSampleData(allocateDirect, 0);
                allocateDirect.rewind();
                if (readSampleData < 0 || mediaExtractor.getSampleTime() >= j2) {
                    String str = TAG;
                    long distinctDurationMs = videoTranscodingTask.getDistinctDurationMs();
                    SLog.i(str, CommonsKt.trimToOneLine("reached audio EOS: \n                    | durationMs=" + distinctDurationMs + "\n                "));
                    mediaExtractor.unselectTrack(i2);
                    String string3 = audioCodecFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                    j.b(string3);
                    videoTranscodingTask.addSamples(string3, C1194l.k1(arrayList));
                    this.countingLatch.down();
                } else {
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    bufferInfo.size = allocateDirect.limit();
                    bufferInfo.offset = 0;
                    bufferInfo.flags = mediaExtractor.getSampleFlags();
                    bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
                    String str2 = TAG;
                    String asString = CommonsKt.asString(bufferInfo);
                    SLog.d(str2, "store audio sample: info=" + asString);
                    ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(allocateDirect.limit());
                    allocateDirect2.put(allocateDirect);
                    arrayList.add(new i(allocateDirect2, bufferInfo));
                    mediaExtractor.advance();
                }
            }
            String str3 = TAG;
            long distinctDurationMs2 = videoTranscodingTask.getDistinctDurationMs();
            SLog.i(str3, CommonsKt.trimToOneLine("reached audio EOS: \n                    | durationMs=" + distinctDurationMs2 + "\n                "));
            mediaExtractor.unselectTrack(i2);
            String string32 = audioCodecFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
            j.b(string32);
            videoTranscodingTask.addSamples(string32, C1194l.k1(arrayList));
            this.countingLatch.down();
            return;
        }
        MimeType audioCodecType = videoTranscodingTask.getAudioCodecType();
        MimeType transcodingAudioCodecType = videoTranscodingTask.getTranscodingAudioCodecType();
        throw new IllegalArgumentException(CommonsKt.trimToOneLine("currently support only same audio codec type, but given file requires conversion from \n                | " + audioCodecType + " to " + transcodingAudioCodecType + "\n            ").toString());
    }

    private final i processVideo(VideoTranscodingTask videoTranscodingTask, MediaExtractor mediaExtractor, int i2) {
        String str = TAG;
        SLog.i(str, "processVideo");
        MediaCodec prepareEncoder = prepareEncoder(videoTranscodingTask, mediaExtractor, i2);
        SLog.i(str, "success to create video encoder");
        prepareEncoder.start();
        mediaExtractor.selectTrack(i2);
        MediaCodec prepareDecoder = prepareDecoder(videoTranscodingTask, mediaExtractor, i2);
        SLog.i(str, "success to create video decoder");
        prepareDecoder.start();
        ImageReader imageReader2 = this.imageReader;
        if (imageReader2 != null) {
            SharedBufferManager.setSurfaceAsDroppable(imageReader2.getSurface(), false);
            if (!this.isUseEncoderCallback) {
                executeEncoder(videoTranscodingTask, prepareEncoder, mediaExtractor, i2);
            }
            if (!this.isUseDecoderCallback) {
                executeDecoder(videoTranscodingTask, prepareDecoder, mediaExtractor, i2);
            }
            return new i(prepareDecoder, prepareEncoder);
        }
        j.k("imageReader");
        throw null;
    }

    private final void setBoostMode(MediaCodec mediaCodec) {
        Bundle bundle = new Bundle();
        bundle.putInt("request-booster", 1);
        bundle.putInt("enable", 1);
        mediaCodec.setParameters(bundle);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void process(com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask r18) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            java.lang.String r3 = "release extractor"
            java.lang.String r0 = "task"
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = TAG
            java.lang.String r4 = r2.path()
            int r5 = r2.getVideoWidth()
            int r6 = r2.getVideoHeight()
            int r7 = r2.getRotation()
            com.samsung.android.motionphoto.utils.v2.MimeType r8 = r2.getVideoCodecType()
            int r9 = r2.getTranscodingVideoWidth()
            int r10 = r2.getTranscodingVideoHeight()
            int r11 = r2.getTranscodingRotation()
            com.samsung.android.motionphoto.utils.v2.MimeType r12 = r2.getTranscodingVideoCodecType()
            java.lang.String r13 = "transcode for "
            java.lang.String r14 = " =>\n            | src-format: w/h/rotation="
            java.lang.String r15 = "/"
            java.lang.StringBuilder r4 = i.C0212a.u(r13, r4, r14, r5, r15)
            java.lang.String r5 = ",\n            | src-codec: video="
            N2.j.x(r4, r6, r15, r7, r5)
            r4.append(r8)
            java.lang.String r5 = ",\n            | dst-format: w/h/rotation="
            r4.append(r5)
            r4.append(r9)
            r4.append(r15)
            java.lang.String r5 = ",\n            | dst-codec: video="
            N2.j.x(r4, r10, r15, r11, r5)
            r4.append(r12)
            java.lang.String r5 = ",\n        "
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r4)
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r4)
            r4 = 0
            r1.numProcessFrames = r4
            android.os.HandlerThread r5 = new android.os.HandlerThread
            java.lang.String r6 = ": video-transcode-thread"
            java.lang.String r0 = i.C0212a.A(r0, r6)
            r6 = -16
            r5.<init>(r0, r6)
            r1._imageReceiveThread = r5
            android.os.HandlerThread r0 = r1.getImageReceiveThread()
            r0.start()
            com.samsung.android.sum.core.functional.Operator r0 = r17.makeConverter(r18)
            r1.converter = r0
            long r5 = java.lang.System.currentTimeMillis()
            com.samsung.android.motionphoto.utils.v2.MPFile r0 = r2.getMpFile()
            java.io.FileInputStream r7 = r0.newInputFileStream()
            android.media.MediaExtractor r8 = new android.media.MediaExtractor     // Catch:{ all -> 0x0161 }
            r8.<init>()     // Catch:{ all -> 0x0161 }
            java.io.FileDescriptor r9 = r7.getFD()     // Catch:{ all -> 0x0161 }
            com.samsung.android.motionphoto.utils.v2.MPFile r0 = r2.getMpFile()     // Catch:{ all -> 0x0161 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r0 = r0.getMpInfo()     // Catch:{ all -> 0x0161 }
            long r10 = r0.getVideoPosition()     // Catch:{ all -> 0x0161 }
            com.samsung.android.motionphoto.utils.v2.MPFile r0 = r2.getMpFile()     // Catch:{ all -> 0x0161 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r0 = r0.getMpInfo()     // Catch:{ all -> 0x0161 }
            long r12 = r0.getVideoSize()     // Catch:{ all -> 0x0161 }
            r8.setDataSource(r9, r10, r12)     // Catch:{ all -> 0x0161 }
            com.samsung.android.sum.core.functional.CountingLatch r0 = r1.countingLatch     // Catch:{ Exception -> 0x0165 }
            r0.reset()     // Catch:{ Exception -> 0x0165 }
            int r0 = r8.getTrackCount()     // Catch:{ Exception -> 0x0165 }
            r10 = r4
            r11 = 0
            r12 = 0
        L_0x00c3:
            if (r10 >= r0) goto L_0x0126
            android.media.MediaFormat r13 = r8.getTrackFormat(r10)     // Catch:{ Exception -> 0x010a }
            java.lang.String r14 = "getTrackFormat(...)"
            kotlin.jvm.internal.j.d(r13, r14)     // Catch:{ Exception -> 0x010a }
            java.lang.String r14 = "mime"
            java.lang.String r14 = r13.getString(r14)     // Catch:{ Exception -> 0x010a }
            kotlin.jvm.internal.j.b(r14)     // Catch:{ Exception -> 0x010a }
            java.lang.String r15 = TAG     // Catch:{ Exception -> 0x010a }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010a }
            r9.<init>()     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = "transcode track: mimeType="
            r9.append(r4)     // Catch:{ Exception -> 0x010a }
            r9.append(r14)     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = ", format="
            r9.append(r4)     // Catch:{ Exception -> 0x010a }
            r9.append(r13)     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = r9.toString()     // Catch:{ Exception -> 0x010a }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r15, (java.lang.String) r4)     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = "audio"
            boolean r4 = Tf.v.t0(r14, r4)     // Catch:{ Exception -> 0x010a }
            if (r4 == 0) goto L_0x010c
            com.samsung.android.sum.core.functional.CountingLatch r4 = r1.countingLatch     // Catch:{ Exception -> 0x010a }
            r4.up()     // Catch:{ Exception -> 0x010a }
            r1.processAudio(r2, r8, r10)     // Catch:{ Exception -> 0x010a }
            goto L_0x0122
        L_0x0107:
            r0 = move-exception
            goto L_0x01e8
        L_0x010a:
            r0 = move-exception
            goto L_0x0168
        L_0x010c:
            java.lang.String r4 = "video"
            boolean r4 = Tf.v.t0(r14, r4)     // Catch:{ Exception -> 0x010a }
            if (r4 == 0) goto L_0x0122
            com.samsung.android.sum.core.functional.CountingLatch r4 = r1.countingLatch     // Catch:{ Exception -> 0x010a }
            r4.up()     // Catch:{ Exception -> 0x010a }
            me.i r4 = r1.processVideo(r2, r8, r10)     // Catch:{ Exception -> 0x010a }
            java.lang.Object r12 = r4.d     // Catch:{ Exception -> 0x010a }
            java.lang.Object r11 = r4.e     // Catch:{ Exception -> 0x010a }
        L_0x0122:
            int r10 = r10 + 1
            r4 = 0
            goto L_0x00c3
        L_0x0126:
            java.lang.String r0 = TAG     // Catch:{ Exception -> 0x010a }
            com.samsung.android.sum.core.functional.CountingLatch r4 = r1.countingLatch     // Catch:{ Exception -> 0x010a }
            int r4 = r4.getCount()     // Catch:{ Exception -> 0x010a }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010a }
            r9.<init>()     // Catch:{ Exception -> 0x010a }
            java.lang.String r10 = "wait....E: "
            r9.append(r10)     // Catch:{ Exception -> 0x010a }
            r9.append(r4)     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = r9.toString()     // Catch:{ Exception -> 0x010a }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r4)     // Catch:{ Exception -> 0x010a }
            com.samsung.android.sum.core.functional.CountingLatch r4 = r1.countingLatch     // Catch:{ Exception -> 0x010a }
            r9 = 15000(0x3a98, double:7.411E-320)
            r13 = 0
            r4.await((int) r13, (long) r9)     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = "wait....X"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r4)     // Catch:{ Exception -> 0x010a }
            boolean r4 = r2.isSilentAudioTrackRequired()     // Catch:{ Exception -> 0x010a }
            if (r4 == 0) goto L_0x015a
            r17.makeSilentAudioTrack(r18)     // Catch:{ Exception -> 0x010a }
        L_0x015a:
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r3)     // Catch:{ all -> 0x0161 }
            r8.release()     // Catch:{ all -> 0x0161 }
            goto L_0x016e
        L_0x0161:
            r0 = move-exception
            r1 = r0
            goto L_0x01f1
        L_0x0165:
            r0 = move-exception
            r11 = 0
            r12 = 0
        L_0x0168:
            r0.printStackTrace()     // Catch:{ all -> 0x0107 }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0161 }
            goto L_0x015a
        L_0x016e:
            r7.close()
            android.media.MediaCodec r12 = (android.media.MediaCodec) r12
            if (r12 == 0) goto L_0x017b
            r12.stop()
            r12.release()
        L_0x017b:
            android.os.HandlerThread r0 = r1._decodeCallbackThread
            if (r0 == 0) goto L_0x0185
            r0.quitSafely()
            r3 = 0
            r1._decodeCallbackThread = r3
        L_0x0185:
            android.media.MediaCodec r11 = (android.media.MediaCodec) r11
            if (r11 == 0) goto L_0x018f
            r11.stop()
            r11.release()
        L_0x018f:
            android.os.HandlerThread r0 = r1._encodeCallbackThread
            if (r0 == 0) goto L_0x019a
            r0.quitSafely()
            r3 = 0
            r1._encodeCallbackThread = r3
            goto L_0x019b
        L_0x019a:
            r3 = 0
        L_0x019b:
            android.os.HandlerThread r0 = r1.getImageReceiveThread()
            r0.quitSafely()
            r1._imageReceiveThread = r3
            android.media.ImageReader r0 = r1.imageReader
            if (r0 == 0) goto L_0x01e0
            r0.close()
            android.media.ImageWriter r0 = r1.imageWriter
            if (r0 == 0) goto L_0x01d8
            r0.close()
            java.lang.String r0 = TAG
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r5
            java.lang.String r1 = r2.path()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "transcoding is done["
            r2.<init>(r5)
            r2.append(r3)
            java.lang.String r3 = " ms]: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r1)
            return
        L_0x01d8:
            java.lang.String r0 = "imageWriter"
            kotlin.jvm.internal.j.k(r0)
            r16 = 0
            throw r16
        L_0x01e0:
            r16 = 0
            java.lang.String r0 = "imageReader"
            kotlin.jvm.internal.j.k(r0)
            throw r16
        L_0x01e8:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0161 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r1, (java.lang.String) r3)     // Catch:{ all -> 0x0161 }
            r8.release()     // Catch:{ all -> 0x0161 }
            throw r0     // Catch:{ all -> 0x0161 }
        L_0x01f1:
            throw r1     // Catch:{ all -> 0x01f2 }
        L_0x01f2:
            r0 = move-exception
            B1.a.g(r7, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder.process(com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask):void");
    }

    public final void setOnEventListener(EventListener eventListener2) {
        j.e(eventListener2, "eventListener");
        this.eventListener = eventListener2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoTranscoder(Builder builder) {
        this();
        j.e(builder, "builder");
        this.isUseDecoderCallback = builder.isUseDecoderCallback$motionphoto_utils_v2_0_17_release();
        this.isUseEncoderCallback = builder.isUseEncoderCallback$motionphoto_utils_v2_0_17_release();
    }

    private final void executeEncoder(VideoTranscodingTask videoTranscodingTask, MediaCodec mediaCodec, MediaExtractor mediaExtractor, int i2) {
        throw new v0("An operation is not implemented: implement", 2);
    }
}
