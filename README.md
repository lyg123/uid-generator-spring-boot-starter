# uid-generator-spring-boot-starter
百度uid spring-boot-starter

使用https://github.com/lyg123/uid-generator

配置参考

```yml
# 以下为可选配置, 如未指定将采用默认值
uid:
  timeBits: 28             # 时间位, 默认:28
  workerBits: 22           # 机器位, 默认:22
  seqBits: 13               # 序列号, 默认:13
  epochStr: "2019-07-11"   # 初始时间, 默认:"2019-07-11"
  cache:     # CachedUidGenerator相关参数
    boostPower: 3          # RingBuffer size扩容参数, 可提高UID生成的吞吐量, 默认:3
    paddingFactor: 50      # 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100), 默认为50
#scheduleInterval: 60    # 默认:不配置此项, 即不实用Schedule线程. 如需使用, 请指定Schedule线程时间间隔, 单位:秒
```
