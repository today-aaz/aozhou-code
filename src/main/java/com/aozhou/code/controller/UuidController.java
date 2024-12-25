package com.aozhou.code.controller;



import com.aozhou.code.common.uuid.SnowflakeIdGenerator;
import cn.hutool.core.lang.generator.UUIDGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Aozhou
 * @Date: 2024/12/3
 */
@RestController
public class UuidController {
    @Resource
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @GetMapping("/getUid")
    public String uuid() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        return uuidGenerator.next();
    }
    @GetMapping("/getSnowflakeId")
    public Long getSnowflakeId() {
        return snowflakeIdGenerator.generateId();
    }




}
