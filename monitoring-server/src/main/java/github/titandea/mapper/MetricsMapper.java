package github.titandea.mapper;

import github.titandea.dto.*;
import github.titandea.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class MetricsMapper {

    public SystemMetricsEntity toEntity(SystemMetrics dto, AgentEntity agentEntity) {
        if (dto == null) return null;

        SystemMetricsEntity entity = new SystemMetricsEntity();
        entity.setAgent(agentEntity);
        entity.setHostname(dto.getHostname());
        entity.setLocalIp(dto.getLocalIp());
        entity.setPublicIp(dto.getPublicIp());
        entity.setDateTime(dto.getDateTime());
        entity.setUptimeMinutes(dto.getUptimeMinutes());

        entity.setCpuMetricsEntities(new HashSet<>());
        entity.setMemoryMetricsEntities(new HashSet<>());
        entity.setDiskMetricsEntities(new HashSet<>());
        entity.setNetworkMetricsEntities(new HashSet<>());

        if (dto.getCpu() != null) {
            CpuMetricsEntity cpu = mapCpu(dto.getCpu());
            cpu.setSystem(entity);
            entity.getCpuMetricsEntities().add(cpu);
        }

        if (dto.getMemory() != null) {
            MemoryMetricsEntity mem = mapMemory(dto.getMemory());
            mem.setSystem(entity);
            entity.getMemoryMetricsEntities().add(mem);
        }

        if (dto.getDisk() != null) {
            for (DiskMetrics d : dto.getDisk()) {
                DiskMetricsEntity disk = mapDisk(d);
                disk.setSystem(entity);
                entity.getDiskMetricsEntities().add(disk);
            }
        }

        if (dto.getNetwork() != null) {
            for (NetworkMetrics n : dto.getNetwork()) {
                NetworkMetricsEntity net = mapNetwork(n);
                net.setSystem(entity);
                entity.getNetworkMetricsEntities().add(net);
            }
        }

        return entity;
    }

    private CpuMetricsEntity mapCpu(CpuMetrics dto) {
        CpuMetricsEntity e = new CpuMetricsEntity();
        e.setUsagePercent(dto.getUsagePercent());
        e.setUserPercent(dto.getUserPercent());
        e.setSystemPercent(dto.getSystemPercent());
        e.setIowaitPercent(dto.getIowaitPercent());
        e.setLoadAverage1(dto.getLoadAverage1());
        e.setLoadAverage5(dto.getLoadAverage5());
        e.setLoadAverage15(dto.getLoadAverage15());
        e.setTemperature(dto.getTemperature());
        e.setPhysicalCores(dto.getPhysicalCores());
        e.setLogicalProcessors(dto.getLogicalProcessors());
        return e;
    }

    private MemoryMetricsEntity mapMemory(MemoryMetrics dto) {
        MemoryMetricsEntity e = new MemoryMetricsEntity();
        e.setTotalBytes(dto.getTotalBytes());
        e.setAvailableBytes(dto.getAvailableBytes());
        e.setUsedBytes(dto.getUsedBytes());
        e.setSwapTotalBytes(dto.getSwapTotalBytes());
        e.setSwapUsedBytes(dto.getSwapUsedBytes());
        e.setSwapUsagePercent(dto.getSwapUsagePercent());
        return e;
    }

    private DiskMetricsEntity mapDisk(DiskMetrics dto) {
        DiskMetricsEntity e = new DiskMetricsEntity();
        e.setMountPoint(dto.getMountPoint());
        e.setType(dto.getType());
        e.setTotalBytes(dto.getTotalBytes());
        e.setUsedBytes(dto.getUsedBytes());
        e.setFreeBytes(dto.getFreeBytes());
        e.setUsagePercent(dto.getUsagePercent());
        return e;
    }

    private NetworkMetricsEntity mapNetwork(NetworkMetrics dto) {
        NetworkMetricsEntity e = new NetworkMetricsEntity();
        e.setInterfaceName(dto.getInterfaceName());
        e.setBytesSent(dto.getBytesSent());
        e.setBytesRecv(dto.getBytesRecv());
        e.setPacketsSent(dto.getPacketsSent());
        e.setPacketsRecv(dto.getPacketsRecv());
        e.setInErrors(dto.getInErrors());
        e.setOutErrors(dto.getOutErrors());
        e.setSpeed(dto.getSpeed());
        return e;
    }
}