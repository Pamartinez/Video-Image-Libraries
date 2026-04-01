package A4;

import B5.b;
import android.app.job.JobParameters;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.internals.NondestructiveSaveRemasterCmd;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.list.abstraction.ConcatThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MonthXsCluster;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.RelationshipPickerLauncher;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.idleworker.IdleWorker;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Classifier;
import com.samsung.android.sdk.scs.ai.language.Configuration;
import com.samsung.android.sdk.scs.ai.language.EmojiAugmentor;
import com.samsung.android.sdk.scs.ai.language.SmartCoverer;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.ai.language.Suggester;
import com.samsung.android.sdk.scs.ai.language.SuggestionInputParams;
import com.samsung.android.sdk.scs.ai.language.WritingComposer;
import com.samsung.android.sdk.scs.ai.language.WritingComposerInputParams;
import com.samsung.android.sdk.scs.ai.language.WritingComposerMultiInputParams;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class S implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2240h;

    public /* synthetic */ S(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f2240h = obj4;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ConcatThumbnailRequestHolder) this.e).lambda$createConcatBitmap$3((Rect) this.f, (Canvas) this.g, (Paint) this.f2240h, (Integer) obj);
                return;
            case 1:
                ((RelationshipPickerLauncher) this.e).lambda$launch$2((b) this.f, (ISearchPicturesView) this.g, (Object[]) this.f2240h, (Map) obj);
                return;
            case 2:
                SearchMyQuery.lambda$update$1((AtomicInteger) this.e, (ContentValues) this.f, (String) this.g, (String[]) this.f2240h, (SQLiteDatabase) obj);
                return;
            case 3:
                ((NondestructiveSaveRemasterCmd) this.e).lambda$moveRemasteredImage$1((String) this.f, (String) this.g, (Uri) this.f2240h, (Boolean) obj);
                return;
            case 4:
                AlbumsDragAndDropDelegate.lambda$getDragItems$9((ArrayList) this.e, (ArrayList) this.f, (int[]) this.g, (int[]) this.f2240h, (MediaItem) obj);
                return;
            case 5:
                ((Classifier) this.e).lambda$classify$0((AppInfo) this.f, (String) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 6:
                ((Configuration) this.e).lambda$prewarmLlm$1((AppInfo) this.f, (String) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 7:
                ((EmojiAugmentor) this.e).lambda$emojiAugment$0((AppInfo) this.f, (String) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 8:
                ((SmartCoverer) this.e).lambda$smartCover$0((AppInfo) this.f, (String) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 9:
                ((SmartReplyer) this.e).lambda$smartReply$0((AppInfo) this.f, (String) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 10:
                ((Suggester) this.e).lambda$suggestion$0((AppInfo) this.f, (String) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 11:
                ((Suggester) this.e).lambda$suggestion$2((SuggestionInputParams) this.f, (AppInfo) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 12:
                ((WritingComposer) this.e).lambda$compose$0((WritingComposerInputParams) this.f, (AppInfo) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 13:
                ((WritingComposer) this.e).lambda$compose$1((WritingComposerMultiInputParams) this.f, (AppInfo) this.g, (Map) this.f2240h, (LlmServiceObserver2) obj);
                return;
            case 14:
                DelegateComposite.lambda$onCreateViewInternal$2((LayoutInflater) this.e, (ViewGroup) this.f, (Bundle) this.g, (ArrayList) this.f2240h, (AbsDelegate) obj);
                return;
            case 15:
                ((MonthXsCluster) this.e).lambda$adjustIndexerValue$1((ArrayList) this.f, (AtomicInteger) this.g, (AtomicInteger) this.f2240h, (Integer) obj);
                return;
            case 16:
                ((SuggestedCreatureSelectPresenter) this.e).lambda$setAsRelation$3((MediaItem) this.f, (ArrayList) this.g, (ArrayList) this.f2240h, (CreatureNameData) obj);
                return;
            case 17:
                ((IdleWorker) this.e).lambda$onStartJob$2((Context) this.f, (JobParameters) this.g, (AtomicInteger) this.f2240h, (IdleWorkerJob) obj);
                return;
            default:
                ((BiometricPromptCompat) this.e).lambda$showCustomPinPrompt$1((Context) this.f, (String) this.g, (Consumer) this.f2240h, (String) obj);
                return;
        }
    }
}
