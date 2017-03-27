/**
 * 
 */
package com.joindata.inf.common.support.idgen.component.sequence.impl;

/**
 * 不带两位命名空间的sequence id生成器
 * @author <a href="mailto:gaowei1@joindata.com">高伟</a>
 * @date 2017年3月24日
 */
public class DefaultSequence extends AbstactSequenceImpl {

	public DefaultSequence(String name) {
		this.name = name;
	}
	
	@Override
	public int getPrefix() {
		return 0;
	}
}
