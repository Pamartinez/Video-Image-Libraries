package com.samsung.android.gallery.module.lottie.recap.template;

import A9.c;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateStory extends RecapTemplateYearMoments {
    public RecapTemplateStory() {
        super("recap_yearly_moments.json", 3295);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$buildScenes$0(String str, RecapImage recapImage) {
        if (recapImage.sceneName.contains("opening") || recapImage.sceneName.contains("buildup") || recapImage.sceneName.contains("closing")) {
            recapImage.reuse();
        }
    }

    public void buildScenes() {
        super.buildScenes();
        RecapTextLayer clear = this.mTexts.get("s01_num_year").clear();
        RecapDataVars recapDataVars = RecapDataVars.STORY_TITLE;
        clear.setVar(recapDataVars).upperCase();
        this.mTexts.get("s01_txt_title").clear().setDefault(" ").upperCase();
        RecapTextLayer clear2 = this.mTexts.get("s01_minicard02_txt_title01").clear();
        RecapDataVars recapDataVars2 = RecapDataVars.STORY_DURATION;
        clear2.setVar(recapDataVars2);
        this.mTexts.get("s02_num_year_01").clear().setVar(recapDataVars2).upperCase();
        this.mTexts.get("s02_num_year_02").clear().setVar(recapDataVars2).upperCase();
        this.mTexts.get("s02_num_year_03").clear().setVar(recapDataVars2).upperCase();
        this.mTexts.get("s05_minicard_num_year").clear().setVar(recapDataVars).maxWidth(400).sp(42).upperCase();
        this.mImages.forEach(new c(13));
    }
}
