package com.samsung.android.gallery.app.ui.list.dragdrop;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreator;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorDexImpl;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorImpl;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorLocalImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClipDataCreatorFactory {
    public static ClipDataCreator create(DnDMode dnDMode, String str, MediaItem[] mediaItemArr) {
        if (dnDMode.isNormal()) {
            return new ClipDataCreatorLocalImpl();
        }
        if (!dnDMode.isDex() || dnDMode.isDexOnPC()) {
            return new ClipDataCreatorImpl(mediaItemArr, LocationKey.isAlbumPictures(str));
        }
        return new ClipDataCreatorDexImpl(mediaItemArr);
    }

    public static ClipDataCreator createForAlbum(DnDMode dnDMode, MediaItem[] mediaItemArr, MediaItem[] mediaItemArr2) {
        if (dnDMode.isNormal()) {
            return new ClipDataCreatorLocalImpl();
        }
        if (!dnDMode.isDex() || dnDMode.isDexOnPC()) {
            return new ClipDataCreatorImpl(mediaItemArr);
        }
        return new ClipDataCreatorDexImpl(mediaItemArr, mediaItemArr2);
    }
}
