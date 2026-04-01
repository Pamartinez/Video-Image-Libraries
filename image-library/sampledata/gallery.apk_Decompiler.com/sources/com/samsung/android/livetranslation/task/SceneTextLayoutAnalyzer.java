package com.samsung.android.livetranslation.task;

import android.graphics.Point;
import com.samsung.android.livetranslation.geometry.LineDetector;
import com.samsung.android.livetranslation.geometry.ParagraphDetector;
import com.samsung.android.livetranslation.geometry.SmallestSurroundingRectangle;
import com.samsung.android.livetranslation.text.SceneText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SceneTextLayoutAnalyzer {
    private int mDeviceOrientation;
    private LineDetector mLineDetector = new LineDetector();
    private ParagraphDetector mParagraphDetector = new ParagraphDetector();

    private boolean isSameLevel(SceneText sceneText, SceneText sceneText2) {
        int i2;
        int i7;
        SceneText.SceneTextType type = sceneText2.getType();
        SceneText.SceneTextType sceneTextType = SceneText.SceneTextType.LINE;
        if (type == sceneTextType) {
            return this.mLineDetector.isSameLine(sceneText.getPoly(), sceneText2.getPoly(), sceneText.getValue().length(), sceneText2.getValue().length());
        }
        if (sceneText.getType() == sceneTextType) {
            i2 = 1;
        } else {
            i2 = sceneText.getComponentNum();
        }
        if (sceneText2.getType() == sceneTextType) {
            i7 = 1;
        } else {
            i7 = sceneText2.getComponentNum();
        }
        Pattern compile = Pattern.compile("^[*●(□]");
        Pattern compile2 = Pattern.compile("^[0-9]");
        if (compile.matcher(sceneText.getValue()).find() && sceneText.getValue().charAt(0) == sceneText2.getValue().charAt(0)) {
            return false;
        }
        if (compile2.matcher(sceneText.getValue()).find() && compile2.matcher(sceneText2.getValue()).find()) {
            if (Character.getNumericValue(sceneText2.getValue().charAt(0)) - Character.getNumericValue(sceneText.getValue().charAt(0)) == 1) {
                return false;
            }
        }
        return this.mParagraphDetector.isSameParagraph(sceneText.getPoly(), sceneText2.getPoly(), i2, i7);
    }

    private CopyOnWriteArrayList<SceneText> makeMultiParagraphs(Stack<SceneText> stack) {
        ArrayList arrayList = new ArrayList(stack);
        CopyOnWriteArrayList<SceneText> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            copyOnWriteArrayList.add((SceneText) it.next());
        }
        return copyOnWriteArrayList;
    }

    private List<SceneText> makeSingleParagraph(List<SceneText> list, Stack<SceneText> stack) {
        ArrayList arrayList = new ArrayList();
        SceneText sceneText = new SceneText();
        sceneText.setType(SceneText.SceneTextType.PARAGRAPH);
        sceneText.setPoly(list.get(0).getPoly());
        sceneText.setValue(list.get(0).getValue());
        Iterator it = new ArrayList(stack).iterator();
        while (it.hasNext()) {
            sceneText.setComponent((SceneText) it.next());
        }
        arrayList.add(sceneText);
        return arrayList;
    }

    private SceneText mergeComponent(SceneText sceneText, SceneText sceneText2) {
        if (sceneText2.getType() == SceneText.SceneTextType.LINE) {
            return mergeWord(sceneText, sceneText2);
        }
        return mergeLine(sceneText, sceneText2);
    }

    private Stack<SceneText> mergeComponents(List<SceneText> list, SceneText.SceneTextType sceneTextType) {
        Stack<SceneText> stack = new Stack<>();
        for (SceneText next : list) {
            SceneText sceneText = new SceneText();
            sceneText.setType(sceneTextType);
            sceneText.setPoly(next.getPoly());
            sceneText.setValue(next.getValue());
            sceneText.setComponent(next);
            sceneText.setDeviceOrientation(this.mDeviceOrientation);
            if (!stack.isEmpty()) {
                SceneText peek = stack.peek();
                if (isSameLevel(peek, sceneText)) {
                    stack.pop();
                    stack.push(mergeComponent(peek, sceneText));
                } else {
                    stack.push(sceneText);
                }
            } else {
                stack.push(sceneText);
            }
        }
        return stack;
    }

    private SceneText mergeLine(SceneText sceneText, SceneText sceneText2) {
        SceneText sceneText3 = new SceneText();
        SceneText.SceneTextType sceneTextType = SceneText.SceneTextType.PARAGRAPH;
        sceneText3.setType(sceneTextType);
        sceneText3.setValue(sceneText.getValue() + "\t" + sceneText2.getValue());
        sceneText3.setPoly(mergePoly(sceneText.getPoly(), sceneText2.getPoly()));
        sceneText3.setDeviceOrientation(this.mDeviceOrientation);
        if (sceneText.getType() == SceneText.SceneTextType.LINE) {
            sceneText3.setComponent(sceneText);
        } else if (sceneText.getType() == sceneTextType) {
            Iterator<SceneText> it = sceneText.getComponents().iterator();
            while (it.hasNext()) {
                sceneText3.setComponent(it.next());
            }
        }
        if (sceneText2.getType() == SceneText.SceneTextType.LINE) {
            sceneText3.setComponent(sceneText2);
            return sceneText3;
        }
        if (sceneText2.getType() == SceneText.SceneTextType.PARAGRAPH) {
            Iterator<SceneText> it2 = sceneText2.getComponents().iterator();
            while (it2.hasNext()) {
                sceneText3.setComponent(it2.next());
            }
        }
        return sceneText3;
    }

    private Point[] mergePoly(Point[] pointArr, Point[] pointArr2) {
        return SmallestSurroundingRectangle.computeCorners(pointArr, pointArr2);
    }

    private SceneText mergeWord(SceneText sceneText, SceneText sceneText2) {
        SceneText sceneText3 = new SceneText();
        SceneText.SceneTextType sceneTextType = SceneText.SceneTextType.LINE;
        sceneText3.setType(sceneTextType);
        sceneText3.setValue(sceneText.getValue() + " " + sceneText2.getValue());
        sceneText3.setPoly(mergePoly(sceneText.getPoly(), sceneText2.getPoly()));
        sceneText3.setDeviceOrientation(this.mDeviceOrientation);
        if (sceneText.getType() == SceneText.SceneTextType.WORD) {
            sceneText3.setComponent(sceneText);
        } else if (sceneText.getType() == sceneTextType) {
            Iterator<SceneText> it = sceneText.getComponents().iterator();
            while (it.hasNext()) {
                sceneText3.setComponent(it.next());
            }
        }
        if (sceneText2.getType() == SceneText.SceneTextType.WORD) {
            sceneText3.setComponent(sceneText2);
            return sceneText3;
        }
        if (sceneText2.getType() == SceneText.SceneTextType.LINE) {
            Iterator<SceneText> it2 = sceneText2.getComponents().iterator();
            while (it2.hasNext()) {
                sceneText3.setComponent(it2.next());
            }
        }
        return sceneText3;
    }

    private void sortSceneTextList(List<SceneText> list) {
        Collections.sort(list.get(0).getComponents(), new Comparator<SceneText>() {
            public int compare(SceneText sceneText, SceneText sceneText2) {
                return 1;
            }
        });
    }

    public CopyOnWriteArrayList<SceneText> analyze(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        this.mDeviceOrientation = copyOnWriteArrayList.get(0).getDeviceOrientation();
        return makeMultiParagraphs(mergeComponents(copyOnWriteArrayList.get(0).getComponents(), SceneText.SceneTextType.PARAGRAPH));
    }
}
