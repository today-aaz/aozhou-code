package com.aozhou.code.common.uuid;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @Author: Aozhou
 * @Date: 2024/12/2
 */
@Component
@Slf4j
public class SnowflakeIdGenerator {
        // 数据中心ID 范围 0-31
        private final long datacenterId;
        // 工作机器 ID，范围 0-31
        private final long workerId;
        // 序列号，初始化为 0，用于记录同一毫秒内生成的 ID
        private long sequence = 0L;
        // 自定义纪元，例：2021-01-01
        private final long epoch = 1609459200000L;
        // 位移
        private final long workerIdShift = 12L;
        private final long datacenterIdShift = 17L;
        private final long timestampLeftShift = 22L;
        // 序列号掩码
        private final long sequenceMask = ~(-1L << 12L);

        // 上一次生成 ID 的时间戳，初始化为 -1，表示未生成过 ID
        private long lastTimestamp = -1L;

        /**
         * 构造方法，用于初始化数据中心 ID 和工作机器 ID
         * @param properties 包含数据中心 ID 和工作机器 ID 的配置类
         */
        public SnowflakeIdGenerator(SnowflakeIdProperties properties) {
            this.datacenterId = properties.getDatacenterId();
            this.workerId = properties.getWorkerId();
        }

        /**
         * 生成唯一的分布式 ID
         * @return 唯一的分布式 ID
         */
        public synchronized long generateId() {

            long timestamp = System.currentTimeMillis();

            if (timestamp < lastTimestamp) {
                throw new RuntimeException("时钟回拨异常");
            }

            if (timestamp == lastTimestamp) {
                sequence = (sequence + 1) & sequenceMask;
                // 如果序列号达到最大值（0 表示溢出），需要等待下一毫秒
                if (sequence == 0) {
                    timestamp = waitNextMillis(lastTimestamp);
                }
            } else {
                // 如果是新的毫秒，序列号重置为 0
                sequence = 0L;
            }

            lastTimestamp = timestamp;
            // 通过位移操作生成唯一 ID
            Long id = ((timestamp - epoch) << timestampLeftShift) |
                    (datacenterId << datacenterIdShift) |
                    (workerId << workerIdShift) |
                    sequence;
            log.info("生成唯一分布式 ID：" + id);
            log.info("生成二进制 ID：" + Long.toBinaryString(id));
            return id;
        }

        /**
         * 等待直到下一毫秒
         * @param lastTimestamp 上一次生成 ID 的时间戳
         * @return 当前时间戳
         */
        private long waitNextMillis(long lastTimestamp) {
            long timestamp = System.currentTimeMillis();
            // 如果当前时间小于或等于上一次时间戳，则循环等待
            while (timestamp <= lastTimestamp) {
                timestamp = System.currentTimeMillis();
            }
            return timestamp;
        }

}
