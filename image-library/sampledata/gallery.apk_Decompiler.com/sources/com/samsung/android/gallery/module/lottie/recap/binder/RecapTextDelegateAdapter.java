package com.samsung.android.gallery.module.lottie.recap.binder;

import x0.J;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTextDelegateAdapter extends J {
    RecapTextDelegateImpl textDelegate;

    public RecapTextDelegateAdapter(w wVar, RecapTextDelegateImpl recapTextDelegateImpl) {
        super(wVar);
        setCacheText(false);
        this.textDelegate = recapTextDelegateImpl;
    }

    public String getText(String str, String str2) {
        return this.textDelegate.getText(str, str2);
    }
}
