package com.cpj.service;

import com.cpj.pojo.Instance;
import org.openstack4j.model.compute.Server;

import java.util.List;

/**
 * Created by chenpengjiang on 2016/3/24.
 */
public interface InstanceService {
    public List<Instance> ServerToInstance(List<? extends Server> servers);
}
