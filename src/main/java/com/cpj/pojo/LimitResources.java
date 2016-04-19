package com.cpj.pojo;

import com.cpj.pojo.base.GeneralPojo;

/**
 * Created by chenpengjiang on 2016/4/11.
 */
public class LimitResources extends GeneralPojo {
    private static final long serialVersionUID = 100000002L;

    private int Instances;
    private int instanceuse;

    private int vcpu;
    private int vcpuuse;

    private int ram;
    private int ramuse;

    private int floatingip;
    private int floatingipuse;

    private int volumes;
    private int volumesuse;

    private int volumessize;
    private int volumessizeuse;

    private int securitygroups;
    private int securitygroupsuse;

    public int getSecuritygroupsuse() {
        return securitygroupsuse;
    }

    public void setSecuritygroupsuse(int securitygroupsuse) {
        this.securitygroupsuse = securitygroupsuse;
    }

    public int getSecuritygroups() {
        return securitygroups;
    }

    public void setSecuritygroups(int securitygroups) {
        this.securitygroups = securitygroups;
    }

    public int getInstances() {
        return Instances;
    }

    public void setInstances(int instances) {
        Instances = instances;
    }

    public int getInstanceuse() {
        return instanceuse;
    }

    public void setInstanceuse(int instanceuse) {
        this.instanceuse = instanceuse;
    }

    public int getVcpu() {
        return vcpu;
    }

    public void setVcpu(int vcpu) {
        this.vcpu = vcpu;
    }

    public int getVcpuuse() {
        return vcpuuse;
    }

    public void setVcpuuse(int vcpuuse) {
        this.vcpuuse = vcpuuse;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRamuse() {
        return ramuse;
    }

    public void setRamuse(int ramuse) {
        this.ramuse = ramuse;
    }

    public int getFloatingip() {
        return floatingip;
    }

    public void setFloatingip(int floatingip) {
        this.floatingip = floatingip;
    }

    public int getFloatingipuse() {
        return floatingipuse;
    }

    public void setFloatingipuse(int floatingipuse) {
        this.floatingipuse = floatingipuse;
    }

    public int getVolumes() {
        return volumes;
    }

    public void setVolumes(int volumes) {
        this.volumes = volumes;
    }

    public int getVolumesuse() {
        return volumesuse;
    }

    public void setVolumesuse(int volumesuse) {
        this.volumesuse = volumesuse;
    }

    public int getVolumessize() {
        return volumessize;
    }

    public void setVolumessize(int volumessize) {
        this.volumessize = volumessize;
    }

    public int getVolumessizeuse() {
        return volumessizeuse;
    }

    public void setVolumessizeuse(int volumessizeuse) {
        this.volumessizeuse = volumessizeuse;
    }
}
