package com.samsung.android.gallery.app.ui.list.search.recommendation.floating;

import A4.A;
import A4.C0369d;
import Ab.a;
import Bb.l;
import D3.i;
import D5.b;
import D5.c;
import D5.d;
import S1.e;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemDelegate;
import com.samsung.android.gallery.app.ui.menu.list.SearchMenuHandler;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationDelegateV2 extends FloatingRecommendationDelegate {
    private GalleryAppBarLayout mAppBarLayout;
    private int mAppbarOffset;
    private final e mAppbarOffsetChange = new d(0, this);
    private FloatingToolbarLayout mFloatingToolbarLayout;
    private final HashMap<String, FloatingItemDelegate> mItemDelegates = new HashMap<>();
    private NestedScrollView mNestedScrollView;
    private final SearchMenuHandler mSearchMenuHandler = new SearchMenuHandler();
    private GalleryToolbar mToolbar;
    private final IMvpBaseView mView;
    private final CategoryItemViewHolderFactory mViewHolderFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        SHORTCUT,
        ACTIVITY,
        SHOT_TYPES,
        MY_TAG
    }

    public FloatingRecommendationDelegateV2(IMvpBaseView iMvpBaseView) {
        super(iMvpBaseView);
        this.mView = iMvpBaseView;
        this.mViewHolderFactory = new CategoryItemViewHolderFactory(iMvpBaseView.getContext());
        initItemDelegates();
    }

    private void adjustToolbarLayout(int i2, int i7) {
        if (!this.mView.supportActivityToolbar() && !isFromQuickSearchShortcut()) {
            adjustAppbarHeightOffset(i2);
            ViewMarginUtils.setTopPadding(this.mFloatingToolbarLayout, i2);
            if (this.mAppBarLayout != null && this.mToolbar != null && this.mContentLayout != null) {
                if (this.mNestedScrollView.computeVerticalScrollRange() > (this.mContentLayout.getHeight() - i2) - i7) {
                    this.mAppBarLayout.seslSetAllowStateToHide(true, true);
                } else {
                    this.mAppBarLayout.seslSetAllowStateToHide(false, false);
                }
            }
        }
    }

    private void adjustTopMargin(int i2) {
        if (isFromQuickSearchShortcut()) {
            ViewMarginUtils.setTopPadding(this.mNestedScrollView, i2);
        }
    }

    private boolean enableSearchToolbar(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !"Activity".equals(mediaItem.getCategory())) {
            return true;
        }
        return false;
    }

    private int getAppbarOffset() {
        float f;
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            f = galleryAppBarLayout.seslGetCollapsedHeight();
        } else {
            f = 0.0f;
        }
        return (((int) f) / 2) + this.mAppbarOffset;
    }

    private void initItemDelegates() {
        this.mItemDelegates.put(Type.SHORTCUT.name(), new FloatingItemDelegate(R.id.floating_shortcut_layout, CategoryPropertyHelper.getInstance("location://search/fileList/Category/MyQuery", false), true));
        this.mItemDelegates.put(Type.ACTIVITY.name(), new FloatingItemDelegate(R.id.floating_activity_layout, CategoryPropertyHelper.getInstance("location://search/fileList/Category/Activity", false), false));
        this.mItemDelegates.put(Type.SHOT_TYPES.name(), new FloatingItemDelegate(R.id.floating_shot_types_layout, CategoryPropertyHelper.getInstance("location://search/fileList/Category/ShotMode", false), false));
        this.mItemDelegates.put(Type.MY_TAG.name(), new FloatingItemDelegate(R.id.floating_my_tag_layout, CategoryPropertyHelper.getInstance("location://search/fileList/Category/MyTag", false), true));
    }

    private void initToolbar(View view) {
        GalleryAppBarLayout galleryAppBarLayout = (GalleryAppBarLayout) view.findViewById(R.id.appbar);
        this.mAppBarLayout = galleryAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.disableAppbarScroll();
            this.mAppBarLayout.addOnOffsetChangedListener(this.mAppbarOffsetChange);
        }
        this.mFloatingToolbarLayout = (FloatingToolbarLayout) view.findViewById(R.id.sesl_floating_toolbar_layout);
        if (this.mView.supportActivityToolbar() || isFromQuickSearchShortcut()) {
            ViewUtils.setVisibleOrGone(this.mFloatingToolbarLayout, false);
            ViewUtils.setVisibleOrGone(this.mAppBarLayout, false);
            return;
        }
        GalleryToolbar galleryToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mToolbar = galleryToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_with_inset);
            this.mToolbar.setNavigationContentDescription(R.string.navigate_up);
            this.mToolbar.setNavigationOnClickListener(new a(15, this));
        }
    }

    private boolean isFromQuickSearchShortcut() {
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isQuickSearchShortcut()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$adjustAppbarHeightOffset$1(int i2, GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.seslSetCollapsedHeight(galleryAppBarLayout.seslGetDefaultCollapsedHeight() + ((float) i2));
        galleryAppBarLayout.seslSetProportionExtraHeight(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareOptionsMenu$4(MenuItem menuItem) {
        menuItem.setVisible(supportEditMyQuery());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startShowAnimation$3(FloatingItemDelegate floatingItemDelegate) {
        floatingItemDelegate.startAnimation(this.mDefaultFadeInAnim);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateData$2(ArrayList arrayList, FloatingItemDelegate floatingItemDelegate) {
        floatingItemDelegate.setData(this, arrayList);
    }

    /* access modifiers changed from: private */
    public void onAppbarOffsetChanged(AppBarLayout appBarLayout, int i2) {
        this.mAppbarOffset = i2;
    }

    /* access modifiers changed from: private */
    public void onNavigationClicked(View view) {
        this.mBlackboard.postEvent(EventMessage.obtain(8530));
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    private boolean showCluster(String str) {
        return false;
    }

    private boolean supportEditMyQuery() {
        return ((Boolean) Optional.ofNullable(this.mItemDelegates.get(Type.SHORTCUT.name())).map(new i(1)).orElse(Boolean.FALSE)).booleanValue();
    }

    private void updateData(Type type, ArrayList<MediaItem> arrayList) {
        Optional.ofNullable(this.mItemDelegates.get(type.name())).ifPresent(new A(10, (Object) this, (Object) arrayList));
    }

    public void adjustAppbarHeightOffset(int i2) {
        Optional.ofNullable(this.mAppBarLayout).ifPresent(new C0369d(i2, 4));
    }

    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.menu_search, menu);
    }

    public int getLayoutResId() {
        return R.layout.floating_recommendation_layout_v3;
    }

    public CategoryItemViewHolderFactory getViewHolderFactory() {
        return this.mViewHolderFactory;
    }

    public void handleConfigurationChanged() {
        super.handleConfigurationChanged();
        this.mItemDelegates.values().forEach(new l(9));
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 8529) {
            return super.handleEvent(eventMessage);
        }
        onIMEInsetChanged((WindowInsets) eventMessage.obj);
        return true;
    }

    public void handleRecommendationData(String str, Object obj) {
        super.handleRecommendationData(str, obj);
        if ("data://user/search/RecommendationData/shortcut".equals(str)) {
            updateData(Type.SHORTCUT, (ArrayList) obj);
        } else if ("data://user/search/RecommendationData/activity".equals(str)) {
            updateData(Type.ACTIVITY, (ArrayList) obj);
        } else if ("data://user/search/RecommendationData/shotTypes".equals(str)) {
            updateData(Type.SHOT_TYPES, (ArrayList) obj);
        } else if ("data://user/search/RecommendationData/myTag".equals(str)) {
            updateData(Type.MY_TAG, (ArrayList) obj);
        }
    }

    public View inflateLayout(ViewGroup viewGroup) {
        View inflateLayout = super.inflateLayout(viewGroup);
        NestedScrollView nestedScrollView = (NestedScrollView) inflateLayout.findViewById(R.id.floating_recommendation_view);
        this.mNestedScrollView = nestedScrollView;
        nestedScrollView.setVerticalScrollBarEnabled(false);
        initToolbar(inflateLayout);
        this.mItemDelegates.values().forEach(new c(inflateLayout, 0));
        return inflateLayout;
    }

    public void initFloatingToolbarLayout() {
        NestedScrollView nestedScrollView;
        FloatingToolbarLayout floatingToolbarLayout = this.mFloatingToolbarLayout;
        if (floatingToolbarLayout != null && (nestedScrollView = this.mNestedScrollView) != null) {
            floatingToolbarLayout.setNestedScrollView(nestedScrollView);
        }
    }

    public void onCategoryItemClicked(MediaItem mediaItem) {
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_SUGGESTION_LIST.toString(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
        this.mBlackboard.post("command://MoveURL", new SearchLocationKeyBuilder(mediaItem, this.mBlackboard).showCluster(showCluster(mediaItem.getCategory())).searchToolbarEnabled(enableSearchToolbar(mediaItem)).build());
    }

    public void onDestroy() {
        super.onDestroy();
        this.mItemDelegates.clear();
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.removeOnOffsetChangedListener(this.mAppbarOffsetChange);
        }
    }

    public void onIMEInsetChanged(WindowInsets windowInsets) {
        int i2;
        int iMEInsetsBottom = WindowUtils.getIMEInsetsBottom(windowInsets);
        NestedScrollView nestedScrollView = this.mNestedScrollView;
        if (iMEInsetsBottom > 0) {
            i2 = iMEInsetsBottom + WindowUtils.getSystemInsets(windowInsets).bottom + getAppbarOffset();
        } else {
            i2 = 0;
        }
        ViewMarginUtils.setBottomMargin(nestedScrollView, i2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.mSearchMenuHandler.onOptionsItemSelected(this.mView.getEventContext(), menuItem);
    }

    public void onPause() {
        super.onPause();
        this.mItemDelegates.values().forEach(new l(10));
    }

    public void prepareOptionsMenu(Menu menu) {
        Optional.ofNullable(menu.findItem(R.id.action_edit_quick_search)).ifPresent(new b(this, 1));
    }

    public void show(ViewGroup viewGroup, boolean z) {
        super.show(viewGroup, z);
        this.mItemDelegates.values().forEach(new l(8));
    }

    public boolean showRecommendationOnNewFragment() {
        return false;
    }

    public void startShowAnimation() {
        super.startShowAnimation();
        this.mItemDelegates.values().forEach(new b(this, 0));
    }

    public void updateViews(int i2, int i7) {
        super.updateViews(i2, i7);
        adjustToolbarLayout(i2, i7);
        adjustTopMargin(i2);
    }
}
