package com.samsung.android.gallery.app.ui.list.timeline.quicksearch;

import O3.l;
import Qb.c;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickSearchView {
    private View mContainer;
    private View mDateFilterButton;
    private View mDateFilterCard;
    private TextView mEndDateTextView;
    private View mExpandButton;
    private View mImageFilterButton;
    private View mLocationFilterButton;
    private View mLocationFilterCard;
    private RecyclerView mLocationFilterListView;
    private View mPeopleFilterButton;
    private View mPeopleFilterCard;
    private RecyclerView mPeopleFilterListView;
    private TextView mStartDateTextView;
    private View mVideoFilterButton;

    private float getExpandedRotation() {
        if (Features.isEnabled(Features.IS_RTL)) {
            return 180.0f;
        }
        return -180.0f;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        this.mImageFilterButton = view.findViewById(R.id.quick_search_image_filter_button);
        this.mVideoFilterButton = view.findViewById(R.id.quick_search_video_filter_button);
        this.mDateFilterButton = view.findViewById(R.id.quick_search_date_filter_button);
        this.mLocationFilterButton = view.findViewById(R.id.quick_search_location_filter_button);
        this.mPeopleFilterButton = view.findViewById(R.id.quick_search_people_filter_button);
        this.mExpandButton = view.findViewById(R.id.quick_search_expand_button);
        this.mDateFilterCard = view.findViewById(R.id.quick_search_date_card);
        this.mLocationFilterCard = view.findViewById(R.id.quick_search_location_card);
        this.mPeopleFilterCard = view.findViewById(R.id.quick_search_people_card);
        this.mStartDateTextView = (TextView) view.findViewById(R.id.quick_search_date_card_start_text_view);
        this.mEndDateTextView = (TextView) view.findViewById(R.id.quick_search_date_card_end_text_view);
        this.mLocationFilterListView = (RecyclerView) view.findViewById(R.id.quick_search_location_filter_list_view);
        this.mPeopleFilterListView = (RecyclerView) view.findViewById(R.id.quick_search_people_filter_list_view);
        ViewUtils.setVisibleOrGone(this.mImageFilterButton, false);
        ViewUtils.setVisibleOrGone(this.mVideoFilterButton, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$rotateExpandIcon$6(boolean z, View view) {
        float f;
        float rotation = view.getRotation();
        if (z) {
            f = getExpandedRotation();
        } else {
            f = 0.0f;
        }
        ObjectAnimator.ofFloat(this.mExpandButton, View.ROTATION, new float[]{rotation, f}).setDuration(300).start();
    }

    private void setFilterButtonTextColor(View view, boolean z) {
        int i2;
        TextView textView = (TextView) view;
        if (z) {
            i2 = R.color.gallery_color_control_activated_no_theme;
        } else {
            i2 = R.color.update_app_card_view_text_color;
        }
        ViewUtils.setTextColor(textView, i2);
    }

    public void bindView(View view) {
        View findViewById = view.findViewById(R.id.quick_search_view_container);
        this.mContainer = findViewById;
        Optional.ofNullable(findViewById).ifPresent(new c(6, this));
    }

    public void refreshExpandIconRotation() {
        Optional.ofNullable(this.mExpandButton).ifPresent(new l(29));
    }

    public void rotateExpandIcon(boolean z) {
        Optional.ofNullable(this.mExpandButton).ifPresent(new E7.c(this, z, 4));
    }

    public void setLocationFilterListViewAdapter(QuickSearchLocationFilterListViewAdapter quickSearchLocationFilterListViewAdapter) {
        Optional.ofNullable(this.mLocationFilterListView).ifPresent(new b(quickSearchLocationFilterListViewAdapter, 1));
    }

    public void setLocationFilterListViewLayoutManager(RecyclerView.LayoutManager layoutManager) {
        Optional.ofNullable(this.mLocationFilterListView).ifPresent(new N4.c(1, layoutManager));
    }

    public void setOnDateFilterButtonClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mDateFilterButton, onClickListener);
    }

    public void setOnDateFilterCardClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mDateFilterCard, onClickListener);
    }

    public void setOnExpandButtonClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mExpandButton, onClickListener);
    }

    public void setOnImageFilterButtonClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mImageFilterButton, onClickListener);
    }

    public void setOnLocationFilterButtonClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mLocationFilterButton, onClickListener);
    }

    public void setOnPeopleFilterButtonClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mPeopleFilterButton, onClickListener);
    }

    public void setOnVideoFilterButtonClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mVideoFilterButton, onClickListener);
    }

    public void setPeopleFilterListViewAdapter(QuickSearchPeopleFilterListViewAdapter quickSearchPeopleFilterListViewAdapter) {
        Optional.ofNullable(this.mPeopleFilterListView).ifPresent(new b(quickSearchPeopleFilterListViewAdapter, 0));
    }

    public void setPeopleFilterListViewLayoutManager(RecyclerView.LayoutManager layoutManager) {
        Optional.ofNullable(this.mPeopleFilterListView).ifPresent(new N4.c(2, layoutManager));
    }

    public void unbindView() {
        View view = this.mImageFilterButton;
        if (view != null) {
            view.setOnClickListener((View.OnClickListener) null);
            this.mImageFilterButton = null;
        }
        View view2 = this.mVideoFilterButton;
        if (view2 != null) {
            view2.setOnClickListener((View.OnClickListener) null);
            this.mVideoFilterButton = null;
        }
        View view3 = this.mDateFilterButton;
        if (view3 != null) {
            view3.setOnClickListener((View.OnClickListener) null);
            this.mDateFilterButton = null;
        }
        View view4 = this.mLocationFilterButton;
        if (view4 != null) {
            view4.setOnClickListener((View.OnClickListener) null);
            this.mLocationFilterButton = null;
        }
        View view5 = this.mPeopleFilterButton;
        if (view5 != null) {
            view5.setOnClickListener((View.OnClickListener) null);
            this.mPeopleFilterButton = null;
        }
        View view6 = this.mExpandButton;
        if (view6 != null) {
            view6.setOnClickListener((View.OnClickListener) null);
            this.mExpandButton = null;
        }
        View view7 = this.mDateFilterCard;
        if (view7 != null) {
            view7.setOnClickListener((View.OnClickListener) null);
            this.mDateFilterCard = null;
        }
        if (this.mLocationFilterCard != null) {
            this.mLocationFilterCard = null;
        }
        if (this.mPeopleFilterCard != null) {
            this.mPeopleFilterCard = null;
        }
        if (this.mStartDateTextView != null) {
            this.mStartDateTextView = null;
        }
        if (this.mEndDateTextView != null) {
            this.mEndDateTextView = null;
        }
        if (this.mLocationFilterListView != null) {
            this.mLocationFilterListView = null;
        }
        if (this.mPeopleFilterListView != null) {
            this.mPeopleFilterListView = null;
        }
    }

    public void updateContainerVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mContainer, z);
    }

    public void updateDateFilterButtonTextColor(boolean z) {
        setFilterButtonTextColor(this.mDateFilterButton, z);
    }

    public void updateDateFilterCardVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mDateFilterCard, z);
    }

    public void updateDateFilterText(String str, String str2) {
        ViewUtils.setText(this.mStartDateTextView, str);
        ViewUtils.setText(this.mEndDateTextView, str2);
    }

    public void updateDateFilterTextVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mStartDateTextView, z);
        ViewUtils.setVisibleOrGone(this.mEndDateTextView, z);
    }

    public void updateImageFilterButtonTextColor(boolean z) {
        setFilterButtonTextColor(this.mImageFilterButton, z);
    }

    public void updateLocationFilterButtonTextColor(boolean z) {
        setFilterButtonTextColor(this.mLocationFilterButton, z);
    }

    public void updateLocationFilterCardVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mLocationFilterCard, z);
    }

    public void updatePeopleFilterButtonTextColor(boolean z) {
        setFilterButtonTextColor(this.mPeopleFilterButton, z);
    }

    public void updatePeopleFilterCardVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mPeopleFilterCard, z);
    }

    public void updateVideoFilterButtonTextColor(boolean z) {
        setFilterButtonTextColor(this.mVideoFilterButton, z);
    }
}
