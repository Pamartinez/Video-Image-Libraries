package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Mp4WebvttParser implements SubtitleParser {
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    private static Cue parseVttCueBox(ParsableByteArray parsableByteArray2, int i2) {
        boolean z;
        CharSequence charSequence = null;
        Cue.Builder builder = null;
        while (i2 > 0) {
            if (i2 >= 8) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Incomplete vtt cue box header found.");
            int readInt = parsableByteArray2.readInt();
            int readInt2 = parsableByteArray2.readInt();
            int i7 = readInt - 8;
            String fromUtf8Bytes = Util.fromUtf8Bytes(parsableByteArray2.getData(), parsableByteArray2.getPosition(), i7);
            parsableByteArray2.skipBytes(i7);
            i2 = (i2 - 8) - i7;
            if (readInt2 == 1937011815) {
                builder = WebvttCueParser.parseCueSettingsList(fromUtf8Bytes);
            } else if (readInt2 == 1885436268) {
                charSequence = WebvttCueParser.parseCueText((String) null, fromUtf8Bytes.trim(), Collections.EMPTY_LIST);
            }
        }
        if (charSequence == null) {
            charSequence = "";
        }
        if (builder != null) {
            return builder.setText(charSequence).build();
        }
        return WebvttCueParser.newCueForText(charSequence);
    }

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        boolean z;
        this.parsableByteArray.reset(bArr, i7 + i2);
        this.parsableByteArray.setPosition(i2);
        ArrayList arrayList = new ArrayList();
        while (this.parsableByteArray.bytesLeft() > 0) {
            if (this.parsableByteArray.bytesLeft() >= 8) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Incomplete Mp4Webvtt Top Level box header found.");
            int readInt = this.parsableByteArray.readInt();
            if (this.parsableByteArray.readInt() == 1987343459) {
                arrayList.add(parseVttCueBox(this.parsableByteArray, readInt - 8));
            } else {
                this.parsableByteArray.skipBytes(readInt - 8);
            }
        }
        consumer.accept(new CuesWithTiming(arrayList, -9223372036854775807L, -9223372036854775807L));
    }
}
