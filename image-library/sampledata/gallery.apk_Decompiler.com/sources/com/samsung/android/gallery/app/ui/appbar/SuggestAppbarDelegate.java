package com.samsung.android.gallery.app.ui.appbar;

import android.view.View;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.ButtonModel;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.mtp.UsbStorageState;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GallerySuggestAppBarModel;
import com.sec.android.gallery3d.R;
import g4.C0456a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestAppbarDelegate<V extends IBaseListView> {
    private GalleryAppBarLayout mAppbarLayout;
    private AppBarModel<?> mUsbStorageAppbarMode;
    private final V mView;

    public SuggestAppbarDelegate(V v) {
        this.mView = v;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showUsbStorageCard$0(View view, AppBarModel appBarModel) {
        this.mView.getBlackboard().publish("command:///UsbStorageTipDismiss", (Object) null);
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1148));
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_USB_SUGGESTED_APP_BAR_SHOW_IN_MENU);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showUsbStorageCard$1(View view, AppBarModel appBarModel) {
        this.mView.getBlackboard().publish("command:///UsbStorageTipDismiss", (Object) null);
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_USB_SUGGESTED_APP_BAR_DISMISS);
    }

    public void hideUsbStorageCard() {
        hideUsbStorageCard(false);
    }

    public void init() {
        this.mAppbarLayout = this.mView.getAppbarLayout();
    }

    public void showUsbStorageCard() {
        if (this.mAppbarLayout == null) {
            Log.e("SuggestAppbarDelegate", "appbar null");
        } else if (!UsbStorageState.getInstance().isMounted()) {
            Log.e("SuggestAppbarDelegate", "not mount");
        } else if (PreferenceCache.UsbStorageTipCardIsShow.getBoolean()) {
            this.mAppbarLayout.clearSuggestAppbarModel();
            Log.d("SuggestAppbarDelegate", "already executed showStorageTipCard");
        } else {
            this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_USB_SUGGESTED_APP_BAR_PRESENCE);
            if (this.mUsbStorageAppbarMode == null) {
                this.mUsbStorageAppbarMode = new GallerySuggestAppBarModel.Builder(this.mAppbarLayout.getContext()).setTitle(AppResources.getString(R.string.usb_storage_device_connected)).setButtons(List.of(new ButtonModel(AppResources.getString(R.string.show_in_menu), new C0456a(this, 0)))).setCloseClickListener(new C0456a(this, 1)).build(UsbAttachSuggestAppbarView.class);
            }
            this.mAppbarLayout.setSuggestAppbarModel(this.mUsbStorageAppbarMode);
        }
    }

    public void updateUsbStorageCard() {
        if (UsbStorageState.getInstance().isMounted()) {
            showUsbStorageCard();
        } else {
            hideUsbStorageCard();
        }
    }

    public void hideUsbStorageCard(boolean z) {
        if (this.mAppbarLayout != null) {
            if (z) {
                PreferenceCache.UsbStorageTipCardIsShow.setBoolean(true);
            }
            this.mAppbarLayout.clearSuggestAppbarModel();
        }
    }
}
