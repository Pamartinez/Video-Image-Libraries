package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class J implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataPublisher e;

    public /* synthetic */ J(SearchDataPublisher searchDataPublisher, int i2) {
        this.d = i2;
        this.e = searchDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SearchDataPublisher searchDataPublisher = this.e;
        switch (i2) {
            case 0:
                searchDataPublisher.onVisualSearchDestroyed(obj, bundle);
                return;
            case 1:
                searchDataPublisher.publishCategoryFiles(obj, bundle);
                return;
            case 2:
                searchDataPublisher.publishMyTagFiles(obj, bundle);
                return;
            case 3:
                searchDataPublisher.publishScreenShotFiles(obj, bundle);
                return;
            case 4:
                searchDataPublisher.publishShotModeFiles(obj, bundle);
                return;
            case 5:
                searchDataPublisher.publishPeopleFiles(obj, bundle);
                return;
            case 6:
                searchDataPublisher.publishPetsFiles(obj, bundle);
                return;
            case 7:
                searchDataPublisher.publishExpressionsFiles(obj, bundle);
                return;
            case 8:
                searchDataPublisher.publishLocationFiles(obj, bundle);
                return;
            case 9:
                searchDataPublisher.publishDocumentFiles(obj, bundle);
                return;
            case 10:
                searchDataPublisher.publishThingsSceneFiles(obj, bundle);
                return;
            case 11:
                searchDataPublisher.publishThingsFiles(obj, bundle);
                return;
            case 12:
                searchDataPublisher.publishCategoriesData(obj, bundle);
                return;
            case 13:
                searchDataPublisher.publishSceneryFiles(obj, bundle);
                return;
            case 14:
                searchDataPublisher.publishKeywordFiles(obj, bundle);
                return;
            case 15:
                searchDataPublisher.publishCategoryData(obj, bundle);
                return;
            case 16:
                searchDataPublisher.publishKeywordStoriesData(obj, bundle);
                return;
            case 17:
                searchDataPublisher.publishSearchAutoComplete(obj, bundle);
                return;
            default:
                searchDataPublisher.publishCreatureSelectMeAll(obj, bundle);
                return;
        }
    }
}
