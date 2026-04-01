package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchCustomViewAdapter extends RecyclerView.Adapter<SearchCustomViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private final ItemTouchHelper.Callback mCallback;
    private boolean mIsReorderMode;
    private final ArrayList<String> mSearchCategoryList;
    private final ItemTouchHelper mTouchHelper;

    public SearchCustomViewAdapter(List<String> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.mSearchCategoryList = arrayList;
        AnonymousClass1 r1 = new ItemTouchHelper.Callback() {
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                SearchCustomViewAdapter.this.onItemDropped(viewHolder);
            }

            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int i2;
                if (SearchCustomViewAdapter.this.isReorderMode()) {
                    i2 = 3;
                } else {
                    i2 = 0;
                }
                return ItemTouchHelper.Callback.makeMovementFlags(i2, 0);
            }

            public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f5, int i2, boolean z) {
                if (viewHolder.getAbsoluteAdapterPosition() == 0 && f5 < 0.0f) {
                    return;
                }
                if (viewHolder.getAbsoluteAdapterPosition() != SearchCustomViewAdapter.this.getItemCount() - 1 || f5 <= 0.0f) {
                    super.onChildDraw(canvas, recyclerView, viewHolder, f, f5, i2, z);
                }
            }

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                return SearchCustomViewAdapter.this.onItemMove(viewHolder.getAbsoluteAdapterPosition(), viewHolder2.getAbsoluteAdapterPosition());
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
            }
        };
        this.mCallback = r1;
        arrayList.addAll(list);
        this.mTouchHelper = new ItemTouchHelper(r1);
    }

    /* access modifiers changed from: private */
    public void onItemDropped(RecyclerView.ViewHolder viewHolder) {
        resetHighlightedBorder(viewHolder);
        if (VisualSearchCategory.update(this.mSearchCategoryList)) {
            Blackboard.publishGlobal("command:///SearchCategoryReorder", Boolean.TRUE);
        } else {
            Log.w(this.TAG, "onItemDropped. update failed");
        }
    }

    /* access modifiers changed from: private */
    public boolean onItemMove(int i2, int i7) {
        this.mSearchCategoryList.add(i7, this.mSearchCategoryList.remove(i2));
        notifyItemMoved(i2, i7);
        return true;
    }

    private void resetHighlightedBorder(RecyclerView.ViewHolder viewHolder) {
        Context context = viewHolder.itemView.getContext();
        if (context == null) {
            Log.e(this.TAG, "context is null");
            return;
        }
        ((CardView) viewHolder.itemView).setCardBackgroundColor(context.getColor(R$color.settings_preference_list_item_background));
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843534, typedValue, true);
        viewHolder.itemView.setForeground(context.getDrawable(typedValue.resourceId));
    }

    private void setHighlightedBorder(View view) {
        if (view != null) {
            ((CardView) view).setCardBackgroundColor(AppResources.getColor(R$color.recommended_bg_color));
            view.setForeground(AppResources.getAppContext().getDrawable(R$drawable.search_custom_highlighted_border));
        }
    }

    public void attachTouchHelper(RecyclerView recyclerView) {
        this.mTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void exitReorder() {
        this.mIsReorderMode = false;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.mSearchCategoryList.size();
    }

    public boolean isDisableCategoryItem(String str) {
        return false;
    }

    public boolean isReorderMode() {
        return this.mIsReorderMode;
    }

    public void onReorderItemTouch(RecyclerView.ViewHolder viewHolder, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mTouchHelper.startDrag(viewHolder);
            setHighlightedBorder(view);
        }
    }

    public void startReorder() {
        this.mIsReorderMode = true;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(SearchCustomViewHolder searchCustomViewHolder, int i2) {
        String str = this.mSearchCategoryList.get(i2);
        if (isDisableCategoryItem(str)) {
            searchCustomViewHolder.itemView.getLayoutParams().height = 0;
            ViewUtils.setVisibleOrGone(searchCustomViewHolder.itemView, false);
            return;
        }
        searchCustomViewHolder.onBind(str);
        searchCustomViewHolder.setReorderMode(isReorderMode());
    }

    public SearchCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new SearchCustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.recycler_item_search_customize, viewGroup, false), i2).setTouchCallback(new y(2, this));
    }
}
