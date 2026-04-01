package com.samsung.android.gallery.widget.window;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DecoViewController {
    protected final String TAG = getClass().getSimpleName();
    private final Activity mActivity;
    protected final GalleryToolbar mToolbar;

    public DecoViewController(Activity activity, GalleryToolbar galleryToolbar) {
        this.mActivity = activity;
        this.mToolbar = galleryToolbar;
    }

    private int getMiddleColor(float f, int i2, int i7) {
        int alpha = Color.alpha(i2);
        int red = Color.red(i2);
        int green = Color.green(i2);
        int blue = Color.blue(i2);
        return Color.argb((int) (((float) alpha) - (((float) (alpha - Color.alpha(i7))) * f)), (int) (((float) red) - (((float) (red - Color.red(i7))) * f)), (int) (((float) green) - (((float) (green - Color.green(i7))) * f)), (int) (((float) blue) - (((float) (blue - Color.blue(i7))) * f)));
    }

    private int getResultColor(Activity activity, float f, int i2, int i7) {
        return getMiddleColor(f, activity.getColor(i2), activity.getColor(i7));
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public abstract int getToolbarBackgroundResId();

    public void setToolbarBackground(float f) {
        Drawable drawable = getActivity().getDrawable(getToolbarBackgroundResId());
        if (drawable != null) {
            drawable.mutate().setAlpha((int) (f * 255.0f));
        }
        this.mToolbar.setBackground(drawable);
    }

    public void updateMenuItemView(float f, Toolbar toolbar) {
        Menu menu = toolbar.getMenu();
        for (int i2 = 0; i2 < menu.size(); i2++) {
            MenuItem item = menu.getItem(i2);
            Drawable icon = item.getIcon();
            if (icon != null) {
                Activity activity = getActivity();
                int i7 = R$color.photo_cover_actionbar_item_text_color;
                icon.mutate().setTint(getMiddleColor(f, activity.getColor(i7), getResultColor(activity, f, i7, R$color.actionbar_menu_text_enabled)));
                item.setIcon(icon);
            }
        }
    }

    public void updateNavigationIcon(float f, Toolbar toolbar) {
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.mutate().setTint(getResultColor(getActivity(), f, R$color.photo_cover_actionbar_item_text_color, R$color.actionbar_menu_text_enabled));
        }
    }

    public void updateOverflowIcon(float f, Toolbar toolbar) {
        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.mutate().setTint(getResultColor(getActivity(), f, R$color.photo_cover_actionbar_item_text_color, R$color.actionbar_menu_text_enabled));
        }
    }

    public void updateSelectMenu(float f, Toolbar toolbar) {
        Activity activity = getActivity();
        View findViewById = toolbar.findViewById(R$id.select_info_layout);
        if (findViewById != null) {
            ((View) findViewById.getParent()).setBackgroundResource(R$drawable.actionbar_transparent_background);
            Drawable buttonDrawable = ((CheckBox) toolbar.findViewById(R$id.select_all)).getButtonDrawable();
            if (buttonDrawable != null) {
                int resultColor = getResultColor(activity, f, R$color.thumbnail_checkbox_off_color, R$color.photo_cover_checkbox_default_tint_color);
                DrawableCompat.setTintList(buttonDrawable, new ColorStateList(new int[][]{new int[]{16842912}, new int[0]}, new int[]{getResultColor(activity, f, R$color.thumbnail_checkbox_on_color, R$color.expansion_view_checkbox_on_tint_color), resultColor}));
            }
            int resultColor2 = getResultColor(activity, f, R$color.photo_cover_actionbar_item_text_color, R$color.actionbar_menu_text_enabled);
            ((TextView) toolbar.findViewById(R$id.select_all_text)).setTextColor(resultColor2);
            ((TextView) toolbar.findViewById(R$id.select_count)).setTextColor(resultColor2);
            ((TextView) toolbar.findViewById(R$id.select_size)).setTextColor(resultColor2);
        }
    }
}
