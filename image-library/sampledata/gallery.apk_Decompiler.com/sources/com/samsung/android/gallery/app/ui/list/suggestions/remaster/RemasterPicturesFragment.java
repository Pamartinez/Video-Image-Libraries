package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import A4.C0366a;
import Ab.b;
import M9.o;
import O3.l;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesPresenter;
import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RemasterPicturesFragment<V extends IRemasterView, P extends RemasterPicturesPresenter<IRemasterView>> extends BaseListFragment<V, P> implements IRemasterView {
    private final AtomicBoolean mIsFinishing = new AtomicBoolean(false);

    private SimpleAutoScroller createSimpleAutoScroller(RecyclerView recyclerView, int i2) {
        return new SimpleAutoScroller(getBlackboard(), recyclerView, i2).setAppbar(getAppbarLayout()).setThemeColor(getThemeColor()).withStartAction(new o(20, this)).withFinishAction(new b((Object) this, (Object) recyclerView, i2, 25));
    }

    private RecyclerView getSuggestionRemasterHorizontalListView() {
        int suggestionRemasterGroupingType = SharedTransition.getSuggestionRemasterGroupingType(this.mBlackboard);
        resetSelectedRemasterGroupType();
        StringCompat stringCompat = this.TAG;
        Log.stv(stringCompat, "addLayoutListenerForAutoScroll targetRemasterGroupingType : " + suggestionRemasterGroupingType);
        return ((RemasterPicturesViewAdapter) getAdapter()).getSuggestionRemasterHorizontalListView((long) suggestionRemasterGroupingType);
    }

    private boolean hasSelectedRemasterGroupType() {
        if (SharedTransition.getSuggestionRemasterGroupingType(this.mBlackboard) >= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSimpleAutoScroller$1(RecyclerView recyclerView, int i2) {
        Optional.ofNullable(getToolbar()).ifPresent(new C0366a(0));
        recyclerView.scrollToPosition(i2);
    }

    private boolean needFinish(boolean z) {
        if (!z) {
            String str = (String) this.mBlackboard.read("location://variable/currentv1");
            if (this.mMediaData.getCount() != 0 || this.mIsFinishing.getAndSet(true) || !LocationKey.isRemasterPictures(str)) {
                return false;
            }
            return true;
        } else if (!hasSelectedRemasterGroupType() || this.mMediaData.getCount() != 0 || this.mIsFinishing.getAndSet(true)) {
            return false;
        } else {
            return true;
        }
    }

    private void resetSelectedRemasterGroupType() {
        SharedTransition.setSuggestionRemasterGroupingType(this.mBlackboard, -1);
    }

    private void setArgumentChildMediaDataInfo(int i2) {
        String str;
        if (i2 >= 0) {
            str = ArgumentsUtil.appendArgs(getLocationKey(), "child_media_data_index", String.valueOf(i2));
        } else {
            str = ArgumentsUtil.removeArg(getLocationKey(), "child_media_data_index");
        }
        setLocationKey(str);
        ((RemasterPicturesPresenter) this.mPresenter).setLocationKeyOnly(str);
    }

    private void updateLayout() {
        Optional.ofNullable((RemasterPicturesViewAdapter) getAdapter()).ifPresent(new l(12));
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        SharedTransition.setSuggestionRemasterGroupingType(this.mBlackboard, getSuggestionRemasterGroupType(mediaItem));
        super.addSharedTransition(listViewHolder, mediaItem, i2, z);
    }

    public GridHelper createGridHelper(String str) {
        return new GridHelper.Builder(str).setSpans(new int[]{1}).build();
    }

    public int getLayoutId() {
        return R.layout.fragment_suggestion_pictures_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SUGGEST_REVITALIZED_PICTURES.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public int getSuggestionRemasterGroupType(MediaItem mediaItem) {
        return RemasterSuggestGroup.get(VslMesDetectorCompat.decodeEnhances(MediaItemSuggest.getRevitalizedType(mediaItem), true)).ordinal();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateLayout();
    }

    public void onDataChangedOnUi() {
        if (!isDestroyed()) {
            super.onDataChangedOnUi();
            if (needFinish(false)) {
                finish();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        resetSelectedRemasterGroupType();
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_SUGGEST_REMASTER_PICTURES_VIEW_DETAIL);
        setArgumentChildMediaDataInfo(getSuggestionRemasterGroupType(mediaItem));
        addSharedTransition(listViewHolder, mediaItem, i2, false);
        ((RemasterPicturesPresenter) this.mPresenter).onListItemClick(listViewHolder, i2, mediaItem);
    }

    public void onViewCreated(View view, Bundle bundle) {
        setOptionsMenu(false);
        setArgumentChildMediaDataInfo(-1);
        super.onViewCreated(view, bundle);
        if (needFinish(true)) {
            finish();
        }
    }

    public void startSimpleAutoScroller(int i2) {
        if (getAdapter() == null) {
            Log.e(this.TAG, "adapter is null");
            return;
        }
        RecyclerView suggestionRemasterHorizontalListView = getSuggestionRemasterHorizontalListView();
        if (suggestionRemasterHorizontalListView == null) {
            this.mBlackboard.erase("data://shrink_active");
            Log.e(this.TAG, "listview is null, erase shrink active");
            return;
        }
        createSimpleAutoScroller(suggestionRemasterHorizontalListView, i2).start();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportFastScroll() {
        return false;
    }

    public boolean supportShareSheet() {
        return false;
    }

    public boolean supportShrinkTransition() {
        return true;
    }

    public LinearLayoutManager createLayoutManager() {
        return new GridLayoutManager(getContext(), 1);
    }

    public RemasterPicturesViewAdapter<IRemasterView> createListViewAdapter(GalleryListView galleryListView) {
        return new RemasterPicturesViewAdapter<>(this, getLocationKey());
    }

    public RemasterPicturesPresenter createPresenter(IRemasterView iRemasterView) {
        return new RemasterPicturesPresenter(this.mBlackboard, iRemasterView);
    }
}
