package com.samsung.android.gallery.module.clip.textextraction;

import A4.C0387w;
import B8.g;
import M4.d;
import M5.a;
import N2.j;
import N3.c;
import O3.o;
import O8.b;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.barcode.BarcodeProvider;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import com.samsung.android.app.sdk.deepsky.textextraction.Recognizer;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrResult;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslationTokenInfo;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.TextExtractionApi;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.deepsky.DeepSkySuggest;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemLog;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionHelper {
    private static final boolean SUPPORT_CAMERA_DOCUMENT_SCAN = Features.isEnabled(Features.SUPPORT_VEX_DOCUMENT_SCAN);
    private List<Barcode> mBarCodeList;
    int mBitmapWidth;
    private CapsuleHelper mCapsuleHelper;
    boolean mCapsuleInitialized;
    private final TextExtractionCapsuleLogger mCapsuleLogger = new TextExtractionCapsuleLogger();
    /* access modifiers changed from: private */
    public final IClipInfo mClipInfo;
    private boolean mDetected;
    private int mDetectionType;
    boolean mDocumentInitialized;
    private Consumer<MediaItem> mDocumentScanClickConsumer;
    private boolean mDocumentScanned;
    boolean mEnabled;
    boolean mExtracted;
    private long mFileId;
    TextData mFilteredTextData;
    boolean mHasBarcodeData;
    boolean mHasData;
    private float mInitX;
    private float mInitY;
    boolean mInitialized;
    private final TextExtractionLangManageListener mLangManageListener = new TextExtractionLangManageListener();
    private MediaItem mMediaItem;
    private ObjectCaptureHelper mObjectCaptureHelper;
    final TextExtractionDrawHelper.ProgressBarCallback mProgressBarCallback = new TextExtractionDrawHelper.ProgressBarCallback() {
        private Dialog mDialog;

        public void finishProgressBar() {
            try {
                Optional.ofNullable(this.mDialog).ifPresent(new d(28));
                this.mDialog = null;
            } catch (Error | Exception e) {
                Log.e("TextExtractionHelper", "hide progress bar failed, e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + this);
            }
        }

        public void startProgressBar() {
            try {
                Dialog progressDialog = TextExtractionHelper.this.mClipInfo.getProgressDialog();
                this.mDialog = progressDialog;
                progressDialog.show();
            } catch (Error | Exception e) {
                Log.e("TextExtractionHelper", "show progress bar failed, e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + TextExtractionHelper.this);
            }
        }
    };
    private boolean mReady;
    private Recognizer mRecognizer;
    private State mState;
    TextData mTextData;
    private TextExtractionDrawHelper mTextExtractionHelper;
    private Consumer<Boolean> mTranslateClickConsumer;
    private TranslateLanguageHelper mTranslateLanguageHelper;
    boolean mTranslationOn;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        NONE,
        PARTIAL,
        FULL,
        FULL_INDIRECT,
        FULL_FILTER
    }

    public TextExtractionHelper(Context context, IClipInfo iClipInfo) {
        char c5;
        char c6;
        boolean z = false;
        this.mBitmapWidth = 0;
        this.mDetectionType = -1;
        this.mInitX = Float.MIN_VALUE;
        this.mInitY = Float.MIN_VALUE;
        this.mFileId = 0;
        this.mState = State.NONE;
        long currentTimeMillis = System.currentTimeMillis();
        MemLog memLog = new MemLog("VTC");
        this.mClipInfo = iClipInfo;
        this.mRecognizer = DeepSkyHelper.getRecognizer(context);
        TextExtractionDrawHelper textExtractionDrawHelper = DeepSkyHelper.getTextExtractionDrawHelper(context, iClipInfo.getContainerKey());
        this.mTextExtractionHelper = textExtractionDrawHelper;
        if (!(this.mRecognizer == null || textExtractionDrawHelper == null)) {
            z = true;
        }
        this.mEnabled = z;
        StringBuilder sb2 = new StringBuilder("construct {");
        if (this.mEnabled) {
            c5 = 'E';
        } else {
            c5 = 'e';
        }
        sb2.append(c5);
        if (this.mRecognizer != null) {
            c6 = 'R';
        } else {
            c6 = 'r';
        }
        sb2.append(c6);
        sb2.append("} +");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d("TextExtractionHelper", sb2.toString());
        memLog.check();
    }

    /* access modifiers changed from: private */
    /* renamed from: addActionCapsule */
    public void lambda$getSuggestion$10(JsonObject jsonObject) {
        try {
            if (this.mCapsuleHelper != null && jsonObject != null) {
                Uri uri = ContentUri.getUri(this.mMediaItem);
                Log.d("TextExtractionHelper", "add action capsule", Logger.getEncodedString((Object) uri), Logger.getEncodedString((Object) jsonObject));
                this.mCapsuleHelper.addActionCapsule(uri, jsonObject);
            }
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("add action capsule failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    private void addDocumentCapsule(Context context) {
        try {
            if (!this.mDocumentInitialized && this.mCapsuleHelper != null && supportDocumentScan(this.mMediaItem)) {
                this.mDocumentInitialized = true;
                Uri.Builder scheme = new Uri.Builder().scheme("android.resource");
                Resources resources = context.getResources();
                int i2 = R$drawable.gallery_ic_detail_scan_document_copy;
                Uri build = scheme.authority(resources.getResourcePackageName(i2)).appendPath(context.getResources().getResourceTypeName(i2)).appendPath(context.getResources().getResourceEntryName(i2)).build();
                Log.d("TextExtractionHelper", "add document capsule", Logger.getEncodedString((Object) build));
                this.mCapsuleHelper.addCapsule(build, context.getString(R$string.scan), new b(this));
                this.mCapsuleLogger.postAnalyticsDetailLog(AnalyticsEventId.EVENT_DETAIL_VIEW_CAPSULE_APPEAR.toString(), AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_SCAN.toString());
            }
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("add document capsule failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    private void barcodeScan(Bitmap bitmap) {
        boolean z;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Optional.ofNullable(this.mClipInfo.getContext()).ifPresent(new c(14, this, bitmap));
            List<Barcode> list = this.mBarCodeList;
            if (list == null || list.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            this.mHasBarcodeData = z;
            Log.d("TextExtractionHelper", "barcode scan done" + Logger.vt(bitmap, this, Long.valueOf(currentTimeMillis)));
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("barcode scan failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: barcodeScanAndObjectCapture */
    public void lambda$extract$1(Bitmap bitmap, boolean z) {
        if (bitmap.isRecycled()) {
            Log.w("TextExtractionHelper", "bitmap is recycled, skip barcode scan and capture");
            return;
        }
        try {
            barcodeScan(bitmap);
            objectCapture(bitmap, z);
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("barcode scan and capture failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    private void checkTextExists() {
        List<OcrData.BlockInfo> list;
        TextData textData = this.mTextData;
        if (textData != null) {
            list = textData.getOcrData().getBlockList();
        } else {
            list = null;
        }
        if (list == null || list.isEmpty() || list.stream().allMatch(new M4.j(23))) {
            Utils.showThemeToast(this.mClipInfo.getContext(), R$string.no_text_found);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: extractText */
    public void lambda$extract$0(Bitmap bitmap) {
        TextData textData;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mHasData) {
                textData = this.mRecognizer.extractTextData(bitmap);
            } else {
                textData = new TextData(new OcrData(new ArrayList()), new EntityData(new ArrayList()));
            }
            this.mTextData = textData;
            Log.d("TextExtractionHelper", "extract text done" + Logger.vt(bitmap, this, Long.valueOf(currentTimeMillis)));
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("extract text failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    private MotionEvent getFakeUpEvent() {
        return MotionEvent.obtain(0, 0, 1, this.mInitX, this.mInitY, 0);
    }

    private TextData getFilteredTextData(TextData textData, String str) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Iterator<OcrData.BlockInfo> it;
        ArrayList arrayList3;
        if (textData == null) {
            return null;
        }
        List<OcrData.BlockInfo> blockList = textData.getOcrData().getBlockList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        if (!blockList.isEmpty()) {
            List list = (List) Arrays.stream(str.split("\\s+")).filter(new M4.j(22)).map(new o(4)).collect(Collectors.toList());
            int size = list.size();
            int[] iArr = new int[size];
            Iterator<OcrData.BlockInfo> it2 = blockList.iterator();
            while (it2.hasNext()) {
                OcrData.BlockInfo next = it2.next();
                ArrayList arrayList6 = new ArrayList();
                for (OcrData.LineInfo next2 : next.getLineList()) {
                    ArrayList arrayList7 = new ArrayList();
                    for (OcrData.WordInfo next3 : next2.getWordList()) {
                        int i2 = 0;
                        while (i2 < size) {
                            if (next3.toString().toLowerCase().contains((CharSequence) list.get(i2))) {
                                iArr[i2] = 1;
                                it = it2;
                                arrayList3 = arrayList4;
                                arrayList7.add(new OcrData.WordInfo(next3.getCharList(), next3.getRect(), next3.getPoly()));
                            } else {
                                it = it2;
                                arrayList3 = arrayList4;
                            }
                            i2++;
                            it2 = it;
                            arrayList4 = arrayList3;
                        }
                    }
                    Iterator<OcrData.BlockInfo> it3 = it2;
                    ArrayList arrayList8 = arrayList4;
                    if (!arrayList7.isEmpty()) {
                        arrayList6.add(new OcrData.LineInfo(arrayList7, next2.getRect(), next2.getPoly()));
                    }
                    it2 = it3;
                    arrayList4 = arrayList8;
                }
                Iterator<OcrData.BlockInfo> it4 = it2;
                ArrayList arrayList9 = arrayList4;
                if (!arrayList6.isEmpty()) {
                    arrayList5.add(new OcrData.BlockInfo(arrayList6, next.getRect(), next.getPoly()));
                }
                it2 = it4;
                arrayList4 = arrayList9;
            }
            arrayList2 = arrayList4;
            if (Arrays.stream(iArr).allMatch(new D3.b(2))) {
                arrayList = arrayList5;
                return new TextData(new OcrData(arrayList), textData.getEntityData());
            }
        } else {
            arrayList2 = arrayList4;
        }
        arrayList = arrayList2;
        return new TextData(new OcrData(arrayList), textData.getEntityData());
    }

    private void getSuggestion() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule)) {
            SimpleThreadPool.getInstance().execute(new O8.c(this, 0));
        }
    }

    private boolean isViewReady() {
        if (!this.mEnabled || !this.mInitialized) {
            return false;
        }
        if (this.mHasData || this.mHasBarcodeData) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addDocumentCapsule$5() {
        Consumer<MediaItem> consumer = this.mDocumentScanClickConsumer;
        if (consumer != null) {
            consumer.accept(this.mMediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$barcodeScan$6(Bitmap bitmap, Context context) {
        this.mBarCodeList = BarcodeProvider.getBarcodeScanner(context).process(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$2(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != bitmap2) {
            putBitmapToPool(bitmap2);
        }
        putBitmapToPool(bitmap);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getFilteredTextData$8(String str) {
        return !str.isEmpty();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getFilteredTextData$9(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PointF[] lambda$getHighlightPoints$4(int i2) {
        return new PointF[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getSuggestion$11() {
        try {
            ThreadUtil.postOnUiThreadDelayed(new a(28, this, DeepSkySuggest.loadTextCapsule(this.mMediaItem, this.mTextData)), 100);
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("request suggestion failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    private void objectCapture(Bitmap bitmap, boolean z) {
        if (z) {
            try {
                if (PocFeatures.CLIP_PARALLEL && DeepSkyHelper.isObjectCaptureSupported()) {
                    ObjectCaptureHelper objectCaptureHelper = new ObjectCaptureHelper(this.mClipInfo);
                    this.mObjectCaptureHelper = objectCaptureHelper;
                    objectCaptureHelper.setInitPoint(this.mInitX, this.mInitY);
                    this.mObjectCaptureHelper.capture(bitmap);
                }
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("object capture failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    /* access modifiers changed from: private */
    public void onTranslateClicked(boolean z) {
        this.mTranslationOn = z;
        this.mTranslateClickConsumer.accept(Boolean.valueOf(z));
        if (z) {
            ThreadUtil.postOnBgThread(new O8.c(this, 1));
        }
    }

    private void putBitmapToPool(Bitmap bitmap) {
        Boolean bool = (Boolean) ObjectDictionary.getTag(bitmap, "Recyclable");
        if (bool != null && bool.booleanValue()) {
            BitmapUtils.putBitmap(bitmap);
        }
    }

    /* access modifiers changed from: private */
    public void requestImageTranslation() {
        try {
            MemLog memLog = new MemLog("VTT");
            this.mTextExtractionHelper.startImageTranslation(new TranslationTokenInfo("183eb3eae9ac0f8883ec1e5a3112c3ef667eaa3e9d123ef7c86da7d021af987d", "308204d4308203bca003020102020900d20995a79c0daad6300d06092a864886f70d01010505003081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d301e170d3131303632323132323531325a170d3338313130373132323531325a3081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d30820120300d06092a864886f70d01010105000382010d00308201080282010100c986384a3e1f2fb206670e78ef232215c0d26f45a22728db99a44da11c35ac33a71fe071c4a2d6825a9b4c88b333ed96f3c5e6c666d60f3ee94c490885abcf8dc660f707aabc77ead3e2d0d8aee8108c15cd260f2e85042c28d2f292daa3c6da0c7bf2391db7841aade8fdf0c9d0defcf77124e6d2de0a9e0d2da746c3670e4ffcdc85b701bb4744861b96ff7311da3603c5a10336e55ffa34b4353eedc85f51015e1518c67e309e39f87639ff178107f109cd18411a6077f26964b6e63f8a70b9619db04306a323c1a1d23af867e19f14f570ffe573d0e3a0c2b30632aaec3173380994be1e341e3a90bd2e4b615481f46db39ea83816448ec35feb1735c1f3020103a382010b30820107301d0603551d0e04160414932c3af70b627a0c7610b5a0e7427d6cfaea3f1e3081d70603551d230481cf3081cc8014932c3af70b627a0c7610b5a0e7427d6cfaea3f1ea181a8a481a53081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d820900d20995a79c0daad6300c0603551d13040530030101ff300d06092a864886f70d01010505000382010100329601fe40e036a4a86cc5d49dd8c1b5415998e72637538b0d430369ac51530f63aace8c019a1a66616a2f1bb2c5fabd6f313261f380e3471623f053d9e3c53f5fd6d1965d7b000e4dc244c1b27e2fe9a323ff077f52c4675e86247aa801187137e30c9bbf01c567a4299db4bf0b25b7d7107a7b81ee102f72ff47950164e26752e114c42f8b9d2a42e7308897ec640ea1924ed13abbe9d120912b62f4926493a86db94c0b46f44c6161d58c2f648164890c512dfb28d42c855bf470dbee2dab6960cad04e81f71525ded46cdd0f359f99c460db9f007d96ce83b4b218ac2d82c48f12608d469733f05a3375594669ccbf8a495544d6c5701e9369c08c810158", "22n6hzkam0", "", ""), new K.a(13));
            memLog.check();
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("request image translation failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            TextExtractionDrawHelper.ProgressBarCallback progressBarCallback = this.mProgressBarCallback;
            Objects.requireNonNull(progressBarCallback);
            ThreadUtil.postOnUiThreadDelayed(new M9.o(17, progressBarCallback), 100);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: scaleAndShift */
    public PointF lambda$getHighlightPoints$3(Point point, PointF pointF, float f) {
        return new PointF((((float) point.x) * f) + pointF.x, (((float) point.y) * f) + pointF.y);
    }

    private void setCapsuleLayoutVisibility() {
        CapsuleHelper capsuleHelper;
        if (this.mEnabled && this.mInitialized && (capsuleHelper = this.mCapsuleHelper) != null) {
            try {
                capsuleHelper.setCapsuleLayoutVisibility(isFullStateExceptFilter());
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("set capsule layout visibility failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    private boolean startBarcodeSelection(float f, float f5) {
        try {
            return this.mTextExtractionHelper.startBarcodeSelection(f, f5);
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("start barcode selection failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            return false;
        }
    }

    private boolean startTextSelection(float f, float f5, boolean z) {
        try {
            return this.mTextExtractionHelper.startTextSelectionWithCoordinate(f, f5, z);
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("start text selection failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            return false;
        }
    }

    private boolean supportDocumentScan(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isSharing() || mediaItem.isDrm() || mediaItem.isPrivateItem() || !DocumentScanner.getInstance().hasDocument(mediaItem)) {
            return false;
        }
        return true;
    }

    private boolean supportMagnifier() {
        MediaItem mediaItem;
        if (!PreferenceFeatures.OneUi7x.TEXT_EXTRACTION_MAGNIFIER || (mediaItem = this.mMediaItem) == null || mediaItem.isVideo() || this.mClipInfo.isMotionPhotoVideoMode()) {
            return false;
        }
        return true;
    }

    public void clear() {
        clearInitPoint();
        clearVariables();
        this.mEnabled = false;
        this.mRecognizer = null;
        this.mTextExtractionHelper = null;
        this.mCapsuleHelper = null;
        this.mCapsuleLogger.setAnalyticsDetailLogCallback((BiConsumer<String, String>) null);
        this.mTranslateLanguageHelper = null;
    }

    public void clearDetectType(boolean z) {
        long j2 = this.mFileId;
        if (j2 > 0) {
            TextExtractionCache.put(j2, -1);
            if (z) {
                DbCompat.textExtractionApi().updateTextExtractionDetectionType(this.mFileId, -1);
            }
            DocumentScanner.getInstance().resetCache(this.mFileId);
        }
    }

    public void clearInitPoint() {
        this.mInitX = Float.MIN_VALUE;
        this.mInitY = Float.MIN_VALUE;
    }

    public void clearSelection() {
        if (isViewReady()) {
            try {
                this.mTextExtractionHelper.clearAllSelection();
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("clear selection failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void clearVariables() {
        this.mCapsuleInitialized = false;
        this.mDetected = false;
        this.mDocumentInitialized = false;
        this.mDocumentScanned = false;
        this.mExtracted = false;
        this.mHasBarcodeData = false;
        this.mHasData = false;
        this.mReady = false;
        this.mTranslationOn = false;
        this.mBitmapWidth = 0;
        this.mDetectionType = -1;
        this.mFileId = 0;
        this.mBarCodeList = null;
        this.mFilteredTextData = null;
        this.mTextData = null;
        this.mObjectCaptureHelper = null;
        this.mState = State.NONE;
    }

    public void configurationChanged() {
        if (this.mEnabled && this.mCapsuleInitialized) {
            try {
                this.mTextExtractionHelper.onConfigurationChanged();
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("configuration change failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void detect(boolean z) {
        boolean z3;
        String str;
        int i2;
        if (this.mEnabled && !this.mDetected) {
            try {
                Trace.beginSection("TextExtractionHelper detect");
                long currentTimeMillis = System.currentTimeMillis();
                int i7 = TextExtractionCache.get(this.mFileId);
                boolean z7 = false;
                Bitmap bitmap = null;
                if (i7 > -1) {
                    this.mDetectionType = i7;
                } else {
                    TextExtractionApi textExtractionApi = DbCompat.textExtractionApi();
                    if (z) {
                        i2 = textExtractionApi.getTextExtractionDetectionType(this.mFileId);
                    } else {
                        i2 = -1;
                    }
                    this.mDetectionType = i2;
                    if (i2 == -1) {
                        MemLog memLog = new MemLog("VTD");
                        Bitmap bitmap2 = this.mClipInfo.getBitmap(false);
                        if (bitmap2 != null) {
                            if (!bitmap2.isRecycled()) {
                                this.mDetectionType = this.mRecognizer.detectText(bitmap2) ? 1 : 0;
                                putBitmapToPool(bitmap2);
                                memLog.check();
                                if (z) {
                                    textExtractionApi.updateTextExtractionDetectionType(this.mFileId, this.mDetectionType);
                                }
                                bitmap = bitmap2;
                            }
                        }
                        Log.w("TextExtractionHelper", "detect skipped, bitmap is null or recycled");
                        Trace.endSection();
                        return;
                    }
                    TextExtractionCache.put(this.mFileId, this.mDetectionType);
                }
                int i8 = this.mDetectionType;
                if (i8 > -1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.mDetected = z3;
                if (i8 > 0) {
                    z7 = true;
                }
                this.mHasData = z7;
                StringBuilder sb2 = new StringBuilder("detect");
                if (i7 > -1) {
                    str = "(c)";
                } else {
                    str = "";
                }
                sb2.append(str);
                sb2.append(Logger.vt(bitmap, this, Long.valueOf(currentTimeMillis)));
                Log.d("TextExtractionHelper", sb2.toString());
            } catch (Error | Exception e) {
                Log.e("TextExtractionHelper", "detect failed, e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + this);
            } finally {
                Trace.endSection();
            }
        }
    }

    public void draw(Canvas canvas) {
        boolean z;
        try {
            if (isViewReady()) {
                TextExtractionDrawHelper textExtractionDrawHelper = this.mTextExtractionHelper;
                if (this.mState == State.PARTIAL && !isTextSelected()) {
                    if (!isBarcodeSelected()) {
                        z = false;
                        textExtractionDrawHelper.setUnderlineVisible(z);
                        this.mTextExtractionHelper.drawSelection(canvas);
                    }
                }
                z = true;
                textExtractionDrawHelper.setUnderlineVisible(z);
                this.mTextExtractionHelper.drawSelection(canvas);
            }
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("draw failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    public void extract(Bitmap bitmap, Bitmap bitmap2, boolean z, String str) {
        if (this.mEnabled && !this.mExtracted) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                Trace.beginSection("TextExtractionHelper extract");
                this.mExtracted = true;
                MemLog memLog = new MemLog("VTE");
                new LatchBuilder("extractText").addWorker(new a(29, this, bitmap)).addWorker(new g((Object) this, (Object) bitmap2, z, 4)).setPostExecutor((Runnable) new J6.c(this, bitmap, bitmap2, 23)).start();
                memLog.check();
                this.mTextExtractionHelper.setBitmap(bitmap);
                this.mTextExtractionHelper.setBarcodeBitmap(bitmap2);
                if (!TextUtils.isEmpty(str)) {
                    this.mFilteredTextData = getFilteredTextData(this.mTextData, str);
                }
                this.mBitmapWidth = bitmap.getWidth();
                getSuggestion();
                Log.d("TextExtractionHelper", "extract done " + this + Logger.vt(currentTimeMillis));
            } catch (Error | Exception e) {
                Log.e("TextExtractionHelper", "extract failed, e=" + e.getMessage() + ArcCommonLog.TAG_COMMA + this);
            } finally {
                Trace.endSection();
            }
        }
    }

    public void finishTextSelection() {
        if (this.mEnabled && this.mInitialized) {
            clearSelection();
            try {
                this.mTextExtractionHelper.finishTextSelection();
                this.mTextExtractionHelper.onConfigurationChanged();
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("finish text selection failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void finishTranslate() {
        CapsuleHelper capsuleHelper;
        if (this.mEnabled && this.mInitialized && this.mTranslationOn && (capsuleHelper = this.mCapsuleHelper) != null) {
            try {
                capsuleHelper.turnOffTranslate();
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("finish translate failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public long getFileId() {
        return this.mFileId;
    }

    public ArrayList<PointF[]> getHighlightPoints(RectF rectF) {
        ArrayList<PointF[]> arrayList = new ArrayList<>();
        try {
            if (this.mBitmapWidth > 0) {
                float width = rectF.width() / ((float) this.mBitmapWidth);
                PointF pointF = new PointF(rectF.left, rectF.top);
                for (OcrResult.WordInfo next : this.mFilteredTextData.toOcrResult().getWordInfoList()) {
                    if (!TextUtils.isEmpty(next.getString())) {
                        arrayList.add((PointF[]) Arrays.stream(next.getPoly()).map(new O8.d(this, pointF, width)).toArray(new C0387w(26)));
                    }
                }
            }
            return arrayList;
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("get highlight points failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            return arrayList;
        }
    }

    public float getInitX() {
        return this.mInitX;
    }

    public float getInitY() {
        return this.mInitY;
    }

    public ObjectCaptureHelper getObjectCaptureHelper() {
        if (PocFeatures.CLIP_PARALLEL) {
            return this.mObjectCaptureHelper;
        }
        return null;
    }

    public boolean hasBarcodeData() {
        return this.mHasBarcodeData;
    }

    public boolean hasData() {
        return this.mHasData;
    }

    public void init(View view, ViewGroup viewGroup, LinearLayout linearLayout) {
        if (this.mEnabled) {
            try {
                if (!this.mInitialized) {
                    long currentTimeMillis = System.currentTimeMillis();
                    MemLog memLog = new MemLog("VTI");
                    this.mTextExtractionHelper.init(view);
                    this.mInitialized = true;
                    Log.d("TextExtractionHelper", "init " + this + " +" + (System.currentTimeMillis() - currentTimeMillis));
                    memLog.check();
                }
                if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule)) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    Context context = view.getContext();
                    if (!this.mCapsuleInitialized) {
                        this.mTextExtractionHelper.initCapsuleLayout(viewGroup);
                        this.mTextExtractionHelper.setProgressBarCallback(this.mProgressBarCallback);
                        this.mTextExtractionHelper.setTranslateClickListener(new b(this));
                        CapsuleHelper capsuleHelper = this.mTextExtractionHelper.getCapsuleHelper();
                        this.mCapsuleHelper = capsuleHelper;
                        if (capsuleHelper != null) {
                            capsuleHelper.updateCapsuleColor(context.getColor(R$color.text_extraction_capsule_bg_color), context.getColor(R$color.text_extraction_capsule_text_color), Integer.valueOf(context.getColor(R$color.text_extraction_capsule_pressed_bg_color)), Integer.valueOf(context.getColor(R$color.text_extraction_capsule_pressed_text_color)), Integer.valueOf(context.getColor(R$color.text_extraction_capsule_ripple_color)));
                            this.mCapsuleHelper.setCapsuleListener(this.mCapsuleLogger);
                        }
                        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionLangManage)) {
                            TranslateLanguageHelper translateLanguageHelper = this.mTextExtractionHelper.getTranslateLanguageHelper();
                            this.mTranslateLanguageHelper = translateLanguageHelper;
                            if (translateLanguageHelper != null) {
                                translateLanguageHelper.initLayout(linearLayout);
                                this.mTranslateLanguageHelper.setListener(this.mLangManageListener);
                            }
                        }
                        this.mCapsuleInitialized = true;
                        Log.d("TextExtractionHelper", "init capsule " + this + " +" + (System.currentTimeMillis() - currentTimeMillis2));
                    }
                    if (!SUPPORT_CAMERA_DOCUMENT_SCAN) {
                        addDocumentCapsule(context);
                    }
                }
                setCapsuleLayoutVisibility();
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("init failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public boolean isBarcodeSelected() {
        if (!isViewReady()) {
            return false;
        }
        try {
            return this.mTextExtractionHelper.isBarcodeSelected();
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("checking barcode selection failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            return false;
        }
    }

    public boolean isDetected() {
        return this.mDetected;
    }

    public boolean isDisabled() {
        return !this.mEnabled;
    }

    public boolean isDocumentScanned() {
        return this.mDocumentScanned;
    }

    public boolean isExtracted() {
        return this.mExtracted;
    }

    public boolean isFullFilterState() {
        if (this.mState == State.FULL_FILTER) {
            return true;
        }
        return false;
    }

    public boolean isFullIndirectState() {
        if (this.mState == State.FULL_INDIRECT) {
            return true;
        }
        return false;
    }

    public boolean isFullState() {
        State state = this.mState;
        if (state == State.FULL || state == State.FULL_INDIRECT || state == State.FULL_FILTER) {
            return true;
        }
        return false;
    }

    public boolean isFullStateExceptFilter() {
        State state = this.mState;
        if (state == State.FULL || state == State.FULL_INDIRECT) {
            return true;
        }
        return false;
    }

    public boolean isNoneState(State state) {
        return state == State.NONE;
    }

    public boolean isPartialState() {
        if (this.mState == State.PARTIAL) {
            return true;
        }
        return false;
    }

    public boolean isReady() {
        return this.mReady;
    }

    public boolean isTextSelected() {
        if (!isViewReady()) {
            return false;
        }
        try {
            return this.mTextExtractionHelper.isTextSelected();
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("checking text selection failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            return false;
        }
    }

    public boolean isTranslationOn() {
        return this.mTranslationOn;
    }

    public void ready(RectF rectF) {
        TextData textData;
        if (this.mEnabled && this.mInitialized) {
            if (rectF == null) {
                Log.e("TextExtractionHelper", "ready failed, null bitmap or rect");
            } else if (this.mExtracted) {
                try {
                    this.mTextExtractionHelper.setBitmapRect(rectF);
                    if (!isFullFilterState() || (textData = this.mFilteredTextData) == null) {
                        TextData textData2 = this.mTextData;
                        if (textData2 != null && this.mBarCodeList != null) {
                            this.mTextExtractionHelper.setTextData(textData2);
                            this.mTextExtractionHelper.setBarcodeList(this.mBarCodeList);
                        } else if (textData2 != null) {
                            this.mTextExtractionHelper.setTextData(textData2);
                        }
                    } else {
                        this.mTextExtractionHelper.setTextData(textData);
                    }
                    this.mTextExtractionHelper.setMagnifierEnabled(supportMagnifier());
                    this.mReady = true;
                    Log.d("TextExtractionHelper", "ready " + this);
                } catch (Error | Exception e) {
                    j.B(e, new StringBuilder("ready failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
                }
            }
        }
    }

    public void refresh(RectF rectF) {
        if (isViewReady()) {
            try {
                this.mTextExtractionHelper.updateTextSelection(rectF);
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("refresh failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void resetFilter() {
        try {
            TextData textData = this.mTextData;
            if (textData != null) {
                this.mTextExtractionHelper.setTextData(textData);
            }
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("reset filter failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
        }
    }

    public void resetObjectCaptureHelper() {
        this.mObjectCaptureHelper = null;
    }

    public void setAnalyticsDetailLogCallback(BiConsumer<String, String> biConsumer) {
        this.mCapsuleLogger.setAnalyticsDetailLogCallback(biConsumer);
    }

    public void setDim() {
        if (this.mEnabled) {
            try {
                this.mTextExtractionHelper.setDimEnabled(isFullState());
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("set dim failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void setDocumentScanClickConsumer(Consumer<MediaItem> consumer) {
        this.mDocumentScanClickConsumer = consumer;
    }

    public void setDocumentScanned() {
        this.mDocumentScanned = true;
    }

    public void setInitPoint(float f, float f5) {
        this.mInitX = f;
        this.mInitY = f5;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        this.mFileId = mediaItem.getFileId();
    }

    public void setPopupMenuVisibility(boolean z) {
        if (!isViewReady()) {
            return;
        }
        if (z) {
            try {
                this.mTextExtractionHelper.showPopupMenu();
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("popup menu visibility change failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        } else {
            this.mTextExtractionHelper.dismissPopupMenu();
        }
    }

    public void setScale(float f) {
        if (isViewReady()) {
            try {
                this.mTextExtractionHelper.setScaleFactor(f);
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("set scale failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void setScaleState(boolean z) {
        if (isViewReady()) {
            try {
                this.mTextExtractionHelper.setOnScaleState(z ? 1 : 0);
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("set scale state failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public void setState(State state) {
        this.mState = state;
        setCapsuleLayoutVisibility();
    }

    public void setTranslateClickConsumer(Consumer<Boolean> consumer) {
        this.mTranslateClickConsumer = consumer;
    }

    public void setTranslationState(boolean z) {
        if (isViewReady()) {
            try {
                this.mTextExtractionHelper.setOnTranslationState(z ? 1 : 0);
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("set translation state failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
    }

    public boolean start(int[] iArr, int i2, float f, boolean z) {
        if (isViewReady()) {
            try {
                float f5 = this.mInitX;
                if (f5 != Float.MIN_VALUE) {
                    float f8 = this.mInitY;
                    if (f8 != Float.MIN_VALUE) {
                        boolean start = start((f5 - ((float) iArr[0])) / f, ((f8 - ((float) iArr[1])) - ((float) i2)) / f, true, z);
                        if (!z) {
                            return start;
                        }
                        touch(getFakeUpEvent());
                        return start;
                    }
                }
                long currentTimeMillis = System.currentTimeMillis();
                checkTextExists();
                this.mTextExtractionHelper.startTextSelectionByButton();
                Log.d("TextExtractionHelper", "start(B)" + Logger.vt(currentTimeMillis));
            } catch (Error | Exception e) {
                j.B(e, new StringBuilder("start(B) failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            }
        }
        return false;
    }

    public String toString() {
        char c5;
        char c6;
        char c8;
        char c10;
        char c11;
        char c12;
        boolean z;
        Object obj;
        StringBuilder sb2 = new StringBuilder("VTH[");
        sb2.append(this.mFileId);
        sb2.append(',');
        if (this.mEnabled) {
            c5 = 'E';
        } else {
            c5 = 'e';
        }
        sb2.append(c5);
        if (this.mInitialized) {
            c6 = 'I';
        } else {
            c6 = 'i';
        }
        sb2.append(c6);
        if (this.mHasData) {
            c8 = 'H';
        } else {
            c8 = 'h';
        }
        sb2.append(c8);
        if (this.mDetected) {
            c10 = 'D';
        } else {
            c10 = 'd';
        }
        sb2.append(c10);
        if (this.mExtracted) {
            c11 = 'X';
        } else {
            c11 = 'x';
        }
        sb2.append(c11);
        if (this.mReady) {
            c12 = 'R';
        } else {
            c12 = 'r';
        }
        sb2.append(c12);
        sb2.append(',');
        if (this.mTextData != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(',');
        List<Barcode> list = this.mBarCodeList;
        if (list != null) {
            obj = Integer.valueOf(list.size());
        } else {
            obj = "-1";
        }
        sb2.append(obj);
        sb2.append(',');
        sb2.append(this.mState);
        sb2.append(',');
        return j.e(sb2, this.mDetectionType, ']');
    }

    public boolean touch(MotionEvent motionEvent) {
        if (!isViewReady()) {
            return false;
        }
        try {
            return this.mTextExtractionHelper.handleTouchEvent(motionEvent);
        } catch (Error | Exception e) {
            j.B(e, new StringBuilder("touch event failed, e="), ArcCommonLog.TAG_COMMA, this, "TextExtractionHelper");
            return false;
        }
    }

    public boolean isNoneState() {
        return this.mState == State.NONE;
    }

    public boolean start(float f, float f5, boolean z, boolean z3) {
        if (!isViewReady()) {
            return false;
        }
        String str = "SP";
        if (z3) {
            StringBuilder sb2 = new StringBuilder("start skipped(");
            if (z) {
                str = "LP";
            }
            sb2.append(str);
            sb2.append("), already up");
            Log.w("TextExtractionHelper", sb2.toString());
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean startBarcodeSelection = startBarcodeSelection(f, f5) | startTextSelection(f, f5, z);
        StringBuilder sb3 = new StringBuilder("start(");
        if (z) {
            str = "LP";
        }
        sb3.append(str);
        sb3.append(")");
        A.a.A(new Object[]{Float.valueOf(f), Float.valueOf(f5), Boolean.valueOf(startBarcodeSelection), Long.valueOf(currentTimeMillis)}, sb3, "TextExtractionHelper");
        return startBarcodeSelection;
    }
}
