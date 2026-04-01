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
public class RecapTemplateYearBrief extends RecapTemplate {
    public RecapTemplateYearBrief() {
        super("recap_yearly_brief.json", 3287);
        this.mDropFrames.add(369);
    }

    public void buildScenes() {
        RecapTemplateScene addText = new RecapTemplateScene(RecapTemplateScene.Type.OPENING, "opening").addImage(new RecapImage("img_51.jpg").notWhiteVerticalMiddle()).addText(new RecapTextLayer("s01_txt_title").setVar(RecapDataVars.YEAR_NUM_YYYY_RECAP));
        RecapTemplateScene.Type type = RecapTemplateScene.Type.BUILD_UP;
        RecapTemplateScene recapTemplateScene = new RecapTemplateScene(type, "buildup1");
        RecapTextLayer recapTextLayer = new RecapTextLayer("s02_txt_header");
        RecapDataVars recapDataVars = RecapDataVars.YEAR_CAPTURED_IN_YYYY;
        RecapTemplateScene addText2 = recapTemplateScene.addText(recapTextLayer.setVar(recapDataVars));
        RecapTextLayer recapTextLayer2 = new RecapTextLayer("s02_txt_num_QTY01");
        RecapDataVars recapDataVars2 = RecapDataVars.IMAGES_COUNT;
        RecapTemplateScene addText3 = addText2.addText(recapTextLayer2.setVar(recapDataVars2));
        RecapTextLayer recapTextLayer3 = new RecapTextLayer("s02_txt_title01");
        int i2 = R$string.recap_q_photos;
        RecapTemplateScene addText4 = addText3.addText(recapTextLayer3.setStringId(i2));
        RecapTextLayer recapTextLayer4 = new RecapTextLayer("s02_txt_num_QTY02");
        RecapDataVars recapDataVars3 = RecapDataVars.VIDEOS_COUNT;
        RecapTemplateScene addText5 = addText4.addText(recapTextLayer4.setVar(recapDataVars3));
        RecapTextLayer recapTextLayer5 = new RecapTextLayer("s02_txt_title02");
        int i7 = R$string.recap_q_videos;
        RecapTemplateScene addText6 = C0280e.b("s02_txt_num_QTY04", recapDataVars3, C0280e.b("s02_txt_num_QTY03", recapDataVars2, addText5.addText(recapTextLayer5.setStringId(i7)).addText(new RecapTextLayer("s02_txt_title03").setStringId(i2))).addText(new RecapTextLayer("s02_txt_title04").setStringId(i7))).addText(new RecapTextLayer("s02_txt_num_QTY05").setVar(RecapDataVars.TOTAL_DAYS)).addText(new RecapTextLayer("s02_txt_title05").setStringId(R$string.recap_y_days_captured));
        RecapTextLayer recapTextLayer6 = new RecapTextLayer("s02_num04_QTY_theme01");
        RecapDataVars recapDataVars4 = RecapDataVars.FILES_COUNT;
        RecapTemplateScene b = C0280e.b("s02_num07_QTY_theme01", recapDataVars4, C0280e.b("s02_num06_QTY_theme01", recapDataVars4, C0280e.b("s02_num05_QTY_theme01", recapDataVars4, C0280e.b("s02_num03_QTY_theme01", recapDataVars4, C0280e.b("s02_num02_QTY_theme01", recapDataVars4, C0280e.b("s02_num01_QTY_theme01", recapDataVars4, addText6.addText(recapTextLayer6.setVar(recapDataVars4))))))));
        RecapTemplateScene addBg = C0280e.b("s03_txt_header", recapDataVars, new RecapTemplateScene(type, "buildup2").addImage(new RecapImage("img_48.jpg", 774, 774).singlePeople().recycleBitmaps()).addImage(new RecapImage("img_49.jpg", 540, 540).singlePeople()).addImage(new RecapImage("img_50.jpg", 630, 630).singlePeople())).addText(new RecapTextLayer("s03_img01_txt_name").setVar(RecapDataVars.FILE_0_PEOPLE_NAME).maxWidth(296).sp(20)).addText(new RecapTextLayer("s03_img02_txt_name").setVar(RecapDataVars.FILE_1_PEOPLE_NAME).maxWidth(296).sp(20)).addText(new RecapTextLayer("s03_img03_txt_name").setVar(RecapDataVars.FILE_2_PEOPLE_NAME).maxWidth(296).sp(20)).addText(new RecapTextLayer("s03_txt_title").setStringId(R$string.visual_search_category_people_and_pets)).addText(new RecapTextLayer("s03_num_QTY").setVar(RecapDataVars.PEOPLE_AND_PET_COUNT)).addBg((RecapBgLayer) new RecapBgLayer("s03_background").targetImage(0).dynamicColor(0.6f, 0.8f));
        RecapTemplateScene addBg2 = C0280e.b("s04_txt_header", recapDataVars, new RecapTemplateScene(type, "buildup3").addImage(new RecapImage("img_42.jpg", 540, 540).location().recycleBitmaps()).addImage(new RecapImage("img_43.jpg", 540, 540).location()).addImage(new RecapImage("img_44.jpg", 540, 540).location()).addImage(new RecapImage("img_45.jpg", 540, 540).location()).addImage(new RecapImage("img_46.jpg", 540, 540).location().notWhiteBottom()).addImage(new RecapImage("img_47.jpg", 540, 540).location()).addText(new RecapTextLayer("s04_num_QTY").setVar(RecapDataVars.LOCATION_COUNT)).addText(new RecapTextLayer("s04_txt_title").setStringId(R$string.recap_places))).addBg((RecapBgLayer) new RecapBgLayer("s04_background").targetImage(0).dynamicColor(0.6f, 0.8f));
        RecapTemplateScene c5 = C0280e.c("img_41.jpg", C0280e.c("img_40.jpg", C0280e.c("img_39.jpg", C0280e.c("img_38.jpg", C0280e.c("img_37.jpg", C0280e.c("img_36.jpg", C0280e.c("img_35.jpg", C0280e.c("img_34.jpg", C0280e.c("img_33.jpg", C0280e.c("img_32.jpg", C0280e.c("img_31.jpg", C0280e.c("img_30.jpg", C0280e.c("img_29.jpg", C0280e.c("img_28.jpg", C0280e.c("img_27.jpg", C0280e.c("img_26.jpg", C0280e.c("img_25.jpg", C0280e.c("img_24.jpg", C0280e.c("img_23.jpg", C0280e.c("img_22.jpg", C0280e.c("img_21.jpg", C0280e.c("img_20.jpg", C0280e.c("img_19.jpg", new RecapTemplateScene(RecapTemplateScene.Type.MAIN, "main").addImage(new RecapImage("img_18.jpg").recycleBitmaps()))))))))))))))))))))))));
        RecapTemplateScene.Type type2 = RecapTemplateScene.Type.KEY_MOMENTS;
        RecapTemplateScene addImage = new RecapTemplateScene(type2, "keyMoments1").addImage(new RecapImage("img_17.jpg").keyMoments().notWhiteBottom().recycleBitmaps(500));
        RecapTextLayer recapTextLayer7 = new RecapTextLayer("s06_txt05_date");
        RecapDataVars recapDataVars5 = RecapDataVars.FILE_MMDD_NUM;
        RecapTemplateScene addBg3 = C0280e.b("s06_txt01_date", recapDataVars5, C0280e.b("s06_txt02_date", recapDataVars5, C0280e.b("s06_txt03_date", recapDataVars5, C0280e.b("s06_txt04_date", recapDataVars5, addImage.addText(recapTextLayer7.setVar(recapDataVars5)))))).addText(new RecapTextLayer("s06_txt_header").setVar(RecapDataVars.BEST_MOMENTS_IN_YYYY)).addBg(new RecapBgLayer("s06_background_theme").dynamicColorFromSingleImage(0.6f, 0.8f));
        RecapTemplateScene addBg4 = C0280e.b("s07_txt01_date", recapDataVars5, C0280e.b("s07_txt02_date", recapDataVars5, C0280e.b("s07_txt03_date", recapDataVars5, C0280e.b("s07_txt04_date", recapDataVars5, C0280e.b("s07_txt05_date", recapDataVars5, new RecapTemplateScene(type2, "keyMoments2").addImage(new RecapImage("img_16.jpg").keyMoments().notWhiteBottom())))))).addBg(new RecapBgLayer("s07_background_theme").dynamicColorFromSingleImage(0.6f, 0.8f));
        RecapTemplateScene addBg5 = C0280e.b("s08_txt01_date", recapDataVars5, C0280e.b("s08_txt02_date", recapDataVars5, C0280e.b("s08_txt03_date", recapDataVars5, C0280e.b("s08_txt04_date", recapDataVars5, C0280e.b("s08_txt05_date", recapDataVars5, new RecapTemplateScene(type2, "keyMoments3").addImage(new RecapImage("img_15.jpg").keyMoments().notWhiteBottom())))))).addBg(new RecapBgLayer("s08_background_theme").dynamicColorFromSingleImage(0.6f, 0.8f));
        RecapTemplateScene.Type type3 = RecapTemplateScene.Type.CLIMAX;
        RecapTemplateScene addBg6 = C0280e.c("img_12.jpg", C0280e.c("img_14.jpg", new RecapTemplateScene(type3, "climax")).addImage(new RecapImage("img_13.jpg").recycleBitmaps(500))).addBg((RecapBgLayer) new RecapBgLayer("s09_background_theme01").targetImage(1).dynamicColor(0.6f, 0.9f));
        RecapTemplateScene addBg7 = C0280e.c("img_9.jpg", C0280e.c("img_10.jpg", C0280e.c("img_11.jpg", new RecapTemplateScene(type3, "climax2")))).addBg((RecapBgLayer) new RecapBgLayer("s09_background_theme02").targetImage(1).dynamicColor(0.6f, 0.9f));
        RecapTemplateScene addText7 = C0280e.c("img_8.jpg", C0280e.c("img_7.jpg", C0280e.c("img_6.jpg", C0280e.c("img_5.jpg", C0280e.c("img_4.jpg", C0280e.c("img_2.jpg", C0280e.c("img_1.jpg", C0280e.c("img_0.jpg", new RecapTemplateScene(RecapTemplateScene.Type.CLOSING, "closing")))).addImage(new RecapImage("img_3.jpg").recycleBitmaps(500))))))).addText(new RecapTextLayer("s10_closing").setVar(RecapDataVars.YEAR_SPOTLIGHT_ON_NUM_YYYY));
        addScene(addText);
        addScene(b);
        addScene(addBg);
        addScene(addBg2);
        addScene(c5);
        addScene(addBg3);
        addScene(addBg4);
        addScene(addBg5);
        addScene(addBg6);
        addScene(addBg7);
        addScene(addText7);
    }

    public void setConfiguration(RecapTemplate.Configuration configuration) {
        configuration.EnableDynamicColor = true;
        configuration.RequirePeoples = true;
        configuration.RequireLocations = true;
    }
}
