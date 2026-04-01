package com.samsung.android.gallery.app.activity.external;

import A.a;
import A4.C0372g;
import A4.C0384t;
import D3.i;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher;
import com.samsung.android.gallery.app.activity.external.launcher.HttpItemViewLauncher;
import com.samsung.android.gallery.app.activity.external.launcher.LocalAlbumViewLauncher;
import com.samsung.android.gallery.app.activity.external.launcher.MirroringViewLauncher;
import com.samsung.android.gallery.app.activity.external.launcher.QuickViewLauncher;
import com.samsung.android.gallery.app.activity.external.launcher.UriItemViewLauncher;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSecMPSettingsCmd;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.remote.SConnectUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.providers.CameraUsbUri;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class QuickViewNavigatorController extends ViewNavigatorController implements EventContext {
    QuickViewLauncher mLauncher;

    public QuickViewNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    private void clearWindowFlagsForLockScreen(Activity activity) {
        if (activity != null) {
            activity.getWindow().clearFlags(524288);
        }
    }

    /* access modifiers changed from: private */
    public void finishActivityOnUiThread() {
        finishActivityOnUiThread(true);
    }

    private void handleOnCreateGifResult(int i2) {
        if (i2 == -1) {
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
        }
    }

    private void handleOnMotionPhotoResult(int i2) {
        if (i2 == -1) {
            isFromLockScreen();
        }
    }

    private boolean hasAbsolutePath() {
        return !TextUtils.isEmpty(getLaunchIntent().getAbsolutePath());
    }

    private boolean hasBucketId() {
        if (getLaunchIntent().getAlbumBucketId() != 0) {
            return true;
        }
        return false;
    }

    private boolean hasUriData() {
        if (getLaunchIntent().getUriData() != null) {
            return true;
        }
        return false;
    }

    private boolean isFromCamera() {
        return getLaunchIntent().isFromCamera();
    }

    private boolean isFromLockScreen() {
        if (!getLaunchIntent().isFromLockScreen() || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    private boolean isFromMyFiles() {
        return getLaunchIntent().isFromMyFiles();
    }

    private boolean isLocalAlbumData() {
        Uri uriData = getLaunchIntent().getUriData();
        if (uriData == null) {
            return false;
        }
        if ((hasAbsolutePath() || hasBucketId()) && MediaUri.getInstance().matches(uriData.toString())) {
            return true;
        }
        return false;
    }

    private boolean isSConnectData() {
        return SConnectUtil.isSConnectIntent(getLaunchIntent().getIntent());
    }

    private boolean isShareMirroring() {
        return ((Boolean) getLaunchIntent().getExtra("share_mirroring", Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishActivityOnUiThread$5(boolean z) {
        if (z) {
            Toast.makeText(getContext(), R.string.unsupported_file, 0).show();
        }
        this.mBlackboard.post("command://request_suicide", (Object) null);
        if (!isFromCamera() && !isFromMyFiles()) {
            Utils.restartGalleryActivity(getContext());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchUriItemView$1() {
        finishActivityOnUiThread(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchUriItemView$2() {
        finishActivityOnUiThread(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadQuickViewInternal$3(MediaItem[] mediaItemArr, int i2) {
        ThumbnailLoader.getInstance().loadThumbnailSync(mediaItemArr[i2], ThumbKind.MEDIUM_KIND);
        this.mBlackboard.publish("data://viewer_first_data", mediaItemArr[i2]);
        this.mBlackboard.publish(DataKey.DATA("location://quickView"), mediaItemArr);
        new VuLauncher(this.mBlackboard).publishData().requestBitmapUrgent().launch("location://quickView", i2, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onNavigatorCreated$0() {
        finishActivityOnUiThread(!this.mActivityView.isRestartedFromQuickView());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSnackBar$4(View view) {
        new StartSecMPSettingsCmd().execute(this, new Object[0]);
        finishActivityOnUiThread(false);
    }

    private void launchUriItemView() {
        QuickViewNavigatorController quickViewNavigatorController;
        LaunchIntent launchIntent = getLaunchIntent();
        UriItemViewLauncher uriItemViewLauncher = new UriItemViewLauncher(this.mBlackboard, launchIntent, new g(this, 0), new g(this, 1));
        if (uriItemViewLauncher.isHttpUri()) {
            quickViewNavigatorController = this;
            quickViewNavigatorController.mLauncher = new HttpItemViewLauncher(this.mBlackboard, launchIntent, new g(this, 0), new g(this, 2), quickViewNavigatorController);
        } else {
            quickViewNavigatorController = this;
            quickViewNavigatorController.mLauncher = uriItemViewLauncher;
        }
        quickViewNavigatorController.mLauncher.execute();
    }

    private boolean loadQuickViewInternal(LaunchIntent launchIntent) {
        MediaItem[] mediaItemArr;
        if (!((Boolean) launchIntent.getExtra("quick-view-internal", Boolean.FALSE)).booleanValue() || (mediaItemArr = (MediaItem[]) Blackboard.getApplicationInstance().pop((String) launchIntent.getExtra("quick-view-data-location", ""))) == null || mediaItemArr.length <= 0) {
            return false;
        }
        Integer num = (Integer) launchIntent.getExtra("quick-view-data-position", 0);
        int intValue = num.intValue();
        Log.d(this.TAG, "loadQuickViewInternal", Integer.valueOf(mediaItemArr.length), num);
        SimpleThreadPool.getInstance().execute(new h(this, mediaItemArr, intValue));
        return true;
    }

    private boolean showSnackBar(Activity activity) {
        boolean z;
        if (activity == null) {
            Log.e(this.TAG, "showSnackBar skip. null activity");
            return false;
        }
        try {
            View findViewById = activity.getWindow().getDecorView().getRootView().findViewById(R.id.content_layout);
            String str = this.TAG;
            if (findViewById != null) {
                z = true;
            } else {
                z = false;
            }
            Log.d(str, "showSnackBar", Boolean.valueOf(z));
            if (findViewById == null) {
                return false;
            }
            r j2 = r.j(findViewById, -2, 0, false, activity.getString(R.string.sec_mp_security_allow_permission_description, new Object[]{PackageMonitorCompat.getInstance().getApplicationLabel("com.samsung.android.providers.media")}));
            j2.k(j2.f2220h.getText(R.string.view), new j(this));
            ThreadUtil.postOnUiThread(new C0372g(24, j2));
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("showSnackBar failed. e="), this.TAG);
            return false;
        }
    }

    public Activity getActivity() {
        return BlackboardUtils.readActivity(getBlackboard());
    }

    public Context getApplicationContext() {
        return BlackboardUtils.readAppContext(getBlackboard());
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public Context getContext() {
        return super.getContext();
    }

    public void onActivityResult(Object obj, Bundle bundle) {
        Object[] objArr = (Object[]) obj;
        int intValue = ((Integer) objArr[0]).intValue();
        int intValue2 = ((Integer) objArr[1]).intValue();
        if (intValue == 789) {
            handleOnCreateGifResult(intValue2);
            return;
        }
        if (intValue == 787) {
            handleOnMotionPhotoResult(intValue2);
        }
        super.onActivityResult(obj, bundle);
    }

    public void onDestroy() {
        QuickViewLauncher quickViewLauncher = this.mLauncher;
        if (quickViewLauncher != null) {
            quickViewLauncher.destroy();
        }
        if (isFromLockScreen()) {
            clearWindowFlagsForLockScreen(this.mActivityView.getActivity());
        }
    }

    public void onNavigatorCreated() {
        LaunchIntent launchIntent = getLaunchIntent();
        if (hasUriData()) {
            if (isFromCamera() && !isFromLockScreen()) {
                if (PocFeatures.isEnabled(PocFeatures.GmpAll)) {
                    GmpCompat.invoke(getContext(), "syncAddedFiles", (String) null);
                }
                if (FoldUtils.isFlipCoverScreen(getActivity()) && !launchIntent.getUriList().isEmpty()) {
                    launchUriItemView();
                } else if (!Features.isEnabled(Features.SUPPORT_CAMERA_USB_STORAGE) || !CameraUsbUri.match(launchIntent.getUriData())) {
                    CameraQuickViewLauncher cameraQuickViewLauncher = new CameraQuickViewLauncher(this.mBlackboard, launchIntent, new g(this, 3));
                    this.mLauncher = cameraQuickViewLauncher;
                    cameraQuickViewLauncher.execute();
                } else {
                    String str = this.TAG;
                    Log.i(str, "exec camera usb storage files : " + launchIntent);
                    launchUriItemView();
                }
            } else if (isLocalAlbumData() || !TextUtils.isEmpty(launchIntent.getAlbumBucketIds())) {
                LocalAlbumViewLauncher localAlbumViewLauncher = new LocalAlbumViewLauncher(this.mBlackboard, launchIntent, new g(this, 0));
                this.mLauncher = localAlbumViewLauncher;
                localAlbumViewLauncher.execute();
            } else if (isShareMirroring()) {
                MirroringViewLauncher mirroringViewLauncher = new MirroringViewLauncher(this.mBlackboard, launchIntent, new g(this, 0));
                this.mLauncher = mirroringViewLauncher;
                mirroringViewLauncher.execute();
            } else {
                launchUriItemView();
            }
        } else if (isSConnectData()) {
            launchUriItemView();
        } else {
            Boolean bool = Boolean.FALSE;
            if (((Boolean) launchIntent.getExtra("quick-view-internal", bool)).booleanValue()) {
                if (!loadQuickViewInternal(launchIntent)) {
                    Log.e(this.TAG, "onNavigatorCreated without internal data");
                    finishActivityOnUiThread();
                    return;
                }
            } else if (launchIntent.getUriData() != null || !((Boolean) Optional.ofNullable(launchIntent.getIntent().getComponent()).map(new C0384t(29)).map(new i(0)).orElse(bool)).booleanValue()) {
                Log.e(this.TAG, "onNavigatorCreated without uri");
                finishActivityOnUiThread();
                return;
            } else {
                Log.w(this.TAG, "onNavigatorCreated without uri > redirect main activity");
                finishActivityOnUiThread(false);
                return;
            }
        }
        String str2 = this.TAG;
        Log.d(str2, "executed : " + this.mLauncher);
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_done", Long.valueOf(ThumbnailLoader.getInstance().getElapsedTime()));
    }

    public void onRequestQuickViewItem(Object obj, Bundle bundle) {
        launchUriItemView();
    }

    private void finishActivityOnUiThread(boolean z) {
        if (!z || !((Boolean) Blackboard.getApplicationInstance().pop("data://user/SecurityExceptionOnSecMp", Boolean.FALSE)).booleanValue() || !showSnackBar(getActivity())) {
            ThreadUtil.postOnUiThread(new i(this, z));
        }
    }

    public void onTimelineDataLoaded(Object obj, Bundle bundle) {
    }
}
