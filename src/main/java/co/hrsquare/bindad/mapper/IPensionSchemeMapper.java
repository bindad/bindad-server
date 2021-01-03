package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.payroll.PensionScheme;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IPensionSchemeMapper {

    @Insert("<script>" +
                "insert into tbl_company_pension_scheme (" +
                "client_id, " +
                "company_id, " +
                "scheme_name, " +
                "provider, " +
                "scheme_reference, " +
                "contribution_type_id, " +
                "contribution_thresholds_lower_annual, " +
                "contribution_thresholds_upper_annual, " +
                "salary_sacrifice_use_total_after_sacrifice_deducted, " +
                "employee_contribution_percentage, " +
                "employer_contribution_percentage, " +
                "employer_contribution_per_employee, " +
                "pension_taxation_id, " +
                "auto_enrollment_compatible, " +
                "auto_enrollment_compatible_delay_start_never, " +
                "auto_enrollment_compatible_delay_start_always, " +
                "auto_enrollment_compatible_delay_start_per_employee, " +
                "address_id, " +
                "is_default, " +
                "is_deleted, " +
                "updated_by, " +
                "updated_time) " +
                "select " +
                "#{client.id}, " +
                "#{company.id}, " +
                "#{name}, " +
                "#{provider}, " +
                "#{schemeReference}, " +
                "<choose>" +
                    "<when test=\"contributionType != null\">#{contributionType.id}, </when>" +
                    "<otherwise> null, </otherwise>" +
                "</choose>" +
                "#{contributionThresholdLower}, " +
                "#{contributionThresholdUpper}, " +
                "#{salarySacrificeUseTotalAfterSacrificeDeducted}, " +
                "#{employeeContributionPercentage}, " +
                "#{employerContributionPercentage}, " +
                "#{employerContributionPerEmployee}, " +
                "<choose>" +
                    "<when test=\"pensionTaxationType != null\">#{pensionTaxationType.id}, </when>" +
                    "<otherwise> null, </otherwise>" +
                "</choose>" +
                "#{autoEnrolmentCompatible}, " +
                "#{autoEnrolmentDelayStartNever}, " +
                "#{autoEnrolmentDelayStartAlways}, " +
                "#{autoEnrolmentDelayStartPerEmployee}, " +
                "<choose>" +
                    "<when test=\"pensionAddress != null\">#{pensionAddress.id}, </when>" +
                    "<otherwise> null, </otherwise>" +
                "</choose>" +
                "#{defaultScheme}, " +
                "#{deleted}, " +
                "#{updatedBy}, " +
                "#{updatedTime}" +
            "</script>"
    )
    void insert(PensionScheme pensionScheme);

    @Select("select id from tbl_company_pension_scheme " +
            "where client_id = #{client.id} " +
            "and company_id = #{company.id} " +
            "and scheme_name = #{name} "
    )
    Long findId(PensionScheme pensionScheme);

    @Delete("delete from tbl_company_pension_scheme " +
            "where client_id = #{clientId} " +
            "and company_id = #{companyId}")
    void deleteByClientAndCompanyId(long clientId, long companyId);

    @Delete("delete from tbl_company_pension_scheme " +
            "where client_id = #{clientId}")
    void deleteByClientId(long clientId);

    @Select("select address_id from tbl_company_pension_scheme " +
            "where client_id = #{clientId} " +
            "and company_id = #{companyId}")
    List<Long> findAllPensionAddressIds(long clientId, long companyId);
}
