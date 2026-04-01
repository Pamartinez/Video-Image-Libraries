package com.samsung.android.gallery.app.ui.list.search.pictures;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.viewholders.SearchPicturesDateLocationViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesAdapter<V extends ISearchPicturesView> extends PicturesHeaderViewAdapter<V> {
    private ExpansionHelper mExpansionHelper;
    private boolean mHideAll;

    public SearchPicturesAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        setHasStableIds(true);
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            this.mExpansionHelper = new ExpansionHelper();
        }
    }

    private void changeExpandState() {
        ((ISearchPicturesView) this.mView).getBlackboard().postEvent(EventMessage.obtain(1066, refreshLocationKey()));
    }

    private long getUniqueItemIdForDataType(int i2) {
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2);
        if (mediaItemFromCache != null) {
            return mediaItemFromCache.getFileId();
        }
        return (long) (-(i2 + 1));
    }

    private long getUniqueItemIdForDividerType(int i2) {
        String str;
        int i7;
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2);
        if (mediaItemFromCache != null) {
            str = mediaItemFromCache.getDateRaw();
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            i7 = str.hashCode();
        } else {
            i7 = -(i2 + 1);
        }
        return (long) i7;
    }

    /* access modifiers changed from: private */
    public void onExpandClicked(MediaItem mediaItem, int i2, boolean z) {
        if (this.mExpansionHelper != null) {
            this.mExpansionHelper.onExpandClicked(this.mMultiClusterAdapter.getClusterItem(i2), this.mMultiClusterAdapter.getDateIds(i2), this.mMultiClusterAdapter.getDateCount(i2));
            changeExpandState();
        }
    }

    private String refreshLocationKey() {
        String removeArg = ArgumentsUtil.removeArg(ArgumentsUtil.removeArg(BlackboardUtils.readLocationKeyCurrent(((ISearchPicturesView) this.mView).getBlackboard()), "ExpandedDates"), "AddedCount");
        String allExpandedDates = this.mExpansionHelper.getAllExpandedDates();
        if (!TextUtils.isEmpty(allExpandedDates)) {
            return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(removeArg, "ExpandedDates", allExpandedDates), "AddedCount", String.valueOf(this.mExpansionHelper.getAddedCount()));
        }
        return removeArg;
    }

    private boolean supportExpand(int i2) {
        if (!((ISearchPicturesView) this.mView).supportExpand()) {
            return false;
        }
        ExpansionHelper expansionHelper = this.mExpansionHelper;
        if ((expansionHelper == null || !expansionHelper.supportExpand(this.mMultiClusterAdapter.getClusterItem(i2), this.mMultiClusterAdapter.getDateCount(i2))) && !this.mMultiClusterAdapter.supportExpand(i2)) {
            return false;
        }
        return true;
    }

    private void updateDividerExpandState(ListViewHolder listViewHolder, int i2) {
        if (listViewHolder instanceof SearchPicturesDateLocationViewHolder) {
            SearchPicturesDateLocationViewHolder searchPicturesDateLocationViewHolder = (SearchPicturesDateLocationViewHolder) listViewHolder;
            if (supportExpand(i2)) {
                searchPicturesDateLocationViewHolder.enableExpandButton(true, new p(16, this));
                ExpansionHelper expansionHelper = this.mExpansionHelper;
                if (expansionHelper != null) {
                    searchPicturesDateLocationViewHolder.setExpanded(expansionHelper.isExpanded(this.mMultiClusterAdapter.getClusterItem(i2)));
                    return;
                }
                return;
            }
            searchPicturesDateLocationViewHolder.enableExpandButton(false, (SearchPicturesDateLocationViewHolder.OnExpandClickListener) null);
        }
    }

    public boolean checkIfEmpty() {
        if (super.checkIfEmpty() || getDataCount() == 0) {
            return ((ISearchPicturesView) this.mView).allowEmptyView();
        }
        return false;
    }

    public void clearExpansion() {
        ExpansionHelper expansionHelper = this.mExpansionHelper;
        if (expansionHelper != null) {
            expansionHelper.clearExpansionInfo();
        }
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new SearchPicturesViewHolderFactory(context);
    }

    public int getHintColumnSpan(int i2, int i7) {
        if (!isFooter(i2) || supportCustomViewSize(i7)) {
            return super.getHintColumnSpan(i2, i7);
        }
        return i7;
    }

    public int getItemCount() {
        if (this.mHideAll) {
            return 0;
        }
        return super.getItemCount();
    }

    public long getItemId(int i2) {
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            return getUniqueItemIdForDataType(i2);
        }
        if (ViewHolderValue.isDivider(itemViewType)) {
            return getUniqueItemIdForDividerType(i2);
        }
        if (ViewHolderValue.isHeader(itemViewType)) {
            return (long) i2;
        }
        return super.getItemId(i2);
    }

    public String getMapLocationTarget() {
        return new UriBuilder(getLocationKey()).appendArg("searchResultOnMapView", true).build();
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (isFooter(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getNextRowIndex(int i2, int i7) {
        if (isFooter(i2)) {
            return i2 + 1;
        }
        return super.getNextRowIndex(i2, i7);
    }

    public int getPrevRowIndex(int i2) {
        if (isFooter(i2)) {
            return i2 - 1;
        }
        return super.getPrevRowIndex(i2);
    }

    public int getSpanSize(int i2) {
        if (isFooter(i2)) {
            return ((ISearchPicturesView) this.mView).getLayoutManager().getSpanCount();
        }
        return super.getSpanSize(i2);
    }

    public void hideAllItems() {
        this.mHideAll = true;
        notifyDataSetChanged();
    }

    public void onDataChanged() {
        this.mHideAll = false;
        super.onDataChanged();
    }

    public boolean skipEmptyView(boolean z) {
        return ((ISearchPicturesView) this.mView).skipEmptyView(z);
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (isFooter(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2, i7);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateDividerExpandState(listViewHolder, i2);
    }
}
