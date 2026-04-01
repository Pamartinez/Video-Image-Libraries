package com.samsung.android.gallery.module.lottie.recap.template;

import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateMonthlyMoments extends RecapTemplateYearMoments {
    public RecapTemplateMonthlyMoments() {
        super("recap_yearly_moments.json", 3295);
    }

    public void buildScenes() {
        super.buildScenes();
        RecapDataVars recapDataVars = RecapDataVars.FROM_MMMM;
        this.mTexts.get("s01_num_year").setVar(recapDataVars).upperCase();
        this.mTexts.get("s01_minicard02_txt_title01").setVar(RecapDataVars.MONTH_CAPTURED_IN_MMMM);
        this.mTexts.get("s02_num_year_01").setVar(recapDataVars).upperCase();
        this.mTexts.get("s02_num_year_02").setVar(recapDataVars).upperCase();
        this.mTexts.get("s02_num_year_03").setVar(recapDataVars).upperCase();
        this.mTexts.get("s05_minicard_num_year").setVar(recapDataVars).upperCase();
    }
}
