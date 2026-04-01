package t8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.mp.impl.PeoplePetImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PeoplePetImpl e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ Query g;

    public /* synthetic */ c(PeoplePetImpl peoplePetImpl, Cursor[] cursorArr, Query query, int i2) {
        this.d = i2;
        this.e = peoplePetImpl;
        this.f = cursorArr;
        this.g = query;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getHiddenPeopleAndPetsCursor$2(this.f, this.g);
                return;
            case 1:
                this.e.lambda$getHiddenPeopleAndPetsCursor$3(this.f, this.g);
                return;
            case 2:
                this.e.lambda$getAllPeopleAndPetsCursor$0(this.f, this.g);
                return;
            default:
                this.e.lambda$getAllPeopleAndPetsCursor$1(this.f, this.g);
                return;
        }
    }
}
