package com.ncvt.website.web.controller;

import com.ncvt.common.client.MyClient;
import com.ncvt.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xueneng on 2020/8/27.
 *         Description:
 */
@RestController
public class MoocController {
    @Value("${constant.icourse163Url}")
    private String icourse163Url;
    /*@Value("${constant.mooc_Appid}")
    private String appId;
    @Value("${constant.mooc_Aeskey}")
    private String aesKey;
    @Value("${constant.mooc_AppSecret}")
    private String appSecret;*/

    @Autowired
    private MyClient myClient;

    private String appId = "1fe22d51cfc678903b998c16daba2d5c";
    private String aesKey = "7b2c00c4a22a883c3e7ddb47a6359197";
    private String appSecret = "fe3760cbe6c414f87f9a84503313a5ec";

    @GetMapping("simpleRequest")
    public String simpleRequest(){
        Map<Object,Object> queryMap = new LinkedHashMap<>();
        String param = getQueryMap(queryMap);
        String url = icourse163Url+"/open/courses/mooc?"+param;
        return myClient.simpleRequest(url);
    }

    private String getQueryMap(Map<Object, Object> queryMap) {
        long timestamp = System.currentTimeMillis() - 1000;
        String nonce = String.valueOf(Math.round(Math.random() * 1000000));
        String format = "json";
        String signature = EncryptionUtils.sha1Encrypt(appSecret + nonce + timestamp);
        queryMap.put("appId",appId);
        queryMap.put("timestamp",timestamp);
        queryMap.put("nonce",nonce);
        queryMap.put("format",format);
        queryMap.put("signature",signature);
        queryMap.put("pageSize",100);
        queryMap.put("pageIndex",1);
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Object, Object> entry : queryMap.entrySet()){
             if(sb.length() > 0){
                 sb.append("&");
             }
             sb.append(String.format("%s=%s",entry.getKey().toString(),entry.getValue().toString()));
        }
        return sb.toString();
    }

}
