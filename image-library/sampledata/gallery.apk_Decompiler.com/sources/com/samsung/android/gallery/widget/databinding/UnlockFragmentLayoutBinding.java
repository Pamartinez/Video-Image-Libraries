package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnlockFragmentLayoutBinding implements ViewBinding {
    public final ImageView lockIcon;
    public final RelativeLayout lockIconLayout;
    private final RelativeLayout rootView;
    public final TextView unlockDescriptionTextView;
    public final RelativeLayout unlockScreenContainer;
    public final TextView unlockTitleTextView;

    private UnlockFragmentLayoutBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, RelativeLayout relativeLayout3, TextView textView2) {
        this.rootView = relativeLayout;
        this.lockIcon = imageView;
        this.lockIconLayout = relativeLayout2;
        this.unlockDescriptionTextView = textView;
        this.unlockScreenContainer = relativeLayout3;
        this.unlockTitleTextView = textView2;
    }

    public static UnlockFragmentLayoutBinding bind(View view) {
        int i2 = R$id.lock_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R$id.lock_icon_layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i2);
            if (relativeLayout != null) {
                i2 = R$id.unlock_description_text_view;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    i2 = R$id.unlock_screen_container;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, i2);
                    if (relativeLayout2 != null) {
                        i2 = R$id.unlock_title_text_view;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                        if (textView2 != null) {
                            return new UnlockFragmentLayoutBinding((RelativeLayout) view, imageView, relativeLayout, textView, relativeLayout2, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static UnlockFragmentLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.unlock_fragment_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
