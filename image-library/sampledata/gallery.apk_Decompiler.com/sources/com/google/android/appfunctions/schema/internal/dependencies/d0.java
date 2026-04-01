package com.google.android.appfunctions.schema.internal.dependencies;

import com.google.protobuf.ByteString;
import java.util.Iterator;
import java.util.NoSuchElementException;
import rf.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d0 implements Iterator {
    public final /* synthetic */ int d = 0;
    public int e;
    public final int f;
    public final /* synthetic */ Iterable g;

    public d0(f0 f0Var) {
        this.g = f0Var;
        this.e = 0;
        this.f = f0Var.r();
    }

    public byte a() {
        try {
            byte[] bArr = ((x) this.g).e;
            int i2 = this.e;
            this.e = i2 + 1;
            return bArr[i2];
        } catch (ArrayIndexOutOfBoundsException e7) {
            throw new NoSuchElementException(e7.getMessage());
        }
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                if (this.e < this.f) {
                    return true;
                }
                return false;
            case 1:
                if (this.e < this.f) {
                    return true;
                }
                return false;
            default:
                if (this.e < this.f) {
                    return true;
                }
                return false;
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                int i2 = this.e;
                if (i2 < this.f) {
                    this.e = i2 + 1;
                    return Byte.valueOf(((f0) this.g).q(i2));
                }
                throw new NoSuchElementException();
            case 1:
                int i7 = this.e;
                if (i7 < this.f) {
                    this.e = i7 + 1;
                    return Byte.valueOf(((ByteString) this.g).t(i7));
                }
                throw new NoSuchElementException();
            default:
                return Byte.valueOf(a());
        }
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                throw new UnsupportedOperationException();
            case 1:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public d0(ByteString byteString) {
        this.g = byteString;
        this.e = 0;
        this.f = byteString.size();
    }

    public d0(x xVar) {
        this.g = xVar;
        this.e = 0;
        this.f = xVar.e.length;
    }
}
