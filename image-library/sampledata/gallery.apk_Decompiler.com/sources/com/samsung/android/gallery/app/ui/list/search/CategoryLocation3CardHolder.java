package com.samsung.android.gallery.app.ui.list.search;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.LocationItemAdapterV3;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryLocation3CardHolder extends CategoryLocation2CardHolder {
    public CategoryLocation3CardHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public void moveToMapView(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.BottomSheetAllMapView)) {
            this.mView.getBlackboard().post("command://MoveURL", new SearchLocationKeyBuilder(mediaItem, this.mView.getBlackboard()).searchToolbarEnabled(false).build());
        } else if (mediaItem != null) {
            JSONObject jSONObject = new JSONObject();
            double latitude = mediaItem.getLatitude();
            double longitude = mediaItem.getLongitude();
            try {
                jSONObject.put("address", "");
                jSONObject.put("latitude", latitude);
                jSONObject.put("longitude", longitude);
                jSONObject.put("entryItem", mediaItem.getFileId());
                jSONObject.put(FileApiContract.Parameter.PATH, mediaItem.getPath());
                this.mView.getBlackboard().publish("data://user/map/InitialLocation", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mView.getBlackboard().post("command://MoveURL", "location://map");
            VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(this.mView.getScreenId(), "Location", (String) null, false);
        }
    }

    public CategoryItemAdapterV2 createItemAdapter(ISearchView iSearchView, String str) {
        LocationItemAdapterV3 locationItemAdapterV3 = new LocationItemAdapterV3(iSearchView, str, this.mListView, this.mPropertyHelper);
        locationItemAdapterV3.setHeaderItemClickListener(new h(this, 1));
        locationItemAdapterV3.updateViewPositionsWithHeaderItem();
        return locationItemAdapterV3;
    }

    public int getContentPaddingTop() {
        int dimensionPixelOffset = getDimensionPixelOffset(R.dimen.search_card_padding_top_61);
        if (this.mPropertyHelper.hasItemMarginTopOnCard()) {
            return dimensionPixelOffset - this.mPropertyHelper.getItemMarginTop(this.itemView.getContext());
        }
        return dimensionPixelOffset;
    }

    public boolean supportInteractiveMap() {
        return false;
    }

    public void loadLatestMap(MediaData mediaData) {
    }
}
