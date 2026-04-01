package C3;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryActivityHandler e;

    public /* synthetic */ e(GalleryActivityHandler galleryActivityHandler, int i2) {
        this.d = i2;
        this.e = galleryActivityHandler;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        GalleryActivityHandler galleryActivityHandler = this.e;
        switch (i2) {
            case 0:
                galleryActivityHandler.onRemoveBixbyAction(obj, bundle);
                return;
            case 1:
                galleryActivityHandler.onHiddenKey(obj, bundle);
                return;
            case 2:
                galleryActivityHandler.onBackPressInvokableStateChanged(obj, bundle);
                return;
            case 3:
                galleryActivityHandler.onActivityResume(obj, bundle);
                return;
            case 4:
                galleryActivityHandler.syncFragmentStack(obj, bundle);
                return;
            case 5:
                galleryActivityHandler.lambda$createSubscriberList$0(obj, bundle);
                return;
            case 6:
                galleryActivityHandler.onActivityResumeBG(obj, bundle);
                return;
            case 7:
                galleryActivityHandler.onActivityPause(obj, bundle);
                return;
            case 8:
                galleryActivityHandler.onActivityPauseBg(obj, bundle);
                return;
            case 9:
                galleryActivityHandler.onActivityConfigurationChanged(obj, bundle);
                return;
            case 10:
                galleryActivityHandler.onActivityCreate(obj, bundle);
                return;
            case 11:
                galleryActivityHandler.onThumbLoadDone(obj, bundle);
                return;
            case 12:
                galleryActivityHandler.onActivityDestroy(obj, bundle);
                return;
            case 13:
                galleryActivityHandler.onActivityBgColorChange(obj, bundle);
                return;
            case 14:
                galleryActivityHandler.onSettingAutoRotationChanged(obj, bundle);
                return;
            case 15:
                galleryActivityHandler.onSettingShapeButtonChanged(obj, bundle);
                return;
            case 16:
                galleryActivityHandler.onSharingServiceChanged(obj, bundle);
                return;
            case 17:
                galleryActivityHandler.onSharedAlbumBlockerChanged(obj, bundle);
                return;
            case 18:
                galleryActivityHandler.onSharedAlbumEnablerChanged(obj, bundle);
                return;
            case 19:
                galleryActivityHandler.onActivityRecreate(obj, bundle);
                return;
            case 20:
                galleryActivityHandler.adjustPopOverOptions(obj, bundle);
                return;
            case 21:
                galleryActivityHandler.onActivityCreateBG(obj, bundle);
                return;
            default:
                galleryActivityHandler.onBixbyAction(obj, bundle);
                return;
        }
    }
}
