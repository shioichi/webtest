package com.cpj.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.model.telemetry.Meter;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.Statistics;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenpengjiang on 2016/4/11.
 */
@Service
public class Ceilometer {
    private MeterService getMeters(OSClient os){return os.telemetry().meters();}

    /**
     * 返回所有信息
     * @param os
     * @return
     */
    public List<? extends Meter> MetersGetAll(OSClient os){
        return getMeters(os).list();
    }

    /**
     * 返回所有信息
     * @param os
     * @return
     */
    public List<? extends Sample> MetersGetSample(OSClient os){
        return getMeters(os).samples("cpu_util");
    }

    /**
     * 返回所有信息
     * @param os
     * @return
     */
    public List<? extends Statistics> MetersGetStatic(OSClient os){
        return getMeters(os).statistics("cpu_util");
    }
}
