package com.samsung.android.gallery.app.controller.internals;

import A.a;
import A8.C0545b;
import E3.e;
import N2.j;
import N3.c;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.WallpaperPackage;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetAsChooserDialogCmd extends BaseCommand {
    String METADATA_KEY = "com.samsung.android.intent.action.SET_AS_WALLPAPER.ORDER";
    private MediaItem[] mMediaItems;
    private final ArrayList<WallpaperPackage> mPackages = new ArrayList<>();

    private void executeTargetPackage(EventContext eventContext, Integer num) {
        WallpaperPackage wallpaperPackage = this.mPackages.get(num.intValue());
        wallpaperPackage.onExecuteCmd(eventContext, this.mMediaItems);
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
        postAnalyticsLog(wallpaperPackage.getEvent(), wallpaperPackage.getEventDetails());
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
            Log.e(this.TAG, str);
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
        Object obj = bundle.get(this.METADATA_KEY);
        if (obj instanceof Integer) {
            return false;
        }
        handleInvalid("metaData invalid. " + this.METADATA_KEY + "=" + obj + " / " + str);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Integer num) {
        String str = this.TAG;
        Log.d(str, "onPreExecute#PppChecker" + Logger.vt(Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)));
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            EventContext eventContext2 = eventContext;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            executePppChecker(eventContext2, mediaItemArr2, new e(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, 5));
            return;
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    private void loadItems() {
        String str;
        int i2;
        boolean isDexMode = isDexMode();
        if (isDexMode) {
            if (this.mMediaItems.length == 1) {
                str = "com.samsung.android.intent.action.SET_AS_WALLPAPER.DEX";
            } else {
                str = "com.samsung.android.intent.action.SET_AS_WALLPAPER_MULTIPLE.DEX";
            }
        } else if (isKnox()) {
            if (this.mMediaItems.length == 1) {
                str = "com.samsung.android.intent.action.SET_AS_WALLPAPER.KNOX";
            } else {
                str = "com.samsung.android.intent.action.SET_AS_WALLPAPER_MULTIPLE.KNOX";
            }
        } else if (this.mMediaItems.length == 1) {
            str = "com.samsung.android.intent.action.SET_AS_WALLPAPER";
        } else {
            str = "com.samsung.android.intent.action.SET_AS_WALLPAPER_MULTIPLE";
        }
        Intent intent = new Intent(str);
        MediaItem[] mediaItemArr = this.mMediaItems;
        if (mediaItemArr.length == 1) {
            intent.setDataAndType(ContentUri.getUri(mediaItemArr[0]), this.mMediaItems[0].getMimeType());
        }
        PackageManager packageManager = getContext().getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 192);
        Log.d(this.TAG, "loadItems : " + queryIntentActivities.size() + " items");
        for (ResolveInfo next : queryIntentActivities) {
            Log.i(this.TAG, "loadItems : " + next);
            String str2 = next.activityInfo.packageName;
            if (SignatureChecker.checkSignature(AppResources.getAppContext(), str2)) {
                CharSequence loadLabel = next.loadLabel(packageManager);
                if (!isInvalid(next, str2, loadLabel)) {
                    ActivityInfo activityInfo = next.activityInfo;
                    String str3 = activityInfo.name;
                    Bundle bundle = activityInfo.metaData;
                    if (bundle == null) {
                        i2 = 0;
                    } else {
                        i2 = bundle.getInt(this.METADATA_KEY);
                    }
                    WallpaperPackage wallpaperPackage = new WallpaperPackage(intent, str2, str3, loadLabel, i2);
                    if (wallpaperPackage.isAod() && !Features.isEnabled(Features.IS_AOD_ENABLED_SIMPLE)) {
                        Log.i(this.TAG, "aod not supported");
                    } else if (wallpaperPackage.isWatchFaceLarge() && !Features.isEnabled(Features.IS_WATCH_FACE_LARGE_ENABLED)) {
                        Log.i(this.TAG, "watch face large not supported");
                    } else if (wallpaperPackage.isCover() && (Features.isEnabled(Features.IS_WATCH_FACE_LARGE_ENABLED) || !Features.isEnabled(Features.IS_COVER_SCREEN_ENABLED))) {
                        Log.i(this.TAG, "cover not supported");
                    } else if (!wallpaperPackage.isClearCover() || supportClearCoverScreen(this.mMediaItems[0])) {
                        if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_80 && isDexMode && wallpaperPackage.isHomeOrLock()) {
                            if (SeApiCompat.isDexStandAloneMode(getContext())) {
                                if (!wallpaperPackage.isStandAloneDex()) {
                                    Log.i(this.TAG, "ignore not stand alone dex : " + str2);
                                }
                            } else if (!wallpaperPackage.isDualDex()) {
                                Log.i(this.TAG, "ignore not dual dex : " + str2);
                            }
                        }
                        this.mPackages.add(wallpaperPackage);
                    } else {
                        Log.i(this.TAG, "clearCover not supported");
                    }
                }
            } else {
                a.u("Signature is not matched. ", str2, this.TAG);
            }
        }
        this.mPackages.sort(Comparator.comparingInt(new C0545b(13)));
    }

    /* access modifiers changed from: private */
    public void onItemSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            Log.d(this.TAG, "selected data null");
        } else {
            executeTargetPackage(eventContext, (Integer) arrayList.get(0));
        }
    }

    private boolean supportClearCoverScreen(MediaItem mediaItem) {
        return SeApiCompat.isClearCoverAttached(getContext());
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER_CHOOSER.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        this.mMediaItems = mediaItemArr;
        if (mediaItemArr == null || mediaItemArr.length == 0 || mediaItemArr[0] == null) {
            Log.e(this.TAG, "Failed to execute -> item is null");
            return;
        }
        loadItems();
        if (this.mPackages.isEmpty()) {
            Log.e(this.TAG, "Failed to execute -> packages empty");
        } else if (this.mPackages.size() == 1) {
            executeTargetPackage(eventContext, 0);
        } else {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/ViewerListChooser").appendArg("list_chooser_title", (int) R.string.set_as_wallpaper).appendArg("list_chooser_items", getItemsArray()).build()).setOnDataCompleteListener(new K4.a(27, this)).start();
        }
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        loadAndExecute(eventContext, new c(13, this, eventContext));
    }
}
