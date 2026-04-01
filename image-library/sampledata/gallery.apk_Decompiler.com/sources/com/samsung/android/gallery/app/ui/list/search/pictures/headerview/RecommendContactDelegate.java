package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import N2.j;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameWrapper;
import com.samsung.android.gallery.app.controller.creature.people.CommitPeopleNContactCmd;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.people.PersonNameDataLoader;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.UUID;
import o6.p;
import z5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecommendContactDelegate {
    private boolean mCanceledContactRecommend;
    private DataLoadListener mDataLoadListener;
    private final EventContext mEventContext;
    private final MediaItem mHeaderItem;
    private CreatureNameData mRecommendCreatureNameData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DataLoadListener {
        void onLoadCompleted(CreatureNameData creatureNameData);
    }

    public RecommendContactDelegate(EventContext eventContext, MediaItem mediaItem) {
        this.mEventContext = eventContext;
        this.mHeaderItem = mediaItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadRecommendContact$0() {
        EditCreatureNameWrapper.requestRuntimePermission(this.mEventContext, 119);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadRecommendContact$1() {
        long[] similarContactData = PeopleDataManager.getSimilarContactData(ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "sub"));
        if (similarContactData == null) {
            this.mDataLoadListener.onLoadCompleted((CreatureNameData) null);
        } else if (EditCreatureNameWrapper.hasRuntimeContactsPermission(this.mEventContext)) {
            PersonNameDataLoader.loadSimilarContactPerson(new p(29, this), similarContactData);
        } else if (PreferenceCache.ConfirmContactPermission.compareAndSet(false, true)) {
            ThreadUtil.postOnUiThread(new c(this, 2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRequestPermissionResult$2() {
        long[] similarContactData = PeopleDataManager.getSimilarContactData(ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "sub"));
        if (similarContactData != null) {
            PersonNameDataLoader.loadSimilarContactPerson(new p(29, this), similarContactData);
        }
    }

    /* access modifiers changed from: private */
    public void loadFinishRecommendContact(ArrayList<CreatureNameData> arrayList) {
        CreatureNameData creatureNameData;
        if (arrayList == null || arrayList.isEmpty()) {
            creatureNameData = null;
        } else {
            creatureNameData = arrayList.get(0);
        }
        this.mRecommendCreatureNameData = creatureNameData;
        this.mDataLoadListener.onLoadCompleted(creatureNameData);
    }

    public CreatureNameData getPersonNameData() {
        return this.mRecommendCreatureNameData;
    }

    public boolean isNeedToCheckRecommendContact() {
        if (!Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT) || this.mCanceledContactRecommend || CreatureData.hasName(this.mHeaderItem) || ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "delete_name_by_manual", false)) {
            return false;
        }
        return true;
    }

    public void loadRecommendContact() {
        Log.s("RecommendContactDelegate", "loadRecommendContact() start");
        SimpleThreadPool.getInstance().execute(new c(this, 1));
    }

    public void onCancel() {
        PeopleDataManager.disableSimilarContact(this.mRecommendCreatureNameData);
        this.mCanceledContactRecommend = true;
    }

    public void onRequestPermissionResult() {
        ThreadUtil.postOnBgThread(new c(this, 0));
    }

    public void onSave(Bitmap bitmap) {
        String str;
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) && this.mRecommendCreatureNameData != null) {
            MediaItem mediaItem = this.mHeaderItem;
            if (mediaItem != null) {
                str = CreatureData.of(mediaItem).creatureUuid;
            } else {
                str = null;
            }
            if (TextUtils.isEmpty(str)) {
                str = UUID.randomUUID().toString();
            }
            this.mRecommendCreatureNameData.setCreatureUuid(str);
        }
        CommitPeopleNContactCmd commitPeopleNContactCmd = new CommitPeopleNContactCmd();
        EventContext eventContext = this.mEventContext;
        commitPeopleNContactCmd.execute(eventContext, eventContext.getLocationKey(), this.mRecommendCreatureNameData, bitmap);
    }

    public void setDataLoadListener(DataLoadListener dataLoadListener) {
        this.mDataLoadListener = dataLoadListener;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("RecommendContactDelegate {");
        CreatureNameData creatureNameData = this.mRecommendCreatureNameData;
        if (creatureNameData != null) {
            str = creatureNameData.toString();
        } else {
            str = "null";
        }
        sb2.append(str);
        sb2.append("|");
        return j.h(sb2, this.mCanceledContactRecommend, "}");
    }
}
