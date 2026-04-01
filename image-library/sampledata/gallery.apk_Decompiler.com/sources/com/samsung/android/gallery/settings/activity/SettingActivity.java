package com.samsung.android.gallery.settings.activity;

import A.a;
import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.receiver.GlobalActionReceiver;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.Locale;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SettingActivity extends BasePreferenceActivity {
    private final Runnable mActivityCreateBg = new d(this, 1);

    private void initDateFormat() {
        ThreadPool.getInstance().submit(new g(this));
    }

    private boolean isSharingServiceGuided(String str) {
        if (!SettingPreference.SharingServiceEnabler.support(getApplicationContext()) || !SettingPreference.SharedNotification.key.equals(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$initDateFormat$1(ThreadPool.JobContext jobContext) {
        TimeUtil.initDateFormat(Locale.getDefault(), getString(R$string.today), getString(R$string.yesterday));
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHomeAsUpPressed$0(Intent intent) {
        String stringExtra;
        if (isGuidedFromSystemSettings(intent) && (stringExtra = intent.getStringExtra(":settings:fragment_args_key")) != null && !isSharingServiceGuided(stringExtra)) {
            startSamsungAppSettings();
        }
    }

    /* access modifiers changed from: private */
    public void onActivityCreateBg() {
        if (!isDestroyed() && !isFinishing()) {
            long currentTimeMillis = System.currentTimeMillis();
            SamsungCloudCompat.initSamsungCloud2(getApplicationContext());
            GlobalActionReceiver.getInstance().register(getApplicationContext());
            a.x(new StringBuilder("onActivityCreateBG +"), currentTimeMillis, this.TAG);
        }
    }

    private void startSamsungAppSettings() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_SETTINGS");
            Bundle bundle = new Bundle();
            bundle.putString("classname", "AppSetting");
            bundle.putString("launch_reason", "hierarchy_up");
            intent.putExtra(":settings:show_fragment_args", bundle);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("startSamsungAppSettings failed e="), this.TAG);
        }
    }

    public String getActivityTitle() {
        return getString(R$string.gallery_settings);
    }

    public Fragment getFragment(String str) {
        if (isSharingServiceGuided(str)) {
            return new SharingServiceFragment();
        }
        return new SettingFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ThreadUtil.postOnBgThread(this.mActivityCreateBg);
        initDateFormat();
        Intent intent = getIntent();
        if (intent == null || !"labs.album.bnr".equals(intent.getStringExtra("gallery.setting.location"))) {
            commitFragment();
        } else {
            commitFragment(new LabsAlbumBnRFragment());
        }
    }

    public void onDestroy() {
        super.onDestroy();
        ThreadUtil.removeCallbackOnBgThread(this.mActivityCreateBg);
        GlobalActionReceiver.getInstance().unregister(getApplicationContext());
    }

    public void onHomeAsUpPressed() {
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5) && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Optional.ofNullable(getIntent()).ifPresent(new a(this, 2));
        }
        super.onHomeAsUpPressed();
    }
}
