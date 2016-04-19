package com.cpj.service.impl;

import com.cpj.pojo.Instance;
import com.cpj.service.InstanceService;
import org.openstack4j.model.compute.Server;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenpengjiang on 2016/3/24.
 */
@Service
public class InstanceServiceImpl implements InstanceService {
    public List<Instance> ServerToInstance(List<? extends Server> servers) {

        List<Instance> instances = new ArrayList<Instance>();
        for(Server server : servers){
              Instance instance = new Instance();
              instance.setServerid(server.getId());
            instance.setFlavorname(server.getFlavor().getName());
            instance.setServername(server.getName());
            instance.setCreatetime(server.getCreated());
            instance.setImagename(server.getImage().getName());
            instance.setIpaddress(server.getAddresses().getAddresses().toString());
            instance.setKeyname(server.getKeyName());
            instance.setState(server.getStatus().name());
            instance.setPowerstate(server.getPowerState());
            instances.add(instance);
        }

        return instances;
    }
}
