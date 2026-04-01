package com.samsung.android.gallery.widget.search.filter;

import H7.A;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.search.filter.MultipleFilterKey;
import com.samsung.android.gallery.module.search.root.FilterOnlyThem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.PeopleFilterResultsEntity;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnlyThemView {
    private Blackboard mBlackboard;
    private View mButton;
    private int mCustomItemLayoutId = 0;
    /* access modifiers changed from: private */
    public int mTitleSize = -1;
    /* access modifiers changed from: private */
    public OnlyThemViewHolder mViewHolder;
    private ViewStub mViewStub;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OnlyThemViewHolder {
        boolean mCollapsed;
        View mContainer;
        FilterResultsEntity mEntity;
        boolean mIsSelected;
        TextView mTitle;
        int mTitleSize = -1;
        ImageView mTypeIcon;

        public OnlyThemViewHolder(View view, FilterOnlyThem filterOnlyThem) {
            this.mContainer = view;
            createEntity(filterOnlyThem);
            bindView();
        }

        private void bindView() {
            ViewUtils.setVisibility(this.mContainer, 0);
            this.mTypeIcon = (ImageView) this.mContainer.findViewById(R$id.type_icon);
            this.mTitle = (TextView) this.mContainer.findViewById(R$id.title);
            ViewUtils.setVisibility(this.mTypeIcon, 0);
            ViewUtils.setVisibility(this.mTitle, 0);
            setEnabled(true);
            this.mTitle.setText(this.mEntity.getName());
            if (this.mEntity.getFieldIconDrawableResId() != null) {
                this.mTypeIcon.setBackground(this.mContainer.getContext().getDrawable(this.mEntity.getFieldIconDrawableResId().intValue()));
            }
        }

        private void createEntity(FilterOnlyThem filterOnlyThem) {
            PeopleFilterResultsEntity peopleFilterResultsEntity = new PeopleFilterResultsEntity(AppResources.getString(R$string.only_them), filterOnlyThem.getKeys());
            this.mEntity = peopleFilterResultsEntity;
            peopleFilterResultsEntity.addCategory("only_them");
            this.mEntity.sumCount(-1);
        }

        private int getDimensionPixelOffset(int i2) {
            return this.mContainer.getResources().getDimensionPixelOffset(i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$restoreTitle$0(boolean z) {
            updatePadding(z, 1.0f);
        }

        private void updatePadding(boolean z, float f) {
            int i2;
            int dimensionPixelOffset = getDimensionPixelOffset(R$dimen.search_filter_result_item_horizontal_padding);
            int dimensionPixelOffset2 = getDimensionPixelOffset(R$dimen.search_only_them_collapsed_horizontal_padding);
            int dimensionPixelOffset3 = getDimensionPixelOffset(R$dimen.search_filter_result_item_icon_margin_end);
            int i7 = dimensionPixelOffset - dimensionPixelOffset2;
            if (z) {
                int i8 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i8 != 0) {
                    dimensionPixelOffset2 += (int) (((float) i7) * f);
                }
                if (i8 == 0) {
                    i2 = 0;
                } else {
                    i2 = (int) (((float) dimensionPixelOffset3) * f);
                }
            } else {
                int i10 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i10 != 0) {
                    dimensionPixelOffset -= (int) (((float) i7) * f);
                }
                if (i10 != 0) {
                    dimensionPixelOffset3 -= (int) (((float) dimensionPixelOffset3) * f);
                }
                dimensionPixelOffset2 = dimensionPixelOffset;
                i2 = dimensionPixelOffset3;
            }
            View view = this.mContainer;
            view.setPadding(dimensionPixelOffset2, view.getPaddingTop(), dimensionPixelOffset2, this.mContainer.getPaddingBottom());
            ViewMarginUtils.setMarginRelative(this.mTypeIcon, 0, (Integer) null, Integer.valueOf(i2), (Integer) null);
        }

        public boolean isCollapsed() {
            return this.mCollapsed;
        }

        public boolean isSelected() {
            return this.mIsSelected;
        }

        public boolean isVisible() {
            return ViewUtils.isVisible(this.mContainer);
        }

        public boolean resizeTitle(int i2) {
            if (this.mTitleSize < 0) {
                return false;
            }
            ViewGroup.LayoutParams layoutParams = this.mTitle.getLayoutParams();
            if (i2 > 0) {
                int i7 = layoutParams.width;
                int i8 = this.mTitleSize;
                if (i7 == i8) {
                    return false;
                }
                layoutParams.width = Math.min(i8, i2);
                this.mTitle.setLayoutParams(layoutParams);
                updatePadding(true, ((float) layoutParams.width) / ((float) this.mTitleSize));
                if (i2 < this.mTitleSize) {
                    return true;
                }
                return false;
            } else if (layoutParams.width == 0) {
                return false;
            } else {
                int i10 = this.mTitleSize + i2;
                int max = Math.max(0, i10);
                layoutParams.width = max;
                int i11 = this.mTitleSize;
                updatePadding(false, ((float) (i11 - max)) / ((float) i11));
                this.mTitle.setLayoutParams(layoutParams);
                if (i10 > 0) {
                    return true;
                }
                return false;
            }
        }

        public void restoreTitle(boolean z) {
            int i2;
            ViewGroup.LayoutParams layoutParams = this.mTitle.getLayoutParams();
            TextView textView = this.mTitle;
            int i7 = layoutParams.width;
            if (z) {
                i2 = this.mTitleSize;
            } else {
                i2 = 0;
            }
            new WidthAnimator(textView, i7, i2).withEndAction(new c(this, z)).setDuration(50).start();
            this.mCollapsed = !z;
        }

        public void setCollapsed(boolean z) {
            this.mCollapsed = z;
        }

        public void setEnabled(boolean z) {
            float f;
            this.mContainer.setSelected(this.mIsSelected);
            this.mContainer.setEnabled(z);
            View view = this.mContainer;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.5f;
            }
            view.setAlpha(f);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mContainer.setOnClickListener(onClickListener);
        }

        public void setSelected(boolean z) {
            this.mIsSelected = z;
            this.mContainer.setSelected(z);
        }

        public void setTitleSize(int i2) {
            this.mTitleSize = i2;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflate$0(View view) {
        isOnlyThemClicked();
    }

    private boolean supportOnlyThem() {
        if (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH) || !Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY) || !PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            return false;
        }
        return true;
    }

    private void updateOnlyThemTitleSize() {
        int i2 = this.mTitleSize;
        if (i2 >= 0) {
            this.mViewHolder.setTitleSize(i2);
        } else if (this.mViewHolder.mTitle.getWidth() > 0) {
            int width = this.mViewHolder.mTitle.getWidth();
            this.mTitleSize = width;
            this.mViewHolder.setTitleSize(width);
        } else {
            this.mViewHolder.mTitle.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                    OnlyThemView.this.mViewHolder.mTitle.removeOnLayoutChangeListener(this);
                    OnlyThemView onlyThemView = OnlyThemView.this;
                    onlyThemView.mTitleSize = onlyThemView.mViewHolder.mTitle.getWidth();
                    OnlyThemView.this.mViewHolder.setTitleSize(OnlyThemView.this.mTitleSize);
                }
            });
        }
    }

    public void bindView(View view) {
        this.mViewStub = (ViewStub) view.findViewById(R$id.only_them);
    }

    public int getCustomItemLayoutId() {
        return this.mCustomItemLayoutId;
    }

    public int getTitleSize() {
        return this.mTitleSize;
    }

    public void inflate(FilterOnlyThem filterOnlyThem) {
        boolean z;
        ViewStub viewStub;
        if (filterOnlyThem != null) {
            if (this.mButton == null && (viewStub = this.mViewStub) != null) {
                int i2 = this.mCustomItemLayoutId;
                if (i2 != 0) {
                    viewStub.setLayoutResource(i2);
                }
                this.mButton = this.mViewStub.inflate();
            }
            if (this.mButton != null) {
                OnlyThemViewHolder onlyThemViewHolder = this.mViewHolder;
                if (onlyThemViewHolder == null || !onlyThemViewHolder.isSelected()) {
                    OnlyThemViewHolder onlyThemViewHolder2 = this.mViewHolder;
                    if (onlyThemViewHolder2 == null || !onlyThemViewHolder2.isCollapsed()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    this.mViewHolder = new OnlyThemViewHolder(this.mButton, filterOnlyThem);
                    this.mViewHolder.setSelected(ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "people_only_them", false));
                    this.mViewHolder.setCollapsed(z);
                    this.mViewHolder.setOnClickListener(new A(21, this));
                    updateOnlyThemTitleSize();
                }
            }
        }
    }

    public void init(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    public boolean isCollapsed() {
        if (!supportOnlyThem() || !this.mViewHolder.isCollapsed()) {
            return false;
        }
        return true;
    }

    public void isOnlyThemClicked() {
        OnlyThemViewHolder onlyThemViewHolder = this.mViewHolder;
        if (onlyThemViewHolder != null) {
            onlyThemViewHolder.setSelected(!onlyThemViewHolder.isSelected());
            this.mBlackboard.postEvent(EventMessage.obtain(8004));
            if (Features.isEnabled(Features.SUPPORT_MULTI_KEYWORD_SEARCH)) {
                this.mBlackboard.postEvent(EventMessage.obtain(8016, this.mViewHolder.mEntity));
                return;
            }
            this.mBlackboard.postEvent(EventMessage.obtain(1066, MultipleFilterKey.build(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), this.mViewHolder.mEntity)));
        }
    }

    public boolean isVisible() {
        return ViewUtils.isVisible(this.mButton);
    }

    public boolean onDelegateTouch(MotionEvent motionEvent, float f) {
        boolean z = true;
        if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return this.mViewHolder.resizeTitle((int) (motionEvent.getRawX() - f));
        }
        OnlyThemViewHolder onlyThemViewHolder = this.mViewHolder;
        if (motionEvent.getRawX() <= f) {
            z = false;
        }
        onlyThemViewHolder.restoreTitle(z);
        return false;
    }

    public void reset() {
        OnlyThemViewHolder onlyThemViewHolder;
        if (supportOnlyThem() && (onlyThemViewHolder = this.mViewHolder) != null) {
            onlyThemViewHolder.setSelected(false);
        }
    }

    public void setButtonVisible(boolean z) {
        ViewUtils.setVisibleOrGone(this.mButton, z);
    }

    public void setCustomItemLayoutId(int i2) {
        this.mCustomItemLayoutId = i2;
    }

    public void setEnabled(boolean z) {
        OnlyThemViewHolder onlyThemViewHolder = this.mViewHolder;
        if (onlyThemViewHolder != null) {
            onlyThemViewHolder.setEnabled(z);
        }
    }

    public void setTitleSize(int i2) {
        this.mTitleSize = i2;
    }

    public boolean supportDelegateTouchEvent() {
        OnlyThemViewHolder onlyThemViewHolder = this.mViewHolder;
        if (onlyThemViewHolder == null || !onlyThemViewHolder.isVisible()) {
            return false;
        }
        return true;
    }
}
