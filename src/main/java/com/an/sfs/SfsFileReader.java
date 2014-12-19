package com.an.sfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SfsFileReader {
    private static final Log logger = LogFactory.getLog(SfsFileReader.class);
    private static final String DEFAULT_CHARSET = "GBK";

    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    /**
     * @param filePath
     *            /log4j.properties or /002647/002647.txt
     */
    public SfsFileReader(String filePath) {
        try {
            init(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void init(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
        logger.info("filePath: " + filePath);
        URL url = this.getClass().getResource(filePath);
        String path = url.getFile();
        fileInputStream = new FileInputStream(path);
        inputStreamReader = new InputStreamReader(fileInputStream, DEFAULT_CHARSET);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public String readLine() {
        if (bufferedReader != null) {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void close() {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
