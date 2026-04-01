package com.samsung.android.gallery.database.dal.contact;

import A.a;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor;
import com.samsung.android.gallery.support.utils.Log;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContactApi {
    private static final String[] PROFILE_PROJECTION = {"profile_given_name", "contact_photo_blob"};
    private static final Uri SINGLE_URI = new Uri.Builder().scheme("content").authority("com.samsung.android.mobileservice.profileProvider").appendPath("new_profile_single").build();
    private boolean IS_GTE_Q;
    private QueryExecutor mQueryExecutor;

    public ContactApi() {
        init(Build.VERSION.SDK_INT, new QueryExecutor());
    }

    private void init(int i2, QueryExecutor queryExecutor) {
        boolean z;
        this.mQueryExecutor = queryExecutor;
        if (i2 >= 29) {
            z = true;
        } else {
            z = false;
        }
        this.IS_GTE_Q = z;
    }

    private boolean updateMyProfilePhotoQ(byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact_photo_blob", bArr);
        try {
            ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
            Bundle bundle = new Bundle();
            bundle.putParcelable("singleData", contentValues);
            contentResolver.call("com.samsung.android.mobileservice.profileProvider", "updateProfile", "22n6hzkam0", bundle);
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("update my profile photo failed e="), "ContactApi");
            return false;
        }
    }

    public Cursor getContact(Uri uri) {
        return this.mQueryExecutor.query(uri, new ContactTable().getDefaultProjection(), (String) null, (String[]) null, (String) null);
    }

    public Cursor getContactId(long j2) {
        ContactTable contactTable = new ContactTable();
        return this.mQueryExecutor.query(contactTable.getContactUri(), contactTable.getDefaultProjection(), contactTable.getSelectionNullCheckForName(j2), (String[]) null, contactTable.getDefaultOrderBy());
    }

    public Cursor getContactName() {
        ContactTable contactTable = new ContactTable();
        return this.mQueryExecutor.query(contactTable.getContactUri(), contactTable.getDefaultProjection(), contactTable.getSelectionNullCheckForName(), (String[]) null, contactTable.getDefaultOrderBy());
    }

    public Cursor getFrequentlyContacted() {
        ContactTable contactTable = new ContactTable();
        return this.mQueryExecutor.query(contactTable.getFrequentlyContactedUri(), contactTable.getDefaultProjection(), (String) null, (String[]) null, contactTable.getOrderByLimit());
    }

    public Cursor getMyProfile() {
        if (this.IS_GTE_Q) {
            return getMyProfileQ();
        }
        ContactTable contactTable = new ContactTable();
        return this.mQueryExecutor.query(contactTable.getMyProfileUri(), contactTable.getDefaultProjection(), (String) null, (String[]) null, (String) null);
    }

    public Cursor getMyProfileQ() {
        try {
            return this.mQueryExecutor.query(SINGLE_URI, PROFILE_PROJECTION, (String) null, (String[]) null, (String) null);
        } catch (Exception e) {
            a.s(e, new StringBuilder("get my profile failed e="), "ContactApi");
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r3v8, types: [java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.Integer, java.lang.String> getMyProfileRelationsMap() {
        /*
            r3 = this;
            com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor r3 = r3.mQueryExecutor     // Catch:{ Exception -> 0x001d }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x001d }
            android.net.Uri r0 = android.provider.ContactsContract.AUTHORITY_URI     // Catch:{ Exception -> 0x001d }
            java.lang.String r1 = "getProfileRelation"
            r2 = 0
            android.os.Bundle r3 = r3.call(r0, r1, r2, r2)     // Catch:{ Exception -> 0x001d }
            if (r3 == 0) goto L_0x001a
            java.lang.String r0 = "profileRelationHashMap"
            java.io.Serializable r3 = r3.getSerializable(r0)     // Catch:{ Exception -> 0x001d }
            r2 = r3
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ Exception -> 0x001d }
        L_0x001a:
            if (r2 == 0) goto L_0x0035
            return r2
        L_0x001d:
            r3 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "save bitmap failed."
            r0.<init>(r1)
            java.lang.String r3 = r3.toString()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "ContactApi"
            com.samsung.android.gallery.support.utils.Log.e(r0, r3)
        L_0x0035:
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.contact.ContactApi.getMyProfileRelationsMap():java.util.HashMap");
    }

    public Cursor getRelatedMyProfileContacts() {
        ContactTable contactTable = new ContactTable();
        return this.mQueryExecutor.query(contactTable.getDataContactUri(), contactTable.getDataProjection(), contactTable.getSelectionForRelation(), (String[]) null, (String) null);
    }

    public boolean isSimAccount(long j2) {
        Cursor cursor;
        Throwable th;
        ContactTable contactTable = new ContactTable();
        boolean z = false;
        try {
            cursor = this.mQueryExecutor.getCursor(contactTable.getRawContactUri(), contactTable.getAccountTypeProjection(), contactTable.getSelectionToCheckSimAccount(j2), (String[]) null, (String) null, (String) null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    z = "vnd.sec.contact.sim".equals(cursor.getString(cursor.getColumnIndex("account_type")));
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return z;
        } catch (SQLiteException e) {
            SQLiteException sQLiteException = e;
            Log.e("ContactApi", "SQLiteException" + sQLiteException.toString());
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public boolean updatePhoto(byte[] bArr, long j2, boolean z) {
        AssetFileDescriptor openAssetFileDescriptor;
        ByteArrayInputStream byteArrayInputStream;
        if (this.IS_GTE_Q && z) {
            return updateMyProfilePhotoQ(bArr);
        }
        Uri withAppendedPath = Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, j2), "display_photo");
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        if (contentResolver == null) {
            Log.e("ContactApi", "content resolver is null");
            return false;
        }
        try {
            openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(withAppendedPath, "rw");
            if (openAssetFileDescriptor == null) {
                Log.e("ContactApi", "file descriptor creation failed.");
                if (openAssetFileDescriptor == null) {
                    return false;
                }
                openAssetFileDescriptor.close();
                return false;
            }
            FileOutputStream createOutputStream = openAssetFileDescriptor.createOutputStream();
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                byte[] bArr2 = new byte[16384];
                while (true) {
                    int read = byteArrayInputStream.read(bArr2);
                    if (read <= 0) {
                        break;
                    }
                    createOutputStream.write(bArr2, 0, read);
                }
                byteArrayInputStream.close();
                if (createOutputStream != null) {
                    createOutputStream.close();
                }
                openAssetFileDescriptor.close();
                return true;
            } catch (Throwable th) {
                if (createOutputStream != null) {
                    createOutputStream.close();
                }
                throw th;
            }
        } catch (IOException | SecurityException e) {
            Log.e("ContactApi", "save bitmap failed." + e.toString());
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
        throw th;
    }

    public Cursor getContactName(long j2) {
        ContactTable contactTable = new ContactTable();
        return this.mQueryExecutor.query(contactTable.getContactUri(), contactTable.getDefaultProjection(), contactTable.getSelectionNullCheckForName(j2), (String[]) null, contactTable.getDefaultOrderBy());
    }
}
