package com.samsung.android.gallery.app.ui.list.albums.mx.header;

import Fb.h;
import H.a;
import J5.e;
import K4.b;
import K4.c;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.IAlbumsHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animations.TipCardDismissAnimation;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsHeaderView extends FrameLayout implements IMxAlbumsHeaderView, IAlbumsHeaderView {
    private boolean mExistInvitation;
    private TextView mInvitationAccept;
    private TextView mInvitationDecline;
    private TextView mInvitationExpireDate;
    private ImageView mInvitationHostIcon;
    private TextView mInvitationHostName;
    private TextView mInvitationTitleText;
    private View mInvitationView;
    private IAlbumsHeaderView.OnDataChangeListener mListener;
    private final String mLocationKey;
    private MxAlbumsHeaderPresenter mPresenter;
    private MxAlbumsHeaderViewState mState;

    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$header$MxAlbumsHeaderViewState;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState[] r0 = com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$header$MxAlbumsHeaderViewState = r0
                com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState r1 = com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState.CUSTOMIZE_ESSENTIAL_ALBUM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$header$MxAlbumsHeaderViewState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState r1 = com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState.TURN_OFF_ESSENTIAL_ALBUM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$header$MxAlbumsHeaderViewState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState r1 = com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState.INVITATION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$header$MxAlbumsHeaderViewState     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState r1 = com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState.SEC_MP_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView.AnonymousClass1.<clinit>():void");
        }
    }

    public MxAlbumsHeaderView(EventContext eventContext) {
        super(eventContext.getContext());
        String str;
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            str = "location://sharing/albums";
        } else {
            str = "location://sharing/albums/invitations";
        }
        this.mLocationKey = str;
        initPresenter(eventContext);
        updateViews(getState(), false, false);
    }

    private boolean checkFirstTipCard() {
        return PreferenceCache.MxAlbumTipCard.getBoolean();
    }

    private boolean checkSecondTipCard() {
        if (PreferenceCache.MxAlbumTipCardCount.getInt() >= 5) {
            return true;
        }
        return false;
    }

    private MxAlbumsHeaderViewState getState() {
        if (((Boolean) Blackboard.getApplicationInstance().read("data://user/SecurityExceptionOnSecMp", Boolean.FALSE)).booleanValue()) {
            return MxAlbumsHeaderViewState.SEC_MP_PERMISSION;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            if (checkFirstTipCard()) {
                return MxAlbumsHeaderViewState.CUSTOMIZE_ESSENTIAL_ALBUM;
            }
            if (checkSecondTipCard()) {
                return MxAlbumsHeaderViewState.TURN_OFF_ESSENTIAL_ALBUM;
            }
        }
        return MxAlbumsHeaderViewState.INVITATION;
    }

    private void guaranteeLayoutMargin(TextView textView) {
        textView.setMaxWidth((int) ((((((float) ResourceCompat.getWindowWidth(getContext())) - getResources().getDimension(R.dimen.tip_card_button_layout_padding_start)) - getResources().getDimension(R.dimen.tip_card_button_layout_padding_end)) - getResources().getDimension(R.dimen.tip_card_button_inside_margin)) / 2.0f));
    }

    private void initGuideFirstTipCard() {
        this.mState = MxAlbumsHeaderViewState.CUSTOMIZE_ESSENTIAL_ALBUM;
        View.inflate(getContext(), R.layout.tip_card_layout_pictures, this);
        ((TextView) findViewById(R.id.headerTitle)).setText(R.string.mx_albums_tip_card_title);
        ((TextView) findViewById(R.id.headerContent)).setText(R.string.mx_albums_tip_card_description);
        updateCancelButton();
        updateDoneButton(R.string.select_essential_albums);
    }

    private void initGuideSecondTipCard() {
        this.mState = MxAlbumsHeaderViewState.TURN_OFF_ESSENTIAL_ALBUM;
        View.inflate(getContext(), R.layout.tip_card_layout_pictures, this);
        ViewUtils.setVisibleOrGone(findViewById(R.id.headerTitle), false);
        ((TextView) findViewById(R.id.headerContent)).setText(R.string.mx_albums_tip_card_description_second);
        updateCancelButton();
        updateDoneButton(R.string.go_to_settings);
    }

    private void initInvitation() {
        MxAlbumsHeaderPresenter mxAlbumsHeaderPresenter = this.mPresenter;
        if (mxAlbumsHeaderPresenter != null && !mxAlbumsHeaderPresenter.supportDrawerLayout()) {
            initInvitationViews();
            loadInvitation();
        }
    }

    private void initInvitationViews() {
        View.inflate(getContext(), R.layout.mx_albums_header_view, this);
        this.mInvitationView = findViewById(R.id.invitation_tip_card);
        ImageView imageView = (ImageView) findViewById(R.id.invitation_icon_view);
        this.mInvitationHostIcon = imageView;
        ViewUtils.setViewShape(imageView, 0, 0.0f);
        this.mInvitationHostName = (TextView) this.mInvitationView.findViewById(R.id.host);
        this.mInvitationExpireDate = (TextView) this.mInvitationView.findViewById(R.id.expire_date);
        this.mInvitationAccept = (TextView) this.mInvitationView.findViewById(R.id.accept);
        this.mInvitationDecline = (TextView) this.mInvitationView.findViewById(R.id.decline);
        this.mInvitationTitleText = (TextView) this.mInvitationView.findViewById(R.id.title);
    }

    private void initPresenter(EventContext eventContext) {
        this.mPresenter = new MxAlbumsHeaderPresenter(this, eventContext);
    }

    private void initSecMpPermissionTipCard() {
        this.mState = MxAlbumsHeaderViewState.SEC_MP_PERMISSION;
        View.inflate(getContext(), R.layout.tip_card_layout_pictures, this);
        ViewUtils.setVisibleOrGone(findViewById(R.id.headerTitle), false);
        ViewUtils.setVisibleOrGone(findViewById(R.id.cancelView), false);
        ((TextView) findViewById(R.id.headerContent)).setText(getContext().getString(R.string.sec_mp_security_allow_permission_description, new Object[]{PackageMonitorCompat.getInstance().getApplicationLabel("com.samsung.android.providers.media")}));
        updateDoneButton(R.string.settings);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setThumbnailOnHostIcon$5(Bitmap bitmap, ImageView imageView) {
        imageView.setBackgroundResource(R.color.sharingv2_invitation_icon_bg_color);
        imageView.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setThumbnailOnHostIcon$6(Bitmap bitmap) {
        if (bitmap != null) {
            Optional.ofNullable(this.mInvitationHostIcon).ifPresent(new e(bitmap, 2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setThumbnailOnHostIcon$7(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new a(22, this, bitmap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCancelButton$2(View view) {
        this.mPresenter.onTipCardDeclineClicked(this.mState);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDoneButton$1(View view) {
        this.mPresenter.onTipCardAcceptClicked(this.mState);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateInvitation$3(MediaItem mediaItem, View view) {
        this.mPresenter.onInvitationAcceptClicked(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateInvitation$4(MediaItem mediaItem, View view) {
        this.mPresenter.onInvitationDeclineClicked(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViews$0(ViewGroup viewGroup, MxAlbumsHeaderViewState mxAlbumsHeaderViewState) {
        ViewUtils.removeAllViews(viewGroup);
        updateNextState(mxAlbumsHeaderViewState);
    }

    private void loadInvitation() {
        this.mPresenter.loadInvitation();
    }

    private void setThumbnailOnHostIcon(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new K4.a(0, this));
        }
    }

    private void updateCancelButton() {
        TextView textView = (TextView) findViewById(R.id.cancelView);
        textView.setText(R.string.not_now);
        guaranteeLayoutMargin(textView);
        findViewById(R.id.cancelView).setOnClickListener(new b(this, 0));
        SeApiCompat.setButtonShapeEnabled(textView);
        textView.setContentDescription(textView.getText() + ArcCommonLog.TAG_COMMA + getContext().getString(R.string.speak_button));
    }

    private void updateDoneButton(int i2) {
        TextView textView = (TextView) findViewById(R.id.doneView);
        textView.setText(i2);
        textView.setOnClickListener(new b(this, 1));
        SeApiCompat.setButtonShapeEnabled(textView);
        textView.setContentDescription(textView.getText() + ArcCommonLog.TAG_COMMA + getContext().getString(R.string.speak_button));
    }

    private boolean updateInvitation(MediaItem mediaItem) {
        boolean z;
        c cVar;
        if (!this.mPresenter.supportDrawerLayout()) {
            boolean z3 = this.mExistInvitation;
            if (mediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            this.mExistInvitation = z;
            if (z) {
                setThumbnailOnHostIcon(mediaItem);
                this.mInvitationTitleText.setText(MediaItemMde.getInvitationSpaceName(mediaItem));
                this.mInvitationHostName.setText(MdeAlbumHelper.getRequesterNameText(MediaItemMde.getInvitationRequesterName(mediaItem)));
                this.mInvitationExpireDate.setText(MdeAlbumHelper.getExpiredTimeText(MediaItemMde.getInvitationExpiredTime(mediaItem)));
            }
            TextView textView = this.mInvitationAccept;
            c cVar2 = null;
            if (this.mExistInvitation) {
                cVar = new c(this, mediaItem, 0);
            } else {
                cVar = null;
            }
            textView.setOnClickListener(cVar);
            TextView textView2 = this.mInvitationDecline;
            if (this.mExistInvitation) {
                cVar2 = new c(this, mediaItem, 1);
            }
            textView2.setOnClickListener(cVar2);
            ViewUtils.setVisibleOrGone(this.mInvitationView, this.mExistInvitation);
            if (z3 != this.mExistInvitation) {
                return true;
            }
        }
        return false;
    }

    private void updateNextState(MxAlbumsHeaderViewState mxAlbumsHeaderViewState) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$header$MxAlbumsHeaderViewState[mxAlbumsHeaderViewState.ordinal()];
        if (i2 == 1) {
            initGuideFirstTipCard();
        } else if (i2 == 2) {
            initGuideSecondTipCard();
        } else if (i2 == 3) {
            initInvitation();
        } else if (i2 == 4) {
            initSecMpPermissionTipCard();
        }
        this.mState = mxAlbumsHeaderViewState;
    }

    private void updateViews(MxAlbumsHeaderViewState mxAlbumsHeaderViewState, boolean z, boolean z3) {
        if (this.mState == mxAlbumsHeaderViewState && !z && !z3) {
            return;
        }
        if (z3) {
            new TipCardDismissAnimation().start(this, new J6.c(this, this, mxAlbumsHeaderViewState, 3));
            return;
        }
        ViewUtils.removeAllViews(this);
        updateNextState(mxAlbumsHeaderViewState);
    }

    public void destroy() {
        MxAlbumsHeaderPresenter mxAlbumsHeaderPresenter = this.mPresenter;
        if (mxAlbumsHeaderPresenter != null) {
            mxAlbumsHeaderPresenter.close();
            this.mPresenter = null;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || super.dispatchTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public void onDataChanged() {
        IAlbumsHeaderView.OnDataChangeListener onDataChangeListener = this.mListener;
        if (onDataChangeListener != null) {
            ((MxAlbumsViewAdapter) ((h) onDataChangeListener).e).lambda$createHeaderView$0();
        }
    }

    public void onEnableHeaderView(boolean z) {
        ViewUtils.setAllViewEnabled(findViewById(R.id.tip_card_layout), z, true);
    }

    public void refreshHeader(boolean z, boolean z3) {
        updateViews(getState(), z, z3);
    }

    public void setOnDataChangedListener(IAlbumsHeaderView.OnDataChangeListener onDataChangeListener) {
        this.mListener = onDataChangeListener;
    }

    public boolean updateHeaderView(MediaItem mediaItem) {
        return updateInvitation(mediaItem);
    }

    public View getView() {
        return this;
    }
}
