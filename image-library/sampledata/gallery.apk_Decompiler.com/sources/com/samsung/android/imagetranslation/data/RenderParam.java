package com.samsung.android.imagetranslation.data;

import android.graphics.Bitmap;
import com.samsung.android.imagetranslation.LttEngineListener;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenderParam {
    private String destLanguage;
    private String imageFormat;
    private Bitmap inPaintedBitmap;
    private Bitmap inputBitmap;
    private LttEngineListener lttEngineListener;
    private LttOcrResult lttOcrResult;
    private List<String> trlResult;

    public String getDestLanguage() {
        return this.destLanguage;
    }

    public String getImageFormat() {
        return this.imageFormat;
    }

    public Bitmap getInPaintedBitmap() {
        return this.inPaintedBitmap;
    }

    public Bitmap getInputBitmap() {
        return this.inputBitmap;
    }

    public LttEngineListener getLttEngineListener() {
        return this.lttEngineListener;
    }

    public LttOcrResult getLttOcrResult() {
        return this.lttOcrResult;
    }

    public List<String> getTrlResult() {
        return this.trlResult;
    }

    public void setDestLanguage(String str) {
        this.destLanguage = str;
    }

    public void setImageFormat(String str) {
        this.imageFormat = str;
    }

    public void setInPaintedBitmap(Bitmap bitmap) {
        this.inPaintedBitmap = bitmap;
    }

    public void setInputBitmap(Bitmap bitmap) {
        this.inputBitmap = bitmap;
    }

    public void setLttEngineListener(LttEngineListener lttEngineListener2) {
        this.lttEngineListener = lttEngineListener2;
    }

    public void setLttOcrResult(LttOcrResult lttOcrResult2) {
        this.lttOcrResult = lttOcrResult2;
    }

    public void setTrlResult(List<String> list) {
        this.trlResult = list;
    }
}
