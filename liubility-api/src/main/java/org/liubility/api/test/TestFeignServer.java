package org.liubility.api.test;

import org.liubility.commons.http.response.normal.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Jdragon
 * @Class: TestFeignServer
 * @Date: 2021.02.07 上午 9:20
 * @Description:
 */

@FeignClient(value = "test-service",path = "test")
public interface TestFeignServer {
    /**
     * test
     */
    @GetMapping(value = "/test")
    public Result<String> test();
}
