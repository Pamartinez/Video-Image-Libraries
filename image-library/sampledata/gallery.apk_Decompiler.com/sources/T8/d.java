package t8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.mp.impl.PeoplePetImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PeoplePetImpl e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ Query g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2708h;

    public /* synthetic */ d(PeoplePetImpl peoplePetImpl, Cursor[] cursorArr, Query query, String str, int i2) {
        this.d = i2;
        this.e = peoplePetImpl;
        this.f = cursorArr;
        this.g = query;
        this.f2708h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getPeopleAndPetsCursor$4(this.f, this.g, this.f2708h);
                return;
            default:
                this.e.lambda$getPeopleAndPetsCursor$5(this.f, this.g, this.f2708h);
                return;
        }
    }
}
