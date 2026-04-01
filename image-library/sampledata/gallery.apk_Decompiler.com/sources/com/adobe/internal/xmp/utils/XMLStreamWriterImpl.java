package com.adobe.internal.xmp.utils;

import com.adobe.internal.xmp.impl.Utils;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMLStreamWriterImpl {
    private static final String DEFAULTNS = "";
    private int baseIndent;
    private boolean charIndent;
    private boolean emptyElement;
    private boolean escapeWhitespaces;
    private int indentLevel;
    private char[] indentStr;
    private boolean namespaceLF;
    private char[] newLineStr;
    private boolean preventNextLF;
    private boolean preventWhitespace;
    private Stack qNameStack;
    private final HashSet registeredPrefixes;
    private boolean startElementOpened;
    private Writer writer;

    public XMLStreamWriterImpl(Writer writer2, SerializeOptions serializeOptions) {
        this(writer2);
        this.newLineStr = serializeOptions.getNewline().toCharArray();
        this.indentStr = serializeOptions.getIndent().toCharArray();
        this.baseIndent = serializeOptions.getBaseIndent();
    }

    private void closeStartElement() {
        if (this.startElementOpened) {
            if (!this.emptyElement) {
                write(">");
            } else {
                this.qNameStack.pop();
                write("/>");
                this.emptyElement = false;
                this.indentLevel--;
            }
            this.startElementOpened = false;
            this.indentLevel++;
        }
    }

    private boolean needToWriteNamespace(String str) {
        if (this.registeredPrefixes.contains(str)) {
            return false;
        }
        this.registeredPrefixes.add(str);
        return true;
    }

    private void write(String str) {
        this.writer.write(str);
    }

    private void writeCharactersInternal(char[] cArr, int i2, int i7, boolean z) {
        this.indentLevel++;
        this.writer.write(Utils.escapeXML(new String(cArr, i2, i7), z, this.escapeWhitespaces));
        this.indentLevel--;
    }

    private void writeCloseElement() {
        this.indentLevel--;
        writeNewLine();
        write("</");
        write((String) this.qNameStack.pop());
        write(">");
        this.preventWhitespace = false;
    }

    private void writeNewLine() {
        if (!this.preventNextLF && !this.preventWhitespace) {
            write(this.newLineStr);
        }
        if (!this.preventWhitespace) {
            for (int i2 = this.baseIndent + this.indentLevel; i2 > 0; i2--) {
                this.writer.write(this.indentStr);
            }
        }
        this.preventNextLF = false;
    }

    public void close() {
        flush();
        this.writer.close();
    }

    public void flush() {
        this.writer.flush();
    }

    public void setEscapeWhitespaces(boolean z) {
        this.escapeWhitespaces = z;
    }

    public void writeAttribute(String str, String str2) {
        if (this.startElementOpened) {
            write(" ");
            write(str);
            write("=\"");
            writeCharactersInternal(str2.toCharArray(), 0, str2.length(), true);
            write("\"");
            return;
        }
        throw new IOException("A start element must be written before an attribute");
    }

    public void writeCData(String str) {
        closeStartElement();
        write("<![CDATA[");
        if (str != null) {
            write(str);
        }
        write("]]>");
    }

    public void writeCharacters(String str) {
        writeCharacters(str.toCharArray(), 0, str.length());
    }

    public void writeComment(String str) {
        closeStartElement();
        write("<!--");
        if (str != null) {
            write(str);
        }
        write("-->");
    }

    public void writeDTD(String str) {
        writeNewLine();
        write(str);
    }

    public void writeDefaultNamespace(String str) {
        if (!this.startElementOpened) {
            throw new IOException("A start element must be written before the default namespace");
        } else if (needToWriteNamespace("")) {
            write(" xmlns");
            write("=\"");
            write(str);
            write("\"");
        }
    }

    public void writeEmptyElement(String str) {
        writeStartElement(str);
        this.emptyElement = true;
    }

    public void writeEndDocument() {
        while (!this.qNameStack.isEmpty()) {
            writeEndElement();
        }
    }

    public void writeEndElement() {
        if (this.startElementOpened) {
            this.qNameStack.pop();
            write("/>");
            this.startElementOpened = false;
            if (this.emptyElement) {
                writeCloseElement();
                this.emptyElement = false;
                return;
            }
            return;
        }
        writeCloseElement();
    }

    public void writeEntityRef(String str) {
        closeStartElement();
        write("&");
        write(str);
        write(";");
    }

    public void writeNamespace(String str, String str2) {
        if (!this.startElementOpened) {
            throw new IOException("A start element must be written before a namespace");
        } else if (str == null || "".equals(str) || "xmlns".equals(str)) {
            writeDefaultNamespace(str2);
        } else if (needToWriteNamespace(str)) {
            if (this.namespaceLF) {
                this.indentLevel++;
                writeNewLine();
                this.indentLevel--;
            } else {
                write(' ');
            }
            write("xmlns:");
            write(str);
            write("=\"");
            write(str2);
            write("\"");
        }
    }

    public void writeProcessingInstruction(String str) {
        closeStartElement();
        writeProcessingInstruction(str, (String) null);
    }

    public void writeStartDocument() {
        writeNewLine();
        write("<?xml version='1.0' encoding='utf-8'?>");
    }

    public void writeStartElement(String str) {
        if (str != null) {
            closeStartElement();
            writeNewLine();
            write("<");
            write(str);
            this.startElementOpened = true;
            this.qNameStack.push(str);
            return;
        }
        throw new IllegalArgumentException("The element name may not be null");
    }

    private void write(char c5) {
        this.writer.write(c5);
    }

    public void writeCharacters(char[] cArr, int i2, int i7) {
        boolean z = this.startElementOpened;
        closeStartElement();
        if (z) {
            if (this.charIndent) {
                writeNewLine();
            } else {
                this.preventWhitespace = true;
            }
        }
        writeCharactersInternal(cArr, i2, i7, false);
    }

    private void write(char[] cArr) {
        this.writer.write(cArr);
    }

    public void writeProcessingInstruction(String str, String str2) {
        closeStartElement();
        writeNewLine();
        write("<?");
        if (str != null) {
            write(str);
        }
        if (str2 != null) {
            write(' ');
            write(str2);
        }
        write("?>");
    }

    public void writeStartDocument(String str) {
        writeNewLine();
        write("<?xml version='");
        write(str);
        write("'?>");
    }

    public XMLStreamWriterImpl(Writer writer2) {
        this.startElementOpened = false;
        this.emptyElement = false;
        this.qNameStack = new Stack();
        this.newLineStr = new char[]{13};
        this.baseIndent = 0;
        this.indentStr = new char[]{' ', ' '};
        this.indentLevel = 0;
        this.charIndent = false;
        this.namespaceLF = true;
        this.preventWhitespace = false;
        this.preventNextLF = true;
        this.registeredPrefixes = new HashSet();
        this.escapeWhitespaces = true;
        this.writer = writer2;
    }

    public void writeStartDocument(String str, String str2) {
        writeNewLine();
        write("<?xml version='");
        write(str2);
        write("' encoding='");
        write(str);
        write("'?>");
    }
}
