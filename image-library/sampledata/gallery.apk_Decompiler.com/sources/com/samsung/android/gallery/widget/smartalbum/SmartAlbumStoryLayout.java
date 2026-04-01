package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.gallery.module.story.StoryAppBarManager;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartAlbumStoryLayout extends SmartAlbumLayout {
    public SmartAlbumStoryLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SmartAlbumItemFactory createItemFactory() {
        return new SmartAlbumItemFactory() {
            public void createItems(LinearLayout linearLayout, View.OnClickListener onClickListener) {
                Context context = linearLayout.getContext();
                if (context != null && linearLayout.getChildCount() == 0) {
                    for (int i2 = 0; i2 < 4; i2++) {
                        SmartAlbumStoryItem smartAlbumStoryItem = new SmartAlbumStoryItem(context);
                        smartAlbumStoryItem.setOnClickListener(onClickListener);
                        smartAlbumStoryItem.setPosition(i2);
                        linearLayout.addView(smartAlbumStoryItem);
                    }
                }
            }
        };
    }

    public int getLayoutId() {
        return R$layout.smart_memory_layout;
    }

    public int getOneItemMinWidth() {
        return getResources().getDimensionPixelOffset(R$dimen.story_appbar_item_title_width);
    }

    public int getStartEndMargin() {
        return getResources().getDimensionPixelOffset(R$dimen.story_appbar_layout_start_end_margin);
    }

    public boolean hasEnoughSpace() {
        int i2;
        if (ResourceCompat.isLandscape((View) this)) {
            i2 = 4;
        } else {
            i2 = 3;
        }
        if (ResourceCompat.getWindowWidth(getContext()) >= (getOneItemMinWidth() * i2) + (getStartEndMargin() * 2)) {
            return true;
        }
        return false;
    }

    public boolean isSmartAlbumTitleAvailable() {
        return false;
    }

    public boolean isSmartAlbumTitleEnabled() {
        return false;
    }

    public boolean needCenterAlign() {
        if (super.needCenterAlign() || ResourceCompat.isLandscape((View) this)) {
            return true;
        }
        return false;
    }

    public void setClickEnabled(boolean z) {
        int i2;
        this.mClickEnabled = z;
        LinearLayout linearLayout = this.mItemsLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
    }

    public void setVisibility(int i2) {
        boolean z;
        super.setVisibility(i2);
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        setClickEnabled(z);
    }

    public void updateSmartAlbumVisible(boolean z) {
        boolean z3;
        if (!z || !StoryAppBarManager.getInstance().isVisibleCondition()) {
            z3 = false;
        } else {
            z3 = true;
        }
        super.updateSmartAlbumVisible(z3);
    }

    public void updateItemsLayout() {
    }

    public void setTitleVisibility(int i2) {
    }
}
