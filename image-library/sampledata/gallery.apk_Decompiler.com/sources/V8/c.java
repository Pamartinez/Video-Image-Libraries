package V8;

import com.samsung.android.gallery.database.dal.mp.helper.CreatureApi;
import com.samsung.android.gallery.database.dal.mp.helper.PeopleApi;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.function.LongPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements LongPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2888a;

    public /* synthetic */ c(int i2) {
        this.f2888a = i2;
    }

    public final boolean test(long j2) {
        switch (this.f2888a) {
            case 0:
                return PeopleDataManager.lambda$updatePeopleDataHolder$3(j2);
            case 1:
                return CreatureApi.lambda$updateByUnifiedId$1(j2);
            case 2:
                return IdentityCreatureUtil.isAssignedCreature(j2);
            default:
                return PeopleApi.lambda$updatePersonId$0(j2);
        }
    }
}
