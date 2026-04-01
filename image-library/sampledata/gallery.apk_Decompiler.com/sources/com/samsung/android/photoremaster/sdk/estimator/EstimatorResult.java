package com.samsung.android.photoremaster.sdk.estimator;

import android.util.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.photoremaster.sdk.common.Constants;
import java.security.InvalidParameterException;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EstimatorResult {
    private static final String TAG = "RemasterSdk-EstimatorResult";
    private final String errorReason;
    private final String estimatedDataJson;
    private final boolean isEstimated;
    private final double scale;
    private final double score;

    public EstimatorResult(boolean z, double d, double d2, String str, String str2) {
        if (d >= 1.0d) {
            this.isEstimated = z;
            this.scale = d;
            this.score = d2;
            this.errorReason = str;
            this.estimatedDataJson = str2;
            return;
        }
        throw new InvalidParameterException("Downscaling is not allowed: scale [" + d + "]");
    }

    public String getErrorReason() {
        return this.errorReason;
    }

    public String getEstimatedDataJson() {
        return this.estimatedDataJson;
    }

    public double getScale() {
        return this.scale;
    }

    public double getScore() {
        return this.score;
    }

    public boolean isEstimated() {
        return this.isEstimated;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isEstimated", this.isEstimated);
            jSONObject.put("scale", this.scale);
            jSONObject.put("score", this.score);
            jSONObject.put("errorReason", this.errorReason);
            jSONObject.put("estimatedDataJson", new JSONObject(this.estimatedDataJson));
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "toJson: " + e);
            return jSONObject;
        }
    }

    public String toString() {
        return String.format(Locale.US, "EstimatorResult { isEstimated = %b, scale = %f, score = %f, errorReason = '%s', estimatedDataJson = '%s'}", new Object[]{Boolean.valueOf(this.isEstimated), Double.valueOf(this.scale), Double.valueOf(this.scale), this.errorReason, this.estimatedDataJson});
    }

    public EstimatorResult(boolean z, double d) {
        this(z, d, MapUtil.INVALID_LOCATION, "", Constants.EMPTY_JSON_STRING);
    }

    public EstimatorResult(boolean z, double d, double d2) {
        this(z, d, d2, "", Constants.EMPTY_JSON_STRING);
    }

    public EstimatorResult(boolean z, double d, double d2, String str) {
        this(z, d, d2, "", str);
    }

    public EstimatorResult(boolean z, String str) {
        this(z, 1.0d, MapUtil.INVALID_LOCATION, str, Constants.EMPTY_JSON_STRING);
    }
}
