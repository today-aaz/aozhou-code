package com.aozhou.code.common.uuid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Aozhou
 * @Date: 2024/12/2
 */
@Component
public class SnowflakeIdProperties {

    @Value("${snowflake.datacenter-id:1}") // 默认值为1，可在配置文件中覆盖
    private long datacenterId;

    @Value("${snowflake.worker-id:1}") // 默认值为1，可在配置文件中覆盖
    private long workerId;

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getWorkerId() {
        return workerId;
    }
}
