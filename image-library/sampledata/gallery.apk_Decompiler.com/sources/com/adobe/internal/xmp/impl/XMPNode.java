package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.options.PropertyOptions;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class XMPNode implements Comparable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean alias;
    private List children;
    private boolean hasAliases;
    private boolean hasValueChild;
    private boolean implicit;
    private String name;
    private PropertyOptions options;
    private XMPNode parent;
    private List qualifier;
    private String value;

    public XMPNode(String str, String str2, PropertyOptions propertyOptions) {
        this.children = null;
        this.qualifier = null;
        this.name = str;
        this.value = str2;
        this.options = propertyOptions;
    }

    private void assertChildNotExisting(String str) {
        if (!XMPConst.ARRAY_ITEM_NAME.equals(str) && findChildByName(str) != null) {
            throw new XMPException(C0212a.m("Duplicate property or field node '", str, "'"), 203);
        }
    }

    private void assertQualifierNotExisting(String str) {
        if (!XMPConst.ARRAY_ITEM_NAME.equals(str) && findQualifierByName(str) != null) {
            throw new XMPException(C0212a.m("Duplicate '", str, "' qualifier"), 203);
        }
    }

    private XMPNode find(List list, String str) {
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            XMPNode xMPNode = (XMPNode) it.next();
            if (xMPNode.getName().equals(str)) {
                return xMPNode;
            }
        }
        return null;
    }

    private List getChildren() {
        if (this.children == null) {
            this.children = new ArrayList(0);
        }
        return this.children;
    }

    private boolean isLanguageNode() {
        return XMPConst.XML_LANG.equals(this.name);
    }

    private boolean isTypeNode() {
        return XMPConst.RDF_TYPE.equals(this.name);
    }

    public void addChild(XMPNode xMPNode) {
        assertChildNotExisting(xMPNode.getName());
        xMPNode.setParent(this);
        getChildren().add(xMPNode);
    }

    public void addQualifier(XMPNode xMPNode) {
        assertQualifierNotExisting(xMPNode.getName());
        xMPNode.setParent(this);
        xMPNode.getOptions().setQualifier(true);
        getOptions().setHasQualifiers(true);
        if (xMPNode.isLanguageNode()) {
            this.options.setHasLanguage(true);
            getQualifier().add(0, xMPNode);
        } else if (xMPNode.isTypeNode()) {
            this.options.setHasType(true);
            getQualifier().add(this.options.getHasLanguage() ? 1 : 0, xMPNode);
        } else {
            getQualifier().add(xMPNode);
        }
    }

    public void cleanupChildren() {
        if (this.children.isEmpty()) {
            this.children = null;
        }
    }

    public void clear() {
        this.options = null;
        this.name = null;
        this.value = null;
        this.children = null;
        this.qualifier = null;
    }

    public Object clone() {
        return clone(false);
    }

    public void cloneSubtree(XMPNode xMPNode, boolean z) {
        try {
            Iterator iterateChildren = iterateChildren();
            while (iterateChildren.hasNext()) {
                XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
                if (!z || (!(xMPNode2.getValue() == null || xMPNode2.getValue().length() == 0) || xMPNode2.hasChildren())) {
                    XMPNode xMPNode3 = (XMPNode) xMPNode2.clone(z);
                    if (xMPNode3 != null) {
                        xMPNode.addChild(xMPNode3);
                    }
                }
            }
            Iterator iterateQualifier = iterateQualifier();
            while (iterateQualifier.hasNext()) {
                XMPNode xMPNode4 = (XMPNode) iterateQualifier.next();
                if (!z || (!(xMPNode4.getValue() == null || xMPNode4.getValue().length() == 0) || xMPNode4.hasChildren())) {
                    XMPNode xMPNode5 = (XMPNode) xMPNode4.clone(z);
                    if (xMPNode5 != null) {
                        xMPNode.addQualifier(xMPNode5);
                    }
                }
            }
        } catch (XMPException unused) {
        }
    }

    public int compareTo(Object obj) {
        if (getOptions().isSchemaNode()) {
            return this.value.compareTo(((XMPNode) obj).getValue());
        }
        return this.name.compareTo(((XMPNode) obj).getName());
    }

    public String dumpNode(boolean z) {
        StringBuffer stringBuffer = new StringBuffer(512);
        dumpNode(stringBuffer, z, 0, 0);
        return stringBuffer.toString();
    }

    public XMPNode findChildByName(String str) {
        return find(getChildren(), str);
    }

    public XMPNode findQualifierByName(String str) {
        return find(this.qualifier, str);
    }

    public XMPNode getChild(int i2) {
        return (XMPNode) getChildren().get(i2 - 1);
    }

    public int getChildrenLength() {
        List list = this.children;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public boolean getHasAliases() {
        return this.hasAliases;
    }

    public boolean getHasValueChild() {
        return this.hasValueChild;
    }

    public String getName() {
        return this.name;
    }

    public PropertyOptions getOptions() {
        if (this.options == null) {
            this.options = new PropertyOptions();
        }
        return this.options;
    }

    public XMPNode getParent() {
        return this.parent;
    }

    public XMPNode getQualifier(int i2) {
        return (XMPNode) getQualifier().get(i2 - 1);
    }

    public int getQualifierLength() {
        List list = this.qualifier;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List getUnmodifiableChildren() {
        return Collections.unmodifiableList(new ArrayList(getChildren()));
    }

    public String getValue() {
        return this.value;
    }

    public boolean hasChildren() {
        List list = this.children;
        if (list == null || list.size() <= 0) {
            return false;
        }
        return true;
    }

    public boolean hasQualifier() {
        List list = this.qualifier;
        if (list == null || list.size() <= 0) {
            return false;
        }
        return true;
    }

    public boolean isAlias() {
        return this.alias;
    }

    public boolean isImplicit() {
        return this.implicit;
    }

    public Iterator iterateChildren() {
        if (this.children != null) {
            return getChildren().iterator();
        }
        return Collections.EMPTY_LIST.listIterator();
    }

    public Iterator iterateQualifier() {
        if (this.qualifier == null) {
            return Collections.EMPTY_LIST.iterator();
        }
        final Iterator it = getQualifier().iterator();
        return new Iterator() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public Object next() {
                return it.next();
            }

            public void remove() {
                throw new UnsupportedOperationException("remove() is not allowed due to the internal contraints");
            }
        };
    }

    public void removeChild(int i2) {
        getChildren().remove(i2 - 1);
        cleanupChildren();
    }

    public void removeChildren() {
        this.children = null;
    }

    public void removeQualifier(XMPNode xMPNode) {
        PropertyOptions options2 = getOptions();
        if (xMPNode.isLanguageNode()) {
            options2.setHasLanguage(false);
        } else if (xMPNode.isTypeNode()) {
            options2.setHasType(false);
        }
        getQualifier().remove(xMPNode);
        if (this.qualifier.isEmpty()) {
            options2.setHasQualifiers(false);
            this.qualifier = null;
        }
    }

    public void removeQualifiers() {
        PropertyOptions options2 = getOptions();
        options2.setHasQualifiers(false);
        options2.setHasLanguage(false);
        options2.setHasType(false);
        this.qualifier = null;
    }

    public void replaceChild(int i2, XMPNode xMPNode) {
        xMPNode.setParent(this);
        getChildren().set(i2 - 1, xMPNode);
    }

    public void setAlias(boolean z) {
        this.alias = z;
    }

    public void setHasAliases(boolean z) {
        this.hasAliases = z;
    }

    public void setHasValueChild(boolean z) {
        this.hasValueChild = z;
    }

    public void setImplicit(boolean z) {
        this.implicit = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOptions(PropertyOptions propertyOptions) {
        this.options = propertyOptions;
    }

    public void setParent(XMPNode xMPNode) {
        this.parent = xMPNode;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void sort() {
        if (hasQualifier()) {
            XMPNode[] xMPNodeArr = (XMPNode[]) getQualifier().toArray(new XMPNode[getQualifierLength()]);
            int i2 = 0;
            while (xMPNodeArr.length > i2 && (XMPConst.XML_LANG.equals(xMPNodeArr[i2].getName()) || XMPConst.RDF_TYPE.equals(xMPNodeArr[i2].getName()))) {
                xMPNodeArr[i2].sort();
                i2++;
            }
            Arrays.sort(xMPNodeArr, i2, xMPNodeArr.length);
            ListIterator listIterator = this.qualifier.listIterator();
            for (int i7 = 0; i7 < xMPNodeArr.length; i7++) {
                listIterator.next();
                listIterator.set(xMPNodeArr[i7]);
                xMPNodeArr[i7].sort();
            }
        }
        if (hasChildren()) {
            if (!getOptions().isArray()) {
                Collections.sort(this.children);
            }
            Iterator iterateChildren = iterateChildren();
            while (iterateChildren.hasNext()) {
                ((XMPNode) iterateChildren.next()).sort();
            }
        }
    }

    private List getQualifier() {
        if (this.qualifier == null) {
            this.qualifier = new ArrayList(0);
        }
        return this.qualifier;
    }

    public Object clone(boolean z) {
        PropertyOptions propertyOptions;
        try {
            propertyOptions = new PropertyOptions(getOptions().getOptions());
        } catch (XMPException unused) {
            propertyOptions = new PropertyOptions();
        }
        XMPNode xMPNode = new XMPNode(this.name, this.value, propertyOptions);
        cloneSubtree(xMPNode, z);
        if (!z) {
            return xMPNode;
        }
        if ((xMPNode.getValue() == null || xMPNode.getValue().length() == 0) && !xMPNode.hasChildren()) {
            return null;
        }
        return xMPNode;
    }

    public void removeChild(XMPNode xMPNode) {
        getChildren().remove(xMPNode);
        cleanupChildren();
    }

    private void dumpNode(StringBuffer stringBuffer, boolean z, int i2, int i7) {
        int i8 = 0;
        for (int i10 = 0; i10 < i2; i10++) {
            stringBuffer.append(9);
        }
        if (this.parent == null) {
            stringBuffer.append("ROOT NODE");
            String str = this.name;
            if (str != null && str.length() > 0) {
                stringBuffer.append(" (");
                stringBuffer.append(this.name);
                stringBuffer.append(')');
            }
        } else if (getOptions().isQualifier()) {
            stringBuffer.append('?');
            stringBuffer.append(this.name);
        } else if (getParent().getOptions().isArray()) {
            stringBuffer.append('[');
            stringBuffer.append(i7);
            stringBuffer.append(']');
        } else {
            stringBuffer.append(this.name);
        }
        String str2 = this.value;
        if (str2 != null && str2.length() > 0) {
            stringBuffer.append(" = \"");
            stringBuffer.append(this.value);
            stringBuffer.append('\"');
        }
        if (getOptions().containsOneOf(-1)) {
            stringBuffer.append("\t(");
            stringBuffer.append(getOptions().toString());
            stringBuffer.append(" : ");
            stringBuffer.append(getOptions().getOptionsString());
            stringBuffer.append(')');
        }
        stringBuffer.append(10);
        if (z && hasQualifier()) {
            XMPNode[] xMPNodeArr = (XMPNode[]) getQualifier().toArray(new XMPNode[getQualifierLength()]);
            int i11 = 0;
            while (xMPNodeArr.length > i11 && (XMPConst.XML_LANG.equals(xMPNodeArr[i11].getName()) || XMPConst.RDF_TYPE.equals(xMPNodeArr[i11].getName()))) {
                i11++;
            }
            Arrays.sort(xMPNodeArr, i11, xMPNodeArr.length);
            int i12 = 0;
            while (i12 < xMPNodeArr.length) {
                i12++;
                xMPNodeArr[i12].dumpNode(stringBuffer, z, i2 + 2, i12);
            }
        }
        if (z && hasChildren()) {
            XMPNode[] xMPNodeArr2 = (XMPNode[]) getChildren().toArray(new XMPNode[getChildrenLength()]);
            if (!getOptions().isArray()) {
                Arrays.sort(xMPNodeArr2);
            }
            while (i8 < xMPNodeArr2.length) {
                i8++;
                xMPNodeArr2[i8].dumpNode(stringBuffer, z, i2 + 1, i8);
            }
        }
    }

    public void addChild(int i2, XMPNode xMPNode) {
        assertChildNotExisting(xMPNode.getName());
        xMPNode.setParent(this);
        getChildren().add(i2 - 1, xMPNode);
    }

    public XMPNode(String str, PropertyOptions propertyOptions) {
        this(str, (String) null, propertyOptions);
    }
}
