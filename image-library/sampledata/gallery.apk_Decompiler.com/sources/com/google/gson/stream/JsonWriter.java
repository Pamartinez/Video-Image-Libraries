package com.google.gson.stream;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private static final Pattern VALID_JSON_NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    private String deferredName;
    private String formattedColon;
    private String formattedComma;
    private FormattingStyle formattingStyle;
    private boolean htmlSafe;
    private final Writer out;
    private boolean serializeNulls;
    private int[] stack = new int[32];
    private int stackSize = 0;
    private Strictness strictness;
    private boolean usesEmptyNewlineAndIndent;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            REPLACEMENT_CHARS[i2] = String.format("\\u%04x", new Object[]{Integer.valueOf(i2)});
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        HTML_SAFE_REPLACEMENT_CHARS = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        push(6);
        this.strictness = Strictness.LEGACY_STRICT;
        this.serializeNulls = true;
        Objects.requireNonNull(writer, "out == null");
        this.out = writer;
        setFormattingStyle(FormattingStyle.COMPACT);
    }

    private static boolean alwaysCreatesValidJsonNumber(Class<? extends Number> cls) {
        if (cls == Integer.class || cls == Long.class || cls == Byte.class || cls == Short.class || cls == BigDecimal.class || cls == BigInteger.class || cls == AtomicInteger.class || cls == AtomicLong.class) {
            return true;
        }
        return false;
    }

    private void beforeName() {
        int peek = peek();
        if (peek == 5) {
            this.out.write(this.formattedComma);
        } else if (peek != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() {
        int peek = peek();
        if (peek == 1) {
            replaceTop(2);
            newline();
        } else if (peek == 2) {
            this.out.append(this.formattedComma);
            newline();
        } else if (peek != 4) {
            if (peek != 6) {
                if (peek != 7) {
                    throw new IllegalStateException("Nesting problem.");
                } else if (this.strictness != Strictness.LENIENT) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            replaceTop(7);
        } else {
            this.out.append(this.formattedColon);
            replaceTop(5);
        }
    }

    private JsonWriter closeScope(int i2, int i7, char c5) {
        int peek = peek();
        if (peek != i7 && peek != i2) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName == null) {
            this.stackSize--;
            if (peek == i7) {
                newline();
            }
            this.out.write(c5);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
    }

    private void newline() {
        if (!this.usesEmptyNewlineAndIndent) {
            this.out.write(this.formattingStyle.getNewline());
            int i2 = this.stackSize;
            for (int i7 = 1; i7 < i2; i7++) {
                this.out.write(this.formattingStyle.getIndent());
            }
        }
    }

    private JsonWriter openScope(int i2, char c5) {
        beforeValue();
        push(i2);
        this.out.write(c5);
        return this;
    }

    private int peek() {
        int i2 = this.stackSize;
        if (i2 != 0) {
            return this.stack[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void push(int i2) {
        int i7 = this.stackSize;
        int[] iArr = this.stack;
        if (i7 == iArr.length) {
            this.stack = Arrays.copyOf(iArr, i7 * 2);
        }
        int[] iArr2 = this.stack;
        int i8 = this.stackSize;
        this.stackSize = i8 + 1;
        iArr2[i8] = i2;
    }

    private void replaceTop(int i2) {
        this.stack[this.stackSize - 1] = i2;
    }

    private void string(String str) {
        String[] strArr;
        String str2;
        if (this.htmlSafe) {
            strArr = HTML_SAFE_REPLACEMENT_CHARS;
        } else {
            strArr = REPLACEMENT_CHARS;
        }
        this.out.write(34);
        int length = str.length();
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            char charAt = str.charAt(i7);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i2 < i7) {
                this.out.write(str, i2, i7 - i2);
            }
            this.out.write(str2);
            i2 = i7 + 1;
        }
        if (i2 < length) {
            this.out.write(str, i2, length - i2);
        }
        this.out.write(34);
    }

    private void writeDeferredName() {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter beginArray() {
        writeDeferredName();
        return openScope(1, '[');
    }

    public JsonWriter beginObject() {
        writeDeferredName();
        return openScope(3, '{');
    }

    public void close() {
        this.out.close();
        int i2 = this.stackSize;
        if (i2 > 1 || (i2 == 1 && this.stack[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    public JsonWriter endArray() {
        return closeScope(1, 2, ']');
    }

    public JsonWriter endObject() {
        return closeScope(3, 5, '}');
    }

    public void flush() {
        if (this.stackSize != 0) {
            this.out.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final FormattingStyle getFormattingStyle() {
        return this.formattingStyle;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final Strictness getStrictness() {
        return this.strictness;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        if (this.strictness == Strictness.LENIENT) {
            return true;
        }
        return false;
    }

    public JsonWriter jsonValue(String str) {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.append(str);
        return this;
    }

    public JsonWriter name(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.deferredName == null) {
            int peek = peek();
            if (peek == 3 || peek == 5) {
                this.deferredName = str;
                return this;
            }
            throw new IllegalStateException("Please begin an object before writing a name.");
        }
        throw new IllegalStateException("Already wrote a name, expecting a value.");
    }

    public JsonWriter nullValue() {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue();
        this.out.write("null");
        return this;
    }

    public final void setFormattingStyle(FormattingStyle formattingStyle2) {
        boolean z;
        Objects.requireNonNull(formattingStyle2);
        this.formattingStyle = formattingStyle2;
        this.formattedComma = GlobalPostProcInternalPPInterface.SPLIT_REGEX;
        if (formattingStyle2.usesSpaceAfterSeparators()) {
            this.formattedColon = ": ";
            if (this.formattingStyle.getNewline().isEmpty()) {
                this.formattedComma = ArcCommonLog.TAG_COMMA;
            }
        } else {
            this.formattedColon = NumericEnum.SEP;
        }
        if (!this.formattingStyle.getNewline().isEmpty() || !this.formattingStyle.getIndent().isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        this.usesEmptyNewlineAndIndent = z;
    }

    public final void setHtmlSafe(boolean z) {
        this.htmlSafe = z;
    }

    public final void setIndent(String str) {
        if (str.isEmpty()) {
            setFormattingStyle(FormattingStyle.COMPACT);
        } else {
            setFormattingStyle(FormattingStyle.PRETTY.withIndent(str));
        }
    }

    @Deprecated
    public final void setLenient(boolean z) {
        Strictness strictness2;
        if (z) {
            strictness2 = Strictness.LENIENT;
        } else {
            strictness2 = Strictness.LEGACY_STRICT;
        }
        setStrictness(strictness2);
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    public final void setStrictness(Strictness strictness2) {
        Objects.requireNonNull(strictness2);
        this.strictness = strictness2;
    }

    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        string(str);
        return this;
    }

    public JsonWriter value(boolean z) {
        writeDeferredName();
        beforeValue();
        this.out.write(z ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        return this;
    }

    public JsonWriter value(Boolean bool) {
        if (bool == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.write(bool.booleanValue() ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        return this;
    }

    public JsonWriter value(float f) {
        writeDeferredName();
        if (this.strictness == Strictness.LENIENT || (!Float.isNaN(f) && !Float.isInfinite(f))) {
            beforeValue();
            this.out.append(Float.toString(f));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + f);
    }

    public JsonWriter value(double d) {
        writeDeferredName();
        if (this.strictness == Strictness.LENIENT || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            beforeValue();
            this.out.append(Double.toString(d));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
    }

    public JsonWriter value(long j2) {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(j2));
        return this;
    }

    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        writeDeferredName();
        String obj = number.toString();
        Class<?> cls = number.getClass();
        if (!alwaysCreatesValidJsonNumber(cls)) {
            if (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN")) {
                if (this.strictness != Strictness.LENIENT) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(obj));
                }
            } else if (!(cls == Float.class || cls == Double.class || VALID_JSON_NUMBER_PATTERN.matcher(obj).matches())) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + obj);
            }
        }
        beforeValue();
        this.out.append(obj);
        return this;
    }
}
