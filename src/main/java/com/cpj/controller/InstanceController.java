package com.cpj.controller;

import com.cpj.openstack.Compute;
import com.cpj.pojo.Instance;
import com.cpj.service.InstanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.identity.Access;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpengjiang on 2016/3/23.
 */
@Controller
@RequestMapping("Instance")
public class InstanceController extends BaseController {
    @Resource
    Compute compute ;
    @Resource
    InstanceService instanceService;

    public static void writeJosn(HttpServletResponse response, String jsonStr) {

        try {

            response.setContentType("text/html");
            response.setHeader("Pragma", "No-cache");// 设置页面不缓存
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.print(jsonStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object arg0) {
        try {
            return objectMapper.writeValueAsString(arg0);
        } catch (Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
    }

    @ResponseBody
    @RequestMapping("getcurInstance")
    public void getCurInstance(HttpServletRequest request ,HttpServletResponse response){
        OSClient os = GetOs_INTERNAL();
        List<? extends Server> servers = compute.Servergetall(os,true);
        List<Instance> instances = instanceService.ServerToInstance(servers);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data", instances);
        String json = writeValueAsString(map);
        writeJosn(response, json);
    }

    @ResponseBody
    @RequestMapping("getconsole")
    public VNCConsole getVNC(HttpServletRequest request,String serverid){
        OSClient os = GetOs_INTERNAL();

        return compute.vncget(os,serverid,VNCConsole.Type.NOVNC);
    }
    @ResponseBody
    @RequestMapping("bootnewserver")
    public Map bootnewserver(HttpServletRequest request,String name){
        OSClient os = getOsClient(request);
        List<String> list = new ArrayList<String>();
        list.add("8d6709c6-0551-470b-bf5d-4a3f78d10534");
        compute.bootserver(os,name,"1","7b365f8f-e337-4ef5-8abf-fdfa212cd0b7",list);
        Map map = new HashMap();
        map.put("flag","success");
        return map;
    }


    private OSClient getOsClient(HttpServletRequest request) {
        Access access = (Access)request.getSession().getAttribute("access_internal");
        return OSFactory.clientFromAccess(access);
    }




}
