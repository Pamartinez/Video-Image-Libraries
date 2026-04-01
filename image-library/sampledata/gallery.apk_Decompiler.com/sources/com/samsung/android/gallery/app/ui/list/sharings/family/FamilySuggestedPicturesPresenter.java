package com.samsung.android.gallery.app.ui.list.sharings.family;

import Ad.C0720a;
import J6.c;
import L5.b;
import M9.o;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.sharing.RemoveContentsFromSuggestionCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.family.IFamilySuggestedPicturesView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FamilySuggestedPicturesPresenter<V extends IFamilySuggestedPicturesView> extends PicturesPresenter<V> {
    private int mFamilyAlbumId;
    private String mGroupId;
    /* access modifiers changed from: private */
    public boolean mIsNormalMode;
    private String mSpaceId;

    public FamilySuggestedPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void addContentsToSharedAlbum() {
        if (!TextUtils.isEmpty(this.mGroupId) && !TextUtils.isEmpty(this.mSpaceId) && getSelectedItems().length > 0) {
            new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_ADD_CONTENTS, this.mSpaceId, this.mGroupId, getSelectedItems());
        }
    }

    private ArrayList<Long> getIdStringFromItems(MediaItem[] mediaItemArr) {
        return (ArrayList) Arrays.stream(mediaItemArr).map(new b(21)).distinct().collect(Collectors.toCollection(new C0720a(1)));
    }

    private void initData() {
        this.mFamilyAlbumId = UnsafeCast.toInt(ArgumentsUtil.getArgValue(getLocationKey(), "id"));
        this.mGroupId = ArgumentsUtil.getArgValue(getLocationKey(), "groupId");
        this.mSpaceId = ArgumentsUtil.getArgValue(getLocationKey(), BundleKey.SPACE_ID);
        this.mIsNormalMode = ArgumentsUtil.getArgValue(getLocationKey(), "support_normal_mode", false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hasPeopleList$1(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch) {
        atomicBoolean.set(!AutoAlbumHelper.getInstance().getSubscribePeopleList((long) this.mFamilyAlbumId).isEmpty());
        countDownLatch.countDown();
    }

    private void removeContentsFromAutoAlbum(boolean z) {
        if (this.mFamilyAlbumId > 0 && getSelectedItems().length > 0) {
            AutoAlbumHelper.getInstance().removeAutoAlbumContents(getIdStringFromItems(getSelectedItems()), (long) this.mFamilyAlbumId);
        }
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(101));
        this.mBlackboard.postEvent(EventMessage.obtain(1003));
        if (z) {
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    /* access modifiers changed from: private */
    public void setFamilySuggestedTipCardCondition() {
        PreferenceCache preferenceCache = PreferenceCache.SharedFamilyAlbumTipCardStep;
        int i2 = preferenceCache.getInt();
        if (i2 <= 3) {
            preferenceCache.setInt(Math.max(i2, getDataCount() / 50) + 1);
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        if (this.mIsNormalMode) {
            MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_family_suggested_pictures);
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_add_to_album, true, false));
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_remove_from_suggestions, true, false));
            return menuDataBinding;
        }
        MenuDataBinding menuDataBinding2 = new MenuDataBinding(R.menu.menu_selection_done);
        menuDataBinding2.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_done, true, false));
        return menuDataBinding2;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 6018) {
            super.handleEvent(eventMessage);
            return true;
        }
        removeContentsFromAutoAlbum(false);
        return true;
    }

    public boolean hasPeopleList() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SimpleThreadPool.getInstance().execute(new c(this, atomicBoolean, countDownLatch, 16));
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return atomicBoolean.get();
    }

    public boolean isNormalMode() {
        return this.mIsNormalMode;
    }

    public void launchPeopleSelectView(View view) {
        this.mBlackboard.post("command://MoveURL", MdeAlbumHelper.buildCreatureSelectForEmptyFamilyAlbumLocation(this.mFamilyAlbumId, this.mGroupId, this.mSpaceId, true));
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        ThreadUtil.postOnBgThread(new o(3, this));
    }

    public void onLocationKeyAssigned() {
        initData();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean z;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_add_to_album) {
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_ADD_SUGGESTED_ITEMS);
        } else if (itemId != R.id.action_done) {
            if (itemId != R.id.action_remove_from_suggestions) {
                return super.onOptionsItemSelected(menuItem);
            }
            new RemoveContentsFromSuggestionCmd().execute(this, getSelectedItems());
            return true;
        }
        addContentsToSharedAlbum();
        if (menuItem.getItemId() == R.id.action_done) {
            z = true;
        } else {
            z = false;
        }
        removeContentsFromAutoAlbum(z);
        return true;
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) toolbar.getContext().getString(R.string.suggested_pictures_and_videos_header));
        setNavigationUpButton(toolbar);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            /* access modifiers changed from: private */
            public /* synthetic */ boolean lambda$updateBottomBarMenuAction$1(int i2) {
                if (!this.mInterface.isSelectionMode() || i2 <= 0 || !getAddToSharedAlbumVisibility(i2)) {
                    return false;
                }
                return true;
            }

            /* access modifiers changed from: private */
            public /* synthetic */ boolean lambda$updateOptionsMenuAction$0() {
                if (FamilySuggestedPicturesPresenter.this.getSelectedItemCount() > 0) {
                    return true;
                }
                return false;
            }

            public void updateBottomBarMenuAction(Menu menu) {
                if (FamilySuggestedPicturesPresenter.this.mIsNormalMode) {
                    setMenuVisibility(menu, (int) R.id.action_add_to_album, (BooleanSupplier) new a(this, FamilySuggestedPicturesPresenter.this.getSelectedItemCount()));
                    super.updateBottomBarMenuAction(menu);
                }
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                if (!FamilySuggestedPicturesPresenter.this.mIsNormalMode) {
                    setMenuVisibility(menu, (int) R.id.action_done, (BooleanSupplier) new b(this));
                }
            }
        };
    }
}
