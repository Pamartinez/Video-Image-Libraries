package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureSelectPresenter<V extends ICreatureSelectView> extends CategoryPresenter<V> {
    private boolean mDataPrepared;
    protected ArrayList<String> mSelectedCreatures = new ArrayList<>();

    public CreatureSelectPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public String getDefaultToolbarTitle(Context context) {
        return context.getString(R.string.people_to_include_in_album);
    }

    public int getSelectedCount() {
        return this.mSelectedCreatures.size();
    }

    public ArrayList<String> getSelectedCreatures() {
        return this.mSelectedCreatures;
    }

    public String getTitle() {
        if (!this.mDataPrepared || getContext() == null) {
            return "";
        }
        int selectedCount = getSelectedCount();
        if (selectedCount > 0) {
            return getContext().getResources().getQuantityString(R.plurals.n_selected, selectedCount, new Object[]{Integer.valueOf(selectedCount)});
        }
        return getDefaultToolbarTitle(getContext());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 8001) {
            return super.handleEvent(eventMessage);
        }
        selectCreatures();
        return true;
    }

    public boolean isAllowItemClick() {
        return true;
    }

    public boolean isEnableSearchToolbar() {
        return false;
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        this.mDataPrepared = true;
        updateToolbar(getToolbar());
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (this.mSelectedCreatures.contains(mediaItem.getSubCategory())) {
            unselect(mediaItem);
        } else {
            select(mediaItem);
        }
        ((ICreatureSelectView) this.mView).getAdapter().onUpdateViewHolder(i2);
        updateToolbar(getToolbar());
        ((ICreatureSelectView) this.mView).invalidateOptionsMenu();
    }

    public void onLocationKeyAssigned() {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "creatures_ids", "");
        if (TextUtils.isEmpty(argValue)) {
            this.mSelectedCreatures = new ArrayList<>();
        } else {
            this.mSelectedCreatures = (ArrayList) Stream.of(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).collect(Collectors.toList());
        }
    }

    public void select(MediaItem mediaItem) {
        this.mSelectedCreatures.add(mediaItem.getSubCategory());
    }

    public void selectCreatures() {
        ICreatureSelectView iCreatureSelectView = (ICreatureSelectView) this.mView;
        Objects.requireNonNull(iCreatureSelectView);
        ThreadUtil.runOnUiThread(new b(15, iCreatureSelectView));
    }

    public void unselect(MediaItem mediaItem) {
        this.mSelectedCreatures.remove(mediaItem.getSubCategory());
    }

    public void updateToolbar(Toolbar toolbar) {
        setNavigationUpButton(toolbar);
        String title = getTitle();
        toolbar.setTitle((CharSequence) title);
        if (((ICreatureSelectView) this.mView).getAppbarLayout() != null) {
            ((ICreatureSelectView) this.mView).getAppbarLayout().setTitle((CharSequence) title);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public boolean isVisibleSelectDoneMenu() {
                return !CreatureSelectPresenter.this.mSelectedCreatures.isEmpty();
            }
        };
    }
}
