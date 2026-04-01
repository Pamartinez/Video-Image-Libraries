package com.samsung.android.gallery.app.ui.list.stories.pictures.related;

import Ad.C0720a;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedInfo {
    private final int mAlbumId;
    private final int mCategoryType;
    private final ArrayList<String> mGroupIds;
    private final boolean mIsNewStory;
    private boolean mIsSameCategory;
    private final ArrayList<String> mLocalities;
    private final ArrayList<String> mPersonIds;
    private final ArrayList<String> mPoiNames;
    private final ArrayList<String> mRelatedGroupIds = new ArrayList<>();
    private final ArrayList<String> mRelatedLocalities = new ArrayList<>();
    private final ArrayList<String> mRelatedPersonIds = new ArrayList<>();
    private final ArrayList<String> mRelatedPoiNames = new ArrayList<>();
    private final int mSaType;
    private final int mStoryType;

    public RelatedInfo(Cursor cursor) {
        int i2;
        boolean z = false;
        this.mAlbumId = cursor.getInt(0);
        int i7 = cursor.getInt(1);
        this.mStoryType = i7;
        this.mCategoryType = StoryType.getCategory(i7).ordinal();
        this.mIsNewStory = cursor.getInt(2) != 1 ? true : z;
        ArrayList<String> parseTag = parseTag(cursor.getString(3));
        this.mGroupIds = parseTag;
        ArrayList<String> parseTag2 = parseTag(cursor.getString(4));
        this.mPersonIds = parseTag2;
        this.mPoiNames = parseTag(cursor.getString(5));
        this.mLocalities = parseTag(cursor.getString(6));
        if (Features.isEnabled(Features.SUPPORT_CMH_STORY_SA_TYPE)) {
            i2 = cursor.getInt(8);
        } else {
            i2 = -1;
        }
        this.mSaType = i2;
        filterInvalidFace(parseTag, new C0720a(2));
        filterInvalidFace(parseTag2, new C0720a(3));
    }

    private void filterInvalidFace(ArrayList<String> arrayList, Supplier<Integer> supplier) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            try {
                if (!TextUtils.isEmpty(next) && Integer.parseInt(next) > supplier.get().intValue()) {
                    arrayList2.add(next);
                }
            } catch (Exception unused) {
            }
        }
        arrayList.clear();
        arrayList.addAll(arrayList2);
    }

    private void filterRelatedInfo(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        arrayList.clear();
        arrayList.addAll(arrayList2);
        arrayList.retainAll(arrayList3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$new$0() {
        return 0;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$new$1() {
        return 1;
    }

    private ArrayList<String> parseTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            return (ArrayList) Arrays.stream(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).filter(new C0464a(13)).collect(Collectors.toCollection(new C0720a(1)));
        }
        return new ArrayList<>();
    }

    public int compare(RelatedInfo relatedInfo) {
        if (relatedInfo.mIsNewStory) {
            return 1;
        }
        return -1;
    }

    public int getAlbumId() {
        return this.mAlbumId;
    }

    public int getStorySaType() {
        return this.mSaType;
    }

    public int getStoryType() {
        return this.mStoryType;
    }

    public boolean hasLocation() {
        if (this.mRelatedPoiNames.size() > 0 || this.mRelatedLocalities.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasPerson() {
        if (this.mRelatedPersonIds.size() > 0 || this.mRelatedGroupIds.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isNewStory() {
        return this.mIsNewStory;
    }

    public boolean isSameCategory() {
        return this.mIsSameCategory;
    }

    public boolean isSameStory(int i2) {
        if (this.mAlbumId == i2) {
            return true;
        }
        return false;
    }

    public void prepare(RelatedInfo relatedInfo) {
        boolean z;
        int i2 = this.mCategoryType;
        if (i2 != relatedInfo.mCategoryType || i2 == StoryCategoryType.COLLECTION.ordinal() || this.mCategoryType == StoryCategoryType.SPECIAL_DAY.ordinal()) {
            z = false;
        } else {
            z = true;
        }
        this.mIsSameCategory = z;
        filterRelatedInfo(this.mRelatedGroupIds, this.mGroupIds, relatedInfo.mGroupIds);
        filterRelatedInfo(this.mRelatedPersonIds, this.mPersonIds, relatedInfo.mPersonIds);
        filterRelatedInfo(this.mRelatedPoiNames, this.mPoiNames, relatedInfo.mPoiNames);
        filterRelatedInfo(this.mRelatedLocalities, this.mLocalities, relatedInfo.mLocalities);
    }

    public String toString() {
        return "RelatedInfo{" + this.mAlbumId + "=" + this.mIsNewStory + "|" + this.mIsSameCategory + "|" + this.mRelatedPersonIds + "|" + this.mRelatedGroupIds + "|" + Logger.getEncodedString((Object) this.mRelatedPoiNames) + "|" + Logger.getEncodedString((Object) this.mRelatedLocalities) + "}";
    }

    public String toStringOrigin() {
        return "RelatedInfo{" + this.mAlbumId + "=" + this.mIsNewStory + "|" + this.mIsSameCategory + "|" + this.mPersonIds + "|" + this.mGroupIds + "|" + Logger.getEncodedString((Object) this.mPoiNames) + "|" + Logger.getEncodedString((Object) this.mLocalities) + "}";
    }
}
