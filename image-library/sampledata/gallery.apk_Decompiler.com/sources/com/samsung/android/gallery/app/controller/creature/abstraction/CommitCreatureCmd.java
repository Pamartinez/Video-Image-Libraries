package com.samsung.android.gallery.app.controller.creature.abstraction;

import Fb.h;
import J3.a;
import N2.j;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CommitCreatureCmd extends BaseCommand {
    private String mCreatureName;
    protected boolean mIsMe;
    private String mLocationKey;
    private MediaItem mMergeItem;
    private String mSourceKey;

    private String getConcatenatedTop5FromFilter(Supplier<FilterResultsEntry> supplier) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Iterator<FilterResultsEntity> it = MyQueryCreator.sortCreatureEntity(supplier.get()).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            FilterResultsEntity next = it.next();
            i2++;
            if (i2 > 5) {
                break;
            }
            stringJoiner.add(next.getMediaItem().getSubCategory());
        }
        return stringJoiner.toString();
    }

    private void insertCreatureTop5(String str) {
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(str)) {
            Supplier supplier = (Supplier) getBlackboard().read("data://user/SearchFilterResultsEntry");
            if (supplier != null) {
                str2 = getConcatenatedTop5FromFilter(supplier);
            } else {
                str2 = MyQueryCreator.getTop5Candidates(getBlackboard(), str, "recommended_id", this.mCreatureName, 2147483646);
            }
            if (!TextUtils.isEmpty(str2)) {
                str = C0212a.B(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX, str2);
            }
            MyQueryCreator.createTop5(str);
            String str3 = this.TAG;
            StringBuilder k = j.k("Create top5 ", str, ", elapsed: +");
            k.append(System.currentTimeMillis() - currentTimeMillis);
            Log.v(str3, k.toString());
        }
    }

    private void insertUpdatedHistory() {
        getBlackboard().postEvent(EventMessage.obtain(8514));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Long lambda$addTag$0(String str, CreatureNameData creatureNameData, EventContext eventContext, Bitmap bitmap, ThreadPool.JobContext jobContext) {
        long addName = addName(str, creatureNameData);
        mergeCreature(this.mMergeItem, addName);
        updateMyQueryData(str, addName);
        postAddTag(eventContext, creatureNameData, bitmap);
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            AutoAlbumHelper.getInstance().updateRecommendedId(addName, str);
            DbCompat.storyApi().updateMergedCreaturesToHideRule(str, createIdentityInfo(addName), (String[]) null, (String) null);
        }
        return Long.valueOf(addName);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addTag$1(Future future) {
        onComplete(((Long) future.get()).longValue());
    }

    private void removeOldHistory() {
        String argValue = ArgumentsUtil.getArgValue(this.mLocationKey, "prev_sub_category");
        if (argValue != null && !IdentityCreatureUtil.isAssignedCreature(argValue)) {
            SearchHistory.getInstance().deleteHistoryWithPrimaryKey(ArgumentsUtil.getArgValue(this.mLocationKey, "term") + "/" + argValue);
        }
        SearchHistory.getInstance().deleteHistory(this.mLocationKey);
    }

    private void tagCreature(EventContext eventContext, Object... objArr) {
        String str = objArr[0];
        this.mLocationKey = str;
        CreatureNameData creatureNameData = objArr[1];
        this.mSourceKey = ArgumentsUtil.removeArgs(str);
        this.mCreatureName = creatureNameData.getName();
        String argValue = ArgumentsUtil.getArgValue(this.mLocationKey, "sub");
        String appendArgs = ArgumentsUtil.appendArgs(this.mLocationKey, "title", this.mCreatureName);
        this.mLocationKey = appendArgs;
        this.mLocationKey = ArgumentsUtil.appendArgs(appendArgs, "isNamed", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        removeOldHistory();
        addTag(eventContext, creatureNameData, argValue, objArr[2]);
    }

    private void updateMyQueryData(String str, long j2) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE || PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY) {
            MyQueryUtil.replaceUnifiedId(str, createIdentityInfo(j2));
        }
    }

    public abstract long addName(String str, CreatureNameData creatureNameData);

    public void addTag(EventContext eventContext, CreatureNameData creatureNameData, String str, Bitmap bitmap) {
        String str2 = str;
        ThreadPool.getInstance().submit(new a(this, str2, creatureNameData, eventContext, bitmap, 0), new h(25, this));
    }

    public abstract String createIdentityInfo(long j2);

    public abstract String getCreatureCategoryType();

    public String getEventId() {
        return AnalyticsEventId.EVENT_PEOPLE_TAG_DIALOG_SAVE_CLICKED.toString();
    }

    public abstract void mergeCreature(MediaItem mediaItem, long j2);

    public void onComplete(long j2) {
        String createIdentityInfo = createIdentityInfo(j2);
        if (((Boolean) getBlackboard().pop("data://user/faceCluster/isFromFaceMerge", Boolean.FALSE)).booleanValue()) {
            getBlackboard().post("data://user/faceCluster/assignedIdentityInfo", createIdentityInfo);
        } else {
            String appendArgs = ArgumentsUtil.appendArgs(this.mLocationKey, "title", this.mCreatureName);
            this.mLocationKey = appendArgs;
            String appendArgs2 = ArgumentsUtil.appendArgs(appendArgs, "isNamed", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            this.mLocationKey = appendArgs2;
            String appendArgs3 = ArgumentsUtil.appendArgs(appendArgs2, "sub", createIdentityInfo);
            this.mLocationKey = appendArgs3;
            this.mLocationKey = ArgumentsUtil.appendArgs(appendArgs3, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, getCreatureCategoryType());
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3001, 1, this.mSourceKey.hashCode(), this.mLocationKey));
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        }
        insertUpdatedHistory();
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && this.mIsMe && MyQueryUtil.isTop5Empty()) {
            insertCreatureTop5(createIdentityInfo);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (PreferenceFeatures.OneUi6x.SUPPORT_MERGE_PEOPLE_WITHOUT_NAME) {
            this.mMergeItem = MediaItemBuilder.cloneCreatureItem((MediaItem) getBlackboard().pop("data:///MergeCreatureTarget"));
        }
        tagCreature(eventContext, objArr);
    }

    public abstract void postAddTag(EventContext eventContext, CreatureNameData creatureNameData, Bitmap bitmap);

    public void postEventToDismiss() {
        getBlackboard().post("command://DismissDialog", (Object) null);
    }
}
