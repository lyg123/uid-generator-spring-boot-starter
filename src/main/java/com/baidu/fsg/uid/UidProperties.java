package com.baidu.fsg.uid;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("uid")
public class UidProperties {
    /**
     * 时间增量值占用位数。当前时间相对于时间基点的增量值，单位为秒
     */
    private int timeBits = 28;

    /**
     * 工作机器ID占用的位数
     */
    private int workerBits = 22;

    /**
     * 序列号占用的位数
     */
    private int seqBits = 13;

    /**
     * 时间基点. 例如 2019-07-11
     */
    private String epochStr = "2019-07-11";
    /**
     * Cache Uid 配置属性
     */
    @NestedConfigurationProperty
    private UidCacheProperties cache = new UidCacheProperties();
    public int getTimeBits() {
        return timeBits;
    }

    public void setTimeBits(int timeBits) {
        if (timeBits > 0) {
            this.timeBits = timeBits;
        }
    }

    public int getWorkerBits() {
        return workerBits;
    }

    public void setWorkerBits(int workerBits) {
        if (workerBits > 0) {
            this.workerBits = workerBits;
        }
    }

    public int getSeqBits() {
        return seqBits;
    }

    public void setSeqBits(int seqBits) {
        if (seqBits > 0) {
            this.seqBits = seqBits;
        }
    }

    public String getEpochStr() {
        return epochStr;
    }

    public void setEpochStr(String epochStr) {
        if (StringUtils.isNotBlank(epochStr)) {
            this.epochStr = epochStr;

        }
    }

    public UidCacheProperties getCache() {
        return cache;
    }

    public void setCache(UidCacheProperties cache) {
        this.cache = cache;
    }

    /**
     * Cache Uid 配置属性
     */
    class UidCacheProperties {
        /**
         * RingBuffer size扩容参数, 可提高UID生成的吞吐量, 默认:3
         */
        private int boostPower = 3;
        /**
         * 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100), 默认为50
         */
        private int paddingFactor = 50;
        /**
         * 默认:不配置此项, 即不使用Schedule线程定时填充. 如需使用, 请指定Schedule线程时间间隔, 单位:秒
         */
        private int scheduleInterval;

        public int getBoostPower() {
            return boostPower;
        }

        public void setBoostPower(int boostPower) {
            this.boostPower = boostPower;
        }

        public int getPaddingFactor() {
            return paddingFactor;
        }

        public void setPaddingFactor(int paddingFactor) {
            this.paddingFactor = paddingFactor;
        }

        public int getScheduleInterval() {
            return scheduleInterval;
        }

        public void setScheduleInterval(int scheduleInterval) {
            this.scheduleInterval = scheduleInterval;
        }
    }
}
