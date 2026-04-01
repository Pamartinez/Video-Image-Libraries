package Gb;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.dialog.CreateSharedAlbumDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceAdapter;
import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandler;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.ReceiveChannelRouter;
import com.samsung.android.sum.core.filter.ImgpDecorateFilter;
import com.samsung.android.sum.core.utils.dump.DumpConfig;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2822a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f2822a = i2;
        this.b = obj;
    }

    public final boolean test(int i2) {
        int i7 = this.f2822a;
        Object obj = this.b;
        switch (i7) {
            case 0:
                return ((AdvancedMouseSelectionHandler) obj).lambda$handleSingleItemSelectionOnShift$1(i2);
            case 1:
                return ReceiveChannelRouter.lambda$receiveAll$0((List) obj, i2);
            case 2:
                return ImgpDecorateFilter.lambda$run$1((MutableMediaBuffer) obj, i2);
            case 3:
                return ((DumpConfig) obj).lambda$containFlags$0(i2);
            case 4:
                return ((AudioInfo) obj).lambda$hasStandardSampleRate$0(i2);
            case 5:
                return ((DrawerTabViewAdapter) obj).lambda$removeAllUsbTab$10(i2);
            case 6:
                return CreateSharedAlbumDialog.lambda$getDefaultName$2((AtomicInteger) obj, i2);
            default:
                return ((RelationshipChoiceAdapter) obj).lambda$findSelectedPosition$1(i2);
        }
    }
}
