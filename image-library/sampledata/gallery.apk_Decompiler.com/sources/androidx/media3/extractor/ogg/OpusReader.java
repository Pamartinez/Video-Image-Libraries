package androidx.media3.extractor.ogg;

import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.ogg.StreamReader;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class OpusReader extends StreamReader {
    private static final byte[] OPUS_COMMENT_HEADER_SIGNATURE = {79, 112, 117, 115, 84, 97, 103, 115};
    private static final byte[] OPUS_ID_HEADER_SIGNATURE = {79, 112, 117, 115, 72, 101, 97, 100};
    private boolean firstCommentHeaderSeen;

    private static boolean peekPacketStartsWith(ParsableByteArray parsableByteArray, byte[] bArr) {
        if (parsableByteArray.bytesLeft() < bArr.length) {
            return false;
        }
        int position = parsableByteArray.getPosition();
        byte[] bArr2 = new byte[bArr.length];
        parsableByteArray.readBytes(bArr2, 0, bArr.length);
        parsableByteArray.setPosition(position);
        return Arrays.equals(bArr2, bArr);
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return peekPacketStartsWith(parsableByteArray, OPUS_ID_HEADER_SIGNATURE);
    }

    public long preparePayload(ParsableByteArray parsableByteArray) {
        return convertTimeToGranule(OpusUtil.getPacketDurationUs(parsableByteArray.getData()));
    }

    public boolean readHeaders(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        if (peekPacketStartsWith(parsableByteArray, OPUS_ID_HEADER_SIGNATURE)) {
            byte[] copyOf = Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit());
            int channelCount = OpusUtil.getChannelCount(copyOf);
            List<byte[]> buildInitializationData = OpusUtil.buildInitializationData(copyOf);
            if (setupData.format == null) {
                setupData.format = new Format.Builder().setContainerMimeType("audio/ogg").setSampleMimeType("audio/opus").setChannelCount(channelCount).setSampleRate(48000).setInitializationData(buildInitializationData).build();
                return true;
            }
        } else {
            byte[] bArr = OPUS_COMMENT_HEADER_SIGNATURE;
            if (peekPacketStartsWith(parsableByteArray, bArr)) {
                Assertions.checkStateNotNull(setupData.format);
                if (!this.firstCommentHeaderSeen) {
                    this.firstCommentHeaderSeen = true;
                    parsableByteArray.skipBytes(bArr.length);
                    Metadata parseVorbisComments = VorbisUtil.parseVorbisComments(U.z(VorbisUtil.readVorbisCommentHeader(parsableByteArray, false, false).comments));
                    if (parseVorbisComments != null) {
                        setupData.format = setupData.format.buildUpon().setMetadata(parseVorbisComments.copyWithAppendedEntriesFrom(setupData.format.metadata)).build();
                        return true;
                    }
                }
            } else {
                Assertions.checkStateNotNull(setupData.format);
                return false;
            }
        }
        return true;
    }

    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.firstCommentHeaderSeen = false;
        }
    }
}
