package com.samsung.android.gallery.widget.details;

import A.a;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.List;
import q1.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsLayoutManager {
    /* access modifiers changed from: private */
    public static final StringCompat TAG = new StringCompat("DetailsLayoutManager");

    public static FlexboxLayoutManager createFlexboxLayoutManager(final RecyclerView recyclerView) {
        AnonymousClass2 r1 = new FlexboxLayoutManager(recyclerView.getContext()) {
            int mLastSize = 0;

            public void onLayoutCompleted(RecyclerView.State state) {
                int computeVerticalScrollRange = computeVerticalScrollRange(state);
                int height = getHeight();
                List<c> flexLines = getFlexLines();
                if (computeVerticalScrollRange != height && (this.mLastSize != state.getItemCount() || this.mLastSize == 0)) {
                    ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
                    if (flexLines.size() > 0) {
                        int i2 = 0;
                        int i7 = getFlexLines().get(0).g;
                        View childAt = getChildAt(0);
                        int size = flexLines.size() * i7;
                        if (childAt != null) {
                            i2 = childAt.getPaddingBottom();
                        }
                        int i8 = size - i2;
                        a.k(i8, "modified scroll height=", "FlexBoxLayout");
                        layoutParams.height = i8;
                        recyclerView.setLayoutParams(layoutParams);
                        recyclerView.postDelayed(new a(this), 1);
                    }
                }
                this.mLastSize = flexLines.size();
                super.onLayoutCompleted(state);
            }
        };
        r1.setFlexWrap(1);
        r1.setFlexDirection(0);
        r1.setAlignItems(4);
        return r1;
    }

    public static GridLayoutManager createGridLayoutManager(RecyclerView recyclerView, int i2) {
        return new GridLayoutManager(recyclerView.getContext(), i2) {
            public boolean canScrollVertically() {
                return false;
            }

            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    StringCompat a7 = DetailsLayoutManager.TAG;
                    Log.e(a7, "onLayoutChildren fail=" + e.getMessage());
                    new InternalException("onLayoutChildren fail").post();
                }
            }

            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
    }

    public static LinearLayoutManager createLinearLayoutManager(RecyclerView recyclerView) {
        return new LinearLayoutManager(recyclerView.getContext(), 0, false) {
            public boolean canScrollHorizontally() {
                if (!super.canScrollHorizontally()) {
                    return false;
                }
                if (getItemCount() == getChildCount() && findLastVisibleItemPosition() == findLastCompletelyVisibleItemPosition() && findFirstVisibleItemPosition() == findFirstCompletelyVisibleItemPosition()) {
                    return false;
                }
                return true;
            }

            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    StringCompat a7 = DetailsLayoutManager.TAG;
                    Log.e(a7, "onLayoutChildren fail=" + e.getMessage());
                    new InternalException("onLayoutChildren fail").post();
                }
            }

            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
    }
}
