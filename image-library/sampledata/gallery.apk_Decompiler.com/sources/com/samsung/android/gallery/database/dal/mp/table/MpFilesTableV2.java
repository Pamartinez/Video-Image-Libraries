package com.samsung.android.gallery.database.dal.mp.table;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpFilesTableV2 extends MpFilesTable {
    public MpFilesTableV2(QueryParams queryParams) {
        super(queryParams);
    }

    public static final String getIdFromUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.substring(str.lastIndexOf("/") + 1);
    }

    public String tag() {
        return "MpFilesTable";
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0268  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:120:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01e4  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0241  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateByQueryParams() {
        /*
            r18 = this;
            r0 = r18
            super.updateByQueryParams()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            boolean r1 = r1.isShowHidden()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r0.mParams
            long r3 = r2.mStartTime
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r8 = 1
            if (r7 == 0) goto L_0x0024
            long r9 = r2.mEndTime
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0024
            java.lang.String r2 = java.lang.String.valueOf(r3)
            r0.filterByFromTime(r2, r8)
            goto L_0x0043
        L_0x0024:
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0032
            long r9 = r2.mEndTime
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0032
            r0.filterCreationTime(r3, r9)
            goto L_0x0043
        L_0x0032:
            long r2 = r2.getFromNow()
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x0043
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r0.mParams
            long r2 = r2.getFromNow()
            r0.replaceDateTakenFrom(r2)
        L_0x0043:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r0.mParams
            long r2 = r2.getFileId()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r4 = r0.mParams
            long r9 = r4.getMediaId()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r4 = r0.mParams
            java.lang.String r4 = r4.getMediaIds()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r7 = r0.mParams
            java.lang.String r7 = r7.getFileIds()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r11 = r0.mParams
            java.lang.String[] r11 = r11.getUriArray()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r12 = r0.mParams
            java.lang.String r12 = r12.getFilePath()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r13 = r0.mParams
            java.lang.String r13 = r13.mDataLike
            int r14 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            java.lang.String r15 = ","
            r16 = r5
            r5 = 0
            if (r14 == 0) goto L_0x008b
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r1 = r0.mQueryBuilder
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "A._id = "
            r4.<init>(r6)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r1.andCondition(r2)
        L_0x0087:
            r1 = r8
            r2 = r1
            goto L_0x0145
        L_0x008b:
            boolean r2 = android.text.TextUtils.isEmpty(r7)
            if (r2 != 0) goto L_0x0095
            r0.filterIds(r7)
            goto L_0x0087
        L_0x0095:
            int r2 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r2 == 0) goto L_0x009d
            r0.filterMediaID(r9)
            goto L_0x0087
        L_0x009d:
            if (r4 == 0) goto L_0x00a3
            r0.filterMediaIDs(r4)
            goto L_0x0087
        L_0x00a3:
            if (r11 == 0) goto L_0x0128
            int r2 = r11.length
            if (r2 <= 0) goto L_0x0128
            java.util.StringJoiner r2 = new java.util.StringJoiner
            r2.<init>(r15)
            java.util.StringJoiner r3 = new java.util.StringJoiner
            r3.<init>(r15)
            int r4 = r11.length
            r6 = r5
        L_0x00b4:
            if (r6 >= r4) goto L_0x00de
            r9 = r11[r6]
            java.lang.String r10 = getIdFromUri(r9)
            if (r10 == 0) goto L_0x00d2
            com.samsung.android.gallery.support.providers.UriInterface r12 = com.samsung.android.gallery.support.providers.MediaUri.getInstance()
            boolean r9 = r12.isSecMediaUri(r9)
            if (r9 == 0) goto L_0x00cc
            r2.add(r10)
            goto L_0x00cf
        L_0x00cc:
            r3.add(r10)
        L_0x00cf:
            int r6 = r6 + 1
            goto L_0x00b4
        L_0x00d2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "uri : "
            java.lang.String r1 = i.C0212a.l(r1, r9)
            r0.<init>(r1)
            throw r0
        L_0x00de:
            int r4 = r2.length()
            if (r4 <= 0) goto L_0x010b
            int r4 = r3.length()
            if (r4 <= 0) goto L_0x010b
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r4 = r0.mQueryBuilder
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r9 = "A._id in ("
            r6.<init>(r9)
            r6.append(r2)
            java.lang.String r2 = ") or A.media_id in ("
            r6.append(r2)
            r6.append(r3)
            java.lang.String r2 = ")"
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            r4.andCondition(r2)
            goto L_0x0126
        L_0x010b:
            int r4 = r2.length()
            if (r4 <= 0) goto L_0x0119
            java.lang.String r2 = r2.toString()
            r0.filterIds(r2)
            goto L_0x0126
        L_0x0119:
            int r2 = r3.length()
            if (r2 <= 0) goto L_0x0126
            java.lang.String r2 = r3.toString()
            r0.filterMediaIDs(r2)
        L_0x0126:
            r2 = r8
            goto L_0x0145
        L_0x0128:
            if (r12 == 0) goto L_0x013f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "\""
            r2.<init>(r3)
            r2.append(r12)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.filterFilePathOnly(r2)
            goto L_0x0126
        L_0x013f:
            if (r13 == 0) goto L_0x0144
            r0.filterDataLike(r13)
        L_0x0144:
            r2 = r5
        L_0x0145:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            long r3 = r3.maxFileId
            r9 = 0
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0166
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r3 = r0.mQueryBuilder
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "_id < "
            r4.<init>(r6)
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r6 = r0.mParams
            long r11 = r6.maxFileId
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r3.andCondition(r4)
        L_0x0166:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            long r3 = r3.minFileId
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0185
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r3 = r0.mQueryBuilder
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "_id > "
            r4.<init>(r6)
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r6 = r0.mParams
            long r9 = r6.minFileId
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r3.andCondition(r4)
        L_0x0185:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            java.lang.String r3 = r3.getExcludeFileIds()
            if (r3 == 0) goto L_0x0190
            r0.excludeFileIds(r3)
        L_0x0190:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            int r3 = r3.getAlbumIdCount()
            if (r3 != r8) goto L_0x01ac
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            int[] r1 = r1.getAlbumIdArray()
            r1 = r1[r5]
            boolean r3 = com.samsung.android.gallery.support.utils.BucketUtils.isRecent(r1)
            if (r3 == 0) goto L_0x01a8
            r1 = r5
            goto L_0x01be
        L_0x01a8:
            r0.filterAlbumID(r1)
            goto L_0x01bd
        L_0x01ac:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            int r3 = r3.getAlbumIdCount()
            if (r3 <= r8) goto L_0x01be
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            int[] r1 = r1.getAlbumIdArray()
            r0.filterAlbumIDs((int[]) r1)
        L_0x01bd:
            r1 = r8
        L_0x01be:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            java.util.ArrayList r3 = r3.getExcludeAlbumIdList()
            if (r3 == 0) goto L_0x01c9
            r0.filterBucketIds(r3, r5)
        L_0x01c9:
            r0.filterStorageType()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r0.mParams
            java.lang.String r3 = r3.getMediaTypeFilter()
            r0.filterGalleryMedia(r3)
            if (r1 != 0) goto L_0x01da
            r0.filterHidden()
        L_0x01da:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            java.lang.String r1 = r1.mFileNamePrefixExclude
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0200
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r1 = r0.mQueryBuilder
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "title not like '"
            r3.<init>(r4)
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r4 = r0.mParams
            java.lang.String r4 = r4.mFileNamePrefixExclude
            r3.append(r4)
            java.lang.String r4 = "%'"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.andCondition(r3)
        L_0x0200:
            if (r2 == 0) goto L_0x0208
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r1 = r0.mQueryBuilder
            r0.addGroupMediaCountProjection(r1)
            goto L_0x0216
        L_0x0208:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            com.samsung.android.gallery.database.dbtype.GroupType[] r1 = r1.getGroupTypes()
            if (r1 == 0) goto L_0x0216
            int r1 = r1.length
            if (r1 <= 0) goto L_0x0216
            r0.filterGroupMediaBest(r8)
        L_0x0216:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            boolean r1 = r1.mGroupSizeSum
            if (r1 == 0) goto L_0x0221
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r1 = r0.mQueryBuilder
            r0.addGroupMediaSizeSumProjection(r1)
        L_0x0221:
            r0.clearOrderBy()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            boolean r1 = r1.getUseIdOrder()
            if (r1 == 0) goto L_0x0241
            java.lang.String[] r1 = r7.split(r15)
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r2 = r0.mQueryBuilder
            java.lang.String r3 = "_id"
            java.lang.String r1 = r0.buildSortProjection(r1, r3)
            java.lang.String r3 = "__idOrder"
            r2.addProjection(r1, r3)
            r0.orderByIds()
            goto L_0x0260
        L_0x0241:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            java.lang.String r1 = r1.getOrder()
            if (r1 == 0) goto L_0x0255
            com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder r1 = r0.mQueryBuilder
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r0.mParams
            java.lang.String r2 = r2.getOrder()
            r1.addOrderBy(r2)
            goto L_0x0260
        L_0x0255:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            int r1 = r1.getSortBy()
            if (r1 == 0) goto L_0x0260
            r0.orderByAlbumPictures(r1)
        L_0x0260:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            int r1 = r1.getLimitSize()
            if (r1 <= 0) goto L_0x0277
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r0.mParams
            int r2 = r2.getLimitOffset()
            if (r2 >= 0) goto L_0x0274
            r0.limit(r1)
            goto L_0x0277
        L_0x0274:
            r0.limit(r2, r1)
        L_0x0277:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            boolean r1 = r1.needDataStamp()
            if (r1 == 0) goto L_0x0282
            r0.addDataStamp()
        L_0x0282:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r0.mParams
            int r1 = r1.mParentAlbumId
            r2 = -1
            if (r1 == r2) goto L_0x028c
            r0.addParentAlbumId(r1)
        L_0x028c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.table.MpFilesTableV2.updateByQueryParams():void");
    }
}
