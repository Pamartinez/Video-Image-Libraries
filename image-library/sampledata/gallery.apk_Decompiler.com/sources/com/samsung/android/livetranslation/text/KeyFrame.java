package com.samsung.android.livetranslation.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyFrame implements Cloneable {
    private static final float DP_TO_PX_FACTOR = 5.0f;
    private int mBaseImageHash;
    private byte[] mBuffer;
    private float mDpScaleFactor;
    private FrameFormat mFrameFormat;
    private long mFrameId;
    private int mGoogleBlockCount;
    private int mHeight;
    private boolean mIsMobile;
    private float mResizeRatio;
    private int mRotation;
    private int mSTRCP;
    private CopyOnWriteArrayList<SceneText> mSceneTexts;
    private List<String> mSortedSrcLangList;
    private String mSrcLang;
    private int mTRLCP;
    private List<Double> mTRLReqLangProportions;
    private List<String> mTRLReqLangs;
    private String mTRLReqSrcLang;
    private List<String> mTRLReqStr;
    private List<String> mTRLResultStr;
    private TRL_UNIT mTRLUnit;
    private String mTarLang;
    private int mWidth;
    private int originalImageHeight;
    private int originalImageWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FrameFormat {
        NV21,
        JPEG
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TRL_UNIT {
        PARAGRAPH,
        LINE,
        GOOGLE_BLOCK
    }

    public KeyFrame() {
        this.mFrameId = 0;
        this.mBuffer = null;
        this.mFrameFormat = FrameFormat.NV21;
        this.mIsMobile = false;
        this.mSTRCP = -1;
        this.mTRLCP = -1;
        this.mTRLUnit = TRL_UNIT.LINE;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mRotation = 0;
        this.mResizeRatio = 1.0f;
        this.mSortedSrcLangList = null;
        this.mSceneTexts = null;
        this.mTRLReqStr = null;
        this.mTRLReqLangs = null;
        this.mTRLReqLangProportions = null;
        this.mTRLResultStr = null;
        this.mGoogleBlockCount = 0;
        this.mBaseImageHash = 0;
        this.mDpScaleFactor = DP_TO_PX_FACTOR;
        this.mSceneTexts = new CopyOnWriteArrayList<>();
    }

    private int countGoogleBlocks() {
        HashSet hashSet = new HashSet();
        Iterator<SceneText> it = this.mSceneTexts.iterator();
        while (it.hasNext()) {
            Iterator<SceneText> it2 = it.next().getComponents().iterator();
            while (it2.hasNext()) {
                hashSet.add(Integer.valueOf(it2.next().getGoogleBlockIdx()));
            }
        }
        return hashSet.size();
    }

    private void setSortedSrcLangList(List<String> list) {
        this.mSortedSrcLangList = new ArrayList(list);
    }

    public int getBaseImageHash() {
        return this.mBaseImageHash;
    }

    public byte[] getBuffer() {
        return this.mBuffer;
    }

    public float getDpScaleFactor() {
        return this.mDpScaleFactor;
    }

    public FrameFormat getFrameFormat() {
        return this.mFrameFormat;
    }

    public long getFrameId() {
        return this.mFrameId;
    }

    public int getGoogleBlockCount() {
        int i2 = this.mGoogleBlockCount;
        if (i2 <= 0) {
            return countGoogleBlocks();
        }
        return i2;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean getIsMobile() {
        return this.mIsMobile;
    }

    public int getOriginalImageHeight() {
        return this.originalImageHeight;
    }

    public int getOriginalImageWidth() {
        return this.originalImageWidth;
    }

    public float getResizeRatio() {
        return this.mResizeRatio;
    }

    public int getRotation() {
        return this.mRotation;
    }

    public int getSTRCP() {
        return this.mSTRCP;
    }

    public CopyOnWriteArrayList<SceneText> getSceneTexts() {
        return this.mSceneTexts;
    }

    public String getSrcLang() {
        return this.mSrcLang;
    }

    public int getTRLCP() {
        return this.mTRLCP;
    }

    public List<Double> getTRLReqLangProportions() {
        return this.mTRLReqLangProportions;
    }

    public List<String> getTRLReqLangs() {
        return this.mTRLReqLangs;
    }

    public String getTRLReqSrcLang() {
        return this.mTRLReqSrcLang;
    }

    public List<String> getTRLReqString() {
        return this.mTRLReqStr;
    }

    public List<String> getTRLResultString() {
        return this.mTRLResultStr;
    }

    public TRL_UNIT getTRLUnit() {
        return this.mTRLUnit;
    }

    public String getTarLang() {
        return this.mTarLang;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isValidSTRCP() {
        if (this.mSTRCP >= 0) {
            return true;
        }
        return false;
    }

    public boolean isValidTRLCP() {
        if (this.mTRLCP >= 0) {
            return true;
        }
        return false;
    }

    public void setBaseImageHash(int i2) {
        this.mBaseImageHash = i2;
    }

    public void setBuffer(byte[] bArr) {
        this.mBuffer = bArr;
    }

    public void setBufferParams(byte[] bArr, int i2, int i7, float f) {
        setBuffer(bArr);
        setWidth(i2);
        setHeight(i7);
        setResizeRatio(f);
    }

    public void setBufferWithCopy(byte[] bArr, int i2, int i7) {
        this.mBuffer = Arrays.copyOfRange(bArr, i2, i7);
    }

    public void setDpScaleFactor(float f) {
        this.mDpScaleFactor = f;
    }

    public void setFrameFormat(FrameFormat frameFormat) {
        this.mFrameFormat = frameFormat;
    }

    public void setFrameId(long j2) {
        this.mFrameId = j2;
    }

    public void setGoogleBlockCount(int i2) {
        this.mGoogleBlockCount = i2;
    }

    public void setHeight(int i2) {
        this.mHeight = i2;
    }

    public void setIsMobile(boolean z) {
        this.mIsMobile = z;
    }

    public void setOriginalImageHeight(int i2) {
        this.originalImageHeight = i2;
    }

    public void setOriginalImageWidth(int i2) {
        this.originalImageWidth = i2;
    }

    public void setResizeRatio(float f) {
        this.mResizeRatio = f;
    }

    public void setRotation(int i2) {
        this.mRotation = i2;
    }

    public void setSTRCP(int i2) {
        this.mSTRCP = i2;
    }

    public void setSceneTexts(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        this.mSceneTexts = copyOnWriteArrayList;
    }

    public void setSrcLang(String str) {
        this.mSrcLang = str;
        this.mTRLReqSrcLang = str;
    }

    public void setTRLCP(int i2) {
        this.mTRLCP = i2;
    }

    public void setTRLReqLangProportions(List<Double> list) {
        this.mTRLReqLangProportions = list;
    }

    public void setTRLReqLangs(List<String> list) {
        this.mTRLReqLangs = list;
    }

    public void setTRLReqString(List<String> list) {
        this.mTRLReqStr = list;
    }

    public void setTRLResultString(List<String> list) {
        this.mTRLResultStr = list;
    }

    public void setTRLUnit(TRL_UNIT trl_unit) {
        this.mTRLUnit = trl_unit;
    }

    public void setTarLang(String str) {
        this.mTarLang = str;
    }

    public void setWidth(int i2) {
        this.mWidth = i2;
    }

    public KeyFrame clone() {
        KeyFrame keyFrame = new KeyFrame();
        keyFrame.setFrameId(this.mFrameId);
        byte[] bArr = this.mBuffer;
        keyFrame.setBufferWithCopy(bArr, 0, bArr.length);
        keyFrame.setFrameFormat(this.mFrameFormat);
        keyFrame.setIsMobile(this.mIsMobile);
        keyFrame.setWidth(this.mWidth);
        keyFrame.setHeight(this.mHeight);
        keyFrame.setRotation(this.mRotation);
        keyFrame.setResizeRatio(this.mResizeRatio);
        keyFrame.setTRLReqString(this.mTRLReqStr);
        keyFrame.setSTRCP(this.mSTRCP);
        keyFrame.setTRLCP(this.mTRLCP);
        keyFrame.setSrcLang(this.mSrcLang);
        keyFrame.setTarLang(this.mTarLang);
        keyFrame.setTRLUnit(this.mTRLUnit);
        keyFrame.setGoogleBlockCount(this.mGoogleBlockCount);
        List<String> list = this.mSortedSrcLangList;
        if (list != null) {
            keyFrame.setSortedSrcLangList(list);
        }
        if (this.mSceneTexts == null) {
            keyFrame.setSceneTexts((CopyOnWriteArrayList<SceneText>) null);
            return keyFrame;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        Iterator<SceneText> it = this.mSceneTexts.iterator();
        while (it.hasNext()) {
            copyOnWriteArrayList.add(it.next().clone());
        }
        keyFrame.setSceneTexts(copyOnWriteArrayList);
        return keyFrame;
    }
}
