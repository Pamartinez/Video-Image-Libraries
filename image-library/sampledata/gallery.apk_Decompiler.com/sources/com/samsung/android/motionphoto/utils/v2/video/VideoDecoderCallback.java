package com.samsung.android.motionphoto.utils.v2.video;

import Ae.a;
import Ae.c;
import Ke.v0;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 02\u00020\u0001:\u000210B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0017¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR$\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u001dj\u0004\u0018\u0001`\u001e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R0\u0010$\u001a\u001c\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u000f\u0018\u00010!j\u0004\u0018\u0001`#8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b*\u0010'R\u0016\u0010+\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b+\u0010'R\u0016\u0010,\u001a\u00020\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010)R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback;", "Landroid/media/MediaCodec$Callback;", "Landroid/media/MediaExtractor;", "extractor", "Landroid/media/MediaFormat;", "format", "<init>", "(Landroid/media/MediaExtractor;Landroid/media/MediaFormat;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "builder", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;)V", "Landroid/media/MediaCodec;", "codec", "Landroid/media/MediaCodec$CodecException;", "e", "Lme/x;", "onError", "(Landroid/media/MediaCodec;Landroid/media/MediaCodec$CodecException;)V", "", "index", "onInputBufferAvailable", "(Landroid/media/MediaCodec;I)V", "Landroid/media/MediaCodec$BufferInfo;", "info", "onOutputBufferAvailable", "(Landroid/media/MediaCodec;ILandroid/media/MediaCodec$BufferInfo;)V", "onOutputFormatChanged", "(Landroid/media/MediaCodec;Landroid/media/MediaFormat;)V", "Landroid/media/MediaExtractor;", "Lkotlin/Function0;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnDecoderInputReachedEOS;", "onInputReachedEOS", "LAe/a;", "Lkotlin/Function2;", "", "Lcom/samsung/android/motionphoto/utils/v2/video/OnDecoderOutputReachedEOS;", "onOutputReachedEOS", "LAe/c;", "maxInputSize", "I", "lastTimestampUs", "J", "numFramesToDecode", "numFramesDecoded", "lastDecodedTimestampUs", "", "isInputReachedEOS", "Z", "Companion", "Builder", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoDecoderCallback extends MediaCodec.Callback {
    public static final Companion Companion = new Companion((e) null);
    private static final int MP_MAX_BITSTREAM_SIZE = 2000000;
    /* access modifiers changed from: private */
    public static final String TAG;
    private final MediaExtractor extractor;
    private boolean isInputReachedEOS;
    private long lastDecodedTimestampUs;
    private long lastTimestampUs;
    private final int maxInputSize;
    private int numFramesDecoded;
    private int numFramesToDecode;
    private a onInputReachedEOS;
    private c onOutputReachedEOS;

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ+\u0010\u0012\u001a\u00020\u00002\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\fj\u0002`\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u00002\u0010\u0010\u0016\u001a\f\u0012\u0004\u0012\u00020\u000f0\u0014j\u0002`\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u000e¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\u0006\u0010\"R\"\u0010#\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b\n\u0010'R,\u0010\u0016\u001a\f\u0012\u0004\u0012\u00020\u000f0\u0014j\u0002`\u00158\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u0016\u0010(\u001a\u0004\b)\u0010*\"\u0004\b\u0017\u0010+R8\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\fj\u0002`\u00108\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u0011\u0010,\u001a\u0004\b-\u0010.\"\u0004\b\u0012\u0010/R\"\u0010\u0019\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0019\u00100\u001a\u0004\b1\u00102\"\u0004\b\u001a\u00103¨\u00064"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "", "<init>", "()V", "Landroid/media/MediaExtractor;", "extractor", "setExtractor", "(Landroid/media/MediaExtractor;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "Landroid/media/MediaFormat;", "format", "setMediaFormat", "(Landroid/media/MediaFormat;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "Lkotlin/Function2;", "", "", "Lme/x;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnDecoderOutputReachedEOS;", "onOutputReachedEOS", "setOnOutputReachedEOS", "(LAe/c;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "Lkotlin/Function0;", "Lcom/samsung/android/motionphoto/utils/v2/video/OnDecoderInputReachedEOS;", "onInputReachedEOS", "setOnInputReachedEOS", "(LAe/a;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "lastTimestampUs", "setLastTimestampUs", "(J)Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback;", "build", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback;", "Landroid/media/MediaExtractor;", "getExtractor", "()Landroid/media/MediaExtractor;", "(Landroid/media/MediaExtractor;)V", "mediaFormat", "Landroid/media/MediaFormat;", "getMediaFormat", "()Landroid/media/MediaFormat;", "(Landroid/media/MediaFormat;)V", "LAe/a;", "getOnInputReachedEOS", "()LAe/a;", "(LAe/a;)V", "LAe/c;", "getOnOutputReachedEOS", "()LAe/c;", "(LAe/c;)V", "J", "getLastTimestampUs", "()J", "(J)V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        public MediaExtractor extractor;
        private long lastTimestampUs;
        public MediaFormat mediaFormat;
        public a onInputReachedEOS;
        public c onOutputReachedEOS;

        public final VideoDecoderCallback build() {
            return new VideoDecoderCallback(this);
        }

        public final MediaExtractor getExtractor() {
            MediaExtractor mediaExtractor = this.extractor;
            if (mediaExtractor != null) {
                return mediaExtractor;
            }
            j.k("extractor");
            throw null;
        }

        public final long getLastTimestampUs() {
            return this.lastTimestampUs;
        }

        public final MediaFormat getMediaFormat() {
            MediaFormat mediaFormat2 = this.mediaFormat;
            if (mediaFormat2 != null) {
                return mediaFormat2;
            }
            j.k("mediaFormat");
            throw null;
        }

        public final a getOnInputReachedEOS() {
            a aVar = this.onInputReachedEOS;
            if (aVar != null) {
                return aVar;
            }
            j.k("onInputReachedEOS");
            throw null;
        }

        public final c getOnOutputReachedEOS() {
            c cVar = this.onOutputReachedEOS;
            if (cVar != null) {
                return cVar;
            }
            j.k("onOutputReachedEOS");
            throw null;
        }

        /* renamed from: setExtractor  reason: collision with other method in class */
        public final void m40setExtractor(MediaExtractor mediaExtractor) {
            j.e(mediaExtractor, "<set-?>");
            this.extractor = mediaExtractor;
        }

        /* renamed from: setLastTimestampUs  reason: collision with other method in class */
        public final void m41setLastTimestampUs(long j2) {
            this.lastTimestampUs = j2;
        }

        /* renamed from: setMediaFormat  reason: collision with other method in class */
        public final void m42setMediaFormat(MediaFormat mediaFormat2) {
            j.e(mediaFormat2, "<set-?>");
            this.mediaFormat = mediaFormat2;
        }

        /* renamed from: setOnInputReachedEOS  reason: collision with other method in class */
        public final void m43setOnInputReachedEOS(a aVar) {
            j.e(aVar, "<set-?>");
            this.onInputReachedEOS = aVar;
        }

        /* renamed from: setOnOutputReachedEOS  reason: collision with other method in class */
        public final void m44setOnOutputReachedEOS(c cVar) {
            j.e(cVar, "<set-?>");
            this.onOutputReachedEOS = cVar;
        }

        public final Builder setExtractor(MediaExtractor mediaExtractor) {
            j.e(mediaExtractor, "extractor");
            setExtractor(mediaExtractor);
            return this;
        }

        public final Builder setLastTimestampUs(long j2) {
            String access$getTAG$cp = VideoDecoderCallback.TAG;
            SLog.i(access$getTAG$cp, "setLastTimestampUs: " + j2);
            this.lastTimestampUs = j2;
            return this;
        }

        public final Builder setMediaFormat(MediaFormat mediaFormat2) {
            j.e(mediaFormat2, "format");
            setMediaFormat(mediaFormat2);
            return this;
        }

        public final Builder setOnInputReachedEOS(a aVar) {
            j.e(aVar, "onInputReachedEOS");
            setOnInputReachedEOS(aVar);
            return this;
        }

        public final Builder setOnOutputReachedEOS(c cVar) {
            j.e(cVar, "onOutputReachedEOS");
            setOnOutputReachedEOS(cVar);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoDecoderCallback$Companion;", "", "<init>", "()V", "TAG", "", "MP_MAX_BITSTREAM_SIZE", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoDecoderCallback.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public VideoDecoderCallback(MediaExtractor mediaExtractor, MediaFormat mediaFormat) {
        j.e(mediaExtractor, "extractor");
        j.e(mediaFormat, "format");
        this.extractor = mediaExtractor;
        this.maxInputSize = Math.min(MP_MAX_BITSTREAM_SIZE, mediaFormat.getInteger("max-input-size"));
    }

    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        j.e(mediaCodec, "codec");
        j.e(codecException, "e");
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    public void onInputBufferAvailable(MediaCodec mediaCodec, int i2) {
        j.e(mediaCodec, "codec");
        if (!this.isInputReachedEOS) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.maxInputSize);
            this.extractor.readSampleData(allocateDirect, 0);
            allocateDirect.rewind();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo.size = allocateDirect.limit();
            bufferInfo.offset = 0;
            bufferInfo.presentationTimeUs = this.extractor.getSampleTime();
            if ((this.extractor.getSampleFlags() & 1) != 0) {
                bufferInfo.flags |= 1;
            }
            if (!this.extractor.advance() || bufferInfo.presentationTimeUs >= this.lastTimestampUs) {
                bufferInfo.flags |= 4;
            }
            if (CommonsKt.containFlag(bufferInfo, 4)) {
                SLog.i(TAG, "decoder input is reached EOS: # of frames " + this.numFramesToDecode);
                mediaCodec.queueInputBuffer(i2, 0, 0, 0, 4);
                this.isInputReachedEOS = true;
                a aVar = this.onInputReachedEOS;
                if (aVar != null) {
                    aVar.invoke();
                }
            } else {
                ByteBuffer inputBuffer = mediaCodec.getInputBuffer(i2);
                j.b(inputBuffer);
                inputBuffer.put(allocateDirect);
                inputBuffer.flip();
                mediaCodec.queueInputBuffer(i2, 0, inputBuffer.limit(), bufferInfo.presentationTimeUs, bufferInfo.flags);
                this.numFramesToDecode++;
            }
            String str = TAG;
            SLog.i(str, "onInputBufferAvailable: numFramesToDecode=" + this.numFramesToDecode + ArcCommonLog.TAG_COMMA + bufferInfo.presentationTimeUs + " us");
            int i7 = this.numFramesToDecode;
            String asString = CommonsKt.asString(bufferInfo);
            StringBuilder h5 = A.a.h(i2, i7, "onInputBufferAvailable: index=", ",\n            | numFramesToDecode=", ",\n            | info=");
            h5.append(asString);
            h5.append("\n        ");
            SLog.d(str, CommonsKt.trimToOneLine(h5.toString()));
        }
    }

    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i2, MediaCodec.BufferInfo bufferInfo) {
        j.e(mediaCodec, "codec");
        j.e(bufferInfo, "info");
        mediaCodec.releaseOutputBuffer(i2, true);
        if (CommonsKt.containFlag(bufferInfo, 4)) {
            String str = TAG;
            String asString = CommonsKt.asString(bufferInfo);
            int i7 = this.numFramesToDecode;
            int i8 = this.numFramesDecoded;
            long j2 = this.lastTimestampUs;
            StringBuilder u = C0212a.u("decoder output is reached EOS: info=", asString, ",\n                    | # of frames ", i7, "/");
            u.append(i8);
            u.append("\n                    | lastTimestampUs=");
            u.append(j2);
            u.append(",\n                ");
            SLog.i(str, CommonsKt.trimToOneLine(u.toString()));
            c cVar = this.onOutputReachedEOS;
            if (cVar != null) {
                cVar.invoke(Integer.valueOf(this.numFramesDecoded), Long.valueOf(this.lastDecodedTimestampUs));
                return;
            }
            return;
        }
        int i10 = this.numFramesDecoded + 1;
        this.numFramesDecoded = i10;
        long j3 = bufferInfo.presentationTimeUs;
        this.lastDecodedTimestampUs = j3;
        String str2 = TAG;
        SLog.i(str2, "onOutputBufferAvailable: numFramesDecoded=" + i10 + ArcCommonLog.TAG_COMMA + j3 + " us");
        int i11 = this.numFramesDecoded;
        String asString2 = CommonsKt.asString(bufferInfo);
        StringBuilder h5 = A.a.h(i2, i11, "onOutputBufferAvailable: index=", ",\n                    | numFramesDecoded=", ",\n                    | info=");
        h5.append(asString2);
        h5.append("\n                ");
        SLog.d(str2, CommonsKt.trimToOneLine(h5.toString()));
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        j.e(mediaCodec, "codec");
        j.e(mediaFormat, "format");
        String str = TAG;
        SLog.i(str, "onOutputFormatChanged: " + mediaFormat);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoDecoderCallback(Builder builder) {
        this(builder.getExtractor(), builder.getMediaFormat());
        j.e(builder, "builder");
        this.lastTimestampUs = builder.getLastTimestampUs();
        this.onOutputReachedEOS = builder.getOnOutputReachedEOS();
        String str = TAG;
        long lastTimestampUs2 = builder.getLastTimestampUs();
        long j2 = this.lastTimestampUs;
        StringBuilder j3 = N2.j.j(lastTimestampUs2, "lastTimestampUs: ", " -> ");
        j3.append(j2);
        SLog.i(str, j3.toString());
    }
}
