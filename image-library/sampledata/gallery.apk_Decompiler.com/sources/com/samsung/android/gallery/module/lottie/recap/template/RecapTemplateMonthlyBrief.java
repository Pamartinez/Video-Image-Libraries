package com.samsung.android.gallery.module.lottie.recap.template;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.sdk.scs.base.StatusCodes;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateMonthlyBrief extends RecapTemplate {
    public RecapTemplateMonthlyBrief() {
        super("recap_monthly_brief.json", 3294);
        this.mDropFrames.add(369);
        this.mDropFrames.add(459);
    }

    public void buildScenes() {
        RecapTemplateScene addText = new RecapTemplateScene(RecapTemplateScene.Type.OPENING, "opening").addImage(new RecapImage("img_40.jpg").highPriority().notWhiteVerticalMiddle()).addText(new RecapTextLayer("s01_txt_title").setVar(RecapDataVars.MONTH_MMMM_RECAP));
        RecapTemplateScene addText2 = new RecapTemplateScene(RecapTemplateScene.Type.BUILD_UP, "buildup1").addText(new RecapTextLayer("s02_txt_header").setVar(RecapDataVars.MONTH_CAPTURED_IN_MMMM));
        RecapTextLayer recapTextLayer = new RecapTextLayer("s02_txt_num_QTY01");
        RecapDataVars recapDataVars = RecapDataVars.IMAGES_COUNT;
        RecapTemplateScene addText3 = addText2.addText(recapTextLayer.setVar(recapDataVars));
        RecapTextLayer recapTextLayer2 = new RecapTextLayer("s02_txt_title01");
        int i2 = R$string.recap_q_photos;
        RecapTemplateScene addText4 = addText3.addText(recapTextLayer2.setStringId(i2));
        RecapTextLayer recapTextLayer3 = new RecapTextLayer("s02_txt_num_QTY02");
        RecapDataVars recapDataVars2 = RecapDataVars.VIDEOS_COUNT;
        RecapTemplateScene addText5 = addText4.addText(recapTextLayer3.setVar(recapDataVars2));
        RecapTextLayer recapTextLayer4 = new RecapTextLayer("s02_txt_title02");
        int i7 = R$string.recap_q_videos;
        RecapTemplateScene addText6 = C0280e.b("s02_txt_num_QTY04", recapDataVars2, C0280e.b("s02_txt_num_QTY03", recapDataVars, addText5.addText(recapTextLayer4.setStringId(i7)).addText(new RecapTextLayer("s02_txt_title03").setStringId(i2))).addText(new RecapTextLayer("s02_txt_title04").setStringId(i7))).addText(new RecapTextLayer("s02_txt_title05").setStringId(R$string.recap_y_days_captured)).addText(new RecapTextLayer("s02_txt_num_QTY05").setVar(RecapDataVars.TOTAL_DAYS));
        RecapTextLayer recapTextLayer5 = new RecapTextLayer("s02_num04_QTY_theme01");
        RecapDataVars recapDataVars3 = RecapDataVars.FILES_COUNT;
        RecapTemplateScene b = C0280e.b("s02_num07_QTY_theme01", recapDataVars3, C0280e.b("s02_num06_QTY_theme01", recapDataVars3, C0280e.b("s02_num05_QTY_theme01", recapDataVars3, C0280e.b("s02_num03_QTY_theme01", recapDataVars3, C0280e.b("s02_num02_QTY_theme01", recapDataVars3, C0280e.b("s02_num01_QTY_theme01", recapDataVars3, addText6.addText(recapTextLayer5.setVar(recapDataVars3))))))));
        RecapTemplateScene h5 = C0280e.h("img_39.jpg", C0280e.c("img_38.jpg", C0280e.h("img_37.jpg", C0280e.h("img_35.jpg", C0280e.c("img_33.jpg", C0280e.h("img_32.jpg", C0280e.h("img_31.jpg", C0280e.c("img_28.jpg", C0280e.h("img_27.jpg", C0280e.c("img_26.jpg", C0280e.h("img_22.jpg", C0280e.c("img_21.jpg", new RecapTemplateScene(RecapTemplateScene.Type.MAIN, "main").addImage(new RecapImage("img_16.jpg").recycleBitmaps()).addImage(new RecapImage("img_17.jpg").lowPriority()).addImage(new RecapImage("img_18.jpg").dummy()).addImage(new RecapImage("img_19.jpg").dummy()).addImage(new RecapImage("img_20.jpg").highPriority()))).addImage(new RecapImage("img_23.jpg").dummy()).addImage(new RecapImage("img_24.jpg").highPriority()).addImage(new RecapImage("img_25.jpg").highPriority())))).addImage(new RecapImage("img_29.jpg").highPriority()).addImage(new RecapImage("img_30.jpg").highPriority())))).addImage(new RecapImage("img_34.jpg").highPriority())).addImage(new RecapImage("img_36.jpg").dummy()))));
        RecapTemplateScene addImage = new RecapTemplateScene(RecapTemplateScene.Type.KEY_MOMENTS, "keyMoments1").addImage(new RecapImage("img_15.jpg").keyMoments().notWhiteBottom().recycleBitmaps(StatusCodes.INPUT_MISSING));
        RecapTextLayer recapTextLayer6 = new RecapTextLayer("s04_txt05_date");
        RecapDataVars recapDataVars4 = RecapDataVars.FILE_MMDD_NUM;
        RecapTemplateScene addBg = C0280e.b("s04_txt01_date", recapDataVars4, C0280e.b("s04_txt02_date", recapDataVars4, C0280e.b("s04_txt03_date", recapDataVars4, C0280e.b("s04_txt04_date", recapDataVars4, addImage.addText(recapTextLayer6.setVar(recapDataVars4)))))).addText(new RecapTextLayer("s04_txt_header 2").setVar(RecapDataVars.BEST_MOMENTS_IN_MMMM)).addBg(new RecapBgLayer("s04_background_theme01").dynamicColorFromSingleImage(0.6f, 0.8f));
        RecapTemplateScene.Type type = RecapTemplateScene.Type.CLIMAX;
        RecapTemplateScene addBg2 = C0280e.c("img_13.jpg", C0280e.c("img_14.jpg", new RecapTemplateScene(type, "climax"))).addImage(new RecapImage("img_12.jpg").recycleBitmaps()).addBg((RecapBgLayer) new RecapBgLayer("s05_background_theme01").targetImage(1).dynamicColor(0.6f, 0.9f));
        RecapTemplateScene addBg3 = C0280e.c("img_9.jpg", C0280e.c("img_10.jpg", C0280e.c("img_11.jpg", new RecapTemplateScene(type, "climax2")))).addBg((RecapBgLayer) new RecapBgLayer("s05_background_theme02").targetImage(1).dynamicColor(0.6f, 0.9f));
        RecapTemplateScene addText7 = C0280e.c("img_8.jpg", C0280e.c("img_7.jpg", C0280e.c("img_6.jpg", C0280e.c("img_5.jpg", C0280e.c("img_4.jpg", C0280e.c("img_1.jpg", new RecapTemplateScene(RecapTemplateScene.Type.CLOSING, "closing").addImage(new RecapImage("img_0.jpg").recycleBitmaps())).addImage(new RecapImage("img_2.jpg").lowPriority()).addImage(new RecapImage("img_3.jpg").lowPriority())))))).addText(new RecapTextLayer("s06_closing").setVar(RecapDataVars.MONTH_SPOTLIGHT_ON_MMMM));
        addScene(addText);
        addScene(b);
        addScene(h5);
        addScene(addBg);
        addScene(addBg2);
        addScene(addBg3);
        addScene(addText7);
    }

    public void setConfiguration(RecapTemplate.Configuration configuration) {
        configuration.EnableDynamicColor = true;
        configuration.RequirePeoples = true;
        configuration.RequireLocations = true;
    }
}
