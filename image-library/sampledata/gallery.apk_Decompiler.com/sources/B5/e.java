package B5;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.StringJoiner;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StringJoiner e;

    public /* synthetic */ e(StringJoiner stringJoiner, int i2) {
        this.d = i2;
        this.e = stringJoiner;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StringJoiner stringJoiner = this.e;
        switch (i2) {
            case 0:
                stringJoiner.add(((MediaItem) obj).getSubCategory());
                return;
            case 1:
                stringJoiner.add(String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(((MediaItem) obj).getSubCategory())));
                return;
            case 2:
                stringJoiner.add((String) obj);
                return;
            case 3:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 4:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 5:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 6:
                stringJoiner.add(String.valueOf(((MapItem) obj).getFileId()));
                return;
            case 7:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 8:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 9:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 10:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 11:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            case 12:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
            default:
                stringJoiner.add(String.valueOf((Long) obj));
                return;
        }
    }
}
