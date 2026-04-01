package com.samsung.android.gallery.widget.dragdrop.clipdata;

import A8.C0545b;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexShortcutIntent {
    private static final boolean IS_TABLET_BY_SYSTEM = Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);

    private void addExtrasForAlbum(Intent intent, MediaItem mediaItem) {
        intent.putExtra("android.intent.extra.shortcut.INTENT", getExtraPendingIntentForAlbum(mediaItem));
        intent.putExtra("AbsolutePath", FileUtils.getDirectoryFromPath(mediaItem.getReferencePath(), false));
        intent.putExtra("android.intent.extra.shortcut.NAME", AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getTitle()));
    }

    private void addExtrasForItem(Intent intent, MediaItem mediaItem) {
        intent.putExtra("android.intent.extra.shortcut.INTENT", getExtraPendingIntentForItem(mediaItem));
        intent.putExtra("AbsolutePath", mediaItem.getPath());
        intent.putExtra("android.intent.extra.shortcut.NAME", mediaItem.getTitle());
    }

    private Intent createIntentWithCommonExtras(Context context, MediaItem mediaItem) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("DragAndDropBinding", "DragAndDropBinding");
        intent.putExtra("itemType", 1);
        intent.putExtra("android.intent.extra.shortcut.ICON", getShortcutBitmap(context, mediaItem));
        return intent;
    }

    private Intent getExtraPendingIntentForAlbum(MediaItem mediaItem) {
        Intent intent = new Intent("com.android.gallery.action.SHORTCUT_ALBUM_VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.putExtra("ALBUM_ID", mediaItem.getAlbumID());
        intent.setData(Uri.parse(FileUtils.getDirectoryFromPath(mediaItem.getPath(), false)));
        intent.putExtra("ALBUM_ID", mediaItem.getAlbumID());
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && mediaItem.isMergedAlbum()) {
            intent.putExtra("bucketIds", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, (List) Arrays.stream(mediaItem.getAlbumsInFolder()).mapToInt(new C0545b(2)).boxed().collect(Collectors.toList())));
        }
        intent.putExtra("key-album-type", mediaItem.getAlbumType().toInt());
        intent.putExtra("IS_VIRTUAL_ALBUM", mediaItem.getVirtualAlbum());
        return intent;
    }

    private Intent getExtraPendingIntentForItem(MediaItem mediaItem) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity"));
        intent.addFlags(335544320);
        intent.setDataAndType(ContentUri.getUri(mediaItem), mediaItem.getMimeType());
        intent.addFlags(1);
        return intent;
    }

    private Bitmap getShortcutBitmap(Context context, MediaItem mediaItem) {
        Resources resources;
        int i2;
        if (IS_TABLET_BY_SYSTEM) {
            resources = context.getResources();
            i2 = R$dimen.add_shortcut_thumb_size_tablet;
        } else {
            resources = context.getResources();
            i2 = R$dimen.add_shortcut_thumb_size;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i2);
        return ShortcutHelper.getInstance().getShortcutBitmap(context, mediaItem, ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.FREE_KIND), dimensionPixelSize, ShortcutHelper.UseType.FOR_HOME_SCREEN);
    }

    public Intent getForAlbum(Context context, MediaItem mediaItem) {
        Intent createIntentWithCommonExtras = createIntentWithCommonExtras(context, mediaItem);
        addExtrasForAlbum(createIntentWithCommonExtras, mediaItem);
        return createIntentWithCommonExtras;
    }

    public Intent getForItem(Context context, MediaItem mediaItem) {
        Intent createIntentWithCommonExtras = createIntentWithCommonExtras(context, mediaItem);
        addExtrasForItem(createIntentWithCommonExtras, mediaItem);
        return createIntentWithCommonExtras;
    }
}
