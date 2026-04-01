package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import A.a;
import B2.h;
import B2.i;
import Ba.g;
import Tf.o;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.ContextThemeWrapper;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.core.content.res.ResourcesCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.SemMultiWindowManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.FloatingOnAttachStateChangeListener;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanelViewHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.PopupAnimationHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.PopupMenuHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.ViewUtils;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.ReflectionContainer;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.ViewTreeObserverOnComputeInternalInsetsListenerReflection;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f*\u0002æ\u0001\b\u0000\u0018\u0000 ü\u00012\u00020\u0001:\u0002ü\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\fJ-\u0010\u001a\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\n¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\n¢\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010 \u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b \u0010!J+\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010#\u001a\u00020\bH\u0007¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\n¢\u0006\u0004\b&\u0010\u001dJ)\u0010*\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010\u00022\b\u0010(\u001a\u0004\u0018\u00010\u00142\u0006\u0010)\u001a\u00020\r¢\u0006\u0004\b*\u0010+J%\u0010-\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\r¢\u0006\u0004\b-\u0010.J\u0015\u0010/\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b/\u0010\u0010J\u0017\u00102\u001a\u00020\n2\b\u00101\u001a\u0004\u0018\u000100¢\u0006\u0004\b2\u00103J\u001b\u00107\u001a\u00020\n2\f\u00106\u001a\b\u0012\u0004\u0012\u00020504¢\u0006\u0004\b7\u00108J\u0017\u0010;\u001a\u00020\n2\u0006\u0010:\u001a\u000209H\u0002¢\u0006\u0004\b;\u0010<J/\u0010=\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0011\u001a\u00020\bH\u0002¢\u0006\u0004\b=\u0010>J\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010@J\u000f\u0010A\u001a\u00020\nH\u0002¢\u0006\u0004\bA\u0010\u001dJ\u0017\u0010B\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u0018H\u0002¢\u0006\u0004\bB\u0010@J\u0017\u0010C\u001a\u00020\b2\u0006\u0010?\u001a\u00020\u0018H\u0002¢\u0006\u0004\bC\u0010DJ\u0017\u0010E\u001a\u00020\b2\u0006\u0010?\u001a\u00020\u0018H\u0002¢\u0006\u0004\bE\u0010DJ\u0017\u0010F\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u0018H\u0002¢\u0006\u0004\bF\u0010@J\u000f\u0010G\u001a\u00020\nH\u0002¢\u0006\u0004\bG\u0010\u001dJ\u000f\u0010H\u001a\u00020\nH\u0002¢\u0006\u0004\bH\u0010\u001dJ\u000f\u0010I\u001a\u00020\nH\u0002¢\u0006\u0004\bI\u0010\u001dJ\u000f\u0010J\u001a\u00020\nH\u0002¢\u0006\u0004\bJ\u0010\u001dJ\u0017\u0010L\u001a\u00020\n2\u0006\u0010K\u001a\u00020\bH\u0002¢\u0006\u0004\bL\u0010\fJ\u000f\u0010M\u001a\u00020\nH\u0002¢\u0006\u0004\bM\u0010\u001dJ\u000f\u0010N\u001a\u00020\nH\u0002¢\u0006\u0004\bN\u0010\u001dJ\u0017\u0010O\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002¢\u0006\u0004\bO\u0010PJ\u000f\u0010Q\u001a\u00020\nH\u0002¢\u0006\u0004\bQ\u0010\u001dJ\u000f\u0010R\u001a\u00020\nH\u0002¢\u0006\u0004\bR\u0010\u001dJ\u000f\u0010S\u001a\u00020\rH\u0002¢\u0006\u0004\bS\u0010TJ\u001d\u0010U\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001404H\u0002¢\u0006\u0004\bU\u00108J\u000f\u0010V\u001a\u00020\nH\u0002¢\u0006\u0004\bV\u0010\u001dJ\u000f\u0010W\u001a\u00020\nH\u0002¢\u0006\u0004\bW\u0010\u001dJ\u0017\u0010Y\u001a\u00020\b2\u0006\u0010X\u001a\u00020\bH\u0002¢\u0006\u0004\bY\u0010PJ\u000f\u0010Z\u001a\u00020\nH\u0002¢\u0006\u0004\bZ\u0010\u001dJ\u000f\u0010\\\u001a\u00020[H\u0002¢\u0006\u0004\b\\\u0010]J\u000f\u0010_\u001a\u00020^H\u0002¢\u0006\u0004\b_\u0010`J\u000f\u0010b\u001a\u00020aH\u0002¢\u0006\u0004\bb\u0010cJ\u000f\u0010e\u001a\u00020dH\u0002¢\u0006\u0004\be\u0010fJ\u000f\u0010g\u001a\u00020\bH\u0002¢\u0006\u0004\bg\u0010hJ\u001f\u0010l\u001a\u00020\r2\u0006\u0010j\u001a\u00020i2\u0006\u0010k\u001a\u00020iH\u0002¢\u0006\u0004\bl\u0010mJ\u001f\u0010n\u001a\u00020\n2\u0006\u0010j\u001a\u00020\b2\u0006\u0010k\u001a\u00020\bH\u0002¢\u0006\u0004\bn\u0010oJ\u000f\u0010p\u001a\u00020\bH\u0002¢\u0006\u0004\bp\u0010hJ\u000f\u0010q\u001a\u00020\nH\u0002¢\u0006\u0004\bq\u0010\u001dJ\u000f\u0010r\u001a\u00020\nH\u0002¢\u0006\u0004\br\u0010\u001dJ\u000f\u0010s\u001a\u00020\nH\u0002¢\u0006\u0004\bs\u0010\u001dJ\u0017\u0010v\u001a\u00020\n2\u0006\u0010u\u001a\u00020tH\u0002¢\u0006\u0004\bv\u0010wJ\u000f\u0010x\u001a\u00020\nH\u0002¢\u0006\u0004\bx\u0010\u001dJ\u0017\u0010y\u001a\u00020[2\u0006\u0010'\u001a\u00020\u0002H\u0002¢\u0006\u0004\by\u0010zJ\u0017\u0010}\u001a\u00020|2\u0006\u0010{\u001a\u00020[H\u0002¢\u0006\u0004\b}\u0010~J\u001a\u0010\u0001\u001a\u00020\u00022\u0006\u0010\u001a\u00020\u0002H\u0002¢\u0006\u0006\b\u0001\u0010\u0001R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010\u0001R\u0015\u0010'\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0007\n\u0005\b'\u0010\u0001R\u0017\u0010\u0001\u001a\u00020|8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0001\u001a\u00020[8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0001\u001a\u00020[8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0001\u001a\u00020a8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0017\u0010\u0001\u001a\u00020^8\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010 \u0001R\u0018\u0010¢\u0001\u001a\u00030¡\u00018\u0002X\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010£\u0001R\u0017\u0010¤\u0001\u001a\u00020\u00188\u0002X\u0004¢\u0006\b\n\u0006\b¤\u0001\u0010¥\u0001R\u0018\u0010§\u0001\u001a\u00030¦\u00018\u0002X\u0004¢\u0006\b\n\u0006\b§\u0001\u0010¨\u0001R\u0018\u0010ª\u0001\u001a\u00030©\u00018\u0002X\u0004¢\u0006\b\n\u0006\bª\u0001\u0010«\u0001R\u0018\u0010­\u0001\u001a\u00030¬\u00018\u0002X\u0004¢\u0006\b\n\u0006\b­\u0001\u0010®\u0001R'\u0010¯\u0001\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0016\n\u0006\b¯\u0001\u0010°\u0001\u001a\u0005\b¯\u0001\u0010T\"\u0005\b±\u0001\u0010\u0010R'\u0010²\u0001\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0016\n\u0006\b²\u0001\u0010°\u0001\u001a\u0005\b²\u0001\u0010T\"\u0005\b³\u0001\u0010\u0010R'\u0010´\u0001\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0016\n\u0006\b´\u0001\u0010°\u0001\u001a\u0005\b´\u0001\u0010T\"\u0005\bµ\u0001\u0010\u0010R\u0019\u0010¶\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b¶\u0001\u0010°\u0001R\u0019\u0010·\u0001\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b·\u0001\u0010\u0001R\u0019\u0010¸\u0001\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b¸\u0001\u0010\u0001R\u001b\u0010¹\u0001\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b¹\u0001\u0010º\u0001R\u0017\u0010»\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\b»\u0001\u0010\u0001R\u0018\u0010½\u0001\u001a\u00030¼\u00018\u0002X\u0004¢\u0006\b\n\u0006\b½\u0001\u0010¾\u0001R(\u0010¿\u0001\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0006@BX\u000e¢\u0006\u000f\n\u0006\b¿\u0001\u0010°\u0001\u001a\u0005\b¿\u0001\u0010TR\u0019\u0010À\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÀ\u0001\u0010°\u0001R\u001d\u0010Â\u0001\u001a\u00030Á\u00018\u0006¢\u0006\u0010\n\u0006\bÂ\u0001\u0010Ã\u0001\u001a\u0006\bÄ\u0001\u0010Å\u0001R,\u0010Æ\u0001\u001a\u0005\u0018\u00010Á\u00018\u0006@\u0006X\u000e¢\u0006\u0018\n\u0006\bÆ\u0001\u0010Ã\u0001\u001a\u0006\bÇ\u0001\u0010Å\u0001\"\u0006\bÈ\u0001\u0010É\u0001R\u001c\u0010Ê\u0001\u001a\u0005\u0018\u00010Á\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÊ\u0001\u0010Ã\u0001R\u0019\u0010Ë\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bË\u0001\u0010°\u0001R\u0019\u0010Ì\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÌ\u0001\u0010°\u0001R\u0017\u0010Í\u0001\u001a\u00020\u00188\u0002X\u0004¢\u0006\b\n\u0006\bÍ\u0001\u0010¥\u0001R\u0019\u0010Î\u0001\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÎ\u0001\u0010\u0001R\u0019\u0010Ï\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÏ\u0001\u0010°\u0001R\u0019\u0010Ð\u0001\u001a\u0004\u0018\u00010\u00048\u0002X\u0004¢\u0006\b\n\u0006\bÐ\u0001\u0010\u0001R\u0019\u0010Ñ\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÑ\u0001\u0010°\u0001R\u0018\u0010Ò\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\bÒ\u0001\u0010\u0001R\u001c\u0010Ô\u0001\u001a\u0005\u0018\u00010Ó\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010Õ\u0001R\u001c\u0010Ö\u0001\u001a\u0005\u0018\u00010Ó\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÖ\u0001\u0010Õ\u0001R\u0017\u0010×\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\b×\u0001\u0010\u0001R\u0017\u0010Ø\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\bØ\u0001\u0010\u0001R\u0017\u0010Ù\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\bÙ\u0001\u0010\u0001R\u0017\u0010Ú\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\bÚ\u0001\u0010\u0001R\u001f\u0010Û\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÛ\u0001\u0010Ü\u0001R\u0019\u0010Ý\u0001\u001a\u00020i8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÝ\u0001\u0010Þ\u0001R\u0019\u0010ß\u0001\u001a\u00020i8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bß\u0001\u0010Þ\u0001R\u0019\u0010à\u0001\u001a\u00020i8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bà\u0001\u0010Þ\u0001R\u0019\u0010á\u0001\u001a\u00020i8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bá\u0001\u0010Þ\u0001R\u0019\u0010â\u0001\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bâ\u0001\u0010\u0001R\u0019\u0010ã\u0001\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bã\u0001\u0010\u0001R\u0017\u0010ä\u0001\u001a\u00020\b8\u0002X\u0004¢\u0006\b\n\u0006\bä\u0001\u0010\u0001R\u0019\u0010å\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bå\u0001\u0010°\u0001R\u001a\u0010ç\u0001\u001a\u00030æ\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bç\u0001\u0010è\u0001R\u0017\u0010é\u0001\u001a\u00020\u00188\u0002X\u0004¢\u0006\b\n\u0006\bé\u0001\u0010¥\u0001R\u0018\u0010ê\u0001\u001a\u00030©\u00018\u0002X\u0004¢\u0006\b\n\u0006\bê\u0001\u0010«\u0001R\u0018\u0010ë\u0001\u001a\u00030¦\u00018\u0002X\u0004¢\u0006\b\n\u0006\bë\u0001\u0010¨\u0001R\u0018\u0010ì\u0001\u001a\u00030¦\u00018\u0002X\u0004¢\u0006\b\n\u0006\bì\u0001\u0010¨\u0001R\u0019\u0010í\u0001\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bí\u0001\u0010°\u0001R\u0018\u0010ï\u0001\u001a\u00030î\u00018\u0002X\u0004¢\u0006\b\n\u0006\bï\u0001\u0010ð\u0001R\u0018\u0010ò\u0001\u001a\u00030ñ\u00018\u0002X\u0004¢\u0006\b\n\u0006\bò\u0001\u0010ó\u0001R\u0013\u0010ô\u0001\u001a\u00020\r8F¢\u0006\u0007\u001a\u0005\bô\u0001\u0010TR\u0013\u0010õ\u0001\u001a\u00020\r8F¢\u0006\u0007\u001a\u0005\bõ\u0001\u0010TR\u0016\u0010ö\u0001\u001a\u00020\r8BX\u0004¢\u0006\u0007\u001a\u0005\bö\u0001\u0010TR\u0016\u0010ø\u0001\u001a\u00020\b8BX\u0004¢\u0006\u0007\u001a\u0005\b÷\u0001\u0010hR\u0016\u0010ù\u0001\u001a\u00020\r8BX\u0004¢\u0006\u0007\u001a\u0005\bù\u0001\u0010TR\u0018\u0010u\u001a\u0004\u0018\u00010t8BX\u0004¢\u0006\b\u001a\u0006\bú\u0001\u0010û\u0001¨\u0006ý\u0001"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "", "Landroid/content/Context;", "ctx", "Landroid/view/View;", "parent", "<init>", "(Landroid/content/Context;Landroid/view/View;)V", "", "color", "Lme/x;", "setMenuTitleColor", "(I)V", "", "value", "setWidthChanged", "(Z)V", "width", "setSuggestedWidth", "", "Landroid/view/MenuItem;", "menuItemList", "Landroid/view/MenuItem$OnMenuItemClickListener;", "menuItemClickListener", "Landroid/graphics/Rect;", "contentRect", "show", "(Ljava/util/List;Landroid/view/MenuItem$OnMenuItemClickListener;Landroid/graphics/Rect;)V", "dismiss", "()V", "hide", "clickListener", "setOnMenuItemClickListener", "(Landroid/view/MenuItem$OnMenuItemClickListener;)V", "menuItems", "toolbarWidth", "layoutMainPanelItems", "(Ljava/util/List;I)Ljava/util/List;", "onDetachFromWindow", "context", "menuItem", "showIcon", "createMenuItemButton", "(Landroid/content/Context;Landroid/view/MenuItem;Z)Landroid/view/View;", "menuItemButton", "updateMenuItemButton", "(Landroid/view/View;Landroid/view/MenuItem;Z)V", "setFlexMode", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "listener", "setToolbarActionListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;)V", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "list", "setToolbarMenuItem", "(Ljava/util/List;)V", "Landroid/view/MotionEvent;", "event", "onTouchOnWindow", "(Landroid/view/MotionEvent;)V", "layoutMenuItems", "(Ljava/util/List;Landroid/view/MenuItem$OnMenuItemClickListener;I)V", "contentRectOnScreen", "(Landroid/graphics/Rect;)V", "setTouchableSurfaceInsetsComputer", "updateCoordinates", "refreshXCoordinates", "(Landroid/graphics/Rect;)I", "refreshYCoordinates", "refreshCoordinatesAndOverflowDirection", "cancelOverflowAnimations", "openOverflow", "closeOverflow", "setPanelsStatesAtRestingPosition", "suggestedHeight", "updateOverflowHeight", "updatePopupSize", "refreshViewPort", "getAdjustedToolbarWidth", "(I)I", "setZeroTouchableSurface", "setContentAreaAsTouchableSurface", "hasOverflow", "()Z", "layoutOverflowPanelItems", "preparePopupContent", "clearPanels", "maxItemSize", "calculateOverflowHeight", "maybeComputeTransitionDurationScale", "Landroid/view/ViewGroup;", "createMainPanel", "()Landroid/view/ViewGroup;", "Landroid/widget/ImageButton;", "createOverflowButton", "()Landroid/widget/ImageButton;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanel;", "createOverflowPanel", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanel;", "Landroid/view/animation/Animation$AnimationListener;", "createOverflowAnimationListener", "()Landroid/view/animation/Animation$AnimationListener;", "getViewPortVisibleHeight", "()I", "", "x", "y", "isInsideOfViewPortRect", "(FF)Z", "calculateCoords", "(II)V", "isNeedToChangeDirection", "shiftPopup", "changeOverflowPanelAdapterOrder", "createDividers", "Landroid/view/DisplayCutout;", "displayCutout", "setCutoutMarginValue", "(Landroid/view/DisplayCutout;)V", "recalCoordsOnWindowX", "createContentContainer", "(Landroid/content/Context;)Landroid/view/ViewGroup;", "content", "Landroid/widget/PopupWindow;", "createPopupWindow", "(Landroid/view/ViewGroup;)Landroid/widget/PopupWindow;", "originalContext", "applyDefaultTheme", "(Landroid/content/Context;)Landroid/content/Context;", "Landroid/view/View;", "Landroid/content/Context;", "popupWindow", "Landroid/widget/PopupWindow;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper;", "animationHelper", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper;", "menuHelper", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper;", "marginHorizontal", "I", "marginVertical", "contentContainer", "Landroid/view/ViewGroup;", "mainPanel", "overflowPanel", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanel;", "overflowButton", "Landroid/widget/ImageButton;", "Landroid/graphics/drawable/Drawable;", "arrow", "Landroid/graphics/drawable/Drawable;", "overflow", "Landroid/graphics/drawable/AnimatedVectorDrawable;", "toArrow", "Landroid/graphics/drawable/AnimatedVectorDrawable;", "toOverflow", "Lcom/samsung/android/app/SemMultiWindowManager;", "multiWindowManager", "Lcom/samsung/android/app/SemMultiWindowManager;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanelViewHelper;", "overflowPanelViewHelper", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanelViewHelper;", "viewPortOnScreen", "Landroid/graphics/Rect;", "Landroid/graphics/Point;", "coordsOnWindow", "Landroid/graphics/Point;", "", "tempCoords", "[I", "Landroid/graphics/Region;", "touchableRegion", "Landroid/graphics/Region;", "isMovingStarted", "Z", "setMovingStarted", "isDiscardTouch", "setDiscardTouch", "isScrolling", "setScrolling", "isFlexMode", "cutoutLeftMargin", "cutoutRightMargin", "menuTitleColor", "Ljava/lang/Integer;", "lineHeight", "Ljava/lang/Runnable;", "preparePopupContentRtlHelper", "Ljava/lang/Runnable;", "isDismissed", "isHidden", "Landroid/util/Size;", "overflowButtonSize", "Landroid/util/Size;", "getOverflowButtonSize", "()Landroid/util/Size;", "overflowPanelSize", "getOverflowPanelSize", "setOverflowPanelSize", "(Landroid/util/Size;)V", "mainPanelSize", "openOverflowUpwards", "isOverflowOpen", "previousContentRect", "suggestedWidth", "widthChanged", "parentRoot", "isClosedOpposites", "arrowSem", "Landroid/widget/ImageView;", "dividerVertical", "Landroid/widget/ImageView;", "dividerHorizontal", "menuFirstLastSidePadding", "menuSidePadding", "popupTopMargin", "popupVerticalOffset", "overflowMenuItems", "Ljava/util/List;", "lastTouchDownX", "F", "lastTouchDownY", "prevTouchX", "prevTouchY", "deltaX", "deltaY", "touchSlop", "isMovingFirstTime", "com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$accessDelegate$1", "accessDelegate", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$accessDelegate$1;", "toolbarVisibleRect", "toolbarHiddenArea", "movedPos", "originalPos", "moved", "Landroid/view/View$OnAttachStateChangeListener;", "onAnchorRootDetachedListener", "Landroid/view/View$OnAttachStateChangeListener;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/reflect/ViewTreeObserverOnComputeInternalInsetsListenerReflection;", "insetsComputer", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/reflect/ViewTreeObserverOnComputeInternalInsetsListenerReflection;", "isShowing", "isOverflowAnimating", "isInRtlMode", "getOverflowWidth", "overflowWidth", "isCutoutMarginSet", "getDisplayCutout", "()Landroid/view/DisplayCutout;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCapturePopup {
    public static final Companion Companion = new Companion((e) null);
    private static final int MAX_MAIN_ITEM_SIZE = 4;
    private static final int MAX_OVERFLOW_SIZE = 4;
    private static final int MIN_OVERFLOW_SIZE = 1;
    private static final int NEED_CHANGE_DIRECTION_ALL = 3;
    private static final int NEED_CHANGE_DIRECTION_HORIZONTAL = 2;
    private static final int NEED_CHANGE_DIRECTION_UPSIDE = 4;
    private static final int NEED_CHANGE_DIRECTION_VERTICAL = 1;
    private static final int NEED_NOT_CHANGE_DIRECTION = 0;
    private static final String TAG = "ObjectCapturePopup";
    private ObjectCapturePopup$accessDelegate$1 accessDelegate = new ObjectCapturePopup$accessDelegate$1();
    /* access modifiers changed from: private */
    public final PopupAnimationHelper animationHelper;
    private final Drawable arrow;
    private final Drawable arrowSem;
    /* access modifiers changed from: private */
    public final ViewGroup contentContainer;
    private final Context context;
    private final Point coordsOnWindow = new Point();
    private int cutoutLeftMargin;
    private int cutoutRightMargin;
    private int deltaX;
    private int deltaY;
    private ImageView dividerHorizontal;
    private ImageView dividerVertical;
    private final ViewTreeObserverOnComputeInternalInsetsListenerReflection insetsComputer;
    private boolean isClosedOpposites;
    private boolean isDiscardTouch;
    private boolean isDismissed = true;
    private boolean isFlexMode;
    private boolean isHidden;
    private boolean isMovingFirstTime;
    private boolean isMovingStarted;
    private boolean isOverflowOpen;
    private boolean isScrolling;
    private float lastTouchDownX;
    private float lastTouchDownY;
    private final int lineHeight;
    /* access modifiers changed from: private */
    public final ViewGroup mainPanel;
    /* access modifiers changed from: private */
    public Size mainPanelSize;
    private final int marginHorizontal;
    private final int marginVertical;
    private final int menuFirstLastSidePadding;
    private final PopupMenuHelper menuHelper;
    private final int menuSidePadding;
    private Integer menuTitleColor;
    private boolean moved;
    private final Point movedPos = new Point();
    private final SemMultiWindowManager multiWindowManager;
    private final View.OnAttachStateChangeListener onAnchorRootDetachedListener;
    private boolean openOverflowUpwards;
    private final Point originalPos = new Point();
    private final Drawable overflow;
    /* access modifiers changed from: private */
    public final ImageButton overflowButton;
    private final Size overflowButtonSize;
    private List<MenuItem> overflowMenuItems = new ArrayList();
    /* access modifiers changed from: private */
    public final OverflowPanel overflowPanel;
    private Size overflowPanelSize;
    /* access modifiers changed from: private */
    public final OverflowPanelViewHelper overflowPanelViewHelper;
    private final View parent;
    private final View parentRoot;
    private final int popupTopMargin;
    private final int popupVerticalOffset;
    private final PopupWindow popupWindow;
    private final Runnable preparePopupContentRtlHelper;
    private float prevTouchX;
    private float prevTouchY;
    private final Rect previousContentRect = new Rect();
    private int suggestedWidth;
    private final int[] tempCoords = new int[2];
    private final AnimatedVectorDrawable toArrow;
    private final AnimatedVectorDrawable toOverflow;
    private final int[] toolbarHiddenArea = new int[2];
    private final Rect toolbarVisibleRect = new Rect();
    private final int touchSlop;
    /* access modifiers changed from: private */
    public final Region touchableRegion = new Region();
    private final Rect viewPortOnScreen = new Rect();
    private boolean widthChanged = true;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$Companion;", "", "<init>", "()V", "TAG", "", "MIN_OVERFLOW_SIZE", "", "MAX_OVERFLOW_SIZE", "MAX_MAIN_ITEM_SIZE", "NEED_NOT_CHANGE_DIRECTION", "NEED_CHANGE_DIRECTION_VERTICAL", "NEED_CHANGE_DIRECTION_HORIZONTAL", "NEED_CHANGE_DIRECTION_ALL", "NEED_CHANGE_DIRECTION_UPSIDE", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ObjectCapturePopup(Context context2, View view) {
        j.e(context2, "ctx");
        j.e(view, "parent");
        this.parent = view;
        FloatingOnAttachStateChangeListener floatingOnAttachStateChangeListener = new FloatingOnAttachStateChangeListener(this);
        this.onAnchorRootDetachedListener = floatingOnAttachStateChangeListener;
        this.insetsComputer = new ObjectCapturePopup$insetsComputer$1(this);
        Context applyDefaultTheme = applyDefaultTheme(context2);
        this.context = applyDefaultTheme;
        ViewGroup createContentContainer = createContentContainer(applyDefaultTheme);
        this.contentContainer = createContentContainer;
        PopupWindow createPopupWindow = createPopupWindow(createContentContainer);
        this.popupWindow = createPopupWindow;
        View rootView = view.getRootView();
        this.parentRoot = rootView;
        PopupAnimationHelper popupAnimationHelper = new PopupAnimationHelper(applyDefaultTheme);
        this.animationHelper = popupAnimationHelper;
        this.menuHelper = new PopupMenuHelper(this);
        this.marginHorizontal = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_horizontal_margin);
        this.marginVertical = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_vertical_margin);
        this.lineHeight = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_height);
        this.multiWindowManager = new SemMultiWindowManager();
        Drawable drawable = ResourcesCompat.getDrawable(applyDefaultTheme.getResources(), R.drawable.popup_avd_to_overflow, applyDefaultTheme.getTheme());
        j.b(drawable);
        this.arrow = drawable;
        drawable.setAutoMirrored(true);
        Drawable drawable2 = ResourcesCompat.getDrawable(applyDefaultTheme.getResources(), R.drawable.popup_avd_to_arrow, applyDefaultTheme.getTheme());
        j.b(drawable2);
        this.overflow = drawable2;
        drawable2.setAutoMirrored(true);
        Drawable drawable3 = ResourcesCompat.getDrawable(applyDefaultTheme.getResources(), R.drawable.popup_avd_to_arrow_animation, applyDefaultTheme.getTheme());
        j.c(drawable3, "null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable3;
        this.toArrow = animatedVectorDrawable;
        animatedVectorDrawable.setAutoMirrored(true);
        Drawable drawable4 = ResourcesCompat.getDrawable(applyDefaultTheme.getResources(), R.drawable.popup_avd_to_overflow_animation, applyDefaultTheme.getTheme());
        j.c(drawable4, "null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
        AnimatedVectorDrawable animatedVectorDrawable2 = (AnimatedVectorDrawable) drawable4;
        this.toOverflow = animatedVectorDrawable2;
        animatedVectorDrawable2.setAutoMirrored(true);
        ImageButton createOverflowButton = createOverflowButton();
        this.overflowButton = createOverflowButton;
        this.overflowButtonSize = ViewUtils.measure(createOverflowButton);
        this.mainPanel = createMainPanel();
        this.overflowPanelViewHelper = new OverflowPanelViewHelper(this, applyDefaultTheme);
        this.overflowPanel = createOverflowPanel();
        popupAnimationHelper.initAnimation(createOverflowAnimationListener(), createContentContainer, createPopupWindow, rootView, floatingOnAttachStateChangeListener);
        this.menuFirstLastSidePadding = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_menu_first_last_side_padding);
        this.menuSidePadding = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_menu_button_side_padding);
        this.popupTopMargin = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_top_margin);
        this.popupVerticalOffset = applyDefaultTheme.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_vertical_offset);
        Drawable drawable5 = ResourcesCompat.getDrawable(applyDefaultTheme.getResources(), R.drawable.popup_arrow, applyDefaultTheme.getTheme());
        j.b(drawable5);
        this.arrowSem = drawable5;
        createDividers();
        this.touchSlop = ViewConfiguration.get(applyDefaultTheme).getScaledTouchSlop();
        createPopupWindow.setTouchInterceptor(new i(18, this));
        this.preparePopupContentRtlHelper = new c(this, 0);
    }

    /* access modifiers changed from: private */
    public static final boolean _init_$lambda$0(ObjectCapturePopup objectCapturePopup, View view, MotionEvent motionEvent) {
        j.e(motionEvent, "event");
        objectCapturePopup.onTouchOnWindow(motionEvent);
        if (view == null) {
            return false;
        }
        view.performClick();
        return false;
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$1(ObjectCapturePopup objectCapturePopup) {
        objectCapturePopup.setPanelsStatesAtRestingPosition();
        objectCapturePopup.setContentAreaAsTouchableSurface();
        objectCapturePopup.contentContainer.setAlpha(1.0f);
    }

    private final Context applyDefaultTheme(Context context2) {
        return new ContextThemeWrapper(context2, 16974563);
    }

    private final void calculateCoords(int i2, int i7) {
        this.parent.getRootView().getLocationOnScreen(this.tempCoords);
        int[] iArr = this.tempCoords;
        int i8 = iArr[0];
        int i10 = iArr[1];
        this.parent.getRootView().getLocationInWindow(this.tempCoords);
        int[] iArr2 = this.tempCoords;
        int i11 = i8 - iArr2[0];
        int i12 = i10 - iArr2[1];
        Rect rect = new Rect();
        this.parent.getRootView().getDrawingRect(rect);
        int i13 = this.viewPortOnScreen.left;
        int i14 = this.toolbarHiddenArea[0];
        int max = (int) Math.max(Math.max((double) (i13 + i14), (double) i14) - ((double) i11), (double) i2);
        int i15 = this.viewPortOnScreen.top;
        int i16 = this.toolbarHiddenArea[1];
        int max2 = (int) Math.max(Math.max((double) (i15 + i16), (double) i16) - ((double) i12), (double) i7);
        int min = (int) Math.min((double) rect.bottom, Math.min((double) ((this.toolbarVisibleRect.height() + max2) - this.toolbarHiddenArea[1]), (double) (getViewPortVisibleHeight() - i12)));
        this.coordsOnWindow.set((int) Math.min((double) max, (double) ((((int) Math.min((double) ((this.toolbarVisibleRect.width() + max) - this.toolbarHiddenArea[0]), (double) (this.viewPortOnScreen.right - i11))) - this.toolbarVisibleRect.width()) + this.toolbarHiddenArea[0])), (int) Math.min((double) max2, (double) ((min - this.toolbarVisibleRect.height()) + this.toolbarHiddenArea[1])));
        if (this.moved) {
            Point point = this.movedPos;
            Point point2 = this.originalPos;
            int i17 = point2.x;
            Point point3 = this.coordsOnWindow;
            point.set(i17 - point3.x, point2.y - point3.y);
        }
    }

    private final int calculateOverflowHeight(int i2) {
        int i7;
        int min = (int) Math.min(4.0d, Math.min(Math.max(1.0d, (double) i2), (double) this.overflowPanel.getCount()));
        if (min < this.overflowPanel.getCount()) {
            i7 = (int) (((float) this.lineHeight) * 0.5f);
        } else {
            i7 = 0;
        }
        return this.overflowButtonSize.getHeight() + (min * this.lineHeight) + i7;
    }

    private final void cancelOverflowAnimations() {
        this.contentContainer.clearAnimation();
        this.mainPanel.animate().cancel();
        this.overflowPanel.animate().cancel();
        this.toArrow.stop();
        this.toOverflow.stop();
    }

    private final void changeOverflowPanelAdapterOrder() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.overflowMenuItems);
        if (this.openOverflowUpwards) {
            Collections.reverse(arrayList);
        }
        ListAdapter adapter = this.overflowPanel.getAdapter();
        j.c(adapter, "null cannot be cast to non-null type android.widget.ArrayAdapter<android.view.MenuItem>");
        ArrayAdapter arrayAdapter = (ArrayAdapter) adapter;
        arrayAdapter.clear();
        arrayAdapter.addAll(arrayList);
        this.overflowPanel.setAdapter(arrayAdapter);
        if (this.openOverflowUpwards) {
            this.overflowPanel.setSelection(arrayAdapter.getCount() - 1);
        }
    }

    private final void clearPanels() {
        this.overflowPanelSize = null;
        this.mainPanelSize = null;
        this.isOverflowOpen = false;
        this.mainPanel.removeAllViews();
        ListAdapter adapter = this.overflowPanel.getAdapter();
        j.c(adapter, "null cannot be cast to non-null type android.widget.ArrayAdapter<android.view.MenuItem>");
        ArrayAdapter arrayAdapter = (ArrayAdapter) adapter;
        arrayAdapter.clear();
        this.overflowPanel.setAdapter(arrayAdapter);
        this.contentContainer.removeAllViews();
    }

    private final void closeOverflow() {
        if (isNeedToChangeDirection() == 2) {
            boolean isInRtlMode = isInRtlMode();
            boolean z = this.isClosedOpposites;
            if (isInRtlMode == z) {
                this.isClosedOpposites = !z;
                float x9 = this.contentContainer.getX() + ((float) this.coordsOnWindow.x);
                Size size = this.mainPanelSize;
                j.b(size);
                if (x9 + ((float) size.getWidth()) > ((float) this.viewPortOnScreen.right)) {
                    shiftPopup();
                    this.isClosedOpposites = !this.isClosedOpposites;
                }
            } else {
                shiftPopup();
                this.isClosedOpposites = !this.isClosedOpposites;
            }
        }
        ImageView imageView = this.dividerHorizontal;
        j.b(imageView);
        imageView.setVisibility(4);
        PopupAnimationHelper popupAnimationHelper = this.animationHelper;
        Size size2 = this.mainPanelSize;
        j.b(size2);
        Size size3 = this.overflowPanelSize;
        j.b(size3);
        popupAnimationHelper.closeOverflowAnimation(size2, size3, this.contentContainer, this.isClosedOpposites, this.mainPanel, this.overflowPanel, this.overflowButton, this.openOverflowUpwards);
        this.isOverflowOpen = false;
    }

    private final ViewGroup createContentContainer(Context context2) {
        View inflate = LayoutInflater.from(context2).inflate(R.layout.popup_container, (ViewGroup) null);
        j.c(inflate, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) inflate;
        viewGroup.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        String str = ObjectCaptureToolbar.TAG;
        viewGroup.setTag(str);
        viewGroup.setContentDescription(str);
        viewGroup.setClipToOutline(true);
        return viewGroup;
    }

    private final void createDividers() {
        ImageView imageView = new ImageView(this.context);
        imageView.setImageResource(R.drawable.popup_divider);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        imageView.setImportantForAccessibility(2);
        imageView.setEnabled(false);
        imageView.setFocusable(false);
        imageView.setContentDescription((CharSequence) null);
        this.dividerVertical = imageView;
        ImageView imageView2 = new ImageView(this.context);
        imageView2.setImageResource(R.drawable.popup_divider_horizontal);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        imageView2.setImportantForAccessibility(2);
        imageView2.setEnabled(false);
        imageView2.setFocusable(false);
        imageView2.setContentDescription((CharSequence) null);
        this.dividerHorizontal = imageView2;
    }

    private final ViewGroup createMainPanel() {
        return new ObjectCapturePopup$createMainPanel$1(this, this.context);
    }

    private final Animation.AnimationListener createOverflowAnimationListener() {
        return new ObjectCapturePopup$createOverflowAnimationListener$listener$1(this);
    }

    private final ImageButton createOverflowButton() {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.popup_overflow_button, (ViewGroup) null);
        j.c(inflate, "null cannot be cast to non-null type android.widget.ImageButton");
        ImageButton imageButton = (ImageButton) inflate;
        imageButton.setImageDrawable(this.overflow);
        imageButton.semSetHoverPopupType(0);
        imageButton.setAccessibilityDelegate(this.accessDelegate);
        imageButton.setOnClickListener(new g(18, this, imageButton));
        return imageButton;
    }

    /* access modifiers changed from: private */
    public static final void createOverflowButton$lambda$3$lambda$2(ObjectCapturePopup objectCapturePopup, ImageButton imageButton, View view) {
        if (!objectCapturePopup.isDiscardTouch) {
            if (objectCapturePopup.isOverflowOpen) {
                imageButton.setImageDrawable(objectCapturePopup.overflow);
                objectCapturePopup.closeOverflow();
                return;
            }
            imageButton.setImageDrawable(objectCapturePopup.arrowSem);
            objectCapturePopup.openOverflow();
        }
    }

    private final OverflowPanel createOverflowPanel() {
        OverflowPanel overflowPanel2 = new OverflowPanel(this.context, this);
        overflowPanel2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        overflowPanel2.setDivider((Drawable) null);
        overflowPanel2.setDividerHeight(0);
        overflowPanel2.setAdapter(new ObjectCapturePopup$createOverflowPanel$1$1(this, overflowPanel2.getContext()));
        overflowPanel2.setOnItemClickListener(new b(this, overflowPanel2));
        return overflowPanel2;
    }

    /* access modifiers changed from: private */
    public static final void createOverflowPanel$lambda$5$lambda$4(ObjectCapturePopup objectCapturePopup, OverflowPanel overflowPanel2, AdapterView adapterView, View view, int i2, long j2) {
        j.e(view, "view");
        if (!objectCapturePopup.isDiscardTouch) {
            view.setAccessibilityDelegate(overflowPanel2.getAccessibilityDelegate());
            Object item = overflowPanel2.getAdapter().getItem(i2);
            j.c(item, "null cannot be cast to non-null type android.view.MenuItem");
            objectCapturePopup.menuHelper.onMenuItemClick((MenuItem) item);
        }
    }

    private final PopupWindow createPopupWindow(ViewGroup viewGroup) {
        LinearLayout linearLayout = new LinearLayout(viewGroup.getContext());
        linearLayout.setGravity(0);
        PopupWindow popupWindow2 = new PopupWindow(linearLayout);
        popupWindow2.setClippingEnabled(false);
        popupWindow2.setAnimationStyle(0);
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0));
        viewGroup.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        linearLayout.addView(viewGroup);
        return popupWindow2;
    }

    private final int getAdjustedToolbarWidth(int i2) {
        refreshViewPort();
        int width = this.viewPortOnScreen.width() - (this.context.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_horizontal_margin) * 2);
        if (i2 <= 0) {
            i2 = this.context.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_preferred_width);
        }
        return (int) Math.min((double) i2, (double) width);
    }

    private final DisplayCutout getDisplayCutout() {
        WindowInsets rootWindowInsets;
        DisplayCutout displayCutout;
        View view = this.parentRoot;
        if (view != null && (rootWindowInsets = view.getRootWindowInsets()) != null && (displayCutout = rootWindowInsets.getDisplayCutout()) != null) {
            return displayCutout;
        }
        this.cutoutRightMargin = 0;
        this.cutoutLeftMargin = 0;
        return null;
    }

    private final int getOverflowWidth() {
        int count = this.overflowPanel.getAdapter().getCount();
        int i2 = 0;
        for (int i7 = 0; i7 < count; i7++) {
            Object item = this.overflowPanel.getAdapter().getItem(i7);
            j.c(item, "null cannot be cast to non-null type android.view.MenuItem");
            i2 = (int) Math.max((double) this.overflowPanelViewHelper.calculateWidth((MenuItem) item), (double) i2);
        }
        return (int) Math.min((double) i2, (double) (this.viewPortOnScreen.width() - (this.marginHorizontal * 2)));
    }

    private final int getViewPortVisibleHeight() {
        if (this.multiWindowManager.getMode() == 2) {
            return this.viewPortOnScreen.bottom;
        }
        int i2 = this.context.getResources().getDisplayMetrics().heightPixels;
        if (this.multiWindowManager.getMode() != 0) {
            return i2 + this.viewPortOnScreen.top;
        }
        return i2;
    }

    private final boolean hasOverflow() {
        if (this.overflowPanelSize != null) {
            return true;
        }
        return false;
    }

    private final boolean isCutoutMarginSet() {
        if (this.cutoutLeftMargin == 0 && this.cutoutRightMargin == 0) {
            return false;
        }
        return true;
    }

    private final boolean isInRtlMode() {
        if (this.context.getResources().getConfiguration().getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    private final boolean isInsideOfViewPortRect(float f, float f5) {
        refreshViewPort();
        Rect rect = this.viewPortOnScreen;
        if (((float) rect.left) > f || ((float) rect.right) < f || ((float) rect.top) > f5 || ((float) rect.bottom) < f5) {
            return false;
        }
        return true;
    }

    private final int isNeedToChangeDirection() {
        Rect rect = new Rect(0, 0, this.popupWindow.getWidth(), this.popupWindow.getHeight());
        Rect rect2 = new Rect(0, 0, this.popupWindow.getWidth(), this.popupWindow.getHeight());
        Size size = this.mainPanelSize;
        j.b(size);
        int width = size.getWidth();
        Size size2 = this.overflowPanelSize;
        j.b(size2);
        int abs = (int) Math.abs((double) (width - size2.getWidth()));
        Size size3 = this.overflowPanelSize;
        j.b(size3);
        int height = size3.getHeight();
        Size size4 = this.mainPanelSize;
        j.b(size4);
        int height2 = height - size4.getHeight();
        if (this.openOverflowUpwards) {
            rect.bottom -= height2;
            rect2.top += height2;
        } else {
            rect.top += height2;
            rect2.bottom -= height2;
        }
        int i2 = rect.top;
        int i7 = this.marginVertical;
        rect.top = i2 + i7;
        rect.bottom -= i7;
        rect2.top += i7;
        rect2.bottom -= i7;
        if (isInRtlMode() != this.isClosedOpposites) {
            int i8 = rect.left;
            int i10 = this.marginHorizontal;
            rect.left = abs + i10 + i8;
            rect.right -= i10;
            rect2.left = abs + i10 + rect2.left;
            rect2.right -= i10;
        } else {
            int i11 = rect.left;
            int i12 = this.marginHorizontal;
            rect.left = i11 + i12;
            rect.right -= abs + i12;
            rect2.left += i12;
            rect2.right -= abs + i12;
        }
        this.parent.getRootView().getLocationOnScreen(this.tempCoords);
        int[] iArr = this.tempCoords;
        int i13 = iArr[0];
        int i14 = iArr[1];
        this.parent.getRootView().getLocationInWindow(this.tempCoords);
        int[] iArr2 = this.tempCoords;
        int i15 = i13 - iArr2[0];
        int i16 = i14 - iArr2[1];
        Point point = this.coordsOnWindow;
        rect.offset(point.x, point.y);
        rect.offset(i15, i16);
        Point point2 = this.coordsOnWindow;
        rect2.offset(point2.x, point2.y);
        rect2.offset(i15, i16);
        Rect rect3 = new Rect();
        rect3.set(this.viewPortOnScreen);
        rect3.bottom = getViewPortVisibleHeight();
        if (rect3.contains(rect)) {
            return 0;
        }
        if (rect3.left > rect.left || rect3.right < rect.right) {
            if (rect3.top > rect.top || rect3.bottom < rect.bottom) {
                return 3;
            }
            return 2;
        } else if (rect3.contains(rect2)) {
            return 1;
        } else {
            if (rect3.bottom >= rect.bottom) {
                return 0;
            }
            LibLogger.d(TAG, "to prevent displaying overflow menu with bottom direction, force changing popup's overflow direction to upside");
            return 4;
        }
    }

    private final void layoutMenuItems(List<MenuItem> list, MenuItem.OnMenuItemClickListener onMenuItemClickListener, int i2) {
        cancelOverflowAnimations();
        clearPanels();
        this.menuHelper.updateMenuItems(list, onMenuItemClickListener);
        List<MenuItem> layoutMainPanelItems = layoutMainPanelItems(list, getAdjustedToolbarWidth(i2));
        if (!layoutMainPanelItems.isEmpty()) {
            layoutOverflowPanelItems(layoutMainPanelItems);
        }
        updatePopupSize();
    }

    private final void layoutOverflowPanelItems(List<? extends MenuItem> list) {
        this.overflowMenuItems.clear();
        this.overflowMenuItems.addAll(list);
        ListAdapter adapter = this.overflowPanel.getAdapter();
        j.c(adapter, "null cannot be cast to non-null type android.widget.ArrayAdapter<android.view.MenuItem>");
        ArrayAdapter arrayAdapter = (ArrayAdapter) adapter;
        arrayAdapter.clear();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayAdapter.add(list.get(i2));
        }
        this.overflowPanel.setAdapter(arrayAdapter);
        if (this.openOverflowUpwards) {
            this.overflowPanel.setY(0.0f);
        } else {
            this.overflowPanel.setY((float) this.overflowButtonSize.getHeight());
        }
        Size size2 = new Size((int) Math.max((double) getOverflowWidth(), (double) this.overflowButtonSize.getWidth()), calculateOverflowHeight(4));
        this.overflowPanelSize = size2;
        ViewUtils.setSize(this.overflowPanel, size2);
    }

    private final void maybeComputeTransitionDurationScale() {
        Size size = this.mainPanelSize;
        if (size != null && this.overflowPanelSize != null) {
            j.b(size);
            int width = size.getWidth();
            Size size2 = this.overflowPanelSize;
            j.b(size2);
            int width2 = width - size2.getWidth();
            Size size3 = this.overflowPanelSize;
            j.b(size3);
            int height = size3.getHeight();
            Size size4 = this.mainPanelSize;
            j.b(size4);
            int height2 = height - size4.getHeight();
            this.animationHelper.setTransitionDurationScale((int) (Math.sqrt((double) ((height2 * height2) + (width2 * width2))) / ((double) this.contentContainer.getContext().getResources().getDisplayMetrics().density)));
        }
    }

    private final void onTouchOnWindow(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.lastTouchDownX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            this.lastTouchDownY = rawY;
            this.prevTouchX = this.lastTouchDownX;
            this.prevTouchY = rawY;
            this.isDiscardTouch = false;
            this.isScrolling = false;
            this.isMovingFirstTime = false;
        } else if (action == 2) {
            this.moved = true;
            if (!this.isScrolling) {
                float rawX = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                this.deltaX = (int) (rawX - this.prevTouchX);
                this.deltaY = (int) (rawY2 - this.prevTouchY);
                int i2 = (int) (rawX - this.lastTouchDownX);
                int i7 = (int) (rawY2 - this.lastTouchDownY);
                boolean z = this.isMovingFirstTime;
                int i8 = (i7 * i7) + (i2 * i2);
                int i10 = this.touchSlop;
                if (i8 >= i10 * i10) {
                    this.isDiscardTouch = true;
                    this.isMovingStarted = true;
                    this.isMovingFirstTime = true;
                }
                if (z != this.isMovingFirstTime) {
                    String str = TAG;
                    StringBuilder h5 = a.h(i2, i7, "\n                                FloatingToolbar will be start to move, moved deltaX, deltaY : ", ArcCommonLog.TAG_COMMA, "\n                                mTouchSlop = ");
                    h5.append(i10);
                    h5.append("\n                                ");
                    LibLogger.d(str, o.l0(h5.toString()));
                }
                if (this.isDiscardTouch) {
                    if (isInsideOfViewPortRect(rawX, rawY2)) {
                        Point point = this.coordsOnWindow;
                        calculateCoords(point.x + this.deltaX, point.y + this.deltaY);
                    }
                    recalCoordsOnWindowX();
                    PopupWindow popupWindow2 = this.popupWindow;
                    Point point2 = this.coordsOnWindow;
                    popupWindow2.update(point2.x, point2.y, popupWindow2.getWidth(), this.popupWindow.getHeight());
                    this.prevTouchX = rawX;
                    this.prevTouchY = rawY2;
                }
            }
        }
    }

    private final void openOverflow() {
        int isNeedToChangeDirection = isNeedToChangeDirection();
        if (isNeedToChangeDirection == 1 || isNeedToChangeDirection == 3) {
            this.openOverflowUpwards = !this.openOverflowUpwards;
        } else if (isNeedToChangeDirection == 4) {
            this.openOverflowUpwards = true;
        }
        String str = TAG;
        boolean z = this.openOverflowUpwards;
        LibLogger.d(str, "overflow menu upwards : " + z);
        if (isNeedToChangeDirection == 2 || isNeedToChangeDirection == 3) {
            boolean isInRtlMode = isInRtlMode();
            boolean z3 = this.isClosedOpposites;
            if (isInRtlMode == z3) {
                this.isClosedOpposites = !z3;
                float x9 = this.contentContainer.getX() + ((float) this.coordsOnWindow.x);
                Size size = this.overflowPanelSize;
                j.b(size);
                if (x9 + ((float) size.getWidth()) > ((float) this.viewPortOnScreen.right)) {
                    shiftPopup();
                    this.isClosedOpposites = !this.isClosedOpposites;
                }
            } else {
                shiftPopup();
                this.isClosedOpposites = !this.isClosedOpposites;
            }
        }
        changeOverflowPanelAdapterOrder();
        PopupAnimationHelper popupAnimationHelper = this.animationHelper;
        Size size2 = this.mainPanelSize;
        j.b(size2);
        Size size3 = this.overflowPanelSize;
        j.b(size3);
        popupAnimationHelper.openOverflowAnimation(size2, size3, this.contentContainer, this.isClosedOpposites, this.mainPanel, this.overflowPanel, this.overflowButton, this.openOverflowUpwards);
        this.isOverflowOpen = true;
    }

    private final void preparePopupContent() {
        this.contentContainer.removeAllViews();
        if (hasOverflow()) {
            this.contentContainer.addView(this.overflowPanel);
        }
        this.contentContainer.addView(this.mainPanel);
        if (hasOverflow()) {
            this.contentContainer.addView(this.overflowButton);
            this.contentContainer.addView(this.dividerHorizontal);
        }
        setPanelsStatesAtRestingPosition();
        setContentAreaAsTouchableSurface();
        if (isInRtlMode()) {
            this.contentContainer.setAlpha(0.0f);
            this.contentContainer.post(this.preparePopupContentRtlHelper);
        }
    }

    private final void recalCoordsOnWindowX() {
        DisplayCutout displayCutout = getDisplayCutout();
        if (displayCutout != null) {
            setCutoutMarginValue(displayCutout);
        }
        if (isCutoutMarginSet()) {
            Object systemService = this.context.getSystemService("window");
            j.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            int rotation = ((WindowManager) systemService).getDefaultDisplay().getRotation();
            if (rotation != 1) {
                if (rotation == 3) {
                    int width = this.popupWindow.getWidth();
                    if (hasOverflow()) {
                        Size size = this.overflowPanelSize;
                        j.b(size);
                        width = (size.getWidth() + width) / 2;
                    }
                    Point point = this.coordsOnWindow;
                    int i2 = point.x;
                    int i7 = i2 + width;
                    int i8 = this.cutoutRightMargin;
                    if (i7 > i8) {
                        i2 = i8 - width;
                    }
                    point.x = i2;
                }
            } else if (!isInRtlMode()) {
                Point point2 = this.coordsOnWindow;
                point2.x = (int) Math.max((double) point2.x, (double) this.cutoutLeftMargin);
            }
        }
    }

    private final void refreshCoordinatesAndOverflowDirection(Rect rect) {
        int i2;
        refreshViewPort();
        if (this.isMovingStarted) {
            LibLogger.w(TAG, "refresh fails : isMovingStarted");
            return;
        }
        int i7 = rect.top;
        Rect rect2 = this.viewPortOnScreen;
        rect.top = i7 + rect2.top;
        rect.left += rect2.left;
        rect.right += rect2.left;
        rect.bottom += rect2.top;
        int refreshXCoordinates = refreshXCoordinates(rect);
        int refreshYCoordinates = refreshYCoordinates(rect);
        int i8 = this.popupVerticalOffset;
        if (hasOverflow()) {
            if (isInRtlMode()) {
                Size size = this.mainPanelSize;
                j.b(size);
                int width = size.getWidth();
                Size size2 = this.overflowPanelSize;
                j.b(size2);
                i2 = 0 - (width - size2.getWidth());
            } else {
                i2 = 0;
            }
            if (!this.openOverflowUpwards) {
                Size size3 = this.overflowPanelSize;
                j.b(size3);
                int height = size3.getHeight();
                Size size4 = this.mainPanelSize;
                j.b(size4);
                i8 -= height - size4.getHeight();
            }
        } else {
            i2 = 0;
        }
        this.parent.getRootView().getLocationOnScreen(this.tempCoords);
        int[] iArr = this.tempCoords;
        int i10 = iArr[0];
        int i11 = iArr[1];
        this.parent.getRootView().getLocationInWindow(this.tempCoords);
        int[] iArr2 = this.tempCoords;
        int i12 = i10 - iArr2[0];
        int i13 = i11 - iArr2[1];
        this.coordsOnWindow.x = (int) Math.max(Math.max((double) this.viewPortOnScreen.left, MapUtil.INVALID_LOCATION) - ((double) i12), (double) (refreshXCoordinates - i12));
        this.coordsOnWindow.y = (int) Math.max(Math.max((double) this.viewPortOnScreen.top, MapUtil.INVALID_LOCATION) - ((double) i13), (double) (refreshYCoordinates - i13));
        this.coordsOnWindow.offset(i2, i8);
        if (!this.moved) {
            this.movedPos.set(0, 0);
            Point point = this.originalPos;
            Point point2 = this.coordsOnWindow;
            point.set(point2.x, point2.y);
        }
    }

    private final void refreshViewPort() {
        this.parent.getWindowVisibleDisplayFrame(this.viewPortOnScreen);
        int[] iArr = new int[2];
        this.parent.getLocationOnScreen(iArr);
        Rect rect = this.viewPortOnScreen;
        rect.top = iArr[1];
        rect.left = iArr[0];
    }

    private final int refreshXCoordinates(Rect rect) {
        double min;
        if (hasOverflow()) {
            int width = this.popupWindow.getWidth();
            Size size = this.overflowPanelSize;
            j.b(size);
            int width2 = (size.getWidth() + width) / 2;
            min = Math.min((double) (rect.centerX() - (width2 / 2)), (double) ((this.viewPortOnScreen.right - width2) - this.marginHorizontal));
        } else {
            min = Math.min((double) (rect.centerX() - (this.popupWindow.getWidth() / 2)), (double) (this.viewPortOnScreen.right - this.popupWindow.getWidth()));
        }
        return (int) min;
    }

    private final int refreshYCoordinates(Rect rect) {
        int i2 = rect.top;
        Rect rect2 = this.viewPortOnScreen;
        int i7 = i2 - rect2.top;
        int i8 = rect2.bottom - rect.bottom;
        int i10 = this.marginVertical * 2;
        int i11 = this.lineHeight + i10;
        if (hasOverflow()) {
            int calculateOverflowHeight = calculateOverflowHeight(1) + i10;
            Rect rect3 = this.viewPortOnScreen;
            int i12 = (rect3.bottom - rect.top) + i11;
            int i13 = (rect.bottom - rect3.top) + i11;
            int height = this.popupWindow.getHeight();
            Size size = this.overflowPanelSize;
            j.b(size);
            int height2 = size.getHeight();
            Size size2 = this.mainPanelSize;
            j.b(size2);
            int height3 = height - (height2 - size2.getHeight());
            if (i7 >= calculateOverflowHeight || this.isFlexMode) {
                if (i7 >= i12) {
                    updateOverflowHeight(i7 - i10);
                    this.openOverflowUpwards = true;
                    return rect.top - height3;
                }
                updateOverflowHeight(i12 - i10);
                this.openOverflowUpwards = false;
                return rect.top - i11;
            } else if (i7 >= i11 && i12 >= calculateOverflowHeight) {
                updateOverflowHeight(i12 - i10);
                this.openOverflowUpwards = false;
                return rect.top - i11;
            } else if (i8 >= calculateOverflowHeight) {
                if (i8 >= i13) {
                    this.openOverflowUpwards = false;
                    updateOverflowHeight(i8 - i10);
                    return rect.bottom;
                }
                updateOverflowHeight(i13 - i10);
                this.openOverflowUpwards = true;
                return (rect.bottom + i11) - height3;
            } else if (i8 < i11 || this.viewPortOnScreen.height() < calculateOverflowHeight) {
                updateOverflowHeight(this.viewPortOnScreen.height() - i10);
                this.openOverflowUpwards = false;
                return this.viewPortOnScreen.top;
            } else {
                updateOverflowHeight(i13 - i10);
                this.openOverflowUpwards = true;
                return (rect.bottom + i11) - height3;
            }
        } else if (i7 >= i11 || this.isFlexMode) {
            return rect.top - i11;
        } else {
            if (i8 >= i11) {
                return rect.bottom;
            }
            if (i8 >= this.lineHeight) {
                return rect.bottom - this.marginVertical;
            }
            return (int) Math.max((double) this.viewPortOnScreen.top, (double) (rect.top - i11));
        }
    }

    /* access modifiers changed from: private */
    public final void setContentAreaAsTouchableSurface() {
        int i2;
        int i7;
        Objects.requireNonNull(this.mainPanelSize);
        if (this.isOverflowOpen) {
            Objects.requireNonNull(this.overflowPanelSize);
            Size size = this.overflowPanelSize;
            j.b(size);
            i7 = size.getWidth();
            Size size2 = this.overflowPanelSize;
            j.b(size2);
            i2 = size2.getHeight();
        } else {
            Size size3 = this.mainPanelSize;
            j.b(size3);
            i7 = size3.getWidth();
            Size size4 = this.mainPanelSize;
            j.b(size4);
            i2 = size4.getHeight();
        }
        this.toolbarVisibleRect.set(0, 0, i7, i2);
        this.touchableRegion.set((int) this.contentContainer.getX(), (int) this.contentContainer.getY(), ((int) this.contentContainer.getX()) + i7, ((int) this.contentContainer.getY()) + i2);
        Rect bounds = this.touchableRegion.getBounds();
        j.d(bounds, "getBounds(...)");
        int[] iArr = this.toolbarHiddenArea;
        Rect rect = this.toolbarVisibleRect;
        iArr[0] = rect.left - bounds.left;
        iArr[1] = rect.top - bounds.top;
    }

    private final void setCutoutMarginValue(DisplayCutout displayCutout) {
        List<Rect> boundingRects = displayCutout.getBoundingRects();
        j.d(boundingRects, "getBoundingRects(...)");
        if (!boundingRects.isEmpty()) {
            Rect rect = new Rect();
            View view = this.parentRoot;
            j.b(view);
            view.getWindowVisibleDisplayFrame(rect);
            for (Rect next : boundingRects) {
                int i2 = next.right;
                int i7 = next.left;
                int i8 = i2 - i7;
                if (i7 == 0) {
                    this.cutoutLeftMargin = i8;
                    this.cutoutRightMargin = rect.right;
                } else {
                    int i10 = rect.right;
                    if (i2 == i10) {
                        this.cutoutLeftMargin = 0;
                        this.cutoutRightMargin = i10 - i8;
                    } else {
                        this.cutoutRightMargin = 0;
                        this.cutoutLeftMargin = 0;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x02ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setPanelsStatesAtRestingPosition() {
        /*
            r6 = this;
            android.widget.ImageButton r0 = r6.overflowButton
            r1 = 1
            r0.setEnabled(r1)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r0 = r6.overflowPanel
            r0.awakenScrollBars()
            boolean r0 = r6.isOverflowOpen
            r1 = 1065353216(0x3f800000, float:1.0)
            r2 = 0
            r3 = 4
            r4 = 0
            if (r0 == 0) goto L_0x01a1
            android.util.Size r0 = r6.overflowPanelSize
            android.view.ViewGroup r5 = r6.contentContainer
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.ViewUtils.setSize(r5, r0)
            android.view.ViewGroup r5 = r6.mainPanel
            r5.setAlpha(r4)
            android.view.ViewGroup r5 = r6.mainPanel
            r5.setVisibility(r3)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r3 = r6.overflowPanel
            r3.setAlpha(r1)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setVisibility(r2)
            android.widget.ImageView r1 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r1)
            r1.setVisibility(r2)
            android.widget.ImageButton r1 = r6.overflowButton
            android.graphics.drawable.Drawable r2 = r6.arrowSem
            r1.setImageDrawable(r2)
            android.widget.ImageButton r1 = r6.overflowButton
            android.content.Context r2 = r6.context
            int r3 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_close_overflow_description
            java.lang.String r2 = r2.getString(r3)
            r1.setContentDescription(r2)
            android.util.Size r1 = r6.mainPanelSize
            kotlin.jvm.internal.j.b(r1)
            int r1 = r1.getWidth()
            android.util.Size r2 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            int r1 = r1 - r2
            if (r1 >= 0) goto L_0x009a
            double r1 = (double) r1
            double r1 = java.lang.Math.abs(r1)
            int r1 = (int) r1
            android.widget.PopupWindow r2 = r6.popupWindow
            int r2 = r2.getWidth()
            int r2 = r2 - r1
            android.util.Size r1 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r1)
            int r1 = r1.getWidth()
            int r2 = r2 - r1
            int r1 = r6.marginHorizontal
            int r2 = r2 - r1
            boolean r1 = r6.isInRtlMode()
            boolean r3 = r6.isClosedOpposites
            if (r1 == r3) goto L_0x00ad
            android.widget.PopupWindow r1 = r6.popupWindow
            int r1 = r1.getWidth()
            android.util.Size r2 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            int r1 = r1 - r2
            int r2 = r6.marginHorizontal
        L_0x0097:
            int r2 = r1 - r2
            goto L_0x00ad
        L_0x009a:
            android.widget.PopupWindow r1 = r6.popupWindow
            int r1 = r1.getWidth()
            android.util.Size r2 = r6.mainPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            int r1 = r1 - r2
            int r2 = r6.marginHorizontal
            goto L_0x0097
        L_0x00ad:
            android.view.ViewGroup r1 = r6.contentContainer
            float r2 = (float) r2
            r1.setX(r2)
            android.widget.ImageView r1 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r1)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.util.Size r2 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            r1.width = r2
            android.widget.ImageView r2 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r2)
            r2.setLayoutParams(r1)
            boolean r1 = r6.isInRtlMode()
            if (r1 == 0) goto L_0x00f9
            android.view.ViewGroup r1 = r6.mainPanel
            r1.setX(r4)
            android.widget.ImageButton r1 = r6.overflowButton
            int r2 = r0.getWidth()
            android.util.Size r3 = r6.overflowButtonSize
            int r3 = r3.getWidth()
            int r2 = r2 - r3
            float r2 = (float) r2
            r1.setX(r2)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setX(r4)
            android.widget.ImageView r1 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r1)
            r1.setX(r4)
            goto L_0x0117
        L_0x00f9:
            android.view.ViewGroup r1 = r6.mainPanel
            android.view.ViewGroup r2 = r6.contentContainer
            float r2 = r2.getX()
            float r2 = -r2
            r1.setX(r2)
            android.widget.ImageButton r1 = r6.overflowButton
            r1.setX(r4)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setX(r4)
            android.widget.ImageView r1 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r1)
            r1.setX(r4)
        L_0x0117:
            boolean r1 = r6.openOverflowUpwards
            if (r1 == 0) goto L_0x015f
            android.view.ViewGroup r1 = r6.contentContainer
            int r2 = r6.popupTopMargin
            float r2 = (float) r2
            r1.setY(r2)
            android.view.ViewGroup r1 = r6.mainPanel
            int r2 = r0.getHeight()
            android.view.ViewGroup r3 = r6.contentContainer
            int r3 = r3.getHeight()
            int r2 = r2 - r3
            float r2 = (float) r2
            r1.setY(r2)
            android.widget.ImageButton r1 = r6.overflowButton
            int r2 = r0.getHeight()
            android.util.Size r3 = r6.overflowButtonSize
            int r3 = r3.getHeight()
            int r2 = r2 - r3
            float r2 = (float) r2
            r1.setY(r2)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setY(r4)
            android.widget.ImageView r1 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r1)
            int r0 = r0.getHeight()
            android.util.Size r6 = r6.overflowButtonSize
            int r6 = r6.getHeight()
            int r0 = r0 - r6
            float r6 = (float) r0
            r1.setY(r6)
            return
        L_0x015f:
            android.view.ViewGroup r0 = r6.contentContainer
            int r1 = r6.popupTopMargin
            android.util.Size r2 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getHeight()
            android.util.Size r3 = r6.mainPanelSize
            kotlin.jvm.internal.j.b(r3)
            int r3 = r3.getHeight()
            int r2 = r2 - r3
            int r2 = r2 + r1
            float r1 = (float) r2
            r0.setY(r1)
            android.view.ViewGroup r0 = r6.mainPanel
            r0.setY(r4)
            android.widget.ImageButton r0 = r6.overflowButton
            r0.setY(r4)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r0 = r6.overflowPanel
            android.util.Size r1 = r6.overflowButtonSize
            int r1 = r1.getHeight()
            float r1 = (float) r1
            r0.setY(r1)
            android.widget.ImageView r0 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r0)
            android.util.Size r6 = r6.overflowButtonSize
            int r6 = r6.getHeight()
            float r6 = (float) r6
            r0.setY(r6)
            return
        L_0x01a1:
            android.util.Size r0 = r6.mainPanelSize
            android.view.ViewGroup r5 = r6.contentContainer
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.ViewUtils.setSize(r5, r0)
            android.view.ViewGroup r5 = r6.mainPanel
            r5.setAlpha(r1)
            android.view.ViewGroup r1 = r6.mainPanel
            r1.setVisibility(r2)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setAlpha(r4)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setVisibility(r3)
            android.widget.ImageButton r1 = r6.overflowButton
            android.graphics.drawable.Drawable r2 = r6.overflow
            r1.setImageDrawable(r2)
            android.widget.ImageButton r1 = r6.overflowButton
            android.content.Context r2 = r6.context
            int r5 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_open_overflow_description
            java.lang.String r2 = r2.getString(r5)
            r1.setContentDescription(r2)
            android.widget.ImageView r1 = r6.dividerHorizontal
            kotlin.jvm.internal.j.b(r1)
            r1.setVisibility(r3)
            boolean r1 = r6.hasOverflow()
            if (r1 == 0) goto L_0x02c7
            android.util.Size r1 = r6.mainPanelSize
            kotlin.jvm.internal.j.b(r1)
            int r1 = r1.getWidth()
            android.util.Size r2 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            int r1 = r1 - r2
            if (r1 >= 0) goto L_0x020a
            android.widget.PopupWindow r1 = r6.popupWindow
            int r1 = r1.getWidth()
            android.util.Size r2 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            int r1 = r1 - r2
            int r2 = r6.marginHorizontal
        L_0x0208:
            int r1 = r1 - r2
            goto L_0x023a
        L_0x020a:
            android.widget.PopupWindow r2 = r6.popupWindow
            int r2 = r2.getWidth()
            int r2 = r2 - r1
            android.util.Size r1 = r6.mainPanelSize
            kotlin.jvm.internal.j.b(r1)
            int r1 = r1.getWidth()
            int r2 = r2 - r1
            int r1 = r6.marginHorizontal
            int r1 = r2 - r1
            boolean r2 = r6.isInRtlMode()
            boolean r3 = r6.isClosedOpposites
            if (r2 == r3) goto L_0x023a
            android.widget.PopupWindow r1 = r6.popupWindow
            int r1 = r1.getWidth()
            android.util.Size r2 = r6.mainPanelSize
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.getWidth()
            int r1 = r1 - r2
            int r2 = r6.marginHorizontal
            goto L_0x0208
        L_0x023a:
            android.view.ViewGroup r2 = r6.contentContainer
            float r1 = (float) r1
            r2.setX(r1)
            boolean r1 = r6.isInRtlMode()
            if (r1 == 0) goto L_0x0256
            android.view.ViewGroup r1 = r6.mainPanel
            r1.setX(r4)
            android.widget.ImageButton r1 = r6.overflowButton
            r1.setX(r4)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            r1.setX(r4)
            goto L_0x0280
        L_0x0256:
            android.view.ViewGroup r1 = r6.mainPanel
            r1.setX(r4)
            android.widget.ImageButton r1 = r6.overflowButton
            int r2 = r0.getWidth()
            android.util.Size r3 = r6.overflowButtonSize
            int r3 = r3.getWidth()
            int r2 = r2 - r3
            float r2 = (float) r2
            r1.setX(r2)
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            int r2 = r0.getWidth()
            android.util.Size r3 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r3)
            int r3 = r3.getWidth()
            int r2 = r2 - r3
            float r2 = (float) r2
            r1.setX(r2)
        L_0x0280:
            android.view.ViewGroup r1 = r6.contentContainer
            int r2 = r6.popupTopMargin
            android.util.Size r3 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r3)
            int r3 = r3.getHeight()
            int r3 = r3 + r2
            int r2 = r0.getHeight()
            int r3 = r3 - r2
            float r2 = (float) r3
            r1.setY(r2)
            android.view.ViewGroup r1 = r6.mainPanel
            r1.setY(r4)
            android.widget.ImageButton r1 = r6.overflowButton
            r1.setY(r4)
            boolean r1 = r6.openOverflowUpwards
            if (r1 == 0) goto L_0x02ba
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r1 = r6.overflowPanel
            int r0 = r0.getHeight()
            android.util.Size r6 = r6.overflowPanelSize
            kotlin.jvm.internal.j.b(r6)
            int r6 = r6.getHeight()
            int r0 = r0 - r6
            float r6 = (float) r0
            r1.setY(r6)
            return
        L_0x02ba:
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel r0 = r6.overflowPanel
            android.util.Size r6 = r6.overflowButtonSize
            int r6 = r6.getHeight()
            float r6 = (float) r6
            r0.setY(r6)
            return
        L_0x02c7:
            android.view.ViewGroup r0 = r6.contentContainer
            int r1 = r6.marginHorizontal
            float r1 = (float) r1
            r0.setX(r1)
            android.view.ViewGroup r0 = r6.contentContainer
            int r1 = r6.popupTopMargin
            float r1 = (float) r1
            r0.setY(r1)
            android.view.ViewGroup r0 = r6.mainPanel
            r0.setX(r4)
            android.view.ViewGroup r6 = r6.mainPanel
            r6.setY(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup.setPanelsStatesAtRestingPosition():void");
    }

    private final void setTouchableSurfaceInsetsComputer() {
        ViewTreeObserver viewTreeObserver = this.popupWindow.getContentView().getRootView().getViewTreeObserver();
        j.d(viewTreeObserver, "getViewTreeObserver(...)");
        try {
            ReflectionContainer.getViewTreeObserver().removeOnComputeInternalInsetsListener(viewTreeObserver, this.insetsComputer.getProxyInstance());
        } catch (Exception e) {
            Log.e(ObjectCaptureToolbar.TAG, e.toString());
        }
        ReflectionContainer.getViewTreeObserver().addOnComputeInternalInsetsListener(viewTreeObserver, this.insetsComputer.getProxyInstance());
    }

    private final void setZeroTouchableSurface() {
        this.touchableRegion.setEmpty();
    }

    private final void shiftPopup() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.coordsOnWindow.x, this.viewPortOnScreen.left - this.marginHorizontal});
        ofInt.addUpdateListener(new h(9, this));
        ofInt.setDuration(100);
        ofInt.start();
    }

    /* access modifiers changed from: private */
    public static final void shiftPopup$lambda$6(ObjectCapturePopup objectCapturePopup, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Point point = objectCapturePopup.coordsOnWindow;
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        point.x = ((Integer) animatedValue).intValue();
        objectCapturePopup.recalCoordsOnWindowX();
        PopupWindow popupWindow2 = objectCapturePopup.popupWindow;
        Point point2 = objectCapturePopup.coordsOnWindow;
        popupWindow2.update(point2.x, point2.y, popupWindow2.getWidth(), objectCapturePopup.popupWindow.getHeight());
    }

    private final void updateCoordinates(Rect rect) {
        if (isShowing() && this.popupWindow.isShowing()) {
            cancelOverflowAnimations();
            refreshCoordinatesAndOverflowDirection(rect);
            preparePopupContent();
            recalCoordsOnWindowX();
            PopupWindow popupWindow2 = this.popupWindow;
            Point point = this.coordsOnWindow;
            popupWindow2.update(point.x, point.y, popupWindow2.getWidth(), this.popupWindow.getHeight());
        }
    }

    private final void updateOverflowHeight(int i2) {
        if (hasOverflow()) {
            int calculateOverflowHeight = calculateOverflowHeight((i2 - this.overflowButtonSize.getHeight()) / this.lineHeight);
            if (calculateOverflowHeight <= i2) {
                i2 = calculateOverflowHeight;
            }
            Size size = this.overflowPanelSize;
            j.b(size);
            if (size.getHeight() != i2) {
                Size size2 = this.overflowPanelSize;
                j.b(size2);
                this.overflowPanelSize = new Size(size2.getWidth(), i2);
            }
            OverflowPanel overflowPanel2 = this.overflowPanel;
            Size size3 = this.overflowPanelSize;
            j.b(size3);
            ViewUtils.setSize(overflowPanel2, size3);
            if (this.isOverflowOpen) {
                ViewGroup viewGroup = this.contentContainer;
                Size size4 = this.overflowPanelSize;
                j.b(size4);
                ViewUtils.setSize(viewGroup, size4);
                if (this.openOverflowUpwards) {
                    Size size5 = this.overflowPanelSize;
                    j.b(size5);
                    int height = size5.getHeight() - i2;
                    ViewGroup viewGroup2 = this.contentContainer;
                    float f = (float) height;
                    viewGroup2.setY(viewGroup2.getY() + f);
                    ImageButton imageButton = this.overflowButton;
                    imageButton.setY(imageButton.getY() - f);
                }
            } else {
                ViewGroup viewGroup3 = this.contentContainer;
                Size size6 = this.mainPanelSize;
                j.b(size6);
                ViewUtils.setSize(viewGroup3, size6);
            }
            updatePopupSize();
        }
    }

    private final void updatePopupSize() {
        int i2;
        Size size = this.mainPanelSize;
        int i7 = 0;
        if (size != null) {
            double d = (double) 0;
            j.b(size);
            int max = (int) Math.max(d, (double) size.getWidth());
            Size size2 = this.mainPanelSize;
            j.b(size2);
            int max2 = (int) Math.max(d, (double) size2.getHeight());
            i7 = max;
            i2 = max2;
        } else {
            i2 = 0;
        }
        Size size3 = this.overflowPanelSize;
        if (size3 != null) {
            j.b(size3);
            i7 = (int) Math.max((double) i7, (double) size3.getWidth());
            double d2 = (double) i2;
            Size size4 = this.overflowPanelSize;
            j.b(size4);
            i2 = (int) Math.max(d2, (double) size4.getHeight());
            Size size5 = this.mainPanelSize;
            if (size5 != null) {
                j.b(size5);
                int width = size5.getWidth();
                Size size6 = this.overflowPanelSize;
                j.b(size6);
                i7 += (int) Math.abs((double) (width - size6.getWidth()));
                Size size7 = this.mainPanelSize;
                j.b(size7);
                i2 = (i2 * 2) - size7.getHeight();
            }
        }
        this.popupWindow.setWidth((this.marginHorizontal * 2) + i7);
        this.popupWindow.setHeight((this.marginVertical * 2) + i2);
        maybeComputeTransitionDurationScale();
    }

    public final View createMenuItemButton(Context context2, MenuItem menuItem, boolean z) {
        View inflate = LayoutInflater.from(context2).inflate(R.layout.popup_menu_button, (ViewGroup) null);
        if (menuItem != null) {
            j.b(inflate);
            updateMenuItemButton(inflate, menuItem, z);
        }
        inflate.semSetHoverPopupType(0);
        return inflate;
    }

    public final void dismiss() {
        if (!this.isDismissed) {
            this.isHidden = false;
            this.isDismissed = true;
            this.animationHelper.runDismissAnimation();
            setZeroTouchableSurface();
        }
    }

    public final Size getOverflowButtonSize() {
        return this.overflowButtonSize;
    }

    public final Size getOverflowPanelSize() {
        return this.overflowPanelSize;
    }

    public final void hide() {
        if (isShowing()) {
            this.isHidden = true;
            this.animationHelper.runHideAnimation();
            setZeroTouchableSurface();
        }
    }

    public final boolean isDiscardTouch() {
        return this.isDiscardTouch;
    }

    public final boolean isDismissed() {
        return this.isDismissed;
    }

    public final boolean isMovingStarted() {
        return this.isMovingStarted;
    }

    public final boolean isOverflowAnimating() {
        return this.animationHelper.isOverflowAnimating();
    }

    public final boolean isScrolling() {
        return this.isScrolling;
    }

    public final boolean isShowing() {
        if (this.isDismissed || this.isHidden) {
            return false;
        }
        return true;
    }

    public final List<MenuItem> layoutMainPanelItems(List<MenuItem> list, int i2) {
        boolean z;
        boolean z3;
        boolean z7;
        j.e(list, "menuItems");
        this.mainPanel.removeAllViews();
        this.mainPanel.setPaddingRelative(0, 0, 0, 0);
        int i7 = i2;
        int i8 = 0;
        int i10 = 0;
        while (i8 < list.size() && list.size() > i8) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) list.get(i8);
            if (4 > i10) {
                j.b(menuItemImpl);
                if (!menuItemImpl.requiresOverflow()) {
                    View createMenuItemButton = createMenuItemButton(this.context, menuItemImpl, true);
                    int i11 = this.menuSidePadding;
                    if (i10 == 0) {
                        i11 = this.menuFirstLastSidePadding;
                    }
                    createMenuItemButton.setPaddingRelative(i11, createMenuItemButton.getPaddingTop(), createMenuItemButton.getPaddingEnd(), createMenuItemButton.getPaddingBottom());
                    if (list.size() == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        createMenuItemButton.setPaddingRelative(createMenuItemButton.getPaddingStart(), createMenuItemButton.getPaddingTop(), this.menuFirstLastSidePadding, createMenuItemButton.getPaddingBottom());
                    }
                    createMenuItemButton.measure(0, 0);
                    int min = (int) Math.min((double) createMenuItemButton.getMeasuredWidth(), (double) i2);
                    if (min <= i7 - this.overflowButtonSize.getWidth()) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z || min > i7) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    if (!z3 && !z7) {
                        break;
                    }
                    this.menuHelper.setButtonTagAndClickListener(createMenuItemButton, menuItemImpl);
                    createMenuItemButton.setTooltipText(menuItemImpl.getTooltipText());
                    this.mainPanel.addView(createMenuItemButton);
                    ViewGroup.LayoutParams layoutParams = createMenuItemButton.getLayoutParams();
                    layoutParams.width = min;
                    createMenuItemButton.setLayoutParams(layoutParams);
                    i7 -= min;
                    list.remove(i8);
                    i10++;
                } else {
                    i8++;
                }
            } else {
                break;
            }
        }
        if (!list.isEmpty()) {
            ViewGroup viewGroup = this.mainPanel;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            if (childAt != null) {
                int paddingEnd = childAt.getPaddingEnd();
                childAt.setPaddingRelative(childAt.getPaddingStart(), childAt.getPaddingTop(), paddingEnd * 2, childAt.getPaddingBottom());
                ViewGroup.LayoutParams layoutParams2 = childAt.getLayoutParams();
                layoutParams2.width += paddingEnd;
                childAt.setLayoutParams(layoutParams2);
                this.mainPanel.addView(this.dividerVertical);
            }
            this.mainPanel.setPaddingRelative(0, 0, this.overflowButtonSize.getWidth(), 0);
        }
        this.mainPanelSize = ViewUtils.measure(this.mainPanel);
        return list;
    }

    public final void onDetachFromWindow() {
        this.animationHelper.cancelAnimation();
        if (this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
        }
    }

    public final void setDiscardTouch(boolean z) {
        this.isDiscardTouch = z;
    }

    public final void setFlexMode(boolean z) {
        this.isFlexMode = z;
    }

    public final void setMenuTitleColor(int i2) {
        this.menuTitleColor = Integer.valueOf(i2);
    }

    public final void setMovingStarted(boolean z) {
        this.isMovingStarted = z;
    }

    public final void setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.menuHelper.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public final void setOverflowPanelSize(Size size) {
        this.overflowPanelSize = size;
    }

    public final void setScrolling(boolean z) {
        this.isScrolling = z;
    }

    public final void setSuggestedWidth(int i2) {
        boolean z;
        if (((double) ((int) Math.abs((double) (i2 - this.suggestedWidth)))) > ((double) this.suggestedWidth) * 0.2d) {
            z = true;
        } else {
            z = false;
        }
        this.widthChanged = z;
        this.suggestedWidth = i2;
    }

    public final void setToolbarActionListener(ToolbarActionListener toolbarActionListener) {
        this.menuHelper.setToolbarActionListener(toolbarActionListener);
    }

    public final void setToolbarMenuItem(List<ToolbarMenuItem> list) {
        j.e(list, "list");
        this.menuHelper.setToolbarMenuItem(list);
    }

    public final void setWidthChanged(boolean z) {
        this.widthChanged = z;
    }

    public final void show(List<MenuItem> list, MenuItem.OnMenuItemClickListener onMenuItemClickListener, Rect rect) {
        j.e(list, "menuItemList");
        j.e(rect, "contentRect");
        if (this.menuHelper.isLayoutRequired(list) || this.widthChanged) {
            dismiss();
            layoutMenuItems(list, onMenuItemClickListener, this.suggestedWidth);
        } else {
            this.menuHelper.updateMenuItems(list, onMenuItemClickListener);
        }
        if (!isShowing()) {
            show(rect);
        } else if (!j.a(this.previousContentRect, rect)) {
            updateCoordinates(rect);
        }
        this.widthChanged = false;
        this.previousContentRect.set(rect);
    }

    public final void updateMenuItemButton(View view, MenuItem menuItem, boolean z) {
        j.e(view, "menuItemButton");
        j.e(menuItem, "menuItem");
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.object_capture_popup_icon_text_spacing);
        TextView textView = (TextView) view.findViewById(R.id.floating_toolbar_menu_item_text);
        textView.setEllipsize((TextUtils.TruncateAt) null);
        if (TextUtils.isEmpty(menuItem.getTitle())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(menuItem.getTitle());
            Integer num = this.menuTitleColor;
            if (num != null) {
                j.b(num);
                textView.setTextColor(num.intValue());
            }
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.floating_toolbar_menu_item_image);
        if (menuItem.getIcon() == null || !z) {
            imageView.setVisibility(8);
            textView.setPaddingRelative(0, 0, 0, 0);
        } else {
            imageView.setVisibility(0);
            imageView.setImageDrawable(menuItem.getIcon());
            textView.setPaddingRelative(dimensionPixelSize, 0, 0, 0);
        }
        CharSequence contentDescription = menuItem.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            view.setContentDescription(menuItem.getTitle());
        } else {
            view.setContentDescription(contentDescription);
        }
    }

    private final void show(Rect rect) {
        View view = this.parentRoot;
        if (view != null) {
            view.addOnAttachStateChangeListener(this.onAnchorRootDetachedListener);
        }
        this.isHidden = false;
        this.isDismissed = false;
        cancelOverflowAnimations();
        this.animationHelper.runShowAnimation();
        refreshCoordinatesAndOverflowDirection(rect);
        preparePopupContent();
        recalCoordsOnWindowX();
        PopupWindow popupWindow2 = this.popupWindow;
        View view2 = this.parent;
        Point point = this.coordsOnWindow;
        popupWindow2.showAtLocation(view2, 0, point.x, point.y);
        setTouchableSurfaceInsetsComputer();
    }
}
