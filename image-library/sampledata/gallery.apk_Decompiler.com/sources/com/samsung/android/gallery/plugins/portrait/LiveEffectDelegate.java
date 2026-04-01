package com.samsung.android.gallery.plugins.portrait;

import A.a;
import D7.g;
import N2.j;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.database.dal.local.LocalProviderHelper;
import com.samsung.android.gallery.database.dal.local.table.HiddenShareHelper;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.module.story.transcode.util.CommonUtil;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SefData;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import com.samsung.o3dp.lib3dphotography.MeshCompressOption;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import com.samsung.o3dp.lib3dphotography.O3DPSurfaceView;
import com.samsung.o3dp.lib3dphotography.O3DPVideoListener;
import com.samsung.o3dp.lib3dphotography.O3DPhotoConfig;
import com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.animation.animations.GyroAnimation;
import com.samsung.o3dp.lib3dphotography.mesh.MeshSefManager;
import com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension;
import com.samsung.o3dp.lib3dphotography.utils.FileExtensionParser;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LiveEffectDelegate {
    O3DPSurfaceView m3DSurfaceView;
    /* access modifiers changed from: private */
    public Animation mAnimation;
    private LottieAnimationView mArrowDown;
    private LottieAnimationView mArrowLeft;
    private LottieAnimationView mArrowRight;
    private LottieAnimationView mArrowUp;
    Bitmap mBitmap;
    private final boolean mEnableDynamics;
    /* access modifiers changed from: private */
    public GyroAnimation mGyroAnimation;
    private final ILiveEffect mILiveEffect;
    private boolean mIsDynamicsVIPlayed = false;
    private boolean mIsFromDetailView = false;
    private boolean mIsGyroMode = false;
    MediaItem mMediaItem;
    AtomicBoolean mProcessingState = new AtomicBoolean(false);
    ProgressBarDialog mProgressDialog;

    /* renamed from: com.samsung.android.gallery.plugins.portrait.LiveEffectDelegate$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$o3dp$lib3dphotography$O3DPListener$ErrorType;
        static final /* synthetic */ int[] $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        static {
            /*
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension[] r0 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension = r0
                r1 = 1
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension r2 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.JPEG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension r3 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.HEIF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.samsung.o3dp.lib3dphotography.O3DPListener$ErrorType[] r2 = com.samsung.o3dp.lib3dphotography.O3DPListener.ErrorType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$samsung$o3dp$lib3dphotography$O3DPListener$ErrorType = r2
                com.samsung.o3dp.lib3dphotography.O3DPListener$ErrorType r3 = com.samsung.o3dp.lib3dphotography.O3DPListener.ErrorType.VIDEO_RECORDING_ABORTED     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$samsung$o3dp$lib3dphotography$O3DPListener$ErrorType     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.samsung.o3dp.lib3dphotography.O3DPListener$ErrorType r2 = com.samsung.o3dp.lib3dphotography.O3DPListener.ErrorType.VIDEO_RECORDING_FAIL     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$samsung$o3dp$lib3dphotography$O3DPListener$ErrorType     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.samsung.o3dp.lib3dphotography.O3DPListener$ErrorType r1 = com.samsung.o3dp.lib3dphotography.O3DPListener.ErrorType.INPUT_INVALID_FAIL     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.plugins.portrait.LiveEffectDelegate.AnonymousClass4.<clinit>():void");
        }
    }

    public LiveEffectDelegate(ILiveEffect iLiveEffect) {
        this.mILiveEffect = iLiveEffect;
        this.mEnableDynamics = Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_DYNAMIC);
    }

    private void applyLiveEffectToImage(String str) {
        long dateModified = FileUtils.getDateModified(str);
        SefData read = new SefData().read(str);
        FileUtils.writeFile(str, getEncodedBytes(FileUtils.readFile(str), this.mMediaItem));
        read.write(str);
        MeshSefManager.mux(str);
        FileUtils.setDateModified(str, dateModified);
    }

    private void bindDynamicsVIArrows(ViewGroup viewGroup) {
        if (this.mEnableDynamics) {
            this.mArrowLeft = (LottieAnimationView) viewGroup.findViewById(R$id.photo_3d_arrow_left);
            this.mArrowRight = (LottieAnimationView) viewGroup.findViewById(R$id.photo_3d_arrow_right);
            this.mArrowUp = (LottieAnimationView) viewGroup.findViewById(R$id.photo_3d_arrow_up);
            this.mArrowDown = (LottieAnimationView) viewGroup.findViewById(R$id.photo_3d_arrow_down);
        }
    }

    private void cancelDynamicsVIAnimation() {
        if (this.mEnableDynamics) {
            this.mArrowLeft.setVisibility(8);
            this.mArrowRight.setVisibility(8);
            this.mArrowUp.setVisibility(8);
            this.mArrowDown.setVisibility(8);
        }
    }

    private void clearOldCachedFiles() {
        Cursor hiddenShareCursor = getHiddenShareCursor();
        if (hiddenShareCursor != null) {
            try {
                int max = Math.max(0, hiddenShareCursor.getCount() - 10);
                LocalProviderHelper localProviderHelper = new LocalProviderHelper(this.mILiveEffect.getActivity().getContentResolver());
                int i2 = 0;
                while (true) {
                    if (!hiddenShareCursor.moveToNext()) {
                        break;
                    } else if (i2 >= max) {
                        break;
                    } else {
                        localProviderHelper.deleteShareFile(hiddenShareCursor.getString(0));
                        i2++;
                    }
                }
                Log.d("LiveEffectDelegate", "clearOldCachedFiles " + max);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (hiddenShareCursor != null) {
            hiddenShareCursor.close();
            return;
        }
        return;
        throw th;
    }

    private void convert(Context context, MediaItem mediaItem, int i2, BiConsumer<O3DPListener.ErrorType, String> biConsumer) {
        convert(context, mediaItem, i2, false, biConsumer);
    }

    private void convertAndSave(Context context, BiConsumer<String, Uri> biConsumer) {
        convert(context, getSaveItem(), R$string.saving_live_effect, new g(this, context, biConsumer));
    }

    private void convertAndSetAs(Context context) {
        MediaItem cacheItem = getCacheItem(context, true);
        if (cacheItem == null) {
            Log.d("LiveEffectDelegate", "convertAndSetAs failed. wrong path", Logger.getEncodedString(this.mMediaItem.getPath()));
            return;
        }
        convert(context, cacheItem, R$string.preparing_wallpaper, true, new f(this, context, 1));
        Log.d("LiveEffectDelegate", "convertAndSetAs", Logger.getEncodedString(this.mMediaItem.getPath()));
    }

    private void convertAndShare(Context context) {
        MediaItem cacheItem = getCacheItem(context, false);
        if (cacheItem == null) {
            Log.e((CharSequence) "LiveEffectDelegate", "convertAndShare failed. wrong path", Logger.getEncodedString(this.mMediaItem.getPath()));
            return;
        }
        convert(context, cacheItem, R$string.preparing_to_share, new f(this, context, 0));
        Log.d("LiveEffectDelegate", "convertAndShare", Logger.getEncodedString(this.mMediaItem.getPath()));
    }

    private boolean convertImage(Context context, String str, BiConsumer<O3DPListener.ErrorType, String> biConsumer) {
        if (str == null) {
            Log.e("LiveEffectDelegate", "convert failed. null path");
            showToast(context, R$string.conversion_canceled);
            return false;
        } else if (this.mProcessingState.get()) {
            Log.e("LiveEffectDelegate", "convert failed. already processing");
            return false;
        } else {
            this.m3DSurfaceView.getConfig().setAnimation(this.mAnimation);
            CompletableFuture completableFuture = new CompletableFuture();
            this.mProcessingState.set(true);
            ThreadUtil.runOnUiThread(new n(this, str, completableFuture, (BiConsumer) biConsumer));
            try {
                return ((Boolean) completableFuture.get(3, TimeUnit.SECONDS)).booleanValue();
            } catch (Exception e) {
                j.v("recordSef failed: ", e, "LiveEffectDelegate");
                biConsumer.accept(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, (Object) null);
                return false;
            }
        }
    }

    private void convertVideo(Context context, String str, int i2, BiConsumer<O3DPListener.ErrorType, String> biConsumer) {
        if (str == null) {
            Log.e("LiveEffectDelegate", "convert failed. null path");
            showToast(context, R$string.conversion_canceled);
        } else if (this.mProcessingState.get()) {
            Log.e("LiveEffectDelegate", "convert failed. already processing");
        } else {
            this.mProcessingState.set(true);
            final long currentTimeMillis = System.currentTimeMillis();
            final O3DPhotoPipeline o3DPhotoPipeline = new O3DPhotoPipeline(context, this.m3DSurfaceView.getConfig());
            showDialog(context, i2, new g(1, o3DPhotoPipeline));
            final String str2 = str;
            final BiConsumer<O3DPListener.ErrorType, String> biConsumer2 = biConsumer;
            o3DPhotoPipeline.recordVideo(this.mBitmap, str2, new O3DPVideoListener() {
                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onFailed$2(O3DPListener.ErrorType errorType, BiConsumer biConsumer) {
                    LiveEffectDelegate.this.postConvert((String) null, errorType, biConsumer);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onProgress$3(float f) {
                    ProgressBarDialog progressBarDialog = LiveEffectDelegate.this.mProgressDialog;
                    if (progressBarDialog != null) {
                        progressBarDialog.onUpdate((int) (f * 100.0f));
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onVideoEncoded$0(Uri uri, BiConsumer biConsumer) {
                    LiveEffectDelegate.this.postConvert(uri.getPath(), (O3DPListener.ErrorType) null, biConsumer);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onVideoEncoded$1(Uri uri, String str, BiConsumer biConsumer) {
                    if (uri != null) {
                        LiveEffectDelegate liveEffectDelegate = LiveEffectDelegate.this;
                        liveEffectDelegate.insertLocation(str, liveEffectDelegate.mMediaItem.getLatitude(), LiveEffectDelegate.this.mMediaItem.getLongitude());
                        ThreadUtil.postOnUiThread(new j(this, uri, biConsumer, 2));
                    }
                }

                public void onFailed(O3DPListener.ErrorType errorType, String str) {
                    Log.e("LiveEffectDelegate", "convert#onFailed" + Logger.vt(errorType, str, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
                    LiveEffectDelegate.this.restoreGyroState();
                    ThreadUtil.runOnUiThread(new j(this, errorType, biConsumer2, 1));
                    o3DPhotoPipeline.release();
                }

                public void onProgress(float f) {
                    ThreadUtil.runOnUiThread(new s(this, f));
                }

                public void onVideoEncoded(Uri uri) {
                    a.A(new Object[]{uri, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}, new StringBuilder("convert#onVideoEncoded"), "LiveEffectDelegate");
                    LiveEffectDelegate.this.restoreGyroState();
                    ThreadUtil.runOnBgThread(new n(this, uri, str2, biConsumer2));
                    o3DPhotoPipeline.release();
                }
            });
        }
    }

    private O3DPListener createO3DPListener(final BiConsumer<View, Boolean> biConsumer) {
        return new O3DPListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onFailed$0(BiConsumer biConsumer) {
                biConsumer.accept(LiveEffectDelegate.this.m3DSurfaceView, Boolean.FALSE);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onRenderStart$1(BiConsumer biConsumer) {
                biConsumer.accept(LiveEffectDelegate.this.m3DSurfaceView, Boolean.TRUE);
            }

            public void onFailed(O3DPListener.ErrorType errorType, String str) {
                Log.e((CharSequence) "LiveEffectDelegate", "onFailed", errorType, str);
                LiveEffectDelegate.this.m3DSurfaceView.post(new r(this, biConsumer, 1));
            }

            public void onRecommendedAnimation(Animation animation) {
                Log.d("LiveEffectDelegate", "onRecommendedAnimation", animation.getName());
                LiveEffectDelegate.this.mAnimation = animation;
            }

            public void onRenderStart() {
                Log.d("LiveEffectDelegate", "onRenderStart");
                LiveEffectDelegate.this.m3DSurfaceView.post(new r(this, biConsumer, 0));
                LiveEffectDelegate liveEffectDelegate = LiveEffectDelegate.this;
                liveEffectDelegate.mGyroAnimation = new GyroAnimation(liveEffectDelegate.mAnimation.getAnimationScript());
                LiveEffectDelegate.this.toggleGyroMode();
            }
        };
    }

    private O3DPSurfaceView createSurfaceView(ViewGroup viewGroup) {
        O3DPSurfaceView o3DPSurfaceView = (O3DPSurfaceView) viewGroup.findViewById(R$id.photo_3d_view);
        O3DPhotoConfig config = o3DPSurfaceView.getConfig();
        config.getMeshDecimatorConfig().enabled = false;
        config.setDynamicsEnabled(this.mEnableDynamics);
        return o3DPSurfaceView;
    }

    private MediaItem getCacheItem(Context context, boolean z) {
        String path = this.mMediaItem.getPath();
        String cachePath = getCachePath(context, z);
        if (path == null || cachePath == null) {
            return null;
        }
        MediaItem clone = this.mMediaItem.clone();
        clone.setPath(cachePath);
        if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            FileUtils.copy(path, cachePath);
            MediaItemUtil.updateFromUri(clone);
        }
        return clone;
    }

    private String getCachePath(Context context) {
        return getCachePath(context, false);
    }

    private byte[] getEncodedBytes(byte[] bArr, MediaItem mediaItem) {
        String path = mediaItem.getPath();
        if (path != null) {
            int i2 = AnonymousClass4.$SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension[FileExtensionParser.getSupportedExtension(path).ordinal()];
            if (i2 == 1) {
                byte[] encodeJpeg3d = this.m3DSurfaceView.getPipeline().encodeJpeg3d(bArr, MeshCompressOption.DRACO);
                if (encodeJpeg3d != null) {
                    return encodeJpeg3d;
                }
                throw new IllegalStateException("Failed to encode jpeg image. encoded bytes is null");
            } else if (i2 == 2) {
                byte[] encodeHeif3d = this.m3DSurfaceView.getPipeline().encodeHeif3d(bArr, MeshCompressOption.DRACO);
                if (encodeHeif3d != null) {
                    return encodeHeif3d;
                }
                throw new IllegalStateException("Failed to encode heic image. encoded byte is null");
            } else {
                throw new IllegalArgumentException("Can't apply live effect to image. given image is not supported type");
            }
        } else {
            throw new IllegalStateException("Given path is null. Please check media item gives valid path.");
        }
    }

    private Cursor getHiddenShareCursor() {
        return HiddenShareHelper.queryToShareTable(new String[]{"__absPath", "meta_data"}, "owner=?", new String[]{"liveEffect"}, "date_added ASC");
    }

    private MediaItem getSaveItem() {
        if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            return this.mMediaItem;
        }
        MediaItem mediaItem = new MediaItem();
        mediaItem.setPath(getSavePath());
        return mediaItem;
    }

    private String getSavePath() {
        return (String) Optional.ofNullable(this.mMediaItem.getPath()).map(new a(1)).orElse((Object) null);
    }

    /* access modifiers changed from: private */
    public void insertLocation(String str, double d, double d2) {
        try {
            if (MapUtil.isValidLocation(d, d2)) {
                MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(str);
                mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LATITUDE, String.valueOf(d));
                mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LONGITUDE, String.valueOf(d2));
                mediaMetadataInterface.saveAttributes();
            }
        } catch (Exception e) {
            Log.e("LiveEffectDelegate", e.toString());
        }
    }

    private boolean isCachedItemExist(boolean z) {
        SecureFile secureFile;
        Cursor hiddenShareCursor = getHiddenShareCursor();
        while (hiddenShareCursor != null) {
            try {
                if (!hiddenShareCursor.moveToNext()) {
                    break;
                }
                String string = hiddenShareCursor.getString(0);
                String string2 = hiddenShareCursor.getString(1);
                if (string != null && string.equals(getCachePath(this.mILiveEffect.getActivity(), z))) {
                    if (string2 == null || !string2.equals(String.valueOf(this.mMediaItem.getSimpleHashCode()))) {
                        FileUtils.delete(string);
                        HiddenShareHelper.delete(string);
                    } else {
                        if (!TextUtils.isEmpty(string)) {
                            secureFile = new SecureFile(string);
                        } else {
                            secureFile = null;
                        }
                        if (secureFile == null || !secureFile.exists()) {
                            HiddenShareHelper.delete(string);
                        } else {
                            hiddenShareCursor.close();
                            return true;
                        }
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (hiddenShareCursor != null) {
            hiddenShareCursor.close();
        }
        return false;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyFlexModeTransition$22(View view, final Runnable runnable) {
        ViewUtils.setVisibility(view, 0);
        AnimatorSet duration = new AnimatorSet().setDuration(200);
        duration.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
        ScaleAnimator scaleAnimator = new ScaleAnimator(view, 0.6f, 1.0f);
        duration.playTogether(new Animator[]{new AlphaAnimator(view, 0.0f, 1.0f), scaleAnimator});
        if (runnable != null) {
            duration.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    runnable.run();
                }
            });
        }
        duration.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$4(BiConsumer biConsumer) {
        biConsumer.accept(this.m3DSurfaceView, Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$5(O3DPListener o3DPListener, MediaItem mediaItem, Bitmap bitmap, BiConsumer biConsumer) {
        String path = this.mMediaItem.getPath();
        if (this.mMediaItem.isLiveEffect() && this.mMediaItem.isVideo()) {
            Log.d("LiveEffectDelegate", "liveEffect for Mp4");
            this.m3DSurfaceView.renderLiveEffect(path, o3DPListener, SupportedExtension.MP4);
        } else if (this.mMediaItem.isLiveEffect() && this.mMediaItem.isImage()) {
            try {
                SupportedExtension supportedExtension = FileExtensionParser.getSupportedExtension(this.mMediaItem.getPath());
                if (supportedExtension == SupportedExtension.HEIF || supportedExtension == SupportedExtension.JPEG) {
                    Log.d("LiveEffectDelegate", "Render liveEffect for extension " + supportedExtension.name());
                    this.m3DSurfaceView.renderLiveEffect(path, o3DPListener, supportedExtension);
                    return;
                }
                Log.e("LiveEffectDelegate", "Failed to render live effect, given supported Extension: " + supportedExtension.name());
            } catch (IOException | IllegalArgumentException e) {
                Log.e((CharSequence) "LiveEffectDelegate", "Failed to get supported extension from file", e);
            }
        } else if (this.mMediaItem.isImage()) {
            Log.d("LiveEffectDelegate", "LiveEffect for Image", Long.valueOf(mediaItem.getFileId()), bitmap);
            if (mediaItem.getOrientation() > 0) {
                this.mBitmap = BitmapUtils.rotateBitmap(this.mBitmap, mediaItem.getOrientation(), mediaItem.getOrientationTag());
            }
            this.m3DSurfaceView.setImageBitmap(this.mBitmap, o3DPListener);
        } else {
            Log.e("LiveEffectDelegate", "We do not support the mimeType: " + mediaItem.getMimeType());
            this.m3DSurfaceView.post(new i(0, this, biConsumer));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$convert$15(Context context, BiConsumer biConsumer, FileItemInterface fileItemInterface) {
        return Boolean.valueOf(convertImage(context, fileItemInterface.getPath(), biConsumer));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$convertAndSave$13(Context context, BiConsumer biConsumer, O3DPListener.ErrorType errorType, String str) {
        if (errorType != null) {
            showToastByError(context, errorType);
            biConsumer.accept((Object) null, (Object) null);
            return;
        }
        scanFile(context, biConsumer, str);
        postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_SAVE);
        Log.d("LiveEffectDelegate", "convertAndSave", Logger.getEncodedString(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$convertAndSetAs$9(Context context, O3DPListener.ErrorType errorType, String str) {
        if (errorType != null) {
            showToast(context, R$string.conversion_canceled);
            return;
        }
        new SetAsWallpaperDialog().show(context, str);
        HiddenShareHelper.insertToShareTable(str, String.valueOf(this.mMediaItem.getSimpleHashCode()), "liveEffect");
        postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_WALLPAPER);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$convertAndShare$8(Context context, O3DPListener.ErrorType errorType, String str) {
        if (errorType != null) {
            showToastByError(context, errorType);
            return;
        }
        startShareActivity(context, str);
        HiddenShareHelper.insertToShareTable(str, String.valueOf(this.mMediaItem.getSimpleHashCode()), "liveEffect");
        postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_SHARE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$convertImage$18(String str, CompletableFuture completableFuture, BiConsumer biConsumer) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            applyLiveEffectToImage(str);
            completableFuture.complete(Boolean.TRUE);
            biConsumer.accept((Object) null, str);
            Log.d("LiveEffectDelegate", "Applying live effect takes " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            this.mProcessingState.set(false);
            restoreGyroState();
        } catch (IOException | IllegalStateException e) {
            completableFuture.complete(Boolean.FALSE);
            biConsumer.accept(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, (Object) null);
            Log.e("LiveEffectDelegate", "Apply failed: " + e);
            this.mProcessingState.set(false);
            restoreGyroState();
        } catch (IllegalArgumentException e7) {
            completableFuture.complete(Boolean.FALSE);
            biConsumer.accept(O3DPListener.ErrorType.INPUT_INVALID_FAIL, (Object) null);
            Log.e("LiveEffectDelegate", "Apply failed: " + e7);
            this.mProcessingState.set(false);
            restoreGyroState();
        } catch (Throwable th) {
            this.mProcessingState.set(false);
            restoreGyroState();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissDialog$21() {
        ProgressBarDialog progressBarDialog = this.mProgressDialog;
        if (progressBarDialog != null) {
            progressBarDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$getCachePath$10(String str, File file) {
        return file.getPath() + File.separator + FileUtils.getBaseName(this.mMediaItem.getPath()) + "_LiveEffect." + str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playDynamicsVIAnimation$0() {
        this.mArrowLeft.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playDynamicsVIAnimation$1() {
        this.mArrowRight.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playDynamicsVIAnimation$2() {
        this.mArrowUp.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playDynamicsVIAnimation$3() {
        this.mArrowDown.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$save$12(BiConsumer biConsumer) {
        if (this.mMediaItem.isLiveEffect()) {
            Log.w("LiveEffectDelegate", "save cached item skipped because it's already converted");
        } else if (!isCachedItemExist(false) || Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            clearOldCachedFiles();
            convertAndSave(this.mILiveEffect.getActivity(), biConsumer);
        } else {
            saveCachedItem(this.mILiveEffect.getActivity(), biConsumer, getSavePath());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanFile$14(Context context, int i2, BiConsumer biConsumer, String str, Uri uri) {
        showToast(context, context.getString(i2, new Object[]{BucketUtils.getTranslatedDirectory(str)}));
        biConsumer.accept(str, uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAsWallpaper$7() {
        if (isCachedItemExist(true)) {
            setAsCachedItem(this.mILiveEffect.getActivity(), getCachePath(this.mILiveEffect.getActivity(), true));
            return;
        }
        clearOldCachedFiles();
        convertAndSetAs(this.mILiveEffect.getActivity());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$share$6() {
        if (this.mMediaItem.isLiveEffect()) {
            shareCachedItem(this.mILiveEffect.getActivity(), this.mMediaItem.getPath());
        } else if (isCachedItemExist(false)) {
            shareCachedItem(this.mILiveEffect.getActivity(), getCachePath(this.mILiveEffect.getActivity()));
        } else {
            clearOldCachedFiles();
            convertAndShare(this.mILiveEffect.getActivity());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$20(Context context, Runnable runnable, int i2) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressBarDialog(context, new p(runnable));
        }
        this.mProgressDialog.setTitle(context.getString(i2));
        this.mProgressDialog.show();
        pauseAnim();
    }

    private void playDynamicsVIAnimation() {
        if (this.mEnableDynamics) {
            this.mArrowLeft.post(new d(2, this));
            this.mArrowRight.post(new d(3, this));
            this.mArrowUp.post(new d(4, this));
            this.mArrowDown.post(new d(5, this));
            this.mIsDynamicsVIPlayed = true;
        }
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_3D_PHOTO.toString(), analyticsEventId.toString(), this.mAnimation.getName());
    }

    /* access modifiers changed from: private */
    public void postConvert(String str, O3DPListener.ErrorType errorType, BiConsumer<O3DPListener.ErrorType, String> biConsumer) {
        dismissDialog();
        resumeAnim();
        biConsumer.accept(errorType, str);
        this.mProcessingState.set(false);
    }

    /* access modifiers changed from: private */
    public void restoreGyroState() {
        if (this.mIsGyroMode) {
            Log.d("LiveEffectDelegate", "After recording image, restore gyro state");
            turnOnGyro();
        }
    }

    private void saveCachedItem(Context context, BiConsumer<String, Uri> biConsumer, String str) {
        try {
            String cachePath = getCachePath(context);
            if (cachePath != null) {
                if (FileUtils.exists(cachePath)) {
                    TimeTickLog timeTickLog = new TimeTickLog();
                    CommonUtil.updateCreationTime(cachePath);
                    timeTickLog.tick();
                    if (FileUtils.copy(cachePath, str)) {
                        timeTickLog.tick();
                        scanFile(context, biConsumer, str);
                        postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_SAVE);
                        Log.d("LiveEffectDelegate", "saveCachedItem", Logger.getEncodedString(str));
                        return;
                    }
                    biConsumer.accept((Object) null, (Object) null);
                    Log.e((CharSequence) "LiveEffectDelegate", "saveCachedItem failed", Logger.getEncodedString(str));
                    return;
                }
            }
            Log.e((CharSequence) "LiveEffectDelegate", "saveCachedItem failed due to invalid path", Logger.getEncodedString(cachePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanFile(Context context, BiConsumer<String, Uri> biConsumer, String str) {
        int i2;
        if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            i2 = R$string.toast_live_effect_applied;
        } else {
            i2 = R$string.toast_video_saved_in;
        }
        MediaScannerConnection.scanFile(context, new String[]{str}, (String[]) null, new h(this, context, i2, biConsumer));
    }

    private void setAsCachedItem(Context context, String str) {
        new SetAsWallpaperDialog().show(context, str);
        Log.d("LiveEffectDelegate", "setAsCachedItem", Logger.getEncodedString(str));
    }

    private void shareCachedItem(Context context, String str) {
        startShareActivity(context, str);
        postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_SHARE);
        Log.d("LiveEffectDelegate", "shareCachedItem", Logger.getEncodedString(str));
    }

    private void showDialog(Context context, int i2, Runnable runnable) {
        ThreadUtil.runOnUiThread(new o(this, context, runnable, i2));
    }

    private void showToastByError(Context context, O3DPListener.ErrorType errorType) {
        int i2 = AnonymousClass4.$SwitchMap$com$samsung$o3dp$lib3dphotography$O3DPListener$ErrorType[errorType.ordinal()];
        if (i2 == 1) {
            showToast(context, R$string.conversion_canceled);
        } else if (i2 == 2) {
            showToast(context, R$string.video_save_fail);
        } else if (i2 != 3) {
            showToast(context, R$string.apply_live_effect_failed);
        } else {
            showToast(context, R$string.file_format_not_supported);
        }
    }

    private void startShareActivity(Context context, String str) {
        Intent intent;
        File file = new File(str);
        Uri uri = FileProviderUtil.getUri(context, file);
        long length = file.length();
        Intent intent2 = new Intent();
        intent2.setAction("android.intent.action.SEND");
        intent2.putExtra("android.intent.extra.STREAM", uri);
        intent2.setType("video/*");
        intent2.addFlags(1);
        intent2.putExtra("exit_on_sent", false);
        intent2.putExtra("more_actions_print", 0);
        intent2.putExtra("more_actions_quick_connect", 1);
        intent2.putExtra("more_actions_package_name", "com.sec.android.gallery3d");
        intent2.putExtra("more_actions_screen_mirroring", 0);
        intent2.putExtra("more_actions_screen_sharing", 0);
        intent2.putExtra("more_actions_dlna", 1);
        intent2.putExtra("location_key", "");
        intent2.putExtra("item_index", -1);
        ShareComponent shareComponent = this.mILiveEffect.getShareComponent();
        if (shareComponent == null || !shareComponent.hasComponents()) {
            intent = Intent.createChooser(intent2, context.getString(R$string.share_short));
            intent.putExtra("sem_extra_chooser_content_count", 1);
            intent.putExtra("sem_extra_chooser_content_size", StringCompat.toReadableSize((double) length));
            Intent intent3 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
            if (intent3 != null) {
                intent3.addFlags(134742016);
            }
        } else {
            intent2.setComponent(new ComponentName(shareComponent.getPackageName(), shareComponent.getClassName()));
            if (shareComponent.isFromBixby()) {
                intent2.putExtra("action_send_addition", "com.sec.android.gallery3d");
            }
            intent = intent2;
        }
        try {
            context.startActivity(intent);
            Log.d("LiveEffectDelegate", "startShareActivity " + intent2);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startShareActivity failed. e="), "LiveEffectDelegate");
        }
    }

    private void turnOffGyro() {
        Log.d("LiveEffectDelegate", "set animation mode");
        this.m3DSurfaceView.getConfig().setAnimation(this.mAnimation);
        this.mIsGyroMode = false;
    }

    private void turnOnGyro() {
        Log.d("LiveEffectDelegate", "set gyro mode");
        this.m3DSurfaceView.getConfig().setAnimation(this.mGyroAnimation);
        this.mIsGyroMode = true;
        if (this.mIsDynamicsVIPlayed) {
            cancelDynamicsVIAnimation();
        }
    }

    public void applyFlexModeTransition(View view, int i2) {
        applyFlexModeTransition(view, i2, (Runnable) null);
    }

    public void applyTransition(View view, View view2) {
        SimpleAnimator.alphaOut(view, 380);
        SimpleAnimator.fadeIn(view2, 380);
    }

    public void bindView(ViewGroup viewGroup, MediaItem mediaItem, Bitmap bitmap, BiConsumer<View, Boolean> biConsumer) {
        this.mMediaItem = mediaItem;
        this.mBitmap = bitmap;
        this.m3DSurfaceView = createSurfaceView(viewGroup);
        bindDynamicsVIArrows(viewGroup);
        SimpleThreadPool.getInstance().execute(new q(this, createO3DPListener(biConsumer), mediaItem, bitmap, biConsumer));
    }

    public void dismissDialog() {
        ThreadUtil.runOnUiThread(new d(0, this));
    }

    public boolean isGyroMode() {
        return this.mIsGyroMode;
    }

    public void pauseAnim() {
        String str;
        if (this.m3DSurfaceView == null) {
            str = " skip";
        } else {
            str = "";
        }
        Log.d("LiveEffectDelegate", "pauseAnim".concat(str));
        O3DPSurfaceView o3DPSurfaceView = this.m3DSurfaceView;
        if (o3DPSurfaceView != null) {
            o3DPSurfaceView.pauseAnimation();
        }
    }

    public void resumeAnim() {
        String str;
        if (this.m3DSurfaceView == null) {
            str = " skip";
        } else {
            str = "";
        }
        Log.d("LiveEffectDelegate", "resumeAnim".concat(str));
        O3DPSurfaceView o3DPSurfaceView = this.m3DSurfaceView;
        if (o3DPSurfaceView != null) {
            o3DPSurfaceView.resumeAnimation();
        }
    }

    public void save(BiConsumer<String, Uri> biConsumer) {
        boolean z;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || this.mBitmap == null) {
            boolean z3 = false;
            if (mediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mBitmap != null) {
                z3 = true;
            }
            Log.e((CharSequence) "LiveEffectDelegate", "save failed. illegal arguments", valueOf, Boolean.valueOf(z3));
            showToast((Context) this.mILiveEffect.getActivity(), R$string.conversion_canceled);
            return;
        }
        SimpleThreadPool.getInstance().execute(new i(1, this, biConsumer));
    }

    public void setAsWallpaper() {
        boolean z;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || this.mBitmap == null) {
            boolean z3 = false;
            if (mediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mBitmap != null) {
                z3 = true;
            }
            Log.e((CharSequence) "LiveEffectDelegate", "wallpaper failed. illegal arguments", valueOf, Boolean.valueOf(z3));
            showToast((Context) this.mILiveEffect.getActivity(), R$string.conversion_canceled);
            return;
        }
        SimpleThreadPool.getInstance().execute(new d(6, this));
    }

    public void setIsFromDetailView(boolean z) {
        this.mIsFromDetailView = z;
    }

    public void share() {
        boolean z;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || this.mBitmap == null) {
            boolean z3 = false;
            if (mediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mBitmap != null) {
                z3 = true;
            }
            Log.e((CharSequence) "LiveEffectDelegate", "share failed. illegal arguments", valueOf, Boolean.valueOf(z3));
            showToast((Context) this.mILiveEffect.getActivity(), R$string.conversion_canceled);
            return;
        }
        SimpleThreadPool.getInstance().execute(new d(1, this));
    }

    public void showToast(Context context, int i2) {
        showToast(context, context.getString(i2));
    }

    public void toggleGyroMode() {
        if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            if (this.mIsGyroMode) {
                turnOffGyro();
            } else {
                turnOnGyro();
            }
            if (this.mIsFromDetailView) {
                postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_GYRO_MODE_FROM_DETAIL_VIEW, this.mIsGyroMode);
            } else {
                postAnalyticsLog(AnalyticsEventId.EVENT_AI_EDIT_3D_PHOTO_GYRO_MODE, this.mIsGyroMode);
            }
        }
        if (!this.mIsGyroMode && !this.mIsDynamicsVIPlayed) {
            playDynamicsVIAnimation();
        }
    }

    public void updateContainerMargin(ViewGroup viewGroup, boolean z) {
        View findViewById = viewGroup.findViewById(R$id.container);
        if (z) {
            ViewMarginUtils.setBottomMargin(findViewById, ViewUtils.getHeight(viewGroup) / 2);
        } else {
            ViewMarginUtils.setBottomMargin(findViewById, 0);
        }
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [com.samsung.android.gallery.module.fileio.redact.OnProgress, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.util.function.BiConsumer, java.lang.Object] */
    private void convert(Context context, MediaItem mediaItem, int i2, boolean z, BiConsumer<O3DPListener.ErrorType, String> biConsumer) {
        String str = (z || !Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) ? "video" : "image";
        if (str.equals("image")) {
            FileRedactorBuilder.edit(new MediaItem[]{mediaItem}).setOperation(new k(this, context, biConsumer)).setCallback(new Object()).withMediaScan(new Object()).ignoreGroup().build().run();
        } else if (str.equals("video")) {
            convertVideo(context, mediaItem.getPath(), i2, biConsumer);
        }
    }

    private String getCachePath(Context context, boolean z) {
        return (String) Optional.ofNullable(context.getExternalFilesDir(".share")).map(new e(this, (z || !Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) ? IFormat.FORMAT_MP4 : FileUtils.getExtension(this.mMediaItem.getPath()))).orElse((Object) null);
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId, boolean z) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_3D_PHOTO.toString(), analyticsEventId.toString(), String.valueOf(z ? 1 : 0));
    }

    private void showToast(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public void applyFlexModeTransition(View view, int i2, Runnable runnable) {
        ViewUtils.setVisibility(view, 4);
        ThreadUtil.postOnUiThreadDelayed(new j(this, view, runnable, 0), (long) i2);
    }
}
