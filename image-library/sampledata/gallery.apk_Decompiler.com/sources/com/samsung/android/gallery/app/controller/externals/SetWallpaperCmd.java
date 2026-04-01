package com.samsung.android.gallery.app.controller.externals;

import A.a;
import N2.j;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.net.Uri;
import android.os.Parcelable;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetWallpaperCmd extends BaseCommand {
    private int mWallPaperType;

    private Intent createBothWallpaper(Uri uri) {
        return createWallpaper(new ComponentName("com.sec.android.wallpapercropper2", "com.sec.android.wallpapercropper2.BothCropActivity"), uri);
    }

    private Intent createBothWallpaperForDex(Uri uri) {
        return createWallpaper(new ComponentName("com.sec.android.app.desktoplauncher", "com.sec.android.app.wallpaperchooser.WallpaperPreviewBothActivity"), uri);
    }

    private Intent createHomeWallpaper(Uri uri) {
        return createWallpaper(new ComponentName("com.sec.android.wallpapercropper2", "com.sec.android.wallpapercropper2.HomeCropActivity"), uri);
    }

    private Intent createHomeWallpaperForDex(Uri uri) {
        return createWallpaper(new ComponentName("com.sec.android.app.desktoplauncher", "com.sec.android.app.wallpaperchooser.WallpaperPreviewHomeActivity"), uri);
    }

    private Intent createIntent() {
        if (!SdkConfig.atLeast(SdkConfig.SEM.R_MR5) || !shouldUseDexIntent()) {
            return SdkConfig.atLeast(SdkConfig.SEM.Q_MR5) ? new Intent("android.intent.action.SET_WALLPAPER") : new Intent("com.samsung.android.app.dressroom.action.SET_AS_WALLPAPER");
        }
        return new Intent("com.sec.android.app.desktoplauncher.SET_WALLPAPER");
    }

    private Intent createLockWallpaper(Uri uri) {
        return createWallpaper(new ComponentName("com.sec.android.wallpapercropper2", "com.sec.android.wallpapercropper2.KeyguardCropActivity"), uri);
    }

    private Intent createLockWallpaperForDex(Uri uri) {
        return createWallpaper(new ComponentName("com.sec.android.app.desktoplauncher", "com.sec.android.app.wallpaperchooser.WallpaperPreviewLockActivity"), uri);
    }

    private Intent createSetAsWallpaperIntent(Context context, Uri uri) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LabeledIntent(createHomeWallpaper(uri), "com.sec.android.gallery3d", 0, 0));
        arrayList.add(new LabeledIntent(createLockWallpaper(uri), "com.sec.android.gallery3d", 0, 0));
        arrayList.add(new LabeledIntent(createBothWallpaper(uri), "com.sec.android.gallery3d", 0, 0));
        if (!isFromThemeStore()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Intent) it.next()).addFlags(268435456);
            }
        }
        Intent createChooser = Intent.createChooser(new Intent(), context.getString(R.string.set_as_wallpaper));
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        return createChooser;
    }

    private Intent createSetAsWallpaperIntentForDex(Context context, Uri uri) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LabeledIntent(createHomeWallpaperForDex(uri), "com.sec.android.gallery3d", 0, 0));
        arrayList.add(new LabeledIntent(createLockWallpaperForDex(uri), "com.sec.android.gallery3d", 0, 0));
        arrayList.add(new LabeledIntent(createBothWallpaperForDex(uri), "com.sec.android.gallery3d", 0, 0));
        Intent createChooser = Intent.createChooser(new Intent(), context.getString(R.string.set_as_samsung_dex_wallpaper));
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        return createChooser;
    }

    private Intent createWallpaper(ComponentName componentName, Uri uri) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(componentName);
        intent.putExtra("type", "gallery_image");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(1);
        setIntentWithCommonExtra(intent);
        return intent;
    }

    private ArrayList<Uri> getUriList(MediaItem[] mediaItemArr) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (MediaItem uri : mediaItemArr) {
            Uri uri2 = ContentUri.getUri(uri);
            if (uri2 != null) {
                arrayList.add(uri2);
            }
            if (arrayList.size() >= 100) {
                break;
            }
        }
        return arrayList;
    }

    private boolean isFromThemeStore() {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isFromThemeStore()) {
            return false;
        }
        return true;
    }

    private boolean isVideoWallpaper(MediaItem mediaItem) {
        return mediaItem.isVideo();
    }

    private boolean shouldUseDexIntent() {
        if (!isDexMode()) {
            return false;
        }
        if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !SeApiCompat.isDexStandAloneMode(getContext())) {
            return true;
        }
        return false;
    }

    private void startImageWallpaper(MediaItem mediaItem) {
        try {
            Activity activity = getActivity();
            Intent createIntent = createIntent(activity, ContentUri.getUri(mediaItem));
            if (isFromThemeStore()) {
                activity.startActivityForResult(createIntent, 2309);
            } else {
                activity.startActivity(createIntent);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("startImageWallpaper failed e="), this.TAG);
        }
    }

    private void startVideoWallpaper(MediaItem mediaItem) {
        String str = "video";
        try {
            Intent intent = new Intent("com.samsung.intent.action.SHOW_VIDEO_WALLPAPER_PREVIEW");
            if (mediaItem.getMimeType() == null || !mediaItem.getMimeType().contains(str)) {
                str = "gif";
            }
            intent.putExtra("type", str);
            intent.putExtra(FileApiContract.Parameter.PATH, mediaItem.getPath());
            setIntentWithCommonExtra(intent);
            if (isFromThemeStore()) {
                getActivity().startActivityForResult(intent, 2309);
            } else {
                getActivity().startActivity(intent);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("startVideoWallpaper failed e="), this.TAG);
        }
    }

    private void startWallpaperChooser(MediaItem mediaItem) {
        if (isVideoWallpaper(mediaItem)) {
            startVideoWallpaper(mediaItem);
        } else {
            startImageWallpaper(mediaItem);
        }
    }

    private void startWallpaperChooserCompatQ(MediaItem mediaItem, int i2) {
        Intent createIntent = createIntent();
        if (!PocFeatures.DUAL_PHOTO_PREVIEW || !mediaItem.isStream()) {
            createIntent.setDataAndType(ContentUri.getUri(mediaItem), mediaItem.getMimeType());
            createIntent.putExtra(FileApiContract.Parameter.PATH, mediaItem.getPath());
        } else {
            createIntent.setDataAndType(ContentUri.getStreamUri(getContext(), mediaItem), mediaItem.getMimeType());
        }
        createIntent.putExtra("from-ThemeStore", isFromThemeStore());
        if (i2 > 0) {
            createIntent.putExtra("which", i2);
            createIntent.putExtra("hide_chooser", true);
        }
        setIntentWithCommonExtra(createIntent);
        createIntent.addFlags(1);
        try {
            if (isFromThemeStore()) {
                getActivity().startActivityForResult(createIntent, 2309);
            } else {
                getActivity().startActivity(createIntent);
            }
        } catch (Exception e) {
            j.s(e, new StringBuilder("startWallpaperChooserCompat failed e="), this.TAG);
        }
    }

    private void startWallpaperMultiPack(MediaItem[] mediaItemArr) {
        Intent intent = new Intent("com.samsung.android.app.dressroom.action.SET_AS_WALLPAPER");
        intent.putExtra("selectedItems", getUriList(mediaItemArr));
        intent.addFlags(1);
        try {
            getActivity().startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startWallpaperMultiPack failed e="), this.TAG);
        }
    }

    public String getAnalyticsDetail() {
        int i2 = this.mWallPaperType;
        if (i2 == 1) {
            return AnalyticsDetail.EVENT_DETAIL_SET_AS_HOME_SCREEN.toString();
        }
        if (i2 == 2) {
            return AnalyticsDetail.EVENT_DETAIL_SET_AS_LOCK_SCREEN.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_SET_AS_LOCK_AND_HOME.toString();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER.toString();
    }

    /* JADX WARNING: type inference failed for: r8v13, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecute(com.samsung.android.gallery.app.controller.EventContext r8, java.lang.Object... r9) {
        /*
            r7 = this;
            boolean r8 = r7.isUpsm()
            if (r8 == 0) goto L_0x000d
            r8 = 2131886519(0x7f1201b7, float:1.940762E38)
            r7.showToast((int) r8)
            return
        L_0x000d:
            r8 = 0
            r0 = r9[r8]
            boolean r1 = r0 instanceof com.samsung.android.gallery.module.data.MediaItem[]
            r2 = 2131888940(0x7f120b2c, float:1.941253E38)
            if (r1 == 0) goto L_0x007f
            com.samsung.android.gallery.module.data.MediaItem[] r0 = (com.samsung.android.gallery.module.data.MediaItem[]) r0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r3 = r0.length
            r4 = r8
        L_0x0025:
            if (r4 >= r3) goto L_0x003b
            r5 = r0[r4]
            if (r5 == 0) goto L_0x0038
            boolean r6 = r5.isPostProcessing()
            if (r6 == 0) goto L_0x0035
            r9.add(r5)
            goto L_0x0038
        L_0x0035:
            r1.add(r5)
        L_0x0038:
            int r4 = r4 + 1
            goto L_0x0025
        L_0x003b:
            boolean r3 = com.samsung.android.gallery.module.camera.GppmHelper.SUPPORT_DONATION_MULTIPLE
            if (r3 == 0) goto L_0x0048
            int r3 = r9.size()
            if (r3 <= 0) goto L_0x0048
            com.samsung.android.gallery.module.camera.GppmHelper.donate((java.util.Collection<com.samsung.android.gallery.database.dbtype.FileItemInterface>) r9)
        L_0x0048:
            int r3 = r1.size()
            if (r3 != 0) goto L_0x006b
            r7.showToast((int) r2)
            java.lang.String r7 = r7.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "set-as-wallpaper skip with ppp "
            r8.<init>(r1)
            java.lang.String r1 = "/"
            c0.C0086a.A(r8, r9, r1)
            int r9 = r0.length
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.samsung.android.gallery.support.utils.Log.majorEvent(r7, r8)
            return
        L_0x006b:
            int r9 = r0.length
            int r2 = r1.size()
            if (r9 == r2) goto L_0x007b
            com.samsung.android.gallery.module.data.MediaItem[] r8 = new com.samsung.android.gallery.module.data.MediaItem[r8]
            java.lang.Object[] r8 = r1.toArray(r8)
            r0 = r8
            com.samsung.android.gallery.module.data.MediaItem[] r0 = (com.samsung.android.gallery.module.data.MediaItem[]) r0
        L_0x007b:
            r7.startWallpaperMultiPack(r0)
            return
        L_0x007f:
            com.samsung.android.gallery.module.data.MediaItem r0 = (com.samsung.android.gallery.module.data.MediaItem) r0
            if (r0 != 0) goto L_0x008b
            java.lang.String r7 = r7.TAG
            java.lang.String r8 = "item is null"
            com.samsung.android.gallery.support.utils.Log.e(r7, r8)
            return
        L_0x008b:
            boolean r1 = r0.isPostProcessing()
            if (r1 == 0) goto L_0x009c
            r7.showToast((int) r2)
            java.lang.String r7 = r7.TAG
            java.lang.String r8 = "set-as-wallpaper skip with ppp 1"
            com.samsung.android.gallery.support.utils.Log.majorEvent(r7, r8)
            return
        L_0x009c:
            int r1 = r9.length
            r2 = 1
            if (r1 <= r2) goto L_0x00a8
            r8 = r9[r2]
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
        L_0x00a8:
            r7.mWallPaperType = r8
            com.samsung.android.gallery.support.config.SdkConfig$GED r8 = com.samsung.android.gallery.support.config.SdkConfig.GED.Q
            boolean r8 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r8)
            if (r8 == 0) goto L_0x00b8
            int r8 = r7.mWallPaperType
            r7.startWallpaperChooserCompatQ(r0, r8)
            return
        L_0x00b8:
            r7.startWallpaperChooser(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.SetWallpaperCmd.onExecute(com.samsung.android.gallery.app.controller.EventContext, java.lang.Object[]):void");
    }

    private Intent createIntent(Context context, Uri uri) {
        if (shouldUseDexIntent()) {
            Log.d(this.TAG, "createIntent(Dex)");
            return createSetAsWallpaperIntentForDex(context, uri);
        } else if (isKnox()) {
            Log.d(this.TAG, "createIntent(Knox)");
            return createHomeWallpaper(uri);
        } else {
            Log.d(this.TAG, "createIntent(Normal)");
            return createSetAsWallpaperIntent(context, uri);
        }
    }
}
