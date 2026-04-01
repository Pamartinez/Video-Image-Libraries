package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import A.a;
import A4.C0385u;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowInsets;
import androidx.core.view.ViewCompat;
import com.samsung.android.gallery.app.controller.story.StartBgmPickerCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmPickerBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmPickerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.BottomSheetCallback;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;
import o6.C0497a;
import o6.C0498b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmPickerDelegate extends Delegate implements BottomSheetCallback {
    private View mBgmPickerIcon;
    private BgmPickerView mBgmPickerView;
    private BgmPickerBehavior<View> mBottomSheetBehavior;
    private View mBottomSheetLayout;
    private View mMainLayout;

    public BgmPickerDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public boolean allowTouchIntercept() {
        return !SheetBehaviorCompat.isClosed(getState());
    }

    private void bindBottomSheet() {
        if (this.mBgmPickerView == null) {
            BgmPickerView bgmPickerView = new BgmPickerView(this.mView);
            this.mBgmPickerView = bgmPickerView;
            bgmPickerView.initView((ViewGroup) this.mBottomSheetLayout, this.mEventHandler.getEffectTheme(), getExpandOffset());
        }
        ViewUtils.setVisibility(this.mBottomSheetLayout, 0);
        updateBottomSheetBackground();
    }

    /* access modifiers changed from: private */
    public void downloadAllBgm(Object... objArr) {
        BgmPickerView bgmPickerView = this.mBgmPickerView;
        if (bgmPickerView != null) {
            bgmPickerView.downloadAllBgm();
        }
    }

    private int getExpandOffset() {
        int i2;
        int dimensionPixelOffset = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_bgm_picker_expend_offset);
        boolean isLandscape = this.mView.isLandscape();
        Context context = this.mView.getContext();
        if (isLandscape) {
            i2 = SystemUi.getToolBarHeight(context);
        } else {
            i2 = SystemUi.getToolBarHeightWithPadding(context);
        }
        return dimensionPixelOffset + i2;
    }

    private int getState() {
        BgmPickerBehavior<View> bgmPickerBehavior = this.mBottomSheetBehavior;
        if (bgmPickerBehavior != null) {
            return bgmPickerBehavior.getState();
        }
        return 5;
    }

    /* access modifiers changed from: private */
    public void hideBgmPicker(Object[] objArr) {
        hideBottomSheet();
    }

    private boolean hideBottomSheet() {
        if (this.mBgmPickerView == null || !SheetBehaviorCompat.isInExpanded(getState())) {
            return false;
        }
        this.mBottomSheetBehavior.setState(5);
        if (this.mView.getOptions().supportFilter()) {
            return true;
        }
        this.mEventHandler.postEvent(Event.HIDE_THEME_OPTION, new Object[0]);
        return true;
    }

    private void initBgmPickerIcon(View view) {
        if (view != null && this.mView.getOptions().supportBgm()) {
            View findViewById = view.findViewById(R.id.story_bgm_picker_icon);
            this.mBgmPickerIcon = findViewById;
            ViewUtils.setOnClickListener(findViewById, new C0496a(5, this));
            ViewUtils.setVisibility(this.mBgmPickerIcon, 0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$3(Object[] objArr) {
        if (onPickerIconClicked()) {
            this.mEventHandler.postEvent(Event.SHOW_THEME_OPTION, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$0(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(SheetBehaviorCompat.isExpanded(this.mBottomSheetBehavior.getState()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$1(DataRequest dataRequest, Object[] objArr) {
        boolean z;
        BgmPickerView bgmPickerView = this.mBgmPickerView;
        if (bgmPickerView == null || !bgmPickerView.isTipCardVisible()) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initBgmPickerIcon$2(View view) {
        onPickerIconClicked();
    }

    /* access modifiers changed from: private */
    public void onBgmChanged(Object... objArr) {
        BgmPickerView bgmPickerView;
        if (objArr[0].isMyMusic && (bgmPickerView = this.mBgmPickerView) != null) {
            bgmPickerView.resetFocus();
        }
    }

    private boolean onPickerIconClicked() {
        IStoryHighlightView iStoryHighlightView = this.mView;
        if (!iStoryHighlightView.setInputBlock(this.TAG + "_onPickerIconClicked")) {
            return false;
        }
        String str = (String) this.mEventHandler.requestData(DataRequest.REQ_BGM_NAME);
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_BGM_PICKER_UI);
        if (isEnabled) {
            bindBottomSheet();
            BgmPickerView bgmPickerView = this.mBgmPickerView;
            if (bgmPickerView != null) {
                bgmPickerView.updateCurrentBgm();
            }
            this.mBottomSheetBehavior.setState(3);
            setImportantAccessibility(this.mBgmPickerIcon, false);
        } else {
            new StartBgmPickerCmd().execute(this.mView.getEventContext(), str);
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_BGM_PICKER_CLICKED);
        return isEnabled;
    }

    private void setImportantAccessibility(View view, boolean z) {
        int i2;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            ViewCompat.setImportantForAccessibility(view, i2);
        }
    }

    private void updateBottomSheetBackground() {
        Bitmap bitmap;
        Drawable drawable = null;
        ViewPagerHolder viewPagerHolder = (ViewPagerHolder) requestData(DataRequest.CURRENT_VIEW_HOLDER, null);
        if (viewPagerHolder != null) {
            drawable = viewPagerHolder.getBlurDrawable();
        }
        if ((drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null) {
            drawable = new BitmapDrawable(viewPagerHolder.itemView.getResources(), bitmap);
        }
        this.mBottomSheetLayout.setBackground(drawable);
    }

    private void updateBottomSheetLayout() {
        updateBottomSheetWidth();
        this.mBottomSheetBehavior.setExpandedOffsetDelta(getExpandOffset());
    }

    private void updateBottomSheetWidth() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mBottomSheetLayout.getLayoutParams();
        int dimensionPixelOffset = this.mView.getResources().getDimensionPixelOffset(R.dimen.stories_bgm_sheet_horizontal_margin);
        int coarseWindowWidth = ResourceCompat.getCoarseWindowWidth(this.mView.getResources());
        marginLayoutParams.width = (int) Math.max(ResourceCompat.getFloatFromDimension(this.mView.getResources(), (int) R.dimen.stories_bgm_sheet_width_ratio) * ((float) coarseWindowWidth), (float) (coarseWindowWidth - (dimensionPixelOffset * 2)));
        this.mBottomSheetLayout.setLayoutParams(marginLayoutParams);
    }

    public void addListenEvent() {
        addEvent(Event.SHOW_BGM_PICKER_ALONE, new C0497a(this, 0));
        addEvent(Event.ON_BGM_CHANGED, new C0497a(this, 1));
        addEvent(Event.HIDE_BGM_PICKER, new C0497a(this, 2));
        addEvent(Event.DOWNLOAD_ALL_BGM, new C0497a(this, 3));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.BGM_PICKER_VISIBLE, new C0498b(this, 0));
        addRequestProvider(DataRequest.BGM_TIP_CARD_VISIBLE, new C0498b(this, 1));
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        initBgmPickerIcon(this.mView.getView());
        updateBottomSheetLayout();
        BgmPickerView bgmPickerView = this.mBgmPickerView;
        if (bgmPickerView != null) {
            bgmPickerView.handleDensityChange(i2, getExpandOffset());
        }
    }

    public void handleResolutionChange(int i2) {
        updateBottomSheetLayout();
        BgmPickerView bgmPickerView = this.mBgmPickerView;
        if (bgmPickerView != null) {
            bgmPickerView.handleResolutionChange(i2, getExpandOffset());
        }
        if (this.mBgmPickerIcon != null) {
            int dimensionPixelOffset = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_highlight_deco_duration_min_height);
            ViewGroup.LayoutParams layoutParams = this.mBgmPickerIcon.getLayoutParams();
            layoutParams.width = dimensionPixelOffset;
            layoutParams.height = dimensionPixelOffset;
            ((ViewGroup.MarginLayoutParams) layoutParams).setMarginEnd(this.mView.getResources().getDimensionPixelOffset(R.dimen.story_highlight_bgm_icon_margin_end));
            this.mBgmPickerIcon.setLayoutParams(layoutParams);
        }
    }

    public void initView(View view) {
        initBgmPickerIcon(view);
        this.mMainLayout = view.findViewById(R.id.main_layout);
        View findViewById = view.findViewById(R.id.story_highlight_bgm_sheet);
        this.mBottomSheetLayout = findViewById;
        ViewUtils.setVisibility(findViewById, 4);
        this.mBottomSheetLayout.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(R.dimen.story_highlight_list_fragment_top_radius);
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + dimensionPixelOffset, (float) dimensionPixelOffset);
            }
        });
        updateBottomSheetWidth();
        updateBottomSheetBackground();
        this.mBottomSheetLayout.setClipToOutline(true);
        BgmPickerBehavior<View> from = BgmPickerBehavior.from(this.mBottomSheetLayout);
        this.mBottomSheetBehavior = from;
        from.addBottomSheetCallback(this);
        this.mBottomSheetBehavior.setTouchInterceptProvider(new C0385u(21, this));
        this.mBottomSheetBehavior.setDraggable(false);
        this.mBottomSheetBehavior.setExpandedOffsetDelta(getExpandOffset());
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        BgmPickerView bgmPickerView = this.mBgmPickerView;
        if (bgmPickerView != null) {
            bgmPickerView.onApplyWindowInsets(view, windowInsets);
        }
    }

    public boolean onBackPressed() {
        return hideBottomSheet();
    }

    public void onDestroy() {
        BgmPickerView bgmPickerView = this.mBgmPickerView;
        if (bgmPickerView != null) {
            bgmPickerView.destroy();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        BgmPickerBehavior<View> bgmPickerBehavior = this.mBottomSheetBehavior;
        if (bgmPickerBehavior != null) {
            bgmPickerBehavior.onMultiWindowModeChanged(z);
        }
    }

    public void onSlide(View view, float f) {
        if (f <= 0.6f) {
            this.mMainLayout.setBackgroundColor(Color.argb(f, 0.0f, 0.0f, 0.0f));
        }
    }

    public void onStateChanged(View view, int i2) {
        if (SheetBehaviorCompat.isExpanded(i2)) {
            this.mView.invalidateOptionsMenu();
            this.mBottomSheetBehavior.setDraggable(true);
            this.mEventHandler.postEvent(Event.BGM_PICKER_STATE_CHANGED, Boolean.FALSE);
            a.k(i2, "onStateChanged expand ", this.TAG);
        } else if (SheetBehaviorCompat.isClosed(i2)) {
            this.mView.invalidateOptionsMenu();
            this.mBottomSheetBehavior.setDraggable(false);
            this.mMainLayout.setBackgroundColor(0);
            BgmPickerView bgmPickerView = this.mBgmPickerView;
            if (bgmPickerView != null) {
                bgmPickerView.onHidden();
            }
            this.mEventHandler.postEvent(Event.BGM_PICKER_STATE_CHANGED, Boolean.TRUE);
            setImportantAccessibility(this.mBgmPickerIcon, true);
            if (!this.mView.getOptions().supportFilter()) {
                this.mEventHandler.postEvent(Event.HIDE_THEME_OPTION, new Object[0]);
            }
            a.k(i2, "onStateChanged close ", this.TAG);
        }
    }
}
