package com.samsung.android.gallery.widget.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RemasterViewerHandlerLayoutBinding implements ViewBinding {
    public final TextView afterText;
    public final TextView beforeText;
    public final FrameLayout circleHandler;
    public final LinearLayout remasterHeaderView;
    private final RelativeLayout rootView;
    public final RelativeLayout verticalLine;
    public final ImageView verticalLineBottom;
    public final ImageView verticalLineUp;

    private RemasterViewerHandlerLayoutBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, FrameLayout frameLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, ImageView imageView, ImageView imageView2) {
        this.rootView = relativeLayout;
        this.afterText = textView;
        this.beforeText = textView2;
        this.circleHandler = frameLayout;
        this.remasterHeaderView = linearLayout;
        this.verticalLine = relativeLayout2;
        this.verticalLineBottom = imageView;
        this.verticalLineUp = imageView2;
    }

    public static RemasterViewerHandlerLayoutBinding bind(View view) {
        int i2 = R$id.after_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = R$id.before_text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView2 != null) {
                i2 = R$id.circle_handler;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout != null) {
                    i2 = R$id.remaster_header_view;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                    if (linearLayout != null) {
                        i2 = R$id.vertical_line;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i2);
                        if (relativeLayout != null) {
                            i2 = R$id.vertical_line_bottom;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
                            if (imageView != null) {
                                i2 = R$id.vertical_line_up;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
                                if (imageView2 != null) {
                                    return new RemasterViewerHandlerLayoutBinding((RelativeLayout) view, textView, textView2, frameLayout, linearLayout, relativeLayout, imageView, imageView2);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
