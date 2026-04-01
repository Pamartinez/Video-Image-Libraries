package i9;

import android.content.Context;
import com.samsung.android.gallery.module.fileio.mp.MpGroupMediaFileOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MpGroupMediaFileOperation e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ FileInfo g;

    public /* synthetic */ a(MpGroupMediaFileOperation mpGroupMediaFileOperation, Context context, FileInfo fileInfo, int i2) {
        this.d = i2;
        this.e = mpGroupMediaFileOperation;
        this.f = context;
        this.g = fileInfo;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$copyInternal$0(this.f, this.g, (List) obj);
                return;
            case 1:
                this.e.lambda$copyInternal$1(this.f, this.g, (List) obj);
                return;
            case 2:
                this.e.lambda$moveInternal$2(this.f, this.g, (List) obj);
                return;
            default:
                this.e.lambda$moveInternal$3(this.f, this.g, (List) obj);
                return;
        }
    }
}
