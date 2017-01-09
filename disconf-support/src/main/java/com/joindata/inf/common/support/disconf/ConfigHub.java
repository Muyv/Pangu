package com.joindata.inf.common.support.disconf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.joindata.inf.common.basic.errors.SystemError;
import com.joindata.inf.common.basic.exceptions.SystemException;
import com.joindata.inf.common.basic.stereotype.AbstractConfigHub;
import com.joindata.inf.common.basic.support.BootInfoHolder;
import com.joindata.inf.common.support.disconf.bootconfig.DisconfConfig;
import com.joindata.inf.common.util.basic.StringUtil;

/**
 * Disconf 支持配置器
 * 
 * @author 宋翔<songxiang@joindata.com>
 * @date 2016年12月2日 下午5:31:11
 */
@Configuration
@Import({DisconfConfig.class})
public class ConfigHub extends AbstractConfigHub
{
    static
    {
        System.setProperty("disconf.app", BootInfoHolder.getAppId());
        System.setProperty("disconf.version", BootInfoHolder.getAppVersion());
        if(StringUtil.isBlank(System.getProperty("disconf.enable.remote.conf")))
        {
            System.setProperty("disconf.enable.remote.conf", "true");
        }
    }

    @Override
    public void check()
    {
        if(StringUtil.isBlank(System.getProperty("disconf.conf_server_host")))
        {
            throw new SystemException(SystemError.DEPEND_RESOURCE_NOT_READY, "没有配置VM变量 disconf.conf_server_host, Disconf 不知道该连哪");
        }
        if(StringUtil.isBlank(System.getProperty("disconf.env")))
        {
            throw new SystemException(SystemError.DEPEND_RESOURCE_NOT_READY, "没有配置VM变量 disconf.env, Disconf 不知道取那个环境的配置");
        }
    }
}