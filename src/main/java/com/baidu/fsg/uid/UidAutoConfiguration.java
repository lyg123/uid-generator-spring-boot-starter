package com.baidu.fsg.uid;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({CachedUidGenerator.class, DefaultUidGenerator.class})
public class UidAutoConfiguration {

}
