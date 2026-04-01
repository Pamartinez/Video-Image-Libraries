package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.text.TextUtils;
import c4.C0438h;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.publisher.retrieval.StorageRetrieval;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchDataPublisherV2 extends SearchDataPublisher {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CarouselColumnHolder {
        static final String[] array;
        static final List<String> list;

        static {
            String[] strArr = {"__Title", "__count", "__mainCategory", "__subCategory", "__tagType", "__creatureName", "__albumID", "__albumType", "__absID", "__absPath", "__orientation", "__creatureFaceGroupID", "__creatureType", "__creatureFaceRecommendedID", "__creatureID", "__creatureFacePosRatio", "__width", "__height", "__size", "__mediaType", "__clusterType", "__cloudOriginalSize", "__storageType", "__cloudServerId", "__latitude", "__longitude", "__pdcStartTime", "__pdcEndTime"};
            array = strArr;
            list = Arrays.asList(strArr);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClusterTypeHolder {
        static final String[] types;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ClusterHolder {
            static final String[] clusterType = {"album_cluster", "facet_location", "person_cluster", "ocrtext"};
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ClusterHolder2 {
            static final String[] clusterType = {"album_cluster", "facet_location", "person_cluster", "ocrtext", "pet_cluster", "usertag"};
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ClusterHolder3 {
            static final String[] clusterType = {"album_cluster", "facet_location", "person_cluster", "ocrtext", "pet_cluster", "usertag", "pdc_cluster"};
        }

        static {
            String[] strArr;
            if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                strArr = ClusterHolder.clusterType;
            } else if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                strArr = ClusterHolder3.clusterType;
            } else {
                strArr = ClusterHolder2.clusterType;
            }
            types = strArr;
        }
    }

    public SearchDataPublisherV2(Blackboard blackboard) {
        super(blackboard);
    }

    private MatrixCursor buildSortedCursor(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        List<String> list = CarouselColumnHolder.list;
        ArrayList<Object[]> makeRowsFromCursor = makeRowsFromCursor(cursorArr, clusterResultsEntry);
        makeRowsFromCursor.sort(Comparator.comparing(new d0(0, list)).reversed().thenComparing(new d0(1, this)));
        MatrixCursor matrixCursor = new MatrixCursor(CarouselColumnHolder.array, makeRowsFromCursor.size());
        for (int i2 = 0; i2 < makeRowsFromCursor.size(); i2++) {
            matrixCursor.addRow(makeRowsFromCursor.get(i2));
        }
        return matrixCursor;
    }

    private Cursor[] getEmptyCursor() {
        return new Cursor[]{new MatrixCursor(new String[]{"_id"})};
    }

    /* access modifiers changed from: private */
    public String getSorter(Object[] objArr) {
        int i2;
        List<String> list = CarouselColumnHolder.list;
        String obj = objArr[list.indexOf("__clusterType")].toString();
        obj.getClass();
        char c5 = 65535;
        switch (obj.hashCode()) {
            case -1671680240:
                if (obj.equals("person_cluster")) {
                    c5 = 0;
                    break;
                }
                break;
            case 1038965053:
                if (obj.equals("facet_location")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1283958266:
                if (obj.equals("creature_cluster")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1955912410:
                if (obj.equals("pet_cluster")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 2:
            case 3:
                i2 = list.indexOf("__creatureName");
                break;
            case 1:
                i2 = list.indexOf("__subCategory");
                break;
            default:
                i2 = list.indexOf("__Title");
                break;
        }
        return objArr[i2].toString();
    }

    private void keywordAlbumQuery(Bundle bundle, boolean z) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        Cursor queryAlbum = this.mKeywordQuery.queryAlbum(bundle, false);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishKeywordAlbumsData " + getCursorInfo(queryAlbum, currentTimeMillis));
        if (z) {
            str = "location://search/fileList/ClusterCategoryAlbum";
        } else {
            str = "location://search/fileList/KeywordAlbums";
        }
        publishCursorArray(str, new Cursor[]{queryAlbum});
    }

    private void keywordLocationQuery(Bundle bundle, boolean z) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        Cursor queryLocation = this.mKeywordQuery.queryLocation(bundle, false);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "keywordLocationQuery " + getCursorInfo(queryLocation, currentTimeMillis));
        if (z) {
            str = "location://search/fileList/ClusterCategoryLocation";
        } else {
            str = "location://search/fileList/KeywordLocations";
        }
        publishCursorArray(str, new Cursor[]{queryLocation});
    }

    private void keywordPeopleQuery(Bundle bundle, boolean z) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        Cursor queryPeople = this.mKeywordQuery.queryPeople(bundle, false);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "keywordPeopleQuery " + getCursorInfo(queryPeople, currentTimeMillis));
        if (z) {
            str = "location://search/fileList/ClusterCategoryPeople";
        } else {
            str = "location://search/fileList/KeywordPeoples";
        }
        publishCursorArray(str, new Cursor[]{queryPeople});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$2(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        cursorArr[0] = this.mKeywordQuery.queryAlbum(clusterResultsEntry.getAlbumNames());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$3(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        cursorArr[1] = this.mKeywordQuery.queryLocation(clusterResultsEntry.getLocationNames());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$4(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        cursorArr[2] = this.mKeywordQuery.queryPeople(clusterResultsEntry.getPeopleNames());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$5(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        cursorArr[3] = clusterListDataQuery(clusterResultsEntry.getOcrIds(), -1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$6(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        cursorArr[4] = this.mKeywordQuery.queryPets(clusterResultsEntry.getPetNames());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$7(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        cursorArr[5] = this.mKeywordQuery.queryTags(clusterResultsEntry.getTagNames());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$8(Cursor[] cursorArr, String str) {
        cursorArr[6] = clusterListDataQuery(str, -1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCarouselClusterData$9(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry, long j2, Bundle bundle) {
        MatrixCursor buildSortedCursor = buildSortedCursor(cursorArr, clusterResultsEntry);
        Arrays.stream(cursorArr).forEach(new C0438h(17));
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishCarouselClusterData " + getCursorInfo(buildSortedCursor, j2));
        publishCursorArray(getLocationKeyFromReq(bundle), new Cursor[]{buildSortedCursor}, ArgumentsUtil.getSubscribeKey(bundle));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishPeopleSelectForRelation$20(Cursor[] cursorArr, String str) {
        cursorArr[0] = DbCompat.query("mp://PeopleNoRelationship", new c0(0, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishPeopleSelectForRelation$22(String str) {
        if (!TextUtils.isEmpty(str)) {
            publishCursorArray("location://search/fileList/SuggestedCreature", new Cursor[]{DbCompat.query("mp://PeopleNoRelationship", new c0(1, str))});
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishWithoutFilterRequest$0(Cursor[][] cursorArr, String str, String str2, String str3, Bundle bundle) {
        cursorArr[0] = getFilterDataCursors(str, str2, (String) null, str3, getExpandedDates(bundle), getAddedCount(bundle));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishWithoutFilterRequest$1(Cursor[][] cursorArr, long j2, String str, Bundle bundle, String str2) {
        if (cursorArr[0][getCursorIndex(cursorArr[0])] != null) {
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "publishWithoutFilterRequest " + getCursorListInfo(cursorArr[0], j2));
            publishCursorArray(str, cursorArr[0], ArgumentsUtil.getSubscribeKey(bundle));
            return;
        }
        Log.e((CharSequence) this.TAG, "publishWithoutFilterRequest failed", str, str2);
    }

    private ArrayList<Object[]> makeRowsFromCursor(Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry) {
        int i2;
        ArrayList<Object[]> arrayList;
        long j2;
        int i7;
        long j3;
        long j8;
        String str;
        int i8;
        long j10;
        String str2;
        double d;
        String str3;
        long j11;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        Cursor[] cursorArr2 = cursorArr;
        ClusterResultsEntry clusterResultsEntry2 = clusterResultsEntry;
        ArrayList<Object[]> arrayList2 = new ArrayList<>();
        int i10 = 0;
        while (i10 < cursorArr2.length) {
            Cursor cursor = cursorArr2[i10];
            String str10 = ClusterTypeHolder.types[i10];
            if (cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
                arrayList = arrayList2;
                i2 = i10;
            } else {
                int columnIndex = cursor.getColumnIndex("__creatureName");
                int columnIndex2 = cursor.getColumnIndex("__albumType");
                int columnIndex3 = cursor.getColumnIndex("__mainCategory");
                int columnIndex4 = cursor.getColumnIndex("__subCategory");
                int columnIndex5 = cursor.getColumnIndex("__tagType");
                int columnIndex6 = cursor.getColumnIndex("__creatureFaceGroupID");
                int columnIndex7 = cursor.getColumnIndex("__creatureType");
                i2 = i10;
                int columnIndex8 = cursor.getColumnIndex("__creatureFaceRecommendedID");
                String str11 = str10;
                int columnIndex9 = cursor.getColumnIndex("__creatureID");
                int i11 = columnIndex;
                int columnIndex10 = cursor.getColumnIndex("__creatureFacePosRatio");
                int i12 = columnIndex2;
                int columnIndex11 = cursor.getColumnIndex("__mediaType");
                int i13 = columnIndex3;
                int columnIndex12 = cursor.getColumnIndex("__cloudOriginalSize");
                int i14 = columnIndex4;
                int columnIndex13 = cursor.getColumnIndex("__storageType");
                ArrayList<Object[]> arrayList3 = arrayList2;
                int columnIndex14 = cursor.getColumnIndex("__cloudServerId");
                String str12 = "__albumType";
                int columnIndex15 = cursor.getColumnIndex("__latitude");
                String str13 = "__creatureName";
                int columnIndex16 = cursor.getColumnIndex("__longitude");
                String str14 = "__tagType";
                String str15 = str11;
                while (true) {
                    if (columnIndex6 < 0) {
                        j2 = -1;
                    } else {
                        j2 = cursor.getLong(columnIndex6);
                    }
                    if (columnIndex7 < 0) {
                        i7 = 0;
                    } else {
                        i7 = cursor.getInt(columnIndex7);
                    }
                    if (columnIndex8 < 0) {
                        j3 = -1;
                    } else {
                        j3 = cursor.getLong(columnIndex8);
                    }
                    if (columnIndex9 < 0) {
                        j8 = -1;
                    } else {
                        j8 = cursor.getLong(columnIndex9);
                    }
                    String str16 = null;
                    if (columnIndex10 < 0) {
                        str = null;
                    } else {
                        str = cursor.getString(columnIndex10);
                    }
                    int i15 = -1;
                    if (columnIndex11 < 0) {
                        i8 = -1;
                    } else {
                        i8 = cursor.getInt(columnIndex11);
                    }
                    long j12 = 0;
                    if (columnIndex12 < 0) {
                        j10 = 0;
                    } else {
                        j10 = cursor.getLong(columnIndex12);
                    }
                    if (columnIndex13 >= 0) {
                        i15 = cursor.getInt(columnIndex13);
                    }
                    if (columnIndex14 < 0) {
                        str2 = null;
                    } else {
                        str2 = cursor.getString(columnIndex14);
                    }
                    double d2 = MapUtil.INVALID_LOCATION;
                    if (columnIndex15 < 0) {
                        d = 0.0d;
                    } else {
                        d = cursor.getDouble(columnIndex15);
                    }
                    if (columnIndex16 >= 0) {
                        d2 = cursor.getDouble(columnIndex16);
                    }
                    int i16 = columnIndex7;
                    String string = cursor.getString(cursor.getColumnIndex("__absID"));
                    int i17 = columnIndex14;
                    String string2 = cursor.getString(cursor.getColumnIndex("__Title"));
                    String string3 = cursor.getString(cursor.getColumnIndex("__count"));
                    if ("pdc_cluster".equals(str15)) {
                        str3 = clusterResultsEntry2.getPdcTitle(string);
                        String pdcCount = clusterResultsEntry2.getPdcCount(string);
                        long[] pdcTime = clusterResultsEntry2.getPdcTime(string);
                        long j13 = pdcTime != null ? pdcTime[0] : 0;
                        if (pdcTime != null) {
                            j12 = pdcTime[1];
                        }
                        long j14 = j13;
                        j11 = j12;
                        j12 = j14;
                        string3 = pdcCount;
                    } else {
                        str3 = string2;
                        j11 = 0;
                    }
                    String str17 = string;
                    if ("ocrtext".equals(str15)) {
                        str3 = clusterResultsEntry2.getOcrTitle();
                        string3 = clusterResultsEntry2.getOcrCount();
                    }
                    if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && ("pet_cluster".equals(str15) || "person_cluster".equals(str15))) {
                        str15 = "creature_cluster";
                    }
                    String str18 = str15;
                    if (i13 < 0) {
                        str4 = null;
                    } else {
                        str4 = cursor.getString(cursor.getColumnIndex("__mainCategory"));
                    }
                    if (i14 < 0) {
                        str5 = null;
                    } else if (j2 > -1) {
                        str5 = IdentityCreatureUtil.create(j2, j8, j3, i7 == 0 ? IdentityCreatureUtil.Category.PEOPLE : IdentityCreatureUtil.Category.PET);
                    } else {
                        str5 = cursor.getString(cursor.getColumnIndex("__subCategory"));
                    }
                    String str19 = str4;
                    String str20 = str14;
                    if (columnIndex5 < 0) {
                        str6 = null;
                    } else {
                        str6 = cursor.getString(cursor.getColumnIndex(str20));
                    }
                    str14 = str20;
                    if (i11 < 0) {
                        str7 = str13;
                        str8 = str6;
                        str9 = null;
                    } else {
                        String str21 = str13;
                        str8 = str6;
                        str9 = cursor.getString(cursor.getColumnIndex(str21));
                        str7 = str21;
                    }
                    String string4 = cursor.getString(cursor.getColumnIndex("__albumID"));
                    String str22 = str12;
                    String str23 = str9;
                    if (i12 >= 0) {
                        str16 = cursor.getString(cursor.getColumnIndex(str22));
                    }
                    String str24 = str22;
                    String str25 = str19;
                    String str26 = string3;
                    String str27 = str3;
                    arrayList = arrayList3;
                    arrayList.add(new Object[]{str27, str26, str25, str5, str8, str23, string4, str16, str17, cursor.getString(cursor.getColumnIndex("__absPath")), cursor.getString(cursor.getColumnIndex("__orientation")), Long.valueOf(j2), Integer.valueOf(i7), Long.valueOf(j3), Long.valueOf(j8), str, cursor.getString(cursor.getColumnIndex("__width")), cursor.getString(cursor.getColumnIndex("__height")), cursor.getString(cursor.getColumnIndex("__size")), Integer.valueOf(i8), str18, Long.valueOf(j10), Integer.valueOf(i15), str2, Double.valueOf(d), Double.valueOf(d2), Long.valueOf(j12), Long.valueOf(j11)});
                    if (!cursor.moveToNext()) {
                        break;
                    }
                    arrayList3 = arrayList;
                    str13 = str7;
                    str15 = str18;
                    columnIndex7 = i16;
                    columnIndex14 = i17;
                    str12 = str24;
                    clusterResultsEntry2 = clusterResultsEntry;
                }
            }
            i10 = i2 + 1;
            cursorArr2 = cursorArr;
            arrayList2 = arrayList;
            clusterResultsEntry2 = clusterResultsEntry;
        }
        return arrayList2;
    }

    /* access modifiers changed from: private */
    public void publishAlbumClusterCategoryData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishAlbumClusterCategoryData skip. There is no Id list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishAlbumClusterCategoryData");
        keywordAlbumQuery(bundle, true);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishKeywordAlbumsData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishKeywordAlbumsData skip. There is no Id list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishKeywordAlbumsData");
        keywordAlbumQuery(bundle, false);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishKeywordLocationsData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishKeywordLocationsData skip. There is no Id list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishKeywordLocationsData");
        keywordLocationQuery(bundle, false);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishKeywordOCRsData(Object obj, Bundle bundle) {
        int i2;
        if (bundle == null || bundle.get("ids") == null) {
            Log.e(this.TAG, "publishKeywordOCRsData skip. There is no Id list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishKeywordOCRsData");
        String str = (String) bundle.get("ids");
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            i2 = -1;
        } else {
            i2 = 5;
        }
        publishCursorArray("location://search/fileList/KeywordOcrs", new Cursor[]{clusterListDataQuery(str, i2)});
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishKeywordPeoplesData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishKeywordPeoplesData skip. There is no name list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishKeywordPeoplesData");
        keywordPeopleQuery(bundle, false);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishLocationClusterCategoryData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishLocationClusterCategoryData skip. There is no Id list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishLocationClusterCategoryData");
        keywordLocationQuery(bundle, true);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishPeopleClusterCategoryData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("name") == null) {
            Log.e(this.TAG, "publishPeopleClusterCategoryData skip. There is no name list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishPeopleClusterCategoryData");
        keywordPeopleQuery(bundle, true);
        Trace.endSection();
    }

    private void publishWithoutFilterRequest(String str, Bundle bundle, String str2, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = 1;
        Cursor[][] cursorArr = (Cursor[][]) Array.newInstance(Cursor.class, iArr);
        Cursor[][] cursorArr2 = cursorArr;
        new LatchBuilder("").setCurrent(new M(this, cursorArr2, str, str2, str3, bundle, 1)).setPostExecutor((Runnable) new b0(this, cursorArr, currentTimeMillis, str, bundle, str2)).start();
    }

    public void addSearchCategorySubscribers(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/MyTag/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/ShotMode/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/People/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Pet/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Expressions/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Location/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Documents/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Scene/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Things/#"), new Y(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/Scenery/#"), new Y(this, 12)));
        if (Features.isEnabled(Features.SUPPORT_PDC_RELATIONSHIP)) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/RelationshipPreview/#"), new Y(this, 7)));
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/Category/ScreenShot/#"), new Y(this, 8)));
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordAlbums"), new Y(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/ClusterCategoryAlbum"), new Y(this, 13)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordPeoples"), new Y(this, 14)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/ClusterCategoryPeople"), new Y(this, 15)));
        if (Features.isEnabled(Features.SUPPORT_LOCATION) && exposeLocationCategory()) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordLocations"), new Y(this, 16)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/ClusterCategoryLocation"), new Y(this, 1)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/LocationAllPictures"), new Y(this, 2)));
        }
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordOcrs"), new Y(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/KeywordClusterPictures"), new Y(this, 4)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/PeopleAllPictures"), new Y(this, 5)));
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/TopResultsPictures"), new Y(this, 6)));
        }
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/CarouselCluster"), new Y(this, 9)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/PeopleSelectForRelation"), new Y(this, 10)));
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/fileList/PdcPictures"), new Y(this, 11)));
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CREATURE_COVER_CHOICE) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://search/CreatureCoverChoice"), new Y(this, 12)));
        }
    }

    public String getAdditionalInfo(Bundle bundle, String str) {
        if (LocationKey.isSearchCategoryLocation(str)) {
            return bundle.getString("term");
        }
        if (LocationKey.isSearchCategoryCreature(str)) {
            return bundle.getString("title");
        }
        if (LocationKey.isSearchCategoryOtherScene(str)) {
            return "Things & Scenery";
        }
        if (LocationKey.isSearchCategoryThings(str)) {
            return "Things";
        }
        if (LocationKey.isSearchCategoryOtherScenery(str)) {
            return "Scenery";
        }
        return null;
    }

    public Cursor[] getFileIdsCursor(String str, String str2, String str3, String str4, String str5, int i2) {
        boolean z;
        QueryParams createCommonQueryParams = createCommonQueryParams(str5);
        if (str3 != null) {
            z = true;
        } else {
            z = false;
        }
        StorageRetrieval retriever = getRetriever(createCommonQueryParams, z);
        SearchFilter.Builder fromInstantSearch = new SearchFilter.Builder(str2).selectedFilter(str3).fromInstantSearch(ArgumentsUtil.getArgValue(str, "from_instant_search", false));
        if (LocationKey.isSearchCategoryLocation(str)) {
            return retriever.getLocationFileIds(str2, fromInstantSearch, str4, i2);
        }
        if (LocationKey.isCreatureCoverChoice(str)) {
            if (IdentityCreatureUtil.isPerson(str2)) {
                return retriever.getPeopleFileIds(str2, fromInstantSearch, str4, i2);
            }
            return retriever.getPetFileIds(str2, fromInstantSearch, str4, i2);
        } else if (LocationKey.isSearchCategoryPeople(str)) {
            return retriever.getPeopleFileIds(str2, fromInstantSearch, str4, i2);
        } else {
            if (LocationKey.isSearchCategoryPet(str)) {
                return retriever.getPetFileIds(str2, fromInstantSearch, str4, i2);
            }
            if (LocationKey.isSearchCategoryMyTag(str)) {
                return retriever.getMyTagsFileIds(str2, fromInstantSearch, i2);
            }
            if (LocationKey.isSearchCategoryShotMode(str)) {
                return retriever.getShotModeFileIds(str2, fromInstantSearch, i2);
            }
            if (LocationKey.isSearchCategoryExpressions(str)) {
                return retriever.getExpressionFileIds(str2, fromInstantSearch, i2);
            }
            if (LocationKey.isSearchCategoryDocuments(str)) {
                return retriever.getDocumentFileIds(str2, fromInstantSearch, i2);
            }
            if (LocationKey.isSearchCategoryOtherScene(str) || LocationKey.isSearchCategoryThings(str) || LocationKey.isSearchCategoryOtherScenery(str)) {
                return retriever.getSceneFileIds(str2, fromInstantSearch, str4, i2);
            }
            if (LocationKey.isSearchRelationshipPreview(str)) {
                return retriever.getPeopleFileIdsMultiple(str2, fromInstantSearch, i2);
            }
            return super.getFileIdsCursor(str, str2, str3, str4, str5, i2);
        }
    }

    public Cursor[] getFilesCursor(String str, String str2, String str3, String str4) {
        boolean z;
        QueryParams createCommonQueryParams = createCommonQueryParams();
        if (str3 != null) {
            z = true;
        } else {
            z = false;
        }
        StorageRetrieval retriever = getRetriever(createCommonQueryParams, z);
        SearchFilter.Builder fromInstantSearch = new SearchFilter.Builder(str2).selectedFilter(str3).fromInstantSearch(ArgumentsUtil.getArgValue(str, "from_instant_search", false));
        if (LocationKey.isSearchCategoryLocation(str)) {
            return retriever.getLocationFiles(str2, fromInstantSearch, str4);
        }
        if (LocationKey.isSearchCategoryPeople(str)) {
            return retriever.getPeopleFiles(str2, fromInstantSearch, str4);
        }
        if (LocationKey.isSearchCategoryPet(str)) {
            return retriever.getPetFiles(str2, fromInstantSearch, str4);
        }
        if (LocationKey.isSearchCategoryMyTag(str)) {
            return retriever.getMyTagsFiles(str2, fromInstantSearch);
        }
        if (LocationKey.isSearchCategoryShotMode(str)) {
            return retriever.getShotModeFiles(str2, fromInstantSearch);
        }
        if (LocationKey.isSearchCategoryExpressions(str)) {
            return retriever.getExpressionFiles(str2, fromInstantSearch);
        }
        if (LocationKey.isSearchCategoryDocuments(str)) {
            return retriever.getDocumentFiles(str2, fromInstantSearch);
        }
        if (LocationKey.isSearchCategoryOtherScene(str) || LocationKey.isSearchCategoryThings(str) || LocationKey.isSearchCategoryOtherScenery(str)) {
            return retriever.getSceneFiles(str2, fromInstantSearch, str4);
        }
        if (LocationKey.isSearchRelationshipPreview(str)) {
            return retriever.getPeopleFilesMultiple(str2, fromInstantSearch);
        }
        return super.getFilesCursor(str, str2, str3, str4);
    }

    public void publishCarouselClusterData(Object obj, Bundle bundle) {
        if (bundle == null) {
            Log.e(this.TAG, "publishCarouselClusterData skip");
            releaseRequestKey(bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Cursor[] cursorArr = new Cursor[ClusterTypeHolder.types.length];
        ClusterResultsEntry clusterResultsEntry = (ClusterResultsEntry) getBlackboard().read("data://user/SearchClusterAllEntry");
        if (clusterResultsEntry != null) {
            LatchBuilder latchBuilder = new LatchBuilder("CarouselClusterQuery");
            latchBuilder.setCurrent(new Z(this, cursorArr, clusterResultsEntry, 0)).addWorker(new Z(this, cursorArr, clusterResultsEntry, 1)).addWorker(new Z(this, cursorArr, clusterResultsEntry, 2));
            if (!TextUtils.isEmpty(clusterResultsEntry.getOcrIds())) {
                latchBuilder.addWorker(new Z(this, cursorArr, clusterResultsEntry, 3));
            }
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                latchBuilder.addWorker(new Z(this, cursorArr, clusterResultsEntry, 4)).addWorker(new Z(this, cursorArr, clusterResultsEntry, 5));
            }
            if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                clusterResultsEntry.setPdcList();
                String pdcIds = clusterResultsEntry.getPdcIds();
                if (!TextUtils.isEmpty(pdcIds)) {
                    latchBuilder.addWorker(new C0636p(this, cursorArr, pdcIds, 4));
                }
            }
            latchBuilder.setPostExecutor((Runnable) new W(this, cursorArr, clusterResultsEntry, currentTimeMillis, bundle)).start();
        }
    }

    public void publishKeywordPdcFiles(Object obj, Bundle bundle) {
        if (bundle == null || bundle.getString("search_pdc_timerange") == null) {
            Log.e(this.TAG, "publishKeywordPdcFiles skip.");
            releaseRequestKey(bundle);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        String[] split = bundle.getString("search_pdc_timerange").split(";");
        QueryParams queryParams = new QueryParams(DbKey.ALBUM_FILES);
        Cursor query = DbCompat.query(queryParams.setDataLike(StorageInfo.getDefault().camera + "/%").setPdcFiles(true).setCreationTimeLimit(Long.parseLong(split[0]), Long.parseLong(split[1])));
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishKeywordPdcFiles " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray("location://search/fileList/PdcPictures", new Cursor[]{query});
    }

    public void publishPeopleSelectForRelation(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        String string = bundle.getString("people_ids", "");
        Cursor[] cursorArr = new Cursor[1];
        new LatchBuilder("publishPeopleSelectForRelation").setCurrent(new a0(cursorArr, string, 0)).addWorker(new r(3, this, string)).start();
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "publishPeopleSelectForRelation " + getCursorInfo(cursorArr[0], currentTimeMillis));
        publishCursorArray("location://search/fileList/PeopleSelectForRelation", new Cursor[]{cursorArr[0]});
    }

    public void publishRelationshipPreviewData(Object obj, Bundle bundle) {
        String locationKeyFromReq = getLocationKeyFromReq(bundle);
        if (!TextUtils.equals(ArgumentsUtil.removeArgs(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard)), ArgumentsUtil.removeArgs(locationKeyFromReq))) {
            this.mBlackboard.erase(CommandKey.DATA_REQUEST(locationKeyFromReq));
            this.mBlackboard.erase(ArgumentsUtil.getSubscribeKey(bundle));
        } else if (bundle.getString("sub") == null || !needKeywordSearchOnSupportMultipleKeyword(bundle)) {
            String string = bundle.getString("selected_creature_unified_ids");
            if (TextUtils.isEmpty(string)) {
                publishCursorArray(locationKeyFromReq, getEmptyCursor(), ArgumentsUtil.getSubscribeKey(bundle));
            } else {
                publishWithoutFilterRequest(locationKeyFromReq, bundle, string, (String) null);
            }
        } else {
            publishKeywordFiles(obj, bundle);
        }
    }

    public void publishTopResultsData(Object obj, Bundle bundle) {
        if (bundle == null || bundle.get("ids") == null) {
            Log.e(this.TAG, "publishTopResultsData skip. There is no Id list");
            releaseRequestKey(bundle);
            return;
        }
        Trace.beginSection("publishTopResultsData");
        publishCursorArray("location://search/fileList/TopResultsPictures", new Cursor[]{clusterListDataQuery((String) bundle.get("ids"), 8)});
        Trace.endSection();
    }
}
