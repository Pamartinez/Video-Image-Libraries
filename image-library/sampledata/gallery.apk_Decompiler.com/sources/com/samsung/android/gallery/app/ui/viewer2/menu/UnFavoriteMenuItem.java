package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnFavoriteMenuItem extends FavoriteMenuItem {
    public UnFavoriteMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remove_from_favorites);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isFavourite()) {
            return false;
        }
        return true;
    }

    public void setMenuAttribute() {
        int i2;
        ViewerMenuItem favoriteCommonAttribute = setFavoriteCommonAttribute();
        if (isFlipCoverTheme()) {
            i2 = R.drawable.gallery_ic_flip_cover_like_on;
        } else {
            i2 = R.drawable.gallery_ic_detail_favorite_on;
        }
        favoriteCommonAttribute.setIconResId(i2).validate(new l(15));
    }

    public boolean supportAnimatedDrawable() {
        return false;
    }
}
