package com.an.sfs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
    /**
     * @param filename
     *            /002039/20140307-002039.txt
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static BufferedReader getBufferedReader(String filename) throws FileNotFoundException,
            UnsupportedEncodingException {
        URL url = FileUtil.class.getResource(filename);
        String path = url.getFile();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader read = new InputStreamReader(fis, "GBK");
        BufferedReader br = new BufferedReader(read);
        return br;
    }

    /**
     * @param code
     * @return
     */
    public static String getSummaryFileName(String code) {
        String result = "summary_" + code + ".txt";
        return result;
    }

    /**
     * @param dir
     * @return
     */
    public static List<String> getFiles(String dir) {
        List<String> result = new ArrayList<String>();

        URL url = FileUtil.class.getResource(dir);
        String path = url.getFile();
        File file = new File(path);

        File[] files = file.listFiles();
        for (File f : files) {
            if (f.getName().endsWith(".txt")) {
                result.add(f.getName());
            }
        }
        return result;
    }

    /**
     * @param files
     * @param filename
     * @throws IOException
     */
    public static void writeTo(List<String> files, String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        Iterator<String> it = files.iterator();
        while (it.hasNext()) {
            bw.write(it.next());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    /**
     * @param files
     * @param filename
     * @throws IOException
     */
    public static void writeTo(List<String> files, File filename) throws IOException {
        filename.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        Iterator<String> it = files.iterator();
        while (it.hasNext()) {
            bw.write(it.next());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) {
        List<String> files = getFiles("/002039");
        try {
            writeTo(files, new File("javafilenamelist.txt"));
        } catch (IOException i) {
            throw new RuntimeException("write fails");
        }
    }
}
