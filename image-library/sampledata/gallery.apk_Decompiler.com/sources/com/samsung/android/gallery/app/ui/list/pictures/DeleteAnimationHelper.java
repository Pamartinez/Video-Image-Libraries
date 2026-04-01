package com.samsung.android.gallery.app.ui.list.pictures;

import B2.h;
import D6.a;
import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteAnimationHelper {
    private static final Comparator<Integer> POSITION_COMPARATOR = new a(24);
    private final ArrayList<Integer> mDeletePositions = new ArrayList<>();
    private int mTotalItemCount;

    private int getNewPosition(int i2) {
        Iterator<Integer> it = this.mDeletePositions.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            if (it.next().intValue() < i2) {
                i7++;
            }
        }
        return i2 - i7;
    }

    private boolean isScrollIdle(RecyclerView recyclerView) {
        if (recyclerView.getScrollState() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareScaledDelete$1(ViewGroup.LayoutParams layoutParams, ValueAnimator valueAnimator) {
        if (valueAnimator.getAnimatedFraction() >= 1.0f) {
            layoutParams.width = -1;
            layoutParams.height = -1;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(Integer num, Integer num2) {
        if (num.intValue() > num2.intValue()) {
            return -1;
        }
        return 1;
    }

    private void prepareScaledDelete(RecyclerView recyclerView, GridLayoutManager gridLayoutManager, PicturesViewAdapter picturesViewAdapter) {
        View view;
        int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            ListViewHolder listViewHolder = (ListViewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
            if (listViewHolder != null) {
                view = listViewHolder.getScalableView();
            } else {
                view = null;
            }
            if (view != null) {
                if (this.mDeletePositions.contains(Integer.valueOf(listViewHolder.getViewPosition()))) {
                    view.setVisibility(8);
                } else {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.width = listViewHolder.getRootView().getLayoutParams().width;
                    layoutParams.height = listViewHolder.getRootView().getLayoutParams().height;
                    int newPosition = getNewPosition(listViewHolder.getViewPosition());
                    ScaleAnimator scaleAnimator = new ScaleAnimator(view, new RectF(0.0f, 0.0f, (float) layoutParams.width, (float) layoutParams.height), new RectF(0.0f, 0.0f, (float) picturesViewAdapter.getSpanSize(newPosition), (float) picturesViewAdapter.getItemHeight(newPosition)));
                    scaleAnimator.enableUpdateLayoutParam(true);
                    scaleAnimator.setDuration(500);
                    scaleAnimator.addUpdateListener(new h(14, layoutParams));
                    scaleAnimator.start();
                }
            }
        }
    }

    public boolean handleDeleteAnimation(RecyclerView recyclerView, PicturesViewAdapter picturesViewAdapter, int i2) {
        int i7;
        if (this.mDeletePositions.isEmpty() || !isScrollIdle(recyclerView)) {
            onAbortDelete();
            return false;
        } else if (i2 != this.mTotalItemCount - this.mDeletePositions.size()) {
            StringBuilder o2 = C0086a.o(i2, "item size not matched - delete anim fail : ", " , ");
            o2.append(this.mTotalItemCount);
            o2.append(" , ");
            o2.append(this.mDeletePositions.size());
            Log.d("DeleteAnimationHelper", o2.toString());
            onAbortDelete();
            return false;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            if (picturesViewAdapter.isRealRatio()) {
                prepareScaledDelete(recyclerView, gridLayoutManager, picturesViewAdapter);
            }
            int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            int size = this.mDeletePositions.size();
            Iterator<Integer> it = this.mDeletePositions.iterator();
            int i8 = 0;
            int i10 = -1;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int intValue = it.next().intValue();
                if (this.mDeletePositions.size() == 1) {
                    picturesViewAdapter.notifyItemRemoved(intValue);
                    break;
                }
                if (findFirstVisibleItemPosition <= intValue && intValue <= findLastVisibleItemPosition) {
                    picturesViewAdapter.notifyItemRemoved(intValue);
                    if (i8 == 1) {
                        picturesViewAdapter.notifyItemRemoved(i10);
                    } else if (i8 > 1) {
                        picturesViewAdapter.notifyItemRangeRemoved(i10, i8);
                    }
                    i8 = 0;
                } else if (i10 == -1) {
                    i8++;
                } else {
                    if (intValue - i10 > 1) {
                        if (i8 == 1) {
                            picturesViewAdapter.notifyItemRemoved(i10);
                        } else {
                            picturesViewAdapter.notifyItemRangeRemoved(i10, i8);
                        }
                        i7 = 0;
                        i10 = -1;
                    } else {
                        i7 = i8 + 1;
                        i10 = intValue;
                    }
                    if (intValue == this.mDeletePositions.get(size - 1).intValue()) {
                        picturesViewAdapter.notifyItemRangeRemoved(intValue, i8);
                    }
                }
                i10 = intValue;
            }
            onAbortDelete();
            StringBuilder sb2 = new StringBuilder("deleteAnim - size : ");
            sb2.append(size);
            sb2.append(" , took : ");
            A.a.x(sb2, currentTimeMillis, "DeleteAnimationHelper");
            return true;
        }
    }

    public void onAbortDelete() {
        this.mTotalItemCount = 0;
        this.mDeletePositions.clear();
    }

    public void onPrepareDelete(GridLayoutManager gridLayoutManager, SelectionManager selectionManager, int i2) {
        this.mDeletePositions.clear();
        ArrayList<Integer> selectedList = selectionManager.getSelectedList();
        if (selectedList != null && !selectedList.isEmpty()) {
            this.mDeletePositions.addAll(selectedList);
            ArrayList<Integer> selectedDividerList = selectionManager.getSelectedDividerList();
            if (selectedDividerList != null && !selectedDividerList.isEmpty()) {
                this.mDeletePositions.addAll(selectedDividerList);
            }
        }
        if (!this.mDeletePositions.isEmpty()) {
            this.mDeletePositions.sort(POSITION_COMPARATOR);
        }
        int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            if (this.mDeletePositions.contains(Integer.valueOf(findFirstVisibleItemPosition))) {
                this.mTotalItemCount = i2;
                return;
            }
        }
        onAbortDelete();
    }
}
