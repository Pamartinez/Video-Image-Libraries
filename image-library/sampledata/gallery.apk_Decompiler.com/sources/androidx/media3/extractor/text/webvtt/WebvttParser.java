package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WebvttParser implements SubtitleParser {
    private final WebvttCssParser cssParser = new WebvttCssParser();
    private final ParsableByteArray parsableWebvttData = new ParsableByteArray();

    private static int getNextEvent(ParsableByteArray parsableByteArray) {
        int i2 = -1;
        int i7 = 0;
        while (i2 == -1) {
            i7 = parsableByteArray.getPosition();
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                i2 = 0;
            } else if ("STYLE".equals(readLine)) {
                i2 = 2;
            } else if (readLine.startsWith("NOTE")) {
                i2 = 1;
            } else {
                i2 = 3;
            }
        }
        parsableByteArray.setPosition(i7);
        return i2;
    }

    private static void skipComment(ParsableByteArray parsableByteArray) {
        do {
        } while (!TextUtils.isEmpty(parsableByteArray.readLine()));
    }

    public int getCueReplacementBehavior() {
        return 1;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        WebvttCueInfo parseCue;
        this.parsableWebvttData.reset(bArr, i7 + i2);
        this.parsableWebvttData.setPosition(i2);
        ArrayList arrayList = new ArrayList();
        try {
            WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
            do {
            } while (!TextUtils.isEmpty(this.parsableWebvttData.readLine()));
            ArrayList arrayList2 = new ArrayList();
            while (true) {
                int nextEvent = getNextEvent(this.parsableWebvttData);
                if (nextEvent == 0) {
                    LegacySubtitleUtil.toCuesWithTiming(new WebvttSubtitle(arrayList2), outputOptions, consumer);
                    return;
                } else if (nextEvent == 1) {
                    skipComment(this.parsableWebvttData);
                } else if (nextEvent == 2) {
                    if (arrayList2.isEmpty()) {
                        this.parsableWebvttData.readLine();
                        arrayList.addAll(this.cssParser.parseBlock(this.parsableWebvttData));
                    } else {
                        throw new IllegalArgumentException("A style block was found after the first cue.");
                    }
                } else if (nextEvent == 3 && (parseCue = WebvttCueParser.parseCue(this.parsableWebvttData, arrayList)) != null) {
                    arrayList2.add(parseCue);
                }
            }
        } catch (ParserException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
