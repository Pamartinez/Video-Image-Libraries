package com.samsung.android.gallery.module.media;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GifAnimationFactory {
    private static GifAnimation createAnimation(FileItemInterface fileItemInterface) {
        if (fileItemInterface.isWebp()) {
            return new GifAnimationDrawable();
        }
        return createGifAnimation(fileItemInterface);
    }

    private static GifAnimation createGifAnimation(FileItemInterface fileItemInterface) {
        return fileItemInterface.getSefFileType() == 2864 ? new GifStickerAnimationDrawable() : createGifAnimation();
    }

    public static GifAnimation getDecodedAnimation(Context context, FileItemInterface fileItemInterface) {
        GifAnimation createAnimation = createAnimation(fileItemInterface);
        createAnimation.decode(context, fileItemInterface);
        return createAnimation;
    }

    public static GifAnimation getDecodedGifAnimation(Context context, FileItemInterface fileItemInterface) {
        GifAnimation createGifAnimation = createGifAnimation(fileItemInterface);
        createGifAnimation.decode(context, fileItemInterface);
        return createGifAnimation;
    }

    public static GifAnimation getDecodedRemasterAnimation(Context context, FileItemInterface fileItemInterface) {
        GifAnimation createGifAnimation = createGifAnimation();
        createGifAnimation.decode(context, fileItemInterface);
        return createGifAnimation;
    }

    private static GifAnimation createGifAnimation() {
        return Features.isEnabled(Features.IS_GED) ? new GifAnimationDrawable() : new GifAnimationQuram();
    }
}
