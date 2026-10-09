/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

/**
 *
 * @author rimas
 */

import java.io.IOException;
import java.io.RandomAccessFile;

/** Utilities for fixed-length UTF-16 character fields in RandomAccessFile. */
public final class FixedRecordIO {
    private FixedRecordIO() {}

    public static void writeFixedString(RandomAccessFile file, String value, int length)
            throws IOException {
        String text = value == null ? "" : value;
        if (text.length() > length) {
            throw new IllegalArgumentException(
                    "Text exceeds maximum field length of " + length + " characters.");
        }
        for (int i = 0; i < length; i++) {
            file.writeChar(i < text.length() ? text.charAt(i) : '\0');
        }
    }

    public static String readFixedString(RandomAccessFile file, int length)
            throws IOException {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char ch = file.readChar();
            if (ch != '\0') result.append(ch);
        }
        return result.toString().trim();
    }
}
