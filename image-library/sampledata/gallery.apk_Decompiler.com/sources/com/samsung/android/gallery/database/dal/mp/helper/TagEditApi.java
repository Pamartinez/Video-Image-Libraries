package com.samsung.android.gallery.database.dal.mp.helper;

import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import q8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TagEditApi extends BaseImpl {
    private static final boolean GED = false;
    private static final Uri USERTAG_TABLE_URI = CmhUri.getUserTags();

    public TagEditApi() {
        super(new QueryParams());
    }

    public static ContentValues buildContentValues(long j2, String str) {
        ContentValues c5 = C0086a.c("tag", str);
        c5.put("sec_media_id", Long.valueOf(j2));
        return c5;
    }

    public static ArrayList<ContentValues> buildContentValuesArray(long j2, ArrayList<String> arrayList) {
        ArrayList<ContentValues> arrayList2 = new ArrayList<>();
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(buildContentValues(j2, it.next()));
            }
        }
        return arrayList2;
    }

    private String getSelection(ArrayList<String> arrayList, boolean z) {
        String str;
        if (z) {
            str = "cloud_server_path";
        } else {
            str = "_data";
        }
        StringBuilder sb2 = new StringBuilder(str.concat(" IN ("));
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            j.z(sb2, "'", it.next(), "',");
        }
        StringBuilder deleteCharAt = sb2.deleteCharAt(sb2.lastIndexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        deleteCharAt.append(")");
        return deleteCharAt.toString();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$queryTagListFromIds$0(Long l) {
        return new ArrayList();
    }

    public void bulkDeleteMyTag(ArrayList<String> arrayList, long j2, boolean z) {
        ArrayList arrayList2 = new ArrayList();
        Uri myTagUri = getMyTagUri();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(ContentProviderOperation.newDelete(myTagUri).withSelection(getMyTagSelection(), getMyTagSelectionArg(j2, it.next())).build());
        }
        try {
            getContentResolver().applyBatch(myTagUri.getAuthority(), arrayList2);
        } catch (OperationApplicationException | RemoteException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public int bulkInsertMyTags(ContentValues[] contentValuesArr) {
        return getContentResolver().bulkInsert(getMyTagUri(), contentValuesArr);
    }

    public String getMyTagSelection() {
        return "sec_media_id = ?  and tag = ? ";
    }

    public String[] getMyTagSelectionArg(long j2, String str) {
        return new String[]{Long.toString(j2), str};
    }

    public Uri getMyTagUri() {
        return USERTAG_TABLE_URI;
    }

    public void insertMyTagArray(Collection<String> collection, long j2, boolean z) {
        try {
            if (!collection.isEmpty()) {
                ArrayList arrayList = new ArrayList(collection.size());
                for (String buildContentValues : collection) {
                    arrayList.add(buildContentValues(j2, buildContentValues));
                }
                bulkInsertMyTags((ContentValues[]) arrayList.toArray(new ContentValues[0]));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Cursor queryNewCreatedIdFromPaths(ArrayList<String> arrayList, boolean z) {
        String str;
        String selection = getSelection(arrayList, z);
        RawQueryExecutor rawQueryExecutor = this.mQueryExecutor;
        Uri secMediaUri = MediaUri.getInstance().getSecMediaUri();
        if (z) {
            str = "cloud_server_path";
        } else {
            str = "_data";
        }
        return rawQueryExecutor.getCursor(secMediaUri, new String[]{"_id", str}, selection, (String[]) null, (String) null, "getCloudServerPath");
    }

    public Map<Long, ArrayList<String>> queryTagListFromIds(Long[] lArr) {
        Cursor query;
        Throwable th;
        HashMap hashMap = new HashMap();
        try {
            query = getContentResolver().query(CmhUri.getTagView(), new String[]{"sec_media_id", "tag_data"}, "sec_media_id IN " + CursorHelper.joinIds(new ArrayList(Arrays.asList(lArr))) + " AND tag_type = 0", (String[]) null, (String) null);
            if (query != null) {
                if (query.getCount() > 0) {
                    while (query.moveToNext()) {
                        ((ArrayList) hashMap.computeIfAbsent(Long.valueOf(query.getLong(0)), new a(3))).add(query.getString(1));
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
            return hashMap;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public boolean removeCapturedValues(long j2) {
        Uri uri;
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("captured_url");
        contentValues.putNull("captured_app");
        ContentResolver contentResolver = getContentResolver();
        if (this.IS_GTE_R) {
            uri = MediaUri.getInstance().getSecMediaUri();
        } else {
            uri = MediaUri.getInstance().getImageUri();
        }
        if (this.IS_GTE_R) {
            str = "media_id=?";
        } else {
            str = "_id=?";
        }
        if (contentResolver.update(uri, contentValues, str, new String[]{String.valueOf(j2)}) > 0) {
            return true;
        }
        return false;
    }

    public void setFavorite(Uri uri, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_favorite", Boolean.valueOf(z));
        try {
            getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("Failed to set favorite : e="), this.TAG);
        }
    }

    public String tag() {
        return "TagEditApi";
    }

    public boolean updateCapturedFilePath(long j2, String str) {
        if (getContentResolver().update(MediaUri.getInstance().getSecMediaUri(), C0086a.c("captured_original_path", str), "media_id=?", new String[]{String.valueOf(j2)}) > 0) {
            return true;
        }
        return false;
    }
}
