package com.samsung.android.gallery.module.lottie.recap.template;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateQuarterBrief extends RecapTemplate {
    public RecapTemplateQuarterBrief() {
        super("recap_quarterly_brief.json", 3286);
        this.mDropFrames.add(1416);
        this.mDropFrames.add(1571);
    }

    public void buildScenes() {
        RecapTemplateScene c5 = C0280e.c("img_48.jpg", C0280e.c("img_47.jpg", C0280e.c("img_46.jpg", C0280e.c("img_45.jpg", C0280e.c("img_44.jpg", C0280e.c("img_43.jpg", new RecapTemplateScene(RecapTemplateScene.Type.OPENING, "opening")))))));
        RecapTextLayer recapTextLayer = new RecapTextLayer("s01_num_year_theme_wh");
        RecapDataVars recapDataVars = RecapDataVars.YEAR_NUM_YYYY;
        RecapTemplateScene b = C0280e.b("s01_num_year_theme_bl", recapDataVars, c5.addText(recapTextLayer.setVar(recapDataVars)));
        RecapTextLayer recapTextLayer2 = new RecapTextLayer("s01_txt_month_theme_wh");
        RecapDataVars recapDataVars2 = RecapDataVars.QUARTER_RANGE_MMM_MMM;
        RecapTemplateScene addBg = b.addText(recapTextLayer2.setVar(recapDataVars2).upperCase()).addText(new RecapTextLayer("s01_txt_month_theme_bl").setVar(recapDataVars2).upperCase()).addBg((RecapBgLayer) new RecapBgLayer("s00_background_theme03").dynamicColor(0.55f, 0.9f)).addBg((RecapBgLayer) new RecapBgLayer("s00_background_theme02").dynamicColor(0.55f, 0.9f)).addBg((RecapBgLayer) new RecapBgLayer("s00_background_theme01").dynamicColor(0.55f, 0.9f));
        RecapTemplateScene.Type type = RecapTemplateScene.Type.BUILD_UP;
        RecapTemplateScene addText = C0280e.c("img_42.jpg", C0280e.c("img_41.jpg", C0280e.c("img_40.jpg", C0280e.c("img_39.jpg", new RecapTemplateScene(type, "buildup1").addImage(new RecapImage("img_38.jpg").recycleBitmaps()))))).addText(new RecapTextLayer("s02_txt_header01").setStringId(R$string.recap_q_photos)).addText(new RecapTextLayer("s02_num01_QTY").setVar(RecapDataVars.IMAGES_COUNT)).addText(new RecapTextLayer("s02_txt_header02").setStringId(R$string.recap_q_videos)).addText(new RecapTextLayer("s02_num02_QTY").setVar(RecapDataVars.VIDEOS_COUNT));
        RecapTemplateScene addText2 = new RecapTemplateScene(type, "buildup2").addImage(new RecapImage("img_32.jpg", 504, 567).people().recycleBitmaps(500)).addImage(new RecapImage("img_33.jpg", 504, 567).people()).addImage(new RecapImage("img_34.jpg", 504, 567).people()).addImage(new RecapImage("img_35.jpg", 504, 567).people()).addImage(new RecapImage("img_36.jpg", 504, 567).people()).addImage(new RecapImage("img_37.jpg", 504, 567).people()).addText(new RecapTextLayer("s03_txt_header").setStringId(RecapTextLayer.Condition.PEOPLE_ONLY, R$string.story_category_people).setStringId(RecapTextLayer.Condition.PET_ONLY, R$string.story_category_pet).setStringId(R$string.visual_search_category_people_and_pets)).addText(new RecapTextLayer("s03_num01_QTY").setVar(RecapDataVars.PEOPLE_AND_PET_COUNT));
        RecapTemplateScene addText3 = new RecapTemplateScene(type, "buildup3").addImage(new RecapImage("img_29.jpg", 1080, 605).location().properScene().recycleBitmaps(500)).addImage(new RecapImage("img_30.jpg", 1080, 605).location().properScene()).addImage(new RecapImage("img_31.jpg", 1080, 605).location().properScene()).addText(new RecapTextLayer("s04_txt_header").setStringId(R$string.recap_places)).addText(new RecapTextLayer("s04_num_QTY").setVar(RecapDataVars.LOCATION_COUNT));
        RecapTemplateScene.Type type2 = RecapTemplateScene.Type.MAIN;
        RecapTemplateScene addImage = new RecapTemplateScene(type2, "main1").addImage(new RecapImage("img_21.jpg").recycleBitmaps(900));
        RecapTextLayer recapTextLayer3 = new RecapTextLayer("s05_num03_date");
        RecapDataVars recapDataVars3 = RecapDataVars.FILE_YYYY_MM_DD_NUM;
        RecapTemplateScene addText4 = addImage.addText(recapTextLayer3.setVar(recapDataVars3));
        RecapTemplateScene b5 = C0280e.b("s05_num02_date", recapDataVars3, C0280e.c("img_22.jpg", new RecapTemplateScene(type2, "main2")));
        RecapTemplateScene b8 = C0280e.b("s05_num01_date", recapDataVars3, C0280e.c("img_23.jpg", new RecapTemplateScene(type2, "main3")));
        RecapTemplateScene c6 = C0280e.c("img_28.jpg", C0280e.c("img_27.jpg", C0280e.c("img_26.jpg", C0280e.c("img_25.jpg", C0280e.c("img_24.jpg", new RecapTemplateScene(type2, "mainContents4"))))));
        RecapTemplateScene.Type type3 = RecapTemplateScene.Type.KEY_MOMENTS;
        RecapTemplateScene addText5 = new RecapTemplateScene(type3, "keyMoments1").addImage(new RecapImage("img_20.jpg").monthly(0).properPeople().recycleBitmaps(500)).addBg(new RecapBgLayer("s06_year01_bg_theme01").dynamicColorFromSingleImage(0.6f, 1.0f).brightFroGray(0.8f, 0.1f)).addBg(new RecapBgLayer("s06_date01_bg_theme01").dynamicColorFromSingleImage(0.5f, 0.85f).brightFroGray(0.85f, 0.1f)).addBg(new RecapBgLayer("s06_day01_bg_theme01").dynamicColorFromSingleImage(0.4f, 0.95f).brightFroGray(0.95f, 0.1f)).addText(new RecapTextLayer("s06_txt_title").setStringId(R$string.recap_q_best_moments));
        RecapTextLayer recapTextLayer4 = new RecapTextLayer("s06_txt_month01");
        RecapDataVars recapDataVars4 = RecapDataVars.FILE_MONTH_FULL_TEXT;
        RecapTemplateScene addText6 = addText5.addText(recapTextLayer4.setVar(recapDataVars4));
        RecapTextLayer recapTextLayer5 = new RecapTextLayer("s06_num_year01");
        RecapDataVars recapDataVars5 = RecapDataVars.FILE_YEAR_YYYY_NUM;
        RecapTemplateScene addText7 = addText6.addText(recapTextLayer5.setVar(recapDataVars5));
        RecapTextLayer recapTextLayer6 = new RecapTextLayer("s06_num_date01");
        RecapDataVars recapDataVars6 = RecapDataVars.FILE_MMDD_NUM;
        RecapTemplateScene addText8 = addText7.addText(recapTextLayer6.setVar(recapDataVars6));
        RecapTextLayer recapTextLayer7 = new RecapTextLayer("s06_num_day01");
        RecapDataVars recapDataVars7 = RecapDataVars.FILE_DAY_SIMPLE_ENG_TEXT;
        RecapTemplateScene addText9 = addText8.addText(recapTextLayer7.setVar(recapDataVars7));
        RecapTemplateScene recapTemplateScene = c6;
        RecapTemplateScene recapTemplateScene2 = b8;
        RecapTemplateScene recapTemplateScene3 = b5;
        RecapTemplateScene b10 = C0280e.b("s06_num_day02", recapDataVars7, C0280e.b("s06_num_date02", recapDataVars6, C0280e.b("s06_num_year02", recapDataVars5, C0280e.b("s06_txt_month02", recapDataVars4, new RecapTemplateScene(type3, "keyMoments2").addImage(new RecapImage("img_19.jpg").monthly(1).properPeople()).addBg(new RecapBgLayer("s06_date02_bg_theme02").dynamicColorFromSingleImage(0.5f, 0.85f).brightFroGray(0.85f, 0.1f)).addBg(new RecapBgLayer("s06_day02_bg_theme02").dynamicColorFromSingleImage(0.4f, 0.95f).brightFroGray(0.95f, 0.1f)).addBg(new RecapBgLayer("s06_year02_bg_theme02").dynamicColorFromSingleImage(0.6f, 1.0f).brightFroGray(0.8f, 0.1f))))));
        RecapTemplateScene b11 = C0280e.b("s06_num_day03", recapDataVars7, C0280e.b("s06_num_date03", recapDataVars6, C0280e.b("s06_num_year03", recapDataVars5, C0280e.b("s06_txt_month03", recapDataVars4, C0280e.h("img_17.jpg", new RecapTemplateScene(type3, "keyMoments3").addImage(new RecapImage("img_18.jpg").monthly(2).properPeople())).addBg(new RecapBgLayer("s06_date03_bg_theme03").dynamicColorFromSingleImage(0.5f, 0.85f).brightFroGray(0.85f, 0.1f)).addBg(new RecapBgLayer("s06_day03_bg_theme03").dynamicColorFromSingleImage(0.4f, 0.95f).brightFroGray(0.95f, 0.1f)).addBg(new RecapBgLayer("s06_year03_bg_theme03").dynamicColorFromSingleImage(0.6f, 1.0f).brightFroGray(0.8f, 0.1f))))));
        RecapTemplateScene addText10 = new RecapTemplateScene(RecapTemplateScene.Type.CLIMAX, "climax").addImage(new RecapImage("img_9.jpg").properZeroPeople()).addImage(new RecapImage("img_10.jpg").properZeroPeople()).addImage(new RecapImage("img_11.jpg").properZeroPeople()).addImage(new RecapImage("img_12.jpg").properPeople()).addImage(new RecapImage("img_13.jpg").properPeople()).addImage(new RecapImage("img_14.jpg").properZeroPeople()).addImage(new RecapImage("img_15.jpg").properZeroPeople()).addImage(new RecapImage("img_16.jpg").properZeroPeople().recycleBitmaps()).addText(new RecapTextLayer("s07_txt_date").setVar(RecapDataVars.QUARTER_RANGE_YYYY_N_MMM_MMM));
        RecapTemplateScene addText11 = C0280e.c("img_8.jpg", C0280e.c("img_7.jpg", C0280e.c("img_5.jpg", C0280e.c("img_4.jpg", C0280e.c("img_3.jpg", C0280e.c("img_2.jpg", C0280e.c("img_1.jpg", new RecapTemplateScene(RecapTemplateScene.Type.CLOSING, "closing").addImage(new RecapImage("img_0.jpg").recycleBitmaps())))))).addImage(new RecapImage("img_6.jpg").notBlackRight()))).addText(new RecapTextLayer("s08_num_year_date").setDefault(" ")).addText(new RecapTextLayer("s08_txt_title01").setStringId(R$string.recap_q_moments_to_remember));
        addScene(addBg);
        addScene(addText);
        addScene(addText2);
        addScene(addText3);
        addScene(addText4);
        addScene(recapTemplateScene3);
        addScene(recapTemplateScene2);
        addScene(recapTemplateScene);
        addScene(addText9);
        addScene(b10);
        addScene(b11);
        addScene(addText10);
        addScene(addText11);
    }

    public void setConfiguration(RecapTemplate.Configuration configuration) {
        configuration.EnableDynamicColor = true;
        configuration.RequirePeoples = true;
        configuration.RequireLocations = true;
    }
}
