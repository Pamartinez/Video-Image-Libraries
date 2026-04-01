package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableWord;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.HandleDrawInfo;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.DrawUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.VectorUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0000\u0018\u0000 G2\u00020\u0001:\u0001GB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\nJ\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001f\u0010\u001aJ\u000f\u0010 \u001a\u00020\bH\u0002¢\u0006\u0004\b \u0010\nJ\u000f\u0010!\u001a\u00020\bH\u0002¢\u0006\u0004\b!\u0010\nJ\u000f\u0010\"\u001a\u00020\bH\u0002¢\u0006\u0004\b\"\u0010\nJ'\u0010'\u001a\u00020\b*\u00020#2\u0006\u0010%\u001a\u00020$2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0004\b'\u0010(J\u001f\u0010,\u001a\u00020\u00182\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0002¢\u0006\u0004\b,\u0010-J#\u0010.\u001a\u00020\b*\u00020#2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0002¢\u0006\u0004\b.\u0010/J\u0019\u00102\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u000200H\u0002¢\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\bH\u0002¢\u0006\u0004\b4\u0010\nJ\u000f\u00105\u001a\u00020\u0018H\u0002¢\u0006\u0004\b5\u0010\u001aJ\u001f\u00106\u001a\u00020\b2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0002¢\u0006\u0004\b6\u00107J\u0017\u00109\u001a\u0002002\u0006\u00108\u001a\u000200H\u0002¢\u0006\u0004\b9\u0010:J\u001f\u0010;\u001a\u00020\b2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0002¢\u0006\u0004\b;\u00107R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010<R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010=R\u0014\u0010>\u001a\u00020#8\u0002X\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020#8\u0002X\u0004¢\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010A\u001a\u0002008\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\"\u0010C\u001a\u00020\u00188\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bC\u0010\u001a\"\u0004\bE\u0010F¨\u0006H"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/HandleController;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper;", "textSelectionHelper", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper;)V", "Lme/x;", "updatePosition", "()V", "", "scale", "setScaleFactor", "(F)V", "setEmpty", "Landroid/view/View;", "teView", "setImageInfo", "(Landroid/view/View;)V", "Landroid/graphics/Canvas;", "canvas", "drawHandles", "(Landroid/graphics/Canvas;)V", "", "isHandleMoving", "()Z", "Landroid/view/MotionEvent;", "event", "handleTouchEvent", "(Landroid/view/MotionEvent;)Z", "isHandleNotEmpty", "setHandleMovingStateToIdle", "updateHandlePosition", "updateHandlePositionWithAnimation", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "selectedChar", "charForAnimation", "updateHandle", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;)V", "", "x", "y", "isTouchPointMoved", "(II)Z", "onHandleMove", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle;II)V", "Landroid/graphics/Point;", "point", "findClosestCharFromPoint", "(Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "updateTextSelection", "isHandleCrossed", "moveWithHandle", "(II)V", "touchPoint", "getEffectiveTouchPoint", "(Landroid/graphics/Point;)Landroid/graphics/Point;", "moveWithLongPress", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper;", "handleStart", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle;", "handleEnd", "touchedPoint", "Landroid/graphics/Point;", "isLongPress", "Z", "setLongPress", "(Z)V", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HandleController {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private final Handle handleEnd;
    private final Handle handleStart;
    private boolean isLongPress;
    private final TextSelectionHelper textSelectionHelper;
    private Point touchedPoint = new Point(-1, -1);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/HandleController$Companion;", "", "<init>", "()V", "TAG", "", "MOVING_THRESHOLD_PX", "", "BOUNDING_RECT_MARGIN_DP", "SELECTION_HANDLE_MARGIN_DP", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public HandleController(Context context2, TextSelectionHelper textSelectionHelper2) {
        j.e(context2, "context");
        j.e(textSelectionHelper2, "textSelectionHelper");
        this.context = context2;
        this.textSelectionHelper = textSelectionHelper2;
        this.handleStart = new Handle(context2, Handle.HandleType.START);
        this.handleEnd = new Handle(context2, Handle.HandleType.END);
    }

    private final SelectableCharacter findClosestCharFromPoint(Point point) {
        SelectableCharacter findTouchedCharacter = this.textSelectionHelper.findTouchedCharacter(point);
        if (findTouchedCharacter == null) {
            return this.textSelectionHelper.findNearestCharacter(point);
        }
        return findTouchedCharacter;
    }

    private final Point getEffectiveTouchPoint(Point point) {
        if (this.handleStart.isMoving()) {
            return this.handleStart.getEffectiveTouchPoint(point);
        }
        if (this.handleEnd.isMoving()) {
            return this.handleEnd.getEffectiveTouchPoint(point);
        }
        return new Point(-1, -1);
    }

    private final boolean isHandleCrossed() {
        if (this.handleStart.getSelectedChar().getIndex() > this.handleEnd.getSelectedChar().getIndex()) {
            return true;
        }
        return false;
    }

    private final boolean isHandleNotEmpty() {
        if (!this.handleStart.isNotEmpty() || !this.handleEnd.isNotEmpty()) {
            return false;
        }
        return true;
    }

    private final boolean isTouchPointMoved(int i2, int i7) {
        if (Math.abs(i2 - this.touchedPoint.x) > 1 || Math.abs(i7 - this.touchedPoint.y) > 1) {
            return true;
        }
        return false;
    }

    private final void moveWithHandle(int i2, int i7) {
        updateTextSelection();
        if (this.handleStart.isMoving()) {
            onHandleMove(this.handleStart, i2, i7);
        } else if (this.handleEnd.isMoving()) {
            onHandleMove(this.handleEnd, i2, i7);
        }
    }

    private final void moveWithLongPress(int i2, int i7) {
        SelectableCharacter findClosestCharFromPoint;
        SelectableWord selectedWord = this.textSelectionHelper.getSelectedWord();
        List<SelectableCharacter> selectableCharacters = this.textSelectionHelper.getSelectableOcrResult().getSelectableCharacters();
        if (selectedWord != null && (findClosestCharFromPoint = findClosestCharFromPoint(new Point(i2, i7))) != null) {
            int index = findClosestCharFromPoint.getIndex();
            int index2 = ((SelectableCharacter) C1194l.L0(selectedWord.getSelectableCharacters())).getIndex();
            if (index <= index2) {
                index2 = index;
            }
            int index3 = ((SelectableCharacter) C1194l.T0(selectedWord.getSelectableCharacters())).getIndex();
            if (index < index3) {
                index = index3;
            }
            this.textSelectionHelper.clearAllSelection();
            this.textSelectionHelper.selectCharacters(selectableCharacters.get(index2), selectableCharacters.get(index));
            this.textSelectionHelper.setLastSelectedChar(findClosestCharFromPoint);
        }
    }

    private final void onHandleMove(Handle handle, int i2, int i7) {
        SelectableCharacter findClosestCharFromPoint = findClosestCharFromPoint(handle.getEffectiveTouchPoint(new Point(i2, i7 - DrawUtil.INSTANCE.convertDpToPx(this.context, Float.valueOf(10.0f)))));
        if (findClosestCharFromPoint != null) {
            updateHandle(handle, findClosestCharFromPoint, (SelectableCharacter) null);
            handle.updateMovingState(Handle.MovingState.MOVING);
            this.textSelectionHelper.setLastSelectedChar(findClosestCharFromPoint);
        }
    }

    private final void setHandleMovingStateToIdle() {
        Handle handle = this.handleStart;
        Handle.MovingState movingState = Handle.MovingState.IDLE;
        handle.updateMovingState(movingState);
        this.handleEnd.updateMovingState(movingState);
    }

    private final void updateHandle(Handle handle, SelectableCharacter selectableCharacter, SelectableCharacter selectableCharacter2) {
        SelectableWord wordContainingCharacter = this.textSelectionHelper.getSelectableOcrResult().getWordContainingCharacter(selectableCharacter);
        DrawUtil drawUtil = DrawUtil.INSTANCE;
        handle.updateHandle(new HandleDrawInfo(selectableCharacter, drawUtil.isLeftToRightText(wordContainingCharacter.getPoly()), this.touchedPoint, VectorUtil.INSTANCE.calcRectWithMargin(this.textSelectionHelper.getBlockBoundingRect(), drawUtil.convertDpToPx(this.context, Float.valueOf(20.0f))), selectableCharacter2));
    }

    public static /* synthetic */ void updateHandle$default(HandleController handleController, Handle handle, SelectableCharacter selectableCharacter, SelectableCharacter selectableCharacter2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            selectableCharacter2 = null;
        }
        handleController.updateHandle(handle, selectableCharacter, selectableCharacter2);
    }

    private final void updateHandlePosition() {
        if (this.textSelectionHelper.isTextSelected()) {
            updateHandle$default(this, this.handleStart, this.textSelectionHelper.findStartCharFromSelection(), (SelectableCharacter) null, 2, (Object) null);
            updateHandle$default(this, this.handleEnd, this.textSelectionHelper.findEndCharFromSelection(), (SelectableCharacter) null, 2, (Object) null);
        }
    }

    private final void updateHandlePositionWithAnimation() {
        if (this.textSelectionHelper.isTextSelected()) {
            SelectableCharacter findStartCharFromSelection = this.textSelectionHelper.findStartCharFromSelection();
            SelectableCharacter findEndCharFromSelection = this.textSelectionHelper.findEndCharFromSelection();
            List<SelectableCharacter> selectableCharacters = this.textSelectionHelper.getSelectableOcrResult().getSelectableCharacters();
            if (!isHandleCrossed()) {
                updateHandle(this.handleStart, findStartCharFromSelection, findStartCharFromSelection);
                updateHandle(this.handleEnd, findEndCharFromSelection, findEndCharFromSelection);
                return;
            }
            int index = findEndCharFromSelection.getIndex() + 1;
            int size = selectableCharacters.size() - 1;
            if (index > size) {
                index = size;
            }
            int index2 = findStartCharFromSelection.getIndex() - 1;
            if (index2 < 0) {
                index2 = 0;
            }
            updateHandle(this.handleStart, findStartCharFromSelection, selectableCharacters.get(index));
            updateHandle(this.handleEnd, findEndCharFromSelection, selectableCharacters.get(index2));
        }
    }

    private final void updateTextSelection() {
        List<SelectableCharacter> selectableCharacters = this.textSelectionHelper.getSelectableOcrResult().getSelectableCharacters();
        SelectableCharacter selectedChar = this.handleStart.getSelectedChar();
        SelectableCharacter selectedChar2 = this.handleEnd.getSelectedChar();
        this.textSelectionHelper.clearAllSelection();
        if (!isHandleCrossed()) {
            this.textSelectionHelper.selectCharacters(selectableCharacters.get(selectedChar.getIndex()), selectableCharacters.get(selectedChar2.getIndex()));
        } else if (this.handleStart.isMoving()) {
            this.textSelectionHelper.selectCharacters(selectableCharacters.get(selectedChar2.getIndex() + 1), selectableCharacters.get(selectedChar.getIndex()));
        } else {
            this.textSelectionHelper.selectCharacters(selectableCharacters.get(selectedChar2.getIndex()), selectableCharacters.get(selectedChar.getIndex() - 1));
        }
    }

    public final void drawHandles(Canvas canvas) {
        j.e(canvas, "canvas");
        if (isHandleNotEmpty() && !this.isLongPress) {
            this.handleStart.draw(canvas);
            this.handleEnd.draw(canvas);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0021, code lost:
        if (r6 != 3) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.j.e(r6, r0)
            float r0 = r6.getX()
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
            int r0 = (int) r0
            float r2 = r6.getY()
            float r2 = r2 + r1
            int r1 = (int) r2
            int r6 = r6.getActionMasked()
            r2 = 0
            r3 = 1
            if (r6 == 0) goto L_0x008c
            if (r6 == r3) goto L_0x0063
            r4 = 2
            if (r6 == r4) goto L_0x0024
            r0 = 3
            if (r6 == r0) goto L_0x0063
            goto L_0x0076
        L_0x0024:
            boolean r6 = r5.isTouchPointMoved(r0, r1)
            if (r6 != 0) goto L_0x0037
            boolean r6 = r5.isHandleMoving()
            if (r6 != 0) goto L_0x0036
            boolean r5 = r5.isLongPress
            if (r5 == 0) goto L_0x0035
            goto L_0x0036
        L_0x0035:
            return r2
        L_0x0036:
            return r3
        L_0x0037:
            android.graphics.Point r6 = new android.graphics.Point
            r6.<init>(r0, r1)
            android.graphics.Point r6 = r5.getEffectiveTouchPoint(r6)
            r5.touchedPoint = r6
            boolean r6 = r5.isHandleMoving()
            if (r6 != 0) goto L_0x004c
            boolean r6 = r5.isLongPress
            if (r6 == 0) goto L_0x0076
        L_0x004c:
            boolean r6 = r5.isHandleMoving()
            if (r6 == 0) goto L_0x0056
            r5.moveWithHandle(r0, r1)
            goto L_0x005d
        L_0x0056:
            boolean r6 = r5.isLongPress
            if (r6 == 0) goto L_0x005d
            r5.moveWithLongPress(r0, r1)
        L_0x005d:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r5 = r5.textSelectionHelper
            r5.updateSelectedTextData()
            return r3
        L_0x0063:
            android.graphics.Point r6 = new android.graphics.Point
            r0 = -1
            r6.<init>(r0, r0)
            r5.touchedPoint = r6
            boolean r6 = r5.isHandleMoving()
            if (r6 != 0) goto L_0x0077
            boolean r6 = r5.isLongPress
            if (r6 == 0) goto L_0x0076
            goto L_0x0077
        L_0x0076:
            return r2
        L_0x0077:
            boolean r6 = r5.isHandleMoving()
            if (r6 == 0) goto L_0x0084
            r5.setHandleMovingStateToIdle()
            r5.updateHandlePositionWithAnimation()
            goto L_0x008b
        L_0x0084:
            boolean r6 = r5.isLongPress
            if (r6 == 0) goto L_0x008b
            r5.updateHandlePosition()
        L_0x008b:
            return r3
        L_0x008c:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r6 = r5.textSelectionHelper
            boolean r6 = r6.isTextSelected()
            if (r6 != 0) goto L_0x0095
            return r2
        L_0x0095:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle r6 = r5.handleStart
            boolean r6 = r6.contains(r0, r1)
            if (r6 == 0) goto L_0x00aa
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle r6 = r5.handleStart
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle$MovingState r0 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle.MovingState.MOVING
            r6.updateMovingState(r0)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r5 = r5.textSelectionHelper
            r5.updateLastSelectedChar(r3)
            return r3
        L_0x00aa:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle r6 = r5.handleEnd
            boolean r6 = r6.contains(r0, r1)
            if (r6 == 0) goto L_0x00bf
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle r6 = r5.handleEnd
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle$MovingState r0 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle.MovingState.MOVING
            r6.updateMovingState(r0)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper r5 = r5.textSelectionHelper
            r5.updateLastSelectedChar(r2)
            return r3
        L_0x00bf:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.HandleController.handleTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean isHandleMoving() {
        if (this.handleStart.isMoving() || this.handleEnd.isMoving()) {
            return true;
        }
        return false;
    }

    public final boolean isLongPress() {
        return this.isLongPress;
    }

    public final void setEmpty() {
        this.handleStart.setEmpty();
        this.handleEnd.setEmpty();
    }

    public final void setImageInfo(View view) {
        j.e(view, "teView");
        this.handleStart.setTeView(view);
        this.handleEnd.setTeView(view);
    }

    public final void setLongPress(boolean z) {
        this.isLongPress = z;
    }

    public final void setScaleFactor(float f) {
        float f5 = ((float) 1) / ((float) (((double) f) + 1.0E-5d));
        this.handleStart.setScaleFactor(f5);
        this.handleEnd.setScaleFactor(f5);
        updateHandlePosition();
    }

    public final void updatePosition() {
        updateHandlePosition();
    }
}
