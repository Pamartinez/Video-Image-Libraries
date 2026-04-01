package com.google.android.gms.auth.api.signin;

import B3.a;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import x1.C0333a;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleSignInAccount extends C0333a implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new a(27);
    public final int d;
    public final String e;
    public final String f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f1332h;

    /* renamed from: i  reason: collision with root package name */
    public final Uri f1333i;

    /* renamed from: j  reason: collision with root package name */
    public String f1334j;
    public final long k;
    public final String l;
    public final List m;
    public final String n;

    /* renamed from: o  reason: collision with root package name */
    public final String f1335o;

    /* renamed from: p  reason: collision with root package name */
    public final HashSet f1336p = new HashSet();

    public GoogleSignInAccount(int i2, String str, String str2, String str3, String str4, Uri uri, String str5, long j2, String str6, ArrayList arrayList, String str7, String str8) {
        this.d = i2;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.f1332h = str4;
        this.f1333i = uri;
        this.f1334j = str5;
        this.k = j2;
        this.l = str6;
        this.m = arrayList;
        this.n = str7;
        this.f1335o = str8;
    }

    public static GoogleSignInAccount b(String str) {
        Uri uri;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl");
        if (!TextUtils.isEmpty(optString)) {
            uri = Uri.parse(optString);
        } else {
            uri = null;
        }
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            hashSet.add(new Scope(1, jSONArray.getString(i2)));
        }
        String optString2 = jSONObject.optString("id");
        if (jSONObject.has("tokenId")) {
            str2 = jSONObject.optString("tokenId");
        } else {
            str2 = null;
        }
        if (jSONObject.has("email")) {
            str3 = jSONObject.optString("email");
        } else {
            str3 = null;
        }
        if (jSONObject.has("displayName")) {
            str4 = jSONObject.optString("displayName");
        } else {
            str4 = null;
        }
        if (jSONObject.has("givenName")) {
            str5 = jSONObject.optString("givenName");
        } else {
            str5 = null;
        }
        if (jSONObject.has("familyName")) {
            str6 = jSONObject.optString("familyName");
        } else {
            str6 = null;
        }
        String string = jSONObject.getString("obfuscatedIdentifier");
        if (!TextUtils.isEmpty(string)) {
            GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, optString2, str2, str3, str4, uri, (String) null, parseLong, string, new ArrayList(hashSet), str5, str6);
            if (jSONObject.has("serverAuthCode")) {
                str7 = jSONObject.optString("serverAuthCode");
            }
            googleSignInAccount.f1334j = str7;
            return googleSignInAccount;
        }
        throw new IllegalArgumentException("Given String is empty or null");
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        if (!googleSignInAccount.l.equals(this.l)) {
            return false;
        }
        HashSet hashSet = new HashSet(googleSignInAccount.m);
        hashSet.addAll(googleSignInAccount.f1336p);
        HashSet hashSet2 = new HashSet(this.m);
        hashSet2.addAll(this.f1336p);
        if (hashSet.equals(hashSet2)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        HashSet hashSet = new HashSet(this.m);
        hashSet.addAll(this.f1336p);
        return ((this.l.hashCode() + 527) * 31) + hashSet.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.T(parcel, 2, this.e);
        c.T(parcel, 3, this.f);
        c.T(parcel, 4, this.g);
        c.T(parcel, 5, this.f1332h);
        c.S(parcel, 6, this.f1333i, i2);
        c.T(parcel, 7, this.f1334j);
        c.Y(8, 8, parcel);
        parcel.writeLong(this.k);
        c.T(parcel, 9, this.l);
        c.V(parcel, this.m, 10);
        c.T(parcel, 11, this.n);
        c.T(parcel, 12, this.f1335o);
        c.X(parcel, W6);
    }
}
