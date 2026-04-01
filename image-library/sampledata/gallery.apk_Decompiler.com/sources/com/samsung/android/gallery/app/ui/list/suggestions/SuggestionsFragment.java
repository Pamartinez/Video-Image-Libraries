package com.samsung.android.gallery.app.ui.list.suggestions;

import M9.o;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.ISuggestionsView;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionsFragment<V extends ISuggestionsView, P extends SuggestionsPresenter> extends BaseListFragment<V, P> implements ISuggestionsView {
    NoItemView mNoItemView;

    public SuggestionsFragment() {
        setLocationKey("location://suggestions");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$0() {
        setSystemUiBarAndBgColor(true);
    }

    private void setSystemUiBarAndBgColor(boolean z) {
        int i2;
        Context context = getContext();
        if (context != null) {
            if (z) {
                i2 = R.color.default_fw_background;
            } else {
                i2 = R.color.default_background;
            }
            this.mBlackboard.post("command://ChangeActivityBgColor", Integer.valueOf(context.getColor(i2)));
        }
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        if (!MediaItemSuggest.isCleanOutGroup(mediaItem) && !MediaItemSuggest.isIntelligentGroup(mediaItem)) {
            super.addSharedTransition(listViewHolder, mediaItem, i2, z);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mNoItemView = (NoItemView) view.findViewById(R.id.no_item_view);
    }

    public GridHelper createGridHelper(String str) {
        return new GridHelper.Builder(str).setSpans(new int[]{1}).setResources(R.array.suggestion_column_count).build();
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SuggestionsViewAdapter(this, getLocationKey());
    }

    public int getLayoutId() {
        return R.layout.fragment_suggestions;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SUGGEST_VIEW.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (this.mListAdapter != null) {
            ((GridLayoutManager) getLayoutManager()).setSpanCount(this.mListAdapter.getGridSize());
            BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
            baseListViewAdapter.notifyItemRangeChanged(0, baseListViewAdapter.getDataCount(), "onConfigurationChanged");
        }
        ((SuggestionsLayoutManager) getLayoutManager()).initDimen(getContext());
    }

    public void initializeEmptyView() {
        int i2;
        int i7;
        NoItemView noItemView = this.mNoItemView;
        Resources resources = getResources();
        boolean z = PreferenceFeatures.OneUi7x.IS_ONE_UI_70;
        if (z) {
            i2 = R.string.empty_set_description_no_clean_out;
        } else {
            i2 = R.string.empty_set_description_no_suggestion;
        }
        noItemView.setLabel(resources.getString(i2));
        NoItemView noItemView2 = this.mNoItemView;
        Resources resources2 = getResources();
        if (z) {
            i7 = R.string.empty_set_description_quickly_fix_up_and_clean_out_for_suggestions_v;
        } else {
            i7 = R.string.empty_set_description_quickly_fix_up_and_clean_out_for_suggestions;
        }
        noItemView2.setDescription(resources2.getString(i7));
        super.initializeEmptyView();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (isViewActive()) {
            ThreadUtil.postOnUiThread(new o(4, this));
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1106) {
            return super.onHandleEvent(eventMessage);
        }
        setSharedElementEnterTransition((Object) null);
        return true;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        setSystemUiBarAndBgColor(!z);
    }

    public void onStart() {
        super.onStart();
        if (isViewActive()) {
            setSystemUiBarAndBgColor(true);
        }
    }

    public void onStop() {
        super.onStop();
        setSystemUiBarAndBgColor(false);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportSelection() {
        return false;
    }

    public void updateAutoItemStatus(MediaItem mediaItem) {
        ((SuggestionsPresenter) this.mPresenter).updateAutoItemStatus(mediaItem);
    }

    public void updateExtraStartPadding(float f) {
        ((SuggestionsLayoutManager) getLayoutManager()).updateExtraStartPadding(f);
    }

    public LinearLayoutManager createLayoutManager() {
        return new SuggestionsLayoutManager(getListView(), getMaxColumnSize());
    }

    public SuggestionsPresenter createPresenter(ISuggestionsView iSuggestionsView) {
        return new SuggestionsPresenter(this.mBlackboard, iSuggestionsView);
    }
}
