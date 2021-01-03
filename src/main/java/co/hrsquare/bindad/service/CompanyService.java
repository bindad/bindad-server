package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.AddressInput;
import co.hrsquare.bindad.controller.input.CompanyInput;
import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.mapper.DataStore;
import co.hrsquare.bindad.mapper.IAddressMapper;
import co.hrsquare.bindad.mapper.ICompanyMapper;
import co.hrsquare.bindad.mapper.IDepartmentMapper;
import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.company.Company;
import co.hrsquare.bindad.model.company.Department;
import co.hrsquare.bindad.model.EmailTelephone;
import co.hrsquare.bindad.model.FullNameDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.hrsquare.bindad.model.Address.fromAddressInput;

@Component
@Slf4j
public class CompanyService {
    private final ICompanyMapper companyMapper;
    private final IDepartmentMapper departmentMapper;
    private final DataStore dataStore;
    private final IAddressMapper addressMapper;

    public CompanyService(ICompanyMapper companyMapper,
                          IDepartmentMapper departmentMapper,
                          DataStore dataStore,
                          IAddressMapper addressMapper) {
        this.companyMapper = companyMapper;
        this.departmentMapper = departmentMapper;
        this.dataStore = dataStore;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public String editCompanyDetails(CompanyInput input) {
        Company co = companyMapper.findByFullName(input.getTradingName());
        if (co == null) {
            return "Cannot find company";
        }

        companyMapper.deleteById(co.getId());

        FullNameDetails adminNameDetails = FullNameDetails.builder()
                .title(input.getAdminNameDetails().getTitle())
                .firstName(input.getAdminNameDetails().getFirstName())
                .knownAs(input.getAdminNameDetails().getKnownAs())
                .middleInitial(input.getAdminNameDetails().getMiddleInitial())
                .lastName(input.getAdminNameDetails().getLastName())
                .build();

        EmailTelephone adminContactDetails = EmailTelephone.builder()
                .email(input.getAdminContactDetails().getEmail())
                .telephone(input.getAdminContactDetails().getTelephone())
                .telephoneExt(input.getAdminContactDetails().getTelephoneExt())
                .mobile(input.getAdminContactDetails().getMobile())
                .build();

        FullNameDetails financeNameDetails;
        EmailTelephone financeContactDetails;
        if (!input.isFinanceSameAsAdmin()) {
            financeNameDetails = FullNameDetails.builder()
                    .title(input.getFinanceNameDetails().getTitle())
                    .firstName(input.getFinanceNameDetails().getFirstName())
                    .knownAs(input.getFinanceNameDetails().getKnownAs())
                    .middleInitial(input.getFinanceNameDetails().getMiddleInitial())
                    .lastName(input.getFinanceNameDetails().getLastName())
                    .build();

            financeContactDetails = EmailTelephone.builder()
                    .email(input.getFinanceContactDetails().getEmail())
                    .telephone(input.getFinanceContactDetails().getTelephone())
                    .telephoneExt(input.getFinanceContactDetails().getTelephoneExt())
                    .mobile(input.getFinanceContactDetails().getMobile())
                    .build();
        } else {
            financeNameDetails = null;
            financeContactDetails = null;
        }

        //1. create skeleton (so without any details for supplementary tables
        //such as addresses, departments, etc) in order to generate id
        Company newCo = Company.builder()
                .tradingName(input.getTradingName())
                .fullName(input.getTradingName())
                .registeredName(input.getRegisteredName())
                .website(input.getWebsite())
                .contactTelephone(input.getContactTelephone())
                .registeredCharityNumber(input.getRegisteredCharityNumber())
                .vatNumber(input.getVatNumber())
                .partnership(input.isPartnership())
                .adminNameDetails(adminNameDetails)
                .adminContactDetails(adminContactDetails)
                .financeSameAsAdmin(input.isFinanceSameAsAdmin())
                .financeNameDetails(input.isFinanceSameAsAdmin() ? adminNameDetails : financeNameDetails)
                .financeContactDetails(input.isFinanceSameAsAdmin() ? adminContactDetails : financeContactDetails)
                .client(co.getClient())
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();

        dataStore.save(ICompanyMapper.class, newCo);

        //2. address
        List<Long> addressIdsToDelete = collectAddresses(co);
        addressMapper.deleteAll(addressIdsToDelete);
        newCo.setPrimaryAddress(saveAddress(newCo, input.getPrimaryAddress()));
        newCo.setAdditionalAddress1(saveAddress(newCo, input.getAdditionalAddress1()));
        newCo.setAdditionalAddress2(saveAddress(newCo, input.getAdditionalAddress2()));
        newCo.setAdditionalAddress3(saveAddress(newCo, input.getAdditionalAddress3()));
        newCo.setAdditionalAddress4(saveAddress(newCo, input.getAdditionalAddress4()));
        newCo.setAdditionalAddress5(saveAddress(newCo, input.getAdditionalAddress5()));
        companyMapper.updateAddresses(newCo);

        //3. departments
        editDepartments(input.getDepartmentsInput());

        return "SUCCESS";
    }

    private List<Long> collectAddresses(Company co) {
        List<Long> list = new ArrayList<>();

        if (co.getPrimaryAddress() != null) {
            list.add(co.getPrimaryAddress().getId());
        }
        if (co.getAdditionalAddress1() != null) {
            list.add(co.getAdditionalAddress1().getId());
        }
        if (co.getAdditionalAddress2() != null) {
            list.add(co.getAdditionalAddress2().getId());
        }
        if (co.getAdditionalAddress3() != null) {
            list.add(co.getAdditionalAddress3().getId());
        }
        if (co.getAdditionalAddress4() != null) {
            list.add(co.getAdditionalAddress4().getId());
        }
        if (co.getAdditionalAddress5() != null) {
            list.add(co.getAdditionalAddress5().getId());
        }

        return list;
    }

    private Address saveAddress(Company co, AddressInput addressInput) {
        if (addressInput == null) {
            return null;
        }
        Address address = fromAddressInput(addressInput, co);
        dataStore.save(IAddressMapper.class, address);
        return address;
    }

    @Transactional
    public String editDepartments(DepartmentsInput input) {
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
