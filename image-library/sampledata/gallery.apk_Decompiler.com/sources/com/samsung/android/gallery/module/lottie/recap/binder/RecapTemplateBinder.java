package com.samsung.android.gallery.module.lottie.recap.binder;

import B0.b;
import X9.a;
import a8.d;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.module.lottie.recap.data.RecapDataValidator;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.data.parser.RecapDataCompat;
import com.samsung.android.gallery.module.lottie.recap.font.LottieFont;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateMonthlyBrief;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateMonthlyMoments;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateQuarterBrief;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateStory;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateYearBrief;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateYearMoments;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateYearPeople;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplateYearPlace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import org.json.JSONArray;
import org.json.JSONObject;
import x0.C0325c;
import x0.C0332j;
import x0.H;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateBinder {
    private static final CharSequence TAG = "RecapTemplateBinder";
    AnalyzedData mAnalyzedData;
    C0325c mImageAssetDelegate;
    RecapImageSelector mImageSelector;
    RecapTemplate mTemplate;
    int mTemplateType;
    RecapTextDelegateImpl mTextDelegate;

    /* JADX INFO: finally extract failed */
    private boolean findTemplate() {
        RecapTemplate recapTemplate;
        String str = "null";
        try {
            int i2 = this.mTemplateType;
            if (i2 == 3292) {
                this.mTemplate = new RecapTemplateYearPlace();
            } else if (i2 == 90001) {
                this.mTemplate = new RecapTemplateStory();
            } else if (i2 == 3294) {
                this.mTemplate = new RecapTemplateMonthlyBrief();
            } else if (i2 != 3295) {
                switch (i2) {
                    case 3286:
                        this.mTemplate = new RecapTemplateQuarterBrief();
                        break;
                    case 3287:
                        this.mTemplate = new RecapTemplateYearBrief();
                        break;
                    case 3288:
                        this.mTemplate = new RecapTemplateYearMoments();
                        break;
                    case 3289:
                        this.mTemplate = new RecapTemplateYearPeople();
                        break;
                    default:
                        CharSequence charSequence = TAG;
                        Log.e(charSequence, "wrong type : " + this.mTemplateType);
                        if (this.mTemplateType != 0 ? (recapTemplate = this.mTemplate) != null : (recapTemplate = this.mTemplate) != null) {
                            str = recapTemplate.getClass().getSimpleName();
                        }
                        Log.i(charSequence, "findTemplate : ".concat(str));
                        return false;
                }
            } else {
                this.mTemplate = new RecapTemplateMonthlyMoments();
            }
            CharSequence charSequence2 = TAG;
            RecapTemplate recapTemplate2 = this.mTemplate;
            if (recapTemplate2 != null) {
                str = recapTemplate2.getClass().getSimpleName();
            }
            Log.i(charSequence2, "findTemplate : ".concat(str));
            return true;
        } catch (Throwable th) {
            CharSequence charSequence3 = TAG;
            RecapTemplate recapTemplate3 = this.mTemplate;
            if (recapTemplate3 != null) {
                str = recapTemplate3.getClass().getSimpleName();
            }
            Log.i(charSequence3, "findTemplate : ".concat(str));
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createLottie$2(AtomicBoolean atomicBoolean, BiConsumer biConsumer, C0332j jVar) {
        if (atomicBoolean.get()) {
            biConsumer.accept((Object) null, (Object) null);
        } else {
            returnDrawable(biConsumer, jVar);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createLottie$3(AtomicBoolean atomicBoolean, BiConsumer biConsumer, C0332j jVar) {
        C0325c cVar;
        boolean z;
        if (atomicBoolean.get()) {
            biConsumer.accept((Object) null, (Object) null);
            return;
        }
        RecapImageSelector recapImageSelector = new RecapImageSelector(this.mTemplate, this.mAnalyzedData);
        this.mImageSelector = recapImageSelector;
        if (!recapImageSelector.selectImages(atomicBoolean)) {
            biConsumer.accept((Object) null, "pick fail");
        } else if (atomicBoolean.get()) {
            biConsumer.accept((Object) null, (Object) null);
        } else {
            if (this.mAnalyzedData.fromPreview) {
                cVar = new RecapImageAssetDelegate(this.mTemplate.getImages());
            } else {
                cVar = new RecapImageAssetDelegateForRecorder(this.mTemplate.getImages());
            }
            this.mImageAssetDelegate = cVar;
            this.mTextDelegate = new RecapTextDelegateImpl(this.mTemplate.getTexts(), this.mAnalyzedData);
            if (!this.mTemplate.getDynamicColorScenes().isEmpty()) {
                new RecapColorPicker(this.mTemplate).findTargetBgColor();
                this.mTemplate.replaceDynamicColor();
                z = true;
            } else {
                z = false;
            }
            if (atomicBoolean.get()) {
                biConsumer.accept((Object) null, (Object) null);
            } else if (z) {
                this.mTemplate.load(new i(this, atomicBoolean, biConsumer, 1));
            } else {
                returnDrawable(biConsumer, jVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createLottie$4(AtomicBoolean atomicBoolean, BiConsumer biConsumer, C0332j jVar) {
        SimpleThreadPool.getInstance().execute(new d((Object) this, (Object) atomicBoolean, (Object) biConsumer, (Object) jVar, 7));
    }

    private void returnDrawable(BiConsumer<w, String> biConsumer, C0332j jVar) {
        w wVar = new w();
        wVar.n(jVar);
        wVar.setBounds(jVar.k);
        wVar.f2081A = H.HARDWARE;
        wVar.e();
        LottieFont.bind(wVar);
        C0325c cVar = this.mImageAssetDelegate;
        wVar.m = cVar;
        b bVar = wVar.k;
        if (bVar != null) {
            bVar.f36c = cVar;
        }
        wVar.q = new RecapTextDelegateAdapter(wVar, this.mTextDelegate);
        biConsumer.accept(wVar, (Object) null);
    }

    public void bindView(LottieAnimationView lottieAnimationView) {
        lottieAnimationView.setImageAssetDelegate(this.mImageAssetDelegate);
        lottieAnimationView.setTextDelegate(new RecapTextDelegateAdapter((w) lottieAnimationView.getDrawable(), this.mTextDelegate));
        LottieFont.bind(lottieAnimationView);
    }

    public void createLottie(BiConsumer<w, String> biConsumer, AtomicBoolean atomicBoolean) {
        this.mTemplate.load(new i(this, atomicBoolean, biConsumer, 0));
    }

    public long dataTimeStamp() {
        return this.mAnalyzedData.timestamp;
    }

    public String getName() {
        return this.mTemplate.getClass().getSimpleName();
    }

    public int getType() {
        return this.mAnalyzedData.type;
    }

    public JSONArray getUsedFileIds() {
        JSONArray jSONArray = new JSONArray();
        this.mTemplate.getImages().values().stream().forEach(new a(jSONArray, 2));
        return jSONArray;
    }

    public Boolean needFrameDrop(Integer num) {
        return Boolean.valueOf(this.mTemplate.mDropFrames.contains(num));
    }

    public boolean setData(JSONObject jSONObject) {
        AnalyzedData parse = RecapDataCompat.parse(jSONObject);
        this.mAnalyzedData = parse;
        if (parse == null) {
            return false;
        }
        this.mTemplateType = parse.getType();
        if (!findTemplate()) {
            return false;
        }
        return RecapDataValidator.isValid(this.mAnalyzedData, this.mTemplate);
    }
}
