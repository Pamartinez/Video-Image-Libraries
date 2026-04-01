package O3;

import D0.e;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.Menu;
import androidx.appcompat.widget.PopupMenu;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.transformer.ExportResult;
import androidx.media3.transformer.Transformer;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ChangeDateCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalMotionPhotoCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsPdfCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.MenuFactory;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgm.BgmPlayerV2;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterItemListAdapter;
import com.samsung.android.gallery.app.ui.viewer2.menu.RemasterSaveMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewOriginalVideoMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.RemasterFocusViewHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataLoadCallback;
import com.samsung.android.gallery.module.details.DetailsDataLoader;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.fileio.redact.OnProgress;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowAdapter;
import com.samsung.android.gallery.widget.videoview.mediaplayer.plugin.MotionPhotoPlugin;
import com.samsung.scsp.common.Holder;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.api.AbstractApiControl;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.decorator.AbstractDecorator;
import ee.C0964A;
import ee.C0966C;
import ee.C0975h;
import ee.C0976i;
import ge.C1022f0;
import ge.E0;
import ge.R0;
import ge.S0;
import ge.T0;
import ge.U0;
import ge.V0;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements GroupShotFormat.OnUpdateCompletionListener, DataCollectionDelegate.OnDataCompletionListener, FixDateTimeCmd.Updater, MediaScannerListener, OnProgress, ThumbnailLoadedListener, OnTranslationListener, PopupMenu.OnDismissListener, ListenerSet.Event, MapUtil.OnLocationChanged, FaultBarrier.ThrowableRunnable, C0966C, MediaPlayerCompat.OnPreparedListener, MediaPlayerCompat.OnPlayBackCompleteListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    public void a(C0976i iVar) {
        E0 e02;
        int i2;
        int i7;
        V0 v02 = (V0) this.f;
        E0 e03 = (E0) this.e;
        e eVar = v02.d;
        C0975h hVar = iVar.f4298a;
        HashMap hashMap = v02.e;
        U0 u02 = (U0) hashMap.get(V0.n(e03));
        if (u02 != null && (e02 = u02.f4481a) == e03 && hVar != C0975h.SHUTDOWN) {
            C0975h hVar2 = C0975h.IDLE;
            if (hVar == hVar2) {
                eVar.U();
            }
            U0.a(u02, hVar);
            C0975h hVar3 = v02.f4485j;
            C0975h hVar4 = C0975h.TRANSIENT_FAILURE;
            if (hVar3 == hVar4 || v02.k == hVar4) {
                if (hVar != C0975h.CONNECTING) {
                    if (hVar == hVar2) {
                        v02.o();
                        return;
                    }
                } else {
                    return;
                }
            }
            int i8 = R0.f4472a[hVar.ordinal()];
            if (i8 == 1) {
                C1022f0 f0Var = v02.f;
                f0Var.b = 0;
                f0Var.f4510c = 0;
                v02.f4485j = hVar2;
                v02.q(hVar2, new T0(v02, v02));
            } else if (i8 == 2) {
                C0975h hVar5 = C0975h.CONNECTING;
                v02.f4485j = hVar5;
                v02.q(hVar5, new S0(C0964A.d));
            } else if (i8 == 3) {
                v02.m();
                for (U0 u03 : hashMap.values()) {
                    if (!u03.f4481a.equals(e02)) {
                        u03.f4481a.b();
                    }
                }
                hashMap.clear();
                C0975h hVar6 = C0975h.READY;
                U0.a(u02, hVar6);
                hashMap.put(V0.n(e02), u02);
                v02.f.e(V0.n(e03));
                v02.f4485j = hVar6;
                v02.r(u02);
            } else if (i8 == 4) {
                if (v02.f.c() && ((U0) hashMap.get(v02.f.a())).f4481a == e03 && v02.f.b()) {
                    v02.m();
                    v02.o();
                }
                C1022f0 f0Var2 = v02.f;
                if (f0Var2 != null && !f0Var2.c()) {
                    int size = hashMap.size();
                    List list = v02.f.f4509a;
                    if (list != null) {
                        i2 = list.size();
                    } else {
                        i2 = 0;
                    }
                    if (size >= i2) {
                        for (U0 u04 : hashMap.values()) {
                            if (!u04.d) {
                                return;
                            }
                        }
                        C0975h hVar7 = C0975h.TRANSIENT_FAILURE;
                        v02.f4485j = hVar7;
                        v02.q(hVar7, new S0(C0964A.a(iVar.b)));
                        int i10 = v02.g + 1;
                        v02.g = i10;
                        List list2 = v02.f.f4509a;
                        if (list2 != null) {
                            i7 = list2.size();
                        } else {
                            i7 = 0;
                        }
                        if (i10 >= i7 || v02.f4483h) {
                            v02.f4483h = false;
                            v02.g = 0;
                            eVar.U();
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Unsupported state:" + hVar);
            }
        }
    }

    public void invoke(Object obj) {
        ((Transformer) this.f).lambda$onExportCompletedWithSuccess$1((ExportResult) this.e, (Transformer.Listener) obj);
    }

    public boolean isTranslated() {
        return ((RemasterViewerHolder) this.f).lambda$setPhotoView$6((PhotoViewCompat) this.e);
    }

    public void onCompleted() {
        ((FixDateTimeCmd) this.f).lambda$scanMedia$11((ArrayList) this.e);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        switch (this.d) {
            case 1:
                ((ChangeDateCmd) this.f).lambda$onExecute$0((MediaItem[]) this.e, eventContext, arrayList);
                return;
            case 3:
                ((EditDateAndTimeCmd) this.f).lambda$onExecute$3((MediaItem[]) this.e, eventContext, arrayList);
                return;
            case 6:
                ((RevertOriginalMotionPhotoCmd) this.f).lambda$startConfirmDialog$0((MediaItem) this.e, eventContext, arrayList);
                return;
            case 7:
                ((SaveAsPdfCmd) this.f).lambda$showConfirmDialogAndSave$1((MediaItem[]) this.e, eventContext, arrayList);
                return;
            default:
                ((RemasterSaveMenuItem) this.f).lambda$showSaveConfirmDialog$1((MediaItem) this.e, eventContext, arrayList);
                return;
        }
    }

    public void onDismiss(PopupMenu popupMenu) {
        MenuFactory.lambda$createPopupMenu$0((Menu) this.f, (Blackboard) this.e, popupMenu);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 8:
                ((RemasterItemListAdapter) this.f).lambda$bindThumbnail$2((ListViewHolder) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 10:
                ((ViewOriginalVideoMenuItem) this.f).lambda$onMenuSelectInternal$1((MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 13:
                ((RemasterFocusViewHandler) this.f).lambda$onLoadedFocusData$2((ArrayList) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 14:
                ((SaveCaptureCmd) this.f).lambda$updateThumbnail$7((MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 17:
                ((SimpleSlideShowAdapter) this.f).lambda$bindThumbnail$3((ListViewHolder) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 18:
                ((StoriesPinAdapter) this.f).lambda$bindThumbnail$2((ListViewHolder) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 20:
                DetailsDataLoader.notify((DetailsDataLoadCallback) this.f, (MediaItem) this.e, new DetailsLoadResult(DetailsUpdateKey.MAP_MARKER_BITMAP, bitmap));
                return;
            case 25:
                ((SharedTransitionHandler) this.f).lambda$loadTransitionInfo$5((MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((CustomCover) this.f).lambda$bindImage$2((MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public void onLocationChanged(Location location) {
        ((MapPickerContainer) this.f).lambda$requestByLocationManager$1((Runnable) this.e, location);
    }

    public void onPlaybackComplete(MediaPlayerCompat mediaPlayerCompat) {
        ((MotionPhotoPlugin) this.f).lambda$setPlaybackSlowMoViewMode$0((MediaPlayerCompat) this.e, mediaPlayerCompat);
    }

    public void onPrepared(MediaPlayerCompat mediaPlayerCompat) {
        ((BgmPlayerV2) this.f).lambda$openPlayer$1((BgmOptions) this.e, mediaPlayerCompat);
    }

    public void run() {
        switch (this.d) {
            case 22:
                ((Holder) this.f).hold(new Response(new GZIPInputStream((InputStream) this.e)).toString());
                return;
            case 23:
                ((AbstractApiControl) this.f).lambda$new$1((Class) this.e);
                return;
            default:
                ((AbstractDecorator) this.f).lambda$new$2((Class) this.e);
                return;
        }
    }

    public boolean update(MediaItem mediaItem) {
        return ((ChangeDateCmd) this.f).lambda$changeDateAsync$3((String) this.e, mediaItem);
    }

    public void onCompleted(int i2, int i7, int i8) {
        ((RevertOriginalImageCmd) this.f).lambda$executeRevertJob$3((MediaItem) this.e, i2, i7, i8);
    }
}
