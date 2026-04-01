package com.samsung.android.gallery.app.ui.list.albums.folder;

import A4.W;
import C4.l;
import C4.m;
import C4.n;
import D3.i;
import G4.a;
import N2.j;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.album.FolderGroupCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragPresenter;
import com.samsung.android.gallery.app.ui.list.albums.folder.IFolderView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.MenuSupportHelper;
import com.samsung.android.gallery.app.ui.menu.list.FolderMenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Stack;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FolderViewPresenter<V extends IFolderView> extends AlbumsDragPresenter<V> {
    private final HashMap<String, Integer> mLastScrollPosition = new HashMap<>();
    private final Stack<String> mLocationKeyStack = new Stack<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class FoldersMenuUpdater extends AlbumsBasePresenter<V>.AlbumsMenuUpdater {
        public FoldersMenuUpdater(V v) {
            super(v);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$0(long j2) {
            if ((j2 & 32) == 0 || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$1(long j2) {
            if ((j2 & 8) == 0 || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$2(long j2) {
            if ((j2 & 16) == 0 || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$3(MediaItem[] mediaItemArr) {
            return FolderViewPresenter.this.supportDeleteAlbum(mediaItemArr);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$4(int i2, long j2) {
            if (i2 <= 0 || !supportAlbumShare(j2)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$5(int i2, MediaItem[] mediaItemArr) {
            if (i2 != 1 || !FolderViewPresenter.this.supportRename(mediaItemArr)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$6(int i2, MediaItem[] mediaItemArr) {
            if (i2 != 1 || !FolderViewPresenter.this.supportChangeCover(mediaItemArr)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$7(int i2, MediaItem[] mediaItemArr) {
            if (i2 != 1 || mediaItemArr[0].isFolder()) {
                return false;
            }
            return true;
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            if (((IFolderView) FolderViewPresenter.this.mView).isMoveMode()) {
                FolderViewPresenter.this.setMoveMenu(menu);
            } else if (FolderViewPresenter.this.isSelectionMode() || FolderViewPresenter.this.isOnAdvancedMouseMultiFocused() || FolderViewPresenter.this.isPopupMenuItemFocused()) {
                int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
                MediaItem[] selectedItems = ((IFolderView) FolderViewPresenter.this.mView).getSelectedItems();
                long supportForGroup = MenuSupportHelper.getSupportForGroup(selectedItems);
                setMenuVisibility(menu, (int) R.id.action_move, (BooleanSupplier) new l(supportForGroup, 3));
                setMenuVisibility(menu, (int) R.id.action_folder_grouping, (BooleanSupplier) new l(supportForGroup, 4));
                setMenuVisibility(menu, (int) R.id.action_folder_ungrouping, (BooleanSupplier) new l(supportForGroup, 5));
                setMenuVisibility(menu, (int) R.id.action_delete_album_in_list, (BooleanSupplier) new W(3, this, selectedItems));
                setMenuVisibility(menu, (int) R.id.action_share_album, (BooleanSupplier) new m(this, selectedItemCountForMenuUpdate, supportForGroup, 1));
                setMenuVisibility(menu, (int) R.id.action_rename_folder_album, (BooleanSupplier) new a(this, selectedItemCountForMenuUpdate, selectedItems, 0));
                setMenuVisibility(menu, (int) R.id.action_change_cover_image, (BooleanSupplier) new a(this, selectedItemCountForMenuUpdate, selectedItems, 1));
                if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    setMenuVisibility(menu, (int) R.id.action_lock_album, (BooleanSupplier) new n(selectedItemCountForMenuUpdate, selectedItems, 1));
                }
                checkMoveToKnoxMenu(menu, selectedItemCountForMenuUpdate);
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            super.updatePopupMenuAction(menu, selectionMode, i2);
            setMenuVisibility(menu, (int) R.id.action_select, !FolderViewPresenter.this.isSelectionMode());
        }
    }

    public FolderViewPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private boolean isFirstDepth(String str) {
        if (str.indexOf(47, 24) == -1) {
            return true;
        }
        return false;
    }

    private boolean isSameLocationKey(String str) {
        String locationKey = getLocationKey();
        if (locationKey == null || !locationKey.equals(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSubTitle$1(Toolbar toolbar) {
        if (getContext() != null) {
            String makeSubTitle = makeSubTitle();
            CharSequence subtitle = toolbar.getSubtitle();
            if (makeSubTitle == null || subtitle == null || !makeSubTitle.contentEquals(subtitle)) {
                toolbar.setSubtitle((CharSequence) makeSubTitle);
            }
            if (((IFolderView) this.mView).getAppbarLayout() != null) {
                CharSequence subTitle = ((IFolderView) this.mView).getAppbarLayout().getSubTitle();
                if (makeSubTitle == null || subTitle == null || !makeSubTitle.contentEquals(subTitle)) {
                    ((IFolderView) this.mView).getAppbarLayout().setSubtitle(makeSubTitle);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateToolbar$0(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar);
    }

    private String makeSubTitle() {
        int i2;
        int i7;
        MediaItem currentItem = getCurrentItem();
        if (currentItem == null) {
            i2 = 0;
        } else {
            i2 = currentItem.getItemsInFolder().length;
        }
        if (i2 == 0) {
            return null;
        }
        int i8 = 0;
        for (MediaItem isFolder : currentItem.getItemsInFolder()) {
            if (isFolder.isFolder()) {
                i8++;
            }
        }
        if (i2 == i8) {
            return getContext().getResources().getQuantityString(R.plurals.number_of_group_count, i8, new Object[]{Integer.valueOf(i8)});
        }
        if (i8 == 0) {
            return getContext().getResources().getQuantityString(R.plurals.number_of_album_group_count, i2, new Object[]{Integer.valueOf(i2)});
        }
        if (i8 == 1 && (i7 = i2 - i8) >= 1) {
            return getContext().getResources().getQuantityString(R.plurals.one_group_and_albums, i7, new Object[]{Integer.valueOf(i7)});
        }
        if (i8 <= 1 || i2 - i8 != 1) {
            return getContext().getResources().getString(R.string.groups_and_albums, new Object[]{Integer.valueOf(i8), Integer.valueOf(i2 - i8)});
        }
        return getContext().getResources().getQuantityString(R.plurals.groups_and_one_albums, i8, new Object[]{Integer.valueOf(i8)});
    }

    /* access modifiers changed from: private */
    public void onAlbumsAdded(Object obj, Bundle bundle) {
        if (obj != null) {
            ((IFolderView) this.mView).onAlbumsAdded((int[]) obj);
        }
        forceReloadData();
    }

    /* access modifiers changed from: private */
    public void onExitMoveMode(Object obj, Bundle bundle) {
        if (!((Boolean) obj).booleanValue()) {
            ((IFolderView) this.mView).getListView().checkIfEmpty();
        }
    }

    /* access modifiers changed from: private */
    public void onMoveToGroup(Object obj, Bundle bundle) {
        String str = (String) obj;
        this.mLocationKeyStack.clear();
        int i2 = 24;
        while (true) {
            int indexOf = str.indexOf(47, i2);
            if (indexOf != -1) {
                String substring = str.substring(0, indexOf);
                this.mLocationKeyStack.push(substring);
                i2 = indexOf + 1;
                if (substring == null) {
                    break;
                }
            } else {
                this.mLocationKeyStack.push(ArgumentsUtil.removeArgs(str));
                break;
            }
        }
        ((IFolderView) this.mView).refreshFolder(ArgumentsUtil.removeArg(str, "from_bixby"), 0);
    }

    private void refreshFolderWithKey(String str) {
        refreshFolderWithKey(str, 0);
    }

    private Object updateSubTitle(Toolbar toolbar) {
        ThreadUtil.postOnUiThread(new d(this, toolbar));
        return null;
    }

    public void addAlbumsToFolder() {
        new FolderGroupCmd().execute(this, getSelectedItems(), Boolean.FALSE);
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_folder);
        MenuDataBinder.bindActionViewAs(menuDataBinding, this.mBlackboard);
        return menuDataBinding;
    }

    public MenuHandler createMenuHandler() {
        return new FolderMenuHandler();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("data://useradd_items_to_folder", new a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://MoveToGroup", new a(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ExitMoveMode", new a(this, 2)).setWorkingOnUI());
    }

    public void enterMoveMode(Object obj, Bundle bundle) {
        super.enterMoveMode(obj, bundle);
        if (!PreferenceFeatures.OneUi40.ALBUM_MOVE_FIRST_DEPTH) {
            return;
        }
        if (((IFolderView) this.mView).isDisplayWithDrawer()) {
            this.mBlackboard.publish("command://RemoveChildFragment", Boolean.TRUE);
        } else {
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    public MediaItem getCurrentItem() {
        return (MediaItem) getBlackboard().read("data://current_folder", null);
    }

    public String getFolderLocationKey() {
        return ((IFolderView) this.mView).getFolderLocationKey();
    }

    public String getLabelForAccessibility(Context context) {
        return (String) Optional.ofNullable(getCurrentItem()).map(new i(27)).orElse((Object) null);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 101) {
            forceReloadData();
            return false;
        } else if (i2 == 2007) {
            addAlbumsToFolder();
            return true;
        } else if (i2 != 2008) {
            return super.handleEvent(eventMessage);
        } else {
            String str = (String) eventMessage.obj;
            if (isSameLocationKey(str)) {
                return true;
            }
            String f = j.f(new StringBuilder(), getFolderLocationKey(), "/", ArgumentsUtil.getArgValue(str, "id"));
            this.mLocationKeyStack.clear();
            this.mLocationKeyStack.push(str);
            this.mLastScrollPosition.put(((IFolderView) this.mView).getFolderLocationKey(), 0);
            refreshFolderWithKey(f);
            setLocationKey(str);
            return true;
        }
    }

    public boolean isDepthIn() {
        if (this.mLocationKeyStack.size() > 1) {
            return true;
        }
        return false;
    }

    public void notifyDataChanged(MediaData mediaData) {
        updateToolbar(((IFolderView) this.mView).getToolbar());
        int count = mediaData.getCount();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "notifyDataChanged " + count);
    }

    public boolean onBackPressed() {
        int i2 = 0;
        if (this.mLocationKeyStack.size() <= 1) {
            return false;
        }
        this.mLocationKeyStack.pop();
        String peek = this.mLocationKeyStack.peek();
        Integer remove = this.mLastScrollPosition.remove(peek);
        if (remove != null) {
            i2 = remove.intValue();
        }
        refreshFolderWithKey(peek, i2);
        return true;
    }

    public void onFolderCreationFailed(boolean z, boolean z3) {
        if (!z) {
            ThreadUtil.postOnUiThreadDelayed(new b(this), 600);
        } else if (z3) {
            forceReloadData();
        } else {
            refreshFolderWithKey(getFolderLocationKey());
        }
        super.onFolderCreationFailed(z, z3);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            String str = getFolderLocationKey() + "/" + mediaItem.getFolderID();
            this.mLocationKeyStack.push(str);
            this.mLastScrollPosition.put(((IFolderView) this.mView).getFolderLocationKey(), Integer.valueOf(((IFolderView) this.mView).getListView().findFirstCompletelyVisibleItemPositionCompat()));
            refreshFolderWithKey(str);
            return;
        }
        super.onListItemClickInternal(i2, mediaItem);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        String folderLocationKey = ((IFolderView) this.mView).getFolderLocationKey();
        if (!isFirstDepth(folderLocationKey)) {
            onMoveToGroup(getLocationKey(), (Bundle) null);
        } else if (this.mLocationKeyStack.isEmpty() || !this.mLocationKeyStack.peek().equals(folderLocationKey)) {
            this.mLocationKeyStack.push(folderLocationKey);
        }
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding == null) {
            Log.e(this.TAG, "fail prepare options menu");
            return;
        }
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename_folder_album) {
            public boolean getDefaultVisibility() {
                return !isUpsm();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_cover_image) {
            public boolean getDefaultVisibility() {
                return !isUpsm();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_folder_add_album) {
            public boolean getDefaultVisibility() {
                return !isUpsm();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_folder_ungrouping) {
            public boolean getDefaultVisibility() {
                return !isUpsm();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_folder_add_folder) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_album_view_new_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        MenuDataBinder.bindActionShareAlbums(menuDataBinding);
        MenuDataBinder.bindKnoxDataBinding(menuDataBinding);
        super.prepareOptionsMenu(menu);
    }

    public void updateToolbar(Toolbar toolbar) {
        MediaItem currentItem = getCurrentItem();
        if (currentItem != null) {
            String folderName = currentItem.getFolderName();
            GalleryAppBarLayout appbarLayout = ((IFolderView) this.mView).getAppbarLayout();
            if (folderName != null && !isSelectionMode()) {
                if (appbarLayout.getTitle() == null || !folderName.contentEquals(appbarLayout.getTitle())) {
                    appbarLayout.setTitle((CharSequence) folderName);
                }
                if (toolbar.getTitle() == null || !folderName.contentEquals(toolbar.getTitle())) {
                    toolbar.setTitle((CharSequence) folderName);
                    toolbar.setSubtitle((CharSequence) " ");
                }
            }
        }
        if (!isSelectionMode()) {
            if (!((IFolderView) this.mView).isDrawerMode()) {
                setNavigationUpButton(toolbar);
            }
            ThreadPool.getInstance().submit(new c(this, toolbar));
        }
    }

    private void refreshFolderWithKey(String str, int i2) {
        ((IFolderView) this.mView).refreshFolder(ArgumentsUtil.appendArgs(str, "id", str.substring(str.lastIndexOf(47) + 1)), i2);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new FoldersMenuUpdater(v);
    }
}
