package com.samsung.android.gallery.module.search.result;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.mp.helper.SearchResultApi;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PetResult extends SearchResult {
    public PetResult(Context context, String str) {
        super(context, str);
    }

    public IntelligentSearchIndex.TagType getIndexingTagType() {
        return IntelligentSearchIndex.TagType.PET;
    }

    public String getSelection() {
        return null;
    }

    public String[] getSelectionArgs(long j2) {
        return null;
    }

    public Uri getUri() {
        return new SearchResultApi().getPetFacesUri();
    }

    public Map<String, String> getValueMap(MediaItem mediaItem) {
        IntelligentSearchIndex.TagType tagType = IntelligentSearchIndex.TagType.PET;
        String str = CreatureData.of(mediaItem).creatureName;
        return tagType.getValueMap(str, IdentityCreatureUtil.getUnifiedIdentityId(this.mSubCategory) + "");
    }

    public int removeTo(MediaItem mediaItem) {
        return PetDataManager.removeTo(mediaItem, this.mSubCategory, false);
    }
}
