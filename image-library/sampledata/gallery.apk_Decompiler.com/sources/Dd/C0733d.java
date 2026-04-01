package Dd;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import o1.C0246a;

/* renamed from: Dd.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0733d {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f3333a = new HashMap();

    public final void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            C0246a.m0("Failure to build logs [setting preference] : Setting key cannot be null.");
        }
        HashMap hashMap = this.f3333a;
        if (!hashMap.containsKey(str) && !TextUtils.isEmpty(str)) {
            hashMap.put(str, new HashSet());
        } else if (TextUtils.isEmpty(str)) {
            C0246a.m0("Failure to build logs [setting preference] : Preference name cannot be null.");
        }
        ((Set) hashMap.get(str)).add(str2);
    }
}
