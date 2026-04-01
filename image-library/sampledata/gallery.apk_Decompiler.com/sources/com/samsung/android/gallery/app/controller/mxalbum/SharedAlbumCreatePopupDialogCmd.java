package com.samsung.android.gallery.app.controller.mxalbum;

import O3.y;
import Ob.a;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedAlbumCreatePopupDialogCmd extends AbsAlbumCreatePopupDialogCmd {
    private AnalyticsEventId mEventId;
    private boolean mFromAlbumChoice;

    private void checkSupportFamilyAlbumCreation(Consumer<Boolean> consumer) {
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
            SimpleThreadPool.getInstance().execute(new a(2, this, consumer));
        } else {
            consumer.accept(Boolean.FALSE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkSupportFamilyAlbumCreation$0(Consumer consumer) {
        boolean z;
        MediaData mediaData = getHandler().getMediaData();
        if (mediaData == null || !MdeGroupHelper.supportFamilySharedAlbumCreation(getContext(), mediaData.getFullData())) {
            z = false;
        } else {
            z = true;
        }
        consumer.accept(Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public void launchProperDialog(boolean z) {
        if (z) {
            DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/AlbumCreatePopup").appendArg("create_album_types", "2131296909,2131296901").build()).setOnDataCompleteListener(new y(8, this)).start();
            return;
        }
        createSharedAlbum();
    }

    public String getEventId() {
        AnalyticsEventId analyticsEventId = this.mEventId;
        if (analyticsEventId != null) {
            return analyticsEventId.toString();
        }
        return super.getEventId();
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public boolean isFromAlbumChoice() {
        return this.mFromAlbumChoice;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        super.onExecute(eventContext, objArr);
        if (objArr.length > 0) {
            this.mFromAlbumChoice = objArr[0].booleanValue();
        }
        if (objArr.length > 1) {
            this.mEventId = objArr[1];
        }
        checkSupportFamilyAlbumCreation(new K5.a(25, this));
    }
}
