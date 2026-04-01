package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.module.story.TitleAlign;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sum.core.Def;
import com.sec.android.gallery3d.R;
import k6.b;
import n5.f;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GuideDecoViewDelegate extends Delegate {
    private View mGuideDecoContainer;
    private View mGuideDecoGradient;
    private View mGuideDecoRootLayout;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1000) {
                GuideDecoViewDelegate.this.hideGuideDeco();
            }
        }
    };
    private LottieAnimationView mPicturesGuideArrow;
    private View mTitleContainer;

    public GuideDecoViewDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    private int getPixelSize(View view, int i2, boolean z) {
        int i7;
        Resources resources = view.getContext().getResources();
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        float f = typedValue.getFloat();
        if (z) {
            i7 = view.getHeight();
        } else {
            i7 = view.getWidth();
        }
        return (int) (f * ((float) i7));
    }

    private TitleAlign getTitleAlign(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN || !needTitleAlign()) {
            return TitleAlign.BOTTOM_MIDDLE;
        }
        return TitleAlign.getTitleAlign(mediaItem);
    }

    private String getTitleTransitionName(MediaItem mediaItem) {
        if (this.mView.getOptions().withOnDemandVi()) {
            return StorySharedTransitionHelper.getOnDemandViTitleTransitionName(mediaItem);
        }
        return StorySharedTransitionHelper.getTitleTransitionName(mediaItem);
    }

    private int getToolbarHeight() {
        Context context = this.mView.getContext();
        if (this.mView.isLandscape()) {
            return SystemUi.getToolBarHeight(context);
        }
        return SystemUi.getToolBarHeightWithPadding(context);
    }

    /* access modifiers changed from: private */
    public void hideGuideDeco() {
        hideGuideDeco(200);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideGuideDeco$1() {
        ViewUtils.setVisibleOrGone(this.mGuideDecoRootLayout, false);
        ViewUtils.setAlpha(this.mGuideDecoRootLayout, 1.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideGuideDeco$2(int i2) {
        this.mGuideDecoRootLayout.animate().alpha(0.0f).setDuration((long) i2).withEndAction(new b(29, this)).start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        if (!((Boolean) requestData(DataRequest.IS_ACTIVE_ZOOM, Boolean.FALSE)).booleanValue()) {
            this.mEventHandler.lambda$postEvent$0(Event.PICTURES_ICON_CLICKED, new Object[0]);
            postAnalyticsLog(AnalyticsEventId.EVENT_STORY_TAP_AFFORDANCE);
        }
    }

    private boolean needTitleAlign() {
        if (this.mView.isTabletDpi() || this.mView.isLandscape()) {
            return true;
        }
        int[] intArray = this.mView.getResources().getIntArray(R.array.stories_pinch_column_count);
        int loadInt = GalleryPreference.getInstance().loadInt(PreferenceName.STORIES_GRID_SIZE, 1);
        if (loadInt < 0 || loadInt >= intArray.length || intArray[loadInt] != 1) {
            return false;
        }
        return true;
    }

    private void scheduleHideDeco() {
        if (this.mHandler.hasMessages(1000)) {
            this.mHandler.removeMessages(1000);
        }
        this.mHandler.sendEmptyMessageDelayed(1000, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
    }

    private void setTextAndAlign(MediaItem mediaItem) {
        View view;
        if (mediaItem != null && (view = this.mTitleContainer) != null) {
            TextView textView = (TextView) view.findViewById(R.id.title);
            TextView textView2 = (TextView) this.mTitleContainer.findViewById(R.id.duration);
            textView.setText(mediaItem.getTitle());
            textView2.setText(MediaItemStory.getStoryTimeDuration(mediaItem));
            this.mTitleContainer.setTransitionName(getTitleTransitionName(mediaItem));
            TitleAlign titleAlign = getTitleAlign(mediaItem);
            titleAlign.setLayoutAlign(this.mTitleContainer);
            titleAlign.setTextAlign(textView);
            titleAlign.setTextAlign(textView2);
            titleAlign.setGradientDirection(this.mGuideDecoGradient);
            String str = this.TAG;
            Log.d(str, "setTextAndAlign = " + titleAlign);
            if (PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN) {
                textView.setTypeface(FontTypefaceUtils.getTextFont(MediaItemStory.getStorySaType(mediaItem), mediaItem.getAlbumID(), StringCompat.isKorean(mediaItem.getTitle())));
            }
        }
    }

    private void showGuideDeco() {
        if (!ViewUtils.isVisible(this.mPicturesGuideArrow)) {
            ViewUtils.setVisibility(this.mPicturesGuideArrow, 0);
            ViewUtils.setAlpha(this.mPicturesGuideArrow, 0.0f);
            this.mPicturesGuideArrow.c();
            this.mPicturesGuideArrow.animate().alpha(1.0f).setDuration(500).start();
            this.mGuideDecoGradient.animate().alpha(1.0f).setDuration(1000).start();
        }
    }

    private void updateGuideDecoLayout() {
        int i2;
        int pixelSize = getPixelSize(this.mGuideDecoContainer.getRootView(), R.dimen.story_highlight_deco_guide_side_margin, false);
        ViewMarginUtils.setMarginRelative(this.mGuideDecoContainer, Integer.valueOf(pixelSize), (Integer) null, Integer.valueOf(pixelSize), (Integer) null);
        View view = this.mGuideDecoContainer;
        if (this.mView.isLandscape()) {
            i2 = SystemUi.getToolBarHeight(this.mGuideDecoContainer.getContext());
        } else {
            i2 = SystemUi.getToolBarHeightWithPadding(this.mGuideDecoContainer.getContext());
        }
        ViewMarginUtils.setTopMargin(view, i2);
        ViewUtils.getMeasuredHeight(this.mGuideDecoContainer);
    }

    private void updateTitleLayout(View view) {
        if (!this.mView.isDestroyed() && this.mTitleContainer != null) {
            Resources resources = view.getResources();
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.story_highlight_deco_guide_title_margin_top);
            int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.story_highlight_deco_guide_title_margin_bottom);
            int height = ((View) this.mTitleContainer.getParent()).getHeight();
            int height2 = this.mTitleContainer.getHeight();
            int systemInsetsTop = (dimensionPixelOffset - WindowUtils.getSystemInsetsTop(view.getRootWindowInsets())) - getToolbarHeight();
            int i2 = (height - height2) / 2;
            if (i2 > systemInsetsTop) {
                ViewMarginUtils.setMarginRelative(this.mTitleContainer, (Integer) null, Integer.valueOf(systemInsetsTop), (Integer) null, Integer.valueOf(dimensionPixelOffset2));
            } else if (i2 < dimensionPixelOffset2) {
                int max = Math.max(0, i2);
                ViewMarginUtils.setMarginRelative(this.mTitleContainer, (Integer) null, Integer.valueOf(max), (Integer) null, Integer.valueOf(max));
            } else {
                ViewMarginUtils.setMarginRelative(this.mTitleContainer, (Integer) null, (Integer) null, (Integer) null, Integer.valueOf(dimensionPixelOffset2));
            }
        }
    }

    public void addListenEvent() {
        addEvent(Event.HIDE_GUIDE_DECO);
        addEvent(Event.ENTER_TRANSITION_END);
        addEvent(Event.RELATED_VIEW_STATE_CHANGED);
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED);
    }

    public void handleEvent(Event event, Object... objArr) {
        Event event2 = Event.HIDE_GUIDE_DECO;
        if (event == event2) {
            hideGuideDeco();
        } else if (event == Event.ENTER_TRANSITION_END) {
            showGuideDeco();
            scheduleHideDeco();
        } else if (event == Event.RELATED_VIEW_STATE_CHANGED) {
            if (objArr[0].booleanValue() && ViewUtils.isVisible(this.mGuideDecoRootLayout)) {
                if (this.mHandler.hasMessages(1000)) {
                    this.mHandler.removeMessages(1000);
                }
                hideGuideDeco(50);
            }
        } else if (event == Event.BOTTOM_SHEET_STATE_CHANGED && !this.mEventHandler.isBottomSheetHidden()) {
            this.mEventHandler.lambda$postEvent$0(event2, new Object[0]);
        }
    }

    public void initView(View view) {
        this.mGuideDecoRootLayout = view.findViewById(R.id.guide_deco_root_layout);
        this.mGuideDecoContainer = view.findViewById(R.id.guide_deco_container);
        this.mTitleContainer = view.findViewById(R.id.title_container);
        this.mGuideDecoGradient = view.findViewById(R.id.guide_deco_gradient);
        MediaItem headerItem = this.mView.getHeaderItem();
        if (headerItem != null) {
            setTextAndAlign(headerItem);
        } else {
            ViewUtils.setVisibility(this.mTitleContainer, 8);
        }
        updateGuideDecoLayout();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.button_pictures_guide);
        this.mPicturesGuideArrow = lottieAnimationView;
        lottieAnimationView.setOnClickListener(new C0496a(6, this));
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        updateTitleLayout(view);
    }

    public void onHeaderUpdated() {
        ViewUtils.setVisibility(this.mTitleContainer, 0);
        setTextAndAlign(this.mView.getHeaderItem());
        Log.d(this.TAG, "set text on header updated");
    }

    private void hideGuideDeco(int i2) {
        if (ViewUtils.isVisible(this.mGuideDecoRootLayout)) {
            ThreadUtil.postOnUiThread(new f(this, i2, 1));
            Log.d(this.TAG, "hideGuideDeco");
        }
    }
}
