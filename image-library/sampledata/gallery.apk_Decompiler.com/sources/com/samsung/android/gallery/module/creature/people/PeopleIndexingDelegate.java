package com.samsung.android.gallery.module.creature.people;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.CreatureApi;
import com.samsung.android.gallery.database.dal.mp.helper.PeopleApi;
import com.samsung.android.gallery.module.creature.base.CreatureIndexingDelegate;
import com.samsung.android.gallery.module.creature.people.relationship.FamilyMember;
import com.samsung.android.gallery.support.search.CreatureIndexingBuilder;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.search.SearchIndexListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleIndexingDelegate extends CreatureIndexingDelegate {
    public void execute(CreatureIndexingBuilder creatureIndexingBuilder, ArrayList<Long> arrayList, int i2, IntelligentSearchIndex.IndexMode indexMode) {
        int i7;
        IntelligentSearchIndex.IndexMode indexMode2 = IntelligentSearchIndex.IndexMode.APPEND;
        if (indexMode == indexMode2) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        HashMap hashMap = new HashMap();
        if ((i2 & 1) != 0 && !TextUtils.isEmpty(creatureIndexingBuilder.getTagName(i7))) {
            hashMap.put(IntelligentSearchIndex.TagType.PERSON.getValue(0), creatureIndexingBuilder.getTagName(i7));
        }
        SearchIndexListener searchIndexListener = null;
        if ((i2 & 2) != 0) {
            hashMap.put(IntelligentSearchIndex.TagType.PERSON.getValue(1), Objects.toString(Long.valueOf(creatureIndexingBuilder.getTagId(i7)), (String) null));
        }
        if (CreatureIndexingDelegate.SUPPORT_UNIFIED_CREATURE_KEY && (i2 & 4) != 0) {
            hashMap.put(IntelligentSearchIndex.TagType.PERSON.getValue(2), Objects.toString(Long.valueOf(creatureIndexingBuilder.getUnifiedId(i7)), (String) null));
        }
        IntelligentSearchIndex.TagType tagType = IntelligentSearchIndex.TagType.RELATIONSHIP;
        if (tagType.isRequestEnabled() && (i2 & 8) != 0 && !TextUtils.isEmpty(creatureIndexingBuilder.getRelationship(i7))) {
            hashMap.put(tagType.getValue(), FamilyMember.getHierarchy(creatureIndexingBuilder.getRelationship(i7)));
        }
        IntelligentSearchIndex instance = IntelligentSearchIndex.getInstance();
        if (indexMode == indexMode2) {
            searchIndexListener = this.mIndexListener;
        }
        instance.indexing((Collection<Long>) arrayList, (Map<String, String>) hashMap, indexMode, searchIndexListener);
    }

    public CreatureApi getCreatureApi() {
        return new PeopleApi();
    }
}
