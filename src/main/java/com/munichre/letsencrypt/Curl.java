package com.munichre.letsencrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

//import com.google.common.collect.*;

class Curl {
    public static Map<String, Object> curl(String url, String method, String body, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpRequestBase request = null;
        try {
            switch (method) {
            case "HEAD":
                request = new HttpHead(url);
                break;
            case "GET":
                request = new HttpGet(url);
                break;
            case "POST":
                StringEntity requestEntity = new StringEntity(body);
                HttpPost post = new HttpPost(url);
                post.setEntity(requestEntity);
                request = post;
                break;
            }

            if (headers != null) {
                setHeaders(request, headers);
            }
            CloseableHttpResponse response = httpClient.execute(request);
            StatusLine statusLine = response.getStatusLine();
            Map<String, String> cooked = cook(response.getAllHeaders());
            Map<String, Object> ret = new Map<>();
            ret.put("headers", cooked);
            ret.put("reason", statusLine.getReasonPhrase());
            ret.put("status-code", statusLine.getStatusCode());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ret.put("body", EntityUtils.toString(response.getEntity()));
            }
            return ret;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void setHeaders(HttpRequestBase request, Map<String, String> headers) {
        headers.forEach((k, v) -> request.addHeader(k, v));
    }

    private static Map<String, Object> cook(Header[] allHeaders) {
        return Arrays.stream(allHeaders).collect(Collectors.toMap(Header::getName, Header::getValue));
    }

    public static void main (String[] args) {
        Map c = curl("https://www.rte.ie/news/2020/0804/1157163-covid-19-phase-3-decision/","GET",null,null);
        System.out.println(c.keySet());
    }
}
