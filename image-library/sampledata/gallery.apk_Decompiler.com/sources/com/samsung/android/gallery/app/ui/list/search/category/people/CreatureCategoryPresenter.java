package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureCategoryView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.sec.android.gallery3d.R;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCategoryPresenter<V extends ICreatureCategoryView> extends CategoryPresenter<V> {
    private boolean mExistHiddenCreature;

    public CreatureCategoryPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkHiddenCreature$0() {
        boolean z = false;
        this.mExistHiddenCreature = false;
        int hiddenPeopleCount = new MpHelper().getHiddenPeopleCount();
        if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            if (hiddenPeopleCount > 0) {
                z = true;
            }
            this.mExistHiddenCreature = z;
        } else if (hiddenPeopleCount > 0) {
            this.mExistHiddenCreature = true;
        } else if (IdentityCreatureUtil.isPetRecognized()) {
            if (new MpHelper().getHiddenPetsCount() > 0) {
                z = true;
            }
            this.mExistHiddenCreature = z;
        }
    }

    public void checkHiddenCreature() {
        LatchBuilder.executeLatch(500, new b(13, this));
    }

    public void executeAddFaces() {
        launchHidePeopleAndPetsView();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8009) {
            ((ICreatureCategoryView) this.mView).editTop5Done();
            return true;
        } else if (i2 != 8022) {
            return super.handleEvent(eventMessage);
        } else {
            ((ICreatureCategoryView) this.mView).onSelectMeDone();
            return true;
        }
    }

    public boolean hasHiddenCreature() {
        return this.mExistHiddenCreature;
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(6, Boolean.FALSE));
        super.onEnterSelectionMode(obj, bundle);
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(6, Boolean.TRUE));
        super.onExitSelectionMode(obj, bundle);
    }

    public void updateToolbar(Toolbar toolbar) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(6, Boolean.valueOf(!((ICreatureCategoryView) this.mView).isTop5EditMode())));
        if (((ICreatureCategoryView) this.mView).isTop5EditMode()) {
            toolbar.setTitle((int) R.string.edit);
            if (((ICreatureCategoryView) this.mView).getAppbarLayout() != null) {
                ((ICreatureCategoryView) this.mView).getAppbarLayout().setTitle((int) R.string.edit);
                return;
            }
            return;
        }
        super.updateToolbar(toolbar);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public boolean isVisibleEditDoneMenu() {
                if (!((ICreatureCategoryView) CreatureCategoryPresenter.this.mView).isTop5EditMode() || !((ICreatureCategoryView) CreatureCategoryPresenter.this.mView).isTop5Changed()) {
                    return false;
                }
                return true;
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                boolean z;
                int i2;
                super.updateOptionsMenuAction(menu, selectionMode);
                if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
                    MenuItem findItem = menu.findItem(R.id.action_hide_people_and_pets);
                    if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                        i2 = R.string.add_people_and_pets_button;
                    } else {
                        i2 = R.string.add_people_button;
                    }
                    setMenuTitle(findItem, i2);
                }
                boolean z3 = false;
                if (((ICreatureCategoryView) CreatureCategoryPresenter.this.mView).isSelectionMode() || ((ICreatureCategoryView) CreatureCategoryPresenter.this.mView).isTop5EditMode() || !CreatureCategoryPresenter.this.hasHiddenCreature() || getDataCount() <= 0) {
                    z = false;
                } else {
                    z = true;
                }
                setMenuVisibility(menu, (int) R.id.action_hide_people_and_pets, z);
                if (!((ICreatureCategoryView) CreatureCategoryPresenter.this.mView).isSelectionMode() && !((ICreatureCategoryView) CreatureCategoryPresenter.this.mView).isTop5EditMode() && getDataCount() > 0) {
                    z3 = true;
                }
                setMenuVisibility(menu, (int) R.id.action_select, z3);
            }
        };
    }
}
