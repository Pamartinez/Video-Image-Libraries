package com.samsung.android.gallery.module.search.result;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.SearchResultApi;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.people.relationship.FamilyMember;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PeopleResult extends SearchResult {
    public PeopleResult(Context context, String str) {
        super(context, str);
    }

    public IntelligentSearchIndex.TagType getIndexingTagType() {
        return IntelligentSearchIndex.TagType.PERSON;
    }

    public String getSelection() {
        return null;
    }

    public String[] getSelectionArgs(long j2) {
        return null;
    }

    public Uri getUri() {
        return new SearchResultApi().getFacesUri();
    }

    public Map<String, String> getValueMap(MediaItem mediaItem) {
        long j2;
        String str;
        HashMap hashMap = new HashMap();
        CreatureData of2 = CreatureData.of(mediaItem);
        if (getIndexingTagType().isRequestEnabled() && !TextUtils.isEmpty(of2.creatureName)) {
            hashMap.put(getIndexingTagType().getValue(), of2.creatureName);
        }
        IntelligentSearchIndex.TagType tagType = IntelligentSearchIndex.TagType.RELATIONSHIP;
        if (tagType.isRequestEnabled() && !TextUtils.isEmpty(of2.relationshipType)) {
            hashMap.put(tagType.getValue(), FamilyMember.getHierarchy(of2.relationshipType));
        }
        IntelligentSearchIndex.TagType tagType2 = IntelligentSearchIndex.TagType.PERSON;
        String value = tagType2.getValue(1);
        if (IdentityCreatureUtil.isAssignedCreature(this.mSubCategory)) {
            j2 = IdentityCreatureUtil.getIdentityId(this.mSubCategory);
        } else {
            j2 = 1;
        }
        hashMap.put(value, String.valueOf(j2));
        String value2 = tagType2.getValue(2);
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            str = String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(this.mSubCategory));
        } else {
            str = null;
        }
        hashMap.put(value2, str);
        return hashMap;
    }

    public int removeTo(MediaItem mediaItem) {
        return PeopleDataManager.removeTo(mediaItem, this.mSubCategory, false);
    }
}
