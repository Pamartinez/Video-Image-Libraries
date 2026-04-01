package com.samsung.android.gallery.app.controller.creature;

import A4.C0387w;
import B8.j;
import C3.C0391a;
import C3.C0392b;
import Fa.C0571z;
import Gb.a;
import I3.g;
import I3.h;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureMultipleCmd extends BaseCommand {
    private final SubscriberListener mAssignedIdentityInfoListener = new C0391a(8, this);
    private boolean mCreateTop5;
    private boolean mIsAllItemNoName;
    private boolean mIsPeople;
    private MediaItem mSourceItem;
    private MediaItem[] mTargetItems;

    private MediaItem[] addTargetItem(MediaItem[] mediaItemArr, MediaItem mediaItem) {
        if (mediaItem == null) {
            return mediaItemArr;
        }
        MediaItem[] mediaItemArr2 = (MediaItem[]) Arrays.stream(mediaItemArr).filter(new j(mediaItem, 1)).toArray(new C0387w(14));
        int length = mediaItemArr2.length;
        MediaItem[] mediaItemArr3 = (MediaItem[]) Arrays.copyOf(mediaItemArr2, length + 1);
        mediaItemArr3[length] = mediaItem;
        return mediaItemArr3;
    }

    private void checkMeItemMergeAsOtherPeople(String[] strArr) {
        if (!this.mCreateTop5) {
            ArrayList<String> top5List = MyQueryUtil.getTop5List();
            if (!top5List.isEmpty()) {
                String str = top5List.get(0);
                if (!TextUtils.isEmpty(str) && Arrays.stream(strArr).anyMatch(new C0392b(str, 1))) {
                    MyQueryUtil.deleteTop5();
                }
            }
        }
    }

    private void executeEditCreatureNameCmd() {
        EventContext handler = getHandler();
        handler.getBlackboard().postEvent(EventMessage.obtain(1003));
        getBlackboard().subscribeOnUi("data://user/faceCluster/assignedIdentityInfo", this.mAssignedIdentityInfoListener);
        getBlackboard().publish("data://user/faceCluster/isFromFaceMerge", Boolean.TRUE);
        new EditCreatureNameCmd().execute(handler, ArgumentsUtil.appendArgs(handler.getLocationKey(), "sub", this.mSourceItem.getSubCategory()), this.mSourceItem);
    }

    private void executeMerge(Object[] objArr, String str) {
        MediaItem mediaItem = objArr[0];
        String subCategory = mediaItem.getSubCategory();
        long unifiedId = getUnifiedId(subCategory);
        MediaItem[] meredItem = getMeredItem(mediaItem, objArr[1]);
        ThreadPool.getInstance().submit(new g(this, getMergedInfo(meredItem), subCategory, meredItem, mediaItem, str), new h(this, unifiedId));
    }

    private MediaItem[] getMeredItem(MediaItem mediaItem, MediaItem[] mediaItemArr) {
        return (MediaItem[]) Arrays.stream(mediaItemArr).filter(new j(mediaItem, 2)).toArray(new C0387w(11));
    }

    private String[] getMergedInfo(MediaItem[] mediaItemArr) {
        return (String[]) Arrays.stream(mediaItemArr).map(new a(10)).toArray(new C0387w(13));
    }

    private String[] getMultiplePrimaryKey(String[] strArr) {
        String str;
        if (this.mIsPeople) {
            str = "recommended_id";
        } else {
            str = "pet_recommended_id";
        }
        return (String[]) Arrays.stream(strArr).map(new C9.a(str.concat("/"), 1)).toArray(new C0387w(12));
    }

    private MediaItem getSourceItem() {
        String locationKey = getHandler().getLocationKey();
        MediaItem mediaItem = getHandler().getSelectedItems()[0];
        if (!LocationKey.isSelectMe(locationKey) && !LocationKey.isSelectMeAll(locationKey)) {
            return mediaItem;
        }
        MediaItem cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(mediaItem);
        CreatureData.of(cloneCreatureItem).relationshipType = "me";
        return cloneCreatureItem;
    }

    private long getUnifiedId(String str) {
        try {
            return Long.parseLong(str.split(NumericEnum.SEP)[1]);
        } catch (Exception e) {
            Log.w(this.TAG, e.getMessage());
            return -1;
        }
    }

    private boolean invalidMergeCondition(MediaItem[] mediaItemArr) {
        if (mediaItemArr.length < 2 || isCreatureMixed(mediaItemArr)) {
            return true;
        }
        return false;
    }

    private boolean isCreatureMixed(MediaItem[] mediaItemArr) {
        if (!Arrays.stream(mediaItemArr).anyMatch(new C0571z(18)) || !Arrays.stream(mediaItemArr).anyMatch(new C0571z(19))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addTargetItem$2(MediaItem mediaItem, MediaItem mediaItem2) {
        return !TextUtils.equals(mediaItem2.getSubCategory(), mediaItem.getSubCategory());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$addTargetItem$3(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$executeMerge$5(String[] strArr, String str, MediaItem[] mediaItemArr, MediaItem mediaItem, String str2, ThreadPool.JobContext jobContext) {
        String[] strArr2;
        if (this.mIsPeople) {
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
                checkMeItemMergeAsOtherPeople(strArr);
            }
            PeopleDataManager.merge(strArr, str, MyQueryUtil.getInterface());
            new PersonUnlinkCmd().execute(getHandler(), mediaItemArr);
        } else {
            PetDataManager.merge(strArr, str, MyQueryUtil.getInterface());
        }
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            strArr2 = strArr;
            AutoAlbumHelper.getInstance().updateMergedRecommendedId(IdentityCreatureUtil.getUnifiedIdentityId(str), strArr2, CreatureData.of(mediaItem), this.mIsPeople);
            DbCompat.storyApi().updateMergedCreaturesToHideRule(str2, str, strArr2, CreatureData.of(mediaItem).faceGroupUuid);
            new AddContentsToMergedCreatureCmd().execute(getHandler(), str, strArr2, Boolean.valueOf(this.mIsPeople));
        } else {
            strArr2 = strArr;
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && this.mCreateTop5) {
            String top5Candidates = MyQueryCreator.getTop5Candidates(getBlackboard(), str, "recommended_id", (String) null, 2147483646);
            if (!TextUtils.isEmpty(top5Candidates)) {
                str = C0212a.B(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX, top5Candidates);
            }
            MyQueryCreator.createTop5(str);
        }
        getBlackboard().postEvent(EventMessage.obtain(1003));
        updateHistory(strArr2);
        getBlackboard().postEvent(EventMessage.obtain(8010));
        if (!this.mCreateTop5) {
            getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMerge$6(long j2, Future future) {
        getBlackboard().post("command://DismissDialog", Long.valueOf(j2));
        consumeExecuteListener(Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMeredItem$7(MediaItem mediaItem, MediaItem mediaItem2) {
        return !TextUtils.equals(mediaItem2.getSubCategory(), mediaItem.getSubCategory());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getMeredItem$8(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getMergedInfo$10(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getMultiplePrimaryKey$13(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onAssignedIdentityInfo((String) obj);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onExecute$1(MediaItem mediaItem) {
        if (CreatureData.of(mediaItem).creatureName != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showMergeConfirmDialog$4(EventContext eventContext, ArrayList arrayList) {
        if (arrayList == null) {
            consumeExecuteListener(Boolean.FALSE);
        } else if (this.mIsAllItemNoName) {
            executeEditCreatureNameCmd();
        } else {
            executeMerge((Object[]) arrayList.get(0), (String) null);
        }
    }

    private void onAssignedIdentityInfo(String str) {
        getBlackboard().unsubscribe("data://user/faceCluster/assignedIdentityInfo", this.mAssignedIdentityInfoListener);
        if (str != null) {
            String subCategory = this.mSourceItem.getSubCategory();
            this.mSourceItem.setSubCategory(str);
            executeMerge(new Object[]{this.mSourceItem, this.mTargetItems}, subCategory);
        }
    }

    private void showMergeConfirmDialog(EventContext eventContext, MediaItem[] mediaItemArr, boolean z) {
        getBlackboard().publish("data://user/selected", mediaItemArr);
        String build = new UriBuilder("data://user/dialog/MergeCreatureMultiple").build();
        if (z) {
            eventContext.getBlackboard().post(CommandKey.DATA_REQUEST(build), (Object) null);
        } else {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(build).setOnDataCompleteListener(new Fb.h(18, this)).start();
        }
    }

    private void updateHistory(String[] strArr) {
        SearchHistory.getInstance().deleteHistoryWithPrimaryKey(getMultiplePrimaryKey(strArr));
        getBlackboard().postEvent(EventMessage.obtain(8514));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        boolean z = false;
        if (objArr.length > 0) {
            selectedItems = addTargetItem(selectedItems, objArr[0]);
        }
        if (objArr.length > 1) {
            MediaItem mediaItem = objArr[1];
            this.mSourceItem = mediaItem;
            selectedItems = addTargetItem(selectedItems, mediaItem);
        }
        if (objArr.length > 2) {
            this.mCreateTop5 = objArr[2].booleanValue();
        }
        if (!invalidMergeCondition(selectedItems)) {
            this.mIsPeople = selectedItems[0].isPeople();
            if (Arrays.stream(selectedItems).noneMatch(new C0571z(17))) {
                this.mTargetItems = (MediaItem[]) Arrays.copyOfRange(selectedItems, 1, selectedItems.length);
                if (this.mSourceItem == null) {
                    this.mSourceItem = getSourceItem();
                }
                this.mIsAllItemNoName = true;
            }
            if (objArr.length > 3 && objArr[3].booleanValue()) {
                z = true;
            }
            showMergeConfirmDialog(eventContext, selectedItems, z);
        }
    }
}
