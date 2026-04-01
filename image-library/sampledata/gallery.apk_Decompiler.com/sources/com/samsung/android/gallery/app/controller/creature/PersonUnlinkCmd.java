package com.samsung.android.gallery.app.controller.creature;

import A9.b;
import I3.i;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.engine.PersonalLinkCore;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PersonUnlinkCmd extends BaseCommand {
    private Long mLoggingValue;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onExecute$0(ArrayList arrayList, ArrayList arrayList2, MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isPeople() && !TextUtils.isEmpty(mediaItem.getSubCategory()) && !TextUtils.isEmpty(CreatureData.of(mediaItem).creatureUuid)) {
            arrayList.add(Long.valueOf(IdentityCreatureUtil.getIdentityId(mediaItem.getSubCategory())));
            arrayList2.add(CreatureData.of(mediaItem).creatureUuid);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(ArrayList arrayList, ArrayList arrayList2) {
        if (PersonalLinkCore.getInstance().isSupported(getContext())) {
            unLink(arrayList, arrayList2);
        }
    }

    private void unLink(ArrayList<Long> arrayList, ArrayList<String> arrayList2) {
        Log.d(this.TAG, "ContactLink request unLink", arrayList, arrayList2);
        PersonalLinkCore.getInstance().unLink(getContext(), arrayList2);
        getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
    }

    public Long getAnalyticsValue() {
        Long l = this.mLoggingValue;
        if (l != null) {
            return l;
        }
        return super.getAnalyticsValue();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_REQUEST_CONTACT_UNLINK.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) && objArr != null && objArr.length != 0 && (mediaItemArr = objArr[0]) != null) {
            MediaItem[] mediaItemArr2 = mediaItemArr;
            if (objArr.length > 1) {
                this.mLoggingValue = objArr[1];
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Arrays.stream(mediaItemArr2).forEach(new i(arrayList, arrayList2, 0));
            if (!arrayList.isEmpty() && !arrayList2.isEmpty()) {
                SimpleThreadPool.getInstance().execute(new b(this, arrayList, arrayList2, 26));
            }
        }
    }
}
