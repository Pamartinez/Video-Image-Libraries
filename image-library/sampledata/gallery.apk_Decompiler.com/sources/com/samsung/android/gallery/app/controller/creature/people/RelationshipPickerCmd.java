package com.samsung.android.gallery.app.controller.creature.people;

import A4.H;
import B8.f;
import I3.c;
import K3.b;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipPickerCmd extends BaseCommand {
    private Blackboard mBlackboard;

    private void completed(ArrayList<Object> arrayList, String str) {
        if (arrayList != null) {
            MediaItem mediaItem = (MediaItem) arrayList.get(0);
            CreatureNameData createPersonData = createPersonData(mediaItem, str);
            AtomicLong atomicLong = new AtomicLong();
            if (createPersonData == null) {
                Log.e(this.TAG, "Couldn't tag person data: PersonData is null");
            } else {
                ThreadPool.getInstance().submit(new c(this, atomicLong, mediaItem, createPersonData), new f((Object) this, (Object) mediaItem, (Object) createPersonData, (Object) atomicLong, 1));
            }
        }
    }

    private CreatureNameData createPersonData(MediaItem mediaItem, String str) {
        String[] parseRelationshipEntry = RelationshipKeySet.parseRelationshipEntry(str);
        if (parseRelationshipEntry == null) {
            return null;
        }
        return new CreatureNameData.Builder("People", CreatureNameData.ContactType.TAGGED).setName(CreatureData.computeNameIfAbsent(mediaItem, new b(parseRelationshipEntry, 0))).setRelationship(parseRelationshipEntry[0]).build();
    }

    private void goToPeopleResultView(MediaItem mediaItem, CreatureNameData creatureNameData, long j2) {
        int tagType = mediaItem.getTagType();
        String createWithCreatureId = IdentityCreatureUtil.createWithCreatureId(j2, IdentityCreatureUtil.Category.PEOPLE);
        String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(LocationKey.getSearchLocationKey("location://search/fileList/Category/People", createWithCreatureId), KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, "People"), "sub", createWithCreatureId), "title", creatureNameData.getName()), "term", FilterResultsKeySet.getField("People", createWithCreatureId, tagType)), "isNamed", String.valueOf(true));
        this.mBlackboard.post("command://MoveURL", appendArgs);
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3001, 1, appendArgs.hashCode(), appendArgs));
        BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$completed$1(AtomicLong atomicLong, MediaItem mediaItem, CreatureNameData creatureNameData, ThreadPool.JobContext jobContext) {
        atomicLong.set(PeopleDataManager.addName(mediaItem.getSubCategory(), creatureNameData));
        if (!PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE || !"me".equals(creatureNameData.getRelationship()) || !MyQueryUtil.isTop5Empty()) {
            return null;
        }
        String top5Candidates = MyQueryCreator.getTop5Candidates(getBlackboard(), mediaItem.getSubCategory(), "recommended_id", creatureNameData.getName(), mediaItem.getCount());
        String createWithCreatureId = IdentityCreatureUtil.createWithCreatureId(atomicLong.get(), IdentityCreatureUtil.Category.PEOPLE);
        if (!TextUtils.isEmpty(top5Candidates)) {
            createWithCreatureId = C0212a.B(createWithCreatureId, GlobalPostProcInternalPPInterface.SPLIT_REGEX, top5Candidates);
        }
        MyQueryCreator.createTop5(createWithCreatureId);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completed$2(MediaItem mediaItem, CreatureNameData creatureNameData, AtomicLong atomicLong, Future future) {
        getBlackboard().post("command://DismissDialog", (Object) null);
        goToPeopleResultView(mediaItem, creatureNameData, atomicLong.get());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$createPersonData$3(String[] strArr) {
        return strArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(String str, EventContext eventContext, ArrayList arrayList) {
        completed(arrayList, str);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_FACE_CONTACT_SUGGESTED_NAME.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str = objArr[0];
        Blackboard blackboard = eventContext.getBlackboard();
        this.mBlackboard = blackboard;
        blackboard.publish("data://user/PersonRelationship", str);
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/CreaturePicker").build()).setOnDataCompleteListener(new H(25, (Object) this, (Object) str)).start();
    }
}
