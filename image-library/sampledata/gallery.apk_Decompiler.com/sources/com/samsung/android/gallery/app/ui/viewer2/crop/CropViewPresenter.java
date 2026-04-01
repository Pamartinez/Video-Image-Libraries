package com.samsung.android.gallery.app.ui.viewer2.crop;

import C3.C0391a;
import Fb.h;
import I7.b;
import I7.c;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.viewer2.crop.handler.CropHandler;
import com.samsung.android.gallery.app.ui.viewer2.crop.handler.CropHandlerFactory;
import com.samsung.android.gallery.app.ui.viewer2.crop.handler.CropHelper;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.media.GifPlayer;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropViewPresenter extends MvpBasePresenter<ICropView> {
    private final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new b(CropViewPresenter.this, 3));
        }
    };
    private GifPlayer mGifPlayer;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            FragmentActivity activity = CropViewPresenter.this.getActivity();
            if (activity == null) {
                Log.w((CharSequence) CropViewPresenter.this.TAG, "handleMessage skip", Integer.valueOf(message.what));
                return;
            }
            int i2 = message.what;
            if (i2 == 0) {
                Intent intent = (Intent) message.obj;
                intent.putExtra("src_uri", ContentUri.getUri(CropViewPresenter.this.mMediaItem));
                intent.putExtra("display_name", CropViewPresenter.this.mMediaItem.getDisplayName());
                activity.setResult(-1, intent);
                ((ICropView) CropViewPresenter.this.mView).finishCropViewer(intent);
            } else if (i2 == 1) {
                Toast.makeText(CropViewPresenter.this.getContext(), activity.getString(((Integer) message.obj).intValue()), 0).show();
                activity.setResult(0);
                activity.finish();
            }
        }
    };
    private MediaData mMediaData;
    /* access modifiers changed from: private */
    public MediaItem mMediaItem;
    /* access modifiers changed from: private */
    public Bitmap mOriginalBitmap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SaveCropJob implements ThreadPool.Job<Intent> {
        private final RectF mCropRect;

        public SaveCropJob(RectF rectF) {
            this.mCropRect = rectF;
        }

        public Bundle getBundle() {
            Bundle bundle = (Bundle) CropViewPresenter.this.getBlackboard().read("data://user/Crop/ReqInfo");
            if (bundle != null) {
                return bundle;
            }
            Intent intent = CropViewPresenter.this.getIntent();
            if (intent != null) {
                return intent.getExtras();
            }
            return null;
        }

        public Intent run(ThreadPool.JobContext jobContext) {
            if (jobContext.isCancelled()) {
                Log.w(CropViewPresenter.this.TAG, "SaveCropJob canceled before processing");
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Rect rect = new Rect((int) Math.ceil((double) this.mCropRect.left), (int) Math.ceil((double) this.mCropRect.top), (int) Math.floor((double) this.mCropRect.right), (int) Math.floor((double) this.mCropRect.bottom));
            StringCompat access$100 = CropViewPresenter.this.TAG;
            Log.i(access$100, "SaveCropJob " + rect);
            Bundle bundle = getBundle();
            MediaItem v = CropViewPresenter.this.mMediaItem;
            Intent intent = CropViewPresenter.this.getIntent();
            if (bundle == null || v == null || intent == null) {
                Log.e(CropViewPresenter.this.TAG, "SaveCropJob failed. no data");
                return null;
            }
            CropHandler create = CropHandlerFactory.create(intent, v, bundle, CropViewPresenter.this.mOriginalBitmap, CropViewPresenter.this.isGifCrop());
            boolean process = create.process(rect);
            StringCompat access$300 = CropViewPresenter.this.TAG;
            Log.d(access$300, "SaveCropJob {" + process + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            if (!jobContext.isCancelled()) {
                return create.buildIntent();
            }
            Log.w(CropViewPresenter.this.TAG, "SaveCropJob canceled after processing");
            create.recycle();
            return null;
        }
    }

    public CropViewPresenter(Blackboard blackboard, ICropView iCropView) {
        super(blackboard, iCropView);
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
            this.mMediaItem = null;
        }
    }

    private String getDecodedImageKey() {
        if (this.mMediaItem == null) {
            return "data://bitmap/viewer/dummy";
        }
        return ArgumentsUtil.appendArgs("data://bitmap/viewer/" + this.mMediaItem.getSimpleHashCode(), com.samsung.android.sum.core.message.Message.KEY_POSITION, String.valueOf(0));
    }

    /* access modifiers changed from: private */
    public boolean isGifCrop() {
        MediaItem mediaItem;
        Bundle bundle;
        boolean z;
        MimeType mimeType;
        if (!Features.isEnabled(Features.SUPPORT_AGIF_CROPPER) || (mediaItem = this.mMediaItem) == null || mediaItem.isBroken() || !this.mMediaItem.isGif()) {
            return false;
        }
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent != null) {
            bundle = launchIntent.getExtra();
        } else {
            bundle = null;
        }
        if (bundle == null || !bundle.getBoolean("support-crop-gif", false)) {
            z = false;
        } else {
            z = true;
        }
        if (!z || !this.mMediaItem.isUriItem() || (mimeType = MimeType.getMimeType(ThumbnailUtil.readMagic(Uri.parse(this.mMediaItem.getPath())))) == MimeType.GIF) {
            return z;
        }
        Log.e((CharSequence) this.TAG, "isGif not movie from stream", mimeType);
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveCropImageFile$1() {
        ((ICropView) this.mView).setProgressCircleVisibility(false);
        ((ICropView) this.mView).finishCropViewer((Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveCropImageFile$2() {
        ((ICropView) this.mView).setProgressCircleVisibility(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveCropImageFile$3(Future future) {
        if (future.isCancelled()) {
            ThreadUtil.runOnUiThread(new b(this, 1));
            return;
        }
        ThreadUtil.runOnUiThread(new b(this, 2));
        Intent intent = (Intent) future.get();
        if (intent != null) {
            Handler handler = this.mMainHandler;
            handler.sendMessage(handler.obtainMessage(0, intent));
            return;
        }
        finishWithErrorMsg(R.string.could_not_save_image);
    }

    private void notifyBrokenVideoLoaded() {
        onImageLoaded((Object) null, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        if (!((ICropView) this.mView).isViewReady() || isDestroyed()) {
            Log.w(this.TAG, "onDataChangedOnUi skip. view not ready");
        } else if (this.mMediaData.getCount() < 1) {
            ((ICropView) this.mView).finishCropViewer((Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void onImageLoaded(Object obj, Bundle bundle) {
        MediaItem mediaItem;
        Bitmap bitmap = (Bitmap) obj;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onImageLoaded {" + MediaItemUtil.getViewerBitmapKey(this.mMediaItem) + "}");
        if (bitmap == null && (mediaItem = this.mMediaItem) != null) {
            bitmap = getBrokenBitmap(mediaItem);
        } else if (isGifCrop()) {
            this.mGifPlayer = new GifPlayer();
        }
        ((ICropView) this.mView).setDefaultImage(bitmap);
        this.mOriginalBitmap = bitmap;
    }

    private void openMediaData(String str) {
        MediaItem mediaItem;
        closeMediaData();
        if (str != null) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(str);
            this.mMediaData = open;
            open.register(this.mDataChangeListener);
            MediaItem read = this.mMediaData.read(0);
            if (read != null) {
                mediaItem = CropHelper.mutateMediaItem(read);
            } else {
                mediaItem = null;
            }
            this.mMediaItem = mediaItem;
            return;
        }
        throw new NullPointerException("location is null");
    }

    /* access modifiers changed from: private */
    public void playMovie() {
        GifPlayer gifPlayer = this.mGifPlayer;
        if (gifPlayer != null && this.mMediaItem != null) {
            if (!gifPlayer.hasMovie()) {
                GifPlayer gifPlayer2 = this.mGifPlayer;
                Context context = getContext();
                MediaItem mediaItem = this.mMediaItem;
                ICropView iCropView = (ICropView) this.mView;
                Objects.requireNonNull(iCropView);
                c cVar = new c(iCropView);
                ICropView iCropView2 = (ICropView) this.mView;
                Objects.requireNonNull(iCropView2);
                gifPlayer2.decodeMovie(context, mediaItem, cVar, new c(iCropView2));
            }
            this.mGifPlayer.startMovie();
        }
    }

    private void requestOriginalBitmap() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            String DATA_REQUEST = CommandKey.DATA_REQUEST(getDecodedImageKey());
            if (this.mBlackboard.publishIfEmpty(DATA_REQUEST, mediaItem)) {
                StringCompat stringCompat = this.TAG;
                Log.p(stringCompat, "requestOriginalBitmap : " + DATA_REQUEST);
                return;
            }
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "requestOriginalBitmap : already requested " + DATA_REQUEST);
        }
    }

    private void saveCropImageFile() {
        if (this.mOriginalBitmap == null) {
            Log.e(this.TAG, "fail to save crop. bitmap is null");
            return;
        }
        ((ICropView) this.mView).setProgressCircleVisibility(true);
        ThreadPool.getInstance().submit(new SaveCropJob(((ICropView) this.mView).getRectToBeSaved()), new h(23, this));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        String removeArgs = ArgumentsUtil.removeArgs(getDecodedImageKey());
        StringCompat stringCompat = this.TAG;
        Log.v(stringCompat, "subscribe : " + removeArgs);
        arrayList.add(new SubscriberInfo(removeArgs, new C0391a(9, this)).setWorkingOnUI());
    }

    public void finishWithErrorMsg(int i2) {
        Handler handler = this.mMainHandler;
        handler.sendMessage(handler.obtainMessage(1, Integer.valueOf(i2)));
    }

    public Bitmap getBrokenBitmap(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3012) {
            saveCropImageFile();
            return true;
        } else if (i2 == 3013) {
            Optional.ofNullable(getActivity()).ifPresent(new I4.b(8));
            ((ICropView) this.mView).finishCropViewer((Object) null);
            return true;
        } else if (i2 != 4002) {
            return false;
        } else {
            return true;
        }
    }

    public void onEnterTransitionEndV2() {
        Bitmap bitmap = this.mOriginalBitmap;
        if (bitmap != null) {
            ((ICropView) this.mView).setDefaultImage(bitmap);
            return;
        }
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && mediaItem.isVideo() && this.mMediaItem.isBroken()) {
            notifyBrokenVideoLoaded();
        }
    }

    public void onViewAttach() {
        String str;
        super.onViewAttach();
        if (getLocationKey() == null) {
            str = "";
        } else {
            str = DataKey.getViewerDataKey(getLocationKey());
        }
        openMediaData(str);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        requestOriginalBitmap();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        BlackboardUtils.cancelAndEraseViewerBitmap(getBlackboard(), getDecodedImageKey());
        closeMediaData();
        this.mOriginalBitmap = null;
        GifPlayer gifPlayer = this.mGifPlayer;
        if (gifPlayer != null) {
            gifPlayer.releaseMovie();
        }
    }

    public void onViewPause() {
        GifPlayer gifPlayer;
        super.onViewPause();
        if (((ICropView) this.mView).isReadyCropView() && (gifPlayer = this.mGifPlayer) != null) {
            gifPlayer.stopMovie();
        }
    }

    public void onViewReady() {
        if (this.mGifPlayer != null) {
            ThreadUtil.postOnBgThread(new b(this, 0));
        }
    }

    public void onViewResume() {
        super.onViewResume();
        if (((ICropView) this.mView).isReadyCropView() && this.mGifPlayer != null) {
            ThreadUtil.postOnBgThreadDelayed(new b(this, 0), 500);
        }
    }
}
