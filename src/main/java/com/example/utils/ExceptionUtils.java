package com.example.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * added by jason.hou
 * 2018.01.17
 * 用于将异常的调用堆栈转化成字符串
 */

public class ExceptionUtils {
    public static String getStackTrace(Throwable anexcepObj) {
        StringWriter sw = null;
        PrintWriter printWriter = null;
        try {
            if (anexcepObj != null) {
                sw = new StringWriter();
                printWriter = new PrintWriter(sw);
                anexcepObj.printStackTrace(printWriter);
                printWriter.flush();
                sw.flush();
                return sw.toString();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        } finally {

            try {
                if (sw != null) {
                    sw.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
