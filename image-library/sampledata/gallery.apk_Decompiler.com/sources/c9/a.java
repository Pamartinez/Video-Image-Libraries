package C9;

import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.filter.MediaParserFilter;
import com.samsung.android.sum.core.types.Version;
import i.C0212a;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2787a;
    public final /* synthetic */ String b;

    public /* synthetic */ a(String str, int i2) {
        this.f2787a = i2;
        this.b = str;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2787a;
        String str = this.b;
        switch (i2) {
            case 0:
                return Boolean.valueOf(str.equalsIgnoreCase((String) obj));
            case 1:
                return C0212a.A(str, (String) obj);
            case 2:
                return ((MediaData) obj).getChildMediaData(str);
            case 3:
                return C0212a.A(str, (String) obj);
            case 4:
                return ((PluginDescriptor) obj).getExtra(str);
            case 5:
                return MediaParserFilter.lambda$isTrackDirectlyMuxing$1(str, obj);
            case 6:
                return Version.lambda$new$0(str, (String) obj);
            default:
                return C0212a.A(str, (String) obj);
        }
    }
}
