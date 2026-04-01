package com.samsung.android.gallery.module.search.root;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;
import org.json.JSONArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterResultsEntity {
    private final ArrayList<String> mCategoryList;
    private final ArrayList<FilterResultsEntity> mChildList;
    private int mCount;
    private final IntelligentSearchIndexFieldIcon mFieldIcon;
    private boolean mIsGroupType;
    private boolean mIsSelected;
    private MediaItem mMediaItem;
    private final String mName;
    protected ArrayList<String> mRawLabels;
    private String mSuggestedKeywordFeature;
    private Bitmap mThumb;

    public FilterResultsEntity(String str, IntelligentSearchIndexFieldIcon intelligentSearchIndexFieldIcon) {
        this.mThumb = null;
        this.mCategoryList = new ArrayList<>();
        this.mRawLabels = new ArrayList<>();
        this.mCount = 0;
        this.mChildList = new ArrayList<>();
        this.mName = str;
        this.mFieldIcon = intelligentSearchIndexFieldIcon;
    }

    public void addCategory(String str) {
        this.mCategoryList.add(str);
    }

    public void addChild(FilterResultsEntity filterResultsEntity) {
        this.mChildList.add(filterResultsEntity);
    }

    public void addRawLabel(String str) {
        this.mRawLabels.add(str);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FilterResultsEntity)) {
            return super.equals(obj);
        }
        FilterResultsEntity filterResultsEntity = (FilterResultsEntity) obj;
        if (!Objects.equals(this.mName, filterResultsEntity.mName) || !new HashSet(this.mCategoryList).equals(new HashSet(filterResultsEntity.mCategoryList)) || !Objects.equals(this.mRawLabels, filterResultsEntity.mRawLabels) || isOnlyThem() != filterResultsEntity.isOnlyThem()) {
            return false;
        }
        return true;
    }

    public String getCategory() {
        return this.mCategoryList.get(0);
    }

    public ArrayList<FilterResultsEntity> getChild() {
        return this.mChildList;
    }

    public int getCount() {
        return this.mCount;
    }

    public IntelligentSearchIndexFieldIcon getFieldIcon() {
        return this.mFieldIcon;
    }

    public Integer getFieldIconDrawableResId() {
        IntelligentSearchIndexFieldIcon intelligentSearchIndexFieldIcon = this.mFieldIcon;
        if (intelligentSearchIndexFieldIcon != null) {
            return intelligentSearchIndexFieldIcon.getDrawableResId();
        }
        return null;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public String getName() {
        return this.mName;
    }

    public String getRawLabels() {
        return this.mRawLabels.get(0);
    }

    public ArrayList<String> getRawLabelsList() {
        return this.mRawLabels;
    }

    public int getRawLabelsSize() {
        return this.mRawLabels.size();
    }

    public String getSelection() {
        String str;
        boolean z = true;
        if (this.mCategoryList.size() != 1) {
            z = false;
        }
        String str2 = "";
        if (z) {
            str = str2;
        } else {
            str = "(";
        }
        if (!z) {
            str2 = ")";
        }
        StringJoiner stringJoiner = new StringJoiner(" or ", str, str2);
        Iterator<String> it = this.mCategoryList.iterator();
        while (it.hasNext()) {
            stringJoiner.add(it.next() + " = ? ");
        }
        return stringJoiner.toString();
    }

    public JSONArray getSelectionArgs() {
        return new JSONArray(this.mRawLabels);
    }

    public String getSuggestedKeywordFeature() {
        return this.mSuggestedKeywordFeature;
    }

    public Bitmap getThumb() {
        return this.mThumb;
    }

    public boolean isCreature() {
        if (isPerson() || isPet()) {
            return true;
        }
        return false;
    }

    public boolean isGroupType() {
        return this.mIsGroupType;
    }

    public boolean isMultiModal() {
        return this.mCategoryList.contains("multi_modal");
    }

    public boolean isOnlyThem() {
        return false;
    }

    public boolean isPde() {
        return false;
    }

    public boolean isPerson() {
        return false;
    }

    public boolean isPet() {
        return false;
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public boolean isStory() {
        return TextUtils.equals(getCategory(), "storytag");
    }

    public boolean isThumbnailType() {
        if (this.mIsGroupType) {
            return false;
        }
        if (this.mCategoryList.contains("recommended_id") || this.mCategoryList.contains("pet_recommended_id") || this.mCategoryList.contains("multi_modal")) {
            return true;
        }
        return false;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void setSelected(boolean z) {
        this.mIsSelected = z;
    }

    public void setSuggestedKeywordFeature(String str) {
        this.mSuggestedKeywordFeature = str;
    }

    public void setThumb(Bitmap bitmap) {
        this.mThumb = bitmap;
    }

    public void sortChild(Comparator<FilterResultsEntity> comparator) {
        this.mChildList.sort(comparator);
    }

    public void sumCount(int i2) {
        this.mCount += i2;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        StringBuilder sb2 = new StringBuilder("Entity{");
        sb2.append(Logger.getEncodedString(this.mName));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mCategoryList);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(Logger.getEncodedString((Object) this.mRawLabels));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (isPerson()) {
            str = "P";
        } else {
            str = "p";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (isPet()) {
            str2 = "PT";
        } else {
            str2 = "pt";
        }
        sb2.append(str2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (isOnlyThem()) {
            str3 = "O";
        } else {
            str3 = "o";
        }
        sb2.append(str3);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mIsGroupType) {
            str4 = "G:(" + Logger.getEncodedString((Object) this.mChildList) + ")";
        } else {
            str4 = "g";
        }
        return C0212a.p(sb2, str4, "}");
    }

    public FilterResultsEntity(String str, String str2) {
        this.mThumb = null;
        ArrayList<String> arrayList = new ArrayList<>();
        this.mCategoryList = arrayList;
        this.mRawLabels = new ArrayList<>();
        this.mCount = 0;
        this.mChildList = new ArrayList<>();
        this.mName = str;
        this.mFieldIcon = null;
        arrayList.add(str2);
    }

    public FilterResultsEntity(String str, IntelligentSearchIndexFieldIcon intelligentSearchIndexFieldIcon, String str2) {
        this.mThumb = null;
        ArrayList<String> arrayList = new ArrayList<>();
        this.mCategoryList = arrayList;
        this.mRawLabels = new ArrayList<>();
        this.mCount = 0;
        this.mChildList = new ArrayList<>();
        this.mName = str;
        this.mFieldIcon = intelligentSearchIndexFieldIcon;
        arrayList.add(str2);
    }

    public FilterResultsEntity(FilterResultsGroupType filterResultsGroupType, String str) {
        this.mThumb = null;
        ArrayList<String> arrayList = new ArrayList<>();
        this.mCategoryList = arrayList;
        this.mRawLabels = new ArrayList<>();
        this.mCount = 0;
        this.mChildList = new ArrayList<>();
        this.mName = AppResources.getString(filterResultsGroupType.getTitleResId());
        this.mFieldIcon = IntelligentSearchIndexFieldIcon.create(filterResultsGroupType.getCategory(), "");
        arrayList.add(str);
        this.mIsGroupType = true;
    }
}
