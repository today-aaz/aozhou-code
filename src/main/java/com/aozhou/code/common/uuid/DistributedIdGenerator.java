package com.aozhou.code.common.uuid;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 基于 Redis 的自增操作（INCR），确保在分布式环境中生成全局唯一且递增的ID。
 * @Author: Aozhou
 * @Date: 2024/12/3
 */
@Component
public class DistributedIdGenerator {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    // 全局前缀
    private final static String SHORT_LINK_DISTRIBUTED_ID_KEY = "short_link_distributed_id_key";

    /**
     *
     * 生成分布式唯一 ID
     * @param key 唯一业务标识
     * @return 全局唯一递增 ID
     */
    public Long generateDistributedId(String key) {
        return stringRedisTemplate.opsForValue().increment(SHORT_LINK_DISTRIBUTED_ID_KEY + key);
    }
}
