package com.samsung.android.gallery.app.ui.list.search.category.people;

import J5.c;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import n5.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectMePresenter<V extends ICreatureSelectView> extends CreatureSelectPresenter<V> {
    private final ArrayList<MediaItem> mSelectedItems = new ArrayList<>();
    private final ArrayList<MediaItem> mUnSelectedItems = new ArrayList<>();

    public SelectMePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void assignMe(MediaItem mediaItem) {
        String computeNameIfAbsent = CreatureData.computeNameIfAbsent(mediaItem, new c(29, this));
        String top5Candidates = MyQueryCreator.getTop5Candidates(getBlackboard(), mediaItem.getSubCategory(), "recommended_id", computeNameIfAbsent, mediaItem.getCount());
        String createWithCreatureId = IdentityCreatureUtil.createWithCreatureId(PeopleDataManager.addName(mediaItem.getSubCategory(), new CreatureNameData.Builder("People", CreatureNameData.ContactType.TAGGED).setName(computeNameIfAbsent).setRelationship("me").build()), IdentityCreatureUtil.Category.PEOPLE);
        if (!TextUtils.isEmpty(top5Candidates)) {
            createWithCreatureId = C0212a.B(createWithCreatureId, GlobalPostProcInternalPPInterface.SPLIT_REGEX, top5Candidates);
        }
        MyQueryCreator.createTop5(createWithCreatureId);
    }

    private void clearAllItems() {
        this.mSelectedCreatures.clear();
        ((ICreatureSelectView) this.mView).getAdapter().notifyItemRangeChanged("update_cue");
        ((ICreatureSelectView) this.mView).invalidateOptionsMenu();
        updateToolbar(getToolbar());
    }

    private void handleMergeDone() {
        ThreadPool.getInstance().submit(new g(this, 1));
    }

    private void handleSingleItem() {
        MediaItem mediaItem = this.mSelectedItems.get(0);
        if (LocationKey.isSelectMeAll(getLocationKey())) {
            assignMe(mediaItem);
        } else {
            removeRelationship();
            MyQueryCreator.createTop5(getBlackboard(), mediaItem);
        }
        if (getContext() != null) {
            String str = CreatureData.of(mediaItem).creatureName;
            if (!TextUtils.isEmpty(str)) {
                Utils.showToast(getContext(), getContext().getString(R.string.saved_as_you, new Object[]{str}));
            }
        }
        notifyWithFinish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$assignMe$2() {
        return RelationshipKeySet.getRelationshipName(getContext(), "me");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$handleMergeDone$0(ThreadPool.JobContext jobContext) {
        removeRelationship();
        notifyWithFinish();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$selectMe$1(ThreadPool.JobContext jobContext) {
        updateSelectedItems();
        if (this.mSelectedItems.size() == 1) {
            handleSingleItem();
        } else {
            new MergeCreatureMultipleCmd().execute(this, null, null, Boolean.TRUE);
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8022));
        return null;
    }

    private void notifyWithFinish() {
        getBlackboard().postEvent(EventMessage.obtain(1003));
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    private void removeRelationship() {
        Iterator<MediaItem> it = this.mUnSelectedItems.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            PeopleDataManager.removeRelationship(next.getSubCategory(), CreatureData.of(next).relationshipType);
        }
    }

    private boolean selectMe() {
        ThreadPool.getInstance().submit(new g(this, 0));
        return true;
    }

    private void updateSelectedItems() {
        ArrayList<MediaItem> allData = getMediaData().getAllData();
        if (allData.isEmpty()) {
            allData = new ArrayList<>(Arrays.asList(getAllItems()));
        }
        this.mSelectedItems.clear();
        this.mUnSelectedItems.clear();
        Iterator<MediaItem> it = allData.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (this.mSelectedCreatures.contains(next.getSubCategory())) {
                this.mSelectedItems.add(next);
            } else {
                this.mUnSelectedItems.add(next);
            }
        }
    }

    public String getDefaultToolbarTitle(Context context) {
        return context.getString(R.string.select_your_face);
    }

    public MediaItem[] getSelectedItems() {
        return (MediaItem[]) this.mSelectedItems.toArray(new MediaItem[0]);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1003) {
            clearAllItems();
            return true;
        }
        if (i2 == 8010) {
            handleMergeDone();
        }
        return super.handleEvent(eventMessage);
    }

    public boolean isSelectionMode() {
        return true;
    }

    public void onDestroy() {
        getBlackboard().postEvent(EventMessage.obtain(1003));
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_set_as_me) {
            return selectMe();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public boolean getCancelMenuVisibility() {
                return !SelectMePresenter.this.mSelectedCreatures.isEmpty();
            }

            public MenuDataBinding.ItemMode getItemMode() {
                if (SelectMePresenter.this.mSelectedCreatures.isEmpty()) {
                    return MenuDataBinding.ItemMode.ANY_ITEM;
                }
                return MenuDataBinding.ItemMode.SELECTED_ITEM;
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                setMenuVisibility(menu, (int) R.id.action_set_as_me, !SelectMePresenter.this.mSelectedCreatures.isEmpty());
            }
        };
    }
}
