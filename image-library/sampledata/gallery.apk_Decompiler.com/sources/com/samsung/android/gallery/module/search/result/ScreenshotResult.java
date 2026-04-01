package com.samsung.android.gallery.module.search.result;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.SearchResultApi;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenshotResult extends SearchResult {
    private String mSubSubCategory;

    public ScreenshotResult(Context context, String str) {
        super(context, str);
        this.mSubSubCategory = getSubSubCategory(str);
    }

    private static String getSubSubCategory(String str) {
        return ArgumentsUtil.getArgValue(str, "sub_sub");
    }

    public IntelligentSearchIndex.TagType getIndexingTagType() {
        return IntelligentSearchIndex.TagType.SCENE_CAPTURE;
    }

    public String getSelection() {
        if (TextUtils.isEmpty(this.mSubSubCategory)) {
            return "sec_media_id = ? AND (scene_name = ? or parent_name = ?)";
        }
        return "sec_media_id = ? AND (scene_name = ? or scene_name = ?)";
    }

    public String[] getSelectionArgs(long j2) {
        if (!TextUtils.isEmpty(this.mSubSubCategory)) {
            return new String[]{String.valueOf(j2), this.mSubSubCategory, this.mSubCategory};
        }
        String valueOf = String.valueOf(j2);
        String str = this.mSubCategory;
        return new String[]{valueOf, str, str};
    }

    public Uri getUri() {
        return new SearchResultApi().getOthersUri();
    }
}
