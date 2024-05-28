package com.tangrl.pan.server.common.cache;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import com.tangrl.pan.cache.core.constants.CacheConstants;
import com.tangrl.pan.core.exception.RPanBusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 手动处理缓存的公用顶级父类
 *
 * @param <V>
 */
public abstract class AbstractManualCacheService<V> implements ManualCacheService<V> {

    @Autowired(required = false)
    private CacheManager cacheManager;

    private Object lock = new Object();

    protected abstract BaseMapper<V> getBaseMapper();

    /**
     * 根据ID查询实体
     * <p>
     * 1、查询缓存，如果命中，直接返回
     * 2、如果没有命中，查询数据库
     * 3、如果数据库有对应的记录，回填缓存
     *
     * @param id
     * @return
     */
    @Override
    public V getById(Serializable id) {
        V result = getByCache(id);
        if (Objects.nonNull(result)) {
            return result;
        }
        // 使用锁机制来保障避免缓存的击穿问题
        synchronized (lock) {
            result = getByCache(id);
            if (Objects.nonNull(result)) {
                return result;
            }
            result = getByDB(id);
            if (Objects.nonNull(result)) {
                putCache(id, result);
            }
        }
        return result;
    }

    /**
     * 根据ID来更新缓存信息
     *
     * @param id
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Serializable id, V entity) {
        int rowNum = getBaseMapper().updateById(entity);
        removeCache(id);
        return rowNum == 1;
    }

    /**
     * 根据ID来删除缓存信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        int rowNum = getBaseMapper().deleteById(id);
        removeCache(id);
        return rowNum == 1;
    }

    /**
     * 根据ID集合查询实体记录列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<V> getByIds(Collection<? extends Serializable> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        List<V> result = ids.stream().map(this::getById).collect(Collectors.toList());
        return result;
    }

    /**
     * 批量更新实体记录
     *
     * @param entityMap
     * @return
     */
    @Override
    public boolean updateByIds(Map<? extends Serializable, V> entityMap) {
        if (MapUtils.isEmpty(entityMap)) {
            return false;
        }
        for (Map.Entry<? extends Serializable, V> entry : entityMap.entrySet()) {
            if (!updateById(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 批量删除实体记录
     *
     * @param ids
     * @return
     */
    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        for (Serializable id : ids) {
            if (!removeById(id)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取缓存对象实体
     *
     * @return
     */
    @Override
    public Cache getCache() {
        if (Objects.isNull(cacheManager)) {
            throw new RPanBusinessException("the cache manager is empty!");
        }
        return cacheManager.getCache(CacheConstants.R_PAN_CACHE_NAME);
    }

    /*************************************private*************************************/

    /**
     * 根据ID从缓存中查询对应的实体信息
     *
     * @param id
     * @return
     */
    private V getByCache(Serializable id) {
        String cacheKey = getCacheKey(id);
        Cache cache = getCache();
        if (Objects.isNull(cache)) {
            return null;
        }
        Cache.ValueWrapper valueWrapper = cache.get(cacheKey);
        if (Objects.isNull(valueWrapper)) {
            return null;
        }
        return (V) valueWrapper.get();
    }

    /**
     * 生成对应的缓存key
     *
     * @param id
     * @return
     */
    private String getCacheKey(Serializable id) {
        return String.format(getKeyFormat(), id);
    }

    /**
     * 将实体信息保存到缓存中
     *
     * @param id
     * @param entity
     */
    private void putCache(Serializable id, V entity) {
        String cacheKey = getCacheKey(id);
        Cache cache = getCache();
        if (Objects.isNull(cache)) {
            return;
        }
        if (Objects.isNull(entity)) {
            return;
        }
        cache.put(cacheKey, entity);
    }

    /**
     * 根据主键查询对应的实体信息
     *
     * @param id
     * @return
     */
    private V getByDB(Serializable id) {
        return getBaseMapper().selectById(id);
    }

    /**
     * 删除缓存信息
     *
     * @param id
     */
    private void removeCache(Serializable id) {
        String cacheKey = getCacheKey(id);
        Cache cache = getCache();
        if (Objects.isNull(cache)) {
            return;
        }
        cache.evict(cacheKey);
    }

}
