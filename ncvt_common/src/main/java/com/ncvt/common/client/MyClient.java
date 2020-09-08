package com.ncvt.common.client;

import com.dtflys.forest.annotation.Request;

/**
 * @author xueneng on 2020/8/25.
 *         Description:
 */
public interface MyClient {
    @Request(
        url = "${0}",
        type = "GET"
    )
    String simpleRequest(String url);
}
