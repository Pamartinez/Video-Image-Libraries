package D9;

import android.graphics.Bitmap;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.DelegateHelper;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreatureContactDelegate;
import com.samsung.android.gallery.module.mde.MdeDataSyncHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import com.samsung.android.motionphoto.utils.v2.OnProgressListener;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.datasync.DataSyncApi;
import com.samsung.android.sdk.mobileservice.social.datasync.result.DataSyncResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DataSyncApi.DataSyncResultCallback, GroupApi.GroupResultCallback, ShareApi.SpaceDeletionResultCallback, ShareApi.ShareBaseResultCallback, ShareApi.SpaceResultCallback, DataCollectionDelegate.OnDataCompletionListener, ThumbnailLoadedListener, OnProgressListener, ActivityResultCallback {
    public final /* synthetic */ int d;
    public final /* synthetic */ Consumer e;

    public /* synthetic */ a(int i2, Consumer consumer) {
        this.d = i2;
        this.e = consumer;
    }

    public void onActivityResult(Object obj) {
        int i2 = this.d;
        Consumer consumer = this.e;
        ActivityResult activityResult = (ActivityResult) obj;
        switch (i2) {
            case 9:
                consumer.accept((Object) null);
                return;
            default:
                CreatureContactDelegate.lambda$createContactPicker$1(consumer, activityResult);
                return;
        }
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        DelegateHelper.lambda$checkNetworkStatus$2(this.e, eventContext, arrayList);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.e.accept(bitmap);
    }

    public void onProgress(float f) {
        MotionScrap.Builder.setOnProgressListener$lambda$1(this.e, f);
    }

    public void onResult(BooleanResult booleanResult) {
        this.e.accept(Integer.valueOf(booleanResult.getStatus().getCode()));
    }

    public void onResult(DataSyncResult dataSyncResult) {
        MdeDataSyncHelper.lambda$requestSpaceListSort$0(this.e, dataSyncResult);
    }

    public void onResult(SpaceResult spaceResult) {
        int i2 = this.d;
        Consumer consumer = this.e;
        switch (i2) {
            case 4:
                consumer.accept(Integer.valueOf(spaceResult.getStatus().getCode()));
                return;
            default:
                consumer.accept(Integer.valueOf(spaceResult.getStatus().getCode()));
                return;
        }
    }

    public void onResult(Object obj) {
        int i2 = this.d;
        Consumer consumer = this.e;
        switch (i2) {
            case 1:
                consumer.accept(Integer.valueOf(((BooleanResult) obj).getStatus().getCode()));
                return;
            default:
                consumer.accept((SpaceResult) obj);
                return;
        }
    }
}
