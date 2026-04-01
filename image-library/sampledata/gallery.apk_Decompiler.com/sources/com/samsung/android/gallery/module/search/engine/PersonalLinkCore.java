package com.samsung.android.gallery.module.search.engine;

import A.a;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.moneta.basicdomain.BasicDomainProvider;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonLink;
import com.samsung.android.sdk.moneta.basicdomain.service.PersonLinkService;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PersonalLinkCore {
    private static final PersonalLinkCore sInstance = new PersonalLinkCore();
    private final String TAG = getClass().getSimpleName();
    private PersonLinkService mPersonLinkService;

    public static PersonalLinkCore getInstance() {
        return sInstance;
    }

    private synchronized PersonLinkService getService(Context context) {
        try {
            if (this.mPersonLinkService == null) {
                this.mPersonLinkService = BasicDomainProvider.getPersonLinkService(context);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mPersonLinkService;
    }

    public long getContactId(Context context, String str) {
        try {
            return getService(context).getContactIdByGalleryPersonUuid(str);
        } catch (Exception e) {
            a.s(e, new StringBuilder("getContactId failed e="), this.TAG);
            return (long) 0;
        }
    }

    public boolean isLinked(Context context, String str) {
        boolean z;
        String str2;
        boolean z3 = false;
        try {
            if (TextUtils.isEmpty(str) || !getService(context).isLinked(str)) {
                z = false;
            } else {
                z = true;
            }
            try {
                String str3 = this.TAG;
                Boolean valueOf = Boolean.valueOf(z);
                if (TextUtils.isEmpty(str)) {
                    str2 = null;
                } else {
                    str2 = str.substring(0, Math.min(7, str.length()));
                }
                Log.v(str3, "ContactLink isLinked", valueOf, str2);
                return z;
            } catch (Exception e) {
                z3 = z;
                e = e;
                a.s(e, new StringBuilder("isLinked failed e="), this.TAG);
                return z3;
            }
        } catch (Exception e7) {
            e = e7;
            a.s(e, new StringBuilder("isLinked failed e="), this.TAG);
            return z3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSupported(android.content.Context r8) {
        /*
            r7 = this;
            r0 = 0
            com.samsung.android.sdk.moneta.PdeSdk r1 = com.samsung.android.sdk.moneta.PdeSdk.INSTANCE     // Catch:{ Exception -> 0x0031 }
            com.samsung.android.sdk.moneta.FeatureType r2 = com.samsung.android.sdk.moneta.FeatureType.PERSON_LINK_SERVICE     // Catch:{ Exception -> 0x0031 }
            boolean r8 = r1.isSupported(r8, r2)     // Catch:{ Exception -> 0x0031 }
            if (r8 == 0) goto L_0x0017
            com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi r1 = com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi.getInstance()     // Catch:{ Exception -> 0x0014 }
            boolean r1 = r1.isAppPdcAvailability()     // Catch:{ Exception -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r1 = move-exception
            r2 = r0
            goto L_0x0034
        L_0x0017:
            r1 = r0
        L_0x0018:
            java.lang.String r2 = r7.TAG     // Catch:{ Exception -> 0x002c }
            java.lang.String r3 = "ContactLink isSupported"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r8)     // Catch:{ Exception -> 0x002c }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x002c }
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}     // Catch:{ Exception -> 0x002c }
            com.samsung.android.gallery.support.utils.Log.v(r2, r3, r4)     // Catch:{ Exception -> 0x002c }
            goto L_0x0041
        L_0x002c:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L_0x0034
        L_0x0031:
            r1 = move-exception
            r8 = r0
            r2 = r8
        L_0x0034:
            java.lang.String r7 = r7.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "isSupported failed e="
            r3.<init>(r4)
            A.a.s(r1, r3, r7)
            r1 = r2
        L_0x0041:
            if (r8 == 0) goto L_0x0046
            if (r1 == 0) goto L_0x0046
            r0 = 1
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.engine.PersonalLinkCore.isSupported(android.content.Context):boolean");
    }

    public void link(Context context, String str, long j2) {
        try {
            getService(context).link(new PersonLink(str, j2));
        } catch (Exception e) {
            a.s(e, new StringBuilder("link failed e="), this.TAG);
        }
    }

    public synchronized void release() {
        this.mPersonLinkService = null;
    }

    public void unLink(Context context, List<String> list) {
        try {
            getService(context).unlinkByGalleryPersonUuid(list);
        } catch (Exception e) {
            a.s(e, new StringBuilder("unlink failed e="), this.TAG);
        }
    }
}
