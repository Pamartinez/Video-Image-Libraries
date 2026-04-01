package androidx.media3.extractor.mp4;

import F2.G;
import F2.U;
import F2.y0;
import P.a;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.samsung.android.sdk.sgpl.media.iso.IsoInterface;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Mp4Extractor implements Extractor, SeekMap {
    @Deprecated
    public static final ExtractorsFactory FACTORY = new a(7);
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private long axteAtomOffset;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int fileType;
    private int firstVideoTrackIndex;
    private final int flags;
    private boolean isSampleDependedOn;
    private U lastSniffFailures = y0.f278h;
    private MotionPhotoMetadata motionPhotoMetadata;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private boolean readingAuxiliaryTracks;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private long sampleOffsetForAuxiliaryTracks;
    private int sampleTrackIndex;
    private final ParsableByteArray scratch;
    private boolean seekToAxteAtom;
    private boolean seenFtypAtom;
    private final SefReader sefReader;
    private final List<Metadata.Entry> slowMotionMetadataEntries;
    private final SubtitleParser.Factory subtitleParserFactory;
    private Mp4Track[] tracks;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;
        public final TrueHdSampleRechunker trueHdSampleRechunker;

        public Mp4Track(Track track2, TrackSampleTable trackSampleTable, TrackOutput trackOutput2) {
            TrueHdSampleRechunker trueHdSampleRechunker2;
            this.track = track2;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput2;
            if ("audio/true-hd".equals(track2.format.sampleMimeType)) {
                trueHdSampleRechunker2 = new TrueHdSampleRechunker();
            } else {
                trueHdSampleRechunker2 = null;
            }
            this.trueHdSampleRechunker = trueHdSampleRechunker2;
        }
    }

    public Mp4Extractor(SubtitleParser.Factory factory, int i2) {
        int i7;
        this.subtitleParserFactory = factory;
        this.flags = i2;
        G g = U.e;
        if ((i2 & 4) != 0) {
            i7 = 3;
        } else {
            i7 = 0;
        }
        this.parserState = i7;
        this.sefReader = new SefReader();
        this.slowMotionMetadataEntries = new ArrayList();
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(6);
        this.scratch = new ParsableByteArray();
        this.sampleTrackIndex = -1;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.tracks = new Mp4Track[0];
    }

    private static int brandToFileType(int i2) {
        if (i2 == 1751476579) {
            return 2;
        }
        if (i2 != 1903435808) {
            return 0;
        }
        return 1;
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i2 = 0; i2 < mp4TrackArr.length; i2++) {
            jArr[i2] = new long[mp4TrackArr[i2].sampleTable.sampleCount];
            jArr2[i2] = mp4TrackArr[i2].sampleTable.timestampsUs[0];
        }
        long j2 = 0;
        int i7 = 0;
        while (i7 < mp4TrackArr.length) {
            long j3 = Long.MAX_VALUE;
            int i8 = -1;
            for (int i10 = 0; i10 < mp4TrackArr.length; i10++) {
                if (!zArr[i10]) {
                    long j8 = jArr2[i10];
                    if (j8 <= j3) {
                        i8 = i10;
                        j3 = j8;
                    }
                }
            }
            int i11 = iArr[i8];
            long[] jArr3 = jArr[i8];
            jArr3[i11] = j2;
            TrackSampleTable trackSampleTable = mp4TrackArr[i8].sampleTable;
            j2 += (long) trackSampleTable.sizes[i11];
            int i12 = i11 + 1;
            iArr[i8] = i12;
            if (i12 < jArr3.length) {
                jArr2[i8] = trackSampleTable.timestampsUs[i12];
            } else {
                zArr[i8] = true;
                i7++;
            }
        }
        return jArr;
    }

    private boolean canReadWithinGopSampleDependencies(Format format) {
        if (Objects.equals(format.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264)) {
            if ((this.flags & 32) != 0) {
                return true;
            }
            return false;
        } else if (!Objects.equals(format.sampleMimeType, "video/hevc") || (this.flags & 128) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int codecsToParseWithinGopSampleDependenciesAsFlags(int i2) {
        int i7;
        if ((i2 & 1) != 0) {
            i7 = 32;
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

    private List<Integer> getAuxiliaryTrackTypesForAuxiliaryTracks(Metadata metadata) {
        List<Integer> auxiliaryTrackTypesFromMap = ((MdtaMetadataEntry) Assertions.checkStateNotNull(MetadataUtil.findMdtaMetadataEntryWithKey(metadata, "auxiliary.tracks.map"))).getAuxiliaryTrackTypesFromMap();
        ArrayList arrayList = new ArrayList(auxiliaryTrackTypesFromMap.size());
        for (int i2 = 0; i2 < auxiliaryTrackTypesFromMap.size(); i2++) {
            int intValue = auxiliaryTrackTypesFromMap.get(i2).intValue();
            int i7 = 1;
            if (intValue != 0) {
                if (intValue != 1) {
                    i7 = 3;
                    if (intValue != 2) {
                        if (intValue != 3) {
                            i7 = 0;
                        } else {
                            i7 = 4;
                        }
                    }
                } else {
                    i7 = 2;
                }
            }
            arrayList.add(Integer.valueOf(i7));
        }
        return arrayList;
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable trackSampleTable, long j2) {
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j2);
        if (indexOfEarlierOrEqualSynchronizationSample == -1) {
            return trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j2);
        }
        return indexOfEarlierOrEqualSynchronizationSample;
    }

    private int getTrackIndexOfNextReadSample(long j2) {
        boolean z;
        int i2 = -1;
        int i7 = -1;
        int i8 = 0;
        long j3 = Long.MAX_VALUE;
        boolean z3 = true;
        long j8 = Long.MAX_VALUE;
        boolean z7 = true;
        long j10 = Long.MAX_VALUE;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i8 >= mp4TrackArr.length) {
                break;
            }
            Mp4Track mp4Track = mp4TrackArr[i8];
            int i10 = mp4Track.sampleIndex;
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            if (i10 != trackSampleTable.sampleCount) {
                long j11 = trackSampleTable.offsets[i10];
                long j12 = ((long[][]) Util.castNonNull(this.accumulatedSampleSizes))[i8][i10];
                long j13 = j11 - j2;
                if (j13 < 0 || j13 >= 262144) {
                    z = true;
                } else {
                    z = false;
                }
                if ((!z && z7) || (z == z7 && j13 < j10)) {
                    j8 = j12;
                    z7 = z;
                    i7 = i8;
                    j10 = j13;
                }
                if (j12 < j3) {
                    j3 = j12;
                    z3 = z;
                    i2 = i8;
                }
            }
            i8++;
        }
        if (j3 == Long.MAX_VALUE || !z3 || j8 < j3 + 10485760) {
            return i7;
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 16)};
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable trackSampleTable, long j2, long j3) {
        int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j2);
        if (synchronizationSampleIndex == -1) {
            return j3;
        }
        return Math.min(trackSampleTable.offsets[synchronizationSampleIndex], j3);
    }

    private void maybeSetDefaultSampleOffsetForAuxiliaryTracks(Metadata metadata) {
        MdtaMetadataEntry findMdtaMetadataEntryWithKey = MetadataUtil.findMdtaMetadataEntryWithKey(metadata, "auxiliary.tracks.interleaved");
        if (findMdtaMetadataEntryWithKey != null && findMdtaMetadataEntryWithKey.value[0] == 0) {
            this.sampleOffsetForAuxiliaryTracks = this.axteAtomOffset + 16;
        }
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput extractorInput) {
        this.scratch.reset(8);
        extractorInput.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        extractorInput.skipFully(this.scratch.getPosition());
        extractorInput.resetPeekPosition();
    }

    private void processAtomEnded(long j2) {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j2) {
            Mp4Box.ContainerBox pop = this.containerAtoms.pop();
            if (pop.type == 1836019574) {
                processMoovAtom(pop);
                this.containerAtoms.clear();
                if (!this.seekToAxteAtom) {
                    this.parserState = 2;
                }
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(pop);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private void processEndOfStreamReadingAtomHeader() {
        Metadata metadata;
        if (this.fileType == 2 && (this.flags & 2) != 0) {
            TrackOutput track = this.extractorOutput.track(0, 4);
            MotionPhotoMetadata motionPhotoMetadata2 = this.motionPhotoMetadata;
            if (motionPhotoMetadata2 == null) {
                metadata = null;
            } else {
                metadata = new Metadata(motionPhotoMetadata2);
            }
            track.format(new Format.Builder().setMetadata(metadata).build());
            this.extractorOutput.endTracks();
            this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        }
    }

    private static int processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int brandToFileType = brandToFileType(parsableByteArray.readInt());
        if (brandToFileType != 0) {
            return brandToFileType;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            int brandToFileType2 = brandToFileType(parsableByteArray.readInt());
            if (brandToFileType2 != 0) {
                return brandToFileType2;
            }
        }
        return 0;
    }

    private void processMoovAtom(Mp4Box.ContainerBox containerBox) {
        List list;
        Metadata metadata;
        boolean z;
        Metadata metadata2;
        boolean z3;
        GaplessInfoHolder gaplessInfoHolder;
        List<TrackSampleTable> list2;
        ArrayList arrayList;
        Metadata metadata3;
        String str;
        int i2;
        Metadata metadata4;
        int i7;
        boolean z7;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        Mp4Box.ContainerBox containerBoxOfType = containerBox2.getContainerBoxOfType(IsoInterface.BOX_META);
        List arrayList2 = new ArrayList();
        if (containerBoxOfType != null) {
            Metadata parseMdtaFromMeta = BoxParser.parseMdtaFromMeta(containerBoxOfType);
            if (this.readingAuxiliaryTracks) {
                Assertions.checkStateNotNull(parseMdtaFromMeta);
                maybeSetDefaultSampleOffsetForAuxiliaryTracks(parseMdtaFromMeta);
                arrayList2 = getAuxiliaryTrackTypesForAuxiliaryTracks(parseMdtaFromMeta);
            } else if (shouldSeekToAxteAtom(parseMdtaFromMeta)) {
                this.seekToAxteAtom = true;
                return;
            }
            metadata = parseMdtaFromMeta;
            list = arrayList2;
        } else {
            list = arrayList2;
            metadata = null;
        }
        ArrayList arrayList3 = new ArrayList();
        if (this.fileType == 1) {
            z = true;
        } else {
            z = false;
        }
        GaplessInfoHolder gaplessInfoHolder2 = new GaplessInfoHolder();
        Mp4Box.LeafBox leafBoxOfType = containerBox2.getLeafBoxOfType(IsoInterface.BOX_UDTA);
        if (leafBoxOfType != null) {
            Metadata parseUdta = BoxParser.parseUdta(leafBoxOfType);
            gaplessInfoHolder2.setFromMetadata(parseUdta);
            metadata2 = parseUdta;
        } else {
            metadata2 = null;
        }
        Metadata metadata5 = new Metadata(BoxParser.parseMvhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(IsoInterface.BOX_MVHD))).data));
        if ((this.flags & 1) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Metadata metadata6 = metadata5;
        List<TrackSampleTable> parseTraks = BoxParser.parseTraks(containerBox2, gaplessInfoHolder2, -9223372036854775807L, (DrmInitData) null, z3, z, new W.a(1));
        if (this.readingAuxiliaryTracks) {
            if (list.size() == parseTraks.size()) {
                z7 = true;
            } else {
                z7 = false;
            }
            Locale locale = Locale.US;
            Assertions.checkState(z7, A.a.d(list.size(), parseTraks.size(), "The number of auxiliary track types from metadata (", ") is not same as the number of auxiliary tracks (", ")"));
        }
        String containerMimeType = MimeTypeResolver.getContainerMimeType(parseTraks);
        int i8 = 0;
        int i10 = 0;
        long j2 = -9223372036854775807L;
        int i11 = -1;
        while (i8 < parseTraks.size()) {
            TrackSampleTable trackSampleTable = parseTraks.get(i8);
            if (trackSampleTable.sampleCount == 0) {
                list2 = parseTraks;
                gaplessInfoHolder = gaplessInfoHolder2;
                str = containerMimeType;
                metadata3 = metadata;
                arrayList = arrayList3;
            } else {
                Track track = trackSampleTable.track;
                list2 = parseTraks;
                ArrayList arrayList4 = arrayList3;
                int i12 = i10 + 1;
                String str2 = containerMimeType;
                Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, this.extractorOutput.track(i10, track.type));
                metadata3 = metadata;
                long j3 = track.durationUs;
                if (j3 == -9223372036854775807L) {
                    j3 = trackSampleTable.durationUs;
                }
                mp4Track.trackOutput.durationUs(j3);
                j2 = Math.max(j2, j3);
                if ("audio/true-hd".equals(track.format.sampleMimeType)) {
                    i2 = trackSampleTable.maximumSize * 16;
                } else {
                    i2 = trackSampleTable.maximumSize + 30;
                }
                Format.Builder buildUpon = track.format.buildUpon();
                buildUpon.setMaxInputSize(i2);
                if (track.type == 2) {
                    int i13 = track.format.roleFlags;
                    if ((this.flags & 8) != 0) {
                        if (i11 == -1) {
                            i7 = 1;
                        } else {
                            i7 = 2;
                        }
                        i13 |= i7;
                    }
                    if (this.readingAuxiliaryTracks) {
                        i13 |= 32768;
                        buildUpon.setAuxiliaryTrackType(((Integer) list.get(i8)).intValue());
                    }
                    buildUpon.setRoleFlags(i13);
                }
                MetadataUtil.setFormatGaplessInfo(track.type, gaplessInfoHolder2, buildUpon);
                int i14 = track.type;
                Metadata metadata7 = track.format.metadata;
                if (this.slowMotionMetadataEntries.isEmpty()) {
                    gaplessInfoHolder = gaplessInfoHolder2;
                    metadata4 = null;
                } else {
                    gaplessInfoHolder = gaplessInfoHolder2;
                    metadata4 = new Metadata((List<? extends Metadata.Entry>) this.slowMotionMetadataEntries);
                }
                MetadataUtil.setFormatMetadata(i14, metadata3, buildUpon, metadata7, metadata4, metadata2, metadata6);
                str = str2;
                buildUpon.setContainerMimeType(str);
                mp4Track.trackOutput.format(buildUpon.build());
                if (track.type == 2 && i11 == -1) {
                    i11 = arrayList4.size();
                }
                arrayList = arrayList4;
                arrayList.add(mp4Track);
                i10 = i12;
            }
            i8++;
            metadata = metadata3;
            arrayList3 = arrayList;
            parseTraks = list2;
            containerMimeType = str;
            gaplessInfoHolder2 = gaplessInfoHolder;
        }
        this.firstVideoTrackIndex = i11;
        this.durationUs = j2;
        Mp4Track[] mp4TrackArr = (Mp4Track[]) arrayList3.toArray(new Mp4Track[0]);
        this.tracks = mp4TrackArr;
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(mp4TrackArr);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private void processUnparsedAtom(long j2) {
        if (this.atomType == 1836086884) {
            int i2 = this.atomHeaderBytesRead;
            this.motionPhotoMetadata = new MotionPhotoMetadata(0, j2, -9223372036854775807L, j2 + ((long) i2), this.atomSize - ((long) i2));
        }
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) {
        boolean z;
        boolean z3;
        Mp4Box.ContainerBox peek;
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                processEndOfStreamReadingAtomHeader();
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
            if (length == -1 && (peek = this.containerAtoms.peek()) != null) {
                length = peek.endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize >= ((long) this.atomHeaderBytesRead)) {
            if (shouldParseContainerAtom(this.atomType)) {
                long position = extractorInput.getPosition();
                long j3 = this.atomSize;
                int i2 = this.atomHeaderBytesRead;
                long j8 = (position + j3) - ((long) i2);
                if (j3 != ((long) i2) && this.atomType == 1835365473) {
                    maybeSkipRemainingMetaAtomHeaderBytes(extractorInput);
                }
                this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, j8));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(j8);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                if (this.atomHeaderBytesRead == 8) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkState(z);
                if (this.atomSize <= 2147483647L) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Assertions.checkState(z3);
                ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
                System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
                this.atomData = parsableByteArray;
                this.parserState = 1;
            } else {
                processUnparsedAtom(extractorInput.getPosition() - ((long) this.atomHeaderBytesRead));
                this.atomData = null;
                this.parserState = 1;
            }
            return true;
        }
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readAtomPayload(androidx.media3.extractor.ExtractorInput r10, androidx.media3.extractor.PositionHolder r11) {
        /*
            r9 = this;
            long r0 = r9.atomSize
            int r2 = r9.atomHeaderBytesRead
            long r2 = (long) r2
            long r0 = r0 - r2
            long r2 = r10.getPosition()
            long r2 = r2 + r0
            androidx.media3.common.util.ParsableByteArray r4 = r9.atomData
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0046
            byte[] r7 = r4.getData()
            int r8 = r9.atomHeaderBytesRead
            int r0 = (int) r0
            r10.readFully(r7, r8, r0)
            int r10 = r9.atomType
            r0 = 1718909296(0x66747970, float:2.8862439E23)
            if (r10 != r0) goto L_0x002b
            r9.seenFtypAtom = r5
            int r10 = processFtypAtom(r4)
            r9.fileType = r10
            goto L_0x005e
        L_0x002b:
            java.util.ArrayDeque<androidx.media3.container.Mp4Box$ContainerBox> r10 = r9.containerAtoms
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x005e
            java.util.ArrayDeque<androidx.media3.container.Mp4Box$ContainerBox> r10 = r9.containerAtoms
            java.lang.Object r10 = r10.peek()
            androidx.media3.container.Mp4Box$ContainerBox r10 = (androidx.media3.container.Mp4Box.ContainerBox) r10
            androidx.media3.container.Mp4Box$LeafBox r0 = new androidx.media3.container.Mp4Box$LeafBox
            int r1 = r9.atomType
            r0.<init>(r1, r4)
            r10.add((androidx.media3.container.Mp4Box.LeafBox) r0)
            goto L_0x005e
        L_0x0046:
            boolean r4 = r9.seenFtypAtom
            if (r4 != 0) goto L_0x0053
            int r4 = r9.atomType
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            if (r4 != r7) goto L_0x0053
            r9.fileType = r5
        L_0x0053:
            r7 = 262144(0x40000, double:1.295163E-318)
            int r4 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r4 >= 0) goto L_0x0060
            int r0 = (int) r0
            r10.skipFully(r0)
        L_0x005e:
            r10 = r6
            goto L_0x0068
        L_0x0060:
            long r7 = r10.getPosition()
            long r7 = r7 + r0
            r11.position = r7
            r10 = r5
        L_0x0068:
            r9.processAtomEnded(r2)
            boolean r0 = r9.seekToAxteAtom
            if (r0 == 0) goto L_0x0078
            r9.readingAuxiliaryTracks = r5
            long r0 = r9.axteAtomOffset
            r11.position = r0
            r9.seekToAxteAtom = r6
            r10 = r5
        L_0x0078:
            if (r10 == 0) goto L_0x0080
            int r9 = r9.parserState
            r10 = 2
            if (r9 == r10) goto L_0x0080
            return r5
        L_0x0080:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.Mp4Extractor.readAtomPayload(androidx.media3.extractor.ExtractorInput, androidx.media3.extractor.PositionHolder):boolean");
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) {
        TrackOutput.CryptoData cryptoData;
        boolean z;
        int i2;
        ExtractorInput extractorInput2 = extractorInput;
        long position = extractorInput2.getPosition();
        if (this.sampleTrackIndex == -1) {
            int trackIndexOfNextReadSample = getTrackIndexOfNextReadSample(position);
            this.sampleTrackIndex = trackIndexOfNextReadSample;
            if (trackIndexOfNextReadSample == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i7 = mp4Track.sampleIndex;
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        long j2 = trackSampleTable.offsets[i7] + this.sampleOffsetForAuxiliaryTracks;
        int i8 = trackSampleTable.sizes[i7];
        TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
        long j3 = (j2 - position) + ((long) this.sampleBytesRead);
        if (j3 < 0 || j3 >= 262144) {
            positionHolder.position = j2;
            return 1;
        }
        if (mp4Track.track.sampleTransformation == 1) {
            j3 += 8;
            i8 -= 8;
        }
        extractorInput2.skipFully((int) j3);
        if (!canReadWithinGopSampleDependencies(mp4Track.track.format)) {
            this.isSampleDependedOn = true;
        }
        Track track = mp4Track.track;
        if (track.nalUnitLengthFieldLength == 0) {
            cryptoData = null;
            if ("audio/ac4".equals(track.format.sampleMimeType)) {
                if (this.sampleBytesWritten == 0) {
                    Ac4Util.getAc4SampleHeader(i8, this.scratch);
                    trackOutput.sampleData(this.scratch, 7);
                    this.sampleBytesWritten += 7;
                }
                i8 += 7;
            } else if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.startSample(extractorInput2);
            }
            while (true) {
                int i10 = this.sampleBytesWritten;
                if (i10 >= i8) {
                    break;
                }
                int sampleData = trackOutput.sampleData((DataReader) extractorInput2, i8 - i10, false);
                this.sampleBytesRead += sampleData;
                this.sampleBytesWritten += sampleData;
                this.sampleCurrentNalBytesRemaining -= sampleData;
            }
        } else {
            byte[] data = this.nalPrefix.getData();
            data[0] = 0;
            data[1] = 0;
            data[2] = 0;
            int i11 = 4 - mp4Track.track.nalUnitLengthFieldLength;
            i8 += i11;
            while (this.sampleBytesWritten < i8) {
                int i12 = this.sampleCurrentNalBytesRemaining;
                if (i12 == 0) {
                    Track track2 = mp4Track.track;
                    int i13 = track2.nalUnitLengthFieldLength;
                    if (this.isSampleDependedOn || NalUnitUtil.numberOfBytesInNalUnitHeader(track2.format) + i13 > mp4Track.sampleTable.sizes[i7] - this.sampleBytesRead) {
                        i2 = 0;
                    } else {
                        i2 = NalUnitUtil.numberOfBytesInNalUnitHeader(mp4Track.track.format);
                        i13 = mp4Track.track.nalUnitLengthFieldLength + i2;
                    }
                    extractorInput2.readFully(data, i11, i13);
                    this.sampleBytesRead += i13;
                    this.nalPrefix.setPosition(0);
                    int readInt = this.nalPrefix.readInt();
                    if (readInt >= 0) {
                        this.sampleCurrentNalBytesRemaining = readInt - i2;
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                        if (i2 > 0) {
                            trackOutput.sampleData(this.nalPrefix, i2);
                            this.sampleBytesWritten += i2;
                            if (NalUnitUtil.isDependedOn(data, 4, i2, mp4Track.track.format)) {
                                this.isSampleDependedOn = true;
                            }
                        }
                    } else {
                        throw ParserException.createForMalformedContainer("Invalid NAL length", (Throwable) null);
                    }
                } else {
                    int sampleData2 = trackOutput.sampleData((DataReader) extractorInput2, i12, false);
                    this.sampleBytesRead += sampleData2;
                    this.sampleBytesWritten += sampleData2;
                    this.sampleCurrentNalBytesRemaining -= sampleData2;
                }
            }
            cryptoData = null;
        }
        int i14 = i8;
        TrackSampleTable trackSampleTable2 = mp4Track.sampleTable;
        long j8 = trackSampleTable2.timestampsUs[i7];
        int i15 = trackSampleTable2.flags[i7];
        if (!this.isSampleDependedOn) {
            i15 |= 67108864;
        }
        if (trueHdSampleRechunker != null) {
            TrueHdSampleRechunker trueHdSampleRechunker2 = trueHdSampleRechunker;
            int i16 = i15;
            z = false;
            long j10 = j8;
            TrackOutput trackOutput2 = trackOutput;
            TrueHdSampleRechunker trueHdSampleRechunker3 = trueHdSampleRechunker2;
            trueHdSampleRechunker3.sampleMetadata(trackOutput2, j10, i16, i14, 0, (TrackOutput.CryptoData) null);
            TrueHdSampleRechunker trueHdSampleRechunker4 = trueHdSampleRechunker3;
            TrackOutput trackOutput3 = trackOutput2;
            if (i7 + 1 == mp4Track.sampleTable.sampleCount) {
                trueHdSampleRechunker4.outputPendingSampleMetadata(trackOutput3, cryptoData);
            }
        } else {
            int i17 = i15;
            z = false;
            trackOutput.sampleMetadata(j8, i17, i14, 0, (TrackOutput.CryptoData) null);
        }
        mp4Track.sampleIndex++;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = z ? 1 : 0;
        this.sampleBytesWritten = z;
        this.sampleCurrentNalBytesRemaining = z;
        this.isSampleDependedOn = z;
        return z;
    }

    private int readSefData(ExtractorInput extractorInput, PositionHolder positionHolder) {
        int read = this.sefReader.read(extractorInput, positionHolder, this.slowMotionMetadataEntries);
        if (read == 1 && positionHolder.position == 0) {
            enterReadingAtomHeaderState();
        }
        return read;
    }

    private static boolean shouldParseContainerAtom(int i2) {
        if (i2 == 1836019574 || i2 == 1953653099 || i2 == 1835297121 || i2 == 1835626086 || i2 == 1937007212 || i2 == 1701082227 || i2 == 1835365473 || i2 == 1635284069) {
            return true;
        }
        return false;
    }

    private static boolean shouldParseLeafAtom(int i2) {
        if (i2 == 1835296868 || i2 == 1836476516 || i2 == 1751411826 || i2 == 1937011556 || i2 == 1937011827 || i2 == 1937011571 || i2 == 1668576371 || i2 == 1701606260 || i2 == 1937011555 || i2 == 1937011578 || i2 == 1937013298 || i2 == 1937007471 || i2 == 1668232756 || i2 == 1953196132 || i2 == 1718909296 || i2 == 1969517665 || i2 == 1801812339 || i2 == 1768715124) {
            return true;
        }
        return false;
    }

    private boolean shouldSeekToAxteAtom(Metadata metadata) {
        MdtaMetadataEntry findMdtaMetadataEntryWithKey;
        if (!(metadata == null || (this.flags & 64) == 0 || (findMdtaMetadataEntryWithKey = MetadataUtil.findMdtaMetadataEntryWithKey(metadata, "auxiliary.tracks.offset")) == null)) {
            long readUnsignedLongToLong = new ParsableByteArray(findMdtaMetadataEntryWithKey.value).readUnsignedLongToLong();
            if (readUnsignedLongToLong > 0) {
                this.axteAtomOffset = readUnsignedLongToLong;
                return true;
            }
        }
        return false;
    }

    private void updateSampleIndex(Mp4Track mp4Track, long j2) {
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j2);
        if (indexOfEarlierOrEqualSynchronizationSample == -1) {
            indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j2);
        }
        mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long[] getSampleTimestampsUs(int i2) {
        Mp4Track[] mp4TrackArr = this.tracks;
        if (mp4TrackArr.length <= i2) {
            return new long[0];
        }
        return mp4TrackArr[i2].sampleTable.timestampsUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        return getSeekPoints(j2, -1);
    }

    public void init(ExtractorOutput extractorOutput2) {
        if ((this.flags & 16) == 0) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
    }

    public boolean isSeekable() {
        return true;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        while (true) {
            int i2 = this.parserState;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2) {
                        return readSample(extractorInput, positionHolder);
                    }
                    if (i2 == 3) {
                        return readSefData(extractorInput, positionHolder);
                    }
                    throw new IllegalStateException();
                } else if (readAtomPayload(extractorInput, positionHolder)) {
                    return 1;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    public void seek(long j2, long j3) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.isSampleDependedOn = false;
        if (j2 != 0) {
            for (Mp4Track mp4Track : this.tracks) {
                updateSampleIndex(mp4Track, j3);
                TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
                if (trueHdSampleRechunker != null) {
                    trueHdSampleRechunker.reset();
                }
            }
        } else if (this.parserState != 3) {
            enterReadingAtomHeaderState();
        } else {
            this.sefReader.reset();
            this.slowMotionMetadataEntries.clear();
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        boolean z;
        y0 y0Var;
        if ((this.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        SniffFailure sniffUnfragmented = Sniffer.sniffUnfragmented(extractorInput, z);
        if (sniffUnfragmented != null) {
            y0Var = U.B(sniffUnfragmented);
        } else {
            G g = U.e;
            y0Var = y0.f278h;
        }
        this.lastSniffFailures = y0Var;
        if (sniffUnfragmented == null) {
            return true;
        }
        return false;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2, int i2) {
        int i7;
        long j3;
        long j8;
        long j10;
        long j11;
        int indexOfLaterOrEqualSynchronizationSample;
        long j12 = j2;
        int i8 = i2;
        Mp4Track[] mp4TrackArr = this.tracks;
        if (mp4TrackArr.length == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        if (i8 != -1) {
            i7 = i8;
        } else {
            i7 = this.firstVideoTrackIndex;
        }
        if (i7 != -1) {
            TrackSampleTable trackSampleTable = mp4TrackArr[i7].sampleTable;
            int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j12);
            if (synchronizationSampleIndex == -1) {
                return new SeekMap.SeekPoints(SeekPoint.START);
            }
            long j13 = trackSampleTable.timestampsUs[synchronizationSampleIndex];
            j3 = trackSampleTable.offsets[synchronizationSampleIndex];
            if (j13 >= j12 || synchronizationSampleIndex >= trackSampleTable.sampleCount - 1 || (indexOfLaterOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j12)) == -1 || indexOfLaterOrEqualSynchronizationSample == synchronizationSampleIndex) {
                j11 = -1;
                j10 = -9223372036854775807L;
            } else {
                j10 = trackSampleTable.timestampsUs[indexOfLaterOrEqualSynchronizationSample];
                j11 = trackSampleTable.offsets[indexOfLaterOrEqualSynchronizationSample];
            }
            long j14 = j13;
            j8 = j11;
            j12 = j14;
        } else {
            j3 = Long.MAX_VALUE;
            j8 = -1;
            j10 = -9223372036854775807L;
        }
        if (i8 == -1) {
            int i10 = 0;
            while (true) {
                Mp4Track[] mp4TrackArr2 = this.tracks;
                if (i10 >= mp4TrackArr2.length) {
                    break;
                }
                if (i10 != this.firstVideoTrackIndex) {
                    TrackSampleTable trackSampleTable2 = mp4TrackArr2[i10].sampleTable;
                    long maybeAdjustSeekOffset = maybeAdjustSeekOffset(trackSampleTable2, j12, j3);
                    if (j10 != -9223372036854775807L) {
                        j8 = maybeAdjustSeekOffset(trackSampleTable2, j10, j8);
                    }
                    j3 = maybeAdjustSeekOffset;
                }
                i10++;
            }
        }
        SeekPoint seekPoint = new SeekPoint(j12, j3);
        if (j10 == -9223372036854775807L) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(j10, j8));
    }

    public U getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    public void release() {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Track lambda$processMoovAtom$2(Track track) {
        return track;
    }
}
