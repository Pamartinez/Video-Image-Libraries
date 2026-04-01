package com.samsung.android.sum.core.descriptor;

import Ad.f;
import android.animation.Animator;
import android.graphics.Paint;
import android.util.Pair;
import android.widget.RelativeLayout;
import com.google.gson.JsonElement;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.list.stories.highlight.MenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightFragment;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.bixby.bixbycard.StoryDataFetcher;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.support.DeleteAlbumInfo;
import com.samsung.android.gallery.module.service.support.StoryServiceHelper;
import com.samsung.android.gallery.widget.animations.CollectAnimation;
import com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;
import com.samsung.android.sesl.visualeffect.utils.WeakViewHashSet;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.filter.EncoderFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterPlaceHolder;
import com.samsung.android.sum.core.filter.NNFWFilterGroup;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.filter.PluginDecorateFilter;
import com.samsung.android.sum.core.filter.collection.ParallelDNCFilter;
import com.samsung.android.sum.core.filter.collection.ParallelSharedFilter;
import com.samsung.android.sum.core.functional.ExecuteDelegator;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.service.RemoteServiceProxy;
import com.samsung.android.sum.core.types.SplitType;
import com.samsung.scsp.common.PushConsumer;
import com.samsung.scsp.common.PushVo;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((MediaFilter.Option) ((MFDescriptor) obj)).setPad((Pair) obj2);
                return;
            case 1:
                ((MediaFilter.Option) ((MFDescriptor) obj)).setSplitType((SplitType) obj2);
                return;
            case 2:
                ((EncoderFilter) obj2).lambda$configCodec$4((MediaBuffer) obj);
                return;
            case 3:
                ((MediaFilter) obj).setMessageProducer((MessageProducer) obj2);
                return;
            case 4:
                ((MediaFilterPlaceHolder) obj2).lambda$reset$0((Consumer) obj);
                return;
            case 5:
                ((NNFWFilterGroup) obj2).lambda$prepare$3((MediaFilter) obj);
                return;
            case 6:
                ((NNFilter) obj2).lambda$onMessageReceived$5((Consumer) obj);
                return;
            case 7:
                PluginDecorateFilter.lambda$new$0((ExecuteDelegator) obj2, (MediaFilter) obj);
                return;
            case 8:
                ((ParallelDNCFilter) obj2).lambda$addFilter$0((MediaFilter) obj);
                return;
            case 9:
                ((ParallelSharedFilter) obj2).lambda$addFilter$0((MediaFilter) obj);
                return;
            case 10:
                ((BufferChannel) obj).send((MediaBuffer) obj2);
                return;
            case 11:
                ((RemoteServiceProxy) obj2).onReceiveResponse((Response) obj);
                return;
            case 12:
                ((PushConsumer) obj2).lambda$new$1((PushVo) obj);
                return;
            case 13:
                ((ApiContext) obj2).parameters.put(MediaApiContract.Parameter.ORIGINAL_URL, ((JsonElement) obj).getAsString());
                return;
            case 14:
                ((Paint) obj2).setColor(((Integer) obj).intValue());
                return;
            case 15:
                ((PopupTipBuilder) obj2).lambda$checkIgnoreRootViewTouch$1((RelativeLayout) obj);
                return;
            case 16:
                ((PicturesPresenter) obj2).lambda$handleEvent$2((PicturesViewAdapter) obj);
                return;
            case 17:
                ((PicturesViewAdapter) obj2).lambda$updateCheckboxOnBindMediaItem$0(((Integer) obj).intValue());
                return;
            case 18:
                ((StoryDataFetcher) obj2).lambda$getStoryData$0((QueryParams) obj);
                return;
            case 19:
                ((CollectAnimation) obj2).lambda$setPlaySequentiallyListener$1((Animator) obj);
                return;
            case 20:
                ((QuickViewTransitionAnimation) obj2).onShrinkDone(((Boolean) obj).booleanValue());
                return;
            case 21:
                ((SimpleAutoScroller) obj2).lambda$shrinkIfFound$7((Boolean) obj);
                return;
            case 22:
                WeakViewHashSet.forEachAlive$lambda$3((f) obj2, obj);
                return;
            case 23:
                ((DelegateComposite) obj2).lambda$createDelegateList$0((AbsDelegate) obj);
                return;
            case 24:
                ((QueryParams) obj).setGroupTypes(GroupType.BURST).setGroupMediaFilter(((FileItemInterface) obj2).getAlbumID(), ((FileItemInterface) obj2).getGroupMediaId());
                return;
            case 25:
                ((DeleteAlbumInfo) obj2).lambda$setCloudInvolved$0((QueryParams) obj);
                return;
            case 26:
                ((StoryServiceHelper) obj2).lambda$prepareEncode$0((Integer) obj);
                return;
            case 27:
                ((ReorderDragManager) obj2).lambda$onDragEnd$4((IReorderHandler) obj);
                return;
            case 28:
                MenuUpdater.lambda$updateFavoriteMenu$2((MenuDataBinding) obj2, (MediaItem) obj);
                return;
            default:
                ((StoryHighlightFragment) obj2).lambda$createDefaultEnterAnimation$0((Boolean) obj);
                return;
        }
    }
}
