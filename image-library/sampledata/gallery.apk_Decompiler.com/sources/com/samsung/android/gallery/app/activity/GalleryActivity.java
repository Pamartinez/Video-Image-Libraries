package com.samsung.android.gallery.app.activity;

import A.a;
import A4.C0368c;
import A4.I;
import C3.C0393c;
import C3.d;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.widget.FrameLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.insets.ProtectionLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.app.ui.map.MapSnapshotPublisher;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.SamsungAccountInfo;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk;
import com.samsung.android.gallery.module.creature.FacePosRatioHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.people.PersonNameDataLoader;
import com.samsung.android.gallery.module.data.CursorHolder;
import com.samsung.android.gallery.module.debugger.HistoryLogger;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.graphics.BitmapPool;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.lottie.service.RecapWorker;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.module.publisher.PublisherFactory;
import com.samsung.android.gallery.module.thumbnail.IconResources;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberManager;
import com.samsung.android.gallery.support.blackboard.key.EventKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.cache.ViewPoolCache;
import com.samsung.android.gallery.widget.recyclerview.FilmStripViewPool;
import com.samsung.android.gallery.widget.utils.PinnedEdgeProperty;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.window.WindowInsetManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.Def;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryActivity extends AppCompatActivity implements IGalleryActivityView, EventContext {
    private final boolean ENABLE_ON_BACK_INVOKED_CALLBACK = SdkConfig.atLeast(SdkConfig.SEM.V);
    protected final String HASH_TAG;
    protected final String TAG;
    protected ViewGroup mActivityLayout;
    protected final Blackboard mBlackboard;
    final OnBackPressedCallback mCallback = new OnBackPressedCallback(false) {
        public void handleOnBackPressed() {
            Log.majorEvent(GalleryActivity.this.TAG, "handleOnBackPressed");
            if (!GalleryActivity.this.preOnBackPressed("handleOnBackPressed")) {
                try {
                    GalleryActivity.this.setBackPressedCallbackEnabled(false);
                    GalleryActivity.this.getOnBackPressedDispatcher().onBackPressed();
                    GalleryActivity.this.postOnBackPressed("handleOnBackPressed");
                    if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
                        GalleryActivity galleryActivity = GalleryActivity.this;
                        if (!galleryActivity.mFragmentDelegate.isBackPressCallbackAvailable(galleryActivity.mCallback.isEnabled())) {
                            return;
                        }
                    } else {
                        GalleryActivity galleryActivity2 = GalleryActivity.this;
                        if (!galleryActivity2.mFragmentDelegate.isBackPressCallbackAvailable(galleryActivity2.getTopFragmentTag())) {
                            return;
                        }
                    }
                    GalleryActivity.this.setBackPressedCallbackEnabled(true);
                } catch (IllegalStateException e) {
                    GalleryActivity.this.errorOnBackPressed("handleOnBackPressed", e);
                }
            }
        }
    };
    protected FrameLayout mContent;
    protected final FragmentDelegate mFragmentDelegate;
    private final GalleryCompat mGalleryCompat;
    private boolean mIsActivityEnterPostponed;
    private boolean mIsActivityReentered;
    private volatile Boolean mIsInMultiWindowMode;
    private boolean mIsMenuOpened;
    private boolean mIsShapeButtonEnabled;
    private boolean mIsShapeButtonStatusChanged;
    private volatile LaunchIntent mLaunchIntent;
    private final ActivityPermissionDelegate mPermissionDelegate;
    private final AtomicBoolean mReservedConfigurationDirty = new AtomicBoolean(false);
    private final Runnable mStartEnterTransition = new C0393c(this, 1);
    protected final SubscriberManager mSubscriberManager;

    public GalleryActivity() {
        String makeTag = makeTag(this);
        this.TAG = makeTag;
        StringBuilder t = C0212a.t(makeTag, com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
        t.append(Integer.toHexString(hashCode()));
        String sb2 = t.toString();
        this.HASH_TAG = sb2;
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("init Activity");
        Log.initPLog(currentTimeMillis);
        String str = "construct {" + sb2 + '}';
        Log.majorEvent(str);
        SeApiCompat.setLegacyFragmentStateManager();
        Blackboard createBlackboard = createBlackboard();
        this.mBlackboard = createBlackboard;
        this.mGalleryCompat = new GalleryCompat(createBlackboard, currentTimeMillis);
        this.mSubscriberManager = new SubscriberManager(createSubscribers());
        this.mFragmentDelegate = new FragmentDelegate(this);
        this.mPermissionDelegate = new ActivityPermissionDelegate(this);
        Log.p(makeTag, str);
        Trace.endSection();
        Trace.endSection();
    }

    private void checkNightModeChange(Bundle bundle) {
        if (bundle != null && bundle.getBoolean("is_night_mode") != isNightMode()) {
            handleNightModeChange(bundle);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|(2:9|10)|11|(1:14)|15|16) */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|(1:14)|15|16) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0028 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0052 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dumpActivity(java.io.PrintWriter r6) {
        /*
            r5 = this;
            java.lang.String r0 = "last removedViews : "
            java.lang.String r1 = "calling activity : "
            java.lang.String r2 = "calling package : "
            java.lang.String r3 = "Resource Config : "
            com.samsung.android.gallery.support.blackboard.SubscriberManager r4 = r5.mSubscriberManager     // Catch:{ Exception -> 0x000d }
            r4.dump(r6)     // Catch:{ Exception -> 0x000d }
        L_0x000d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0028 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0028 }
            android.content.res.Resources r3 = r5.getResources()     // Catch:{ Exception -> 0x0028 }
            android.content.res.Configuration r3 = r3.getConfiguration()     // Catch:{ Exception -> 0x0028 }
            java.lang.String r3 = com.samsung.android.gallery.support.utils.Logger.toString((android.content.res.Configuration) r3)     // Catch:{ Exception -> 0x0028 }
            r4.append(r3)     // Catch:{ Exception -> 0x0028 }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x0028 }
            com.samsung.android.gallery.support.utils.Logger.dumpLog(r6, r3)     // Catch:{ Exception -> 0x0028 }
        L_0x0028:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r3.<init>(r2)     // Catch:{ Exception -> 0x003b }
            java.lang.String r2 = r5.getCallingPackage()     // Catch:{ Exception -> 0x003b }
            r3.append(r2)     // Catch:{ Exception -> 0x003b }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x003b }
            com.samsung.android.gallery.support.utils.Logger.dumpLog(r6, r2)     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0052 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0052 }
            android.content.ComponentName r1 = r5.getCallingActivity()     // Catch:{ Exception -> 0x0052 }
            java.lang.String r1 = r1.getClassName()     // Catch:{ Exception -> 0x0052 }
            r2.append(r1)     // Catch:{ Exception -> 0x0052 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0052 }
            com.samsung.android.gallery.support.utils.Logger.dumpLog(r6, r1)     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0065 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r0 = com.samsung.android.gallery.widget.utils.ViewUtils.dump()     // Catch:{ Exception -> 0x0065 }
            r1.append(r0)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0065 }
            com.samsung.android.gallery.support.utils.Logger.dumpLog(r6, r0)     // Catch:{ Exception -> 0x0065 }
        L_0x0065:
            android.view.ViewGroup r0 = r5.mActivityLayout
            if (r0 == 0) goto L_0x0088
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "============== dump of views ("
            r0.<init>(r1)
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r5)
            r0.append(r1)
            java.lang.String r1 = ") ==========="
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Logger.dumpLog(r6, r0)
            android.view.ViewGroup r0 = r5.mActivityLayout
            com.samsung.android.gallery.module.utils.ViewDebug.printViewHierarchy(r6, r0)
        L_0x0088:
            com.samsung.android.gallery.app.activity.FragmentDelegate r5 = r5.mFragmentDelegate
            r5.dump(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.GalleryActivity.dumpActivity(java.io.PrintWriter):void");
    }

    /* access modifiers changed from: private */
    public void errorOnBackPressed(String str, IllegalStateException illegalStateException) {
        String str2 = this.TAG;
        Log.e((CharSequence) str2, str + " failed", (Throwable) illegalStateException);
        this.mFragmentDelegate.consumePendingTransactions();
        finish();
    }

    private int getActivityEnterAnim() {
        int i2 = getLaunchIntent().getExtra().getInt("animationDuration", StatusCodes.INPUT_MISSING);
        if (i2 == 100) {
            return R.anim.fade_in_100;
        }
        if (i2 != 200) {
            return R.anim.fade_in_300;
        }
        return R.anim.fade_in_200;
    }

    private String getBlackboardTag() {
        return toString();
    }

    private boolean isNeedToFinishActivityOnRecreating(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        Set<String> keySet = bundle.keySet();
        if (!keySet.contains("shape_button_status_changed") || !keySet.contains("last_shape_button_status")) {
            return false;
        }
        if (bundle.getBoolean("shape_button_status_changed") || bundle.getBoolean("last_shape_button_status") != Features.isEnabled(Features.IS_ENABLED_SHOW_BUTTON_SHAPES)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6() {
        Log.ate(this.TAG, "startPostponedEnterTransition delay timer expired. force start enter transition.");
        startPostponedEnterTransition();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0() {
        TimeUtil.initDateFormat(Locale.getDefault(), getString(R.string.today), getString(R.string.yesterday));
        SystemEnvironment.updateIfChanged(getApplicationContext());
        this.mGalleryCompat.createJobService(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateInternal$1(Object obj, Bundle bundle) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateInternal$2(Object obj, Bundle bundle) {
        simulateUserKeyEvent(((Integer) obj).intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateInternal$3(Object obj, Bundle bundle) {
        simulateUserKeyEventWithDelay(((Integer) obj).intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateInternal$4() {
        new VersionUpdateHandler(this.mBlackboard).updateBuildVersion();
    }

    private static String makeTag(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        Trace.mark(simpleName);
        Trace.beginSection("APP_GalleryActivity newInstance");
        return simpleName;
    }

    /* access modifiers changed from: private */
    public void postOnBackPressed(String str) {
        if (this.mFragmentDelegate.getStackSize() != 1 || getTopFragment() == null) {
            popFragmentTag();
            String str2 = this.TAG;
            StringBuilder t = C0212a.t(str, " {");
            t.append(this.mFragmentDelegate.getStackSize());
            t.append(',');
            t.append(getTopFragmentTag());
            t.append('}');
            Log.d(str2, t.toString());
            this.mBlackboard.post("command://MoveCMD", "command://MoveCMD/SyncBackKey");
            return;
        }
        String str3 = this.TAG;
        Log.i(str3, str + " {1} destroy");
    }

    /* access modifiers changed from: private */
    public void setBackPressedCallbackEnabled(boolean z) {
        this.mCallback.setEnabled(z);
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            this.mBlackboard.publish("function://activity_back_pressed_callback_enabled", Boolean.valueOf(z));
        }
    }

    private void setConfigurationDirty() {
        SeApiCompat.setConfigurationDirty();
        IconResources.clear();
        WindowInsetManager.getInstance(this.mBlackboard).clear();
    }

    private void setOnBackPressedDispatcherCallback() {
        if (this.ENABLE_ON_BACK_INVOKED_CALLBACK) {
            getOnBackPressedDispatcher().addCallback(this, this.mCallback);
        }
    }

    private void startTimerIfActivityEnterPostponed() {
        if (this.mIsActivityEnterPostponed) {
            ThreadUtil.postOnUiThreadDelayed(this.mStartEnterTransition, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    public void callSuperOnActivityResult() {
        super.onActivityResult(Integer.MIN_VALUE, 0, (Intent) null);
    }

    public boolean clearBackStackImmediate() {
        return this.mFragmentDelegate.clearBackStackImmediate();
    }

    public boolean commitFragment(Fragment fragment, String str) {
        return this.mFragmentDelegate.commitFragment(fragment, str);
    }

    public Subscriber createActivityHandler(Blackboard blackboard) {
        return new GalleryActivityHandler(blackboard, this);
    }

    public Blackboard createBlackboard() {
        return Blackboard.getInstance(getBlackboardTag());
    }

    public ArrayList<Subscriber> createSubscribers() {
        Blackboard blackboard = getBlackboard();
        ArrayList<Subscriber> arrayList = new ArrayList<>();
        PublisherFactory.subscribeGeneric(arrayList, blackboard);
        arrayList.add(createActivityHandler(blackboard));
        arrayList.add(createViewNavigator(blackboard));
        arrayList.add(new MapSnapshotPublisher(blackboard));
        return arrayList;
    }

    public Subscriber createViewNavigator(Blackboard blackboard) {
        return new ViewNavigator(blackboard, this);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mFragmentDelegate.dispatchGenericMotionEvent(motionEvent) || super.dispatchGenericMotionEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0061 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0065 A[SYNTHETIC, Splitter:B:16:0x0065] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            java.lang.String r0 = ","
            java.lang.String r1 = "dispatchTouchEvent MotionEvent["
            int r2 = r6.getAction()     // Catch:{ all -> 0x007a }
            java.lang.String r3 = "userInput"
            if (r2 == 0) goto L_0x0025
            int r2 = r6.getAction()     // Catch:{ all -> 0x007a }
            r4 = 2
            if (r2 != r4) goto L_0x0014
            goto L_0x0025
        L_0x0014:
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r5.mBlackboard     // Catch:{ all -> 0x007a }
            r2.erase(r3)     // Catch:{ all -> 0x007a }
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r5.mBlackboard     // Catch:{ all -> 0x007a }
            r3 = 4004(0xfa4, float:5.611E-42)
            com.samsung.android.gallery.support.blackboard.key.EventMessage r3 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r3)     // Catch:{ all -> 0x007a }
            r2.postEvent(r3)     // Catch:{ all -> 0x007a }
            goto L_0x002c
        L_0x0025:
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r5.mBlackboard     // Catch:{ all -> 0x007a }
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x007a }
            r2.publish(r3, r4)     // Catch:{ all -> 0x007a }
        L_0x002c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r2.<init>(r1)     // Catch:{ all -> 0x007a }
            int r1 = r6.getAction()     // Catch:{ all -> 0x007a }
            r2.append(r1)     // Catch:{ all -> 0x007a }
            r2.append(r0)     // Catch:{ all -> 0x007a }
            float r1 = r6.getX()     // Catch:{ all -> 0x007a }
            r2.append(r1)     // Catch:{ all -> 0x007a }
            r2.append(r0)     // Catch:{ all -> 0x007a }
            float r0 = r6.getY()     // Catch:{ all -> 0x007a }
            r2.append(r0)     // Catch:{ all -> 0x007a }
            java.lang.String r0 = "]"
            r2.append(r0)     // Catch:{ all -> 0x007a }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x007a }
            com.samsung.android.gallery.support.trace.Trace.beginSection(r0)     // Catch:{ all -> 0x007a }
            com.samsung.android.gallery.app.activity.FragmentDelegate r0 = r5.mFragmentDelegate     // Catch:{ all -> 0x007a }
            boolean r0 = r0.dispatchTouchEvent(r6)     // Catch:{ all -> 0x007a }
            r1 = 1
            if (r0 == 0) goto L_0x0065
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r1
        L_0x0065:
            int r0 = r6.getAction()     // Catch:{ all -> 0x007a }
            if (r0 != r1) goto L_0x0072
            java.lang.String r0 = r5.TAG     // Catch:{ all -> 0x007a }
            java.lang.String r1 = "user touch up"
            com.samsung.android.gallery.support.utils.Log.v(r0, r1)     // Catch:{ all -> 0x007a }
        L_0x0072:
            boolean r5 = super.dispatchTouchEvent(r6)     // Catch:{ all -> 0x007a }
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r5
        L_0x007a:
            r5 = move-exception
            com.samsung.android.gallery.support.trace.Trace.endSection()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.GalleryActivity.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (str == null && fileDescriptor == null) {
            dumpActivity(printWriter);
        } else {
            super.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void finish() {
        super.finish();
        if (isQuickViewFromCamera()) {
            overridePendingTransition(getActivityEnterAnim(), R.anim.fade_out_100);
        }
    }

    public boolean finishFragment() {
        return this.mFragmentDelegate.finishFragment();
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public LaunchIntent getLaunchIntent() {
        if (this.mLaunchIntent == null) {
            this.mLaunchIntent = new LaunchIntent(getIntent());
        }
        return this.mLaunchIntent;
    }

    public int getMainLayout() {
        return R.layout.gallery_activity;
    }

    public final int getNightModeFlags() {
        return getResources().getConfiguration().uiMode & 48;
    }

    public String getRefererPackageName() {
        return AppResources.getCallingPackageCompat(this, (Supplier<Intent>) null);
    }

    public IBaseFragment getTopFragment() {
        return this.mFragmentDelegate.getTopFragment();
    }

    public String getTopFragmentTag() {
        return this.mFragmentDelegate.getTopFragmentTag();
    }

    public void handleBroadcastEvent(EventMessage eventMessage) {
        String str = this.TAG;
        Log.v(str, "handleBroadcastEvent " + EventKey.getName(eventMessage.what));
        this.mFragmentDelegate.handleBroadcastEventOnStack(eventMessage);
    }

    public void handleEvent(EventMessage eventMessage) {
        this.mFragmentDelegate.handleEventOnStack(eventMessage);
    }

    public void handleNightModeChange(Bundle bundle) {
        DeepSkyHelper.closeTextTranslationWindow(this);
    }

    public void initLayoutCache() {
        LayoutCache.getInstance().open(this);
    }

    public boolean isActivityDestroyed() {
        if (isDestroyed() || isFinishing()) {
            return true;
        }
        return false;
    }

    public boolean isActivityResumed() {
        return getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    public boolean isDialogShowing(String str) {
        return this.mFragmentDelegate.isDialogShowing(str);
    }

    public boolean isExistFragment(String str) {
        return this.mFragmentDelegate.isExistFragment(str);
    }

    public boolean isFromShortcut() {
        return "com.android.gallery.action.SHORTCUT_ALBUM_VIEW".equals(getIntent().getAction());
    }

    public boolean isInMultiWindowMode() {
        if (this.mIsInMultiWindowMode == null) {
            this.mIsInMultiWindowMode = Boolean.valueOf(super.isInMultiWindowMode());
        }
        return this.mIsInMultiWindowMode.booleanValue();
    }

    public final boolean isNightMode() {
        if (getNightModeFlags() == 32) {
            return true;
        }
        return false;
    }

    public final boolean isQuickViewFromCamera() {
        return getLaunchIntent().isFromCamera();
    }

    public boolean isShapeButtonEnabled() {
        return this.mIsShapeButtonEnabled;
    }

    public boolean isShapeButtonStatusChanged() {
        return this.mIsShapeButtonStatusChanged;
    }

    public void onActivityReenter(int i2, Intent intent) {
        String str = this.TAG;
        Log.p(str, "onActivityReenter {" + this.HASH_TAG + '}' + Logger.v(Integer.valueOf(i2), intent));
        this.mIsActivityReentered = true;
        this.mBlackboard.post("lifecycle://on_activity_reenter", new Object[]{Integer.valueOf(i2), intent});
        startTimerIfActivityEnterPostponed();
    }

    public void onActivityResult(int i2, int i7, Intent intent) {
        super.onActivityResult(i2, i7, intent);
        this.mBlackboard.post("lifecycle://on_activity_result", new Object[]{Integer.valueOf(i2), Integer.valueOf(i7), intent});
    }

    public void onBackPressed() {
        Log.majorEvent(this.TAG, "onBackPressed");
        if (!preOnBackPressed("onBackPressed")) {
            try {
                super.onBackPressed();
                postOnBackPressed("onBackPressed");
            } catch (IllegalStateException e) {
                errorOnBackPressed("onBackPressed", e);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        DeviceInfo.recycle(this);
        PinnedEdgeProperty.getInstance().recycle();
        setConfigurationDirty();
        Log.majorEvent(this.TAG, "newConfig = " + Logger.toString(configuration) + "-" + DeviceInfo.getDisplaySize(getActivity()));
        super.onConfigurationChanged(configuration);
        setBackPressedCallbackEnabled();
        this.mGalleryCompat.resetColorMode(this, configuration);
        this.mBlackboard.post("lifecycle://on_activity_configuration_changed", configuration);
    }

    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("APP_GalleryActivity onCreate");
        Log.sluggish("OnCreate");
        Log.initPLog(System.currentTimeMillis());
        Configuration configuration = getResources().getConfiguration();
        checkNightModeChange(bundle);
        LaunchIntent launchIntent = getLaunchIntent();
        this.mBlackboard.publish("data://launch_intent", launchIntent);
        String str3 = "onCreate {" + this.HASH_TAG + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getRefererPackageName() + "} " + launchIntent;
        Log.majorEvent(str3);
        Log.p(this.TAG, str3);
        this.mBlackboard.publish("lifecycle://last_activity_lifecycle", "lifecycle://on_activity_create");
        setConfigurationDirty();
        this.mGalleryCompat.setDisplayMode(this, configuration);
        SimpleThreadPool.getInstance().execute(new C0393c(this, 0));
        ThumbnailLoader.getInstance().open();
        AnalyticsLogger.getInstance().open();
        BitmapPool.getInstance().open();
        setOnBackPressedDispatcherCallback();
        Trace.beginSection("APP_super.onCreate");
        super.onCreate(bundle);
        Trace.endSection();
        if (isNeedToFinishActivityOnRecreating(bundle)) {
            Log.w(this.TAG, "GalleryActivity recreated, but need to finish");
            finish();
            Trace.endSection();
            return;
        }
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            setDisplayCutOutStateFlag();
        }
        this.mGalleryCompat.onCreate(this, this.mSubscriberManager, currentTimeMillis);
        this.mPermissionDelegate.inspectMandatoryPermission();
        Trace.beginSection("APP_setContentView");
        setContentView(getMainLayout());
        Trace.endSection();
        this.mActivityLayout = (ViewGroup) findViewById(R.id.content_layout);
        updateGradientProtection(getColor(R.color.gradient_protection_color));
        this.mContent = (FrameLayout) findViewById(R.id.content);
        setContentLayoutBgForExternal();
        initLayoutCache();
        DeviceInfo.recycle(this);
        String str4 = this.TAG;
        StringBuilder sb2 = new StringBuilder("onCreate");
        String str5 = this.HASH_TAG;
        if (this.mGalleryCompat.isWideColorGamut()) {
            str = "WCG";
        } else {
            str = "NORM";
        }
        if (!SuperHdrConfig.SUPPORT || !SuperHdrConfig.isEnabled()) {
            str2 = "SDR";
        } else {
            str2 = "HDR";
        }
        sb2.append(Logger.vt(str5, str, str2, Long.valueOf(currentTimeMillis)));
        Log.p(str4, sb2.toString());
        Trace.endSection();
    }

    public void onCreateInternal() {
        long currentTimeMillis = System.currentTimeMillis();
        this.mSubscriberManager.onCreate();
        this.mBlackboard.publish("function://isDialogShowing", new I(2, this));
        this.mBlackboard.publish("data://app_context", getApplicationContext());
        this.mBlackboard.publish("data://activity", this);
        this.mBlackboard.post("lifecycle://on_activity_create", (Object) null);
        this.mBlackboard.subscribe("command://request_suicide", new d(this, 0));
        this.mBlackboard.subscribeOnUi("command://simulateUserKey", new d(this, 1));
        this.mBlackboard.subscribeOnUi("command://simulateUserKeyWithDelay", new d(this, 2));
        ThreadUtil.postOnBgThread(new C0393c(this, 2));
        a.x(new StringBuilder("onCreateInternal +"), currentTimeMillis, this.TAG);
    }

    public void onDestroy() {
        String p6 = C0212a.p(new StringBuilder("onDestroy {"), this.HASH_TAG, "}");
        Log.p(this.TAG, p6);
        Log.majorEvent(p6);
        this.mBlackboard.publish("lifecycle://last_activity_lifecycle", "lifecycle://on_activity_destroy");
        this.mFragmentDelegate.onDestroy();
        super.onDestroy();
        onDestroyInternal();
        LayoutCache.getInstance().close();
        ThumbnailLoader.getInstance().close();
        AnalyticsLogger.getInstance().close();
        BitmapPool.getInstance().close();
        SeApiCompat.getBoosterCompat(getApplicationContext()).destroy();
        PersonNameDataLoader.clear();
        SamsungCloudCompat.releaseSyncState();
        ViewPoolCache.releaseInstance();
        ImageDecoder.clear();
        Features.recycle();
        DeviceInfo.recycle((Context) null);
        DeviceInfo.clearDexMode(getActivity());
        YourPhone.clearConnection(getActivity());
        FilmStripViewPool.clear();
        GridMarginHelper.clear();
        this.mGalleryCompat.onDestroy(this);
        Trace.recycleLog();
    }

    public void onDestroyInternal() {
        this.mBlackboard.post("lifecycle://on_activity_destroy", (Object) null);
        this.mSubscriberManager.onDestroy();
        this.mBlackboard.reset(getBlackboardTag());
        Clipboard.getInstance(this.mBlackboard).release();
        SamsungCloudSdk.initAccountInfo((SamsungAccountInfo) null);
        MdeSharingService.getInstance().clearServiceState();
        CursorHolder.clear();
        PeopleDataManager.clear();
        FacePosRatioHelper.clear();
        SharedTransition.onRelease(this.mBlackboard);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() > 0) {
            if (SystemClock.uptimeMillis() - keyEvent.getDownTime() < ((long) ViewConfiguration.getLongPressTimeout()) || !this.mFragmentDelegate.onKeyOnFragment(i2, keyEvent)) {
                return false;
            }
            return true;
        } else if (this.mFragmentDelegate.onKeyOnFragment(i2, keyEvent) || super.onKeyDown(i2, keyEvent)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.mFragmentDelegate.onKeyOnFragment(i2, keyEvent) || super.onKeyUp(i2, keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean onMenuOpened(int i2, Menu menu) {
        if (!this.mIsMenuOpened) {
            this.mBlackboard.postEvent(EventMessage.obtain(1100));
            a.k(i2, "onMenuOpened ", this.TAG);
        }
        this.mIsMenuOpened = true;
        return super.onMenuOpened(i2, menu);
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.mIsInMultiWindowMode = Boolean.valueOf(z);
        String str = this.TAG;
        Log.majorEvent(str, "onMultiWindowModeChanged " + z);
        super.onMultiWindowModeChanged(z);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mLaunchIntent = new LaunchIntent(intent);
        this.mBlackboard.publish("data://launch_intent", this.mLaunchIntent);
        String str = this.TAG;
        Log.majorEvent(str, "onNewIntent : " + this.HASH_TAG + "/" + this.mLaunchIntent);
    }

    public void onPanelClosed(int i2, Menu menu) {
        super.onPanelClosed(i2, menu);
        if (this.mIsMenuOpened) {
            this.mBlackboard.postEvent(EventMessage.obtain(GroupAuthority.REASON_NOT_A_MEMBER));
            a.k(i2, "onPanelClosed ", this.TAG);
        }
        this.mIsMenuOpened = false;
    }

    public void onPause() {
        String p6 = C0212a.p(new StringBuilder("onPause {"), this.HASH_TAG, ",c963dd8}");
        Log.p(this.TAG, p6);
        Log.majorEvent(p6);
        if (PocFeatures.isEnabled(PocFeatures.GalleryLabs)) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("GalleryLabs Enabled : ");
            sb2.append(Logger.getEncodedString(PreferenceFeatures.toDebugString() + "\n" + PocFeatures.toDebugString()));
            Log.w(str, sb2.toString());
        }
        try {
            super.onPause();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "onPause failed", (Throwable) e);
        }
        this.mBlackboard.publish("lifecycle://last_activity_lifecycle", "lifecycle://on_activity_pause");
        this.mBlackboard.post("lifecycle://on_activity_pause", (Object) null);
        setConfigurationDirty();
        this.mReservedConfigurationDirty.set(true);
        this.mGalleryCompat.onPause(this);
        Trace.clearLog();
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        this.mPermissionDelegate.onRequestPermissionsResult(i2, strArr, iArr);
    }

    public void onResume() {
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("APP_GalleryActivity onResume");
        String str = "onResume {" + this.HASH_TAG + "}";
        Log.sluggish("OnResume");
        Log.majorEvent(str);
        Trace.beginSection("APP_super.onResume");
        super.onResume();
        Trace.endSection();
        if (this.mReservedConfigurationDirty.compareAndSet(true, false)) {
            setConfigurationDirty();
        }
        this.mGalleryCompat.onResume(this);
        this.mPermissionDelegate.acquireMandatoryPermission();
        this.mFragmentDelegate.restoreLast();
        this.mBlackboard.publish("lifecycle://last_activity_lifecycle", "lifecycle://on_activity_resume");
        this.mBlackboard.post("lifecycle://on_activity_resume", (Object) null);
        SeApiCompat.disableViewRoundedCorner(getWindow().getDecorView());
        this.mFragmentDelegate.onResume();
        RecapWorker.clearOnWorksOrPending();
        this.mBlackboard.publishIfEmpty("debug://TimeDoneOnResume", Long.valueOf(currentTimeMillis));
        Log.sluggish("Executed");
        String str2 = this.TAG;
        StringBuilder t = C0212a.t(str, " +");
        t.append(System.currentTimeMillis() - currentTimeMillis);
        Log.p(str2, t.toString());
        Trace.endSection();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.clear();
        bundle.putBoolean("is_night_mode", isNightMode());
    }

    public void onStart() {
        String str = this.TAG;
        Log.p(str, "onStart {" + this.HASH_TAG + '}');
        super.onStart();
        startTimerIfActivityEnterPostponed();
    }

    public void onStop() {
        if (!this.mIsActivityEnterPostponed || (!this.mIsActivityReentered && !isActivityTransitionRunning())) {
            String str = this.TAG;
            Log.p(str, "onStop {" + this.HASH_TAG + '}');
        } else {
            String str2 = this.TAG;
            Log.at(str2, "onStop {" + this.HASH_TAG + "} transition case" + Logger.v(Boolean.valueOf(this.mIsActivityReentered), Boolean.valueOf(isActivityTransitionRunning())));
            startPostponedEnterTransition();
            this.mBlackboard.postEvent(EventMessage.obtain(GroupAuthority.REASON_NOT_ACCEPTED_THE_INVITATION_YET));
        }
        if (PreferenceFeatures.OneUi41.LOG_ACTION_VIEW) {
            HistoryLogger.getInstance().keepViewHistory();
        }
        this.mBlackboard.publish("lifecycle://last_activity_lifecycle", "lifecycle://on_activity_stop");
        this.mBlackboard.post("lifecycle://on_activity_stop", (Object) null);
        super.onStop();
    }

    public void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        this.mFragmentDelegate.onTrimMemory(i2);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.d(this.TAG, "onWindowFocusChanged", Boolean.valueOf(z));
        this.mBlackboard.postEvent(EventMessage.obtain(1110, Boolean.valueOf(z)));
        this.mFragmentDelegate.onWindowFocusChanged(z);
    }

    public void popFragmentTag() {
        this.mFragmentDelegate.popFragmentTag();
    }

    public void postponeEnterTransition() {
        this.mIsActivityEnterPostponed = true;
        if (this.mIsActivityReentered) {
            Log.at(this.TAG, "clear mIsActivityReentered by postponeEnterTransition");
            this.mIsActivityReentered = false;
        }
        super.postponeEnterTransition();
    }

    public boolean preOnBackPressed(String str) {
        SharedTransition.dumpTransitionView(this.mBlackboard);
        if (this.mFragmentDelegate.onBackPressedOnFragment()) {
            String str2 = this.TAG;
            Log.d(str2, str + " consumed by fragment");
            return true;
        } else if (this.mFragmentDelegate.getStackSize() != 1) {
            return false;
        } else {
            if (!isQuickViewFromCamera() && !isFromShortcut()) {
                return false;
            }
            Log.w((CharSequence) this.TAG, C0212a.A(str, " finish"), Boolean.valueOf(isQuickViewFromCamera()), Boolean.valueOf(isFromShortcut()));
            finish();
            return true;
        }
    }

    public void resetAutoRotation(boolean z) {
        int requestedOrientation;
        if (z && (requestedOrientation = getRequestedOrientation()) != 4 && requestedOrientation != -1) {
            Log.d(this.TAG, "resetAutoRotation");
            setRequestedOrientation(-1);
        }
    }

    public void setBackgroundColor(int i2) {
        ViewGroup viewGroup = this.mActivityLayout;
        if (viewGroup != null) {
            viewGroup.setBackgroundColor(i2);
        }
    }

    public void setDisplayCutOutStateFlag() {
        SystemUi.setLayoutInDisPlayCutoutMode(getWindow(), 1);
    }

    public void setShapeButtonStatusChanged() {
        this.mIsShapeButtonEnabled = Features.isEnabled(Features.IS_ENABLED_SHOW_BUTTON_SHAPES);
        this.mIsShapeButtonStatusChanged = true;
    }

    public void showDialog(DialogFragment dialogFragment, String str) {
        this.mFragmentDelegate.showDialog(dialogFragment, str);
    }

    public void simulateUserKeyEvent(int i2) {
        if (this.mContent == null) {
            Log.e(this.TAG, "fail simulateUserKeyEvent");
            return;
        }
        BaseInputConnection baseInputConnection = new BaseInputConnection(this.mContent, true);
        KeyEvent keyEvent = new KeyEvent(0, i2);
        KeyEvent keyEvent2 = new KeyEvent(1, i2);
        baseInputConnection.sendKeyEvent(keyEvent);
        baseInputConnection.sendKeyEvent(keyEvent2);
    }

    public void simulateUserKeyEventWithDelay(int i2) {
        if (this.mContent == null) {
            Log.e(this.TAG, "fail simulateUserKeyEvent");
            return;
        }
        BaseInputConnection baseInputConnection = new BaseInputConnection(this.mContent, true);
        baseInputConnection.sendKeyEvent(new KeyEvent(0, i2));
        ThreadUtil.postOnUiThreadDelayed(new C0368c(i2, baseInputConnection), 350);
    }

    public void startPostponedEnterTransition() {
        this.mIsActivityEnterPostponed = false;
        if (this.mIsActivityReentered) {
            Log.at(this.TAG, "clear mIsActivityReentered by startPostponedEnterTransition");
            this.mIsActivityReentered = false;
        }
        ThreadUtil.removeCallbackOnUiThread(this.mStartEnterTransition);
        try {
            super.startPostponedEnterTransition();
        } catch (NullPointerException e) {
            Log.e(this.TAG, "NPE at startPostponedEnterTransition");
            e.printStackTrace();
        }
    }

    public void syncFragmentStack() {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) && !this.mFragmentDelegate.onPreSyncFragmentStack()) {
            postOnBackPressed("syncFragmentStack");
            setBackPressedCallbackEnabled();
        }
    }

    public void updateGradientProtection(int i2) {
        if (PocFeatures.isEnabled(PocFeatures.GradientProtection)) {
            ViewGroup viewGroup = this.mActivityLayout;
            if (viewGroup instanceof ProtectionLayout) {
                ViewUtils.updateGradientProtection((ProtectionLayout) viewGroup, ViewUtils.ProtectionGradientType.ALL, i2);
            }
        }
    }

    public void setBackPressedCallbackEnabled(String str) {
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) && this.ENABLE_ON_BACK_INVOKED_CALLBACK) {
            this.mCallback.setEnabled(this.mFragmentDelegate.isBackPressCallbackAvailable(str));
        }
    }

    public void setBackPressedCallbackEnabled() {
        if (!isActivityDestroyed()) {
            setBackPressedCallbackEnabled(this.mFragmentDelegate.isBackPressCallbackAvailable(this.mCallback.isEnabled()));
        }
    }

    public Activity getActivity() {
        return this;
    }

    public Context getContext() {
        return this;
    }
}
