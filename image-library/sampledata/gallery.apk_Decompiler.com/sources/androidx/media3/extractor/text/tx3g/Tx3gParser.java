package androidx.media3.extractor.text.tx3g;

import A.a;
import F2.G;
import F2.U;
import F2.y0;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Tx3gParser implements SubtitleParser {
    private final int calculatedVideoTrackHeight;
    private final boolean customVerticalPlacement;
    private final int defaultColorRgba;
    private final int defaultFontFace;
    private final String defaultFontFamily;
    private final float defaultVerticalPlacement;
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    public Tx3gParser(List<byte[]> list) {
        String str = "sans-serif";
        boolean z = false;
        if (list.size() == 1 && (list.get(0).length == 48 || list.get(0).length == 53)) {
            byte[] bArr = list.get(0);
            this.defaultFontFace = bArr[24];
            this.defaultColorRgba = ((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
            this.defaultFontFamily = "Serif".equals(Util.fromUtf8Bytes(bArr, 43, bArr.length - 43)) ? "serif" : str;
            int i2 = bArr[25] * 20;
            this.calculatedVideoTrackHeight = i2;
            z = (bArr[0] & 32) != 0 ? true : z;
            this.customVerticalPlacement = z;
            if (z) {
                this.defaultVerticalPlacement = Util.constrainValue(((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) i2), 0.0f, 0.95f);
            } else {
                this.defaultVerticalPlacement = 0.85f;
            }
        } else {
            this.defaultFontFace = 0;
            this.defaultColorRgba = -1;
            this.defaultFontFamily = str;
            this.customVerticalPlacement = false;
            this.defaultVerticalPlacement = 0.85f;
            this.calculatedVideoTrackHeight = -1;
        }
    }

    private void applyStyleRecord(ParsableByteArray parsableByteArray2, SpannableStringBuilder spannableStringBuilder) {
        boolean z;
        if (parsableByteArray2.bytesLeft() >= 12) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray2.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        parsableByteArray2.skipBytes(1);
        int readInt = parsableByteArray2.readInt();
        if (readUnsignedShort2 > spannableStringBuilder.length()) {
            StringBuilder o2 = C0086a.o(readUnsignedShort2, "Truncating styl end (", ") to cueText.length() (");
            o2.append(spannableStringBuilder.length());
            o2.append(").");
            Log.w("Tx3gParser", o2.toString());
            readUnsignedShort2 = spannableStringBuilder.length();
        }
        int i2 = readUnsignedShort2;
        if (readUnsignedShort >= i2) {
            Log.w("Tx3gParser", a.d(readUnsignedShort, i2, "Ignoring styl with start (", ") >= end (", ")."));
            return;
        }
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        attachFontFace(spannableStringBuilder2, readUnsignedByte, this.defaultFontFace, readUnsignedShort, i2, 0);
        attachColor(spannableStringBuilder2, readInt, this.defaultColorRgba, readUnsignedShort, i2, 0);
    }

    private static void attachColor(SpannableStringBuilder spannableStringBuilder, int i2, int i7, int i8, int i10, int i11) {
        if (i2 != i7) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i2 >>> 8) | ((i2 & ScoverState.TYPE_NFC_SMART_COVER) << 24)), i8, i10, i11 | 33);
        }
    }

    private static void attachFontFace(SpannableStringBuilder spannableStringBuilder, int i2, int i7, int i8, int i10, int i11) {
        boolean z;
        boolean z3;
        if (i2 != i7) {
            int i12 = i11 | 33;
            boolean z7 = true;
            if ((i2 & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((i2 & 2) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z) {
                if (z3) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i8, i10, i12);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i8, i10, i12);
                }
            } else if (z3) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i8, i10, i12);
            }
            if ((i2 & 4) == 0) {
                z7 = false;
            }
            if (z7) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i8, i10, i12);
            }
            if (!z7 && !z && !z3) {
                spannableStringBuilder.setSpan(new StyleSpan(0), i8, i10, i12);
            }
        }
    }

    private static void attachFontFamily(SpannableStringBuilder spannableStringBuilder, String str, int i2, int i7) {
        if (str != "sans-serif") {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i2, i7, 16711713);
        }
    }

    private static String readSubtitleText(ParsableByteArray parsableByteArray2) {
        boolean z;
        if (parsableByteArray2.bytesLeft() >= 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        if (readUnsignedShort == 0) {
            return "";
        }
        int position = parsableByteArray2.getPosition();
        Charset readUtfCharsetFromBom = parsableByteArray2.readUtfCharsetFromBom();
        int position2 = readUnsignedShort - (parsableByteArray2.getPosition() - position);
        if (readUtfCharsetFromBom == null) {
            readUtfCharsetFromBom = StandardCharsets.UTF_8;
        }
        return parsableByteArray2.readString(position2, readUtfCharsetFromBom);
    }

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        this.parsableByteArray.reset(bArr, i7 + i2);
        this.parsableByteArray.setPosition(i2);
        String readSubtitleText = readSubtitleText(this.parsableByteArray);
        if (readSubtitleText.isEmpty()) {
            G g = U.e;
            consumer.accept(new CuesWithTiming(y0.f278h, -9223372036854775807L, -9223372036854775807L));
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(readSubtitleText);
        attachFontFace(spannableStringBuilder, this.defaultFontFace, 0, 0, spannableStringBuilder.length(), 16711680);
        attachColor(spannableStringBuilder, this.defaultColorRgba, -1, 0, spannableStringBuilder.length(), 16711680);
        attachFontFamily(spannableStringBuilder, this.defaultFontFamily, 0, spannableStringBuilder.length());
        float f = this.defaultVerticalPlacement;
        while (this.parsableByteArray.bytesLeft() >= 8) {
            int position = this.parsableByteArray.getPosition();
            int readInt = this.parsableByteArray.readInt();
            int readInt2 = this.parsableByteArray.readInt();
            boolean z = true;
            if (readInt2 == 1937013100) {
                if (this.parsableByteArray.bytesLeft() < 2) {
                    z = false;
                }
                Assertions.checkArgument(z);
                int readUnsignedShort = this.parsableByteArray.readUnsignedShort();
                for (int i8 = 0; i8 < readUnsignedShort; i8++) {
                    applyStyleRecord(this.parsableByteArray, spannableStringBuilder);
                }
            } else if (readInt2 == 1952608120 && this.customVerticalPlacement) {
                if (this.parsableByteArray.bytesLeft() < 2) {
                    z = false;
                }
                Assertions.checkArgument(z);
                f = Util.constrainValue(((float) this.parsableByteArray.readUnsignedShort()) / ((float) this.calculatedVideoTrackHeight), 0.0f, 0.95f);
            }
            this.parsableByteArray.setPosition(position + readInt);
        }
        consumer.accept(new CuesWithTiming(U.B(new Cue.Builder().setText(spannableStringBuilder).setLine(f, 0).setLineAnchor(0).build()), -9223372036854775807L, -9223372036854775807L));
    }
}
