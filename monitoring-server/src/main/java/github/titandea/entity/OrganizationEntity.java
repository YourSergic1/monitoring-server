package github.titandea.entity;

import github.titandea.enums.AgentState;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность организации для БД.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Column(name = "contact_person", length = 255)
    private String contactPerson;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AgentEntity> agents = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 20)
    private AgentState state;
}
