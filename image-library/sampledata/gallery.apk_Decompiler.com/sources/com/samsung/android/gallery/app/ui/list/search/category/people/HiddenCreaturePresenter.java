package com.samsung.android.gallery.app.ui.list.search.category.people;

import A4.C0381p;
import Ad.C0720a;
import Gb.a;
import android.content.Context;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HiddenCreaturePresenter<V extends ICreatureSelectView> extends CreatureSelectPresenter<V> {
    public HiddenCreaturePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$selectCreatures$0(ThreadPool.JobContext jobContext) {
        PeopleDataManager.unHidePeople((ArrayList) this.mSelectedCreatures.stream().filter(new C0464a(14)).map(new a(14)).collect(Collectors.toCollection(new C0720a(1))));
        PetDataManager.updatePetHideState(new ArrayList(), (ArrayList) this.mSelectedCreatures.stream().filter(new C0464a(15)).map(new a(14)).collect(Collectors.toCollection(new C0720a(1))));
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$selectCreatures$1(Future future) {
        ICreatureSelectView iCreatureSelectView = (ICreatureSelectView) this.mView;
        Objects.requireNonNull(iCreatureSelectView);
        ThreadUtil.postOnUiThreadDelayed(new b(15, iCreatureSelectView), 300);
    }

    public String getDefaultToolbarTitle(Context context) {
        int i2;
        if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            i2 = R.string.hidden_faces;
        } else if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            i2 = R.string.add_people_and_pets_button;
        } else {
            i2 = R.string.add_people_button;
        }
        return context.getString(i2);
    }

    public void selectCreatures() {
        ThreadPool.getInstance().submit(new C0381p(9, this), new com.samsung.android.sdk.scs.ai.language.a(23, this));
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public int getSelectDoneMenuTitle() {
                if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
                    return R.string.add;
                }
                return R.string.unhide_faces;
            }

            public boolean isEnableSelectDoneMenu() {
                return !HiddenCreaturePresenter.this.mSelectedCreatures.isEmpty();
            }
        };
    }
}
