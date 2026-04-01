package com.google.gson.stream;

import A.a;
import N2.j;
import c0.C0086a;
import com.google.gson.Strictness;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.bind.JsonTreeReader;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JsonReader implements Closeable {
    static final int BUFFER_SIZE = 1024;
    static final int DEFAULT_NESTING_LIMIT = 255;
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer = new char[1024];
    private final Reader in;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int nestingLimit = 255;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos = 0;
    private int[] stack;
    private int stackSize;
    private Strictness strictness = Strictness.LEGACY_STRICT;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            public void promoteNameToValue(JsonReader jsonReader) {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int i2 = jsonReader.peeked;
                if (i2 == 0) {
                    i2 = jsonReader.doPeek();
                }
                if (i2 == 13) {
                    jsonReader.peeked = 9;
                } else if (i2 == 12) {
                    jsonReader.peeked = 8;
                } else if (i2 == 14) {
                    jsonReader.peeked = 10;
                } else {
                    throw jsonReader.unexpectedTokenError("a name");
                }
            }
        };
    }

    public JsonReader(Reader reader) {
        int[] iArr = new int[32];
        this.stack = iArr;
        this.stackSize = 1;
        iArr[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.in = reader;
    }

    private void checkLenient() {
        if (this.strictness != Strictness.LENIENT) {
            throw syntaxError("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
        }
    }

    private void consumeNonExecutePrefix() {
        nextNonWhitespace(true);
        int i2 = this.pos;
        this.pos = i2 - 1;
        if (i2 + 4 <= this.limit || fillBuffer(5)) {
            int i7 = this.pos;
            char[] cArr = this.buffer;
            if (cArr[i7] == ')' && cArr[i7 + 1] == ']' && cArr[i7 + 2] == '}' && cArr[i7 + 3] == '\'' && cArr[i7 + 4] == 10) {
                this.pos = i7 + 5;
            }
        }
    }

    private boolean fillBuffer(int i2) {
        int i7;
        int i8;
        char[] cArr = this.buffer;
        int i10 = this.lineStart;
        int i11 = this.pos;
        this.lineStart = i10 - i11;
        int i12 = this.limit;
        if (i12 != i11) {
            int i13 = i12 - i11;
            this.limit = i13;
            System.arraycopy(cArr, i11, cArr, 0, i13);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i14 = this.limit;
            int read = reader.read(cArr, i14, cArr.length - i14);
            if (read == -1) {
                return false;
            }
            i7 = this.limit + read;
            this.limit = i7;
            if (this.lineNumber == 0 && (i8 = this.lineStart) == 0 && i7 > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = i8 + 1;
                i2++;
                continue;
            }
        } while (i7 < i2);
        return true;
    }

    private String getPath(boolean z) {
        StringBuilder sb2 = new StringBuilder("$");
        int i2 = 0;
        while (true) {
            int i7 = this.stackSize;
            if (i2 >= i7) {
                return sb2.toString();
            }
            int i8 = this.stack[i2];
            switch (i8) {
                case 1:
                case 2:
                    int i10 = this.pathIndices[i2];
                    if (z && i10 > 0 && i2 == i7 - 1) {
                        i10--;
                    }
                    sb2.append('[');
                    sb2.append(i10);
                    sb2.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb2.append('.');
                    String str = this.pathNames[i2];
                    if (str == null) {
                        break;
                    } else {
                        sb2.append(str);
                        break;
                    }
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError(C0086a.i(i8, "Unknown scope value: "));
            }
            i2++;
        }
    }

    private boolean isLiteral(char c5) {
        if (c5 == 9 || c5 == 10 || c5 == 12 || c5 == 13 || c5 == ' ') {
            return false;
        }
        if (c5 != '#') {
            if (c5 == ',') {
                return false;
            }
            if (!(c5 == '/' || c5 == '=')) {
                if (c5 == '{' || c5 == '}' || c5 == ':') {
                    return false;
                }
                if (c5 != ';') {
                    switch (c5) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    private int nextNonWhitespace(boolean z) {
        char c5;
        char[] cArr = this.buffer;
        int i2 = this.pos;
        int i7 = this.limit;
        while (true) {
            if (i2 == i7) {
                this.pos = i2;
                if (fillBuffer(1)) {
                    i2 = this.pos;
                    i7 = this.limit;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + locationString());
                }
            }
            int i8 = i2 + 1;
            c5 = cArr[i2];
            if (c5 == 10) {
                this.lineNumber++;
                this.lineStart = i8;
            } else if (!(c5 == ' ' || c5 == 13 || c5 == 9)) {
                if (c5 == '/') {
                    this.pos = i8;
                    if (i8 == i7) {
                        this.pos = i2;
                        boolean fillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!fillBuffer) {
                            break;
                        }
                    }
                    checkLenient();
                    int i10 = this.pos;
                    char c6 = cArr[i10];
                    if (c6 == '*') {
                        this.pos = i10 + 1;
                        if (skipTo("*/")) {
                            i2 = this.pos + 2;
                            i7 = this.limit;
                        } else {
                            throw syntaxError("Unterminated comment");
                        }
                    } else if (c6 != '/') {
                        break;
                    } else {
                        this.pos = i10 + 1;
                        skipToEndOfLine();
                        i2 = this.pos;
                        i7 = this.limit;
                    }
                } else if (c5 == '#') {
                    this.pos = i8;
                    checkLenient();
                    skipToEndOfLine();
                    i2 = this.pos;
                    i7 = this.limit;
                } else {
                    this.pos = i8;
                    return c5;
                }
            }
            i2 = i8;
        }
        return c5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        if (r1 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
        r1.append(r0, r3, r2 - r3);
        r10.pos = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextQuotedValue(char r11) {
        /*
            r10 = this;
            char[] r0 = r10.buffer
            r1 = 0
        L_0x0003:
            int r2 = r10.pos
            int r3 = r10.limit
        L_0x0007:
            r4 = r3
            r3 = r2
        L_0x0009:
            r5 = 16
            r6 = 1
            if (r2 >= r4) goto L_0x006c
            int r7 = r2 + 1
            char r2 = r0[r2]
            com.google.gson.Strictness r8 = r10.strictness
            com.google.gson.Strictness r9 = com.google.gson.Strictness.STRICT
            if (r8 != r9) goto L_0x0024
            r8 = 32
            if (r2 < r8) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            java.lang.String r11 = "Unescaped control characters (\\u0000-\\u001F) are not allowed in strict mode"
            com.google.gson.stream.MalformedJsonException r10 = r10.syntaxError(r11)
            throw r10
        L_0x0024:
            if (r2 != r11) goto L_0x003a
            r10.pos = r7
            int r7 = r7 - r3
            int r7 = r7 - r6
            if (r1 != 0) goto L_0x0032
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0, r3, r7)
            return r10
        L_0x0032:
            r1.append(r0, r3, r7)
            java.lang.String r10 = r1.toString()
            return r10
        L_0x003a:
            r8 = 92
            if (r2 != r8) goto L_0x005f
            r10.pos = r7
            int r7 = r7 - r3
            int r2 = r7 + -1
            if (r1 != 0) goto L_0x0050
            int r7 = r7 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r7, r5)
            r1.<init>(r4)
        L_0x0050:
            r1.append(r0, r3, r2)
            char r2 = r10.readEscapeCharacter()
            r1.append(r2)
            int r2 = r10.pos
            int r3 = r10.limit
            goto L_0x0007
        L_0x005f:
            r5 = 10
            if (r2 != r5) goto L_0x006a
            int r2 = r10.lineNumber
            int r2 = r2 + r6
            r10.lineNumber = r2
            r10.lineStart = r7
        L_0x006a:
            r2 = r7
            goto L_0x0009
        L_0x006c:
            if (r1 != 0) goto L_0x007c
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L_0x007c:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r10.pos = r2
            boolean r2 = r10.fillBuffer(r6)
            if (r2 == 0) goto L_0x008b
            goto L_0x0003
        L_0x008b:
            java.lang.String r11 = "Unterminated string"
            com.google.gson.stream.MalformedJsonException r10 = r10.syntaxError(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextQuotedValue(char):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004a, code lost:
        checkLenient();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextUnquotedValue() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            r2 = r1
        L_0x0003:
            int r3 = r6.pos
            int r4 = r3 + r2
            int r5 = r6.limit
            if (r4 >= r5) goto L_0x004e
            char[] r4 = r6.buffer
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L_0x005c
            r4 = 10
            if (r3 == r4) goto L_0x005c
            r4 = 12
            if (r3 == r4) goto L_0x005c
            r4 = 13
            if (r3 == r4) goto L_0x005c
            r4 = 32
            if (r3 == r4) goto L_0x005c
            r4 = 35
            if (r3 == r4) goto L_0x004a
            r4 = 44
            if (r3 == r4) goto L_0x005c
            r4 = 47
            if (r3 == r4) goto L_0x004a
            r4 = 61
            if (r3 == r4) goto L_0x004a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L_0x005c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x005c
            r4 = 58
            if (r3 == r4) goto L_0x005c
            r4 = 59
            if (r3 == r4) goto L_0x004a
            switch(r3) {
                case 91: goto L_0x005c;
                case 92: goto L_0x004a;
                case 93: goto L_0x005c;
                default: goto L_0x0047;
            }
        L_0x0047:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x004a:
            r6.checkLenient()
            goto L_0x005c
        L_0x004e:
            char[] r3 = r6.buffer
            int r3 = r3.length
            if (r2 >= r3) goto L_0x005e
            int r3 = r2 + 1
            boolean r3 = r6.fillBuffer(r3)
            if (r3 == 0) goto L_0x005c
            goto L_0x0003
        L_0x005c:
            r1 = r2
            goto L_0x007e
        L_0x005e:
            if (r0 != 0) goto L_0x006b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L_0x006b:
            char[] r3 = r6.buffer
            int r4 = r6.pos
            r0.append(r3, r4, r2)
            int r3 = r6.pos
            int r3 = r3 + r2
            r6.pos = r3
            r2 = 1
            boolean r2 = r6.fillBuffer(r2)
            if (r2 != 0) goto L_0x0002
        L_0x007e:
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = new java.lang.String
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r0.<init>(r2, r3, r1)
            goto L_0x0095
        L_0x008a:
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r0.append(r2, r3, r1)
            java.lang.String r0 = r0.toString()
        L_0x0095:
            int r2 = r6.pos
            int r2 = r2 + r1
            r6.pos = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextUnquotedValue():java.lang.String");
    }

    private int peekKeyword() {
        int i2;
        String str;
        String str2;
        boolean z;
        char c5 = this.buffer[this.pos];
        if (c5 == 't' || c5 == 'T') {
            str2 = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
            str = "TRUE";
            i2 = 5;
        } else if (c5 == 'f' || c5 == 'F') {
            str2 = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
            str = "FALSE";
            i2 = 6;
        } else if (c5 != 'n' && c5 != 'N') {
            return 0;
        } else {
            str2 = "null";
            str = "NULL";
            i2 = 7;
        }
        if (this.strictness != Strictness.STRICT) {
            z = true;
        } else {
            z = false;
        }
        int length = str2.length();
        for (int i7 = 0; i7 < length; i7++) {
            if (this.pos + i7 >= this.limit && !fillBuffer(i7 + 1)) {
                return 0;
            }
            char c6 = this.buffer[this.pos + i7];
            if (c6 != str2.charAt(i7) && (!z || c6 != str.charAt(i7))) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i2;
        return i2;
    }

    private int peekNumber() {
        int i2;
        char c5;
        int i7;
        char[] cArr = this.buffer;
        int i8 = this.pos;
        int i10 = this.limit;
        int i11 = 0;
        int i12 = 0;
        char c6 = 0;
        boolean z = false;
        int i13 = 1;
        long j2 = 0;
        while (true) {
            char c8 = 2;
            if (i8 + i12 == i10) {
                if (i12 == cArr.length) {
                    return i11;
                }
                if (!fillBuffer(i12 + 1)) {
                    i2 = i11;
                    break;
                }
                i8 = this.pos;
                i10 = this.limit;
            }
            c5 = cArr[i8 + i12];
            i2 = i11;
            if (c5 == '+') {
                c8 = 6;
                if (c6 != 5) {
                    return i2;
                }
            } else if (c5 == 'E' || c5 == 'e') {
                if (c6 != 2 && c6 != 4) {
                    return i2;
                }
                c6 = 5;
                i12++;
                i11 = i2;
            } else if (c5 != '-') {
                if (c5 != '.') {
                    if (c5 >= '0' && c5 <= '9') {
                        if (c6 == 1 || c6 == 0) {
                            j2 = (long) (-(c5 - '0'));
                        } else if (c6 == 2) {
                            if (j2 == 0) {
                                return i2;
                            }
                            long j3 = (10 * j2) - ((long) (c5 - '0'));
                            int i14 = (j2 > MIN_INCOMPLETE_INTEGER ? 1 : (j2 == MIN_INCOMPLETE_INTEGER ? 0 : -1));
                            if (i14 > 0 || (i14 == 0 && j3 < j2)) {
                                i7 = 1;
                            } else {
                                i7 = i2;
                            }
                            i13 &= i7;
                            j2 = j3;
                        } else if (c6 == 3) {
                            c6 = 4;
                        } else if (c6 == 5 || c6 == 6) {
                            c6 = 7;
                        }
                    }
                } else if (c6 != 2) {
                    return i2;
                } else {
                    c6 = 3;
                }
                i12++;
                i11 = i2;
            } else {
                c8 = 6;
                if (c6 == 0) {
                    c6 = 1;
                    z = true;
                    i12++;
                    i11 = i2;
                } else if (c6 != 5) {
                    return i2;
                }
            }
            c6 = c8;
            i12++;
            i11 = i2;
        }
        if (isLiteral(c5)) {
            return i2;
        }
        if (c6 == 2 && i13 != 0 && ((j2 != Long.MIN_VALUE || z) && (j2 != 0 || !z))) {
            if (!z) {
                j2 = -j2;
            }
            this.peekedLong = j2;
            this.pos += i12;
            this.peeked = 15;
            return 15;
        } else if (c6 != 2 && c6 != 4 && c6 != 7) {
            return i2;
        } else {
            this.peekedNumberLength = i12;
            this.peeked = 16;
            return 16;
        }
    }

    private void push(int i2) {
        int i7 = this.stackSize;
        if (i7 - 1 < this.nestingLimit) {
            int[] iArr = this.stack;
            if (i7 == iArr.length) {
                int i8 = i7 * 2;
                this.stack = Arrays.copyOf(iArr, i8);
                this.pathIndices = Arrays.copyOf(this.pathIndices, i8);
                this.pathNames = (String[]) Arrays.copyOf(this.pathNames, i8);
            }
            int[] iArr2 = this.stack;
            int i10 = this.stackSize;
            this.stackSize = i10 + 1;
            iArr2[i10] = i2;
            return;
        }
        throw new MalformedJsonException("Nesting limit " + this.nestingLimit + " reached" + locationString());
    }

    private char readEscapeCharacter() {
        int i2;
        if (this.pos != this.limit || fillBuffer(1)) {
            char[] cArr = this.buffer;
            int i7 = this.pos;
            int i8 = i7 + 1;
            this.pos = i8;
            char c5 = cArr[i7];
            if (c5 != 10) {
                if (c5 != '\"') {
                    if (c5 != '\'') {
                        if (!(c5 == '/' || c5 == '\\')) {
                            if (c5 == 'b') {
                                return 8;
                            }
                            if (c5 == 'f') {
                                return 12;
                            }
                            if (c5 == 'n') {
                                return 10;
                            }
                            if (c5 == 'r') {
                                return 13;
                            }
                            if (c5 == 't') {
                                return 9;
                            }
                            if (c5 != 'u') {
                                throw syntaxError("Invalid escape sequence");
                            } else if (i7 + 5 <= this.limit || fillBuffer(4)) {
                                int i10 = this.pos;
                                int i11 = i10 + 4;
                                int i12 = 0;
                                while (i10 < i11) {
                                    char[] cArr2 = this.buffer;
                                    char c6 = cArr2[i10];
                                    int i13 = i12 << 4;
                                    if (c6 >= '0' && c6 <= '9') {
                                        i2 = c6 - '0';
                                    } else if (c6 >= 'a' && c6 <= 'f') {
                                        i2 = c6 - 'W';
                                    } else if (c6 < 'A' || c6 > 'F') {
                                        throw syntaxError("Malformed Unicode escape \\u".concat(new String(cArr2, this.pos, 4)));
                                    } else {
                                        i2 = c6 - '7';
                                    }
                                    i12 = i2 + i13;
                                    i10++;
                                }
                                this.pos += 4;
                                return (char) i12;
                            } else {
                                throw syntaxError("Unterminated escape sequence");
                            }
                        }
                    }
                }
                return c5;
            } else if (this.strictness != Strictness.STRICT) {
                this.lineNumber++;
                this.lineStart = i8;
            } else {
                throw syntaxError("Cannot escape a newline character in strict mode");
            }
            if (this.strictness == Strictness.STRICT) {
                throw syntaxError("Invalid escaped character \"'\" in strict mode");
            }
            return c5;
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private void skipQuotedValue(char c5) {
        char[] cArr = this.buffer;
        do {
            int i2 = this.pos;
            int i7 = this.limit;
            while (i2 < i7) {
                int i8 = i2 + 1;
                char c6 = cArr[i2];
                if (c6 == c5) {
                    this.pos = i8;
                    return;
                } else if (c6 == '\\') {
                    this.pos = i8;
                    readEscapeCharacter();
                    i2 = this.pos;
                    i7 = this.limit;
                } else {
                    if (c6 == 10) {
                        this.lineNumber++;
                        this.lineStart = i8;
                    }
                    i2 = i8;
                }
            }
            this.pos = i2;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String str) {
        int length = str.length();
        while (true) {
            int i2 = 0;
            if (this.pos + length > this.limit && !fillBuffer(length)) {
                return false;
            }
            char[] cArr = this.buffer;
            int i7 = this.pos;
            if (cArr[i7] == 10) {
                this.lineNumber++;
                this.lineStart = i7 + 1;
            } else {
                while (i2 < length) {
                    if (this.buffer[this.pos + i2] == str.charAt(i2)) {
                        i2++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private void skipToEndOfLine() {
        char c5;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i2 = this.pos;
                int i7 = i2 + 1;
                this.pos = i7;
                c5 = cArr[i2];
                if (c5 == 10) {
                    this.lineNumber++;
                    this.lineStart = i7;
                    return;
                }
            } else {
                return;
            }
        } while (c5 != 13);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0048, code lost:
        checkLenient();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void skipUnquotedValue() {
        /*
            r4 = this;
        L_0x0000:
            r0 = 0
        L_0x0001:
            int r1 = r4.pos
            int r2 = r1 + r0
            int r3 = r4.limit
            if (r2 >= r3) goto L_0x0051
            char[] r2 = r4.buffer
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L_0x004b
            r2 = 10
            if (r1 == r2) goto L_0x004b
            r2 = 12
            if (r1 == r2) goto L_0x004b
            r2 = 13
            if (r1 == r2) goto L_0x004b
            r2 = 32
            if (r1 == r2) goto L_0x004b
            r2 = 35
            if (r1 == r2) goto L_0x0048
            r2 = 44
            if (r1 == r2) goto L_0x004b
            r2 = 47
            if (r1 == r2) goto L_0x0048
            r2 = 61
            if (r1 == r2) goto L_0x0048
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x004b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L_0x004b
            r2 = 58
            if (r1 == r2) goto L_0x004b
            r2 = 59
            if (r1 == r2) goto L_0x0048
            switch(r1) {
                case 91: goto L_0x004b;
                case 92: goto L_0x0048;
                case 93: goto L_0x004b;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0048:
            r4.checkLenient()
        L_0x004b:
            int r1 = r4.pos
            int r1 = r1 + r0
            r4.pos = r1
            return
        L_0x0051:
            int r1 = r1 + r0
            r4.pos = r1
            r0 = 1
            boolean r0 = r4.fillBuffer(r0)
            if (r0 != 0) goto L_0x0000
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.skipUnquotedValue():void");
    }

    private MalformedJsonException syntaxError(String str) {
        StringBuilder s = C0212a.s(str);
        s.append(locationString());
        s.append("\nSee ");
        s.append(TroubleshootingGuide.createUrl("malformed-json"));
        throw new MalformedJsonException(s.toString());
    }

    /* access modifiers changed from: private */
    public IllegalStateException unexpectedTokenError(String str) {
        String str2;
        if (peek() == JsonToken.NULL) {
            str2 = "adapter-not-null-safe";
        } else {
            str2 = "unexpected-json-structure";
        }
        StringBuilder k = j.k("Expected ", str, " but was ");
        k.append(peek());
        k.append(locationString());
        k.append("\nSee ");
        k.append(TroubleshootingGuide.createUrl(str2));
        return new IllegalStateException(k.toString());
    }

    public void beginArray() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("BEGIN_ARRAY");
    }

    public void beginObject() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("BEGIN_OBJECT");
    }

    public void close() {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public int doPeek() {
        int nextNonWhitespace;
        int[] iArr = this.stack;
        int i2 = this.stackSize;
        int i7 = iArr[i2 - 1];
        if (i7 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i7 == 2) {
            int nextNonWhitespace2 = nextNonWhitespace(true);
            if (nextNonWhitespace2 != 44) {
                if (nextNonWhitespace2 == 59) {
                    checkLenient();
                } else if (nextNonWhitespace2 == 93) {
                    this.peeked = 4;
                    return 4;
                } else {
                    throw syntaxError("Unterminated array");
                }
            }
        } else if (i7 == 3 || i7 == 5) {
            iArr[i2 - 1] = 4;
            if (i7 == 5 && (nextNonWhitespace = nextNonWhitespace(true)) != 44) {
                if (nextNonWhitespace == 59) {
                    checkLenient();
                } else if (nextNonWhitespace == 125) {
                    this.peeked = 2;
                    return 2;
                } else {
                    throw syntaxError("Unterminated object");
                }
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 != 125) {
                checkLenient();
                this.pos--;
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            } else if (i7 != 5) {
                this.peeked = 2;
                return 2;
            } else {
                throw syntaxError("Expected name");
            }
        } else if (i7 == 4) {
            iArr[i2 - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int i8 = this.pos;
                        if (cArr[i8] == '>') {
                            this.pos = i8 + 1;
                        }
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (i7 == 6) {
            if (this.strictness == Strictness.LENIENT) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (i7 == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (i7 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 != 39) {
            if (!(nextNonWhitespace5 == 44 || nextNonWhitespace5 == 59)) {
                if (nextNonWhitespace5 == 91) {
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 != 123) {
                        this.pos--;
                        int peekKeyword = peekKeyword();
                        if (peekKeyword != 0) {
                            return peekKeyword;
                        }
                        int peekNumber = peekNumber();
                        if (peekNumber != 0) {
                            return peekNumber;
                        }
                        if (isLiteral(this.buffer[this.pos])) {
                            checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        throw syntaxError("Expected value");
                    }
                    this.peeked = 1;
                    return 1;
                } else if (i7 == 1) {
                    this.peeked = 4;
                    return 4;
                }
            }
            if (i7 == 1 || i7 == 2) {
                checkLenient();
                this.pos--;
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        } else {
            checkLenient();
            this.peeked = 8;
            return 8;
        }
    }

    public void endArray() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 4) {
            int i7 = this.stackSize;
            this.stackSize = i7 - 1;
            int[] iArr = this.pathIndices;
            int i8 = i7 - 2;
            iArr[i8] = iArr[i8] + 1;
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("END_ARRAY");
    }

    public void endObject() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 2) {
            int i7 = this.stackSize;
            int i8 = i7 - 1;
            this.stackSize = i8;
            this.pathNames[i8] = null;
            int[] iArr = this.pathIndices;
            int i10 = i7 - 2;
            iArr[i10] = iArr[i10] + 1;
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("END_OBJECT");
    }

    public final int getNestingLimit() {
        return this.nestingLimit;
    }

    public String getPreviousPath() {
        return getPath(true);
    }

    public final Strictness getStrictness() {
        return this.strictness;
    }

    public boolean hasNext() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 2 || i2 == 4 || i2 == 17) {
            return false;
        }
        return true;
    }

    public final boolean isLenient() {
        if (this.strictness == Strictness.LENIENT) {
            return true;
        }
        return false;
    }

    public String locationString() {
        StringBuilder h5 = a.h(this.lineNumber + 1, (this.pos - this.lineStart) + 1, " at line ", " column ", " path ");
        h5.append(getPath());
        return h5.toString();
    }

    public boolean nextBoolean() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i7 = this.stackSize - 1;
            iArr[i7] = iArr[i7] + 1;
            return true;
        } else if (i2 == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i8 = this.stackSize - 1;
            iArr2[i8] = iArr2[i8] + 1;
            return false;
        } else {
            throw unexpectedTokenError("a boolean");
        }
    }

    public double nextDouble() {
        char c5;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i7 = this.stackSize - 1;
            iArr[i7] = iArr[i7] + 1;
            return (double) this.peekedLong;
        }
        if (i2 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i2 == 8 || i2 == 9) {
            if (i2 == 8) {
                c5 = '\'';
            } else {
                c5 = '\"';
            }
            this.peekedString = nextQuotedValue(c5);
        } else if (i2 == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i2 != 11) {
            throw unexpectedTokenError("a double");
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (this.strictness == Strictness.LENIENT || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i8 = this.stackSize - 1;
            iArr2[i8] = iArr2[i8] + 1;
            return parseDouble;
        }
        throw syntaxError("JSON forbids NaN and infinities: " + parseDouble);
    }

    public int nextInt() {
        char c5;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 15) {
            long j2 = this.peekedLong;
            int i7 = (int) j2;
            if (j2 == ((long) i7)) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i8 = this.stackSize - 1;
                iArr[i8] = iArr[i8] + 1;
                return i7;
            }
            throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
        }
        if (i2 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                if (i2 == 8) {
                    c5 = '\'';
                } else {
                    c5 = '\"';
                }
                this.peekedString = nextQuotedValue(c5);
            }
            try {
                int parseInt = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i10 = this.stackSize - 1;
                iArr2[i10] = iArr2[i10] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw unexpectedTokenError("an int");
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        int i11 = (int) parseDouble;
        if (((double) i11) == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i12 = this.stackSize - 1;
            iArr3[i12] = iArr3[i12] + 1;
            return i11;
        }
        throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
    }

    public long nextLong() {
        char c5;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i7 = this.stackSize - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.peekedLong;
        }
        if (i2 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                if (i2 == 8) {
                    c5 = '\'';
                } else {
                    c5 = '\"';
                }
                this.peekedString = nextQuotedValue(c5);
            }
            try {
                long parseLong = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i8 = this.stackSize - 1;
                iArr2[i8] = iArr2[i8] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw unexpectedTokenError("a long");
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        long j2 = (long) parseDouble;
        if (((double) j2) == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i10 = this.stackSize - 1;
            iArr3[i10] = iArr3[i10] + 1;
            return j2;
        }
        throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
    }

    public String nextName() {
        String str;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 14) {
            str = nextUnquotedValue();
        } else if (i2 == 12) {
            str = nextQuotedValue('\'');
        } else if (i2 == 13) {
            str = nextQuotedValue('\"');
        } else {
            throw unexpectedTokenError("a name");
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = str;
        return str;
    }

    public void nextNull() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i7 = this.stackSize - 1;
            iArr[i7] = iArr[i7] + 1;
            return;
        }
        throw unexpectedTokenError("null");
    }

    public String nextString() {
        String str;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 10) {
            str = nextUnquotedValue();
        } else if (i2 == 8) {
            str = nextQuotedValue('\'');
        } else if (i2 == 9) {
            str = nextQuotedValue('\"');
        } else if (i2 == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (i2 == 15) {
            str = Long.toString(this.peekedLong);
        } else if (i2 == 16) {
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw unexpectedTokenError("a string");
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i7 = this.stackSize - 1;
        iArr[i7] = iArr[i7] + 1;
        return str;
    }

    public JsonToken peek() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        switch (i2) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
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

    public final void setNestingLimit(int i2) {
        if (i2 >= 0) {
            this.nestingLimit = i2;
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Invalid nesting limit: "));
    }

    public final void setStrictness(Strictness strictness2) {
        Objects.requireNonNull(strictness2);
        this.strictness = strictness2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005c, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        r7.peeked = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipValue() {
        /*
            r7 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            int r2 = r7.peeked
            if (r2 != 0) goto L_0x000a
            int r2 = r7.doPeek()
        L_0x000a:
            r3 = 39
            r4 = 34
            java.lang.String r5 = "<skipped>"
            r6 = 1
            switch(r2) {
                case 1: goto L_0x006f;
                case 2: goto L_0x005f;
                case 3: goto L_0x0059;
                case 4: goto L_0x0051;
                case 5: goto L_0x0014;
                case 6: goto L_0x0014;
                case 7: goto L_0x0014;
                case 8: goto L_0x004d;
                case 9: goto L_0x0049;
                case 10: goto L_0x0045;
                case 11: goto L_0x0014;
                case 12: goto L_0x0038;
                case 13: goto L_0x002b;
                case 14: goto L_0x001e;
                case 15: goto L_0x0014;
                case 16: goto L_0x0016;
                case 17: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0074
        L_0x0015:
            return
        L_0x0016:
            int r2 = r7.pos
            int r3 = r7.peekedNumberLength
            int r2 = r2 + r3
            r7.pos = r2
            goto L_0x0074
        L_0x001e:
            r7.skipUnquotedValue()
            if (r1 != 0) goto L_0x0074
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r2[r3] = r5
            goto L_0x0074
        L_0x002b:
            r7.skipQuotedValue(r4)
            if (r1 != 0) goto L_0x0074
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r2[r3] = r5
            goto L_0x0074
        L_0x0038:
            r7.skipQuotedValue(r3)
            if (r1 != 0) goto L_0x0074
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r2[r3] = r5
            goto L_0x0074
        L_0x0045:
            r7.skipUnquotedValue()
            goto L_0x0074
        L_0x0049:
            r7.skipQuotedValue(r4)
            goto L_0x0074
        L_0x004d:
            r7.skipQuotedValue(r3)
            goto L_0x0074
        L_0x0051:
            int r2 = r7.stackSize
            int r2 = r2 - r6
            r7.stackSize = r2
        L_0x0056:
            int r1 = r1 + -1
            goto L_0x0074
        L_0x0059:
            r7.push(r6)
        L_0x005c:
            int r1 = r1 + 1
            goto L_0x0074
        L_0x005f:
            if (r1 != 0) goto L_0x0069
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r4 = 0
            r2[r3] = r4
        L_0x0069:
            int r2 = r7.stackSize
            int r2 = r2 - r6
            r7.stackSize = r2
            goto L_0x0056
        L_0x006f:
            r2 = 3
            r7.push(r2)
            goto L_0x005c
        L_0x0074:
            r7.peeked = r0
            if (r1 > 0) goto L_0x0002
            int[] r0 = r7.pathIndices
            int r7 = r7.stackSize
            int r7 = r7 - r6
            r1 = r0[r7]
            int r1 = r1 + r6
            r0[r7] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.skipValue():void");
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }

    public String getPath() {
        return getPath(false);
    }
}
