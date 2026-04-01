package com.adobe.internal.xmp.impl;

import java.io.PushbackReader;
import java.io.Reader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixASCIIControlsReader extends PushbackReader {
    private static final int BUFFER_SIZE = 8;
    private static final int STATE_AMP = 1;
    private static final int STATE_DIG1 = 4;
    private static final int STATE_ERROR = 5;
    private static final int STATE_HASH = 2;
    private static final int STATE_HEX = 3;
    private static final int STATE_START = 0;
    private int control = 0;
    private int digits = 0;
    private int state = 0;

    public FixASCIIControlsReader(Reader reader) {
        super(reader, 8);
    }

    private char processChar(char c5) {
        int i2;
        int i7 = this.state;
        if (i7 != 0) {
            if (i7 != 1) {
                if (i7 != 2) {
                    if (i7 != 3) {
                        if (i7 != 4) {
                            if (i7 == 5) {
                                this.state = 0;
                                return c5;
                            }
                        } else if ('0' <= c5 && c5 <= '9') {
                            this.control = Character.digit(c5, 10) + (this.control * 10);
                            int i8 = this.digits + 1;
                            this.digits = i8;
                            if (i8 <= 5) {
                                this.state = 4;
                                return c5;
                            }
                            this.state = 5;
                            return c5;
                        } else if (c5 != ';' || !Utils.isControlChar((char) this.control)) {
                            this.state = 5;
                            return c5;
                        } else {
                            this.state = 0;
                            i2 = this.control;
                        }
                    } else if (('0' <= c5 && c5 <= '9') || (('a' <= c5 && c5 <= 'f') || ('A' <= c5 && c5 <= 'F'))) {
                        this.control = Character.digit(c5, 16) + (this.control * 16);
                        int i10 = this.digits + 1;
                        this.digits = i10;
                        if (i10 <= 4) {
                            this.state = 3;
                            return c5;
                        }
                        this.state = 5;
                        return c5;
                    } else if (c5 != ';' || !Utils.isControlChar((char) this.control)) {
                        this.state = 5;
                        return c5;
                    } else {
                        this.state = 0;
                        i2 = this.control;
                    }
                    return (char) i2;
                } else if (c5 == 'x') {
                    this.control = 0;
                    this.digits = 0;
                    this.state = 3;
                    return c5;
                } else if ('0' > c5 || c5 > '9') {
                    this.state = 5;
                    return c5;
                } else {
                    this.control = Character.digit(c5, 10);
                    this.digits = 1;
                    this.state = 4;
                    return c5;
                }
            } else if (c5 == '#') {
                this.state = 2;
                return c5;
            } else {
                this.state = 5;
                return c5;
            }
        } else if (c5 == '&') {
            this.state = 1;
        }
        return c5;
    }

    public int read(char[] cArr, int i2, int i7) {
        boolean z;
        char[] cArr2 = new char[8];
        int i8 = 0;
        int i10 = 0;
        loop0:
        while (true) {
            z = true;
            while (z && i8 < i7) {
                if (super.read(cArr2, i10, 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    char processChar = processChar(cArr2[i10]);
                    int i11 = this.state;
                    if (i11 == 0) {
                        if (Utils.isControlChar(processChar)) {
                            processChar = ' ';
                        }
                        cArr[i2] = processChar;
                        i8++;
                        i2++;
                    } else if (i11 == 5) {
                        unread(cArr2, 0, i10 + 1);
                    } else {
                        i10++;
                    }
                    i10 = 0;
                } else if (i10 > 0) {
                    unread(cArr2, 0, i10);
                    this.state = 5;
                    i10 = 0;
                }
            }
        }
        if (i8 > 0 || z) {
            return i8;
        }
        return -1;
    }
}
