package com.samsung.android.gallery.widget.search.searchbar.autocomplete;

import H7.A;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingAutoCompleteView extends FilterAutoCompleteView {
    private View mContainer;
    private View mFilterButton;
    private int mInitialSize;
    private final int mMaxHeight;
    private final ViewGroup mParent;
    private final Consumer<Boolean> mVisibilityChangeConsumer;

    public FloatingAutoCompleteView(Blackboard blackboard, ViewGroup viewGroup, Consumer<Boolean> consumer) {
        super(blackboard);
        this.mParent = viewGroup;
        this.mVisibilityChangeConsumer = consumer;
        int dimensionPixelSize = AppResources.getAppContext().getResources().getDimensionPixelSize(R$dimen.bottom_search_toolbar_floating_auto_complete_max_height);
        this.mMaxHeight = dimensionPixelSize;
        this.mInitialSize = dimensionPixelSize;
    }

    /* access modifiers changed from: private */
    public void onFilterClicked(View view) {
        updateRecyclerView(false);
        Consumer<Boolean> consumer = this.mVisibilityChangeConsumer;
        if (consumer != null) {
            consumer.accept(Boolean.FALSE);
        }
    }

    public FilterAutoCompleteAdapter createAdapter() {
        return new BottomFilterAutoCompleteAdapter(this.mBlackboard);
    }

    public void dismiss() {
        ViewUtils.setVisibility(this.mContainer, 8);
    }

    public int getBackgroundColorResId() {
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            return R$color.recommended_bg_color;
        }
        return R$color.bottom_search_bar_background_color;
    }

    public View inflateContainer(Activity activity) {
        View inflate = LayoutInflater.from(activity).inflate(R$layout.floating_autocomplete_layout, this.mParent, false);
        this.mContainer = inflate;
        View findViewById = inflate.findViewById(R$id.filter_button);
        this.mFilterButton = findViewById;
        ViewUtils.setOnClickListener(findViewById, new A(25, this));
        ViewUtils.setHeight(this.mContainer, this.mInitialSize);
        this.mParent.addView(this.mContainer, 0);
        return this.mContainer;
    }

    public boolean isVisible() {
        return ViewUtils.isVisible(this.mContainer);
    }

    public void onDataChanged(ArrayList<AutoCompleteItem> arrayList, String str) {
        super.onDataChanged(arrayList, str);
        Consumer<Boolean> consumer = this.mVisibilityChangeConsumer;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(!arrayList.isEmpty()));
        }
    }

    public void setFilterButtonVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mFilterButton, z);
    }

    public void setHeight(int i2) {
        int min = Math.min(i2, this.mMaxHeight);
        View view = this.mContainer;
        if (view != null) {
            ViewUtils.setHeight(view, min);
        } else {
            this.mInitialSize = min;
        }
    }

    public void updateRecyclerView(boolean z) {
        int i2;
        View view = this.mContainer;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }
}
