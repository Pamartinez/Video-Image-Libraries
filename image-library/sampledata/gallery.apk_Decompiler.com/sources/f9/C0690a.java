package f9;

import android.content.Context;
import com.samsung.android.gallery.module.fileio.cmh.CmhGroupMediaFileOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import java.util.List;
import java.util.function.Consumer;

/* renamed from: f9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0690a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ CmhGroupMediaFileOperation e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ FileInfo g;

    public /* synthetic */ C0690a(CmhGroupMediaFileOperation cmhGroupMediaFileOperation, Context context, FileInfo fileInfo, int i2) {
        this.d = i2;
        this.e = cmhGroupMediaFileOperation;
        this.f = context;
        this.g = fileInfo;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$moveInternal$2(this.f, this.g, (List) obj);
                return;
            case 1:
                this.e.lambda$moveInternal$3(this.f, this.g, (List) obj);
                return;
            case 2:
                this.e.lambda$copyInternal$0(this.f, this.g, (List) obj);
                return;
            default:
                this.e.lambda$copyInternal$1(this.f, this.g, (List) obj);
                return;
        }
    }
}
