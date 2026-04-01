package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.scsp.media.api.d;
import f7.C0455a;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseTipCardDelegate<V extends IBaseListView> implements TipCardDelegate {
    protected final V mView;

    public BaseTipCardDelegate(V v) {
        this.mView = v;
    }

    public abstract List<TipCardView> createTipCardList();

    public TipCardView getAndCheckTipCard(Context context, String str) {
        Log.d("BaseTipCardDelegate", "checkTipCard", str);
        return createTipCardList().stream().filter(new a(24)).filter(new C0455a(context, 0)).findFirst().orElse((Object) null);
    }

    public final V getView() {
        return this.mView;
    }

    public boolean isEmpty() {
        return ((Boolean) Optional.ofNullable(createTipCardList()).map(new d(15)).orElse(Boolean.TRUE)).booleanValue();
    }

    public void release() {
    }
}
