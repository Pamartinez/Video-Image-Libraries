package androidx.media3.extractor.ogg;

import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.ogg.StreamReader;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class VorbisReader extends StreamReader {
    private VorbisUtil.CommentHeader commentHeader;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    private VorbisUtil.VorbisIdHeader vorbisIdHeader;
    private VorbisSetup vorbisSetup;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VorbisSetup {
        public final VorbisUtil.CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, VorbisUtil.CommentHeader commentHeader2, byte[] bArr, VorbisUtil.Mode[] modeArr, int i2) {
            this.idHeader = vorbisIdHeader;
            this.commentHeader = commentHeader2;
            this.setupHeaderData = bArr;
            this.modes = modeArr;
            this.iLogModes = i2;
        }
    }

    public static void appendNumberOfSamples(ParsableByteArray parsableByteArray, long j2) {
        if (parsableByteArray.capacity() < parsableByteArray.limit() + 4) {
            parsableByteArray.reset(Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit() + 4));
        } else {
            parsableByteArray.setLimit(parsableByteArray.limit() + 4);
        }
        byte[] data = parsableByteArray.getData();
        data[parsableByteArray.limit() - 4] = (byte) ((int) (j2 & 255));
        data[parsableByteArray.limit() - 3] = (byte) ((int) ((j2 >>> 8) & 255));
        data[parsableByteArray.limit() - 2] = (byte) ((int) ((j2 >>> 16) & 255));
        data[parsableByteArray.limit() - 1] = (byte) ((int) ((j2 >>> 24) & 255));
    }

    private static int decodeBlockSize(byte b, VorbisSetup vorbisSetup2) {
        if (!vorbisSetup2.modes[readBits(b, vorbisSetup2.iLogModes, 1)].blockFlag) {
            return vorbisSetup2.idHeader.blockSize0;
        }
        return vorbisSetup2.idHeader.blockSize1;
    }

    public static int readBits(byte b, int i2, int i7) {
        return (b >> i7) & (ScoverState.TYPE_NFC_SMART_COVER >>> (8 - i2));
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, parsableByteArray, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    public void onSeekEnd(long j2) {
        boolean z;
        super.onSeekEnd(j2);
        int i2 = 0;
        if (j2 != 0) {
            z = true;
        } else {
            z = false;
        }
        this.seenFirstAudioPacket = z;
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = this.vorbisIdHeader;
        if (vorbisIdHeader2 != null) {
            i2 = vorbisIdHeader2.blockSize0;
        }
        this.previousPacketBlockSize = i2;
    }

    public long preparePayload(ParsableByteArray parsableByteArray) {
        int i2 = 0;
        if ((parsableByteArray.getData()[0] & 1) == 1) {
            return -1;
        }
        int decodeBlockSize = decodeBlockSize(parsableByteArray.getData()[0], (VorbisSetup) Assertions.checkStateNotNull(this.vorbisSetup));
        if (this.seenFirstAudioPacket) {
            i2 = (this.previousPacketBlockSize + decodeBlockSize) / 4;
        }
        long j2 = (long) i2;
        appendNumberOfSamples(parsableByteArray, j2);
        this.seenFirstAudioPacket = true;
        this.previousPacketBlockSize = decodeBlockSize;
        return j2;
    }

    public boolean readHeaders(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        if (this.vorbisSetup != null) {
            Assertions.checkNotNull(setupData.format);
            return false;
        }
        VorbisSetup readSetupHeaders = readSetupHeaders(parsableByteArray);
        this.vorbisSetup = readSetupHeaders;
        if (readSetupHeaders == null) {
            return true;
        }
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = readSetupHeaders.idHeader;
        ArrayList arrayList = new ArrayList();
        arrayList.add(vorbisIdHeader2.data);
        arrayList.add(readSetupHeaders.setupHeaderData);
        setupData.format = new Format.Builder().setContainerMimeType("audio/ogg").setSampleMimeType("audio/vorbis").setAverageBitrate(vorbisIdHeader2.bitrateNominal).setPeakBitrate(vorbisIdHeader2.bitrateMaximum).setChannelCount(vorbisIdHeader2.channels).setSampleRate(vorbisIdHeader2.sampleRate).setInitializationData(arrayList).setMetadata(VorbisUtil.parseVorbisComments(U.z(readSetupHeaders.commentHeader.comments))).build();
        return true;
    }

    public VorbisSetup readSetupHeaders(ParsableByteArray parsableByteArray) {
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = this.vorbisIdHeader;
        if (vorbisIdHeader2 == null) {
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(parsableByteArray);
            return null;
        }
        VorbisUtil.CommentHeader commentHeader2 = this.commentHeader;
        if (commentHeader2 == null) {
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(parsableByteArray);
            return null;
        }
        byte[] bArr = new byte[parsableByteArray.limit()];
        System.arraycopy(parsableByteArray.getData(), 0, bArr, 0, parsableByteArray.limit());
        VorbisUtil.Mode[] readVorbisModes = VorbisUtil.readVorbisModes(parsableByteArray, vorbisIdHeader2.channels);
        return new VorbisSetup(vorbisIdHeader2, commentHeader2, bArr, readVorbisModes, VorbisUtil.iLog(readVorbisModes.length - 1));
    }

    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.vorbisSetup = null;
            this.vorbisIdHeader = null;
            this.commentHeader = null;
        }
        this.previousPacketBlockSize = 0;
        this.seenFirstAudioPacket = false;
    }
}
