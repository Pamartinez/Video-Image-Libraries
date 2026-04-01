package com.samsung.android.gallery.database.dal.abstraction.query;

import A8.C0545b;
import M9.a;
import android.net.Uri;
import android.os.Build;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QueryParams implements Cloneable {
    public static final boolean SUPPORT_CAM_MODEL = Features.isEnabled(Features.SUPPORT_MP_CAM_MODEL);
    public static final boolean SUPPORT_CAPTURE_FRAMERATE = Features.isEnabled(Features.SUPPORT_MP_CAPTURE_FRAMERATE);
    public static final boolean SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH;
    public static final boolean SUPPORT_LOCAL_TIME = Features.isEnabled(Features.SUPPORT_LOCAL_TIME);
    private static final boolean isGED = Features.isEnabled(Features.IS_GED);
    public boolean CMH_TO_MP_MIGRATION;
    public boolean STORY_ONE_UI_50;
    public boolean STORY_ONE_UI_70;
    public boolean SUPPORT_DUAL_REC;
    public final boolean SUPPORT_MEMORY_DATA;
    public boolean SUPPORT_PET_CLUSTER;
    public boolean SUPPORT_PPP_DELETE_TO_TRASH;
    public boolean SUPPORT_PPP_DRAFT;
    public boolean SUPPORT_RECENT_PRIMARY;
    public boolean SUPPORT_SEARCH_PEOPLE_FACE_SCORE;
    public final boolean SUPPORT_STORIES_DATA_SEP11 = Features.isEnabled(Features.SUPPORT_STORIES_DATA_SEP11);
    public final boolean SUPPORT_STORIES_DATA_SEP13;
    public boolean SUPPORT_TEXT_EXTRACTION_CMH_DETECTION;
    public boolean VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61;
    public boolean VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_611;
    public boolean VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71;
    public boolean VISUAL_SEARCH_ONEUI_30;
    public boolean cloudSyncOn;
    public boolean essentialFaceOnly;
    public long excludeHourHighBound;
    public long excludeHourLowBound;
    public RawQueryExecutor inQueryExecutor;
    public boolean mAddDataStamp;
    public String mAlbumDisplayName;
    private ArrayList<Integer> mAlbumIdList;
    public String mCreatureFaceGroupIds;
    public String mCreatureTagName;
    public String mDataLike;
    private String mDbKey;
    private DbStorageType mDbStorageType;
    public boolean mDynamicViewingInfo;
    public long mEndTime;
    public String mExceptRecommendedIds;
    private ArrayList<Integer> mExcludeAlbumIdList;
    public long mExcludeCreationDay;
    public String mExcludeFileIds;
    public String mExpandedDates;
    public long mFaceGroupId;
    private long mFileId;
    private String mFileIds;
    public String mFileNamePrefixExclude;
    private String mFilePath;
    private long mFromNow;
    public String mGpsRange;
    private GroupMediaFilter mGroupMediaFilter;
    public boolean mGroupSizeSum;
    private GroupType[] mGroupTypes;
    private boolean mIncludeFavorite;
    public String mIncludeFileIds;
    public boolean mIsForOnDemandQuery;
    public boolean mIsOrderImmutable;
    private boolean mIsStoryFavoriteType;
    private int mLimitOffset;
    private int mLimitSize;
    private long mMediaId;
    private String mMediaIds;
    private String mMediaIdsFilter;
    private String mMediaTypeFilter;
    public int mMinFaceCount;
    private String mNames;
    public boolean mNeedFaceScore;
    public boolean mNoRelationshipCreature;
    public String mOrder;
    private int mOsVersion;
    public int mParentAlbumId;
    private String mParentCategory;
    public boolean mPdcFiles;
    public boolean mPrintQuery;
    public String mRecommendedIds;
    public boolean mReplaceVolumeName;
    private ArrayList<String> mRequiredColumns;
    private int mScreenShotFolderId;
    private boolean mShowHidden;
    private int mSortBy;
    public long mStartTime;
    private int mStoryCategoryType;
    private String mStoryIds;
    private boolean mStoryVisible;
    private String mSubCategory;
    private HashMap<String, Object> mTag;
    public TargetDbTypes mTargetDb;
    public boolean mTrashOnly;
    public boolean mUngroupBurstShot;
    private ArrayList<String> mUriList;
    private boolean mUseBigAlbumIndex;
    public boolean mUseFileIdsConcat;
    private boolean mUseIdOrder;
    private boolean mWithEmptyAlbums;
    public long maxFileId;
    public long minFileId;
    public int minResolution;
    public int minSize;
    public boolean useDefaultOrder;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DbStorageType {
        All,
        LocalOnly,
        includeCloud,
        CloudOnly,
        LocalCloud
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GroupMediaFilter {
        public int albumId;
        public boolean bestOnly = false;
        public long groupId;
        public String groupIds;
        public boolean hasBest = false;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TargetDbTypes {
        SEC,
        GED,
        GMP
    }

    static {
        boolean z;
        if (DeviceConfig.UNIT_TEST || !PreferenceFeatures.Poc.SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH = z;
    }

    public QueryParams() {
        this.SUPPORT_MEMORY_DATA = Features.isEnabled(Features.SUPPORT_MEMORY_DATA) && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.SUPPORT_STORIES_DATA_SEP13 = PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_50 = PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_70 = PreferenceFeatures.OneUi7x.STORY_ONE_UI_70;
        this.VISUAL_SEARCH_ONEUI_30 = SdkConfig.atLeast(SdkConfig.GED.R);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61 = Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS);
        this.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION = Features.isEnabled(Features.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION);
        this.SUPPORT_RECENT_PRIMARY = Features.isEnabled(Features.SUPPORT_RECENT_BACKUP);
        this.SUPPORT_PPP_DELETE_TO_TRASH = Features.isEnabled(Features.SUPPORT_PPP_MENU);
        this.SUPPORT_PPP_DRAFT = Features.isEnabled(Features.SUPPORT_PPP_DRAFT);
        this.SUPPORT_DUAL_REC = Features.isEnabled(Features.SUPPORT_DUAL_REC);
        this.SUPPORT_SEARCH_PEOPLE_FACE_SCORE = PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE;
        this.SUPPORT_PET_CLUSTER = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_611 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2);
        this.CMH_TO_MP_MIGRATION = Features.isEnabled(Features.CMH_TO_MP_MIGRATION);
        this.mOsVersion = DeviceConfig.UNIT_TEST ? 29 : Build.VERSION.SDK_INT;
        this.mAddDataStamp = false;
        this.maxFileId = -1;
        this.minFileId = -1;
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mMediaTypeFilter = null;
        this.mMediaIdsFilter = null;
        this.mFromNow = -1;
        this.mDbStorageType = DbStorageType.All;
        this.mShowHidden = false;
        this.mTargetDb = (isGED || PocFeatures.isEnabled(PocFeatures.GmpAll)) ? TargetDbTypes.GMP : TargetDbTypes.SEC;
        this.mSortBy = 12;
        this.mMediaId = -1;
        this.mFileId = -1;
        this.mStoryCategoryType = -1;
        this.mStoryVisible = true;
        this.mMinFaceCount = 0;
        this.mExcludeCreationDay = -1;
        this.useDefaultOrder = true;
        this.mParentAlbumId = -1;
        this.mFaceGroupId = -1;
        setGroupTypes(GroupType.BURST);
    }

    private String getGroupTypesInfo(GroupType[] groupTypeArr) {
        String str = "";
        if (groupTypeArr != null) {
            for (GroupType groupType : groupTypeArr) {
                StringBuilder s = C0212a.s(str);
                s.append(groupType.tag);
                str = s.toString();
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addUris$0(Uri uri) {
        addUri(uri.toString());
    }

    public QueryParams addAlbumId(int i2) {
        if (this.mAlbumIdList == null) {
            this.mAlbumIdList = new ArrayList<>();
        }
        this.mAlbumIdList.add(Integer.valueOf(i2));
        return this;
    }

    public QueryParams addAlbumIds(int[] iArr) {
        if (iArr != null) {
            if (this.mAlbumIdList == null) {
                this.mAlbumIdList = new ArrayList<>();
            }
            for (int valueOf : iArr) {
                this.mAlbumIdList.add(Integer.valueOf(valueOf));
            }
        }
        return this;
    }

    public QueryParams addDataStamp() {
        this.mAddDataStamp = true;
        return this;
    }

    public QueryParams addGroupType(GroupType groupType) {
        GroupType[] groupTypeArr = this.mGroupTypes;
        if (groupTypeArr == null) {
            this.mGroupTypes = new GroupType[1];
        } else {
            this.mGroupTypes = (GroupType[]) Arrays.copyOf(groupTypeArr, groupTypeArr.length + 1);
        }
        GroupType[] groupTypeArr2 = this.mGroupTypes;
        groupTypeArr2[groupTypeArr2.length - 1] = groupType;
        return this;
    }

    public QueryParams addRequiredColumns(String str) {
        if (this.mRequiredColumns == null) {
            this.mRequiredColumns = new ArrayList<>();
        }
        this.mRequiredColumns.add(str);
        return this;
    }

    public QueryParams addUri(String str) {
        if (this.mUriList == null) {
            this.mUriList = new ArrayList<>();
        }
        this.mUriList.add(str);
        return this;
    }

    public QueryParams addUris(String[] strArr) {
        if (strArr != null) {
            for (String addUri : strArr) {
                addUri(addUri);
            }
        }
        return this;
    }

    public void clearAlbumIds() {
        ArrayList<Integer> arrayList = this.mAlbumIdList;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public QueryParams excludeAlbumId(int i2) {
        if (this.mExcludeAlbumIdList == null) {
            this.mExcludeAlbumIdList = new ArrayList<>();
        }
        this.mExcludeAlbumIdList.add(Integer.valueOf(i2));
        return this;
    }

    public QueryParams excludeScreenShots() {
        this.mFileNamePrefixExclude = "Screenshot_";
        return excludeAlbumId(StorageInfo.getDefault().buckets().screenShot);
    }

    public QueryParams excludeTakenTime(long j2, long j3, long j8) {
        this.mExcludeCreationDay = j2;
        this.excludeHourLowBound = j3;
        this.excludeHourHighBound = j8;
        return this;
    }

    public String getAlbumDisplayName() {
        return this.mAlbumDisplayName;
    }

    public int[] getAlbumIdArray() {
        ArrayList<Integer> arrayList = this.mAlbumIdList;
        if (arrayList == null) {
            return null;
        }
        return arrayList.stream().mapToInt(new C0545b(28)).toArray();
    }

    public int getAlbumIdCount() {
        ArrayList<Integer> arrayList = this.mAlbumIdList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public ArrayList<Integer> getAlbumIdList() {
        return this.mAlbumIdList;
    }

    public String getDbKey() {
        return this.mDbKey;
    }

    public DbStorageType getDbStorageType() {
        return this.mDbStorageType;
    }

    public ArrayList<Integer> getExcludeAlbumIdList() {
        return this.mExcludeAlbumIdList;
    }

    public String getExcludeFileIds() {
        return this.mExcludeFileIds;
    }

    public String getExpandedDates() {
        return this.mExpandedDates;
    }

    public long getFileId() {
        return this.mFileId;
    }

    public String getFileIds() {
        return this.mFileIds;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public long getFromNow() {
        return this.mFromNow;
    }

    public GroupMediaFilter getGroupMediaFilter() {
        return this.mGroupMediaFilter;
    }

    public GroupType[] getGroupTypes() {
        return this.mGroupTypes;
    }

    public String getIncludeFileIds() {
        return this.mIncludeFileIds;
    }

    public int getLimitOffset() {
        return this.mLimitOffset;
    }

    public int getLimitSize() {
        return this.mLimitSize;
    }

    public long getMediaId() {
        return this.mMediaId;
    }

    public String getMediaIds() {
        return this.mMediaIds;
    }

    public String getMediaIdsFilter() {
        return this.mMediaIdsFilter;
    }

    public String getMediaTypeFilter() {
        return this.mMediaTypeFilter;
    }

    public String getNames() {
        return this.mNames;
    }

    public String getOrder() {
        return this.mOrder;
    }

    public int getOsVersion() {
        return this.mOsVersion;
    }

    public String getParentCategory() {
        return this.mParentCategory;
    }

    public ArrayList<String> getRequiredColumns() {
        return this.mRequiredColumns;
    }

    public int getScreenShotFolderId() {
        return this.mScreenShotFolderId;
    }

    public int getSortBy() {
        return this.mSortBy;
    }

    public int getStoryCategoryType() {
        return this.mStoryCategoryType;
    }

    public String getStoryIds() {
        return this.mStoryIds;
    }

    public String getSubCategory() {
        return this.mSubCategory;
    }

    public Object getTag(String str, Object obj) {
        HashMap<String, Object> hashMap = this.mTag;
        if (hashMap != null) {
            return hashMap.getOrDefault(str, obj);
        }
        return obj;
    }

    public String[] getUriArray() {
        ArrayList<String> arrayList = this.mUriList;
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public boolean getUseIdOrder() {
        return this.mUseIdOrder;
    }

    public boolean hasGroupType(GroupType groupType) {
        return Arrays.asList(this.mGroupTypes).contains(groupType);
    }

    public boolean hasLimit() {
        if (this.mLimitSize > 0) {
            return true;
        }
        return false;
    }

    public boolean isIncludeFavorite() {
        return this.mIncludeFavorite;
    }

    public boolean isShowCloudOnly() {
        if (this.mDbStorageType == DbStorageType.CloudOnly) {
            return true;
        }
        return false;
    }

    public boolean isShowHidden() {
        return this.mShowHidden;
    }

    public boolean isShowLocal() {
        DbStorageType dbStorageType = this.mDbStorageType;
        if (dbStorageType == DbStorageType.LocalOnly || dbStorageType == DbStorageType.LocalCloud) {
            return true;
        }
        return false;
    }

    public boolean isShowLocalOnly() {
        if (this.mDbStorageType == DbStorageType.LocalOnly || Features.isEnabled(Features.IS_UPSM) || !Features.isEnabled(Features.SUPPORT_CLOUD)) {
            return true;
        }
        return false;
    }

    public boolean isStoryFavoriteType() {
        return this.mIsStoryFavoriteType;
    }

    public boolean isStoryVisible() {
        return this.mStoryVisible;
    }

    public boolean isWithEmptyAlbums() {
        return this.mWithEmptyAlbums;
    }

    public boolean needDataStamp() {
        return this.mAddDataStamp;
    }

    public QueryParams onlyTrashed() {
        if (this.SUPPORT_PPP_DELETE_TO_TRASH) {
            this.mTrashOnly = true;
        }
        return this;
    }

    public void printQuery() {
        this.mPrintQuery = true;
    }

    public QueryParams setAlbumCount(int i2) {
        boolean z;
        if (i2 > 80000) {
            z = true;
        } else {
            z = false;
        }
        this.mUseBigAlbumIndex = z;
        return this;
    }

    public QueryParams setCloudSync(boolean z) {
        this.cloudSyncOn = z;
        return this;
    }

    public QueryParams setCreationTimeLimit(long j2, long j3) {
        this.mStartTime = j2;
        this.mEndTime = j3;
        return this;
    }

    public QueryParams setDataLike(String str) {
        this.mDataLike = str;
        return this;
    }

    public QueryParams setDbKey(String str) {
        this.mDbKey = str;
        return this;
    }

    public QueryParams setDynamicViewingInfo(boolean z) {
        this.mDynamicViewingInfo = z;
        return this;
    }

    public QueryParams setEssentialFaceOnly(boolean z) {
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            this.essentialFaceOnly = z;
        }
        return this;
    }

    public QueryParams setExcludeFileIds(String str) {
        this.mExcludeFileIds = str;
        return this;
    }

    public QueryParams setExpandedDates(String str) {
        this.mExpandedDates = str;
        return this;
    }

    public QueryParams setFileId(long j2) {
        this.mFileId = j2;
        return this;
    }

    public QueryParams setFileIds(String str) {
        this.mFileIds = str;
        return this;
    }

    public QueryParams setFilePath(String str) {
        this.mFilePath = str;
        return this;
    }

    public QueryParams setGpsRange(String str) {
        this.mGpsRange = str;
        return this;
    }

    public QueryParams setGroupMediaFilter(int i2, long j2) {
        return setGroupMediaFilter(i2, j2, false);
    }

    public void setGroupSizeSum() {
        this.mGroupSizeSum = true;
    }

    public QueryParams setGroupTypes(GroupType... groupTypeArr) {
        this.mGroupTypes = groupTypeArr;
        return this;
    }

    public final QueryParams setImageOnly() {
        this.mMediaTypeFilter = MediaFilterType.IMAGE_ONLY.toString();
        return this;
    }

    public QueryParams setIncludeFavorite() {
        this.mIncludeFavorite = true;
        return this;
    }

    public QueryParams setLimit(int i2) {
        this.mLimitOffset = 0;
        this.mLimitSize = i2;
        return this;
    }

    public QueryParams setMaxFileId(long j2) {
        this.maxFileId = j2;
        return this;
    }

    public QueryParams setMediaId(long j2) {
        this.mMediaId = j2;
        return this;
    }

    public QueryParams setMediaIds(String str) {
        this.mMediaIds = str;
        return this;
    }

    public final QueryParams setMediaIdsFilter(String str) {
        this.mMediaIdsFilter = str;
        return this;
    }

    public final QueryParams setMediaTypeFilter(String str) {
        this.mMediaTypeFilter = str;
        return this;
    }

    public QueryParams setMinFileId(long j2) {
        this.minFileId = j2;
        return this;
    }

    public QueryParams setNames(String str) {
        this.mNames = str;
        return this;
    }

    public QueryParams setOrder(String str) {
        this.mOrder = str;
        return this;
    }

    public QueryParams setOrderImmutable(boolean z) {
        this.mIsOrderImmutable = z;
        return this;
    }

    public QueryParams setParentAlbumId(int i2) {
        this.mParentAlbumId = i2;
        return this;
    }

    public QueryParams setParentCategory(String str) {
        this.mParentCategory = str;
        return this;
    }

    public QueryParams setPdcFiles(boolean z) {
        this.mPdcFiles = z;
        return this;
    }

    public QueryParams setReplaceVolumeName() {
        this.mReplaceVolumeName = true;
        return this;
    }

    public QueryParams setScreenShotFolderId(int i2) {
        this.mScreenShotFolderId = i2;
        return this;
    }

    public QueryParams setShowHidden(boolean z) {
        this.mShowHidden = z;
        return this;
    }

    public final QueryParams setSortBy(int i2) {
        this.mSortBy = i2;
        return this;
    }

    public QueryParams setStartTime(long j2) {
        this.mStartTime = j2;
        return this;
    }

    public final QueryParams setStorageTypes(DbStorageType dbStorageType) {
        this.mDbStorageType = dbStorageType;
        return this;
    }

    public QueryParams setStoryFavoriteType(boolean z) {
        this.mIsStoryFavoriteType = z;
        return this;
    }

    public QueryParams setStoryIds(String str) {
        this.mStoryIds = str;
        return this;
    }

    public QueryParams setSubCategory(String str) {
        this.mSubCategory = str;
        return this;
    }

    public QueryParams setTag(String str, Object obj) {
        if (this.mTag == null) {
            this.mTag = new HashMap<>();
        }
        this.mTag.put(str, obj);
        return this;
    }

    public final QueryParams setTimeLimit(long j2) {
        this.mFromNow = j2;
        return this;
    }

    public QueryParams setUngroupBurstShot(boolean z) {
        this.mUngroupBurstShot = z;
        return this;
    }

    public QueryParams setUseIdOrder(boolean z) {
        this.mUseIdOrder = z;
        return this;
    }

    public QueryParams setWithEmptyAlbums(boolean z) {
        this.mWithEmptyAlbums = z;
        return this;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("QueryParams{");
        sb2.append(this.mDbKey);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(SortByType.toDebugString(this.mSortBy));
        sb2.append(getGroupTypesInfo(this.mGroupTypes));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDbStorageType);
        String str2 = "";
        if (this.mAlbumIdList == null) {
            str = str2;
        } else {
            str = ",[" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mAlbumIdList.iterator()) + "]";
        }
        sb2.append(str);
        if (this.mLimitSize > 0) {
            str2 = GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mLimitOffset + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mLimitSize;
        }
        return C0212a.p(sb2, str2, "}");
    }

    public boolean useBigAlbumIndex() {
        return this.mUseBigAlbumIndex;
    }

    public QueryParams clone() {
        try {
            QueryParams queryParams = (QueryParams) super.clone();
            if (this.mAlbumIdList != null) {
                queryParams.mAlbumIdList = new ArrayList<>(this.mAlbumIdList);
            }
            if (this.mExcludeAlbumIdList != null) {
                queryParams.mExcludeAlbumIdList = new ArrayList<>(this.mExcludeAlbumIdList);
            }
            if (this.mUriList != null) {
                queryParams.mUriList = new ArrayList<>(this.mUriList);
            }
            if (this.mRequiredColumns != null) {
                queryParams.mRequiredColumns = new ArrayList<>(this.mRequiredColumns);
            }
            return queryParams;
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) "QueryParams", "clone failed", (Throwable) e);
            return this;
        }
    }

    public QueryParams setGroupMediaFilter(String str) {
        GroupMediaFilter groupMediaFilter = new GroupMediaFilter();
        this.mGroupMediaFilter = groupMediaFilter;
        groupMediaFilter.groupIds = str;
        return this;
    }

    public QueryParams addUris(Collection<Uri> collection) {
        if (collection != null) {
            collection.forEach(new a(2, this));
        }
        return this;
    }

    public QueryParams setLimit(int i2, int i7) {
        this.mLimitOffset = i2;
        this.mLimitSize = i7;
        return this;
    }

    public QueryParams addUri(Uri uri) {
        return uri != null ? addUri(uri.toString()) : this;
    }

    public QueryParams excludeAlbumId(Collection<Integer> collection) {
        if (collection != null && collection.size() > 0) {
            if (this.mExcludeAlbumIdList == null) {
                this.mExcludeAlbumIdList = new ArrayList<>();
            }
            this.mExcludeAlbumIdList.addAll(collection);
        }
        return this;
    }

    public QueryParams setGroupMediaFilter(int i2, long j2, boolean z) {
        GroupMediaFilter groupMediaFilter = new GroupMediaFilter();
        this.mGroupMediaFilter = groupMediaFilter;
        groupMediaFilter.albumId = i2;
        groupMediaFilter.bestOnly = z;
        groupMediaFilter.groupId = j2;
        return this;
    }

    public QueryParams addAlbumIds(Collection<Integer> collection) {
        if (collection != null) {
            if (this.mAlbumIdList == null) {
                this.mAlbumIdList = new ArrayList<>();
            }
            this.mAlbumIdList.addAll(collection);
        }
        return this;
    }

    public QueryParams(String str) {
        this.SUPPORT_MEMORY_DATA = Features.isEnabled(Features.SUPPORT_MEMORY_DATA) && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.SUPPORT_STORIES_DATA_SEP13 = PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_50 = PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_70 = PreferenceFeatures.OneUi7x.STORY_ONE_UI_70;
        this.VISUAL_SEARCH_ONEUI_30 = SdkConfig.atLeast(SdkConfig.GED.R);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61 = Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS);
        this.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION = Features.isEnabled(Features.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION);
        this.SUPPORT_RECENT_PRIMARY = Features.isEnabled(Features.SUPPORT_RECENT_BACKUP);
        this.SUPPORT_PPP_DELETE_TO_TRASH = Features.isEnabled(Features.SUPPORT_PPP_MENU);
        this.SUPPORT_PPP_DRAFT = Features.isEnabled(Features.SUPPORT_PPP_DRAFT);
        this.SUPPORT_DUAL_REC = Features.isEnabled(Features.SUPPORT_DUAL_REC);
        this.SUPPORT_SEARCH_PEOPLE_FACE_SCORE = PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE;
        this.SUPPORT_PET_CLUSTER = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_611 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2);
        this.CMH_TO_MP_MIGRATION = Features.isEnabled(Features.CMH_TO_MP_MIGRATION);
        this.mOsVersion = DeviceConfig.UNIT_TEST ? 29 : Build.VERSION.SDK_INT;
        this.mAddDataStamp = false;
        this.maxFileId = -1;
        this.minFileId = -1;
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mMediaTypeFilter = null;
        this.mMediaIdsFilter = null;
        this.mFromNow = -1;
        this.mDbStorageType = DbStorageType.All;
        this.mShowHidden = false;
        this.mTargetDb = (isGED || PocFeatures.isEnabled(PocFeatures.GmpAll)) ? TargetDbTypes.GMP : TargetDbTypes.SEC;
        this.mSortBy = 12;
        this.mMediaId = -1;
        this.mFileId = -1;
        this.mStoryCategoryType = -1;
        this.mStoryVisible = true;
        this.mMinFaceCount = 0;
        this.mExcludeCreationDay = -1;
        this.useDefaultOrder = true;
        this.mParentAlbumId = -1;
        this.mFaceGroupId = -1;
        this.mDbKey = str;
        setGroupTypes(GroupType.BURST);
    }

    public QueryParams(boolean z, boolean z3, boolean z7) {
        this.SUPPORT_MEMORY_DATA = Features.isEnabled(Features.SUPPORT_MEMORY_DATA) && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.SUPPORT_STORIES_DATA_SEP13 = PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_50 = PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_70 = PreferenceFeatures.OneUi7x.STORY_ONE_UI_70;
        this.VISUAL_SEARCH_ONEUI_30 = SdkConfig.atLeast(SdkConfig.GED.R);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61 = Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS);
        this.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION = Features.isEnabled(Features.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION);
        this.SUPPORT_RECENT_PRIMARY = Features.isEnabled(Features.SUPPORT_RECENT_BACKUP);
        this.SUPPORT_PPP_DELETE_TO_TRASH = Features.isEnabled(Features.SUPPORT_PPP_MENU);
        this.SUPPORT_PPP_DRAFT = Features.isEnabled(Features.SUPPORT_PPP_DRAFT);
        this.SUPPORT_DUAL_REC = Features.isEnabled(Features.SUPPORT_DUAL_REC);
        this.SUPPORT_SEARCH_PEOPLE_FACE_SCORE = PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE;
        this.SUPPORT_PET_CLUSTER = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_611 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2);
        this.CMH_TO_MP_MIGRATION = Features.isEnabled(Features.CMH_TO_MP_MIGRATION);
        this.mOsVersion = DeviceConfig.UNIT_TEST ? 29 : Build.VERSION.SDK_INT;
        this.mAddDataStamp = false;
        this.maxFileId = -1;
        this.minFileId = -1;
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mMediaTypeFilter = null;
        this.mMediaIdsFilter = null;
        this.mFromNow = -1;
        this.mDbStorageType = DbStorageType.All;
        this.mShowHidden = false;
        this.mTargetDb = (isGED || PocFeatures.isEnabled(PocFeatures.GmpAll)) ? TargetDbTypes.GMP : TargetDbTypes.SEC;
        this.mSortBy = 12;
        this.mMediaId = -1;
        this.mFileId = -1;
        this.mStoryCategoryType = -1;
        this.mStoryVisible = true;
        this.mMinFaceCount = 0;
        this.mExcludeCreationDay = -1;
        this.useDefaultOrder = true;
        this.mParentAlbumId = -1;
        this.mFaceGroupId = -1;
        setGroupTypes(GroupType.BURST);
        if (z7) {
            setImageOnly();
        }
        if (z3) {
            setShowHidden(true);
        }
        if (z) {
            setStorageTypes(DbStorageType.LocalOnly);
        }
    }

    public QueryParams(GroupType... groupTypeArr) {
        this.SUPPORT_MEMORY_DATA = Features.isEnabled(Features.SUPPORT_MEMORY_DATA) && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.SUPPORT_STORIES_DATA_SEP13 = PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_50 = PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
        this.STORY_ONE_UI_70 = PreferenceFeatures.OneUi7x.STORY_ONE_UI_70;
        this.VISUAL_SEARCH_ONEUI_30 = SdkConfig.atLeast(SdkConfig.GED.R);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61 = Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS);
        this.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION = Features.isEnabled(Features.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION);
        this.SUPPORT_RECENT_PRIMARY = Features.isEnabled(Features.SUPPORT_RECENT_BACKUP);
        this.SUPPORT_PPP_DELETE_TO_TRASH = Features.isEnabled(Features.SUPPORT_PPP_MENU);
        this.SUPPORT_PPP_DRAFT = Features.isEnabled(Features.SUPPORT_PPP_DRAFT);
        this.SUPPORT_DUAL_REC = Features.isEnabled(Features.SUPPORT_DUAL_REC);
        this.SUPPORT_SEARCH_PEOPLE_FACE_SCORE = PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE;
        this.SUPPORT_PET_CLUSTER = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_611 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP);
        this.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71 = Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2);
        this.CMH_TO_MP_MIGRATION = Features.isEnabled(Features.CMH_TO_MP_MIGRATION);
        this.mOsVersion = DeviceConfig.UNIT_TEST ? 29 : Build.VERSION.SDK_INT;
        this.mAddDataStamp = false;
        this.maxFileId = -1;
        this.minFileId = -1;
        this.mStartTime = -1;
        this.mEndTime = -1;
        this.mMediaTypeFilter = null;
        this.mMediaIdsFilter = null;
        this.mFromNow = -1;
        this.mDbStorageType = DbStorageType.All;
        this.mShowHidden = false;
        this.mTargetDb = (isGED || PocFeatures.isEnabled(PocFeatures.GmpAll)) ? TargetDbTypes.GMP : TargetDbTypes.SEC;
        this.mSortBy = 12;
        this.mMediaId = -1;
        this.mFileId = -1;
        this.mStoryCategoryType = -1;
        this.mStoryVisible = true;
        this.mMinFaceCount = 0;
        this.mExcludeCreationDay = -1;
        this.useDefaultOrder = true;
        this.mParentAlbumId = -1;
        this.mFaceGroupId = -1;
        this.mGroupTypes = groupTypeArr;
    }
}
