package com.samsung.android.gallery.settings.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.ui.AboutFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AboutActivity extends BaseActivity {
    public String getActivityTitle() {
        return "";
    }

    public ViewGroup getContentFrame() {
        return (ViewGroup) findViewById(R$id.content);
    }

    public int getLayoutId() {
        return R$layout.activity_about_page_layout;
    }

    public /* bridge */ /* synthetic */ void initActionBar() {
        super.initActionBar();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        commitFragment(new AboutFragment());
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
