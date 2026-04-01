package com.samsung.android.gallery.app.controller.creature;

import H.a;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.search.engine.PersonalLinkCore;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PersonLinkCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(CreatureNameData creatureNameData) {
        if (PersonalLinkCore.getInstance().isSupported(getContext())) {
            link(creatureNameData);
        }
    }

    private void link(CreatureNameData creatureNameData) {
        String str;
        long contactRawId = creatureNameData.getContactRawId();
        String creatureUuid = creatureNameData.getCreatureUuid();
        String str2 = this.TAG;
        Long valueOf = Long.valueOf(contactRawId);
        if (TextUtils.isEmpty(creatureUuid)) {
            str = null;
        } else {
            str = creatureUuid.substring(0, Math.min(7, creatureUuid.length()));
        }
        Log.d(str2, "ContactLink request Link", valueOf, str);
        if (contactRawId > 0 && !TextUtils.isEmpty(creatureUuid)) {
            PersonalLinkCore.getInstance().link(getContext(), creatureUuid, contactRawId);
            getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_REQUEST_CONTACT_LINK.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        CreatureNameData creatureNameData;
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) && objArr != null && objArr.length != 0 && (creatureNameData = objArr[0]) != null) {
            SimpleThreadPool.getInstance().execute(new a(11, this, creatureNameData));
        }
    }
}
