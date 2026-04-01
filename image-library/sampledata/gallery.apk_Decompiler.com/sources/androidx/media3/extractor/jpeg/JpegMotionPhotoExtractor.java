package androidx.media3.extractor.jpeg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import com.adobe.internal.xmp.XMPConst;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class JpegMotionPhotoExtractor implements Extractor {
    private ExtractorOutput extractorOutput;
    private ExtractorInput lastExtractorInput;
    private int marker;
    private MotionPhotoMetadata motionPhotoMetadata;
    private Mp4Extractor mp4Extractor;
    private StartOffsetExtractorInput mp4ExtractorStartOffsetExtractorInput;
    private long mp4StartPosition = -1;
    private final ParsableByteArray scratch = new ParsableByteArray(2);
    private int segmentLength;
    private int state;

    private void advancePeekPositionToNextSegment(ExtractorInput extractorInput) {
        this.scratch.reset(2);
        extractorInput.peekFully(this.scratch.getData(), 0, 2);
        extractorInput.advancePeekPosition(this.scratch.readUnsignedShort() - 2);
    }

    private void endReading() {
        ((ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)).endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        this.state = 6;
    }

    private static MotionPhotoMetadata getMotionPhotoMetadata(String str, long j2) {
        MotionPhotoDescription parse;
        if (j2 == -1 || (parse = XmpMotionPhotoDescriptionParser.parse(str)) == null) {
            return null;
        }
        return parse.getMotionPhotoMetadata(j2);
    }

    private void outputImageTrack(MotionPhotoMetadata motionPhotoMetadata2) {
        ((ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)).track(1024, 4).format(new Format.Builder().setContainerMimeType("image/jpeg").setMetadata(new Metadata(motionPhotoMetadata2)).build());
    }

    private int peekMarker(ExtractorInput extractorInput) {
        this.scratch.reset(2);
        extractorInput.peekFully(this.scratch.getData(), 0, 2);
        return this.scratch.readUnsignedShort();
    }

    private void readMarker(ExtractorInput extractorInput) {
        this.scratch.reset(2);
        extractorInput.readFully(this.scratch.getData(), 0, 2);
        int readUnsignedShort = this.scratch.readUnsignedShort();
        this.marker = readUnsignedShort;
        if (readUnsignedShort == 65498) {
            if (this.mp4StartPosition != -1) {
                this.state = 4;
            } else {
                endReading();
            }
        } else if ((readUnsignedShort < 65488 || readUnsignedShort > 65497) && readUnsignedShort != 65281) {
            this.state = 1;
        }
    }

    private void readSegment(ExtractorInput extractorInput) {
        String readNullTerminatedString;
        if (this.marker == 65505) {
            ParsableByteArray parsableByteArray = new ParsableByteArray(this.segmentLength);
            extractorInput.readFully(parsableByteArray.getData(), 0, this.segmentLength);
            if (this.motionPhotoMetadata == null && XMPConst.NS_XMP.equals(parsableByteArray.readNullTerminatedString()) && (readNullTerminatedString = parsableByteArray.readNullTerminatedString()) != null) {
                MotionPhotoMetadata motionPhotoMetadata2 = getMotionPhotoMetadata(readNullTerminatedString, extractorInput.getLength());
                this.motionPhotoMetadata = motionPhotoMetadata2;
                if (motionPhotoMetadata2 != null) {
                    this.mp4StartPosition = motionPhotoMetadata2.videoStartPosition;
                }
            }
        } else {
            extractorInput.skipFully(this.segmentLength);
        }
        this.state = 0;
    }

    private void readSegmentLength(ExtractorInput extractorInput) {
        this.scratch.reset(2);
        extractorInput.readFully(this.scratch.getData(), 0, 2);
        this.segmentLength = this.scratch.readUnsignedShort() - 2;
        this.state = 2;
    }

    private void sniffMotionPhotoVideo(ExtractorInput extractorInput) {
        if (!extractorInput.peekFully(this.scratch.getData(), 0, 1, true)) {
            endReading();
            return;
        }
        extractorInput.resetPeekPosition();
        if (this.mp4Extractor == null) {
            this.mp4Extractor = new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 8);
        }
        StartOffsetExtractorInput startOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
        this.mp4ExtractorStartOffsetExtractorInput = startOffsetExtractorInput;
        if (this.mp4Extractor.sniff(startOffsetExtractorInput)) {
            this.mp4Extractor.init(new StartOffsetExtractorOutput(this.mp4StartPosition, (ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)));
            startReadingMotionPhoto();
            return;
        }
        endReading();
    }

    private void startReadingMotionPhoto() {
        outputImageTrack((MotionPhotoMetadata) Assertions.checkNotNull(this.motionPhotoMetadata));
        this.state = 5;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        int i2 = this.state;
        if (i2 == 0) {
            readMarker(extractorInput);
            return 0;
        } else if (i2 == 1) {
            readSegmentLength(extractorInput);
            return 0;
        } else if (i2 == 2) {
            readSegment(extractorInput);
            return 0;
        } else if (i2 == 4) {
            long position = extractorInput.getPosition();
            long j2 = this.mp4StartPosition;
            if (position != j2) {
                positionHolder.position = j2;
                return 1;
            }
            sniffMotionPhotoVideo(extractorInput);
            return 0;
        } else if (i2 == 5) {
            if (this.mp4ExtractorStartOffsetExtractorInput == null || extractorInput != this.lastExtractorInput) {
                this.lastExtractorInput = extractorInput;
                this.mp4ExtractorStartOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
            }
            int read = ((Mp4Extractor) Assertions.checkNotNull(this.mp4Extractor)).read(this.mp4ExtractorStartOffsetExtractorInput, positionHolder);
            if (read == 1) {
                positionHolder.position += this.mp4StartPosition;
            }
            return read;
        } else if (i2 == 6) {
            return -1;
        } else {
            throw new IllegalStateException();
        }
    }

    public void release() {
        Mp4Extractor mp4Extractor2 = this.mp4Extractor;
        if (mp4Extractor2 != null) {
            mp4Extractor2.release();
        }
    }

    public void seek(long j2, long j3) {
        if (j2 == 0) {
            this.state = 0;
            this.mp4Extractor = null;
        } else if (this.state == 5) {
            ((Mp4Extractor) Assertions.checkNotNull(this.mp4Extractor)).seek(j2, j3);
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        if (peekMarker(extractorInput) != 65496) {
            return false;
        }
        int peekMarker = peekMarker(extractorInput);
        this.marker = peekMarker;
        if (peekMarker == 65504) {
            advancePeekPositionToNextSegment(extractorInput);
            this.marker = peekMarker(extractorInput);
        }
        if (this.marker == 65505) {
            return true;
        }
        return false;
    }
}
