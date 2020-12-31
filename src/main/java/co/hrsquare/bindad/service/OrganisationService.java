package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.mapper.DataStore;
import co.hrsquare.bindad.mapper.IDepartmentMapper;
import co.hrsquare.bindad.mapper.IOrganisationMapper;
import co.hrsquare.bindad.model.organisation.Department;
import co.hrsquare.bindad.model.organisation.Organisation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrganisationService {
    private final IOrganisationMapper organisationMapper;
    private final IDepartmentMapper departmentMapper;
    private final DataStore dataStore;

    public OrganisationService(IOrganisationMapper organisationMapper,
                               IDepartmentMapper departmentMapper,
                               DataStore dataStore) {
        this.organisationMapper = organisationMapper;
        this.departmentMapper = departmentMapper;
        this.dataStore = dataStore;
    }

    @Transactional
    public String createOrUpdateDepartments(DepartmentsInput input) {
        if (CollectionUtils.isEmpty(input.getDepartments())) {
            return "No departments to add in request";
        }

        Organisation org = organisationMapper.findByFullName(input.getOrgFullName());
        if (org == null) {
            return "Cannot find organisation";
        }

        //delete all existing
        departmentMapper.deleteByClientAndOrganisationId(org.getClient().getId(), org.getId());

        List<Department> departments = input.getDepartments().stream()
                .map(d -> Department.builder()
                        .publicId(Optional.ofNullable(d.getPublicId()).orElse(d.getFullName()))
                        .shortName(Optional.ofNullable(d.getShortName()).orElse(d.getFullName()))
                        .fullName(d.getFullName())
                        .organisation(org)
                        .client(org.getClient())
                        .deleted(false)
                        .updatedBy(-1)
                        .updatedTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        departments.forEach(d -> dataStore.save(IDepartmentMapper.class, d));

        return "SUCCESS";
    }

}
