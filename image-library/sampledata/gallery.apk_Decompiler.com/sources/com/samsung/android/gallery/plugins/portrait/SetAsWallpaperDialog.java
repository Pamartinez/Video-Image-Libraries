package com.samsung.android.gallery.plugins.portrait;

import A.a;
import A8.C0545b;
import A9.b;
import Da.g;
import N2.j;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetAsWallpaperDialog {
    private final ArrayList<VideoWallpaperPackage> mPackages = new ArrayList<>();

    private void collectPackages(Context context, Intent intent) {
        Intent intent2;
        int i2;
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 192);
        Log.d("SetAsWallpaperDialog", "loadItems : " + queryIntentActivities.size() + " items");
        for (ResolveInfo next : queryIntentActivities) {
            Log.i("SetAsWallpaperDialog", "loadItems : " + next);
            String str = next.activityInfo.packageName;
            if (SignatureChecker.checkSignature(AppResources.getAppContext(), str)) {
                CharSequence loadLabel = next.loadLabel(packageManager);
                if (!isInvalid(next, str, loadLabel)) {
                    ActivityInfo activityInfo = next.activityInfo;
                    String str2 = activityInfo.name;
                    Bundle bundle = activityInfo.metaData;
                    if (bundle == null) {
                        i2 = 0;
                    } else {
                        i2 = bundle.getInt("com.samsung.android.intent.action.SET_AS_WALLPAPER.ORDER");
                    }
                    intent2 = intent;
                    VideoWallpaperPackage videoWallpaperPackage = new VideoWallpaperPackage(intent2, str, str2, loadLabel, i2);
                    if (videoWallpaperPackage.isWatchFaceLarge() && !Features.isEnabled(Features.IS_WATCH_FACE_LARGE_ENABLED)) {
                        Log.i("SetAsWallpaperDialog", "watch face large not supported");
                    } else if (videoWallpaperPackage.isCover() && (Features.isEnabled(Features.IS_WATCH_FACE_LARGE_ENABLED) || !Features.isEnabled(Features.IS_COVER_SCREEN_ENABLED))) {
                        Log.i("SetAsWallpaperDialog", "cover not supported");
                    } else if (!videoWallpaperPackage.isClearCover() || SeApiCompat.isClearCoverAttached(context)) {
                        if (DeviceInfo.isDexMode(context) && videoWallpaperPackage.isLock()) {
                            if (SeApiCompat.isDexStandAloneMode(context)) {
                                if (!videoWallpaperPackage.isStandAloneDex()) {
                                    Log.i("SetAsWallpaperDialog", "ignore not stand alone dex : " + str);
                                }
                            } else if (!videoWallpaperPackage.isDualDex()) {
                                Log.i("SetAsWallpaperDialog", "ignore not dual dex : " + str);
                            }
                        }
                        if (!Features.SUPPORT_FLIP_COVER_GALLERY.isEnabled() ? videoWallpaperPackage.isLock() : videoWallpaperPackage.isLock() || videoWallpaperPackage.isWatchFaceLarge()) {
                            this.mPackages.add(videoWallpaperPackage);
                        }
                    } else {
                        Log.i("SetAsWallpaperDialog", "clearCover not supported");
                    }
                }
            } else {
                intent2 = intent;
                a.u("Signature is not matched. ", str, "SetAsWallpaperDialog");
            }
            intent = intent2;
        }
        this.mPackages.sort(Comparator.comparingInt(new C0545b(6)));
    }

    private Intent getIntent(Context context, String str) {
        String str2;
        if (DeviceInfo.isDexMode(context)) {
            str2 = "com.samsung.android.intent.action.SET_AS_WALLPAPER.DEX";
        } else if (Features.isEnabled(Features.IS_KNOX_MODE)) {
            str2 = "com.samsung.android.intent.action.SET_AS_WALLPAPER.KNOX";
        } else {
            str2 = "com.samsung.android.intent.action.SET_AS_WALLPAPER";
        }
        Intent intent = new Intent(str2);
        intent.setDataAndType(FileProviderUtil.getUri(context, str), FileType.getMimeType(str));
        return intent;
    }

    private String[] getItemsArray() {
        int size = this.mPackages.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = this.mPackages.get(i2).getLabel();
        }
        return strArr;
    }

    private void handleInvalid(String str) {
        if (!DeviceConfig.DEBUG_BINARY) {
            Log.e("SetAsWallpaperDialog", str);
            return;
        }
        throw new IllegalArgumentException(str);
    }

    private boolean isInvalid(ResolveInfo resolveInfo, String str, CharSequence charSequence) {
        if (charSequence == null) {
            StringBuilder k = j.k("loadLabel is null. ", str, " ");
            k.append(resolveInfo.activityInfo.name);
            handleInvalid(k.toString());
            return true;
        }
        Bundle bundle = resolveInfo.activityInfo.metaData;
        if (bundle == null) {
            StringBuilder k10 = j.k("metaData is null. ", str, " ");
            k10.append(resolveInfo.activityInfo.name);
            handleInvalid(k10.toString());
            return true;
        }
        Object obj = bundle.get("com.samsung.android.intent.action.SET_AS_WALLPAPER.ORDER");
        if (obj instanceof Integer) {
            return false;
        }
        handleInvalid("metaData invalid. com.samsung.android.intent.action.SET_AS_WALLPAPER.ORDER=" + obj + " / " + str);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$0(Context context, String str, DialogInterface dialogInterface, int i2) {
        this.mPackages.get(i2).onExecuteCmd(context, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$1(Context context, String str) {
        if (!((Activity) context).isDestroyed()) {
            new AlertDialog.Builder(context).setTitle(R$string.set_as_wallpaper).setItems(getItemsArray(), new g(this, context, str, 0)).create().show();
        }
    }

    private void loadItems(Context context, String str) {
        collectPackages(context, getIntent(context, str));
    }

    public void show(Context context, String str) {
        loadItems(context, str);
        if (this.mPackages.isEmpty()) {
            Log.e("SetAsWallpaperDialog", "Failed to execute -> packages empty");
        } else {
            ThreadUtil.postOnUiThread(new b(this, context, str, 12));
        }
    }
}
