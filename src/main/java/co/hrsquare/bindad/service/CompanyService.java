package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.mapper.DataStore;
import co.hrsquare.bindad.mapper.IDepartmentMapper;
import co.hrsquare.bindad.mapper.ICompanyMapper;
import co.hrsquare.bindad.model.company.Department;
import co.hrsquare.bindad.model.company.Company;
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
public class CompanyService {
    private final ICompanyMapper companyMapper;
    private final IDepartmentMapper departmentMapper;
    private final DataStore dataStore;

    public CompanyService(ICompanyMapper companyMapper,
                          IDepartmentMapper departmentMapper,
                          DataStore dataStore) {
        this.companyMapper = companyMapper;
        this.departmentMapper = departmentMapper;
        this.dataStore = dataStore;
    }

    @Transactional
    public String createOrUpdateDepartments(DepartmentsInput input) {
        if (CollectionUtils.isEmpty(input.getDepartments())) {
            return "No departments to add in request";
        }

        Company co = companyMapper.findByFullName(input.getCoFullName());
        if (co == null) {
            return "Cannot find company";
        }

        //delete all existing
        departmentMapper.deleteByClientAndCompanyId(co.getClient().getId(), co.getId());

        List<Department> departments = input.getDepartments().stream()
                .map(d -> Department.builder()
                        .publicId(Optional.ofNullable(d.getPublicId()).orElse(d.getFullName()))
                        .shortName(Optional.ofNullable(d.getShortName()).orElse(d.getFullName()))
                        .fullName(d.getFullName())
                        .company(co)
                        .client(co.getClient())
                        .deleted(false)
                        .updatedBy(-1)
                        .updatedTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        departments.forEach(d -> dataStore.save(IDepartmentMapper.class, d));

        return "SUCCESS";
    }

}
