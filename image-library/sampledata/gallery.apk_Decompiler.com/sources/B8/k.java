package B8;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.viewer.DirectorsViewEditCmd;
import com.samsung.android.gallery.app.controller.viewer.DownloadForViewerCmd;
import com.samsung.android.gallery.app.controller.viewer.MultiDownloadForViewerCmd;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.c2pa.C2paScsImpl;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.data.DummyAnalyzer;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.CorrectionSubTask;
import com.samsung.android.sdk.scs.ai.language.Corrector;
import com.samsung.android.sdk.scs.ai.language.ExtractionCategory;
import com.samsung.android.sdk.scs.ai.language.ExtractionInputParams;
import com.samsung.android.sdk.scs.ai.language.ExtractionMultiInputParams;
import com.samsung.android.sdk.scs.ai.language.Extractor;
import com.samsung.android.sdk.scs.ai.language.FormatConversionType;
import com.samsung.android.sdk.scs.ai.language.FormatConverter;
import com.samsung.android.sdk.scs.ai.language.Generic;
import com.samsung.android.sdk.scs.ai.language.NotesOrganizationFormatType;
import com.samsung.android.sdk.scs.ai.language.NotesOrganizer;
import com.samsung.android.sdk.scs.ai.language.SmartReplyCategory;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.ai.language.Suggester;
import com.samsung.android.sdk.scs.ai.language.Summarizer;
import com.samsung.android.sdk.scs.ai.language.SummaryOnDeviceType;
import com.samsung.android.sdk.scs.ai.language.ToneConverter;
import com.samsung.android.sdk.scs.ai.language.ToneType;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sum.core.graph.GraphNode;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import com.samsung.android.sum.core.graph.MFGraph;
import com.samsung.android.visual.ai.sdkcommon.c;
import com.samsung.android.visual.ai.sdkcommon.o;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2775h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2776i;

    public /* synthetic */ k(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f2775h = obj4;
        this.f2776i = obj5;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ShortcutHelper) this.e).lambda$updateToShortcutManager$8((MediaItem) this.f, (ArrayList) this.g, (Context) this.f2775h, (Bitmap) this.f2776i, (ShortcutInfo) obj);
                return;
            case 1:
                ((C2paScsImpl) this.e).lambda$update$2((c) this.f, (String) this.g, (FileItemInterface) this.f2775h, (Function) this.f2776i, (o) obj);
                return;
            case 2:
                ((DirectorsViewEditCmd) this.e).lambda$executorEdit$0((MediaItem) this.f, (EventContext) this.g, (DownloadSyncMgr) this.f2775h, (Consumer) this.f2776i, (Integer) obj);
                return;
            case 3:
                ((DownloadForViewerCmd) this.e).lambda$onExecute$0((MediaItem) this.f, (Consumer) this.g, (DownloadType) this.f2775h, (DownloadSyncMgr) this.f2776i, (Boolean) obj);
                return;
            case 4:
                ((MultiDownloadForViewerCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f, (Consumer) this.g, (DownloadType) this.f2775h, (DownloadSyncMgr) this.f2776i, (Boolean) obj);
                return;
            case 5:
                ((Corrector) this.e).lambda$correct$0((AppInfo) this.f, (String) this.g, (CorrectionSubTask) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 6:
                ((Extractor) this.e).lambda$extract$1((ExtractionInputParams) this.f, (AppInfo.RequestType) this.g, (AppInfo) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 7:
                ((Extractor) this.e).lambda$extract$2((ExtractionMultiInputParams) this.f, (AppInfo.RequestType) this.g, (AppInfo) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 8:
                ((Extractor) this.e).lambda$extract$0((AppInfo) this.f, (String) this.g, (ExtractionCategory) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 9:
                ((FormatConverter) this.e).lambda$convert$0((AppInfo) this.f, (String) this.g, (FormatConversionType) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 10:
                ((Generic) this.e).lambda$generic$0((AppInfo) this.f, (String) this.g, (String) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 11:
                ((NotesOrganizer) this.e).lambda$notesOrganize$0((AppInfo) this.f, (String) this.g, (NotesOrganizationFormatType) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 12:
                ((SmartReplyer) this.e).lambda$smartReply$1((AppInfo) this.f, (String) this.g, (SmartReplyCategory) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 13:
                ((Suggester) this.e).lambda$reportGeneration$3((AppInfo) this.f, (String) this.g, (String) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 14:
                ((Summarizer) this.e).lambda$summarizeOnDevice$1((AppInfo) this.f, (String) this.g, (SummaryOnDeviceType) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 15:
                ((ToneConverter) this.e).lambda$toneConvert$0((AppInfo) this.f, (String) this.g, (ToneType) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 16:
                ((ToneConverter) this.e).lambda$toneConvert$1((AppInfo) this.f, (String) this.g, (String) this.f2775h, (Map) this.f2776i, (LlmServiceObserver2) obj);
                return;
            case 17:
                MFDescriptorGraph.lambda$toMediaFilterGraph$3((List) this.e, (Map) this.f, (Map) this.g, (MFGraph.Builder) this.f2775h, (GraphNode) this.f2776i, (Integer) obj);
                return;
            default:
                DummyAnalyzer.lambda$createDataJson$5((AtomicInteger) this.e, (JSONArray) this.f, (JSONArray) this.g, (AtomicInteger) this.f2775h, (JSONArray) this.f2776i, (JSONObject) obj);
                return;
        }
    }
}
