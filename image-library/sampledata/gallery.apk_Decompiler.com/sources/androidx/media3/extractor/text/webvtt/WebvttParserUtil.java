package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WebvttParserUtil {
    private static final Pattern COMMENT = Pattern.compile("^NOTE([ \t].*)?$");

    public static boolean isWebvttHeaderLine(ParsableByteArray parsableByteArray) {
        String readLine = parsableByteArray.readLine();
        if (readLine == null || !readLine.startsWith("WEBVTT")) {
            return false;
        }
        return true;
    }

    public static float parsePercentage(String str) {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static long parseTimestampUs(String str) {
        String[] splitAtFirst = Util.splitAtFirst(str, "\\.");
        long j2 = 0;
        for (String parseLong : Util.split(splitAtFirst[0], NumericEnum.SEP)) {
            j2 = (j2 * 60) + Long.parseLong(parseLong);
        }
        long j3 = j2 * 1000;
        if (splitAtFirst.length == 2) {
            String trim = splitAtFirst[1].trim();
            if (trim.length() == 3) {
                j3 += Long.parseLong(trim);
            } else {
                throw new IllegalArgumentException("Expected 3 decimal places, got: ".concat(trim));
            }
        }
        return j3 * 1000;
    }

    public static void validateWebvttHeaderLine(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        if (!isWebvttHeaderLine(parsableByteArray)) {
            parsableByteArray.setPosition(position);
            throw ParserException.createForMalformedContainer("Expected WEBVTT. Got " + parsableByteArray.readLine(), (Throwable) null);
        }
    }
}
