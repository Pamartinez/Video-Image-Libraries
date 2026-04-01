package com.samsung.android.gallery.widget.abstraction;

import android.content.Context;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContextThemeWrapperCompat extends ContextThemeWrapper {
    final WeakReference<Context> mRefContext;
    private LayoutInflater mStubLayoutInflater;

    public ContextThemeWrapperCompat(Context context, Resources.Theme theme) {
        super(context.getApplicationContext(), theme);
        setStubLayoutInflater(context);
        this.mRefContext = new WeakReference<>(context);
    }

    public Context getBaseContext() {
        return this.mRefContext.get();
    }

    public LayoutInflater getStubLayoutInflater() {
        return this.mStubLayoutInflater;
    }

    public void setStubLayoutInflater(Context context) {
        LayoutInflater layoutInflater;
        if (context != null) {
            layoutInflater = LayoutInflater.from(context);
        } else {
            layoutInflater = null;
        }
        this.mStubLayoutInflater = layoutInflater;
    }
}
