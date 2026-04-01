package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ParseState {
    private int pos = 0;
    private String str;

    public ParseState(String str2) {
        this.str = str2;
    }

    public char ch(int i2) {
        if (i2 < this.str.length()) {
            return this.str.charAt(i2);
        }
        return 0;
    }

    public int gatherInt(String str2, int i2) {
        char ch = ch(this.pos);
        int i7 = 0;
        boolean z = false;
        while ('0' <= ch && ch <= '9') {
            i7 = (i7 * 10) + (ch - '0');
            z = true;
            int i8 = this.pos + 1;
            this.pos = i8;
            ch = ch(i8);
        }
        if (!z) {
            throw new XMPException(str2, 5);
        } else if (i7 > i2) {
            return i2;
        } else {
            if (i7 < 0) {
                return 0;
            }
            return i7;
        }
    }

    public boolean hasNext() {
        if (this.pos < this.str.length()) {
            return true;
        }
        return false;
    }

    public int length() {
        return this.str.length();
    }

    public int pos() {
        return this.pos;
    }

    public void skip() {
        this.pos++;
    }

    public char ch() {
        if (this.pos < this.str.length()) {
            return this.str.charAt(this.pos);
        }
        return 0;
    }
}
