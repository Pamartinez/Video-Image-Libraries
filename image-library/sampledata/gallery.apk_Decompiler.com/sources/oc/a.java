package oc;

import E2.i;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import java.util.ArrayList;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f3283a;
    public final Uri b;

    /* renamed from: c  reason: collision with root package name */
    public final i f3284c;
    public final ArrayList d = new ArrayList();
    public final String e;
    public final String f;

    public a(String str, Uri uri, String str2, String str3, i iVar) {
        j.e(str, "itemKey");
        j.e(uri, BuddyContract.Image.THUMBNAIL);
        j.e(str3, "contentDescription");
        if (!TextUtils.isEmpty(str)) {
            this.f3283a = str;
            this.b = uri;
            this.f3284c = iVar;
            this.e = str2;
            this.f = str3;
            return;
        }
        throw new IllegalArgumentException("itemKey must be not null or empty");
    }

    public final void a(int i2) {
        ArrayList arrayList = this.d;
        if (arrayList.size() > i2) {
            try {
                if (arrayList.get(i2) == null) {
                    throw null;
                }
                throw new ClassCastException();
            } catch (Exception e7) {
                pc.a.a("SearchResultItem", "fail to get action label: " + e7);
            }
        }
    }

    public final void b(int i2) {
        ArrayList arrayList = this.d;
        if (arrayList.size() > i2) {
            try {
                if (arrayList.get(i2) == null) {
                    throw null;
                }
                throw new ClassCastException();
            } catch (Exception e7) {
                pc.a.a("SearchResultItem", "fail to get action payload: " + e7);
            }
        }
    }
}
