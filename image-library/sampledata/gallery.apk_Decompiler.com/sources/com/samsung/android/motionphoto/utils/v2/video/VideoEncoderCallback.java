package com.samsung.android.motionphoto.utils.v2.video;

import A.a;
import Ae.b;
import Ke.v0;
import android.media.MediaCodec;
import android.media.MediaFormat;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.sum.core.SLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 .2\u00020\u0001:\u0002/.B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR&\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00170 0\u001f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R<\u0010&\u001a(\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00170 0\u001f\u0012\u0004\u0012\u00020\u000b\u0018\u00010$j\u0004\u0018\u0001`%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'R*\u0010)\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0018\u00010$j\u0004\u0018\u0001`(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010'R\u0016\u0010*\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\b\u0010-R\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\n\u0010+¨\u00060"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback;", "Landroid/media/MediaCodec$Callback;", "<init>", "()V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback$Builder;", "builder", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback$Builder;)V", "", "numFramesToEncode", "", "lastTimestampUs", "Lme/x;", "setEOSHint", "(IJ)V", "Landroid/media/MediaCodec;", "codec", "Landroid/media/MediaCodec$CodecException;", "e", "onError", "(Landroid/media/MediaCodec;Landroid/media/MediaCodec$CodecException;)V", "index", "onInputBufferAvailable", "(Landroid/media/MediaCodec;I)V", "Landroid/media/MediaCodec$BufferInfo;", "info", "onOutputBufferAvailable", "(Landroid/media/MediaCodec;ILandroid/media/MediaCodec$BufferInfo;)V", "Landroid/media/MediaFormat;", "format", "onOutputFormatChanged", "(Landroid/media/MediaCodec;Landroid/media/MediaFormat;)V", "", "Lme/i;", "Ljava/nio/ByteBuffer;", "samples", "Ljava/util/List;", "Lkotlin/Function1;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnEncoderReachedEOS;", "onReachedEOS", "LAe/b;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnEachFrameDone;", "onEachFrameDone", "eosTimestampUs", "J", "numFramesEncoded", "I", "Companion", "Builder", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoEncoderCallback extends MediaCodec.Callback {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private long eosTimestampUs;
    private long lastTimestampUs;
    private int numFramesEncoded;
    private int numFramesToEncode;
    private b onEachFrameDone;
    private b onReachedEOS;
    private final List<i> samples;

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\f\u001a\u00020\u00002(\u0010\u000b\u001a$\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\n¢\u0006\u0004\b\f\u0010\rJ%\u0010\u0010\u001a\u00020\u00002\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\u000e¢\u0006\u0004\b\u0010\u0010\rJ\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017RD\u0010\u000b\u001a$\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\n8\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u000b\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\f\u0010\u001bR2\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\u000e8\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u000f\u0010\u0018\u001a\u0004\b\u001c\u0010\u001a\"\u0004\b\u0010\u0010\u001bR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback$Builder;", "", "<init>", "()V", "Lkotlin/Function1;", "", "Lme/i;", "Ljava/nio/ByteBuffer;", "Landroid/media/MediaCodec$BufferInfo;", "Lme/x;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnEncoderReachedEOS;", "onReachedEOS", "setOnReachedEOS", "(LAe/b;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnEachFrameDone;", "onEachFrameDone", "setOnEachFrameDone", "", "eosTimestampUs", "setEOSTimestampUs", "(J)Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback;", "build", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback;", "LAe/b;", "getOnReachedEOS", "()LAe/b;", "(LAe/b;)V", "getOnEachFrameDone", "J", "getEosTimestampUs", "()J", "setEosTimestampUs", "(J)V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private long eosTimestampUs;
        public b onEachFrameDone;
        public b onReachedEOS;

        public final VideoEncoderCallback build() {
            return new VideoEncoderCallback(this);
        }

        public final long getEosTimestampUs() {
            return this.eosTimestampUs;
        }

        public final b getOnEachFrameDone() {
            b bVar = this.onEachFrameDone;
            if (bVar != null) {
                return bVar;
            }
            j.k("onEachFrameDone");
            throw null;
        }

        public final b getOnReachedEOS() {
            b bVar = this.onReachedEOS;
            if (bVar != null) {
                return bVar;
            }
            j.k("onReachedEOS");
            throw null;
        }

        public final Builder setEOSTimestampUs(long j2) {
            this.eosTimestampUs = j2;
            return this;
        }

        public final void setEosTimestampUs(long j2) {
            this.eosTimestampUs = j2;
        }

        /* renamed from: setOnEachFrameDone  reason: collision with other method in class */
        public final void m45setOnEachFrameDone(b bVar) {
            j.e(bVar, "<set-?>");
            this.onEachFrameDone = bVar;
        }

        /* renamed from: setOnReachedEOS  reason: collision with other method in class */
        public final void m46setOnReachedEOS(b bVar) {
            j.e(bVar, "<set-?>");
            this.onReachedEOS = bVar;
        }

        public final Builder setOnEachFrameDone(b bVar) {
            j.e(bVar, "onEachFrameDone");
            setOnEachFrameDone(bVar);
            return this;
        }

        public final Builder setOnReachedEOS(b bVar) {
            j.e(bVar, "onReachedEOS");
            setOnReachedEOS(bVar);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoEncoderCallback$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoEncoderCallback.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public VideoEncoderCallback() {
        this.samples = new ArrayList();
        this.numFramesToEncode = Integer.MAX_VALUE;
        this.lastTimestampUs = Long.MAX_VALUE;
    }

    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        j.e(mediaCodec, "codec");
        j.e(codecException, "e");
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    public void onInputBufferAvailable(MediaCodec mediaCodec, int i2) {
        j.e(mediaCodec, "codec");
        String str = TAG;
        SLog.d(str, "onInputBufferAvailable: " + i2);
    }

    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i2, MediaCodec.BufferInfo bufferInfo) {
        j.e(mediaCodec, "codec");
        j.e(bufferInfo, "info");
        ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(i2);
        j.b(outputBuffer);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(outputBuffer.limit());
        allocateDirect.put(outputBuffer);
        this.samples.add(new i(allocateDirect, bufferInfo));
        mediaCodec.releaseOutputBuffer(i2, false);
        if (!CommonsKt.containFlag(bufferInfo, 2)) {
            this.numFramesEncoded++;
        }
        b bVar = this.onEachFrameDone;
        if (bVar != null) {
            bVar.invoke(bufferInfo);
        }
        String str = TAG;
        SLog.i(str, "onOutputBufferAvailable: " + bufferInfo.presentationTimeUs + " us");
        int i7 = this.numFramesEncoded;
        String asString = CommonsKt.asString(bufferInfo);
        StringBuilder h5 = a.h(i2, i7, "onOutputBufferAvailable: index=", ", numFramesEncoded=", ", info=");
        h5.append(asString);
        SLog.d(str, h5.toString());
        int i8 = this.numFramesEncoded;
        int i10 = this.numFramesToEncode;
        if (i8 >= i10 || bufferInfo.presentationTimeUs >= this.lastTimestampUs) {
            long j2 = bufferInfo.presentationTimeUs;
            long j3 = this.lastTimestampUs;
            StringBuilder h6 = a.h(i8, i10, "encoder output is reached EOS:\n                | # of frames=", "/", ",\n                | timestampUs=");
            h6.append(j2);
            h6.append("/");
            h6.append(j3);
            h6.append("\n            ");
            SLog.i(str, CommonsKt.trimToOneLine(h6.toString()));
            b bVar2 = this.onReachedEOS;
            if (bVar2 != null) {
                bVar2.invoke(this.samples);
            }
        }
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        j.e(mediaCodec, "codec");
        j.e(mediaFormat, "format");
        String str = TAG;
        SLog.i(str, "onOutputFormatChanged: " + mediaFormat);
    }

    public final void setEOSHint(int i2, long j2) {
        String str = TAG;
        SLog.i(str, "setEOSHint: numFramesToEncode=" + i2 + ", lastTimestampUs=" + j2);
        this.numFramesToEncode = i2;
        if (j2 < this.lastTimestampUs) {
            this.lastTimestampUs = j2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoEncoderCallback(Builder builder) {
        this();
        j.e(builder, "builder");
        this.lastTimestampUs = builder.getEosTimestampUs();
        this.onEachFrameDone = builder.getOnEachFrameDone();
        this.onReachedEOS = builder.getOnReachedEOS();
    }
}
