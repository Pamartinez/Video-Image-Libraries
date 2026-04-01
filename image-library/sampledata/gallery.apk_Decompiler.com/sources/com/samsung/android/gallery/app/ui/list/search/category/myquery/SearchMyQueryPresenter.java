package com.samsung.android.gallery.app.ui.list.search.category.myquery;

import Fa.C0558l;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.IMyQueryCategoryView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import ic.l;
import java.util.ArrayList;
import java.util.Optional;
import m5.C0486a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMyQueryPresenter<V extends IMyQueryCategoryView> extends CategoryPresenter<V> {
    private final ArrayList<Integer> mCandidatesForDeletion = new ArrayList<>();
    private AlertDialog mConfirmDialog;

    public SearchMyQueryPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveMyQueryChange$1() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_EDIT_SHORTCUTS_SAVE);
        ((IMyQueryCategoryView) this.mView).finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveMyQueryChange$2() {
        this.mCandidatesForDeletion.forEach(new l(28));
        publishDataChange();
        ThreadUtil.postOnUiThread(new C0486a(this, 1));
    }

    /* access modifiers changed from: private */
    public void onDialogClicked(DialogInterface dialogInterface, int i2) {
        Log.d(this.TAG, "onDialogClicked", Integer.valueOf(i2));
        if (i2 == -1) {
            saveMyQueryChange();
        } else if (i2 == -2) {
            discardMyQueryChange();
        }
        AlertDialog alertDialog = this.mConfirmDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.mConfirmDialog = null;
    }

    private void publishDataChange() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new l(29));
    }

    private void showConfirmDialog(Context context) {
        if (context != null) {
            AlertDialog create = new AlertDialog.Builder(context).setMessage(R.string.save_or_discard_change).setPositiveButton(R.string.save, new C0558l(9, this)).setNegativeButton(R.string.crop_back_key_confirm_dialog_discard, new C0558l(9, this)).setNeutralButton(R.string.cancel, new C0558l(9, this)).create();
            this.mConfirmDialog = create;
            create.show();
        }
    }

    public void discardMyQueryChange() {
        ((IMyQueryCategoryView) this.mView).finish();
    }

    public boolean hasCandidatesForDeletion() {
        return !this.mCandidatesForDeletion.isEmpty();
    }

    public boolean isConsumeBackPress() {
        if (!hasCandidatesForDeletion()) {
            return false;
        }
        showConfirmDialog(getContext());
        return true;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        this.mCandidatesForDeletion.add(Integer.valueOf(mediaItem.getAlbumID()));
        ((IMyQueryCategoryView) this.mView).removeItem(i2);
        ((IMyQueryCategoryView) this.mView).invalidateOptionsMenu();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_save) {
            saveMyQueryChange();
            return true;
        } else if (menuItem.getItemId() != R.id.action_cancel) {
            return true;
        } else {
            discardMyQueryChange();
            return true;
        }
    }

    public void saveMyQueryChange() {
        SimpleThreadPool.getInstance().execute(new C0486a(this, 0));
    }

    public void updateToolbar(Toolbar toolbar) {
        String categoryTitle = ((IMyQueryCategoryView) this.mView).getPropertyHelper().getCategoryTitle(getContext());
        toolbar.setTitle((CharSequence) categoryTitle);
        if (((IMyQueryCategoryView) this.mView).getAppbarLayout() != null) {
            ((IMyQueryCategoryView) this.mView).getAppbarLayout().setTitle((CharSequence) categoryTitle);
        }
        setNavigationUpButton(toolbar);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$updateOptionsMenuAction$0(MenuItem menuItem) {
                menuItem.setEnabled(SearchMyQueryPresenter.this.hasCandidatesForDeletion());
            }

            public boolean getCancelMenuVisibility() {
                return ((IMyQueryCategoryView) SearchMyQueryPresenter.this.mView).isLandscape();
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                boolean isLandscape = ((IMyQueryCategoryView) SearchMyQueryPresenter.this.mView).isLandscape();
                setMenuVisibility(menu, (int) R.id.action_cancel, isLandscape);
                setMenuVisibility(menu, (int) R.id.action_save, isLandscape);
                if (isLandscape) {
                    Optional.ofNullable(menu.findItem(R.id.action_save)).ifPresent(new a(this));
                }
            }
        };
    }
}
