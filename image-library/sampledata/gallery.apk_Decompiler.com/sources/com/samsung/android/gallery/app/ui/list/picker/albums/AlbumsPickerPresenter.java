package com.samsung.android.gallery.app.ui.list.picker.albums;

import C3.C0391a;
import T3.a;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.picker.AlbumPickerMenuFactory;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumsPickerView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.AlbumPickerMenuHandler;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPickerPresenter<V extends IAlbumsPickerView> extends AlbumsBasePresenter<V> {
    private final HashSet<AlbumType> mDisabledAlbumType = new HashSet<>();
    private final LaunchIntent mLaunchIntent = ((LaunchIntent) this.mBlackboard.read("data://launch_intent"));
    private final LaunchModeType mLaunchModeType = ((LaunchModeType) this.mBlackboard.read("data://launch_mode_type"));
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    public AlbumsPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        updateDisableAlbumType(v.getLocationKey());
    }

    private boolean checkAvailableCount(MediaItem mediaItem) {
        if (mediaItem.isEmptyAlbum() || mediaItem.getCount() <= 0) {
            return false;
        }
        return true;
    }

    private boolean contains(List<MediaItem> list, String str) {
        return list.stream().anyMatch(new a(2, this, str));
    }

    private boolean isEnableFolder(MediaItem mediaItem) {
        if (isEmptyFolder(mediaItem)) {
            return false;
        }
        String includedPath = this.mLaunchIntent.getIncludedPath();
        if (includedPath == null || contains(mediaItem.getChildAlbums(), includedPath)) {
            return true;
        }
        return false;
    }

    private void updateDisableAlbumType(String str) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            try {
                String argValue = ArgumentsUtil.getArgValue(str, "disabledAlbumType", (String) null);
                if (argValue != null) {
                    for (String parseInt : argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                        AlbumType albumType = AlbumType.get(Integer.parseInt(parseInt));
                        if (albumType != AlbumType.None) {
                            this.mDisabledAlbumType.add(albumType);
                        }
                    }
                    Log.d(this.TAG, "Disabled AlbumType", argValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        return AlbumPickerMenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public MenuHandler createMenuHandler() {
        if (this.mLaunchModeType == LaunchModeType.ACTION_PICK || this.mLaunchIntent.supportAlbumCreation()) {
            return new AlbumPickerMenuHandler();
        }
        return super.createMenuHandler();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://FolderViewChanged", new C0391a(19, this)).setWorkingOnUI());
    }

    public MediaItem getCurrentItem() {
        return (MediaItem) getBlackboard().read("data://current_folder", null);
    }

    public LaunchModeType getLaunchMode() {
        return this.mLaunchModeType;
    }

    public String getRequestKey(int i2, MediaItem mediaItem) {
        return PickerUtil.appendPickerArgs(this.mBlackboard, super.getRequestKey(i2, mediaItem));
    }

    public void handleDensityChange() {
        this.mSearchToolbarDelegate.handleDensityChange();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
            return true;
        }
        return false;
    }

    public boolean isEnableAlbum(MediaItem mediaItem) {
        if (this.mDisabledAlbumType.contains(mediaItem.getAlbumType())) {
            return false;
        }
        String includedPath = this.mLaunchIntent.getIncludedPath();
        if (includedPath != null) {
            return lambda$contains$0(mediaItem, includedPath);
        }
        return checkAvailableCount(mediaItem);
    }

    public boolean isItemEnabled(MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            return isEnableFolder(mediaItem);
        }
        return isEnableAlbum(mediaItem);
    }

    public void moveToSearch() {
        if (((IAlbumsPickerView) this.mView).supportSmartAlbum()) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(14, Boolean.TRUE));
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (this.mLaunchModeType != LaunchModeType.ACTION_ALBUM_PICK || mediaItem.isFolder()) {
            super.onListItemClickInternal(i2, mediaItem);
        } else {
            this.mBlackboard.post("command://SinglePickerItemSelection", mediaItem);
        }
    }

    public void onViewChanged(Object obj, Bundle bundle) {
        if (!LocationKey.isFolder(getLocationKey())) {
            super.onViewChanged(obj, bundle);
        }
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.build(this);
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mSearchToolbarDelegate.onDestroy();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
    }

    public boolean supportLocalDatabaseUpdate() {
        if (PickerUtil.isPickerFilterMode(this.mBlackboard) || PickerUtil.isLocalContentOnly(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    public void updateToolbar(Toolbar toolbar) {
        if (LocationKey.isFolder(getLocationKey())) {
            setNavigationUpButton(toolbar);
            toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
            return;
        }
        toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
        toolbar.setSubtitle((CharSequence) null);
        toolbar.setNavigationIcon((Drawable) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: contains */
    public boolean lambda$contains$0(MediaItem mediaItem, String str) {
        String albumPath = mediaItem.getAlbumPath();
        return albumPath != null && albumPath.contains(str);
    }
}
