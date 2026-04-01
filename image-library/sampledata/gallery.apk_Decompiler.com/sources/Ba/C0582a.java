package ba;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.headeritem.StoryHeaderItem2;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapColorPicker;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import com.samsung.android.gallery.module.remotegallery.RemoteServer;
import com.samsung.android.gallery.module.remotegallery.TransferHeader;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.service.notification.DownloadNotificationHelper;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;
import com.samsung.android.gallery.widget.story.transitory.PageTransformer;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerListener;
import com.samsung.android.motionphoto.utils.ex.WrapVSWEnginePlugin;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl;
import com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask;
import com.samsung.android.sdk.scs.ai.language.Corrector;
import com.samsung.android.sdk.scs.ai.language.EmojiAugmentor;
import com.samsung.android.sdk.scs.ai.language.Extractor;
import com.samsung.android.sdk.scs.ai.language.SmartCoverer;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.ai.language.Suggester;
import com.samsung.android.sdk.scs.ai.language.SuggesterForExternal;
import com.samsung.android.sdk.scs.ai.language.Summarizer;
import com.samsung.android.sdk.scs.ai.language.ToneConverter;
import com.samsung.android.sdk.scs.ai.language.WritingComposer;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightControl;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.DetachableSurfaceReadChannel;
import com.samsung.android.sum.core.channel.StapleSurfaceWriteChannel;
import com.samsung.android.sum.core.channel.SurfaceReadChannel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* renamed from: ba.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0582a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0582a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((ClusterResultsEntry) obj2).lambda$setPdcList$2((ArrayList) obj);
                return;
            case 1:
                ((FilterResultsEntry) obj2).addEntity((FilterResultsEntity) obj);
                return;
            case 2:
                ((PageTransformer) obj).setTransformListener((ViewPagerListener) obj2);
                return;
            case 3:
                ((DownloadNotificationHelper) obj2).dismiss(((Integer) obj).intValue());
                return;
            case 4:
                ((Blackboard) obj).postEvent(EventMessage.obtain(3035, (RemasterHelper.Result) obj2));
                return;
            case 5:
                ((StoryHeaderItem2) obj2).lambda$onHeaderLoaded$0((Consumer) obj);
                return;
            case 6:
                ProcessingLightControl.buildAnimation$lambda$6((ProcessingLightControl) obj2, (View) obj);
                return;
            case 7:
                ((SqliteCaseBuilder) obj2).whenThen("_name is '" + ((ScreenShotFilterType) obj).key() + "'", ((ScreenShotFilterType) obj).ordinal());
                return;
            case 8:
                RecapColorPicker.lambda$findTargetBgColor$2((RecapImage) obj2, (RecapBgLayer) obj);
                return;
            case 9:
                ((RecapColorPicker) obj2).lambda$findTargetBgColor$3((RecapTemplateScene) obj);
                return;
            case 10:
                ((TransferHeader) obj2).mFileData.add(new Pair(new File(((MediaItem) obj).getPath()).getName(), Long.valueOf(((MediaItem) obj).getFileSize())));
                return;
            case 11:
                ((RemoteServer) obj2).lambda$setFiles$0((MediaItem) obj);
                return;
            case 12:
                ((OnDemandStory) obj2).lambda$load$2((String[]) obj);
                return;
            case 13:
                WrapVSWEnginePlugin.bindToFixture$lambda$1((WrapVSWEnginePlugin) obj2, (Context) obj);
                return;
            case 14:
                ((MotionPhotoInfoImpl) obj2).parseExtraOfMotionPhotoFromSEF((List) obj);
                return;
            case 15:
                ((SttRecognitionTask) obj2).taskCompleted((String) obj);
                return;
            case 16:
                ((com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask) obj2).taskCompleted((String) obj);
                return;
            case 17:
                ((Corrector) obj2).lambda$release$1((LlmServiceObserver2) obj);
                return;
            case 18:
                ((EmojiAugmentor) obj2).lambda$release$1((LlmServiceObserver2) obj);
                return;
            case 19:
                ((Extractor) obj2).lambda$release$3((LlmServiceObserver2) obj);
                return;
            case 20:
                ((SmartCoverer) obj2).lambda$release$1((LlmServiceObserver2) obj);
                return;
            case 21:
                ((SmartReplyer) obj2).lambda$release$3((LlmServiceObserver2) obj);
                return;
            case 22:
                ((Suggester) obj2).lambda$release$4((LlmServiceObserver2) obj);
                return;
            case 23:
                ((SuggesterForExternal) obj2).lambda$release$0((LlmServiceObserver2) obj);
                return;
            case 24:
                ((Summarizer) obj2).lambda$release$3((LlmServiceObserver2) obj);
                return;
            case 25:
                ((ToneConverter) obj2).lambda$release$2((LlmServiceObserver2) obj);
                return;
            case 26:
                ((WritingComposer) obj2).lambda$release$2((LlmServiceObserver2) obj);
                return;
            case 27:
                ((DetachableSurfaceReadChannel) obj2).lambda$send$0((MediaBuffer) obj);
                return;
            case 28:
                ((SurfaceReadChannel) obj2).send((MediaBuffer) obj);
                return;
            default:
                ((StapleSurfaceWriteChannel) obj2).lambda$send$5((MediaBuffer) obj);
                return;
        }
    }
}
