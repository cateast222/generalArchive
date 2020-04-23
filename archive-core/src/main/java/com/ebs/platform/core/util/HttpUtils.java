package com.ebs.platform.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Http请求工具类
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:23
 */
public class HttpUtils {
    /**
     * 发送Post请求
     * @param url
     * @param params
     * @param token
     * @return
     * @throws IOException
     */
    public static WebResult sendPost(String url, String params, String token) {
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        StringBuffer responseBuffer = new StringBuffer();
        try {
            URL httpUrl = null;
            httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            if (token != null) {
                conn.setRequestProperty("Authorization", "Bearer " + token);
            }
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(params==null?"":params);
            out.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                responseBuffer.append(lines);
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        @SuppressWarnings("rawtypes")
		WebResult result = JSON.parseObject(responseBuffer.toString(), new TypeReference<WebResult>() {});
        if(result.getCode() == 0){
            return result;
        }else{
            throw new RuntimeException(result.getMessage());
        }
    }

    public static WebResult sendGet(String path, Map<String,String> param, String token) {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            //GET请求直接在链接后面拼上请求参数
            String mPath = path;

            if(param != null && param.size()>0){
              //  mPath += "?";
                for(String key:param.keySet()){
               //     mPath += key + "=" + param.get(key) + "&";
                    mPath += key ;
                }
            }

            URL url = new URL(mPath);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            if (token != null) {
                conn.setRequestProperty("Authorization", "Bearer " + token);
            }
            //Get请求不需要DoOutPut
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //连接服务器
            conn.connect();
            // 取得输入流，并使用Reader读取
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭输入流
        finally{
            try{
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        WebResult res = JSON.parseObject(result.toString(), new TypeReference<WebResult>() {});
        if(res.getCode() == 0){
            return res;
        }else{
            throw new RuntimeException(res.getMessage());
        }
    }
}
