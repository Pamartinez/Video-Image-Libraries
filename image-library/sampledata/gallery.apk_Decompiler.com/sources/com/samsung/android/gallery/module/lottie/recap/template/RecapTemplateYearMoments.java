package com.samsung.android.gallery.module.lottie.recap.template;

import android.graphics.Typeface;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.font.LottieFont;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.sum.core.message.Message;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateYearMoments extends RecapTemplate {
    public RecapTemplateYearMoments() {
        super("recap_yearly_moments.json", 3288);
        dropFrames();
    }

    private void dropFrames() {
        this.mDropFrames.add(Integer.valueOf(Message.RELEASE_GRAPH));
        this.mDropFrames.add(741);
        this.mDropFrames.add(Integer.valueOf(ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8));
    }

    public void buildScenes() {
        RecapTemplateScene addImage = C0280e.h("img_38.jpg", C0280e.h("img_37.jpg", C0280e.h("img_36.jpg", C0280e.c("img_32.jpg", C0280e.h("img_27.jpg", C0280e.h("img_26.jpg", new RecapTemplateScene(RecapTemplateScene.Type.OPENING, "opening"))).addImage(new RecapImage("img_28.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_29.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_30.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_31.jpg").useHeadGuard(230, 143))).addImage(new RecapImage("img_33.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_34.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_35.jpg").lowPriority())))).addImage(new RecapImage("img_39.jpg").lowPriority()).addImage(new RecapImage("img_40.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_41.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_42.jpg").lowPriority());
        RecapTextLayer recapTextLayer = new RecapTextLayer("s01_num_year");
        RecapDataVars recapDataVars = RecapDataVars.YEAR_NUM_YYYY;
        RecapTemplateScene addText = addImage.addText(recapTextLayer.setVar(recapDataVars)).addText(new RecapTextLayer("s01_txt_title").setStringId(R$string.recap_y_moments).upperCase());
        RecapImage.TargetCandidate targetCandidate = RecapImage.TargetCandidate.YEAR_TOP_ACTIVE_DAYS;
        RecapTemplateScene allImageType = addText.setAllImageType(targetCandidate);
        RecapTemplateScene allImageType2 = C0280e.c("img_44.jpg", new RecapTemplateScene(RecapTemplateScene.Type.BUILD_UP, "buildup").addImage(new RecapImage("img_43.jpg").dummy())).addImage(new RecapImage("img_45.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_46.jpg").useHeadGuard(230, 143)).addImage(new RecapImage("img_47.jpg").lowPriority()).addImage(new RecapImage("img_48.jpg").lowPriority()).addText(new RecapTextLayer("s01_minicard01_num_days").setVar(RecapDataVars.TOTAL_DAYS)).addText(new RecapTextLayer("s01_minicard01_txt_title").setStringId(R$string.recap_days)).addText(new RecapTextLayer("s01_minicard02_txt_title01").setVar(RecapDataVars.YEAR_CAPTURED_IN_YYYY)).setAllImageType(targetCandidate);
        RecapTemplateScene addText2 = C0280e.h("img_25.jpg", C0280e.h("img_24.jpg", C0280e.h("img_22.jpg", new RecapTemplateScene(RecapTemplateScene.Type.MAIN, "main").addImage(new RecapImage("img_20.jpg").highPriority()).addImage(new RecapImage("img_21.jpg").highPriority().recycleBitmaps()).addImage(new RecapImage("img_23.jpg").highPriority())))).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s02_num_year_01").setVar(recapDataVars).dynamicColor(0.6f, 0.8f)).targetImage(0));
        RecapTextLayer recapTextLayer2 = new RecapTextLayer("s02_txt_title_01");
        int i2 = R$string.recap_y_memories;
        RecapTemplateScene allImageType3 = addText2.addText((RecapTextLayer) ((RecapTextLayer) recapTextLayer2.setStringId(i2).upperCase().dynamicColor(0.6f, 0.5f)).targetImage(0)).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s02_num_year_02").setVar(recapDataVars).dynamicColor(0.6f, 0.8f)).targetImage(1)).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s02_txt_title_02").setStringId(i2).upperCase().dynamicColor(0.6f, 0.5f)).targetImage(1)).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s02_num_year_03").setVar(recapDataVars).dynamicColor(0.6f, 0.8f)).targetImage(2)).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s02_txt_title_03").setStringId(i2).upperCase().dynamicColor(0.6f, 0.5f)).targetImage(2)).setAllImageType(targetCandidate);
        RecapTemplateScene addText3 = new RecapTemplateScene(RecapTemplateScene.Type.KEY_MOMENTS, "keyMoments1").addImage(new RecapImage("img_17.jpg").highPriority().recycleBitmaps(500)).addImage(new RecapImage("img_18.jpg").highPriority()).addImage(new RecapImage("img_19.jpg").highPriority()).addText(new RecapTextLayer("s03_tag03_txt").engOnly()).addText(new RecapTextLayer("s03_tag02_txt").engOnly()).addText(new RecapTextLayer("s03_tag01_txt").engOnly()).addText(new RecapTextLayer("s03_num_date").setVar(RecapDataVars.MOST_ACTIVE_DAY_MMDD));
        RecapImage.TargetCandidate targetCandidate2 = RecapImage.TargetCandidate.YEAR_MOST_ACTIVE_DAY;
        RecapTemplateScene allImageType4 = addText3.setAllImageType(targetCandidate2);
        RecapTemplateScene addImage2 = new RecapTemplateScene(RecapTemplateScene.Type.CLIMAX, "climax").addImage(new RecapImage("img_15.jpg").highPriority()).addImage(new RecapImage("img_16.jpg").highPriority().recycleBitmaps());
        RecapTextLayer recapTextLayer3 = new RecapTextLayer("s04_txt01_highlight");
        int i7 = R$string.recap_highlights;
        RecapTextLayer sp = recapTextLayer3.setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60);
        Typeface typeface = LottieFont.sec_900_black_font;
        RecapTemplateScene allImageType5 = addImage2.addText(sp.font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt02_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt03_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt04_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt05_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt06_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt07_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt08_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt09_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addText(new RecapTextLayer("s04_txt10_highlight").setStringId(i7).upperCase().repeatInWidth(807.52d).sp(60).font(typeface).repeatWithSpace()).addBg((RecapBgLayer) new RecapBgLayer("s04_background_theme").dynamicColor(0.6f, 0.75f)).setAllImageType(targetCandidate2);
        RecapTemplateScene addText4 = C0280e.c("img_13.jpg", C0280e.c("img_9.jpg", C0280e.c("img_7.jpg", C0280e.c("img_5.jpg", C0280e.c("img_3.jpg", C0280e.c("img_1.jpg", C0280e.c("img_0.jpg", new RecapTemplateScene(RecapTemplateScene.Type.CLOSING, "closing")).addImage(new RecapImage("img_0.jpg").useHeadGuard(192, 118))).addImage(new RecapImage("img_2.jpg").useHeadGuard(192, 118))).addImage(new RecapImage("img_4.jpg").useHeadGuard(192, 118))).addImage(new RecapImage("img_6.jpg").recycleBitmaps(500))).addImage(new RecapImage("img_8.jpg").useHeadGuard(192, 118))).addImage(new RecapImage("img_10.jpg").useHeadGuard(192, 118)).addImage(new RecapImage("img_11.jpg").dummy()).addImage(new RecapImage("img_12.jpg").useHeadGuard(192, 118))).addImage(new RecapImage("img_14.jpg").useHeadGuard(192, 118)).addText(new RecapTextLayer("s05_minicard_txt_header").setStringId(R$string.recap_y_moments_closing_title)).addText(new RecapTextLayer("s05_minicard_num_year").setVar(recapDataVars));
        addScene(allImageType);
        addScene(allImageType2);
        addScene(allImageType3);
        addScene(allImageType4);
        addScene(allImageType5);
        addScene(addText4);
    }

    public void setConfiguration(RecapTemplate.Configuration configuration) {
        configuration.EnableDynamicColor = true;
        configuration.RequirePeoples = false;
        configuration.RequireLocations = false;
    }

    public RecapTemplateYearMoments(String str, int i2) {
        super(str, i2);
        dropFrames();
    }
}
