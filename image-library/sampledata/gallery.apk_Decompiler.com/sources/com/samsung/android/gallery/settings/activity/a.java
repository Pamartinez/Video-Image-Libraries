package com.samsung.android.gallery.settings.activity;

import android.content.Intent;
import com.samsung.android.gallery.settings.activity.AppPermissionActivity;
import com.samsung.android.gallery.settings.activity.ThirdPartyAccessActivity;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseActivity e;

    public /* synthetic */ a(BaseActivity baseActivity, int i2) {
        this.d = i2;
        this.e = baseActivity;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BaseActivity baseActivity = this.e;
        switch (i2) {
            case 0:
                ((AppPermissionActivity) baseActivity).bindTypeView((AppPermissionActivity.PermissionType) obj);
                return;
            case 1:
                ((ThirdPartyAccessActivity) baseActivity).bindCategoryView((ThirdPartyAccessActivity.SubCategory) obj);
                return;
            default:
                ((SettingActivity) baseActivity).lambda$onHomeAsUpPressed$0((Intent) obj);
                return;
        }
    }
}
