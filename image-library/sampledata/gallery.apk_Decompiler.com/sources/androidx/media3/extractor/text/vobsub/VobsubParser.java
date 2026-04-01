package androidx.media3.extractor.text.vobsub;

import F2.G;
import F2.U;
import F2.y0;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VobsubParser implements SubtitleParser {
    private final CueBuilder cueBuilder;
    private final ParsableByteArray inflatedScratch = new ParsableByteArray();
    private Inflater inflater;
    private final ParsableByteArray scratch = new ParsableByteArray();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CueBuilder {
        private Rect boundingBox;
        private final int[] colors = new int[4];
        private int dataOffset0 = -1;
        private int dataOffset1 = -1;
        private boolean hasColors;
        private boolean hasPlane;
        private int[] palette;
        private int planeHeight;
        private int planeWidth;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Run {
            public int colorIndex;
            public int length;

            private Run() {
            }
        }

        private static int getColor(int[] iArr, int i2) {
            if (i2 < 0 || i2 >= iArr.length) {
                return iArr[0];
            }
            return iArr[i2];
        }

        private static int parseColor(String str) {
            try {
                return Integer.parseInt(str, 16);
            } catch (RuntimeException unused) {
                return 0;
            }
        }

        private void parseControl(int[] iArr, ParsableByteArray parsableByteArray, int i2) {
            while (parsableByteArray.getPosition() < i2 && parsableByteArray.bytesLeft() > 0) {
                switch (parsableByteArray.readUnsignedByte()) {
                    case 0:
                    case 1:
                    case 2:
                        break;
                    case 3:
                        if (parseControlColors(iArr, parsableByteArray)) {
                            break;
                        } else {
                            return;
                        }
                    case 4:
                        if (parseControlAlpha(parsableByteArray)) {
                            break;
                        } else {
                            return;
                        }
                    case 5:
                        if (parseControlArea(parsableByteArray)) {
                            break;
                        } else {
                            return;
                        }
                    case 6:
                        if (parseControlOffsets(parsableByteArray)) {
                            break;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        }

        private boolean parseControlAlpha(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 2 || !this.hasColors) {
                return false;
            }
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            int[] iArr = this.colors;
            iArr[3] = setAlpha(iArr[3], readUnsignedByte >> 4);
            int[] iArr2 = this.colors;
            iArr2[2] = setAlpha(iArr2[2], readUnsignedByte & 15);
            int[] iArr3 = this.colors;
            iArr3[1] = setAlpha(iArr3[1], readUnsignedByte2 >> 4);
            int[] iArr4 = this.colors;
            iArr4[0] = setAlpha(iArr4[0], readUnsignedByte2 & 15);
            return true;
        }

        private boolean parseControlArea(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 6) {
                return false;
            }
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            int i2 = (readUnsignedByte << 4) | (readUnsignedByte2 >> 4);
            int readUnsignedByte3 = ((readUnsignedByte2 & 15) << 8) | parsableByteArray.readUnsignedByte();
            int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
            int readUnsignedByte5 = parsableByteArray.readUnsignedByte();
            this.boundingBox = new Rect(i2, (readUnsignedByte4 << 4) | (readUnsignedByte5 >> 4), readUnsignedByte3 + 1, (parsableByteArray.readUnsignedByte() | ((readUnsignedByte5 & 15) << 8)) + 1);
            return true;
        }

        private boolean parseControlColors(int[] iArr, ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 2) {
                return false;
            }
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            this.colors[3] = getColor(iArr, readUnsignedByte >> 4);
            this.colors[2] = getColor(iArr, readUnsignedByte & 15);
            this.colors[1] = getColor(iArr, readUnsignedByte2 >> 4);
            this.colors[0] = getColor(iArr, readUnsignedByte2 & 15);
            this.hasColors = true;
            return true;
        }

        private boolean parseControlOffsets(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 4) {
                return false;
            }
            this.dataOffset0 = parsableByteArray.readUnsignedShort();
            this.dataOffset1 = parsableByteArray.readUnsignedShort();
            return true;
        }

        private void parseRleData(ParsableBitArray parsableBitArray, boolean z, Rect rect, int[] iArr) {
            int width = rect.width();
            int height = rect.height();
            int i2 = !z;
            int i7 = i2 * width;
            Run run = new Run();
            while (true) {
                int i8 = 0;
                do {
                    parseRun(parsableBitArray, width, run);
                    int min = Math.min(run.length, width - i8);
                    if (min > 0) {
                        int i10 = i7 + min;
                        Arrays.fill(iArr, i7, i10, this.colors[run.colorIndex]);
                        i8 += min;
                        i7 = i10;
                        continue;
                    }
                } while (i8 < width);
                i2 += 2;
                if (i2 < height) {
                    i7 = i2 * width;
                    parsableBitArray.byteAlign();
                } else {
                    return;
                }
            }
        }

        private static void parseRun(ParsableBitArray parsableBitArray, int i2, Run run) {
            int i7 = 1;
            int i8 = 0;
            while (i8 < i7 && i7 <= 64) {
                if (parsableBitArray.bitsLeft() < 4) {
                    run.colorIndex = -1;
                    run.length = 0;
                    return;
                }
                i8 = (i8 << 4) | parsableBitArray.readBits(4);
                i7 <<= 2;
            }
            run.colorIndex = i8 & 3;
            if (i8 >= 4) {
                i2 = i8 >> 2;
            }
            run.length = i2;
        }

        private static int setAlpha(int i2, int i7) {
            return (i2 & 16777215) | ((i7 * 17) << 24);
        }

        public Cue build(ParsableByteArray parsableByteArray) {
            Rect rect;
            if (this.palette == null || !this.hasPlane || !this.hasColors || (rect = this.boundingBox) == null || this.dataOffset0 == -1 || this.dataOffset1 == -1 || rect.width() < 2 || this.boundingBox.height() < 2) {
                return null;
            }
            Rect rect2 = this.boundingBox;
            int[] iArr = new int[(rect2.height() * rect2.width())];
            ParsableBitArray parsableBitArray = new ParsableBitArray();
            parsableByteArray.setPosition(this.dataOffset0);
            parsableBitArray.reset(parsableByteArray);
            parseRleData(parsableBitArray, true, rect2, iArr);
            parsableByteArray.setPosition(this.dataOffset1);
            parsableBitArray.reset(parsableByteArray);
            parseRleData(parsableBitArray, false, rect2, iArr);
            return new Cue.Builder().setBitmap(Bitmap.createBitmap(iArr, rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888)).setPosition(((float) rect2.left) / ((float) this.planeWidth)).setPositionAnchor(0).setLine(((float) rect2.top) / ((float) this.planeHeight), 0).setLineAnchor(0).setSize(((float) rect2.width()) / ((float) this.planeWidth)).setBitmapHeight(((float) rect2.height()) / ((float) this.planeHeight)).build();
        }

        public void parseIdx(String str) {
            for (String str2 : Util.split(str.trim(), "\\r?\\n")) {
                if (str2.startsWith("palette: ")) {
                    String[] split = Util.split(str2.substring(9), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    this.palette = new int[split.length];
                    for (int i2 = 0; i2 < split.length; i2++) {
                        this.palette[i2] = parseColor(split[i2].trim());
                    }
                } else if (str2.startsWith("size: ")) {
                    String[] split2 = Util.split(str2.substring(6).trim(), "x");
                    if (split2.length == 2) {
                        try {
                            this.planeWidth = Integer.parseInt(split2[0]);
                            this.planeHeight = Integer.parseInt(split2[1]);
                            this.hasPlane = true;
                        } catch (RuntimeException e) {
                            Log.w("VobsubParser", "Parsing IDX failed", e);
                        }
                    }
                }
            }
        }

        public void parseSpu(ParsableByteArray parsableByteArray) {
            int[] iArr = this.palette;
            if (iArr != null && this.hasPlane) {
                parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() - 2);
                parseControl(iArr, parsableByteArray, parsableByteArray.readUnsignedShort());
            }
        }

        public void reset() {
            this.hasColors = false;
            this.boundingBox = null;
            this.dataOffset0 = -1;
            this.dataOffset1 = -1;
        }
    }

    public VobsubParser(List<byte[]> list) {
        CueBuilder cueBuilder2 = new CueBuilder();
        this.cueBuilder = cueBuilder2;
        cueBuilder2.parseIdx(new String(list.get(0), StandardCharsets.UTF_8));
    }

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        y0 y0Var;
        this.scratch.reset(bArr, i7 + i2);
        this.scratch.setPosition(i2);
        Cue parse = parse();
        if (parse != null) {
            y0Var = U.B(parse);
        } else {
            G g = U.e;
            y0Var = y0.f278h;
        }
        consumer.accept(new CuesWithTiming(y0Var, -9223372036854775807L, 5000000));
    }

    private Cue parse() {
        if (this.inflater == null) {
            this.inflater = new Inflater();
        }
        if (Util.maybeInflate(this.scratch, this.inflatedScratch, this.inflater)) {
            this.scratch.reset(this.inflatedScratch.getData(), this.inflatedScratch.limit());
        }
        this.cueBuilder.reset();
        int bytesLeft = this.scratch.bytesLeft();
        if (bytesLeft < 2 || this.scratch.readUnsignedShort() != bytesLeft) {
            return null;
        }
        this.cueBuilder.parseSpu(this.scratch);
        return this.cueBuilder.build(this.scratch);
    }
}
