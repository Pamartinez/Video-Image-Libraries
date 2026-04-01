package com.samsung.android.gallery.app.ui.list.timeline;

import C3.C0391a;
import Qb.e;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverFiles;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.voc.FindHiddenFiles;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TimelinePresenter<V extends ITimelineView> extends PicturesPresenter<V> {
    private boolean mCloudEnabled = CloudStateCompat.isEnabledInPref();
    private int mCloudService = CloudStateCompat.getService();
    private boolean mCloudSyncOn = CloudStateCompat.isSyncOnInPref();
    private boolean mMigrationSupported = CloudStateCompat.isMigrationSupportedInPref();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class TimelineMenuUpdater extends ListMenuUpdater {
        public TimelineMenuUpdater(V v) {
            super(v, TimelinePresenter.this);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updatePopupMenuAction$0(MenuItem menuItem) {
            int i2;
            if (SimilarPhotoHelper.isSimilarPhotoMode()) {
                i2 = R.string.ungroup_similar_image;
            } else {
                i2 = R.string.group_similar_image;
            }
            menuItem.setTitle(i2);
        }

        public boolean supportCreateMenu() {
            if (TimelinePresenter.this.isTimelineYearOrMonthForViewing() || !super.supportCreateMenu()) {
                return false;
            }
            return true;
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            boolean z;
            boolean R = TimelinePresenter.this.isTimelineYearOrMonthForViewing();
            boolean z3 = !R;
            if (R || TimelinePresenter.this.isSelectionMode()) {
                z = false;
            } else {
                z = true;
            }
            setMenuVisibility(menu, (int) R.id.action_select, z);
            setMenuVisibility(menu, (int) R.id.action_create, z3);
            Optional.ofNullable(menu.findItem(R.id.action_similar)).ifPresent(new c(2));
        }
    }

    public TimelinePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void checkSimilarData() {
        SimpleThreadPool.getInstance().execute(new e(this, 1));
    }

    private int getCloudSyncMenuIndex() {
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            if (!this.mMigrationSupported) {
                return -1;
            }
            if (!this.mCloudEnabled || !this.mCloudSyncOn) {
                return 0;
            }
            return 1;
        } else if (CloudStateCompat.isEnabled()) {
            return this.mCloudSyncOn ? 1 : 0;
        } else {
            if (CloudStateCompat.isOneDriveLinkRequired()) {
                return 0;
            }
            return -1;
        }
    }

    private int getSimilarMenuResId() {
        return R.id.action_similar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.Function] */
    /* access modifiers changed from: private */
    public boolean isTimelineYearOrMonthForViewing() {
        return ((Boolean) Optional.ofNullable(this.mView.getAdapter()).map(new Object()).orElse(Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkSimilarData$2() {
        try {
            if (SimilarPhotoHelper.checkSimilarPhoto(getContext()) && !isDestroyed()) {
                ThreadUtil.postOnUiThread(new e(this, 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isTimelineYearOrMonthForViewing$1(PicturesViewAdapter picturesViewAdapter) {
        boolean z;
        if (picturesViewAdapter.isYear() || picturesViewAdapter.isMonthForViewing()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void onCloudMediaSyncStatusChanged(Object obj, Bundle bundle) {
        ((ITimelineView) this.mView).onCloudMediaSyncStatusChanged();
    }

    /* access modifiers changed from: private */
    public void onCloudServiceChanged(Object obj, Bundle bundle) {
        ((ITimelineView) this.mView).checkAndUpdateTipCard(false);
        ITimelineView iTimelineView = (ITimelineView) this.mView;
        Objects.requireNonNull(iTimelineView);
        ThreadUtil.postOnUiThread(new e(3, iTimelineView));
    }

    /* access modifiers changed from: private */
    public void onCloudSyncOnOffChanged(Object obj, Bundle bundle) {
        ((ITimelineView) this.mView).onCloudSyncOnOffChanged();
        ((ITimelineView) this.mView).checkAndUpdateTipCard(false);
        ITimelineView iTimelineView = (ITimelineView) this.mView;
        Objects.requireNonNull(iTimelineView);
        ThreadUtil.postOnUiThread(new e(3, iTimelineView));
    }

    /* access modifiers changed from: private */
    public void toggleSimilarPhotoMode() {
        String str;
        String str2;
        int i2;
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("toggleSimilarPhotoMode {");
        if (SimilarPhotoHelper.isSimilarPhotoMode()) {
            str = "similar";
        } else {
            str = "normal";
        }
        sb2.append(str);
        sb2.append("}");
        Log.d(stringCompat, sb2.toString());
        if (SimilarPhotoHelper.checkToShowSimilarPhotoToast()) {
            if (((ITimelineView) this.mView).getToolbar() == null || ((ITimelineView) this.mView).getAppbarLayout() == null) {
                i2 = 0;
            } else {
                i2 = ((ITimelineView) this.mView).getToolbar().getHeight() + ((ITimelineView) this.mView).getAppbarLayout().getVisibleHeight();
            }
            Utils.showToast(getActivity(), R.string.cannot_show_similar_photo, 49, 0, i2);
            return;
        }
        SimilarPhotoHelper.toggleSimilarPhotoMode();
        getBlackboard().postEvent(EventMessage.obtain(1072));
        ITimelineView iTimelineView = (ITimelineView) this.mView;
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_MENU_SIMILAR_PHOTO;
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineSimilarPhotoMode)) {
            str2 = "1";
        } else {
            str2 = "0";
        }
        iTimelineView.postAnalyticsLog(analyticsEventId, str2);
    }

    private void updateCloudSyncMenu(MenuDataBinding menuDataBinding) {
        boolean isMigrationSupportedInPref = CloudStateCompat.isMigrationSupportedInPref();
        boolean isEnabledInPref = CloudStateCompat.isEnabledInPref();
        boolean isSyncOnInPref = CloudStateCompat.isSyncOnInPref();
        int service = CloudStateCompat.getService();
        if (this.mMigrationSupported != isMigrationSupportedInPref || this.mCloudEnabled != isEnabledInPref || this.mCloudSyncOn != isSyncOnInPref || this.mCloudService != service) {
            this.mMigrationSupported = isMigrationSupportedInPref;
            this.mCloudEnabled = isEnabledInPref;
            this.mCloudSyncOn = isSyncOnInPref;
            this.mCloudService = service;
            MenuDataBinding.MenuData menuData = menuDataBinding.getMenuData(R.id.action_cloud_sync_timeline);
            if (menuData != null) {
                int cloudSyncMenuIndex = getCloudSyncMenuIndex();
                if (cloudSyncMenuIndex > -1) {
                    menuData.updateTitleArray();
                    menuData.setIndex((IntSupplier) new f(cloudSyncMenuIndex));
                    menuData.setVisible(true);
                } else {
                    menuData.setVisible(false);
                }
                if (CloudStateCompat.isLegacyServiceStatusRequired()) {
                    StringCompat stringCompat = this.TAG;
                    Log.d(stringCompat, "updateCloudSyncMenu supported=" + this.mMigrationSupported + ", enabled=" + this.mCloudEnabled + ", syncOn=" + this.mCloudSyncOn);
                    return;
                }
                StringCompat stringCompat2 = this.TAG;
                Log.d(stringCompat2, "updateCloudSyncMenu service=" + CloudStateCompat.getServiceName(service) + ", syncOn=" + this.mCloudSyncOn);
            }
        }
    }

    private void updateSimilarMenu(MenuDataBinding menuDataBinding, boolean z) {
        MenuDataBinding.MenuData menuData = menuDataBinding.getMenuData(getSimilarMenuResId());
        if (menuData != null) {
            menuData.setVisible(!z);
            menuData.setIndex(SimilarPhotoHelper.isSimilarPhotoMode() ? 1 : 0);
        }
    }

    /* access modifiers changed from: private */
    public void updateTipCard(Object obj, Bundle bundle) {
        ((ITimelineView) this.mView).checkAndUpdateTipCard(false);
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        if (Features.isEnabled(Features.SUPPORT_CMH_PROVIDER_PERMISSION)) {
            arrayList.add(new SubscriberInfo("global://setting/advanced/cmhProvider", new d(this, 0)));
        }
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
            arrayList.add(new SubscriberInfo("global://setting/advanced/LocationAuth", new d(this, 0)));
        }
        arrayList.add(new SubscriberInfo("event/secmp/migration", new d(this, 0)));
        arrayList.add(new SubscriberInfo("global://event/cloud/service/changed", new d(this, 1)));
        arrayList.add(new SubscriberInfo("global://cloud/media/sync/status/changed", new d(this, 2)));
        arrayList.add(new SubscriberInfo("cloud/sync/on/off/changed", new d(this, 3)));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://TimelineViewChanged", new d(this, 4)).setWorkingOnUI());
        ITimelineView iTimelineView = (ITimelineView) this.mView;
        Objects.requireNonNull(iTimelineView);
        arrayList.add(new SubscriberInfo("command://startScrollAndShrink", new C0391a(17, iTimelineView)).setWorkingOnUI());
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.pictures);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 111) {
            if (i2 == 1061) {
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "received migration state : " + ((Integer) eventMessage.obj).intValue());
                ((ITimelineView) this.mView).checkAndUpdateTipCard(false);
            } else if (i2 == 1072) {
                Log.d(this.TAG, "[Similar] received data changed");
                this.mBlackboard.publish("data://keep_divider_position", Boolean.TRUE);
                if (SimilarPhotoHelper.checkToShowSimilarPhotoAnim()) {
                    stopAllPreview(false);
                    ((ITimelineView) this.mView).operateSimilarPhoto();
                }
                forceReloadData();
            } else if (i2 != 1076) {
                if (i2 != 2009) {
                    if (i2 == 1104 || i2 == 1105) {
                        Log.d(this.TAG, "receive smartswitch restore start or completed");
                        ((ITimelineView) this.mView).updateSmartSwitchView();
                    }
                } else if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    forceReloadData();
                    return true;
                }
                return super.handleEvent(eventMessage);
            } else if (Features.isEnabled(Features.SUPPORT_SD_CARD_ERRORS_TIP_CARD)) {
                if (eventMessage.arg1 == 0) {
                    PreferenceCache.SdCardErrorTipCardCount.clear();
                    PreferenceCache.SdCardErrorTipCardTime.clear();
                }
                ((ITimelineView) this.mView).checkAndUpdateTipCard(false);
            }
        } else if (((ITimelineView) this.mView).isActive() && ((ITimelineView) this.mView).getAdapter() != null && ((ITimelineView) this.mView).getAdapter().isYear()) {
            ((ITimelineView) this.mView).getAdapter().notifyDataSetChanged();
        }
        return true;
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        super.onEnterSelectionMode(obj, bundle);
        if (isMenuInitRequired()) {
            initMenu();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != getSimilarMenuResId() || !SimilarPhotoHelper.supportSimilarPhoto() || !((ITimelineView) this.mView).getListView().supportSimilarPhoto()) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (!SimilarPhotoHelper.isSimilarPhotoMode()) {
            checkSimilarData();
            return true;
        }
        toggleSimilarPhotoMode();
        return true;
    }

    public void onTopOverScroll(int i2) {
        FindHiddenFiles.execute();
        RecoverFiles.execute();
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            boolean isTimelineYearOrMonthForViewing = isTimelineYearOrMonthForViewing();
            updateSimilarMenu(menuDataBinding, isTimelineYearOrMonthForViewing);
            menuDataBinding.setVisible((int) R.id.action_select, !isTimelineYearOrMonthForViewing);
            updateCloudSyncMenu(menuDataBinding);
        }
        super.prepareOptionsMenu(menu);
    }

    public boolean showDeleteAllWarning() {
        return isSelectAll();
    }

    public void updateToolbar(Toolbar toolbar) {
        if (!((ITimelineView) this.mView).supportSmartAlbum()) {
            ((ITimelineView) this.mView).getAppbarLayout().setTitle((CharSequence) toolbar.getContext().getString(R.string.pictures));
        }
        toolbar.setTitle((CharSequence) null);
        toolbar.setSubtitle((CharSequence) null);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new TimelineMenuUpdater(v);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$updateCloudSyncMenu$3(int i2) {
        return i2;
    }
}
