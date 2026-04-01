package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import A4.C0385u;
import H7.x;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowInsets;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.IStoryHighlightListV2View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.BottomSheetCallback;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o4.a;
import o6.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomSheetDelegate extends Delegate implements BottomSheetCallback {
    private StoryHighlightBehavior<View> mBottomSheetBehavior;
    private ViewGroup mBottomSheetLayout;
    private IStoryHighlightListV2View mHighlightListView;
    private ViewGroup mMainLayout;
    private int mPreviousState;
    private boolean mTransitionEnd;

    public BottomSheetDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public boolean allowTouchIntercept() {
        if ((!SheetBehaviorCompat.isClosed(getState()) || !this.mEventHandler.isShowingRelatedView()) && !this.mEventHandler.isActiveZoom()) {
            return true;
        }
        return false;
    }

    private void bindBottomSheet() {
        if (this.mHighlightListView == null) {
            if (this.mView.getOptions().isOnDemandStory()) {
                Optional.ofNullable(this.mView.getHeaderItem()).ifPresent(new a(2, this));
            }
            IStoryHighlightListV2View createHighlightListView = createHighlightListView();
            this.mHighlightListView = createHighlightListView;
            createHighlightListView.setEventHandler(this.mEventHandler);
            this.mView.getChildFragmentManager().beginTransaction().replace(R.id.story_highlight_bottom_sheet, (Fragment) this.mHighlightListView).commitAllowingStateLoss();
        }
    }

    private IStoryHighlightListV2View createHighlightListView() {
        return new StoryHighlightListV2Fragment(this.mView.getLocationKey(), new com.samsung.android.sdk.scs.ai.language.a(29, this));
    }

    private String getListScreenId() {
        return AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_LIST.toString();
    }

    private int getState() {
        StoryHighlightBehavior<View> storyHighlightBehavior = this.mBottomSheetBehavior;
        if (storyHighlightBehavior != null) {
            return storyHighlightBehavior.getState();
        }
        return 5;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$0(DataRequest dataRequest, Object[] objArr) {
        int i2;
        StoryHighlightBehavior<View> storyHighlightBehavior = this.mBottomSheetBehavior;
        if (storyHighlightBehavior != null) {
            i2 = storyHighlightBehavior.getState();
        } else {
            i2 = 0;
        }
        return Integer.valueOf(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$1(DataRequest dataRequest, Object[] objArr) {
        boolean z;
        if (this.mHighlightListView == null || SheetBehaviorCompat.isClosed(this.mBottomSheetBehavior.getState())) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindBottomSheet$2(MediaItem mediaItem) {
        this.mView.getBlackboard().publish(LocationKey.getHeaderCacheKey("stories", mediaItem.getAlbumID()), mediaItem);
    }

    /* access modifiers changed from: private */
    public void moveToHighlight(int i2) {
        this.mBottomSheetBehavior.setState(5);
        this.mEventHandler.postEvent(Event.MOVE_TO_HIGHLIGHT, Integer.valueOf(i2));
    }

    private void setEnableBottomSheetDragging(boolean z) {
        StoryHighlightBehavior<View> storyHighlightBehavior = this.mBottomSheetBehavior;
        if (storyHighlightBehavior != null && this.mTransitionEnd) {
            storyHighlightBehavior.setDraggable(z);
        }
    }

    private void setFocusability(ViewGroup viewGroup, boolean z) {
        int i2;
        if (z) {
            i2 = 131072;
        } else {
            i2 = 393216;
        }
        viewGroup.setDescendantFocusability(i2);
        if (z) {
            viewGroup.requestFocus();
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

    public void addListenEvent() {
        addEvent(Event.PICTURES_ICON_CLICKED);
        addEvent(Event.CHANGE_STORY);
        addEvent(Event.ENTER_TRANSITION_END);
        addEvent(Event.SHOW_THEME_OPTION);
        addEvent(Event.HIDE_THEME_OPTION);
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.BOTTOM_SHEET_STATE, new j(this, 0));
        addRequestProvider(DataRequest.LIST_VIEW_VISIBLE, new j(this, 1));
    }

    public void handleEvent(Event event, Object... objArr) {
        if (event == Event.PICTURES_ICON_CLICKED) {
            bindBottomSheet();
            if (SheetBehaviorCompat.isHidden(this.mPreviousState)) {
                updateBottomSheetBackground();
            }
            this.mBottomSheetBehavior.setState(3);
        } else if (event != Event.CHANGE_STORY) {
            boolean z = true;
            if (event == Event.ENTER_TRANSITION_END) {
                this.mTransitionEnd = true;
                setEnableBottomSheetDragging(true);
            } else if (event == Event.SHOW_THEME_OPTION || event == Event.HIDE_THEME_OPTION) {
                if (event != Event.HIDE_THEME_OPTION) {
                    z = false;
                }
                setEnableBottomSheetDragging(z);
            }
        } else if (this.mHighlightListView != null) {
            this.mView.getChildFragmentManager().beginTransaction().remove((Fragment) this.mHighlightListView).commitAllowingStateLoss();
            this.mHighlightListView = null;
        }
    }

    public void initView(View view) {
        this.mMainLayout = (ViewGroup) view.findViewById(R.id.main_layout);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.story_highlight_bottom_sheet);
        this.mBottomSheetLayout = viewGroup;
        ViewUtils.setVisibleOrGone(viewGroup, true);
        this.mBottomSheetLayout.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(R.dimen.story_highlight_list_fragment_top_radius);
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + dimensionPixelOffset, (float) dimensionPixelOffset);
            }
        });
        updateBottomSheetBackground();
        this.mBottomSheetLayout.setClipToOutline(true);
        StoryHighlightBehavior<View> from = StoryHighlightBehavior.from(this.mBottomSheetLayout);
        this.mBottomSheetBehavior = from;
        from.addBottomSheetCallback(this);
        this.mBottomSheetBehavior.setTouchInterceptProvider(new C0385u(22, this));
        this.mPreviousState = this.mBottomSheetBehavior.getState();
        this.mBottomSheetBehavior.setDraggable(false);
        this.mBottomSheetBehavior.setExpandedOffsetDelta(this.mMainLayout.getResources().getDimensionPixelOffset(R.dimen.story_highlight_list_expend_offset));
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ViewMarginUtils.setHorizontalRelativeMargin(this.mBottomSheetLayout, WindowUtils.getSystemInsetsStart(windowInsets), WindowUtils.getSystemInsetsEnd(windowInsets));
    }

    public boolean onBackPressed() {
        if (this.mHighlightListView == null || !SheetBehaviorCompat.isInExpanded(getState())) {
            return false;
        }
        if (this.mHighlightListView.isSelectionMode()) {
            this.mHighlightListView.exitSelectionMode(true);
        } else {
            this.mBottomSheetBehavior.setState(5);
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mEventHandler.lambda$postEvent$0(Event.BOTTOM_SHEET_STATE_CHANGED, Integer.valueOf(getState()));
    }

    public void onDestroy() {
        StoryHighlightBehavior<View> storyHighlightBehavior = this.mBottomSheetBehavior;
        if (storyHighlightBehavior != null) {
            storyHighlightBehavior.removeBottomSheetCallback(this);
        }
        IStoryHighlightListV2View iStoryHighlightListV2View = this.mHighlightListView;
        if (iStoryHighlightListV2View != null) {
            iStoryHighlightListV2View.setEventHandler((EventHandler) null);
        }
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        if (this.mHighlightListView == null || !SheetBehaviorCompat.isInExpanded(getState())) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action == 0) {
            return this.mHighlightListView.onKeyDown(i2, keyEvent);
        }
        if (action != 1) {
            return false;
        }
        return this.mHighlightListView.onKeyUp(i2, keyEvent);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        StoryHighlightBehavior<View> storyHighlightBehavior = this.mBottomSheetBehavior;
        if (storyHighlightBehavior != null) {
            storyHighlightBehavior.onMultiWindowModeChanged(z);
        }
    }

    public void onResume() {
        super.onResume();
        this.mEventHandler.lambda$postEvent$0(Event.BOTTOM_SHEET_STATE_CHANGED, Integer.valueOf(getState()));
    }

    public void onSlide(View view, float f) {
        if (f <= 0.6f) {
            this.mMainLayout.setBackgroundColor(Color.argb(f, 0.0f, 0.0f, 0.0f));
        }
        Optional.ofNullable(this.mHighlightListView).ifPresent(new x(f, 4));
    }

    public void onStateChanged(View view, int i2) {
        this.mEventHandler.lambda$postEvent$0(Event.BOTTOM_SHEET_STATE_CHANGED, Integer.valueOf(i2));
        if (SheetBehaviorCompat.isDragging(i2)) {
            bindBottomSheet();
            if (SheetBehaviorCompat.isHidden(this.mPreviousState)) {
                updateBottomSheetBackground();
            }
        }
        if (SheetBehaviorCompat.isHidden(i2)) {
            this.mMainLayout.setBackgroundColor(0);
            IStoryHighlightListV2View iStoryHighlightListV2View = this.mHighlightListView;
            if (iStoryHighlightListV2View != null && iStoryHighlightListV2View.isSelectionMode()) {
                this.mHighlightListView.exitSelectionMode(false);
            }
            setFocusability(this.mMainLayout, true);
            setFocusability(this.mBottomSheetLayout, false);
        }
        if (SheetBehaviorCompat.isExpanded(i2)) {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SCROLL_UP);
            AnalyticsLogger.getInstance().postLog(this.mView.getAnalyticsScreenId(getListScreenId()));
            this.mEventHandler.postEvent(Event.SHOW_NAVIGATION_BAR, new Object[0]);
            IStoryHighlightListV2View iStoryHighlightListV2View2 = this.mHighlightListView;
            if (iStoryHighlightListV2View2 != null && iStoryHighlightListV2View2.isSelectionMode() && this.mHighlightListView.getSelectedItemCount() > 0) {
                this.mHighlightListView.getBlackboard().post("command://ShowBottomBar", (Object) null);
            }
            setFocusability(this.mMainLayout, false);
            setFocusability(this.mBottomSheetLayout, true);
        }
        this.mPreviousState = i2;
    }
}
