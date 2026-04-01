package com.samsung.android.gallery.app.ui.container.menu;

import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TabMenuHelper {
    public static int getBottomMenuIds(boolean z) {
        if (z) {
            return R.menu.menu_bottom_tab;
        }
        return getPickerBottomMenuIds();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getIdByLocationKey(java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            r1 = -1
            if (r0 != 0) goto L_0x0093
            java.lang.String r2 = com.samsung.android.gallery.support.utils.ArgumentsUtil.removeArgs(r2)
            r2.getClass()
            int r0 = r2.hashCode()
            switch(r0) {
                case -2009053537: goto L_0x0065;
                case -984961596: goto L_0x005a;
                case -440239236: goto L_0x004f;
                case -212479357: goto L_0x0044;
                case -125579287: goto L_0x0039;
                case 263612166: goto L_0x002e;
                case 383247789: goto L_0x0023;
                case 983147555: goto L_0x0018;
                default: goto L_0x0015;
            }
        L_0x0015:
            r2 = r1
            goto L_0x006f
        L_0x0018:
            java.lang.String r0 = "location://collection"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0021
            goto L_0x0015
        L_0x0021:
            r2 = 7
            goto L_0x006f
        L_0x0023:
            java.lang.String r0 = "location://search"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x002c
            goto L_0x0015
        L_0x002c:
            r2 = 6
            goto L_0x006f
        L_0x002e:
            java.lang.String r0 = "location://timeline"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0037
            goto L_0x0015
        L_0x0037:
            r2 = 5
            goto L_0x006f
        L_0x0039:
            java.lang.String r0 = "location://albums"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0042
            goto L_0x0015
        L_0x0042:
            r2 = 4
            goto L_0x006f
        L_0x0044:
            java.lang.String r0 = "location://story/albums"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x004d
            goto L_0x0015
        L_0x004d:
            r2 = 3
            goto L_0x006f
        L_0x004f:
            java.lang.String r0 = "location://sharing/albums"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0058
            goto L_0x0015
        L_0x0058:
            r2 = 2
            goto L_0x006f
        L_0x005a:
            java.lang.String r0 = "location://mtp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0063
            goto L_0x0015
        L_0x0063:
            r2 = 1
            goto L_0x006f
        L_0x0065:
            java.lang.String r0 = "location://search/fileList/KeywordTab"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x006e
            goto L_0x0015
        L_0x006e:
            r2 = 0
        L_0x006f:
            switch(r2) {
                case 0: goto L_0x008f;
                case 1: goto L_0x008b;
                case 2: goto L_0x0087;
                case 3: goto L_0x0083;
                case 4: goto L_0x007f;
                case 5: goto L_0x007b;
                case 6: goto L_0x0077;
                case 7: goto L_0x0073;
                default: goto L_0x0072;
            }
        L_0x0072:
            goto L_0x0093
        L_0x0073:
            r2 = 2131296367(0x7f09006f, float:1.8210649E38)
            return r2
        L_0x0077:
            r2 = 2131296505(0x7f0900f9, float:1.8210929E38)
            return r2
        L_0x007b:
            r2 = 2131296545(0x7f090121, float:1.821101E38)
            return r2
        L_0x007f:
            r2 = 2131296339(0x7f090053, float:1.8210592E38)
            return r2
        L_0x0083:
            r2 = 2131296538(0x7f09011a, float:1.8210996E38)
            return r2
        L_0x0087:
            r2 = 2131296524(0x7f09010c, float:1.8210967E38)
            return r2
        L_0x008b:
            r2 = 2131296455(0x7f0900c7, float:1.8210827E38)
            return r2
        L_0x008f:
            r2 = 2131296506(0x7f0900fa, float:1.821093E38)
            return r2
        L_0x0093:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.menu.TabMenuHelper.getIdByLocationKey(java.lang.String):int");
    }

    public static String getLocationKeyById(int i2) {
        switch (i2) {
            case R.id.action_albums:
                return "location://albums";
            case R.id.action_collection:
                return "location://collection";
            case R.id.action_menu_list:
                return null;
            case R.id.action_mtp:
                return "location://mtp";
            case R.id.action_search:
                return "location://search";
            case R.id.action_search_keyword:
                return "location://search/fileList/KeywordTab";
            case R.id.action_sharings:
                return "location://sharing/albums";
            case R.id.action_stories:
                return "location://story/albums";
            case R.id.action_timeline:
                return "location://timeline";
            default:
                throw new IllegalArgumentException("unexpected menuItem id");
        }
    }

    private static int getPickerBottomMenuIds() {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            return R.menu.menu_bottom_tab_picker_v2;
        }
        return R.menu.menu_bottom_tab_picker;
    }

    public static boolean hasMoreTab(Blackboard blackboard) {
        if (blackboard == null || !PickerUtil.isNormalLaunchMode(blackboard)) {
            return false;
        }
        return true;
    }
}
