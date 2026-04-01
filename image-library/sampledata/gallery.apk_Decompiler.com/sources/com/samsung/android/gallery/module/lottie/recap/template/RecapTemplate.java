package com.samsung.android.gallery.module.lottie.recap.template;

import android.content.res.Resources;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.module.lottie.recap.template.functions.RecapTemplateColorChanger;
import com.samsung.android.gallery.support.utils.AppResources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;
import o4.a;
import t8.e;
import x0.C0332j;
import x0.l;
import x0.n;
import x0.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecapTemplate {
    private final String TAG = getClass().getCanonicalName();
    private final Configuration configuration;
    String mAssetName;
    HashMap<String, RecapBgLayer> mBackgrounds = new HashMap<>();
    public final HashSet<Integer> mDropFrames;
    ArrayList<RecapTemplateScene> mDynamicColorScene = new ArrayList<>();
    public HashMap<String, RecapTextLayer> mDynamicColorText = new HashMap<>();
    HashMap<String, RecapImage> mImages = new HashMap<>();
    String mJsonString;
    int mSaType;
    HashMap<String, RecapTextLayer> mTexts = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Configuration {
        protected boolean EnableDynamicColor = false;
        protected boolean RequireLocations = false;
        protected boolean RequirePeoples = false;
    }

    public RecapTemplate(String str, int i2) {
        Configuration configuration2 = new Configuration();
        this.configuration = configuration2;
        this.mDropFrames = new HashSet<>();
        this.mAssetName = str;
        this.mSaType = i2;
        this.mJsonString = getJsonAsset(str);
        setConfiguration(configuration2);
        buildScenes();
    }

    public static String getJsonAsset(String str) {
        InputStream open;
        Resources resources = AppResources.getAppContext().getResources();
        StringBuilder sb2 = new StringBuilder();
        try {
            open = resources.getAssets().open(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, StandardCharsets.UTF_8));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb2.append(readLine);
            }
            bufferedReader.close();
            if (open != null) {
                open.close();
            }
            return sb2.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(Consumer consumer, C0332j jVar) {
        updateTemplateImageResolutionsFromLottie(jVar);
        consumer.accept(jVar);
    }

    /* access modifiers changed from: private */
    public void lambda$updateTemplateImageResolutionsFromLottie$1(y yVar) {
        RecapImage recapImage = this.mImages.get(yVar.d.replace("png", "jpg"));
        if (recapImage != null) {
            recapImage.setResolution(yVar.f2099a, yVar.b);
            return;
        }
        throw new RuntimeException("fail set resolution : " + yVar.d);
    }

    private void updateTemplateImageResolutionsFromLottie(C0332j jVar) {
        ((HashMap) jVar.d()).values().forEach(new a(29, this));
    }

    public void addScene(RecapTemplateScene recapTemplateScene) {
        this.mImages.putAll(recapTemplateScene.mImages);
        this.mTexts.putAll(recapTemplateScene.mTexts);
        if (this.configuration.EnableDynamicColor) {
            if (!recapTemplateScene.mBackgrounds.isEmpty()) {
                this.mBackgrounds.putAll(recapTemplateScene.mBackgrounds);
                this.mDynamicColorScene.add(recapTemplateScene);
            }
            if (!recapTemplateScene.mDynamicText.isEmpty()) {
                if (!this.mDynamicColorScene.contains(recapTemplateScene)) {
                    this.mDynamicColorScene.add(recapTemplateScene);
                }
                this.mDynamicColorText.putAll(recapTemplateScene.mDynamicText);
            }
        }
    }

    public abstract void buildScenes();

    public ArrayList<RecapTemplateScene> getDynamicColorScenes() {
        return this.mDynamicColorScene;
    }

    public HashMap<String, RecapImage> getImages() {
        return this.mImages;
    }

    public HashMap<String, RecapTextLayer> getTexts() {
        return this.mTexts;
    }

    public boolean isValidLocations(int i2) {
        if (!this.configuration.RequireLocations || i2 > 0) {
            return true;
        }
        return false;
    }

    public boolean isValidPeoples(int i2) {
        if (!this.configuration.RequirePeoples || i2 > 0) {
            return true;
        }
        return false;
    }

    public void load(Consumer<C0332j> consumer) {
        n.a((String) null, new t0.a(3, this.mJsonString), (e) null).b(new l(2, this, consumer));
    }

    public void replaceDynamicColor() {
        this.mJsonString = new RecapTemplateColorChanger(this.mBackgrounds, this.mDynamicColorText).scan(this.mJsonString);
    }

    public abstract void setConfiguration(Configuration configuration2);
}
