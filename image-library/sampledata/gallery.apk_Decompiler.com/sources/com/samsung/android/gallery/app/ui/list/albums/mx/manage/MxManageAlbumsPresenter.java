package com.samsung.android.gallery.app.ui.list.albums.mx.manage;

import E7.c;
import K4.a;
import android.content.Context;
import android.view.Menu;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumLevelCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.IMxManageAlbumsView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxManageAlbumsPresenter<V extends IMxManageAlbumsView> extends AlbumsPresenter<V> {
    private boolean mFirstSet = true;
    private final ConcurrentHashMap<MediaItem, Integer> mUpdateValues = new ConcurrentHashMap<>();

    public MxManageAlbumsPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        setOnSelectionListener(new a(3, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAllItemsSelected$0(boolean z, MediaItem mediaItem) {
        this.mUpdateValues.put(mediaItem, Integer.valueOf(z ? 1 : 0));
    }

    /* access modifiers changed from: private */
    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr == null) {
            return false;
        }
        new ChangeAlbumLevelCmd().execute(this, this.mUpdateValues);
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        return true;
    }

    private void updateSelectedItems(MediaItem[] mediaItemArr) {
        if (this.mFirstSet) {
            for (int i2 = 0; i2 < mediaItemArr.length; i2++) {
                if (mediaItemArr[i2].isAlbumLvFirst()) {
                    ((IMxManageAlbumsView) this.mView).getAdapter().selectItem(i2, true, true);
                    ((IMxManageAlbumsView) this.mView).getAdapter().notifySelectedItemChanged(i2, i2);
                }
            }
            this.mFirstSet = false;
        }
    }

    private void updateValuesMap(MediaItem mediaItem, int i2) {
        if (!this.mFirstSet && this.mUpdateValues.remove(mediaItem) == null) {
            this.mUpdateValues.put(mediaItem, Integer.valueOf(i2));
        }
    }

    public MenuDataBinding createFullMenuDataBinding() {
        return createMenuDataBinding();
    }

    public MenuDataBinding createMenuDataBinding() {
        return new MenuDataBinding(R.menu.menu_done);
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.select_essential_albums);
    }

    public void onAllItemsSelected(boolean z) {
        MediaData mediaData = getMediaData();
        if (mediaData != null) {
            int count = mediaData.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                Optional.ofNullable(mediaData.read(i2)).ifPresent(new c(this, z, 1));
            }
        }
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        updateSelectedItems(getAllItems());
    }

    public void onItemSelected(int i2, boolean z) {
        updateValuesMap(getMediaData().read(i2), z ? 1 : 0);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new MxManageAlbumsMenuUpdater(v, this);
    }

    public void prepareBottomMenu(Menu menu) {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MxManageAlbumsMenuUpdater extends ListMenuUpdater {
        public MxManageAlbumsMenuUpdater(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
            super(iBaseListView, iMenuDelegation);
        }

        public void updateOptionsMenuActionDone(Menu menu, MenuDataBinding.SelectionMode selectionMode, MenuDataBinding.ItemMode itemMode) {
        }
    }
}
