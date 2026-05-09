package github.titandea.mapper;

import github.titandea.dto.create.*;
import github.titandea.entity.*;
import org.mapstruct.*;

/**
 * Интерфейс маппера для преобразования DTO создания в JPA-сущности.
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CreateDtoToEntityMapper {

    /**
     * Преобразует SystemMetrics DTO в Entity.
     * Коллекции игнорируются, так как требуют кастомной логики (1 объект → Set + bidirectional link).
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "agent", source = "agentEntity")
    @Mapping(target = "localIp", source = "dto.localIp")
    @Mapping(target = "cpuMetricsEntities", ignore = true)
    @Mapping(target = "memoryMetricsEntities", ignore = true)
    @Mapping(target = "diskMetricsEntities", ignore = true)
    @Mapping(target = "networkMetricsEntities", ignore = true)
    SystemMetricsEntity toSystemMetricsEntity(SystemMetrics dto, AgentEntity agentEntity);

    /**
     * Вложенные мапперы для метрик
     */
    CpuMetricsEntity toCpuMetricsEntity(CpuMetrics dto);

    MemoryMetricsEntity toMemoryMetricsEntity(MemoryMetrics dto);

    DiskMetricsEntity toDiskMetricsEntity(DiskMetrics dto);

    NetworkMetricsEntity toNetworkMetricsEntity(NetworkMetrics dto);

    /**
     * Преобразует Organization DTO в Entity.
     */
    @Mapping(target = "id", ignore = true)
    OrganizationEntity toOrganizationEntity(Organization dto);

    /**
     * Заполняет коллекции метрик и восстанавливает двусторонние связи.
     * Вызывается автоматически после базового маппинга полей.
     */
    @AfterMapping
    default void populateMetricsAndLink(SystemMetrics dto, @MappingTarget SystemMetricsEntity entity) {
        // CPU
        if (dto.getCpu() != null) {
            CpuMetricsEntity cpu = toCpuMetricsEntity(dto.getCpu());
            cpu.setSystem(entity);
            entity.getCpuMetricsEntities().add(cpu);
        }
        // Memory
        if (dto.getMemory() != null) {
            MemoryMetricsEntity mem = toMemoryMetricsEntity(dto.getMemory());
            mem.setSystem(entity);
            entity.getMemoryMetricsEntities().add(mem);
        }
        // Disk
        if (dto.getDisk() != null) {
            for (DiskMetrics d : dto.getDisk()) {
                DiskMetricsEntity disk = toDiskMetricsEntity(d);
                disk.setSystem(entity);
                entity.getDiskMetricsEntities().add(disk);
            }
        }
        // Network
        if (dto.getNetwork() != null) {
            for (NetworkMetrics n : dto.getNetwork()) {
                NetworkMetricsEntity net = toNetworkMetricsEntity(n);
                net.setSystem(entity);
                entity.getNetworkMetricsEntities().add(net);
            }
        }
    }
}