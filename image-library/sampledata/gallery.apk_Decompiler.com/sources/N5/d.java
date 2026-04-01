package n5;

import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreatureSelectPresenterV2 e;

    public /* synthetic */ d(CreatureSelectPresenterV2 creatureSelectPresenterV2, int i2) {
        this.d = i2;
        this.e = creatureSelectPresenterV2;
    }

    public final void run() {
        int i2 = this.d;
        CreatureSelectPresenterV2 creatureSelectPresenterV2 = this.e;
        switch (i2) {
            case 0:
                creatureSelectPresenterV2.lambda$saveSelectedCreature$0();
                return;
            default:
                creatureSelectPresenterV2.lambda$finish$12();
                return;
        }
    }
}
