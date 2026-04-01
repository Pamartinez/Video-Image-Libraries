package androidx.appcompat.widget;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RtlSpacingHelper {
    private int mEnd = Integer.MIN_VALUE;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRelative = false;
    private boolean mIsRtl = false;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = Integer.MIN_VALUE;

    public int getEnd() {
        if (this.mIsRtl) {
            return this.mLeft;
        }
        return this.mRight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        if (this.mIsRtl) {
            return this.mRight;
        }
        return this.mLeft;
    }

    public void setAbsolute(int i2, int i7) {
        this.mIsRelative = false;
        if (i2 != Integer.MIN_VALUE) {
            this.mExplicitLeft = i2;
            this.mLeft = i2;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.mExplicitRight = i7;
            this.mRight = i7;
        }
    }

    public void setDirection(boolean z) {
        if (z != this.mIsRtl) {
            this.mIsRtl = z;
            if (!this.mIsRelative) {
                this.mLeft = this.mExplicitLeft;
                this.mRight = this.mExplicitRight;
            } else if (z) {
                int i2 = this.mEnd;
                if (i2 == Integer.MIN_VALUE) {
                    i2 = this.mExplicitLeft;
                }
                this.mLeft = i2;
                int i7 = this.mStart;
                if (i7 == Integer.MIN_VALUE) {
                    i7 = this.mExplicitRight;
                }
                this.mRight = i7;
            } else {
                int i8 = this.mStart;
                if (i8 == Integer.MIN_VALUE) {
                    i8 = this.mExplicitLeft;
                }
                this.mLeft = i8;
                int i10 = this.mEnd;
                if (i10 == Integer.MIN_VALUE) {
                    i10 = this.mExplicitRight;
                }
                this.mRight = i10;
            }
        }
    }

    public void setRelative(int i2, int i7) {
        this.mStart = i2;
        this.mEnd = i7;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (i7 != Integer.MIN_VALUE) {
                this.mLeft = i7;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.mRight = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.mLeft = i2;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.mRight = i7;
        }
    }
}
