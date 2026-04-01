package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\tJ\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0013\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanelViewHelper;", "", "objectCapturePopup", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "context", "Landroid/content/Context;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;Landroid/content/Context;)V", "calculator", "Landroid/view/View;", "sidePadding", "", "mContext", "getView", "menuItem", "Landroid/view/MenuItem;", "minimumWidth", "convertView", "calculateWidth", "createMenuButton", "shouldShowIcon", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OverflowPanelViewHelper {
    private final View calculator = createMenuButton((MenuItem) null);
    private final Context mContext;
    private final ObjectCapturePopup objectCapturePopup;
    private final int sidePadding;

    public OverflowPanelViewHelper(ObjectCapturePopup objectCapturePopup2, Context context) {
        j.e(objectCapturePopup2, "objectCapturePopup");
        j.e(context, "context");
        this.objectCapturePopup = objectCapturePopup2;
        this.sidePadding = context.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_overflow_side_padding);
        this.mContext = context;
    }

    private final View createMenuButton(MenuItem menuItem) {
        View createMenuItemButton = this.objectCapturePopup.createMenuItemButton(this.mContext, menuItem, shouldShowIcon(menuItem));
        int i2 = this.sidePadding;
        createMenuItemButton.setPadding(i2, 0, i2, 0);
        return createMenuItemButton;
    }

    private final boolean shouldShowIcon(MenuItem menuItem) {
        if (menuItem == null || menuItem.getGroupId() != 16908353) {
            return false;
        }
        return true;
    }

    public final int calculateWidth(MenuItem menuItem) {
        j.e(menuItem, "menuItem");
        this.objectCapturePopup.updateMenuItemButton(this.calculator, menuItem, shouldShowIcon(menuItem));
        this.calculator.measure(0, 0);
        return this.calculator.getMeasuredWidth();
    }

    public final View getView(MenuItem menuItem, int i2, View view) {
        j.e(menuItem, "menuItem");
        if (view != null) {
            this.objectCapturePopup.updateMenuItemButton(view, menuItem, shouldShowIcon(menuItem));
        } else {
            view = createMenuButton(menuItem);
        }
        view.setMinimumWidth(i2);
        return view;
    }
}
