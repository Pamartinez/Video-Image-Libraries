package androidx.viewpager2.widget;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AnimateLayoutChangeDetector {
    private static final ViewGroup.MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS;
    private LinearLayoutManager mLayoutManager;

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        ZERO_MARGIN_LAYOUT_PARAMS = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    public AnimateLayoutChangeDetector(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
    }

    private boolean arePagesLaidOutContiguously() {
        boolean z;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int top;
        int i2;
        int bottom;
        int i7;
        int childCount = this.mLayoutManager.getChildCount();
        if (childCount == 0) {
            return true;
        }
        if (this.mLayoutManager.getOrientation() == 0) {
            z = true;
        } else {
            z = false;
        }
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = childCount;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = this.mLayoutManager.getChildAt(i8);
            if (childAt != null) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                } else {
                    marginLayoutParams = ZERO_MARGIN_LAYOUT_PARAMS;
                }
                int[] iArr3 = iArr2[i8];
                if (z) {
                    top = childAt.getLeft();
                    i2 = marginLayoutParams.leftMargin;
                } else {
                    top = childAt.getTop();
                    i2 = marginLayoutParams.topMargin;
                }
                iArr3[0] = top - i2;
                int[] iArr4 = iArr2[i8];
                if (z) {
                    bottom = childAt.getRight();
                    i7 = marginLayoutParams.rightMargin;
                } else {
                    bottom = childAt.getBottom();
                    i7 = marginLayoutParams.bottomMargin;
                }
                iArr4[1] = bottom + i7;
                i8++;
            } else {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
        }
        Arrays.sort(iArr2, new Comparator<int[]>() {
            public int compare(int[] iArr, int[] iArr2) {
                return iArr[0] - iArr2[0];
            }
        });
        for (int i10 = 1; i10 < childCount; i10++) {
            if (iArr2[i10 - 1][1] != iArr2[i10][0]) {
                return false;
            }
        }
        int[] iArr5 = iArr2[0];
        int i11 = iArr5[1];
        int i12 = iArr5[0];
        int i13 = i11 - i12;
        if (i12 > 0 || iArr2[childCount - 1][1] < i13) {
            return false;
        }
        return true;
    }

    private boolean hasRunningChangingLayoutTransition() {
        int childCount = this.mLayoutManager.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (hasRunningChangingLayoutTransition(this.mLayoutManager.getChildAt(i2))) {
                return true;
            }
        }
        return false;
    }

    public boolean mayHaveInterferingAnimations() {
        if ((!arePagesLaidOutContiguously() || this.mLayoutManager.getChildCount() <= 1) && hasRunningChangingLayoutTransition()) {
            return true;
        }
        return false;
    }

    private static boolean hasRunningChangingLayoutTransition(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                return true;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (hasRunningChangingLayoutTransition(viewGroup.getChildAt(i2))) {
                    return true;
                }
            }
        }
        return false;
    }
}
