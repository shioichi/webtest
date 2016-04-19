package com.cpj.controller;

import com.cpj.openstack.Cinder;
import com.cpj.openstack.Compute;
import com.cpj.openstack.KeyStone;
import com.cpj.pojo.LimitResources;
import com.cpj.pojo.NewsInfo;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Limits;
import org.openstack4j.model.storage.block.BlockQuotaSetUsage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenpengjiang on 2016/3/23.
 */
@Controller
@RequestMapping("general")
public class MenuController extends BaseController{
    @Resource
    Compute compute;
    @Resource
    Cinder cinder;
    @Resource
    KeyStone keystone;
    @RequestMapping("menu")
    public ModelAndView index(@RequestParam("module")String module) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(module+"/"+module);
        return mv;
  }

    @ResponseBody
    @RequestMapping("limitinfo")
    public LimitResources limitinfo(HttpServletRequest request){
        OSClient os = GetOs_INTERNAL();
        Limits limits = compute.getQuotaUsage(os,keystone.Projectgetall(os).get(0));
        BlockQuotaSetUsage blockQuotaSetUsage = cinder.Cinderinusage(os,keystone.Projectgetall(os).get(0).getId());
        LimitResources limitResources = new LimitResources();
        limitResources.setInstances(limits.getAbsolute().getMaxTotalInstances());
        limitResources.setInstanceuse(limits.getAbsolute().getTotalInstancesUsed());
        limitResources.setVcpu(limits.getAbsolute().getMaxTotalCores());
        limitResources.setVcpuuse(limits.getAbsolute().getTotalCoresUsed());
        limitResources.setRam(limits.getAbsolute().getMaxTotalRAMSize());
        limitResources.setRamuse(limits.getAbsolute().getTotalRAMUsed());
        limitResources.setFloatingip(limits.getAbsolute().getMaxTotalFloatingIps());
        limitResources.setFloatingipuse(limits.getAbsolute().getTotalFloatingIpsUsed());
        limitResources.setSecuritygroups(limits.getAbsolute().getMaxSecurityGroups());
        limitResources.setSecuritygroupsuse(limits.getAbsolute().getTotalSecurityGroupsUsed());
        limitResources.setVolumes(blockQuotaSetUsage.getVolumes().getLimit());
        limitResources.setVolumesuse(blockQuotaSetUsage.getVolumes().getInUse());
        limitResources.setVolumessize(blockQuotaSetUsage.getGigabytes().getLimit());
        limitResources.setVolumessizeuse(blockQuotaSetUsage.getGigabytes().getInUse());
        return limitResources;
    }

    @ResponseBody
    @RequestMapping("newsinfo")
    public List<NewsInfo> getNews(HttpServletRequest request){
        List<NewsInfo> lists = new ArrayList<NewsInfo>();
        NewsInfo newsInfo1 = new NewsInfo();
        newsInfo1.setId(1);
        newsInfo1.setType(0);
        newsInfo1.setContent("系统维护通知");
        newsInfo1.setCreattime(new Date());
        NewsInfo newInfo2 = new NewsInfo();
        newInfo2.setId(2);
        newInfo2.setType(1);
        newInfo2.setContent("虚拟机实验1");
        newInfo2.setCreattime(new Date());
        NewsInfo newInfo3 = new NewsInfo();
        newInfo3.setId(2);
        newInfo3.setType(1);
        newInfo3.setContent("虚拟机实验2");
        newInfo3.setCreattime(new Date());
        lists.add(newsInfo1);
        lists.add(newInfo2);
        lists.add(newInfo3);

        return  lists;
    }


}
