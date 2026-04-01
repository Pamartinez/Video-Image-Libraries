package com.samsung.android.gallery.settings.ui;

import android.accounts.Account;
import com.samsung.android.gallery.module.account.SamsungAccountManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class J implements SamsungAccountManager.OnAccountUpdatedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SettingCloud f3115a;

    public /* synthetic */ J(SettingCloud settingCloud) {
        this.f3115a = settingCloud;
    }

    public final void onSyncAccountsUpdated(Account account, boolean z) {
        this.f3115a.initCloudCategories(account, z);
    }
}
