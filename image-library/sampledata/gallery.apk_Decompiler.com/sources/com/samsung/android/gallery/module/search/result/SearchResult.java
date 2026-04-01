package com.samsung.android.gallery.module.search.result;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.search.SearchIndexListener;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchResult {
    Context mAppContext;
    String mSubCategory;

    public SearchResult(Context context, String str) {
        this.mAppContext = context.getApplicationContext();
        this.mSubCategory = getSubCategory(str);
    }

    public static SearchResult create(Context context, String str) {
        String category = getCategory(str);
        if (TextUtils.isEmpty(category)) {
            return null;
        }
        category.getClass();
        char c5 = 65535;
        switch (category.hashCode()) {
            case -1907941713:
                if (category.equals("People")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1179428371:
                if (category.equals("My tags")) {
                    c5 = 1;
                    break;
                }
                break;
            case 80127:
                if (category.equals("Pet")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1576064422:
                if (category.equals("ScreenShot")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1843423419:
                if (category.equals("Expressions")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return new PeopleResult(context, str);
            case 1:
                return new MyTagResult(context, str);
            case 2:
                return new PetResult(context, str);
            case 3:
                return new ScreenshotResult(context, str);
            case 4:
                return new ExpressionResult(context, str);
            default:
                return new OthersResult(context, str);
        }
    }

    private static String getCategory(String str) {
        return ArgumentsUtil.getArgValue(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
    }

    private static String getSubCategory(String str) {
        String argValue = ArgumentsUtil.getArgValue(str, "sub");
        if ("Other Documents".equals(argValue)) {
            return "Documents".toLowerCase();
        }
        return argValue;
    }

    public abstract IntelligentSearchIndex.TagType getIndexingTagType();

    public abstract String getSelection();

    public abstract String[] getSelectionArgs(long j2);

    public abstract Uri getUri();

    public Map<String, String> getValueMap(MediaItem mediaItem) {
        HashMap hashMap = new HashMap();
        if (getIndexingTagType().isRequestEnabled()) {
            hashMap.put(getIndexingTagType().getValue(), this.mSubCategory);
        }
        return hashMap;
    }

    public void indexing(ArrayList<Long> arrayList, MediaItem mediaItem, SearchIndexListener searchIndexListener) {
        IntelligentSearchIndex.getInstance().indexing((Collection<Long>) arrayList, getValueMap(mediaItem), IntelligentSearchIndex.IndexMode.REMOVE, searchIndexListener);
    }

    public int removeTo(MediaItem mediaItem) {
        return this.mAppContext.getContentResolver().delete(getUri(), getSelection(), getSelectionArgs(mediaItem.getFileId()));
    }
}
