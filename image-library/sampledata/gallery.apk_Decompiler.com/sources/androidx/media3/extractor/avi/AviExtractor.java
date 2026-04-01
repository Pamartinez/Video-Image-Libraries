package androidx.media3.extractor.avi;

import F2.G;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.NoOpExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AviExtractor implements Extractor {
    private AviMainHeaderChunk aviHeader;
    private final ChunkHeaderHolder chunkHeaderHolder;
    /* access modifiers changed from: private */
    public ChunkReader[] chunkReaders;
    private ChunkReader currentChunkReader;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int hdrlSize;
    private int idx1BodySize;
    private long moviEnd;
    private long moviStart;
    private final boolean parseSubtitlesDuringExtraction;
    private long pendingReposition;
    private final ParsableByteArray scratch;
    private boolean seekMapHasBeenOutput;
    private int state;
    private final SubtitleParser.Factory subtitleParserFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AviSeekMap implements SeekMap {
        private final long durationUs;

        public AviSeekMap(long j2) {
            this.durationUs = j2;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekMap.SeekPoints getSeekPoints(long j2) {
            SeekMap.SeekPoints seekPoints = AviExtractor.this.chunkReaders[0].getSeekPoints(j2);
            for (int i2 = 1; i2 < AviExtractor.this.chunkReaders.length; i2++) {
                SeekMap.SeekPoints seekPoints2 = AviExtractor.this.chunkReaders[i2].getSeekPoints(j2);
                if (seekPoints2.first.position < seekPoints.first.position) {
                    seekPoints = seekPoints2;
                }
            }
            return seekPoints;
        }

        public boolean isSeekable() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ChunkHeaderHolder {
        public int chunkType;
        public int listType;
        public int size;

        private ChunkHeaderHolder() {
        }

        public void populateFrom(ParsableByteArray parsableByteArray) {
            this.chunkType = parsableByteArray.readLittleEndianInt();
            this.size = parsableByteArray.readLittleEndianInt();
            this.listType = 0;
        }

        public void populateWithListHeaderFrom(ParsableByteArray parsableByteArray) {
            populateFrom(parsableByteArray);
            if (this.chunkType == 1414744396) {
                this.listType = parsableByteArray.readLittleEndianInt();
                return;
            }
            throw ParserException.createForMalformedContainer("LIST expected, found: " + this.chunkType, (Throwable) null);
        }
    }

    public AviExtractor(int i2, SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        this.parseSubtitlesDuringExtraction = (i2 & 1) != 0 ? false : true;
        this.scratch = new ParsableByteArray(12);
        this.chunkHeaderHolder = new ChunkHeaderHolder();
        this.extractorOutput = new NoOpExtractorOutput();
        this.chunkReaders = new ChunkReader[0];
        this.moviStart = -1;
        this.moviEnd = -1;
        this.hdrlSize = -1;
        this.durationUs = -9223372036854775807L;
    }

    private static void alignInputToEvenPosition(ExtractorInput extractorInput) {
        if ((extractorInput.getPosition() & 1) == 1) {
            extractorInput.skipFully(1);
        }
    }

    private ChunkReader getChunkReader(int i2) {
        for (ChunkReader chunkReader : this.chunkReaders) {
            if (chunkReader.handlesChunkId(i2)) {
                return chunkReader;
            }
        }
        return null;
    }

    private void parseHdrlBody(ParsableByteArray parsableByteArray) {
        ListChunk parseFrom = ListChunk.parseFrom(1819436136, parsableByteArray);
        if (parseFrom.getType() == 1819436136) {
            AviMainHeaderChunk aviMainHeaderChunk = (AviMainHeaderChunk) parseFrom.getChild(AviMainHeaderChunk.class);
            if (aviMainHeaderChunk != null) {
                this.aviHeader = aviMainHeaderChunk;
                this.durationUs = ((long) aviMainHeaderChunk.totalFrames) * ((long) aviMainHeaderChunk.frameDurationUs);
                ArrayList arrayList = new ArrayList();
                G A10 = parseFrom.children.listIterator(0);
                int i2 = 0;
                while (A10.hasNext()) {
                    AviChunk aviChunk = (AviChunk) A10.next();
                    if (aviChunk.getType() == 1819440243) {
                        int i7 = i2 + 1;
                        ChunkReader processStreamList = processStreamList((ListChunk) aviChunk, i2);
                        if (processStreamList != null) {
                            arrayList.add(processStreamList);
                        }
                        i2 = i7;
                    }
                }
                this.chunkReaders = (ChunkReader[]) arrayList.toArray(new ChunkReader[0]);
                this.extractorOutput.endTracks();
                return;
            }
            throw ParserException.createForMalformedContainer("AviHeader not found", (Throwable) null);
        }
        throw ParserException.createForMalformedContainer("Unexpected header list type " + parseFrom.getType(), (Throwable) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [int] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseIdx1Body(androidx.media3.common.util.ParsableByteArray r11) {
        /*
            r10 = this;
            long r0 = r10.peekSeekOffset(r11)
        L_0x0004:
            int r2 = r11.bytesLeft()
            r3 = 0
            r4 = 1
            r5 = 16
            if (r2 < r5) goto L_0x002f
            int r2 = r11.readLittleEndianInt()
            int r6 = r11.readLittleEndianInt()
            int r7 = r11.readLittleEndianInt()
            long r7 = (long) r7
            long r7 = r7 + r0
            r9 = 4
            r11.skipBytes(r9)
            androidx.media3.extractor.avi.ChunkReader r2 = r10.getChunkReader(r2)
            if (r2 != 0) goto L_0x0027
            goto L_0x0004
        L_0x0027:
            r6 = r6 & r5
            if (r6 != r5) goto L_0x002b
            r3 = r4
        L_0x002b:
            r2.appendIndexChunk(r7, r3)
            goto L_0x0004
        L_0x002f:
            androidx.media3.extractor.avi.ChunkReader[] r11 = r10.chunkReaders
            int r0 = r11.length
        L_0x0032:
            if (r3 >= r0) goto L_0x003c
            r1 = r11[r3]
            r1.commitIndex()
            int r3 = r3 + 1
            goto L_0x0032
        L_0x003c:
            r10.seekMapHasBeenOutput = r4
            androidx.media3.extractor.avi.ChunkReader[] r11 = r10.chunkReaders
            int r11 = r11.length
            if (r11 != 0) goto L_0x0050
            androidx.media3.extractor.ExtractorOutput r11 = r10.extractorOutput
            androidx.media3.extractor.SeekMap$Unseekable r0 = new androidx.media3.extractor.SeekMap$Unseekable
            long r1 = r10.durationUs
            r0.<init>(r1)
            r11.seekMap(r0)
            return
        L_0x0050:
            androidx.media3.extractor.ExtractorOutput r11 = r10.extractorOutput
            androidx.media3.extractor.avi.AviExtractor$AviSeekMap r0 = new androidx.media3.extractor.avi.AviExtractor$AviSeekMap
            long r1 = r10.durationUs
            r0.<init>(r1)
            r11.seekMap(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.avi.AviExtractor.parseIdx1Body(androidx.media3.common.util.ParsableByteArray):void");
    }

    private long peekSeekOffset(ParsableByteArray parsableByteArray) {
        long j2 = 0;
        if (parsableByteArray.bytesLeft() < 16) {
            return 0;
        }
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(8);
        long j3 = this.moviStart;
        if (((long) parsableByteArray.readLittleEndianInt()) <= j3) {
            j2 = j3 + 8;
        }
        parsableByteArray.setPosition(position);
        return j2;
    }

    private ChunkReader processStreamList(ListChunk listChunk, int i2) {
        AviStreamHeaderChunk aviStreamHeaderChunk = (AviStreamHeaderChunk) listChunk.getChild(AviStreamHeaderChunk.class);
        StreamFormatChunk streamFormatChunk = (StreamFormatChunk) listChunk.getChild(StreamFormatChunk.class);
        if (aviStreamHeaderChunk == null) {
            Log.w("AviExtractor", "Missing Stream Header");
            return null;
        } else if (streamFormatChunk == null) {
            Log.w("AviExtractor", "Missing Stream Format");
            return null;
        } else {
            long durationUs2 = aviStreamHeaderChunk.getDurationUs();
            Format format = streamFormatChunk.format;
            Format.Builder buildUpon = format.buildUpon();
            buildUpon.setId(i2);
            int i7 = aviStreamHeaderChunk.suggestedBufferSize;
            if (i7 != 0) {
                buildUpon.setMaxInputSize(i7);
            }
            StreamNameChunk streamNameChunk = (StreamNameChunk) listChunk.getChild(StreamNameChunk.class);
            if (streamNameChunk != null) {
                buildUpon.setLabel(streamNameChunk.name);
            }
            int trackType = MimeTypes.getTrackType(format.sampleMimeType);
            if (trackType != 1 && trackType != 2) {
                return null;
            }
            TrackOutput track = this.extractorOutput.track(i2, trackType);
            track.format(buildUpon.build());
            track.durationUs(durationUs2);
            this.durationUs = Math.max(this.durationUs, durationUs2);
            return new ChunkReader(i2, aviStreamHeaderChunk, track);
        }
    }

    private int readMoviChunks(ExtractorInput extractorInput) {
        if (extractorInput.getPosition() >= this.moviEnd) {
            return -1;
        }
        ChunkReader chunkReader = this.currentChunkReader;
        if (chunkReader == null) {
            alignInputToEvenPosition(extractorInput);
            int i2 = 12;
            extractorInput.peekFully(this.scratch.getData(), 0, 12);
            this.scratch.setPosition(0);
            int readLittleEndianInt = this.scratch.readLittleEndianInt();
            if (readLittleEndianInt == 1414744396) {
                this.scratch.setPosition(8);
                if (this.scratch.readLittleEndianInt() != 1769369453) {
                    i2 = 8;
                }
                extractorInput.skipFully(i2);
                extractorInput.resetPeekPosition();
                return 0;
            }
            int readLittleEndianInt2 = this.scratch.readLittleEndianInt();
            if (readLittleEndianInt == 1263424842) {
                this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt2) + 8;
                return 0;
            }
            extractorInput.skipFully(8);
            extractorInput.resetPeekPosition();
            ChunkReader chunkReader2 = getChunkReader(readLittleEndianInt);
            if (chunkReader2 == null) {
                this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt2);
                return 0;
            }
            chunkReader2.onChunkStart(readLittleEndianInt2);
            this.currentChunkReader = chunkReader2;
        } else if (chunkReader.onChunkData(extractorInput)) {
            this.currentChunkReader = null;
        }
        return 0;
    }

    private boolean resolvePendingReposition(ExtractorInput extractorInput, PositionHolder positionHolder) {
        boolean z;
        if (this.pendingReposition != -1) {
            long position = extractorInput.getPosition();
            long j2 = this.pendingReposition;
            if (j2 < position || j2 > 262144 + position) {
                positionHolder.position = j2;
                z = true;
                this.pendingReposition = -1;
                return z;
            }
            extractorInput.skipFully((int) (j2 - position));
        }
        z = false;
        this.pendingReposition = -1;
        return z;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.state = 0;
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
        this.pendingReposition = -1;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        if (resolvePendingReposition(extractorInput, positionHolder)) {
            return 1;
        }
        switch (this.state) {
            case 0:
                if (sniff(extractorInput)) {
                    extractorInput.skipFully(12);
                    this.state = 1;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("AVI Header List not found", (Throwable) null);
            case 1:
                extractorInput.readFully(this.scratch.getData(), 0, 12);
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateWithListHeaderFrom(this.scratch);
                ChunkHeaderHolder chunkHeaderHolder2 = this.chunkHeaderHolder;
                if (chunkHeaderHolder2.listType == 1819436136) {
                    this.hdrlSize = chunkHeaderHolder2.size;
                    this.state = 2;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("hdrl expected, found: " + this.chunkHeaderHolder.listType, (Throwable) null);
            case 2:
                int i2 = this.hdrlSize - 4;
                ParsableByteArray parsableByteArray = new ParsableByteArray(i2);
                extractorInput.readFully(parsableByteArray.getData(), 0, i2);
                parseHdrlBody(parsableByteArray);
                this.state = 3;
                return 0;
            case 3:
                if (this.moviStart != -1) {
                    long position = extractorInput.getPosition();
                    long j2 = this.moviStart;
                    if (position != j2) {
                        this.pendingReposition = j2;
                        return 0;
                    }
                }
                extractorInput.peekFully(this.scratch.getData(), 0, 12);
                extractorInput.resetPeekPosition();
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateFrom(this.scratch);
                int readLittleEndianInt = this.scratch.readLittleEndianInt();
                int i7 = this.chunkHeaderHolder.chunkType;
                if (i7 == 1179011410) {
                    extractorInput.skipFully(12);
                    return 0;
                } else if (i7 == 1414744396 && readLittleEndianInt == 1769369453) {
                    long position2 = extractorInput.getPosition();
                    this.moviStart = position2;
                    this.moviEnd = position2 + ((long) this.chunkHeaderHolder.size) + 8;
                    if (!this.seekMapHasBeenOutput) {
                        if (((AviMainHeaderChunk) Assertions.checkNotNull(this.aviHeader)).hasIndex()) {
                            this.state = 4;
                            this.pendingReposition = this.moviEnd;
                            return 0;
                        }
                        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                        this.seekMapHasBeenOutput = true;
                    }
                    this.pendingReposition = extractorInput.getPosition() + 12;
                    this.state = 6;
                    return 0;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) this.chunkHeaderHolder.size) + 8;
                    return 0;
                }
            case 4:
                extractorInput.readFully(this.scratch.getData(), 0, 8);
                this.scratch.setPosition(0);
                int readLittleEndianInt2 = this.scratch.readLittleEndianInt();
                int readLittleEndianInt3 = this.scratch.readLittleEndianInt();
                if (readLittleEndianInt2 == 829973609) {
                    this.state = 5;
                    this.idx1BodySize = readLittleEndianInt3;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt3);
                }
                return 0;
            case 5:
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(this.idx1BodySize);
                extractorInput.readFully(parsableByteArray2.getData(), 0, this.idx1BodySize);
                parseIdx1Body(parsableByteArray2);
                this.state = 6;
                this.pendingReposition = this.moviStart;
                return 0;
            case 6:
                return readMoviChunks(extractorInput);
            default:
                throw new AssertionError();
        }
    }

    public void seek(long j2, long j3) {
        this.pendingReposition = -1;
        this.currentChunkReader = null;
        for (ChunkReader seekToPosition : this.chunkReaders) {
            seekToPosition.seekToPosition(j2);
        }
        if (j2 != 0) {
            this.state = 6;
        } else if (this.chunkReaders.length == 0) {
            this.state = 0;
        } else {
            this.state = 3;
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        extractorInput.peekFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        if (this.scratch.readLittleEndianInt() != 1179011410) {
            return false;
        }
        this.scratch.skipBytes(4);
        if (this.scratch.readLittleEndianInt() == 541677121) {
            return true;
        }
        return false;
    }

    public void release() {
    }
}
