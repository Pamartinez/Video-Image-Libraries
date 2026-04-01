package androidx.media3.extractor.mp4;

import F2.G;
import F2.U;
import F2.y0;
import P.a;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexMerger;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.emsg.EventMessageEncoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.sgpl.media.iso.IsoInterface;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FragmentedMp4Extractor implements Extractor {
    private static final Format EMSG_FORMAT = new Format.Builder().setSampleMimeType("application/x-emsg").build();
    @Deprecated
    public static final ExtractorsFactory FACTORY = new a(6);
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] ceaTrackOutputs;
    private final ChunkIndexMerger chunkIndexMerger;
    private final List<Format> closedCaptionFormats;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final EventMessageEncoder eventMessageEncoder;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private boolean haveOutputSeekMapFromMultipleSidx;
    private boolean isSampleDependedOn;
    private U lastSniffFailures;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private final ParsableByteArray nalUnitWithoutHeaderBuffer;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private final ReorderingBufferQueue reorderingBufferQueue;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private final ParsableByteArray scratch;
    private final byte[] scratchBytes;
    private long seekPositionBeforeSidxProcessing;
    private long segmentIndexEarliestPresentationTimeUs;
    private final Track sideloadedTrack;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MetadataSampleInfo {
        public final boolean sampleTimeIsRelative;
        public final long sampleTimeUs;
        public final int size;

        public MetadataSampleInfo(long j2, boolean z, int i2) {
            this.sampleTimeUs = j2;
            this.sampleTimeIsRelative = z;
            this.size = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrackBundle {
        private final Format baseFormat;
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        /* access modifiers changed from: private */
        public boolean currentlyInFragment;
        private final ParsableByteArray defaultInitializationVector = new ParsableByteArray();
        public DefaultSampleValues defaultSampleValues;
        private final ParsableByteArray encryptionSignalByte = new ParsableByteArray(1);
        public int firstSampleToOutputIndex;
        public final TrackFragment fragment = new TrackFragment();
        public TrackSampleTable moovSampleTable;
        public final TrackOutput output;
        public final ParsableByteArray scratch = new ParsableByteArray();

        public TrackBundle(TrackOutput trackOutput, TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues2, Format format) {
            this.output = trackOutput;
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues2;
            this.baseFormat = format;
            reset(trackSampleTable, defaultSampleValues2);
        }

        public int getCurrentSampleFlags() {
            int i2;
            if (!this.currentlyInFragment) {
                i2 = this.moovSampleTable.flags[this.currentSampleIndex];
            } else if (this.fragment.sampleIsSyncFrameTable[this.currentSampleIndex]) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (getEncryptionBoxIfEncrypted() != null) {
                return 1073741824 | i2;
            }
            return i2;
        }

        public long getCurrentSampleOffset() {
            if (!this.currentlyInFragment) {
                return this.moovSampleTable.offsets[this.currentSampleIndex];
            }
            return this.fragment.trunDataPosition[this.currentTrackRunIndex];
        }

        public long getCurrentSamplePresentationTimeUs() {
            if (!this.currentlyInFragment) {
                return this.moovSampleTable.timestampsUs[this.currentSampleIndex];
            }
            return this.fragment.getSamplePresentationTimeUs(this.currentSampleIndex);
        }

        public int getCurrentSampleSize() {
            if (!this.currentlyInFragment) {
                return this.moovSampleTable.sizes[this.currentSampleIndex];
            }
            return this.fragment.sampleSizeTable[this.currentSampleIndex];
        }

        public TrackEncryptionBox getEncryptionBoxIfEncrypted() {
            if (!this.currentlyInFragment) {
                return null;
            }
            int i2 = ((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex;
            TrackEncryptionBox trackEncryptionBox = this.fragment.trackEncryptionBox;
            if (trackEncryptionBox == null) {
                trackEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(i2);
            }
            if (trackEncryptionBox == null || !trackEncryptionBox.isEncrypted) {
                return null;
            }
            return trackEncryptionBox;
        }

        public boolean next() {
            this.currentSampleIndex++;
            if (!this.currentlyInFragment) {
                return false;
            }
            int i2 = this.currentSampleInTrackRun + 1;
            this.currentSampleInTrackRun = i2;
            int[] iArr = this.fragment.trunLength;
            int i7 = this.currentTrackRunIndex;
            if (i2 != iArr[i7]) {
                return true;
            }
            this.currentTrackRunIndex = i7 + 1;
            this.currentSampleInTrackRun = 0;
            return false;
        }

        public int outputSampleEncryptionData(int i2, int i7) {
            ParsableByteArray parsableByteArray;
            boolean z;
            int i8;
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted == null) {
                return 0;
            }
            int i10 = encryptionBoxIfEncrypted.perSampleIvSize;
            if (i10 != 0) {
                parsableByteArray = this.fragment.sampleEncryptionData;
            } else {
                byte[] bArr = (byte[]) Util.castNonNull(encryptionBoxIfEncrypted.defaultInitializationVector);
                this.defaultInitializationVector.reset(bArr, bArr.length);
                ParsableByteArray parsableByteArray2 = this.defaultInitializationVector;
                i10 = bArr.length;
                parsableByteArray = parsableByteArray2;
            }
            boolean sampleHasSubsampleEncryptionTable = this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex);
            if (sampleHasSubsampleEncryptionTable || i7 != 0) {
                z = true;
            } else {
                z = false;
            }
            byte[] data = this.encryptionSignalByte.getData();
            if (z) {
                i8 = 128;
            } else {
                i8 = 0;
            }
            data[0] = (byte) (i8 | i10);
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1, 1);
            this.output.sampleData(parsableByteArray, i10, 1);
            if (!z) {
                return i10 + 1;
            }
            if (!sampleHasSubsampleEncryptionTable) {
                this.scratch.reset(8);
                byte[] data2 = this.scratch.getData();
                data2[0] = 0;
                data2[1] = 1;
                data2[2] = (byte) ((i7 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                data2[3] = (byte) (i7 & ScoverState.TYPE_NFC_SMART_COVER);
                data2[4] = (byte) ((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
                data2[5] = (byte) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
                data2[6] = (byte) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                data2[7] = (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
                this.output.sampleData(this.scratch, 8, 1);
                return i10 + 9;
            }
            ParsableByteArray parsableByteArray3 = this.fragment.sampleEncryptionData;
            int readUnsignedShort = parsableByteArray3.readUnsignedShort();
            parsableByteArray3.skipBytes(-2);
            int i11 = (readUnsignedShort * 6) + 2;
            if (i7 != 0) {
                this.scratch.reset(i11);
                byte[] data3 = this.scratch.getData();
                parsableByteArray3.readBytes(data3, 0, i11);
                int i12 = (((data3[2] & 255) << 8) | (data3[3] & 255)) + i7;
                data3[2] = (byte) ((i12 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                data3[3] = (byte) (i12 & ScoverState.TYPE_NFC_SMART_COVER);
                parsableByteArray3 = this.scratch;
            }
            this.output.sampleData(parsableByteArray3, i11, 1);
            return i10 + 1 + i11;
        }

        public void reset(TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues2) {
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues2;
            this.output.format(this.baseFormat);
            resetFragmentInfo();
        }

        public void resetFragmentInfo() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
            this.currentlyInFragment = false;
        }

        public void seek(long j2) {
            int i2 = this.currentSampleIndex;
            while (true) {
                TrackFragment trackFragment = this.fragment;
                if (i2 < trackFragment.sampleCount && trackFragment.getSamplePresentationTimeUs(i2) <= j2) {
                    if (this.fragment.sampleIsSyncFrameTable[i2]) {
                        this.firstSampleToOutputIndex = i2;
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }

        public void skipSampleEncryptionData() {
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted != null) {
                ParsableByteArray parsableByteArray = this.fragment.sampleEncryptionData;
                int i2 = encryptionBoxIfEncrypted.perSampleIvSize;
                if (i2 != 0) {
                    parsableByteArray.skipBytes(i2);
                }
                if (this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex)) {
                    parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() * 6);
                }
            }
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            String str;
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex);
            if (sampleDescriptionEncryptionBox != null) {
                str = sampleDescriptionEncryptionBox.schemeType;
            } else {
                str = null;
            }
            this.output.format(this.baseFormat.buildUpon().setDrmInitData(drmInitData.copyWithSchemeType(str)).build());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FragmentedMp4Extractor(SubtitleParser.Factory factory, int i2) {
        this(factory, i2, (TimestampAdjuster) null, (Track) null, y0.f278h, (TrackOutput) null);
        G g = U.e;
    }

    private boolean canReadWithinGopSampleDependencies(Format format) {
        if (Objects.equals(format.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264)) {
            if ((this.flags & 64) != 0) {
                return true;
            }
            return false;
        } else if (!Objects.equals(format.sampleMimeType, "video/hevc") || (this.flags & 128) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static int checkNonNegative(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw ParserException.createForMalformedContainer("Unexpected negative value: " + i2, (Throwable) null);
    }

    public static int codecsToParseWithinGopSampleDependenciesAsFlags(int i2) {
        int i7;
        if ((i2 & 1) != 0) {
            i7 = 64;
        } else {
            i7 = 0;
        }
        if ((i2 & 2) != 0) {
            return i7 | 128;
        }
        return i7;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray<DefaultSampleValues> sparseArray, int i2) {
        if (sparseArray.size() == 1) {
            return sparseArray.valueAt(0);
        }
        return (DefaultSampleValues) Assertions.checkNotNull(sparseArray.get(i2));
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<Mp4Box.LeafBox> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            Mp4Box.LeafBox leafBox = list.get(i2);
            if (leafBox.type == 1886614376) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] data = leafBox.data.getData();
                UUID parseUuid = PsshAtomUtil.parseUuid(data);
                if (parseUuid == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(parseUuid, Encode.ContentType.VIDEO_MP4, data));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData((List<DrmInitData.SchemeData>) arrayList);
    }

    private static TrackBundle getNextTrackBundle(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j2 = Long.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            TrackBundle valueAt = sparseArray.valueAt(i2);
            if ((valueAt.currentlyInFragment || valueAt.currentSampleIndex != valueAt.moovSampleTable.sampleCount) && (!valueAt.currentlyInFragment || valueAt.currentTrackRunIndex != valueAt.fragment.trunCount)) {
                long currentSampleOffset = valueAt.getCurrentSampleOffset();
                if (currentSampleOffset < j2) {
                    trackBundle = valueAt;
                    j2 = currentSampleOffset;
                }
            }
        }
        return trackBundle;
    }

    private void initExtraTracks() {
        int i2;
        TrackOutput[] trackOutputArr = new TrackOutput[2];
        this.emsgTrackOutputs = trackOutputArr;
        TrackOutput trackOutput = this.additionalEmsgTrackOutput;
        int i7 = 0;
        if (trackOutput != null) {
            trackOutputArr[0] = trackOutput;
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = 100;
        if ((this.flags & 4) != 0) {
            trackOutputArr[i2] = this.extractorOutput.track(100, 5);
            i8 = 101;
            i2++;
        }
        TrackOutput[] trackOutputArr2 = (TrackOutput[]) Util.nullSafeArrayCopy(this.emsgTrackOutputs, i2);
        this.emsgTrackOutputs = trackOutputArr2;
        for (TrackOutput format : trackOutputArr2) {
            format.format(EMSG_FORMAT);
        }
        this.ceaTrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
        while (i7 < this.ceaTrackOutputs.length) {
            TrackOutput track = this.extractorOutput.track(i8, 3);
            track.format(this.closedCaptionFormats.get(i7));
            this.ceaTrackOutputs[i7] = track;
            i7++;
            i8++;
        }
    }

    private static boolean isEdtsListDurationForEntireMediaTimeline(Track track) {
        long[] jArr = track.editListDurations;
        if (!(jArr == null || jArr.length != 1 || track.editListMediaTimes == null)) {
            long j2 = jArr[0];
            if (j2 != 0 && Util.scaleLargeTimestamp(j2, 1000000, track.movieTimescale) + Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000000, track.timescale) < track.durationUs) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(long j2, ParsableByteArray parsableByteArray) {
        CeaUtil.consume(j2, parsableByteArray, this.ceaTrackOutputs);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new FragmentedMp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 32)};
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput extractorInput) {
        this.scratch.reset(8);
        extractorInput.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        extractorInput.skipFully(this.scratch.getPosition());
        extractorInput.resetPeekPosition();
    }

    private void onContainerAtomRead(Mp4Box.ContainerBox containerBox) {
        int i2 = containerBox.type;
        if (i2 == 1836019574) {
            onMoovContainerAtomRead(containerBox);
        } else if (i2 == 1836019558) {
            onMoofContainerAtomRead(containerBox);
        } else if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(containerBox);
        }
    }

    private void onEmsgLeafAtomRead(ParsableByteArray parsableByteArray) {
        String str;
        String str2;
        long scaleLargeTimestamp;
        long j2;
        long scaleLargeTimestamp2;
        long readUnsignedInt;
        long j3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        if (this.emsgTrackOutputs.length != 0) {
            parsableByteArray2.setPosition(8);
            int parseFullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray2.readInt());
            if (parseFullBoxVersion == 0) {
                str = (String) Assertions.checkNotNull(parsableByteArray2.readNullTerminatedString());
                str2 = (String) Assertions.checkNotNull(parsableByteArray2.readNullTerminatedString());
                long readUnsignedInt2 = parsableByteArray2.readUnsignedInt();
                scaleLargeTimestamp = Util.scaleLargeTimestamp(parsableByteArray2.readUnsignedInt(), 1000000, readUnsignedInt2);
                long j8 = this.segmentIndexEarliestPresentationTimeUs;
                if (j8 != -9223372036854775807L) {
                    j2 = j8 + scaleLargeTimestamp;
                } else {
                    j2 = -9223372036854775807L;
                }
                scaleLargeTimestamp2 = Util.scaleLargeTimestamp(parsableByteArray2.readUnsignedInt(), 1000, readUnsignedInt2);
                readUnsignedInt = parsableByteArray2.readUnsignedInt();
                j3 = j2;
            } else if (parseFullBoxVersion != 1) {
                A.a.D(parseFullBoxVersion, "Skipping unsupported emsg version: ", "FragmentedMp4Extractor");
                return;
            } else {
                long readUnsignedInt3 = parsableByteArray2.readUnsignedInt();
                j3 = Util.scaleLargeTimestamp(parsableByteArray2.readUnsignedLongToLong(), 1000000, readUnsignedInt3);
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(parsableByteArray2.readUnsignedInt(), 1000, readUnsignedInt3);
                long readUnsignedInt4 = parsableByteArray2.readUnsignedInt();
                str = (String) Assertions.checkNotNull(parsableByteArray2.readNullTerminatedString());
                str2 = (String) Assertions.checkNotNull(parsableByteArray2.readNullTerminatedString());
                scaleLargeTimestamp2 = scaleLargeTimestamp3;
                readUnsignedInt = readUnsignedInt4;
                scaleLargeTimestamp = -9223372036854775807L;
            }
            String str3 = str;
            String str4 = str2;
            byte[] bArr = new byte[parsableByteArray2.bytesLeft()];
            parsableByteArray2.readBytes(bArr, 0, parsableByteArray2.bytesLeft());
            ParsableByteArray parsableByteArray3 = new ParsableByteArray(this.eventMessageEncoder.encode(new EventMessage(str3, str4, scaleLargeTimestamp2, readUnsignedInt, bArr)));
            int bytesLeft = parsableByteArray3.bytesLeft();
            for (TrackOutput sampleData : this.emsgTrackOutputs) {
                parsableByteArray3.setPosition(0);
                sampleData.sampleData(parsableByteArray3, bytesLeft);
            }
            if (j3 == -9223372036854775807L) {
                this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(scaleLargeTimestamp, true, bytesLeft));
                this.pendingMetadataSampleBytes += bytesLeft;
            } else if (!this.pendingMetadataSampleInfos.isEmpty()) {
                this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(j3, false, bytesLeft));
                this.pendingMetadataSampleBytes += bytesLeft;
            } else {
                TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
                if (timestampAdjuster2 == null || timestampAdjuster2.isInitialized()) {
                    TimestampAdjuster timestampAdjuster3 = this.timestampAdjuster;
                    if (timestampAdjuster3 != null) {
                        j3 = timestampAdjuster3.adjustSampleTimestamp(j3);
                    }
                    long j10 = j3;
                    for (TrackOutput sampleMetadata : this.emsgTrackOutputs) {
                        sampleMetadata.sampleMetadata(j10, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
                    }
                    return;
                }
                this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(j3, false, bytesLeft));
                this.pendingMetadataSampleBytes += bytesLeft;
            }
        }
    }

    private void onLeafAtomRead(Mp4Box.LeafBox leafBox, ExtractorInput extractorInput) {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafBox);
            return;
        }
        int i2 = leafBox.type;
        if (i2 == 1936286840) {
            Pair<Long, ChunkIndex> parseSidx = parseSidx(leafBox.data, extractorInput.getPosition());
            this.chunkIndexMerger.add((ChunkIndex) parseSidx.second);
            if (!this.haveOutputSeekMap) {
                this.segmentIndexEarliestPresentationTimeUs = ((Long) parseSidx.first).longValue();
                this.extractorOutput.seekMap((SeekMap) parseSidx.second);
                this.haveOutputSeekMap = true;
            } else if ((this.flags & 256) != 0 && !this.haveOutputSeekMapFromMultipleSidx && this.chunkIndexMerger.size() > 1) {
                this.seekPositionBeforeSidxProcessing = extractorInput.getPosition();
            }
        } else if (i2 == 1701671783) {
            onEmsgLeafAtomRead(leafBox.data);
        }
    }

    private void onMoofContainerAtomRead(Mp4Box.ContainerBox containerBox) {
        boolean z;
        SparseArray<TrackBundle> sparseArray = this.trackBundles;
        if (this.sideloadedTrack != null) {
            z = true;
        } else {
            z = false;
        }
        parseMoof(containerBox, sparseArray, z, this.flags, this.scratchBytes);
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerBox.leafChildren);
        if (drmInitDataFromAtoms != null) {
            int size = this.trackBundles.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.trackBundles.valueAt(i2).updateDrmInitData(drmInitDataFromAtoms);
            }
        }
        if (this.pendingSeekTimeUs != -9223372036854775807L) {
            int size2 = this.trackBundles.size();
            for (int i7 = 0; i7 < size2; i7++) {
                this.trackBundles.valueAt(i7).seek(this.pendingSeekTimeUs);
            }
            this.pendingSeekTimeUs = -9223372036854775807L;
        }
    }

    private void onMoovContainerAtomRead(Mp4Box.ContainerBox containerBox) {
        boolean z;
        Metadata metadata;
        boolean z3;
        long j2;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        int i2 = 0;
        boolean z7 = true;
        if (this.sideloadedTrack == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z, "Unexpected moov box.");
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerBox2.leafChildren);
        Mp4Box.ContainerBox containerBox3 = (Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(1836475768));
        SparseArray sparseArray = new SparseArray();
        int size = containerBox3.leafChildren.size();
        long j3 = -9223372036854775807L;
        for (int i7 = 0; i7 < size; i7++) {
            Mp4Box.LeafBox leafBox = containerBox3.leafChildren.get(i7);
            int i8 = leafBox.type;
            if (i8 == 1953654136) {
                Pair<Integer, DefaultSampleValues> parseTrex = parseTrex(leafBox.data);
                sparseArray.put(((Integer) parseTrex.first).intValue(), (DefaultSampleValues) parseTrex.second);
            } else if (i8 == 1835362404) {
                j3 = parseMehd(leafBox.data);
            }
        }
        Mp4Box.ContainerBox containerBoxOfType = containerBox2.getContainerBoxOfType(IsoInterface.BOX_META);
        Metadata metadata2 = null;
        if (containerBoxOfType != null) {
            metadata = BoxParser.parseMdtaFromMeta(containerBoxOfType);
        } else {
            metadata = null;
        }
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Mp4Box.LeafBox leafBoxOfType = containerBox2.getLeafBoxOfType(IsoInterface.BOX_UDTA);
        if (leafBoxOfType != null) {
            metadata2 = BoxParser.parseUdta(leafBoxOfType);
            gaplessInfoHolder.setFromMetadata(metadata2);
        }
        Metadata metadata3 = metadata2;
        Metadata metadata4 = new Metadata(BoxParser.parseMvhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(IsoInterface.BOX_MVHD))).data));
        if ((this.flags & 16) != 0) {
            j2 = j3;
            z3 = true;
        } else {
            j2 = j3;
            z3 = false;
        }
        List<TrackSampleTable> parseTraks = BoxParser.parseTraks(containerBox2, gaplessInfoHolder, j2, drmInitDataFromAtoms, z3, false, new X.a(this));
        int size2 = parseTraks.size();
        if (this.trackBundles.size() == 0) {
            String containerMimeType = MimeTypeResolver.getContainerMimeType(parseTraks);
            while (i2 < size2) {
                TrackSampleTable trackSampleTable = parseTraks.get(i2);
                Track track = trackSampleTable.track;
                TrackOutput track2 = this.extractorOutput.track(i2, track.type);
                track2.durationUs(track.durationUs);
                Format.Builder buildUpon = track.format.buildUpon();
                buildUpon.setContainerMimeType(containerMimeType);
                MetadataUtil.setFormatGaplessInfo(track.type, gaplessInfoHolder, buildUpon);
                MetadataUtil.setFormatMetadata(track.type, metadata, buildUpon, track.format.metadata, metadata3, metadata4);
                this.trackBundles.put(track.id, new TrackBundle(track2, trackSampleTable, getDefaultSampleValues(sparseArray, track.id), buildUpon.build()));
                this.durationUs = Math.max(this.durationUs, track.durationUs);
                i2++;
                gaplessInfoHolder = gaplessInfoHolder;
            }
            this.extractorOutput.endTracks();
            return;
        }
        if (this.trackBundles.size() != size2) {
            z7 = false;
        }
        Assertions.checkState(z7);
        while (i2 < size2) {
            TrackSampleTable trackSampleTable2 = parseTraks.get(i2);
            Track track3 = trackSampleTable2.track;
            this.trackBundles.get(track3.id).reset(trackSampleTable2, getDefaultSampleValues(sparseArray, track3.id));
            i2++;
        }
    }

    private void outputPendingMetadataSamples(long j2) {
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo removeFirst = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= removeFirst.size;
            long j3 = removeFirst.sampleTimeUs;
            if (removeFirst.sampleTimeIsRelative) {
                j3 += j2;
            }
            TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
            if (timestampAdjuster2 != null) {
                j3 = timestampAdjuster2.adjustSampleTimestamp(j3);
            }
            long j8 = j3;
            for (TrackOutput sampleMetadata : this.emsgTrackOutputs) {
                sampleMetadata.sampleMetadata(j8, 1, removeFirst.size, this.pendingMetadataSampleBytes, (TrackOutput.CryptoData) null);
            }
        }
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (BoxParser.parseFullBoxVersion(parsableByteArray.readInt()) == 0) {
            return parsableByteArray.readUnsignedInt();
        }
        return parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Mp4Box.ContainerBox containerBox, SparseArray<TrackBundle> sparseArray, boolean z, int i2, byte[] bArr) {
        int size = containerBox.containerChildren.size();
        for (int i7 = 0; i7 < size; i7++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i7);
            if (containerBox2.type == 1953653094) {
                parseTraf(containerBox2, sparseArray, z, i2, bArr);
            }
        }
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) {
        long j2;
        parsableByteArray.setPosition(8);
        int readInt = parsableByteArray.readInt();
        if ((BoxParser.parseFullBoxFlags(readInt) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt == 1) {
            int parseFullBoxVersion = BoxParser.parseFullBoxVersion(readInt);
            long j3 = trackFragment.auxiliaryDataPosition;
            if (parseFullBoxVersion == 0) {
                j2 = parsableByteArray.readUnsignedInt();
            } else {
                j2 = parsableByteArray.readUnsignedLongToLong();
            }
            trackFragment.auxiliaryDataPosition = j3 + j2;
            return;
        }
        throw ParserException.createForMalformedContainer("Unexpected saio entry count: " + readUnsignedIntToInt, (Throwable) null);
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) {
        int i2;
        boolean z;
        int i7 = trackEncryptionBox.perSampleIvSize;
        parsableByteArray.setPosition(8);
        boolean z3 = true;
        if ((BoxParser.parseFullBoxFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt <= trackFragment.sampleCount) {
            if (readUnsignedByte == 0) {
                boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
                i2 = 0;
                for (int i8 = 0; i8 < readUnsignedIntToInt; i8++) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    i2 += readUnsignedByte2;
                    if (readUnsignedByte2 > i7) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zArr[i8] = z;
                }
            } else {
                if (readUnsignedByte <= i7) {
                    z3 = false;
                }
                i2 = readUnsignedByte * readUnsignedIntToInt;
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z3);
            }
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, readUnsignedIntToInt, trackFragment.sampleCount, false);
            if (i2 > 0) {
                trackFragment.initEncryptionData(i2);
                return;
            }
            return;
        }
        StringBuilder o2 = C0086a.o(readUnsignedIntToInt, "Saiz sample count ", " is greater than fragment sample count");
        o2.append(trackFragment.sampleCount);
        throw ParserException.createForMalformedContainer(o2.toString(), (Throwable) null);
    }

    private static void parseSampleGroups(Mp4Box.ContainerBox containerBox, String str, TrackFragment trackFragment) {
        boolean z;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        TrackFragment trackFragment2 = trackFragment;
        byte[] bArr = null;
        ParsableByteArray parsableByteArray = null;
        ParsableByteArray parsableByteArray2 = null;
        for (int i2 = 0; i2 < containerBox2.leafChildren.size(); i2++) {
            Mp4Box.LeafBox leafBox = containerBox2.leafChildren.get(i2);
            ParsableByteArray parsableByteArray3 = leafBox.data;
            int i7 = leafBox.type;
            if (i7 == 1935828848) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == 1936025959) {
                    parsableByteArray = parsableByteArray3;
                }
            } else if (i7 == 1936158820) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == 1936025959) {
                    parsableByteArray2 = parsableByteArray3;
                }
            }
        }
        if (parsableByteArray != null && parsableByteArray2 != null) {
            parsableByteArray.setPosition(8);
            int parseFullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray.readInt());
            parsableByteArray.skipBytes(4);
            if (parseFullBoxVersion == 1) {
                parsableByteArray.skipBytes(4);
            }
            if (parsableByteArray.readInt() == 1) {
                parsableByteArray2.setPosition(8);
                int parseFullBoxVersion2 = BoxParser.parseFullBoxVersion(parsableByteArray2.readInt());
                parsableByteArray2.skipBytes(4);
                if (parseFullBoxVersion2 == 1) {
                    if (parsableByteArray2.readUnsignedInt() == 0) {
                        throw ParserException.createForUnsupportedContainerFeature("Variable length description in sgpd found (unsupported)");
                    }
                } else if (parseFullBoxVersion2 >= 2) {
                    parsableByteArray2.skipBytes(4);
                }
                if (parsableByteArray2.readUnsignedInt() == 1) {
                    parsableByteArray2.skipBytes(1);
                    int readUnsignedByte = parsableByteArray2.readUnsignedByte();
                    int i8 = (readUnsignedByte & 240) >> 4;
                    int i10 = readUnsignedByte & 15;
                    if (parsableByteArray2.readUnsignedByte() == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
                        byte[] bArr2 = new byte[16];
                        parsableByteArray2.readBytes(bArr2, 0, 16);
                        if (readUnsignedByte2 == 0) {
                            int readUnsignedByte3 = parsableByteArray2.readUnsignedByte();
                            bArr = new byte[readUnsignedByte3];
                            parsableByteArray2.readBytes(bArr, 0, readUnsignedByte3);
                        }
                        trackFragment2.definesEncryptionData = true;
                        trackFragment2.trackEncryptionBox = new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i8, i10, bArr);
                        return;
                    }
                    return;
                }
                throw ParserException.createForUnsupportedContainerFeature("Entry count in sgpd != 1 (unsupported).");
            }
            throw ParserException.createForUnsupportedContainerFeature("Entry count in sbgp != 1 (unsupported).");
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j2) {
        long readUnsignedLongToLong;
        long readUnsignedLongToLong2;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(8);
        int parseFullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray2.readInt());
        parsableByteArray2.skipBytes(4);
        long readUnsignedInt = parsableByteArray2.readUnsignedInt();
        if (parseFullBoxVersion == 0) {
            readUnsignedLongToLong = parsableByteArray2.readUnsignedInt();
            readUnsignedLongToLong2 = parsableByteArray2.readUnsignedInt();
        } else {
            readUnsignedLongToLong = parsableByteArray2.readUnsignedLongToLong();
            readUnsignedLongToLong2 = parsableByteArray2.readUnsignedLongToLong();
        }
        long j3 = readUnsignedLongToLong2 + j2;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(readUnsignedLongToLong, 1000000, readUnsignedInt);
        parsableByteArray2.skipBytes(2);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        long[] jArr3 = new long[readUnsignedShort];
        long j8 = j3;
        long j10 = scaleLargeTimestamp;
        int i2 = 0;
        while (i2 < readUnsignedShort) {
            int readInt = parsableByteArray2.readInt();
            if ((Integer.MIN_VALUE & readInt) == 0) {
                long readUnsignedInt2 = parsableByteArray2.readUnsignedInt();
                iArr[i2] = readInt & Integer.MAX_VALUE;
                jArr[i2] = j8;
                jArr3[i2] = j10;
                readUnsignedLongToLong += readUnsignedInt2;
                long[] jArr4 = jArr3;
                j10 = Util.scaleLargeTimestamp(readUnsignedLongToLong, 1000000, readUnsignedInt);
                jArr2[i2] = j10 - jArr4[i2];
                parsableByteArray2.skipBytes(4);
                j8 += (long) iArr[i2];
                i2++;
                jArr3 = jArr4;
            } else {
                throw ParserException.createForMalformedContainer("Unhandled indirect reference", (Throwable) null);
            }
        }
        return Pair.create(Long.valueOf(scaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (BoxParser.parseFullBoxVersion(parsableByteArray.readInt()) == 1) {
            return parsableByteArray.readUnsignedLongToLong();
        }
        return parsableByteArray.readUnsignedInt();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.util.SparseArray, android.util.SparseArray<androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.mp4.FragmentedMp4Extractor.TrackBundle parseTfhd(androidx.media3.common.util.ParsableByteArray r4, android.util.SparseArray<androidx.media3.extractor.mp4.FragmentedMp4Extractor.TrackBundle> r5, boolean r6) {
        /*
            r0 = 8
            r4.setPosition(r0)
            int r0 = r4.readInt()
            int r0 = androidx.media3.extractor.mp4.BoxParser.parseFullBoxFlags(r0)
            int r1 = r4.readInt()
            if (r6 == 0) goto L_0x001b
            r6 = 0
            java.lang.Object r5 = r5.valueAt(r6)
        L_0x0018:
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle r5 = (androidx.media3.extractor.mp4.FragmentedMp4Extractor.TrackBundle) r5
            goto L_0x0020
        L_0x001b:
            java.lang.Object r5 = r5.get(r1)
            goto L_0x0018
        L_0x0020:
            if (r5 != 0) goto L_0x0024
            r4 = 0
            return r4
        L_0x0024:
            r6 = r0 & 1
            if (r6 == 0) goto L_0x0032
            long r1 = r4.readUnsignedLongToLong()
            androidx.media3.extractor.mp4.TrackFragment r6 = r5.fragment
            r6.dataPosition = r1
            r6.auxiliaryDataPosition = r1
        L_0x0032:
            androidx.media3.extractor.mp4.DefaultSampleValues r6 = r5.defaultSampleValues
            r1 = r0 & 2
            if (r1 == 0) goto L_0x003f
            int r1 = r4.readInt()
            int r1 = r1 + -1
            goto L_0x0041
        L_0x003f:
            int r1 = r6.sampleDescriptionIndex
        L_0x0041:
            r2 = r0 & 8
            if (r2 == 0) goto L_0x004a
            int r2 = r4.readInt()
            goto L_0x004c
        L_0x004a:
            int r2 = r6.duration
        L_0x004c:
            r3 = r0 & 16
            if (r3 == 0) goto L_0x0055
            int r3 = r4.readInt()
            goto L_0x0057
        L_0x0055:
            int r3 = r6.size
        L_0x0057:
            r0 = r0 & 32
            if (r0 == 0) goto L_0x0060
            int r4 = r4.readInt()
            goto L_0x0062
        L_0x0060:
            int r4 = r6.flags
        L_0x0062:
            androidx.media3.extractor.mp4.TrackFragment r6 = r5.fragment
            androidx.media3.extractor.mp4.DefaultSampleValues r0 = new androidx.media3.extractor.mp4.DefaultSampleValues
            r0.<init>(r1, r2, r3, r4)
            r6.header = r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.FragmentedMp4Extractor.parseTfhd(androidx.media3.common.util.ParsableByteArray, android.util.SparseArray, boolean):androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle");
    }

    private static void parseTraf(Mp4Box.ContainerBox containerBox, SparseArray<TrackBundle> sparseArray, boolean z, int i2, byte[] bArr) {
        String str;
        TrackBundle parseTfhd = parseTfhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(1952868452))).data, sparseArray, z);
        if (parseTfhd != null) {
            TrackFragment trackFragment = parseTfhd.fragment;
            long j2 = trackFragment.nextFragmentDecodeTime;
            boolean z3 = trackFragment.nextFragmentDecodeTimeIncludesMoov;
            parseTfhd.resetFragmentInfo();
            boolean unused = parseTfhd.currentlyInFragment = true;
            Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(1952867444);
            if (leafBoxOfType == null || (i2 & 2) != 0) {
                trackFragment.nextFragmentDecodeTime = j2;
                trackFragment.nextFragmentDecodeTimeIncludesMoov = z3;
            } else {
                trackFragment.nextFragmentDecodeTime = parseTfdt(leafBoxOfType.data);
                trackFragment.nextFragmentDecodeTimeIncludesMoov = true;
            }
            parseTruns(containerBox, parseTfhd, i2);
            TrackEncryptionBox sampleDescriptionEncryptionBox = parseTfhd.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Assertions.checkNotNull(trackFragment.header)).sampleDescriptionIndex);
            Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(1935763834);
            if (leafBoxOfType2 != null) {
                parseSaiz((TrackEncryptionBox) Assertions.checkNotNull(sampleDescriptionEncryptionBox), leafBoxOfType2.data, trackFragment);
            }
            Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(1935763823);
            if (leafBoxOfType3 != null) {
                parseSaio(leafBoxOfType3.data, trackFragment);
            }
            Mp4Box.LeafBox leafBoxOfType4 = containerBox.getLeafBoxOfType(1936027235);
            if (leafBoxOfType4 != null) {
                parseSenc(leafBoxOfType4.data, trackFragment);
            }
            if (sampleDescriptionEncryptionBox != null) {
                str = sampleDescriptionEncryptionBox.schemeType;
            } else {
                str = null;
            }
            parseSampleGroups(containerBox, str, trackFragment);
            int size = containerBox.leafChildren.size();
            for (int i7 = 0; i7 < size; i7++) {
                Mp4Box.LeafBox leafBox = containerBox.leafChildren.get(i7);
                if (leafBox.type == 1970628964) {
                    parseUuid(leafBox.data, trackFragment, bArr);
                }
            }
        }
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readInt() - 1, parsableByteArray.readInt(), parsableByteArray.readInt(), parsableByteArray.readInt()));
    }

    private static int parseTrun(TrackBundle trackBundle, int i2, int i7, ParsableByteArray parsableByteArray, int i8) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        long j2;
        boolean z11;
        boolean z12;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z13;
        TrackBundle trackBundle2 = trackBundle;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(8);
        int parseFullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray2.readInt());
        Track track = trackBundle2.moovSampleTable.track;
        TrackFragment trackFragment = trackBundle2.fragment;
        DefaultSampleValues defaultSampleValues = (DefaultSampleValues) Util.castNonNull(trackFragment.header);
        trackFragment.trunLength[i2] = parsableByteArray2.readUnsignedIntToInt();
        long[] jArr = trackFragment.trunDataPosition;
        long j3 = trackFragment.dataPosition;
        jArr[i2] = j3;
        if ((parseFullBoxFlags & 1) != 0) {
            jArr[i2] = j3 + ((long) parsableByteArray2.readInt());
        }
        if ((parseFullBoxFlags & 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        int i17 = defaultSampleValues.flags;
        if (z) {
            i17 = parsableByteArray2.readInt();
        }
        if ((parseFullBoxFlags & 256) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if ((parseFullBoxFlags & 512) != 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        if ((parseFullBoxFlags & 1024) != 0) {
            z9 = true;
        } else {
            z9 = false;
        }
        if ((parseFullBoxFlags & 2048) != 0) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (isEdtsListDurationForEntireMediaTimeline(track)) {
            j2 = ((long[]) Util.castNonNull(track.editListMediaTimes))[0];
        } else {
            j2 = 0;
        }
        int[] iArr = trackFragment.sampleSizeTable;
        long[] jArr2 = trackFragment.samplePresentationTimesUs;
        boolean[] zArr = trackFragment.sampleIsSyncFrameTable;
        boolean z14 = z10;
        if (track.type != 2 || (i7 & 1) == 0) {
            z11 = false;
        } else {
            z11 = true;
        }
        int i18 = i8 + trackFragment.trunLength[i2];
        boolean z15 = z;
        long[] jArr3 = jArr2;
        long j8 = track.timescale;
        long j10 = trackFragment.nextFragmentDecodeTime;
        int i19 = i8;
        while (i19 < i18) {
            if (z3) {
                i10 = parsableByteArray.readInt();
                z12 = z11;
            } else {
                z12 = z11;
                i10 = defaultSampleValues.duration;
            }
            int checkNonNegative = checkNonNegative(i10);
            if (z7) {
                i12 = parsableByteArray.readInt();
                i11 = i18;
            } else {
                i11 = i18;
                i12 = defaultSampleValues.size;
            }
            int checkNonNegative2 = checkNonNegative(i12);
            if (z9) {
                i13 = checkNonNegative2;
                i14 = parsableByteArray.readInt();
            } else if (i19 != 0 || !z15) {
                i13 = checkNonNegative2;
                i14 = defaultSampleValues.flags;
            } else {
                i13 = checkNonNegative2;
                i14 = i17;
            }
            if (z14) {
                i15 = i14;
                i16 = parsableByteArray.readInt();
            } else {
                i15 = i14;
                i16 = 0;
            }
            int i20 = i19;
            long scaleLargeTimestamp = Util.scaleLargeTimestamp((((long) i16) + j10) - j2, 1000000, j8);
            jArr3[i20] = scaleLargeTimestamp;
            long j11 = scaleLargeTimestamp;
            if (!trackFragment.nextFragmentDecodeTimeIncludesMoov) {
                jArr3[i20] = j11 + trackBundle2.moovSampleTable.durationUs;
            }
            iArr[i20] = i13;
            if (((i15 >> 16) & 1) != 0 || (z12 && i20 != 0)) {
                z13 = false;
            } else {
                z13 = true;
            }
            zArr[i20] = z13;
            j10 += (long) checkNonNegative;
            i19 = i20 + 1;
            i18 = i11;
            z11 = z12;
        }
        int i21 = i18;
        trackFragment.nextFragmentDecodeTime = j10;
        return i21;
    }

    private static void parseTruns(Mp4Box.ContainerBox containerBox, TrackBundle trackBundle, int i2) {
        List<Mp4Box.LeafBox> list = containerBox.leafChildren;
        int size = list.size();
        int i7 = 0;
        int i8 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            Mp4Box.LeafBox leafBox = list.get(i10);
            if (leafBox.type == 1953658222) {
                ParsableByteArray parsableByteArray = leafBox.data;
                parsableByteArray.setPosition(12);
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (readUnsignedIntToInt > 0) {
                    i8 += readUnsignedIntToInt;
                    i7++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i7, i8);
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < size; i13++) {
            Mp4Box.LeafBox leafBox2 = list.get(i13);
            if (leafBox2.type == 1953658222) {
                i12 = parseTrun(trackBundle, i11, i2, leafBox2.data, i12);
                i11++;
            }
        }
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private void processAtomEnded(long j2) {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j2) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j2 = this.atomSize;
        if (j2 == 1) {
            extractorInput.readFully(this.atomHeader.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j2 == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = this.containerAtoms.peek().endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        long j3 = this.atomSize;
        int i2 = this.atomHeaderBytesRead;
        if (j3 < ((long) i2)) {
            throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
        } else if (this.seekPositionBeforeSidxProcessing != -1) {
            if (this.atomType == 1936286840) {
                this.scratch.reset((int) j3);
                System.arraycopy(this.atomHeader.getData(), 0, this.scratch.getData(), 0, 8);
                extractorInput.readFully(this.scratch.getData(), 8, (int) (this.atomSize - ((long) this.atomHeaderBytesRead)));
                this.chunkIndexMerger.add((ChunkIndex) parseSidx(new Mp4Box.LeafBox(1936286840, this.scratch).data, extractorInput.getPeekPosition()).second);
            } else {
                extractorInput.skipFully((int) (j3 - ((long) i2)), true);
            }
            enterReadingAtomHeaderState();
            return true;
        } else {
            long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
            int i7 = this.atomType;
            if ((i7 == 1836019558 || i7 == 1835295092) && !this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs, position));
                this.haveOutputSeekMap = true;
            }
            if (this.atomType == 1836019558) {
                int size = this.trackBundles.size();
                for (int i8 = 0; i8 < size; i8++) {
                    TrackFragment trackFragment = this.trackBundles.valueAt(i8).fragment;
                    trackFragment.atomPosition = position;
                    trackFragment.auxiliaryDataPosition = position;
                    trackFragment.dataPosition = position;
                }
            }
            int i10 = this.atomType;
            if (i10 == 1835295092) {
                this.currentTrackBundle = null;
                this.endOfMdatPosition = position + this.atomSize;
                this.parserState = 2;
                return true;
            }
            if (shouldParseContainerAtom(i10)) {
                long position2 = extractorInput.getPosition();
                long j8 = this.atomSize;
                long j10 = (position2 + j8) - 8;
                if (j8 != ((long) this.atomHeaderBytesRead) && this.atomType == 1835365473) {
                    maybeSkipRemainingMetaAtomHeaderBytes(extractorInput);
                }
                this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, j10));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(j10);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                if (this.atomHeaderBytesRead != 8) {
                    throw ParserException.createForUnsupportedContainerFeature("Leaf atom defines extended atom size (unsupported).");
                } else if (this.atomSize <= 2147483647L) {
                    ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
                    System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
                    this.atomData = parsableByteArray;
                    this.parserState = 1;
                } else {
                    throw ParserException.createForUnsupportedContainerFeature("Leaf atom with length > 2147483647 (unsupported).");
                }
            } else if (this.atomSize <= 2147483647L) {
                this.atomData = null;
                this.parserState = 1;
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Skipping atom with length > 2147483647 (unsupported).");
            }
            return true;
        }
    }

    private void readAtomPayload(ExtractorInput extractorInput) {
        int i2 = (int) (this.atomSize - ((long) this.atomHeaderBytesRead));
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.getData(), 8, i2);
            onLeafAtomRead(new Mp4Box.LeafBox(this.atomType, parsableByteArray), extractorInput);
        } else {
            extractorInput.skipFully(i2);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void readEncryptionData(ExtractorInput extractorInput) {
        int size = this.trackBundles.size();
        long j2 = Long.MAX_VALUE;
        TrackBundle trackBundle = null;
        for (int i2 = 0; i2 < size; i2++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i2).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill) {
                long j3 = trackFragment.auxiliaryDataPosition;
                if (j3 < j2) {
                    trackBundle = this.trackBundles.valueAt(i2);
                    j2 = j3;
                }
            }
        }
        if (trackBundle == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j2 - extractorInput.getPosition());
        if (position >= 0) {
            extractorInput.skipFully(position);
            trackBundle.fragment.fillEncryptionData(extractorInput);
            return;
        }
        throw ParserException.createForMalformedContainer("Offset to encryption data was negative.", (Throwable) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0110, code lost:
        if ((r5.nalUnitLengthFieldLength + r14) <= (r0.sampleSize - r0.sampleBytesWritten)) goto L_0x0114;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readSample(androidx.media3.extractor.ExtractorInput r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle r2 = r0.currentTrackBundle
            r3 = 0
            r4 = 0
            if (r2 != 0) goto L_0x0043
            android.util.SparseArray<androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle> r2 = r0.trackBundles
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle r2 = getNextTrackBundle(r2)
            if (r2 != 0) goto L_0x002a
            long r5 = r0.endOfMdatPosition
            long r7 = r1.getPosition()
            long r5 = r5 - r7
            int r2 = (int) r5
            if (r2 < 0) goto L_0x0023
            r1.skipFully(r2)
            r0.enterReadingAtomHeaderState()
            return r4
        L_0x0023:
            java.lang.String r0 = "Offset to end of mdat was negative."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r3)
            throw r0
        L_0x002a:
            long r5 = r2.getCurrentSampleOffset()
            long r7 = r1.getPosition()
            long r5 = r5 - r7
            int r5 = (int) r5
            if (r5 >= 0) goto L_0x003e
            java.lang.String r5 = "FragmentedMp4Extractor"
            java.lang.String r6 = "Ignoring negative offset to sample data."
            androidx.media3.common.util.Log.w(r5, r6)
            r5 = r4
        L_0x003e:
            r1.skipFully(r5)
            r0.currentTrackBundle = r2
        L_0x0043:
            int r5 = r0.parserState
            r6 = 3
            r7 = 4
            r8 = 1
            if (r5 != r6) goto L_0x00c8
            int r5 = r2.getCurrentSampleSize()
            r0.sampleSize = r5
            androidx.media3.extractor.mp4.TrackSampleTable r5 = r2.moovSampleTable
            androidx.media3.extractor.mp4.Track r5 = r5.track
            androidx.media3.common.Format r5 = r5.format
            boolean r5 = r0.canReadWithinGopSampleDependencies(r5)
            r5 = r5 ^ r8
            r0.isSampleDependedOn = r5
            int r5 = r2.currentSampleIndex
            int r9 = r2.firstSampleToOutputIndex
            if (r5 >= r9) goto L_0x0076
            int r4 = r0.sampleSize
            r1.skipFully(r4)
            r2.skipSampleEncryptionData()
            boolean r1 = r2.next()
            if (r1 != 0) goto L_0x0073
            r0.currentTrackBundle = r3
        L_0x0073:
            r0.parserState = r6
            return r8
        L_0x0076:
            androidx.media3.extractor.mp4.TrackSampleTable r5 = r2.moovSampleTable
            androidx.media3.extractor.mp4.Track r5 = r5.track
            int r5 = r5.sampleTransformation
            if (r5 != r8) goto L_0x0088
            int r5 = r0.sampleSize
            r9 = 8
            int r5 = r5 - r9
            r0.sampleSize = r5
            r1.skipFully(r9)
        L_0x0088:
            androidx.media3.extractor.mp4.TrackSampleTable r5 = r2.moovSampleTable
            androidx.media3.extractor.mp4.Track r5 = r5.track
            androidx.media3.common.Format r5 = r5.format
            java.lang.String r5 = r5.sampleMimeType
            java.lang.String r9 = "audio/ac4"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x00b5
            int r5 = r0.sampleSize
            r9 = 7
            int r5 = r2.outputSampleEncryptionData(r5, r9)
            r0.sampleBytesWritten = r5
            int r5 = r0.sampleSize
            androidx.media3.common.util.ParsableByteArray r10 = r0.scratch
            androidx.media3.extractor.Ac4Util.getAc4SampleHeader(r5, r10)
            androidx.media3.extractor.TrackOutput r5 = r2.output
            androidx.media3.common.util.ParsableByteArray r10 = r0.scratch
            r5.sampleData(r10, r9)
            int r5 = r0.sampleBytesWritten
            int r5 = r5 + r9
            r0.sampleBytesWritten = r5
            goto L_0x00bd
        L_0x00b5:
            int r5 = r0.sampleSize
            int r5 = r2.outputSampleEncryptionData(r5, r4)
            r0.sampleBytesWritten = r5
        L_0x00bd:
            int r5 = r0.sampleSize
            int r9 = r0.sampleBytesWritten
            int r5 = r5 + r9
            r0.sampleSize = r5
            r0.parserState = r7
            r0.sampleCurrentNalBytesRemaining = r4
        L_0x00c8:
            androidx.media3.extractor.mp4.TrackSampleTable r5 = r2.moovSampleTable
            androidx.media3.extractor.mp4.Track r5 = r5.track
            androidx.media3.extractor.TrackOutput r9 = r2.output
            long r10 = r2.getCurrentSamplePresentationTimeUs()
            androidx.media3.common.util.TimestampAdjuster r12 = r0.timestampAdjuster
            if (r12 == 0) goto L_0x00da
            long r10 = r12.adjustSampleTimestamp(r10)
        L_0x00da:
            int r12 = r5.nalUnitLengthFieldLength
            if (r12 == 0) goto L_0x01fb
            androidx.media3.common.util.ParsableByteArray r12 = r0.nalPrefix
            byte[] r12 = r12.getData()
            r12[r4] = r4
            r12[r8] = r4
            r13 = 2
            r12[r13] = r4
            int r13 = r5.nalUnitLengthFieldLength
            int r13 = 4 - r13
        L_0x00ef:
            int r14 = r0.sampleBytesWritten
            int r15 = r0.sampleSize
            if (r14 >= r15) goto L_0x020c
            int r14 = r0.sampleCurrentNalBytesRemaining
            if (r14 != 0) goto L_0x017a
            androidx.media3.extractor.TrackOutput[] r14 = r0.ceaTrackOutputs
            int r14 = r14.length
            if (r14 > 0) goto L_0x0102
            boolean r14 = r0.isSampleDependedOn
            if (r14 != 0) goto L_0x0113
        L_0x0102:
            androidx.media3.common.Format r14 = r5.format
            int r14 = androidx.media3.container.NalUnitUtil.numberOfBytesInNalUnitHeader(r14)
            int r15 = r5.nalUnitLengthFieldLength
            int r15 = r15 + r14
            int r6 = r0.sampleSize
            int r3 = r0.sampleBytesWritten
            int r6 = r6 - r3
            if (r15 > r6) goto L_0x0113
            goto L_0x0114
        L_0x0113:
            r14 = r4
        L_0x0114:
            int r3 = r5.nalUnitLengthFieldLength
            int r3 = r3 + r14
            r1.readFully(r12, r13, r3)
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalPrefix
            r3.setPosition(r4)
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalPrefix
            int r3 = r3.readInt()
            if (r3 < 0) goto L_0x0172
            int r3 = r3 - r14
            r0.sampleCurrentNalBytesRemaining = r3
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalStartCode
            r3.setPosition(r4)
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalStartCode
            r9.sampleData(r3, r7)
            int r3 = r0.sampleBytesWritten
            int r3 = r3 + r7
            r0.sampleBytesWritten = r3
            int r3 = r0.sampleSize
            int r3 = r3 + r13
            r0.sampleSize = r3
            androidx.media3.extractor.TrackOutput[] r3 = r0.ceaTrackOutputs
            int r3 = r3.length
            if (r3 <= 0) goto L_0x0151
            if (r14 <= 0) goto L_0x0151
            androidx.media3.common.Format r3 = r5.format
            byte r6 = r12[r7]
            boolean r3 = androidx.media3.container.NalUnitUtil.isNalUnitSei(r3, r6)
            if (r3 == 0) goto L_0x0151
            r3 = r8
            goto L_0x0152
        L_0x0151:
            r3 = r4
        L_0x0152:
            r0.processSeiNalUnitPayload = r3
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalPrefix
            r9.sampleData(r3, r14)
            int r3 = r0.sampleBytesWritten
            int r3 = r3 + r14
            r0.sampleBytesWritten = r3
            if (r14 <= 0) goto L_0x016e
            boolean r3 = r0.isSampleDependedOn
            if (r3 != 0) goto L_0x016e
            androidx.media3.common.Format r3 = r5.format
            boolean r3 = androidx.media3.container.NalUnitUtil.isDependedOn(r12, r7, r14, r3)
            if (r3 == 0) goto L_0x016e
            r0.isSampleDependedOn = r8
        L_0x016e:
            r3 = 0
            r6 = 3
            goto L_0x00ef
        L_0x0172:
            java.lang.String r0 = "Invalid NAL length"
            r1 = 0
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r1)
            throw r0
        L_0x017a:
            boolean r3 = r0.processSeiNalUnitPayload
            if (r3 == 0) goto L_0x01eb
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalUnitWithoutHeaderBuffer
            r3.reset((int) r14)
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalUnitWithoutHeaderBuffer
            byte[] r3 = r3.getData()
            int r6 = r0.sampleCurrentNalBytesRemaining
            r1.readFully(r3, r4, r6)
            androidx.media3.common.util.ParsableByteArray r3 = r0.nalUnitWithoutHeaderBuffer
            int r6 = r0.sampleCurrentNalBytesRemaining
            r9.sampleData(r3, r6)
            int r3 = r0.sampleCurrentNalBytesRemaining
            androidx.media3.common.util.ParsableByteArray r6 = r0.nalUnitWithoutHeaderBuffer
            byte[] r6 = r6.getData()
            androidx.media3.common.util.ParsableByteArray r14 = r0.nalUnitWithoutHeaderBuffer
            int r14 = r14.limit()
            int r6 = androidx.media3.container.NalUnitUtil.unescapeStream(r6, r14)
            androidx.media3.common.util.ParsableByteArray r14 = r0.nalUnitWithoutHeaderBuffer
            r14.setPosition(r4)
            androidx.media3.common.util.ParsableByteArray r14 = r0.nalUnitWithoutHeaderBuffer
            r14.setLimit(r6)
            androidx.media3.common.Format r6 = r5.format
            int r6 = r6.maxNumReorderSamples
            r14 = -1
            if (r6 != r14) goto L_0x01c6
            androidx.media3.container.ReorderingBufferQueue r6 = r0.reorderingBufferQueue
            int r6 = r6.getMaxSize()
            if (r6 == 0) goto L_0x01d7
            androidx.media3.container.ReorderingBufferQueue r6 = r0.reorderingBufferQueue
            r6.setMaxSize(r4)
            goto L_0x01d7
        L_0x01c6:
            androidx.media3.container.ReorderingBufferQueue r6 = r0.reorderingBufferQueue
            int r6 = r6.getMaxSize()
            androidx.media3.common.Format r14 = r5.format
            int r14 = r14.maxNumReorderSamples
            if (r6 == r14) goto L_0x01d7
            androidx.media3.container.ReorderingBufferQueue r6 = r0.reorderingBufferQueue
            r6.setMaxSize(r14)
        L_0x01d7:
            androidx.media3.container.ReorderingBufferQueue r6 = r0.reorderingBufferQueue
            androidx.media3.common.util.ParsableByteArray r14 = r0.nalUnitWithoutHeaderBuffer
            r6.add(r10, r14)
            int r6 = r2.getCurrentSampleFlags()
            r6 = r6 & r7
            if (r6 == 0) goto L_0x01ef
            androidx.media3.container.ReorderingBufferQueue r6 = r0.reorderingBufferQueue
            r6.flush()
            goto L_0x01ef
        L_0x01eb:
            int r3 = r9.sampleData((androidx.media3.common.DataReader) r1, (int) r14, (boolean) r4)
        L_0x01ef:
            int r6 = r0.sampleBytesWritten
            int r6 = r6 + r3
            r0.sampleBytesWritten = r6
            int r6 = r0.sampleCurrentNalBytesRemaining
            int r6 = r6 - r3
            r0.sampleCurrentNalBytesRemaining = r6
            goto L_0x016e
        L_0x01fb:
            int r3 = r0.sampleBytesWritten
            int r5 = r0.sampleSize
            if (r3 >= r5) goto L_0x020c
            int r5 = r5 - r3
            int r3 = r9.sampleData((androidx.media3.common.DataReader) r1, (int) r5, (boolean) r4)
            int r5 = r0.sampleBytesWritten
            int r5 = r5 + r3
            r0.sampleBytesWritten = r5
            goto L_0x01fb
        L_0x020c:
            int r1 = r2.getCurrentSampleFlags()
            boolean r3 = r0.isSampleDependedOn
            if (r3 != 0) goto L_0x0217
            r3 = 67108864(0x4000000, float:1.5046328E-36)
            r1 = r1 | r3
        L_0x0217:
            r12 = r1
            androidx.media3.extractor.mp4.TrackEncryptionBox r1 = r2.getEncryptionBoxIfEncrypted()
            if (r1 == 0) goto L_0x0222
            androidx.media3.extractor.TrackOutput$CryptoData r1 = r1.cryptoData
            r15 = r1
            goto L_0x0223
        L_0x0222:
            r15 = 0
        L_0x0223:
            int r13 = r0.sampleSize
            r14 = 0
            r9.sampleMetadata(r10, r12, r13, r14, r15)
            r0.outputPendingMetadataSamples(r10)
            boolean r1 = r2.next()
            if (r1 != 0) goto L_0x0235
            r1 = 0
            r0.currentTrackBundle = r1
        L_0x0235:
            r1 = 3
            r0.parserState = r1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.FragmentedMp4Extractor.readSample(androidx.media3.extractor.ExtractorInput):boolean");
    }

    private static boolean shouldParseContainerAtom(int i2) {
        if (i2 == 1836019574 || i2 == 1953653099 || i2 == 1835297121 || i2 == 1835626086 || i2 == 1937007212 || i2 == 1836019558 || i2 == 1953653094 || i2 == 1836475768 || i2 == 1701082227 || i2 == 1835365473) {
            return true;
        }
        return false;
    }

    private static boolean shouldParseLeafAtom(int i2) {
        if (i2 == 1751411826 || i2 == 1835296868 || i2 == 1836476516 || i2 == 1936286840 || i2 == 1937011556 || i2 == 1937011827 || i2 == 1668576371 || i2 == 1937011555 || i2 == 1937011578 || i2 == 1937013298 || i2 == 1937007471 || i2 == 1668232756 || i2 == 1937011571 || i2 == 1952867444 || i2 == 1952868452 || i2 == 1953196132 || i2 == 1953654136 || i2 == 1953658222 || i2 == 1886614376 || i2 == 1935763834 || i2 == 1935763823 || i2 == 1936027235 || i2 == 1970628964 || i2 == 1935828848 || i2 == 1936158820 || i2 == 1701606260 || i2 == 1835362404 || i2 == 1701671783 || i2 == 1969517665 || i2 == 1801812339 || i2 == 1768715124) {
            return true;
        }
        return false;
    }

    public void init(ExtractorOutput extractorOutput2) {
        if ((this.flags & 32) == 0) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
        enterReadingAtomHeaderState();
        initExtraTracks();
        Track track = this.sideloadedTrack;
        if (track != null) {
            Format.Builder buildUpon = track.format.buildUpon();
            buildUpon.setContainerMimeType(MimeTypeResolver.getContainerMimeType(this.sideloadedTrack.format));
            this.trackBundles.put(0, new TrackBundle(this.extractorOutput.track(0, this.sideloadedTrack.type), new TrackSampleTable(this.sideloadedTrack, new long[0], new int[0], 0, new long[0], new int[0], 0), new DefaultSampleValues(0, 0, 0, 0), buildUpon.build()));
            this.extractorOutput.endTracks();
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        while (true) {
            int i2 = this.parserState;
            if (i2 != 0) {
                if (i2 == 1) {
                    readAtomPayload(extractorInput);
                } else if (i2 == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
                }
            } else if (!readAtomHeader(extractorInput)) {
                long j2 = this.seekPositionBeforeSidxProcessing;
                if (j2 != -1) {
                    positionHolder.position = j2;
                    this.seekPositionBeforeSidxProcessing = -1;
                    this.extractorOutput.seekMap(this.chunkIndexMerger.merge());
                    this.haveOutputSeekMapFromMultipleSidx = true;
                    return 1;
                }
                this.reorderingBufferQueue.flush();
                return -1;
            }
        }
    }

    public void seek(long j2, long j3) {
        int size = this.trackBundles.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.trackBundles.valueAt(i2).resetFragmentInfo();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.reorderingBufferQueue.clear();
        this.pendingSeekTimeUs = j3;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    public boolean sniff(ExtractorInput extractorInput) {
        y0 y0Var;
        SniffFailure sniffFragmented = Sniffer.sniffFragmented(extractorInput);
        if (sniffFragmented != null) {
            y0Var = U.B(sniffFragmented);
        } else {
            G g = U.e;
            y0Var = y0.f278h;
        }
        this.lastSniffFailures = y0Var;
        if (sniffFragmented == null) {
            return true;
        }
        return false;
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i2, TrackFragment trackFragment) {
        parsableByteArray.setPosition(i2 + 8);
        int parseFullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
        if ((parseFullBoxFlags & 1) == 0) {
            boolean z = (parseFullBoxFlags & 2) != 0;
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (readUnsignedIntToInt == 0) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, trackFragment.sampleCount, false);
            } else if (readUnsignedIntToInt == trackFragment.sampleCount) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z);
                trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
                trackFragment.fillEncryptionData(parsableByteArray);
            } else {
                StringBuilder o2 = C0086a.o(readUnsignedIntToInt, "Senc sample count ", " is different from fragment sample count");
                o2.append(trackFragment.sampleCount);
                throw ParserException.createForMalformedContainer(o2.toString(), (Throwable) null);
            }
        } else {
            throw ParserException.createForUnsupportedContainerFeature("Overriding TrackEncryptionBox parameters is unsupported.");
        }
    }

    public U getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory factory, int i2, TimestampAdjuster timestampAdjuster2, Track track, List<Format> list, TrackOutput trackOutput) {
        this.subtitleParserFactory = factory;
        this.flags = i2;
        this.timestampAdjuster = timestampAdjuster2;
        this.sideloadedTrack = track;
        this.closedCaptionFormats = Collections.unmodifiableList(list);
        this.additionalEmsgTrackOutput = trackOutput;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(6);
        this.nalUnitWithoutHeaderBuffer = new ParsableByteArray();
        byte[] bArr = new byte[16];
        this.scratchBytes = bArr;
        this.scratch = new ParsableByteArray(bArr);
        this.containerAtoms = new ArrayDeque<>();
        this.pendingMetadataSampleInfos = new ArrayDeque<>();
        this.trackBundles = new SparseArray<>();
        G g = U.e;
        this.lastSniffFailures = y0.f278h;
        this.durationUs = -9223372036854775807L;
        this.pendingSeekTimeUs = -9223372036854775807L;
        this.segmentIndexEarliestPresentationTimeUs = -9223372036854775807L;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.emsgTrackOutputs = new TrackOutput[0];
        this.ceaTrackOutputs = new TrackOutput[0];
        this.reorderingBufferQueue = new ReorderingBufferQueue(new X.a(this));
        this.chunkIndexMerger = new ChunkIndexMerger();
        this.seekPositionBeforeSidxProcessing = -1;
    }

    public void release() {
    }

    public Track modifyTrack(Track track) {
        return track;
    }
}
