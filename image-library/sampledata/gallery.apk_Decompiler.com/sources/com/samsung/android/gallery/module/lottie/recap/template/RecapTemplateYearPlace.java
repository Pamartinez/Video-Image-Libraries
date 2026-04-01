package com.samsung.android.gallery.module.lottie.recap.template;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateYearPlace extends RecapTemplate {
    public RecapTemplateYearPlace() {
        super("recap_yearly_places.json", 3292);
        this.mDropFrames.add(765);
        this.mDropFrames.add(1453);
    }

    public void buildScenes() {
        RecapTemplateScene addText = new RecapTemplateScene(RecapTemplateScene.Type.OPENING, "opening").addImage(new RecapImage("img_16.jpg").location()).addImage(new RecapImage("img_17.jpg").location()).addImage(new RecapImage("img_18.jpg").location()).addImage(new RecapImage("img_19.jpg").location()).addImage(new RecapImage("img_20.jpg").location()).addImage(new RecapImage("img_21.jpg").location()).addImage(new RecapImage("img_22.jpg").location()).addImage(new RecapImage("img_23.jpg").location()).addText(new RecapTextLayer("s01_txt_title01_theme01").setVar(RecapDataVars.PLACES_IN_YYYY).upperCase());
        RecapTextLayer recapTextLayer = new RecapTextLayer("s01_tag01_txt_theme01");
        RecapDataVars recapDataVars = RecapDataVars.PLACE_NAME_MAX13;
        RecapTemplateScene addText2 = C0280e.b("s01_tag13_txt_theme01", recapDataVars, C0280e.b("s01_tag12_txt_theme01", recapDataVars, C0280e.b("s01_tag11_txt_theme01", recapDataVars, C0280e.b("s01_tag10_txt_theme01", recapDataVars, C0280e.b("s01_tag09_txt_theme01", recapDataVars, C0280e.b("s01_tag08_txt_theme01", recapDataVars, C0280e.b("s01_tag07_txt_theme01", recapDataVars, C0280e.b("s01_tag06_txt_theme01", recapDataVars, C0280e.b("s01_tag05_txt_theme01", recapDataVars, C0280e.b("s01_tag04_txt_theme01", recapDataVars, C0280e.b("s01_tag03_txt_theme01", recapDataVars, C0280e.b("s01_tag02_txt_theme01", recapDataVars, addText.addText(recapTextLayer.setVar(recapDataVars)))))))))))))).addBg(new RecapBgLayer("s01_tag01_bg_theme01")).addBg(new RecapBgLayer("s01_tag02_bg_theme01")).addBg(new RecapBgLayer("s01_tag03_bg_theme01")).addBg(new RecapBgLayer("s01_tag04_bg_theme01")).addBg(new RecapBgLayer("s01_tag05_bg_theme01")).addBg(new RecapBgLayer("s01_tag06_theme01")).addBg(new RecapBgLayer("s01_tag07_bg_theme01")).addBg(new RecapBgLayer("s01_tag08_bg_theme01")).addBg(new RecapBgLayer("s01_tag09_bg_theme01")).addBg(new RecapBgLayer("s01_tag10_bg_theme01")).addBg(new RecapBgLayer("s01_tag11_bg_theme01")).addBg(new RecapBgLayer("s01_tag12_bg_theme01")).addBg(new RecapBgLayer("s01_tag13_bg_theme01")).addBg(new RecapBgLayer("s01_bg_theme01")).addText(new RecapTextLayer("s02_num02_QTY_theme01").setVar(RecapDataVars.TOTAL_PLACE_COUNT_PLACES)).addText(new RecapTextLayer("s02_txt_Places_theme01").setStringId(R$string.recap_places));
        RecapTemplateScene recapTemplateScene = new RecapTemplateScene(RecapTemplateScene.Type.BUILD_UP, "buildup");
        RecapImage recapImage = new RecapImage("img_15.jpg");
        RecapImage.TargetCandidate targetCandidate = RecapImage.TargetCandidate.NEW_PLACE;
        RecapTemplateScene addBg = recapTemplateScene.addImage(recapImage.type(targetCandidate).properScene().notWhiteBottom().recycleBitmaps()).addImage(new RecapImage("img_14.jpg").type(targetCandidate).properScene().lowPriority()).addText(new RecapTextLayer("s03_num02_QTY_theme01").setVar(RecapDataVars.TOTAL_PLACE_COUNT)).addText(new RecapTextLayer("s03_txt_slash_theme01").engOnly()).addText(new RecapTextLayer("s03_vector_num_QTY").engOnly()).addText(new RecapTextLayer("s03_num01_QTY_theme01").setVar(RecapDataVars.NEW_LOCATION_COUNT)).addText(new RecapTextLayer("s03_txt_THE_theme01").engOnly()).addText(new RecapTextLayer("s03_tag_txt_theme01").engOnly()).addBg(new RecapBgLayer("s03_background_theme01"));
        RecapTemplateScene addImage = new RecapTemplateScene(RecapTemplateScene.Type.MAIN, "main").addImage(new RecapImage("img_12.jpg").type(targetCandidate).properScene().properPeople()).addImage(new RecapImage("img_9.jpg").type(targetCandidate).properScene().recycleBitmaps()).addImage(new RecapImage("img_13.jpg").type(targetCandidate).properScene().properPeople());
        RecapTextLayer recapTextLayer2 = new RecapTextLayer("s04_txt_title01_theme01");
        RecapDataVars recapDataVars2 = RecapDataVars.PLACE_NAME_CAPITAL;
        RecapTemplateScene addBg2 = C0280e.b("s04_txt_title16_theme01", recapDataVars2, C0280e.b("s04_txt_title15_theme01", recapDataVars2, C0280e.b("s04_txt_title14_theme01", recapDataVars2, C0280e.b("s04_txt_title13_theme01", recapDataVars2, C0280e.b("s04_txt_title12_theme01", recapDataVars2, C0280e.b("s04_txt_title11_theme01", recapDataVars2, C0280e.b("s04_txt_title10_theme01", recapDataVars2, C0280e.b("s04_txt_title09_theme01", recapDataVars2, C0280e.b("s04_txt_title08_theme01", recapDataVars2, C0280e.b("s04_txt_title07_theme01", recapDataVars2, C0280e.b("s04_txt_title06_theme01", recapDataVars2, C0280e.b("s04_txt_title05_theme01", recapDataVars2, C0280e.b("s04_txt_title04_theme01", recapDataVars2, C0280e.b("s04_txt_title03_theme01", recapDataVars2, C0280e.b("s04_txt_title02_theme01", recapDataVars2, addImage.addText(recapTextLayer2.setVar(recapDataVars2))))))))))))))))).addBg(new RecapBgLayer("s04_background_theme01"));
        RecapTemplateScene recapTemplateScene2 = new RecapTemplateScene(RecapTemplateScene.Type.KEY_MOMENTS, "keyMoments1");
        RecapImage recapImage2 = new RecapImage("img_11.jpg");
        RecapImage.TargetCandidate targetCandidate2 = RecapImage.TargetCandidate.BEST_PLACE;
        RecapTemplateScene addText3 = recapTemplateScene2.addImage(recapImage2.type(targetCandidate2).properScene().notWhiteBottom().recycleBitmaps()).addText(new RecapTextLayer("s05_txt_title01_theme01").engOnly()).addText(new RecapTextLayer("s05_tag_txt_theme01").engOnly()).addText(new RecapTextLayer("s05_txt_title02_theme01").engOnly()).addText(new RecapTextLayer("s05_txt_subtitle_theme01").setVar(RecapDataVars.VISITED_FOR_DD_IN_YYYY));
        RecapTextLayer recapTextLayer3 = new RecapTextLayer("s05_txt01_theme01");
        RecapDataVars recapDataVars3 = RecapDataVars.VISITED_DD_DAYS;
        RecapTemplateScene addBg3 = C0280e.b("s05_txt02_theme01", recapDataVars3, addText3.addText(recapTextLayer3.setVar(recapDataVars3))).addBg(new RecapBgLayer("s05_background_theme01"));
        RecapTemplateScene.Type type = RecapTemplateScene.Type.CLIMAX;
        RecapTemplateScene h5 = C0280e.h("img_10.jpg", C0280e.h("img_8.jpg", new RecapTemplateScene(type, "climax").addImage(new RecapImage("img_6.jpg").type(targetCandidate2).properScene().properPeople().recycleBitmaps(500))));
        RecapTextLayer recapTextLayer4 = new RecapTextLayer("s06_txt12_theme01");
        RecapDataVars recapDataVars4 = RecapDataVars.BEST_PLACE_NAME_CAPITAL;
        RecapTemplateScene addBg4 = C0280e.b("s06_txt01_theme01", recapDataVars4, C0280e.b("s06_txt02_theme01", recapDataVars4, C0280e.b("s06_txt03_theme01", recapDataVars4, C0280e.b("s06_txt04_theme01", recapDataVars4, C0280e.b("s06_txt05_theme01", recapDataVars4, C0280e.b("s06_txt06_theme01", recapDataVars4, C0280e.b("s06_txt07_theme01", recapDataVars4, C0280e.b("s06_txt08_theme01", recapDataVars4, C0280e.b("s06_txt09_theme01", recapDataVars4, C0280e.b("s06_txt10_theme01", recapDataVars4, C0280e.b("s06_txt11_theme01", recapDataVars4, h5.addText(recapTextLayer4.setVar(recapDataVars4))))))))))))).addBg(new RecapBgLayer("s06_background_theme01"));
        RecapTemplateScene b = C0280e.b("s06_txt13_theme01", recapDataVars4, C0280e.b("s06_txt14_theme01", recapDataVars4, C0280e.b("s06_txt15_theme01", recapDataVars4, C0280e.b("s06_txt16_theme01", recapDataVars4, C0280e.b("s06_txt17_theme01", recapDataVars4, C0280e.b("s06_txt18_theme01", recapDataVars4, C0280e.b("s06_txt19_theme01", recapDataVars4, C0280e.b("s06_txt20_theme01", recapDataVars4, C0280e.b("s06_txt21_theme01", recapDataVars4, C0280e.b("s06_txt22_theme01", recapDataVars4, C0280e.b("s06_txt23_theme01", recapDataVars4, C0280e.b("s06_txt24_theme01", recapDataVars4, new RecapTemplateScene(type, "climax2").addImage(new RecapImage("img_7.jpg").type(targetCandidate2).properScene().properPeople())))))))))))));
        RecapTemplateScene addBg5 = new RecapTemplateScene(RecapTemplateScene.Type.CLOSING, "closing").addImage(new RecapImage("img_0.jpg").properScene().hidden()).addImage(new RecapImage("img_1.jpg").properScene().lowPriority()).addImage(new RecapImage("img_2.jpg").properScene().hidden()).addImage(new RecapImage("img_3.jpg", 481, ErrorCodeConvertor.TEMP_AGENT_VOLLEY_PARSING).placeClosing()).addImage(new RecapImage("img_4.jpg", 481, ErrorCodeConvertor.TEMP_AGENT_VOLLEY_PARSING).placeClosing().recycleBitmaps().highPriority()).addImage(new RecapImage("img_5.jpg", 481, ErrorCodeConvertor.TEMP_AGENT_VOLLEY_PARSING).placeClosing().highPriority()).addText(new RecapTextLayer("s07_txt_title01_theme01").setStringId(R$string.recap_y_moments_closing_title).upperCase()).addText(new RecapTextLayer("s07_txt_year_theme01").setVar(RecapDataVars.YEAR_NUM_YYYY)).addBg(new RecapBgLayer("s07_background_theme01"));
        addScene(addText2);
        addScene(addBg);
        addScene(addBg2);
        addScene(addBg3);
        addScene(addBg4);
        addScene(b);
        addScene(addBg5);
    }

    public void setConfiguration(RecapTemplate.Configuration configuration) {
        configuration.EnableDynamicColor = false;
        configuration.RequirePeoples = false;
        configuration.RequireLocations = true;
    }
}
