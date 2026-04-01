package com.samsung.android.gallery.image360.widget;

import A.a;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Image360FastOptionMoreMenuAdapter extends ArrayAdapter<MenuItem> {
    private final LayoutInflater mInflater;

    public Image360FastOptionMoreMenuAdapter(Context context, int i2) {
        super(context, i2);
        this.mInflater = LayoutInflater.from(context);
    }

    private View getCustomView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            try {
                view = this.mInflater.inflate(R$layout.fast_option_more_menu_item, viewGroup, false);
            } catch (Exception e) {
                a.s(e, new StringBuilder("custom view generation failed, e="), "Image360FastOptionMoreMenuAdapter");
                return view;
            }
        }
        MenuItem menuItem = (MenuItem) getItem(i2);
        if (menuItem != null) {
            TextView textView = (TextView) view.findViewById(R$id.fast_option_menu_title);
            textView.setText(menuItem.getTitle());
            textView.setContentDescription((CharSequence) null);
        }
        return view;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        return getCustomView(i2, view, viewGroup);
    }
}
