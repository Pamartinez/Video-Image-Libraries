package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FastoptionItemBinding implements ViewBinding {
    public final ViewStub dotBadge;
    public final RelativeLayout fastOptionItemContainer;
    public final ViewStub iconText;
    public final ViewStub imgLayout;
    public final ViewStub newBadge;
    private final RelativeLayout rootView;

    private FastoptionItemBinding(RelativeLayout relativeLayout, ViewStub viewStub, RelativeLayout relativeLayout2, ViewStub viewStub2, ViewStub viewStub3, ViewStub viewStub4) {
        this.rootView = relativeLayout;
        this.dotBadge = viewStub;
        this.fastOptionItemContainer = relativeLayout2;
        this.iconText = viewStub2;
        this.imgLayout = viewStub3;
        this.newBadge = viewStub4;
    }

    public static FastoptionItemBinding bind(View view) {
        int i2 = R$id.dot_badge;
        ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(view, i2);
        if (viewStub != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i2 = R$id.icon_text;
            ViewStub viewStub2 = (ViewStub) ViewBindings.findChildViewById(view, i2);
            if (viewStub2 != null) {
                i2 = R$id.img_layout;
                ViewStub viewStub3 = (ViewStub) ViewBindings.findChildViewById(view, i2);
                if (viewStub3 != null) {
                    i2 = R$id.new_badge;
                    ViewStub viewStub4 = (ViewStub) ViewBindings.findChildViewById(view, i2);
                    if (viewStub4 != null) {
                        return new FastoptionItemBinding(relativeLayout, viewStub, relativeLayout, viewStub2, viewStub3, viewStub4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static FastoptionItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fastoption_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
