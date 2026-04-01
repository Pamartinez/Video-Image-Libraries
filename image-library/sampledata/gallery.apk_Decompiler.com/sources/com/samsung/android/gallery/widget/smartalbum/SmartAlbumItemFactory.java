package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartAlbumItemFactory {
    private int mPosition = 0;

    private SmartAlbumItem createItem(Context context, int i2, View.OnClickListener onClickListener) {
        SmartAlbumItem itemByTag = getItemByTag(context, i2);
        itemByTag.setOnClickListener(onClickListener);
        int i7 = this.mPosition;
        this.mPosition = i7 + 1;
        itemByTag.setPosition(i7);
        if (itemByTag.usingFixedImageAndTitle()) {
            Resources resources = context.getResources();
            itemByTag.setImageDrawable(resources.getDrawable(itemByTag.getDrawableId(), (Resources.Theme) null));
            itemByTag.setTitle(resources.getString(itemByTag.getTitleStringId()));
            itemByTag.setContentDescription(resources.getString(itemByTag.getTitleStringId()));
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        itemByTag.setLayoutParams(layoutParams);
        return itemByTag;
    }

    private SmartAlbumItem getItemByTag(Context context, int i2) {
        if (i2 == 0) {
            return new SmartAlbumVideoItem(context);
        }
        if (i2 == 1) {
            return new SmartAlbumFavoriteItem(context);
        }
        if (i2 == 2) {
            return new SmartAlbumRecentlyItem(context);
        }
        if (i2 == 3) {
            return new SmartAlbumSuggestionItem(context);
        }
        throw new AssertionError(C0086a.i(i2, "wrong item "));
    }

    public void createItems(LinearLayout linearLayout, View.OnClickListener onClickListener) {
        Context context = linearLayout.getContext();
        linearLayout.addView(createItem(context, 0, onClickListener));
        linearLayout.addView(createItem(context, 1, onClickListener));
        linearLayout.addView(createItem(context, 2, onClickListener));
        linearLayout.addView(createItem(context, 3, onClickListener));
    }
}
