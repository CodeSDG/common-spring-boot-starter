package com.codesdg.common.test.contoller;

import com.codesdg.common.test.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.codesdg.common.whitelist.annotation.DoWhiteList;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午11:34
 * @description
 * @Copyright
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     *       - admin
     *       - lisi
     *       - zhangsan
     *       - wangwu
     * 通过：http://localhost:8090/api/queryUserInfo?userId=admin&name=test
     * 拦截：http://localhost:8090/api/queryUserInfo?userId=123&name=test
     */
    @DoWhiteList(key = "userId", returnValue = "{\"code\":\"1111\",\"info\":\"非白名单用户，用户拦截！\"}")
    @GetMapping(path = "/api/queryUserInfo")
    public UserInfo queryUserInfo(@RequestParam String userId,@RequestParam String name) {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("虫虫:" + userId, 19, "杭州西湖风景名胜区三潭映月");
    }


}
