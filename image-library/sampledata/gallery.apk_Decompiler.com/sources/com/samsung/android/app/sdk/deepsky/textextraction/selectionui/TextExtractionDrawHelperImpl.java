package com.samsung.android.app.sdk.deepsky.textextraction.selectionui;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelperImpl;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LogUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode.TextExtractionActionModeHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeDialogListener;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.ImageInfo;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.HandleController;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.DragAndDropHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.KeyEventHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.MagnifierHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.VibrationHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.renderer.BackgroundRenderer;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.renderer.EntityRenderer;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.renderer.SelectionRenderer;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressDetector;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressListener;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.LanguageChangeListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslationTokenInfo;
import com.samsung.android.app.sdk.deepsky.textextraction.util.OcrUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000Î\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 É\u00012\u00020\u0001:\u0002É\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010 \u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0015H\u0016¢\u0006\u0004\b \u0010\u001bJ\u0017\u0010#\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\bH\u0016¢\u0006\u0004\b%\u0010\u001dJ\u0017\u0010(\u001a\u00020\b2\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\b2\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b*\u0010)J\u0017\u0010-\u001a\u00020\b2\u0006\u0010,\u001a\u00020+H\u0016¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\bH\u0016¢\u0006\u0004\b/\u0010\u001dJ\u0017\u00100\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0015H\u0016¢\u0006\u0004\b0\u0010\u001bJ\u001d\u00104\u001a\u00020\b2\f\u00103\u001a\b\u0012\u0004\u0012\u00020201H\u0016¢\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020\b2\u0006\u00107\u001a\u000206H\u0016¢\u0006\u0004\b8\u00109J\u0017\u0010:\u001a\u00020\b2\u0006\u00107\u001a\u000206H\u0016¢\u0006\u0004\b:\u00109J\u0017\u0010=\u001a\u00020\b2\u0006\u0010<\u001a\u00020;H\u0016¢\u0006\u0004\b=\u0010>J\u000f\u0010?\u001a\u00020\u0015H\u0016¢\u0006\u0004\b?\u0010\u0017J'\u0010C\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020\u0015H\u0016¢\u0006\u0004\bC\u0010DJ\u001f\u0010E\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\u000bH\u0016¢\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\bH\u0016¢\u0006\u0004\bG\u0010\u001dJ\u0017\u0010H\u001a\u00020\b2\u0006\u0010<\u001a\u00020;H\u0016¢\u0006\u0004\bH\u0010>J\u000f\u0010I\u001a\u00020\bH\u0016¢\u0006\u0004\bI\u0010\u001dJ\u000f\u0010J\u001a\u00020\bH\u0016¢\u0006\u0004\bJ\u0010\u001dJ\u0011\u0010L\u001a\u0004\u0018\u00010KH\u0016¢\u0006\u0004\bL\u0010MJ\u0011\u0010O\u001a\u0004\u0018\u00010NH\u0016¢\u0006\u0004\bO\u0010PJ\u0017\u0010S\u001a\u00020\b2\u0006\u0010R\u001a\u00020QH\u0016¢\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\bH\u0016¢\u0006\u0004\bU\u0010\u001dJ\u001f\u0010U\u001a\u00020\b2\u0006\u0010W\u001a\u00020V2\u0006\u0010R\u001a\u00020XH\u0017¢\u0006\u0004\bU\u0010YJ\u0017\u0010\\\u001a\u00020\b2\u0006\u0010[\u001a\u00020ZH\u0017¢\u0006\u0004\b\\\u0010]J\u0017\u0010`\u001a\u00020\b2\u0006\u0010_\u001a\u00020^H\u0017¢\u0006\u0004\b`\u0010aJ\u000f\u0010b\u001a\u00020\u0015H\u0002¢\u0006\u0004\bb\u0010\u0017J\u000f\u0010c\u001a\u00020\u0015H\u0002¢\u0006\u0004\bc\u0010\u0017J\u000f\u0010d\u001a\u00020\bH\u0002¢\u0006\u0004\bd\u0010\u001dJ\u0019\u0010e\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0004\be\u0010.J\u0019\u0010f\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0004\bf\u0010.J\u000f\u0010g\u001a\u00020\bH\u0002¢\u0006\u0004\bg\u0010\u001dJ\u0017\u0010j\u001a\u00020\b2\u0006\u0010i\u001a\u00020hH\u0002¢\u0006\u0004\bj\u0010kJ\u000f\u0010l\u001a\u00020\bH\u0002¢\u0006\u0004\bl\u0010\u001dJ\u0017\u0010m\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020!H\u0002¢\u0006\u0004\bm\u0010$J\u001f\u0010n\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000fH\u0002¢\u0006\u0004\bn\u0010oJ\u0017\u0010p\u001a\u00020\b2\u0006\u0010B\u001a\u00020\u0015H\u0002¢\u0006\u0004\bp\u0010\u001bJ'\u0010q\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u0015H\u0002¢\u0006\u0004\bq\u0010rJ\u000f\u0010s\u001a\u00020\u0015H\u0002¢\u0006\u0004\bs\u0010\u0017J-\u0010w\u001a\u00020\b2\u0006\u0010'\u001a\u00020&2\n\b\u0002\u0010u\u001a\u0004\u0018\u00010t2\b\b\u0002\u0010v\u001a\u00020\u000bH\u0002¢\u0006\u0004\bw\u0010xJ'\u0010y\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u0015H\u0002¢\u0006\u0004\by\u0010rJ\u000f\u0010z\u001a\u00020\bH\u0002¢\u0006\u0004\bz\u0010\u001dJ\u000f\u0010{\u001a\u00020\bH\u0002¢\u0006\u0004\b{\u0010\u001dJ\u000f\u0010|\u001a\u00020\u0015H\u0002¢\u0006\u0004\b|\u0010\u0017J\u0015\u0010}\u001a\b\u0012\u0004\u0012\u00020t01H\u0002¢\u0006\u0004\b}\u0010~J\u001b\u0010\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020H\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b\u0001\u0010\u001dR\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010\u0001R\u0019\u0010\u0001\u001a\u00020\u00068\u0002@\u0002X.¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0019\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\b,\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0019\u0010\u0001\u001a\u00020\u00158\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0019\u0010\u0001\u001a\u00020\u00158\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\b\u0010\u0010\u0001R\u0017\u0010\u0013\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\b\u0013\u0010\u0001R\u001a\u0010\u0001\u001a\u00030\u00018\u0002@\u0002X.¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u001a\u0010\u0001\u001a\u00030\u00018\u0002@\u0002X.¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010 \u0001R\u0018\u0010¢\u0001\u001a\u00030¡\u00018\u0002X\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010£\u0001R\u0018\u0010¥\u0001\u001a\u00030¤\u00018\u0002X\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R\u0018\u0010¨\u0001\u001a\u00030§\u00018\u0002X\u0004¢\u0006\b\n\u0006\b¨\u0001\u0010©\u0001R\u0018\u0010«\u0001\u001a\u00030ª\u00018\u0002X\u0004¢\u0006\b\n\u0006\b«\u0001\u0010¬\u0001R\u0018\u0010®\u0001\u001a\u00030­\u00018\u0002X\u0004¢\u0006\b\n\u0006\b®\u0001\u0010¯\u0001R\u0018\u0010±\u0001\u001a\u00030°\u00018\u0002X\u0004¢\u0006\b\n\u0006\b±\u0001\u0010²\u0001R\u0018\u0010´\u0001\u001a\u00030³\u00018\u0002X\u0004¢\u0006\b\n\u0006\b´\u0001\u0010µ\u0001R\u0018\u0010·\u0001\u001a\u00030¶\u00018\u0002X\u0004¢\u0006\b\n\u0006\b·\u0001\u0010¸\u0001R\u001c\u0010º\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bº\u0001\u0010»\u0001R\u001c\u0010½\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b½\u0001\u0010¾\u0001R\u001b\u0010¿\u0001\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b¿\u0001\u0010À\u0001R\u001b\u0010Á\u0001\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u001a\u0010Ä\u0001\u001a\u00030Ã\u00018\u0002@\u0002X.¢\u0006\b\n\u0006\bÄ\u0001\u0010Å\u0001R\u001a\u0010Ç\u0001\u001a\u00030Æ\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÇ\u0001\u0010È\u0001¨\u0006Ê\u0001"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelperImpl;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/View;", "view", "Lme/x;", "init", "(Landroid/view/View;)V", "", "scale", "setScaleFactor", "(F)V", "", "scaleState", "setOnScaleState", "(I)V", "translationState", "setOnTranslationState", "", "isBarcodeSelected", "()Z", "isTextSelected", "enabled", "setMagnifierEnabled", "(Z)V", "selectAll", "()V", "hapticFeedback", "visible", "setUnderlineVisible", "Landroid/view/MotionEvent;", "event", "handleTouchEvent", "(Landroid/view/MotionEvent;)Z", "onConfigurationChanged", "Landroid/graphics/Canvas;", "canvas", "drawSelection", "(Landroid/graphics/Canvas;)V", "drawTranslation", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "textData", "setTextData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;)V", "clearAllSelection", "setDimEnabled", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "barcodeList", "setBarcodeList", "(Ljava/util/List;)V", "Landroid/graphics/Bitmap;", "bitmap", "setBitmap", "(Landroid/graphics/Bitmap;)V", "setBarcodeBitmap", "Landroid/graphics/RectF;", "rect", "setBitmapRect", "(Landroid/graphics/RectF;)V", "startTextSelectionByButton", "x", "y", "isLongPress", "startTextSelectionWithCoordinate", "(FFZ)Z", "startBarcodeSelection", "(FF)Z", "finishTextSelection", "updateTextSelection", "dismissPopupMenu", "showPopupMenu", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper;", "getCapsuleHelper", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "getTranslateLanguageHelper", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ProgressBarCallback;", "callback", "setProgressBarCallback", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ProgressBarCallback;)V", "startImageTranslation", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslationTokenInfo;", "tokenInfo", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ImageTranslationResultCallback;", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslationTokenInfo;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ImageTranslationResultCallback;)V", "Landroid/view/ViewGroup;", "layout", "initCapsuleLayout", "(Landroid/view/ViewGroup;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$TranslateCapsuleClickListener;", "listener", "setTranslateClickListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$TranslateCapsuleClickListener;)V", "isTalkBackEnabled", "isImageTranslationOn", "initInternal", "updateTextExtraction", "updateTextExtractionData", "updateTextExtractionDrawInfo", "", "contentDescription", "setContentDescription", "(Ljava/lang/String;)V", "updateTextExtractionWithNewRect", "handleTouchEventCommon", "handleLongPress", "(II)Z", "updateLongPressState", "canStartNewTextSelection", "(IIZ)Z", "canDrawImageTranslation", "Landroid/graphics/Rect;", "boundary", "radius", "drawSelectionInternal", "(Landroid/graphics/Canvas;Landroid/graphics/Rect;F)V", "startNewTextSelection", "initTextSelection", "initImageInfo", "canShowPopupMenu", "getVisibleCharactersRectList", "()Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;", "languageLayoutState", "doImageTranslate", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;)V", "setTranslateCapsuleDelegate", "Landroid/content/Context;", "teView", "Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/ImageInfo;", "imageInfo", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/ImageInfo;", "isImageScale", "Z", "isShowPopupEnabled", "I", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextExtractionActionModeHelper;", "actionModeHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextExtractionActionModeHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/MagnifierHelper;", "magnifierHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/MagnifierHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/KeyEventHelper;", "keyEventHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/KeyEventHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;", "textClassificationHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper;", "vibrationHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeHelper;", "barcodeHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeHelper;", "Landroid/view/accessibility/AccessibilityManager;", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper;", "textSelectionHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/HandleController;", "handleController", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/HandleController;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressDetector;", "longPressDetector", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressDetector;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/SelectionRenderer;", "selectionRenderer", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/SelectionRenderer;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/EntityRenderer;", "entityRenderer", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/EntityRenderer;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/BackgroundRenderer;", "backgroundRenderer", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/BackgroundRenderer;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "langPackManager", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "imageTranslator", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl;", "capsuleHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl;", "translateLangHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "progressBarCallback", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper$ProgressBarCallback;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/DragAndDropHelper;", "dragAndDropHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/DragAndDropHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/TextExtractionState;", "textExtractionState", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/TextExtractionState;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionDrawHelperImpl implements TextExtractionDrawHelper {
    public static final Companion Companion = new Companion((e) null);
    private final AccessibilityManager accessibilityManager;
    private TextExtractionActionModeHelper actionModeHelper;
    private final BackgroundRenderer backgroundRenderer;
    private final BarcodeHelper barcodeHelper;
    /* access modifiers changed from: private */
    public CapsuleHelperImpl capsuleHelper;
    private final Context context;
    private DragAndDropHelper dragAndDropHelper;
    private final EntityRenderer entityRenderer;
    private final HandleController handleController;
    private final ImageInfo imageInfo = new ImageInfo((Bitmap) null, (RectF) null, 0, 0, 0.0f, 0, 0, 0.0f, (Point) null, 0.0f, 1023, (e) null);
    /* access modifiers changed from: private */
    public ImageTranslator imageTranslator;
    private boolean isImageScale = true;
    private boolean isShowPopupEnabled = true;
    private final KeyEventHelper keyEventHelper;
    /* access modifiers changed from: private */
    public final LangPackManager langPackManager;
    private final LongPressDetector longPressDetector;
    private MagnifierHelper magnifierHelper;
    /* access modifiers changed from: private */
    public TextExtractionDrawHelper.ProgressBarCallback progressBarCallback;
    private int scaleState;
    private final SelectionRenderer selectionRenderer;
    /* access modifiers changed from: private */
    public View teView;
    private final TextClassificationHelper textClassificationHelper;
    private TextData textData;
    /* access modifiers changed from: private */
    public TextExtractionState textExtractionState;
    /* access modifiers changed from: private */
    public final TextSelectionHelper textSelectionHelper;
    /* access modifiers changed from: private */
    public TranslateLanguageHelper translateLangHelper;
    private int translationState;
    private final VibrationHelper vibrationHelper;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
    @C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl$4", f = "TextExtractionDrawHelperImpl.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass4 extends i implements c {
        int label;
        final /* synthetic */ TextExtractionDrawHelperImpl this$0;

        {
            this.this$0 = r1;
        }

        public final C1227c create(Object obj, C1227c cVar) {
            return new AnonymousClass4(this.this$0, cVar);
        }

        public final Object invoke(A a7, C1227c cVar) {
            return ((AnonymousClass4) create(a7, cVar)).invokeSuspend(x.f4917a);
        }

        public final Object invokeSuspend(Object obj) {
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                a.A(obj);
                this.this$0.langPackManager.init();
                return x.f4917a;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelperImpl$Companion;", "", "<init>", "()V", "TAG", "", "SCALE_STATE_NONE", "", "SCALE_STATE_PROGRESS", "TRANSLATION_STATE_NONE", "TRANSLATION_STATE_PROGRESS", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            /*
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState.START_BY_BUTTON     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState.START_BY_LONG_PRESS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState.IMAGE_TRANSLATION     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState.IDLE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl.WhenMappings.<clinit>():void");
        }
    }

    public TextExtractionDrawHelperImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        this.keyEventHelper = new KeyEventHelper(context2);
        this.textClassificationHelper = new TextClassificationHelper(context2);
        VibrationHelper vibrationHelper2 = new VibrationHelper(context2);
        this.vibrationHelper = vibrationHelper2;
        TextSelectionHelper textSelectionHelper2 = new TextSelectionHelper(vibrationHelper2);
        this.textSelectionHelper = textSelectionHelper2;
        this.handleController = new HandleController(context2, textSelectionHelper2);
        this.selectionRenderer = new SelectionRenderer(context2);
        this.entityRenderer = new EntityRenderer(context2);
        this.backgroundRenderer = new BackgroundRenderer(context2);
        LangPackManager langPackManager2 = new LangPackManager(context2);
        this.langPackManager = langPackManager2;
        this.textExtractionState = TextExtractionState.IDLE;
        this.barcodeHelper = new BarcodeHelper(context2, new BarcodeDialogListener(this) {
            final /* synthetic */ TextExtractionDrawHelperImpl this$0;

            {
                this.this$0 = r1;
            }

            public void onDismiss() {
                this.this$0.clearAllSelection();
                View access$getTeView$p = this.this$0.teView;
                if (access$getTeView$p != null) {
                    access$getTeView$p.invalidate();
                } else {
                    j.k("teView");
                    throw null;
                }
            }
        });
        Object systemService = context2.getSystemService("accessibility");
        j.c(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        this.accessibilityManager = (AccessibilityManager) systemService;
        Context context3 = context2;
        this.longPressDetector = new LongPressDetector(context3, 0, 0, new LongPressListener(this) {
            final /* synthetic */ TextExtractionDrawHelperImpl this$0;

            {
                this.this$0 = r1;
            }

            public void onLongPress(int i2, int i7) {
                boolean unused = this.this$0.handleLongPress(i2, i7);
            }

            public void onLongPressCancelled() {
                ImageTranslator access$getImageTranslator$p = this.this$0.imageTranslator;
                if (access$getImageTranslator$p != null) {
                    access$getImageTranslator$p.setIgnoreLastTouchUpEvent(true);
                }
            }
        }, 6, (e) null);
        if (Rune.INSTANCE.isSupportCapsule()) {
            ImageTranslator imageTranslator2 = new ImageTranslator(context3, new TextTranslator(context3, langPackManager2));
            this.imageTranslator = imageTranslator2;
            this.capsuleHelper = new CapsuleHelperImpl(context3);
            this.translateLangHelper = new TranslateLanguageHelperImpl(context3, imageTranslator2, langPackManager2, new LanguageChangeListener(this) {
                final /* synthetic */ TextExtractionDrawHelperImpl this$0;

                {
                    this.this$0 = r1;
                }

                public void onLanguageChanged(boolean z) {
                    if (z) {
                        TextExtractionDrawHelper.ProgressBarCallback access$getProgressBarCallback$p = this.this$0.progressBarCallback;
                        if (access$getProgressBarCallback$p != null) {
                            access$getProgressBarCallback$p.startProgressBar();
                        }
                        this.this$0.doImageTranslate(LayoutState.LANGUAGE_CHANGE_LAYOUT);
                    }
                }
            });
            setTranslateCapsuleDelegate();
        }
        D.n(D.a(M.f3843a), (C0886x) null, (C) null, new AnonymousClass4(this, (C1227c) null), 3);
    }

    private final boolean canDrawImageTranslation() {
        ImageTranslator imageTranslator2;
        if (this.textExtractionState != TextExtractionState.IMAGE_TRANSLATION || (imageTranslator2 = this.imageTranslator) == null || imageTranslator2.isLongPress()) {
            return false;
        }
        return true;
    }

    private final boolean canShowPopupMenu() {
        if (!this.isShowPopupEnabled || this.scaleState == 1 || !isTextSelected() || isImageTranslationOn()) {
            return false;
        }
        return true;
    }

    private final boolean canStartNewTextSelection(int i2, int i7, boolean z) {
        SelectableCharacter findTouchedCharacter;
        if (isTalkBackEnabled()) {
            LibLogger.e("TextExtractionDrawHelperImpl", "text selection is not supported on talkback mode!");
            return false;
        } else if (this.scaleState == 1 || this.translationState == 1) {
            LibLogger.e("TextExtractionDrawHelperImpl", "text selection is not supported during scaling or translating!");
            return false;
        } else if (this.textExtractionState == TextExtractionState.START_BY_LONG_PRESS && isTextSelected()) {
            LibLogger.e("TextExtractionDrawHelperImpl", "reselection is not allowed in long press mode!");
            return false;
        } else if (this.handleController.isHandleMoving()) {
            return false;
        } else {
            if (!z || (findTouchedCharacter = this.textSelectionHelper.findTouchedCharacter(new Point(i2, i7))) == null || !findTouchedCharacter.isSelected()) {
                return true;
            }
            LibLogger.w("TextExtractionDrawHelperImpl", "ignore long press on touched character");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final void doImageTranslate(LayoutState layoutState) {
        ImageTranslator imageTranslator2 = this.imageTranslator;
        if (imageTranslator2 != null) {
            imageTranslator2.doImageTranslate(new TextExtractionDrawHelperImpl$doImageTranslate$1$1(this, layoutState, imageTranslator2));
        }
    }

    private final void drawSelectionInternal(Canvas canvas, Rect rect, float f) {
        TextExtractionState textExtractionState2 = this.textExtractionState;
        if (textExtractionState2 == TextExtractionState.IDLE) {
            LibLogger.w("TextExtractionDrawHelperImpl", "drawSelection called with invalid state(" + textExtractionState2 + ")");
        } else if (canDrawImageTranslation()) {
            this.backgroundRenderer.drawTranslationBackground(canvas, this.imageInfo.getImageRect());
            drawTranslation(canvas);
        } else {
            this.backgroundRenderer.drawSelectionBackground(canvas);
            this.entityRenderer.drawEntities(canvas, this.textExtractionState, this.textSelectionHelper.getSelectableOcrResult().getSelectableEntities());
            Canvas canvas2 = canvas;
            this.selectionRenderer.drawSelection(canvas2, this.textSelectionHelper.getSelectedCharacters(), this.barcodeHelper.getSelectedBarcodes(), rect, f);
            this.handleController.drawHandles(canvas2);
        }
    }

    public static /* synthetic */ void drawSelectionInternal$default(TextExtractionDrawHelperImpl textExtractionDrawHelperImpl, Canvas canvas, Rect rect, float f, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            rect = null;
        }
        if ((i2 & 4) != 0) {
            f = 0.0f;
        }
        textExtractionDrawHelperImpl.drawSelectionInternal(canvas, rect, f);
    }

    private final List<Rect> getVisibleCharactersRectList() {
        Rect rect;
        Rect rect2 = new Rect();
        int[] iArr = new int[2];
        View view = this.teView;
        if (view != null) {
            view.getGlobalVisibleRect(rect2);
            View view2 = this.teView;
            if (view2 != null) {
                view2.getLocationInWindow(iArr);
                Point point = new Point(iArr[0], iArr[1]);
                ArrayList arrayList = new ArrayList();
                for (SelectableCharacter poly : this.textSelectionHelper.getSelectedCharacters()) {
                    Point[] transformedPoly = PointUtil.INSTANCE.getTransformedPoly(poly.getPoly(), this.imageInfo.getScaleFactor(), point);
                    int length = transformedPoly.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            rect = null;
                            break;
                        }
                        Point point2 = transformedPoly[i2];
                        if (rect2.contains(point2.x, point2.y)) {
                            rect = PointUtil.INSTANCE.polyToRect(transformedPoly);
                            break;
                        }
                        i2++;
                    }
                    if (rect != null) {
                        arrayList.add(rect);
                    }
                }
                return arrayList;
            }
            j.k("teView");
            throw null;
        }
        j.k("teView");
        throw null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleLongPress(int r5, int r6) {
        /*
            r4 = this;
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r0 = r4.textExtractionState
            int[] r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x0020
            r3 = 2
            if (r0 == r3) goto L_0x0020
            r5 = 3
            if (r0 == r5) goto L_0x0015
            goto L_0x0051
        L_0x0015:
            r4.hapticFeedback()
            com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator r5 = r4.imageTranslator
            if (r5 == 0) goto L_0x0052
            r5.setLongPress(r2)
            goto L_0x0052
        L_0x0020:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r0 = r4.textSelectionHelper
            android.graphics.Point r3 = new android.graphics.Point
            r3.<init>(r5, r6)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter r5 = r0.findTouchedCharacter(r3)
            if (r5 == 0) goto L_0x0051
            boolean r5 = r5.isSelected()
            if (r5 != r2) goto L_0x0051
            java.lang.String r5 = "TextExtractionDrawHelperImpl"
            java.lang.String r6 = "drag and drop started"
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r5, r6)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r5 = r4.textSelectionHelper
            java.lang.String r5 = r5.getSelectedText()
            r4.clearAllSelection()
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.DragAndDropHelper r6 = r4.dragAndDropHelper
            if (r6 == 0) goto L_0x004b
            r6.startDragAndDrop(r5)
            goto L_0x0051
        L_0x004b:
            java.lang.String r4 = "dragAndDropHelper"
            kotlin.jvm.internal.j.k(r4)
            throw r1
        L_0x0051:
            r2 = 0
        L_0x0052:
            if (r2 == 0) goto L_0x0063
            android.view.View r4 = r4.teView
            if (r4 == 0) goto L_0x005c
            r4.invalidate()
            return r2
        L_0x005c:
            java.lang.String r4 = "teView"
            kotlin.jvm.internal.j.k(r4)
            throw r1
        L_0x0063:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl.handleLongPress(int, int):boolean");
    }

    private final boolean handleTouchEventCommon(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            dismissPopupMenu();
            MagnifierHelper magnifierHelper2 = this.magnifierHelper;
            if (magnifierHelper2 != null) {
                magnifierHelper2.dismiss();
                return false;
            }
            j.k("magnifierHelper");
            throw null;
        } else if (actionMasked == 1) {
            updateLongPressState(false);
            MagnifierHelper magnifierHelper3 = this.magnifierHelper;
            if (magnifierHelper3 != null) {
                magnifierHelper3.dismiss();
                showPopupMenu();
                return true;
            }
            j.k("magnifierHelper");
            throw null;
        } else if (actionMasked != 2 || !isTextSelected() || (!this.handleController.isHandleMoving() && !this.handleController.isLongPress())) {
            return false;
        } else {
            MagnifierHelper magnifierHelper4 = this.magnifierHelper;
            if (magnifierHelper4 != null) {
                magnifierHelper4.setShowing(true);
                return true;
            }
            j.k("magnifierHelper");
            throw null;
        }
    }

    private final void initImageInfo() {
        float f;
        View view = this.teView;
        if (view != null) {
            int width = view.getWidth();
            View view2 = this.teView;
            if (view2 != null) {
                int height = view2.getHeight();
                Rect rect = new Rect();
                View view3 = this.teView;
                if (view3 != null) {
                    view3.getGlobalVisibleRect(rect);
                    ImageInfo imageInfo2 = this.imageInfo;
                    float f5 = 1.0f;
                    if (this.isImageScale) {
                        f = ((float) width) / ((float) imageInfo2.getImageWidth());
                        float imageHeight = ((float) height) / ((float) imageInfo2.getImageHeight());
                        if (f > imageHeight) {
                            f = imageHeight;
                        }
                    } else {
                        f = 1.0f;
                    }
                    imageInfo2.setImageRatio(f);
                    if (this.isImageScale) {
                        float barcodeImageWidth = ((float) width) / ((float) imageInfo2.getBarcodeImageWidth());
                        float barcodeImageHeight = ((float) height) / ((float) imageInfo2.getBarcodeImageHeight());
                        if (barcodeImageWidth > barcodeImageHeight) {
                            f5 = barcodeImageHeight;
                        } else {
                            f5 = barcodeImageWidth;
                        }
                    }
                    imageInfo2.setBarcodeImageRatio(f5);
                    imageInfo2.setCenterOffset(new Point((int) imageInfo2.getImageRect().left, (int) imageInfo2.getImageRect().top));
                    HandleController handleController2 = this.handleController;
                    View view4 = this.teView;
                    if (view4 != null) {
                        handleController2.setImageInfo(view4);
                    } else {
                        j.k("teView");
                        throw null;
                    }
                } else {
                    j.k("teView");
                    throw null;
                }
            } else {
                j.k("teView");
                throw null;
            }
        } else {
            j.k("teView");
            throw null;
        }
    }

    private final void initInternal() {
        View view = this.teView;
        if (view != null) {
            view.setWillNotDraw(false);
            View view2 = this.teView;
            if (view2 != null) {
                view2.setLayerType(2, (Paint) null);
            } else {
                j.k("teView");
                throw null;
            }
        } else {
            j.k("teView");
            throw null;
        }
    }

    private final void initTextSelection() {
        Bitmap bitmap;
        if (this.textExtractionState != TextExtractionState.IDLE) {
            LibLogger.w("TextExtractionDrawHelperImpl", "initTextSelection called with wrong state");
            return;
        }
        initImageInfo();
        clearAllSelection();
        updateTextExtraction(this.textData);
        TextData textData2 = this.textData;
        if (textData2 != null && Rune.INSTANCE.isSupportCapsule() && (bitmap = this.imageInfo.getBitmap()) != null) {
            ImageTranslator imageTranslator2 = this.imageTranslator;
            if (imageTranslator2 != null) {
                imageTranslator2.init(bitmap, textData2.getOcrData());
            }
            D.n(D.a(M.f3843a), (C0886x) null, (C) null, new TextExtractionDrawHelperImpl$initTextSelection$1$1$1(this, textData2, (C1227c) null), 3);
        }
    }

    private final boolean isImageTranslationOn() {
        if (this.textExtractionState == TextExtractionState.IMAGE_TRANSLATION) {
            return true;
        }
        return false;
    }

    private final boolean isTalkBackEnabled() {
        if (!this.accessibilityManager.isEnabled() || !this.accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final void setContentDescription(String str) {
        View view = this.teView;
        if (view != null) {
            view.setContentDescription(str);
        } else {
            j.k("teView");
            throw null;
        }
    }

    private final void setTranslateCapsuleDelegate() {
        CapsuleHelperImpl capsuleHelperImpl = this.capsuleHelper;
        if (capsuleHelperImpl != null) {
            capsuleHelperImpl.setTranslateCapsuleDelegate(new TextExtractionDrawHelperImpl$setTranslateCapsuleDelegate$1(this));
        }
    }

    private final boolean startNewTextSelection(int i2, int i7, boolean z) {
        if (!canStartNewTextSelection(i2, i7, z)) {
            return false;
        }
        boolean startNewTextSelection = this.textSelectionHelper.startNewTextSelection(i2, i7);
        if (startNewTextSelection) {
            if (z) {
                hapticFeedback();
                MagnifierHelper magnifierHelper2 = this.magnifierHelper;
                if (magnifierHelper2 != null) {
                    magnifierHelper2.setShowing(true);
                    updateLongPressState(true);
                    this.longPressDetector.cancelJob();
                } else {
                    j.k("magnifierHelper");
                    throw null;
                }
            } else {
                showPopupMenu();
            }
            this.handleController.updatePosition();
            View view = this.teView;
            if (view != null) {
                view.invalidate();
                return startNewTextSelection;
            }
            j.k("teView");
            throw null;
        }
        clearAllSelection();
        updateLongPressState(false);
        return startNewTextSelection;
    }

    private final void updateLongPressState(boolean z) {
        this.vibrationHelper.setLongPress(z);
        this.handleController.setLongPress(z);
        ImageTranslator imageTranslator2 = this.imageTranslator;
        if (imageTranslator2 != null) {
            imageTranslator2.setLongPress(z);
        }
    }

    private final void updateTextExtraction(TextData textData2) {
        updateTextExtractionData(textData2);
        updateTextExtractionDrawInfo();
        View view = this.teView;
        if (view != null) {
            view.invalidate();
        } else {
            j.k("teView");
            throw null;
        }
    }

    private final void updateTextExtractionData(TextData textData2) {
        ImageInfo imageInfo2 = this.imageInfo;
        if (textData2 != null) {
            this.textSelectionHelper.updateSelectableData(textData2, imageInfo2.getImageRatio(), imageInfo2.getCenterOffset());
        }
        this.barcodeHelper.updateSelectableBarcodes(imageInfo2.getBarcodeImageRatio(), imageInfo2.getCenterOffset());
    }

    private final void updateTextExtractionDrawInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.textSelectionHelper.makeHighlightPolyPerBlock());
        arrayList.addAll(this.barcodeHelper.makeHighlightPolyPerBarcode());
        setContentDescription(this.textSelectionHelper.getContentDescription());
        this.backgroundRenderer.updateHighlightPath(arrayList, this.imageInfo.getImageRect());
    }

    private final void updateTextExtractionWithNewRect() {
        if (this.textData == null) {
            LibLogger.e("TextExtractionDrawHelperImpl", "updateTextExtractionWithNewRect skipped by null TextData");
            return;
        }
        ArrayList<Number> arrayList = new ArrayList<>();
        int i2 = 0;
        for (SelectableCharacter isSelected : this.textSelectionHelper.getSelectableOcrResult().getSelectableCharacters()) {
            int i7 = i2 + 1;
            if (isSelected.isSelected()) {
                arrayList.add(Integer.valueOf(i2));
            }
            i2 = i7;
        }
        updateTextExtraction(this.textData);
        for (Number intValue : arrayList) {
            this.textSelectionHelper.getSelectableOcrResult().getSelectableCharacters().get(intValue.intValue()).setSelected(true);
        }
    }

    public void clearAllSelection() {
        LibLogger.i("TextExtractionDrawHelperImpl", "clearAllSelection called");
        this.barcodeHelper.clearAllSelection();
        this.textSelectionHelper.clearAllSelection();
        this.textSelectionHelper.updateSelectedTextData();
        this.handleController.setEmpty();
        dismissPopupMenu();
        View view = this.teView;
        if (view != null) {
            view.invalidate();
        } else {
            j.k("teView");
            throw null;
        }
    }

    public void dismissPopupMenu() {
        LibLogger.i("TextExtractionDrawHelperImpl", "dismissPopupMenu called");
        if (this.isShowPopupEnabled) {
            TextExtractionActionModeHelper textExtractionActionModeHelper = this.actionModeHelper;
            if (textExtractionActionModeHelper != null) {
                textExtractionActionModeHelper.stopActionMode();
            } else {
                j.k("actionModeHelper");
                throw null;
            }
        }
    }

    public void drawSelection(Canvas canvas) {
        j.e(canvas, "canvas");
        drawSelectionInternal$default(this, canvas, (Rect) null, 0.0f, 6, (Object) null);
    }

    public void drawTranslation(Canvas canvas) {
        j.e(canvas, "canvas");
        ImageTranslator imageTranslator2 = this.imageTranslator;
        if (imageTranslator2 != null) {
            imageTranslator2.drawTranslation(canvas, this.imageInfo.getImageRect());
        }
    }

    public void finishTextSelection() {
        LibLogger.i("TextExtractionDrawHelperImpl", "finishTextSelection called");
        clearAllSelection();
        CapsuleHelperImpl capsuleHelperImpl = this.capsuleHelper;
        if (capsuleHelperImpl != null) {
            capsuleHelperImpl.turnOffTranslate();
        }
        CapsuleHelperImpl capsuleHelperImpl2 = this.capsuleHelper;
        if (capsuleHelperImpl2 != null) {
            capsuleHelperImpl2.clearCapsuleLayout();
        }
        this.longPressDetector.cancelJob();
        MagnifierHelper magnifierHelper2 = this.magnifierHelper;
        if (magnifierHelper2 != null) {
            magnifierHelper2.dismiss();
            this.textExtractionState = TextExtractionState.IDLE;
            return;
        }
        j.k("magnifierHelper");
        throw null;
    }

    public CapsuleHelper getCapsuleHelper() {
        return this.capsuleHelper;
    }

    public TranslateLanguageHelper getTranslateLanguageHelper() {
        return this.translateLangHelper;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.j.e(r8, r0)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r0 = r7.textExtractionState
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState.IDLE
            r2 = 0
            if (r0 != r1) goto L_0x000d
            return r2
        L_0x000d:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressDetector r0 = r7.longPressDetector
            r0.handleTouchEvent(r8)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState r0 = r7.textExtractionState
            int[] r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r3 = 1
            if (r0 == r3) goto L_0x003d
            r4 = 2
            if (r0 == r4) goto L_0x003d
            r4 = 3
            if (r0 == r4) goto L_0x0028
        L_0x0026:
            r0 = r2
            goto L_0x0075
        L_0x0028:
            com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator r0 = r7.imageTranslator
            if (r0 == 0) goto L_0x0026
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.ImageInfo r4 = r7.imageInfo
            float r4 = r4.getImageRatio()
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.ImageInfo r5 = r7.imageInfo
            android.graphics.Point r5 = r5.getCenterOffset()
            boolean r0 = r0.handleTouchEvent(r8, r4, r5)
            goto L_0x0075
        L_0x003d:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.HandleController r0 = r7.handleController
            boolean r0 = r0.handleTouchEvent(r8)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r4 = r7.textSelectionHelper
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableLine r4 = r4.getLastSelectedLine()
            boolean r5 = r7.isTextSelected()
            if (r5 == 0) goto L_0x006f
            if (r4 == 0) goto L_0x006a
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.MagnifierHelper r5 = r7.magnifierHelper
            if (r5 == 0) goto L_0x0064
            android.graphics.Point[] r4 = r4.getPoly()
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.ImageInfo r6 = r7.imageInfo
            float r6 = r6.getScaleFactor()
            boolean r4 = r5.handleTouchEvent(r8, r4, r6)
            goto L_0x006b
        L_0x0064:
            java.lang.String r7 = "magnifierHelper"
            kotlin.jvm.internal.j.k(r7)
            throw r1
        L_0x006a:
            r4 = r2
        L_0x006b:
            if (r4 == 0) goto L_0x006f
            r4 = r3
            goto L_0x0070
        L_0x006f:
            r4 = r2
        L_0x0070:
            if (r0 != 0) goto L_0x0074
            if (r4 == 0) goto L_0x0026
        L_0x0074:
            r0 = r3
        L_0x0075:
            boolean r8 = r7.handleTouchEventCommon(r8)
            if (r0 != 0) goto L_0x007d
            if (r8 == 0) goto L_0x007e
        L_0x007d:
            r2 = r3
        L_0x007e:
            if (r2 == 0) goto L_0x008f
            android.view.View r7 = r7.teView
            if (r7 == 0) goto L_0x0088
            r7.invalidate()
            return r2
        L_0x0088:
            java.lang.String r7 = "teView"
            kotlin.jvm.internal.j.k(r7)
            throw r1
        L_0x008f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl.handleTouchEvent(android.view.MotionEvent):boolean");
    }

    public void hapticFeedback() {
        this.vibrationHelper.hapticFeedback();
    }

    public void init(View view) {
        j.e(view, "view");
        LibLogger.i("TextExtractionDrawHelperImpl", "initialize TextExtractionDrawHelper");
        view.setFocusableInTouchMode(true);
        view.setFocusable(true);
        view.setImportantForAccessibility(1);
        this.teView = view;
        Context context2 = this.context;
        View view2 = this.teView;
        if (view2 != null) {
            this.actionModeHelper = new TextExtractionActionModeHelper(context2, view2, this.textClassificationHelper, new TextExtractionDrawHelperImpl$init$2(this));
            Context context3 = this.context;
            View view3 = this.teView;
            if (view3 != null) {
                this.magnifierHelper = new MagnifierHelper(context3, view3);
                Context context4 = this.context;
                View view4 = this.teView;
                if (view4 != null) {
                    this.dragAndDropHelper = new DragAndDropHelper(context4, view4);
                    initInternal();
                    return;
                }
                j.k("teView");
                throw null;
            }
            j.k("teView");
            throw null;
        }
        j.k("teView");
        throw null;
    }

    public void initCapsuleLayout(ViewGroup viewGroup) {
        j.e(viewGroup, "layout");
        CapsuleHelperImpl capsuleHelperImpl = this.capsuleHelper;
        if (capsuleHelperImpl != null) {
            capsuleHelperImpl.initCapsuleLayout(viewGroup);
        }
    }

    public boolean isBarcodeSelected() {
        return this.barcodeHelper.isBarcodeSelected();
    }

    public boolean isTextSelected() {
        return this.textSelectionHelper.isTextSelected();
    }

    public void onConfigurationChanged() {
        LibLogger.i("TextExtractionDrawHelperImpl", "onConfigurationChanged called");
        CapsuleHelperImpl capsuleHelperImpl = this.capsuleHelper;
        if (capsuleHelperImpl != null) {
            capsuleHelperImpl.onConfigurationChanged();
        }
        TranslateLanguageHelper translateLanguageHelper = this.translateLangHelper;
        if (translateLanguageHelper != null) {
            translateLanguageHelper.onConfigurationChanged();
        }
    }

    public void selectAll() {
        LibLogger.i("TextExtractionDrawHelperImpl", "selectAll called");
        clearAllSelection();
        this.textSelectionHelper.selectAll();
        this.handleController.updatePosition();
        this.textSelectionHelper.updateSelectedTextData();
        showPopupMenu();
        View view = this.teView;
        if (view != null) {
            view.postInvalidate();
            int length = this.textSelectionHelper.getSelectedText().length();
            LibLogger.i("TextExtractionDrawHelperImpl", "selectAll " + length + " chars selected");
            return;
        }
        j.k("teView");
        throw null;
    }

    public void setBarcodeBitmap(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        String logString = LogUtil.INSTANCE.logString(bitmap);
        LibLogger.i("TextExtractionDrawHelperImpl", "setBarcodeBitmap with " + logString);
        this.imageInfo.setBarcodeImageWidth(bitmap.getWidth());
        this.imageInfo.setBarcodeImageHeight(bitmap.getHeight());
    }

    public void setBarcodeList(List<Barcode> list) {
        j.e(list, "barcodeList");
        int size = list.size();
        LibLogger.i("TextExtractionDrawHelperImpl", "setBarcodeList with " + size + " items");
        this.barcodeHelper.setBarcodeList(list);
    }

    public void setBitmap(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        String logString = LogUtil.INSTANCE.logString(bitmap);
        LibLogger.i("TextExtractionDrawHelperImpl", "setBitmap with " + logString);
        setBarcodeBitmap(bitmap);
        ImageInfo imageInfo2 = this.imageInfo;
        imageInfo2.setBitmap(bitmap);
        imageInfo2.setImageWidth(bitmap.getWidth());
        imageInfo2.setImageHeight(bitmap.getHeight());
    }

    public void setBitmapRect(RectF rectF) {
        j.e(rectF, "rect");
        LibLogger.i("TextExtractionDrawHelperImpl", "setBitmapRect with " + rectF);
        this.imageInfo.setImageRect(rectF);
    }

    public void setDimEnabled(boolean z) {
        LibLogger.i("TextExtractionDrawHelperImpl", "setDimEnabled " + z);
        this.backgroundRenderer.setDimEnabled(z);
    }

    public void setMagnifierEnabled(boolean z) {
        LibLogger.i("TextExtractionDrawHelperImpl", "setMagnifierEnabled " + z);
        MagnifierHelper magnifierHelper2 = this.magnifierHelper;
        if (magnifierHelper2 != null) {
            magnifierHelper2.setEnabled(z);
        } else {
            j.k("magnifierHelper");
            throw null;
        }
    }

    public void setOnScaleState(int i2) {
        this.scaleState = i2;
    }

    public void setOnTranslationState(int i2) {
        this.translationState = i2;
    }

    public void setProgressBarCallback(TextExtractionDrawHelper.ProgressBarCallback progressBarCallback2) {
        j.e(progressBarCallback2, Profile.PhoneNumberData.TYPE_CALLBACK);
        this.progressBarCallback = progressBarCallback2;
    }

    public void setScaleFactor(float f) {
        this.imageInfo.setScaleFactor(f);
        this.handleController.setScaleFactor(f);
    }

    public void setTextData(TextData textData2) {
        j.e(textData2, "textData");
        int size = textData2.getOcrData().getBlockList().size();
        LibLogger.i("TextExtractionDrawHelperImpl", "setTextData with " + size + " blocks");
        this.textData = TextData.copy$default(textData2, OcrUtil.INSTANCE.sortOcrData(textData2.getOcrData()), (EntityData) null, 2, (Object) null);
    }

    public void setTranslateClickListener(TextExtractionDrawHelper.TranslateCapsuleClickListener translateCapsuleClickListener) {
        j.e(translateCapsuleClickListener, "listener");
        CapsuleHelperImpl capsuleHelperImpl = this.capsuleHelper;
        if (capsuleHelperImpl != null) {
            capsuleHelperImpl.setTranslateCapsuleListener(new TextExtractionDrawHelperImpl$setTranslateClickListener$1(translateCapsuleClickListener));
        }
    }

    public void setUnderlineVisible(boolean z) {
        LibLogger.i("TextExtractionDrawHelperImpl", "setUnderlineVisible " + z);
        if (this.entityRenderer.isEntityAreaVisible() != z) {
            this.entityRenderer.setEntityAreaVisible(z);
            View view = this.teView;
            if (view != null) {
                view.invalidate();
            } else {
                j.k("teView");
                throw null;
            }
        }
    }

    public void showPopupMenu() {
        if (!canShowPopupMenu()) {
            LibLogger.i("TextExtractionDrawHelperImpl", "showPopupMenu ignored");
            return;
        }
        LibLogger.i("TextExtractionDrawHelperImpl", "showPopupMenu called");
        Iterable selectedCharacters = this.textSelectionHelper.getSelectedCharacters();
        boolean z = false;
        if (!(selectedCharacters instanceof Collection) || !((Collection) selectedCharacters).isEmpty()) {
            Iterator it = selectedCharacters.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((SelectableCharacter) it.next()).isVertical()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        boolean z3 = z;
        List<Rect> visibleCharactersRectList = getVisibleCharactersRectList();
        if (visibleCharactersRectList.isEmpty()) {
            dismissPopupMenu();
            return;
        }
        TextExtractionActionModeHelper textExtractionActionModeHelper = this.actionModeHelper;
        if (textExtractionActionModeHelper != null) {
            textExtractionActionModeHelper.startActionMode(this.textSelectionHelper.getSelectedTextForActionMode(), this.textSelectionHelper.getSelectedTextForTextClassification(), this.textSelectionHelper.getLeftAdjacentStringFromSelection(), this.textSelectionHelper.getRightAdjacentStringFromSelection(), visibleCharactersRectList, z3, this.textSelectionHelper.isAllTextSelected());
        } else {
            j.k("actionModeHelper");
            throw null;
        }
    }

    public boolean startBarcodeSelection(float f, float f5) {
        LibLogger.i("TextExtractionDrawHelperImpl", "startBarcodeSelection called with " + f + ArcCommonLog.TAG_COMMA + f5);
        if (isTalkBackEnabled()) {
            LibLogger.e("TextExtractionDrawHelperImpl", "startBarcodeSelection is not supported on talkback mode!");
            return false;
        } else if (this.scaleState == 1 || this.translationState == 1) {
            LibLogger.e("TextExtractionDrawHelperImpl", "scaleState or translation state in progress!");
            return false;
        } else {
            this.barcodeHelper.clearAllSelection();
            return this.barcodeHelper.showBarcodeDialog((int) (f + 0.5f), (int) (f5 + 0.5f));
        }
    }

    public void startImageTranslation() {
        if (Rune.INSTANCE.isSupportCapsule()) {
            doImageTranslate(LayoutState.LANGUAGE_CODE_BUTTON);
        }
    }

    public boolean startTextSelectionByButton() {
        LibLogger.i("TextExtractionDrawHelperImpl", "startTextSelectionByButton called");
        if (this.textExtractionState == TextExtractionState.IDLE) {
            initTextSelection();
        }
        this.textExtractionState = TextExtractionState.START_BY_BUTTON;
        View view = this.teView;
        if (view != null) {
            view.invalidate();
            return !this.textSelectionHelper.getSelectedCharacters().isEmpty();
        }
        j.k("teView");
        throw null;
    }

    public boolean startTextSelectionWithCoordinate(float f, float f5, boolean z) {
        LibLogger.i("TextExtractionDrawHelperImpl", "startTextSelectionWithCoordinate called with " + f + ArcCommonLog.TAG_COMMA + f5 + ", isLongPress=" + z);
        int i2 = (int) (f + 0.5f);
        int i7 = (int) (0.5f + f5);
        int i8 = WhenMappings.$EnumSwitchMapping$0[this.textExtractionState.ordinal()];
        if (i8 == 1 || i8 == 2) {
            return startNewTextSelection(i2, i7, z);
        }
        if (i8 == 3) {
            return true;
        }
        if (i8 != 4) {
            throw new RuntimeException();
        } else if (!z) {
            return false;
        } else {
            LibLogger.i("TextExtractionDrawHelperImpl", "text selection started by long press");
            initTextSelection();
            this.textExtractionState = TextExtractionState.START_BY_LONG_PRESS;
            return startTextSelectionWithCoordinate(f, f5, true);
        }
    }

    public void updateTextSelection(RectF rectF) {
        j.e(rectF, "rect");
        LibLogger.i("TextExtractionDrawHelperImpl", "updateTextSelection called with " + rectF);
        dismissPopupMenu();
        setBitmapRect(rectF);
        initImageInfo();
        updateTextExtractionWithNewRect();
        this.handleController.updatePosition();
        showPopupMenu();
        View view = this.teView;
        if (view != null) {
            view.invalidate();
        } else {
            j.k("teView");
            throw null;
        }
    }

    public void startImageTranslation(TranslationTokenInfo translationTokenInfo, TextExtractionDrawHelper.ImageTranslationResultCallback imageTranslationResultCallback) {
        j.e(translationTokenInfo, "tokenInfo");
        j.e(imageTranslationResultCallback, Profile.PhoneNumberData.TYPE_CALLBACK);
        startImageTranslation();
    }
}
