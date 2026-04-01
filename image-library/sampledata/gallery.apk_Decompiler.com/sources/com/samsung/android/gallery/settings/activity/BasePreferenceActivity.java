package com.samsung.android.gallery.settings.activity;

import F8.a;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.window.embedding.c;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.provider.SettingSearchIndexablesProvider;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BasePreferenceActivity extends BaseActivity {
    private void checkValidPreference(Consumer<Boolean> consumer) {
        ThreadUtil.postOnBgThread(new c(23, this, consumer));
    }

    private boolean findPreference(Cursor cursor, String str) {
        while (cursor.moveToNext()) {
            if (cursor.getString(12).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String getGuideKey(Intent intent) {
        if (intent == null) {
            return null;
        }
        if (isGuidedFromBixby(intent)) {
            return (String) Optional.ofNullable(intent.getData()).map(new f(0)).orElse((Object) null);
        }
        if (isGuidedFromSystemSettings(intent)) {
            return (String) Optional.ofNullable(intent.getExtras()).map(new f(1)).orElse((Object) null);
        }
        return (String) Optional.ofNullable(intent.getExtras()).map(new f(2)).orElse((Object) null);
    }

    private boolean isGuidedFromBixby(Intent intent) {
        return "android.intent.action.VIEW".equals(intent.getAction());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkValidPreference$4(Consumer consumer, boolean z, String str) {
        consumer.accept(Boolean.valueOf(z));
        String str2 = this.TAG;
        Log.d(str2, "checkValidPreference key=" + str + ", found=" + z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkValidPreference$5(Consumer consumer) {
        Cursor queryMenuData;
        try {
            queryMenuData = new SettingSearchIndexablesProvider().queryMenuData();
            String guideKey = getGuideKey(getIntent());
            runOnUiThread(new a(this, consumer, findPreference(queryMenuData, guideKey), guideKey));
            if (queryMenuData != null) {
                queryMenuData.close();
                return;
            }
            return;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("checkValidPreference failed. e="), this.TAG);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$commitFragment$0(String str, Boolean bool) {
        if (bool.booleanValue()) {
            moveSettingWithGuide(str);
            return;
        }
        Toast.makeText(this, getString(R$string.menu_currently_not_available), 0).show();
        finish();
    }

    private void moveSettingWithGuide(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("preference_key", str);
        }
        Fragment fragment = getFragment(str);
        fragment.setArguments(bundle);
        commitFragment(fragment);
    }

    public void commitFragment() {
        String guideKey = getGuideKey(getIntent());
        if (guideKey != null) {
            checkValidPreference(new e(0, this, guideKey));
        } else {
            moveSettingWithGuide((String) null);
        }
    }

    public abstract Fragment getFragment(String str);

    public int getLayoutId() {
        return R$layout.activity_settings_layout;
    }

    public /* bridge */ /* synthetic */ void initActionBar() {
        super.initActionBar();
    }

    public boolean isGuidedFromSystemSettings(Intent intent) {
        if ("com.sec.android.intent.action.SEC_APPLICATION_SETTINGS".equals(intent.getAction()) || "com.android.gallery.settings.action.VIEW_SHARED_ALBUM_NOTIFICATIONS".equals(intent.getAction()) || "com.android.gallery.settings.action.ACTION_VIEW_INTELLIGENT_FEATURES".equals(intent.getAction())) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public /* bridge */ /* synthetic */ void setBackPressedCallbackEnabled(boolean z) {
        super.setBackPressedCallbackEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }
}
