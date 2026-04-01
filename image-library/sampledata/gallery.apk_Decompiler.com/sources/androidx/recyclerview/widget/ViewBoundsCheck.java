package androidx.recyclerview.widget;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewBoundsCheck {
    BoundFlags mBoundFlags = new BoundFlags();
    final Callback mCallback;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BoundFlags {
        int mBoundFlags = 0;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        public void addFlags(int i2) {
            this.mBoundFlags = i2 | this.mBoundFlags;
        }

        public boolean boundsMatch() {
            int i2 = this.mBoundFlags;
            if ((i2 & 7) != 0 && (i2 & compare(this.mChildStart, this.mRvStart)) == 0) {
                return false;
            }
            int i7 = this.mBoundFlags;
            if ((i7 & 112) != 0 && (i7 & (compare(this.mChildStart, this.mRvEnd) << 4)) == 0) {
                return false;
            }
            int i8 = this.mBoundFlags;
            if ((i8 & 1792) != 0 && (i8 & (compare(this.mChildEnd, this.mRvStart) << 8)) == 0) {
                return false;
            }
            int i10 = this.mBoundFlags;
            if ((i10 & 28672) == 0 || ((compare(this.mChildEnd, this.mRvEnd) << 12) & i10) != 0) {
                return true;
            }
            return false;
        }

        public int compare(int i2, int i7) {
            if (i2 > i7) {
                return 1;
            }
            if (i2 == i7) {
                return 2;
            }
            return 4;
        }

        public void resetFlags() {
            this.mBoundFlags = 0;
        }

        public void setBounds(int i2, int i7, int i8, int i10) {
            this.mRvStart = i2;
            this.mRvEnd = i7;
            this.mChildStart = i8;
            this.mChildEnd = i10;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        View getChildAt(int i2);

        int getChildEnd(View view);

        int getChildStart(View view);

        int getParentEnd();

        int getParentStart();
    }

    public ViewBoundsCheck(Callback callback) {
        this.mCallback = callback;
    }

    public View findOneViewWithinBoundFlags(int i2, int i7, int i8, int i10) {
        int i11;
        int parentStart = this.mCallback.getParentStart();
        int parentEnd = this.mCallback.getParentEnd();
        if (i7 > i2) {
            i11 = 1;
        } else {
            i11 = -1;
        }
        View view = null;
        while (i2 != i7) {
            View childAt = this.mCallback.getChildAt(i2);
            this.mBoundFlags.setBounds(parentStart, parentEnd, this.mCallback.getChildStart(childAt), this.mCallback.getChildEnd(childAt));
            if (i8 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(i8);
                if (this.mBoundFlags.boundsMatch()) {
                    return childAt;
                }
            }
            if (i10 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(i10);
                if (this.mBoundFlags.boundsMatch()) {
                    view = childAt;
                }
            }
            i2 += i11;
        }
        return view;
    }

    public boolean isViewWithinBoundFlags(View view, int i2) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(view), this.mCallback.getChildEnd(view));
        if (i2 == 0) {
            return false;
        }
        this.mBoundFlags.resetFlags();
        this.mBoundFlags.addFlags(i2);
        return this.mBoundFlags.boundsMatch();
    }
}
