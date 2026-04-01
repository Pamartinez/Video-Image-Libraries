package androidx.media3.extractor.text.pgs;

import android.graphics.Bitmap;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PgsParser implements SubtitleParser {
    private final ParsableByteArray buffer = new ParsableByteArray();
    private final CueBuilder cueBuilder = new CueBuilder();
    private final ParsableByteArray inflatedBuffer = new ParsableByteArray();
    private Inflater inflater;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CueBuilder {
        private final ParsableByteArray bitmapData = new ParsableByteArray();
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private final int[] colors = new int[256];
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;

        /* access modifiers changed from: private */
        public void parseBitmapSection(ParsableByteArray parsableByteArray, int i2) {
            boolean z;
            int readUnsignedInt24;
            if (i2 >= 4) {
                parsableByteArray.skipBytes(3);
                if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                int i7 = i2 - 4;
                if (z) {
                    if (i7 >= 7 && (readUnsignedInt24 = parsableByteArray.readUnsignedInt24()) >= 4) {
                        this.bitmapWidth = parsableByteArray.readUnsignedShort();
                        this.bitmapHeight = parsableByteArray.readUnsignedShort();
                        this.bitmapData.reset(readUnsignedInt24 - 4);
                        i7 = i2 - 11;
                    } else {
                        return;
                    }
                }
                int position = this.bitmapData.getPosition();
                int limit = this.bitmapData.limit();
                if (position < limit && i7 > 0) {
                    int min = Math.min(i7, limit - position);
                    parsableByteArray.readBytes(this.bitmapData.getData(), position, min);
                    this.bitmapData.setPosition(position + min);
                }
            }
        }

        /* access modifiers changed from: private */
        public void parseIdentifierSection(ParsableByteArray parsableByteArray, int i2) {
            if (i2 >= 19) {
                this.planeWidth = parsableByteArray.readUnsignedShort();
                this.planeHeight = parsableByteArray.readUnsignedShort();
                parsableByteArray.skipBytes(11);
                this.bitmapX = parsableByteArray.readUnsignedShort();
                this.bitmapY = parsableByteArray.readUnsignedShort();
            }
        }

        /* access modifiers changed from: private */
        public void parsePaletteSection(ParsableByteArray parsableByteArray, int i2) {
            if (i2 % 5 == 2) {
                ParsableByteArray parsableByteArray2 = parsableByteArray;
                parsableByteArray2.skipBytes(2);
                Arrays.fill(this.colors, 0);
                int i7 = i2 / 5;
                for (int i8 = 0; i8 < i7; i8++) {
                    int readUnsignedByte = parsableByteArray2.readUnsignedByte();
                    int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
                    int readUnsignedByte3 = parsableByteArray2.readUnsignedByte();
                    int readUnsignedByte4 = parsableByteArray2.readUnsignedByte();
                    double d = (double) readUnsignedByte2;
                    double d2 = (double) (readUnsignedByte3 - 128);
                    double d3 = (double) (readUnsignedByte4 - 128);
                    this.colors[readUnsignedByte] = (Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, (int) ScoverState.TYPE_NFC_SMART_COVER) << 8) | (parsableByteArray2.readUnsignedByte() << 24) | (Util.constrainValue((int) ((1.402d * d2) + d), 0, (int) ScoverState.TYPE_NFC_SMART_COVER) << 16) | Util.constrainValue((int) ((d3 * 1.772d) + d), 0, (int) ScoverState.TYPE_NFC_SMART_COVER);
                }
                this.colorsSet = true;
            }
        }

        public Cue build() {
            int i2;
            int i7;
            int i8;
            if (this.planeWidth == 0 || this.planeHeight == 0 || this.bitmapWidth == 0 || this.bitmapHeight == 0 || this.bitmapData.limit() == 0 || this.bitmapData.getPosition() != this.bitmapData.limit() || !this.colorsSet) {
                return null;
            }
            this.bitmapData.setPosition(0);
            int i10 = this.bitmapWidth * this.bitmapHeight;
            int[] iArr = new int[i10];
            int i11 = 0;
            while (i11 < i10) {
                int readUnsignedByte = this.bitmapData.readUnsignedByte();
                if (readUnsignedByte != 0) {
                    i8 = i11 + 1;
                    iArr[i11] = this.colors[readUnsignedByte];
                } else {
                    int readUnsignedByte2 = this.bitmapData.readUnsignedByte();
                    if (readUnsignedByte2 != 0) {
                        if ((readUnsignedByte2 & 64) == 0) {
                            i2 = readUnsignedByte2 & 63;
                        } else {
                            i2 = ((readUnsignedByte2 & 63) << 8) | this.bitmapData.readUnsignedByte();
                        }
                        if ((readUnsignedByte2 & 128) == 0) {
                            i7 = this.colors[0];
                        } else {
                            i7 = this.colors[this.bitmapData.readUnsignedByte()];
                        }
                        i8 = i2 + i11;
                        Arrays.fill(iArr, i11, i8, i7);
                    }
                }
                i11 = i8;
            }
            return new Cue.Builder().setBitmap(Bitmap.createBitmap(iArr, this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888)).setPosition(((float) this.bitmapX) / ((float) this.planeWidth)).setPositionAnchor(0).setLine(((float) this.bitmapY) / ((float) this.planeHeight), 0).setLineAnchor(0).setSize(((float) this.bitmapWidth) / ((float) this.planeWidth)).setBitmapHeight(((float) this.bitmapHeight) / ((float) this.planeHeight)).build();
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }

    private static Cue readNextSection(ParsableByteArray parsableByteArray, CueBuilder cueBuilder2) {
        int limit = parsableByteArray.limit();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition() + readUnsignedShort;
        Cue cue = null;
        if (position > limit) {
            parsableByteArray.setPosition(limit);
            return null;
        }
        if (readUnsignedByte != 128) {
            switch (readUnsignedByte) {
                case 20:
                    cueBuilder2.parsePaletteSection(parsableByteArray, readUnsignedShort);
                    break;
                case 21:
                    cueBuilder2.parseBitmapSection(parsableByteArray, readUnsignedShort);
                    break;
                case 22:
                    cueBuilder2.parseIdentifierSection(parsableByteArray, readUnsignedShort);
                    break;
            }
        } else {
            cue = cueBuilder2.build();
            cueBuilder2.reset();
        }
        parsableByteArray.setPosition(position);
        return cue;
    }

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        this.buffer.reset(bArr, i7 + i2);
        this.buffer.setPosition(i2);
        if (this.inflater == null) {
            this.inflater = new Inflater();
        }
        if (Util.maybeInflate(this.buffer, this.inflatedBuffer, this.inflater)) {
            this.buffer.reset(this.inflatedBuffer.getData(), this.inflatedBuffer.limit());
        }
        this.cueBuilder.reset();
        ArrayList arrayList = new ArrayList();
        while (this.buffer.bytesLeft() >= 3) {
            Cue readNextSection = readNextSection(this.buffer, this.cueBuilder);
            if (readNextSection != null) {
                arrayList.add(readNextSection);
            }
        }
        consumer.accept(new CuesWithTiming(arrayList, -9223372036854775807L, -9223372036854775807L));
    }
}
