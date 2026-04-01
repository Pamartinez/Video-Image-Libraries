package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.DelegateFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2562a;
    public final /* synthetic */ DelegateFactory.ThemeProvideDelegate b;

    public /* synthetic */ a(DelegateFactory.ThemeProvideDelegate themeProvideDelegate, int i2) {
        this.f2562a = i2;
        this.b = themeProvideDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2562a;
        DelegateFactory.ThemeProvideDelegate themeProvideDelegate = this.b;
        switch (i2) {
            case 0:
                return themeProvideDelegate.lambda$addRequestProvider$0(dataRequest, objArr);
            case 1:
                return themeProvideDelegate.lambda$addRequestProvider$1(dataRequest, objArr);
            default:
                return themeProvideDelegate.lambda$addRequestProvider$2(dataRequest, objArr);
        }
    }
}
