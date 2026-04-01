package com.samsung.android.gallery.app.provider;

import A4.P;
import N2.j;
import V8.a;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.search.engine.BaseSearchEngine;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.module.search.root.SearchParser;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import i.C0212a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySearchProvider extends ContentProvider {
    static final String[] COLUMNS = {"suggest_text_1", "suggest_icon_1", "suggest_target_type", "suggest_icon_2", "suggest_intent_data", "suggest_intent", "suggest_intent_data_id", "suggest_intent_extra", "suggest_text_2"};
    static final String[] HISTORY_COLUMNS = {"title"};
    protected final String TAG = getClass().getSimpleName();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HistoryPackageHolder {
        static final ArrayList<String> PACKAGES;

        static {
            ArrayList<String> arrayList = new ArrayList<>();
            PACKAGES = arrayList;
            arrayList.add("com.sec.android.gallery3d");
            arrayList.add("com.samsung.android.rubin.app");
            arrayList.add("com.samsung.android.app.galaxyfinder");
            arrayList.add("com.sec.android.app.launcher");
            arrayList.add("com.samsung.android.app.searchwidget");
            arrayList.add("com.samsung.android.video");
            arrayList.add("com.samsung.android.honeyboard");
            if (DeviceConfig.DEBUG_BINARY) {
                arrayList.add("com.samsung.android.app.gallerysearchtest");
            }
        }

        public static boolean isSystemPackage(String str) {
            if (str == null || !PACKAGES.contains(str) || !SignatureChecker.checkSignature(AppResources.getAppContext(), str)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UriMatcherHolder {
        static final UriMatcher URI_MATCHER;

        static {
            UriMatcher uriMatcher = new UriMatcher(-1);
            URI_MATCHER = uriMatcher;
            uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider", "search_suggest_regex_query", 4);
            uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider", "search_suggest_regex_query/*", 4);
            uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider", "search_suggest_tag_query", 6);
            uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider", "search_suggest_tag_query/*", 6);
            uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider", "search_suggest_history", 7);
            uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider", "search_suggest_history/*", 7);
        }
    }

    private Object[] createColumnValues(Cursor cursor, boolean z) {
        try {
            MediaItem create = MediaItemBuilder.create(cursor);
            if (!z && create.isDrm()) {
                return null;
            }
            String displayName = create.getDisplayName();
            String uriString = ContentUri.getUriString(create);
            return new Object[]{displayName, uriString, Integer.valueOf(create.getMediaType().toInt()), uriString, uriString, null, null, null, Long.valueOf((long) create.getFileDuration())};
        } catch (Exception e) {
            Log.w((CharSequence) this.TAG, "Can not create columnValues", (Throwable) e);
            return null;
        }
    }

    private void dumpFile(PrintWriter printWriter, String str) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                printWriter.println(readLine);
            }
            bufferedReader.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static String dumpNoMediaPath() {
        StringBuilder sb2 = new StringBuilder();
        try {
            for (String next : StorageInfo.getDefault().getMajorList()) {
                if (new File(next + File.separator + ".nomedia").exists()) {
                    sb2.append(next);
                    sb2.append("\n");
                }
            }
            FileUtils.getSdcardDirList().stream().map(new a(10)).forEach(new P(sb2, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb2.toString();
    }

    private Cursor emptyHistoryCursor() {
        return new MatrixCursor(HISTORY_COLUMNS, 0);
    }

    private Cursor getImages(Uri uri, String str, String[] strArr) {
        Cursor complexSearch;
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Unknown URL " + uri);
        }
        Context context = getContext();
        if (context == null) {
            Log.e(this.TAG, "getImages failed. null context");
            return null;
        }
        SearchParser parse = new SearchParser().parse(str, strArr);
        String subCategory = getSubCategory(parse.getLocationKey());
        boolean supportDrm = supportDrm(parse.getLocationKey());
        try {
            complexSearch = createSearchEngine(context).complexSearch(new SearchFilter.Builder(subCategory).period(new long[]{Long.parseLong(parse.getFromTime()), Long.parseLong(parse.getToTime())}).mediaType(parse.getMediaType()).locationKey(parse.getLocationKey()).withUri(uri).build(context));
            if (complexSearch != null) {
                MatrixCursor tableForRegexForTagInfo = setTableForRegexForTagInfo(complexSearch, 200, supportDrm);
                complexSearch.close();
                return tableForRegexForTagInfo;
            }
            if (complexSearch != null) {
                complexSearch.close();
            }
            return null;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("getImages failed. e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Cursor getSearchHistory(String[] strArr, String str, String[] strArr2, String str2) {
        if (getContext() != null) {
            return SearchWordCollector.getInstance().query(strArr, str, strArr2, str2);
        }
        Log.e(this.TAG, "getSearchHistory failed. null context");
        return emptyHistoryCursor();
    }

    private String getSubCategory(String str) {
        return ArgumentsUtil.getArgValue(str, "sub", (String) null);
    }

    private boolean isDisabledTagSearch(SearchParser searchParser, String str) {
        if (searchParser.isSupportTagSearch() || str == null || !"videos".contentEquals(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$dumpNoMediaPath$0(String str, StringBuilder sb2, StorageInfo storageInfo) {
        for (String next : storageInfo.getMajorList()) {
            if (new File(C0212a.p(C0212a.s(next), File.separator, str)).exists()) {
                sb2.append(next);
                sb2.append("\n");
            }
        }
    }

    public static String replaceDateTimeQ(String str) {
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            return str.replace(IParameterKey.DATE_TAKEN, IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME);
        }
        return str;
    }

    public static String replaceGroupIdQ(String str) {
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            return str.replace(BundleKey.GROUP_ID, "burst_group_id");
        }
        return str;
    }

    private MatrixCursor setTableForRegexForTagInfo(Cursor cursor, int i2, boolean z) {
        if (i2 == -1) {
            i2 = 200;
        }
        MatrixCursor matrixCursor = new MatrixCursor(COLUMNS, i2);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Object[] createColumnValues = createColumnValues(cursor, z);
                        if (createColumnValues != null) {
                            matrixCursor.addRow(createColumnValues);
                        }
                    } while (cursor.moveToNext());
                    if (PreferenceFeatures.isEnabled(PreferenceFeatures.SseDebug)) {
                        matrixCursor.setExtras(cursor.getExtras());
                        return matrixCursor;
                    }
                }
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "Can not setTableForRegexForTagInfo", (Throwable) e);
            }
        }
        return matrixCursor;
    }

    private boolean supportDrm(String str) {
        return LocationKey.isVideoPictures(str);
    }

    public void assertSystemPackage() {
        String callingPackage = getCallingPackage();
        if (!"com.sec.android.gallery3d".equals(callingPackage) && !HistoryPackageHolder.isSystemPackage(callingPackage)) {
            throw new SecurityException(C0212a.A(callingPackage, " is not allowed"));
        }
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        assertSystemPackage();
        if (!"getInAppSearchIntent".equals(str)) {
            return null;
        }
        Intent intent = new Intent("com.android.gallery.action.SEARCH_FROM_GALAXY_FINDER_VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("inAppSearchIntent", intent);
        return bundle2;
    }

    public BaseSearchEngine createSearchEngine(Context context) {
        BaseSearchEngine create = SearchEngineFactory.create(context);
        create.clearCache();
        return create;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|(3:10|11|12)|13|15|16|17|18|19|(4:21|22|(1:24)|25)(3:26|(1:28)(1:29)|30)|31|(3:33|34|36)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x006f */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0079 A[SYNTHETIC, Splitter:B:21:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b2 A[Catch:{ Exception -> 0x00d0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dump(java.io.FileDescriptor r1, java.io.PrintWriter r2, java.lang.String[] r3) {
        /*
            r0 = this;
            java.lang.String r1 = "== cloud hidden=="
            r2.println(r1)     // Catch:{ Exception -> 0x0013 }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r1 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x0013 }
            r1.<init>()     // Catch:{ Exception -> 0x0013 }
            java.lang.String r3 = "select _id,media_type,is_cloud,_data as dx from files where is_cloud=2 and cloud_thumb_path is null"
            java.lang.String r1 = r1.logQuery(r3)     // Catch:{ Exception -> 0x0013 }
            r2.print(r1)     // Catch:{ Exception -> 0x0013 }
        L_0x0013:
            java.lang.String r1 = "== dateTaken(dateTime) =="
            r2.println(r1)     // Catch:{ Exception -> 0x002a }
            java.lang.String r1 = "select _id,media_type,date_modified,date_added, datetaken,_data from files where (datetaken is null or (datetaken=1000 and _size<>9015) or datetaken <=-62167219200000) and media_type in (1,3) limit 0,10"
            java.lang.String r1 = replaceDateTimeQ(r1)     // Catch:{ Exception -> 0x002a }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r3 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x002a }
            r3.<init>()     // Catch:{ Exception -> 0x002a }
            java.lang.String r1 = r3.logQuery(r1)     // Catch:{ Exception -> 0x002a }
            r2.print(r1)     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            com.samsung.android.gallery.support.config.SdkConfig$GED r1 = com.samsung.android.gallery.support.config.SdkConfig.GED.T
            boolean r1 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r1)
            if (r1 != 0) goto L_0x004d
            java.lang.String r1 = "== group_id =="
            r2.println(r1)     // Catch:{ Exception -> 0x004d }
            java.lang.String r1 = "select _id,media_type,group_id, datetaken,_data from files where group_id is null and media_type in (1,3) limit 0,10"
            java.lang.String r1 = replaceDateTimeQ(r1)     // Catch:{ Exception -> 0x004d }
            java.lang.String r1 = replaceGroupIdQ(r1)     // Catch:{ Exception -> 0x004d }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r3 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x004d }
            r3.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.String r1 = r3.logQuery(r1)     // Catch:{ Exception -> 0x004d }
            r2.print(r1)     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            java.lang.String r1 = "== hidden album =="
            r2.println(r1)     // Catch:{ Exception -> 0x006f }
            java.lang.String r1 = "select bucket_display_name,count() from files where datetaken is not null and is_hide = 1 group by bucket_id"
            java.lang.String r1 = replaceDateTimeQ(r1)     // Catch:{ Exception -> 0x006f }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r3 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x006f }
            r3.<init>()     // Catch:{ Exception -> 0x006f }
            java.lang.String r1 = r3.logQuery(r1)     // Catch:{ Exception -> 0x006f }
            r2.print(r1)     // Catch:{ Exception -> 0x006f }
            android.content.Context r1 = r0.getContext()     // Catch:{ Exception -> 0x006f }
            java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.getHiddenLogFile(r1)     // Catch:{ Exception -> 0x006f }
            r0.dumpFile(r2, r1)     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            com.samsung.android.gallery.support.config.SdkConfig$GED r0 = com.samsung.android.gallery.support.config.SdkConfig.GED.R     // Catch:{ Exception -> 0x00d0 }
            boolean r0 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r0)     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = "== no media =="
            if (r0 == 0) goto L_0x00b2
            com.samsung.android.gallery.support.config.SdkConfig$GED r0 = com.samsung.android.gallery.support.config.SdkConfig.GED.T     // Catch:{ Exception -> 0x00d0 }
            boolean r0 = com.samsung.android.gallery.support.config.SdkConfig.lessThan((com.samsung.android.gallery.support.config.SdkConfig.GED) r0)     // Catch:{ Exception -> 0x00d0 }
            if (r0 == 0) goto L_0x008b
            r2.println(r1)     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r0 = dumpNoMediaPath()     // Catch:{ Exception -> 0x00d0 }
            r2.print(r0)     // Catch:{ Exception -> 0x00d0 }
        L_0x008b:
            java.lang.String r0 = "== null data =="
            r2.println(r0)     // Catch:{ Exception -> 0x00d0 }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r0 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00d0 }
            r0.<init>()     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = "select _id,media_id,_data as dx,owner_package_name from files where is_pending is null or datetime is null limit 0,10"
            java.lang.String r0 = r0.logQuery(r1)     // Catch:{ Exception -> 0x00d0 }
            r2.print(r0)     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r0 = "== zero size file =="
            r2.println(r0)     // Catch:{ Exception -> 0x00d0 }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r0 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00d0 }
            r0.<init>()     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = "select _id,media_id,_data as dx,_size,owner_package_name from files where _size is null or _size=0 limit 0,10"
            java.lang.String r0 = r0.logQuery(r1)     // Catch:{ Exception -> 0x00d0 }
            r2.print(r0)     // Catch:{ Exception -> 0x00d0 }
            goto L_0x00d0
        L_0x00b2:
            r2.println(r1)     // Catch:{ Exception -> 0x00d0 }
            com.samsung.android.gallery.support.config.SdkConfig$GED r0 = com.samsung.android.gallery.support.config.SdkConfig.GED.Q     // Catch:{ Exception -> 0x00d0 }
            boolean r0 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r0)     // Catch:{ Exception -> 0x00d0 }
            if (r0 == 0) goto L_0x00c2
            java.lang.String r0 = dumpNoMediaPath()     // Catch:{ Exception -> 0x00d0 }
            goto L_0x00cd
        L_0x00c2:
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r0 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00d0 }
            r0.<init>()     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r1 = "select _data as dx,count() from files where media_type = 0 and mime_type like 'image%' and bucket_display_name not like '.%' group by bucket_id"
            java.lang.String r0 = r0.logQuery(r1)     // Catch:{ Exception -> 0x00d0 }
        L_0x00cd:
            r2.print(r0)     // Catch:{ Exception -> 0x00d0 }
        L_0x00d0:
            java.lang.String r0 = "== group_type =="
            r2.println(r0)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r0 = "select _id,group_type,group_id, datetaken,_data from files where group_id>0 and group_type is null limit 0,10"
            java.lang.String r0 = replaceDateTimeQ(r0)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r0 = replaceGroupIdQ(r0)     // Catch:{ Exception -> 0x00eb }
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r1 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00eb }
            r1.<init>()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r0 = r1.logQuery(r0)     // Catch:{ Exception -> 0x00eb }
            r2.print(r0)     // Catch:{ Exception -> 0x00eb }
        L_0x00eb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.provider.GallerySearchProvider.dump(java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    public String getType(Uri uri) {
        int match = match(uri);
        if (match == 4 || match == 6) {
            return "vnd.android.cursor.dir/vnd.android.search.suggest";
        }
        if (match == 7) {
            return null;
        }
        throw new IllegalArgumentException("Unknown URL " + uri);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    public int match(Uri uri) {
        return UriMatcherHolder.URI_MATCHER.match(uri);
    }

    /* JADX INFO: finally extract failed */
    public boolean onCreate() {
        try {
            Trace.beginSection("APP_GallerySearchProvider onCreate");
            AppResources.setAppContext(getContext());
            Trace.endSection();
            return true;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        assertSystemPackage();
        FileUtils.initializeIfAbsent(getContext());
        int match = match(uri);
        if (match == 4 || match == 6) {
            return getImages(uri);
        }
        if (match != 7) {
            return getImages(uri, str, strArr2);
        }
        return getSearchHistory(strArr, str, strArr2, str2);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    private Cursor getImages(Uri uri) {
        Cursor search;
        SearchParser parse = new SearchParser().parse(uri);
        boolean isSupportTagSearch = parse.isSupportTagSearch();
        String searchFilter = parse.getSearchFilter();
        if (!isDisabledTagSearch(parse, searchFilter) && !parse.isEmptyKeyString()) {
            Context context = getContext();
            if (context == null) {
                Log.e(this.TAG, "getImages failed. null context");
                return null;
            }
            try {
                search = createSearchEngine(context).search(new SearchFilter.Builder(parse.getKeyString()).filterOutCloudVideo().build(context));
                if (search != null && (searchFilter == null || !isSupportTagSearch)) {
                    MatrixCursor tableForRegexForTagInfo = setTableForRegexForTagInfo(search, parse.getLimit(), false);
                    search.close();
                    return tableForRegexForTagInfo;
                } else if (search != null) {
                    search.close();
                }
            } catch (Exception e) {
                j.v("Can not getImages.", e, this.TAG);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return null;
        throw th;
    }
}
