package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CarouselClusterResult extends ClusterResult {
    public CarouselClusterResult(View view, EventContext eventContext) {
        super(view, eventContext);
    }

    public String getCategoryLocationKey() {
        return getLocationKey();
    }

    public String getClusterItemLocationKey(ListViewHolder listViewHolder, MediaItem mediaItem) {
        String str = (String) mediaItem.getExtra(ExtrasID.CLUSTER_TYPE);
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1671680240:
                if (str.equals("person_cluster")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1623182517:
                if (str.equals("ocrtext")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1480586582:
                if (str.equals("pdc_cluster")) {
                    c5 = 2;
                    break;
                }
                break;
            case -147112977:
                if (str.equals("usertag")) {
                    c5 = 3;
                    break;
                }
                break;
            case 627202090:
                if (str.equals("album_cluster")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1038965053:
                if (str.equals("facet_location")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1955912410:
                if (str.equals("pet_cluster")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return getTargetPeople(mediaItem);
            case 1:
                return getTargetOcr();
            case 2:
                return getTargetPdc(mediaItem);
            case 3:
                return getTargetUserTag(mediaItem);
            case 4:
                return getTargetAlbum(mediaItem);
            case 5:
                return getTargetLocation(mediaItem);
            case 6:
                return getTargetPet(mediaItem);
            default:
                return null;
        }
    }

    public String getLocationKey() {
        return new UriBuilder("location://search/fileList/CarouselCluster").build();
    }

    public ClusterResultType getType() {
        return ClusterResultType.CAROUSELS;
    }

    public int getViewStubId() {
        return R.id.carousel_cluster_view_stub;
    }

    public void initListView() {
        super.initListView();
        ViewMarginUtils.setTopMargin(getListView(), getListView().getContext().getResources().getDimensionPixelOffset(R.dimen.search_cluster_results_recycler_view_margin_top));
    }

    public void onClusterItemClicked(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        boolean z;
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter == null || !this.mIsEnabled) {
            String str = this.TAG;
            if (clusterResultPresenter == null) {
                z = true;
            } else {
                z = false;
            }
            Log.w((CharSequence) str, "onClusterItemClicked failed : ", Boolean.valueOf(z), Boolean.valueOf(this.mIsEnabled));
            return;
        }
        String str2 = (String) mediaItem.getExtra(ExtrasID.CLUSTER_TYPE);
        str2.getClass();
        if (str2.equals("person_cluster")) {
            VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
        } else if (str2.equals("album_cluster")) {
            this.mPresenter.getBlackboard().publish("data://albums/current", mediaItem);
        }
        super.onClusterItemClicked(listViewHolder, mediaItem, i2);
    }
}
