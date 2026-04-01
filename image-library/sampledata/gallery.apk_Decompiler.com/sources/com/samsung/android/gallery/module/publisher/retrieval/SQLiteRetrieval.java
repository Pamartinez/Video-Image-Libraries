package com.samsung.android.gallery.module.publisher.retrieval;

import A.a;
import B5.e;
import N9.b;
import N9.c;
import N9.d;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SQLiteRetrieval implements StorageRetrieval {
    private final QueryParams mCommonParams;
    private boolean mIsSupportedTimeline = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DbKeySet {
        /* access modifiers changed from: private */
        public final String dataKey;
        /* access modifiers changed from: private */
        public final String dateKey;
        /* access modifiers changed from: private */
        public final String extraKey;
        /* access modifiers changed from: private */
        public final String realRatioKey;
        /* access modifiers changed from: private */
        public final boolean useQOD;

        public /* synthetic */ DbKeySet() {
            this("mp://myTag/files/fileIds", "mp://myTag/files/day", "mp://myTag/files/realratio", true);
        }

        /* access modifiers changed from: private */
        public String getName() {
            return this.dataKey;
        }

        public /* synthetic */ DbKeySet(int i2, String str, String str2, String str3) {
            this(str, str2, str3);
        }

        public /* synthetic */ DbKeySet(String str, String str2, String str3, String str4) {
            this(str, str2, str3, str4, true);
        }

        private DbKeySet(String str, String str2, String str3) {
            this(str, str2, str3, (String) null, false);
        }

        private DbKeySet(String str, String str2, String str3, boolean z) {
            this(str, str2, str3, (String) null, z);
        }

        private DbKeySet(String str, String str2, String str3, String str4, boolean z) {
            this.useQOD = z;
            this.dataKey = str;
            this.dateKey = str2;
            this.realRatioKey = str3;
            this.extraKey = str4;
        }
    }

    public SQLiteRetrieval(QueryParams queryParams) {
        this.mCommonParams = queryParams;
    }

    private Cursor convertFileIdsCollectionCursor(Cursor cursor) {
        TimeTickLog timeTickLog = new TimeTickLog();
        StringBuilder sb2 = new StringBuilder();
        CollectionCursor collectionCursor = new CollectionCursor("__absID");
        try {
            timeTickLog.tick();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        long j2 = cursor.getLong(0);
                        sb2.append(j2);
                        collectionCursor.add(Long.valueOf(j2));
                    } while (cursor.moveToNext());
                    timeTickLog.tick();
                    cursor.close();
                    String str = sb2.toString().hashCode() + "_" + collectionCursor.getCount();
                    Bundle bundle = new Bundle();
                    bundle.putString("dataHash", str);
                    collectionCursor.setExtras(bundle);
                    a.A(new Object[]{str, timeTickLog}, new StringBuilder("convertFileIdsCollectionCursor"), "SQLiteRetrieval");
                    return collectionCursor;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return collectionCursor;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Cursor getCursor(Consumer<QueryParams> consumer, String str) {
        if (str != null) {
            return DbCompat.query(str, consumer);
        }
        return null;
    }

    private Cursor getCursorByPartition(String str, Function<String, Cursor> function) {
        if (TextUtils.isEmpty(str)) {
            return createDummyCursor();
        }
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
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
            arrayList.add(function.apply(stringJoiner.toString()));
            i2++;
        }
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return createDummyCursor();
    }

    private Cursor[] getCursors(Consumer<QueryParams> consumer, DbKeySet dbKeySet) {
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        AtomicReference atomicReference3 = new AtomicReference();
        AtomicReference atomicReference4 = new AtomicReference();
        AtomicReference atomicReference5 = new AtomicReference();
        LatchBuilder latchBuilder = new LatchBuilder(dbKeySet.getName());
        if (dbKeySet.useQOD) {
            latchBuilder.setCurrent(new a(this, atomicReference3, consumer, dbKeySet, 0));
        } else {
            latchBuilder.setCurrent(new a(this, atomicReference, consumer, dbKeySet, 1));
        }
        AtomicReference atomicReference6 = atomicReference2;
        AtomicReference atomicReference7 = atomicReference4;
        latchBuilder.addWorker(new a(this, atomicReference6, consumer, dbKeySet, 2)).addWorker(new a(this, atomicReference7, consumer, dbKeySet, 3)).addWorker(new a(this, atomicReference5, consumer, dbKeySet, 4)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
        return new Cursor[]{(Cursor) atomicReference.get(), (Cursor) atomicReference6.get(), null, (Cursor) atomicReference7.get(), null, (Cursor) atomicReference3.get(), null, (Cursor) atomicReference5.get()};
    }

    private Cursor[] getCursorsOnDemand(Consumer<QueryParams> consumer, DbKeySet dbKeySet) {
        return getCursors(consumer, dbKeySet);
    }

    private Cursor getDateCursor(Consumer<QueryParams> consumer, String str) {
        if (this.mIsSupportedTimeline) {
            return getCursor(consumer, str);
        }
        return null;
    }

    private String getIdListString(Cursor cursor) {
        if (!(cursor instanceof CollectionCursor)) {
            return "-1";
        }
        ArrayList rawData = ((CollectionCursor) cursor).getRawData();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        rawData.forEach(new e(stringJoiner, 4));
        return stringJoiner.toString();
    }

    private Consumer<QueryParams> getQueryParamModifier(String str, boolean z) {
        return new N9.a(this, str, z, 0);
    }

    private Consumer<QueryParams> getQueryParamModifierForGenerated(String str, boolean z) {
        return new N9.a(this, str, z, 1);
    }

    private Consumer<QueryParams> getQueryParamModifierForMultiplePeople(String str, boolean z) {
        return new N9.a(this, str, z, 2);
    }

    private Consumer<QueryParams> getQueryParamModifierForPeople(String str, String str2, boolean z) {
        return new b(this, str, str2, z);
    }

    private Consumer<QueryParams> getQueryParamModifierForPet(String str, boolean z) {
        return new N9.a(this, str, z, 3);
    }

    private Consumer<QueryParams> getQueryParamModifierForShotMode(String str, boolean z) {
        return new N9.a(this, str, z, 4);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCursors$21(AtomicReference atomicReference, Consumer consumer, DbKeySet dbKeySet) {
        atomicReference.set(convertFileIdsCollectionCursor(getCursor(consumer, dbKeySet.dataKey)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCursors$22(AtomicReference atomicReference, Consumer consumer, DbKeySet dbKeySet) {
        atomicReference.set(getCursor(consumer, dbKeySet.dataKey));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCursors$23(AtomicReference atomicReference, Consumer consumer, DbKeySet dbKeySet) {
        atomicReference.set(getDateCursor(consumer, dbKeySet.dateKey));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCursors$24(AtomicReference atomicReference, Consumer consumer, DbKeySet dbKeySet) {
        atomicReference.set(getCursor(consumer, dbKeySet.realRatioKey));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCursors$25(AtomicReference atomicReference, Consumer consumer, DbKeySet dbKeySet) {
        atomicReference.set(getCursor(consumer, dbKeySet.extraKey));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifier$0(String str, boolean z, QueryParams queryParams) {
        queryParams.setSubCategory(str);
        queryParams.mIsForOnDemandQuery = z;
        setCommonParams(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForGenerated$2(String str, boolean z, QueryParams queryParams) {
        queryParams.setSubCategory(str);
        queryParams.mIsForOnDemandQuery = z;
        setCommonParams(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForMultiplePeople$5(String str, boolean z, QueryParams queryParams) {
        queryParams.mRecommendedIds = str;
        queryParams.mIsForOnDemandQuery = z;
        setCommonParams(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForPeople$3(String str, String str2, boolean z, QueryParams queryParams) {
        queryParams.setSubCategory(str);
        if (!Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            queryParams.mCreatureTagName = str2;
        }
        queryParams.mIsForOnDemandQuery = z;
        setCommonParams(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForPet$4(String str, boolean z, QueryParams queryParams) {
        queryParams.setSubCategory(str);
        queryParams.mIsForOnDemandQuery = z;
        setCommonParams(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForShotMode$1(String str, boolean z, QueryParams queryParams) {
        if (!"video".equals(str)) {
            queryParams.addGroupType(GroupType.SINGLE_TAKEN);
        }
        queryParams.setSubCategory(str);
        queryParams.mIsForOnDemandQuery = z;
        setCommonParams(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$getTimelineFileIds$12(boolean z, boolean z3, String str) {
        String str2;
        QueryParams clone = this.mCommonParams.clone();
        if (z) {
            str2 = DbKey.VIRTUAL_ALBUM_VIDEO_DAY;
        } else if (z3) {
            str2 = DbKey.ALBUM_FILE_DAY;
        } else {
            str2 = DbKey.TIMELINE_DAY;
        }
        return DbCompat.query(clone.setDbKey(str2).setFileIds(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getTimelineFileIds$13(AtomicReference atomicReference, String str, boolean z, boolean z3) {
        atomicReference.set(getCursorByPartition(str, new c(this, z, z3, 0)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$getTimelineFileIds$14(boolean z, boolean z3, String str) {
        String str2;
        QueryParams clone = this.mCommonParams.clone();
        if (z) {
            str2 = DbKey.VIRTUAL_ALBUM_VIDEO_REAL_RATIO;
        } else if (z3) {
            str2 = DbKey.ALBUM_FILE_REALRATIO;
        } else {
            str2 = DbKey.TIMELINE_REALRATIO;
        }
        return DbCompat.query(clone.setDbKey(str2).setFileIds(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getTimelineFileIds$15(AtomicReference atomicReference, String str, boolean z, boolean z3) {
        atomicReference.set(getCursorByPartition(str, new c(this, z, z3, 1)));
    }

    private void setCommonParams(QueryParams queryParams) {
        queryParams.setMediaTypeFilter(this.mCommonParams.getMediaTypeFilter());
        queryParams.setStorageTypes(this.mCommonParams.getDbStorageType());
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            queryParams.excludeAlbumId((Collection<Integer>) this.mCommonParams.getExcludeAlbumIdList());
        }
        if (!TextUtils.isEmpty(this.mCommonParams.getExpandedDates())) {
            queryParams.setExpandedDates(this.mCommonParams.getExpandedDates());
        }
        if (!TextUtils.isEmpty(this.mCommonParams.getParentCategory())) {
            queryParams.setParentCategory(this.mCommonParams.getParentCategory());
        }
        if (!TextUtils.isEmpty(this.mCommonParams.getFileIds())) {
            queryParams.setFileIds(this.mCommonParams.getFileIds());
        }
    }

    public Cursor createDummyCursor() {
        return new MatrixCursor(new String[]{"_id"});
    }

    public Cursor[] getDocumentFileIds(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand(getQueryParamModifier(str, true), new DbKeySet("mp://Document/files/fileIds", "mp://Document/files/day", "mp://Document/files/realratio", (String) null));
    }

    public Cursor[] getDocumentFiles(String str, SearchFilter.Builder builder) {
        return getCursors(getQueryParamModifier(str, false), new DbKeySet(0, "mp://Document/files", "mp://Document/files/day", "mp://Document/files/realratio"));
    }

    public Cursor[] getExpressionFileIds(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand(getQueryParamModifier(str, true), new DbKeySet("mp://Expression/files/fileIds", "mp://Expression/files/day", "mp://Expression/files/realratio", (String) null));
    }

    public Cursor[] getExpressionFiles(String str, SearchFilter.Builder builder) {
        return getCursors(getQueryParamModifier(str, false), new DbKeySet(0, "mp://Expression/files", "mp://Expression/files/day", "mp://Expression/files/realratio"));
    }

    public Cursor[] getGeneratedFileIds(String str, int i2) {
        return getCursorsOnDemand(getQueryParamModifierForGenerated(str, true), new DbKeySet("mp://Generated/files/fileIds", "mp://Generated/files/day", "mp://Generated/files/realratio", (String) null));
    }

    public Cursor[] getGeneratedFiles(String str) {
        return getCursors(getQueryParamModifierForGenerated(str, false), new DbKeySet(0, "mp://Generated/files", "mp://Generated/files/day", "mp://Generated/files/realratio"));
    }

    public Cursor[] getLocationFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand(getQueryParamModifier(str, true), new DbKeySet("mp://Location/fileIds", "mp://Location/day", "mp://Location/files/realratio", "mp://Location/files/gps"));
    }

    public Cursor[] getLocationFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors(getQueryParamModifier(str, false), new DbKeySet(0, "mp://Location/files", "mp://Location/day", "mp://Location/files/realratio"));
    }

    public Cursor[] getMyTagsFileIds(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand(getQueryParamModifier(str, true), new DbKeySet());
    }

    public Cursor[] getMyTagsFiles(String str, SearchFilter.Builder builder) {
        return getCursors(getQueryParamModifier(str, false), new DbKeySet(0, "mp://myTag/files", "mp://myTag/files/day", "mp://myTag/files/realratio"));
    }

    public Cursor[] getPeopleFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand(getQueryParamModifierForPeople(str, str2, true), new DbKeySet("mp://People/files/fileIds", "mp://People/files/day", "mp://People/files/realratio", (String) null));
    }

    public Cursor[] getPeopleFileIdsMultiple(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand(getQueryParamModifierForMultiplePeople(str, true), new DbKeySet("mp://People/files/fileIds", "mp://People/files/day", "mp://People/files/realratio", (String) null));
    }

    public Cursor[] getPeopleFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors(getQueryParamModifierForPeople(str, str2, false), new DbKeySet(0, "mp://People/files", "mp://People/files/day", "mp://People/files/realratio"));
    }

    public Cursor[] getPeopleFilesMultiple(String str, SearchFilter.Builder builder) {
        return getCursors(getQueryParamModifierForMultiplePeople(str, false), new DbKeySet(0, "mp://People/files", "mp://People/files/day", "mp://People/files/realratio"));
    }

    public Cursor[] getPetFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand(getQueryParamModifierForPet(str, true), new DbKeySet("mp://Pets/files/fileIds", "mp://Pets/day", "mp://Pets/files/realratio", (String) null));
    }

    public Cursor[] getPetFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors(getQueryParamModifierForPet(str, false), new DbKeySet(0, "mp://Pets/files", "mp://Pets/day", "mp://Pets/files/realratio"));
    }

    public Cursor[] getRecentlyEdited(String str) {
        return getCursors(getQueryParamModifierForGenerated(str, false), new DbKeySet(0, "mp://RecentlyEdited/files", "mp://RecentlyEdited/files/day", "mp://RecentlyEdited/realratio"));
    }

    public Cursor[] getRecentlyEditedFileIds(String str, int i2) {
        return getCursorsOnDemand(getQueryParamModifierForGenerated(str, true), new DbKeySet("mp://RecentlyEdited/fileIds", "mp://RecentlyEdited/files/day", "mp://RecentlyEdited/realratio", (String) null));
    }

    public Cursor[] getSceneFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand(getQueryParamModifier(str, true), new DbKeySet("mp://Scene/files/fileIds", "mp://Scene/files/day", "mp://Scene/files/realratio", (String) null));
    }

    public Cursor[] getSceneFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors(getQueryParamModifier(str, false), new DbKeySet(0, "mp://Scene/files", "mp://Scene/files/day", "mp://Scene/files/realratio"));
    }

    public Cursor[] getShotModeFileIds(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand(getQueryParamModifierForShotMode(str, true), new DbKeySet("mp://ShotMode/files/fileIds", "mp://ShotMode/files/day", "mp://ShotMode/files/realratio", (String) null));
    }

    public Cursor[] getShotModeFiles(String str, SearchFilter.Builder builder) {
        return getCursors(getQueryParamModifierForShotMode(str, false), new DbKeySet(0, "mp://ShotMode/files", "mp://ShotMode/files/day", "mp://ShotMode/files/realratio"));
    }

    public Cursor[] getTimelineFileIds(Cursor cursor) {
        boolean z;
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        String idListString = getIdListString(cursor);
        boolean equals = MediaFilterType.VIDEO_ONLY.toString().equals(this.mCommonParams.getMediaTypeFilter());
        if (this.mCommonParams.getAlbumIdCount() > 0) {
            z = true;
        } else {
            z = false;
        }
        LatchBuilder current = new LatchBuilder("publishMapClusterData").setCurrent(new d(this, atomicReference, idListString, equals, z, 0));
        boolean z3 = z;
        AtomicReference atomicReference3 = atomicReference2;
        current.addWorker(new d(this, atomicReference3, idListString, equals, z3, 1)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
        return new Cursor[]{null, (Cursor) atomicReference.get(), null, (Cursor) atomicReference3.get(), null, cursor, null, null, null};
    }

    public void setSupportedTimeline(boolean z) {
        this.mIsSupportedTimeline = z;
    }
}
