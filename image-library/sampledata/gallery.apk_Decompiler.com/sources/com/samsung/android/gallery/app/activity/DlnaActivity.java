package com.samsung.android.gallery.app.activity;

import Ab.a;
import C3.C0391a;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DlnaActivity extends AppCompatActivity {
    private final int EXIT_SWIPE_LEFT = 1;
    private final int EXIT_SWIPE_RIGHT = 2;
    private int mExitSwipe = 0;
    private final SubscriberListener mFinishListener = new C0391a(0, this);
    private GestureDetector mGestureDetector;
    private boolean mIsRTL;
    protected GalleryToolbar mToolbar;
    private final View.OnClickListener onNavigationPressed = new a(9, this);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        public /* synthetic */ GestureListener(DlnaActivity dlnaActivity, int i2) {
            this();
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
            if (motionEvent == null) {
                return false;
            }
            float x9 = motionEvent2.getX() - motionEvent.getX();
            if (Math.abs(x9) <= Math.abs(motionEvent2.getY() - motionEvent.getY())) {
                return false;
            }
            if (x9 > 0.0f) {
                DlnaActivity.this.handleSwipeRight();
                return true;
            }
            DlnaActivity.this.handleSwipeLeft();
            return true;
        }

        private GestureListener() {
        }
    }

    private boolean disconnectDlnaOriginalContent() {
        Blackboard.getApplicationInstance().post("global://remote2/event/disconnect_dmr_content", (Object) null);
        return false;
    }

    /* access modifiers changed from: private */
    public void handleSwipeLeft() {
        this.mExitSwipe = 1;
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public void handleSwipeRight() {
        this.mExitSwipe = 2;
        onBackPressed();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0018, code lost:
        if (r4.mIsRTL != false) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r4.mIsRTL != false) goto L_0x0025;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFinish(java.lang.Object r5, android.os.Bundle r6) {
        /*
            r4 = this;
            java.lang.String r5 = "DlnaActivity"
            java.lang.String r6 = "onFinish"
            com.samsung.android.gallery.support.utils.Log.rm(r5, r6)
            android.content.Intent r5 = new android.content.Intent
            r5.<init>()
            int r6 = r4.mExitSwipe
            r0 = 2
            java.lang.String r1 = "next"
            java.lang.String r2 = "prev"
            r3 = 1
            if (r6 != r3) goto L_0x001c
            boolean r6 = r4.mIsRTL
            if (r6 == 0) goto L_0x0025
        L_0x001a:
            r1 = r2
            goto L_0x0025
        L_0x001c:
            if (r6 != r0) goto L_0x0023
            boolean r6 = r4.mIsRTL
            if (r6 == 0) goto L_0x001a
            goto L_0x0025
        L_0x0023:
            java.lang.String r1 = ""
        L_0x0025:
            java.lang.String r6 = "exitSwipe"
            r5.putExtra(r6, r1)
            r6 = -1
            r4.setResult(r6, r5)
            r4.finish()
            int r5 = r4.mExitSwipe
            if (r5 != r3) goto L_0x0039
            r5 = 2130772252(0x7f01011c, float:1.7147617E38)
            goto L_0x0042
        L_0x0039:
            if (r5 != r0) goto L_0x003f
            r5 = 2130772254(0x7f01011e, float:1.7147621E38)
            goto L_0x0042
        L_0x003f:
            r5 = 2130772011(0x7f01002b, float:1.7147128E38)
        L_0x0042:
            r6 = 2130771996(0x7f01001c, float:1.7147098E38)
            r4.overridePendingTransition(r6, r5)
            r4.disconnectDlnaOriginalContent()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.DlnaActivity.onFinish(java.lang.Object, android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public void onNavigationPressed(View view) {
        onBackPressed();
    }

    private void setFullScreen() {
        try {
            getWindow().setFlags(1024, 1024);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("setStatusBarColor failed e="), "DlnaActivity");
        }
    }

    private void setLayout() {
        setFullScreen();
        setNavigationBarColor();
        setContentView((int) R.layout.dlna_activity_layout);
        SeApiCompat.disableViewRoundedCorner(getWindow().getDecorView());
    }

    private void setNavigationBarColor() {
        try {
            getWindow().setNavigationBarColor(getColor(R.color.black_color));
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("setStatusBarColor failed e="), "DlnaActivity");
        }
    }

    private void subscribeEvents() {
        try {
            Blackboard.getInstance(getIntent().getStringExtra("blackboard_name")).subscribeOnUi("command://FinishDlnaActivity", this.mFinishListener);
        } catch (Exception e) {
            Log.rme("DlnaActivity", "subscribeEvents is failed: " + e.toString());
        }
    }

    private void unsubscribeEvents() {
        try {
            Blackboard.getInstance(getIntent().getStringExtra("blackboard_name")).unsubscribe("command://FinishDlnaActivity", this.mFinishListener);
        } catch (Exception e) {
            Log.rme("DlnaActivity", "unsubscribeEvents is failed: " + e.toString());
        }
    }

    private void updateToolbar() {
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_detailview_light);
            this.mToolbar.setNavigationContentDescription(R.string.navigate_up);
            this.mToolbar.setOverflowIcon(getApplicationContext().getDrawable(R.drawable.gallery_ic_ab_more));
            this.mToolbar.setTitle((CharSequence) null);
            this.mToolbar.setSubtitle((CharSequence) null);
            this.mToolbar.setNavigationOnClickListener(this.onNavigationPressed);
            this.mToolbar.setBackgroundColor(-16777216);
        }
    }

    public void onBackPressed() {
        Log.rm("DlnaActivity", "onBackPressed start");
        if (disconnectDlnaOriginalContent()) {
            super.onBackPressed();
            Log.rm("DlnaActivity", "onBackPressed success");
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.rm("DlnaActivity", "onConfigurationChanged");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setLayout();
        this.mIsRTL = Features.isEnabled(Features.IS_RTL);
        this.mGestureDetector = new GestureDetector(this, new GestureListener(this, 0));
        Log.rm("DlnaActivity", "onCreate");
    }

    public void onDestroy() {
        Log.rm("DlnaActivity", "onDestroy");
        disconnectDlnaOriginalContent();
        unsubscribeEvents();
        this.mGestureDetector = null;
        super.onDestroy();
    }

    public void onResume() {
        Log.rm("DlnaActivity", "onResume");
        this.mToolbar = (GalleryToolbar) findViewById(R.id.toolbar);
        updateToolbar();
        subscribeEvents();
        super.onResume();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mGestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }
}
