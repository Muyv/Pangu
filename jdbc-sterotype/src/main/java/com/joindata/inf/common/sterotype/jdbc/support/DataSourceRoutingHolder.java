package com.joindata.inf.common.sterotype.jdbc.support;

import java.util.Map;

import javax.sql.DataSource;

import com.joindata.inf.common.sterotype.jdbc.exception.NoSuchDatasourceException;
import com.joindata.inf.common.util.basic.CollectionUtil;
import com.joindata.inf.common.util.log.Logger;

/**
 * 数据源类型持有者
 * 
 * @author <a href="mailto:songxiang@joindata.com">宋翔</a>
 * @date Mar 27, 2017 2:55:44 PM
 */
public class DataSourceRoutingHolder
{
    private static final Logger log = Logger.get();

    /** 里面是连接串 */
    public static final ThreadLocal<String> RoutingKey = new ThreadLocal<>();

    /** 数据源 Map */
    private static Map<Object, Object> DS_MAP = CollectionUtil.newMap();

    /** 默认数据源 key */
    private static String DefaultDatasource = null;

    private RoutingDataSource routingDataSource;

    public DataSourceRoutingHolder(RoutingDataSource routingDataSource)
    {
        this.routingDataSource = routingDataSource;
    }

    /**
     * 获取数据源 Map
     * 
     * @return 数据源 Map
     */
    public static Map<Object, Object> getDataSourceMap()
    {
        return DS_MAP;
    }

    /**
     * 添加数据源<br />
     * <strong>这个方法可以在运行时调用，但是不建议真这么做，这是不安全的，建议仅在启动时调用</strong>
     * 
     * @param type 数据源类型
     * @param ds 数据源
     */
    public void addDataSource(String key, DataSource ds)
    {
        log.info("动态添加数据源: {}", key);

        DS_MAP.put(key, ds);
        reloadDatasource();
    }

    /**
     * 添加数据源<br />
     * <strong>这个方法可以在运行时调用，但是不建议真这么做，这是不安全的，建议仅在启动时调用</strong>
     * 
     * @param type 数据源类型
     * @param ds 数据源
     */
    public void addDataSource(Map<String, DataSource> dsMap)
    {
        if(dsMap == null)
        {
            throw new IllegalArgumentException("没有给数据源你添加个 JB");
        }

        log.info("动态添加数据源: {}", dsMap.keySet());

        DS_MAP.putAll(dsMap);
        reloadDatasource();
    }

    /**
     * 重新加载数据源
     */
    private void reloadDatasource()
    {
        routingDataSource.afterPropertiesSet();
    }

    /**
     * 获取当前在用的数据源 key
     * 
     * @return 当前数据源 key
     */
    public static Object getRoutingKey()
    {
        String key = RoutingKey.get();

        // 如果没有，就用默认数据源
        if(key == null)
        {
            log.debug("使用默认数据源");
            key = DefaultDatasource;
        }

        if(!DS_MAP.containsKey(key))
        {
            log.error("没有 {} 这个数据源", key);
            throw new NoSuchDatasourceException(key);
        }

        return key;
    }

    /**
     * 选择数据源
     * 
     * @param key 数据源名
     */
    public void useDatasource(String key)
    {
        log.info("切换到数据源: {}", key);
        RoutingKey.set(key);
    }

    /**
     * 设置默认数据源
     * 
     * @param key 数据源名
     */
    public void setDefaultDatasource(String key)
    {
        DefaultDatasource = key;
    }
}