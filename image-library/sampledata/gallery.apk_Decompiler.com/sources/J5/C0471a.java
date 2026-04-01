package j5;

import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import java.util.function.Function;

/* renamed from: j5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0471a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f2650a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ C0471a(String str, boolean z) {
        this.f2650a = str;
        this.b = z;
    }

    public final Object apply(Object obj) {
        boolean z = this.b;
        return CategoryPropertyHelper.createCategoryProperty(this.f2650a, z);
    }
}
