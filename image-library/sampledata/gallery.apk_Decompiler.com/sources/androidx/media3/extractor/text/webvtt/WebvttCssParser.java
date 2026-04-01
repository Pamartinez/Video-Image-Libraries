package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ColorParser;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WebvttCssParser {
    private static final Pattern FONT_SIZE_PATTERN = Pattern.compile("^((?:[0-9]*\\.)?[0-9]+)(px|em|%)$");
    private static final Pattern VOICE_NAME_PATTERN = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private final StringBuilder stringBuilder = new StringBuilder();
    private final ParsableByteArray styleInput = new ParsableByteArray();

    private void applySelectorToStyle(WebvttCssStyle webvttCssStyle, String str) {
        if (!str.isEmpty()) {
            int indexOf = str.indexOf(91);
            if (indexOf != -1) {
                Matcher matcher = VOICE_NAME_PATTERN.matcher(str.substring(indexOf));
                if (matcher.matches()) {
                    webvttCssStyle.setTargetVoice((String) Assertions.checkNotNull(matcher.group(1)));
                }
                str = str.substring(0, indexOf);
            }
            String[] split = Util.split(str, "\\.");
            String str2 = split[0];
            int indexOf2 = str2.indexOf(35);
            if (indexOf2 != -1) {
                webvttCssStyle.setTargetTagName(str2.substring(0, indexOf2));
                webvttCssStyle.setTargetId(str2.substring(indexOf2 + 1));
            } else {
                webvttCssStyle.setTargetTagName(str2);
            }
            if (split.length > 1) {
                webvttCssStyle.setTargetClasses((String[]) Util.nullSafeArrayCopyOfRange(split, 1, split.length));
            }
        }
    }

    private static boolean maybeSkipComment(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        if (position + 2 > limit) {
            return false;
        }
        int i2 = position + 1;
        if (data[position] != 47) {
            return false;
        }
        int i7 = position + 2;
        if (data[i2] != 42) {
            return false;
        }
        while (true) {
            int i8 = i7 + 1;
            if (i8 >= limit) {
                parsableByteArray.skipBytes(limit - parsableByteArray.getPosition());
                return true;
            } else if (((char) data[i7]) == '*' && ((char) data[i8]) == '/') {
                i7 += 2;
                limit = i7;
            } else {
                i7 = i8;
            }
        }
    }

    private static boolean maybeSkipWhitespace(ParsableByteArray parsableByteArray) {
        char peekCharAtPosition = peekCharAtPosition(parsableByteArray, parsableByteArray.getPosition());
        if (peekCharAtPosition != 9 && peekCharAtPosition != 10 && peekCharAtPosition != 12 && peekCharAtPosition != 13 && peekCharAtPosition != ' ') {
            return false;
        }
        parsableByteArray.skipBytes(1);
        return true;
    }

    private static void parseFontSize(String str, WebvttCssStyle webvttCssStyle) {
        Matcher matcher = FONT_SIZE_PATTERN.matcher(k.S(str));
        if (!matcher.matches()) {
            Log.w("WebvttCssParser", "Invalid font-size: '" + str + "'.");
            return;
        }
        String str2 = (String) Assertions.checkNotNull(matcher.group(2));
        str2.getClass();
        char c5 = 65535;
        switch (str2.hashCode()) {
            case 37:
                if (str2.equals("%")) {
                    c5 = 0;
                    break;
                }
                break;
            case 3240:
                if (str2.equals("em")) {
                    c5 = 1;
                    break;
                }
                break;
            case 3592:
                if (str2.equals("px")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                webvttCssStyle.setFontSizeUnit(3);
                break;
            case 1:
                webvttCssStyle.setFontSizeUnit(2);
                break;
            case 2:
                webvttCssStyle.setFontSizeUnit(1);
                break;
            default:
                throw new IllegalStateException();
        }
        webvttCssStyle.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1))));
    }

    private static String parseIdentifier(ParsableByteArray parsableByteArray, StringBuilder sb2) {
        boolean z = false;
        sb2.setLength(0);
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit && !z) {
            char c5 = (char) parsableByteArray.getData()[position];
            if ((c5 < 'A' || c5 > 'Z') && ((c5 < 'a' || c5 > 'z') && !((c5 >= '0' && c5 <= '9') || c5 == '#' || c5 == '-' || c5 == '.' || c5 == '_'))) {
                z = true;
            } else {
                position++;
                sb2.append(c5);
            }
        }
        parsableByteArray.skipBytes(position - parsableByteArray.getPosition());
        return sb2.toString();
    }

    public static String parseNextToken(ParsableByteArray parsableByteArray, StringBuilder sb2) {
        skipWhitespaceAndComments(parsableByteArray);
        if (parsableByteArray.bytesLeft() == 0) {
            return null;
        }
        String parseIdentifier = parseIdentifier(parsableByteArray, sb2);
        if (!parseIdentifier.isEmpty()) {
            return parseIdentifier;
        }
        return "" + ((char) parsableByteArray.readUnsignedByte());
    }

    private static String parsePropertyValue(ParsableByteArray parsableByteArray, StringBuilder sb2) {
        StringBuilder sb3 = new StringBuilder();
        boolean z = false;
        while (!z) {
            int position = parsableByteArray.getPosition();
            String parseNextToken = parseNextToken(parsableByteArray, sb2);
            if (parseNextToken == null) {
                return null;
            }
            if ("}".equals(parseNextToken) || ";".equals(parseNextToken)) {
                parsableByteArray.setPosition(position);
                z = true;
            } else {
                sb3.append(parseNextToken);
            }
        }
        return sb3.toString();
    }

    private static String parseSelector(ParsableByteArray parsableByteArray, StringBuilder sb2) {
        String str;
        skipWhitespaceAndComments(parsableByteArray);
        if (parsableByteArray.bytesLeft() < 5 || !"::cue".equals(parsableByteArray.readString(5))) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        String parseNextToken = parseNextToken(parsableByteArray, sb2);
        if (parseNextToken == null) {
            return null;
        }
        if ("{".equals(parseNextToken)) {
            parsableByteArray.setPosition(position);
            return "";
        }
        if ("(".equals(parseNextToken)) {
            str = readCueTarget(parsableByteArray);
        } else {
            str = null;
        }
        if (!")".equals(parseNextToken(parsableByteArray, sb2))) {
            return null;
        }
        return str;
    }

    private static void parseStyleDeclaration(ParsableByteArray parsableByteArray, WebvttCssStyle webvttCssStyle, StringBuilder sb2) {
        skipWhitespaceAndComments(parsableByteArray);
        String parseIdentifier = parseIdentifier(parsableByteArray, sb2);
        if (!parseIdentifier.isEmpty() && NumericEnum.SEP.equals(parseNextToken(parsableByteArray, sb2))) {
            skipWhitespaceAndComments(parsableByteArray);
            String parsePropertyValue = parsePropertyValue(parsableByteArray, sb2);
            if (parsePropertyValue != null && !parsePropertyValue.isEmpty()) {
                int position = parsableByteArray.getPosition();
                String parseNextToken = parseNextToken(parsableByteArray, sb2);
                if (!";".equals(parseNextToken)) {
                    if ("}".equals(parseNextToken)) {
                        parsableByteArray.setPosition(position);
                    } else {
                        return;
                    }
                }
                if ("color".equals(parseIdentifier)) {
                    webvttCssStyle.setFontColor(ColorParser.parseCssColor(parsePropertyValue));
                } else if ("background-color".equals(parseIdentifier)) {
                    webvttCssStyle.setBackgroundColor(ColorParser.parseCssColor(parsePropertyValue));
                } else {
                    boolean z = true;
                    if ("ruby-position".equals(parseIdentifier)) {
                        if ("over".equals(parsePropertyValue)) {
                            webvttCssStyle.setRubyPosition(1);
                        } else if ("under".equals(parsePropertyValue)) {
                            webvttCssStyle.setRubyPosition(2);
                        }
                    } else if ("text-combine-upright".equals(parseIdentifier)) {
                        if (!"all".equals(parsePropertyValue) && !parsePropertyValue.startsWith("digits")) {
                            z = false;
                        }
                        webvttCssStyle.setCombineUpright(z);
                    } else if ("text-decoration".equals(parseIdentifier)) {
                        if ("underline".equals(parsePropertyValue)) {
                            webvttCssStyle.setUnderline(true);
                        }
                    } else if ("font-family".equals(parseIdentifier)) {
                        webvttCssStyle.setFontFamily(parsePropertyValue);
                    } else if ("font-weight".equals(parseIdentifier)) {
                        if ("bold".equals(parsePropertyValue)) {
                            webvttCssStyle.setBold(true);
                        }
                    } else if ("font-style".equals(parseIdentifier)) {
                        if ("italic".equals(parsePropertyValue)) {
                            webvttCssStyle.setItalic(true);
                        }
                    } else if ("font-size".equals(parseIdentifier)) {
                        parseFontSize(parsePropertyValue, webvttCssStyle);
                    }
                }
            }
        }
    }

    private static char peekCharAtPosition(ParsableByteArray parsableByteArray, int i2) {
        return (char) parsableByteArray.getData()[i2];
    }

    private static String readCueTarget(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        boolean z = false;
        while (position < limit && !z) {
            int i2 = position + 1;
            if (((char) parsableByteArray.getData()[position]) == ')') {
                z = true;
            } else {
                z = false;
            }
            position = i2;
        }
        return parsableByteArray.readString((position - 1) - parsableByteArray.getPosition()).trim();
    }

    public static void skipStyleBlock(ParsableByteArray parsableByteArray) {
        do {
        } while (!TextUtils.isEmpty(parsableByteArray.readLine()));
    }

    public static void skipWhitespaceAndComments(ParsableByteArray parsableByteArray) {
        while (true) {
            boolean z = true;
            while (parsableByteArray.bytesLeft() > 0 && z) {
                if (!maybeSkipWhitespace(parsableByteArray) && !maybeSkipComment(parsableByteArray)) {
                    z = false;
                }
            }
            return;
        }
    }

    public List<WebvttCssStyle> parseBlock(ParsableByteArray parsableByteArray) {
        boolean z;
        this.stringBuilder.setLength(0);
        int position = parsableByteArray.getPosition();
        skipStyleBlock(parsableByteArray);
        this.styleInput.reset(parsableByteArray.getData(), parsableByteArray.getPosition());
        this.styleInput.setPosition(position);
        ArrayList arrayList = new ArrayList();
        while (true) {
            String parseSelector = parseSelector(this.styleInput, this.stringBuilder);
            if (parseSelector == null || !"{".equals(parseNextToken(this.styleInput, this.stringBuilder))) {
                return arrayList;
            }
            WebvttCssStyle webvttCssStyle = new WebvttCssStyle();
            applySelectorToStyle(webvttCssStyle, parseSelector);
            String str = null;
            boolean z3 = false;
            while (!z3) {
                int position2 = this.styleInput.getPosition();
                String parseNextToken = parseNextToken(this.styleInput, this.stringBuilder);
                if (parseNextToken == null || "}".equals(parseNextToken)) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    this.styleInput.setPosition(position2);
                    parseStyleDeclaration(this.styleInput, webvttCssStyle, this.stringBuilder);
                }
                str = parseNextToken;
                z3 = z;
            }
            if ("}".equals(str)) {
                arrayList.add(webvttCssStyle);
            }
        }
        return arrayList;
    }
}
