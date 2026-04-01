package androidx.media3.transformer;

import F2.N;
import F2.U;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.muxer.MuxerException;
import androidx.media3.transformer.MuxerWrapper;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SampleExporter {
    private final Metadata metadata;
    private final MuxerWrapper muxerWrapper;
    private boolean muxerWrapperTrackAdded;
    private final int outputTrackType;

    public SampleExporter(Format format, MuxerWrapper muxerWrapper2) {
        this.muxerWrapper = muxerWrapper2;
        this.metadata = format.metadata;
        this.outputTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
    }

    private boolean feedMuxer() {
        if (!this.muxerWrapperTrackAdded) {
            Format muxerInputFormat = getMuxerInputFormat();
            if (muxerInputFormat == null) {
                return false;
            }
            if (this.metadata != null) {
                muxerInputFormat = muxerInputFormat.buildUpon().setMetadata(this.metadata).build();
            }
            if (!this.muxerWrapper.supportsSampleMimeType(muxerInputFormat.sampleMimeType)) {
                String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(muxerInputFormat);
                if (this.muxerWrapper.supportsSampleMimeType(alternativeCodecMimeType)) {
                    muxerInputFormat = muxerInputFormat.buildUpon().setSampleMimeType(alternativeCodecMimeType).build();
                }
            }
            try {
                this.muxerWrapper.addTrackFormat(muxerInputFormat);
                this.muxerWrapperTrackAdded = true;
            } catch (MuxerException e) {
                throw ExportException.createForMuxer(e, 7001);
            } catch (MuxerWrapper.AppendTrackFormatException e7) {
                throw ExportException.createForMuxer(e7, 7003);
            }
        }
        if (isMuxerInputEnded()) {
            this.muxerWrapper.endTrack(this.outputTrackType);
            return false;
        }
        DecoderInputBuffer muxerInputBuffer = getMuxerInputBuffer();
        if (muxerInputBuffer == null) {
            return false;
        }
        try {
            if (!this.muxerWrapper.writeSample(this.outputTrackType, (ByteBuffer) Assertions.checkStateNotNull(muxerInputBuffer.data), muxerInputBuffer.isKeyFrame(), muxerInputBuffer.timeUs)) {
                return false;
            }
            releaseMuxerInputBuffer();
            return true;
        } catch (MuxerException e8) {
            throw ExportException.createForMuxer(e8, 7001);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [F2.b0, F2.N] */
    public static String findSupportedMimeTypeForEncoderAndMuxer(Format format, List<String> list) {
        boolean isVideo = MimeTypes.isVideo((String) Assertions.checkNotNull(format.sampleMimeType));
        ? n = new N(4);
        n.f(format.sampleMimeType);
        if (isVideo) {
            n.a("video/hevc");
            n.a(Encode.CodecsMime.VIDEO_CODEC_H264);
        }
        list.getClass();
        n.c(list);
        U p6 = n.g().p();
        for (int i2 = 0; i2 < p6.size(); i2++) {
            String str = (String) p6.get(i2);
            if (list.contains(str)) {
                if (!isVideo || !ColorInfo.isTransferHdr(format.colorInfo)) {
                    if (!EncoderUtil.getSupportedEncoders(str).isEmpty()) {
                    }
                } else if (!EncoderUtil.getSupportedEncodersForHdrEditing(str, format.colorInfo).isEmpty()) {
                }
                return str;
            }
        }
        return null;
    }

    public abstract GraphInput getInput(EditedMediaItem editedMediaItem, Format format, int i2);

    public abstract DecoderInputBuffer getMuxerInputBuffer();

    public abstract Format getMuxerInputFormat();

    public abstract boolean isMuxerInputEnded();

    public final boolean processData() {
        if (feedMuxer()) {
            return true;
        }
        if (isMuxerInputEnded() || !processDataUpToMuxer()) {
            return false;
        }
        return true;
    }

    public boolean processDataUpToMuxer() {
        return false;
    }

    public abstract void release();

    public abstract void releaseMuxerInputBuffer();
}
