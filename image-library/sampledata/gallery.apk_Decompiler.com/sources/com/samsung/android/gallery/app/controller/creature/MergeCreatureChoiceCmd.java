package com.samsung.android.gallery.app.controller.creature;

import Fb.h;
import I3.c;
import I3.d;
import I3.e;
import I3.f;
import N2.j;
import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureChoiceCmd extends BaseCommand {
    private MediaItem mHeaderItem;
    private boolean mIsPeopleCreature;

    private void executeMerge(ArrayList<Object> arrayList) {
        if (arrayList != null) {
            Object[] objArr = (Object[]) arrayList.get(0);
            if (objArr.length <= 1 || !((Boolean) objArr[1]).booleanValue()) {
                Object[] objArr2 = (Object[]) objArr[0];
                MediaItem mediaItem = (MediaItem) objArr2[0];
                String subCategory = mediaItem.getSubCategory();
                String str = CreatureData.of(mediaItem).creatureName;
                String str2 = CreatureData.of(mediaItem).relationshipType;
                String str3 = (String) objArr2[1];
                String str4 = str3;
                ThreadPool.getInstance().submit(new c(this, subCategory, str3, mediaItem, 0), new d(this, subCategory, (String) objArr2[2], str, str2, str4));
                return;
            }
            new EditCreatureNameCmd().execute(getHandler(), getHandler().getLocationKey(), this.mHeaderItem);
            getBlackboard().post("command://DismissDialog", (Object) null);
        }
    }

    private List<Long> getFaceGroupIds(String str) {
        if (!PocFeatures.isEnabled(PocFeatures.UndoPeopleMerge)) {
            return new ArrayList();
        }
        if (this.mIsPeopleCreature) {
            return PeopleDataManager.getGroupIds(str);
        }
        return PetDataManager.getGroupIds(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$executeMerge$1(String str, String str2, MediaItem mediaItem, ThreadPool.JobContext jobContext) {
        List<Long> faceGroupIds = getFaceGroupIds(str);
        if (this.mIsPeopleCreature) {
            PeopleDataManager.merge(new String[]{str}, str2, MyQueryUtil.getInterface());
            new PersonUnlinkCmd().execute(getHandler(), new MediaItem[]{mediaItem});
        } else {
            PetDataManager.merge(new String[]{str}, str2, MyQueryUtil.getInterface());
        }
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            AutoAlbumHelper.getInstance().updateMergedRecommendedId(IdentityCreatureUtil.getUnifiedIdentityId(str2), new String[]{str}, CreatureData.of(this.mHeaderItem), this.mIsPeopleCreature);
            DbCompat.storyApi().updateMergedCreaturesToHideRule(str2, str2, new String[]{str}, CreatureData.of(this.mHeaderItem).faceGroupUuid);
            new AddContentsToMergedCreatureCmd().execute(getHandler(), str2, new String[]{str}, Boolean.valueOf(this.mIsPeopleCreature));
        }
        updateHistory(str);
        return faceGroupIds;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMerge$2(String str, String str2, String str3, String str4, String str5, Future future) {
        MergeCreatureChoiceCmd mergeCreatureChoiceCmd;
        if (PocFeatures.isEnabled(PocFeatures.UndoPeopleMerge)) {
            mergeCreatureChoiceCmd = this;
            mergeCreatureChoiceCmd.showUndoMergeSnackBar(str, str2, str3, str4, (List) future.get());
        } else {
            mergeCreatureChoiceCmd = this;
        }
        mergeCreatureChoiceCmd.getBlackboard().post("command://DismissDialog", str5);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(EventContext eventContext, ArrayList arrayList) {
        executeMerge(arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showUndoMergeSnackBar$3(String str, List list, String str2, String str3, String str4, View view) {
        unmerge(str, list, str2, str3, str4);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unmerge$4(boolean z) {
        String str;
        Context context = getContext();
        if (z) {
            str = "Undo completed";
        } else {
            str = "Undo failed";
        }
        Utils.showToast(context, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unmerge$5(String str, List list, String str2, String str3, String str4) {
        boolean unmerge = PeopleDataManager.unmerge(str, list, str2, str3, true, MyQueryUtil.getInterface());
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3001, 1, ArgumentsUtil.removeArgs(str4).hashCode(), str4));
        BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
        ThreadUtil.postOnUiThreadDelayed(new B4.c((Object) this, unmerge, 11), 300);
    }

    private void publishSourceItem(Object... objArr) {
        getBlackboard().publish("data:///MergeCreatureSource", objArr[0]);
    }

    private void showUndoMergeSnackBar(String str, String str2, String str3, String str4, List<Long> list) {
        if (!list.isEmpty()) {
            r j2 = r.j(getActivity().findViewById(R.id.content), 0, 0, false, "Do you want to undo merge?");
            List<Long> list2 = list;
            j2.k("Undo", new e(this, str, list2, str3, str4, str2));
            j2.l();
        }
    }

    private void unmerge(String str, List<Long> list, String str2, String str3, String str4) {
        SimpleThreadPool.getInstance().execute(new f(this, str, (List) list, str2, str3, str4));
    }

    private void updateHistory(String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder();
        if (this.mIsPeopleCreature) {
            str2 = "recommended_id";
        } else {
            str2 = "pet_recommended_id";
        }
        SearchHistory.getInstance().deleteHistoryWithPrimaryKey(j.f(sb2, str2, "/", str));
        getBlackboard().postEvent(EventMessage.obtain(8514));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mHeaderItem = mediaItem;
        if (mediaItem == null) {
            Log.e(this.TAG, "item is null. stop MergeCreatureChoiceCmd");
            return;
        }
        this.mIsPeopleCreature = mediaItem.isPeople();
        publishSourceItem(objArr);
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/MergePeopleChoice").build()).setOnDataCompleteListener(new h(17, this)).start();
    }
}
