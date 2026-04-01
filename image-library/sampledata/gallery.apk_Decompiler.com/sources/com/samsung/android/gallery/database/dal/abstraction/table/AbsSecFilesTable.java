package com.samsung.android.gallery.database.dal.abstraction.table;

import A.a;
import B5.e;
import N2.j;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.IGroupMediaQuery;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.functional.g;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsSecFilesTable extends DbTable implements SecFilesTable {
    private static final boolean SUPPORT_SINGLE_SHOT_ONLY = Features.isEnabled(Features.SUPPORT_SINGLE_SHOT_ONLY);
    protected IGroupMediaQuery mGroupMediaQueryInterface;

    public AbsSecFilesTable(QueryParams queryParams) {
        super(queryParams);
    }

    private void addProjectionBestImage() {
        this.mQueryBuilder.addProjection(this.mGroupMediaQueryInterface.getColumnGroupBestImage(), "__GroupMediaBest");
    }

    private String composeShotModeProjection(String str, String str2, String str3) {
        StringBuilder q = C0086a.q(" WHEN ", str, " in ", str2, " THEN ");
        q.append(str3);
        return q.toString();
    }

    private int getRepresentDualRecSefFileTypeValue() {
        if (SdkConfig.FirstApiLevel.LESS_THAN_U) {
            return 3088;
        }
        return 3312;
    }

    private String getUnionSubQueryForSefShotMode(boolean z) {
        StringJoiner stringJoiner = new StringJoiner(" union all ", "(", ")");
        String filterVolumeConditionForShotMode = getFilterVolumeConditionForShotMode();
        stringJoiner.add(subQueryForSefType(z, filterVolumeConditionForShotMode));
        stringJoiner.add(subQueryForRecordingMode(filterVolumeConditionForShotMode, convertToArray(RecordingMode.listOfQuery())));
        stringJoiner.add(subQueryFor360Video(filterVolumeConditionForShotMode));
        if (QueryParams.SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH) {
            stringJoiner.add(subQueryForNondestructiveRecordingMode(filterVolumeConditionForShotMode, convertToArray(RecordingMode.listOfNds())));
        }
        return stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$filterAlbumIDs$0(int[] iArr) {
        filterAlbumIDs((Collection<Integer>) (Collection) IntStream.of(iArr).boxed().collect(Collectors.toList()));
    }

    private String recordingModeProjection() {
        String str;
        String composeShotModeProjection = composeShotModeProjection("A.recording_mode", convertToArray(RecordingMode.listOfSuperSlowMoNds()), String.valueOf(7));
        String composeShotModeProjection2 = composeShotModeProjection("A.recording_mode", convertToArray(RecordingMode.listOfSlowMoNds()), String.valueOf(1));
        String composeShotModeProjection3 = composeShotModeProjection("A.recording_mode", convertToArray(RecordingMode.listOfHyperLapse()), String.valueOf(5));
        boolean z = PreferenceFeatures.OneUi6x.IS_ONE_UI_60;
        String str2 = "";
        if (z) {
            str = composeShotModeProjection("A.recording_mode", convertToArray(RecordingMode.listOfPro()), String.valueOf(16));
        } else {
            str = str2;
        }
        if (z) {
            str2 = composeShotModeProjection("A.recording_mode", convertToArray(List.of(24, 28)), String.valueOf(24));
        }
        return j.f(C0086a.q("CASE ", composeShotModeProjection, composeShotModeProjection2, composeShotModeProjection3, str), str2, composeShotModeProjection("A.recording_mode", "(0)", "null"), " ELSE A.recording_mode END");
    }

    private String sefFileTypeProjection(boolean z) {
        String str;
        String composeShotModeProjection = composeShotModeProjection("A.sef_file_type", convertToArray(ShotModeType.listOfLiveFocus()), String.valueOf(2736));
        if (this.mParams.SUPPORT_DUAL_REC) {
            str = composeShotModeProjection("A.sef_file_type", convertToArray(ShotModeType.listOfDualRec()), String.valueOf(getRepresentDualRecSefFileTypeValue()));
        } else {
            str = "";
        }
        String composeShotModeProjection2 = composeShotModeProjection("A.sef_file_type IN (0, -1) or A.sef_file_type", convertToArray(ShotModeType.listOfSpecialVideo()), "null");
        if (z) {
            return j.d("CASE", composeShotModeProjection, str, composeShotModeProjection2, " ELSE A.sef_file_type END");
        }
        StringBuilder q = C0086a.q("CASE", composeShotModeProjection("A.sef_file_type", convertToArray(ShotModeType.listOfSelfie(true)), String.valueOf(2304)), composeShotModeProjection, str, composeShotModeProjection2);
        q.append(" ELSE A.sef_file_type END");
        return q.toString();
    }

    private String subQueryFor360Video(String str) {
        return C0212a.m("select _id from files where media_type in (3) ", str, " and is_360_video = 1");
    }

    private String subQueryForNondestructiveRecordingMode(String str, String str2) {
        return j.d("select F._id from files AS F, nondestruction as N where media_type in (3) ", str, " and F.recording_mode IN ", str2, " and F.original_file_hash not null and F.original_file_hash = N.hash");
    }

    private String subQueryForRecordingMode(String str, String str2) {
        return C0212a.n("select _id from files where media_type in (3) ", str, " and recording_mode IN ", str2);
    }

    private String subQueryForSefType(boolean z, String str) {
        StringBuilder k = j.k("select _id from files where media_type in (1,3) ", str, " and sef_file_type IN ");
        k.append(convertToArray(ShotModeType.listOfImage(z)));
        return k.toString();
    }

    private boolean supportGotoUrl() {
        return Features.isEnabled(Features.SUPPORT_GO_TO_URL);
    }

    private boolean supportSefFileTypes() {
        return SdkConfig.atLeast(SdkConfig.SEM.R_MR1);
    }

    public void addDataStamp() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("(" + getDataStampQuery() + ")", "__dataStamp");
    }

    public void addGroupMediaCountProjection() {
        addGroupMediaCountProjection(this.mQueryBuilder);
    }

    public void addGroupMediaSizeSumProjection(QueryBuilder queryBuilder) {
        queryBuilder.addProjection(this.mGroupMediaQueryInterface.getGroupMediaSizeSumTable(), "total_size");
    }

    public void addOrderByDate() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + getColumnDateTaken() + " DESC, A._id DESC");
    }

    public void addParentAlbumId(int i2) {
        this.mQueryBuilder.addProjection(String.valueOf(i2), "__parentAlbumId");
    }

    public void addProjectionCloud() {
        this.mQueryBuilder.addProjection("A.is_cloud", "__storageType");
        this.mQueryBuilder.addProjection(getColumnNameCloudId(), "__cloudId");
        addProjectionFileStatus();
        this.mQueryBuilder.addProjection("A.cloud_server_id", "__cloudServerId");
        this.mQueryBuilder.addProjection("A.cloud_server_path", "__cloudServerPath");
        this.mQueryBuilder.addProjection("A.cloud_cached_path", "__cloudCachedPath");
        this.mQueryBuilder.addProjection("A.cloud_thumb_path", "__cloudTP");
        this.mQueryBuilder.addProjection("A.cloud_original_size", "__cloudOriginalSize");
    }

    public void addProjectionDay() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("strftime('%Y-%m-%d', (A." + getColumnDateTaken() + " / 1000)+" + DbTable.TIMEZONE_OFFSET_SECOND + ", 'unixepoch')", "__day");
    }

    public void addProjectionDynamicViewInfo() {
        if (Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW) && this.mParams.mDynamicViewingInfo) {
            this.mQueryBuilder.addProjection("A.dynamic_view_info", "__dynamicViewInfo");
        }
    }

    public void addProjectionForMaxId() {
        this.mQueryBuilder.addProjection("max(A._id)");
    }

    public abstract void addProjectionGroupId();

    public void addProjectionHideAlbum() {
        this.mQueryBuilder.addProjection("max(A.is_hide)", "__ishide");
    }

    public void addProjectionHour() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(" (A." + getColumnDateTaken() + " / 1000+" + DbTable.TIMEZONE_OFFSET_SECOND + ")/" + (PreferenceCache.YearTimeSlot.getInt() * 60), "__hour");
    }

    public void addProjectionIdOrder(String str) {
        this.mQueryBuilder.addProjection(buildSortProjection(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX), "_id"), "__idOrder");
    }

    public abstract void addProjectionOrientationTag();

    public void addProjectionSefFileTypes() {
        this.mQueryBuilder.addProjection("A.sef_file_types", "__sefFileTypes");
    }

    public void addProjectionUrl() {
        this.mQueryBuilder.addProjection("A.captured_app", "__capturedAPP");
        this.mQueryBuilder.addProjection("A.captured_url", "__capturedURL");
    }

    public abstract void addProjectionVolumeName();

    public void addProjectionYear() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("ifnull(strftime('%Y', (A." + getColumnDateTaken() + " / 1000)+" + DbTable.TIMEZONE_OFFSET_SECOND + ", 'unixepoch'),'9999')", "__year");
    }

    public void addRevitalizeProjection() {
        if (SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
            this.mQueryBuilder.addProjection("A.revitalized_type", "revitalized_type");
            if (Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER)) {
                this.mQueryBuilder.addProjection("A.revitalized_path", "revitalized_path");
            }
        }
    }

    public void addSingleCount() {
        this.mQueryBuilder.addProjection("1", "__count");
    }

    public void appendVolumeNameForFaces() {
        if (this.IS_GTE_Q && !this.mParams.mReplaceVolumeName) {
            setWhere(getWhere().replace("'external_primary'", "'external_primary','external_primary'"));
        }
    }

    public String buildSortProjection(String[] strArr, String str) {
        SqliteCaseBuilder sqliteCaseBuilder = new SqliteCaseBuilder();
        Arrays.stream(strArr).forEach(new g(sqliteCaseBuilder, str, new AtomicInteger(1), 8));
        return sqliteCaseBuilder.elseThen(str).build();
    }

    public abstract IGroupMediaQuery createGroupMediaQueryImpl();

    public void filter360Video() {
        this.mQueryBuilder.andCondition(get360VideoQuery());
    }

    public void filter3dCaptureShotMode() {
        this.mQueryBuilder.andCondition("(A.sef_file_type IN (3568,3584) OR A.recording_mode = 29)");
    }

    public void filterAlbumID(int i2) {
        C0212a.w("A._id in (", C0086a.i(i2, "select _id from files where bucket_id="), ")", this.mQueryBuilder);
    }

    public void filterAlbumIDWithFavorites(int i2) {
        C0212a.w("A._id in (", C0086a.i(i2, "select _id from files where is_favorite=1 or bucket_id="), ")", this.mQueryBuilder);
    }

    public void filterAlbumIDs(int[] iArr) {
        Optional.ofNullable(iArr).ifPresent(new g6.g(26, this));
    }

    public void filterAlbumIDsWithFavorites(int[] iArr) {
        C0212a.w("A._id in (", "select _id from files where is_favorite=1 or bucket_id in " + CursorHelper.joinIds((Collection) IntStream.of(iArr).boxed().collect(Collectors.toList())), ")", this.mQueryBuilder);
    }

    public void filterAlbumName(String str, boolean z) {
        String str2;
        QueryBuilder queryBuilder = this.mQueryBuilder;
        if (z) {
            str2 = likeWhereArgs("A.bucket_display_name", str);
        } else {
            str2 = likeWild("A.bucket_display_name", str);
        }
        queryBuilder.andCondition(str2);
    }

    public void filterAlbumsName(String str) {
        C0212a.w("A.bucket_display_name in (", str, ")", this.mQueryBuilder);
    }

    public void filterAllScreenShot() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mParams.getScreenShotFolderId());
        if (this.mParams.getScreenShotFolderId() == FileUtils.getBucketId(StorageInfo.getDefault().screenShot)) {
            str = "";
        } else {
            str = GlobalPostProcInternalPPInterface.SPLIT_REGEX + FileUtils.getBucketId(StorageInfo.getDefault().screenShot);
        }
        sb2.append(str);
        String sb3 = sb2.toString();
        StringJoiner stringJoiner = new StringJoiner("','", "('", "')");
        ScreenShotFilterType.sSubCategoryInfo.keySet().forEach(new e(stringJoiner, 2));
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder k = j.k("(A.bucket_id in (", sb3, ") or A.sef_file_types like '%");
        k.append(SefInfo.SCREEN_CAPTURE_INFO.keyCode);
        k.append("%'or A._id in (select sec_media_id from scene where scene_name in ");
        k.append(stringJoiner);
        k.append("))");
        queryBuilder.andCondition(k.toString());
    }

    public void filterAndGroupBurstShot() {
        if (this.IS_GTE_R) {
            this.mQueryBuilder.andCondition(getBurstShotOnlyQueryIgnoreBestPolicy());
        } else {
            this.mQueryBuilder.andCondition(getBurstShotOnlyQuery());
        }
        this.mQueryBuilder.limit("1");
        this.mQueryBuilder.replaceProjectionByAliasWithValue("burst_shot", "__subCategory");
    }

    public void filterAndGroupSefShotMode(boolean z) {
        this.mQueryBuilder.replaceProjectionByAlias(sefFileTypeProjection(z), "__sefFileType");
        this.mQueryBuilder.replaceProjectionByAlias(recordingModeProjection(), "__recordingMode");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id in " + getUnionSubQueryForSefShotMode(z));
        this.mQueryBuilder.groupBy("__sefFileType,__recordingMode,__subCategory");
        if (!this.IS_GTE_R) {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.having("Max(printf('%020d%020d', A." + getColumnDateTaken() + ", A._id))");
        }
    }

    public void filterAndGroupSelfieShotMode() {
        this.mQueryBuilder.replaceProjectionByAlias("2304", "__sefFileType");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id in (select _id from files where sef_file_type IN " + convertToArray(ShotModeType.listOfSelfie(false)) + ")");
        this.mQueryBuilder.groupBy("__sefFileType,__subCategory");
        this.mQueryBuilder.replaceProjectionByAliasWithValue("selfie", "__subCategory");
    }

    public void filterAndGroupSingleTaken() {
        this.mQueryBuilder.andCondition(getSingleTakeOnlyQuery());
        this.mQueryBuilder.limit("1");
        this.mQueryBuilder.replaceProjectionByAliasWithValue("Single Taken", "__subCategory");
    }

    public void filterApvShotMode() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.recording_mode in " + convertToArray(RecordingMode.listOfApv()));
    }

    public void filterBlurryImages() {
        this.mQueryBuilder.andCondition(getFilterBlurryImages());
    }

    public void filterBurstShotOnly() {
        this.mQueryBuilder.andCondition(getBurstShotOnlyQuery());
        addGroupMediaCountProjection(this.mQueryBuilder);
    }

    public void filterByFromTime(String str, boolean z) {
        StringBuilder sb2;
        String str2;
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder sb3 = new StringBuilder();
        if (z) {
            sb2 = new StringBuilder("(A.");
            sb2.append(getColumnDateTaken());
            str2 = ">=";
        } else {
            sb2 = new StringBuilder("(A.");
            sb2.append(getColumnDateTaken());
            str2 = "<=";
        }
        sb2.append(str2);
        sb3.append(sb2.toString());
        sb3.append(str);
        sb3.append(" )");
        queryBuilder.andCondition(sb3.toString());
    }

    public void filterByMap() {
        this.mQueryBuilder.andCondition("A.latitude != 0");
        this.mQueryBuilder.andCondition("A.longitude != 0");
    }

    public void filterByRecentlyAdded() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("(A.date_added IS NOT NULL AND A.date_added>=" + (new TimeUtil().startOfDaysAgo(2) / 1000) + " )");
    }

    public void filterCloud() {
        this.mQueryBuilder.andCondition("A.is_cloud!=1");
    }

    public void filterCloudOnlyOnTransfer() {
        this.mQueryBuilder.andCondition("A._id not in (select _id from files where is_cloud=2 and cloud_thumb_path is null)");
    }

    public void filterCreationTime(long j2, long j3) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("( " + getColumnDateTaken() + " > " + j2 + " AND " + getColumnDateTaken() + " <= " + j3 + ")");
    }

    public void filterCreationTimeForPdcFiles(long j2, long j3) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder j8 = j.j(j2, "( datetaken >= ", " AND datetaken <= ");
        j8.append(j3);
        j8.append(")");
        queryBuilder.andCondition(j8.toString());
    }

    public void filterDataLike(String str) {
        C0212a.w("A._data LIKE '", str, "'", this.mQueryBuilder);
    }

    public void filterFilePathOnly(String str) {
        this.mQueryBuilder.clearSelection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._data=" + str);
    }

    public void filterGalleryMedia() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.andCondition("A.media_type in (1,3)");
            return;
        }
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A." + getColumnDateTaken() + " >-62167219200000 and A.media_type in (1,3)");
    }

    public void filterGenerated() {
        this.mQueryBuilder.andCondition("A.sef_file_types like '%3473%'");
    }

    public void filterGif() {
        this.mQueryBuilder.andCondition("A._id in (select _id from files where mime_type like 'image/gif') ");
    }

    public void filterGpsRange(String str) {
        if (str != null) {
            String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("A.latitude is not null AND A.latitude >= " + split[0] + " AND A.latitude <=" + split[1] + " AND A.longitude is not null AND A.longitude >= " + split[2] + " AND A.longitude <=" + split[3]);
        }
    }

    public void filterGroupMediaBest(boolean z) {
        if (!SUPPORT_SINGLE_SHOT_ONLY) {
            this.mQueryBuilder.andCondition(j.d("(", this.mGroupMediaQueryInterface.getGroupBestMedia(), "or ", this.mGroupMediaQueryInterface.getGroupFirstMedia(getColumnDateTaken()), ")"));
            if (z) {
                addGroupMediaCountProjection(this.mQueryBuilder);
                return;
            }
            return;
        }
        removeBurstShotProjections();
    }

    public void filterGroupMediaHasBest() {
        this.mQueryBuilder.andCondition(this.mGroupMediaQueryInterface.getGroupBestMedia());
    }

    public void filterGroupMediaId(int i2, long j2) {
        if (this.IS_GTE_Q) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition(getColumnGroupMediaType() + "=" + i2);
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.andCondition(getColumnGroupMediaId() + "=" + j2);
            return;
        }
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        queryBuilder3.andCondition("A." + getColumnGroupMediaId() + "=" + j2);
    }

    public void filterGroupMediaIds(int i2, String str) {
        if (this.IS_GTE_Q) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition(getColumnGroupMediaType() + "=" + i2);
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.andCondition(getColumnGroupMediaId() + " IN (" + str + ")");
            return;
        }
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        queryBuilder3.andCondition("A." + getColumnGroupMediaId() + " IN (" + str + ")");
    }

    public void filterHidden() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in " + getNonHideBucketIds() + " group by bucket_id)");
    }

    public void filterId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id=" + j2);
    }

    public void filterIds(String str) {
        C0212a.w("A._id IN (", str, ")", this.mQueryBuilder);
    }

    public void filterImage() {
        this.mQueryBuilder.andCondition(getFilterImage());
    }

    public void filterIsFavorite() {
        this.mQueryBuilder.andCondition(getFilterIsFavorite());
    }

    public void filterLogShotMode() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.recording_mode in " + convertToArray(RecordingMode.listOfLogVideo()));
    }

    public void filterMediaID(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getColumnNameMediaId() + "=" + j2);
    }

    public void filterMediaIDs(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getColumnNameMediaId() + " IN (" + str + ")");
    }

    public void filterMinResolution(int i2, int i7) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder h5 = a.h(i7, i7, "(A.width >= ", " AND A.height >= ", " AND A.width * A.height >= ");
        h5.append(i2);
        h5.append(")");
        queryBuilder.andCondition(h5.toString());
    }

    public void filterModifiedTime(long j2, long j3) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder j8 = j.j(j2, "( date_modified >= ", " AND date_modified <= ");
        j8.append(j3);
        j8.append(")");
        queryBuilder.andCondition(j8.toString());
    }

    public abstract void filterMountedVolume();

    public void filterNonDestructiveRecording(String str) {
        ArrayList<Integer> arrayList;
        int i2;
        if ("slow_motion".equals(str)) {
            arrayList = RecordingMode.listOfSlowMo();
        } else {
            arrayList = RecordingMode.listOfSuperSlowMo();
        }
        String convertToArray = convertToArray(arrayList);
        if ("slow_motion".equals(str)) {
            i2 = 101;
        } else {
            i2 = 111;
        }
        StringJoiner stringJoiner = new StringJoiner(" union all ", "(", ")");
        stringJoiner.add(subQueryForRecordingMode(getFilterVolumeConditionForShotMode(), convertToArray));
        stringJoiner.add(subQueryForNondestructiveRecordingMode(getFilterVolumeConditionForShotMode(), C0212a.j(i2, "(", ")")));
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id in " + stringJoiner.toString());
    }

    public void filterPartitionIds(long j2, long j3) {
        String str;
        String f = a.f("A._id > ", j2);
        if (j3 > 0) {
            str = "(A." + getColumnDateTaken() + " > " + j3 + " OR (A." + getColumnDateTaken() + " = " + j3 + " AND " + f + "))";
        } else {
            str = C0212a.m("(", f, ")");
        }
        this.mQueryBuilder.andCondition(str);
    }

    public void filterPartitionIdsByMediaId(long j2, long j3) {
        this.mQueryBuilder.andCondition("(A." + getColumnDateTaken() + " > " + j3 + " OR (A." + getColumnDateTaken() + " = " + j3 + " AND " + ("A._id > (SELECT _id from files where " + getCommonColumnNameMediaId() + " = " + j2 + ")") + "))");
    }

    public void filterRawShotMode() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id in (select _id from files where mime_type IN " + convertStringKeysToArray(MimeType.DNG_MIME_TYPE_MAP.keySet()) + " OR mime_type IN " + convertStringKeysToArray(MimeType.RAW_MIME_TYPE_MAP.keySet()) + ")");
    }

    public void filterRecentlyEdited() {
        this.mQueryBuilder.andCondition("original_file_hash in (select hash from nondestruction)");
    }

    public void filterRecordingMode(ArrayList<Integer> arrayList) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.recording_mode in " + convertToArray(arrayList));
    }

    public void filterSefFileType(ArrayList<Integer> arrayList) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.sef_file_type in " + convertToArray(arrayList));
    }

    public void filterSingleTakeOnly() {
        this.mQueryBuilder.andCondition(getSingleTakeOnlyQuery());
        addGroupMediaCountProjection(this.mQueryBuilder);
    }

    public void filterVideo() {
        this.mQueryBuilder.andCondition(getFilterVideo());
    }

    public String get360VideoQuery() {
        return "(A.is_360_video=1)";
    }

    public String getAlbumOrderByQuery(int i2) {
        StringBuilder sb2 = new StringBuilder();
        int sortBy = SortByType.getSortBy(i2);
        if (sortBy == 20) {
            sb2.append("A.date_modified ");
        } else if (sortBy != 30) {
            if (sortBy != 60) {
                if (sortBy != 70) {
                    sb2.append("A." + getColumnDateTaken() + " ");
                } else if (this.mParams.SUPPORT_RECENT_PRIMARY) {
                    return "A.recent_primary DESC, A._ID DESC";
                } else {
                    return "A._ID DESC";
                }
            } else if (SortByType.getOrderBy(i2) == 2) {
                return "A._ID DESC";
            } else {
                return "A._ID ASC";
            }
        } else if (Features.isEnabled(Features.SUPPORT_NATURAL_SORT)) {
            sb2.append("A._display_name COLLATE LOCALIZED_NATURAL ");
        } else {
            sb2.append("A._display_name ");
        }
        if (SortByType.getOrderBy(i2) != 2) {
            sb2.append("ASC, A._ID ASC");
        } else {
            sb2.append("DESC, A._ID DESC");
        }
        return sb2.toString();
    }

    public String getBurstShotOnlyQuery() {
        return this.mGroupMediaQueryInterface.getBurstShotOnlyQuery(getColumnDateTaken());
    }

    public String getBurstShotOnlyQueryIgnoreBestPolicy() {
        return this.mGroupMediaQueryInterface.getBurstShotOnlyQueryIgnoreBestPolicy(getColumnDateTaken());
    }

    public abstract String getColumnDateTaken();

    public String getColumnGroupMediaId() {
        return this.mGroupMediaQueryInterface.getColumnGroupMediaId();
    }

    public String getColumnGroupMediaType() {
        return this.mGroupMediaQueryInterface.getColumnGroupType();
    }

    public String getColumnNameCloudId() {
        return "A._id";
    }

    public abstract String getColumnNameMediaId();

    public abstract String getCommonColumnNameMediaId();

    public String getDataStampQuery() {
        if (this.IS_GTE_T) {
            if (this.mParams.cloudSyncOn) {
                return "select count(*)||'_'|| max(_id)||'_'||(select ifnull(max(timestamp),0) from files where is_cloud=2) ||'_'||(select generation_modified from local_metadata limit 1)||'_'||(select count(_id) from files where sef_file_type in (2928, 2947)) from files where (is_trashed is null or is_trashed=0)";
            }
            return "select count(*)||'_'|| max(_id)||'_'||(select generation_modified from local_metadata limit 1)||'_'||(select count(_id) from files where sef_file_type in (2928, 2947)) from files where (is_trashed is null or is_trashed=0)";
        } else if (this.IS_GTE_R) {
            if (this.mParams.cloudSyncOn) {
                return "select (total(_size)+total(cloud_original_size))||'_'||(select ifnull(max(timestamp),0) from files where is_cloud=2)||'_'||(select max(_id) from files)||'_'||max(generation_modified) from (select _size,cloud_original_size,generation_modified from files where 1)";
            }
            return "select (total(_size)+total(cloud_original_size))||'_'||(select max(_id) from files)||'_'||max(generation_modified) from (select _size,cloud_original_size,generation_modified from files where 1)";
        } else if (this.IS_GTE_Q) {
            return C0212a.l("select (total(_size)+total(cloud_original_size))||'_'||(select max(_id) from files)||'_'||max(date_touched) from ", "(select _size,cloud_original_size,date_touched    from files  where 1 order by " + getColumnDateTaken() + " desc, _id desc limit 0,10000)");
        } else {
            return "select (total(_size)+total(cloud_original_size))||'_'||(select max(_id) from files)||'_'||total(" + getColumnDateTaken() + ") from " + ("(select _size,cloud_original_size," + getColumnDateTaken() + "    from files  where 1  order by " + getColumnDateTaken() + " desc, _id desc limit 0,10000)");
        }
    }

    public String getFilterBlurryImages() {
        return "(A.image_quality=0)";
    }

    public String getFilterImage() {
        return "(A.media_type = 1)";
    }

    public String getFilterIsFavorite() {
        return "(A.is_favorite=1)";
    }

    public String getFilterVideo() {
        return "(A.media_type = 3)";
    }

    public abstract String getFilterVolumeConditionForShotMode();

    public String getHavingBySort(int i2) {
        String str;
        String str2;
        int sortBy = SortByType.getSortBy(i2);
        if (sortBy == 30) {
            str = "A._display_name";
        } else if (sortBy == 20) {
            str = "A.date_modified";
        } else {
            str = "A." + getColumnDateTaken();
        }
        int orderBy = SortByType.getOrderBy(i2);
        StringBuilder sb2 = new StringBuilder();
        if (orderBy == 2) {
            str2 = "max";
        } else {
            str2 = "min";
        }
        return C0212a.q(sb2, str2, "(printf('%020d%020d',", str, ",A._id))");
    }

    public String getNonHideBucketIds() {
        return CursorHelper.joinIds(BucketUtils.values());
    }

    public String getSingleTakeOnlyQuery() {
        return this.mGroupMediaQueryInterface.getSingleTakeOnlyQuery(getColumnDateTaken());
    }

    public String getSingleTakenQuery() {
        return "A.burst_group_id in (select burst_group_id from files where group_type = 3 group by burst_group_id having count(burst_group_id) > 1)";
    }

    public String getTableNameRaw() {
        return "files";
    }

    public void groupBy(String str, String str2) {
        this.mQueryBuilder.groupBy(str);
        if (!TextUtils.isEmpty(str2)) {
            this.mQueryBuilder.having(str2);
        }
    }

    public void groupByAlbum() {
        this.mQueryBuilder.groupBy("bucket_id");
    }

    public void groupForAlbum(boolean z) {
        String str;
        this.mQueryBuilder.groupBy("bucket_id");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder sb2 = new StringBuilder();
        if (z) {
            str = "";
        } else {
            str = "max(coalesce(is_hide,0)) < 1 and ";
        }
        sb2.append(str);
        sb2.append("max(printf('%020d%020d',");
        sb2.append(getColumnDateTaken());
        sb2.append(",_id))");
        queryBuilder.having(sb2.toString());
        this.mQueryBuilder.replaceProjectionByAlias("count(*)", "__count");
        this.mQueryBuilder.replaceProjectionByAlias("A.bucket_display_name", "__Title");
    }

    public void includeIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.firstOrCondition("A._id IN (" + str + ")");
    }

    public void onConstruct() {
        this.mGroupMediaQueryInterface = createGroupMediaQueryImpl();
    }

    public void orderBy(String str) {
        this.mQueryBuilder.clearOrderBy();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + getColumnDateTaken() + " " + str + ", A._ID " + str);
    }

    public void orderByAlbumPictures(int i2) {
        this.mQueryBuilder.clearOrderBy();
        this.mQueryBuilder.addOrderBy(getAlbumOrderByQuery(i2));
    }

    public void orderByIds() {
        this.mQueryBuilder.clearOrderBy();
        this.mQueryBuilder.addOrderBy("__idOrder ASC");
    }

    public void orderByMap() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + getColumnDateTaken() + " DESC");
    }

    public void orderByModifiedTime() {
        this.mQueryBuilder.clearOrderBy();
        this.mQueryBuilder.addOrderBy("A.date_modified DESC");
    }

    public void orderByName() {
        this.mQueryBuilder.addOrderBy("A._display_name");
    }

    public void orderByRecent() {
        this.mQueryBuilder.clearOrderBy();
        if (this.mParams.SUPPORT_RECENT_PRIMARY) {
            this.mQueryBuilder.addOrderBy("A.recent_primary DESC, A._id DESC");
        } else {
            this.mQueryBuilder.addOrderBy("A._id DESC");
        }
    }

    public void orderForNewAlbumLabel() {
        this.mQueryBuilder.addOrderBy("date_modified DESC, _id DESC");
        this.mQueryBuilder.addProjection("A.bucket_id", "__albumID");
    }

    public void removeBurstShotProjections() {
        this.mQueryBuilder.removeProjectionByAlias("__GroupMediaID");
        this.mQueryBuilder.removeProjectionByAlias("__GroupMediaBest");
        this.mQueryBuilder.removeProjectionByAlias("__group_type");
    }

    public void replaceDateTakenFrom(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A." + getColumnDateTaken() + " < " + j2);
    }

    public void resetProjectionForAlbum() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection(getColumnNameMediaId(), "__fileMediaId");
        this.mQueryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("A." + getColumnDateTaken(), "__dateTaken");
        this.mQueryBuilder.addProjection("A.bucket_display_name", "__albumName");
        this.mQueryBuilder.addProjection("A.bucket_id", "__albumID");
        this.mQueryBuilder.addProjection("A.date_modified", "__dateModified");
        this.mQueryBuilder.addProjection("A._display_name", "__Title");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.duration", "__fileDuration");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.sef_file_type", "__sefFileType");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
        this.mQueryBuilder.addProjection("A.is_drm", "__isDrm");
        this.mQueryBuilder.addProjection("A.mime_type", "__mimeType");
        this.mQueryBuilder.addProjection("A._size", "__size");
        if (this.mParams.SUPPORT_PPP_DRAFT) {
            this.mQueryBuilder.addProjection("A._data_draft", "__draftPath");
        }
        this.mQueryBuilder.addProjection("A.recording_mode", "__recordingMode");
        addProjectionCloud();
        addProjectionVolumeName();
        addProjectionOrientationTag();
    }

    public void resetProjectionForAutoComplete() {
        this.mQueryBuilder.clearProjection();
        setDefaultProjection();
        this.mQueryBuilder.removeProjectionByAlias("__albumID");
        this.mQueryBuilder.removeProjectionByAlias("__cloudOriginalSize");
    }

    public void resetProjectionForStoryAppBar() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection(getColumnNameMediaId(), "__fileMediaId");
        this.mQueryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
    }

    public void setDefaultCondition() {
        filterIsPending();
        filterFileStatus();
        if (this.mParams.isShowCloudOnly()) {
            filterCloudOnly(true);
        } else if (this.mParams.isShowLocalOnly()) {
            filterLocalOnly(true);
        } else {
            filterCloudOnlyOnTransfer();
        }
        if (!this.mParams.isShowHidden()) {
            filterHidden();
        }
        filterGalleryMedia(this.mParams.getMediaTypeFilter());
        filterMountedVolume();
        QueryParams queryParams = this.mParams;
        int i2 = queryParams.minResolution;
        if (i2 > 0) {
            filterMinResolution(i2, queryParams.minSize);
        }
        if (!TextUtils.isEmpty(this.mParams.mGpsRange)) {
            filterGpsRange(this.mParams.mGpsRange);
        }
    }

    public void setDefaultOrder() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + getColumnDateTaken() + " DESC, A._id DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection(getColumnNameMediaId(), "__fileMediaId");
        this.mQueryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("A." + getColumnDateTaken(), "__dateTaken");
        this.mQueryBuilder.addProjection("A.date_added", "__dateAdded");
        this.mQueryBuilder.addProjection("A.bucket_display_name", "__albumName");
        this.mQueryBuilder.addProjection("A.bucket_id", "__albumID");
        this.mQueryBuilder.addProjection("A.date_modified", "__dateModified");
        this.mQueryBuilder.addProjection("A._size", "__size");
        this.mQueryBuilder.addProjection("A.addr", "__Address");
        this.mQueryBuilder.addProjection("A.latitude", "__latitude");
        this.mQueryBuilder.addProjection("A.longitude", "__longitude");
        this.mQueryBuilder.addProjection("A._display_name", "__Title");
        this.mQueryBuilder.addProjection("A.mime_type", "__mimeType");
        this.mQueryBuilder.addProjection("A.duration", "__fileDuration");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.sef_file_type", "__sefFileType");
        this.mQueryBuilder.addProjection("A.sef_file_sub_type", "__sefFileSubType");
        this.mQueryBuilder.addProjection("A.recording_mode", "__recordingMode");
        this.mQueryBuilder.addProjection("A.recordingtype", "__recordingType");
        this.mQueryBuilder.addProjection("A.is_360_video", "__is360Video");
        this.mQueryBuilder.addProjection("A.is_hdr10_video", "__isHdr10Video");
        this.mQueryBuilder.addProjection("A.resolution", "__resolution");
        addProjectionBestImage();
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        this.mQueryBuilder.addProjection("A.is_favorite", "__isFavourite");
        this.mQueryBuilder.addProjection("A.is_drm", "__isDrm");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
        this.mQueryBuilder.addProjection("A.audio_codec_info", "__audioCodecInfo");
        this.mQueryBuilder.addProjection("A.video_codec_info", "__videoCodecInfo");
        addProjectionGroupId();
        if (supportGotoUrl()) {
            addProjectionUrl();
        }
        if (supportSefFileTypes()) {
            addProjectionSefFileTypes();
        }
        addProjectionCloud();
        addProjectionRelativePath();
        addProjectionDynamicViewInfo();
        addRevitalizeProjection();
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "A");
    }

    public void setIndex(String str) {
        this.mQueryBuilder.replaceTable(getTableNameRaw(), "A", str);
    }

    public void updateByQueryParams() {
        QueryParams queryParams = this.mParams;
        long j2 = queryParams.mExcludeCreationDay;
        if (j2 > -1) {
            long j3 = queryParams.excludeHourHighBound;
            if (j3 != 0) {
                long j8 = queryParams.excludeHourLowBound;
                if (j8 != 0) {
                    long j10 = (j8 * 3600000) + j2;
                    long j11 = Long.MAX_VALUE;
                    if (j3 != Long.MAX_VALUE) {
                        j11 = (j3 * 3600000) + j2;
                    }
                    QueryBuilder queryBuilder = this.mQueryBuilder;
                    queryBuilder.andCondition("(A." + getColumnDateTaken() + " not between " + j10 + " and " + j11 + ")");
                }
            }
        }
    }

    public void addGroupMediaCountProjection(QueryBuilder queryBuilder) {
        queryBuilder.addProjection(this.mGroupMediaQueryInterface.getGroupMediaCountTable(), "__count");
    }

    public void filterAlbumIDs(Collection<Integer> collection) {
        C0212a.w("A._id in (", "select _id from files where bucket_id in " + CursorHelper.joinIds(collection), ")", this.mQueryBuilder);
    }

    public void filterGalleryMedia(String str) {
        if (!"".equals(str)) {
            if (MediaFilterType.IMAGE_ONLY.toString().equals(str)) {
                this.mQueryBuilder.andCondition("(A.media_type = 1)");
            } else if (MediaFilterType.VIDEO_ONLY.toString().equals(str)) {
                this.mQueryBuilder.andCondition("(A.media_type = 3)");
            } else {
                filterGalleryMedia();
            }
        }
    }

    public void addProjectionFileStatus() {
    }

    public void addProjectionRelativePath() {
    }

    public void filterFileStatus() {
    }

    public void filterIsPending() {
    }
}
