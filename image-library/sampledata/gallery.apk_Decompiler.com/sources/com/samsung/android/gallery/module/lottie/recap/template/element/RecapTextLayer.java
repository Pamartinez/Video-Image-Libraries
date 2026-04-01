package com.samsung.android.gallery.module.lottie.recap.template.element;

import android.graphics.Typeface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTextLayer extends RecapDynamicElement<RecapTextLayer> {
    public boolean addSpaceForRepeat;
    public String defaultTargetValue;
    public int fontSp;
    public Typeface fontTypeFace;
    public boolean isEngOnly;
    public String layerNameForLongText;
    HashMap<Condition, Integer> mStringResourceIdMap = new HashMap<>();
    public int maxWidthDp;
    public int minLineForLongText;
    public int minWidthDpForLongText;
    public boolean noEllipsis;
    public RecapTemplateScene parent;
    public int repeatMaxWidth;
    public int stringResourceId;
    public String targetColor;
    public String targetFile;
    public String targetValue;
    public boolean useFirstNameOnly;
    public boolean useUpperCaseOnly;
    public RecapDataVars variables;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Condition {
        PEOPLE_ONLY,
        PET_ONLY
    }

    public RecapTextLayer(String str) {
        super(str);
    }

    public RecapTextLayer clear() {
        this.targetValue = " ";
        this.stringResourceId = 0;
        this.defaultTargetValue = null;
        this.useUpperCaseOnly = false;
        this.variables = null;
        return this;
    }

    public RecapTextLayer engOnly() {
        this.isEngOnly = true;
        return this;
    }

    public RecapTextLayer font(Typeface typeface) {
        this.fontTypeFace = typeface;
        return this;
    }

    public RecapTextLayer maxWidth(int i2) {
        this.maxWidthDp = i2;
        return this;
    }

    public boolean needToUseLongLangLayer() {
        int i2 = this.minWidthDpForLongText;
        if (i2 > 0) {
            List<String> wrapText = StringCompat.wrapText(this.targetValue, this.fontTypeFace, (float) this.fontSp, (float) i2);
            Log.i("RecapTextLayer", "needToUseLongLangLayer : " + AppResources.getLanguage() + ArcCommonLog.TAG_COMMA + wrapText.size() + ArcCommonLog.TAG_COMMA + this.targetValue);
            if (wrapText.size() > this.minLineForLongText) {
                return true;
            }
        }
        return false;
    }

    public RecapTextLayer noEllipsis() {
        this.noEllipsis = true;
        return this;
    }

    public RecapTextLayer repeatInWidth(double d) {
        this.repeatMaxWidth = (int) d;
        return this;
    }

    public RecapTextLayer repeatWithSpace() {
        this.addSpaceForRepeat = true;
        return this;
    }

    public RecapTextLayer setDefault(String str) {
        this.defaultTargetValue = str;
        return this;
    }

    public RecapTextLayer setLongTextLayer(String str, int i2, int i7) {
        this.layerNameForLongText = str;
        this.minWidthDpForLongText = i2;
        this.minLineForLongText = i7;
        return this;
    }

    public RecapTextLayer setStringId(int i2) {
        this.stringResourceId = i2;
        return this;
    }

    public RecapTextLayer setVar(RecapDataVars recapDataVars) {
        this.variables = recapDataVars;
        return this;
    }

    public RecapTextLayer sp(int i2) {
        this.fontSp = i2;
        return this;
    }

    public RecapTextLayer targetFile(String str) {
        this.targetFile = str;
        return this;
    }

    public RecapTextLayer upperCase() {
        this.useUpperCaseOnly = true;
        return this;
    }

    public RecapTextLayer setStringId(Condition condition, int i2) {
        this.mStringResourceIdMap.put(condition, Integer.valueOf(i2));
        return this;
    }

    public RecapTextLayer useFirstNameOnly() {
        return this;
    }
}
