package com.samsung.android.gallery.module.search.engine;

import A6.a;
import B5.c;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.database.dal.mp.helper.SearchApi;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentExpandedSearchEngine extends IntelligentSearchEngine {
    public IntelligentExpandedSearchEngine(Context context, IntelligentSearch intelligentSearch) {
        super(context, intelligentSearch);
    }

    private void addIncludeIds(String str, ArrayList<Long> arrayList) {
        if (!TextUtils.isEmpty(str)) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, NumericEnum.SEP);
            while (stringTokenizer.hasMoreTokens()) {
                StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                ArrayList arrayList2 = new ArrayList();
                int i2 = -1;
                while (stringTokenizer2.hasMoreTokens()) {
                    long parseLong = Long.parseLong(stringTokenizer2.nextToken());
                    int indexOf = arrayList.indexOf(Long.valueOf(parseLong));
                    if (indexOf >= 0) {
                        if (!arrayList2.isEmpty()) {
                            arrayList.addAll(indexOf, arrayList2);
                            indexOf += arrayList2.size();
                            arrayList2.clear();
                        }
                        i2 = indexOf + 1;
                    } else if (i2 < 0) {
                        arrayList2.add(Long.valueOf(parseLong));
                    } else {
                        arrayList.add(i2, Long.valueOf(parseLong));
                        i2++;
                    }
                }
            }
        }
    }

    private Cursor getAllDatesCursor(QueryParams queryParams) {
        return new SearchApi(queryParams).getAllDatesCursor();
    }

    private IntelligentSearchEngine.SearchResult getSearchExpandedResult(SearchFilter searchFilter, QueryParams queryParams, AtomicReference<Cursor> atomicReference) {
        String str;
        ExtraResults extraResults;
        Cursor[] cursorArr;
        SearchFilter searchFilter2;
        String rawKeyword = searchFilter.getRawKeyword();
        String selectedFilter = searchFilter.getSelectedFilter();
        StringBuilder s = C0212a.s(rawKeyword);
        if (selectedFilter != null) {
            str = selectedFilter;
        } else {
            str = "";
        }
        s.append(str);
        String sb2 = s.toString();
        if (!TextUtils.isEmpty(searchFilter.getExpandedDates())) {
            StringBuilder s5 = C0212a.s(sb2);
            s5.append(searchFilter.getExpandedDates());
            sb2 = s5.toString();
        }
        String str2 = sb2;
        String ids = SearchResultCache.getInstance().getIds(str2);
        ExtraResults extraResults2 = SearchResultCache.getInstance().getExtraResults(str2);
        if (TextUtils.isEmpty(ids) || extraResults2 == null) {
            String cachedResult = this.mIntelligentSearch.getCachedResult(str2);
            stampStartingLog(rawKeyword);
            Cursor[] cursorArr2 = {null};
            ExtraResults extraResults3 = new ExtraResults();
            if (cachedResult == null) {
                AtomicReference atomicReference2 = new AtomicReference(new ArrayList());
                cursorArr = cursorArr2;
                extraResults = extraResults3;
                AtomicReference atomicReference3 = atomicReference2;
                new LatchBuilder("getSearchExpandedResult").setCurrent(new c(this, cursorArr2, searchFilter, extraResults3, atomicReference2, 15)).addWorker(new a((Object) this, (Object) searchFilter, (Object) queryParams, (Object) atomicReference, 23)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
                if (!Features.isEnabled(Features.SUPPORT_DYNAMIC_SEARCH_SUGGESTION)) {
                    searchFilter2 = searchFilter;
                    searchSuggestionKeywords(rawKeyword, searchFilter2, (ArrayList) atomicReference3.get());
                } else {
                    searchFilter2 = searchFilter;
                }
                addIncludeIds(searchFilter2.getIncludeIds(), (ArrayList) atomicReference3.get());
                cachedResult = getJoinedString((ArrayList) atomicReference3.get());
                if (!TextUtils.isEmpty(extraResults.getFuzzyKeyword())) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(extraResults.getFuzzyKeyword());
                    if (selectedFilter == null) {
                        selectedFilter = "";
                    }
                    sb3.append(selectedFilter);
                    str2 = sb3.toString();
                    if (!TextUtils.isEmpty(searchFilter2.getExpandedDates())) {
                        StringBuilder s6 = C0212a.s(str2);
                        s6.append(searchFilter2.getExpandedDates());
                        str2 = s6.toString();
                    }
                }
                setScsQueryExtra(cursorArr[0], extraResults);
                this.mIntelligentSearch.saveCacheResult(str2, cachedResult);
                SearchResultCache.getInstance().put(str2, cachedResult, extraResults);
            } else {
                cursorArr = cursorArr2;
                extraResults = extraResults3;
            }
            if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
                return new IntelligentSearchEngine.SearchResult(cursorArr[0], cachedResult, extraResults);
            }
            Utils.closeSilently(cursorArr[0]);
            return new IntelligentSearchEngine.SearchResult(cachedResult, extraResults);
        }
        extraResults2.setFuzzySuggestKeyword((String) null);
        return new IntelligentSearchEngine.SearchResult(ids, extraResults2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getSearchExpandedResult$5(Cursor[] cursorArr, SearchFilter searchFilter, ExtraResults extraResults, AtomicReference atomicReference) {
        ArrayList arrayList = new ArrayList();
        Cursor queryInternal = queryInternal(searchFilter, extraResults);
        cursorArr[0] = queryInternal;
        if (queryInternal != null) {
            getIdsNExtraResult(queryInternal, extraResults, arrayList, searchFilter);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        if (needToCheckFileIdValidation(searchFilter)) {
            String checkFileIdValidation = checkFileIdValidation(searchFilter, getJoinedString(arrayList));
            if (!TextUtils.isEmpty(checkFileIdValidation)) {
                atomicReference.set((ArrayList) Stream.of(checkFileIdValidation.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new Gb.a(9)).collect(Collectors.toList()));
                return;
            }
            return;
        }
        atomicReference.set(arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchExpandedResult$0(Cursor[] cursorArr, SearchFilter searchFilter, IntelligentSearchEngine.SearchResult searchResult, QueryParams queryParams) {
        cursorArr[0] = getDataCursor(searchFilter, searchResult, queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchExpandedResult$1(SearchFilter searchFilter, Cursor[] cursorArr, IntelligentSearchEngine.SearchResult searchResult, QueryParams queryParams) {
        Cursor cursor;
        if (searchFilter.supportTimeline()) {
            cursorArr[1] = getDateCursor(searchFilter, searchResult, queryParams);
        }
        if (searchFilter.isForQueryOnDemand()) {
            if (searchFilter.supportTimeline()) {
                cursor = cursorArr[1];
            } else {
                cursor = getDateCursor(searchFilter, searchResult, queryParams);
            }
            cursorArr[5] = getFileIdsCursor(searchResult, cursor, searchFilter);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchExpandedResult$2(Cursor[] cursorArr, SearchFilter searchFilter, IntelligentSearchEngine.SearchResult searchResult, QueryParams queryParams) {
        cursorArr[3] = getRealRatioCursor(searchFilter, searchResult, queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchExpandedResult$3(Cursor[] cursorArr, IntelligentSearchEngine.SearchResult searchResult, AtomicReference atomicReference) {
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            cursorArr[6] = searchResult.getIntelligentSearchCursor();
            cursorArr[8] = (Cursor) atomicReference.get();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchExpandedResult$4(Cursor[] cursorArr, IntelligentSearchEngine.SearchResult searchResult) {
        cursorArr[7] = new LocationApi(new QueryParams().setFileIds(searchResult.getResultIdList())).getGpsFileGpsCursor();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.database.Cursor[], java.io.Serializable] */
    /* JADX WARNING: type inference failed for: r4v1, types: [android.database.Cursor[], java.io.Serializable] */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0062, code lost:
        if (r5.supportTimeline() != false) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor[] searchExpandedResult(com.samsung.android.gallery.database.dbtype.SearchFilter r13) {
        /*
            r12 = this;
            com.samsung.android.gallery.support.utils.PreferenceFeatures r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.LocationAuth
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r0)
            if (r0 != 0) goto L_0x0027
            java.lang.String r0 = "poitag"
            java.lang.String r1 = r13.getTerm()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r13 = r12.TAG
            java.lang.String r0 = "search skip for poi. no location auth"
            com.samsung.android.gallery.support.utils.Log.s(r13, r0)
            android.database.Cursor r12 = r12.createDummyCursor()
            r13 = 1
            android.database.Cursor[] r13 = new android.database.Cursor[r13]
            r0 = 0
            r13[r0] = r12
            return r13
        L_0x0027:
            long r0 = java.lang.System.currentTimeMillis()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r7 = r12.createQueryParams(r13)
            java.util.concurrent.atomic.AtomicReference r9 = new java.util.concurrent.atomic.AtomicReference
            r9.<init>()
            com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine$SearchResult r6 = r12.getSearchExpandedResult(r13, r7, r9)
            r2 = 9
            android.database.Cursor[] r4 = new android.database.Cursor[r2]
            com.samsung.android.gallery.support.utils.LatchBuilder r10 = new com.samsung.android.gallery.support.utils.LatchBuilder
            java.lang.String r2 = "searchExpandedResult"
            r10.<init>(r2)
            boolean r2 = r13.isForQueryOnDemand()
            if (r2 != 0) goto L_0x0056
            com.samsung.android.gallery.module.search.engine.a r2 = new com.samsung.android.gallery.module.search.engine.a
            r8 = 0
            r3 = r12
            r5 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r10.setCurrent(r2)
            goto L_0x0058
        L_0x0056:
            r3 = r12
            r5 = r13
        L_0x0058:
            boolean r12 = r5.isForQueryOnDemand()
            if (r12 != 0) goto L_0x0064
            boolean r12 = r5.supportTimeline()
            if (r12 == 0) goto L_0x0076
        L_0x0064:
            com.samsung.android.gallery.module.search.engine.a r2 = new com.samsung.android.gallery.module.search.engine.a
            r11 = r5
            r5 = r3
            r3 = r7
            r7 = r4
            r4 = r11
            r2.<init>(r3, r4, r5, r6, r7)
            r11 = r7
            r7 = r3
            r3 = r5
            r5 = r4
            r4 = r11
            r10.addWorker(r2)
        L_0x0076:
            com.samsung.android.gallery.module.search.engine.a r2 = new com.samsung.android.gallery.module.search.engine.a
            r8 = 2
            r2.<init>(r3, r4, r5, r6, r7, r8)
            com.samsung.android.gallery.support.utils.LatchBuilder r12 = r10.addWorker(r2)
            com.samsung.android.gallery.module.search.engine.b r13 = new com.samsung.android.gallery.module.search.engine.b
            r13.<init>(r4, r6, r9)
            r12.setPostExecutor((java.lang.Runnable) r13)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r12 = com.samsung.android.gallery.support.utils.PreferenceFeatures.SearchResultOnMapView
            boolean r12 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r12)
            if (r12 == 0) goto L_0x0098
            com.samsung.android.gallery.module.search.engine.c r12 = new com.samsung.android.gallery.module.search.engine.c
            r12.<init>(r4, r6)
            r10.addWorker(r12)
        L_0x0098:
            r12 = 5000(0x1388, double:2.4703E-320)
            com.samsung.android.gallery.support.utils.LatchBuilder r12 = r10.setTimeout(r12)
            r12.start()
            boolean r12 = r5.isForQueryOnDemand()
            r3.setScsExtra(r4, r6, r12)
            java.lang.String r12 = r3.TAG
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r2 = "search(Expand) "
            r13.<init>(r2)
            java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.toString((android.database.Cursor[]) r4)
            java.lang.String r3 = " +"
            java.lang.String r13 = c0.C0086a.j(r0, r2, r3, r13)
            com.samsung.android.gallery.support.utils.Log.s(r12, r13)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.engine.IntelligentExpandedSearchEngine.searchExpandedResult(com.samsung.android.gallery.database.dbtype.SearchFilter):android.database.Cursor[]");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x003b  */
    /* renamed from: setExpandedResult */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void lambda$getSearchExpandedResult$6(com.samsung.android.gallery.database.dbtype.SearchFilter r4, com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r5, java.util.concurrent.atomic.AtomicReference<android.database.Cursor> r6) {
        /*
            r3 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.database.Cursor r3 = r3.getAllDatesCursor(r5)
            if (r3 == 0) goto L_0x002e
            boolean r5 = r3.moveToFirst()
            if (r5 == 0) goto L_0x002e
        L_0x0011:
            java.lang.String r5 = "__day"
            int r5 = r3.getColumnIndex(r5)
            java.lang.String r5 = r3.getString(r5)
            java.lang.String r1 = "__fileIds"
            int r1 = r3.getColumnIndex(r1)
            java.lang.String r1 = r3.getString(r1)
            r0.put(r5, r1)
            boolean r5 = r3.moveToNext()
            if (r5 != 0) goto L_0x0011
        L_0x002e:
            r6.set(r3)
            java.lang.String r3 = r4.getExpandedDates()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0063
            java.util.StringJoiner r3 = new java.util.StringJoiner
            java.lang.String r5 = ":"
            r3.<init>(r5)
            java.lang.String r6 = r4.getExpandedDates()
            java.lang.String[] r5 = r6.split(r5)
            int r6 = r5.length
            r1 = 0
        L_0x004c:
            if (r1 >= r6) goto L_0x005c
            r2 = r5[r1]
            java.lang.Object r2 = r0.get(r2)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x004c
        L_0x005c:
            java.lang.String r3 = r3.toString()
            r4.setIncludeIds(r3)
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.engine.IntelligentExpandedSearchEngine.lambda$getSearchExpandedResult$6(com.samsung.android.gallery.database.dbtype.SearchFilter, com.samsung.android.gallery.database.dal.abstraction.query.QueryParams, java.util.concurrent.atomic.AtomicReference):void");
    }

    public String getResultIdList(SearchFilter searchFilter) {
        return getSearchExpandedResult(searchFilter, createQueryParams(searchFilter), new AtomicReference()).getResultIdList();
    }

    public Cursor[] searchForTimeline(SearchFilter searchFilter) {
        if (searchFilter.isClusterEnabled()) {
            return searchExpandedResult(searchFilter);
        }
        return super.searchForTimeline(searchFilter);
    }

    public String tag() {
        return "IntelExSearchEngine";
    }
}
