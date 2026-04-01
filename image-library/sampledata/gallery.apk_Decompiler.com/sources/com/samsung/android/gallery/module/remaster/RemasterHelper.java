package com.samsung.android.gallery.module.remaster;

import android.graphics.RectF;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.abstraction.RemasterType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterHelper {
    public static Result INTERRUPTED_RESULT = new InterruptedResult();
    private final Object LOCK = new Object();
    private boolean mIsInterrupted;
    private Consumer<Double> mProgressConsumer;
    private VslMesDetectorCompat mVslMesDetectorCompat;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FakeResult extends Result {
        public FakeResult(MediaItem mediaItem) {
            this.success = true;
            this.mediaItem = mediaItem;
            this.path = MediaItemSuggest.getRemasterPath(mediaItem);
            this.type = MediaItemSuggest.getRevitalizedType(mediaItem);
            this.elapsed = 0;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InterruptedResult extends Result {
        public InterruptedResult() {
            this.interrupt = true;
            this.success = false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Result {
        public long elapsed;
        public ErrorReason errorReason = ErrorReason.UNKNOWN;
        public ArrayList<RectF> focusRectList;
        public boolean interrupt;
        public FileItemInterface mediaItem;
        public String path;
        public boolean success;
        public long type = -1;
    }

    public RemasterHelper(VslMesDetectorCompat vslMesDetectorCompat) {
        this.mVslMesDetectorCompat = vslMesDetectorCompat;
        init();
    }

    private void deInit() {
        try {
            Log.d("RemasterHelper", "deInit");
            this.mVslMesDetectorCompat.deInit(Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF));
        } catch (Exception e) {
            Log.e("RemasterHelper", "failed to deInit -> " + e.getMessage());
        } catch (Throwable th) {
            this.mProgressConsumer = null;
            this.mVslMesDetectorCompat = null;
            throw th;
        }
        this.mProgressConsumer = null;
        this.mVslMesDetectorCompat = null;
    }

    private int getAltRemasterType(MediaItem mediaItem) {
        if (this.mVslMesDetectorCompat.supportUpscaleType(mediaItem.getWidthInDB(), mediaItem.getHeightInDB())) {
            return 1;
        }
        return 7;
    }

    private ErrorReason getErrorReason(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("ErrorReason");
                switch (optString.hashCode()) {
                    case -2011810908:
                        if (optString.equals("ERROR_TOO_MANY_FRAMES")) {
                            return ErrorReason.MANY_FRAMES;
                        }
                        break;
                    case -1733153365:
                        if (optString.equals("ERROR_TOO_MANY_PIXELS")) {
                            return ErrorReason.MANY_PIXELS;
                        }
                        break;
                    case -1203932051:
                        if (optString.equals("ERROR_TOO_LOW_VISUAL_QUALITY")) {
                            return ErrorReason.LOW_QUALITY;
                        }
                        break;
                    case -1130071673:
                        if (optString.equals("ERROR_DECODING_GIF")) {
                            return ErrorReason.ERROR_DECODING;
                        }
                        break;
                    case -376764658:
                        if (optString.equals("ERROR_NO_REMASTERING_NEEDED")) {
                            return ErrorReason.ERROR_NO_REMASTERING_NEED;
                        }
                        break;
                }
                return ErrorReason.UNKNOWN;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ErrorReason.UNKNOWN;
    }

    public static int getErrorReasonText(MediaItem mediaItem) {
        if (mediaItem != null) {
            int revitalizedType = (int) MediaItemSuggest.getRevitalizedType(mediaItem);
            if (revitalizedType == 19) {
                return R$string.canceling_colorization;
            }
            if (revitalizedType == 9) {
                return R$string.canceling_moire;
            }
            if (revitalizedType == 17) {
                return R$string.canceling_lens_flare;
            }
            if (revitalizedType == 15) {
                return R$string.canceling_lens_distortion;
            }
        }
        return R$string.canceling_remastering;
    }

    private ArrayList<RectF> getFocusRectList(boolean z, String str, String str2) {
        String str3;
        ArrayList<RectF> arrayList = new ArrayList<>();
        if (z) {
            str3 = "";
        } else {
            str3 = this.mVslMesDetectorCompat.getFocusRoi(str, str2);
        }
        if (TextUtils.isEmpty(str3)) {
            Log.d("RemasterHelper", "empty focus roi");
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(new JSONObject(str3).optString("FocusRoiSet"));
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                RectF rectF = new RectF((float) jSONObject.optDouble("left"), (float) jSONObject.optDouble("top"), (float) jSONObject.optDouble("right"), (float) jSONObject.optDouble("bottom"));
                if (rectF.width() > 0.0f && rectF.height() > 0.0f) {
                    arrayList.add(rectF);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.add(0, new RectF(0.0f, 0.0f, 1.0f, 1.0f));
            }
            return arrayList;
        } catch (JSONException e) {
            Log.e("RemasterHelper", "getFocusRectList failed. e=" + e.getMessage());
            return arrayList;
        }
    }

    public static int getProcessingMessage(MediaItem mediaItem) {
        boolean z;
        if (mediaItem == null || !mediaItem.isCloudOnly()) {
            z = false;
        } else {
            z = true;
        }
        if (mediaItem != null) {
            if (!mediaItem.isGif()) {
                long revitalizedType = MediaItemSuggest.getRevitalizedType(mediaItem);
                if (revitalizedType == 19) {
                    if (z) {
                        return R$string.download_remaster_color_ongoing;
                    }
                    return R$string.remaster_adding_color_ongoing;
                } else if (revitalizedType == 9) {
                    if (z) {
                        return R$string.download_remaster_moire_ongoing;
                    }
                    return R$string.remaster_removing_moire_ongoing;
                } else if (revitalizedType == 17) {
                    if (z) {
                        return R$string.download_remaster_lens_flare_ongoing;
                    }
                    return R$string.remaster_removing_lens_flare_ongoing;
                } else if (revitalizedType == 15) {
                    if (z) {
                        return R$string.download_remaster_lens_distortion_ongoing;
                    }
                    return R$string.remaster_fixing_lens_distortion_ongoing;
                }
            } else if (z) {
                return R$string.gif_download_remaster_notification_ongoing;
            } else {
                return R$string.gif_remaster_notification_ongoing;
            }
        }
        if (z) {
            return R$string.download_remaster_notification_ongoing;
        }
        return R$string.remaster_notification_ongoing;
    }

    private void init() {
        this.mVslMesDetectorCompat.init(AppResources.getAppContext());
    }

    private Result processImage(FileItemInterface fileItemInterface, long j2) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        Result result = new Result();
        Consumer<Double> consumer = this.mProgressConsumer;
        if (consumer != null) {
            this.mVslMesDetectorCompat.setProgressUpdateListener(consumer);
        }
        result.success = this.mVslMesDetectorCompat.processImage(ContentUri.getUri(fileItemInterface), fileItemInterface.getPath(), fileItemInterface.getDateTaken(), this.mVslMesDetectorCompat.getProcessMode(), j2);
        result.elapsed = System.currentTimeMillis() - currentTimeMillis;
        result.path = this.mVslMesDetectorCompat.getFullPathRevitalized();
        result.mediaItem = fileItemInterface;
        result.type = this.mVslMesDetectorCompat.getEnumEnhanceType();
        result.interrupt = isInterrupted();
        if (result.success) {
            result.focusRectList = getFocusRectList(fileItemInterface.isGif(), fileItemInterface.getPath(), result.path);
        } else {
            result.errorReason = getErrorReason(this.mVslMesDetectorCompat.getTagAnalyzedFull());
        }
        if (!FileUtils.exists(result.path) || FileUtils.length(result.path) <= 0) {
            z = false;
        } else {
            z = true;
        }
        Log.d("RemasterHelper", "processImage", Boolean.valueOf(result.success), result.errorReason, Long.valueOf(result.elapsed), Long.valueOf(j2), Long.valueOf(result.type), Boolean.valueOf(z), "*");
        if (z || j2 > 0) {
            return result;
        }
        return processImage(fileItemInterface, (long) getAltRemasterType((MediaItem) fileItemInterface));
    }

    public Result fakeRemaster(MediaItem mediaItem) {
        FakeResult fakeResult = new FakeResult(mediaItem);
        fakeResult.interrupt = isInterrupted();
        deInit();
        return fakeResult;
    }

    public ArrayList<RectF> getFocusRoiRectList(boolean z, String str, String str2) {
        ArrayList<RectF> focusRectList = getFocusRectList(z, str, str2);
        this.mVslMesDetectorCompat.deInit(Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF));
        return focusRectList;
    }

    public void interrupt() {
        synchronized (this.LOCK) {
            try {
                Log.d("RemasterHelper", "VslMesDetector interrupt ");
                long currentTimeMillis = System.currentTimeMillis();
                this.mIsInterrupted = true;
                VslMesDetectorCompat vslMesDetectorCompat = this.mVslMesDetectorCompat;
                if (vslMesDetectorCompat != null) {
                    vslMesDetectorCompat.stop();
                }
                Log.d("RemasterHelper", "VslMesDetector stop elapsed=: " + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isInterrupted() {
        return this.mIsInterrupted;
    }

    public Result remaster(FileItemInterface fileItemInterface, long j2) {
        try {
            if (!isInterrupted()) {
                Log.d("RemasterHelper", "remaster EnhanceType", Long.valueOf(j2), RemasterType.toDebugString(j2));
                Result processImage = processImage(fileItemInterface, j2);
                deInit();
                return processImage;
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            deInit();
            throw th;
        }
        deInit();
        return INTERRUPTED_RESULT;
    }

    public void setProgressListener(Consumer<Double> consumer) {
        this.mProgressConsumer = consumer;
    }

    public boolean supportUpscaleType(int i2, int i7) {
        VslMesDetectorCompat vslMesDetectorCompat = this.mVslMesDetectorCompat;
        if (vslMesDetectorCompat == null || !vslMesDetectorCompat.supportUpscaleType(i2, i7)) {
            return false;
        }
        return true;
    }
}
