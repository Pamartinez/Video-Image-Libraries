package com.samsung.android.photoremaster.sdk.ip;

import A.a;
import android.util.Size;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrSize {
    private final int mHeight;
    private final int mWidth;

    public PrSize(int i2, int i7) {
        this.mWidth = i2;
        this.mHeight = i7;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof PrSize) {
            PrSize prSize = (PrSize) obj;
            if (this.mWidth == prSize.mWidth && this.mHeight == prSize.mHeight) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isEmpty() {
        if (this.mWidth == 0 && this.mHeight == 0) {
            return true;
        }
        return false;
    }

    public Size toSize() {
        return new Size(this.mWidth, this.mHeight);
    }

    public String toString() {
        Locale locale = Locale.US;
        return a.d(this.mWidth, this.mHeight, "PrSize { mWidth = ", ", mHeight = ", " }");
    }

    public PrSize(Size size) {
        this.mWidth = size.getWidth();
        this.mHeight = size.getHeight();
    }

    public PrSize() {
        this(0, 0);
    }
}
