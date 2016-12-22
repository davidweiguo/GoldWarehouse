package com.goldwarehouse.common.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guo_d on 2016/12/22.
 */
public interface ISerialization {

    Logger log = LoggerFactory.getLogger(ISerialization.class);

    Object serialize(Object obj) throws Exception;

    Object deSerialize(Object obj) throws Exception;

}
