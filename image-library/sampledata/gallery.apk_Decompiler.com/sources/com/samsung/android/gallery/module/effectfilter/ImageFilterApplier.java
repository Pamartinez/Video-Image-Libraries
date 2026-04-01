package com.samsung.android.gallery.module.effectfilter;

import A.a;
import U3.g;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import com.samsung.android.camera.filter.SemFilter;
import com.samsung.android.camera.filter.SemFilterBufferedProcessor;
import com.samsung.android.gallery.support.renderscript.RenderScriptCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageFilterApplier {
    public static final float[] DEFAULT_MATRIX = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    protected Filter mFilter = Filter.NONE;
    private FilterManager mFilterManager;
    protected int mIntensity = -1;
    private HashMap<Filter, RenderScriptCompat.LutInfo> mLutMap;

    /* access modifiers changed from: private */
    /* renamed from: applyInternal */
    public void lambda$apply$0(Bitmap bitmap, Filter filter, int i2, boolean z, BiConsumer<Bitmap, Filter> biConsumer) {
        Bitmap bitmap2;
        boolean z3;
        if (!getFilterManager().isAvailable()) {
            biConsumer.accept(bitmap, filter);
            return;
        }
        if (useColorMatrix(z, filter, i2)) {
            if (filter == Filter.BW) {
                z3 = true;
            } else {
                z3 = false;
            }
            bitmap2 = applyWithColorMatrixForGrey(bitmap, z3);
        } else if (useSemFilter(z)) {
            bitmap2 = applyWithSemFilter(bitmap, getFilterManager().getSemFilter(filter), i2);
        } else {
            bitmap2 = applyWithLut(bitmap, filter);
        }
        biConsumer.accept(bitmap2, filter);
    }

    private Bitmap applyWithColorMatrixForGrey(Bitmap bitmap, boolean z) {
        float f;
        ColorMatrix colorMatrix = new ColorMatrix(DEFAULT_MATRIX);
        if (z) {
            f = 0.4f;
        } else {
            f = 0.6f;
        }
        colorMatrix.setSaturation(f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    private Bitmap applyWithLut(Bitmap bitmap, Filter filter) {
        return RenderScriptCompat.getLutAppliedBitmap(bitmap, getLutInfo(filter));
    }

    private Bitmap applyWithSemFilter(Bitmap bitmap, SemFilter semFilter, int i2) {
        if (semFilter == null) {
            Log.e((CharSequence) "ImageFilterApplier", "SemFilter is not supported", this.mFilter);
            return bitmap;
        }
        try {
            SemFilterBufferedProcessor semFilterBufferedProcessor = new SemFilterBufferedProcessor();
            semFilterBufferedProcessor.initialize();
            semFilterBufferedProcessor.setFilter(semFilter);
            if (i2 >= 0 && i2 <= 100) {
                semFilterBufferedProcessor.setFilterParameter("intensity=" + i2);
            }
            Bitmap processImage = semFilterBufferedProcessor.processImage(bitmap, false);
            semFilterBufferedProcessor.release();
            return processImage;
        } catch (Exception e) {
            a.s(e, new StringBuilder("applyWithSemFilter failed="), "ImageFilterApplier");
            return bitmap;
        }
    }

    private FilterManager getFilterManager() {
        if (this.mFilterManager == null) {
            this.mFilterManager = FilterManager.getInstance();
        }
        return this.mFilterManager;
    }

    private int getIntensity(Filter filter, boolean z) {
        if (!z) {
            return 100;
        }
        int i2 = this.mIntensity;
        if (i2 != -1) {
            return i2;
        }
        return filter.getIntensity();
    }

    private synchronized HashMap<Filter, RenderScriptCompat.LutInfo> getLutMap() {
        try {
            if (this.mLutMap == null) {
                this.mLutMap = createLutMap();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mLutMap;
    }

    private boolean useColorMatrix(boolean z, Filter filter, int i2) {
        if (z || i2 >= 100) {
            return false;
        }
        if (filter == Filter.BW || filter == Filter.Grayscale) {
            return true;
        }
        return false;
    }

    public void apply(Bitmap bitmap, boolean z, BiConsumer<Bitmap, Filter> biConsumer) {
        Filter filter = this.mFilter;
        apply(bitmap, filter, getIntensity(filter, z), z, biConsumer);
    }

    public Bitmap applySync(Bitmap bitmap, Filter filter, int i2, boolean z) {
        boolean z3;
        if (filter.noneFilter() || !getFilterManager().isAvailable()) {
            return bitmap;
        }
        if (useColorMatrix(z, filter, i2)) {
            if (filter == Filter.BW) {
                z3 = true;
            } else {
                z3 = false;
            }
            return applyWithColorMatrixForGrey(bitmap, z3);
        } else if (useSemFilter(z)) {
            return applyWithSemFilter(bitmap, getFilterManager().getSemFilter(filter), i2);
        } else {
            return applyWithLut(bitmap, filter);
        }
    }

    public HashMap<Filter, RenderScriptCompat.LutInfo> createLutMap() {
        return new HashMap<>();
    }

    public String getFilterPath() {
        return getFilterManager().getFilterRawPath(this.mFilter);
    }

    public RenderScriptCompat.LutInfo getLutInfo(Filter filter) {
        RenderScriptCompat.LutInfo lutInfo;
        HashMap<Filter, RenderScriptCompat.LutInfo> lutMap = getLutMap();
        synchronized (lutMap) {
            try {
                lutInfo = lutMap.get(filter);
                if (lutInfo == null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    Bitmap decodeFile = BitmapFactory.decodeFile(getFilterManager().getFilterRawPath(filter));
                    int width = decodeFile.getWidth();
                    int height = decodeFile.getHeight();
                    int i2 = width * height;
                    int[] iArr = new int[i2];
                    decodeFile.getPixels(iArr, 0, width, 0, 0, width, height);
                    int[] iArr2 = new int[i2];
                    int i7 = 0;
                    for (int i8 = 0; i8 < 64; i8++) {
                        for (int i10 = 0; i10 < 64; i10++) {
                            int i11 = 0;
                            while (i11 < 64) {
                                iArr2[i7] = iArr[((i11 % 8) * 64) + i8 + ((((i11 / 8) * 64) + i10) * 512)];
                                i11++;
                                i7++;
                            }
                        }
                    }
                    lutInfo = new RenderScriptCompat.LutInfo(decodeFile, iArr2);
                    lutMap.put(filter, lutInfo);
                    Log.d("ImageFilterApplier", "makeLut", filter, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return lutInfo;
    }

    public void setImageFilter(Filter filter) {
        this.mFilter = filter;
        Log.d("ImageFilterApplier", "setImageFilter", filter);
    }

    public void apply(Bitmap bitmap, Filter filter, int i2, boolean z, BiConsumer<Bitmap, Filter> biConsumer) {
        if (filter.noneFilter()) {
            biConsumer.accept(bitmap, filter);
        } else if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new g(this, bitmap, filter, i2, z, (BiConsumer) biConsumer));
        } else {
            lambda$apply$0(bitmap, filter, i2, z, biConsumer);
        }
    }

    private boolean useSemFilter(boolean z) {
        return z;
    }
}
