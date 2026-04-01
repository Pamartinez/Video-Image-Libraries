package com.samsung.android.gallery.support.search;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import c0.C0086a;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchIndexingTask extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<Context> mContextRef;
    private final IntelligentSearchIndex.IndexMode mIndexMode;
    private final SearchIndexListener mListener;
    private final ArrayList<IndexParams> mParamsList;

    public SearchIndexingTask(Context context, ArrayList<IndexParams> arrayList, IntelligentSearchIndex.IndexMode indexMode, SearchIndexListener searchIndexListener) {
        ArrayList<IndexParams> arrayList2 = new ArrayList<>();
        this.mParamsList = arrayList2;
        this.mContextRef = new WeakReference<>(context);
        arrayList2.addAll(arrayList);
        this.mIndexMode = indexMode;
        this.mListener = searchIndexListener;
    }

    private String composeDumpInfo(Map<String, String> map, IntelligentSearchIndex.IndexMode indexMode) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        map.forEach(new a(stringJoiner, indexMode));
        return Logger.getEncodedString(stringJoiner.toString());
    }

    private Map<String, String> getValueMap() {
        HashMap hashMap = new HashMap();
        Iterator<IndexParams> it = this.mParamsList.iterator();
        while (it.hasNext()) {
            hashMap.putAll(it.next().getValueMap());
        }
        return hashMap;
    }

    public ContentValues buildContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        for (Map.Entry next : map.entrySet()) {
            contentValues.put((String) next.getKey(), (String) next.getValue());
        }
        if (this.mIndexMode != IntelligentSearchIndex.IndexMode.SET) {
            contentValues.put("mode", IntelligentSearchIndex.getInstance().getIndexMode(this.mIndexMode));
        }
        return contentValues;
    }

    public ArrayList<ContentProviderOperation> getOperations() {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        Iterator<IndexParams> it = this.mParamsList.iterator();
        while (it.hasNext()) {
            IndexParams next = it.next();
            arrayList.add(ContentProviderOperation.newUpdate(IntelligentSearchConstants.MEDIA_URI).withValues(buildContentValues(next.getValueMap())).withSelection(next.getSelection(), next.getSelectionArgs()).build());
        }
        return arrayList;
    }

    public void onPreExecute() {
        super.onPreExecute();
        Log.d("SearchIndexingTask", "indexing start : " + this.mIndexMode);
    }

    public Integer doInBackground(Void... voidArr) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ContentResolver contentResolver = this.mContextRef.get().getContentResolver();
            String authority = IntelligentSearchConstants.MEDIA_URI.getAuthority();
            Objects.requireNonNull(authority);
            int length = contentResolver.applyBatch(authority, getOperations()).length;
            Log.d("SearchIndexingTask", "indexing completed {" + composeDumpInfo(getValueMap(), this.mIndexMode) + ',' + length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            return Integer.valueOf(length);
        } catch (Exception e) {
            Log.e((CharSequence) "SearchIndexingTask", "indexing failed {", C0086a.m(new StringBuilder(), composeDumpInfo(getValueMap(), this.mIndexMode), '}'), e);
            return -1;
        }
    }

    public void onPostExecute(Integer num) {
        SearchIndexListener searchIndexListener = this.mListener;
        if (searchIndexListener != null) {
            searchIndexListener.onIndexComplete(num.intValue());
        }
    }
}
