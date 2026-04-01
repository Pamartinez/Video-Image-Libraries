package com.samsung.android.gallery.module.dynamicview;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslator;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.MediaParserDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SocVendor;
import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.jpeg3dcontainer.model.Segment;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRect;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3013a;

    public /* synthetic */ a(int i2) {
        this.f3013a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3013a) {
            case 0:
                return DynamicViewInfo.lambda$bestClipAvailable$1((ClipInfo) obj);
            case 1:
                return SpeedRun.lambda$getViewerPlayback$0((PlaybackOption) obj);
            case 2:
                return NeuralTranslator.lambda$getSourceLanguageList$6((Map.Entry) obj);
            case 3:
                return NeuralTranslator.lambda$getTargetLanguageList$7((Map.Entry) obj);
            case 4:
                return Def.lambda$contentToString$1((String) obj);
            case 5:
                return Def.lambda$contentToStringln$2((String) obj);
            case 6:
                return ((String) obj).startsWith("com.");
            case 7:
                return MediaParserDescriptor.lambda$countToMuxerConfigure$0((MediaType) obj);
            case 8:
                return PluginDescriptor.lambda$getFilterId$0((String) obj);
            case 9:
                return ((MFDescriptor) obj).getFilterOption().isKeepFilterDatatype();
            case 10:
                return Objects.nonNull(obj);
            case 11:
                return ((MediaBuffer) obj).containsExtra("composer");
            case 12:
                return Pattern.compile("-?\\d+(\\.\\d+)?").matcher((String) obj).matches();
            case 13:
                return SocVendor.lambda$all$0((SocVendor) obj);
            case 14:
                return Jpeg3d.lambda$getMpfSegment$4((Segment) obj);
            case 15:
                return Jpeg3d.lambda$getXmpSegments$3((Segment) obj);
            case 16:
                return ((O3DPRect) obj).isToBeDeleted();
            case 17:
                return Objects.nonNull((Uri) obj);
            case 18:
                return PrivateDatabase.lambda$getNdeFile$5((String[]) obj);
            case 19:
                return Objects.nonNull((Fragment) obj);
            case 20:
                return PicturesViewAdapter.lambda$getMapUrl$14((MediaItem) obj);
            case 21:
                return ((MediaItem) ((MediaItem) obj)).isImage();
            case 22:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 23:
                return ((ClusterItem) obj).isMonth();
            case 24:
                return Objects.nonNull((TipCardView) obj);
            case 25:
                return AiEditHandler.lambda$startLiveEffectActivity$15((FastOptionView) obj);
            case 26:
                return RestoreUserData.lambda$init$0((FileOpJob) obj);
            case 27:
                return RestoreUserData.lambda$prepare$3((FileOpJob) obj);
            case 28:
                return ((String) obj).startsWith("/data/sec");
            default:
                return FileUtils.isInRemovableStorage(((MediaItem) obj).getDataPath());
        }
    }
}
