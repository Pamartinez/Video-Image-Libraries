package com.samsung.android.gallery.app.controller.externals;

import Gb.a;
import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.UrlLookup;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class CreateMeituMediaCmd extends CreateMediaCmd {
    protected static final String MT_ACTION = UrlLookup.getData("meitu.action");
    protected static final String MT_PACKAGE_NAME = UrlLookup.getData("meitu");

    private PackageInfo getMeituPackageInfo() {
        try {
            return PackageMonitorCompat.getInstance().getPackageManager().getPackageInfo(MT_PACKAGE_NAME, 0);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            j.s(e, new StringBuilder("getPackageInfo failed e="), this.TAG);
            return null;
        }
    }

    private boolean isPackageEnabled(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo;
        if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || !applicationInfo.enabled) {
            return false;
        }
        return true;
    }

    private boolean isPackageInstalled(PackageInfo packageInfo) {
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$createMedia$0(MediaItem mediaItem) {
        return !mediaItem.isPostProcessing();
    }

    private void startMeituEdit(Intent intent) {
        PackageInfo meituPackageInfo = getMeituPackageInfo();
        if (!isPackageInstalled(meituPackageInfo)) {
            guideDownloadPackage(MT_PACKAGE_NAME, getContext().getString(R.string.meitu_app));
            return;
        }
        String str = MT_PACKAGE_NAME;
        if (!isValidPackageVersion(str, 9755)) {
            guideUpdatePackage(str, getContext().getString(R.string.meitu_app));
        } else if (!isPackageEnabled(meituPackageInfo)) {
            Utils.showToast(getContext(), SeApiCompat.naturalizeText(getContext().getString(R.string.turn_on_setting_dialog_msg, new Object[]{getContext().getString(R.string.meitu_app)})));
        } else {
            try {
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException | SecurityException e) {
                j.u(e, new StringBuilder("start failed, e="), this.TAG);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.Predicate, java.lang.Object] */
    public void createMedia(MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "item is zero");
        } else {
            startMeituEdit(getIntent((ArrayList) Arrays.stream(mediaItemArr).filter(new Object()).map(new a(4)).collect(Collectors.toList())));
        }
    }

    public abstract Intent getIntent(ArrayList<Uri> arrayList);
}
