package com.samsung.android.gallery.settings.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.ui.TwoStepVerificationFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoStepVerificationActivity extends BaseActivity {
    TwoStepVerificationFragment mFragment;

    private boolean isActivityResumed() {
        return getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    private boolean isFragmentResumed() {
        TwoStepVerificationFragment twoStepVerificationFragment = this.mFragment;
        if (twoStepVerificationFragment == null || !twoStepVerificationFragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return false;
        }
        return true;
    }

    public String getActivityTitle() {
        return "";
    }

    public ViewGroup getContentFrame() {
        return (ViewGroup) findViewById(R$id.content);
    }

    public int getLayoutId() {
        return R$layout.activity_two_step_verification_layout;
    }

    public void onBackPressed() {
        if (!onBackPressedOnFragment()) {
            super.onBackPressed();
        }
    }

    public boolean onBackPressedOnFragment() {
        if (!isFragmentResumed() && isActivityResumed()) {
            return true;
        }
        TwoStepVerificationFragment twoStepVerificationFragment = this.mFragment;
        if (twoStepVerificationFragment == null || !twoStepVerificationFragment.onBackPressed()) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        TwoStepVerificationFragment twoStepVerificationFragment = new TwoStepVerificationFragment(this);
        this.mFragment = twoStepVerificationFragment;
        commitFragment(twoStepVerificationFragment);
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

    public void initActionBar() {
    }
}
