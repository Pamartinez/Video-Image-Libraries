package com.samsung.android.gallery.app.controller.creature.people;

import B5.e;
import J6.c;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipMultiplePickerCmd extends BaseCommand {
    private String getPeopleIds(List<Long> list) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        list.forEach(new e(stringJoiner, 3));
        return stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(Object[] objArr, EventContext eventContext) {
        String str;
        if (new MpHelper().getPeopleNoRelationshipCount() == 0) {
            Log.d(this.TAG, "no people data");
            return;
        }
        LinkedHashMap linkedHashMap = objArr[0];
        List arrayList = new ArrayList();
        if (!linkedHashMap.isEmpty()) {
            Iterator it = linkedHashMap.keySet().iterator();
            if (it.hasNext()) {
                String str2 = (String) it.next();
                str = RelationshipKeySet.getRelationshipTypes(str2);
                arrayList = (List) linkedHashMap.get(str2);
                getBlackboard().publish("data://user/PersonRelationshipCandidateMap", linkedHashMap);
                getBlackboard().post("command://MoveURL", new UriBuilder("location://search/fileList/PeopleSelectForRelation").appendArg("people_ids", getPeopleIds(arrayList)).appendArg("relationship_name", str).appendArg("fromStoriesCategory", LocationKey.isStoryOnDemandFloating(eventContext.getLocationKey())).appendArg("fromRelationshipPreview", LocationKey.isSearchRelationshipPreview(eventContext.getLocationKey())).build());
            }
        }
        str = "";
        getBlackboard().publish("data://user/PersonRelationshipCandidateMap", linkedHashMap);
        getBlackboard().post("command://MoveURL", new UriBuilder("location://search/fileList/PeopleSelectForRelation").appendArg("people_ids", getPeopleIds(arrayList)).appendArg("relationship_name", str).appendArg("fromStoriesCategory", LocationKey.isStoryOnDemandFloating(eventContext.getLocationKey())).appendArg("fromRelationshipPreview", LocationKey.isSearchRelationshipPreview(eventContext.getLocationKey())).build());
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().submit(new c(this, objArr, eventContext, 2));
    }
}
