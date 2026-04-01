package com.samsung.android.gallery.app.ui.container.tablet;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabController;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import k6.b;
import n4.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListContainerPresenter<V extends IListContainerView> extends MvpBasePresenter<V> {
    public ListContainerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private String appendAlbumId(String str, String str2) {
        return ArgumentsUtil.appendArgs(str, "id", String.valueOf(FileUtils.getBucketId(str2)));
    }

    private String getTimelineFakeLocationKey() {
        return "location://timeline/fake";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).updatePrimaryNavigationFragment();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEssentialAlbumsChanged$2() {
        this.mBlackboard.publish("command://RemoveChildFragment", Boolean.TRUE);
    }

    private void moveUrl(String str) {
        if (LocationKey.isRootOfContainerExceptTab(str)) {
            selectRootItem(str);
        } else {
            this.mBlackboard.post("command://MoveURL", str);
        }
    }

    private void moveUrlWithDepth(String str) {
        if (!((IListContainerView) this.mView).refreshChildFragment(str)) {
            if (!LocationKey.isRootOfContainerExceptTab(str)) {
                if (!setInputBlock(this.TAG + "_moveUrlWithDepth")) {
                    return;
                }
            }
            moveUrl(str);
        }
    }

    /* access modifiers changed from: private */
    public void onAddChildFragment(Object obj, Bundle bundle) {
        if (!((IListContainerView) this.mView).isDestroyed()) {
            ((IListContainerView) this.mView).addChildFragment((String) obj);
        }
    }

    /* access modifiers changed from: private */
    public void onChangeTabFocus(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).changeFocus((String) obj);
    }

    /* access modifiers changed from: private */
    public void onCreateChildFragmentView(Object obj, Bundle bundle) {
        if (!((IListContainerView) this.mView).isDestroyed()) {
            ((IListContainerView) this.mView).onChildFragmentViewCreated((IBaseFragment) obj);
        }
    }

    /* access modifiers changed from: private */
    public void onEssentialAlbumsChanged(Object obj, Bundle bundle) {
        if (((IListContainerView) this.mView).isSelectionMode()) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
        if (((IListContainerView) this.mView).isMoveMode()) {
            this.mBlackboard.post("command://ExitMoveMode", Boolean.FALSE);
        }
        this.mBlackboard.postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
        ThreadUtil.postOnUiThreadDelayed(new b(11, this), 100);
    }

    /* access modifiers changed from: private */
    public void onRemoveChildFragment(Object obj, Bundle bundle) {
        if (((IListContainerView) this.mView).isDestroyed()) {
            return;
        }
        if (((Boolean) obj).booleanValue()) {
            ((IListContainerView) this.mView).removeChildFragment();
        } else {
            ((IListContainerView) this.mView).onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void onRemoveSiblingFragments(Object obj, Bundle bundle) {
        if (!((IListContainerView) this.mView).isDestroyed()) {
            ((IListContainerView) this.mView).removeSiblingFragments((String[]) obj);
        }
    }

    /* access modifiers changed from: private */
    public void onSelectBottomTabMenu(Object obj, Bundle bundle) {
        try {
            if (!isViewPaused()) {
                ((IListContainerView) this.mView).selectBottomNavigationMenu((String) obj);
            }
        } catch (IllegalArgumentException unused) {
            Log.e(this.TAG, "unsupported current key. ignore current change.");
        }
    }

    /* access modifiers changed from: private */
    public void onSettingsBadgeUpdated(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).settingsBadgeUpdated(((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public void onSharedAlbumsBadgeUpdated(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).updateBadgeOnTab(R.id.action_sharings, ((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public void onSharingServiceDisabled(Object obj, Bundle bundle) {
        onMenuItemSelected("location://timeline");
    }

    /* access modifiers changed from: private */
    public void onStartShrinkAnimation(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).startShrinkAnimation();
    }

    /* access modifiers changed from: private */
    public void onUpdateBottomMenuTabBadge(Object obj, Bundle bundle) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            ((IListContainerView) this.mView).updateBadgeOnTab(R.id.action_menu_list, ((Boolean) obj).booleanValue());
            this.mBlackboard.erase("data://badge/bottom_menu");
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateBottomTabFloatingView(Object obj, Bundle bundle) {
        if (!((IListContainerView) this.mView).isDestroyed()) {
            ((IListContainerView) this.mView).onUpdateBottomTabFloatingView((View) obj);
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateBottomTabMenu(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).updateBottomNavigationMenu();
    }

    /* access modifiers changed from: private */
    public void onUpdateNotificationsBadge(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).deliverEvent(EventMessage.obtain(1050, obj));
        this.mBlackboard.erase("data://badge/notifications");
    }

    /* access modifiers changed from: private */
    public void onUpdateSharingTabBadge(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).updateBadgeOnTab(R.id.action_sharings, ((Boolean) obj).booleanValue());
        this.mBlackboard.erase("data://badge/sharings");
    }

    /* access modifiers changed from: private */
    public void onUpdateStoriesTabBadge(Object obj, Bundle bundle) {
        ((IListContainerView) this.mView).updateBadgeOnTab(R.id.action_stories, ((Boolean) obj).booleanValue());
        this.mBlackboard.erase("data://badge/stories");
    }

    private void selectRootItem(String str) {
        ((IListContainerView) this.mView).selectView(str, false);
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/albums/selectEssentialAlbums", new d(this, 8)).setWorkingOnUI());
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            arrayList.add(new SubscriberInfo("event/UsbStorageVolumeChanged", new l4.d(1)).setWorkingOnUI());
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://UpdateBottomNavigationItem", new d(this, 0)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://RemoveSiblingsFragments", new d(this, 16)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://AddChildFragment", new d(this, 17)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://RemoveChildFragment", new d(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://CreateChildFragmentView", new d(this, 2)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://UiEventStartShrinkAnimation", new d(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://bottomtab/select", new d(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://SharingServiceDisabled", new d(this, 5)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://UpdateBottomTabFloatingView", new d(this, 6)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("data://badge/notifications", new d(this, 7)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/stories", new d(this, 9)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/sharings", new d(this, 10)).setTriggerPreloadedData().setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://badge/bottom_menu", new d(this, 11)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://drawer_focus_changed", new d(this, 12)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://shared_albums_updated", new d(this, 13)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://settings_badge_updated", new d(this, 14)).setWorkingOnUI());
        for (ITabController createSubscriberList : ((IListContainerView) this.mView).getControllers()) {
            createSubscriberList.createSubscriberList(arrayList);
        }
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            arrayList.add(new SubscriberInfo("function://activity_back_pressed_callback_enabled", new d(this, 15)).setWorkingCurrent());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01f9, code lost:
        moveUrl(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01fc, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c9, code lost:
        selectRootItem(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01cc, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMenuItemSelected(java.lang.String r6) {
        /*
            r5 = this;
            com.samsung.android.gallery.support.utils.StringCompat r0 = r5.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onMenuItemSelected - locationKey : "
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.d(r0, r1)
            java.lang.String r0 = com.samsung.android.gallery.support.utils.ArgumentsUtil.removeArgs(r6)
            r0.getClass()
            int r1 = r0.hashCode()
            java.lang.String r2 = "location://timeline"
            r3 = 0
            r4 = -1
            switch(r1) {
                case -2094179156: goto L_0x011a;
                case -1648894317: goto L_0x010f;
                case -1536241977: goto L_0x0104;
                case -1141680973: goto L_0x00f9;
                case -984961596: goto L_0x00ee;
                case -520073496: goto L_0x00e3;
                case -440239236: goto L_0x00d8;
                case -332973319: goto L_0x00cd;
                case -300413254: goto L_0x00bf;
                case -212479357: goto L_0x00b1;
                case -125579287: goto L_0x00a3;
                case 188274925: goto L_0x0095;
                case 263612166: goto L_0x0089;
                case 383247789: goto L_0x007b;
                case 910438587: goto L_0x006d;
                case 983147555: goto L_0x005f;
                case 1177645495: goto L_0x0051;
                case 1297591864: goto L_0x0043;
                case 1328558602: goto L_0x0035;
                case 1944014916: goto L_0x0027;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x0124
        L_0x0027:
            java.lang.String r1 = "location://virtual/album/favorite/fileList"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0031
            goto L_0x0124
        L_0x0031:
            r4 = 19
            goto L_0x0124
        L_0x0035:
            java.lang.String r1 = "location://suggestions"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x003f
            goto L_0x0124
        L_0x003f:
            r4 = 18
            goto L_0x0124
        L_0x0043:
            java.lang.String r1 = "location://virtual/album/recently/fileList"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x004d
            goto L_0x0124
        L_0x004d:
            r4 = 17
            goto L_0x0124
        L_0x0051:
            java.lang.String r1 = "location://virtual/album/video/fileList"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x005b
            goto L_0x0124
        L_0x005b:
            r4 = 16
            goto L_0x0124
        L_0x005f:
            java.lang.String r1 = "location://collection"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0069
            goto L_0x0124
        L_0x0069:
            r4 = 15
            goto L_0x0124
        L_0x006d:
            java.lang.String r1 = "location://search/fileList/Category/ShotMode"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0077
            goto L_0x0124
        L_0x0077:
            r4 = 14
            goto L_0x0124
        L_0x007b:
            java.lang.String r1 = "location://search"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0085
            goto L_0x0124
        L_0x0085:
            r4 = 13
            goto L_0x0124
        L_0x0089:
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0091
            goto L_0x0124
        L_0x0091:
            r4 = 12
            goto L_0x0124
        L_0x0095:
            java.lang.String r1 = "location://albums/otg/fileList"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x009f
            goto L_0x0124
        L_0x009f:
            r4 = 11
            goto L_0x0124
        L_0x00a3:
            java.lang.String r1 = "location://albums"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00ad
            goto L_0x0124
        L_0x00ad:
            r4 = 10
            goto L_0x0124
        L_0x00b1:
            java.lang.String r1 = "location://story/albums"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00bb
            goto L_0x0124
        L_0x00bb:
            r4 = 9
            goto L_0x0124
        L_0x00bf:
            java.lang.String r1 = "location://albums/fileList/mxVirtual/favorite"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00c9
            goto L_0x0124
        L_0x00c9:
            r4 = 8
            goto L_0x0124
        L_0x00cd:
            java.lang.String r1 = "location://albums/fileList/mxVirtual/recent"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00d6
            goto L_0x0124
        L_0x00d6:
            r4 = 7
            goto L_0x0124
        L_0x00d8:
            java.lang.String r1 = "location://sharing/albums"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00e1
            goto L_0x0124
        L_0x00e1:
            r4 = 6
            goto L_0x0124
        L_0x00e3:
            java.lang.String r1 = "location://settings"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00ec
            goto L_0x0124
        L_0x00ec:
            r4 = 5
            goto L_0x0124
        L_0x00ee:
            java.lang.String r1 = "location://mtp"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00f7
            goto L_0x0124
        L_0x00f7:
            r4 = 4
            goto L_0x0124
        L_0x00f9:
            java.lang.String r1 = "location://search/fileList/Category/Location"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0102
            goto L_0x0124
        L_0x0102:
            r4 = 3
            goto L_0x0124
        L_0x0104:
            java.lang.String r1 = "location:///VideoStudio"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x010d
            goto L_0x0124
        L_0x010d:
            r4 = 2
            goto L_0x0124
        L_0x010f:
            java.lang.String r1 = "location://trash"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0118
            goto L_0x0124
        L_0x0118:
            r4 = 1
            goto L_0x0124
        L_0x011a:
            java.lang.String r1 = "location://albums/private/fileList"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0123
            goto L_0x0124
        L_0x0123:
            r4 = r3
        L_0x0124:
            java.lang.String r0 = "false"
            switch(r4) {
                case 0: goto L_0x0232;
                case 1: goto L_0x0223;
                case 2: goto L_0x0218;
                case 3: goto L_0x01fd;
                case 4: goto L_0x01f9;
                case 5: goto L_0x01ea;
                case 6: goto L_0x01d1;
                case 7: goto L_0x01cd;
                case 8: goto L_0x01cd;
                case 9: goto L_0x01c9;
                case 10: goto L_0x01b8;
                case 11: goto L_0x0195;
                case 12: goto L_0x016a;
                case 13: goto L_0x0240;
                case 14: goto L_0x01fd;
                case 15: goto L_0x01c9;
                case 16: goto L_0x0160;
                case 17: goto L_0x0156;
                case 18: goto L_0x0146;
                case 19: goto L_0x012d;
                default: goto L_0x0129;
            }
        L_0x0129:
            r5.moveUrlWithDepth(r6)
            return
        L_0x012d:
            com.samsung.android.gallery.support.utils.UriBuilder r1 = new com.samsung.android.gallery.support.utils.UriBuilder
            r1.<init>(r6)
            java.lang.String r6 = "with_group"
            com.samsung.android.gallery.support.utils.UriBuilder r6 = r1.appendArg((java.lang.String) r6, (java.lang.String) r0)
            java.lang.String r6 = r6.build()
            java.lang.String r0 = "Virtual/Favourites"
            java.lang.String r6 = r5.appendAlbumId(r6, r0)
            r5.moveUrl(r6)
            return
        L_0x0146:
            com.samsung.android.gallery.support.utils.SimpleThreadPool r0 = com.samsung.android.gallery.support.utils.SimpleThreadPool.getInstance()
            C3.i r1 = new C3.i
            r2 = 23
            r1.<init>(r2)
            r0.execute(r1)
            goto L_0x01f9
        L_0x0156:
            java.lang.String r0 = "Virtual/Recently"
            java.lang.String r6 = r5.appendAlbumId(r6, r0)
            r5.moveUrl(r6)
            return
        L_0x0160:
            java.lang.String r0 = "Virtual/Video"
            java.lang.String r6 = r5.appendAlbumId(r6, r0)
            r5.moveUrl(r6)
            return
        L_0x016a:
            V r0 = r5.mView
            com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView r0 = (com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView) r0
            boolean r0 = r0.isTimelineFirstSelect()
            if (r0 == 0) goto L_0x0191
            java.lang.String r0 = com.samsung.android.gallery.support.blackboard.key.DataKey.DATA_CURSOR(r2)
            com.samsung.android.gallery.support.blackboard.Blackboard r1 = r5.mBlackboard
            boolean r0 = r1.isEmpty(r0)
            if (r0 == 0) goto L_0x018a
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r5.mBlackboard
            java.lang.String r1 = r5.getTimelineFakeLocationKey()
            com.samsung.android.gallery.module.utils.BlackboardUtils.publishDataRequest(r0, r1)
            goto L_0x0191
        L_0x018a:
            com.samsung.android.gallery.support.utils.StringCompat r0 = r5.TAG
            java.lang.String r1 = "data cursor exist. skip req timeline fake"
            com.samsung.android.gallery.support.utils.Log.d(r0, r1)
        L_0x0191:
            r5.selectRootItem(r6)
            return
        L_0x0195:
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r5.mBlackboard
            java.lang.String r1 = "command:///UsbStorageTipDismiss"
            r2 = 0
            r0.publish(r1, r2)
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.SUPPORT_USB_STORAGE
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            if (r0 == 0) goto L_0x0240
            com.samsung.android.gallery.app.controller.externals.GotoMyFilesCmd r0 = new com.samsung.android.gallery.app.controller.externals.GotoMyFilesCmd
            r0.<init>()
            java.lang.String r1 = "directory"
            java.lang.String r6 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue(r6, r1)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            r0.execute(r5, r6)
            return
        L_0x01b8:
            V r0 = r5.mView
            com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView r0 = (com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView) r0
            boolean r0 = r0.isAlbumFirstSelect()
            if (r0 == 0) goto L_0x01c9
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r5.mBlackboard
            java.lang.String r1 = "location://albums/cache"
            com.samsung.android.gallery.module.utils.BlackboardUtils.publishDataRequest(r0, r1)
        L_0x01c9:
            r5.selectRootItem(r6)
            return
        L_0x01cd:
            r5.moveUrl(r6)
            return
        L_0x01d1:
            com.samsung.android.gallery.module.mde.MdeSharingService r0 = com.samsung.android.gallery.module.mde.MdeSharingService.getInstance()
            boolean r0 = r0.showSemsPermissionPopup()
            if (r0 == 0) goto L_0x01e6
            com.samsung.android.gallery.app.controller.sharing.GroupSharingApplyCmd r6 = new com.samsung.android.gallery.app.controller.sharing.GroupSharingApplyCmd
            r6.<init>()
            java.lang.Object[] r0 = new java.lang.Object[r3]
            r6.execute(r5, r0)
            return
        L_0x01e6:
            r5.moveUrl(r6)
            return
        L_0x01ea:
            com.samsung.android.gallery.app.controller.internals.StartSettingsCmd r6 = new com.samsung.android.gallery.app.controller.internals.StartSettingsCmd
            r6.<init>()
            com.samsung.android.gallery.settings.helper.PopoverUtils$Anchor r0 = com.samsung.android.gallery.settings.helper.PopoverUtils.Anchor.TOP_START
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            r6.execute(r5, r0)
            return
        L_0x01f9:
            r5.moveUrl(r6)
            return
        L_0x01fd:
            com.samsung.android.gallery.support.utils.UriBuilder r1 = new com.samsung.android.gallery.support.utils.UriBuilder
            r1.<init>(r6)
            java.lang.String r6 = "searchToolbar"
            com.samsung.android.gallery.support.utils.UriBuilder r6 = r1.appendArg((java.lang.String) r6, (java.lang.String) r0)
            java.lang.String r0 = "searchFromTabMenu"
            java.lang.String r1 = "true"
            com.samsung.android.gallery.support.utils.UriBuilder r6 = r6.appendArg((java.lang.String) r0, (java.lang.String) r1)
            java.lang.String r6 = r6.build()
            r5.moveUrl(r6)
            return
        L_0x0218:
            com.samsung.android.gallery.app.controller.externals.StartVideoStudioCmd r6 = new com.samsung.android.gallery.app.controller.externals.StartVideoStudioCmd
            r6.<init>()
            java.lang.Object[] r0 = new java.lang.Object[r3]
            r6.execute(r5, r0)
            return
        L_0x0223:
            com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd r6 = new com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd
            r6.<init>()
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            r6.execute(r5, r0)
            return
        L_0x0232:
            boolean r6 = com.samsung.android.gallery.support.utils.PocFeatures.SUPPORT_PRIVATE_ALBUM
            if (r6 == 0) goto L_0x0240
            com.samsung.android.gallery.app.controller.album.StartPrivateAlbumCmd r6 = new com.samsung.android.gallery.app.controller.album.StartPrivateAlbumCmd
            r6.<init>()
            java.lang.Object[] r0 = new java.lang.Object[r3]
            r6.execute(r5, r0)
        L_0x0240:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.tablet.ListContainerPresenter.onMenuItemSelected(java.lang.String):void");
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon((Drawable) null);
        toolbar.setTitle((int) R.string.app_name);
        toolbar.setSubtitle((CharSequence) null);
    }

    public void initMenuOnViewResume() {
    }
}
