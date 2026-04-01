package Gb;

import android.content.ClipData;
import android.mtp.MtpObjectInfo;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.module.c2pa.C2paInfo;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.pinch.v3.BitmapCache;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchAnimInfo;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2821a;

    public /* synthetic */ a(int i2) {
        this.f2821a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2821a) {
            case 0:
                return Boolean.valueOf(((GalleryListAdapter) obj).isOnShiftKeyCombination());
            case 1:
                return Boolean.valueOf(((GalleryListAdapter) obj).isOnVirtualCtrlKeyCombination());
            case 2:
                return Boolean.valueOf(((GalleryListAdapter) obj).isOnCtrlKeyCombination());
            case 3:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 4:
                return ContentUri.getUri((MediaItem) obj);
            case 5:
                return ((MediaItem) ((MediaItem) obj)).getTitle();
            case 6:
                return String.valueOf(((MediaItem) obj).getFileId());
            case 7:
                return Integer.valueOf(((MediaItem) obj).getAlbumID());
            case 8:
                return MotionPhotoUtils.lambda$getPlaybackInfo$1((MotionPhotoPlayback) obj);
            case 9:
                return Long.valueOf(Long.parseLong((String) obj));
            case 10:
                return ((MediaItem) ((MediaItem) obj)).getSubCategory();
            case 11:
                return String.valueOf(((Long) obj).longValue() + 100000);
            case 12:
                return ((MediaItem) ((MediaItem) obj)).getSubCategory();
            case 13:
                return ((MediaItem) ((MediaItem) obj)).getSubCategory();
            case 14:
                return Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId((String) obj));
            case 15:
                return CreatureData.of((MediaItem) obj).faceGroupUuid;
            case 16:
                return Integer.valueOf(((MediaData) obj).getDataVersion());
            case 17:
                return MtpClient.lambda$getObjectListFromSubDirectory$0((MtpObjectInfo) obj);
            case 18:
                return ActionInvoker.lambda$add$0((Enum) obj);
            case 19:
                return ActionInvoker.lambda$addExclusive$1((Enum) obj);
            case 20:
                return ((ClipData.Item) obj).getUri();
            case 21:
                return BitmapCache.lambda$addConsumer$2((String) obj);
            case 22:
                return ((PinchAnimInfo) obj).getFocused();
            case 23:
                return ((PinchAnimInfo) obj).getRange();
            case 24:
                return ((MediaData) obj).read(0);
            case 25:
                return SpotifySlideshowFragment.lambda$initViewPager$5((ViewerObjectComposite) obj);
            case 26:
                return ((C2paInfo.C2paActionWrapper) obj).toString();
            case 27:
                return Blackboard.lambda$getContext$2((Blackboard) obj);
            case 28:
                return Blackboard.lambda$getInstance$0((String) obj);
            default:
                return Blackboard.lambda$addSubscriberList$7((String) obj);
        }
    }
}
