package com.goldwarehouse.server.io;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class WebPageParser {
    private final String HK_STOCK_CONNECTED_URL = "http://www.sse.com.cn/services/hkexsc/disclo/eligible/";

    private final String HK_EXCHANGE_SEC = "https://www.hkex.com.hk/chi/market/sec_tradinfo/secstpduty_c.htm";

    private OkHttpClient httpClient = new OkHttpClient();

    /**
     * Read web page with traditional java io
     */
    public void readHKStockConnectedPage() {
        BufferedReader bufferedReader = null;
        PrintWriter writer = null;
        try {
            URLConnection urlConn = new URL(HK_STOCK_CONNECTED_URL).openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            writer = new PrintWriter(new FileWriter("HK.stock"));

            boolean begin = false;
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                lineData = lineData.trim();
                if (lineData.equals("list:[")) {
                    begin = true;
                } else if (begin && lineData.equals("]")) {
                    break;
                }

                if (begin && lineData.equals("'',")) {
                    bufferedReader.readLine();
                    lineData = bufferedReader.readLine();
                    if (StringUtils.hasText(lineData)) {
                        writer.println(lineData.trim().replaceAll("\"(\\d+)\".*", "$1"));
                    }
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
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

    /**
     * Read Web page with OkHttp
     */
    public void readHKExchangeTradeInfo() {
        Response response = null;
        BufferedReader bufferedReader = null;
        try {
            Request request = new Request.Builder().url(HK_EXCHANGE_SEC).get().build();
            response = httpClient.newCall(request).execute();
            bufferedReader = new BufferedReader(response.body().charStream());
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                lineData = lineData.trim();
                if (lineData.startsWith("<p style=\"text-align:center\" align=center><span lang=EN-US>")) {
                    lineData = lineData.replaceAll("<[^>]+>", "").trim();
                    if (lineData.matches("\\d{5}")) {
                        System.out.println(lineData);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                response.close();
            }
        }
    }

    public static void main(String[] args) {
        WebPageParser parser = new WebPageParser();
        parser.readHKExchangeTradeInfo();
    }
}
