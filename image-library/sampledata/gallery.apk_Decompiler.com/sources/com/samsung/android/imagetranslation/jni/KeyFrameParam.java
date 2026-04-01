package com.samsung.android.imagetranslation.jni;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Size;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyFrameParam {
    private static final String TAG = "KeyFrame";
    private int blockCount;
    private Context context;
    private String destLanguage;
    private int deviceHeight;
    private int deviceRotation = 0;
    private int deviceWidth;
    private float dpScaleFactor = 5.0f;
    private String imageFormat;
    private Bitmap inPaintedImage;
    private Bitmap inputImage;
    private LttOcrResult lttOcrResult;
    private int mserNeeded;
    private byte[] nv21InPaintedImage;
    private byte[] nv21InputImage;
    private Size originalImageSize;
    private int requestId;
    private float resizeRatio = 1.0f;
    private Size resizedImageSize;
    private CopyOnWriteArrayList<SceneText> sceneTexts;
    private List<String> trlResult;

    private int countBlocks() {
        HashSet hashSet = new HashSet();
        Iterator<SceneText> it = this.sceneTexts.iterator();
        while (it.hasNext()) {
            Iterator<SceneText> it2 = it.next().getComponents().iterator();
            while (it2.hasNext()) {
                hashSet.add(Integer.valueOf(it2.next().getGoogleBlockIdx()));
            }
        }
        this.blockCount = hashSet.size();
        return hashSet.size();
    }

    public int getBlockCount() {
        int i2 = this.blockCount;
        if (i2 <= 0) {
            return countBlocks();
        }
        return i2;
    }

    public Context getContext() {
        return this.context;
    }

    public String getDestLanguage() {
        return this.destLanguage;
    }

    public int getDeviceHeight() {
        return this.deviceHeight;
    }

    public int getDeviceRotation() {
        return this.deviceRotation;
    }

    public int getDeviceWidth() {
        return this.deviceWidth;
    }

    public float getDpScaleFactor() {
        return this.dpScaleFactor;
    }

    public String getImageFormat() {
        return this.imageFormat;
    }

    public Bitmap getInPaintedImage() {
        return this.inPaintedImage;
    }

    public Bitmap getInputImage() {
        return this.inputImage;
    }

    public int getInputImageHeight() {
        return this.inputImage.getHeight();
    }

    public int getInputImageWidth() {
        return this.inputImage.getWidth();
    }

    public LttOcrResult getLttOcrResult() {
        return this.lttOcrResult;
    }

    public int getMserNeeded() {
        return this.mserNeeded;
    }

    public byte[] getNv21InPaintedImage() {
        return this.nv21InPaintedImage;
    }

    public byte[] getNv21InputImage() {
        return this.nv21InputImage;
    }

    public int getOriginalHeight() {
        return this.originalImageSize.getHeight();
    }

    public Size getOriginalImageSize() {
        return this.originalImageSize;
    }

    public int getOriginalWidth() {
        return this.originalImageSize.getWidth();
    }

    public int getRequestId() {
        return this.requestId;
    }

    public float getResizeRatio() {
        return this.resizeRatio;
    }

    public Size getResizedImageSize() {
        return this.resizedImageSize;
    }

    public CopyOnWriteArrayList<SceneText> getSceneTexts() {
        return this.sceneTexts;
    }

    public List<String> getTrlResult() {
        return this.trlResult;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setDestLanguage(String str) {
        this.destLanguage = str;
    }

    public void setDeviceHeight(int i2) {
        this.deviceHeight = i2;
    }

    public void setDeviceRotation(int i2) {
        this.deviceRotation = i2;
    }

    public void setDeviceWidth(int i2) {
        this.deviceWidth = i2;
    }

    public void setDpScaleFactor(float f) {
        this.dpScaleFactor = f;
    }

    public void setImageFormat(String str) {
        this.imageFormat = str;
    }

    public void setInPaintedImage(Bitmap bitmap) {
        this.inPaintedImage = bitmap;
    }

    public void setInputImage(Bitmap bitmap) {
        this.inputImage = bitmap;
    }

    public void setLttOcrResult(LttOcrResult lttOcrResult2) {
        this.lttOcrResult = lttOcrResult2;
    }

    public void setMserNeeded(int i2) {
        this.mserNeeded = i2;
    }

    public void setNv21InPaintedImage(byte[] bArr) {
        this.nv21InPaintedImage = bArr;
    }

    public void setNv21InputImage(byte[] bArr) {
        this.nv21InputImage = bArr;
    }

    public void setOriginalImageSize(Size size) {
        this.originalImageSize = size;
    }

    public void setRequestId(int i2) {
        this.requestId = i2;
    }

    public void setResizeRatio(float f) {
        this.resizeRatio = f;
    }

    public void setResizedImageSize(Size size) {
        this.resizedImageSize = size;
    }

    public void setSceneTexts(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        this.sceneTexts = copyOnWriteArrayList;
    }

    public void setTrlResult(List<String> list) {
        this.trlResult = list;
    }
}
