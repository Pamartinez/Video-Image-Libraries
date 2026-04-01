package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.textclassifier.TextClassification;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.LockScreenHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.ClipboardUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PackageHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SemApiCompat;
import gc.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1196n;
import q3.a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 d2\u00020\u0001:\u0001dB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ3\u0010#\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u001f2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e¢\u0006\u0004\b#\u0010$J\u0019\u0010'\u001a\u00020\u000e2\b\u0010&\u001a\u0004\u0018\u00010%H\u0002¢\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\u0016H\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0016H\u0002¢\u0006\u0004\b+\u0010*J\u000f\u0010,\u001a\u00020\u0016H\u0002¢\u0006\u0004\b,\u0010*J\u0019\u0010-\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010%H\u0002¢\u0006\u0004\b-\u0010.J!\u00100\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010/\u001a\u00020\u0012H\u0002¢\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u00020\u00162\u0006\u00102\u001a\u00020\u0012H\u0002¢\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b7\u00108J\u0017\u00109\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b9\u00106J7\u0010@\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010;\u001a\u00020:2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020<H\u0002¢\u0006\u0004\b@\u0010AJ!\u0010G\u001a\u00020F2\u0006\u0010C\u001a\u00020B2\b\u0010E\u001a\u0004\u0018\u00010DH\u0002¢\u0006\u0004\bG\u0010HJ\u0019\u0010J\u001a\u0004\u0018\u00010D2\u0006\u0010I\u001a\u00020\u000eH\u0002¢\u0006\u0004\bJ\u0010KR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010LR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010MR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010NR \u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020F0O8\u0002X\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010R\u001a\u0004\u0018\u00010B8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010TR\u0016\u0010U\u001a\u00020\u001d8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bU\u0010TR\u0016\u0010V\u001a\u00020\u00128\u0002@\u0002X.¢\u0006\u0006\n\u0004\bV\u0010WR&\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00190Xj\b\u0012\u0004\u0012\u00020\u0019`Y8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b \u0010ZR\u0016\u0010!\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010[R\u0016\u0010\"\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\"\u0010[R\u0014\u0010\\\u001a\u00020\u000e8\u0002X\u0004¢\u0006\u0006\n\u0004\b\\\u0010[R$\u0010^\u001a\u0004\u0018\u00010]8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b^\u0010_\u001a\u0004\b`\u0010a\"\u0004\bb\u0010c¨\u0006e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextActionModeCallback;", "Landroid/view/ActionMode$Callback2;", "Landroid/content/Context;", "context", "Landroid/view/View;", "view", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/ActionModeListener;", "listener", "<init>", "(Landroid/content/Context;Landroid/view/View;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/ActionModeListener;)V", "Landroid/view/ActionMode;", "mode", "Landroid/view/Menu;", "menu", "", "onCreateActionMode", "(Landroid/view/ActionMode;Landroid/view/Menu;)Z", "onPrepareActionMode", "Landroid/view/MenuItem;", "item", "onActionItemClicked", "(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z", "Lme/x;", "onDestroyActionMode", "(Landroid/view/ActionMode;)V", "Landroid/graphics/Rect;", "outRect", "onGetContentRect", "(Landroid/view/ActionMode;Landroid/view/View;Landroid/graphics/Rect;)V", "", "selectedText", "", "visibleWordsRect", "isVerticalSelection", "isAllTextSelected", "setSelectedTextInformation", "(Ljava/lang/String;Ljava/util/List;ZZ)V", "Landroid/app/KeyguardManager;", "keyguardManager", "isScreenLocked", "(Landroid/app/KeyguardManager;)Z", "onCopyClicked", "()V", "onSelectAllClicked", "onTranslateClicked", "onShareClicked", "(Landroid/app/KeyguardManager;)V", "assistMenu", "onAssistMenuClicked", "(Landroid/app/KeyguardManager;Landroid/view/MenuItem;)V", "assistMenuItem", "onAssistMenuClickedInternal", "(Landroid/view/MenuItem;)V", "populateMenuWithItems", "(Landroid/view/Menu;)V", "canShowCopyMenu", "(Landroid/content/Context;)Z", "updateAssistMenuItems", "Landroid/app/RemoteAction;", "action", "", "itemId", "order", "showAsAction", "addAssistMenuItem", "(Landroid/view/Menu;Landroid/app/RemoteAction;III)Landroid/view/MenuItem;", "Landroid/app/PendingIntent;", "intent", "Landroid/content/Intent;", "textClassificationIntent", "Landroid/view/View$OnClickListener;", "createIntentOnClickListener", "(Landroid/app/PendingIntent;Landroid/content/Intent;)Landroid/view/View$OnClickListener;", "requestFromLock", "makeChooserIntent", "(Z)Landroid/content/Intent;", "Landroid/content/Context;", "Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/ActionModeListener;", "", "assistClickHandlers", "Ljava/util/Map;", "pendingIntent", "Landroid/app/PendingIntent;", "Ljava/lang/String;", "lastSelectedText", "lastSelectedMenuItem", "Landroid/view/MenuItem;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Ljava/util/ArrayList;", "Z", "canTranslate", "Landroid/view/textclassifier/TextClassification;", "textClassification", "Landroid/view/textclassifier/TextClassification;", "getTextClassification", "()Landroid/view/textclassifier/TextClassification;", "setTextClassification", "(Landroid/view/textclassifier/TextClassification;)V", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextActionModeCallback extends ActionMode.Callback2 {
    public static final Companion Companion = new Companion((e) null);
    private final Map<MenuItem, View.OnClickListener> assistClickHandlers = new HashMap();
    private final boolean canTranslate;
    /* access modifiers changed from: private */
    public final Context context;
    private boolean isAllTextSelected;
    private boolean isVerticalSelection;
    private MenuItem lastSelectedMenuItem;
    /* access modifiers changed from: private */
    public String lastSelectedText = "";
    /* access modifiers changed from: private */
    public final ActionModeListener listener;
    private PendingIntent pendingIntent;
    private String selectedText = "";
    private TextClassification textClassification;
    private View view;
    private ArrayList<Rect> visibleWordsRect = new ArrayList<>();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextActionModeCallback$Companion;", "", "<init>", "()V", "TAG", "", "TRANSLATE_PACKAGE_NAME", "TRANSLATE_ACTIVITY", "ID_ASSIST", "", "MENU_ITEM_COPY", "MENU_ITEM_TRANSLATE", "MENU_ITEM_SELECT_ALL", "MENU_ITEM_SHARE", "MENU_ITEM_SECONDARY_ASSIST", "HEIGHT_MARGIN_VERTICAL", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextActionModeCallback(Context context2, View view2, ActionModeListener actionModeListener) {
        j.e(context2, "context");
        j.e(view2, "view");
        j.e(actionModeListener, "listener");
        this.context = context2;
        this.view = view2;
        this.listener = actionModeListener;
        this.canTranslate = PackageHelper.INSTANCE.isPackageExists(context2, "com.samsung.android.app.interpreter");
    }

    private final MenuItem addAssistMenuItem(Menu menu, RemoteAction remoteAction, int i2, int i7, int i8) {
        Intent intent;
        MenuItem add = menu.add(16908353, i2, i7, remoteAction.getTitle().toString());
        add.setContentDescription(remoteAction.getContentDescription());
        if (remoteAction.shouldShowIcon()) {
            add.setIcon(remoteAction.getIcon().loadDrawable(this.context));
        }
        add.setShowAsAction(i8);
        Map<MenuItem, View.OnClickListener> map = this.assistClickHandlers;
        PendingIntent actionIntent = remoteAction.getActionIntent();
        j.d(actionIntent, "getActionIntent(...)");
        TextClassification textClassification2 = this.textClassification;
        if (textClassification2 != null) {
            intent = textClassification2.getIntent();
        } else {
            intent = null;
        }
        map.put(add, createIntentOnClickListener(actionIntent, intent));
        return add;
    }

    private final boolean canShowCopyMenu(Context context2) {
        SemApiCompat semApiCompat = SemApiCompat.INSTANCE;
        if (semApiCompat.isKnoxMode(context2) || semApiCompat.isAfw(context2)) {
            return false;
        }
        return true;
    }

    private final View.OnClickListener createIntentOnClickListener(PendingIntent pendingIntent2, Intent intent) {
        return new b(pendingIntent2, this, intent, 2);
    }

    /* access modifiers changed from: private */
    public static final void createIntentOnClickListener$lambda$6(PendingIntent pendingIntent2, TextActionModeCallback textActionModeCallback, Intent intent, View view2) {
        try {
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            if (Build.VERSION.SDK_INT >= 33) {
                makeBasic.setPendingIntentBackgroundActivityLaunchAllowed(true);
            }
            pendingIntent2.send(textActionModeCallback.context, 0, intent, (PendingIntent.OnFinished) null, (Handler) null, (String) null, makeBasic.toBundle());
        } catch (PendingIntent.CanceledException e) {
            LibLogger.e("TextActionModeCallback", "Error sending PendingIntent", e);
        }
    }

    private final boolean isScreenLocked(KeyguardManager keyguardManager) {
        if (keyguardManager == null || !keyguardManager.isKeyguardLocked()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final Intent makeChooserIntent(boolean z) {
        String str;
        IntentSender intentSender;
        if (z) {
            str = this.lastSelectedText;
        } else {
            str = this.selectedText;
        }
        if (str.length() <= 0) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.removeExtra("android.intent.extra.TEXT");
        intent.putExtra("android.intent.extra.TEXT", str);
        PendingIntent pendingIntent2 = this.pendingIntent;
        if (pendingIntent2 == null || !(this.context instanceof Activity)) {
            return Intent.createChooser(intent, (CharSequence) null);
        }
        if (pendingIntent2 != null) {
            intentSender = pendingIntent2.getIntentSender();
        } else {
            intentSender = null;
        }
        return Intent.createChooser(intent, (CharSequence) null, intentSender);
    }

    private final void onAssistMenuClicked(KeyguardManager keyguardManager, MenuItem menuItem) {
        this.listener.onAssistMenuClicked();
        if (isScreenLocked(keyguardManager)) {
            this.lastSelectedText = this.selectedText;
            if (keyguardManager != null) {
                Context context2 = this.context;
                j.c(context2, "null cannot be cast to non-null type android.app.Activity");
                keyguardManager.requestDismissKeyguard((Activity) context2, new TextActionModeCallback$onAssistMenuClicked$1(this, menuItem));
                return;
            }
            return;
        }
        onAssistMenuClickedInternal(menuItem);
    }

    /* access modifiers changed from: private */
    public final void onAssistMenuClickedInternal(MenuItem menuItem) {
        View.OnClickListener onClickListener = this.assistClickHandlers.get(menuItem);
        if (onClickListener != null) {
            onClickListener.onClick(this.view);
        }
    }

    private final void onCopyClicked() {
        LockScreenHelper.requestDismissKeyguard$default(LockScreenHelper.INSTANCE, this.context, new a(this, 1), (Ae.a) null, 4, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final x onCopyClicked$lambda$0(TextActionModeCallback textActionModeCallback) {
        ClipboardUtil.INSTANCE.copyClipboard(textActionModeCallback.context, textActionModeCallback.selectedText);
        textActionModeCallback.listener.onCopyClicked();
        return x.f4917a;
    }

    private final void onSelectAllClicked() {
        this.listener.onSelectAllClicked();
    }

    private final void onShareClicked(KeyguardManager keyguardManager) {
        int i2;
        if (isScreenLocked(keyguardManager)) {
            this.lastSelectedText = this.selectedText;
            if (keyguardManager != null) {
                Context context2 = this.context;
                j.c(context2, "null cannot be cast to non-null type android.app.Activity");
                keyguardManager.requestDismissKeyguard((Activity) context2, new TextActionModeCallback$onShareClicked$1(this));
                return;
            }
            return;
        }
        Intent makeChooserIntent = makeChooserIntent(false);
        if (makeChooserIntent != null) {
            if (this.context instanceof Activity) {
                i2 = PropertyOptions.DELETE_EXISTING;
            } else {
                i2 = 268435456;
            }
            makeChooserIntent.setFlags(i2);
            this.context.startActivity(makeChooserIntent);
            this.listener.onShareClicked();
        }
    }

    private final void onTranslateClicked() {
        LockScreenHelper.requestDismissKeyguard$default(LockScreenHelper.INSTANCE, this.context, new a(this, 0), (Ae.a) null, 4, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final x onTranslateClicked$lambda$2(TextActionModeCallback textActionModeCallback) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.samsung.android.app.interpreter", "com.samsung.android.app.interpreter.translation.view.TranslationActivity"));
        intent.putExtra("android.intent.extra.TEXT", textActionModeCallback.selectedText);
        if (textActionModeCallback.context instanceof Activity) {
            intent.setFlags(PropertyOptions.DELETE_EXISTING);
        } else {
            intent.setFlags(268435456);
        }
        try {
            textActionModeCallback.context.startActivity(intent);
            textActionModeCallback.listener.onTranslateClicked();
        } catch (ActivityNotFoundException e) {
            LibLogger.e("TextActionModeCallback", "ActivityNotFoundException", e);
        }
        return x.f4917a;
    }

    private final void populateMenuWithItems(Menu menu) {
        updateAssistMenuItems(menu);
        if (canShowCopyMenu(this.context)) {
            menu.add(0, 1, 1, R$string.copy);
        }
        if (Rune.INSTANCE.isSupportTranslation() && this.canTranslate) {
            menu.add(0, 2, 2, R$string.translate).setShowAsAction(1);
        }
        if (!this.isAllTextSelected) {
            menu.add(0, 3, 3, R$string.selectAll);
        }
        menu.add(0, 4, 4, R$string.share);
    }

    private final void updateAssistMenuItems(Menu menu) {
        int i2;
        TextClassification textClassification2 = this.textClassification;
        if (textClassification2 == null) {
            return;
        }
        if (textClassification2.getActions().isEmpty()) {
            LibLogger.d("TextActionModeCallback", "updateAssistMenuItems - Null or Empty, or screen is locked");
            return;
        }
        if (menu.findItem(16908353) == null) {
            RemoteAction remoteAction = textClassification2.getActions().get(0);
            j.d(remoteAction, "get(...)");
            addAssistMenuItem(menu, remoteAction, 16908353, 0, 2);
        }
        int size = textClassification2.getActions().size();
        if (size > 1 && menu.findItem(5) == null) {
            if (size > 3) {
                i2 = 3;
            } else {
                i2 = size;
            }
            for (int i7 = 1; i7 < i2; i7++) {
                RemoteAction remoteAction2 = textClassification2.getActions().get(i7);
                j.d(remoteAction2, "get(...)");
                int i8 = i7 + 4;
                addAssistMenuItem(menu, remoteAction2, i8, i8, 0);
            }
        }
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        j.e(actionMode, "mode");
        j.e(menuItem, "item");
        int itemId = menuItem.getItemId();
        this.lastSelectedMenuItem = menuItem;
        KeyguardManager keyguardManager = (KeyguardManager) this.context.getSystemService("keyguard");
        if (menuItem.getGroupId() == 16908353) {
            onAssistMenuClicked(keyguardManager, menuItem);
        }
        if (itemId == 1) {
            onCopyClicked();
        } else if (itemId == 2) {
            onTranslateClicked();
        } else if (itemId == 3) {
            onSelectAllClicked();
        } else if (itemId == 4) {
            onShareClicked(keyguardManager);
        }
        return true;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        j.e(actionMode, "mode");
        j.e(menu, "menu");
        actionMode.setTitle("");
        actionMode.setSubtitle("");
        actionMode.setTitleOptionalHint(true);
        populateMenuWithItems(menu);
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        j.e(actionMode, "mode");
    }

    public void onGetContentRect(ActionMode actionMode, View view2, Rect rect) {
        j.e(actionMode, "mode");
        j.e(view2, "view");
        j.e(rect, "outRect");
        if (!this.visibleWordsRect.isEmpty()) {
            int[] iArr = new int[2];
            view2.getLocationInWindow(iArr);
            Object systemService = this.context.getSystemService("window");
            j.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            Rect bounds = ((WindowManager) systemService).getCurrentWindowMetrics().getBounds();
            j.d(bounds, "getBounds(...)");
            LibLogger.d("TextActionModeCallback", A.a.d(iArr[0], iArr[1], "current location in window: (", ArcCommonLog.TAG_COMMA, ")"));
            LibLogger.d("TextActionModeCallback", "current window bounds: " + bounds);
            Iterator<T> it = this.visibleWordsRect.iterator();
            if (it.hasNext()) {
                int i2 = ((Rect) it.next()).left;
                while (it.hasNext()) {
                    int i7 = ((Rect) it.next()).left;
                    if (i2 > i7) {
                        i2 = i7;
                    }
                }
                int i8 = i2 - iArr[0];
                Iterator<T> it2 = this.visibleWordsRect.iterator();
                if (it2.hasNext()) {
                    int i10 = ((Rect) it2.next()).top;
                    while (it2.hasNext()) {
                        int i11 = ((Rect) it2.next()).top;
                        if (i10 > i11) {
                            i10 = i11;
                        }
                    }
                    int i12 = i10 - iArr[1];
                    Iterator<T> it3 = this.visibleWordsRect.iterator();
                    if (it3.hasNext()) {
                        int i13 = ((Rect) it3.next()).right;
                        while (it3.hasNext()) {
                            int i14 = ((Rect) it3.next()).right;
                            if (i13 < i14) {
                                i13 = i14;
                            }
                        }
                        int i15 = i13 - iArr[0];
                        Iterator<T> it4 = this.visibleWordsRect.iterator();
                        if (it4.hasNext()) {
                            int i16 = ((Rect) it4.next()).bottom;
                            while (it4.hasNext()) {
                                int i17 = ((Rect) it4.next()).bottom;
                                if (i16 < i17) {
                                    i16 = i17;
                                }
                            }
                            Rect rect2 = new Rect(i8, i12, i15, i16 - iArr[1]);
                            Rect rect3 = new Rect();
                            if (!rect3.setIntersect(bounds, rect2)) {
                                rect3 = rect2;
                            }
                            if (this.isVerticalSelection) {
                                rect3.top -= 30;
                                rect3.bottom -= 30;
                            }
                            LibLogger.d("TextActionModeCallback", "visibleRect: " + rect2 + " targetRect: " + rect3);
                            rect.set(rect3);
                            return;
                        }
                        throw new NoSuchElementException();
                    }
                    throw new NoSuchElementException();
                }
                throw new NoSuchElementException();
            }
            throw new NoSuchElementException();
        }
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        j.e(actionMode, "mode");
        j.e(menu, "menu");
        updateAssistMenuItems(menu);
        return false;
    }

    public final void setSelectedTextInformation(String str, List<Rect> list, boolean z, boolean z3) {
        j.e(str, "selectedText");
        j.e(list, "visibleWordsRect");
        this.selectedText = str;
        Iterable<Rect> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (Rect rect : iterable) {
            arrayList.add(new Rect(rect));
        }
        this.visibleWordsRect = new ArrayList<>(arrayList);
        this.isVerticalSelection = z;
        this.isAllTextSelected = z3;
    }

    public final void setTextClassification(TextClassification textClassification2) {
        this.textClassification = textClassification2;
    }
}
