package com.samsung.android.gallery.module.lottie.recorder;

import F2.U;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.transformer.AudioEncoderSettings;
import androidx.media3.transformer.Composition;
import androidx.media3.transformer.DefaultEncoderFactory;
import androidx.media3.transformer.EditedMediaItem;
import androidx.media3.transformer.EditedMediaItemSequence;
import androidx.media3.transformer.ExportException;
import androidx.media3.transformer.ExportResult;
import androidx.media3.transformer.Transformer;
import androidx.media3.transformer.VideoEncoderSettings;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.lottie.recorder.BgmProvider;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BgmMixer {
    private final Runnable mCompleteCallback;
    private final ExportInfo mExportInfo;
    private Transformer mTransformer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExportInfo {
        long mAudioDuration;
        List<Uri> mAudioUris = new ArrayList();
        File mTargetFile;
        File mTempVideoFile;
        int mVideoBitrate;
        long mVideoDuration;
        Uri mVideoUri;

        public String toString() {
            StringBuilder sb2 = new StringBuilder("ExportInfo");
            sb2.append(Logger.v("targetFile=" + this.mTargetFile, "tempVideoFile=" + this.mTempVideoFile, "videoUri=" + this.mVideoUri, "audioUris=" + this.mAudioUris.size(), "videoDuration=" + this.mVideoDuration, "audioDuration=" + this.mAudioDuration, "bitrate=" + this.mVideoBitrate));
            return sb2.toString();
        }
    }

    public BgmMixer(File file, Runnable runnable) {
        ExportInfo exportInfo = new ExportInfo();
        this.mExportInfo = exportInfo;
        exportInfo.mTargetFile = file;
        this.mCompleteCallback = runnable;
    }

    /* access modifiers changed from: private */
    /* renamed from: export */
    public void lambda$mix$0(Context context) {
        if (this.mExportInfo.mAudioUris.isEmpty()) {
            finish("audio uris are empty");
            AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_NONE.toString(), AnalyticsEventId.EVENT_DEV_RECAP_VIDEO_CREATION_BGM_FAILED.toString());
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        Log.d("BgmMixer", "Export start with " + this.mExportInfo);
        EditedMediaItemSequence build = new EditedMediaItemSequence.Builder((List<EditedMediaItem>) U.B(new EditedMediaItem.Builder(MediaItem.fromUri(this.mExportInfo.mVideoUri)).setRemoveAudio(true).build())).build();
        ArrayList arrayList = new ArrayList();
        this.mExportInfo.mAudioUris.forEach(new c(arrayList));
        EditedMediaItemSequence.Builder builder = new EditedMediaItemSequence.Builder((List<EditedMediaItem>) arrayList);
        ExportInfo exportInfo = this.mExportInfo;
        Composition build2 = new Composition.Builder((List<EditedMediaItemSequence>) U.D(build, builder.addGap(exportInfo.mVideoDuration - exportInfo.mAudioDuration).build())).build();
        Transformer build3 = new Transformer.Builder(context).setAudioMimeType(Encode.CodecsMime.AUDIO_CODEC_AAC).setEncoderFactory(new DefaultEncoderFactory.Builder(context).setRequestedAudioEncoderSettings(new AudioEncoderSettings.Builder().setBitrate(128000).build()).setRequestedVideoEncoderSettings(new VideoEncoderSettings.Builder().setBitrate(this.mExportInfo.mVideoBitrate).build()).build()).addListener(new Transformer.Listener() {
            public void onCompleted(Composition composition, ExportResult exportResult) {
                BgmMixer bgmMixer = BgmMixer.this;
                bgmMixer.finish("EXPORT completed " + Logger.vt(currentTimeMillis));
            }

            public void onError(Composition composition, ExportResult exportResult, ExportException exportException) {
                BgmMixer bgmMixer = BgmMixer.this;
                bgmMixer.finish("EXPORT FAILED : " + exportException.getErrorCodeName() + ArcCommonLog.TAG_COMMA + exportException.getCause() + ArcCommonLog.TAG_COMMA + exportException.getMessage() + Logger.vt(currentTimeMillis));
            }
        }).build();
        this.mTransformer = build3;
        build3.start(build2, this.mExportInfo.mTargetFile.getAbsolutePath());
        Log.d("BgmMixer", "Transformer started...");
    }

    /* access modifiers changed from: private */
    public void finish(String str) {
        Log.d("BgmMixer", "Finished with reason :" + str);
        this.mCompleteCallback.run();
        FileUtils.delete(this.mExportInfo.mTempVideoFile);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$cancel$3() {
        Transformer transformer = this.mTransformer;
        if (transformer != null) {
            transformer.cancel();
        }
        finish("cancel()");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$mix$1() {
        File externalFilesDir = AppResources.getAppContext().getExternalFilesDir(".share");
        FileUtils.makeDirectoryIfAbsent(externalFilesDir);
        if (externalFilesDir == null) {
            finish("share path is null");
            return;
        }
        ExportInfo exportInfo = this.mExportInfo;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(externalFilesDir.getPath());
        exportInfo.mTempVideoFile = new SecureFile(C0212a.p(sb2, File.separator, "recapMovie_tmp.mp4"));
        ExportInfo exportInfo2 = this.mExportInfo;
        if (!FileUtils.copy(exportInfo2.mTargetFile, exportInfo2.mTempVideoFile)) {
            finish("making temp file failed : " + this.mExportInfo.mTempVideoFile.getAbsolutePath());
            return;
        }
        Context appContext = AppResources.getAppContext();
        ExportInfo exportInfo3 = this.mExportInfo;
        exportInfo3.mVideoUri = FileProvider.getUriForFile(appContext, appContext.getPackageName() + ".fileprovider", exportInfo3.mTempVideoFile);
        updateMeta(appContext, this.mExportInfo);
        ExportInfo exportInfo4 = this.mExportInfo;
        long j2 = exportInfo4.mVideoDuration;
        if (j2 == 0) {
            finish("video duration is zero");
        } else if (exportInfo4.mVideoBitrate == 0) {
            finish("video bitrate is zero");
        } else {
            BgmProvider.BgmInfo bgmInfo = new BgmProvider().getBgmInfo(j2);
            if (bgmInfo != null) {
                ExportInfo exportInfo5 = this.mExportInfo;
                exportInfo5.mAudioUris = bgmInfo.uris;
                exportInfo5.mAudioDuration = bgmInfo.duration;
            }
            ThreadUtil.runOnUiThread(new b(this, appContext));
        }
    }

    private void updateMeta(Context context, ExportInfo exportInfo) {
        MediaMetadataRetriever mediaMetadataRetriever;
        long j2;
        int i2;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(context, exportInfo.mVideoUri);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            if (extractMetadata == null) {
                j2 = 0;
            } else {
                j2 = Long.parseLong(extractMetadata);
            }
            exportInfo.mVideoDuration = j2;
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(20);
            if (extractMetadata2 == null) {
                i2 = 0;
            } else {
                i2 = Integer.parseInt(extractMetadata2);
            }
            exportInfo.mVideoBitrate = i2;
            mediaMetadataRetriever.close();
            return;
        } catch (Error | Exception e) {
            Log.e((CharSequence) "BgmMixer", "build failed {}", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void cancel() {
        ThreadUtil.postOnUiThread(new a(this, 1));
    }

    public void mix() {
        ThreadUtil.runOnBgThread(new a(this, 0));
    }
}
