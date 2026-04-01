package com.samsung.android.gallery.app.controller.creature.abstraction;

import C3.g;
import Fb.h;
import H3.l;
import I5.e;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreatureHideCmd extends BaseCommand {
    private long getUnifiedIdentityId(String str) {
        return IdentityCreatureUtil.getUnifiedIdentityId(str);
    }

    private void hide(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(getUnifiedIdentityId(str)));
        ThreadPool.getInstance().submit(new e(1, this, arrayList), new h(26, this));
    }

    private void hideThisCreatureAndFinishFragment() {
        MediaItem headerItem = getHandler().getHeaderItem();
        if (headerItem != null) {
            if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
                hide(headerItem.getSubCategory());
            } else {
                new AlertDialog.Builder(getContext()).setTitle((CharSequence) getContext().getString(R.string.hide_face_from_searches_q)).setMessage((int) R.string.hide_people_pets_from_search_description).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new g(2, this, headerItem)).create().show();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$hide$1(ArrayList arrayList, ThreadPool.JobContext jobContext) {
        if (!arrayList.isEmpty()) {
            hideCreature(arrayList);
            getBlackboard().postBroadcastEvent(EventMessage.obtain(103, 10, 0, (Object) null));
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$2() {
        getHandler().getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$3(Future future) {
        ThreadUtil.postOnUiThreadDelayed(new l(13, this), 200);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideThisCreatureAndFinishFragment$0(MediaItem mediaItem, DialogInterface dialogInterface, int i2) {
        hide(mediaItem.getSubCategory());
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_CREATURE_HIDE_FACE_FROM_SEARCHES.toString();
    }

    public abstract void hideCreature(ArrayList<Long> arrayList);

    public void onExecute(EventContext eventContext, Object... objArr) {
        hideThisCreatureAndFinishFragment();
    }
}
