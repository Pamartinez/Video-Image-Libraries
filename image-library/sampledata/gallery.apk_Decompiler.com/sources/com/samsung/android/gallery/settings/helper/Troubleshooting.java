package com.samsung.android.gallery.settings.helper;

import A.a;
import N2.j;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import i.C0212a;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Troubleshooting {
    /* access modifiers changed from: private */
    public static final Uri FILES_TABLE_URI = MediaUri.getInstance().getFilesUri();
    public static final boolean IS_QOS = SdkConfig.atLeast(SdkConfig.GED.Q);
    /* access modifiers changed from: private */
    public static final String[] PROJECTIONS = {"_id", "_data", getDatetakenColumnName(), getGroupIdColumnName(), "bucket_id"};
    private static volatile Troubleshooting sInstance;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CloudOnlyResolver extends TroubleResolver {
        public /* synthetic */ CloudOnlyResolver(int i2) {
            this();
        }

        public String getSummary() {
            if (this.mIssues > 0) {
                return C0086a.l(new StringBuilder(), this.mIssues, " item(s) have empty cloud-data, which might cause broken image");
            }
            return super.getSummary();
        }

        public String getTitle() {
            return "Cloud contents having empty data";
        }

        public String getTodo() {
            return AppResources.getString(R$string.ts_guide_null_cloud);
        }

        public boolean hasResolver() {
            return false;
        }

        public int resolve() {
            ThreadUtil.postOnUiThread(new a(0));
            return 0;
        }

        public int test() {
            int logQuery = logQuery(MediaUri.getInstance().getSecMediaUri(), "media_type in (1,3) and is_cloud=2 and cloud_thumb_path is null");
            this.mIssues = logQuery;
            return logQuery;
        }

        private CloudOnlyResolver() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FavoriteResolver extends TroubleResolver {
        public /* synthetic */ FavoriteResolver(int i2) {
            this();
        }

        public String getSummary() {
            if (this.mIssues > 0) {
                return C0086a.l(new StringBuilder(), this.mIssues, " images or videos are marked as favorite");
            }
            return AppResources.getString(R$string.ts_none);
        }

        public String getTitle() {
            return "Contents with favorite tag";
        }

        public String getTodo() {
            return AppResources.getString(R$string.ts_guide_favorites);
        }

        public boolean requireConfirm() {
            return true;
        }

        public int resolve() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_favorite", 0);
            return update(contentValues, "media_type in (1,3) and is_favorite=1");
        }

        public int test() {
            int logQuery = logQuery(Troubleshooting.FILES_TABLE_URI, "media_type in (1,3) and is_favorite=1");
            this.mIssues = logQuery;
            return logQuery;
        }

        private FavoriteResolver() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GroupIdResolver extends TroubleResolver {
        private static final String WHERE_CLAUSE = ("media_type in (1,3) and " + Troubleshooting.getGroupIdColumnName() + " is null");

        public /* synthetic */ GroupIdResolver(int i2) {
            this();
        }

        public String getSummary() {
            if (this.mIssues > 0) {
                return C0086a.l(new StringBuilder("Database has "), this.mIssues, " item(s) that have wrong group-id");
            }
            return super.getSummary();
        }

        public String getTitle() {
            return "Invalid group-id";
        }

        public int resolve() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Troubleshooting.getGroupIdColumnName(), 0);
            return update(contentValues, WHERE_CLAUSE);
        }

        public int test() {
            int logQuery = logQuery(Troubleshooting.FILES_TABLE_URI, WHERE_CLAUSE);
            this.mIssues = logQuery;
            return logQuery;
        }

        private GroupIdResolver() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HiddenAlbumResolver extends TroubleResolver {
        private final HashMap<Integer, String> mMap;

        public /* synthetic */ HiddenAlbumResolver(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$getSummary$0(StringBuilder sb2, Integer num, String str) {
            sb2.append(Troubleshooting.getUserPath(str));
            sb2.append("\n");
        }

        public String getSummary() {
            if (this.mIssues <= 0) {
                return super.getSummary();
            }
            StringBuilder sb2 = new StringBuilder(this.mIssues + " content(s) are hidden in " + this.mMap.size() + " album(s)\n\n");
            this.mMap.forEach(new b(sb2));
            sb2.deleteCharAt(sb2.length() + -1);
            return sb2.toString();
        }

        public String getTitle() {
            return "Contents in hidden album";
        }

        public String getTodo() {
            return AppResources.getString(R$string.ts_guide_hide_album);
        }

        public boolean hasResolver() {
            return false;
        }

        public int resolve() {
            ThreadUtil.postOnUiThread(new a(1));
            return 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int test() {
            /*
                r5 = this;
                java.util.HashMap<java.lang.Integer, java.lang.String> r0 = r5.mMap
                r0.clear()
                android.net.Uri r0 = com.samsung.android.gallery.settings.helper.Troubleshooting.FILES_TABLE_URI
                java.lang.String r1 = "media_type in (1,3) and is_hide=1"
                android.database.Cursor r0 = r5.query(r0, r1)
                r1 = 0
                if (r0 == 0) goto L_0x0019
                int r2 = r0.getCount()     // Catch:{ all -> 0x0017 }
                goto L_0x001a
            L_0x0017:
                r5 = move-exception
                goto L_0x0055
            L_0x0019:
                r2 = r1
            L_0x001a:
                r5.mIssues = r2     // Catch:{ all -> 0x0017 }
                if (r0 == 0) goto L_0x004d
                boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x0017 }
                if (r2 == 0) goto L_0x004d
            L_0x0024:
                r2 = 4
                int r2 = r0.getInt(r2)     // Catch:{ all -> 0x0017 }
                java.util.HashMap<java.lang.Integer, java.lang.String> r3 = r5.mMap     // Catch:{ all -> 0x0017 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0017 }
                boolean r3 = r3.containsKey(r4)     // Catch:{ all -> 0x0017 }
                if (r3 != 0) goto L_0x0047
                r3 = 1
                java.lang.String r3 = r0.getString(r3)     // Catch:{ all -> 0x0017 }
                java.util.HashMap<java.lang.Integer, java.lang.String> r4 = r5.mMap     // Catch:{ all -> 0x0017 }
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0017 }
                java.lang.String r3 = com.samsung.android.gallery.support.utils.FileUtils.getDirectoryFromPath(r3, r1)     // Catch:{ all -> 0x0017 }
                r4.put(r2, r3)     // Catch:{ all -> 0x0017 }
            L_0x0047:
                boolean r2 = r0.moveToNext()     // Catch:{ all -> 0x0017 }
                if (r2 != 0) goto L_0x0024
            L_0x004d:
                if (r0 == 0) goto L_0x0052
                r0.close()
            L_0x0052:
                int r5 = r5.mIssues
                return r5
            L_0x0055:
                if (r0 == 0) goto L_0x005f
                r0.close()     // Catch:{ all -> 0x005b }
                goto L_0x005f
            L_0x005b:
                r0 = move-exception
                r5.addSuppressed(r0)
            L_0x005f:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.settings.helper.Troubleshooting.HiddenAlbumResolver.test():int");
        }

        private HiddenAlbumResolver() {
            this.mMap = new HashMap<>();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NoMediaResolver extends TroubleResolver {
        private final ArrayList<String> mList;

        public /* synthetic */ NoMediaResolver(int i2) {
            this();
        }

        public String getSummary() {
            if (this.mIssues <= 0) {
                return super.getSummary();
            }
            String l = C0086a.l(new StringBuilder(), this.mIssues, " file(s) found. All contents in the folder and its sub-folders cannot be included in the database\n\n");
            Iterator<String> it = this.mList.iterator();
            while (it.hasNext()) {
                StringBuilder s = C0212a.s(l);
                s.append(Troubleshooting.getUserPath(it.next()));
                s.append("\n");
                l = s.toString();
            }
            if (l.length() > 0) {
                return C0280e.d(1, 0, l);
            }
            return l;
        }

        public String getTitle() {
            return "No-media file(.nomedia)";
        }

        public int resolve() {
            if (this.mList.size() == 0) {
                return 0;
            }
            ThreadUtil.postOnUiThread(new c(".nomedia files:" + AppResources.getAppContext().getString(R$string.ts_delete_unnecessary) + "\n" + TextUtils.join("\n", this.mList)));
            return 0;
        }

        public int test() {
            this.mIssues = 0;
            this.mList.clear();
            Cursor query = query(Troubleshooting.FILES_TABLE_URI, "title = '.nomedia'");
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        do {
                            String string = query.getString(1);
                            if (!TextUtils.isEmpty(string)) {
                                this.mList.add(string);
                            }
                        } while (query.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (query != null) {
                query.close();
            }
            int size = this.mList.size();
            this.mIssues = size;
            return size;
            throw th;
        }

        private NoMediaResolver() {
            this.mList = new ArrayList<>();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NullDateTakenResolver extends TroubleResolver {
        private static final String WHERE_CLAUSE = ("media_type in (1,3) and " + Troubleshooting.getDatetakenColumnName() + " is null");

        public /* synthetic */ NullDateTakenResolver(int i2) {
            this();
        }

        public String getSummary() {
            if (this.mIssues > 0) {
                return C0086a.l(new StringBuilder("Database has "), this.mIssues, " item(s) that have wrong date&time");
            }
            return super.getSummary();
        }

        public String getTitle() {
            return "Invalid date&time(empty date&time)";
        }

        public int resolve() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Troubleshooting.getDatetakenColumnName(), Long.valueOf(System.currentTimeMillis()));
            return update(contentValues, WHERE_CLAUSE);
        }

        public int test() {
            int logQuery = logQuery(Troubleshooting.FILES_TABLE_URI, WHERE_CLAUSE);
            this.mIssues = logQuery;
            return logQuery;
        }

        private NullDateTakenResolver() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimilarResolver extends TroubleResolver {
        private ArrayList<SimilarData> mIssues;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class SimilarData {
            int count;
            /* access modifiers changed from: package-private */
            public long mpId;
            long secId;

            public /* synthetic */ SimilarData(int i2) {
                this();
            }

            private SimilarData() {
            }
        }

        public /* synthetic */ SimilarResolver(int i2) {
            this();
        }

        private int queryDeletion(long j2, long j3) {
            ContentResolver contentResolver = AppResources.getAppContext().getContentResolver();
            Uri groupTableUri = MediaUri.getInstance().getGroupTableUri();
            StringBuilder j8 = j.j(j2, "_id < ", " and sec_media_id = ");
            j8.append(j3);
            return contentResolver.delete(groupTableUri, j8.toString(), (String[]) null);
        }

        private Cursor queryDuplicated() {
            return new SecMpQueryExecutor().rawQuery("select max(_id) as mpId, sec_media_id as secId, count(_id) as count from group_contents group by sec_media_id having count > 1", "");
        }

        public String getSummary() {
            if (this.mIssues.size() <= 0) {
                return super.getSummary();
            }
            return this.mIssues.size() + " contents are grouped wrongly by similar clustering engine";
        }

        public String getTitle() {
            return "Duplication in similar group";
        }

        public boolean hasIssues() {
            return hasResolver();
        }

        public boolean hasResolver() {
            if (this.mIssues.size() > 0) {
                return true;
            }
            return false;
        }

        public int resolve() {
            try {
                Iterator<SimilarData> it = this.mIssues.iterator();
                while (it.hasNext()) {
                    SimilarData next = it.next();
                    int queryDeletion = queryDeletion(next.mpId, next.secId);
                    Log.d("SimilarResolver", "resolve#delete {" + next.mpId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + next.secId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + next.count + GlobalPostProcInternalPPInterface.SPLIT_REGEX + queryDeletion + "}");
                }
                this.mIssues.clear();
                return 0;
            } catch (Exception e) {
                a.s(e, new StringBuilder("resolve failed e="), "SimilarResolver");
                return 0;
            }
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, java.util.function.ToLongFunction] */
        public int test() {
            Cursor queryDuplicated;
            this.mIssues.clear();
            try {
                queryDuplicated = queryDuplicated();
                if (queryDuplicated != null) {
                    if (queryDuplicated.moveToFirst()) {
                        do {
                            SimilarData similarData = new SimilarData(0);
                            similarData.mpId = queryDuplicated.getLong(0);
                            similarData.secId = queryDuplicated.getLong(1);
                            similarData.count = queryDuplicated.getInt(2);
                            this.mIssues.add(similarData);
                        } while (queryDuplicated.moveToNext());
                        log("test\n" + dumpCursor(queryDuplicated));
                        Log.d("SimilarResolver", "test #" + this.mIssues.size() + " {" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mIssues.stream().mapToLong(new Object()).limit(6).iterator()) + "}");
                    }
                }
                if (queryDuplicated != null) {
                    queryDuplicated.close();
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("test failed e="), "SimilarResolver");
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            return this.mIssues.size();
            throw th;
        }

        private SimilarResolver() {
            this.mIssues = new ArrayList<>();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class TroubleResolver {
        int mIssues;

        public final String dumpCursor(Cursor cursor) {
            if (cursor == null || cursor.getCount() == 0) {
                return "{empty}";
            }
            StringBuilder sb2 = new StringBuilder();
            try {
                if (cursor.moveToFirst()) {
                    String[] columnNames = cursor.getColumnNames();
                    for (String append : columnNames) {
                        sb2.append(append);
                        sb2.append("\t");
                    }
                    sb2.deleteCharAt(sb2.length() - 1);
                    sb2.append("\n");
                    do {
                        for (String columnIndex : columnNames) {
                            sb2.append(cursor.getString(cursor.getColumnIndex(columnIndex)));
                            sb2.append("\t");
                        }
                        sb2.deleteCharAt(sb2.length() - 1);
                        sb2.append("\n");
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb2.toString();
        }

        public String getSummary() {
            return "No problem in the database";
        }

        public abstract String getTitle();

        public String getTodo() {
            return AppResources.getString(R$string.ts_guide);
        }

        public boolean hasIssues() {
            if (this.mIssues > 0) {
                return true;
            }
            return false;
        }

        public boolean hasResolver() {
            if (this.mIssues > 0) {
                return true;
            }
            return false;
        }

        public final void log(String str) {
            Log.d("TroubleResolver", str);
        }

        public final int logQuery(Uri uri, String str) {
            Cursor query = query(uri, str);
            if (query != null) {
                try {
                    int count = query.getCount();
                    query.close();
                    return count;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else if (query == null) {
                return 0;
            } else {
                query.close();
                return 0;
            }
            throw th;
        }

        public final Cursor query(Uri uri, String str) {
            return query(uri, Troubleshooting.PROJECTIONS, str);
        }

        public boolean requireConfirm() {
            return false;
        }

        public abstract int resolve();

        public abstract int test();

        public String toString() {
            StringBuilder sb2 = new StringBuilder("TroubleResolver{");
            sb2.append(getTitle());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0086a.l(sb2, this.mIssues, "}");
        }

        public final int update(ContentValues contentValues, String str) {
            Uri uri;
            ContentResolver contentResolver = AppResources.getAppContext().getContentResolver();
            if (Troubleshooting.IS_QOS) {
                uri = Troubleshooting.FILES_TABLE_URI;
            } else {
                uri = MediaUri.getInstance().getSecMediaUri();
            }
            return contentResolver.update(uri, contentValues, str, (String[]) null);
        }

        public final Cursor query(Uri uri, String[] strArr, String str) {
            Cursor query = AppResources.getAppContext().getContentResolver().query(uri, strArr, str, (String[]) null, (String) null);
            if (query != null) {
                log(dumpCursor(query));
            }
            return query;
        }
    }

    private Troubleshooting() {
    }

    /* access modifiers changed from: private */
    public static String getDatetakenColumnName() {
        if (IS_QOS) {
            return IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME;
        }
        return IParameterKey.DATE_TAKEN;
    }

    /* access modifiers changed from: private */
    public static String getGroupIdColumnName() {
        if (IS_QOS) {
            return "burst_group_id";
        }
        return BundleKey.GROUP_ID;
    }

    public static Troubleshooting getInstance() {
        if (sInstance == null) {
            synchronized (Troubleshooting.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new Troubleshooting();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public static String getUserPath(String str) {
        if (str.startsWith(FileUtils.EXTERNAL_STORAGE_DIR)) {
            return str.replaceFirst(FileUtils.EXTERNAL_STORAGE_DIR, "/internal");
        }
        if (str.startsWith(FileUtils.getSdcardDir())) {
            return str.replaceFirst(FileUtils.getSdcardDir(), "/sdcard");
        }
        return str;
    }

    public ArrayList<TroubleResolver> getResolver() {
        ArrayList<TroubleResolver> arrayList = new ArrayList<>();
        arrayList.add(new GroupIdResolver(0));
        arrayList.add(new NullDateTakenResolver(0));
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            arrayList.add(new SimilarResolver(0));
        } else {
            arrayList.add(new DateTakenResolver());
            arrayList.add(new NoMediaResolver(0));
        }
        arrayList.add(new CloudOnlyResolver(0));
        arrayList.add(new HiddenAlbumResolver(0));
        arrayList.add(new FavoriteResolver(0));
        return arrayList;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DateTakenResolver extends TroubleResolver {
        private static final DatePattern[] PATTERNS;
        private static final String WHERE_CLAUSE = (Troubleshooting.getDatetakenColumnName() + "=date_modified*1000 and TYPE <> -1 and title not like strftime('%Y%m%d'," + Troubleshooting.getDatetakenColumnName() + "/1000, 'unixepoch', 'localtime')||'%'");
        static SimpleDateFormat sdfAllDash;
        static SimpleDateFormat sdfNoDivider;
        static SimpleDateFormat sdfSingleDash;
        static SimpleDateFormat sdfUnderbar;
        ArrayList<DataHolder> mDataList = new ArrayList<>();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class DataHolder {
            long _id;
            String title;
            int type;

            public DataHolder(long j2, int i2, String str) {
                this._id = j2;
                this.type = i2;
                this.title = str;
            }
        }

        static {
            Locale locale = Locale.ENGLISH;
            sdfUnderbar = new SimpleDateFormat("yyyyMMdd_HHmmss", locale);
            sdfSingleDash = new SimpleDateFormat("yyyyMMdd-HHmmss", locale);
            sdfAllDash = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", locale);
            sdfNoDivider = new SimpleDateFormat("yyyyMMddHHmmss", locale);
            String str = "screenshot_";
            PATTERNS = new DatePattern[]{new DatePattern("'20______\\_______' escape '\\' ", sdfUnderbar, (String) null, (String) null), new DatePattern("'Screenshot\\_20______-______' escape '\\' ", sdfSingleDash, "screenshot_", (String) null), new DatePattern("'Screenshot\\_20______-______\\_%' escape '\\' ", sdfSingleDash, str, 15), new DatePattern("'Screenshot\\_20__-__-__-__-__-__' escape '\\' ", sdfAllDash, str, (String) null), new DatePattern("'15___________' or title like '14___________' escape '\\' ", (SimpleDateFormat) null, (String) null, (String) null), new DatePattern("'20__-__-__-__-__-__' escape '\\' ", sdfAllDash, (String) null, (String) null), new DatePattern("'20____________'", sdfNoDivider, (String) null, (String) null), new DatePattern("'img\\_20_______________' escape '\\' ", sdfNoDivider, "img_", (String) null), new DatePattern("'pict\\_20______\\_______' escape '\\' ", sdfUnderbar, "pict_", (String) null), new DatePattern("'kakaotalk_15___________' or title like 'kakaotalk_14___________' ", (SimpleDateFormat) null, "kakaotalk_", (String) null), new DatePattern("'smartselectImage\\_20__-__-__-__-__-__' escape '\\' ", sdfAllDash, "smartselectImage_", (String) null), new DatePattern("'20______\\_______-ANIMATION' escape '\\' ", sdfUnderbar, "", "-ANIMATION")};
        }

        public DateTakenResolver() {
            updateDataList();
        }

        private String getTypeProjection() {
            String str = "";
            int i2 = 0;
            for (DatePattern datePattern : PATTERNS) {
                StringBuilder t = C0212a.t(str, "case when title like ");
                t.append(datePattern.mPattern);
                str = t.toString() + " then " + i2 + " else ";
                i2++;
            }
            String A10 = C0212a.A(str, " -1 ");
            for (int i7 = 0; i7 < PATTERNS.length; i7++) {
                A10 = C0212a.A(A10, " end ");
            }
            return C0212a.A(A10, " as TYPE ");
        }

        private void updateDataList() {
            this.mDataList.clear();
            ArrayList arrayList = new ArrayList();
            arrayList.add("_id");
            arrayList.add(getTypeProjection());
            arrayList.add("title");
            Cursor query = query(Troubleshooting.FILES_TABLE_URI, (String[]) arrayList.toArray(new String[0]), WHERE_CLAUSE);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        do {
                            this.mDataList.add(new DataHolder(query.getLong(0), query.getInt(1), query.getString(2)));
                        } while (query.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
            throw th;
        }

        public String getSummary() {
            if (this.mIssues > 0) {
                return C0086a.l(new StringBuilder(), this.mIssues, " files(s) maybe have a date taken value in file name.");
            }
            return super.getSummary();
        }

        public String getTitle() {
            return "Extract date taken";
        }

        public int resolve() {
            Iterator<DataHolder> it = this.mDataList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                DataHolder next = it.next();
                ContentValues contentValues = new ContentValues();
                long dateTaken = PATTERNS[next.type].getDateTaken(next.title);
                if (dateTaken != -1) {
                    contentValues.put(Troubleshooting.getDatetakenColumnName(), Long.valueOf(dateTaken));
                    i2 = update(contentValues, "_id=" + next._id) + i2;
                }
            }
            if (i2 > 0) {
                updateDataList();
            }
            return i2;
        }

        public int test() {
            int size = this.mDataList.size();
            this.mIssues = size;
            return size;
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class DatePattern {
            String footer;
            String header;
            int length;
            String mPattern;
            SimpleDateFormat sdf;

            public DatePattern(String str, SimpleDateFormat simpleDateFormat, String str2, int i2) {
                this.mPattern = str;
                this.sdf = simpleDateFormat;
                this.header = str2;
                this.length = i2;
            }

            public long getDateTaken(String str) {
                try {
                    String str2 = this.header;
                    if (str2 != null) {
                        int length2 = str2.length();
                        int i2 = this.length;
                        if (i2 > 0) {
                            str = str.substring(length2, i2 + length2);
                        } else {
                            str = str.substring(length2);
                        }
                    }
                    if (this.footer != null) {
                        str = str.substring(0, str.length() - this.footer.length());
                    }
                    SimpleDateFormat simpleDateFormat = this.sdf;
                    if (simpleDateFormat == null) {
                        return UnsafeCast.toLong(str, -1);
                    }
                    return simpleDateFormat.parse(str).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                    return -1;
                }
            }

            public DatePattern(String str, SimpleDateFormat simpleDateFormat, String str2, String str3) {
                this.mPattern = str;
                this.sdf = simpleDateFormat;
                this.header = str2;
                this.footer = str3;
            }
        }
    }
}
