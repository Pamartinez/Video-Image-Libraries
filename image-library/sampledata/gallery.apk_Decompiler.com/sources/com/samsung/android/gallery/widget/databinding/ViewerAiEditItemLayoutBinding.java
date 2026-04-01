package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewerAiEditItemLayoutBinding implements ViewBinding {
    public final LinearLayout aiEditItemBg;
    public final ImageView aiEditItemImage;
    public final ConstraintLayout aiEditItemLayout;
    public final TextView aiEditItemText;
    public final LinearLayout aiEditTextLayout;
    public final ViewStub effectView;
    private final ConstraintLayout rootView;

    private ViewerAiEditItemLayoutBinding(ConstraintLayout constraintLayout, LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout2, TextView textView, LinearLayout linearLayout2, ViewStub viewStub) {
        this.rootView = constraintLayout;
        this.aiEditItemBg = linearLayout;
        this.aiEditItemImage = imageView;
        this.aiEditItemLayout = constraintLayout2;
        this.aiEditItemText = textView;
        this.aiEditTextLayout = linearLayout2;
        this.effectView = viewStub;
    }

    public static ViewerAiEditItemLayoutBinding bind(View view) {
        int i2 = R$id.ai_edit_item_bg;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
        if (linearLayout != null) {
            i2 = R$id.ai_edit_item_image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
            if (imageView != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i2 = R$id.ai_edit_item_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    i2 = R$id.ai_edit_text_layout;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                    if (linearLayout2 != null) {
                        i2 = R$id.effect_view;
                        ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(view, i2);
                        if (viewStub != null) {
                            return new ViewerAiEditItemLayoutBinding(constraintLayout, linearLayout, imageView, constraintLayout, textView, linearLayout2, viewStub);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static ViewerAiEditItemLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.viewer_ai_edit_item_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
