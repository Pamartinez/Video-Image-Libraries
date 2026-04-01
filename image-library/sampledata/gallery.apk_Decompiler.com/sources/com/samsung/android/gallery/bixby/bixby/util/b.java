package com.samsung.android.gallery.bixby.bixby.util;

import com.samsung.android.gallery.bixby.bixby.util.ActionHandlerUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionHandlerUtil.ComparatorName {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2614a;
    public final /* synthetic */ ActionHandlerUtil b;

    public /* synthetic */ b(ActionHandlerUtil actionHandlerUtil, int i2) {
        this.f2614a = i2;
        this.b = actionHandlerUtil;
    }

    public final boolean compareName(String str, String str2) {
        int i2 = this.f2614a;
        ActionHandlerUtil actionHandlerUtil = this.b;
        switch (i2) {
            case 0:
                return actionHandlerUtil.compareNameUsingPattern(str, str2);
            case 1:
                return actionHandlerUtil.compareNameExceptSpecialChar(str, str2);
            default:
                return actionHandlerUtil.compareName(str, str2);
        }
    }
}
