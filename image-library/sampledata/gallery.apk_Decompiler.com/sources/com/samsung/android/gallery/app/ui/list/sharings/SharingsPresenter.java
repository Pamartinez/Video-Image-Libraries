package com.samsung.android.gallery.app.ui.list.sharings;

import C3.i;
import L5.d;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.mxalbum.SharedAlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSyncCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuVisibility;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingsPresenter extends BaseListPresenter<ISharingsView> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SharingsMenuUpdater extends ListMenuUpdater {
        public SharingsMenuUpdater(ISharingsView iSharingsView) {
            super(iSharingsView, SharingsPresenter.this);
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            if (i2 == 1) {
                MediaItem[] selectedItems = SharingsPresenter.this.getSelectedItems();
                if (selectedItems.length > 0) {
                    setMenuVisibility(menu, (int) R.id.action_rename_shared_album_in_list, PopupMenuVisibility.isActiveSharedAlbumRename(selectedItems[0]));
                    setMenuVisibility(menu, (int) R.id.action_delete_shared_album_in_list, PopupMenuVisibility.isActiveSharedAlbumDelete(selectedItems[0]));
                    setMenuVisibility(menu, (int) R.id.action_leave_shared_album_in_list, PopupMenuVisibility.isActiveSharedAlbumLeave(selectedItems[0]));
                }
            }
        }
    }

    public SharingsPresenter(Blackboard blackboard, ISharingsView iSharingsView) {
        super(blackboard, iSharingsView);
    }

    /* access modifiers changed from: private */
    public void completeSession(Object obj, Bundle bundle) {
        ((ISharingsView) this.mView).completeSession(((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public void downloadSharedAlbumCover(Object obj, Bundle bundle) {
        new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_DOWNLOAD_COVER, (MediaItem) obj);
    }

    /* access modifiers changed from: private */
    public void finishSharedAlbum(Object obj, Bundle bundle) {
        ((ISharingsView) getView()).finish();
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(this.mBlackboard);
    }

    public void createSharedAlbum() {
        new SharedAlbumCreatePopupDialogCmd().execute(this, new Object[0]);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://ConnectCompleteSession", new d(this, 0)).setWorkingOnUI());
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER)) {
            arrayList.add(new SubscriberInfo("data://download_shared_cover", new d(this, 1)).setWorkingOnUI());
        }
        if (Features.isEnabled(Features.SUPPORT_SHARED_PERMISSION_POPUP)) {
            arrayList.add(new SubscriberInfo("command://FinishSharingAlbum", new d(this, 2)).setWorkingOnUI());
        }
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.shared_albums);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1004) {
            updateAddContentMenu();
            return true;
        } else if (i2 == 1060) {
            ((ISharingsView) this.mView).printTransitionDebugInfo();
            return true;
        } else if (i2 == 6014) {
            return true;
        } else {
            if (i2 == 6002) {
                this.mMenuDelegation.updateNewBadgeForInvitation();
                return true;
            } else if (i2 != 6003) {
                return super.handleEvent(eventMessage);
            } else {
                createSharedAlbum();
                return true;
            }
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        AnalyticsEventId analyticsEventId;
        String groupId = MediaItemMde.getGroupId(mediaItem);
        this.mBlackboard.post("command://MoveURL", new UriBuilder("location://sharing/albums/fileList").appendArg("id", MediaItemMde.getSpaceId(mediaItem)).appendArg(Message.KEY_POSITION, i2).appendArg("groupId", groupId).appendArg("owner", MediaItemMde.isOwnedByMe(mediaItem)).build());
        this.mBlackboard.publish("data://albums/current", mediaItem);
        if (MdeGroupHelper.isSAFamilyGroup(groupId)) {
            analyticsEventId = AnalyticsEventId.EVENT_FAMILY_SHARED_VIEW_ENTER;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SHARED_VIEW_ENTER;
        }
        postAnalyticsLog(analyticsEventId);
    }

    public void onViewResume() {
        super.onViewResume();
        SimpleThreadPool.getInstance().execute(new i(8));
        if (getLocationKey().equals(this.mBlackboard.read("location://variable/currentv1"))) {
            new RequestSyncCmd().execute(this, getLocationKey());
        }
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_shared_album) {
                public boolean getDefaultEnabling() {
                    return !SharingsPresenter.this.isLowStorageMode();
                }

                public boolean getDefaultVisibility() {
                    if (((ISharingsView) SharingsPresenter.this.mView).getItemCount() > 0) {
                        return true;
                    }
                    return false;
                }
            });
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_sharings_storage_use) {
                public boolean getDefaultExcluding() {
                    return !PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE;
                }

                public boolean getDefaultVisibility() {
                    return MdeAuthHelper.isReadyToUseShareService();
                }
            });
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_sort_by_sharing) {
                public boolean getDefaultVisibility() {
                    return PreferenceFeatures.OneUi6x.SUPPORT_SHARING_SORT_BY;
                }
            });
        }
        this.mMenuDelegation.updateNewBadgeForInvitation();
        super.prepareOptionsMenu(menu);
    }

    public void updateAddContentMenu() {
        boolean z;
        if (getMenuDataBinding() != null) {
            MenuDataBinding menuDataBinding = getMenuDataBinding();
            if (((ISharingsView) this.mView).getItemCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            menuDataBinding.setVisible((int) R.id.action_create_shared_album, z);
            getMenuDataBinding().setEnabled(R.id.action_create_shared_album, !isLowStorageMode());
            ((ISharingsView) this.mView).invalidateOptionsMenu();
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        if (((ISharingsView) this.mView).isSupportExtendedAppBar()) {
            ((ISharingsView) this.mView).getAppbarLayout().setTitle((CharSequence) getContext().getString(R.string.shared_albums));
        }
        setNavigationUpButton(toolbar);
        toolbar.setTitle((int) R.string.shared_albums);
        toolbar.setSubtitle((CharSequence) null);
    }

    public ListMenuUpdater createMenuUpdateDelegation(ISharingsView iSharingsView) {
        return new SharingsMenuUpdater(iSharingsView);
    }
}
