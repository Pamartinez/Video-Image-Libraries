package Ad;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.dragdrop.CreatureDragAndDrop;
import com.samsung.android.gallery.app.ui.list.dragdrop.DnDMode;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.MxAlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.CollectionPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.timeline.TimelinePickerFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment;
import com.samsung.android.gallery.support.config.SysConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.sum.core.descriptor.CodecDescriptor;
import com.samsung.android.sum.core.filter.DecoratedPluginFilter;
import com.samsung.android.sum.core.filter.MediaCodecFilter;
import com.samsung.android.sum.core.message.MessageChannel;
import com.samsung.android.sum.core.message.MessageConsumer;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.android.vexfwk.sdk.unifiedDetector.VexFwkUnifiedDetector;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.framework.core.SContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* renamed from: Ad.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0720a implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ C0720a(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return VexFwkUnifiedDetector.supportedModes_delegate$lambda$2$lambda$1();
            case 1:
                return new ArrayList();
            case 2:
                return RelatedInfo.lambda$new$0();
            case 3:
                return RelatedInfo.lambda$new$1();
            case 4:
                return SysConfig.computeIfAbsent("serial_number", new C0720a(5));
            case 5:
                return DeviceInfo.genRandomSerial();
            case 6:
                return new Bundle();
            case 7:
                return DnDMode.injectCustomMode(new CreatureDragAndDrop());
            case 8:
                return SelectionViewFragment.lambda$getSelectedPositions$12();
            case 9:
                return SelectionViewFragment.lambda$getSelectedItems$11();
            case 10:
                return new LinkedHashMap();
            case 11:
                return NumberFormat.getInstance(Locale.getDefault());
            case 12:
                return Collections.EMPTY_LIST;
            case 13:
                return CodecDescriptor.lambda$new$0();
            case 14:
                return DecoratedPluginFilter.lambda$getMessagesToReceive$0();
            case 15:
                return DecoratedPluginFilter.lambda$getMessagesToReceive$1();
            case 16:
                return MediaCodecFilter.lambda$getMessagesToReceive$0();
            case 17:
                return Stream.of(new MessageChannel[0]);
            case 18:
                return Stream.of(new MessageConsumer[0]);
            case 19:
                return NumericEnum.lambda$fromValue$1();
            case 20:
                return VexFwkHelperBase.Companion.isAvailable$lambda$0();
            case 21:
                return SContext.lambda$new$1();
            case 22:
                return ContextFactory.getApplicationContext();
            case 23:
                return PicturesViewAdapter.lambda$recalculatePosition$11();
            case 24:
                return PicturesViewAdapter.lambda$recalculatePosition$12();
            case 25:
                return new TimelinePickerFragment();
            case 26:
                return new CollectionPickerFragment();
            case 27:
                return new SearchPickerFragment();
            case 28:
                return new MxAlbumsPickerFragment();
            default:
                return new AlbumsPickerFragment();
        }
    }
}
