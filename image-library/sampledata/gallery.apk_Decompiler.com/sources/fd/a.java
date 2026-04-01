package fd;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a extends ContentProvider {
    private String mAuthority;
    private UriMatcher mMatcher;

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        this.mAuthority = providerInfo.authority;
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.mMatcher = uriMatcher;
        uriMatcher.addURI(this.mAuthority, "settings/indexables_xml_res", 1);
        this.mMatcher.addURI(this.mAuthority, "settings/indexables_raw", 2);
        this.mMatcher.addURI(this.mAuthority, "settings/non_indexables_key", 3);
        this.mMatcher.addURI(this.mAuthority, "settings/site_map_pairs", 4);
        this.mMatcher.addURI(this.mAuthority, "settings/slice_uri_pairs", 5);
        this.mMatcher.addURI(this.mAuthority, "settings/dynamic_indexables_raw", 6);
        if (!providerInfo.exported) {
            throw new SecurityException("Provider must be exported");
        } else if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grantUriPermissions");
        } else if ("android.permission.READ_SEARCH_INDEXABLES".equals(providerInfo.readPermission)) {
            super.attachInfo(context, providerInfo);
        } else {
            throw new SecurityException("Provider must be protected by READ_SEARCH_INDEXABLES");
        }
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Delete not supported");
    }

    public String getType(Uri uri) {
        int match = this.mMatcher.match(uri);
        if (match == 1) {
            return "vnd.android.cursor.dir/indexables_xml_res";
        }
        if (match == 2) {
            return "vnd.android.cursor.dir/indexables_raw";
        }
        if (match == 3) {
            return "vnd.android.cursor.dir/non_indexables_key";
        }
        if (match == 6) {
            return "vnd.android.cursor.dir/indexables_raw";
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Insert not supported");
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            switch (this.mMatcher.match(uri)) {
                case 1:
                    return queryXmlResources((String[]) null);
                case 2:
                    return queryRawData((String[]) null);
                case 3:
                    return queryNonIndexableKeys((String[]) null);
                case 4:
                    return querySiteMapPairs();
                case 5:
                    return querySliceUriPairs();
                case 6:
                    return queryDynamicRawData((String[]) null);
                default:
                    throw new UnsupportedOperationException("Unknown Uri " + uri);
            }
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e7) {
            Log.e("IndexablesProvider", "Provider querying exception:", e7);
            return null;
        }
    }

    public abstract Cursor queryDynamicRawData(String[] strArr);

    public abstract Cursor queryNonIndexableKeys(String[] strArr);

    public abstract Cursor queryRawData(String[] strArr);

    public abstract Cursor querySiteMapPairs();

    public abstract Cursor querySliceUriPairs();

    public abstract Cursor queryXmlResources(String[] strArr);

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Update not supported");
    }
}
