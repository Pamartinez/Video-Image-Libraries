package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item;

import Bb.l;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingItemDelegate {
    private FloatingItemAdapter mAdapter;
    private int mColumnCount = -1;
    private TextView mCountView;
    private View mLayout;
    private final int mLayoutResId;
    private FlexBoxListView mListView;
    private final CategoryPropertyHelper mPropertyHelper;
    private final boolean mShowCount;
    private TextView mTitle;

    public FloatingItemDelegate(int i2, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        this.mLayoutResId = i2;
        this.mPropertyHelper = categoryPropertyHelper;
        this.mShowCount = z;
    }

    private void initCountView() {
        if (this.mShowCount) {
            this.mCountView = (TextView) this.mLayout.findViewById(R.id.floating_item_count);
        }
    }

    private void initLayoutManager(Context context) {
        if (this.mPropertyHelper.useGridLayoutOnCard()) {
            int i2 = this.mPropertyHelper.getColumnCount(context)[0];
            this.mColumnCount = i2;
            this.mListView.setLayoutManager(new GridLayoutManager(context, i2));
            return;
        }
        this.mListView.setLayoutManager(new FlexboxLayoutManager(context));
    }

    private void initListView(Context context) {
        this.mListView = (FlexBoxListView) this.mLayout.findViewById(R.id.floating_item_list_view);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.search_floating_recommend_item_list_margin_horizontal) - this.mPropertyHelper.getItemHorizontalSpacing(context);
        int floatingItemVerticalMargin = this.mPropertyHelper.getFloatingItemVerticalMargin(context);
        ViewMarginUtils.setMargin(this.mListView, Integer.valueOf(dimensionPixelOffset), Integer.valueOf(Math.max(0, floatingItemVerticalMargin - this.mPropertyHelper.getItemMarginTop(context))), Integer.valueOf(dimensionPixelOffset), Integer.valueOf(Math.max(0, floatingItemVerticalMargin - this.mPropertyHelper.getItemMarginBottom(context))));
    }

    private void initTitle() {
        TextView textView = (TextView) this.mLayout.findViewById(R.id.floating_item_title);
        this.mTitle = textView;
        ViewUtils.setText(textView, this.mPropertyHelper.getCategoryTitle(this.mLayout.getContext()));
    }

    private void updateCountView(int i2) {
        TextView textView = this.mCountView;
        if (textView != null) {
            textView.setText(StringCompat.toReadableCount(i2));
            this.mCountView.setVisibility(0);
        }
    }

    public void clearAnimation() {
        View view = this.mLayout;
        if (view != null) {
            view.clearAnimation();
            this.mLayout.setAlpha(1.0f);
        }
    }

    public void handleConfigurationChanged() {
        if (this.mAdapter != null) {
            if (this.mPropertyHelper.useGridLayoutOnCard()) {
                int i2 = this.mPropertyHelper.getColumnCount(this.mLayout.getContext())[0];
                this.mColumnCount = i2;
                this.mAdapter.updateColumnCount(i2);
                RecyclerView.LayoutManager layoutManager = this.mListView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    ((GridLayoutManager) layoutManager).setSpanCount(this.mColumnCount);
                }
            }
            this.mListView.setItemViewCacheSize(0);
            Optional.ofNullable(this.mListView.getLayoutManager()).ifPresent(new l(19));
            this.mListView.removeAllViews();
            this.mListView.getRecycledViewPool().clear();
            this.mAdapter.notifyDataSetChanged();
        }
        int dimensionPixelOffset = this.mLayout.getResources().getDimensionPixelOffset(R.dimen.search_floating_recommend_title_text_size);
        ViewUtils.setTextSize(this.mTitle, 0, dimensionPixelOffset);
        ViewUtils.setTextSize(this.mCountView, 0, dimensionPixelOffset);
    }

    public void init(View view) {
        Context context = view.getContext();
        this.mLayout = view.findViewById(this.mLayoutResId);
        initListView(context);
        initLayoutManager(context);
        initCountView();
        initTitle();
    }

    public boolean isShortcutVisible() {
        FloatingItemAdapter floatingItemAdapter = this.mAdapter;
        if (floatingItemAdapter == null || floatingItemAdapter.isEmpty()) {
            return false;
        }
        return true;
    }

    public void setData(RecommendationViewListener recommendationViewListener, ArrayList<MediaItem> arrayList) {
        if (this.mAdapter == null) {
            FloatingItemAdapter floatingItemAdapter = new FloatingItemAdapter(recommendationViewListener, this.mPropertyHelper.getItemViewType(), this.mColumnCount);
            this.mAdapter = floatingItemAdapter;
            this.mListView.setAdapter(floatingItemAdapter);
        }
        this.mAdapter.setData(arrayList);
        updateCountView(arrayList.size());
        updateVisibility();
    }

    public void startAnimation(Animation animation) {
        View view = this.mLayout;
        if (view != null) {
            view.startAnimation(animation);
        }
    }

    public void updateVisibility() {
        ViewUtils.setVisibleOrGone(this.mLayout, isShortcutVisible());
    }
}
