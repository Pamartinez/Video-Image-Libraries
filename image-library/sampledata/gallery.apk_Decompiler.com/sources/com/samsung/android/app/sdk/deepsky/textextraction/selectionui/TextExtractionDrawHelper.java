package com.samsung.android.app.sdk.deepsky.textextraction.selectionui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslationTokenInfo;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0004[\\]^J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH&¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000fH&¢\u0006\u0004\b\u0014\u0010\u0012J\u0017\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0015H&¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019H&¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001dH&¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010$\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H&¢\u0006\u0004\b$\u0010%J'\u0010)\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u0007H&¢\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u000bH&¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u0007H&¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u0004H&¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u000201H&¢\u0006\u0004\b3\u00104J\u0017\u00107\u001a\u00020\u00042\u0006\u00106\u001a\u000205H&¢\u0006\u0004\b7\u00108J\u0017\u00109\u001a\u00020\u00042\u0006\u00102\u001a\u000201H&¢\u0006\u0004\b9\u00104J\u000f\u0010:\u001a\u00020\u0004H&¢\u0006\u0004\b:\u00100J\u0017\u0010;\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b;\u0010\nJ\u0017\u0010<\u001a\u00020\u00042\u0006\u00106\u001a\u000205H&¢\u0006\u0004\b<\u00108J\u0017\u0010>\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0007H&¢\u0006\u0004\b>\u0010\nJ\u000f\u0010?\u001a\u00020\u0004H&¢\u0006\u0004\b?\u00100J\u000f\u0010@\u001a\u00020\u0004H&¢\u0006\u0004\b@\u00100J\u000f\u0010A\u001a\u00020\u0007H&¢\u0006\u0004\bA\u0010.J\u000f\u0010B\u001a\u00020\u0004H&¢\u0006\u0004\bB\u00100J\u0017\u0010E\u001a\u00020\u00042\u0006\u0010D\u001a\u00020CH&¢\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\u0007H&¢\u0006\u0004\bG\u0010.J\u0011\u0010I\u001a\u0004\u0018\u00010HH&¢\u0006\u0004\bI\u0010JJ\u0011\u0010L\u001a\u0004\u0018\u00010KH&¢\u0006\u0004\bL\u0010MJ\u001f\u0010Q\u001a\u00020\u00042\u0006\u0010O\u001a\u00020N2\u0006\u0010D\u001a\u00020PH'¢\u0006\u0004\bQ\u0010RJ\u0017\u0010U\u001a\u00020\u00042\u0006\u0010T\u001a\u00020SH'¢\u0006\u0004\bU\u0010VJ\u0017\u0010Y\u001a\u00020\u00042\u0006\u0010X\u001a\u00020WH'¢\u0006\u0004\bY\u0010Z¨\u0006_"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper;", "", "Landroid/view/View;", "view", "Lme/x;", "init", "(Landroid/view/View;)V", "", "enabled", "setMagnifierEnabled", "(Z)V", "", "scale", "setScaleFactor", "(F)V", "", "scaleState", "setOnScaleState", "(I)V", "translationState", "setOnTranslationState", "Landroid/view/MotionEvent;", "event", "handleTouchEvent", "(Landroid/view/MotionEvent;)Z", "Landroid/graphics/Canvas;", "canvas", "drawSelection", "(Landroid/graphics/Canvas;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "textData", "setTextData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;)V", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "barcodeList", "setBarcodeList", "(Ljava/util/List;)V", "x", "y", "isLongPress", "startTextSelectionWithCoordinate", "(FFZ)Z", "startBarcodeSelection", "(FF)Z", "startTextSelectionByButton", "()Z", "finishTextSelection", "()V", "Landroid/graphics/Bitmap;", "bitmap", "setBitmap", "(Landroid/graphics/Bitmap;)V", "Landroid/graphics/RectF;", "rect", "setBitmapRect", "(Landroid/graphics/RectF;)V", "setBarcodeBitmap", "clearAllSelection", "setDimEnabled", "updateTextSelection", "visible", "setUnderlineVisible", "showPopupMenu", "dismissPopupMenu", "isTextSelected", "onConfigurationChanged", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ProgressBarCallback;", "callback", "setProgressBarCallback", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ProgressBarCallback;)V", "isBarcodeSelected", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper;", "getCapsuleHelper", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "getTranslateLanguageHelper", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslationTokenInfo;", "tokenInfo", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ImageTranslationResultCallback;", "startImageTranslation", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslationTokenInfo;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ImageTranslationResultCallback;)V", "Landroid/view/ViewGroup;", "layout", "initCapsuleLayout", "(Landroid/view/ViewGroup;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$TranslateCapsuleClickListener;", "listener", "setTranslateClickListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$TranslateCapsuleClickListener;)V", "OnToolbarMenuClickListener", "ProgressBarCallback", "TranslateCapsuleClickListener", "ImageTranslationResultCallback", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TextExtractionDrawHelper {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bg\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ImageTranslationResultCallback;", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ImageTranslationResultCallback {
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$OnToolbarMenuClickListener;", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnToolbarMenuClickListener {
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ProgressBarCallback;", "", "Lme/x;", "startProgressBar", "()V", "finishProgressBar", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ProgressBarCallback {
        void finishProgressBar();

        void startProgressBar();
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bg\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$TranslateCapsuleClickListener;", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TranslateCapsuleClickListener {
    }

    void clearAllSelection();

    void dismissPopupMenu();

    void drawSelection(Canvas canvas);

    void finishTextSelection();

    CapsuleHelper getCapsuleHelper();

    TranslateLanguageHelper getTranslateLanguageHelper();

    boolean handleTouchEvent(MotionEvent motionEvent);

    void init(View view);

    void initCapsuleLayout(ViewGroup viewGroup);

    boolean isBarcodeSelected();

    boolean isTextSelected();

    void onConfigurationChanged();

    void setBarcodeBitmap(Bitmap bitmap);

    void setBarcodeList(List<Barcode> list);

    void setBitmap(Bitmap bitmap);

    void setBitmapRect(RectF rectF);

    void setDimEnabled(boolean z);

    void setMagnifierEnabled(boolean z);

    void setOnScaleState(int i2);

    void setOnTranslationState(int i2);

    void setProgressBarCallback(ProgressBarCallback progressBarCallback);

    void setScaleFactor(float f);

    void setTextData(TextData textData);

    void setTranslateClickListener(TranslateCapsuleClickListener translateCapsuleClickListener);

    void setUnderlineVisible(boolean z);

    void showPopupMenu();

    boolean startBarcodeSelection(float f, float f5);

    void startImageTranslation(TranslationTokenInfo translationTokenInfo, ImageTranslationResultCallback imageTranslationResultCallback);

    boolean startTextSelectionByButton();

    boolean startTextSelectionWithCoordinate(float f, float f5, boolean z);

    void updateTextSelection(RectF rectF);
}
