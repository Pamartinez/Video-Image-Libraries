package com.samsung.android.gallery.module.lottie.recap.binder;

import A4.C0371f;
import N2.j;
import android.graphics.Bitmap;
import android.graphics.RectF;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.HeadGuardian;
import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapImageSelector {
    public static boolean PRINT_IMAGE_ID = false;
    private static final CharSequence TAG = "RecapImageSelector";
    AnalyzedData mAnalyzedData;
    private AtomicInteger mFailPickIndex = new AtomicInteger(0);
    RecapTemplate mTemplate;

    public RecapImageSelector(RecapTemplate recapTemplate, AnalyzedData analyzedData) {
        this.mTemplate = recapTemplate;
        this.mAnalyzedData = analyzedData;
    }

    private ArrayList<RecapImageCandidate> getRecapImageCandidateArrayList(RecapImage recapImage) {
        ArrayList<RecapImageCandidate> arrayList = new ArrayList<>();
        if (recapImage.isMonthlyImage()) {
            if (recapImage.getMonth() < this.mAnalyzedData.mMonthlyBestCandidates.size()) {
                arrayList.addAll(this.mAnalyzedData.mMonthlyBestCandidates.get(recapImage.getMonth()));
            }
        } else if (recapImage.isKeyMoments()) {
            arrayList.addAll(this.mAnalyzedData.mKeyMomentsCandidates);
        } else {
            RecapImage.TargetCandidate targetCandidate = recapImage.mTargetType;
            if (targetCandidate == RecapImage.TargetCandidate.NEW_PLACE) {
                arrayList.addAll(this.mAnalyzedData.mNewLocationCandidates);
                arrayList.addAll(this.mAnalyzedData.mLocationCandidates);
            } else if (targetCandidate == RecapImage.TargetCandidate.BEST_PLACE) {
                arrayList.addAll(this.mAnalyzedData.mKeyMomentsCandidates);
                return arrayList;
            } else if (targetCandidate == RecapImage.TargetCandidate.YEAR_TOP_ACTIVE_DAYS) {
                arrayList.addAll(this.mAnalyzedData.mTopActiveDaysContents);
            } else if (targetCandidate == RecapImage.TargetCandidate.YEAR_MOST_ACTIVE_DAY) {
                arrayList.addAll(this.mAnalyzedData.mMostActiveDayContents);
            } else if (targetCandidate == RecapImage.TargetCandidate.TOP_3_FACE) {
                if (this.mAnalyzedData.mTopFrequentFacesCandidates.size() > recapImage.getIndex()) {
                    arrayList.addAll(this.mAnalyzedData.mTopFrequentFacesCandidates.get(recapImage.getIndex()));
                    return arrayList;
                }
                Log.w(TAG, j.g(new StringBuilder("not enough top face : "), this.mAnalyzedData.mTopFrequentFacesCandidates), Integer.valueOf(recapImage.getIndex()));
                arrayList.addAll(this.mAnalyzedData.mFaceCandidates);
            } else if (targetCandidate == RecapImage.TargetCandidate.WITH_TOP_3_FACE) {
                arrayList.addAll(this.mAnalyzedData.mWithTopFrequentFacesCandidates);
            }
        }
        if (recapImage.needToUsePeoplesImage()) {
            arrayList.addAll(this.mAnalyzedData.mFaceCandidates);
        } else if (recapImage.needToUseLocationImages()) {
            arrayList.addAll(this.mAnalyzedData.mLocationCandidates);
            arrayList.addAll(this.mAnalyzedData.mNewLocationCandidates);
        } else if (recapImage.needNonPeopleImage()) {
            arrayList.addAll(this.mAnalyzedData.mLocationCandidates);
            arrayList.addAll(this.mAnalyzedData.mNewLocationCandidates);
        }
        arrayList.addAll(this.mAnalyzedData.mImageCandidates);
        if (recapImage.placeClosing) {
            arrayList.addAll(this.mAnalyzedData.mLocationCandidates);
            arrayList.addAll(this.mAnalyzedData.mNewLocationCandidates);
        }
        return arrayList;
    }

    private static Comparator<Map.Entry<String, RecapImage>> imageSelectionPriority() {
        return new c(1);
    }

    private static Predicate<RecapImageCandidate> isPeoplesHeadSafeForAll(RecapImage recapImage) {
        return new h(3, recapImage);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$imageSelectionPriority$1(Map.Entry entry, Map.Entry entry2) {
        RecapImage recapImage = (RecapImage) entry.getValue();
        RecapImage recapImage2 = (RecapImage) entry2.getValue();
        int compare = Integer.compare(recapImage.mTargetType.ordinal(), recapImage2.mTargetType.ordinal());
        if (compare != 0) {
            return compare;
        }
        int compare2 = Integer.compare(recapImage.getPriority(), recapImage2.getPriority());
        if (compare2 != 0) {
            return compare2;
        }
        return Integer.compare(recapImage.fileWidth * recapImage.fileHeight, recapImage2.fileWidth * recapImage2.fileHeight);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isPeoplesHeadSafeForAll$7(RecapImage recapImage, RecapImageCandidate recapImageCandidate) {
        boolean z;
        RectF firstFaceRectF = recapImageCandidate.getFirstFaceRectF();
        if (firstFaceRectF != null) {
            String str = recapImage.dataBindingKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + recapImageCandidate.item.getTitle();
            if (recapImage.useViewSizeHeadGuard) {
                z = HeadGuardian.isFaceIn(str, recapImageCandidate.item.getPath(), recapImageCandidate.item.getOrientation(), recapImageCandidate.item.getOrientationTag(), firstFaceRectF, recapImageCandidate.width, recapImageCandidate.height, recapImage.fileWidth, recapImage.fileHeight, recapImage.viewWidth, recapImage.viewHeight);
            } else {
                z = HeadGuardian.isImageCropSafe(firstFaceRectF, recapImageCandidate.width, recapImageCandidate.height, recapImage.fileWidth, recapImage.fileHeight);
            }
            if (!z) {
                Log.i(TAG, "Head cut detected : " + recapImage);
            }
            return z;
        }
        Log.i(TAG, "no face pos");
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r5.hasPeoplePetTag == false) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (r5.mFaces.size() == 1) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r5.mFaces.size() == 1) goto L_0x0010;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ boolean lambda$pickCandidate$4(com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage r4, com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate r5) {
        /*
            com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage$TargetCandidate r0 = r4.mTargetType
            com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage$TargetCandidate r1 = com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage.TargetCandidate.SINGLE_PEOPLE
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L_0x0013
            java.util.ArrayList<com.samsung.android.gallery.module.lottie.recap.data.parser.FaceData> r0 = r5.mFaces
            int r0 = r0.size()
            if (r0 != r3) goto L_0x0011
        L_0x0010:
            r2 = r3
        L_0x0011:
            r3 = r2
            goto L_0x0040
        L_0x0013:
            boolean r0 = r4.placeClosing
            if (r0 == 0) goto L_0x0020
            java.util.ArrayList<com.samsung.android.gallery.module.lottie.recap.data.parser.FaceData> r0 = r5.mFaces
            int r0 = r0.size()
            if (r0 != r3) goto L_0x0011
            goto L_0x0010
        L_0x0020:
            boolean r0 = r4.needNonPeopleImage()
            if (r0 == 0) goto L_0x002d
            java.util.ArrayList<com.samsung.android.gallery.module.lottie.recap.data.parser.FaceData> r0 = r5.mFaces
            boolean r3 = r0.isEmpty()
            goto L_0x0040
        L_0x002d:
            boolean r0 = r4.needPeopleImage()
            if (r0 == 0) goto L_0x0040
            java.util.ArrayList<com.samsung.android.gallery.module.lottie.recap.data.parser.FaceData> r0 = r5.mFaces
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0010
            boolean r0 = r5.hasPeoplePetTag
            if (r0 == 0) goto L_0x0011
            goto L_0x0010
        L_0x0040:
            boolean r4 = r4.properScene
            if (r4 == 0) goto L_0x0048
            boolean r4 = r5.hasSceneryTag
            r4 = r4 & r3
            return r4
        L_0x0048:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.lottie.recap.binder.RecapImageSelector.lambda$pickCandidate$4(com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage, com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate):boolean");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$pickCandidate$5(RecapImage recapImage, RecapImageCandidate recapImageCandidate) {
        if (recapImage.viewWidth == recapImage.viewHeight || recapImage.isVertical == recapImageCandidate.isVertical()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$pickCandidate$6(RecapImage recapImage, RecapImageCandidate recapImageCandidate) {
        boolean isDarkForRecap;
        boolean z = true;
        if (recapImage.mAvoidColor == null) {
            return true;
        }
        Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(recapImageCandidate.item, ThumbKind.MEDIUM_KIND);
        RectF rectF = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
        BitmapOperator rotate = new BitmapOperator(loadThumbnailSync).rotate(recapImageCandidate.item.getOrientation(), recapImageCandidate.item.getOrientationTag());
        if (recapImage.mAvoidColor.equals("whiteTop")) {
            rectF.bottom = 0.3f;
            isDarkForRecap = BitmapUtils.isBrightForRecap(rotate.crop(rectF).apply());
        } else if (recapImage.mAvoidColor.equals("whiteBottom")) {
            rectF.top = 0.7f;
            isDarkForRecap = BitmapUtils.isBrightForRecap(rotate.crop(rectF).apply());
        } else if (recapImage.mAvoidColor.equals("whiteVerticalMiddle")) {
            rectF.top = 0.45f;
            rectF.bottom = 0.55f;
            isDarkForRecap = BitmapUtils.isBrightForRecap(rotate.crop(rectF).apply());
        } else {
            if (recapImage.mAvoidColor.equals("blackRight")) {
                rectF.left = 0.7f;
                isDarkForRecap = BitmapUtils.isDarkForRecap(rotate.crop(rectF).apply());
            }
            CharSequence charSequence = TAG;
            Log.w(charSequence, recapImage.dataBindingKey + " color avoid : " + z, recapImage.mAvoidColor, Logger.getEncodedString(recapImageCandidate.item.getDisplayName()));
            return z;
        }
        z = true ^ isDarkForRecap;
        CharSequence charSequence2 = TAG;
        Log.w(charSequence2, recapImage.dataBindingKey + " color avoid : " + z, recapImage.mAvoidColor, Logger.getEncodedString(recapImageCandidate.item.getDisplayName()));
        return z;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$selectImages$0(AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, Map.Entry entry) {
        if (!atomicBoolean.get()) {
            String str = (String) entry.getKey();
            RecapImage recapImage = (RecapImage) entry.getValue();
            ArrayList<RecapImageCandidate> recapImageCandidateArrayList = getRecapImageCandidateArrayList(recapImage);
            RecapImageCandidate pickCandidate = pickCandidate(recapImage, recapImageCandidateArrayList);
            if (pickCandidate != null) {
                setBitmap(pickCandidate, recapImage);
                CharSequence charSequence = TAG;
                Log.i(charSequence, recapImage.dataBindingKey + "(" + recapImage.getPriority() + ") pick " + recapImage.mTargetType + "(" + recapImage.getMonth() + ")[" + recapImageCandidateArrayList.indexOf(pickCandidate) + "] from " + pickCandidate.mSourceTag + ArcCommonLog.TAG_COMMA + Logger.getEncodedString(pickCandidate.item.getPath()));
                return;
            }
            CharSequence charSequence2 = TAG;
            Log.e(charSequence2, recapImage.dataBindingKey + " pick fail");
            atomicBoolean2.set(false);
        }
    }

    private RecapImageCandidate pickCandidate(RecapImage recapImage, ArrayList<RecapImageCandidate> arrayList) {
        RecapImageCandidate orElse = arrayList.stream().filter(new a(3)).filter(new h(0, recapImage)).filter(new h(1, recapImage)).filter(isPeoplesHeadSafeForAll(recapImage)).filter(new h(2, recapImage)).findFirst().orElse((Object) null);
        if (orElse == null && (orElse = arrayList.stream().filter(new a(3)).filter(isPeoplesHeadSafeForAll(recapImage)).findFirst().orElse((Object) null)) != null) {
            orElse.wrongRatio = true;
        }
        if (orElse == null) {
            orElse = arrayList.stream().filter(new a(3)).findFirst().orElse((Object) null);
        }
        if (orElse != null) {
            orElse.used = true;
            return orElse;
        }
        int andIncrement = this.mFailPickIndex.getAndIncrement();
        if (andIncrement >= arrayList.size()) {
            this.mFailPickIndex.set(1);
            andIncrement = 0;
        }
        try {
            RecapImageCandidate recapImageCandidate = arrayList.get(andIncrement);
            try {
                CharSequence charSequence = TAG;
                Log.w(charSequence, recapImage.dataBindingKey + " pick Fail. reuse sequence. " + recapImage.mTargetType + "(" + recapImage.getMonth() + ") : " + andIncrement);
                return recapImageCandidate;
            } catch (IndexOutOfBoundsException unused) {
                orElse = recapImageCandidate;
                Log.e(TAG, C0212a.p(new StringBuilder(), recapImage.dataBindingKey, " pick Fail. null. "), Integer.valueOf(andIncrement), Integer.valueOf(arrayList.size()), recapImage);
                return orElse;
            }
        } catch (IndexOutOfBoundsException unused2) {
            Log.e(TAG, C0212a.p(new StringBuilder(), recapImage.dataBindingKey, " pick Fail. null. "), Integer.valueOf(andIncrement), Integer.valueOf(arrayList.size()), recapImage);
            return orElse;
        }
    }

    private void setBitmap(RecapImageCandidate recapImageCandidate, RecapImage recapImage) {
        String str;
        MediaItem mediaItem = recapImageCandidate.item;
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem));
        String replace = recapImage.dataBindingKey.replace(".jpg", "]").replace("img_", "[");
        BitmapOperator rotate = new BitmapOperator(decodedBitmap).rotate(mediaItem.getOrientation(), mediaItem.getOrientationTag());
        if (PRINT_IMAGE_ID) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(replace);
            sb2.append(replace);
            sb2.append(replace);
            sb2.append(replace);
            sb2.append(replace);
            C0086a.z(sb2, replace, replace, replace, replace);
            C0086a.z(sb2, replace, replace, replace, replace);
            str = j.f(sb2, replace, replace, replace);
        } else {
            str = null;
        }
        Bitmap apply = rotate.writeDebugText(str).resize(recapImage.fileWidth, recapImage.fileHeight).apply();
        if (decodedBitmap != apply) {
            BitmapUtils.putBitmap(decodedBitmap);
        }
        if (this.mAnalyzedData.fromPreview) {
            recapImage.mTargetBitmap = apply;
            recapImage.mUsedItem = mediaItem;
            recapImage.calculateHsv();
        } else {
            recapImage.mTargetBitmap = apply;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            apply.compress(Bitmap.CompressFormat.JPEG, 92, byteArrayOutputStream);
            recapImage.mTargetJpeg = byteArrayOutputStream.toByteArray();
            recapImage.mUsedItem = mediaItem;
            recapImage.calculateHsv();
            recapImage.mTargetBitmap = null;
            BitmapUtils.putBitmap(apply);
        }
        String firstPeopleName = recapImageCandidate.getFirstPeopleName();
        if (firstPeopleName != null) {
            recapImage.peopleName = firstPeopleName;
        }
        recapImage.locationName = recapImageCandidate.cityName;
    }

    public boolean selectImages(AtomicBoolean atomicBoolean) {
        HashMap<String, RecapImage> images = this.mTemplate.getImages();
        AtomicBoolean atomicBoolean2 = new AtomicBoolean(true);
        images.entrySet().stream().sorted(imageSelectionPriority()).forEach(new C0371f((Object) this, (Object) atomicBoolean, (Object) atomicBoolean2, 25));
        return atomicBoolean2.get();
    }
}
