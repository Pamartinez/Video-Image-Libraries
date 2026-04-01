package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.content.Context;
import android.util.Size;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanelViewHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$createOverflowPanel$1$1", "Landroid/widget/ArrayAdapter;", "Landroid/view/MenuItem;", "getView", "Landroid/view/View;", "position", "", "convertView", "parent", "Landroid/view/ViewGroup;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCapturePopup$createOverflowPanel$1$1 extends ArrayAdapter<MenuItem> {
    final /* synthetic */ ObjectCapturePopup this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectCapturePopup$createOverflowPanel$1$1(ObjectCapturePopup objectCapturePopup, Context context) {
        super(context, 0);
        this.this$0 = objectCapturePopup;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        j.e(viewGroup, "parent");
        OverflowPanelViewHelper access$getOverflowPanelViewHelper$p = this.this$0.overflowPanelViewHelper;
        Object item = getItem(i2);
        j.b(item);
        Size overflowPanelSize = this.this$0.getOverflowPanelSize();
        j.b(overflowPanelSize);
        return access$getOverflowPanelViewHelper$p.getView((MenuItem) item, overflowPanelSize.getWidth(), view);
    }
}
