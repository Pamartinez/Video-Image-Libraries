package com.samsung.android.gallery.module.lottie.recap.binder;

import ba.C0582a;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapColorPicker {
    private static final CharSequence TAG = "RecapColorPicker";
    RecapTemplate mTemplate;

    public RecapColorPicker(RecapTemplate recapTemplate) {
        this.mTemplate = recapTemplate;
    }

    private boolean canUseByHue(ArrayList<RecapImage> arrayList, RecapImage recapImage) {
        int i2 = 0;
        while (i2 < arrayList.size()) {
            RecapImage recapImage2 = arrayList.get(i2);
            if (recapImage2 == null || Math.abs(recapImage.hue - recapImage2.hue) > 10.0f) {
                i2++;
            } else {
                CharSequence charSequence = TAG;
                Log.i(charSequence, "drop by color hue  : " + recapImage2.hue, Float.valueOf(recapImage.hue));
                return true;
            }
        }
        return true;
    }

    private ArrayList<RecapImage> getImagesByHueDiff(ArrayList<RecapImage> arrayList) {
        arrayList.sort(new c(0));
        ArrayList<RecapImage> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            RecapImage recapImage = arrayList.get(i2);
            if (canUseByHue(arrayList2, recapImage)) {
                arrayList2.add(recapImage);
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$findTargetBgColor$0(RecapBgLayer recapBgLayer) {
        if (recapBgLayer.fixedSat == -1.0f || recapBgLayer.fixedBright == -1.0f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$findTargetBgColor$2(RecapImage recapImage, RecapBgLayer recapBgLayer) {
        float f = recapBgLayer.graySaturationMax;
        if (f != 0.0f) {
            float f5 = recapImage.saturation;
            if (f5 <= f) {
                recapBgLayer.targetColor = recapImage.createDynamicColor(f5, recapBgLayer.fixedBrightForGray);
                CharSequence charSequence = TAG;
                Log.i(charSequence, "setBgColor for " + recapBgLayer.dataBindingKey + " from " + recapImage.dataBindingKey + "#" + recapImage.getIndex(), recapBgLayer.targetColor);
            }
        }
        recapBgLayer.targetColor = recapImage.createDynamicColor(recapBgLayer.fixedSat, recapBgLayer.fixedBright);
        CharSequence charSequence2 = TAG;
        Log.i(charSequence2, "setBgColor for " + recapBgLayer.dataBindingKey + " from " + recapImage.dataBindingKey + "#" + recapImage.getIndex(), recapBgLayer.targetColor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$findTargetBgColor$3(RecapTemplateScene recapTemplateScene) {
        ArrayList arrayList = new ArrayList(recapTemplateScene.mImages.values());
        ArrayList arrayList2 = new ArrayList(recapTemplateScene.mBackgrounds.values());
        if (!arrayList2.removeIf(new a(0))) {
            arrayList2.removeIf(new a(1));
        }
        if (!arrayList2.isEmpty()) {
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RecapBgLayer recapBgLayer = (RecapBgLayer) it.next();
                if (!recapBgLayer.isFileIndexedColorPick()) {
                    if (!recapBgLayer.isSingleImageColorPick()) {
                        if (recapBgLayer.colorPickType != RecapBgLayer.ColorPickType.COLOR_BY_FILE_NAME) {
                            updateTargetColor(arrayList2, getImagesByHueDiff(arrayList));
                            break;
                        }
                        RecapImage recapImage = this.mTemplate.getImages().get(recapBgLayer.targetFile);
                        recapBgLayer.targetColor = recapImage.createDynamicColor(recapBgLayer.fixedSat, recapBgLayer.fixedBright);
                        CharSequence charSequence = TAG;
                        Log.i(charSequence, "setBgColor for " + recapBgLayer.dataBindingKey + " from " + recapImage.dataBindingKey + "#" + recapImage.getIndex() + "(hsv=" + recapImage.hue + "/" + recapImage.saturation + "/" + recapImage.bright + ")", recapBgLayer.targetColor);
                    } else {
                        RecapImage recapImage2 = (RecapImage) arrayList.get(0);
                        if (recapImage2 != null) {
                            arrayList2.forEach(new C0582a(8, recapImage2));
                        } else {
                            return;
                        }
                    }
                } else {
                    arrayList.sort(Comparator.comparingInt(new b(0)));
                    updateTargetColor(arrayList2, arrayList);
                    break;
                }
            }
        }
        updateTextColor(recapTemplateScene, arrayList);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$getImagesByHueDiff$6(RecapImage recapImage, RecapImage recapImage2) {
        return -Float.compare(recapImage2.saturation, recapImage.saturation);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateTargetColor$7(RecapBgLayer recapBgLayer) {
        if (recapBgLayer.fileIndex > -1) {
            return true;
        }
        return false;
    }

    private void updateTargetColor(ArrayList<RecapBgLayer> arrayList, ArrayList<RecapImage> arrayList2) {
        if (arrayList.size() != 0) {
            if (arrayList.stream().anyMatch(new a(2))) {
                arrayList.sort(Comparator.comparingInt(new b(1)));
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                RecapBgLayer recapBgLayer = arrayList.get(i2);
                int i7 = recapBgLayer.fileIndex;
                if (i7 == -1) {
                    i7 = i2;
                }
                if (i7 < arrayList2.size()) {
                    RecapImage recapImage = arrayList2.get(i7);
                    recapBgLayer.targetColor = recapImage.createDynamicColor(recapBgLayer.fixedSat, recapBgLayer.fixedBright);
                    CharSequence charSequence = TAG;
                    Log.i(charSequence, "setBgColor for " + recapBgLayer.dataBindingKey + " from " + recapImage.dataBindingKey + "#" + recapImage.getIndex() + "(hsv=" + recapImage.hue + "/" + recapImage.saturation + "/" + recapImage.bright + ")", recapBgLayer.targetColor);
                } else {
                    CharSequence charSequence2 = TAG;
                    Log.i(charSequence2, "setBgColor for " + recapBgLayer.dataBindingKey + " fail. " + i2, Integer.valueOf(arrayList2.size()));
                }
            }
        }
    }

    private void updateTextColor(RecapTemplateScene recapTemplateScene, ArrayList<RecapImage> arrayList) {
        RecapImage recapImage;
        ArrayList arrayList2 = new ArrayList(recapTemplateScene.mDynamicText.values());
        if (!arrayList2.isEmpty()) {
            arrayList2.sort(Comparator.comparingInt(new b(2)));
            arrayList.sort(Comparator.comparingInt(new b(3)));
            int i2 = 0;
            while (i2 < arrayList2.size()) {
                RecapTextLayer recapTextLayer = (RecapTextLayer) arrayList2.get(i2);
                int i7 = recapTextLayer.fileIndex;
                if (i7 > -1 && i7 < arrayList.size()) {
                    recapImage = arrayList.get(recapTextLayer.fileIndex);
                } else if (recapTextLayer.targetFile != null) {
                    recapImage = this.mTemplate.getImages().get(recapTextLayer.targetFile);
                } else {
                    recapImage = null;
                }
                if (recapImage != null) {
                    recapTextLayer.targetColor = recapImage.createDynamicColor(recapTextLayer.fixedSat, recapTextLayer.fixedBright);
                    CharSequence charSequence = TAG;
                    Log.i(charSequence, "setTextColor for " + recapTextLayer.dataBindingKey + " from " + recapImage.dataBindingKey + "#" + recapImage.getIndex() + "(hsv=" + recapImage.hue + "/" + recapImage.saturation + "/" + recapImage.bright + ")", recapTextLayer.targetColor);
                    i2++;
                } else {
                    throw new IllegalArgumentException("fail set text color");
                }
            }
        }
    }

    public void findTargetBgColor() {
        this.mTemplate.getDynamicColorScenes().forEach(new C0582a(9, this));
    }
}
