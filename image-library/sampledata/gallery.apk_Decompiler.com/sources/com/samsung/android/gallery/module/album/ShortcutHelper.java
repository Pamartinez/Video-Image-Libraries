package com.samsung.android.gallery.module.album;

import A.a;
import A8.C0545b;
import B5.c;
import B8.e;
import B8.f;
import B8.g;
import B8.h;
import B8.i;
import B8.j;
import B8.k;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShortcutHelper {
    private static final ShortcutHelper sInstance = new ShortcutHelper();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum UseType {
        FOR_HOME_SCREEN,
        FOR_TASK_EDGE_ITEM,
        FOR_TASK_EDGE_ALBUM
    }

    private ShortcutHelper() {
    }

    private Intent buildHomeScreenShortcutIntent(MediaItem mediaItem) {
        Intent albumShortcutIntent = getAlbumShortcutIntent(mediaItem);
        albumShortcutIntent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        albumShortcutIntent.addCategory("android.intent.category.LAUNCHER");
        albumShortcutIntent.putExtra("android.intent.extra.shortcut.NAME", AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getTitle()));
        albumShortcutIntent.setData(Uri.parse(mediaItem.getPath()));
        return albumShortcutIntent;
    }

    private Intent buildIntent(MediaItem mediaItem, UseType useType) {
        if (useType == UseType.FOR_HOME_SCREEN) {
            return buildHomeScreenShortcutIntent(mediaItem);
        }
        return buildTaskEdgeShortcutIntent(mediaItem, useType);
    }

    private Intent buildTaskEdgeResultIntent(MediaItem mediaItem, Bitmap[] bitmapArr, Intent intent, UseType useType) {
        Bitmap bitmap;
        Intent intent2 = new Intent();
        intent2.putExtra("com.samsung.android.execute.extra.NAME", getTitle(mediaItem, useType));
        Bitmap bitmap2 = null;
        if (bitmapArr != null) {
            bitmap = bitmapArr[0];
        } else {
            bitmap = null;
        }
        intent2.putExtra("com.samsung.android.execute.extra.ICON", bitmap);
        if (bitmapArr != null) {
            bitmap2 = bitmapArr[1];
        }
        intent2.putExtra("com.samsung.android.execute.extra.SMALLICON", bitmap2);
        intent2.putExtra("com.samsung.android.execute.extra.INTENT", intent);
        return intent2;
    }

    private Intent buildTaskEdgeShortcutIntent(MediaItem mediaItem, UseType useType) {
        Intent intent;
        boolean isAlbumMediaItem = isAlbumMediaItem(mediaItem, useType);
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && mediaItem.isReadOnlyAlbum()) {
            isAlbumMediaItem = true;
        }
        if (isAlbumMediaItem) {
            intent = getAlbumShortcutIntent(mediaItem);
        } else {
            intent = getItemShortcutIntent(mediaItem);
        }
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        intent.addFlags(268468224);
        intent.addCategory("android.intent.category.DEFAULT");
        return intent;
    }

    private ShortcutInfo createPinShortcutInfo(Context context, MediaItem mediaItem, Bitmap bitmap, Intent intent) {
        int albumID = mediaItem.getAlbumID();
        return new ShortcutInfo.Builder(context, String.valueOf(albumID)).setShortLabel(AlbumTitleHelper.getAlbumTitle(albumID, mediaItem.getTitle())).setIcon(Icon.createWithBitmap(bitmap)).setIntent(intent).setDisabledMessage(context.getResources().getString(R$string.pinned_shortcut_disabled_msg)).build();
    }

    private void disableShortcut(Activity activity, int i2) {
        ShortcutManager shortcutManager = (ShortcutManager) activity.getSystemService(ShortcutManager.class);
        if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(String.valueOf(i2));
            shortcutManager.disableShortcuts(arrayList);
        }
    }

    private static Bitmap getAdjustedBitmap(MediaItem mediaItem, int i2, Bitmap bitmap) {
        int i7;
        Bitmap cropCenterByRatio = BitmapUtils.cropCenterByRatio(bitmap, 1.0f);
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            i7 = 0;
        } else {
            i7 = mediaItem.getOrientation();
        }
        Bitmap rotateBitmap = BitmapUtils.rotateBitmap(cropCenterByRatio, i7);
        if (rotateBitmap == null || i2 <= 0) {
            return rotateBitmap;
        }
        return Bitmap.createScaledBitmap(rotateBitmap, i2, i2, false);
    }

    private static Intent getAlbumShortcutIntent(MediaItem mediaItem) {
        boolean z;
        Intent intent = new Intent("com.android.gallery.action.SHORTCUT_ALBUM_VIEW");
        intent.putExtra("ALBUM_ID", mediaItem.getAlbumID());
        intent.putExtra("key-album-type", mediaItem.getAlbumType().toInt());
        intent.putExtra("key-show-album-info", mediaItem.isAlbumShowInfo());
        if (mediaItem.isCustomCover()) {
            intent.putExtra("key-is-custom-cover", mediaItem.isCustomCover());
            intent.putExtra("key-is-custom-cover-rect", MediaItemBuilder.getCoverRectInfo(mediaItem));
        }
        if (mediaItem.getVirtualAlbum() || mediaItem.isVirtualAlbum()) {
            z = true;
        } else {
            z = false;
        }
        intent.putExtra("IS_VIRTUAL_ALBUM", z);
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && mediaItem.isMergedAlbum()) {
            intent.putExtra("bucketIds", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, (List) Arrays.stream(mediaItem.getAlbumsInFolder()).mapToInt(new C0545b(2)).boxed().collect(Collectors.toList())));
        }
        return intent;
    }

    private int getBrokenDrawable(MediaItem mediaItem) {
        if (!mediaItem.isDrm()) {
            return R$drawable.gallery_ic_timeview_error;
        }
        if (mediaItem.isVideo()) {
            return R$drawable.gallery_ic_timeview_drm_video;
        }
        return R$drawable.gallery_ic_timeview_drm_image;
    }

    private Bitmap getDefaultBitmap(Context context, MediaItem mediaItem, UseType useType) {
        if (useType == UseType.FOR_HOME_SCREEN) {
            return BitmapUtils.getBitmapFromDrawable(context.getPackageManager().getApplicationIcon(context.getApplicationInfo()));
        }
        return ThumbnailLoader.getInstance().getReplacedThumbnail(context, getBrokenDrawable(mediaItem), R$color.cloud_only_image_bg);
    }

    public static ShortcutHelper getInstance() {
        return sInstance;
    }

    private static Intent getItemShortcutIntent(MediaItem mediaItem) {
        Intent intent = new Intent("com.android.gallery.action.SHORTCUT_ITEM_VIEW");
        intent.putExtra("view_item", true);
        intent.putExtra("from_shortcut", true);
        intent.setDataAndType(ContentUri.getUri(mediaItem), mediaItem.getMimeType());
        return intent;
    }

    private Bitmap[] getTaskEdgeIcons(Context context, MediaItem mediaItem, Bitmap bitmap, UseType useType) {
        Bitmap bitmap2;
        Context context2;
        if (context == null) {
            return null;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.gallery_main_edge_icon_size);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.gallery_sub_edge_icon_size);
        if (bitmap == null) {
            bitmap2 = Bitmap.createScaledBitmap(ThumbnailLoader.getInstance().getReplacedThumbnail(context, getBrokenDrawable(mediaItem), R$color.cloud_only_image_bg), dimensionPixelSize, dimensionPixelSize, false);
            context2 = context;
        } else {
            context2 = context;
            bitmap2 = getShortcutBitmap(context2, mediaItem, bitmap, dimensionPixelSize, useType);
        }
        return new Bitmap[]{bitmap2, Bitmap.createScaledBitmap(BitmapUtils.getBitmapFromDrawable(context2.getPackageManager().getApplicationIcon(context2.getApplicationInfo())), dimensionPixelSize2, dimensionPixelSize2, false)};
    }

    private String getTitle(MediaItem mediaItem, UseType useType) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (mediaItem.isVirtualAlbum()) {
                return AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getTitle());
            }
            if (mediaItem.isAutoAlbum()) {
                return mediaItem.getTitle();
            }
        }
        if (!isAlbumMediaItem(mediaItem, useType)) {
            return mediaItem.getTitle();
        }
        String directoryFromPath = FileUtils.getDirectoryFromPath(mediaItem.getReferencePath(), false);
        return AlbumTitleHelper.getAlbumTitle(FileUtils.getBucketId(directoryFromPath), FileUtils.getNameFromPath(directoryFromPath));
    }

    private boolean isAlbumMediaItem(MediaItem mediaItem, UseType useType) {
        if (!UseType.FOR_TASK_EDGE_ALBUM.equals(useType) || mediaItem.getAlbumID() == 0 || mediaItem.getCount() <= 0 || mediaItem.getGroupMediaId() != 0 || mediaItem.isBurstShot()) {
            return false;
        }
        return true;
    }

    private boolean isHomeShortcutAlbum(LaunchIntent launchIntent) {
        return launchIntent.isCategoryLauncher();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleResultCanceled$9(Blackboard blackboard, boolean z) {
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        if (readActivity == null) {
            Log.e("ShortcutHelper", "handleResultCanceled(" + z + ") failed (null activity)");
            return;
        }
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent != null) {
            showErrorToast(z, launchIntent, readActivity);
            readActivity.setResult(0, new Intent("android.intent.action.DELETE"));
            readActivity.finish();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHomeScreenShortcut$1(Context context, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new i(this, context, mediaItem2, bitmap2, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setTaskEdgeShortcut$3(MediaItem mediaItem, UseType useType, Activity activity, Bitmap bitmap) {
        activity.setResult(-1, buildTaskEdgeResultIntent(mediaItem, getTaskEdgeIcons(activity, mediaItem, bitmap, useType), buildIntent(mediaItem, useType), useType));
        activity.finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setTaskEdgeShortcut$4(MediaItem mediaItem, UseType useType, Activity activity, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        Activity activity2 = activity;
        UseType useType2 = useType;
        ThreadUtil.postOnUiThread(new c(this, mediaItem, useType2, activity2, bitmap2, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHomeScreenShortcut$6(Context context, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new i(this, context, mediaItem2, bitmap2, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToShortcutManager$8(MediaItem mediaItem, List list, Context context, Bitmap bitmap, ShortcutInfo shortcutInfo) {
        Log.d("ShortcutHelper", "Album is pinned on HomeScreen as Shortcut.");
        shortcutInfo.getIntent().putExtra("key-show-album-info", mediaItem.isAlbumShowInfo());
        list.add(createPinShortcutInfo(context, mediaItem, bitmap, shortcutInfo.getIntent()));
    }

    /* access modifiers changed from: private */
    public static boolean needToUpdateShortcut(MediaItem mediaItem, ShortcutInfo shortcutInfo) {
        Intent intent = shortcutInfo.getIntent();
        if (intent == null || mediaItem.getTitle() == null || mediaItem.getAlbumID() != intent.getIntExtra("ALBUM_ID", 0) || mediaItem.getAlbumType().toInt() != intent.getIntExtra("key-album-type", 0) || ((!AlbumType.isAutoAlbum(mediaItem.getAlbumType()) && !AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getTitle()).equals(intent.getStringExtra("android.intent.extra.shortcut.NAME"))) || shortcutInfo.isDeclaredInManifest())) {
            return false;
        }
        return true;
    }

    private void setHomeScreenShortcut(Context context, MediaItem mediaItem) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new h(this, context, mediaItem, 0));
    }

    private void showErrorToast(boolean z, LaunchIntent launchIntent, Activity activity) {
        if (!z && isHomeShortcutAlbum(launchIntent)) {
            Toast.makeText(activity, R$string.shortcut_destination_not_available, 0).show();
            disableShortcut(activity, launchIntent.getShortcutAlbumId());
        } else if (launchIntent.isQuickShortcut()) {
            Toast.makeText(activity, R$string.shortcut_destination_not_available, 0).show();
        } else if (z) {
            Toast.makeText(activity, R$string.album_is_hidden_and_cannot_be_opened, 0).show();
        } else if (launchIntent.isViewItem()) {
            Toast.makeText(activity, R$string.no_such_item, 0).show();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getShortcutAlbumTarget(com.samsung.android.gallery.module.abstraction.LaunchIntent r6, int r7, int r8) {
        /*
            r5 = this;
            r5 = 0
            if (r6 != 0) goto L_0x0004
            return r5
        L_0x0004:
            boolean r0 = r6.getVirtualAlbum()
            r1 = 1
            java.lang.String r2 = "shortcut_album"
            java.lang.String r3 = "id"
            r4 = 0
            if (r0 == 0) goto L_0x0063
            int r0 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.video
            if (r7 != r0) goto L_0x0018
            java.lang.String r5 = "location://virtual/album/video/fileList"
            goto L_0x0065
        L_0x0018:
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi5x.MX_ALBUMS
            if (r0 == 0) goto L_0x0029
            boolean r0 = com.samsung.android.gallery.support.utils.BucketUtils.isRecent(r7)
            if (r0 != 0) goto L_0x0063
            boolean r0 = com.samsung.android.gallery.support.utils.BucketUtils.isFavourite(r7)
            if (r0 == 0) goto L_0x0029
            goto L_0x0063
        L_0x0029:
            int r0 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.favorite
            if (r7 != r0) goto L_0x0030
            java.lang.String r5 = "location://virtual/album/favorite/fileList"
            goto L_0x0065
        L_0x0030:
            int r0 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.recent
            if (r7 != r0) goto L_0x0037
            java.lang.String r5 = "location://virtual/album/recently/fileList"
            goto L_0x0065
        L_0x0037:
            int r0 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.repair
            if (r7 != r0) goto L_0x0063
            android.os.Bundle r8 = r6.getExtra()
            if (r8 == 0) goto L_0x004b
            android.os.Bundle r6 = r6.getExtra()
            java.lang.String r8 = "repair-data-type"
            java.lang.String r5 = r6.getString(r8, r5)
        L_0x004b:
            com.samsung.android.gallery.support.utils.UriBuilder r6 = new com.samsung.android.gallery.support.utils.UriBuilder
            java.lang.String r8 = "location://virtual/album/repair/fileList"
            r6.<init>(r8)
            com.samsung.android.gallery.support.utils.UriBuilder r6 = r6.appendArg((java.lang.String) r3, (int) r7)
            if (r5 != 0) goto L_0x0059
            goto L_0x005a
        L_0x0059:
            r1 = r4
        L_0x005a:
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r6.appendArg((java.lang.String) r2, (boolean) r1)
            java.lang.String r5 = r5.build()
            return r5
        L_0x0063:
            java.lang.String r5 = "location://albums/fileList"
        L_0x0065:
            com.samsung.android.gallery.support.utils.UriBuilder r0 = new com.samsung.android.gallery.support.utils.UriBuilder
            r0.<init>(r5)
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r0.appendArg((java.lang.String) r3, (int) r7)
            java.lang.String r0 = "type"
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r5.appendArg((java.lang.String) r0, (int) r8)
            java.lang.String r8 = "ids"
            java.lang.String r0 = r6.getAlbumBucketIds()
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r5.appendArg((java.lang.String) r8, (java.lang.String) r0)
            java.lang.String r8 = "show_album_info"
            boolean r0 = r6.isShowAlbumInfo()
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r5.appendArg((java.lang.String) r8, (boolean) r0)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.String r0 = "filterStorageType"
            java.lang.Object r6 = r6.getExtra(r0, r8)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r5.appendArg((java.lang.String) r0, (int) r6)
            com.samsung.android.gallery.support.utils.UriBuilder r5 = r5.appendArg((java.lang.String) r2, (boolean) r1)
            int r6 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.favorite
            if (r7 != r6) goto L_0x00ac
            java.lang.String r6 = "with_group"
            r5.appendArg((java.lang.String) r6, (boolean) r4)
        L_0x00ac:
            java.lang.String r5 = r5.build()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.ShortcutHelper.getShortcutAlbumTarget(com.samsung.android.gallery.module.abstraction.LaunchIntent, int, int):java.lang.String");
    }

    public Bitmap getShortcutBitmap(Context context, MediaItem mediaItem, Bitmap bitmap, int i2, UseType useType) {
        Bitmap resizeAndCropCenter;
        Bitmap bitmap2 = null;
        if (bitmap != null) {
            if (mediaItem != null) {
                if (!(!isAlbumMediaItem(mediaItem, useType) || mediaItem.getAlbumCover() == null || mediaItem.getCropRect() == null)) {
                    bitmap2 = BitmapUtils.crop(bitmap, RectUtils.getSmartCropRect(bitmap, mediaItem.getCropRect()));
                }
                if (bitmap2 != null) {
                    bitmap = bitmap2;
                }
                resizeAndCropCenter = getAdjustedBitmap(mediaItem, i2, bitmap);
            } else {
                resizeAndCropCenter = BitmapUtils.resizeAndCropCenter(bitmap, ThumbKind.MEDIUM_KIND_SIZE);
            }
            bitmap2 = resizeAndCropCenter;
            Log.d("ShortcutHelper", "shortcutBitmap is set thumbnail");
        }
        if (bitmap2 != null) {
            return bitmap2;
        }
        Bitmap defaultBitmap = getDefaultBitmap(context, mediaItem, useType);
        if (i2 > 0) {
            defaultBitmap = Bitmap.createScaledBitmap(defaultBitmap, i2, i2, false);
        }
        Log.d("ShortcutHelper", "shortcutBitmap is set default");
        return defaultBitmap;
    }

    public void handleResultCanceled(Blackboard blackboard) {
        handleResultCanceled(blackboard, false);
    }

    public void setShortcut(Activity activity, MediaItem mediaItem, UseType useType) {
        if (useType == UseType.FOR_HOME_SCREEN) {
            setHomeScreenShortcut(activity, mediaItem);
        } else {
            setTaskEdgeShortcut(activity, mediaItem, useType);
        }
    }

    public void setTaskEdgeShortcut(Activity activity, MediaItem mediaItem, UseType useType) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.SMALL_CROP_KIND;
        Objects.requireNonNull(mediaItem);
        MediaItem mediaItem2 = mediaItem;
        instance.loadThumbnail(mediaItem2, thumbKind, new e(mediaItem, 0), new f((Object) this, (Object) mediaItem2, (Object) useType, (Object) activity, 0));
    }

    /* renamed from: setToShortcutManager */
    public void lambda$setHomeScreenShortcut$0(Context context, MediaItem mediaItem, Bitmap bitmap) {
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
            UseType useType = UseType.FOR_HOME_SCREEN;
            Context context2 = context;
            MediaItem mediaItem2 = mediaItem;
            ShortcutInfo createPinShortcutInfo = createPinShortcutInfo(context2, mediaItem2, getShortcutBitmap(context2, mediaItem2, bitmap, -1, useType), buildIntent(mediaItem, useType));
            try {
                shortcutManager.requestPinShortcut(createPinShortcutInfo, AndroidCompat.createBroadcastPendingIntent(context2, 0, shortcutManager.createShortcutResultIntent(createPinShortcutInfo), 0).getIntentSender());
                Log.d("ShortcutHelper", "set shortcut as pinned for album");
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
                shortcutManager.updateShortcuts(Collections.singletonList(createPinShortcutInfo));
                shortcutManager.enableShortcuts(Collections.singletonList(createPinShortcutInfo.getId()));
                Log.e((CharSequence) "ShortcutHelper", "set shortcut failed", (Throwable) illegalArgumentException);
            } catch (Exception e7) {
                a.s(e7, new StringBuilder("set shortcut failed. e="), "ShortcutHelper");
            }
        }
    }

    public void updateHomeScreenShortcut(Context context, MediaItem mediaItem) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new h(this, context, mediaItem, 1));
    }

    /* renamed from: updateToShortcutManager */
    public void lambda$updateHomeScreenShortcut$5(Context context, MediaItem mediaItem, Bitmap bitmap) {
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager != null) {
            List<ShortcutInfo> pinnedShortcuts = shortcutManager.getPinnedShortcuts();
            ArrayList arrayList = new ArrayList();
            Bitmap shortcutBitmap = getShortcutBitmap(context, mediaItem, bitmap, -1, UseType.FOR_HOME_SCREEN);
            ArrayList arrayList2 = arrayList;
            pinnedShortcuts.stream().filter(new j(mediaItem, 0)).forEach(new k(this, mediaItem, arrayList2, context, shortcutBitmap, 0));
            try {
                shortcutManager.updateShortcuts(arrayList2);
                Log.d("ShortcutHelper", "updateHomeScreenShortcut is finished.");
            } catch (IllegalArgumentException e) {
                Log.e("ShortcutHelper", "updateHomeScreenShortcut fail : " + e);
            }
        }
    }

    public void handleResultCanceled(Blackboard blackboard, boolean z) {
        ThreadUtil.postOnUiThread(new g((Object) this, (Object) blackboard, z, 0));
    }
}
