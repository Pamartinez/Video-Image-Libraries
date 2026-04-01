package com.samsung.android.gallery.app.ui.list.search.recommendation.floating;

import A4.C0372g;
import A4.O;
import B2.i;
import Bb.l;
import D5.e;
import D5.f;
import D5.g;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.search.recommendation.QuerySuggesterFactory;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.InputBlock;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.constants.ForegroundMode;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.Def;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ForegroundViewController {
    final Activity mActivity;
    private final Blackboard mBlackboard;
    private FloatingRecommendationBlur mBlur;
    private FloatingRecommendationDelegate mDelegate;
    private ViewGroup mForegroundLayout;
    private final ViewGroup mForegroundView;
    private final AtomicBoolean mForegroundViewVisible = new AtomicBoolean(true);
    private float mInitialY;
    private final InputBlock mInputBlock;
    private boolean mIsFinishing;
    private boolean mIsPickMode;
    private final ForegroundMode mMode;
    private MotionEvent mPendingMotionEvent;
    private SearchToolbarDelegate mSearchToolbarDelegate;
    private final IMvpBaseView mView;

    public ForegroundViewController(IMvpBaseView iMvpBaseView, ViewStub viewStub, Bitmap bitmap) {
        ImageView imageView;
        FloatingRecommendationDelegate floatingRecommendationDelegate;
        InputBlock inputBlock = new InputBlock();
        this.mInputBlock = inputBlock;
        this.mView = iMvpBaseView;
        Blackboard blackboard = iMvpBaseView.getBlackboard();
        this.mBlackboard = blackboard;
        this.mActivity = iMvpBaseView.getActivity();
        ViewGroup viewGroup = (ViewGroup) ViewUtils.inflateViewStub(viewStub);
        this.mForegroundView = viewGroup;
        ForegroundMode valueOf = ForegroundMode.valueOf(ArgumentsUtil.getArgValue(iMvpBaseView.getLocationKey(), "searchMode", ForegroundMode.NONE.name()));
        this.mMode = valueOf;
        if (valueOf == ForegroundMode.SEARCH) {
            this.mForegroundLayout = (ViewGroup) viewGroup.findViewById(R.id.floating_recommendation_background_layout);
            if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
                floatingRecommendationDelegate = new FloatingRecommendationDelegateV2(iMvpBaseView);
            } else {
                floatingRecommendationDelegate = new FloatingRecommendationDelegate(iMvpBaseView);
            }
            this.mDelegate = floatingRecommendationDelegate;
            floatingRecommendationDelegate.onCreate();
            FloatingRecommendationBlur floatingRecommendationBlur = new FloatingRecommendationBlur();
            this.mBlur = floatingRecommendationBlur;
            floatingRecommendationBlur.initView(viewGroup, bitmap);
        } else if (valueOf == ForegroundMode.ALBUM && (imageView = (ImageView) viewGroup.findViewById(R.id.floating_recommendation_background_image)) != null) {
            imageView.setImageBitmap(bitmap);
            imageView.setOnTouchListener(new i(6, this));
        }
        viewGroup.setOnTouchListener(new f(0));
        viewGroup.setOnHoverListener(new g(0, this));
        ViewUtils.setVisibleOrGone(viewGroup.findViewById(R.id.search_toolbar_container), false);
        updateViews(viewGroup.getRootWindowInsets());
        if (inputBlock.set("prepareSE", Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS)) {
            PersonalDataCore.getInstance(blackboard).prepareSearchEngine();
        }
        this.mIsPickMode = PickerUtil.isPickerMode(blackboard);
    }

    private void finishFragment() {
        if (!this.mIsFinishing) {
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            this.mIsFinishing = true;
        }
    }

    private int getBottomTabHeight() {
        return this.mView.getBottomTabHeight();
    }

    private boolean isAlbumMode() {
        if (this.mMode == ForegroundMode.ALBUM) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleResolutionChange$3() {
        this.mView.getBlackboard().postEvent(EventMessage.obtain(8520, new Object[]{Boolean.FALSE, ArgumentsUtil.appendArgs(this.mView.getLocationKey(), "initShowIme", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE)}));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(View view, MotionEvent motionEvent) {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViews$2(Rect rect, int i2, FloatingRecommendationDelegate floatingRecommendationDelegate) {
        floatingRecommendationDelegate.updateViews(rect.top - i2, rect.bottom + getBottomTabHeight());
    }

    /* access modifiers changed from: private */
    public boolean onHover(View view, MotionEvent motionEvent) {
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onIntercept(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mInitialY = motionEvent.getY();
        }
        if (!this.mSearchToolbarDelegate.hasFocus() || Math.abs(this.mInitialY - motionEvent.getY()) <= 16.0f) {
            return false;
        }
        this.mSearchToolbarDelegate.clearFocus();
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        finishFragment();
        this.mPendingMotionEvent = MotionEvent.obtain(motionEvent);
        return true;
    }

    private boolean supportToShowExtraInfo() {
        if (this.mMode != ForegroundMode.SEARCH || !PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
            return false;
        }
        return true;
    }

    public String getScreenId() {
        AnalyticsScreenId analyticsScreenId;
        if (this.mMode != ForegroundMode.SEARCH || !isVisible()) {
            return null;
        }
        if (this.mForegroundViewVisible.get()) {
            analyticsScreenId = AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_SUGGESTION_LIST;
        } else {
            analyticsScreenId = AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST;
        }
        return analyticsScreenId.toString();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8021) {
            Boolean bool = (Boolean) ((Object[]) eventMessage.obj)[0];
            boolean booleanValue = bool.booleanValue();
            if (booleanValue) {
                if (!this.mSearchToolbarDelegate.hasFocus()) {
                    this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(27));
                }
                this.mForegroundViewVisible.set(true);
            } else if (this.mForegroundViewVisible.getAndSet(false)) {
                reloadSuggestionData();
            }
            setVisibleOrGone(booleanValue);
            Optional.ofNullable(this.mDelegate).ifPresent(new e(0, eventMessage));
            if (!this.mIsPickMode) {
                this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(10, bool));
            }
        } else if (i2 == 8515) {
            requestSuggestionData(true, false);
        } else if (i2 == 8525) {
            requestSuggestionData(false, false);
        }
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mDelegate;
        if (floatingRecommendationDelegate == null || !floatingRecommendationDelegate.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public void handleResolutionChange() {
        if (this.mMode != ForegroundMode.ALBUM || !isVisible()) {
            Optional.ofNullable(this.mDelegate).ifPresent(new l(12));
            return;
        }
        finishFragment();
        ThreadUtil.postOnUiThreadDelayed(new C0372g(28, this), 500);
    }

    public void initFloatingToolbarLayout() {
        Optional.ofNullable(this.mDelegate).ifPresent(new l(11));
    }

    public boolean isSearchForegroundMode() {
        if (this.mMode == ForegroundMode.SEARCH) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        return ViewUtils.isVisible(this.mForegroundView);
    }

    public void onDestroy(FragmentActivity fragmentActivity) {
        MotionEvent motionEvent;
        Optional.ofNullable(this.mDelegate).ifPresent(new l(14));
        Optional.ofNullable(this.mBlur).ifPresent(new l(15));
        if (fragmentActivity != null && (motionEvent = this.mPendingMotionEvent) != null) {
            fragmentActivity.dispatchTouchEvent(motionEvent);
            this.mPendingMotionEvent.recycle();
        }
    }

    public void onPause() {
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.onPause();
        }
    }

    public void onViewCreated(SearchToolbarDelegate searchToolbarDelegate) {
        this.mSearchToolbarDelegate = searchToolbarDelegate;
        searchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(25, this.mMode));
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.onAttached(this.mForegroundLayout);
            this.mDelegate.setToolbarDimAreaHeight(this.mSearchToolbarDelegate.getDimAreaHeight());
            this.mDelegate.setOnInterceptTouchListener(new O(19, this));
            requestSuggestionData(isAlbumMode(), supportToShowExtraInfo());
            if (!this.mIsPickMode) {
                this.mSearchToolbarDelegate.initMoreOptions(this.mDelegate);
            }
        }
    }

    public void onViewResume() {
        if (isVisible()) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(27));
        }
    }

    public void reloadSuggestionData() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlwaysGetNewSuggestions)) {
            QuerySuggesterFactory.create().clear();
        }
        requestSuggestionData(isAlbumMode(), false);
    }

    public void requestSuggestionData(boolean z, boolean z3) {
        this.mBlackboard.publish(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation"), new Object[]{Boolean.valueOf(z), Boolean.valueOf(z3)});
    }

    public void setVisibleOrGone(boolean z) {
        ViewUtils.setVisibleOrGone(this.mForegroundView, z);
    }

    public void updateViews(WindowInsets windowInsets) {
        Rect systemInsets = WindowUtils.getSystemInsets(windowInsets);
        ViewMarginUtils.setHorizontalMargin(this.mForegroundView, -systemInsets.left, -systemInsets.right);
        ViewMarginUtils.setHorizontalPadding(this.mForegroundLayout, systemInsets.left, systemInsets.right);
        int i2 = WindowUtils.getCaptionBarInsets(windowInsets).top;
        ViewMarginUtils.setTopMargin(this.mForegroundView, -i2);
        ViewMarginUtils.setTopPadding(this.mForegroundLayout, i2);
        Optional.ofNullable(this.mDelegate).ifPresent(new C4.i((Object) this, (Object) systemInsets, i2, 1));
        Optional.ofNullable(this.mBlur).ifPresent(new l(13));
    }
}
