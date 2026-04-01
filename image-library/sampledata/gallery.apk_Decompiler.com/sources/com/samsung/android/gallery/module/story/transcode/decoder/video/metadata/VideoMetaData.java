package com.samsung.android.gallery.module.story.transcode.decoder.video.metadata;

import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.transcode.util.TranscodingHelper;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.FileInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoMetaData {
    private String TAG = "VideoMetaData";
    private final long mEncodeDurMs;
    private long mEndTimeUs;
    private String mFilePath;
    private Filter mFilter;
    private int mInputOrientation;
    private final long mIntervalUs;
    private long mLength;
    private boolean mLooping;
    private MediaFormat mMediaFormat;
    private long mOffset;
    private long mPlayEndTimeUs;
    private int mSourceFrameRate;
    private long mSourceIntervalUs;
    private final int mStartMs;
    private long mStartTimeUs;
    private Uri mUri;

    public VideoMetaData(int i2, int i7, int i8, long j2, boolean z) {
        this.TAG += Log.TAG_SEPARATOR + i2;
        this.mStartMs = i8;
        this.mEncodeDurMs = j2;
        this.mIntervalUs = ((long) (1000 / i7)) * 1000;
        this.mLooping = z;
    }

    private boolean isUriItem() {
        if (this.mUri != null) {
            return true;
        }
        return false;
    }

    private int loadFrameRateWithFile() {
        if (isUriItem()) {
            return TranscodingHelper.getVideoFrameRate(this.mUri, 30);
        }
        return TranscodingHelper.getVideoFrameRate(this.mFilePath, 30);
    }

    private boolean needHdrToSdr(MediaMetadataRetriever mediaMetadataRetriever) {
        int i2;
        int i7;
        if (!TranscodingHelper.supportHdrToSdr()) {
            com.samsung.android.gallery.support.utils.Log.d(this.TAG, "supportHdrToSdr", Boolean.FALSE);
            return false;
        }
        String extractMetadata = mediaMetadataRetriever.extractMetadata(1027);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_NETWORK_TIMEOUT);
        String extractMetadata3 = mediaMetadataRetriever.extractMetadata(1022);
        boolean equals = "yes".equals(extractMetadata);
        if (!TextUtils.isEmpty(extractMetadata2)) {
            i2 = Integer.parseInt(extractMetadata2);
        } else {
            i2 = -1;
        }
        if (!TextUtils.isEmpty(extractMetadata3)) {
            i7 = Integer.parseInt(extractMetadata3);
        } else {
            i7 = 0;
        }
        if (!equals || TranscodingHelper.getHdrType(equals, i2, i7) != 2) {
            return false;
        }
        return true;
    }

    private boolean requireHdrToSdrParamV() {
        if (!SdkConfig.atLeast(SdkConfig.SEM.V)) {
            return false;
        }
        Filter filter = this.mFilter;
        if (filter == null || Filter.NONE.equals(filter)) {
            return true;
        }
        return false;
    }

    private void setHdrToSdr(MediaMetadataRetriever mediaMetadataRetriever, MediaFormat mediaFormat) {
        if (needHdrToSdr(mediaMetadataRetriever)) {
            if (requireHdrToSdrParamV()) {
                setHdrToSdrParamV(mediaFormat);
            } else if (SdkConfig.lessThan(SdkConfig.SEM.V)) {
                setHdrToSdrParamU(mediaFormat);
            } else {
                String str = this.TAG;
                com.samsung.android.gallery.support.utils.Log.d(str, "ignore HDR to SDR v=" + SdkConfig.SEM_VER, this.mFilter);
            }
        }
    }

    private void setHdrToSdrParamU(MediaFormat mediaFormat) {
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "setHdrToSdrParamU");
        mediaFormat.setInteger("force-hdr2sdr-enable", 1);
        mediaFormat.setInteger("HDR-OFF", 1);
        String lowerCase = SeApiCompat.getSystemPropertiesString("ro.hardware.chipname", "").toLowerCase();
        String lowerCase2 = SeApiCompat.getSystemPropertiesString("ro.product.board", "").toLowerCase();
        String lowerCase3 = SeApiCompat.getSystemPropertiesString("ro.soc.model", "").toLowerCase();
        if (lowerCase.isEmpty()) {
            lowerCase = lowerCase2;
        }
        if (!lowerCase.isEmpty()) {
            lowerCase3 = lowerCase;
        }
        if (lowerCase3.startsWith("exynos") || lowerCase3.startsWith("s5e")) {
            mediaFormat.setInteger("vendor.sec-dec-output.image-convert.value", 1);
            mediaFormat.setInteger("vendor.sec-ext-imageformat-filter-enableInplace.value", 1);
            return;
        }
        mediaFormat.setInteger("vendor.qti-ext-dec-forceNonUBWC.value", 1);
        mediaFormat.setInteger("vendor.qti-ext-imageformat-filter-enabled.value", 1);
        mediaFormat.setInteger("vendor.qti-ext-imageformat-filter-enableInplace.value", 1);
    }

    private void setHdrToSdrParamV(MediaFormat mediaFormat) {
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "setHdrToSdrParamV");
        mediaFormat.setInteger("force-hdr2sdr-enable", 1);
        mediaFormat.setInteger("HDR-OFF", 1);
        mediaFormat.setInteger("vendor.renderengine-hdr-tonemap.value", 1);
    }

    private void setInputOrientation(MediaMetadataRetriever mediaMetadataRetriever) {
        this.mInputOrientation = TranscodingHelper.getInputVideoOrientation(mediaMetadataRetriever.extractMetadata(24), 0);
    }

    private void setSourceFrameRate(MediaFormat mediaFormat) {
        try {
            int integer = mediaFormat.getInteger("frame-rate");
            this.mSourceFrameRate = integer;
            if (integer == 29) {
                this.mSourceFrameRate = 30;
            }
            int i2 = this.mSourceFrameRate;
            if (i2 == 0 || i2 > 250) {
                this.mSourceFrameRate = loadFrameRateWithFile();
            }
        } catch (NullPointerException unused) {
            this.mSourceFrameRate = loadFrameRateWithFile();
        }
        int i7 = this.mSourceFrameRate;
        this.mSourceIntervalUs = ((long) (1000 / i7)) * 1000;
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "frame rate of video", Integer.valueOf(i7), Long.valueOf(this.mSourceIntervalUs));
    }

    private void setTimeUs(MediaFormat mediaFormat) {
        this.mEndTimeUs = mediaFormat.getLong("durationUs");
        long j2 = ((long) this.mStartMs) * 1000;
        this.mStartTimeUs = j2;
        this.mPlayEndTimeUs = ((this.mEncodeDurMs + 500) * 1000) + j2;
    }

    public long getDuration() {
        return this.mEndTimeUs - this.mStartTimeUs;
    }

    public long getEncodeIntervalUs() {
        return this.mIntervalUs;
    }

    public long getEndTimeUs() {
        return this.mEndTimeUs;
    }

    public int getFrameRate() {
        return this.mSourceFrameRate;
    }

    public int getInputOrientation() {
        return this.mInputOrientation;
    }

    public MediaFormat getMediaFormat() {
        return this.mMediaFormat;
    }

    public long getPlayEndTimeUs() {
        return this.mPlayEndTimeUs;
    }

    public long getPlayStartTimeUs() {
        return this.mStartTimeUs;
    }

    public long getSourceIntervalUs() {
        return this.mSourceIntervalUs;
    }

    public long getStartTimeUs() {
        return this.mStartTimeUs;
    }

    public void init(MediaFormat mediaFormat) {
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        this.mMediaFormat = mediaFormat;
        mediaFormat.setInteger("priority", 1);
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (isUriItem()) {
                mediaMetadataRetriever.setDataSource(AppResources.getAppContext(), this.mUri);
            } else if (this.mOffset <= 0 || this.mLength <= 0) {
                mediaMetadataRetriever.setDataSource(this.mFilePath);
            } else {
                fileInputStream = new FileInputStream(this.mFilePath);
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD(), this.mOffset, this.mLength);
                fileInputStream.close();
            }
            setHdrToSdr(mediaMetadataRetriever, mediaFormat);
            setInputOrientation(mediaMetadataRetriever);
            setSourceFrameRate(mediaFormat);
            setTimeUs(mediaFormat);
            com.samsung.android.gallery.support.utils.Log.d(this.TAG, "VideoMetaData", Long.valueOf(this.mStartTimeUs), Long.valueOf(this.mEndTimeUs));
            mediaMetadataRetriever.close();
            return;
            throw th;
            throw th2;
        } catch (IllegalArgumentException e) {
            com.samsung.android.gallery.support.utils.Log.e((CharSequence) this.TAG, "Error during prepare src video codec", (Throwable) e);
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
    }

    public boolean isLooping() {
        return this.mLooping;
    }

    public void setData(String str) {
        this.mFilePath = str;
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
    }

    public void setSubData(long j2, long j3) {
        this.mOffset = j2;
        this.mLength = j3;
    }

    public void setData(Uri uri) {
        this.mUri = uri;
    }
}
