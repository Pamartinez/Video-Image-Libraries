package com.samsung.android.gallery.module.lottie.recap.template;

import android.graphics.Typeface;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.font.LottieFont;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.sdk.scs.base.StatusCodes;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateYearPeople extends RecapTemplate {
    public RecapTemplateYearPeople() {
        super("recap_yearly_people.json", 3289);
        this.mDropFrames.add(226);
        this.mDropFrames.add(476);
        this.mDropFrames.add(1105);
    }

    public void buildScenes() {
        RecapTemplateScene.Type type = RecapTemplateScene.Type.OPENING;
        RecapTemplateScene addImage = new RecapTemplateScene(type, "opening").addImage(new RecapImage("img_27.jpg").people()).addImage(new RecapImage("img_28.jpg").people()).addImage(new RecapImage("img_29.jpg").people());
        RecapTextLayer recapTextLayer = new RecapTextLayer("s01_num_year");
        RecapDataVars recapDataVars = RecapDataVars.YEAR_NUM_YYYY;
        RecapTemplateScene addText = addImage.addText((RecapTextLayer) recapTextLayer.setVar(recapDataVars).targetFile("img_25.jpg").dynamicColor(0.7f, 0.55f)).addText(new RecapTextLayer("s01_txt_title01").engOnly()).addText(new RecapTextLayer("s01_txt_title02").engOnly()).addText(new RecapTextLayer("s01_txt_title03").engOnly());
        RecapTextLayer recapTextLayer2 = new RecapTextLayer("s01_txt_bgText04");
        int i2 = R$string.visual_search_category_people_and_pets;
        RecapTextLayer repeatInWidth = recapTextLayer2.setStringId(i2).repeatInWidth(5840.0d);
        Typeface typeface = LottieFont.sec_900_black_font;
        RecapTemplateScene addBg = addText.addText(repeatInWidth.font(typeface).sp(84)).addText(new RecapTextLayer("s01_txt_bgText03").setStringId(i2).repeatInWidth(5840.0d).font(typeface).sp(84)).addText(new RecapTextLayer("s01_txt_bgText02").setStringId(i2).repeatInWidth(5840.0d).font(typeface).sp(84)).addText(new RecapTextLayer("s01_txt_bgText01").setStringId(i2).repeatInWidth(5840.0d).font(typeface).sp(84)).addBg((RecapBgLayer) new RecapBgLayer("s01_background_theme").targetImage("img_25.jpg").dynamicColor(0.7f, 0.9f)).addBg((RecapBgLayer) new RecapBgLayer("s02_background_theme").targetImage("img_24.jpg").dynamicColor(0.7f, 0.9f));
        RecapTemplateScene addText2 = C0280e.h("img_26.jpg", new RecapTemplateScene(type, "opening2").addImage(new RecapImage("img_25.jpg").people().notWhiteBottom().recycleBitmaps()).addImage(new RecapImage("img_24.jpg").people().notWhiteBottom())).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s02_num_year").setVar(recapDataVars).targetImage(0)).dynamicColor(0.7f, 0.55f)).addText(new RecapTextLayer("s02_txt_title01").engOnly()).addText(new RecapTextLayer("s02_txt_title02").engOnly()).addText(new RecapTextLayer("s02_txt_title03").engOnly()).addText(new RecapTextLayer("s02_txt_bgText04").setStringId(i2).repeatInWidth(2080.0d).font(typeface).sp(84)).addText(new RecapTextLayer("s02_txt_bgText03").setStringId(i2).repeatInWidth(2080.0d).font(typeface).sp(84)).addText(new RecapTextLayer("s02_txt_bgText02").setStringId(i2).repeatInWidth(2080.0d).font(typeface).sp(84)).addText(new RecapTextLayer("s02_txt_bgText01").setStringId(i2).repeatInWidth(2080.0d).font(typeface).sp(84));
        RecapTemplateScene addImage2 = new RecapTemplateScene(RecapTemplateScene.Type.BUILD_UP, "buildup").addImage(new RecapImage("img_22.jpg").people().recycleBitmaps(100)).addImage(new RecapImage("img_23.jpg").people());
        RecapTextLayer recapTextLayer3 = new RecapTextLayer("s03_txt_title01");
        int i7 = R$string.recap_the_moments;
        RecapTemplateScene addText3 = addImage2.addText(recapTextLayer3.setStringId(i7).upperCase()).addText(new RecapTextLayer("s03_txt_title01_shadow").setStringId(i7).upperCase());
        RecapTextLayer recapTextLayer4 = new RecapTextLayer("s03_txt_title02_theme");
        int i8 = R$string.recap_together;
        RecapTemplateScene addBg2 = addText3.addText((RecapTextLayer) ((RecapTextLayer) recapTextLayer4.setStringId(i8).upperCase().targetImage(0)).dynamicColor(0.6f, 0.95f)).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s03_txt_title03_theme").setStringId(i8).upperCase().targetImage(0)).dynamicColor(0.6f, 0.8f)).addText((RecapTextLayer) ((RecapTextLayer) new RecapTextLayer("s03_txt_title04_theme").setStringId(i8).upperCase().targetImage(0)).dynamicColor(0.6f, 0.7f)).addBg((RecapBgLayer) new RecapBgLayer("s03_background_theme").targetImage(0).dynamicColor(0.6f, 0.8f));
        RecapTemplateScene recapTemplateScene = new RecapTemplateScene(RecapTemplateScene.Type.MAIN, "main");
        RecapTextLayer sp = new RecapTextLayer("s04_txt_title01").setLongTextLayer("s04_txt_title01_multiLang", Math.round(329.59998f), 4).font(typeface).sp(53);
        int i10 = R$string.recap_people_main_title;
        RecapTemplateScene addText4 = recapTemplateScene.addText(sp.setStringId(i10).upperCase()).addText(new RecapTextLayer("s04_txt_bgText01").setStringId(i10).upperCase()).addText(new RecapTextLayer("s04_txt_bgText02").setStringId(i10).upperCase()).addText(new RecapTextLayer("s04_txt_bgText03").setStringId(i10).upperCase()).addText(new RecapTextLayer("s04_txt_bgText04").setStringId(i10).upperCase());
        RecapTemplateScene recapTemplateScene2 = new RecapTemplateScene(RecapTemplateScene.Type.KEY_MOMENTS, "keyMoments1");
        RecapImage recapImage = new RecapImage("img_19.jpg");
        RecapImage.TargetCandidate targetCandidate = RecapImage.TargetCandidate.TOP_3_FACE;
        RecapTemplateScene addText5 = recapTemplateScene2.addImage(recapImage.type(targetCandidate).recycleBitmaps()).addImage(new RecapImage("img_20.jpg").type(targetCandidate)).addImage(new RecapImage("img_21.jpg").type(targetCandidate)).addText(new RecapTextLayer("s05_txt_title01").setStringId(R$string.first));
        RecapTextLayer recapTextLayer5 = new RecapTextLayer("s05_txt_subtitle01");
        RecapDataVars recapDataVars2 = RecapDataVars.FILE_0_PEOPLE_NAME;
        RecapTemplateScene addBg3 = addText5.addText(recapTextLayer5.setVar(recapDataVars2).maxWidth(StatusCodes.INPUT_MISSING).sp(38)).addText(new RecapTextLayer("s05_txt_title02").setStringId(R$string.second)).addText(new RecapTextLayer("s05_txt_subtitle02").setVar(RecapDataVars.FILE_1_PEOPLE_NAME).maxWidth(StatusCodes.INPUT_MISSING).sp(38)).addText(new RecapTextLayer("s05_txt_title03").setStringId(R$string.third)).addText(new RecapTextLayer("s05_txt_subtitle03").setVar(RecapDataVars.FILE_2_PEOPLE_NAME).maxWidth(StatusCodes.INPUT_MISSING).sp(38)).addBg((RecapBgLayer) new RecapBgLayer("s05_txt_title01_bg_theme").targetImage(0).dynamicColor(0.6f, 0.8f)).addBg((RecapBgLayer) new RecapBgLayer("s05_txt_title02_bg_theme").targetImage(1).dynamicColor(0.6f, 0.8f)).addBg((RecapBgLayer) new RecapBgLayer("s05_txt_title03_bg_theme").targetImage(2).dynamicColor(0.6f, 0.8f));
        RecapTemplateScene recapTemplateScene3 = new RecapTemplateScene(RecapTemplateScene.Type.CLIMAX, "climax");
        RecapImage recapImage2 = new RecapImage("img_12.jpg");
        RecapImage.TargetCandidate targetCandidate2 = RecapImage.TargetCandidate.WITH_TOP_3_FACE;
        RecapTemplateScene h5 = C0280e.h("img_18.jpg", C0280e.h("img_17.jpg", recapTemplateScene3.addImage(recapImage2.type(targetCandidate2).lowPriority().recycleBitmaps(500)).addImage(new RecapImage("img_13.jpg").type(targetCandidate2).lowPriority()).addImage(new RecapImage("img_14.jpg").type(targetCandidate2)).addImage(new RecapImage("img_15.jpg").type(targetCandidate2)).addImage(new RecapImage("img_16.jpg").type(targetCandidate2))));
        RecapTemplateScene.Type type2 = RecapTemplateScene.Type.CLOSING;
        RecapTemplateScene addText6 = new RecapTemplateScene(type2, "closing1").addImage(new RecapImage("img_11.jpg").type(RecapImage.TargetCandidate.KEY_MOMENTS).notWhiteTop().recycleBitmaps()).addText(new RecapTextLayer("s07_txt_title01").setStringId(R$string.recap_y_best_moments)).addText(new RecapTextLayer("s07_txt_title03").setVar(RecapDataVars.WITH_FILE_0_PEOPLE_NAME).maxWidth(150).sp(30).useFirstNameOnly()).addText(new RecapTextLayer("s07_txt_bgText01").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase()).addText(new RecapTextLayer("s07_txt_bgText02").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase()).addText(new RecapTextLayer("s07_txt_bgText03").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase()).addText(new RecapTextLayer("s07_txt_bgText04").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase()).addText(new RecapTextLayer("s07_txt_bgText05").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase()).addText(new RecapTextLayer("s07_txt_bgText06").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase()).addText(new RecapTextLayer("s07_txt_bgText07").setVar(recapDataVars2).maxWidth(337).sp(106).useFirstNameOnly().noEllipsis().upperCase());
        RecapTemplateScene addImage3 = new RecapTemplateScene(type2, "closing2").addImage(new RecapImage("img_0.jpg").properPeople()).addImage(new RecapImage("img_1.jpg").properPeople().recycleBitmaps(StatusCodes.INPUT_MISSING)).addImage(new RecapImage("img_2.jpg").properPeople()).addImage(new RecapImage("img_3.jpg").properPeople()).addImage(new RecapImage("img_4.jpg").properPeople()).addImage(new RecapImage("img_5.jpg").properPeople().lowPriority()).addImage(new RecapImage("img_6.jpg").properPeople()).addImage(new RecapImage("img_7.jpg").properPeople()).addImage(new RecapImage("img_8.jpg").properPeople()).addImage(new RecapImage("img_9.jpg").properPeople()).addImage(new RecapImage("img_10.jpg").properPeople());
        RecapTextLayer recapTextLayer6 = new RecapTextLayer("s08_txt_bgText01");
        int i11 = R$string.recap_y_memories;
        RecapTemplateScene addText7 = addImage3.addText(recapTextLayer6.setStringId(i11).repeatInWidth(2380.0d).font(typeface).sp(84)).addText(new RecapTextLayer("s08_txt_bgText02").setStringId(i11).repeatInWidth(2380.0d).font(typeface).sp(84));
        addScene(addBg);
        addScene(addText2);
        addScene(addBg2);
        addScene(addText4);
        addScene(addBg3);
        addScene(h5);
        addScene(addText6);
        addScene(addText7);
    }

    public void setConfiguration(RecapTemplate.Configuration configuration) {
        configuration.EnableDynamicColor = true;
        configuration.RequirePeoples = true;
        configuration.RequireLocations = false;
    }
}
