package com.samsung.android.gallery.app.ui.list.albums.virtual;

import A5.a;
import O3.l;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.tipcard.VirtualAlbumTipCardDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VirtualAlbumPicturesFragment<V extends IPicturesView, P extends VirtualAlbumPicturesPresenter<V>> extends PicturesFragment<V, P> implements IPicturesView {
    private View mCreateButton;
    protected NoItemView mEmptyViewText;
    volatile VirtualAlbumType mVirtualAlbumType;

    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment$VirtualAlbumType[] r0 = com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment.VirtualAlbumType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType = r0
                com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment$VirtualAlbumType r1 = com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment.VirtualAlbumType.Favorite     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment$VirtualAlbumType r1 = com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment.VirtualAlbumType.Video     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment$VirtualAlbumType r1 = com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment.VirtualAlbumType.Recent     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment$VirtualAlbumType r1 = com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment.VirtualAlbumType.ViewPictures     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum VirtualAlbumType {
        Recent,
        Favorite,
        Video,
        ViewPictures,
        Unknown
    }

    private View createTipCard() {
        TipCardDelegate tipCardDelegate = this.mTipCardDelegate;
        if (tipCardDelegate != null) {
            return (View) Optional.ofNullable(tipCardDelegate.getAndCheckTipCard(getContext())).map(new a(23, this)).orElse((Object) null);
        }
        return null;
    }

    private void enableHeaderView() {
        Optional.ofNullable(getListView()).ifPresent(new S4.a(this, 0));
    }

    private boolean isMonthForViewing(int i2) {
        int[] spanMonths = this.mGridSpans.spanMonths();
        if (spanMonths.length <= 1 || spanMonths[1] != i2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ View lambda$createTipCard$4(TipCardView tipCardView) {
        return tipCardView.createTipCardView(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$enableHeaderView$0(GalleryListView galleryListView) {
        if (getAdapter() != null) {
            getAdapter().enableHeaderView(!isMonthForViewing(galleryListView.getColumnCount()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshEmptyView$3(NoItemView noItemView) {
        ViewUtils.setVisibleOrGone(noItemView, true);
        setEmptyViewText(noItemView);
    }

    private boolean needUpdateOptionMenu(int i2, int i7) {
        if (isMonthForViewing(i2) || isMonthForViewing(i7)) {
            return true;
        }
        return false;
    }

    private void refreshEmptyView() {
        Optional.ofNullable(this.mCreateButton).ifPresent(new l(26));
        Optional.ofNullable(this.mEmptyViewText).ifPresent(new S4.a(this, 1));
    }

    public void bindView(View view) {
        super.bindView(view);
        Optional.ofNullable(this.mAppBarLayout).ifPresent(new Bb.l(17));
    }

    public TipCardDelegate createTipCardDelegate() {
        return new VirtualAlbumTipCardDelegate(this);
    }

    public VirtualAlbumType getAlbumType() {
        if (this.mVirtualAlbumType == null) {
            String locationKey = getLocationKey();
            if (LocationKey.isRecentlyPictures(locationKey)) {
                this.mVirtualAlbumType = VirtualAlbumType.Recent;
            } else if (LocationKey.isFavoritePictures(locationKey)) {
                this.mVirtualAlbumType = VirtualAlbumType.Favorite;
            } else if (LocationKey.isVideoPictures(locationKey)) {
                this.mVirtualAlbumType = VirtualAlbumType.Video;
            } else if (LocationKey.isAlbumViewPictures(locationKey)) {
                this.mVirtualAlbumType = VirtualAlbumType.ViewPictures;
            } else {
                this.mVirtualAlbumType = VirtualAlbumType.Unknown;
            }
        }
        return this.mVirtualAlbumType;
    }

    public int getLayoutId() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType[getAlbumType().ordinal()];
        if (i2 == 1) {
            return R.layout.fragment_favorite_layout;
        }
        if (i2 != 2) {
            return R.layout.fragment_recent_layout;
        }
        return R.layout.fragment_video_layout;
    }

    public int getPreferenceDefault() {
        return getGridHelper().getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return getGridHelper().getPreferenceName();
    }

    public String getScreenId() {
        String locationKey = getLocationKey();
        if (LocationKey.isVideoPictures(locationKey)) {
            if (isSelectionMode()) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_VIDEO_EDIT.toString();
            }
            return AnalyticsScreenId.SCREEN_SMART_ALBUM_VIDEO.toString();
        } else if (LocationKey.isFavoritePictures(locationKey)) {
            if (isSelectionMode()) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_FAVORITE_EDIT.toString();
            }
            return AnalyticsScreenId.SCREEN_SMART_ALBUM_FAVORITE.toString();
        } else if (!LocationKey.isRecentlyPictures(locationKey)) {
            return null;
        } else {
            if (isSelectionMode()) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_RECENT_EDIT.toString();
            }
            return AnalyticsScreenId.SCREEN_SMART_ALBUM_RECENT.toString();
        }
    }

    public int getStartPinchDepth() {
        return getPinchDepthRecoveredIfAbsent();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null && baseListViewAdapter.getHeaderView() != null) {
            this.mListAdapter.setHeaderView(createTipCard());
        }
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        enableHeaderView();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (LocationKey.isShortcutAlbum(getLocationKey())) {
            this.mBlackboard.publish("shortcut_album_loading", Boolean.TRUE);
        }
    }

    public boolean onBackPressed() {
        P p6 = this.mPresenter;
        if ((p6 == null || !((VirtualAlbumPicturesPresenter) p6).isConsumeBackPress()) && !super.onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onDataRangeInserted(int i2, int i7) {
        P p6;
        super.onDataRangeInserted(i2, i7);
        if (LocationKey.isRecentlyPictures(getLocationKey()) && (p6 = this.mPresenter) != null) {
            ((VirtualAlbumPicturesPresenter) p6).updateSubTitle();
        }
    }

    public void onEmptyViewLayoutChecked() {
        if (ViewUtils.isVisible(this.mEmptyView)) {
            refreshEmptyView();
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        View view2 = this.mEmptyView;
        if (view2 != null) {
            if (view2.getVisibility() == 0) {
                if (this.mCreateButton == null) {
                    View findViewById = this.mEmptyView.findViewById(R.id.create_button_layout);
                    this.mCreateButton = findViewById;
                    ViewUtils.setVisibility(findViewById, 8);
                    ViewUtils.setAccessibilityRoleDescription(this.mCreateButton, R.string.speak_button);
                }
                if (this.mEmptyViewText == null) {
                    this.mEmptyViewText = (NoItemView) this.mEmptyView.findViewById(R.id.empty_view_text);
                }
            }
            refreshEmptyView();
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            if (needUpdateOptionMenu(i2, i7)) {
                invalidateOptionsMenu();
                enableHeaderView();
            }
            super.onGridChanged(i2, i7);
        }
    }

    public void postAnalyticsLog() {
        if (getAlbumType() != VirtualAlbumType.ViewPictures) {
            super.postAnalyticsLog();
        }
    }

    public void setEmptyViewText(NoItemView noItemView) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$virtual$VirtualAlbumPicturesFragment$VirtualAlbumType[getAlbumType().ordinal()];
        if (i2 == 1) {
            noItemView.setLabel((int) R.string.empty_set_description_no_favorites);
            noItemView.setDescription((int) R.string.empty_set_description_favorite_no_item_vi);
        } else if (i2 == 2) {
            noItemView.setLabel((int) R.string.empty_set_description_no_videos);
            noItemView.setDescription((int) R.string.empty_set_description_video_no_item_vi);
        } else if (i2 == 3 || i2 == 4) {
            noItemView.setLabel((int) R.string.empty_set_description_no_recent);
            noItemView.setDescription((int) R.string.empty_set_description_recent_no_item_vi);
        }
    }

    public void startPostponedEnterTransitionV2() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof MvpBaseFragment) {
            ((MvpBaseFragment) parentFragment).startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    public boolean supportEnterDefaultTransition() {
        return PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU;
    }

    public boolean supportTimeline() {
        try {
            return SortByType.isGroupByDate(String.valueOf(((VirtualAlbumPicturesPresenter) this.mPresenter).getCurrentItem().getAlbumID()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateTabMode(boolean z) {
        super.updateTabMode(z);
        if (z) {
            Optional.ofNullable((VirtualAlbumPicturesPresenter) this.mPresenter).ifPresent(new a(1));
        }
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        TipCardDelegate tipCardDelegate = this.mTipCardDelegate;
        if (tipCardDelegate == null || tipCardDelegate.isEmpty()) {
            return super.createListViewAdapter(galleryListView);
        }
        return new VirtualAlbumPicturesViewAdapter(this, getLocationKey(), createTipCard(), isRealRatioSupported());
    }

    public VirtualAlbumPicturesPresenter createPresenter(IPicturesView iPicturesView) {
        return new VirtualAlbumPicturesPresenter(this.mBlackboard, iPicturesView);
    }
}
