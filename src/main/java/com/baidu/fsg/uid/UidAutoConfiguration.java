package com.baidu.fsg.uid;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
@ConditionalOnClass({CachedUidGenerator.class, DefaultUidGenerator.class})
@EnableConfigurationProperties({UidProperties.class})
@MapperScan(basePackages = {"com.baidu.fsg.uid.worker.dao"}, annotationClass = Repository.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class UidAutoConfiguration {
    @Autowired
    private UidProperties uidProperties;

    @Bean
    @ConditionalOnMissingBean
    public WorkerIdAssigner disposableWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }
    @Bean
    @ConditionalOnMissingBean
    public UidGenerator cachedUidGenerator(WorkerIdAssigner workerIdAssigner) {
        CachedUidGenerator uidGenerator = new CachedUidGenerator();
        uidGenerator.setWorkerIdAssigner(workerIdAssigner);
        uidGenerator.setEpochStr(uidProperties.getEpochStr());
        uidGenerator.setTimeBits(uidProperties.getTimeBits());
        uidGenerator.setWorkerBits(uidProperties.getWorkerBits());
        uidGenerator.setSeqBits(uidProperties.getSeqBits());
        uidGenerator.setBoostPower(uidProperties.getCache().getBoostPower());
        uidGenerator.setPaddingFactor(uidProperties.getCache().getPaddingFactor());
        if (uidProperties.getCache().getScheduleInterval() > 0) {
            uidGenerator.setScheduleInterval(uidProperties.getCache().getScheduleInterval());
        }
        return uidGenerator;

    }
    @Bean
    @ConditionalOnMissingBean
    public UidGenerator defaultUidGenerator(WorkerIdAssigner workerIdAssigner) {
        DefaultUidGenerator uidGenerator = new DefaultUidGenerator();
        uidGenerator.setWorkerIdAssigner(workerIdAssigner);
        uidGenerator.setEpochStr(uidProperties.getEpochStr());
        uidGenerator.setTimeBits(uidProperties.getTimeBits());
        uidGenerator.setWorkerBits(uidProperties.getWorkerBits());
        uidGenerator.setSeqBits(uidProperties.getSeqBits());
        return uidGenerator;

    }
}
