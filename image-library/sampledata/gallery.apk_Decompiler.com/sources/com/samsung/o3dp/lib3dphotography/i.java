package com.samsung.o3dp.lib3dphotography;

import Kd.a;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.MultiInstanceInvalidationClient;
import androidx.room.MultiInstanceInvalidationClient$callback$1;
import androidx.room.TransactionExecutor;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.pictures.ClusterPositionFinder;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV3;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.YearQueryCluster;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionViewAdapterV2;
import com.samsung.android.gallery.app.ui.list.stories.highlight.MusicPickerHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AwesomeIntelligenceHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.ColorCorrectHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterAutoTiltAiEdit;
import com.samsung.android.gallery.bixby.cmd.OnThreadCmd;
import com.samsung.android.gallery.module.aiedit.AiEditBixbyRequest;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.list.YearQueryCache;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingViHandler;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.SelectInfoView;
import com.samsung.o3dp.lib3dphotography.mesh.AnimatedMesh;
import ee.C0969b;
import ee.C0970c;
import ee.a0;
import fe.c;
import fe.d;
import fe.m;
import fe.o;
import ge.C1025g0;
import ge.C1031i0;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ i(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        Integer num;
        a0 a0Var;
        o oVar;
        switch (this.d) {
            case 0:
                ((O3DPhotoPipeline) this.e).lambda$render$7((Bitmap) this.f);
                return;
            case 1:
                ((O3DPhotoPipeline) this.e).lambda$render$8((AnimatedMesh) this.f);
                return;
            case 2:
                ((O3DPhotoPipeline) this.e).lambda$new$0((Surface) this.f);
                return;
            case 3:
                ((AbsProgressService) this.e).lambda$onAppend$2((Intent) this.f);
                return;
            case 4:
                ((MvpBasePresenter) this.e).lambda$setNavigationUpClickListener$5((Toolbar) this.f);
                return;
            case 5:
                ((ViewGroup) this.e).setLayoutParams((ViewGroup.LayoutParams) this.f);
                return;
            case 6:
                ((ClusterPositionFinder) this.e).lambda$new$0((GalleryListView) this.f);
                return;
            case 7:
                ((ClusterPositionFinder) this.e).lambda$moveToTargetPosition$3((PicturesViewAdapter) this.f);
                return;
            case 8:
                ((PicturesFragment) this.e).lambda$createViewHolder$6((RecyclerView.ViewHolder) this.f);
                return;
            case 9:
                ((PicturesPinchAnimationManagerV3) this.e).lambda$addListAnimators$2((PicturesPinchAnimationManagerV3.Type) this.f);
                return;
            case 10:
                ((HideSceneSelectionViewAdapterV2) this.e).lambda$onBindViewHolder$0((ListViewHolder) this.f);
                return;
            case 11:
                ((SimpleAutoScroller) this.e).lambda$shrinkIfFound$6((ListViewHolder) this.f);
                return;
            case 12:
                ((SimpleAutoScroller) this.e).lambda$findAndShrink$10((RecyclerView.ViewHolder) this.f);
                return;
            case 13:
                ((SelectInfoView) this.e).lambda$onCheckClick$1((View) this.f);
                return;
            case 14:
                ((YearQueryCluster) this.e).lambda$init$1((YearQueryCache) this.f);
                return;
            case 15:
                ((OnThreadCmd) this.e).lambda$execute$0((Context) this.f);
                return;
            case 16:
                ((ThrowingViHandler) this.e).lambda$onAnimationEnd$1((View) this.f);
                return;
            case 17:
                c cVar = (c) this.e;
                IBinder iBinder = (IBinder) this.f;
                synchronized (cVar) {
                    C0969b bVar = cVar.g;
                    num = (Integer) bVar.f4292a.get(c.w);
                }
                if (num == null) {
                    a0Var = a0.f4288j.g("No remote UID available");
                } else {
                    a0Var = cVar.f4343p.j(num.intValue());
                }
                synchronized (cVar) {
                    try {
                        if (cVar.j(d.SETUP)) {
                            if (!a0Var.e()) {
                                cVar.o(a0Var, true);
                            } else {
                                Executor executor = cVar.f4342o;
                                Logger logger = o.b;
                                if (iBinder instanceof Binder) {
                                    oVar = new m(iBinder, executor);
                                } else {
                                    oVar = new o(iBinder);
                                }
                                cVar.f4341j = oVar;
                                oVar.f4361a.linkToDeath(cVar, 0);
                                if (!cVar.l()) {
                                    cVar.n(d.READY);
                                    a aVar = cVar.t;
                                    C0969b bVar2 = cVar.g;
                                    Iterator it = ((C1031i0) aVar.f).f4520j.iterator();
                                    if (!it.hasNext()) {
                                        cVar.g = bVar2;
                                        a aVar2 = cVar.t;
                                        C1031i0 i0Var = (C1031i0) aVar2.f;
                                        i0Var.f4519i.b(C0970c.INFO, "READY");
                                        i0Var.k.execute(new C1025g0(aVar2, 0));
                                    } else {
                                        it.next().getClass();
                                        throw new ClassCastException();
                                    }
                                }
                            }
                        }
                    } catch (RemoteException unused) {
                        cVar.o(a0.f4289o.g("Failed to observe outgoing binder"), true);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
            case 18:
                MultiInstanceInvalidationClient$callback$1.onInvalidation$lambda$0((MultiInstanceInvalidationClient) this.e, (String[]) this.f);
                return;
            case 19:
                TransactionExecutor.execute$lambda$1$lambda$0((Runnable) this.e, (TransactionExecutor) this.f);
                return;
            case 20:
                ((MusicPickerHandler) this.e).lambda$handleMusicPickDone$2((Uri) this.f);
                return;
            case 21:
                ((SharedTransitionHandler) this.e).lambda$startTransition$0((SharedTransition.TransitionListener) this.f);
                return;
            case 22:
                ((SharedTransitionHandler) this.e).lambda$applyFilter$6((Bitmap) this.f);
                return;
            case 23:
                ((StoryHighlightPresenter) this.e).lambda$updateNewBadgeState$1((MediaItem) this.f);
                return;
            case 24:
                ((AiEditHandler) this.e).lambda$addActionInvokeListener$3((Object[]) this.f);
                return;
            case 25:
                ((AiEditHandler) this.e).lambda$updateVisibilityIfExpanded$8((MediaItem) this.f);
                return;
            case 26:
                AiEditList.lambda$requestLoadAndExecuteDirectly$12((AiEditItem) this.e, (AiEditBixbyRequest) this.f);
                return;
            case 27:
                ((RemasterAutoTiltAiEdit) this.e).onMenuSelect(((AiEditBixbyRequest) this.f).autoSave);
                return;
            case 28:
                ((AwesomeIntelligenceHandler) this.e).lambda$onConfigurationChanged$1((Configuration) this.f);
                return;
            default:
                ((ColorCorrectHandler) this.e).lambda$executed$2((MediaItem) this.f);
                return;
        }
    }
}
