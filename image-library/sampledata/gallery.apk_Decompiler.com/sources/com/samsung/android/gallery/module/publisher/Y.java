package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Y implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataPublisherV2 e;

    public /* synthetic */ Y(SearchDataPublisherV2 searchDataPublisherV2, int i2) {
        this.d = i2;
        this.e = searchDataPublisherV2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SearchDataPublisherV2 searchDataPublisherV2 = this.e;
        switch (i2) {
            case 0:
                searchDataPublisherV2.publishKeywordAlbumsData(obj, bundle);
                return;
            case 1:
                searchDataPublisherV2.publishLocationClusterCategoryData(obj, bundle);
                return;
            case 2:
                searchDataPublisherV2.publishLocationFiles(obj, bundle);
                return;
            case 3:
                searchDataPublisherV2.publishKeywordOCRsData(obj, bundle);
                return;
            case 4:
                searchDataPublisherV2.publishKeywordFiles(obj, bundle);
                return;
            case 5:
                searchDataPublisherV2.publishPeopleFiles(obj, bundle);
                return;
            case 6:
                searchDataPublisherV2.publishTopResultsData(obj, bundle);
                return;
            case 7:
                searchDataPublisherV2.publishRelationshipPreviewData(obj, bundle);
                return;
            case 8:
                searchDataPublisherV2.publishScreenShotFiles(obj, bundle);
                return;
            case 9:
                searchDataPublisherV2.publishCarouselClusterData(obj, bundle);
                return;
            case 10:
                searchDataPublisherV2.publishPeopleSelectForRelation(obj, bundle);
                return;
            case 11:
                searchDataPublisherV2.publishKeywordPdcFiles(obj, bundle);
                return;
            case 12:
                searchDataPublisherV2.publishCategoryFiles(obj, bundle);
                return;
            case 13:
                searchDataPublisherV2.publishAlbumClusterCategoryData(obj, bundle);
                return;
            case 14:
                searchDataPublisherV2.publishKeywordPeoplesData(obj, bundle);
                return;
            case 15:
                searchDataPublisherV2.publishPeopleClusterCategoryData(obj, bundle);
                return;
            default:
                searchDataPublisherV2.publishKeywordLocationsData(obj, bundle);
                return;
        }
    }
}
