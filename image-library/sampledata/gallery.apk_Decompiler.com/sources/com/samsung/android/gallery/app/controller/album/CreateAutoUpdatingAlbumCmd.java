package com.samsung.android.gallery.app.controller.album;

import A4.C0387w;
import Fa.C0571z;
import Fb.h;
import Gb.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateAutoUpdatingAlbumCmd extends BaseCommand {
    private String[] getAutoAlbumTitleArray() {
        return (String[]) Arrays.stream(getHandler().getAllItems()).filter(new C0571z(6)).map(new a(5)).toArray(new C0387w(8));
    }

    private String getTargetKey(EventContext eventContext) {
        return new UriBuilder("data://user/dialog/AutoUpdatingAlbumName").appendArg("screenId", eventContext.getScreenId()).appendArg("autoAlbumTitles", getAutoAlbumTitleArray(), NumericEnum.SEP).build();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getAutoAlbumTitleArray$1(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public void launchCreatureSelect(EventContext eventContext, ArrayList<Object> arrayList) {
        String str;
        if (arrayList != null) {
            Object obj = arrayList.get(0);
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr[0] instanceof String) {
                    if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
                        str = "location://search/fileList/Category/CreatureSelect";
                    } else {
                        str = "location://search/fileList/Category/PeopleSelect";
                    }
                    getBlackboard().post("command://MoveURL", new UriBuilder(str).appendArg("name", (String) objArr[0]).build());
                }
            }
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_ALBUM_CREATE_AUTO_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            getBlackboard().post("command://MoveURL", new UriBuilder("location://search/fileList/Category/CreatureSelect").appendArg("autoAlbumTitles", getAutoAlbumTitleArray(), NumericEnum.SEP).build());
        } else {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(getTargetKey(eventContext)).setOnDataCompleteListener(new h(4, this)).start();
        }
    }
}
