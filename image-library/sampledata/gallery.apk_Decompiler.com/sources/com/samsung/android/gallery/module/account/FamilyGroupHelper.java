package com.samsung.android.gallery.module.account;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.concurrent.atomic.AtomicReference;
import x6.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FamilyGroupHelper {
    private static final Uri FAMILY_GROUP_INFO_URI = Uri.parse("content://com.samsung.android.samsungaccount.familyGroupProvider/family_group_info");
    public static final Uri FAMILY_GROUP_MEMBER_INFO_URI = Uri.parse("content://com.samsung.android.samsungaccount.familyGroupProvider/family_group_member_info");

    public static int getFamilyMemberCount(Context context) {
        Cursor query;
        Throwable th;
        int i2 = 0;
        if (context == null || !Features.isEnabled(Features.SUPPORT_FAMILY_ACCOUNT_PROVIDER)) {
            return 0;
        }
        try {
            query = context.getContentResolver().query(FAMILY_GROUP_MEMBER_INFO_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                i2 = query.getCount();
            }
            if (query != null) {
                query.close();
            }
            return i2;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getFamilyMemberCount failed : "), "FamilyGroupHelper");
            return 1;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static String getFamilyMemberType(Context context) {
        AtomicReference atomicReference = new AtomicReference("none");
        if (context == null || !Features.isEnabled(Features.SUPPORT_FAMILY_ACCOUNT_PROVIDER)) {
            return (String) atomicReference.get();
        }
        if (ThreadUtil.isMainThread()) {
            LatchBuilder.executeLatch(1000, new f(9, context, atomicReference));
        } else {
            requestMyMemberType(context, atomicReference);
        }
        return (String) atomicReference.get();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0036 A[Catch:{ all -> 0x0024, all -> 0x002a, Exception -> 0x0030 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean hasFamilyGroup(android.content.Context r8) {
        /*
            r1 = 0
            if (r8 == 0) goto L_0x0046
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.SUPPORT_FAMILY_ACCOUNT_PROVIDER
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            if (r0 != 0) goto L_0x000c
            goto L_0x0046
        L_0x000c:
            android.content.ContentResolver r2 = r8.getContentResolver()
            android.net.Uri r3 = FAMILY_GROUP_INFO_URI     // Catch:{ Exception -> 0x0030 }
            r6 = 0
            r7 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0030 }
            if (r8 == 0) goto L_0x0033
            int r0 = r8.getCount()     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0024:
            r0 = move-exception
            r2 = r0
            r8.close()     // Catch:{ all -> 0x002a }
            goto L_0x002f
        L_0x002a:
            r0 = move-exception
            r8 = r0
            r2.addSuppressed(r8)     // Catch:{ Exception -> 0x0030 }
        L_0x002f:
            throw r2     // Catch:{ Exception -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            r8 = r0
            goto L_0x003a
        L_0x0033:
            r0 = r1
        L_0x0034:
            if (r8 == 0) goto L_0x0039
            r8.close()     // Catch:{ Exception -> 0x0030 }
        L_0x0039:
            return r0
        L_0x003a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "hasFamilyGroup failed : "
            r0.<init>(r2)
            java.lang.String r2 = "FamilyGroupHelper"
            A.a.s(r8, r0, r2)
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.account.FamilyGroupHelper.hasFamilyGroup(android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    public static void requestMyMemberType(Context context, AtomicReference<String> atomicReference) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(FAMILY_GROUP_MEMBER_INFO_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    while (true) {
                        if (query.getInt(query.getColumnIndex("is_me")) <= 0) {
                            if (!query.moveToNext()) {
                                break;
                            }
                        } else {
                            atomicReference.set(query.getString(query.getColumnIndex("member_type")));
                            break;
                        }
                    }
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestMyMemberType failed : "), "FamilyGroupHelper");
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }
}
