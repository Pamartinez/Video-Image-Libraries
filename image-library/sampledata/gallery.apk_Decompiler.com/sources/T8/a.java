package t8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CategoriesImpl e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ Query g;

    public /* synthetic */ a(CategoriesImpl categoriesImpl, Cursor[] cursorArr, Query query, int i2) {
        this.d = i2;
        this.e = categoriesImpl;
        this.f = cursorArr;
        this.g = query;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getShotModeCursor$0(this.f, this.g);
                return;
            case 1:
                this.e.lambda$getShotModeCursor$9(this.f, this.g);
                return;
            case 2:
                this.e.lambda$getShotModeCursor$1(this.f, this.g);
                return;
            case 3:
                this.e.lambda$getShotModeCursor$2(this.f, this.g);
                return;
            case 4:
                this.e.lambda$getShotModeCursor$3(this.f, this.g);
                return;
            case 5:
                this.e.lambda$getShotModeCursor$4(this.f, this.g);
                return;
            case 6:
                this.e.lambda$getShotModeCursor$5(this.f, this.g);
                return;
            case 7:
                this.e.lambda$getShotModeCursor$6(this.f, this.g);
                return;
            case 8:
                this.e.lambda$getShotModeCursor$7(this.f, this.g);
                return;
            default:
                this.e.lambda$getShotModeCursor$8(this.f, this.g);
                return;
        }
    }
}
