package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ChildHelper {
    final Bucket mBucket;
    final Callback mCallback;
    final List<View> mHiddenViews;
    private int mRemoveStatus = 0;
    private View mViewInRemoveView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Bucket {
        long mData = 0;
        Bucket mNext;

        private void ensureNext() {
            if (this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        public void clear(int i2) {
            if (i2 >= 64) {
                Bucket bucket = this.mNext;
                if (bucket != null) {
                    bucket.clear(i2 - 64);
                    return;
                }
                return;
            }
            this.mData &= ~(1 << i2);
        }

        public int countOnesBefore(int i2) {
            Bucket bucket = this.mNext;
            if (bucket == null) {
                if (i2 >= 64) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(((1 << i2) - 1) & this.mData);
            } else if (i2 < 64) {
                return Long.bitCount(((1 << i2) - 1) & this.mData);
            } else {
                return Long.bitCount(this.mData) + bucket.countOnesBefore(i2 - 64);
            }
        }

        public boolean get(int i2) {
            if (i2 >= 64) {
                ensureNext();
                return this.mNext.get(i2 - 64);
            }
            if (((1 << i2) & this.mData) != 0) {
                return true;
            }
            return false;
        }

        public void insert(int i2, boolean z) {
            boolean z3;
            if (i2 >= 64) {
                ensureNext();
                this.mNext.insert(i2 - 64, z);
                return;
            }
            long j2 = this.mData;
            if ((Long.MIN_VALUE & j2) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            long j3 = (1 << i2) - 1;
            this.mData = ((j2 & (~j3)) << 1) | (j2 & j3);
            if (z) {
                set(i2);
            } else {
                clear(i2);
            }
            if (z3 || this.mNext != null) {
                ensureNext();
                this.mNext.insert(0, z3);
            }
        }

        public boolean remove(int i2) {
            boolean z;
            if (i2 >= 64) {
                ensureNext();
                return this.mNext.remove(i2 - 64);
            }
            long j2 = 1 << i2;
            long j3 = this.mData;
            if ((j3 & j2) != 0) {
                z = true;
            } else {
                z = false;
            }
            long j8 = j3 & (~j2);
            this.mData = j8;
            long j10 = j2 - 1;
            this.mData = (j8 & j10) | Long.rotateRight((~j10) & j8, 1);
            Bucket bucket = this.mNext;
            if (bucket != null) {
                if (bucket.get(0)) {
                    set(63);
                }
                this.mNext.remove(0);
            }
            return z;
        }

        public void reset() {
            this.mData = 0;
            Bucket bucket = this.mNext;
            if (bucket != null) {
                bucket.reset();
            }
        }

        public void set(int i2) {
            if (i2 >= 64) {
                ensureNext();
                this.mNext.set(i2 - 64);
                return;
            }
            this.mData |= 1 << i2;
        }

        public String toString() {
            if (this.mNext == null) {
                return Long.toBinaryString(this.mData);
            }
            return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        void addView(View view, int i2);

        void attachViewToParent(View view, int i2, ViewGroup.LayoutParams layoutParams);

        void detachViewFromParent(int i2);

        View getChildAt(int i2);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int i2);
    }

    public ChildHelper(Callback callback) {
        this.mCallback = callback;
        this.mBucket = new Bucket();
        this.mHiddenViews = new ArrayList();
    }

    private int getOffset(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int i7 = i2;
        while (i7 < childCount) {
            int countOnesBefore = i2 - (i7 - this.mBucket.countOnesBefore(i7));
            if (countOnesBefore == 0) {
                while (this.mBucket.get(i7)) {
                    i7++;
                }
                return i7;
            }
            i7 += countOnesBefore;
        }
        return -1;
    }

    private void hideViewInternal(View view) {
        this.mHiddenViews.add(view);
        this.mCallback.onEnteredHiddenState(view);
    }

    private boolean unhideViewInternal(View view) {
        if (!this.mHiddenViews.remove(view)) {
            return false;
        }
        this.mCallback.onLeftHiddenState(view);
        return true;
    }

    public void addView(View view, boolean z) {
        addView(view, -1, z);
    }

    public void attachViewToParent(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int i7;
        if (i2 < 0) {
            i7 = this.mCallback.getChildCount();
        } else {
            i7 = getOffset(i2);
        }
        this.mBucket.insert(i7, z);
        if (z) {
            hideViewInternal(view);
        }
        this.mCallback.attachViewToParent(view, i7, layoutParams);
    }

    public void detachViewFromParent(int i2) {
        int offset = getOffset(i2);
        this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }

    public View findHiddenNonRemovedView(int i2) {
        int size = this.mHiddenViews.size();
        for (int i7 = 0; i7 < size; i7++) {
            View view = this.mHiddenViews.get(i7);
            RecyclerView.ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == i2 && !childViewHolder.isInvalid() && !childViewHolder.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    public View getChildAt(int i2) {
        return this.mCallback.getChildAt(getOffset(i2));
    }

    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    public View getUnfilteredChildAt(int i2) {
        return this.mCallback.getChildAt(i2);
    }

    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    public void hide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild >= 0) {
            this.mBucket.set(indexOfChild);
            hideViewInternal(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    public int indexOfChild(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild != -1 && !this.mBucket.get(indexOfChild)) {
            return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
        }
        return -1;
    }

    public boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    public void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(size));
            this.mHiddenViews.remove(size);
        }
        this.mCallback.removeAllViews();
    }

    public void removeView(View view) {
        int i2 = this.mRemoveStatus;
        if (i2 == 1) {
            throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
        } else if (i2 != 2) {
            try {
                this.mRemoveStatus = 1;
                this.mViewInRemoveView = view;
                int indexOfChild = this.mCallback.indexOfChild(view);
                if (indexOfChild >= 0) {
                    if (this.mBucket.remove(indexOfChild)) {
                        unhideViewInternal(view);
                    }
                    this.mCallback.removeViewAt(indexOfChild);
                }
                this.mRemoveStatus = 0;
                this.mViewInRemoveView = null;
            } catch (Throwable th) {
                this.mRemoveStatus = 0;
                this.mViewInRemoveView = null;
                throw th;
            }
        } else {
            throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
        }
    }

    public void removeViewAt(int i2) {
        int i7 = this.mRemoveStatus;
        if (i7 == 1) {
            throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
        } else if (i7 != 2) {
            try {
                int offset = getOffset(i2);
                View childAt = this.mCallback.getChildAt(offset);
                if (childAt != null) {
                    this.mRemoveStatus = 1;
                    this.mViewInRemoveView = childAt;
                    if (this.mBucket.remove(offset)) {
                        unhideViewInternal(childAt);
                    }
                    this.mCallback.removeViewAt(offset);
                }
                this.mRemoveStatus = 0;
                this.mViewInRemoveView = null;
            } catch (Throwable th) {
                this.mRemoveStatus = 0;
                this.mViewInRemoveView = null;
                throw th;
            }
        } else {
            throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean removeViewIfHidden(View view) {
        int i2 = this.mRemoveStatus;
        if (i2 == 1) {
            if (this.mViewInRemoveView == view) {
                return false;
            }
            throw new IllegalStateException("Cannot call removeViewIfHidden within removeView(At) for a different view");
        } else if (i2 != 2) {
            try {
                this.mRemoveStatus = 2;
                int indexOfChild = this.mCallback.indexOfChild(view);
                if (indexOfChild == -1) {
                    unhideViewInternal(view);
                    this.mRemoveStatus = 0;
                    return true;
                } else if (this.mBucket.get(indexOfChild)) {
                    this.mBucket.remove(indexOfChild);
                    unhideViewInternal(view);
                    this.mCallback.removeViewAt(indexOfChild);
                    this.mRemoveStatus = 0;
                    return true;
                } else {
                    this.mRemoveStatus = 0;
                    return false;
                }
            } catch (Throwable th) {
                this.mRemoveStatus = 0;
                throw th;
            }
        } else {
            throw new IllegalStateException("Cannot call removeViewIfHidden within removeViewIfHidden");
        }
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    public void unhide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (this.mBucket.get(indexOfChild)) {
            this.mBucket.clear(indexOfChild);
            unhideViewInternal(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public void addView(View view, int i2, boolean z) {
        int i7;
        if (i2 < 0) {
            i7 = this.mCallback.getChildCount();
        } else {
            i7 = getOffset(i2);
        }
        this.mBucket.insert(i7, z);
        if (z) {
            hideViewInternal(view);
        }
        this.mCallback.addView(view, i7);
    }
}
