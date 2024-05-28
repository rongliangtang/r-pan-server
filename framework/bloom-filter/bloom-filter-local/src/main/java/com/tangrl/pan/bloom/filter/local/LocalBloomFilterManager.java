package com.tangrl.pan.bloom.filter.local;

import com.google.common.collect.Maps;
import com.tangrl.pan.bloom.filter.core.BloomFilter;
import com.tangrl.pan.bloom.filter.core.BloomFilterManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 本地布隆过滤器的管理器
 */
@Component
public class LocalBloomFilterManager implements BloomFilterManager, InitializingBean {

    @Autowired
    private LocalBloomFilterConfig config;

    /**
     * 容器
     */
    private final Map<String, BloomFilter> bloomFilterContainer = Maps.newConcurrentMap();

    /**
     * 根据名称获取对应的布隆过滤器
     *
     * @param name
     * @return
     */
    @Override
    public BloomFilter getFilter(String name) {
        return bloomFilterContainer.get(name);
    }

    /**
     * 获取目前管理中存在的布隆过滤器名称列表
     *
     * @return
     */
    @Override
    public Collection<String> getFilterNames() {
        return bloomFilterContainer.keySet();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<LocalBloomFilterConfigItem> items = config.getItems();
        if (CollectionUtils.isNotEmpty(items)) {
            items.stream().forEach(item -> {
                String funnelTypeName = item.getFunnelTypeName();
                try {
                    FunnelType funnelType = FunnelType.valueOf(funnelTypeName);
                    if (Objects.nonNull(funnelType)) {
                        bloomFilterContainer.putIfAbsent(item.getName(), new LocalBloomFilter(funnelType.getFunnel(), item.getExpectedInsertions(), item.getFpp()));
                    }
                } catch (Exception e) {

                }
            });
        }
    }

}
