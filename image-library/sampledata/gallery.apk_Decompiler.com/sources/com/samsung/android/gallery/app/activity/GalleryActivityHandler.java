package com.samsung.android.gallery.app.activity;

import A.a;
import A2.d;
import B8.e;
import Bb.l;
import C3.f;
import C3.i;
import N2.j;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.autofill.AutofillManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.GotoMyFilesCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.ShowLocationGdprDialogCmd;
import com.samsung.android.gallery.app.controller.internals.ShowLowStorageCmd;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.commandline.CommandLine;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.display.DesktopWindowingManager;
import com.samsung.android.gallery.module.idleworker.IdleWorker;
import com.samsung.android.gallery.module.receiver.GlobalActionReceiver;
import com.samsung.android.gallery.module.remote.SlideshowServiceHelper;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.module.store.AppRatingHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.module.utils.ProgressServiceUtil;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.LowPerformanceLogger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.bottom.BottomBarData;
import com.samsung.android.gallery.widget.bottom.BottomMoveBar;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryActivityHandler extends Subscriber implements EventContext {
    protected IGalleryActivityView mActivityView;
    private Context mAppContext;
    private BoosterCompat mBoosterCompat;
    private final BottomBarHandler<?> mBottomBarHandler;
    private final BottomBarHandler<?> mBottomMoveBarHandler;
    private final DesktopWindowingManager mDesktopWindowingManager;
    private boolean mIsFirstThumbLoad;
    private Runnable mPendedBixbyAction = null;
    private final Runnable mReleaseCpuRunnable = new f(this, 0);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomBarHandlerImpl extends BottomBarHandler<BottomBar> {
        public BottomBarHandlerImpl(Blackboard blackboard) {
            super(blackboard);
        }

        private void hideBottomTabIfPossible() {
            Runnable runnable = (Runnable) this.mBlackboard.pop("command:///HideBottomTabCallback");
            if (runnable != null) {
                runnable.run();
            }
        }

        private void initView(BottomBar bottomBar, BottomBarData bottomBarData) {
            ArrayList<MenuItem> arrayList;
            ArrayList<MenuItem> arrayList2 = bottomBarData.items;
            if ((arrayList2 != null && !arrayList2.isEmpty()) || ((arrayList = bottomBarData.itemsMore) != null && !arrayList.isEmpty())) {
                if (bottomBarData.items != null && !Features.isEnabled(Features.SUPPORT_SHARE_VIA)) {
                    bottomBarData.items.removeIf(new b(Arrays.asList(new Integer[]{Integer.valueOf(R.id.action_share), Integer.valueOf(R.id.action_share_album)})));
                }
                bottomBar.setMenuItems(bottomBarData);
                if (!bottomBar.isBarVisible()) {
                    this.mBlackboard.publish("data://bottom_bar_visible_changed", Boolean.TRUE);
                }
                BottomBarData.InitListener initListener = bottomBarData.initListener;
                if (initListener != null) {
                    initListener.onInit(bottomBarData.locationKey, bottomBar);
                }
                showWhenAdd(bottomBar);
                PopoverHelper.notifyAnchorChanged(this.mBlackboard, bottomBar, 2, true);
            }
            initFloatingLayout(bottomBar, bottomBarData.listView);
        }

        public void add(Object obj, Bundle bundle) {
            BottomBar bottomBar = (BottomBar) getBar();
            if (bottomBar != null && obj != null) {
                initView(bottomBar, (BottomBarData) obj);
            }
        }

        public void close(Object obj, Bundle bundle) {
            boolean z;
            V v = this.mCustomView;
            if (v != null) {
                BottomBar bottomBar = (BottomBar) v;
                if (obj == null || !((Boolean) obj).booleanValue()) {
                    z = false;
                } else {
                    z = true;
                }
                bottomBar.close(z);
                this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{0, Boolean.FALSE});
            }
        }

        public int getHeightRes() {
            return R.dimen.bottom_bar_floating_height;
        }

        public void hide(Object obj, Bundle bundle) {
            boolean z;
            V v = this.mCustomView;
            if (v != null) {
                BottomBar bottomBar = (BottomBar) v;
                if (obj == null || !((Boolean) obj).booleanValue()) {
                    z = false;
                } else {
                    z = true;
                }
                bottomBar.hide(z);
                this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{0, Boolean.FALSE});
            }
        }

        public void restore(Object obj, Bundle bundle) {
            super.restore(obj, bundle);
            V v = this.mCustomView;
            if (v != null) {
                this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{Integer.valueOf(getBottomBarHeight(v)), Boolean.FALSE});
            }
        }

        public void show(Object obj, Bundle bundle) {
            V v = this.mCustomView;
            if (v != null) {
                ((BottomBar) v).show();
            }
        }

        public void showWhenAdd(BottomBar bottomBar) {
            hideBottomTabIfPossible();
            bottomBar.show();
            this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{Integer.valueOf(getBottomBarHeight(bottomBar)), Boolean.FALSE});
        }

        public BottomBar getView(Activity activity) {
            View findViewById = activity.getWindow().getDecorView().findViewById(R.id.bottom_bar_view_stub);
            if (findViewById instanceof ViewStub) {
                findViewById = ((ViewStub) findViewById).inflate();
            }
            return (BottomBar) findViewById.findViewById(R.id.bottom_bar);
        }

        public void setViewListener(BottomBar bottomBar) {
            bottomBar.setStateChangedListener(new c(this));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMoveBarHandlerImpl extends BottomBarHandler<BottomMoveBar> {
        public BottomMoveBarHandlerImpl(Blackboard blackboard) {
            super(blackboard);
        }

        private void initView(BottomMoveBar bottomMoveBar, int i2, MediaItem mediaItem, RecyclerView recyclerView) {
            if (mediaItem == null) {
                a.B(i2, "initView failed null item ", "BottomMoveBarHandlerImpl");
                return;
            }
            Log.d("BottomMoveBarHandlerImpl", "initView " + MediaItemUtil.getSimpleLog(mediaItem) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2);
            ((BottomMoveBar) this.mCustomView).initView(mediaItem, i2);
            ((BottomMoveBar) this.mCustomView).setOnClickListener(new d(this));
            if (mediaItem.isFolder() || mediaItem.isEmptyAlbum()) {
                Bitmap defaultAlbumImage = ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
                if (defaultAlbumImage != null) {
                    ((BottomMoveBar) this.mCustomView).bindImage(defaultAlbumImage);
                }
            } else {
                ThumbnailLoader.getInstance().getOrLoad(mediaItem, ThumbKind.SMALL_CROP_KIND, new e(mediaItem, 1), new e(this, mediaItem));
            }
            initFloatingLayout(bottomMoveBar, recyclerView);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$initView$0(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
            if (this.mCustomView != null) {
                if (bitmap == null) {
                    bitmap = ResourceUtil.getRemoteBrokenBitmap(mediaItem);
                    mediaItem.setBroken(true);
                }
                ((BottomMoveBar) this.mCustomView).bindImage(bitmap);
            }
        }

        /* access modifiers changed from: private */
        public void onButtonClick(View view) {
            boolean z;
            String str;
            if (view.getId() == R.id.moveButton) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                str = "move";
            } else {
                str = Contract.COMMAND_ID_CANCEL;
            }
            Log.d("BottomMoveBarHandlerImpl", "onClick ".concat(str));
            this.mBlackboard.post("command://ExitMoveMode", Boolean.valueOf(z));
        }

        public void add(Object obj, Bundle bundle) {
            BottomMoveBar bottomMoveBar = (BottomMoveBar) getBar();
            if (bottomMoveBar != null && obj != null) {
                Object[] objArr = (Object[]) obj;
                initView(bottomMoveBar, ((Integer) objArr[0]).intValue(), (MediaItem) objArr[1], (RecyclerView) objArr[2]);
            }
        }

        public int getBottomMoveBarHeight(View view) {
            return view.getResources().getDimensionPixelSize(R.dimen.move_bar_floating_height);
        }

        public int getHeightRes() {
            return R.dimen.move_bar_floating_height;
        }

        public void hide(Object obj, Bundle bundle) {
            boolean z;
            V v = this.mCustomView;
            if (v != null) {
                BottomMoveBar bottomMoveBar = (BottomMoveBar) v;
                if (obj == null || !((Boolean) obj).booleanValue()) {
                    z = false;
                } else {
                    z = true;
                }
                bottomMoveBar.hideBottomBar(z);
                this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{0, Boolean.TRUE});
            }
        }

        public void release() {
            V v = this.mCustomView;
            if (v != null) {
                ((BottomMoveBar) v).destroy();
                this.mBlackboard.erase("data://album_move");
                this.mBlackboard.erase("album_move_src_location");
                this.mBlackboard.erase("album_move_tgt_location");
            }
            super.release();
        }

        public void restore(Object obj, Bundle bundle) {
            super.restore(obj, bundle);
            V v = this.mCustomView;
            if (v != null) {
                this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{Integer.valueOf(getBottomBarHeight(v)), Boolean.TRUE});
            }
        }

        public void show(Object obj, Bundle bundle) {
            if (this.mCustomView != null && obj != null) {
                Object[] objArr = (Object[]) obj;
                boolean z = false;
                boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
                boolean booleanValue2 = ((Boolean) objArr[1]).booleanValue();
                if (objArr.length > 2 && ((Boolean) objArr[2]).booleanValue()) {
                    z = true;
                }
                ((BottomMoveBar) this.mCustomView).showBottomBar(booleanValue, booleanValue2, z);
                this.mBlackboard.post("command://UpdateListViewBottomPadding", new Object[]{Integer.valueOf(getBottomMoveBarHeight(this.mCustomView)), Boolean.TRUE});
            }
        }

        public BottomMoveBar getView(Activity activity) {
            View findViewById = activity.getWindow().getDecorView().findViewById(R.id.bottom_move_bar_view_stub);
            if (findViewById instanceof ViewStub) {
                findViewById = ((ViewStub) findViewById).inflate();
            }
            return (BottomMoveBar) findViewById.findViewById(R.id.bottom_move_bar);
        }
    }

    public GalleryActivityHandler(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard);
        this.mActivityView = iGalleryActivityView;
        this.mBottomBarHandler = new BottomBarHandlerImpl(blackboard);
        this.mBottomMoveBarHandler = new BottomMoveBarHandlerImpl(blackboard);
        this.mDesktopWindowingManager = new DesktopWindowingManager();
    }

    /* access modifiers changed from: private */
    public void adjustPopOverOptions(Object obj, Bundle bundle) {
        Object[] objArr = (Object[]) obj;
        PopoverHelper.adjustPopOverOptions(getActivity(), (int[]) objArr[0], (int[]) objArr[1], (Point[]) objArr[2], (int[]) objArr[3]);
    }

    private void boostCpu() {
        getBoosterCompat().acquireScroll();
        ThreadUtil.postOnBgThreadDelayed(this.mReleaseCpuRunnable, 2000);
    }

    private void disableAutoFillService(Context context) {
        AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            try {
                autofillManager.disableAutofillServices();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private BoosterCompat getBoosterCompat() {
        if (this.mBoosterCompat == null) {
            this.mBoosterCompat = SeApiCompat.getBoosterCompat(getApplicationContext());
        }
        return this.mBoosterCompat;
    }

    private void handleBixbyAction(Object obj) {
        Blackboard.postGlobal("command://remove_bixby_action", (Object) null);
        String str = (String) this.mBlackboard.read("location://variable/currentv1");
        StringCompat stringCompat = this.TAG;
        StringBuilder k = j.k("handleBixbyAction {", str, "} ");
        k.append(Logger.getEncodedString(obj));
        Log.bx(stringCompat, k.toString());
        if (str != null && obj != null) {
            this.mBlackboard.postEvent(EventMessage.obtain(3008, obj));
        }
    }

    /* access modifiers changed from: private */
    public void handleMainScreenChanged() {
        Log.d(this.TAG, "recreateActivity");
        Utils.recreateActivity(getActivity());
    }

    private void handlePendedBixbyAction() {
        StringCompat stringCompat = this.TAG;
        Log.bx(stringCompat, "handlePendedBixbyAction " + this.mPendedBixbyAction);
        Runnable runnable = this.mPendedBixbyAction;
        if (runnable != null) {
            runnable.run();
        }
    }

    private boolean hasWindowFocus() {
        if (getActivity() == null || !getActivity().hasWindowFocus()) {
            return false;
        }
        return true;
    }

    private boolean isAlive() {
        if (this.mActivityView != null) {
            return true;
        }
        return false;
    }

    private boolean isExecutable(String str) {
        String appState;
        if (this.mAppContext == null || str == null || (appState = Sbixby.getStateHandler().getAppState(this.mAppContext, (Bundle) null)) == null || !appState.contains(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        if (obj != null) {
            Integer num = (Integer) obj;
            if (num.intValue() != 0) {
                this.mActivityView.updateGradientProtection(num.intValue());
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onActivityRecreate$8() {
        Optional.ofNullable(getActivity()).ifPresent(new l(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onActivityResumeBG$5(String str, DialogInterface dialogInterface, int i2) {
        new GotoMyFilesCmd().execute(this, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBackPressInvokableStateChanged$9() {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView != null && !iGalleryActivityView.isActivityDestroyed()) {
            this.mActivityView.setBackPressedCallbackEnabled((String) null);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBixbyAction$7(Object obj) {
        this.mPendedBixbyAction = null;
        handleBixbyAction(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBottomTabItems$3() {
        this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
    }

    private boolean needGDPR() {
        if (Features.isEnabled(Features.SHOW_BAIDU_LOCATION_AUTH_POPUP)) {
            return GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_FOR_BAIDU, true);
        }
        if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
            return GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_V2, true);
        }
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP, true);
    }

    /* access modifiers changed from: private */
    public void onActivityBgColorChange(Object obj, Bundle bundle) {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView != null && !iGalleryActivityView.isActivityDestroyed()) {
            this.mActivityView.setBackgroundColor(((Integer) obj).intValue());
        }
    }

    private void onActivityPostResume() {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "onPostResume(m) " + this.mBlackboard);
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView != null && iGalleryActivityView.isActivityResumed()) {
            checkGDPR();
            ThreadPool.getInstance().submit(new C3.j(this, 1));
            if (Features.isEnabled(Features.SUPPORT_AUTO_CREATE_STORY) && GalleryPreference.getInstance().loadInt(PreferenceName.AUTO_CREATE_EVENT_FROM_CMH, -1) < 0) {
                SettingPreference.StoryAutoCreation.setAndNotifyIfChanged(getContext(), true);
            }
        }
    }

    /* access modifiers changed from: private */
    public Object onActivityPostResumeOnBg(ThreadPool.JobContext jobContext) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "onPostResume(w) " + this.mBlackboard);
        Context applicationContext = getApplicationContext();
        if (applicationContext != null) {
            this.mBlackboard.post("command://BeamPrepare", (Object) null);
            disableAutoFillService(applicationContext);
            requestMediaScan();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onActivityRecreate(Object obj, Bundle bundle) {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView == null) {
            return;
        }
        if (iGalleryActivityView.isExistFragment("BottomTabFragment") || this.mActivityView.isExistFragment("ListContainerFragment")) {
            ThreadUtil.postOnUiThreadDelayed(new f(this, 1), 50);
        }
    }

    /* access modifiers changed from: private */
    public void onBackPressInvokableStateChanged(Object obj, Bundle bundle) {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            IGalleryActivityView iGalleryActivityView = this.mActivityView;
            if (iGalleryActivityView != null && !iGalleryActivityView.isActivityDestroyed()) {
                this.mActivityView.setBackPressedCallbackEnabled();
                return;
            }
            return;
        }
        ThreadUtil.postOnUiThread(new f(this, 3));
    }

    /* access modifiers changed from: private */
    public void onBixbyAction(Object obj, Bundle bundle) {
        if (!isExecutable(this.mBlackboard.getName())) {
            Log.bx(this.TAG, "onBixbyAction skipped, not executable");
        } else if ((DeviceInfo.isDexMode(getContext()) || SystemUi.isInMultiWindowMode(getActivity())) && hasWindowFocus()) {
            Log.bx(this.TAG, "onBixbyAction direct call");
            handleBixbyAction(obj);
        } else {
            Log.bx(this.TAG, "onBixbyAction pended");
            this.mPendedBixbyAction = new d(22, this, obj);
        }
    }

    /* access modifiers changed from: private */
    public void onHiddenKey(Object obj, Bundle bundle) {
        String str = (String) obj;
        if (!str.startsWith("*#9900#debug") && !str.startsWith("*#0911#func#")) {
            return;
        }
        if (str.startsWith("*#9900#debug#")) {
            new CommandLine().onCmd(getActivity(), str.replace("*#9900#debug#", "*#9900#debug# "));
        } else {
            new CommandLine().onCmd(getActivity(), str);
        }
    }

    /* access modifiers changed from: private */
    public void onRemoveBixbyAction(Object obj, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.bx(stringCompat, "onRemoveBixbyAction " + this.mPendedBixbyAction);
        this.mPendedBixbyAction = null;
    }

    /* access modifiers changed from: private */
    public void onSettingAutoRotationChanged(Object obj, Bundle bundle) {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView != null) {
            iGalleryActivityView.resetAutoRotation(((Boolean) obj).booleanValue());
        }
    }

    /* access modifiers changed from: private */
    public void onSettingShapeButtonChanged(Object obj, Bundle bundle) {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView != null) {
            iGalleryActivityView.setShapeButtonStatusChanged();
        }
    }

    /* access modifiers changed from: private */
    public void onSharedAlbumBlockerChanged(Object obj, Bundle bundle) {
        String str;
        StringCompat stringCompat = this.TAG;
        Boolean bool = (Boolean) obj;
        if (bool.booleanValue()) {
            str = "blocked";
        } else {
            str = "unblocked";
        }
        Log.d(stringCompat, "onSharedAlbumBlockerChanged : shared albums are ".concat(str));
        this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        if ((!bool.booleanValue() || !PreferenceFeatures.OneUi5x.MX_ALBUMS || !LocationKey.isAlbumsMatch(readLocationKeyCurrent)) && !LocationKey.isSharings(readLocationKeyCurrent) && !LocationKey.isSharingChoice(readLocationKeyCurrent) && !LocationKey.isSharingPictures(readLocationKeyCurrent) && (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !LocationKey.isTrash(readLocationKeyCurrent))) {
            BlackboardUtils.publishDataRequestForce(this.mBlackboard, "location://sharing/albums");
        } else {
            Blackboard.getApplicationInstance().post("global://event/activityRecreate", (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void onSharedAlbumEnablerChanged(Object obj, Bundle bundle) {
        String str;
        StringCompat stringCompat = this.TAG;
        Boolean bool = (Boolean) obj;
        if (bool.booleanValue()) {
            str = MediaApiContract.Parameter.ENABLED;
        } else {
            str = "disabled";
        }
        Log.d(stringCompat, "onSharedAlbumEnablerChanged : shared albums are ".concat(str));
        this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        StringCompat stringCompat2 = this.TAG;
        Log.d(stringCompat2, "onSharedAlbumEnablerChanged : current=" + readLocationKeyCurrent);
        if (!bool.booleanValue() && "location://sharing/albums".equals(readLocationKeyCurrent)) {
            this.mBlackboard.post("command://SharingServiceDisabled", (Object) null);
        } else if (PreferenceFeatures.OneUi5x.MX_ALBUMS && LocationKey.isAlbumsMatch(readLocationKeyCurrent)) {
            BlackboardUtils.publishDataRequestForce(this.mBlackboard, "location://sharing/albums");
        }
    }

    /* access modifiers changed from: private */
    public void onSharingServiceChanged(Object obj, Bundle bundle) {
        if (!((Boolean) obj).booleanValue()) {
            this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
            String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
            if ("location://sharing/albums".equals(readLocationKeyCurrent)) {
                GalleryPreference.getInstance().saveState("location://variable/currentv1", "location://timeline");
                if (this.mActivityView.isActivityResumed()) {
                    StringCompat stringCompat = this.TAG;
                    Log.d(stringCompat, "onSharingServiceChanged : current=" + readLocationKeyCurrent);
                    this.mBlackboard.post("location://variable/currentv1", "location://timeline");
                    return;
                }
            }
            Log.d(this.TAG, "onSharingServiceChanged : Service is stopped and finish");
            this.mBlackboard.post("command://request_suicide", (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public boolean preloadHeavyFeatures(ThreadPool.JobContext jobContext) {
        Trace.beginSection("preloadHeavyFeatures");
        long currentTimeMillis = System.currentTimeMillis();
        Features.isEnabled(Features.IS_ENABLED_SHOW_BUTTON_SHAPES);
        Features.isEnabled(Features.SUPPORT_REAL_RATIO);
        Features.isEnabled(Features.SUPPORT_LIVE_DRAWING);
        Features.isEnabled(Features.SUPPORT_VISION_INTELLIGENCE);
        Features.isEnabled(Features.IS_KNOX_MODE);
        Features.isEnabled(Features.IS_AFW_MODE);
        Features.isEnabled(Features.IS_AOD_ENABLED);
        if (!SamsungCloudCompat.initSamsungCloud2(getApplicationContext())) {
            CloudStateCompat.load(false);
        }
        Features.isEnabled(Features.SUPPORT_SAMSUNG_CLIPBOARD);
        Features.isEnabled(Features.SUPPORT_DYNAMIC_SEARCH_SUGGESTION_WITH_DIRECT);
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "preloadHeavyFeatures +" + (System.currentTimeMillis() - currentTimeMillis));
        Trace.endSection();
        return true;
    }

    private void registerReceivers() {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView == null || iGalleryActivityView.isActivityDestroyed()) {
            Log.e(this.TAG, "registerReceivers skipped. activity already destroyed");
        } else {
            GlobalActionReceiver.getInstance().register(getApplicationContext());
        }
    }

    /* access modifiers changed from: private */
    public void releaseCpu() {
        ThreadUtil.removeCallbackOnBgThread(this.mReleaseCpuRunnable);
        getBoosterCompat().releaseScroll();
    }

    private void requestMediaScan() {
        try {
            PreferenceCache preferenceCache = PreferenceCache.LastFileOpPath;
            String string = preferenceCache.getString();
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(NumericEnum.SEP);
                if (split.length > 0) {
                    MediaScanner.scanFolders(Arrays.asList(split), (MediaScannerListener) null);
                    preferenceCache.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAppContext() {
        if (this.mAppContext == null) {
            this.mAppContext = this.mActivityView.getActivity().getApplicationContext();
        }
    }

    private void sharePendingFileIfExists(Context context, LaunchIntent launchIntent) {
        if (launchIntent != null && launchIntent.hasPendingShareEvent()) {
            launchIntent.consumePendingShareEvent();
            String andClear = PendingShare.getAndClear();
            if (andClear == null) {
                Log.w(this.TAG, "share pending file skip. invalid path");
            } else if (TextUtils.equals(this.mBlackboard.getName(), launchIntent.getPendingBlackboardName())) {
                Log.d(this.TAG, "share pending file skip. ignore pending action on the same activity");
            } else {
                if (ConvertingUsageType.COMMON_SHARE.value == launchIntent.getPendingUsageTypeValue()) {
                    new ShareViaCmd().execute(this, new MediaItem[]{UriItemLoader.createUriItem(context, new File(andClear))}, null);
                }
                Blackboard.postEventGlobal(EventMessage.obtain(3023));
            }
        }
    }

    /* access modifiers changed from: private */
    public void syncFragmentStack(Object obj, Bundle bundle) {
        IGalleryActivityView iGalleryActivityView;
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) && (iGalleryActivityView = this.mActivityView) != null && !iGalleryActivityView.isActivityDestroyed()) {
            this.mActivityView.syncFragmentStack();
        }
    }

    private void unregisterReceivers() {
        GlobalActionReceiver.getInstance().unregister(getApplicationContext());
    }

    private void updateBottomTabItems() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU || DrawerUtil.supportDrawerLayout(getContext())) {
            ThreadUtil.postOnUiThread(new f(this, 2));
        }
    }

    public void checkGDPR() {
        boolean needGDPR = needGDPR();
        if (Features.isEnabled(Features.SUPPORT_LOCATION) && needGDPR && !this.mActivityView.isDialogShowing("data://user/dialog/GDPRLocation")) {
            if (!Features.isEnabled(Features.SUPPORT_GDPR)) {
                SettingPreference.LocationAuth.setAndNotifyIfChanged(getContext(), true);
                this.mBlackboard.postEvent(EventMessage.obtain(1026));
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "GDPR not supported in " + Features.getSalesCode());
            } else if (!Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                if (GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_FOR_BAIDU, true) && SettingPreference.LocationAuth.setAndNotifyIfChanged(getContext(), false)) {
                    this.mBlackboard.postEvent(EventMessage.obtain(1026));
                }
                new ShowLocationGdprDialogCmd().execute(this, null);
            }
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/system/auto_rotation", new C3.e(this, 14)));
        arrayList.add(new SubscriberInfo("global://setting/system/shape_button", new C3.e(this, 15)));
        arrayList.add(new SubscriberInfo("global://setting/service/sharings", new C3.e(this, 16)));
        arrayList.add(new SubscriberInfo("global://setting/secure/shared_album_blocker_changed", new C3.e(this, 17)));
        arrayList.add(new SubscriberInfo("global://setting/event/shared_album_enabler_changed", new C3.e(this, 18)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://event/activityRecreate", new C3.e(this, 19)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("adjust/popover/options", new C3.e(this, 20)).setWorkingOnUI());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_create", new C3.e(this, 10)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_create", new C3.e(this, 21)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_resume", new C3.e(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_resume", new C3.e(this, 6)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_pause", new C3.e(this, 7)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_pause", new C3.e(this, 8)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_configuration_changed", new C3.e(this, 9)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_done", new C3.e(this, 11)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_destroy", new C3.e(this, 12)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ChangeActivityBgColor", new C3.e(this, 13)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler = this.mBottomBarHandler;
        Objects.requireNonNull(bottomBarHandler);
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new a(bottomBarHandler, 0)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler2 = this.mBottomBarHandler;
        Objects.requireNonNull(bottomBarHandler2);
        arrayList.add(new SubscriberInfo("command://InflateBottomBar", new a(bottomBarHandler2, 1)).setWorkingCurrent());
        BottomBarHandler<?> bottomBarHandler3 = this.mBottomBarHandler;
        Objects.requireNonNull(bottomBarHandler3);
        arrayList.add(new SubscriberInfo("command://AddBottomBar", new a(bottomBarHandler3, 2)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler4 = this.mBottomBarHandler;
        Objects.requireNonNull(bottomBarHandler4);
        arrayList.add(new SubscriberInfo("command://ShowBottomBar", new a(bottomBarHandler4, 3)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler5 = this.mBottomBarHandler;
        Objects.requireNonNull(bottomBarHandler5);
        arrayList.add(new SubscriberInfo("command://HideBottomBar", new a(bottomBarHandler5, 4)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler6 = this.mBottomBarHandler;
        Objects.requireNonNull(bottomBarHandler6);
        arrayList.add(new SubscriberInfo("command://RestoreBottomBar", new a(bottomBarHandler6, 5)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler7 = this.mBottomMoveBarHandler;
        Objects.requireNonNull(bottomBarHandler7);
        arrayList.add(new SubscriberInfo("command://InflateBottomMoveBar", new a(bottomBarHandler7, 1)).setWorkingCurrent());
        BottomBarHandler<?> bottomBarHandler8 = this.mBottomMoveBarHandler;
        Objects.requireNonNull(bottomBarHandler8);
        arrayList.add(new SubscriberInfo("command://AddBottomMoveBar", new a(bottomBarHandler8, 2)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler9 = this.mBottomMoveBarHandler;
        Objects.requireNonNull(bottomBarHandler9);
        arrayList.add(new SubscriberInfo("command://ShowBottomMoveBar", new a(bottomBarHandler9, 3)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler10 = this.mBottomMoveBarHandler;
        Objects.requireNonNull(bottomBarHandler10);
        arrayList.add(new SubscriberInfo("command://HideBottomMoveBar", new a(bottomBarHandler10, 4)).setWorkingOnUI());
        BottomBarHandler<?> bottomBarHandler11 = this.mBottomMoveBarHandler;
        Objects.requireNonNull(bottomBarHandler11);
        arrayList.add(new SubscriberInfo("command://RestoreBottomMoveBar", new a(bottomBarHandler11, 5)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://bixby_action", new C3.e(this, 22)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://remove_bixby_action", new C3.e(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("debug://showDebugLog", new C3.e(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new C3.e(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new C3.e(this, 2)).setWorkingOnUI());
        PocFeatures pocFeatures = PocFeatures.FragmentPredictiveBack;
        if (!PocFeatures.isEnabled(pocFeatures)) {
            arrayList.add(new SubscriberInfo("data://child_fragment_changed", new C3.e(this, 2)).setWorkingOnUI());
        }
        arrayList.add(new SubscriberInfo("data://drawer_opened", new C3.e(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data:///SearchToolbarFocusChanged", new C3.e(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://EnterMoveMode", new C3.e(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ExitMoveMode", new C3.e(this, 2)).setWorkingOnUI());
        if (PocFeatures.isEnabled(pocFeatures)) {
            arrayList.add(new SubscriberInfo("command:///OnBackPressInvokableStateChanged", new C3.e(this, 2)).setWorkingOnUI());
            arrayList.add(new SubscriberInfo("command:///OnPredictiveBackPressedInFragment", new C3.e(this, 4)).setWorkingOnUI());
        }
        arrayList.add(new SubscriberInfo("command://UpdateGradientProtection", new C3.e(this, 5)).setWorkingOnUI());
    }

    public Activity getActivity() {
        try {
            return this.mActivityView.getActivity();
        } catch (NullPointerException unused) {
            Log.e(this.TAG, "getActivity failed");
            return null;
        }
    }

    public Context getApplicationContext() {
        return this.mAppContext;
    }

    public Context getContext() {
        try {
            return this.mActivityView.getContext();
        } catch (NullPointerException unused) {
            Log.e(this.TAG, "getContext failed");
            return null;
        }
    }

    public void onActivityConfigurationChanged(Object obj, Bundle bundle) {
        IGalleryActivityView iGalleryActivityView = this.mActivityView;
        if (iGalleryActivityView == null || iGalleryActivityView.isActivityDestroyed()) {
            Log.e(this.TAG, "activity already destroyed");
            return;
        }
        DesktopWindowingManager desktopWindowingManager = this.mDesktopWindowingManager;
        if (desktopWindowingManager != null) {
            desktopWindowingManager.configChanged(getContext());
        }
    }

    public void onActivityCreate(Object obj, Bundle bundle) {
        this.mIsFirstThumbLoad = true;
        setAppContext();
    }

    public void onActivityCreateBG(Object obj, Bundle bundle) {
        if (this.mActivityView != null) {
            setAppContext();
            this.mBlackboard.publish("data://dex_mode", Boolean.valueOf(DeviceInfo.isDexMode(getContext())));
            boostCpu();
            registerReceivers();
            SimpleThreadPool.getInstance().execute(new i(0));
            DesktopWindowingManager desktopWindowingManager = this.mDesktopWindowingManager;
            if (desktopWindowingManager != null) {
                desktopWindowingManager.init(getContext(), new f(this, 4));
            }
        }
    }

    public void onActivityDestroy(Object obj, Bundle bundle) {
        Context applicationContext = getApplicationContext();
        LowPerformanceLogger.dumpToFile();
        KnoxUtil.releaseInstance();
        unregisterReceivers();
        SlideshowServiceHelper.stopService(applicationContext, getBlackboard().getName());
        ShareList.clearShareList();
        SeApiCompat.getMediaResourceHelper().release(this.mBlackboard.getName());
        this.mBottomBarHandler.release();
        BottomBarHandler<?> bottomBarHandler = this.mBottomMoveBarHandler;
        if (bottomBarHandler != null) {
            bottomBarHandler.release();
        }
        DesktopWindowingManager desktopWindowingManager = this.mDesktopWindowingManager;
        if (desktopWindowingManager != null) {
            desktopWindowingManager.release();
        }
        this.mAppContext = null;
        this.mActivityView = null;
    }

    public void onActivityPause(Object obj, Bundle bundle) {
        SmartSuggestionsSettingApi.getInstance().clearPdc();
    }

    public void onActivityPauseBg(Object obj, Bundle bundle) {
        Context applicationContext = getApplicationContext();
        if (applicationContext != null) {
            IdleWorker.startIdle(applicationContext);
            AppRatingHelper.increaseCount(getContext(), this.mBlackboard);
            SearchWordCollector.clearRubinState();
        }
    }

    public void onActivityResume(Object obj, Bundle bundle) {
        updateBottomTabItems();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        r5 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceDebug();
        r6 = com.samsung.android.gallery.support.utils.PreferenceName.LAST_DECODE_FILE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResumeBG(java.lang.Object r5, android.os.Bundle r6) {
        /*
            r4 = this;
            android.content.Context r5 = r4.getApplicationContext()
            r4.showSystemWarning()
            r4.handlePendedBixbyAction()
            com.samsung.android.gallery.module.editor.PresetServiceBinder r6 = com.samsung.android.gallery.module.editor.PresetServiceBinder.getInstance()
            r6.onResume()
            r4.resumeServiceIfPresent(r5)
            com.samsung.android.gallery.support.blackboard.Blackboard r6 = r4.mBlackboard
            java.lang.String r0 = "data://launch_intent"
            java.lang.Object r6 = r6.read(r0)
            com.samsung.android.gallery.module.abstraction.LaunchIntent r6 = (com.samsung.android.gallery.module.abstraction.LaunchIntent) r6
            r0 = 0
            if (r6 == 0) goto L_0x0051
            r4.sharePendingFileIfExists(r5, r6)     // Catch:{ Exception -> 0x0025 }
            goto L_0x0032
        L_0x0025:
            r5 = move-exception
            com.samsung.android.gallery.support.utils.StringCompat r1 = r4.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "onActivityResumeBG pending intent failed. e="
            r2.<init>(r3)
            A.a.r(r5, r2, r1)
        L_0x0032:
            java.lang.String r5 = "pending-timeout-service"
            java.lang.Object r5 = r6.popExtra(r5, r0)     // Catch:{ Exception -> 0x0044 }
            android.content.Intent r5 = (android.content.Intent) r5     // Catch:{ Exception -> 0x0044 }
            if (r5 == 0) goto L_0x0051
            android.content.Context r6 = r4.getContext()     // Catch:{ Exception -> 0x0044 }
            r6.startService(r5)     // Catch:{ Exception -> 0x0044 }
            goto L_0x0051
        L_0x0044:
            r5 = move-exception
            com.samsung.android.gallery.support.utils.StringCompat r6 = r4.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onActivityResumeBG pending timeout intent failed. e="
            r1.<init>(r2)
            A.a.r(r5, r1, r6)
        L_0x0051:
            android.app.Activity r5 = r4.getActivity()
            com.samsung.android.gallery.app.provider.ShareProvider.clearExtendedShareList(r5)
            boolean r5 = com.samsung.android.gallery.support.utils.SafeMode.ENABLED
            if (r5 == 0) goto L_0x00b7
            com.samsung.android.gallery.support.utils.GalleryPreference r5 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceDebug()
            com.samsung.android.gallery.support.utils.PreferenceName r6 = com.samsung.android.gallery.support.utils.PreferenceName.LAST_DECODE_FILE
            java.lang.String r5 = r5.loadString((com.samsung.android.gallery.support.utils.PreferenceName) r6, (java.lang.String) r0)
            if (r5 == 0) goto L_0x00b7
            java.lang.String r0 = "latest failed file="
            java.lang.String r0 = r0.concat(r5)
            java.lang.String r1 = "SafeMode"
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
            com.samsung.android.gallery.support.utils.GalleryPreference r0 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceDebug()
            r0.removeState((com.samsung.android.gallery.support.utils.PreferenceName) r6)
            android.app.AlertDialog$Builder r6 = new android.app.AlertDialog$Builder
            android.content.Context r0 = r4.getContext()
            r6.<init>(r0)
            r0 = 2131888247(0x7f120877, float:1.9411124E38)
            android.app.AlertDialog$Builder r6 = r6.setTitle(r0)
            java.lang.String r0 = "Latest failed file:\n"
            java.lang.String r0 = r0.concat(r5)
            android.app.AlertDialog$Builder r6 = r6.setMessage(r0)
            Ba.a r0 = new Ba.a
            r1 = 1
            r0.<init>(r1)
            r1 = 2131886465(0x7f120181, float:1.940751E38)
            android.app.AlertDialog$Builder r6 = r6.setNegativeButton(r1, r0)
            C3.g r0 = new C3.g
            r1 = 0
            r0.<init>(r1, r4, r5)
            r4 = 2131887777(0x7f1206a1, float:1.941017E38)
            android.app.AlertDialog$Builder r4 = r6.setPositiveButton(r4, r0)
            C3.h r5 = new C3.h
            r6 = 0
            r5.<init>(r4, r6)
            com.samsung.android.gallery.support.utils.ThreadUtil.runOnUiThread(r5)
        L_0x00b7:
            com.samsung.android.gallery.support.utils.Features r4 = com.samsung.android.gallery.support.utils.Features.SUPPORT_USB_STORAGE
            boolean r4 = com.samsung.android.gallery.support.utils.Features.isEnabled(r4)
            if (r4 == 0) goto L_0x00c6
            com.samsung.android.gallery.module.mtp.UsbStorageState r4 = com.samsung.android.gallery.module.mtp.UsbStorageState.getInstance()
            r4.notifyIfAbsent()
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.GalleryActivityHandler.onActivityResumeBG(java.lang.Object, android.os.Bundle):void");
    }

    public void onThumbLoadDone(Object obj, Bundle bundle) {
        ThreadUtil.postOnBgThread(new f(this, 0));
        if (isAlive() && this.mIsFirstThumbLoad) {
            this.mIsFirstThumbLoad = false;
            Log.enableSaveErrorLogs();
            ThreadUtil.startPostponedHandler();
            onActivityPostResume();
            ThreadPool.getInstance().submit(new C3.j(this, 0));
        }
    }

    public void resumeServiceIfPresent(Context context) {
        new ProgressServiceUtil(this.mBlackboard.getName()).continueIfServiceRunning(context);
    }

    public void showSystemWarning() {
        boolean checkLowStorage = StorageUtil.checkLowStorage(false);
        if (checkLowStorage) {
            new ShowLowStorageCmd().execute(this, new Object[0]);
        }
        if (StorageUtil.isLowStorage() != checkLowStorage) {
            StorageUtil.setLowStorage(checkLowStorage);
            this.mBlackboard.postEvent(EventMessage.obtain(1004));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class BottomBarHandler<V extends View> {
        final Blackboard mBlackboard;
        V mCustomView;

        public BottomBarHandler(Blackboard blackboard) {
            this.mBlackboard = blackboard;
        }

        public abstract void add(Object obj, Bundle bundle);

        public V getBar() {
            if (this.mCustomView == null) {
                try {
                    V view = getView(BlackboardUtils.readActivity(this.mBlackboard));
                    this.mCustomView = view;
                    if (view != null) {
                        setViewListener(view);
                    }
                } catch (Exception e) {
                    a.s(e, new StringBuilder("getView failed e="), "BottomBarHandler");
                }
            }
            return this.mCustomView;
        }

        public int getBottomBarHeight(View view) {
            return view.getResources().getDimensionPixelSize(getHeightRes());
        }

        public abstract int getHeightRes();

        public abstract V getView(Activity activity);

        public abstract void hide(Object obj, Bundle bundle);

        public void inflate(Object obj, Bundle bundle) {
            getBar();
        }

        public void initFloatingLayout(View view, RecyclerView recyclerView) {
            ViewParent parent = view.getParent();
            if (parent instanceof FloatingBottomLayout) {
                FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) parent;
                floatingBottomLayout.m(true, false);
                floatingBottomLayout.a(List.of(view));
                floatingBottomLayout.f(false);
                floatingBottomLayout.setRecyclerView(recyclerView);
            }
        }

        public void onStateChange(boolean z) {
            if (!z) {
                ViewMarginUtils.setStartMargin(this.mCustomView, 0);
            }
            this.mBlackboard.postEvent(EventMessage.obtain(1064, Boolean.valueOf(z)));
        }

        public void release() {
            this.mCustomView = null;
        }

        public void restore(Object obj, Bundle bundle) {
            V v = this.mCustomView;
            if (v != null) {
                this.mCustomView.setMinimumHeight(v.getResources().getDimensionPixelOffset(getHeightRes()));
            }
        }

        public abstract void show(Object obj, Bundle bundle);

        public void setViewListener(V v) {
        }

        public void close(Object obj, Bundle bundle) {
        }
    }
}
