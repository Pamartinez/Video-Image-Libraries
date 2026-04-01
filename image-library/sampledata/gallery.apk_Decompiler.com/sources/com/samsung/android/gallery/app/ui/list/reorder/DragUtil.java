package com.samsung.android.gallery.app.ui.list.reorder;

import android.graphics.RectF;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DragUtil {
    public static void animateView(View view, int i2, SimpleAnimationListener simpleAnimationListener) {
        if (view != null) {
            view.clearAnimation();
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), i2);
            if (simpleAnimationListener != null) {
                loadAnimation.setAnimationListener(simpleAnimationListener);
            }
            view.startAnimation(loadAnimation);
        }
    }

    public static RectF createRootRect(View view, View view2) {
        float rootX = getRootX(view, view2);
        float rootY = getRootY(view, view2);
        return new RectF(rootX, rootY, ((float) view.getWidth()) + rootX, ((float) view.getHeight()) + rootY);
    }

    public static int getClosestViewIndex(RecyclerView recyclerView, float f, float f5) {
        int i2 = -1;
        for (int i7 = 0; i7 < recyclerView.getChildCount(); i7++) {
            View childAt = recyclerView.getChildAt(i7);
            ListViewHolder listViewHolder = (ListViewHolder) recyclerView.getChildViewHolder(childAt);
            if (listViewHolder != null && !isReorderNotSupportedType(listViewHolder) && !isReorderNotSupportedItem(listViewHolder.getMediaItem())) {
                float width = ((float) childAt.getWidth()) / 2.0f;
                float height = ((float) childAt.getHeight()) / 2.0f;
                float abs = Math.abs(((childAt.getX() + width) + ((float) getParentXPosition(recyclerView))) - f);
                if (Math.abs((childAt.getY() + height) - f5) < height && (!isGridViewHolder(listViewHolder) || abs < width)) {
                    i2 = i7;
                }
            }
        }
        return i2;
    }

    public static int getHighlightIndex(RecyclerView recyclerView, float f, float f5) {
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            View childAt = recyclerView.getChildAt(i2);
            ListViewHolder listViewHolder = (ListViewHolder) recyclerView.getChildViewHolder(childAt);
            if (listViewHolder != null && !isReorderNotSupportedType(listViewHolder) && !isReorderNotSupportedItem(listViewHolder.getMediaItem())) {
                float width = (float) childAt.getWidth();
                float height = (float) childAt.getHeight();
                float x9 = (width / 2.0f) + childAt.getX() + ((float) getParentXPosition(recyclerView));
                float y = (height / 2.0f) + childAt.getY();
                float abs = Math.abs(x9 - f);
                float abs2 = Math.abs(y - f5);
                if (isValidDropIndex(listViewHolder) && abs < width / 4.0f && abs2 < height / 4.0f) {
                    return listViewHolder.getAdapterPosition();
                }
            }
        }
        return -1;
    }

    private static int getParentXPosition(View view) {
        if (!DrawerUtil.supportDrawerLayout(view.getContext())) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationInSurface(iArr);
        return iArr[0];
    }

    public static int getRelativeTop(View view, RecyclerView recyclerView) {
        int[] iArr = new int[2];
        recyclerView.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view.getLocationInWindow(iArr2);
        return iArr[1] - iArr2[1];
    }

    public static ListViewHolder getReorderTouchViewHolder(RecyclerView recyclerView, float f, float f5) {
        View view;
        View findChildViewUnder = recyclerView.findChildViewUnder(f, f5);
        if (findChildViewUnder != null) {
            ListViewHolder listViewHolder = (ListViewHolder) recyclerView.getChildViewHolder(findChildViewUnder);
            if (listViewHolder != null) {
                view = listViewHolder.getDecoView(22);
            } else {
                view = null;
            }
            if (ViewUtils.isVisible(view)) {
                float width = (float) view.getWidth();
                float f8 = 0.0f;
                float f10 = 0.0f;
                while (view != null && view != recyclerView) {
                    f8 += view.getX();
                    f10 += view.getY();
                    view = (View) view.getParent();
                }
                if (f < f8 || f > f8 + width || f5 < f10 || f5 > f10 + width) {
                    return null;
                }
                return listViewHolder;
            }
        }
        return null;
    }

    public static float getRootX(View view, View view2) {
        if (view == null) {
            throw new AssertionError("Invalid view");
        } else if (view == view2) {
            return 0.0f;
        } else {
            return getRootX((View) view.getParent(), view2) + view.getX();
        }
    }

    public static float getRootY(View view, View view2) {
        if (view == null) {
            throw new AssertionError("Invalid view");
        } else if (view == view2) {
            return 0.0f;
        } else {
            return getRootY((View) view.getParent(), view2) + view.getY();
        }
    }

    public static View getViewAt(RecyclerView recyclerView, int i2) {
        return recyclerView.getChildAt(i2 - ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
    }

    private static boolean isGridViewHolder(ListViewHolder listViewHolder) {
        if (listViewHolder.getItemViewType() == 2) {
            return true;
        }
        return false;
    }

    private static boolean isReorderNotSupportedItem(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    private static boolean isReorderNotSupportedType(ListViewHolder listViewHolder) {
        if (listViewHolder.getViewType() < 0) {
            return true;
        }
        return false;
    }

    private static boolean isValidDropIndex(ListViewHolder listViewHolder) {
        if (!isGridViewHolder(listViewHolder) || listViewHolder.getMediaItem() == null) {
            return false;
        }
        return true;
    }

    public static void measureView(View view, int i2, int i7, int i8, int i10) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(i10, 1073741824));
        view.layout(i2, i7, i8, i10);
    }
}
