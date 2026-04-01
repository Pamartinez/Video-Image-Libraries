package com.samsung.android.gallery.module.search.rubin;

import A4.C0381p;
import J5.c;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import ca.C0588a;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchWordCollector {
    private static volatile SearchWordCollector sInstance;
    protected Boolean mIsRubinEnabled;
    private final LocalDatabaseHelper mLocalDatabaseHelper = LocalDatabaseHelper.getInstance(AppResources.getAppContext());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        KEYWORD(1),
        RECENT_HISTORY(3),
        HIERARCHICAL_SUGGESTION(4),
        VISUAL_SEARCH(5),
        BIXBY_VOICE(6),
        KEYWORD_AUTOCOMPLETE(8),
        VISUAL_SEARCH_TAG(11),
        VISUAL_SEARCH_PEOPLE(12),
        VISUAL_SEARCH_LOCATIONS(13),
        VISUAL_SEARCH_SHOTTYPES(14),
        VISUAL_SEARCH_THINGSANDSCENERY(15),
        VISUAL_SEARCH_DOCUMENTS(16),
        VISUAL_SEARCH_PET(17);
        
        private final int mValue;

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.search.rubin.SearchWordCollector$Type$1  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass1 extends Type {
            public /* synthetic */ AnonymousClass1() {
                this("SUGGESTION", 1, 2);
            }

            public boolean supportHistory(boolean z) {
                return PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM;
            }

            private AnonymousClass1(String str, int i2, int i7) {
                super(str, i2, i7, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.search.rubin.SearchWordCollector$Type$2  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass2 extends Type {
            public /* synthetic */ AnonymousClass2() {
                this("KEYWORD_INPUT", 6, 7);
            }

            public boolean supportHistory(boolean z) {
                return !z;
            }

            private AnonymousClass2(String str, int i2, int i7) {
                super(str, i2, i7, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.search.rubin.SearchWordCollector$Type$3  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass3 extends Type {
            public /* synthetic */ AnonymousClass3() {
                this("FACET", 8, 9);
            }

            public boolean supportHistory(boolean z) {
                return false;
            }

            private AnonymousClass3(String str, int i2, int i7) {
                super(str, i2, i7, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.search.rubin.SearchWordCollector$Type$4  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass4 extends Type {
            public /* synthetic */ AnonymousClass4() {
                this("KEYWORD_SUGGESTION", 9, 10);
            }

            public boolean supportHistory(boolean z) {
                return false;
            }

            private AnonymousClass4(String str, int i2, int i7) {
                super(str, i2, i7, 0);
            }
        }

        public int getValue() {
            return this.mValue;
        }

        public boolean supportHistory(boolean z) {
            return true;
        }

        private Type(int i2) {
            this.mValue = i2;
        }
    }

    private SearchWordCollector() {
    }

    private void clear() {
        ThreadPool.getInstance().submit(new C0381p(7, this));
    }

    public static void clearRubinState() {
        if (sInstance != null) {
            sInstance.mIsRubinEnabled = null;
        }
    }

    public static String getCollectedKeyword(String str, String str2, String str3) {
        str3.getClass();
        char c5 = 65535;
        switch (str3.hashCode()) {
            case -1790835355:
                if (str3.equals("Things")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1347456360:
                if (str3.equals("Documents")) {
                    c5 = 1;
                    break;
                }
                break;
            case -775468146:
                if (str3.equals("scenetag")) {
                    c5 = 2;
                    break;
                }
                break;
            case -715666509:
                if (str3.equals("Scenery")) {
                    c5 = 3;
                    break;
                }
                break;
            case -638961672:
                if (str3.equals("Things Scenery")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return str2;
            default:
                return str;
        }
    }

    public static SearchWordCollector getInstance() {
        if (sInstance == null) {
            synchronized (SearchWordCollector.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SearchWordCollector();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private SQLiteDatabase getReadableDatabase() {
        return this.mLocalDatabaseHelper.getReadableDatabase();
    }

    public static Type getType(String str) {
        try {
            return Type.valueOf(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static String getVisualSearchType(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1907941713:
                if (str.equals("People")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1347456360:
                if (str.equals("Documents")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1179428371:
                if (str.equals("My tags")) {
                    c5 = 2;
                    break;
                }
                break;
            case -638961672:
                if (str.equals("Things Scenery")) {
                    c5 = 3;
                    break;
                }
                break;
            case -626311778:
                if (str.equals("Camera mode")) {
                    c5 = 4;
                    break;
                }
                break;
            case 80127:
                if (str.equals("Pet")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1965687765:
                if (str.equals("Location")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return Type.VISUAL_SEARCH_PEOPLE.toString();
            case 1:
                return Type.VISUAL_SEARCH_DOCUMENTS.toString();
            case 2:
                return Type.VISUAL_SEARCH_TAG.toString();
            case 3:
                return Type.VISUAL_SEARCH_THINGSANDSCENERY.toString();
            case 4:
                return Type.VISUAL_SEARCH_SHOTTYPES.toString();
            case 5:
                return Type.VISUAL_SEARCH_PET.toString();
            case 6:
                return Type.VISUAL_SEARCH_LOCATIONS.toString();
            default:
                return Type.VISUAL_SEARCH.toString();
        }
    }

    private SQLiteDatabase getWritableDatabase() {
        return this.mLocalDatabaseHelper.getWritableDatabase();
    }

    private boolean isRubinEnabled() {
        if (this.mIsRubinEnabled == null) {
            if (Features.isEnabled(Features.SUPPORT_RUBIN_COLLECT_SEARCH)) {
                Boolean bool = (Boolean) LatchBuilder.loadLazy("SearchWordCollector#RubinEnabled", Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, new c(12, this));
                if (bool == null || !bool.booleanValue()) {
                    return false;
                }
                return true;
            }
            this.mIsRubinEnabled = Boolean.FALSE;
        }
        return this.mIsRubinEnabled.booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$clear$1(ThreadPool.JobContext jobContext) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int delete = getWritableDatabase().delete("search_collect", (String) null, (String[]) null);
            Log.s("SearchWordCollector", "clear {" + delete + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Exception e) {
            Log.se("SearchWordCollector", "clear failed" + e);
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$collect$0(String str, Type type, boolean z, boolean z3, ThreadPool.JobContext jobContext) {
        if (isRubinEnabled()) {
            insertKeywords(str, type.getValue(), z ? 1 : 0, z3 ? 1 : 0);
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isRubinEnabled$2() {
        Boolean valueOf = Boolean.valueOf(RubinManager.isRubinEnabled(AppResources.getAppContext()));
        this.mIsRubinEnabled = valueOf;
        return valueOf;
    }

    private Cursor queryInternal(String[] strArr, String str, String[] strArr2, String str2) {
        int i2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Cursor query = getReadableDatabase().query("search_collect", strArr, str, strArr2, (String) null, (String) null, str2);
            StringBuilder sb2 = new StringBuilder("query keyword {");
            if (query != null) {
                i2 = query.getCount();
            } else {
                i2 = -1;
            }
            sb2.append(i2);
            sb2.append(" +");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            Log.s("SearchWordCollector", sb2.toString());
            return query;
        } catch (Exception e) {
            Exception exc = e;
            Log.se("SearchWordCollector", "queryInternal failed" + exc);
            return null;
        }
    }

    public void collect(String str, Type type, boolean z, boolean z3) {
        if (!TextUtils.isEmpty(str)) {
            Boolean bool = this.mIsRubinEnabled;
            if (bool == null || bool.booleanValue()) {
                ThreadPool.getInstance().submit(new C0588a(this, str, type, z, z3));
            }
        }
    }

    public void insertKeywords(String str, int i2, int i7, int i8) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", str);
            contentValues.put("date_added", Long.valueOf(currentTimeMillis));
            contentValues.put("search_type", Integer.valueOf(i2));
            contentValues.put("existed", Integer.valueOf(i7));
            contentValues.put("clicked", Integer.valueOf(i8));
            long insert = getWritableDatabase().insert("search_collect", (String) null, contentValues);
            Log.s("SearchWordCollector", "insertKeywords" + Logger.vt(Integer.valueOf(i2), Long.valueOf(insert), Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            Log.se("SearchWordCollector", "insertKeywords failed" + e);
        }
    }

    public Cursor query(String[] strArr, String str, String[] strArr2, String str2) {
        Cursor queryInternal = queryInternal(strArr, str, strArr2, str2);
        if (queryInternal != null && queryInternal.getCount() > 0) {
            clear();
        }
        return queryInternal;
    }
}
