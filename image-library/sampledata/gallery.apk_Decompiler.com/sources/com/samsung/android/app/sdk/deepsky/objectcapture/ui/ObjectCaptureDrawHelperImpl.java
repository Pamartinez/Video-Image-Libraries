package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import A.a;
import Sf.q;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.view.Display;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.MaskedObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.multi.MultiObjectViewManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.DeviceType;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.data.ImageInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.data.SelectableObject;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.BitmapUtil;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.RectUtil;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMData;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMListener;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPServiceSession;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.VideoClipper;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.VideoData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1196n;

@Metadata(d1 = {"\u0000Ö\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00022\u00020\u0001:\u0002\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0015H\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0019\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0018H\u0016¢\u0006\u0004\b \u0010\u001aJ\u001f\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016¢\u0006\u0004\b \u0010\u001fJ/\u0010(\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b(\u0010)J\u001f\u0010*\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016¢\u0006\u0004\b*\u0010\u001dJ\u0017\u0010,\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u0018H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u00100\u001a\u00020\b2\u0006\u0010/\u001a\u00020.H\u0017¢\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u00020\b2\u0006\u00103\u001a\u000202H\u0016¢\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020\u00182\u0006\u00107\u001a\u000206H\u0016¢\u0006\u0004\b8\u00109J\r\u0010:\u001a\u00020\b¢\u0006\u0004\b:\u0010;J\u000f\u0010<\u001a\u00020\bH\u0016¢\u0006\u0004\b<\u0010;J\u000f\u0010=\u001a\u00020\bH\u0016¢\u0006\u0004\b=\u0010;J\u000f\u0010>\u001a\u00020\bH\u0016¢\u0006\u0004\b>\u0010;J\u0017\u0010?\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b?\u0010@J\u0011\u0010A\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\bA\u0010BJ\u0017\u0010?\u001a\u00020\b2\u0006\u0010D\u001a\u00020CH\u0016¢\u0006\u0004\b?\u0010EJ\u0015\u0010G\u001a\b\u0012\u0004\u0012\u00020C0FH\u0016¢\u0006\u0004\bG\u0010HJ\u001f\u0010K\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u0018H\u0017¢\u0006\u0004\bK\u0010LJ\u0017\u0010K\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u0015H\u0016¢\u0006\u0004\bK\u0010MJ\u0017\u0010P\u001a\u00020\b2\u0006\u0010O\u001a\u00020NH\u0016¢\u0006\u0004\bP\u0010QJ\u0017\u0010S\u001a\u00020\b2\u0006\u0010R\u001a\u00020\u0018H\u0016¢\u0006\u0004\bS\u0010-J\u0017\u0010U\u001a\u00020\b2\u0006\u0010T\u001a\u00020\u0018H\u0016¢\u0006\u0004\bU\u0010-J\u0017\u0010V\u001a\u00020\b2\u0006\u0010O\u001a\u00020NH\u0016¢\u0006\u0004\bV\u0010QJ\u000f\u0010W\u001a\u00020\bH\u0016¢\u0006\u0004\bW\u0010;J\u000f\u0010X\u001a\u00020\bH\u0016¢\u0006\u0004\bX\u0010;J\u000f\u0010Y\u001a\u00020\bH\u0016¢\u0006\u0004\bY\u0010;J\u000f\u0010Z\u001a\u00020&H\u0016¢\u0006\u0004\bZ\u0010[J\u000f\u0010]\u001a\u00020\\H\u0016¢\u0006\u0004\b]\u0010^J\u0017\u0010a\u001a\u00020\b2\u0006\u0010`\u001a\u00020_H\u0016¢\u0006\u0004\ba\u0010bJ\u0017\u0010e\u001a\u00020\b2\u0006\u0010d\u001a\u00020cH\u0016¢\u0006\u0004\be\u0010fJ\u0017\u0010h\u001a\u00020\b2\u0006\u0010g\u001a\u00020\u000fH\u0016¢\u0006\u0004\bh\u0010\u0012J\u0017\u0010i\u001a\u00020\b2\u0006\u0010i\u001a\u00020\u0018H\u0016¢\u0006\u0004\bi\u0010-J\u0017\u0010l\u001a\u00020\b2\u0006\u0010k\u001a\u00020jH\u0016¢\u0006\u0004\bl\u0010mJ\u0017\u0010o\u001a\u00020\b2\u0006\u00103\u001a\u00020nH\u0016¢\u0006\u0004\bo\u0010pJ\u000f\u0010q\u001a\u00020\u0018H\u0016¢\u0006\u0004\bq\u0010\u001aJ\u0017\u0010s\u001a\u00020\b2\u0006\u00103\u001a\u00020rH\u0016¢\u0006\u0004\bs\u0010tJ\u000f\u0010u\u001a\u00020\bH\u0016¢\u0006\u0004\bu\u0010;J#\u0010y\u001a\u00020\b2\u0012\u0010x\u001a\n\u0012\u0006\b\u0001\u0012\u00020w0v\"\u00020wH\u0016¢\u0006\u0004\by\u0010zJ \u0010\u001a\u00020\b2\u0006\u0010|\u001a\u00020{2\u0006\u0010~\u001a\u00020}H\u0016¢\u0006\u0005\b\u0010\u0001J\u0011\u0010\u0001\u001a\u00020\u0018H\u0016¢\u0006\u0005\b\u0001\u0010\u001aJ\u001b\u0010\u0001\u001a\u00020\b2\u0007\u00103\u001a\u00030\u0001H\u0016¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b\u0001\u0010;J\u001a\u0010\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\u000bH\u0002¢\u0006\u0005\b\u0001\u0010\u000eJ\u001a\u0010\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\u000bH\u0002¢\u0006\u0005\b\u0001\u0010\u000eJ%\u0010\u0001\u001a\u00020\b2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u000bH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u0012\u0010\u0001\u001a\u00020NH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J#\u0010\u0001\u001a\u00030\u00012\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\"\u0010\u0001\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J!\u0010\u0001\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002¢\u0006\u0005\b\u0001\u0010\u001fJ>\u0010\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020N2\u0007\u0010\u0001\u001a\u00020N2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\b\u0010\u0001\u001a\u00030\u0001H\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b\u0001\u0010;J\"\u0010\u0001\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\"\u0010\u0001\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J)\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0007\u0010\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0005\b\u001e\u0010\u0001J\u0011\u0010 \u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b \u0001\u0010;J\u0011\u0010¡\u0001\u001a\u00020\bH\u0003¢\u0006\u0005\b¡\u0001\u0010;J\u0011\u0010¢\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b¢\u0001\u0010;J\u0011\u0010£\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b£\u0001\u0010;J\u001a\u0010¤\u0001\u001a\u00020\b2\u0006\u00107\u001a\u000206H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J\u0019\u0010¦\u0001\u001a\u00020\u00182\u0006\u00107\u001a\u000206H\u0002¢\u0006\u0005\b¦\u0001\u00109J\u0011\u0010§\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b§\u0001\u0010;J&\u0010«\u0001\u001a\u00020\u000f2\b\u0010©\u0001\u001a\u00030¨\u00012\b\u0010ª\u0001\u001a\u00030¨\u0001H\u0002¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u0011\u0010­\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0005\b­\u0001\u0010\u001aJ\u001a\u0010®\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0005\b®\u0001\u0010-J\u0011\u0010¯\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b¯\u0001\u0010;J\u0011\u0010°\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b°\u0001\u0010;J\u0011\u0010±\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b±\u0001\u0010;J*\u0010²\u0001\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002¢\u0006\u0006\b²\u0001\u0010³\u0001J\u001a\u0010´\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0005\b´\u0001\u0010-J\u0011\u0010µ\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\bµ\u0001\u0010;J\u001a\u0010¶\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0005\b¶\u0001\u0010-J\u0011\u0010·\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b·\u0001\u0010;J\u0011\u0010¸\u0001\u001a\u00020\bH\u0002¢\u0006\u0005\b¸\u0001\u0010;R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010¹\u0001R\u001b\u0010º\u0001\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bº\u0001\u0010»\u0001R\u001c\u0010½\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b½\u0001\u0010¾\u0001R\u001b\u0010¿\u0001\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b¿\u0001\u0010À\u0001R\u001b\u0010Á\u0001\u001a\u0004\u0018\u00010n8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u0019\u0010Ã\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÃ\u0001\u0010Ä\u0001R\u0018\u0010Æ\u0001\u001a\u00030Å\u00018\u0002X\u0004¢\u0006\b\n\u0006\bÆ\u0001\u0010Ç\u0001R\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\b\"\u0010È\u0001R\u0019\u0010É\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010Ä\u0001R\u001b\u0010Ê\u0001\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÊ\u0001\u0010Ë\u0001R\u001b\u0010Ì\u0001\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÌ\u0001\u0010Í\u0001R\u0019\u0010Î\u0001\u001a\u00020&8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÎ\u0001\u0010Í\u0001R\u001b\u0010Ï\u0001\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÏ\u0001\u0010Í\u0001R\u001a\u0010Ñ\u0001\u001a\u0005\u0018\u00010Ð\u00018\u0002X\u0004¢\u0006\b\n\u0006\bÑ\u0001\u0010Ò\u0001R\u001a\u0010Ô\u0001\u001a\u00030Ó\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010Õ\u0001R\u0018\u0010Ö\u0001\u001a\u00030¨\u00018\u0002X\u0004¢\u0006\b\n\u0006\bÖ\u0001\u0010×\u0001R\u0017\u0010Ø\u0001\u001a\u00020&8\u0002X\u0004¢\u0006\b\n\u0006\bØ\u0001\u0010Í\u0001R \u0010Ú\u0001\u001a\t\u0012\u0004\u0012\u00020C0Ù\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÚ\u0001\u0010Û\u0001R\u001b\u0010Ü\u0001\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÜ\u0001\u0010È\u0001R\u0019\u0010Ý\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÝ\u0001\u0010Ä\u0001R\u0019\u0010Þ\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÞ\u0001\u0010Ä\u0001R\u0019\u0010ß\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bß\u0001\u0010Ä\u0001R\u0017\u0010i\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\bi\u0010Ä\u0001R\u0017\u0010q\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\bq\u0010Ä\u0001R\u0017\u0010T\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\bT\u0010Ä\u0001R\u0019\u0010à\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bà\u0001\u0010Ä\u0001R\u0019\u0010á\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bá\u0001\u0010Ä\u0001R\u0019\u0010â\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bâ\u0001\u0010Ä\u0001R\u001c\u0010ã\u0001\u001a\u0005\u0018\u00010Å\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bã\u0001\u0010Ç\u0001R\u0019\u0010ä\u0001\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bä\u0001\u0010å\u0001R\u0017\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\b\u0010\u0010æ\u0001R\u0017\u0010\u0013\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\u0007\n\u0005\b\u0013\u0010æ\u0001R\u0019\u0010ç\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bç\u0001\u0010Ä\u0001R\u0018\u0010è\u0001\u001a\u00030¨\u00018\u0002X\u0004¢\u0006\b\n\u0006\bè\u0001\u0010×\u0001R\u0018\u0010é\u0001\u001a\u00030¨\u00018\u0002X\u0004¢\u0006\b\n\u0006\bé\u0001\u0010×\u0001R\u0018\u0010ê\u0001\u001a\u00030¨\u00018\u0002X\u0004¢\u0006\b\n\u0006\bê\u0001\u0010×\u0001R\u0018\u0010ë\u0001\u001a\u00030¨\u00018\u0002X\u0004¢\u0006\b\n\u0006\bë\u0001\u0010×\u0001R\u0019\u0010ì\u0001\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bì\u0001\u0010å\u0001R\u0019\u0010í\u0001\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bí\u0001\u0010Ä\u0001R\u001c\u0010ï\u0001\u001a\u0005\u0018\u00010î\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bï\u0001\u0010ð\u0001R\u001c\u0010ò\u0001\u001a\u0005\u0018\u00010ñ\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bò\u0001\u0010ó\u0001R\u001b\u0010ô\u0001\u001a\u0004\u0018\u0001028\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bô\u0001\u0010õ\u0001R\u001c\u0010ö\u0001\u001a\u0005\u0018\u00010\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bö\u0001\u0010÷\u0001R\u0018\u0010ù\u0001\u001a\u00030ø\u00018\u0002X\u0004¢\u0006\b\n\u0006\bù\u0001\u0010ú\u0001R\u001c\u0010ü\u0001\u001a\u0005\u0018\u00010û\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bü\u0001\u0010ý\u0001R\u001c\u0010ÿ\u0001\u001a\u0005\u0018\u00010þ\u00018\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÿ\u0001\u0010\u0002R\u001c\u0010\u0002\u001a\u0005\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0002\u0010\u0002R\u001c\u0010\u0002\u001a\u0005\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0002\u0010\u0002R\u0019\u0010\u0002\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0002\u0010Ä\u0001R\u0019\u0010\u0002\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0002\u0010æ\u0001R\u0019\u0010\u0002\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0002\u0010æ\u0001R\u001c\u0010\u0002\u001a\u0005\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0002\u0010\u0002R\u0016\u0010\u0002\u001a\u00020\u00188BX\u0004¢\u0006\u0007\u001a\u0005\b\u0002\u0010\u001a¨\u0006\u0002"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureDrawHelperImpl;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/ViewGroup;", "parentView", "Lme/x;", "init", "(Landroid/view/ViewGroup;)V", "", "scale", "setScaleFactor", "(F)V", "", "scaleState", "setOnScaleState", "(I)V", "translationState", "setOnTranslationState", "Landroid/graphics/Bitmap;", "getObjectCaptureBitmap", "()Landroid/graphics/Bitmap;", "", "isObjectSelected", "()Z", "x", "y", "(FF)I", "startObjectCaptureByLongClick", "(FF)Z", "startObjectCaptureByButton", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "objectResult", "index", "Landroid/graphics/PointF;", "point", "Landroid/graphics/Rect;", "cropRect", "startObjectCaptureByResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;ILandroid/graphics/PointF;Landroid/graphics/Rect;)V", "startObjectCaptureByTap", "immediately", "setShowToolbarImmediately", "(Z)V", "Landroid/graphics/Canvas;", "canvas", "dispatchDraw", "(Landroid/graphics/Canvas;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "listener", "setOnStartDragListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;)V", "Landroid/view/MotionEvent;", "event", "handleTouchEvent", "(Landroid/view/MotionEvent;)Z", "clearObjectCaptureView", "()V", "clearObjectCapture", "clearMaskedObjectResult", "clearDimView", "setObjectResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;)V", "getObjectResult", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "maskedObjectResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;)V", "", "getMaskedObjectResult", "()Ljava/util/List;", "bitmap", "isScale", "setBitmap", "(Landroid/graphics/Bitmap;Z)V", "(Landroid/graphics/Bitmap;)V", "Landroid/graphics/RectF;", "rect", "setBitmapRect", "(Landroid/graphics/RectF;)V", "isAnimated", "setAnimatedBitmapInfo", "isFlexMode", "setFlexMode", "updateObjectRect", "dismissToolbar", "hideToolbar", "showToolbar", "getObjectCaptureViewRect", "()Landroid/graphics/Rect;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "createToolbarMenuBuilder", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "toolbarMenu", "setToolbarMenu", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "deviceType", "setDeviceType", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;)V", "width", "setToolbarMaxWidth", "useDefaultMenu", "Landroid/view/Menu;", "menu", "addToolbarMenu", "(Landroid/view/Menu;)V", "Landroid/view/MenuItem$OnMenuItemClickListener;", "setToolbarOnMenuItemClickListener", "(Landroid/view/MenuItem$OnMenuItemClickListener;)V", "isSelectAll", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;", "connectGPPMSession", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;)V", "disconnectGPPMSession", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/LayerType;", "type", "setLayerView", "([Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/LayerType;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;", "data", "", "stickerPath", "generateGif", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;Ljava/lang/String;)V", "isVideoClippingSupported", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "setOnMoveClipListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;)V", "initInternal", "ratio", "setInitialSelection", "createObjectList", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;", "objectInfo", "makeSelectableObject", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;F)V", "calcTransformedRect", "()Landroid/graphics/RectF;", "", "calcTransformedTouchPoint", "(II)[F", "setNewSelectObject", "(II)I", "isValidObject", "src", "dst", "out", "pointTransform", "(Landroid/graphics/RectF;Landroid/graphics/RectF;FF[F)V", "calcObjectRectForDnd", "startMergedObjectCapture", "(FF)V", "updateScaledObject", "byButton", "(FFZ)Z", "updateScaledObjectRectInScreen", "playDndFeedbacks", "setDimView", "calcCaptureImageScaleFactor", "initTouchVariables", "(Landroid/view/MotionEvent;)V", "onTouchDown", "clearSelection", "Landroid/graphics/Point;", "source", "target", "getDistance", "(Landroid/graphics/Point;Landroid/graphics/Point;)I", "isOnScaleOrTranslation", "showObjectCaptureResult", "resetToolbar", "clearView", "clearMembersToHandleTouch", "updateObjectResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;FF)V", "initObjectSelection", "updateImageInfo", "showToolbarInternal", "prepareObjectCaptureToolbar", "prepareToolbarActionManager", "Landroid/content/Context;", "view", "Landroid/view/ViewGroup;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "objectCaptureToolbar", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "customMenu", "Landroid/view/Menu;", "customMenuItemClickListener", "Landroid/view/MenuItem$OnMenuItemClickListener;", "needToInit", "Z", "Landroid/graphics/Paint;", "paintFillClear", "Landroid/graphics/Paint;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "hitObject", "objectCaptureBitmap", "Landroid/graphics/Bitmap;", "objectInitRect", "Landroid/graphics/Rect;", "objectRectForDndInScreen", "scaledObjectRectInScreen", "Landroid/graphics/drawable/BitmapDrawable;", "objectCaptureBitmapDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/data/ImageInfo;", "imageInfo", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/data/ImageInfo;", "centerOffset", "Landroid/graphics/Point;", "viewRect", "", "maskedObjects", "Ljava/util/List;", "ArcSoftResult", "selectionStarted", "dimRemoving", "isImageScale", "isSelectionMode", "useOutGlowLayerView", "useParticleLayerView", "underLinePaint", "scaleFactor", "F", "I", "onScaleOrTranslation", "lastTouchPoint", "longDownPoint", "toolbarTouchPoint", "lastTranslatePoint", "dragTouchSlopSquare", "dragStarted", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView;", "objectCaptureView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DimView;", "dimView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DimView;", "dragListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "moveClipListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "videoClipper", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPServiceSession;", "gppServiceSession", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPServiceSession;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/multi/MultiObjectViewManager;", "multiObjectViewManager", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/multi/MultiObjectViewManager;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager;", "toolbarMenuManager", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionManager;", "toolbarActionManager", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionManager;", "showToolbarImmediately", "toolbarMaxWidth", "selectedObjectIndex", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/data/SelectableObject;", "selectableObject", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/data/SelectableObject;", "isEnableSelectAllMenu", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureDrawHelperImpl implements ObjectCaptureDrawHelper {
    public static final Companion Companion = new Companion((e) null);
    public static final int SCALE_STATE_NONE = 0;
    public static final int SCALE_STATE_PROGRESS = 1;
    private static final String TAG = "ObjectCaptureDrawHelperImpl";
    public static float captureImageScaleFactor;
    private ObjectResult ArcSoftResult;
    private final Point centerOffset = new Point(0, 0);
    private final Context context;
    private Menu customMenu;
    private MenuItem.OnMenuItemClickListener customMenuItemClickListener;
    private boolean dimRemoving;
    private DimView dimView;
    private ObjectCaptureDrawHelper.OnStartDragListener dragListener;
    private boolean dragStarted;
    private float dragTouchSlopSquare = 2500.0f;
    private GPPServiceSession gppServiceSession;
    private boolean hitObject;
    private ImageInfo imageInfo = new ImageInfo((Bitmap) null, (RectF) null, 0, 0, 0.0f, 31, (e) null);
    private boolean isFlexMode;
    private boolean isImageScale = true;
    private boolean isSelectAll;
    private boolean isSelectionMode;
    private final Point lastTouchPoint = new Point(0, 0);
    private final Point lastTranslatePoint = new Point(0, 0);
    private final Point longDownPoint = new Point(0, 0);
    private List<MaskedObjectResult> maskedObjects = new ArrayList();
    private ObjectCaptureDrawHelper.OnMoveClipListener moveClipListener;
    private MultiObjectViewManager multiObjectViewManager;
    private boolean needToInit = true;
    private Bitmap objectCaptureBitmap;
    private final BitmapDrawable objectCaptureBitmapDrawable;
    private ObjectCaptureToolbar objectCaptureToolbar;
    private ObjectCaptureView objectCaptureView;
    private Rect objectInitRect;
    private Rect objectRectForDndInScreen = new Rect();
    private ObjectResult objectResult;
    private boolean onScaleOrTranslation;
    private final Paint paintFillClear = new Paint(1);
    private float scaleFactor = 1.0f;
    private int scaleState;
    private Rect scaledObjectRectInScreen;
    private SelectableObject selectableObject;
    private int selectedObjectIndex = -1;
    /* access modifiers changed from: private */
    public boolean selectionStarted;
    private boolean showToolbarImmediately = true;
    private ToolbarActionManager toolbarActionManager;
    private int toolbarMaxWidth;
    private ToolbarMenuManager toolbarMenuManager;
    private final Point toolbarTouchPoint = new Point(0, 0);
    private int translationState;
    private Paint underLinePaint;
    private boolean useDefaultMenu = true;
    private boolean useOutGlowLayerView;
    private boolean useParticleLayerView;
    private final VideoClipper videoClipper = new VideoClipper();
    private ViewGroup view;
    private final Rect viewRect = new Rect();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u00020\u00078\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0016\u0010\t\u001a\u00020\u00078\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureDrawHelperImpl$Companion;", "", "<init>", "()V", "TAG", "", "SCALE_STATE_NONE", "", "getSCALE_STATE_NONE$annotations", "SCALE_STATE_PROGRESS", "getSCALE_STATE_PROGRESS$annotations", "captureImageScaleFactor", "", "applyRatioAndOffsetToPoints", "", "Landroid/graphics/Point;", "points", "ratio", "offset", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final List<Point> applyRatioAndOffsetToPoints(List<? extends Point> list, float f, Point point) {
            Iterable<Point> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (Point point2 : iterable) {
                arrayList.add(new Point((int) ((((float) point2.x) * f) + ((float) point.x) + 0.5f), (int) ((((float) point2.y) * f) + ((float) point.y) + 0.5f)));
            }
            return arrayList;
        }

        private Companion() {
        }

        public static /* synthetic */ void getSCALE_STATE_NONE$annotations() {
        }

        public static /* synthetic */ void getSCALE_STATE_PROGRESS$annotations() {
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType[] r0 = com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType r1 = com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType.OUT_GLOW_LAYER     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType r1 = com.samsung.android.app.sdk.deepsky.objectcapture.ui.LayerType.PARTICLE_LAYER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureDrawHelperImpl.WhenMappings.<clinit>():void");
        }
    }

    public ObjectCaptureDrawHelperImpl(Context context2) {
        j.e(context2, "context");
        LibLogger.d(TAG, "create ObjectCaptureDrawHelperImpl context=" + context2);
        this.context = context2;
    }

    private final void calcCaptureImageScaleFactor() {
        int i2;
        int i7;
        float f;
        float f5;
        float f8;
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        Object systemService = viewGroup.getContext().getSystemService("window");
        j.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getRealSize(point);
        defaultDisplay.getSize(point2);
        Rect rect = this.scaledObjectRectInScreen;
        j.b(rect);
        int width = rect.width();
        Rect rect2 = this.scaledObjectRectInScreen;
        j.b(rect2);
        int height = rect2.height();
        captureImageScaleFactor = 0.99f;
        if (defaultDisplay.getRotation() == 0 || defaultDisplay.getRotation() == 2) {
            i2 = point.x / 2;
            i7 = (int) (((float) point2.y) * 0.25f);
            f = (float) point.y;
            f5 = 0.2f;
        } else {
            f5 = 0.4f;
            i2 = (int) (((float) point.x) * 0.4f);
            i7 = (int) (((float) point2.y) * 0.4f);
            f = (float) point.y;
        }
        int i8 = (int) (f * f5);
        if (i8 > height) {
            i8 = height;
        }
        if (height > i7) {
            if (i7 < i8) {
                f8 = ((float) i8) / ((float) height);
                LibLogger.i(TAG, "1. captureImageScaleFactor: " + f8);
            } else {
                f8 = ((float) i7) / ((float) height);
                LibLogger.i(TAG, "2. captureImageScaleFactor: " + f8);
            }
            int i10 = (int) (((float) width) * f8);
            if (i10 > i2) {
                float f10 = (((float) i2) / ((float) i10)) * f8;
                captureImageScaleFactor = f10;
                LibLogger.i(TAG, "3. captureImageScaleFactor: " + f10);
            } else {
                captureImageScaleFactor = f8;
                LibLogger.i(TAG, "4. captureImageScaleFactor: " + f8);
            }
        } else if (width > i2) {
            float f11 = ((float) i2) / ((float) width);
            captureImageScaleFactor = f11;
            LibLogger.i(TAG, "5. captureImageScaleFactor: " + f11);
        }
        if (captureImageScaleFactor == 1.0f) {
            captureImageScaleFactor = 0.99f;
        }
    }

    private final void calcObjectRectForDnd() {
        int[] iArr = new int[2];
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        viewGroup.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i7 = iArr[1];
        LibLogger.i(TAG, a.d(i2, i7, "calcObjectRectForDnd: view.getLocationInWindow = [", ArcCommonLog.TAG_COMMA, "]"));
        Rect rect = this.objectInitRect;
        LibLogger.i(TAG, "calcObjectRectForDnd: objectInitRect = " + rect);
        RectUtil rectUtil = RectUtil.INSTANCE;
        Rect rect2 = this.objectInitRect;
        j.b(rect2);
        Rect scaledRect = rectUtil.getScaledRect(rect2, this.scaleFactor, ((float) i2) + 0.5f, ((float) i7) + 0.5f);
        this.objectRectForDndInScreen = scaledRect;
        LibLogger.i(TAG, "calcObjectRectForDnd: #1 objectRectForDndInScreen = " + scaledRect);
        Context context2 = this.context;
        if (context2 instanceof Activity) {
            View decorView = ((Activity) context2).getWindow().getDecorView();
            j.d(decorView, "getDecorView(...)");
            int dimensionPixelSize = ((Activity) this.context).getResources().getDimensionPixelSize(R.dimen.extra_space_for_shadow);
            int[] iArr2 = new int[2];
            decorView.getLocationOnScreen(iArr2);
            LibLogger.i(TAG, a.d(iArr2[0], iArr2[1], "calcObjectRectForDnd: decor.getLocationOnScreen = [", ArcCommonLog.TAG_COMMA, "]"));
            Rect rect3 = this.objectRectForDndInScreen;
            int i8 = rect3.left;
            int i10 = iArr2[0];
            float f = (float) dimensionPixelSize;
            float f5 = this.scaleFactor;
            float f8 = (float) 2;
            rect3.left = (i10 - ((int) (((f * f5) / f8) + 0.5f))) + i8;
            int i11 = rect3.top;
            int i12 = iArr2[1];
            rect3.top = (i12 - ((int) (((f * f5) / f8) + 0.5f))) + i11;
            rect3.right = i10 + ((int) (((f * f5) / f8) + 0.5f)) + rect3.right;
            rect3.bottom = i12 + ((int) (((f * f5) / f8) + 0.5f)) + rect3.bottom;
            LibLogger.i(TAG, "calcObjectRectForDnd: #2 objectRectForDndInScreen = " + rect3);
        }
    }

    private final RectF calcTransformedRect() {
        int[] iArr = new int[2];
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        viewGroup.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i7 = iArr[1];
        int imageHeight = this.imageInfo.getImageHeight();
        float imageRatio = this.imageInfo.getImageRatio();
        RectF bitmapRectInScreen = this.imageInfo.getBitmapRectInScreen();
        RectF rectF = new RectF(0.0f, 0.0f, bitmapRectInScreen.width() / imageRatio, ((((float) 2) * bitmapRectInScreen.bottom) / imageRatio) + ((float) imageHeight));
        ViewGroup viewGroup2 = this.view;
        j.b(viewGroup2);
        ViewGroup viewGroup3 = this.view;
        j.b(viewGroup3);
        ViewGroup viewGroup4 = this.view;
        j.b(viewGroup4);
        ViewGroup viewGroup5 = this.view;
        j.b(viewGroup5);
        RectF rectF2 = new RectF((float) viewGroup2.getLeft(), (float) viewGroup3.getTop(), (float) viewGroup4.getRight(), (float) viewGroup5.getBottom());
        RectUtil rectUtil = RectUtil.INSTANCE;
        float f = (float) i2;
        float f5 = (float) i7;
        RectF scaledRect = rectUtil.getScaledRect(bitmapRectInScreen, this.scaleFactor, f, f5);
        RectF scaledRect2 = rectUtil.getScaledRect(rectF2, this.scaleFactor, f, f5);
        RectF rectF3 = new RectF();
        rectF3.setIntersect(rectF2, scaledRect);
        float[] fArr = new float[2];
        RectF rectF4 = scaledRect2;
        pointTransform(rectF4, rectF, rectF3.left, rectF3.top, fArr);
        float[] fArr2 = fArr;
        float[] fArr3 = new float[2];
        pointTransform(rectF4, rectF, rectF3.right, rectF3.bottom, fArr3);
        float f8 = fArr2[0];
        float f10 = fArr2[1];
        float f11 = bitmapRectInScreen.bottom;
        return new RectF(f8, f10 - (f11 / imageRatio), f8, fArr3[1] - (f11 / imageRatio));
    }

    private final float[] calcTransformedTouchPoint(int i2, int i7) {
        float[] fArr = new float[2];
        pointTransform(this.imageInfo.getBitmapRectInScreen(), new RectF(0.0f, 0.0f, (float) this.imageInfo.getImageWidth(), (float) this.imageInfo.getImageHeight()), (float) i2, (float) i7, fArr);
        return fArr;
    }

    private final void clearMembersToHandleTouch() {
        this.selectionStarted = false;
        this.lastTranslatePoint.set(0, 0);
    }

    private final void clearSelection() {
        clearObjectCaptureView();
        this.selectedObjectIndex = -1;
        this.selectionStarted = false;
        this.hitObject = false;
    }

    private final void clearView() {
        if (this.objectCaptureView != null && this.view != null) {
            LibLogger.i(TAG, "clearObjectCaptureView: removeView objectCaptureView");
            ViewGroup viewGroup = this.view;
            j.c(viewGroup, "null cannot be cast to non-null type android.view.ViewGroup");
            viewGroup.removeView(this.objectCaptureView);
            ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
            if (objectCaptureView2 != null) {
                objectCaptureView2.recycleBitmap();
            }
            this.objectCaptureView = null;
        }
    }

    private final void createObjectList(float f) {
        Bitmap bitmap;
        SelectableObject selectableObject2 = this.selectableObject;
        if (!(selectableObject2 == null || (bitmap = selectableObject2.getBitmap()) == null)) {
            bitmap.recycle();
        }
        this.selectableObject = null;
        ObjectResult objectResult2 = this.objectResult;
        if (objectResult2 != null) {
            updateObjectResult(objectResult2, -1.0f, -1.0f);
            makeSelectableObject(objectResult2.getOutput(), f);
        }
    }

    private final int getDistance(Point point, Point point2) {
        int i2 = point.x - point2.x;
        int i7 = point.y - point2.y;
        return (i7 * i7) + (i2 * i2);
    }

    private static final x handleTouchEvent$lambda$5(ObjectCaptureDrawHelperImpl objectCaptureDrawHelperImpl, int i2, int i7, int i8, int i10) {
        objectCaptureDrawHelperImpl.longDownPoint.set(i2, i7);
        objectCaptureDrawHelperImpl.toolbarTouchPoint.set(i8, i10);
        return x.f4917a;
    }

    private final void initInternal() {
        if (this.needToInit) {
            int hashCode = hashCode();
            LibLogger.d(TAG, hashCode + "/init");
            ViewGroup viewGroup = this.view;
            j.b(viewGroup);
            viewGroup.setWillNotDraw(false);
            ViewGroup viewGroup2 = this.view;
            j.b(viewGroup2);
            viewGroup2.setLayerType(2, (Paint) null);
            ViewGroup viewGroup3 = this.view;
            j.b(viewGroup3);
            viewGroup3.setLayoutDirection(0);
            this.paintFillClear.setStyle(Paint.Style.FILL_AND_STROKE);
            this.paintFillClear.setStrokeJoin(Paint.Join.ROUND);
            this.paintFillClear.setStrokeCap(Paint.Cap.ROUND);
            this.paintFillClear.setAntiAlias(true);
            this.needToInit = false;
            Paint paint = new Paint();
            this.underLinePaint = paint;
            paint.setFlags(1);
            return;
        }
        int hashCode2 = hashCode();
        LibLogger.d(TAG, hashCode2 + "/init_skip");
    }

    private final void initObjectSelection(boolean z) {
        updateImageInfo();
        if (!this.selectionStarted) {
            if (!z) {
                this.selectionStarted = true;
            }
            setInitialSelection(this.imageInfo.getImageRatio());
        }
    }

    private final void initTouchVariables(MotionEvent motionEvent) {
        int x9 = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        Point point = this.lastTouchPoint;
        point.x = x9;
        point.y = y;
        Point point2 = this.longDownPoint;
        point2.x = x9;
        point2.y = y;
        Point point3 = this.toolbarTouchPoint;
        point3.x = (int) motionEvent.getRawX();
        point3.y = (int) motionEvent.getRawY();
        Point point4 = this.lastTranslatePoint;
        point4.x = 0;
        point4.y = 0;
        this.dragStarted = false;
        this.onScaleOrTranslation = false;
    }

    private final boolean isEnableSelectAllMenu() {
        Boolean bool;
        ObjectResult objectResult2;
        boolean z = this.isSelectAll;
        ObjectResult objectResult3 = this.objectResult;
        if (objectResult3 != null) {
            bool = Boolean.valueOf(objectResult3.isSingleObject());
        } else {
            bool = null;
        }
        LibLogger.d(TAG, "isSelectAll : " + z + ", isSingleObject : " + bool);
        if (this.isSelectAll || (objectResult2 = this.objectResult) == null || objectResult2.isSingleObject()) {
            return false;
        }
        return true;
    }

    private final boolean isOnScaleOrTranslation() {
        if (this.scaleState == 1 || this.translationState == 1) {
            return true;
        }
        return false;
    }

    private final boolean isValidObject(float f, float f5) {
        int i2;
        RectF calcTransformedRect = calcTransformedRect();
        float[] calcTransformedTouchPoint = calcTransformedTouchPoint((int) f, (int) f5);
        ObjectResult objectResult2 = this.objectResult;
        if (objectResult2 != null) {
            i2 = objectResult2.findObjectIndexByPosition((int) calcTransformedTouchPoint[0], (int) calcTransformedTouchPoint[1], calcTransformedRect);
        } else {
            i2 = -1;
        }
        if (i2 != -1) {
            return true;
        }
        return false;
    }

    private final void makeSelectableObject(ObjectInfo objectInfo, float f) {
        Rect boundRect = objectInfo.getBoundRect();
        LibLogger.i(TAG, "createObjectList: objectRect = " + boundRect);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Point());
        arrayList.add(new Point());
        ((Point) arrayList.get(0)).x = boundRect.left;
        ((Point) arrayList.get(0)).y = boundRect.top;
        ((Point) arrayList.get(1)).x = boundRect.right;
        ((Point) arrayList.get(1)).y = boundRect.bottom;
        Point[] pointArr = (Point[]) Companion.applyRatioAndOffsetToPoints(arrayList, f, this.centerOffset).toArray(new Point[0]);
        Point point = pointArr[0];
        int i2 = point.x;
        int i7 = point.y;
        Point point2 = pointArr[1];
        Rect rect = new Rect(i2, i7, point2.x, point2.y);
        LibLogger.i(TAG, "createObjectList: adjustedRect = " + rect);
        Bitmap objBitmap = objectInfo.getObjBitmap();
        Bitmap.Config config = objBitmap.getConfig();
        j.b(config);
        Bitmap copy = objBitmap.copy(config, true);
        j.d(copy, "copy(...)");
        this.selectableObject = new SelectableObject(0, rect, copy);
    }

    private final boolean onTouchDown(MotionEvent motionEvent) {
        int y = (int) (motionEvent.getY() + 0.5f);
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        StringBuilder h5 = a.h((int) (motionEvent.getX() + 0.5f), y, "handleTouchEvent ACTION_DOWN x: ", " y: ", ", view : ");
        h5.append(objectCaptureView2);
        LibLogger.i(TAG, h5.toString());
        initTouchVariables(motionEvent);
        resetToolbar();
        ObjectCaptureView objectCaptureView3 = this.objectCaptureView;
        if (objectCaptureView3 != null) {
            objectCaptureView3.setTouchProcessing(true);
        }
        if (!isObjectSelected() || !isValidObject(motionEvent.getX(), motionEvent.getY())) {
            LibLogger.i(TAG, "Object not selected");
            clearSelection();
            return false;
        }
        LibLogger.i(TAG, "It's valid object");
        ObjectCaptureView objectCaptureView4 = this.objectCaptureView;
        if (objectCaptureView4 != null && objectCaptureView4.getDragging()) {
            LibLogger.i(TAG, "Tap & Hold continues");
            this.selectedObjectIndex = -1;
            this.selectionStarted = false;
            this.hitObject = false;
            return false;
        } else if (this.isSelectAll || isObjectSelected(motionEvent.getX(), motionEvent.getY()) == this.selectedObjectIndex) {
            int i2 = this.selectedObjectIndex;
            LibLogger.i(TAG, "Already selected, same object is selected: " + i2);
            updateScaledObject(motionEvent.getX(), motionEvent.getY());
            this.hitObject = true;
            this.selectionStarted = true;
            return true;
        } else {
            LibLogger.i(TAG, "Other object selected");
            clearSelection();
            return false;
        }
    }

    private final void playDndFeedbacks() {
        Object systemService = this.context.getSystemService("audio");
        j.c(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        ((AudioManager) systemService).playSoundEffect(106);
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        viewGroup.performHapticFeedback(HapticFeedbackConstants.semGetVibrationIndex(108));
    }

    private final void pointTransform(RectF rectF, RectF rectF2, float f, float f5, float[] fArr) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        float[] fArr2 = {f, f5};
        matrix.mapPoints(fArr2);
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
    }

    private final void prepareObjectCaptureToolbar() {
        Boolean bool;
        ToolbarMenuManager toolbarMenuManager2;
        ObjectCaptureToolbar objectCaptureToolbar2 = this.objectCaptureToolbar;
        if (objectCaptureToolbar2 != null) {
            objectCaptureToolbar2.useDefaultMenu(this.useDefaultMenu);
            objectCaptureToolbar2.updateFlexMode(this.isFlexMode);
            boolean z = this.useDefaultMenu;
            ToolbarMenuManager toolbarMenuManager3 = this.toolbarMenuManager;
            if (toolbarMenuManager3 != null) {
                bool = Boolean.valueOf(toolbarMenuManager3.hasToolbarMenu());
            } else {
                bool = null;
            }
            LibLogger.d(TAG, "useDefaultMenu : " + z + ", toolbarMenuManager : " + toolbarMenuManager3 + ", hasToolbarMenu : " + bool);
            if (!this.useDefaultMenu && (toolbarMenuManager2 = this.toolbarMenuManager) != null) {
                toolbarMenuManager2.init(this.videoClipper);
                if (toolbarMenuManager2.hasToolbarMenu()) {
                    prepareToolbarActionManager();
                    toolbarMenuManager2.updateToolbarMenu(5, isEnableSelectAllMenu());
                    objectCaptureToolbar2.setToolbarMenuList(toolbarMenuManager2.getToolbarMenuList());
                }
                Integer titleColor = toolbarMenuManager2.getTitleColor();
                if (titleColor != null) {
                    objectCaptureToolbar2.setMenuTitleColor(titleColor.intValue());
                }
                ToolbarActionManager toolbarActionManager2 = this.toolbarActionManager;
                j.b(toolbarActionManager2);
                ObjectResult objectResult2 = this.objectResult;
                j.b(objectResult2);
                toolbarActionManager2.setObjectResult(objectResult2);
                ToolbarActionManager toolbarActionManager3 = this.toolbarActionManager;
                j.b(toolbarActionManager3);
                objectCaptureToolbar2.setToolbarActionListener(toolbarActionManager3.getToolbarActionListener(getObjectCaptureViewRect()));
            }
            Menu menu = this.customMenu;
            if (menu != null) {
                objectCaptureToolbar2.addMenu(menu);
            }
            objectCaptureToolbar2.setSuggestedWidth(this.toolbarMaxWidth);
            objectCaptureToolbar2.setOnMenuItemClickListener(this.customMenuItemClickListener);
        }
    }

    private final void prepareToolbarActionManager() {
        if (this.toolbarActionManager == null) {
            ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
            j.b(toolbarMenuManager2);
            this.toolbarActionManager = new ToolbarActionManager(toolbarMenuManager2, new q(3, this));
        }
    }

    /* access modifiers changed from: private */
    public static final x prepareToolbarActionManager$lambda$10(ObjectCaptureDrawHelperImpl objectCaptureDrawHelperImpl) {
        objectCaptureDrawHelperImpl.clearView();
        objectCaptureDrawHelperImpl.isSelectAll = true;
        ToolbarMenuManager toolbarMenuManager2 = objectCaptureDrawHelperImpl.toolbarMenuManager;
        j.b(toolbarMenuManager2);
        toolbarMenuManager2.updateToolbarMenu(5, false);
        VideoClipper videoClipper2 = objectCaptureDrawHelperImpl.videoClipper;
        Bitmap bitmap = objectCaptureDrawHelperImpl.imageInfo.getBitmap();
        j.b(bitmap);
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        j.d(copy, "copy(...)");
        videoClipper2.updateClippedImageInformation(copy, -1.0f, -1.0f);
        ToolbarMenuManager toolbarMenuManager3 = objectCaptureDrawHelperImpl.toolbarMenuManager;
        j.b(toolbarMenuManager3);
        toolbarMenuManager3.updateToolbarMenu(6, objectCaptureDrawHelperImpl.videoClipper.isSupportedPoint());
        objectCaptureDrawHelperImpl.startObjectCaptureByButton();
        return x.f4917a;
    }

    private final void resetToolbar() {
        ObjectCaptureToolbar objectCaptureToolbar2 = this.objectCaptureToolbar;
        if (objectCaptureToolbar2 != null) {
            objectCaptureToolbar2.dismiss();
        }
        this.objectCaptureToolbar = null;
        Point point = this.toolbarTouchPoint;
        point.x = -1;
        point.y = -1;
    }

    private final void setDimView() {
        if (this.dimView == null) {
            ViewGroup viewGroup = this.view;
            j.b(viewGroup);
            int childCount = viewGroup.getChildCount();
            int i2 = 0;
            while (true) {
                if (i2 >= childCount) {
                    ViewGroup viewGroup2 = this.view;
                    j.b(viewGroup2);
                    Object systemService = viewGroup2.getContext().getSystemService("layout_inflater");
                    j.c(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
                    View inflate = ((LayoutInflater) systemService).inflate(R.layout.dim_layout, (ViewGroup) null);
                    j.c(inflate, "null cannot be cast to non-null type com.samsung.android.app.sdk.deepsky.objectcapture.ui.DimView");
                    this.dimView = (DimView) inflate;
                    break;
                }
                ViewGroup viewGroup3 = this.view;
                j.b(viewGroup3);
                if (viewGroup3.getChildAt(i2) instanceof DimView) {
                    ViewGroup viewGroup4 = this.view;
                    j.b(viewGroup4);
                    View childAt = viewGroup4.getChildAt(i2);
                    j.c(childAt, "null cannot be cast to non-null type com.samsung.android.app.sdk.deepsky.objectcapture.ui.DimView");
                    this.dimView = (DimView) childAt;
                    break;
                }
                i2++;
            }
        }
        RectF bitmapRectInScreen = this.imageInfo.getBitmapRectInScreen();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) bitmapRectInScreen.width(), (int) bitmapRectInScreen.height());
        layoutParams.leftMargin = (int) bitmapRectInScreen.left;
        layoutParams.topMargin = (int) bitmapRectInScreen.top;
        DimView dimView2 = this.dimView;
        j.b(dimView2);
        dimView2.setLayoutParams(layoutParams);
    }

    private final void setInitialSelection(float f) {
        this.objectResult = getObjectResult();
        createObjectList(f);
    }

    private final int setNewSelectObject(int i2, int i7) {
        float f = this.scaleFactor;
        StringBuilder h5 = a.h(i2, i7, "setNewSelectObject: (", ArcCommonLog.TAG_COMMA, "), scaleFactor = ");
        h5.append(f);
        LibLogger.i(TAG, h5.toString());
        RectF calcTransformedRect = calcTransformedRect();
        LibLogger.i(TAG, "setNewSelectObject: transformedVisibleRect = " + calcTransformedRect);
        float[] calcTransformedTouchPoint = calcTransformedTouchPoint(i2, i7);
        float f5 = calcTransformedTouchPoint[0];
        float f8 = calcTransformedTouchPoint[1];
        LibLogger.i(TAG, "setNewSelectObject: transformedX = " + f5 + " transformedY = " + f8);
        ObjectResult objectResult2 = this.objectResult;
        if (objectResult2 == null || !objectResult2.isSingleObject()) {
            VideoClipper videoClipper2 = this.videoClipper;
            Bitmap bitmap = this.imageInfo.getBitmap();
            j.b(bitmap);
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy, "copy(...)");
            videoClipper2.updateClippedImageInformation(copy, calcTransformedTouchPoint[0], calcTransformedTouchPoint[1]);
        } else {
            VideoClipper videoClipper3 = this.videoClipper;
            Bitmap bitmap2 = this.imageInfo.getBitmap();
            j.b(bitmap2);
            Bitmap copy2 = bitmap2.copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy2, "copy(...)");
            videoClipper3.updateClippedImageInformation(copy2, -1.0f, -1.0f);
        }
        ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
        if (toolbarMenuManager2 != null) {
            toolbarMenuManager2.updateToolbarMenu(6, this.videoClipper.isSupportedPoint());
        }
        ObjectResult objectResult3 = this.objectResult;
        if (objectResult3 == null) {
            return -1;
        }
        j.b(objectResult3);
        int findObjectIndexByPosition = objectResult3.findObjectIndexByPosition((int) calcTransformedTouchPoint[0], (int) calcTransformedTouchPoint[1], calcTransformedRect);
        this.selectedObjectIndex = findObjectIndexByPosition;
        LibLogger.i(TAG, "objectIndex = " + findObjectIndexByPosition);
        ObjectResult objectResult4 = this.objectResult;
        j.b(objectResult4);
        updateObjectResult(objectResult4, (float) ((int) calcTransformedTouchPoint[0]), (float) ((int) calcTransformedTouchPoint[1]));
        ObjectResult objectResult5 = this.objectResult;
        j.b(objectResult5);
        makeSelectableObject(objectResult5.getObjectResult(findObjectIndexByPosition), this.imageInfo.getImageRatio());
        return findObjectIndexByPosition;
    }

    /* access modifiers changed from: private */
    public final void showObjectCaptureResult(boolean z) {
        showToolbarInternal(z);
        DimView dimView2 = this.dimView;
        if (dimView2 != null) {
            dimView2.startDarkDimAnimation();
        }
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        if (objectCaptureView2 != null) {
            objectCaptureView2.setTranslationX(0.0f);
        }
        ObjectCaptureView objectCaptureView3 = this.objectCaptureView;
        if (objectCaptureView3 != null) {
            objectCaptureView3.setTranslationY(0.0f);
        }
        ObjectCaptureView objectCaptureView4 = this.objectCaptureView;
        if (objectCaptureView4 != null) {
            objectCaptureView4.setScaleX(1.0f);
        }
        ObjectCaptureView objectCaptureView5 = this.objectCaptureView;
        if (objectCaptureView5 != null) {
            objectCaptureView5.setScaleY(1.0f);
        }
        ObjectCaptureView objectCaptureView6 = this.objectCaptureView;
        if (objectCaptureView6 != null) {
            objectCaptureView6.setAlpha(1.0f);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0097, code lost:
        r12 = r11.toolbarTouchPoint;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void showToolbarInternal(boolean r12) {
        /*
            r11 = this;
            boolean r0 = r11.showToolbarImmediately
            if (r0 != 0) goto L_0x0006
            goto L_0x00bc
        L_0x0006:
            int r0 = r11.scaleState
            r1 = 1
            java.lang.String r2 = "ObjectCaptureDrawHelperImpl"
            if (r0 == r1) goto L_0x00c3
            android.view.ViewGroup r3 = r11.view
            if (r3 != 0) goto L_0x0013
            goto L_0x00c3
        L_0x0013:
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureView r0 = r11.objectCaptureView
            if (r0 != 0) goto L_0x001d
            java.lang.String r11 = "Cancel showing popup. objectCaptureView is null"
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.e(r2, r11)
            return
        L_0x001d:
            android.content.Context r0 = r11.context
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L_0x00bd
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureToolbar r0 = r11.objectCaptureToolbar
            if (r0 != 0) goto L_0x003b
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureToolbar r0 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureToolbar
            android.content.Context r3 = r11.context
            android.app.Activity r3 = (android.app.Activity) r3
            android.view.Window r3 = r3.getWindow()
            java.lang.String r4 = "getWindow(...)"
            kotlin.jvm.internal.j.d(r3, r4)
            r0.<init>(r3)
            r11.objectCaptureToolbar = r0
        L_0x003b:
            r11.prepareObjectCaptureToolbar()
            r0 = 2
            int[] r0 = new int[r0]
            android.view.ViewGroup r3 = r11.view
            kotlin.jvm.internal.j.b(r3)
            r3.getLocationInWindow(r0)
            r3 = 0
            r4 = r0[r3]
            r0 = r0[r1]
            com.samsung.android.app.sdk.deepsky.objectcapture.utils.RectUtil r1 = com.samsung.android.app.sdk.deepsky.objectcapture.utils.RectUtil.INSTANCE
            android.graphics.Rect r5 = r11.objectInitRect
            kotlin.jvm.internal.j.b(r5)
            float r6 = r11.scaleFactor
            float r7 = (float) r4
            float r8 = (float) r0
            android.graphics.Rect r1 = r1.getScaledRect((android.graphics.Rect) r5, (float) r6, (float) r7, (float) r8)
            float r5 = r11.scaleFactor
            android.graphics.Point r6 = r11.toolbarTouchPoint
            int r7 = r6.x
            int r6 = r6.y
            java.lang.String r8 = " y="
            java.lang.String r9 = " scale = "
            java.lang.String r10 = "showPopupMenu: view location x="
            java.lang.StringBuilder r0 = A.a.h(r4, r0, r10, r8, r9)
            r0.append(r5)
            java.lang.String r4 = " adjustedRect = "
            r0.append(r4)
            r0.append(r1)
            java.lang.String r4 = ", x = "
            r0.append(r4)
            java.lang.String r4 = ", y = "
            java.lang.String r5 = " byButton = "
            N2.j.x(r0, r7, r4, r6, r5)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.d(r2, r0)
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>(r3, r3)
            if (r12 != 0) goto L_0x00a7
            android.graphics.Point r12 = r11.toolbarTouchPoint
            int r2 = r12.x
            r3 = -1
            if (r2 == r3) goto L_0x00a7
            int r12 = r12.y
            if (r12 != r3) goto L_0x00a3
            goto L_0x00a7
        L_0x00a3:
            r0.set(r2, r12)
            goto L_0x00b2
        L_0x00a7:
            int r12 = r1.centerX()
            int r2 = r1.centerY()
            r0.set(r12, r2)
        L_0x00b2:
            com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureToolbar r11 = r11.objectCaptureToolbar
            if (r11 == 0) goto L_0x00bc
            r11.setContentRect(r1)
            r11.show()
        L_0x00bc:
            return
        L_0x00bd:
            java.lang.String r11 = "Context is not Activity"
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.e(r2, r11)
            return
        L_0x00c3:
            android.view.ViewGroup r11 = r11.view
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r1 = "Cancel showing popup. scaleState = "
            r12.<init>(r1)
            r12.append(r0)
            java.lang.String r0 = " view = "
            r12.append(r0)
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.e(r2, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureDrawHelperImpl.showToolbarInternal(boolean):void");
    }

    private final void startMergedObjectCapture(float f, float f5) {
        LibLogger.i(TAG, "startMergedObjectCapture: (" + f + ArcCommonLog.TAG_COMMA + f5 + ")");
        this.selectionStarted = true;
        LibLogger.i(TAG, "startMergedObjectCapture: #2 hit a object");
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        viewGroup.performHapticFeedback(HapticFeedbackConstants.semGetVibrationIndex(108));
        if (this.objectResult != null) {
            updateScaledObject(f, f5);
            this.hitObject = true;
            this.dimRemoving = false;
            return;
        }
        LibLogger.i(TAG, "startMergedObjectCapture: #2 hit no object");
        this.hitObject = false;
    }

    private final void updateImageInfo() {
        float f;
        int imageWidth = this.imageInfo.getImageWidth();
        int imageHeight = this.imageInfo.getImageHeight();
        RectF bitmapRectInScreen = this.imageInfo.getBitmapRectInScreen();
        LibLogger.i(TAG, "getImageInfo: bitmap width = " + imageWidth + " height = " + imageHeight);
        if (imageWidth > 0 && imageHeight > 0) {
            ViewGroup viewGroup = this.view;
            j.b(viewGroup);
            int width = viewGroup.getWidth();
            ViewGroup viewGroup2 = this.view;
            j.b(viewGroup2);
            int height = viewGroup2.getHeight();
            ViewGroup viewGroup3 = this.view;
            j.b(viewGroup3);
            viewGroup3.getGlobalVisibleRect(this.viewRect);
            if (this.isImageScale) {
                f = (float) Math.min((double) (((float) width) / ((float) imageWidth)), (double) (((float) height) / ((float) imageHeight)));
            } else {
                f = 1.0f;
            }
            Point point = this.centerOffset;
            point.x = (int) bitmapRectInScreen.left;
            point.y = (int) bitmapRectInScreen.top;
            Rect rect = this.viewRect;
            StringBuilder h5 = a.h(imageWidth, imageHeight, "getImageInfo: imageWidth = ", " imageHeight = ", " rawWidth = ");
            N2.j.x(h5, width, " rawHeight = ", height, " imageRatio = ");
            h5.append(f);
            h5.append(" centerOffset = ");
            h5.append(point);
            h5.append(" imageRect = ");
            h5.append(bitmapRectInScreen);
            h5.append(" view rect = ");
            h5.append(rect);
            LibLogger.i(TAG, h5.toString());
            this.imageInfo.setImageRatio(f);
        }
    }

    private final void updateObjectResult(ObjectResult objectResult2, float f, float f5) {
        ObjectInfo objectInfo;
        if (f == -1.0f || f5 == -1.0f || this.isSelectAll) {
            objectInfo = objectResult2.getSingleOutput();
        } else {
            objectInfo = objectResult2.getObjectResult(objectResult2.findObjectIndexByPosition((int) f, (int) f5));
        }
        objectResult2.setOutput(objectInfo);
        this.objectResult = objectResult2;
    }

    private final void updateScaledObject(float f, float f5) {
        Bitmap bitmap;
        if (this.selectableObject != null && (bitmap = this.objectCaptureBitmap) != null && this.objectCaptureView != null) {
            int i2 = (int) (f + 0.5f);
            int i7 = (int) (f5 + 0.5f);
            j.b(bitmap);
            int width = bitmap.getWidth();
            Bitmap bitmap2 = this.objectCaptureBitmap;
            j.b(bitmap2);
            int height = bitmap2.getHeight();
            LibLogger.i(TAG, "updateScaledObject: #2 setCapturedInfo bitmap w = " + width + " h = " + height);
            ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
            if (objectCaptureView2 != null) {
                objectCaptureView2.getLastTouchPoint().x = i2;
                objectCaptureView2.getLastTouchPoint().y = i7;
                updateScaledObjectRectInScreen();
                calcCaptureImageScaleFactor();
                calcObjectRectForDnd();
                Rect rect = this.scaledObjectRectInScreen;
                LibLogger.i(TAG, "scaledObjectRectInScreen: " + rect);
                Rect rect2 = this.scaledObjectRectInScreen;
                ViewGroup viewGroup = this.view;
                j.b(viewGroup);
                float translationX = viewGroup.getTranslationX();
                ViewGroup viewGroup2 = this.view;
                j.b(viewGroup2);
                objectCaptureView2.setDistanceOfTouchFromCenter(rect2, translationX, viewGroup2.getTranslationY());
                objectCaptureView2.getLastTouchPoint().x = this.lastTouchPoint.x;
                objectCaptureView2.getLastTouchPoint().y = this.lastTouchPoint.y;
                objectCaptureView2.startAnimation();
            }
        }
    }

    private final void updateScaledObjectRectInScreen() {
        Bitmap bitmap = this.objectCaptureBitmap;
        j.b(bitmap);
        float f = (float) 1;
        float width = (this.scaleFactor - f) * ((float) bitmap.getWidth());
        float f5 = (float) 2;
        int i2 = (int) (width / f5);
        Bitmap bitmap2 = this.objectCaptureBitmap;
        j.b(bitmap2);
        int height = (int) (((this.scaleFactor - f) * ((float) bitmap2.getHeight())) / f5);
        Rect rect = this.objectInitRect;
        j.b(rect);
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        int translationX = (int) (viewGroup.getTranslationX() + ((float) (rect.left - i2)));
        Rect rect2 = this.objectInitRect;
        j.b(rect2);
        ViewGroup viewGroup2 = this.view;
        j.b(viewGroup2);
        int translationY = (int) (viewGroup2.getTranslationY() + ((float) (rect2.top - height)));
        Rect rect3 = this.objectInitRect;
        j.b(rect3);
        float f8 = (float) (rect3.right + i2);
        ViewGroup viewGroup3 = this.view;
        j.b(viewGroup3);
        int translationX2 = (int) (viewGroup3.getTranslationX() + f8);
        Rect rect4 = this.objectInitRect;
        j.b(rect4);
        float f10 = (float) (rect4.bottom + height);
        ViewGroup viewGroup4 = this.view;
        j.b(viewGroup4);
        this.scaledObjectRectInScreen = new Rect(translationX, translationY, translationX2, (int) (viewGroup4.getTranslationY() + f10));
    }

    public void addToolbarMenu(Menu menu) {
        j.e(menu, "menu");
        this.customMenu = menu;
    }

    public void clearDimView() {
        DimView dimView2 = this.dimView;
        if (dimView2 != null && dimView2.isShowing()) {
            LibLogger.i(TAG, "clearDimView");
            DimView dimView3 = this.dimView;
            if (dimView3 != null) {
                dimView3.startRemoveDimAnimation();
            }
            this.dimRemoving = true;
        }
    }

    public void clearMaskedObjectResult() {
        LibLogger.i(TAG, "clear MaskedObjects");
        ObjectResult objectResult2 = this.ArcSoftResult;
        j.b(objectResult2);
        this.objectResult = objectResult2.copy();
        this.maskedObjects.clear();
    }

    public void clearObjectCapture() {
        Bitmap bitmap;
        LibLogger.i(TAG, "clearObjectCapture");
        this.objectResult = null;
        this.dragListener = null;
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        if (objectCaptureView2 != null) {
            objectCaptureView2.setOnStartDragListener((ObjectCaptureDrawHelper.OnStartDragListener) null);
        }
        ObjectCaptureView objectCaptureView3 = this.objectCaptureView;
        if (objectCaptureView3 != null) {
            objectCaptureView3.setOnMoveClipListener((ObjectCaptureDrawHelper.OnMoveClipListener) null);
        }
        clearObjectCaptureView();
        Bitmap bitmap2 = this.objectCaptureBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.objectCaptureBitmap = null;
        SelectableObject selectableObject2 = this.selectableObject;
        if (!(selectableObject2 == null || (bitmap = selectableObject2.getBitmap()) == null)) {
            bitmap.recycle();
        }
        this.selectableObject = null;
        clearMembersToHandleTouch();
        ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
        if (toolbarMenuManager2 != null) {
            toolbarMenuManager2.unbindPhotoEditor();
        }
        this.videoClipper.finish();
    }

    public final void clearObjectCaptureView() {
        clearView();
        this.isSelectAll = false;
        this.isSelectionMode = false;
        ViewGroup viewGroup = this.view;
        if (viewGroup != null) {
            viewGroup.removeView(this.dimView);
        }
        MultiObjectViewManager multiObjectViewManager2 = this.multiObjectViewManager;
        if (multiObjectViewManager2 != null) {
            multiObjectViewManager2.clearViewList();
        }
        ObjectCaptureToolbar objectCaptureToolbar2 = this.objectCaptureToolbar;
        if (objectCaptureToolbar2 != null) {
            objectCaptureToolbar2.updateFlexMode(false);
        }
        ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
        if (toolbarMenuManager2 != null) {
            toolbarMenuManager2.updateToolbarMenu(5, true);
        }
        resetToolbar();
    }

    public void connectGPPMSession(GPPMListener gPPMListener) {
        j.e(gPPMListener, "listener");
        if (this.gppServiceSession == null) {
            this.gppServiceSession = new GPPServiceSession(this.context);
        }
        GPPServiceSession gPPServiceSession = this.gppServiceSession;
        j.b(gPPServiceSession);
        gPPServiceSession.connect(gPPMListener);
    }

    public ToolbarMenu.Builder createToolbarMenuBuilder() {
        if (this.toolbarMenuManager == null) {
            this.toolbarMenuManager = new ToolbarMenuManager(this.context);
        }
        ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
        j.b(toolbarMenuManager2);
        return toolbarMenuManager2.createToolbarMenuBuilder();
    }

    public void disconnectGPPMSession() {
        GPPServiceSession gPPServiceSession = this.gppServiceSession;
        if (gPPServiceSession != null) {
            gPPServiceSession.disconnect();
        }
    }

    public void dismissToolbar() {
        LibLogger.i(TAG, "dismissToolbar");
        resetToolbar();
        DimView dimView2 = this.dimView;
        if (dimView2 != null && dimView2.isShowing()) {
            DimView dimView3 = this.dimView;
            if (dimView3 != null) {
                dimView3.startRemoveDimAnimation();
            }
            clearObjectCaptureView();
        }
    }

    public void dispatchDraw(Canvas canvas) {
        j.e(canvas, "canvas");
    }

    public void generateGif(GPPMData gPPMData, String str) {
        j.e(gPPMData, "data");
        j.e(str, "stickerPath");
        boolean z = true;
        if (this.isSelectAll) {
            VideoClipper videoClipper2 = this.videoClipper;
            Bitmap bitmap = this.imageInfo.getBitmap();
            j.b(bitmap);
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy, "copy(...)");
            videoClipper2.updateClippedImageInformation(copy, -1.0f, -1.0f);
        }
        if (!this.videoClipper.isSupportedPoint()) {
            LibLogger.e(TAG, "generateGif is fail. this object is not supported.");
            return;
        }
        if (str.length() != 0) {
            z = false;
        }
        VideoData videoData = this.videoClipper.getVideoData();
        if (videoData == null) {
            return;
        }
        if (z) {
            ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
            j.b(toolbarMenuManager2);
            String stickerID = toolbarMenuManager2.getStickerID();
            LibLogger.d(TAG, "generateGif, stickerId: " + stickerID);
            GPPServiceSession gPPServiceSession = this.gppServiceSession;
            j.b(gPPServiceSession);
            ToolbarMenuManager toolbarMenuManager3 = this.toolbarMenuManager;
            j.b(toolbarMenuManager3);
            gPPServiceSession.runPP(gPPMData, videoData, toolbarMenuManager3.getStickerID());
            ToolbarMenuManager toolbarMenuManager4 = this.toolbarMenuManager;
            if (toolbarMenuManager4 != null) {
                toolbarMenuManager4.clearStickerId();
                return;
            }
            return;
        }
        LibLogger.d(TAG, "generateGif, stickerPath: " + str + ")");
        GPPServiceSession gPPServiceSession2 = this.gppServiceSession;
        j.b(gPPServiceSession2);
        gPPServiceSession2.runPP(gPPMData, videoData, str);
    }

    public List<MaskedObjectResult> getMaskedObjectResult() {
        return this.maskedObjects;
    }

    public Bitmap getObjectCaptureBitmap() {
        Bitmap bitmap = this.objectCaptureBitmap;
        j.b(bitmap);
        return bitmap;
    }

    public Rect getObjectCaptureViewRect() {
        Rect rect = new Rect();
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        j.b(objectCaptureView2);
        objectCaptureView2.getGlobalVisibleRect(rect);
        return rect;
    }

    public ObjectResult getObjectResult() {
        return this.objectResult;
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        j.e(motionEvent, "event");
        int x9 = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            return onTouchDown(motionEvent);
        }
        if (actionMasked == 1) {
            boolean z = this.selectionStarted;
            boolean z3 = this.hitObject;
            boolean z7 = this.dragStarted;
            LibLogger.i(TAG, "handleTouchEvent ACTION_UP. selectionStarted: " + z + " hitObject: " + z3 + " dragStarted: " + z7 + "}");
            ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
            if (objectCaptureView2 != null && objectCaptureView2.getDragging()) {
                return true;
            }
            ObjectCaptureView objectCaptureView3 = this.objectCaptureView;
            if (objectCaptureView3 != null) {
                objectCaptureView3.setTouchProcessing(false);
            }
            if (this.hitObject || this.onScaleOrTranslation) {
                showObjectCaptureResult(false);
                this.hitObject = false;
                this.dragStarted = false;
                this.selectionStarted = false;
                this.lastTranslatePoint.set(0, 0);
                return true;
            }
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                return false;
            }
            if (actionMasked == 5) {
                LibLogger.i(TAG, "Multi touch down - clear oc view");
                clearObjectCaptureView();
                return true;
            }
        } else if (this.dimRemoving) {
            this.longDownPoint.set(x9, y);
            this.lastTouchPoint.set(x9, y);
            this.toolbarTouchPoint.set(rawX, rawY);
            return false;
        } else {
            ObjectCaptureView objectCaptureView4 = this.objectCaptureView;
            if (objectCaptureView4 != null) {
                if (!this.dragStarted) {
                    int distance = getDistance(new Point(x9, y), this.longDownPoint);
                    Point point = this.lastTranslatePoint;
                    Point point2 = this.lastTouchPoint;
                    int i2 = (point.x + x9) - point2.x;
                    point.x = i2;
                    point.y = (point.y + y) - point2.y;
                    objectCaptureView4.setTranslationX((float) i2);
                    objectCaptureView4.setTranslationY((float) this.lastTranslatePoint.y);
                    if (((float) distance) > this.dragTouchSlopSquare) {
                        this.dragStarted = true;
                        objectCaptureView4.getCurrentPoint().x = x9;
                        objectCaptureView4.getCurrentPoint().y = y;
                        objectCaptureView4.getLastTouchPoint().x = x9;
                        objectCaptureView4.getLastTouchPoint().y = y;
                        objectCaptureView4.getLastTranslatePoint().x = this.lastTranslatePoint.x;
                        objectCaptureView4.getLastTranslatePoint().y = this.lastTranslatePoint.y;
                        objectCaptureView4.startScaleDownAnimation(this.scaleFactor, this.objectRectForDndInScreen);
                        if (!this.onScaleOrTranslation) {
                            this.onScaleOrTranslation = isOnScaleOrTranslation();
                        }
                    }
                } else {
                    objectCaptureView4.getCurrentPoint().set(x9, y);
                }
            }
            this.lastTouchPoint.set(x9, y);
            if (this.selectionStarted && this.objectCaptureBitmapDrawable != null) {
                ViewGroup viewGroup = this.view;
                j.b(viewGroup);
                viewGroup.invalidate();
            }
            if (this.selectionStarted && this.objectCaptureView != null) {
                return true;
            }
        }
        ViewGroup viewGroup2 = this.view;
        if (viewGroup2 != null) {
            viewGroup2.invalidate();
        }
        return false;
    }

    public void hideToolbar() {
        ObjectCaptureToolbar objectCaptureToolbar2 = this.objectCaptureToolbar;
        if (objectCaptureToolbar2 != null) {
            objectCaptureToolbar2.hide();
        }
    }

    public void init(ViewGroup viewGroup) {
        j.e(viewGroup, "parentView");
        this.view = viewGroup;
        initInternal();
    }

    public boolean isObjectSelected() {
        return this.objectCaptureView != null;
    }

    public boolean isSelectAll() {
        return this.isSelectAll;
    }

    public boolean isVideoClippingSupported() {
        boolean isSupportedPoint = this.videoClipper.isSupportedPoint();
        LibLogger.d(TAG, "isVideoClippingSupported, isSupported: " + isSupportedPoint);
        return isSupportedPoint;
    }

    public void setAnimatedBitmapInfo(boolean z) {
        this.videoClipper.updateAnimatedBitmapInfo(z);
    }

    public void setBitmap(Bitmap bitmap, boolean z) {
        j.e(bitmap, "bitmap");
        setBitmap(bitmap);
        this.isImageScale = z;
        LibLogger.i(TAG, "setBitmap: scale = " + z);
    }

    public void setBitmapRect(RectF rectF) {
        j.e(rectF, "rect");
        this.imageInfo.setBitmapRectInScreen(rectF);
    }

    public void setDeviceType(DeviceType deviceType) {
        j.e(deviceType, "deviceType");
        ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
        if (toolbarMenuManager2 != null) {
            toolbarMenuManager2.setDeviceType(deviceType);
        }
    }

    public void setFlexMode(boolean z) {
        this.isFlexMode = z;
    }

    public void setLayerView(LayerType... layerTypeArr) {
        j.e(layerTypeArr, "type");
        for (LayerType ordinal : layerTypeArr) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[ordinal.ordinal()];
            if (i2 == 1) {
                this.useOutGlowLayerView = true;
            } else if (i2 == 2) {
                this.useParticleLayerView = true;
            } else {
                throw new RuntimeException();
            }
        }
    }

    public void setObjectResult(ObjectResult objectResult2) {
        j.e(objectResult2, "objectResult");
        LibLogger.i(TAG, "set objectResult");
        this.objectResult = objectResult2;
        this.ArcSoftResult = objectResult2.copy();
    }

    public void setOnMoveClipListener(ObjectCaptureDrawHelper.OnMoveClipListener onMoveClipListener) {
        j.e(onMoveClipListener, "listener");
        this.moveClipListener = onMoveClipListener;
        MultiObjectViewManager multiObjectViewManager2 = this.multiObjectViewManager;
        if (multiObjectViewManager2 != null) {
            multiObjectViewManager2.setOnMoveClipListener(onMoveClipListener);
        }
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        if (objectCaptureView2 != null) {
            objectCaptureView2.setOnMoveClipListener(onMoveClipListener);
        }
    }

    public void setOnScaleState(int i2) {
        this.scaleState = i2;
    }

    public void setOnStartDragListener(ObjectCaptureDrawHelper.OnStartDragListener onStartDragListener) {
        j.e(onStartDragListener, "listener");
        this.dragListener = onStartDragListener;
        MultiObjectViewManager multiObjectViewManager2 = this.multiObjectViewManager;
        if (multiObjectViewManager2 != null) {
            multiObjectViewManager2.setOnStartDragListener(onStartDragListener);
        }
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        if (objectCaptureView2 != null) {
            objectCaptureView2.setOnStartDragListener(onStartDragListener);
        }
    }

    public void setOnTranslationState(int i2) {
        this.translationState = i2;
    }

    public void setScaleFactor(float f) {
        this.scaleFactor = f;
        this.dragTouchSlopSquare = 2500.0f / (f * f);
    }

    public void setShowToolbarImmediately(boolean z) {
        this.showToolbarImmediately = z;
    }

    public void setToolbarMaxWidth(int i2) {
        this.toolbarMaxWidth = i2;
    }

    public void setToolbarMenu(ToolbarMenu toolbarMenu) {
        j.e(toolbarMenu, "toolbarMenu");
        ToolbarMenuManager toolbarMenuManager2 = this.toolbarMenuManager;
        if (toolbarMenuManager2 != null) {
            toolbarMenuManager2.setToolbarMenu(toolbarMenu);
        }
    }

    public void setToolbarOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        j.e(onMenuItemClickListener, "listener");
        this.customMenuItemClickListener = onMenuItemClickListener;
    }

    public void showToolbar() {
        showToolbarInternal(false);
    }

    public boolean startObjectCaptureByButton() {
        boolean z;
        LibLogger.i(TAG, "startObjectCaptureByButton");
        if (isObjectSelected() && !this.isSelectAll) {
            return true;
        }
        initObjectSelection(true);
        if (this.selectableObject != null) {
            LibLogger.i(TAG, "find a valid object");
            z = startObjectCaptureByLongClick(-2.14748365E9f, -2.14748365E9f, true);
            if (this.imageInfo.getBitmap() != null) {
                VideoClipper videoClipper2 = this.videoClipper;
                Bitmap bitmap = this.imageInfo.getBitmap();
                j.b(bitmap);
                Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                j.d(copy, "copy(...)");
                videoClipper2.updateClippedImageInformation(copy, -1.0f, -1.0f);
            }
        } else {
            z = false;
        }
        if (z) {
            showObjectCaptureResult(true);
        }
        return z;
    }

    public boolean startObjectCaptureByLongClick(float f, float f5) {
        LibLogger.i(TAG, "startObjectCaptureByLongClick: (" + f + ArcCommonLog.TAG_COMMA + f5 + ")");
        ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
        if ((objectCaptureView2 != null && objectCaptureView2.getDragging()) || (isObjectSelected() && !this.isSelectAll)) {
            return true;
        }
        if (!this.isSelectAll) {
            return startObjectCaptureByLongClick(f, f5, false);
        }
        if (!isValidObject(f, f5)) {
            DimView dimView2 = this.dimView;
            j.b(dimView2);
            dimView2.startRemoveDimAnimation();
            clearObjectCaptureView();
            this.dimRemoving = true;
            this.hitObject = false;
            return true;
        }
        startMergedObjectCapture(f, f5);
        return true;
    }

    public void startObjectCaptureByResult(ObjectResult objectResult2, int i2, PointF pointF, Rect rect) {
        ObjectResult objectResult3 = objectResult2;
        PointF pointF2 = pointF;
        Rect rect2 = rect;
        j.e(objectResult3, "objectResult");
        j.e(pointF2, "point");
        j.e(rect2, "cropRect");
        Point point = new Point((int) (((double) pointF2.x) + 0.5d), (int) (((double) pointF2.y) + 0.5d));
        MultiObjectViewManager multiObjectViewManager2 = this.multiObjectViewManager;
        if (multiObjectViewManager2 == null) {
            Context context2 = this.context;
            float imageRatio = this.imageInfo.getImageRatio();
            Point point2 = this.centerOffset;
            ViewGroup viewGroup = this.view;
            j.b(viewGroup);
            this.multiObjectViewManager = new MultiObjectViewManager(context2, imageRatio, point2, viewGroup, objectResult3, this.isSelectionMode, true, true, false, this.dragListener, this.moveClipListener, i2, point, rect2);
        } else {
            j.b(multiObjectViewManager2);
            multiObjectViewManager2.clearViewList();
            MultiObjectViewManager multiObjectViewManager3 = this.multiObjectViewManager;
            j.b(multiObjectViewManager3);
            multiObjectViewManager3.updateObjectView(objectResult3, i2, point, rect2);
        }
        playDndFeedbacks();
        MultiObjectViewManager multiObjectViewManager4 = this.multiObjectViewManager;
        j.b(multiObjectViewManager4);
        this.objectCaptureView = multiObjectViewManager4.getObjectCaptureViewList().get(0);
    }

    public int startObjectCaptureByTap(float f, float f5) {
        int i2;
        Point point = new Point((int) (f + 0.5f), (int) (f5 + 0.5f));
        initObjectSelection(true);
        int newSelectObject = setNewSelectObject(point.x, point.y);
        if (this.objectCaptureView != null) {
            ViewGroup viewGroup = this.view;
            j.b(viewGroup);
            viewGroup.removeView(this.objectCaptureView);
        }
        if (newSelectObject < 0) {
            return newSelectObject;
        }
        if (this.multiObjectViewManager == null) {
            Context context2 = this.context;
            int i7 = newSelectObject;
            float imageRatio = this.imageInfo.getImageRatio();
            Point point2 = this.centerOffset;
            int i8 = i7;
            ViewGroup viewGroup2 = this.view;
            j.b(viewGroup2);
            int i10 = i8;
            ObjectResult objectResult2 = this.objectResult;
            j.b(objectResult2);
            i2 = i10;
            this.multiObjectViewManager = new MultiObjectViewManager(context2, imageRatio, point2, viewGroup2, objectResult2, this.isSelectionMode, this.useOutGlowLayerView, this.useParticleLayerView, true, this.dragListener, this.moveClipListener, -1, point, new Rect());
        } else {
            i2 = newSelectObject;
        }
        this.isSelectAll = false;
        MultiObjectViewManager multiObjectViewManager2 = this.multiObjectViewManager;
        int i11 = i2;
        if (multiObjectViewManager2 == null) {
            return i11;
        }
        multiObjectViewManager2.startAnimation(i11);
        return i11;
    }

    public void updateObjectRect(RectF rectF) {
        SelectableObject selectableObject2;
        j.e(rectF, "rect");
        float imageRatio = this.imageInfo.getImageRatio();
        LibLogger.i(TAG, "updateObjectRect c = " + imageRatio + " bitmap rect = " + rectF);
        setBitmapRect(rectF);
        updateImageInfo();
        float imageRatio2 = this.imageInfo.getImageRatio();
        setInitialSelection(imageRatio2);
        DimView dimView2 = this.dimView;
        if (!(dimView2 == null || !dimView2.isShowing() || (selectableObject2 = this.selectableObject) == null || this.selectedObjectIndex == -1)) {
            j.b(selectableObject2);
            Bitmap bitmap = selectableObject2.getBitmap();
            SelectableObject selectableObject3 = this.selectableObject;
            j.b(selectableObject3);
            ObjectInfo objectInfo = new ObjectInfo(bitmap, selectableObject3.getRect());
            Rect rect = new Rect(objectInfo.getBoundRect());
            this.objectInitRect = rect;
            rect.set(objectInfo.getBoundRect().left, objectInfo.getBoundRect().top, objectInfo.getBoundRect().right, objectInfo.getBoundRect().bottom);
            BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
            Bitmap copy = objectInfo.getObjBitmap().copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy, "copy(...)");
            Bitmap resizeBitmapByScale = bitmapUtil.resizeBitmapByScale(copy, imageRatio2);
            this.objectCaptureBitmap = resizeBitmapByScale;
            if (resizeBitmapByScale != null) {
                j.b(resizeBitmapByScale);
                int width = resizeBitmapByScale.getWidth();
                Bitmap bitmap2 = this.objectCaptureBitmap;
                j.b(bitmap2);
                int height = bitmap2.getHeight();
                LibLogger.i(TAG, "update position and size of ObjectCaptureView. objectCaptureBitmap size: w = " + width + " h = " + height);
                ObjectCaptureView objectCaptureView2 = this.objectCaptureView;
                j.b(objectCaptureView2);
                Bitmap bitmap3 = this.objectCaptureBitmap;
                j.b(bitmap3);
                Rect rect2 = this.objectInitRect;
                j.b(rect2);
                int i2 = rect2.left;
                Rect rect3 = this.objectInitRect;
                j.b(rect3);
                objectCaptureView2.setCapturedInfo(bitmap3, i2, rect3.top, Boolean.valueOf(this.isSelectionMode), this.useOutGlowLayerView, this.useParticleLayerView, this.isSelectAll);
                setDimView();
                ObjectCaptureView objectCaptureView3 = this.objectCaptureView;
                j.b(objectCaptureView3);
                objectCaptureView3.invalidate();
            }
            this.toolbarTouchPoint.set(-1, -1);
            showToolbarInternal(false);
        }
        ViewGroup viewGroup = this.view;
        j.b(viewGroup);
        viewGroup.invalidate();
    }

    public void useDefaultMenu(boolean z) {
        this.useDefaultMenu = z;
    }

    public int isObjectSelected(float f, float f5) {
        RectF calcTransformedRect = calcTransformedRect();
        float[] calcTransformedTouchPoint = calcTransformedTouchPoint((int) f, (int) f5);
        ObjectResult objectResult2 = this.objectResult;
        if (objectResult2 != null) {
            return objectResult2.findObjectIndexByPosition((int) calcTransformedTouchPoint[0], (int) calcTransformedTouchPoint[1], calcTransformedRect);
        }
        return -1;
    }

    public void setBitmap(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        this.imageInfo.setImageWidth(bitmap.getWidth());
        this.imageInfo.setImageHeight(bitmap.getHeight());
        this.imageInfo.setBitmap(bitmap);
        LibLogger.i(TAG, "setBitmap: bitmap = " + bitmap);
    }

    public void setObjectResult(MaskedObjectResult maskedObjectResult) {
        j.e(maskedObjectResult, "maskedObjectResult");
        LibLogger.i(TAG, "set MaskedObjectResult");
        if (maskedObjectResult.isCaptured()) {
            LibLogger.i(TAG, "set Vex objectResult");
            this.maskedObjects.add(maskedObjectResult);
            Bitmap maskedObject = maskedObjectResult.getMaskedObject();
            j.b(maskedObject);
            Rect boundingBox = maskedObjectResult.getBoundingBox();
            j.b(boundingBox);
            ObjectInfo objectInfo = new ObjectInfo(maskedObject, boundingBox);
            ArrayList arrayList = new ArrayList();
            ObjectResult objectResult2 = this.objectResult;
            j.b(objectResult2);
            if (!objectResult2.isCaptured()) {
                LibLogger.i(TAG, "Empty objectResult, So maskedObjectResult is new objectResult");
                arrayList.add(objectInfo);
                this.objectResult = new ObjectResult(true, objectInfo, objectInfo, arrayList, maskedObjectResult.getErrorCode(), maskedObjectResult.getSolutionInfo());
                return;
            }
            LibLogger.i(TAG, "Add maskedObjectResult in objectResult's list");
            ObjectResult objectResult3 = this.objectResult;
            j.b(objectResult3);
            arrayList.addAll(objectResult3.getObjects());
            arrayList.add(objectInfo);
            ObjectResult objectResult4 = this.objectResult;
            j.b(objectResult4);
            objectResult4.setOutput(objectInfo);
            ObjectResult objectResult5 = this.objectResult;
            j.b(objectResult5);
            objectResult5.setObjects(arrayList);
            return;
        }
        LibLogger.i(TAG, "MaskedObjectResult is fail");
    }

    private final boolean startObjectCaptureByLongClick(float f, float f5, boolean z) {
        int i2 = (int) (f + 0.5f);
        int i7 = (int) (f5 + 0.5f);
        if (!z) {
            initObjectSelection(false);
        }
        if ((!z ? setNewSelectObject(i2, i7) : -1) != -1 || (z && this.selectableObject != null)) {
            LibLogger.i(TAG, "startObjectCaptureByLongClick: #2 hit a object");
            playDndFeedbacks();
            if (!z) {
                this.hitObject = true;
            }
            if (!(this.view == null || this.objectCaptureView != null || this.objectResult == null)) {
                ObjectCaptureView objectCaptureView2 = new ObjectCaptureView(this.context);
                objectCaptureView2.setViewListener(new ObjectCaptureDrawHelperImpl$startObjectCaptureByLongClick$1$1(this));
                objectCaptureView2.setOnStartDragListener(this.dragListener);
                objectCaptureView2.setOnMoveClipListener(this.moveClipListener);
                this.objectCaptureView = objectCaptureView2;
                setDimView();
                ObjectCaptureView objectCaptureView3 = this.objectCaptureView;
                j.b(objectCaptureView3);
                objectCaptureView3.setDimView(this.dimView);
                SelectableObject selectableObject2 = this.selectableObject;
                if (selectableObject2 != null) {
                    j.b(selectableObject2);
                    Bitmap bitmap = selectableObject2.getBitmap();
                    SelectableObject selectableObject3 = this.selectableObject;
                    j.b(selectableObject3);
                    ObjectInfo objectInfo = new ObjectInfo(bitmap, selectableObject3.getRect());
                    Rect rect = this.objectInitRect;
                    if (rect == null) {
                        this.objectInitRect = new Rect(objectInfo.getBoundRect());
                    } else {
                        j.b(rect);
                        rect.set(objectInfo.getBoundRect().left, objectInfo.getBoundRect().top, objectInfo.getBoundRect().right, objectInfo.getBoundRect().bottom);
                    }
                    BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
                    Bitmap copy = objectInfo.getObjBitmap().copy(Bitmap.Config.ARGB_8888, true);
                    j.d(copy, "copy(...)");
                    Bitmap resizeBitmapByScale = bitmapUtil.resizeBitmapByScale(copy, this.imageInfo.getImageRatio());
                    this.objectCaptureBitmap = resizeBitmapByScale;
                    if (resizeBitmapByScale != null) {
                        j.b(resizeBitmapByScale);
                        int width = resizeBitmapByScale.getWidth();
                        Bitmap bitmap2 = this.objectCaptureBitmap;
                        j.b(bitmap2);
                        int height = bitmap2.getHeight();
                        LibLogger.i(TAG, "startObjectCaptureByLongClick: #2 setCapturedInfo bitmap w = " + width + " h = " + height);
                        if (!z) {
                            ObjectCaptureView objectCaptureView4 = this.objectCaptureView;
                            j.b(objectCaptureView4);
                            objectCaptureView4.getLastTouchPoint().set(i2, i7);
                        }
                        updateScaledObjectRectInScreen();
                        ObjectCaptureView objectCaptureView5 = this.objectCaptureView;
                        j.b(objectCaptureView5);
                        Rect rect2 = this.scaledObjectRectInScreen;
                        ViewGroup viewGroup = this.view;
                        j.b(viewGroup);
                        float translationX = viewGroup.getTranslationX();
                        ViewGroup viewGroup2 = this.view;
                        j.b(viewGroup2);
                        objectCaptureView5.setDistanceOfTouchFromCenter(rect2, translationX, viewGroup2.getTranslationY());
                        ObjectCaptureView objectCaptureView6 = this.objectCaptureView;
                        j.b(objectCaptureView6);
                        Bitmap bitmap3 = this.objectCaptureBitmap;
                        j.b(bitmap3);
                        Rect rect3 = this.objectInitRect;
                        j.b(rect3);
                        int i8 = rect3.left;
                        Rect rect4 = this.objectInitRect;
                        j.b(rect4);
                        objectCaptureView6.setCapturedInfo(bitmap3, i8, rect4.top, Boolean.valueOf(this.isSelectionMode), this.useOutGlowLayerView, this.useParticleLayerView, this.isSelectAll);
                        ViewGroup viewGroup3 = this.view;
                        j.b(viewGroup3);
                        if (viewGroup3.indexOfChild(this.dimView) == -1) {
                            ViewGroup viewGroup4 = this.view;
                            j.b(viewGroup4);
                            viewGroup4.addView(this.dimView);
                        }
                        ViewGroup viewGroup5 = this.view;
                        j.b(viewGroup5);
                        viewGroup5.addView(this.objectCaptureView);
                        calcCaptureImageScaleFactor();
                        Rect rect5 = this.scaledObjectRectInScreen;
                        LibLogger.i(TAG, "scaledObjectRectInScreen : " + rect5);
                        calcObjectRectForDnd();
                        if (!z) {
                            ObjectCaptureView objectCaptureView7 = this.objectCaptureView;
                            j.b(objectCaptureView7);
                            Point lastTouchPoint2 = objectCaptureView7.getLastTouchPoint();
                            Point point = this.lastTouchPoint;
                            lastTouchPoint2.set(point.x, point.y);
                        }
                    }
                }
            }
            this.dimRemoving = false;
            return true;
        }
        LibLogger.i(TAG, "startObjectCaptureByLongClick: #2 hit no object");
        this.hitObject = false;
        return false;
    }

    public boolean startObjectCaptureByButton(float f, float f5) {
        LibLogger.i(TAG, "startObjectCaptureByButton with (" + f + ArcCommonLog.TAG_COMMA + f5 + ")");
        if (isObjectSelected() && !this.isSelectAll) {
            return true;
        }
        initObjectSelection(true);
        boolean z = false;
        if (this.selectableObject != null) {
            LibLogger.i(TAG, "find a valid object");
            z = startObjectCaptureByLongClick(f, f5, false);
            if (this.imageInfo.getBitmap() != null) {
                VideoClipper videoClipper2 = this.videoClipper;
                Bitmap bitmap = this.imageInfo.getBitmap();
                j.b(bitmap);
                Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                j.d(copy, "copy(...)");
                videoClipper2.updateClippedImageInformation(copy, -1.0f, -1.0f);
            }
        }
        if (z) {
            showObjectCaptureResult(true);
        }
        return z;
    }
}
