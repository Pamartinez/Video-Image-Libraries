package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesPresenter extends BaseListPresenter<IRevitalizedView> {
    private boolean mIsPendingFinish = false;

    public RevitalizedPicturesPresenter(Blackboard blackboard, IRevitalizedView iRevitalizedView) {
        super(blackboard, iRevitalizedView);
    }

    public MenuDataBinding createMenuDataBinding() {
        return new MenuDataBinding(R.menu.menu_revitalized_pictures);
    }

    public MenuHandler createMenuHandler() {
        return new RevitalizedPicturesMenuHandler();
    }

    public int getCurrentViewDepth() {
        return ((IRevitalizedView) this.mView).getListView().getDepth();
    }

    public int getMaxDepth() {
        return ((IRevitalizedView) this.mView).getListView().getMaxDepth();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1111) {
            return super.handleEvent(eventMessage);
        }
        ((IRevitalizedView) this.mView).changeView();
        return true;
    }

    public void notifyDataChanged(MediaData mediaData) {
        if (mediaData.getCount() != 0) {
            return;
        }
        if (isViewPaused()) {
            this.mIsPendingFinish = true;
        } else {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        ThumbnailLoader.getInstance().clearMemoryCache(ThumbKind.LARGE_KIND);
    }

    public void onViewResume() {
        super.onViewResume();
        if (this.mIsPendingFinish) {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_view) {
                public int getDefaultIndex() {
                    return RevitalizedPicturesPresenter.this.getCurrentViewDepth();
                }

                public boolean getDefaultVisibility() {
                    return true;
                }

                public int[] getIconArray() {
                    return new int[]{R.drawable.tw_ic_suggestions_view_large, R.drawable.tw_ic_suggestions_view_small};
                }

                public int[] getTitleArray() {
                    return new int[]{R.string.large, R.string.small};
                }
            });
            MenuDataBinding.MenuData menuData = menuDataBinding.getMenuData(R.id.action_change_view);
            if (menuData != null) {
                menuData.setIndex(getCurrentViewDepth());
            }
        }
        super.prepareOptionsMenu(menu);
    }

    public void updateMenu() {
        MenuDataBinding.MenuData menuData;
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null && (menuData = menuDataBinding.getMenuData(R.id.action_change_view)) != null) {
            menuData.setIndex(getCurrentViewDepth());
            ((IRevitalizedView) this.mView).invalidateOptionsMenu();
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) null);
        setNavigationUpButton(toolbar);
    }
}
