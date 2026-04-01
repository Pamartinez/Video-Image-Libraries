package com.samsung.android.gallery.module.search.root;

import N2.j;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.search.IntelligentSearchConstants;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentSearch {
    private static final IntelligentSearch sInstance = new IntelligentSearch();
    private final ConcurrentHashMap<String, String> mCachedResult = new ConcurrentHashMap<>();
    private final ContentObserver mContentObserver = new ContentObserver(ThreadUtil.createMainThreadHandler()) {
        public void onChange(boolean z) {
            Log.s("IntelligentSearch", "onChange {" + z + "} " + IntelligentSearch.this.mDataChangedListenerMap + "}");
            for (DataChangedListener dataChangedListener : IntelligentSearch.this.mDataChangedListenerMap.values()) {
                if (dataChangedListener != null) {
                    dataChangedListener.onChanged();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<Integer, DataChangedListener> mDataChangedListenerMap = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DataChangedListener {
        void onChanged();
    }

    private void assertionErrorIfNeeded(String str) {
        if (!DeviceConfig.DEBUG_BINARY) {
            Log.se("IntelligentSearch", "[From SCS] " + str);
            return;
        }
        throw new AssertionError(C0212a.l("[From SCS] ", str));
    }

    private static void callMediaSearchNotificationIfNeeded(SearchFilter searchFilter) {
        if (searchFilter.isSemanticQueryEnabled()) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Bundle call = AppResources.getAppContext().getContentResolver().call(Uri.parse("content://com.samsung.mediasearch"), "init", searchFilter.getRawKeyword(), (Bundle) null);
                Log.s("IntelligentSearch", "MediaSearch Notification called (+" + (System.currentTimeMillis() - currentTimeMillis) + "ms), " + call);
            } catch (Exception e) {
                Log.se("IntelligentSearch", "call MediaSearch Notification failed (+ " + (System.currentTimeMillis() - currentTimeMillis) + "ms), " + e.getMessage());
            }
        }
    }

    public static IntelligentSearch getInstance() {
        return sInstance;
    }

    private static void setScsQueryExtra(Cursor cursor, Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_EVENT_FACET) && cursor != null) {
            Bundle extras = cursor.getExtras();
            if (Bundle.EMPTY.equals(extras)) {
                extras = new Bundle();
                cursor.setExtras(extras);
            }
            extras.putBundle("searchScsQueryExtra", bundle);
        }
    }

    public void clearCachedResult() {
        this.mCachedResult.clear();
    }

    public String getCachedResult(String str) {
        return this.mCachedResult.getOrDefault(str, (Object) null);
    }

    public Cursor query(SearchFilter searchFilter) {
        String[] strArr;
        Uri uri;
        int i2;
        String str;
        Object obj;
        long currentTimeMillis = System.currentTimeMillis();
        Bundle create = new IntelligentSearchArgs(searchFilter).create();
        try {
            SearchLog.d("IntelligentSearch", "SSE#in\nFILTER=" + searchFilter.toDebugString() + "\nARGS=" + Logger.toString(create, false));
            callMediaSearchNotificationIfNeeded(searchFilter);
            boolean isFuzzySuggestKeywordQueryEnabled = searchFilter.isFuzzySuggestKeywordQueryEnabled();
            if (isFuzzySuggestKeywordQueryEnabled) {
                strArr = IntelligentSearchConstants.getProjectionForFuzzy();
            } else {
                strArr = IntelligentSearchConstants.getProjectionForNormal();
            }
            if (isFuzzySuggestKeywordQueryEnabled) {
                uri = IntelligentSearchConstants.SUGGEST_MEDIA_URI;
            } else {
                uri = IntelligentSearchConstants.MEDIA_URI;
            }
            Cursor query = AppResources.getAppContext().getContentResolver().query(uri, strArr, create, searchFilter.getCancellationSignal());
            StringBuilder sb2 = new StringBuilder(Contract.QUERY);
            if (query != null) {
                i2 = query.getCount();
            } else {
                i2 = -1;
            }
            Integer valueOf = Integer.valueOf(i2);
            if (isFuzzySuggestKeywordQueryEnabled) {
                str = "F";
            } else {
                str = "f";
            }
            sb2.append(Logger.vt(valueOf, str, Long.valueOf(currentTimeMillis)));
            sb2.append("\n");
            sb2.append(IntelligentSearchArgs.toDebugString(create));
            Log.s("IntelligentSearch", sb2.toString());
            StringBuilder sb3 = new StringBuilder("SSE#out {");
            String str2 = "";
            if (query != null) {
                obj = Integer.valueOf(query.getCount());
            } else {
                obj = str2;
            }
            sb3.append(obj);
            sb3.append("} +");
            sb3.append(System.currentTimeMillis() - currentTimeMillis);
            if (query != null) {
                str2 = "\nEXTRA=" + Logger.toString(query.getExtras(), false);
            }
            sb3.append(str2);
            SearchLog.d("IntelligentSearch", sb3.toString());
            setScsQueryExtra(query, create);
            return query;
        } catch (IllegalArgumentException e) {
            assertionErrorIfNeeded("query failed. e=" + e.getMessage() + "\n" + Logger.getEncodedString(create.toString()));
            return null;
        } catch (Exception e7) {
            Log.se("IntelligentSearch", "query failed. e=" + e7.getMessage() + "\n" + Logger.getEncodedString(create.toString()));
            return null;
        }
    }

    public Cursor queryAutoComplete(SearchFilter searchFilter) {
        Bundle createAutoComplete = new IntelligentSearchArgs(searchFilter).createAutoComplete();
        try {
            return AppResources.getAppContext().getContentResolver().query(IntelligentSearchConstants.SUGGEST_MEDIA_URI, IntelligentSearchConstants.getProjectionForAutocomplete(), createAutoComplete, searchFilter.getCancellationSignal());
        } catch (IllegalArgumentException e) {
            assertionErrorIfNeeded("queryAutoComplete failed. e=" + e.getMessage() + "\n" + Logger.getEncodedString(createAutoComplete.toString()));
            return null;
        } catch (Exception e7) {
            Log.se("IntelligentSearch", "queryAutoComplete failed. e=" + e7.getMessage() + "\n" + Logger.getEncodedString(createAutoComplete.toString()));
            return null;
        }
    }

    public Cursor queryHintKeywords(int i2) {
        int i7;
        long currentTimeMillis = System.currentTimeMillis();
        Bundle bundle = new Bundle();
        try {
            bundle.putInt("android:query-arg-limit", i2);
            bundle.putBoolean("locationAccess", PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth));
            Cursor query = AppResources.getAppContext().getContentResolver().query(IntelligentSearchConstants.HINT_MEDIA_URI, (String[]) null, bundle, (CancellationSignal) null);
            StringBuilder sb2 = new StringBuilder("queryHint");
            if (query != null) {
                i7 = query.getCount();
            } else {
                i7 = -1;
            }
            sb2.append(Logger.vt(Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
            Log.s("IntelligentSearch", sb2.toString());
            return query;
        } catch (IllegalArgumentException e) {
            assertionErrorIfNeeded("queryHint failed " + Logger.getEncodedString(bundle.toString()) + "\n" + e.getMessage());
            return null;
        } catch (Exception e7) {
            Log.se("IntelligentSearch", "queryHint failed " + Logger.getEncodedString(bundle.toString()) + "\n" + e7.getMessage());
            return null;
        }
    }

    public void registerContentObserver(int i2, DataChangedListener dataChangedListener) {
        this.mDataChangedListenerMap.put(Integer.valueOf(i2), dataChangedListener);
        if (this.mDataChangedListenerMap.size() == 1) {
            try {
                AppResources.getAppContext().getContentResolver().registerContentObserver(IntelligentSearchConstants.MEDIA_OBSERVING_URI, true, this.mContentObserver);
            } catch (Exception e) {
                Log.se("IntelligentSearch", "registerContentObserver failed e=" + e.getMessage());
            }
        }
    }

    public void requestUpdateConfigStatus() {
        try {
            Log.i("IntelligentSearch", "UPDATE_DATA_CONFIG_STATUS", AppResources.getAppContext().getContentResolver().call(IntelligentSearchConstants.MEDIA_URI, "UPDATE_DATA_CONFIG_STATUS", "com.google.android.providers.media", (Bundle) null));
        } catch (Exception e) {
            j.v("UPDATE_DATA_CONFIG_STATUS failed.", e, "IntelligentSearch");
        }
    }

    public void saveCacheResult(String str, String str2) {
        if (str == null || str2 == null) {
            Log.e("IntelligentSearch", "keyword or idList is null");
        } else {
            this.mCachedResult.put(str, str2);
        }
    }

    public void unregisterContentObserver(int i2) {
        this.mDataChangedListenerMap.remove(Integer.valueOf(i2));
        if (this.mDataChangedListenerMap.isEmpty()) {
            try {
                AppResources.getAppContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
            } catch (Exception e) {
                Log.se("IntelligentSearch", "unregisterContentObserver failed e=" + e.getMessage());
            }
        }
    }
}
