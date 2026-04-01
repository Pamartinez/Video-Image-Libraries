package com.samsung.android.gallery.widget.fastoption2;

import A.a;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionMoreMenuAdapter extends ArrayAdapter<FastOptionMenuItem> {
    private final int mDividerId;
    private final LayoutInflater mInflater;

    public FastOptionMoreMenuAdapter(Context context, int i2, int i7) {
        super(context, i2);
        this.mInflater = LayoutInflater.from(context);
        this.mDividerId = i7;
    }

    private View getCustomView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            try {
                view = this.mInflater.inflate(R$layout.fast_option_more_menu_item, viewGroup, false);
            } catch (Exception e) {
                a.s(e, new StringBuilder("custom view generation failed, e="), "FastOptionMoreMenuAdapter");
                return view;
            }
        } else {
            TextView textView = (TextView) view.findViewById(R$id.fast_option_menu_title);
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.height = -2;
            textView.setLayoutParams(layoutParams);
        }
        FastOptionMenuItem fastOptionMenuItem = (FastOptionMenuItem) getItem(i2);
        if (fastOptionMenuItem != null) {
            boolean isDivider = isDivider(fastOptionMenuItem);
            TextView textView2 = (TextView) view.findViewById(R$id.fast_option_menu_title);
            if (!isDivider) {
                if (!TextUtils.isEmpty(fastOptionMenuItem.getTitle())) {
                    textView2.setText(fastOptionMenuItem.getTitle());
                } else {
                    textView2.setText(fastOptionMenuItem.getTitleResId());
                }
                textView2.setContentDescription((CharSequence) null);
                updateNewBadge(view, fastOptionMenuItem);
            }
            setViewVisibility(getDividerView(view), isDivider);
            setViewVisibility((View) textView2.getParent(), !isDivider);
        }
        return view;
    }

    private View getDividerView(View view) {
        if (view != null) {
            return view.findViewById(R$id.fast_option_menu_divider);
        }
        return null;
    }

    private View getNewBadgeView(View view) {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            i2 = R$id.fast_option_menu_dot_badge;
        } else {
            i2 = R$id.fast_option_menu_new_badge;
        }
        return view.findViewById(i2);
    }

    private boolean isDivider(FastOptionMenuItem fastOptionMenuItem) {
        if (fastOptionMenuItem == null || fastOptionMenuItem.getMenuId() != this.mDividerId) {
            return false;
        }
        return true;
    }

    private void setViewVisibility(View view, boolean z) {
        int i2;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    private boolean supportNewBadge(FastOptionMenuItem fastOptionMenuItem) {
        if (supportNewBadgeForRemaster(fastOptionMenuItem) || supportNewBadgeForMotionPhotoExport(fastOptionMenuItem) || supportNewBadgeForGifRemaster(fastOptionMenuItem)) {
            return true;
        }
        return false;
    }

    private boolean supportNewBadgeForGifRemaster(FastOptionMenuItem fastOptionMenuItem) {
        if (fastOptionMenuItem == null || fastOptionMenuItem.getTitleResId() != R$string.remaster_gif || !PreferenceCache.RemasterGifNewBadge.getBoolean()) {
            return false;
        }
        return true;
    }

    private boolean supportNewBadgeForMotionPhotoExport(FastOptionMenuItem fastOptionMenuItem) {
        if (fastOptionMenuItem == null || fastOptionMenuItem.getTitleResId() != R$string.export_as || !SdkConfig.match(SdkConfig.GED.S) || !PreferenceCache.MotionPhotoExportNewBadge.getBoolean()) {
            return false;
        }
        return true;
    }

    private boolean supportNewBadgeForRemaster(FastOptionMenuItem fastOptionMenuItem) {
        if (fastOptionMenuItem == null || fastOptionMenuItem.getTitleResId() != R$string.remaster_picture || !SdkConfig.lessThan(SdkConfig.GED.S) || !PreferenceCache.RemasterNewBadge.getBoolean()) {
            return false;
        }
        return true;
    }

    private void updateNewBadge(View view, FastOptionMenuItem fastOptionMenuItem) {
        int i2;
        View newBadgeView = getNewBadgeView(view);
        if (supportNewBadge(fastOptionMenuItem)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(newBadgeView, i2);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        return getCustomView(i2, view, viewGroup);
    }

    public boolean hasNewBadgeMenu() {
        for (int i2 = 0; i2 < getCount(); i2++) {
            if (supportNewBadge((FastOptionMenuItem) getItem(i2))) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnabled(int i2) {
        return !isDivider((FastOptionMenuItem) getItem(i2));
    }
}
