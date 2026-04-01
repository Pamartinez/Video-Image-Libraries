package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.impl.xpath.XMPPath;
import com.adobe.internal.xmp.impl.xpath.XMPPathParser;
import com.adobe.internal.xmp.options.IteratorOptions;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.properties.XMPPropertyInfo;
import i.C0212a;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPIteratorImpl implements XMPIterator {
    private String baseNS = null;
    private Iterator nodeIterator = null;
    private IteratorOptions options;
    protected boolean skipSiblings = false;
    protected boolean skipSubtree = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class NodeIteratorChildren extends NodeIterator {
        private Iterator childrenIterator;
        private int index = 0;
        private String parentPath;

        public NodeIteratorChildren(XMPNode xMPNode, String str) {
            super();
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            }
            this.parentPath = accumulatePath(xMPNode, str, 1);
            this.childrenIterator = xMPNode.iterateChildren();
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x005c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0061  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean hasNext() {
            /*
                r4 = this;
                com.adobe.internal.xmp.properties.XMPPropertyInfo r0 = r4.getReturnProperty()
                r1 = 1
                if (r0 == 0) goto L_0x0008
                return r1
            L_0x0008:
                com.adobe.internal.xmp.impl.XMPIteratorImpl r0 = com.adobe.internal.xmp.impl.XMPIteratorImpl.this
                boolean r0 = r0.skipSiblings
                r2 = 0
                if (r0 == 0) goto L_0x0010
                return r2
            L_0x0010:
                java.util.Iterator r0 = r4.childrenIterator
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto L_0x006f
                java.util.Iterator r0 = r4.childrenIterator
                java.lang.Object r0 = r0.next()
                com.adobe.internal.xmp.impl.XMPNode r0 = (com.adobe.internal.xmp.impl.XMPNode) r0
                int r2 = r4.index
                int r2 = r2 + r1
                r4.index = r2
                com.adobe.internal.xmp.options.PropertyOptions r2 = r0.getOptions()
                boolean r2 = r2.isSchemaNode()
                if (r2 == 0) goto L_0x0039
                com.adobe.internal.xmp.impl.XMPIteratorImpl r2 = com.adobe.internal.xmp.impl.XMPIteratorImpl.this
                java.lang.String r3 = r0.getName()
                r2.setBaseNS(r3)
                goto L_0x0048
            L_0x0039:
                com.adobe.internal.xmp.impl.XMPNode r2 = r0.getParent()
                if (r2 == 0) goto L_0x0048
                java.lang.String r2 = r4.parentPath
                int r3 = r4.index
                java.lang.String r2 = r4.accumulatePath(r0, r2, r3)
                goto L_0x0049
            L_0x0048:
                r2 = 0
            L_0x0049:
                com.adobe.internal.xmp.impl.XMPIteratorImpl r3 = com.adobe.internal.xmp.impl.XMPIteratorImpl.this
                com.adobe.internal.xmp.options.IteratorOptions r3 = r3.getOptions()
                boolean r3 = r3.isJustLeafnodes()
                if (r3 == 0) goto L_0x0061
                boolean r3 = r0.hasChildren()
                if (r3 != 0) goto L_0x005c
                goto L_0x0061
            L_0x005c:
                boolean r4 = r4.hasNext()
                return r4
            L_0x0061:
                com.adobe.internal.xmp.impl.XMPIteratorImpl r3 = com.adobe.internal.xmp.impl.XMPIteratorImpl.this
                java.lang.String r3 = r3.getBaseNS()
                com.adobe.internal.xmp.properties.XMPPropertyInfo r0 = r4.createPropertyInfo(r0, r3, r2)
                r4.setReturnProperty(r0)
                return r1
            L_0x006f:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPIteratorImpl.NodeIteratorChildren.hasNext():boolean");
        }
    }

    public XMPIteratorImpl(XMPMetaImpl xMPMetaImpl, String str, String str2, IteratorOptions iteratorOptions) {
        boolean z;
        boolean z3;
        XMPNode xMPNode;
        String str3 = null;
        this.options = iteratorOptions == null ? new IteratorOptions() : iteratorOptions;
        if (str == null || str.length() <= 0) {
            z = false;
        } else {
            z = true;
        }
        if (str2 == null || str2.length() <= 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!z && !z3) {
            xMPNode = xMPMetaImpl.getRoot();
        } else if (z && z3) {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPPath xMPPath = new XMPPath();
            for (int i2 = 0; i2 < expandXPath.size() - 1; i2++) {
                xMPPath.add(expandXPath.getSegment(i2));
            }
            xMPNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, false, (PropertyOptions) null);
            this.baseNS = str;
            str3 = xMPPath.toString();
        } else if (!z || z3) {
            throw new XMPException("Schema namespace URI is required", 101);
        } else {
            xMPNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), str, false);
        }
        if (xMPNode == null) {
            this.nodeIterator = Collections.EMPTY_LIST.iterator();
        } else if (!this.options.isJustChildren()) {
            this.nodeIterator = new NodeIterator(xMPNode, str3, 1);
        } else {
            this.nodeIterator = new NodeIteratorChildren(xMPNode, str3);
        }
    }

    public String getBaseNS() {
        return this.baseNS;
    }

    public IteratorOptions getOptions() {
        return this.options;
    }

    public boolean hasNext() {
        return this.nodeIterator.hasNext();
    }

    public Object next() {
        return this.nodeIterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }

    public void setBaseNS(String str) {
        this.baseNS = str;
    }

    public void skipSiblings() {
        skipSubtree();
        this.skipSiblings = true;
    }

    public void skipSubtree() {
        this.skipSubtree = true;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class NodeIterator implements Iterator {
        protected static final int ITERATE_CHILDREN = 1;
        protected static final int ITERATE_NODE = 0;
        protected static final int ITERATE_QUALIFIER = 2;
        private Iterator childrenIterator = null;
        private int index = 0;
        private String path;
        private XMPPropertyInfo returnProperty = null;
        private int state = 0;
        private Iterator subIterator = Collections.EMPTY_LIST.iterator();
        private XMPNode visitedNode;

        public NodeIterator() {
        }

        private boolean iterateChildren(Iterator it) {
            XMPIteratorImpl xMPIteratorImpl = XMPIteratorImpl.this;
            if (xMPIteratorImpl.skipSiblings) {
                xMPIteratorImpl.skipSiblings = false;
                this.subIterator = Collections.EMPTY_LIST.iterator();
            }
            if (!this.subIterator.hasNext() && it.hasNext()) {
                int i2 = this.index + 1;
                this.index = i2;
                this.subIterator = new NodeIterator((XMPNode) it.next(), this.path, i2);
            }
            if (!this.subIterator.hasNext()) {
                return false;
            }
            this.returnProperty = (XMPPropertyInfo) this.subIterator.next();
            return true;
        }

        public String accumulatePath(XMPNode xMPNode, String str, int i2) {
            String str2;
            String str3;
            if (xMPNode.getParent() == null || xMPNode.getOptions().isSchemaNode()) {
                return null;
            }
            if (xMPNode.getParent().getOptions().isArray()) {
                str3 = "[" + String.valueOf(i2) + "]";
                str2 = "";
            } else {
                str3 = xMPNode.getName();
                str2 = "/";
            }
            if (str == null || str.length() == 0) {
                return str3;
            }
            if (!XMPIteratorImpl.this.getOptions().isJustLeafname()) {
                return C0212a.B(str, str2, str3);
            }
            if (!str3.startsWith("?")) {
                return str3;
            }
            return str3.substring(1);
        }

        public XMPPropertyInfo createPropertyInfo(XMPNode xMPNode, String str, String str2) {
            String value;
            if (xMPNode.getOptions().isSchemaNode()) {
                value = null;
            } else {
                value = xMPNode.getValue();
            }
            final String str3 = value;
            final XMPNode xMPNode2 = xMPNode;
            final String str4 = str;
            final String str5 = str2;
            return new XMPPropertyInfo() {
                public String getLanguage() {
                    return null;
                }

                public String getNamespace() {
                    if (xMPNode2.getOptions().isSchemaNode()) {
                        return str4;
                    }
                    return XMPMetaFactory.getSchemaRegistry().getNamespaceURI(new QName(xMPNode2.getName()).getPrefix());
                }

                public PropertyOptions getOptions() {
                    return xMPNode2.getOptions();
                }

                public String getPath() {
                    return str5;
                }

                public String getValue() {
                    return str3;
                }
            };
        }

        public Iterator getChildrenIterator() {
            return this.childrenIterator;
        }

        public XMPPropertyInfo getReturnProperty() {
            return this.returnProperty;
        }

        public boolean hasNext() {
            if (this.returnProperty != null) {
                return true;
            }
            int i2 = this.state;
            if (i2 == 0) {
                return reportNode();
            }
            if (i2 == 1) {
                if (this.childrenIterator == null) {
                    this.childrenIterator = this.visitedNode.iterateChildren();
                }
                boolean iterateChildren = iterateChildren(this.childrenIterator);
                if (iterateChildren || !this.visitedNode.hasQualifier() || XMPIteratorImpl.this.getOptions().isOmitQualifiers()) {
                    return iterateChildren;
                }
                this.state = 2;
                this.childrenIterator = null;
                return hasNext();
            }
            if (this.childrenIterator == null) {
                this.childrenIterator = this.visitedNode.iterateQualifier();
            }
            return iterateChildren(this.childrenIterator);
        }

        public Object next() {
            if (hasNext()) {
                XMPPropertyInfo xMPPropertyInfo = this.returnProperty;
                this.returnProperty = null;
                return xMPPropertyInfo;
            }
            throw new NoSuchElementException("There are no more nodes to return");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean reportNode() {
            this.state = 1;
            if (this.visitedNode.getParent() == null || (XMPIteratorImpl.this.getOptions().isJustLeafnodes() && this.visitedNode.hasChildren())) {
                return hasNext();
            }
            this.returnProperty = createPropertyInfo(this.visitedNode, XMPIteratorImpl.this.getBaseNS(), this.path);
            return true;
        }

        public void setChildrenIterator(Iterator it) {
            this.childrenIterator = it;
        }

        public void setReturnProperty(XMPPropertyInfo xMPPropertyInfo) {
            this.returnProperty = xMPPropertyInfo;
        }

        public NodeIterator(XMPNode xMPNode, String str, int i2) {
            this.visitedNode = xMPNode;
            this.state = 0;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            }
            this.path = accumulatePath(xMPNode, str, i2);
        }
    }
}
