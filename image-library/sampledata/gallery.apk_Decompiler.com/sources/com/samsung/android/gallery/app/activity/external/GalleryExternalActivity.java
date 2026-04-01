package com.samsung.android.gallery.app.activity.external;

import A4.C0367b;
import A4.C0372g;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.activity.GalleryActivity;
import com.samsung.android.gallery.module.abstraction.FlipCoverSavedData;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.ThemeType;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BrightnessModeHelper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IntentAction;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryExternalActivity extends GalleryActivity {
    private static final boolean SUPPORT_PICKER_THEME = SdkConfig.atLeast(SdkConfig.GED.V);
    private boolean mBrightnessCtrlEnabled;
    private boolean mIsNeedToRecoverBg;
    private boolean mIsPickMode;
    private boolean mIsRestartedFromCameraQuickView;

    private void checkCoverSavedData(Bundle bundle) {
        FlipCoverSavedData flipCoverSavedData;
        if (Features.isEnabled(Features.SUPPORT_FLIP_COVER_GALLERY) && bundle != null && !bundle.isEmpty() && (flipCoverSavedData = (FlipCoverSavedData) bundle.getParcelable("viewer_recover_data_from_flip_cover")) != null) {
            Log.d(this.TAG, "checkCoverSavedData", flipCoverSavedData);
            this.mBlackboard.publish("data://viewer_recover_data_from_flip_cover", flipCoverSavedData);
        }
    }

    private void checkRestarted(Bundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            this.mIsRestartedFromCameraQuickView = bundle.getBoolean("prev_from_camera");
        }
    }

    private void clearBrightnessLimit() {
        if (!this.mBrightnessCtrlEnabled) {
            return;
        }
        if (isFinishing()) {
            BrightnessModeHelper.releaseBrightnessLimit();
        } else {
            BrightnessModeHelper.clearBrightnessLimit(this);
        }
    }

    private boolean commitChildFragment(Fragment fragment, String str) {
        return this.mFragmentDelegate.commitChildFragment(fragment, str);
    }

    private String getBaseActivityPackage() {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager == null) {
            Log.w(this.TAG, "getBaseActivityPackage failed : ActivityManager is null");
            return null;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        if (runningTasks == null || runningTasks.isEmpty()) {
            runningTaskInfo = null;
        } else {
            runningTaskInfo = runningTasks.get(0);
        }
        if (runningTaskInfo != null) {
            return runningTaskInfo.baseActivity.getPackageName();
        }
        Log.w(this.TAG, "getBaseActivityPackage failed : task is null");
        return null;
    }

    private boolean isCameraQuickViewOnLockScreen() {
        LaunchIntent launchIntent = getLaunchIntent();
        if (!launchIntent.isFromCamera() || !launchIntent.isFromLockScreen()) {
            return false;
        }
        return true;
    }

    private boolean isInternalPick() {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent == null || launchIntent.getIntent().getStringExtra("blackboard_name") == null) {
            return false;
        }
        return true;
    }

    private boolean isNeedToUpdateBgColor() {
        Intent intent = getIntent();
        if (!isNeedToUpdateBgColorForR(intent) || this.mActivityLayout == null || intent == null) {
            return false;
        }
        if ((IntentAction.isForView(intent.getAction()) || IntentAction.isForShortCutItemView(intent.getAction())) && intent.getData() != null && !TextUtils.equals("gallery", intent.getStringExtra("check"))) {
            return true;
        }
        return false;
    }

    private boolean isNeedToUpdateBgColorForR(Intent intent) {
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            return true;
        }
        if (intent == null || !intent.getBooleanExtra("from-Camera", false)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSaveInstanceState$1(Clipboard clipboard) {
        if (clipboard.getTotalCount() > 0) {
            getIntent().putExtra("picker_preselected_sem_id", StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, clipboard.cloneSet()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setLockScreenView$0() {
        try {
            SystemUi.clearShowWhenLocked(this);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "clearShowWhenLock failed", (Throwable) e);
        }
    }

    private void onSaveLastDataFromFlipCover(Bundle bundle) {
        Uri uri = (Uri) getBlackboard().read("data://viewer_last_data_from_flip_cover");
        if (uri != null) {
            String str = (String) getBlackboard().read("location://variable/currentv1");
            FlipCoverSavedData flipCoverSavedData = new FlipCoverSavedData(uri, ArgumentsUtil.getArgValue(str, Message.KEY_POSITION, 0), str);
            bundle.putParcelable("viewer_recover_data_from_flip_cover", flipCoverSavedData);
            Log.d(this.TAG, "SaveLastDataFromFlipCover", flipCoverSavedData);
        }
    }

    private void setBrightnessLowerBoundLimit() {
        LaunchIntent launchIntent;
        if (this.mBrightnessCtrlEnabled && (launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent", null)) != null) {
            BrightnessModeHelper.setBrightnessLowerBoundLimit(this, launchIntent.getBrightness());
        }
    }

    private void setLockScreenView() {
        if (isCameraQuickViewOnLockScreen()) {
            setShowWhenLocked(true);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags |= 524288;
            getWindow().setAttributes(attributes);
            return;
        }
        ThreadUtil.postOnBgThread(new C0372g(22, this));
    }

    private void setScreenOrientation() {
        int i2;
        if (getLaunchIntent().isFromCamera()) {
            Log.d(this.TAG, "setRequestOrientation from camera 4");
            setRequestedOrientation(4);
        } else if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            String baseActivityPackage = getBaseActivityPackage();
            if (TextUtils.equals(baseActivityPackage, "com.sec.android.gallery3d")) {
                i2 = 2;
            } else {
                i2 = 3;
            }
            String str = this.TAG;
            Log.d(str, "setRequestOrientation : " + i2 + ", baseActivity : " + baseActivityPackage);
            setRequestedOrientation(i2);
        }
    }

    private boolean supportBrightnessControl() {
        if (SdkConfig.lessThan(SdkConfig.GED.S)) {
            LaunchIntent launchIntent = getLaunchIntent();
            if (!launchIntent.isFromCamera() || !launchIntent.isKeepBrightness() || !"com.sec.android.app.camera".equals(getCallingPackage())) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void updateAppTheme() {
        int nightMode;
        int i2;
        LaunchIntent launchIntent = getLaunchIntent();
        String theme = launchIntent.getTheme();
        if (launchIntent.isFromGalleryWidget()) {
            if ("widget_cover_theme".equals(theme)) {
                Resources.Theme theme2 = getTheme();
                if (FoldUtils.isFlipCoverScreen(this)) {
                    i2 = 2131952536;
                } else {
                    i2 = 2131952545;
                }
                theme2.applyStyle(i2, true);
            }
        } else if (SUPPORT_PICKER_THEME && this.mIsPickMode && (nightMode = ThemeType.getNightMode(theme)) > 0 && nightMode != ThemeType.getNightMode(getResources().getConfiguration())) {
            Log.d(this.TAG, "AppTheme#update", theme, Integer.valueOf(nightMode));
            getDelegate().setLocalNightMode(nightMode);
        }
    }

    public void clearContentLayoutBgForExternal() {
        int color = getColor(17170445);
        setBackgroundColor(color);
        getWindow().getDecorView().setBackgroundColor(color);
    }

    public boolean commitFragment(Fragment fragment, String str) {
        if (!this.mIsPickMode || !commitChildFragment(fragment, str)) {
            return super.commitFragment(fragment, str);
        }
        return true;
    }

    public Subscriber createActivityHandler(Blackboard blackboard) {
        return new GalleryExternalActivityHandler(blackboard, this);
    }

    public ArrayList<Subscriber> createSubscribers() {
        ArrayList<Subscriber> createSubscribers = super.createSubscribers();
        createSubscribers.add(new PickerSelectionHandler(getBlackboard()));
        return createSubscribers;
    }

    public Subscriber createViewNavigator(Blackboard blackboard) {
        return new ViewNavigatorExternal(blackboard, this);
    }

    public void handleNightModeChange(Bundle bundle) {
        super.handleNightModeChange(bundle);
        if (bundle.getBoolean("is_internal_pick")) {
            Log.e(this.TAG, "finish by night mode changed");
            finish();
        }
    }

    public boolean isRestartedFromQuickView() {
        return this.mIsRestartedFromCameraQuickView;
    }

    public void onCreate(Bundle bundle) {
        this.mBrightnessCtrlEnabled = supportBrightnessControl();
        this.mIsPickMode = IntentAction.isForPick(getIntent().getAction());
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.USER_EVENT);
        setScreenOrientation();
        checkRestarted(bundle);
        checkCoverSavedData(bundle);
        updateAppTheme();
        super.onCreate(bundle);
        setActivityOpaque();
        setLockScreenView();
    }

    public void onPause() {
        super.onPause();
        clearBrightnessLimit();
    }

    public void onResume() {
        super.onResume();
        setBrightnessLowerBoundLimit();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mIsPickMode) {
            bundle.putBoolean("last_shape_button_status", isShapeButtonEnabled());
            bundle.putBoolean("shape_button_status_changed", isShapeButtonStatusChanged());
            bundle.putBoolean("is_internal_pick", isInternalPick());
            if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
                Optional.of(Clipboard.getInstance(this.mBlackboard)).ifPresent(new C0367b(20, this));
            }
        }
        if (isQuickViewFromCamera()) {
            bundle.putBoolean("prev_from_camera", true);
        }
        if (Features.isEnabled(Features.SUPPORT_FLIP_COVER_GALLERY)) {
            onSaveLastDataFromFlipCover(bundle);
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void resetAutoRotation(boolean z) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            int requestedOrientation = getRequestedOrientation();
            if (z && requestedOrientation != 4 && requestedOrientation != -1) {
                Log.d(this.TAG, "resetAutoRotation true");
                setRequestedOrientation(4);
            } else if (!z && requestedOrientation != 3 && requestedOrientation != -1) {
                Log.d(this.TAG, "resetAutoRotation false");
                setRequestedOrientation(3);
            }
        } else {
            super.resetAutoRotation(z);
        }
    }

    public void restoreContentLayoutBgForExternal() {
        if (isNeedToUpdateBgColor() && this.mIsNeedToRecoverBg) {
            setBackgroundColor(getColor(R.color.default_background));
            this.mIsNeedToRecoverBg = false;
        }
    }

    public void setActivityOpaque() {
        if (SdkConfig.lessThan(SdkConfig.SEM.B)) {
            SeApiCompat.convertActivityFromTranslucent(this);
        }
    }

    public void setContentLayoutBgForExternal() {
        if (isNeedToUpdateBgColor()) {
            if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                ViewUtils.requestCancelDraw(getWindow().getDecorView(), 1);
            }
            setBackgroundColor(getContext().getColor(R.color.black_color));
            this.mIsNeedToRecoverBg = true;
        }
    }

    public void setDisplayCutOutStateFlag() {
        if (!FoldUtils.SUPPORT_FLIP_COVER_GALLERY || !FoldUtils.isFlipCoverScreen(this)) {
            super.setDisplayCutOutStateFlag();
            return;
        }
        SystemUi.setLayoutInDisPlayCutoutMode(getWindow(), 3);
        SystemUi.setDecorFitsSystemWindows(getWindow(), false);
    }

    public void initLayoutCache() {
    }
}
