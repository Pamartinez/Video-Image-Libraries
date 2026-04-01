package fd;

import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c extends a {
    private String mAuthority;
    private Bundle mExtraInfo = new Bundle();
    private UriMatcher mMatcher;

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (!TextUtils.isEmpty(secQueryGetFingerprint())) {
            this.mAuthority = providerInfo.authority;
            UriMatcher uriMatcher = new UriMatcher(-1);
            this.mMatcher = uriMatcher;
            uriMatcher.addURI(this.mAuthority, "sec_settings/sec_variable_raw_data", 301);
            this.mMatcher.addURI(this.mAuthority, "sec_settings/sec_non_indexables_key", StatusCodes.INPUT_MISSING);
            return;
        }
        throw new ClassCastException("secQueryGetFingerprint must implement");
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        char c5 = 65535;
        if (bundle != null) {
            this.mExtraInfo.putInt("isDexMode", bundle.getInt("isDexMode", -1));
        }
        str.getClass();
        switch (str.hashCode()) {
            case -1946714157:
                if (str.equals("secGetVersion")) {
                    c5 = 0;
                    break;
                }
                break;
            case -125085888:
                if (str.equals("secGetAvailability")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1187993183:
                if (str.equals("secGetFingerprint")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString("key_version", String.valueOf(1));
                return bundle;
            case 1:
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putInt("availability", secQueryAvailability(bundle));
                return bundle;
            case 2:
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString("key_fingerprint", secQueryGetFingerprint());
                return bundle;
            default:
                return super.call(str, str2, bundle);
        }
    }

    public String getType(Uri uri) {
        int match = this.mMatcher.match(uri);
        if (match == 300) {
            return "vnd.android.cursor.dir/sec_non_indexables_key";
        }
        if (match != 301) {
            return super.getType(uri);
        }
        return "vnd.android.cursor.dir/sec_variable_raw_data";
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Bundle bundle;
        Set<String> queryParameterNames;
        int i2;
        if (!(uri == null || (queryParameterNames = uri.getQueryParameterNames()) == null || queryParameterNames.isEmpty())) {
            for (String next : queryParameterNames) {
                next.getClass();
                if (next.equals("isDexMode")) {
                    try {
                        i2 = Integer.parseInt(uri.getQueryParameter("isDexMode"));
                    } catch (NullPointerException | NumberFormatException e) {
                        Log.e("SecSearchIndexablesProvider", "getValueAsInt() " + uri + " / " + e);
                        i2 = -1;
                    }
                    this.mExtraInfo.putInt("isDexMode", i2);
                }
            }
        }
        int match = this.mMatcher.match(uri);
        if (match == 300) {
            Set<String> queryParameterNames2 = uri.getQueryParameterNames();
            if (queryParameterNames2 == null || queryParameterNames2.isEmpty()) {
                bundle = null;
            } else {
                bundle = new Bundle();
                for (String next2 : queryParameterNames2) {
                    bundle.putString(next2, uri.getQueryParameter(next2));
                }
            }
            return secQueryNonIndexableKeys((String[]) null, bundle);
        } else if (match != 301) {
            return super.query(uri, strArr, str, strArr2, str2);
        } else {
            return secQueryVariableRawData((String[]) null);
        }
    }

    public /* bridge */ /* synthetic */ Cursor queryDynamicRawData(String[] strArr) {
        return null;
    }

    public /* bridge */ /* synthetic */ Cursor querySiteMapPairs() {
        return null;
    }

    public /* bridge */ /* synthetic */ Cursor querySliceUriPairs() {
        return null;
    }

    public int secQueryAvailability(Bundle bundle) {
        Cursor cursor;
        String string = bundle.getString("preference_key", "");
        if (TextUtils.isEmpty(string)) {
            return 1;
        }
        boolean containsKey = bundle.containsKey("requiringConfigInfo");
        String[] strArr = b.b;
        if (containsKey) {
            cursor = secQueryNonIndexableKeys(strArr, bundle);
        } else {
            cursor = queryNonIndexableKeys(strArr);
        }
        if (cursor != null && cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                if (string.equals(cursor.getString(0))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public abstract String secQueryGetFingerprint();

    public Cursor secQueryNonIndexableKeys(String[] strArr, Bundle bundle) {
        return queryNonIndexableKeys(strArr);
    }

    @Deprecated
    public Cursor secQueryVariableRawData(String[] strArr) {
        return null;
    }
}
