package com.samsung.android.gallery.settings.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.helper.SettingLayoutUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseActivity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    private static final boolean ENABLE_ON_BACK_INVOKED_CALLBACK = SdkConfig.atLeast(SdkConfig.GED.V);
    protected final String TAG = getClass().getSimpleName();
    protected final Blackboard mBlackboard = Blackboard.getInstance(getBlackboardTag());
    /* access modifiers changed from: private */
    public final OnBackPressedCallback mCallback = new OnBackPressedCallback(false) {
        public void handleOnBackPressed() {
            Log.majorEvent(BaseActivity.this.TAG, "handleOnBackPressed");
            try {
                if (!BaseActivity.this.onBackPressedOnFragment()) {
                    BaseActivity.this.mCallback.setEnabled(false);
                    BaseActivity.this.getOnBackPressedDispatcher().onBackPressed();
                }
            } catch (IllegalStateException e) {
                Log.e((CharSequence) BaseActivity.this.TAG, "handleOnBackPressed failed", (Throwable) e);
                BaseActivity.this.finish();
            }
        }
    };

    private String getBlackboardTag() {
        return toString();
    }

    /* access modifiers changed from: private */
    public void handleHomeAsUpAsync() {
        try {
            getSupportFragmentManager().popBackStack();
        } catch (Exception unused) {
            Log.e(this.TAG, "handleHomeAsUpAsync failed");
        }
    }

    private void setApplyWindowInsetsListener(ViewGroup viewGroup) {
        if (viewGroup != null && viewGroup.getChildCount() > 0) {
            viewGroup.getChildAt(0).setOnApplyWindowInsetsListener(new b(this));
        }
    }

    private void setDisplayCutOutStateFlag() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
    }

    private void setOnBackPressedDispatcherCallback() {
        if (ENABLE_ON_BACK_INVOKED_CALLBACK) {
            getOnBackPressedDispatcher().addCallback(this, this.mCallback);
        }
    }

    private void setScreenMode() {
        int i2;
        if (SystemUi.hasNoStatusBarInLandscape(this)) {
            Window window = getWindow();
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            if (getResources().getConfiguration().orientation == 2) {
                i2 = systemUiVisibility | 4;
            } else {
                i2 = systemUiVisibility & -5;
            }
            window.getDecorView().setSystemUiVisibility(i2);
        }
    }

    private void updateToolbar(Toolbar toolbar) {
        ((CollapsingToolbarLayout) toolbar.getRootView().findViewById(R$id.toolbar_layout)).setTitle(getActivityTitle());
    }

    public final void commitFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction().replace(R$id.content, fragment).setTransition(0).commit();
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
        }
    }

    public abstract String getActivityTitle();

    public ViewGroup getContentFrame() {
        return null;
    }

    public abstract int getLayoutId();

    /* JADX WARNING: type inference failed for: r2v8, types: [java.util.function.Consumer, java.lang.Object] */
    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R$id.toolbar);
        setSupportActionBar(toolbar);
        this.mBlackboard.publish("data://settings/appbar", findViewById(R$id.appbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) getActivityTitle());
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(false);
            try {
                Optional.ofNullable((TextView) getWindow().findViewById(Resources.getSystem().getIdentifier("action_bar_title", "id", "android"))).ifPresent(new Object());
            } catch (NullPointerException unused) {
                Log.d(this.TAG, "initActionBar#action_bar_title failed");
            }
        }
        updateToolbar(toolbar);
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        SettingLayoutUtils.adjustSettingToolbarLayout(findViewById(R$id.toolbar), (AppBarLayout) findViewById(R$id.appbar), windowInsets);
        updateContentHorizontalPadding(view, windowInsets);
        return windowInsets;
    }

    public boolean onBackPressedOnFragment() {
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setScreenMode();
        updateLayout();
    }

    public void onCreate(Bundle bundle) {
        Object obj;
        super.onCreate(bundle);
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onCreate ");
        if (bundle == null) {
            obj = "";
        } else {
            obj = bundle;
        }
        sb2.append(obj);
        Log.p(str, sb2.toString());
        this.mBlackboard.publish("data://app_context", getApplicationContext());
        this.mBlackboard.publish("data://activity", this);
        if (bundle == null || RuntimePermissionUtil.hasLaunchPermission(this)) {
            setContentView(getLayoutId());
            setApplyWindowInsetsListener((ViewGroup) findViewById(16908290));
            setOnBackPressedDispatcherCallback();
            if (SdkConfig.atLeast(SdkConfig.GED.P)) {
                setDisplayCutOutStateFlag();
            }
            initActionBar();
            updateLayout();
            bindView();
            return;
        }
        Log.e(this.TAG, "onCreate: NoPermission > suicide");
        finish();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.reset(getBlackboardTag());
    }

    public void onHomeAsUpPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            ThreadUtil.postOnUiThreadDelayed(new d(this, 0), 200);
        } else if (ENABLE_ON_BACK_INVOKED_CALLBACK) {
            getOnBackPressedDispatcher().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        setScreenMode();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onHomeAsUpPressed();
        return true;
    }

    public void onResume() {
        super.onResume();
        setScreenMode();
        BixbyAppStateUtil.updateEmptyState(this.TAG);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.clear();
    }

    public void setBackPressedCallbackEnabled(boolean z) {
        if (ENABLE_ON_BACK_INVOKED_CALLBACK) {
            this.mCallback.setEnabled(z);
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        this.mBlackboard.publish("data://settings/toolbar", toolbar);
        super.setSupportActionBar(toolbar);
    }

    public void updateContentHorizontalPadding(View view, WindowInsets windowInsets) {
        ViewMarginUtils.setStartPadding(view, WindowUtils.getSystemInsetsStart(windowInsets));
        ViewMarginUtils.setEndPadding(view, WindowUtils.getSystemInsetsEnd(windowInsets));
    }

    public void updateLayout() {
        SettingLayoutUtils.updateLayoutWeight(this, findViewById(R$id.content_start_pane), getContentFrame(), findViewById(R$id.content_end_pane));
    }

    public void bindView() {
    }
}
