package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.publisher.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0643x implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListDataPublisher e;

    public /* synthetic */ C0643x(ListDataPublisher listDataPublisher, int i2) {
        this.d = i2;
        this.e = listDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        ListDataPublisher listDataPublisher = this.e;
        switch (i2) {
            case 0:
                listDataPublisher.onContext(obj, bundle);
                return;
            case 1:
                listDataPublisher.onThumbnailLoadDone(obj, bundle);
                return;
            case 2:
                listDataPublisher.publishQuickViewFileData(obj, bundle);
                return;
            case 3:
                listDataPublisher.publishTimelineData(obj, bundle);
                return;
            case 4:
                listDataPublisher.publishAllPictures(obj, bundle);
                return;
            case 5:
                listDataPublisher.publishTimelineFakeData(obj, bundle);
                return;
            case 6:
                listDataPublisher.publishMtpData(obj, bundle);
                return;
            case 7:
                listDataPublisher.publishMtpFileData(obj, bundle);
                return;
            case 8:
                listDataPublisher.publishTrashData(obj, bundle);
                return;
            case 9:
                listDataPublisher.publishSuggestionsData(obj, bundle);
                return;
            case 10:
                listDataPublisher.publishCleanOutPicturesData(obj, bundle);
                return;
            case 11:
                listDataPublisher.publishMotionPhotoClipPicturesData(obj, bundle);
                return;
            case 12:
                listDataPublisher.publishDuplicatedPicturesData(obj, bundle);
                return;
            case 13:
                listDataPublisher.publishCleanOutBurstSimilarPicturesData(obj, bundle);
                return;
            case 14:
                listDataPublisher.publishRevitalizedPicturesData(obj, bundle);
                return;
            case 15:
                listDataPublisher.publishHighlightPicturesData(obj, bundle);
                return;
            case 16:
                listDataPublisher.publishPortraitPicturesData(obj, bundle);
                return;
            case 17:
                listDataPublisher.publishPrivateAlbumData(obj, bundle);
                return;
            default:
                listDataPublisher.publishPrivateTrashData(obj, bundle);
                return;
        }
    }
}
