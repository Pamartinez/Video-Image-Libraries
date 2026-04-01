package com.samsung.android.gallery.module.mde;

import android.database.Cursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import i.C0212a;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeCacheHelper {
    private static void getGroupString(SharingCursorHolder sharingCursorHolder, Cursor cursor, HashMap<String, String> hashMap) {
        String string = sharingCursorHolder.getString(cursor, sharingCursorHolder.indexGroupId, "");
        if (string != null) {
            hashMap.put(string, ArgumentsUtil.encode(getString(cursor, sharingCursorHolder.indexGroupName)) + "&" + sharingCursorHolder.getInt(cursor, sharingCursorHolder.indexMembersCount, 0) + "&" + getString(cursor, sharingCursorHolder.indexType) + "&" + sharingCursorHolder.getLong(cursor, sharingCursorHolder.indexCreatedTime, 0) + "&" + ArgumentsUtil.encode(getString(cursor, sharingCursorHolder.indexThumbnailLocalPath)) + "&");
        }
    }

    private static String getSpaceString(SharingCursorHolder sharingCursorHolder, Cursor cursor, HashMap<String, String> hashMap) {
        String string = getString(cursor, sharingCursorHolder.indexGroupId);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sharingCursorHolder.getInt(cursor, sharingCursorHolder.indexId, 0));
        sb2.append("&");
        C0086a.z(sb2, getString(cursor, sharingCursorHolder.indexSpaceId), "&", string, "&");
        sb2.append(sharingCursorHolder.getInt(cursor, sharingCursorHolder.indexUnreadCount, 0));
        sb2.append("&");
        sb2.append(sharingCursorHolder.getInt(cursor, sharingCursorHolder.indexMediaCount, 0));
        sb2.append("&");
        sb2.append(ArgumentsUtil.encode(getString(cursor, sharingCursorHolder.indexTitle)));
        sb2.append("&");
        sb2.append(getString(cursor, sharingCursorHolder.indexOwner));
        sb2.append("&");
        sb2.append(sharingCursorHolder.getInt(cursor, sharingCursorHolder.indexIsOwnedByMe, 0));
        sb2.append("&");
        sb2.append(ArgumentsUtil.encode(getString(cursor, sharingCursorHolder.indexThumbnailLocalPath)));
        sb2.append("&");
        sb2.append(getString(cursor, sharingCursorHolder.indexMetaData));
        sb2.append("&");
        sb2.append(getString(cursor, sharingCursorHolder.indexCoverItem));
        sb2.append("&");
        sb2.append(getString(cursor, sharingCursorHolder.indexCoverId));
        sb2.append("&");
        String p6 = C0212a.p(sb2, getString(cursor, sharingCursorHolder.indexMemo), "&");
        String str = hashMap.get(string);
        StringBuilder s = C0212a.s(p6);
        if (TextUtils.isEmpty(str)) {
            str = "&0&&0&&";
        }
        s.append(str);
        return C0212a.A(s.toString(), "\r");
    }

    private static String getString(Cursor cursor, int i2) {
        String str;
        if (i2 < 0) {
            str = null;
        } else {
            str = cursor.getString(i2);
        }
        if (str != null) {
            return str;
        }
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String makeSpaceGroupString(android.database.Cursor r4, android.database.Cursor r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            int r2 = r5.getCount()
            if (r2 <= 0) goto L_0x0025
            boolean r2 = r5.moveToFirst()
            if (r2 == 0) goto L_0x0025
            com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder$SharedCursorType r2 = com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder.SharedCursorType.GROUP_CURSOR
            com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder r2 = com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder.getCursorHolder(r5, r2)
        L_0x001c:
            getGroupString(r2, r5, r1)
            boolean r3 = r5.moveToNext()
            if (r3 != 0) goto L_0x001c
        L_0x0025:
            int r5 = r4.getCount()
            if (r5 <= 0) goto L_0x0049
            boolean r5 = r4.moveToFirst()
            if (r5 == 0) goto L_0x0049
            com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder$SharedCursorType r5 = com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder.SharedCursorType.SPACE_CURSOR
            com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder r5 = com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder.getCursorHolder(r4, r5)
        L_0x0037:
            java.lang.String r2 = getSpaceString(r5, r4, r1)
            r0.append(r2)
            java.lang.String r2 = "\u001f"
            r0.append(r2)
            boolean r2 = r4.moveToNext()
            if (r2 != 0) goto L_0x0037
        L_0x0049:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mde.MdeCacheHelper.makeSpaceGroupString(android.database.Cursor, android.database.Cursor):java.lang.String");
    }
}
