package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    private boolean mAllowDividerAfterLastItem = false;
    private boolean mAllowItemOffsets = true;
    private final Rect mBounds = new Rect();
    private Drawable mDivider;
    private int mOrientation;

    public DividerItemDecoration(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ATTRS);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        this.mDivider = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        obtainStyledAttributes.recycle();
        setOrientation(i2);
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int i2;
        int i7;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i7 = recyclerView.getPaddingTop();
            i2 = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i7, recyclerView.getWidth() - recyclerView.getPaddingRight(), i2);
        } else {
            i2 = recyclerView.getHeight();
            i7 = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = recyclerView.getChildAt(i8);
            recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int round = Math.round(childAt.getTranslationX()) + this.mBounds.right;
            this.mDivider.setBounds(round - this.mDivider.getIntrinsicWidth(), i7, round, i2);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int i2;
        int i7;
        int i8;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i7 = recyclerView.getPaddingLeft();
            i2 = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i7, recyclerView.getPaddingTop(), i2, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            i2 = recyclerView.getWidth();
            i7 = 0;
        }
        if (this.mAllowDividerAfterLastItem) {
            i8 = recyclerView.getChildCount();
        } else {
            i8 = recyclerView.getChildCount() - 1;
        }
        for (int i10 = 0; i10 < i8; i10++) {
            View childAt = recyclerView.getChildAt(i10);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int round = Math.round(childAt.getTranslationY()) + this.mBounds.bottom;
            this.mDivider.setBounds(i7, round - this.mDivider.getIntrinsicHeight(), i2, round);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (!this.mAllowItemOffsets) {
            rect.set(0, 0, 0, 0);
            return;
        }
        Drawable drawable = this.mDivider;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.mOrientation == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getLayoutManager() != null && this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawVertical(canvas, recyclerView);
            } else {
                drawHorizontal(canvas, recyclerView);
            }
        }
    }

    public void setOrientation(int i2) {
        if (i2 == 0 || i2 == 1) {
            this.mOrientation = i2;
            return;
        }
        throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
    }
}
