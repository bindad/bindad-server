package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.AddressInput;
import co.hrsquare.bindad.controller.input.CompanyInput;
import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.mapper.DataStore;
import co.hrsquare.bindad.mapper.IAddressMapper;
import co.hrsquare.bindad.mapper.ICompanyMapper;
import co.hrsquare.bindad.mapper.IDepartmentMapper;
import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.AddressType;
import co.hrsquare.bindad.model.company.Company;
import co.hrsquare.bindad.model.company.Department;
import co.hrsquare.bindad.model.employee.EmailTelephone;
import co.hrsquare.bindad.model.employee.FullNameDetails;
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
        co = Company.builder()
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

        dataStore.save(ICompanyMapper.class, co);

        //2. address
        co.setPrimaryAddress(saveAddress(co, input.getPrimaryAddress()));
        co.setAdditionalAddress1(saveAddress(co, input.getAdditionalAddress1()));
        co.setAdditionalAddress2(saveAddress(co, input.getAdditionalAddress2()));
        co.setAdditionalAddress3(saveAddress(co, input.getAdditionalAddress3()));
        co.setAdditionalAddress4(saveAddress(co, input.getAdditionalAddress4()));
        co.setAdditionalAddress5(saveAddress(co, input.getAdditionalAddress5()));
        companyMapper.updateAddresses(co);

        //3. departments
        editDepartments(input.getDepartmentsInput());

        return "SUCCESS";
    }

    private Address saveAddress(Company co, AddressInput addressInput) {
        if (addressInput == null) {
            return null;
        }
        Address address = fromInput(addressInput, co);
        dataStore.save(IAddressMapper.class, address);
        return address;
    }

    private Address fromInput(AddressInput addressInput, Company co) {
        if (addressInput == null) {
            return null;
        }

        return Address.builder()
                .type(AddressType.COMPANY)
                .addressShortCutName(addressInput.getAddressShortCutName())
                .line1(addressInput.getLine1())
                .line2(addressInput.getLine2())
                .line3(addressInput.getLine3())
                .town(addressInput.getTown())
                .country(addressInput.getCountry())
                .postCode(addressInput.getPostCode())
                .client(co.getClient())
                .company(co)
                .employee(null)
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();
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
