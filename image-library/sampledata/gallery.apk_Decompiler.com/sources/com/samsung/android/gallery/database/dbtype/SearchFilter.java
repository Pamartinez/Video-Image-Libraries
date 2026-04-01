package com.samsung.android.gallery.database.dbtype;

import android.content.Context;
import android.net.Uri;
import android.os.CancellationSignal;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFilter implements Cloneable {
    private static final boolean SUPPORT_NATIVE_AI_V2 = Features.isEnabled(Features.SUPPORT_NATIVE_AI_V2);
    private static final boolean SUPPORT_SEMANTIC_SEARCH = Features.isEnabled(Features.SUPPORT_SEMANTIC_SEARCH);
    private int mAddedCount;
    private CancellationSignal mCancellationSignal;
    private String mCountry = "";
    private String mCountryCode = "";
    private final ArrayList<String> mDividedSearchKeyword = new ArrayList<>();
    private String mEngKeyword;
    private String mExceptedIds;
    private String mExpandedDates;
    private ArrayList<String> mFacetTermsFields;
    private boolean mFilterOutCloudVideo;
    private boolean mFromInstantSearch;
    private String mFuzzyKeyword;
    private String mIncludeIds;
    private boolean mIs360Video = false;
    private boolean mIsAll = false;
    private boolean mIsAutoCompleteQueryEnabled;
    private boolean mIsBurstShot = false;
    private boolean mIsClusterEnable;
    private boolean mIsFilterEnabled = true;
    private boolean mIsForQueryOnDemand;
    private boolean mIsForceSync;
    private boolean mIsFullCluster;
    private boolean mIsFuzzySuggestKeywordQueryEnabled;
    private boolean mIsImageOnly = false;
    private boolean mIsLocalOnly;
    private boolean mIsPickMode;
    private boolean mIsSemanticQueryEnabled;
    private boolean mIsSingleTaken = false;
    private boolean mIsVideoOnly = false;
    private String mKeyboardLanguage;
    private final KeywordComparator mKeywordComparator;
    private int mLimit;
    private String mLocationKey;
    private String[] mMainEntityInfo;
    private final ArrayList<SearchFilter> mMajorFilter = new ArrayList<>();
    private Integer mMaxFacetTermsSize;
    private String mMediaType;
    private String mOrder;
    private String mOriginEngKeyword;
    private String mPdcInfo;
    private String mPdcToken;
    private long[] mPeriod;
    private String mRawKeyword;
    private final ArrayList<Integer> mRecordingModes = new ArrayList<>();
    private boolean mRequestFacetOnly;
    private final ArrayList<String> mSearchKeyword = new ArrayList<>();
    private final ArrayList<Integer> mSefFileType = new ArrayList<>();
    private final SearchSelectedFilter mSelectedFilter;
    private final ArrayList<SearchFilter> mSubFilter = new ArrayList<>();
    private String mSuggestedKeywordFeature;
    private boolean mSupportTagKeyword = Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE_TAG_SEARCH);
    private boolean mSupportTimeline = true;
    private String mTerm;
    private boolean mUseIdOrder;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public int mAddedCount;
        /* access modifiers changed from: private */
        public CancellationSignal mCancellationSignal;
        /* access modifiers changed from: private */
        public String[] mCountryInfo;
        /* access modifiers changed from: private */
        public String mExpandedDates;
        /* access modifiers changed from: private */
        public ArrayList<String> mFacetTermsFields;
        /* access modifiers changed from: private */
        public boolean mFilterOutCloudVideo;
        /* access modifiers changed from: private */
        public boolean mFromInstantSearch;
        /* access modifiers changed from: private */
        public String mFuzzyKeyword;
        /* access modifiers changed from: private */
        public String mIncludeIds;
        /* access modifiers changed from: private */
        public boolean mIsClusterEnable;
        /* access modifiers changed from: private */
        public boolean mIsFilterEnabled = true;
        /* access modifiers changed from: private */
        public boolean mIsForceSync;
        /* access modifiers changed from: private */
        public boolean mIsFullCluster;
        /* access modifiers changed from: private */
        public boolean mIsLocalOnly;
        /* access modifiers changed from: private */
        public boolean mIsPickMode;
        /* access modifiers changed from: private */
        public String mKeyword;
        /* access modifiers changed from: private */
        public int mLimit;
        /* access modifiers changed from: private */
        public String mLocationKey;
        /* access modifiers changed from: private */
        public String[] mMainEntityInfo;
        /* access modifiers changed from: private */
        public Integer mMaxFacetTermsSize;
        /* access modifiers changed from: private */
        public String mMediaType;
        /* access modifiers changed from: private */
        public String mOrder;
        /* access modifiers changed from: private */
        public String mOrgEngKeyword;
        /* access modifiers changed from: private */
        public long[] mPeriod = {0, 0};
        /* access modifiers changed from: private */
        public boolean mRequestFacetOnly;
        /* access modifiers changed from: private */
        public String mSelectedFilter;
        /* access modifiers changed from: private */
        public ArrayList<ShotMode> mShotModes = new ArrayList<>();
        /* access modifiers changed from: private */
        public boolean mSupportTimeline = true;
        /* access modifiers changed from: private */
        public String mTerm = "key_word";
        /* access modifiers changed from: private */
        public Uri mUri;

        public Builder(String str) {
            this.mKeyword = str;
        }

        public SearchFilter build(Context context) {
            return new SearchFilter(context, this);
        }

        public Builder countryInfo(String[] strArr) {
            this.mCountryInfo = strArr;
            return this;
        }

        public Builder filterOutCloudVideo() {
            this.mFilterOutCloudVideo = true;
            return this;
        }

        public Builder fromInstantSearch(boolean z) {
            this.mFromInstantSearch = z;
            return this;
        }

        public Builder keyword(String str) {
            this.mKeyword = str;
            return this;
        }

        public Builder locationKey(String str) {
            this.mLocationKey = str;
            return this;
        }

        public Builder mediaType(String str) {
            this.mMediaType = str;
            return this;
        }

        public Builder order(String str) {
            this.mOrder = str;
            return this;
        }

        public Builder orgEngKeyword(String str) {
            this.mOrgEngKeyword = str;
            return this;
        }

        public Builder period(long[] jArr) {
            if (jArr != null) {
                this.mPeriod = jArr;
            }
            return this;
        }

        public Builder requestFacetOnly() {
            this.mRequestFacetOnly = true;
            return this;
        }

        public Builder selectedFilter(String str) {
            this.mSelectedFilter = str;
            return this;
        }

        public Builder setAddedCount(int i2) {
            this.mAddedCount = i2;
            return this;
        }

        public Builder setCancellationSignal(CancellationSignal cancellationSignal) {
            this.mCancellationSignal = cancellationSignal;
            return this;
        }

        public Builder setClusterEnable(boolean z) {
            this.mIsClusterEnable = z;
            return this;
        }

        public Builder setExpandedDates(String str) {
            this.mExpandedDates = str;
            return this;
        }

        public Builder setFacetTermsFields(ArrayList<String> arrayList) {
            this.mFacetTermsFields = arrayList;
            return this;
        }

        public Builder setFilterEnable(boolean z) {
            this.mIsFilterEnabled = z;
            return this;
        }

        public Builder setForceSync(boolean z) {
            this.mIsForceSync = z;
            return this;
        }

        public Builder setFullCluster(boolean z) {
            this.mIsFullCluster = z;
            return this;
        }

        public Builder setFuzzyKeyword(String str) {
            this.mFuzzyKeyword = str;
            return this;
        }

        public Builder setLimit(int i2) {
            this.mLimit = Math.min(i2, 40000);
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            this.mIsLocalOnly = z;
            return this;
        }

        public Builder setMainEntityInfo(String[] strArr) {
            this.mMainEntityInfo = strArr;
            return this;
        }

        public Builder setMaxFacetTermsSize(int i2) {
            this.mMaxFacetTermsSize = Integer.valueOf(i2);
            return this;
        }

        public Builder setPickMode(boolean z) {
            this.mIsPickMode = z;
            setFilterEnable(!z);
            return this;
        }

        public Builder setSupportTimeline(boolean z) {
            this.mSupportTimeline = z;
            return this;
        }

        public Builder shotMode(ArrayList<ShotMode> arrayList) {
            this.mShotModes = arrayList;
            return this;
        }

        public Builder term(String str) {
            this.mTerm = str;
            return this;
        }

        public Builder withUri(Uri uri) {
            this.mUri = uri;
            return this;
        }
    }

    public SearchFilter(Context context, Builder builder) {
        KeywordComparator keywordComparator;
        this.mTerm = builder.mTerm;
        this.mSelectedFilter = new SearchSelectedFilter(builder.mSelectedFilter);
        this.mFuzzyKeyword = builder.mFuzzyKeyword;
        initKeyword(context, builder.mKeyword, builder.mFuzzyKeyword, builder.mTerm);
        initShotModes(builder.mShotModes);
        initCountryInfo(builder.mCountryInfo);
        updateSelectedFilterIfNeeded();
        this.mOrder = builder.mOrder;
        this.mPeriod = builder.mPeriod;
        this.mLocationKey = builder.mLocationKey;
        this.mMediaType = builder.mMediaType;
        this.mOriginEngKeyword = builder.mOrgEngKeyword;
        this.mFilterOutCloudVideo = builder.mFilterOutCloudVideo;
        this.mIsPickMode = builder.mIsPickMode;
        this.mIsLocalOnly = builder.mIsLocalOnly;
        this.mIsFilterEnabled = builder.mIsFilterEnabled;
        this.mIsClusterEnable = builder.mIsClusterEnable;
        this.mIsFullCluster = builder.mIsFullCluster;
        this.mMainEntityInfo = builder.mMainEntityInfo;
        this.mCancellationSignal = builder.mCancellationSignal;
        this.mExpandedDates = builder.mExpandedDates;
        this.mIncludeIds = builder.mIncludeIds;
        this.mAddedCount = builder.mAddedCount;
        this.mMaxFacetTermsSize = builder.mMaxFacetTermsSize;
        this.mFacetTermsFields = builder.mFacetTermsFields;
        this.mIsSemanticQueryEnabled = isSemanticQuerySupported(builder.mUri);
        this.mIsForceSync = builder.mIsForceSync;
        this.mLimit = builder.mLimit;
        this.mSupportTimeline = builder.mSupportTimeline;
        this.mRequestFacetOnly = builder.mRequestFacetOnly;
        this.mFromInstantSearch = builder.mFromInstantSearch;
        if (context != null) {
            keywordComparator = new KeywordComparator(context.getResources());
        } else {
            keywordComparator = null;
        }
        this.mKeywordComparator = keywordComparator;
        this.mKeyboardLanguage = DeviceInfo.getKeyboardLanguage();
    }

    private boolean checkVideoSefType(ShotMode shotMode) {
        if (shotMode.contains(3088) || shotMode.contains(3312)) {
            return true;
        }
        return false;
    }

    private long getTime(int i2) {
        TimeUtil timeUtil = new TimeUtil();
        if (i2 == 0) {
            return timeUtil.today();
        }
        if (i2 == 1) {
            return timeUtil.startOf7DaysAgo();
        }
        if (i2 == 2) {
            return timeUtil.startOfMonthsAgo(1);
        }
        if (i2 == 3) {
            return timeUtil.startOfPastMonths(6);
        }
        if (i2 == 4) {
            return timeUtil.startOfPastMonths(12);
        }
        if (i2 != 5) {
            return 0;
        }
        return timeUtil.startOf2DaysAgo();
    }

    private void initCountryInfo(String[] strArr) {
        if (strArr != null) {
            this.mCountry = strArr[0];
            this.mCountryCode = strArr[1];
        }
    }

    private void initKeyword(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (IntelligentSearchIndexField.CREATURE_TERM.contains(str3)) {
            this.mRawKeyword = String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str));
            return;
        }
        if (!TextUtils.isEmpty(str2) && getLastKeyword() == null) {
            str = str2;
        }
        this.mRawKeyword = str;
        String translateToEng = translateToEng(context, str2);
        this.mEngKeyword = translateToEng;
        if (this.mRawKeyword.equals(translateToEng)) {
            this.mEngKeyword = null;
        }
    }

    private void initShotModes(ArrayList<ShotMode> arrayList) {
        Iterator<ShotMode> it = arrayList.iterator();
        while (it.hasNext()) {
            ShotMode next = it.next();
            String str = next.type;
            str.getClass();
            char c5 = 65535;
            switch (str.hashCode()) {
                case -502677702:
                    if (str.equals("Contents")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 100313435:
                    if (str.equals("image")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 112202875:
                    if (str.equals("video")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 119089129:
                    if (str.equals("360_video")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case 410607289:
                    if (str.equals("burst_shot")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 481952495:
                    if (str.equals("Single Taken")) {
                        c5 = 5;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    this.mIsAll = true;
                    return;
                case 1:
                    this.mIsImageOnly = true;
                    return;
                case 2:
                    this.mIsVideoOnly = true;
                    return;
                case 3:
                    this.mIs360Video = true;
                    break;
                case 4:
                    this.mIsBurstShot = true;
                    break;
                case 5:
                    this.mIsSingleTaken = true;
                    break;
                default:
                    if (!next.isImage() && !checkVideoSefType(next)) {
                        this.mRecordingModes.addAll(next.getRecordingModesForSearch());
                        break;
                    } else {
                        this.mSefFileType.addAll(next.getSefTypesForSearch());
                        break;
                    }
            }
        }
    }

    private boolean isSemanticQuerySupported(Uri uri) {
        if (!SUPPORT_SEMANTIC_SEARCH) {
            return false;
        }
        if ((uri == null || !uri.getBooleanQueryParameter("use_semantic_search", false)) && !"key_word".equals(this.mTerm) && !this.mSelectedFilter.hasKeyword()) {
            return false;
        }
        return true;
    }

    private String translateToEng(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return TranslationManager.getInstance().getEngString(context, str);
    }

    private void updateSelectedFilterIfNeeded() {
        if (!TextUtils.isEmpty(this.mFuzzyKeyword) && !TextUtils.isEmpty(getLastKeyword())) {
            this.mSelectedFilter.replaceLastKeyword(this.mFuzzyKeyword);
        }
    }

    public void addDividedSearchKeyword(String str) {
        this.mDividedSearchKeyword.add(str);
    }

    public void addMajorFilter(SearchFilter searchFilter) {
        this.mMajorFilter.add(searchFilter);
    }

    public void addSearchKeyword(String str) {
        this.mSearchKeyword.add(str);
    }

    public void addSubFilter(SearchFilter searchFilter) {
        this.mSubFilter.add(searchFilter);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e("SearchFilter", "clone failed e=" + e.getMessage());
            return this;
        }
    }

    public SearchFilter createFilter(Context context, String str) {
        return new Builder(str).orgEngKeyword(translateToEng(context, this.mRawKeyword)).mediaType(this.mMediaType).period(this.mPeriod).order(this.mOrder).shotMode(ShotModeList.getInstance().findByStringKeyword(str, true)).countryInfo(new String[]{this.mCountry, this.mCountryCode}).build(context);
    }

    public boolean filterOutCloudVideo() {
        return this.mFilterOutCloudVideo;
    }

    public boolean fromInstantSearch() {
        return this.mFromInstantSearch;
    }

    public int getAddedCount() {
        return this.mAddedCount;
    }

    public CancellationSignal getCancellationSignal() {
        return this.mCancellationSignal;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public Collection<String> getCreatureKeys() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if ("recommended_id".equals(this.mTerm)) {
            linkedHashSet.add(this.mRawKeyword);
        }
        linkedHashSet.addAll(this.mSelectedFilter.getCreatureKeys());
        return linkedHashSet;
    }

    public ArrayList<String> getDividedSearchKeyword() {
        return this.mDividedSearchKeyword;
    }

    public String getEngKeyword() {
        return this.mEngKeyword;
    }

    public String getExceptedIds() {
        return this.mExceptedIds;
    }

    public String getExpandedDates() {
        return this.mExpandedDates;
    }

    public ArrayList<String> getFacetTermsFields() {
        return this.mFacetTermsFields;
    }

    public String getFilterMediaType() {
        if ("1".equals(this.mMediaType)) {
            return MediaFilterType.IMAGE_ONLY.toString();
        }
        if ("3".equals(this.mMediaType)) {
            return MediaFilterType.VIDEO_ONLY.toString();
        }
        return MediaFilterType.ALL.toString();
    }

    public Collection<String> getFilterSelections() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.mSelectedFilter.getFilterSelections());
        return linkedHashSet;
    }

    public long getFromKeywordTime() {
        int i2;
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator != null) {
            i2 = keywordComparator.equalsTimeKeyword(this.mRawKeyword);
        } else {
            i2 = -1;
        }
        return getTime(i2);
    }

    public long getFromTime() {
        return this.mPeriod[0];
    }

    public String getFuzzyAnalysisKeyword() {
        if (getLastKeyword() != null) {
            return getLastKeyword();
        }
        return getRawKeyword();
    }

    public String getIncludeIds() {
        return this.mIncludeIds;
    }

    public String getKeyboardLanguage() {
        return this.mKeyboardLanguage;
    }

    public String getLastKeyword() {
        return this.mSelectedFilter.getLastKeyword();
    }

    public int getLimit() {
        int i2 = this.mLimit;
        if (i2 == 0) {
            return 40000;
        }
        return i2;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public String[] getMainEntityInfo() {
        return this.mMainEntityInfo;
    }

    public ArrayList<SearchFilter> getMajorFilterList() {
        return this.mMajorFilter;
    }

    public Integer getMaxFacetTermsSize() {
        return this.mMaxFacetTermsSize;
    }

    public String getMediaType() {
        return this.mMediaType;
    }

    public String getOrder() {
        return this.mOrder;
    }

    public String getOriginEngKeyword() {
        return this.mOriginEngKeyword;
    }

    public String getPdcToken() {
        return this.mPdcToken;
    }

    public String getRawKeyword() {
        return this.mRawKeyword;
    }

    public ArrayList<Integer> getRecordingModes() {
        return this.mRecordingModes;
    }

    public ArrayList<String> getSearchKeyword() {
        return this.mSearchKeyword;
    }

    public ArrayList<Integer> getSefFileType() {
        return this.mSefFileType;
    }

    public String getSelectedFilter() {
        return this.mSelectedFilter.getContent();
    }

    public ArrayList<SearchFilter> getSubFilterList() {
        return this.mSubFilter;
    }

    public String getSuggestedKeywordFeature() {
        return this.mSuggestedKeywordFeature;
    }

    public String getTerm() {
        return this.mTerm;
    }

    public long getToTime() {
        return this.mPeriod[1];
    }

    public boolean hasHashTag() {
        if (TextUtils.isEmpty(this.mRawKeyword) || !this.mRawKeyword.startsWith("#")) {
            return false;
        }
        return true;
    }

    public boolean hasLastKeyword() {
        if (getLastKeyword() != null) {
            return true;
        }
        return false;
    }

    public boolean is360Video() {
        return this.mIs360Video;
    }

    public boolean isAll() {
        return this.mIsAll;
    }

    public boolean isAutoCompleteQueryEnabled() {
        return this.mIsAutoCompleteQueryEnabled;
    }

    public boolean isBlurry() {
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator == null || !keywordComparator.equalsBlurryKeyword(this.mRawKeyword)) {
            return false;
        }
        return true;
    }

    public boolean isBurstShot() {
        return this.mIsBurstShot;
    }

    public boolean isClusterEnabled() {
        return this.mIsClusterEnable;
    }

    public boolean isCountryKeyword(String str) {
        String str2 = this.mCountry;
        if (str2 == null || !str2.contains(str) || TextUtils.isEmpty(this.mCountryCode)) {
            return false;
        }
        return true;
    }

    public boolean isDislike() {
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator == null || !keywordComparator.containDislikeKeyword(this.mRawKeyword)) {
            return false;
        }
        return true;
    }

    public boolean isExpressions() {
        if (isHappy() || isNeutral() || isDislike() || isSurprise()) {
            return true;
        }
        return false;
    }

    public boolean isFavorite() {
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator == null || !keywordComparator.equalsFavoriteKeyword(this.mRawKeyword)) {
            return false;
        }
        return true;
    }

    public boolean isFilterEnabled() {
        return this.mIsFilterEnabled;
    }

    public boolean isForQueryOnDemand() {
        return this.mIsForQueryOnDemand;
    }

    public boolean isForceSync() {
        return this.mIsForceSync;
    }

    public boolean isFullClusterEnabled() {
        return this.mIsFullCluster;
    }

    public boolean isFuzzySuggestKeywordQueryEnabled() {
        return this.mIsFuzzySuggestKeywordQueryEnabled;
    }

    public boolean isHappy() {
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator == null || !keywordComparator.containHappyKeyword(this.mRawKeyword)) {
            return false;
        }
        return true;
    }

    public boolean isImageOnly() {
        return this.mIsImageOnly;
    }

    public boolean isKeywordSuggestion() {
        if (this.mIsAutoCompleteQueryEnabled || this.mIsFuzzySuggestKeywordQueryEnabled) {
            return true;
        }
        return false;
    }

    public boolean isLlmEnabled() {
        return SUPPORT_NATIVE_AI_V2;
    }

    public boolean isLocalOnly() {
        return this.mIsLocalOnly;
    }

    public boolean isNeutral() {
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator == null || !keywordComparator.containNeutralKeyword(this.mRawKeyword)) {
            return false;
        }
        return true;
    }

    public boolean isPickMode() {
        return this.mIsPickMode;
    }

    public boolean isRequestFacetOnly() {
        return this.mRequestFacetOnly;
    }

    public boolean isSemanticQueryEnabled() {
        return this.mIsSemanticQueryEnabled;
    }

    public boolean isSingleTaken() {
        return this.mIsSingleTaken;
    }

    public boolean isSurprise() {
        KeywordComparator keywordComparator = this.mKeywordComparator;
        if (keywordComparator == null || !keywordComparator.containSurpriseKeyword(this.mRawKeyword)) {
            return false;
        }
        return true;
    }

    public boolean isTagKeyword() {
        String str;
        if (!this.mSupportTagKeyword || (str = this.mRawKeyword) == null) {
            return false;
        }
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            if (TextUtils.equals("#", str)) {
                return true;
            }
            return false;
        } else if (str.startsWith("#")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isVideoOnly() {
        return this.mIsVideoOnly;
    }

    public void replaceKeyword(Context context, String str) {
        if (getLastKeyword() != null) {
            this.mSelectedFilter.replaceLastKeyword(str);
        } else {
            initKeyword(context, str, (String) null, "key_word");
        }
    }

    public void replaceTerm(String str) {
        this.mTerm = str;
    }

    public void semanticQueryOff() {
        this.mIsSemanticQueryEnabled = false;
    }

    public void setAutoCompleteQueryEnabled(boolean z) {
        this.mIsAutoCompleteQueryEnabled = z;
    }

    public void setCancellationSignal(CancellationSignal cancellationSignal) {
        this.mCancellationSignal = cancellationSignal;
    }

    public void setExceptedIds(String str) {
        this.mExceptedIds = str;
    }

    public void setFuzzySuggestKeywordQueryEnabled(boolean z) {
        this.mIsFuzzySuggestKeywordQueryEnabled = z;
    }

    public void setIncludeIds(String str) {
        this.mIncludeIds = str;
    }

    public void setMainEntityInfo(String[] strArr) {
        this.mMainEntityInfo = strArr;
    }

    public void setPdcInfo(String str) {
        this.mPdcInfo = str;
    }

    public void setPdcToken(String str) {
        this.mPdcToken = str;
    }

    public void setPeriod(long[] jArr) {
        this.mPeriod = jArr;
    }

    public void setQueryOnDemand(boolean z) {
        this.mIsForQueryOnDemand = z;
    }

    public void setRawKeyword(String str) {
        this.mRawKeyword = str;
    }

    public void setSuggestedKeywordFeature(String str) {
        this.mSuggestedKeywordFeature = str;
    }

    public void setSupportTimeline(boolean z) {
        this.mSupportTimeline = z;
    }

    public boolean supportTimeline() {
        return this.mSupportTimeline;
    }

    public String toDebugString() {
        char c5;
        char c6;
        char c8;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        StringBuilder sb2 = new StringBuilder("SearchFilter{");
        if (this.mIsFilterEnabled) {
            c5 = 'F';
        } else {
            c5 = 'f';
        }
        sb2.append(c5);
        if (this.mIsClusterEnable) {
            c6 = 'C';
        } else {
            c6 = 'c';
        }
        sb2.append(c6);
        if (this.mIsSemanticQueryEnabled) {
            c8 = 'S';
        } else {
            c8 = 's';
        }
        sb2.append(c8);
        sb2.append(",term=");
        sb2.append(this.mTerm);
        String str16 = "";
        if (this.mFuzzyKeyword != null) {
            str = ",fuzzyKeyword=" + this.mFuzzyKeyword;
        } else {
            str = str16;
        }
        sb2.append(str);
        if (this.mOrder != null) {
            str2 = ",order=" + this.mOrder;
        } else {
            str2 = str16;
        }
        sb2.append(str2);
        if (this.mPeriod != null) {
            str3 = ",period=" + StringCompat.toString(this.mPeriod);
        } else {
            str3 = str16;
        }
        sb2.append(str3);
        if (this.mMediaType != null) {
            str4 = ",mediaType=" + this.mMediaType;
        } else {
            str4 = str16;
        }
        sb2.append(str4);
        if (this.mOriginEngKeyword != null) {
            str5 = ",engKeyword=" + this.mOriginEngKeyword;
        } else {
            str5 = str16;
        }
        sb2.append(str5);
        if (this.mIsPickMode) {
            str6 = ",pickerMode";
        } else {
            str6 = str16;
        }
        sb2.append(str6);
        if (this.mIsFullCluster) {
            str7 = ",fullCluster";
        } else {
            str7 = str16;
        }
        sb2.append(str7);
        if (this.mIsLocalOnly) {
            str8 = ",localOnly";
        } else {
            str8 = str16;
        }
        sb2.append(str8);
        if (this.mFilterOutCloudVideo) {
            str9 = ",excludeCloudVideo";
        } else {
            str9 = str16;
        }
        sb2.append(str9);
        if (this.mIsForceSync) {
            str10 = ",forceSync";
        } else {
            str10 = str16;
        }
        sb2.append(str10);
        sb2.append(",addedCount=");
        sb2.append(this.mAddedCount);
        if (this.mMainEntityInfo != null) {
            str11 = "mainEntity=" + StringCompat.toString((T[]) this.mMainEntityInfo);
        } else {
            str11 = str16;
        }
        sb2.append(str11);
        if (this.mExpandedDates != null) {
            str12 = ",expandedDates=" + this.mExpandedDates;
        } else {
            str12 = str16;
        }
        sb2.append(str12);
        if (this.mIncludeIds != null) {
            str13 = ",includeIds=" + this.mIncludeIds;
        } else {
            str13 = str16;
        }
        sb2.append(str13);
        if (this.mMaxFacetTermsSize != null) {
            str14 = ",maxFacetTermSize=" + this.mMaxFacetTermsSize;
        } else {
            str14 = str16;
        }
        sb2.append(str14);
        if (this.mFacetTermsFields != null) {
            str15 = ",facetTermsFields=" + StringCompat.toString(this.mFacetTermsFields);
        } else {
            str15 = str16;
        }
        sb2.append(str15);
        if (this.mKeyboardLanguage != null) {
            str16 = ",keyboard=" + this.mKeyboardLanguage;
        }
        sb2.append(str16);
        sb2.append(", mLimit : ");
        return C0086a.l(sb2, this.mLimit, "}");
    }

    public boolean useIdOrder() {
        return this.mUseIdOrder;
    }
}
