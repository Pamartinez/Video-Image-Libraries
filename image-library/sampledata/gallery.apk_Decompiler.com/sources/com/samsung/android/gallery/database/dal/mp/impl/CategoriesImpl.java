package com.samsung.android.gallery.database.dal.mp.impl;

import C3.i;
import N2.j;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.table.MpFacesView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpSceneView;
import com.samsung.android.gallery.database.dal.mp.table.MpTagView;
import com.samsung.android.gallery.database.dal.mp.table.MpUserTagView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.type.ExpressionsType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ScreenShotHelper;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import q8.a;
import t4.C0517a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoriesImpl extends BaseImpl {
    public CategoriesImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private SecFilesTable addProjectionForNewVisualSearch(SecFilesTable secFilesTable, boolean z) {
        if (!this.mParams.VISUAL_SEARCH_ONEUI_30) {
            return secFilesTable;
        }
        secFilesTable.addProjectionForCursorCount(true);
        if (z) {
            secFilesTable.getQueryBuilder().addGroupBy("__subCategory");
        }
        if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            secFilesTable.addProjectionForMaxId();
        }
        return secFilesTable;
    }

    private boolean checkVideoSefType(ShotMode shotMode) {
        if (shotMode.contains(3088) || shotMode.contains(3312)) {
            return true;
        }
        return false;
    }

    private boolean existScreenshotAllItem() {
        Cursor rawQuery;
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder("select distinct(_id) from files where (bucket_id = ");
        sb2.append(FileUtils.getBucketId(ScreenShotHelper.getScreenshotFolder(this.mQueryExecutor.getContext())));
        sb2.append(" or sef_file_types like '%");
        try {
            rawQuery = this.mQueryExecutor.rawQuery(C0086a.l(sb2, SefInfo.SCREEN_CAPTURE_INFO.keyCode, "%') and media_type = 1 LIMIT 1"), "checkAnyScreenshotAllItem");
            Log.d("CategoriesImpl", "existScreenshotAllItem " + Logger.toString(rawQuery) + " +" + (System.currentTimeMillis() - currentTimeMillis));
            if (rawQuery == null || !rawQuery.moveToFirst()) {
                z = false;
            } else {
                z = true;
            }
            if (rawQuery == null) {
                return z;
            }
            rawQuery.close();
            return z;
        } catch (Exception e) {
            j.v("existScreenshotAllItem failed ", e, "CategoriesImpl");
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Query getBurstShotModeQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterAndGroupBurstShot();
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    private MpFacesView getExpressionFileView() {
        QueryParams queryParams = this.mParams;
        return MpFacesView.getExpressionFileView(queryParams, queryParams.getSubCategory());
    }

    private Query getExpressionsQuery(boolean z) {
        Query query = new Query(getExpressionsSubQuery(z, ExpressionsType.EXPRESSION_HAPPY));
        Query query2 = new Query(getExpressionsSubQuery(z, ExpressionsType.EXPRESSION_NEUTRAL));
        Query query3 = new Query(getExpressionsSubQuery(z, ExpressionsType.EXPRESSION_DISLIKE));
        Query query4 = new Query(getExpressionsSubQuery(z, ExpressionsType.EXPRESSION_SURPRISE));
        query.unionAll(query2);
        query.unionAll(query3);
        query.unionAll(query4);
        return query;
    }

    private Query getExpressionsSubQuery(boolean z, ExpressionsType expressionsType) {
        MpFacesView mpFacesView = new MpFacesView(this.mParams);
        if (z) {
            mpFacesView.resetProjectionForAutoComplete();
        }
        mpFacesView.setExpressionFilter(expressionsType);
        mpFacesView.groupBy("__subCategory");
        mpFacesView.getQueryBuilder().replaceProjectionByAliasWithValue(expressionsType.getSubCategory(), "__subCategory");
        mpFacesView.getQueryBuilder().replaceProjectionByAliasWithValue("Expressions", "__mainCategory");
        return mpFacesView.buildSelectQuery();
    }

    private Query getGifQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterGif();
        createShotModeFilesTable.limit(1);
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("GIF", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    private Query getMyTagQuery() {
        MpUserTagView mpUserTagView = new MpUserTagView(this.mParams);
        if (!this.mParams.mUngroupBurstShot) {
            mpUserTagView.filterBurstShotBestImage(false);
        }
        mpUserTagView.filterValidTagData();
        mpUserTagView.groupTagData();
        mpUserTagView.havingLatestMedia();
        if (!TextUtils.isEmpty(this.mParams.getNames())) {
            mpUserTagView.filterTagNames(this.mParams.getNames());
        }
        if (this.mParams.VISUAL_SEARCH_ONEUI_30) {
            mpUserTagView.addProjectionForCount();
            mpUserTagView.addOrderByCount();
        }
        return mpUserTagView.buildSelectQuery();
    }

    private MpUserTagView getMyTagView() {
        MpUserTagView mpUserTagView = new MpUserTagView(this.mParams);
        mpUserTagView.filterTagData(this.mParams.getSubCategory());
        mpUserTagView.filterBurstShotBestImage(true);
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            mpUserTagView.includeFileIds(this.mParams.getIncludeFileIds());
        }
        return mpUserTagView;
    }

    private MpTagView getSceneView() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.getQueryBuilder().replaceProjectionByAlias("A.smartcrop_rect_ratio", "__sceneRegion");
        if (!TextUtils.isEmpty(this.mParams.getSubCategory())) {
            mpSceneView.filterTagData(this.mParams.getSubCategory());
        }
        String excludeFileIds = this.mParams.getExcludeFileIds();
        if (excludeFileIds != null) {
            mpSceneView.excludeFileIds(excludeFileIds);
        }
        mpSceneView.filterBurstShotBestImage(true);
        mpSceneView.groupByFileId();
        mpSceneView.addOrderByDate();
        if (this.mParams.getLimitSize() > 0) {
            mpSceneView.limit(this.mParams.getLimitSize());
        }
        if (this.mParams.getOrder() != null) {
            mpSceneView.getQueryBuilder().clearOrderBy().addOrderBy(this.mParams.getOrder());
        }
        if (!TextUtils.isEmpty(this.mParams.mFileNamePrefixExclude)) {
            QueryBuilder queryBuilder = mpSceneView.getQueryBuilder();
            queryBuilder.andCondition("title not like '" + this.mParams.mFileNamePrefixExclude + "%'");
        }
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            mpSceneView.includeFileIds(this.mParams.getIncludeFileIds());
        }
        if (!TextUtils.isEmpty(this.mParams.getParentCategory())) {
            mpSceneView.filterParentScene(this.mParams.getParentCategory());
        }
        return mpSceneView;
    }

    private Query getScenesQuery() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.filterSceneOrSubScene();
        mpSceneView.filterRemainScenes();
        long fileId = this.mParams.getFileId();
        int i2 = (fileId > -1 ? 1 : (fileId == -1 ? 0 : -1));
        if (i2 == 0) {
            mpSceneView.filterBurstShotBestImage(false);
        }
        mpSceneView.groupTagData();
        if (i2 == 0) {
            mpSceneView.havingMinimumTagCount();
        } else {
            QueryBuilder queryBuilder = mpSceneView.getQueryBuilder();
            queryBuilder.andCondition("A._id = " + fileId);
            mpSceneView.havingMedia();
        }
        if (this.mParams.VISUAL_SEARCH_ONEUI_30) {
            mpSceneView.addProjectionForCount();
            mpSceneView.addOrderByCount();
        }
        return mpSceneView.buildSelectQuery();
    }

    private Query getSefShotModesQuery(boolean z, boolean z3) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterGalleryMedia();
        createShotModeFilesTable.filterAndGroupSefShotMode(z3);
        createShotModeFilesTable.getQueryBuilder().addProjection("CASE WHEN A.is_360_video = 1 or A.recordingtype in (6, 7) THEN '360_video' ELSE null END", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, false).buildSelectQuery();
    }

    private Query getSelfieShotModeQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterGalleryMedia();
        createShotModeFilesTable.filterAndGroupSelfieShotMode();
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, false).buildSelectQuery();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable getShotModeFilesTable(java.lang.String r5) {
        /*
            r4 = this;
            com.samsung.android.gallery.database.dal.mp.table.MpFilesTable r0 = new com.samsung.android.gallery.database.dal.mp.table.MpFilesTable
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r4.mParams
            r0.<init>(r1)
            r5.getClass()
            int r1 = r5.hashCode()
            r2 = 0
            r3 = -1
            switch(r1) {
                case -1997563968: goto L_0x009a;
                case -1419937693: goto L_0x008f;
                case -422395980: goto L_0x0084;
                case 70564: goto L_0x0079;
                case 112680: goto L_0x006e;
                case 100313435: goto L_0x0063;
                case 112202875: goto L_0x0058;
                case 119089129: goto L_0x004d;
                case 410607289: goto L_0x003f;
                case 481952495: goto L_0x0031;
                case 1121735576: goto L_0x0023;
                case 1126770652: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x00a4
        L_0x0015:
            java.lang.String r1 = "super_slow_mo"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x001f
            goto L_0x00a4
        L_0x001f:
            r3 = 11
            goto L_0x00a4
        L_0x0023:
            java.lang.String r1 = "3d_capture"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x002d
            goto L_0x00a4
        L_0x002d:
            r3 = 10
            goto L_0x00a4
        L_0x0031:
            java.lang.String r1 = "Single Taken"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x003b
            goto L_0x00a4
        L_0x003b:
            r3 = 9
            goto L_0x00a4
        L_0x003f:
            java.lang.String r1 = "burst_shot"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0049
            goto L_0x00a4
        L_0x0049:
            r3 = 8
            goto L_0x00a4
        L_0x004d:
            java.lang.String r1 = "360_video"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0056
            goto L_0x00a4
        L_0x0056:
            r3 = 7
            goto L_0x00a4
        L_0x0058:
            java.lang.String r1 = "video"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0061
            goto L_0x00a4
        L_0x0061:
            r3 = 6
            goto L_0x00a4
        L_0x0063:
            java.lang.String r1 = "image"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x006c
            goto L_0x00a4
        L_0x006c:
            r3 = 5
            goto L_0x00a4
        L_0x006e:
            java.lang.String r1 = "raw"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0077
            goto L_0x00a4
        L_0x0077:
            r3 = 4
            goto L_0x00a4
        L_0x0079:
            java.lang.String r1 = "GIF"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0082
            goto L_0x00a4
        L_0x0082:
            r3 = 3
            goto L_0x00a4
        L_0x0084:
            java.lang.String r1 = "slow_motion"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x008d
            goto L_0x00a4
        L_0x008d:
            r3 = 2
            goto L_0x00a4
        L_0x008f:
            java.lang.String r1 = "apv_video"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0098
            goto L_0x00a4
        L_0x0098:
            r3 = 1
            goto L_0x00a4
        L_0x009a:
            java.lang.String r1 = "log_video"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x00a3
            goto L_0x00a4
        L_0x00a3:
            r3 = r2
        L_0x00a4:
            switch(r3) {
                case 0: goto L_0x011a;
                case 1: goto L_0x0116;
                case 2: goto L_0x00ce;
                case 3: goto L_0x00ca;
                case 4: goto L_0x00c6;
                case 5: goto L_0x00c2;
                case 6: goto L_0x00bb;
                case 7: goto L_0x00b7;
                case 8: goto L_0x00b2;
                case 9: goto L_0x00ad;
                case 10: goto L_0x00a8;
                case 11: goto L_0x00ce;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            goto L_0x00d9
        L_0x00a8:
            r0.filter3dCaptureShotMode()
            goto L_0x011d
        L_0x00ad:
            r0.filterSingleTakeOnly()
            goto L_0x011d
        L_0x00b2:
            r0.filterBurstShotOnly()
            goto L_0x011d
        L_0x00b7:
            r0.filter360Video()
            goto L_0x011d
        L_0x00bb:
            r0.addSingleCount()
            r0.filterVideo()
            goto L_0x011d
        L_0x00c2:
            r0.filterImage()
            goto L_0x011d
        L_0x00c6:
            r0.filterRawShotMode()
            goto L_0x011d
        L_0x00ca:
            r0.filterGif()
            goto L_0x011d
        L_0x00ce:
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.Poc.SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH
            if (r1 == 0) goto L_0x00d9
            r0.filterVideo()
            r0.filterNonDestructiveRecording(r5)
            goto L_0x011d
        L_0x00d9:
            com.samsung.android.gallery.support.shotmode.ShotModeList r1 = com.samsung.android.gallery.support.shotmode.ShotModeList.getInstance()
            java.util.ArrayList r1 = r1.findByStringKeyword(r5, r2)
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x00f3
            java.lang.String r1 = "getShotModeFilesTable unexpected result : "
            java.lang.String r5 = r1.concat(r5)
            java.lang.String r1 = "CategoriesImpl"
            com.samsung.android.gallery.support.utils.Log.w(r1, r5)
            goto L_0x011d
        L_0x00f3:
            java.lang.Object r5 = r1.get(r2)
            com.samsung.android.gallery.support.shotmode.ShotMode r5 = (com.samsung.android.gallery.support.shotmode.ShotMode) r5
            boolean r1 = r5.isImage()
            if (r1 != 0) goto L_0x010e
            boolean r1 = r4.checkVideoSefType(r5)
            if (r1 == 0) goto L_0x0106
            goto L_0x010e
        L_0x0106:
            java.util.ArrayList r5 = r5.getRecordingModesForSearch()
            r0.filterRecordingMode(r5)
            goto L_0x011d
        L_0x010e:
            java.util.ArrayList r5 = r5.getSefTypesForSearch()
            r0.filterSefFileType(r5)
            goto L_0x011d
        L_0x0116:
            r0.filterApvShotMode()
            goto L_0x011d
        L_0x011a:
            r0.filterLogShotMode()
        L_0x011d:
            r4.setFilterIds(r0)
            r4.setIncludeIds(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl.getShotModeFilesTable(java.lang.String):com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable");
    }

    private Query getSingleTakeTakenShotQuery() {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        createShotModeFilesTable.filterAndGroupSingleTaken();
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    private Query getVideoQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterVideo();
        createShotModeFilesTable.limit(1);
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("video", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        SecFilesTable addProjectionForNewVisualSearch = addProjectionForNewVisualSearch(createShotModeFilesTable, false);
        if (this.mParams.VISUAL_SEARCH_ONEUI_30) {
            addProjectionForNewVisualSearch.getQueryBuilder().groupBy("__subCategory");
            addProjectionForNewVisualSearch.getQueryBuilder().having("__count > 0");
        }
        return addProjectionForNewVisualSearch.buildSelectQuery();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getScreenShotCursor$11(String str) {
        if ("__subCategory".equals(str)) {
            return "cap_all";
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object[] lambda$getScreenShotCursor$12(int i2) {
        return new Object[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getScreenShotCursor$13(String str) {
        if ("__subCategory".equals(str)) {
            return "cap_ask";
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object[] lambda$getScreenShotCursor$14(int i2) {
        return new Object[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$0(Cursor[] cursorArr, Query query) {
        cursorArr[0] = getCursor(query, "shot mode video");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$1(Cursor[] cursorArr, Query query) {
        cursorArr[1] = getCursor(query, "shot mode burst");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$2(Cursor[] cursorArr, Query query) {
        cursorArr[2] = getCursor(query, "shot mode singleTaken");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$3(Cursor[] cursorArr, Query query) {
        cursorArr[3] = getCursor(query, "shot mode gif");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$4(Cursor[] cursorArr, Query query) {
        cursorArr[4] = getCursor(query, "shot mode sef");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$5(Cursor[] cursorArr, Query query) {
        cursorArr[5] = getCursor(query, "shot mode sefSelfie");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$6(Cursor[] cursorArr, Query query) {
        cursorArr[6] = getCursor(query, "shot mode raw");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$7(Cursor[] cursorArr, Query query) {
        cursorArr[7] = getCursor(query, "shot mode log video");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$8(Cursor[] cursorArr, Query query) {
        cursorArr[8] = getCursor(query, "shot mode 3d capture");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getShotModeCursor$9(Cursor[] cursorArr, Query query) {
        cursorArr[9] = getCursor(query, "shot mode apv");
    }

    private void setFilterIds(SecFilesTable secFilesTable) {
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            secFilesTable.filterIds(this.mParams.getFileIds());
        }
    }

    private void setIncludeIds(SecFilesTable secFilesTable) {
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            secFilesTable.includeIds(this.mParams.getIncludeFileIds());
        }
    }

    /* access modifiers changed from: private */
    public boolean useShotModeThumbnail() {
        if (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE) || PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU) {
            return true;
        }
        return false;
    }

    public final MpFilesTable createShotModeFilesTable() {
        return new MpFilesTable(this.mParams) {
            public void setDefaultProjection() {
                this.mQueryBuilder.addProjection("A._id", "__absID");
                this.mQueryBuilder.addProjection(getColumnNameMediaId(), "__fileMediaId");
                this.mQueryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
                this.mQueryBuilder.addProjection("A.mime_type", "__mimeType");
                this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
                this.mQueryBuilder.addProjection("A.sef_file_type", "__sefFileType");
                this.mQueryBuilder.addProjection("A.sef_file_sub_type", "__sefFileSubType");
                this.mQueryBuilder.addProjection("A.recording_mode", "__recordingMode");
                this.mQueryBuilder.addProjection("A.recordingtype", "__recordingType");
                this.mQueryBuilder.addProjection("A.is_360_video", "__is360Video");
                if (CategoriesImpl.this.useShotModeThumbnail()) {
                    this.mQueryBuilder.addProjection("A.width", "__width");
                    this.mQueryBuilder.addProjection("A.height", "__height");
                    this.mQueryBuilder.addProjection("A.orientation", "__orientation");
                }
                if (PreferenceFeatures.OneUi6x.IS_ONE_UI_60) {
                    this.mQueryBuilder.addProjection("A.sef_file_types", "__sefFileTypes");
                }
                this.mQueryBuilder.addProjection("A.is_cloud", "__storageType");
            }

            public String tag() {
                return "ShotModeFilesTable";
            }
        };
    }

    public Query get3dCaptureShotModeQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterGalleryMedia();
        createShotModeFilesTable.filter3dCaptureShotMode();
        createShotModeFilesTable.getQueryBuilder().addProjection("CASE WHEN A.sef_file_type IN (3568,3584) or  A.recording_mode = 29 THEN '3d_capture' ELSE null END", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    public Query getAllDocumentsQuery() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.filterAllDocuments();
        mpSceneView.filterBurstShotBestImage(false);
        mpSceneView.groupTagData();
        mpSceneView.havingMaxScore();
        if (this.mParams.VISUAL_SEARCH_ONEUI_30) {
            mpSceneView.addProjectionForCount();
            mpSceneView.addOrderByCount();
        }
        return mpSceneView.buildSelectQuery();
    }

    public Query getApvShotModeQuery() {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        createShotModeFilesTable.filterGalleryMedia();
        createShotModeFilesTable.filterApvShotMode();
        createShotModeFilesTable.getQueryBuilder().addProjection("CASE WHEN A.recording_mode in (" + TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, RecordingMode.listOfApv()) + ") THEN 'apv_video' ELSE null END", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    public Cursor getBlurredFileCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterImage();
        mpFilesTable.filterBlurryImages();
        mpFilesTable.removeBurstShotProjections();
        return getCursor(mpFilesTable.buildSelectQuery(), "blurred files");
    }

    public Cursor getDocumentCursor() {
        if (this.mParams.getFileId() != -1) {
            return getDocumentCursor(this.mParams.getFileId());
        }
        Query documentQuery = getDocumentQuery();
        documentQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Documents", "__mainCategory");
        return getCursor(documentQuery, "Document");
    }

    public Cursor getDocumentFileCursor() {
        String subCategory = this.mParams.getSubCategory();
        MpTagView documentFileView = getDocumentFileView(subCategory);
        documentFileView.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            documentFileView.filterFileIds(this.mParams.getFileIds());
        }
        if (this.mParams.mIsForOnDemandQuery) {
            documentFileView.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            Query buildSelectQuery = documentFileView.buildSelectQuery();
            return getFileIdsConcatCursor(buildSelectQuery, "Document : " + subCategory);
        }
        Query buildSelectQuery2 = documentFileView.buildSelectQuery();
        return getCursor(buildSelectQuery2, "Document : " + subCategory);
    }

    public Cursor getDocumentFileDateCursor() {
        String subCategory = this.mParams.getSubCategory();
        MpTagView documentFileView = getDocumentFileView(subCategory);
        documentFileView.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        documentFileView.distinctIdCountForTimelineDateData();
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(documentFileView.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "Document date : " + subCategory);
    }

    public Cursor getDocumentFileRealRatioCursor() {
        MpTagView documentFileView = getDocumentFileView(this.mParams.getSubCategory());
        documentFileView.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            documentFileView.filterFileIds(this.mParams.getFileIds());
        }
        return ImplHelper.getRealRatioMergedCursor((BaseView) documentFileView, this.mQueryExecutor);
    }

    public MpTagView getDocumentFileView(String str) {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.havingMedia();
        mpSceneView.filterSceneOrSubScene();
        str.getClass();
        if (str.equals("Other Documents")) {
            mpSceneView.filterOtherDocuments();
        } else if (!str.equals("Documents")) {
            mpSceneView.filterDocuments();
            mpSceneView.filterTagData(str);
        } else {
            mpSceneView.filterAllDocuments();
        }
        mpSceneView.filterBurstShotBestImage(true);
        mpSceneView.groupByFileId();
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            mpSceneView.includeFileIds(this.mParams.getIncludeFileIds());
        }
        return mpSceneView;
    }

    public Query getDocumentQuery() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.filterSceneOrSubScene();
        mpSceneView.filterDocuments();
        mpSceneView.filterBurstShotBestImage(false);
        mpSceneView.groupTagData();
        mpSceneView.havingLatestMedia();
        if (this.mParams.VISUAL_SEARCH_ONEUI_30) {
            mpSceneView.addProjectionForCount();
            mpSceneView.addOrderByCount();
        }
        return mpSceneView.buildSelectQuery();
    }

    public Cursor getExpressionCursor() {
        if (this.mParams.getFileId() != -1) {
            return getExpressionCursor(this.mParams.getFileId());
        }
        return getCursor(getExpressionsQuery(false), "Expressions");
    }

    public Cursor getExpressionFileCursor() {
        MpFacesView expressionFileView = getExpressionFileView();
        expressionFileView.getQueryBuilder().replaceProjectionByAliasWithValue(this.mParams.getSubCategory(), "__subCategory");
        if (this.mParams.getOrder() != null) {
            expressionFileView.getQueryBuilder().clearOrderBy().addOrderBy(this.mParams.getOrder());
        }
        if (this.mParams.getLimitSize() > 0) {
            expressionFileView.limit(this.mParams.getLimitSize());
        }
        String fileIds = this.mParams.getFileIds();
        if (!TextUtils.isEmpty(fileIds)) {
            expressionFileView.filterFileIds(fileIds);
        }
        if (this.mParams.mIsForOnDemandQuery) {
            expressionFileView.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            Query buildSelectQuery = expressionFileView.buildSelectQuery();
            return getFileIdsConcatCursor(buildSelectQuery, "expression files " + this.mParams.getSubCategory());
        }
        Query buildSelectQuery2 = expressionFileView.buildSelectQuery();
        return getCursor(buildSelectQuery2, "expression files " + this.mParams.getSubCategory());
    }

    public Cursor getExpressionFileDateCursor() {
        MpFacesView expressionFileView = getExpressionFileView();
        expressionFileView.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        expressionFileView.distinctIdCountForTimelineDateData();
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(expressionFileView.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "expression files date" + this.mParams.getSubCategory());
    }

    public Cursor getExpressionFileRealRatioCursor() {
        MpFacesView expressionFileView = getExpressionFileView();
        expressionFileView.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            expressionFileView.filterFileIds(this.mParams.getFileIds());
        }
        return ImplHelper.getRealRatioMergedCursor((BaseView) expressionFileView, this.mQueryExecutor);
    }

    public final Cursor getFileIdsConcatCursor(Query query, String str) {
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(query.buildSql());
        addFromSelect.addProjection("group_concat(__absID)", "__fileIds");
        return getCursor(new Query(addFromSelect), str);
    }

    public Query getLogShotModeQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterGalleryMedia();
        createShotModeFilesTable.filterLogShotMode();
        createShotModeFilesTable.getQueryBuilder().addProjection("CASE WHEN A.recording_mode in (" + TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, RecordingMode.listOfLogVideo()) + ") THEN 'log_video' ELSE null END", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    public Cursor getMyTagCursor() {
        Query myTagQuery = getMyTagQuery();
        long fileId = this.mParams.getFileId();
        if (fileId != -1) {
            myTagQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Related My Tags", "__mainCategory");
            QueryBuilder queryBuilder = myTagQuery.getQueryBuilder();
            queryBuilder.andCondition("A._id = " + fileId);
        } else {
            myTagQuery.getQueryBuilder().replaceProjectionByAliasWithValue("My tags", "__mainCategory");
        }
        return getCursor(myTagQuery, "My TAG : " + fileId);
    }

    public Cursor getMyTagFilRealRatioCursor() {
        MpUserTagView myTagView = getMyTagView();
        myTagView.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            myTagView.filterFileIds(this.mParams.getFileIds());
        }
        return ImplHelper.getRealRatioMergedCursor((BaseView) myTagView, this.mQueryExecutor);
    }

    public Cursor getMyTagFileCursor() {
        MpUserTagView myTagView = getMyTagView();
        myTagView.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            myTagView.filterFileIds(this.mParams.getFileIds());
        }
        if (this.mParams.mIsForOnDemandQuery) {
            myTagView.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            Query buildSelectQuery = myTagView.buildSelectQuery();
            return getFileIdsConcatCursor(buildSelectQuery, "My Tag : " + this.mParams.getSubCategory());
        }
        Query buildSelectQuery2 = myTagView.buildSelectQuery();
        return getCursor(buildSelectQuery2, "My Tag : " + this.mParams.getSubCategory());
    }

    public Cursor getMyTagFileDateCursor() {
        MpUserTagView myTagView = getMyTagView();
        myTagView.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(myTagView.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "My Tag date : " + this.mParams.getSubCategory());
    }

    public Query getRawShotModeQuery(boolean z) {
        MpFilesTable createShotModeFilesTable = createShotModeFilesTable();
        if (z) {
            createShotModeFilesTable.resetProjectionForAutoComplete();
        }
        createShotModeFilesTable.filterGalleryMedia();
        createShotModeFilesTable.filterRawShotMode();
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("raw", "__subCategory");
        createShotModeFilesTable.getQueryBuilder().replaceProjectionByAliasWithValue("Camera mode", "__mainCategory");
        return addProjectionForNewVisualSearch(createShotModeFilesTable, true).buildSelectQuery();
    }

    public Cursor getRelatedSceneCursor() {
        SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
        return secMpQueryExecutor.rawQuery("select distinct scene_name from scene where is_subscene=1 and sec_media_id=" + this.mParams.getFileId(), "relatedScene");
    }

    public Cursor getSceneCursor() {
        Query scenesQuery = getScenesQuery();
        scenesQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Things Scenery", "__mainCategory");
        return getCursor(scenesQuery, "Scene");
    }

    public Cursor getSceneFileCursor() {
        MpTagView sceneView = getSceneView();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            sceneView.filterFileIds(this.mParams.getFileIds());
        }
        if (this.mParams.mIsForOnDemandQuery) {
            sceneView.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            return getFileIdsConcatCursor(sceneView.buildSelectQuery(), this.mParams.getSubCategory());
        }
        return getCursor(sceneView.buildSelectQuery(), this.mParams.getSubCategory());
    }

    public Cursor getSceneFileDateCursor() {
        String subCategory = this.mParams.getSubCategory();
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        if (!TextUtils.isEmpty(this.mParams.getSubCategory())) {
            mpSceneView.filterTagData(this.mParams.getSubCategory());
        }
        mpSceneView.filterBurstShotBestImage(false);
        mpSceneView.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        mpSceneView.distinctIdCountForTimelineDateData();
        ArrayList<Integer> excludeAlbumIdList = this.mParams.getExcludeAlbumIdList();
        if (excludeAlbumIdList != null) {
            mpSceneView.filterBucketIds(excludeAlbumIdList, false);
        }
        String excludeFileIds = this.mParams.getExcludeFileIds();
        if (excludeFileIds != null) {
            mpSceneView.excludeFileIds(excludeFileIds);
        }
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            mpSceneView.includeFileIds(this.mParams.getIncludeFileIds());
        }
        if (!TextUtils.isEmpty(this.mParams.getParentCategory())) {
            mpSceneView.filterParentScene(this.mParams.getParentCategory());
        }
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(mpSceneView.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, " date : " + subCategory);
    }

    public Cursor getSceneFileRealRatioCursor() {
        MpTagView sceneView = getSceneView();
        sceneView.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            sceneView.filterFileIds(this.mParams.getFileIds());
        }
        return ImplHelper.getRealRatioMergedCursor((BaseView) sceneView, this.mQueryExecutor);
    }

    public Cursor getSceneryCursor() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.filterScenery();
        mpSceneView.filterBurstShotBestImage(false);
        mpSceneView.groupTagData();
        mpSceneView.havingMinimumTagCount();
        Query buildSelectQuery = mpSceneView.buildSelectQuery();
        buildSelectQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Scenery", "__mainCategory");
        return getCursor(buildSelectQuery, "Scenery");
    }

    public Cursor getScreenShotCursor() {
        MatrixCursor matrixCursor;
        int count;
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.addProjectionForCount();
        mpSceneView.addProjectionSceneOrder();
        mpSceneView.filterCapture();
        mpSceneView.groupBySceneName();
        mpSceneView.havingLatest();
        mpSceneView.addOrderByCount();
        Cursor cursor = getCursor(mpSceneView.buildSelectQuery(), "ScreenShot");
        MatrixCursor matrixCursor2 = null;
        if (cursor == null || ((count = cursor.getCount()) <= 0 && (count != 0 || !existScreenshotAllItem()))) {
            matrixCursor = null;
        } else {
            Object[] array = Arrays.stream(cursor.getColumnNames()).map(new a(6)).toArray(new C0517a(2));
            matrixCursor = new MatrixCursor(cursor.getColumnNames(), 1);
            matrixCursor.addRow(array);
        }
        if (PocFeatures.ASK_SCREENSHOT && cursor != null) {
            Object[] array2 = Arrays.stream(cursor.getColumnNames()).map(new a(7)).toArray(new C0517a(3));
            matrixCursor2 = new MatrixCursor(cursor.getColumnNames(), 1);
            matrixCursor2.addRow(array2);
        }
        if (matrixCursor != null) {
            if (matrixCursor2 != null) {
                return new MergeCursor(new Cursor[]{matrixCursor, matrixCursor2, cursor});
            }
            return new MergeCursor(new Cursor[]{matrixCursor, cursor});
        } else if (matrixCursor2 == null) {
            return cursor;
        } else {
            return new MergeCursor(new Cursor[]{matrixCursor2, cursor});
        }
    }

    public Cursor getScreenShotSubList() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.addProjectionForCount();
        mpSceneView.addProjectionSceneOrder();
        mpSceneView.filterScene((String) this.mParams.getTag(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, (Object) null), (String) this.mParams.getTag("sub", (Object) null));
        mpSceneView.groupBySceneName();
        mpSceneView.havingLatest();
        mpSceneView.addOrderBySceneOrder();
        return getCursor(mpSceneView.buildSelectQuery(), "getScreenShotFilterSubList");
    }

    public Cursor getScreenShotSubListFileIdsCursor() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.clearProjection();
        mpSceneView.addScreenShotFilterFileIdsProjection();
        mpSceneView.filterCapture();
        mpSceneView.groupBySceneName();
        return getCursor(mpSceneView.buildSelectQuery(), "ScreenShotSubListFileIds");
    }

    public int[] getSearchClusterPicturesCount() {
        if (this.mParams.getFileIds() != null) {
            int[] iArr = {0, 0};
            String[] split = this.mParams.getFileIds().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            int i2 = 0;
            while (split.length > i2) {
                StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                int i7 = 0;
                while (i2 < split.length) {
                    stringJoiner.add(split[i2]);
                    int i8 = i7 + 1;
                    if (i7 == 5000) {
                        break;
                    }
                    i2++;
                    i7 = i8;
                }
                MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
                mpFilesTable.resetProjectionForCursorCount(false);
                mpFilesTable.groupByMediaType();
                mpFilesTable.filterIds(stringJoiner.toString());
                int[] mediaCount = QueryUtils.getMediaCount(this.mQueryExecutor, mpFilesTable.buildSelectQuery());
                iArr[0] = iArr[0] + mediaCount[0];
                iArr[1] = iArr[1] + mediaCount[1];
                i2++;
            }
            return iArr;
        }
        MpFilesTable mpFilesTable2 = new MpFilesTable(this.mParams);
        mpFilesTable2.resetProjectionForCursorCount(false);
        mpFilesTable2.groupByMediaType();
        return QueryUtils.getMediaCount(this.mQueryExecutor, mpFilesTable2.buildSelectQuery());
    }

    public Cursor getShotModeCursor() {
        Query query;
        Query query2;
        Query query3;
        this.mParams.useDefaultOrder = false;
        long currentTimeMillis = System.currentTimeMillis();
        Query query4 = new Query(getVideoQuery(false));
        Query query5 = new Query(getBurstShotModeQuery(false));
        Query query6 = new Query(getSingleTakeTakenShotQuery());
        Query query7 = new Query(getGifQuery(false));
        Query sefShotModesQuery = getSefShotModesQuery(false, this.IS_GTE_R);
        Query query8 = null;
        if (this.IS_GTE_R) {
            query = getSelfieShotModeQuery(false);
        } else {
            query = null;
        }
        if (PreferenceFeatures.OneUi6x.IS_ONE_UI_60) {
            query2 = getRawShotModeQuery(false);
        } else {
            query2 = null;
        }
        boolean z = PreferenceFeatures.OneUi7x.IS_ONE_UI_70;
        if (z) {
            query3 = getLogShotModeQuery(false);
        } else {
            query3 = null;
        }
        if (z) {
            query8 = get3dCaptureShotModeQuery(false);
        }
        Query apvShotModeQuery = getApvShotModeQuery();
        Cursor[] cursorArr = new Cursor[10];
        LatchBuilder latchBuilder = new LatchBuilder("shotModeQuery");
        long j2 = currentTimeMillis;
        latchBuilder.addWorker(new t8.a(this, cursorArr, query4, 0));
        latchBuilder.setCurrent(new t8.a(this, cursorArr, query5, 2));
        latchBuilder.addWorker(new t8.a(this, cursorArr, query6, 3));
        latchBuilder.addWorker(new t8.a(this, cursorArr, query7, 4));
        latchBuilder.addWorker(new t8.a(this, cursorArr, sefShotModesQuery, 5));
        if (query != null) {
            latchBuilder.addWorker(new t8.a(this, cursorArr, query, 6));
        }
        if (query2 != null) {
            latchBuilder.addWorker(new t8.a(this, cursorArr, query2, 7));
        }
        if (query3 != null) {
            latchBuilder.addWorker(new t8.a(this, cursorArr, query3, 8));
        }
        if (query8 != null) {
            latchBuilder.addWorker(new t8.a(this, cursorArr, query8, 9));
        }
        if (apvShotModeQuery != null) {
            latchBuilder.addWorker(new t8.a(this, cursorArr, apvShotModeQuery, 1));
        }
        latchBuilder.setPostExecutor((Runnable) new i(25));
        latchBuilder.start();
        Log.d("CategoriesImpl", C0086a.j(j2, Logger.toString(cursorArr), " +", new StringBuilder("shotModeQuery ")));
        return new MergeCursor(cursorArr);
    }

    public Cursor getShotModeFileCursor() {
        String subCategory = this.mParams.getSubCategory();
        SecFilesTable shotModeFilesTable = getShotModeFilesTable(subCategory);
        if (this.mParams.mIsForOnDemandQuery) {
            shotModeFilesTable.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            Query buildSelectQuery = shotModeFilesTable.buildSelectQuery();
            return getFileIdsConcatCursor(buildSelectQuery, "shotMode : " + subCategory);
        }
        Query buildSelectQuery2 = shotModeFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery2, "shotMode : " + subCategory);
    }

    public Cursor getShotModeFileDateCursor() {
        SecFilesTable shotModeFilesTable = getShotModeFilesTable(this.mParams.getSubCategory());
        shotModeFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(shotModeFilesTable.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "shotMode date : " + this.mParams.getSubCategory());
    }

    public Cursor getShotModeFileRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(getShotModeFilesTable(this.mParams.getSubCategory()), this.mQueryExecutor);
    }

    public Cursor getSmileFileCursor() {
        MpFacesView mpFacesView = new MpFacesView(this.mParams);
        mpFacesView.filterSmile();
        mpFacesView.modifyForPictures();
        return getCursor(mpFacesView.buildSelectQuery(), "smile files");
    }

    public Cursor getThingsCursor() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.filterThings();
        mpSceneView.filterBurstShotBestImage(false);
        mpSceneView.groupTagData();
        mpSceneView.havingMinimumTagCount();
        Query buildSelectQuery = mpSceneView.buildSelectQuery();
        buildSelectQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Things", "__mainCategory");
        return getCursor(buildSelectQuery, "Things");
    }

    public Cursor getVideoFileCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterVideo();
        return getCursor(mpFilesTable.buildSelectQuery(), "video files");
    }

    private Cursor getExpressionCursor(long j2) {
        String f = A.a.f("A._id = ", j2);
        Query query = new Query(new QueryBuilder().addFromSelect(getExpressionsSubQuery(false, ExpressionsType.EXPRESSION_HAPPY).getQueryBuilder().andCondition(f).build()));
        Query query2 = new Query(new QueryBuilder().addFromSelect(getExpressionsSubQuery(false, ExpressionsType.EXPRESSION_NEUTRAL).getQueryBuilder().andCondition(f).build()));
        Query query3 = new Query(new QueryBuilder().addFromSelect(getExpressionsSubQuery(false, ExpressionsType.EXPRESSION_DISLIKE).getQueryBuilder().andCondition(f).build()));
        Query query4 = new Query(new QueryBuilder().addFromSelect(getExpressionsSubQuery(false, ExpressionsType.EXPRESSION_SURPRISE).getQueryBuilder().andCondition(f).build()));
        query.unionAll(query2);
        query.unionAll(query3);
        query.unionAll(query4);
        return getCursor(query, "Expressions : " + j2);
    }

    private Cursor getDocumentCursor(long j2) {
        Query documentQuery = getDocumentQuery();
        QueryBuilder queryBuilder = documentQuery.getQueryBuilder();
        queryBuilder.andCondition("A._id=" + j2);
        documentQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Documents", "__mainCategory");
        Query allDocumentsQuery = getAllDocumentsQuery();
        QueryBuilder queryBuilder2 = allDocumentsQuery.getQueryBuilder();
        queryBuilder2.andCondition("A._id=" + j2);
        allDocumentsQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Documents All", "__mainCategory");
        if (!SdkConfig.atLeast(SdkConfig.GED.Q)) {
            allDocumentsQuery.getQueryBuilder().replaceProjectionByAliasWithValue("Documents", "__subCategory");
        }
        Query unionAll = documentQuery.unionAll(allDocumentsQuery);
        return getCursor(unionAll, "Document : " + j2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getShotModeCursor$10() {
    }
}
