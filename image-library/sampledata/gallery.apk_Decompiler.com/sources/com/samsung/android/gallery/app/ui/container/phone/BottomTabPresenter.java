package com.samsung.android.gallery.app.ui.container.phone;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.container.phone.IBottomTabView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import i4.C0468a;
import java.util.ArrayList;
import java.util.Optional;
import l4.c;
import l4.d;
import l4.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabPresenter<V extends IBottomTabView> extends MvpBasePresenter<V> {
    private boolean mIsPendingShowBottomTab;

    public BottomTabPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public void blockFocus(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).blockFocus(((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isMoveMode$3(Blackboard blackboard) {
        boolean z;
        if (blackboard.read("data://album_move") != null) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMoveExit$2() {
        showBottomTab(true, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSelectionEnabled$1() {
        showBottomTab(false);
    }

    /* access modifiers changed from: private */
    public void onEssentialAlbumsChanged(Object obj, Bundle bundle) {
        if (((IBottomTabView) this.mView).isSelectionMode()) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
        if (((IBottomTabView) this.mView).isMoveMode()) {
            this.mBlackboard.post("command://ExitMoveMode", Boolean.FALSE);
        }
        this.mBlackboard.postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    /* access modifiers changed from: private */
    public void onMoveEnter(Object obj, Bundle bundle) {
        showBottomTab(false);
    }

    /* access modifiers changed from: private */
    public void onMoveExit(Object obj, Bundle bundle) {
        if (isMoveMode()) {
            ThreadUtil.postOnUiThreadDelayed(new e(this, 0), 10);
        }
    }

    /* access modifiers changed from: private */
    public void onRemoveSiblingFragments(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).removeSiblingFragments((String[]) obj);
    }

    /* access modifiers changed from: private */
    public void onRequestBottomMenuTabBadge(Object obj, Bundle bundle) {
        this.mBlackboard.publish(CommandKey.DATA_REQUEST("data://badge/bottom_menu"), (Object) null);
    }

    /* access modifiers changed from: private */
    public void onSelectBottomTabMenu(Object obj, Bundle bundle) {
        String str = (String) obj;
        try {
            if (!isViewPaused()) {
                ((IBottomTabView) this.mView).selectBottomNavigationMenu(str);
            }
        } catch (IllegalArgumentException unused) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "unsupported current key. ignore current change : " + str);
        }
    }

    /* access modifiers changed from: private */
    public void onSelectionDisabled(Object obj, Bundle bundle) {
        if (!isMoveMode()) {
            showBottomTab(true);
        }
    }

    /* access modifiers changed from: private */
    public void onSelectionEnabled(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).getBlackboard().publish("command:///HideBottomTabCallback", new e(this, 1));
    }

    /* access modifiers changed from: private */
    public void onSharedAlbumBlockerChanged(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).hideTabMenu();
    }

    /* access modifiers changed from: private */
    public void onStartShrinkAnimation(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).startShrinkAnimation();
    }

    /* access modifiers changed from: private */
    public void onUpdateBottomMenuTabBadge(Object obj, Bundle bundle) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            ((IBottomTabView) this.mView).updateBadgeOnTab(R.id.action_menu_list, ((Boolean) obj).booleanValue());
            this.mBlackboard.erase("data://badge/bottom_menu");
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateBottomTabFloatingView(Object obj, Bundle bundle) {
        if (!((IBottomTabView) this.mView).isDestroyed()) {
            ((IBottomTabView) this.mView).onUpdateBottomTabFloatingView((View) obj);
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateBottomTabMenu(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).updateBottomNavigationMenu();
    }

    /* access modifiers changed from: private */
    public void onUpdateNotificationsBadge(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).deliverEvent(EventMessage.obtain(1050, obj));
        this.mBlackboard.erase("data://badge/notifications");
    }

    /* access modifiers changed from: private */
    public void onUpdateSharingTabBadge(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).updateBadgeOnTab(R.id.action_sharings, ((Boolean) obj).booleanValue());
        this.mBlackboard.erase("data://badge/sharings");
    }

    /* access modifiers changed from: private */
    public void onUpdateStoriesTabBadge(Object obj, Bundle bundle) {
        ((IBottomTabView) this.mView).updateBadgeOnTab(R.id.action_stories, ((Boolean) obj).booleanValue());
        this.mBlackboard.erase("data://badge/stories");
    }

    private void showBottomTab(boolean z) {
        showBottomTab(z, false);
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/albums/selectEssentialAlbums", new c(this, 6)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/secure/shared_album_blocker_changed", new c(this, 7)).setWorkingOnUI());
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            arrayList.add(new SubscriberInfo("event/UsbStorageVolumeChanged", new d(0)).setWorkingOnUI());
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://UpdateBottomNavigationItem", new c(this, 0)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://RemoveSiblingsFragments", new c(this, 13)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://UiEventStartShrinkAnimation", new c(this, 14)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new c(this, 15)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new c(this, 16)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://EnterMoveMode", new c(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ExitMoveMode", new c(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://bottomtab/select", new c(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/notifications", new c(this, 4)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/stories", new c(this, 5)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/sharings", new c(this, 8)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/bottom_menu", new c(this, 9)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://shared_albums_updated", new c(this, 10)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://settings_badge_updated", new c(this, 10)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://bottomtab/focus", new c(this, 11)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://UpdateBottomTabFloatingView", new c(this, 12)).setWorkingCurrent());
    }

    public boolean isMoveMode() {
        return ((Boolean) Optional.of(this.mBlackboard).map(new C0468a(24)).orElse(Boolean.FALSE)).booleanValue();
    }

    public void onViewResume() {
        super.onViewResume();
        if (this.mIsPendingShowBottomTab) {
            showBottomTab(true);
            this.mIsPendingShowBottomTab = false;
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon((Drawable) null);
        toolbar.setTitle((int) R.string.app_name);
        toolbar.setSubtitle((CharSequence) null);
    }

    private void showBottomTab(boolean z, boolean z3) {
        ((IBottomTabView) this.mView).getBlackboard().erase("command:///HideBottomTabCallback");
        if (!((IBottomTabView) this.mView).isViewActive() || !((IBottomTabView) this.mView).supportTabLayout()) {
            if (z3) {
                this.mIsPendingShowBottomTab = true;
            }
        } else if (z) {
            ((IBottomTabView) this.mView).showTabLayout();
        } else {
            ((IBottomTabView) this.mView).hideTabLayout();
        }
    }

    public void initMenuOnViewResume() {
    }
}
