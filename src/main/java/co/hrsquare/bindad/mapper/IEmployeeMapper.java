package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.employee.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IEmployeeMapper {

    @Insert("<script>" +
            "insert into tbl_employee (" +
            "client_id, " +
            "organisation_id, " +
            "title, " +
            "first_name, " +
            "known_as, " +
            "middle_initial, " +
            "last_name, " +
            "work_email, " +
            "work_telephone, " +
            "work_telephone_ext, " +
            "work_mobile, " +
            "home_email, " +
            "home_telephone, " +
            "home_telephone_ext, " +
            "home_mobile, " +
            "home_address_id, " +
            "contract_type, " +
            "contract_start_date, " +
            "continued_service_date, " +
            "is_permanent, " +
            "probation_end_date, " +
            "employer_notice_period_is_statutory, " +
            "employer_notice_period_weeks, " +
            "employer_notice_period_months, " +
            "employee_notice_period_is_statutory, " +
            "employee_notice_period_weeks, " +
            "employee_notice_period_months, " +
            "is_work_permit_required, " +
            "work_permit_expiry_date, " +
            "job_title, " +
            "employee_id, " +
            "department_id, " +
            "is_owner, " +
            "is_manager, " +
            "is_apprentice, " +
            "manager_id, " +
            "hours_type, " +
            "hours_standard_id, " +
            "hours_full_time_individual_id, " +
            "hours_part_time_id, " +
            "work_location_address_id, " +
            "home_working_type, " +
            "home_working_days, " +
            "is_home_working_ad_hoc, " +
            "pay_rate, " +
            "pay_rate_unit, " +
            "pay_standard_hours_in_day, " +
            "pay_schedule, " +
            "national_insurance_number, " +
            "is_director, " +
            "tax_code, " +
            "is_w1m1, " +
            "has_student_load, " +
            "student_loan_type, " +
            "has_post_graduate_loan, " +
            "nic_table_letter_id, " +
            "p45_received, " +
            "p45_leaving_date, " +
            "p45_total_pay_to_date, " +
            "p45_total_tax_to_date, " +
            "p45_tax_code, " +
            "p45_is_w1m1, " +
            "p45_circumstances_at_start_id, " +
            "bank_account_name, " +
            "bank_account_number, " +
            "bank_account_sort_code, " +
            "bank_name, " +
            "bank_address_id, " +
            "pension_scheme, " +
            "birth_date, " +
            "gender, " +
            "allergies, " +
            "allergies_action, " +
            "food_intolerances, " +
            "dietary_preferences, " +
            "emergency_contact_relationship, " +
            "emergency_contact_title, " +
            "emergency_contact_first_name, " +
            "emergency_contact_known_as, " +
            "emergency_contact_middle_initial, " +
            "emergency_contact_last_name, " +
            "emergency_contact_email, " +
            "emergency_contact_telephone, " +
            "emergency_contact_telephone_ext, " +
            "emergency_contact_mobile, " +
            "emergency_contact_address_id, " +
            "hs_is_appointed_person, " +
            "hs_is_fire_warden, " +
            "hs_is_first_aider, " +
            "hs_is_key_holder, " +
            "is_deleted, " +
            "updated_by, " +
            "updated_time) " +
            "select " +
            "#{client.id}, " +
            "#{organisation.id}, " +
            "#{contactDetails.fullNameDetails.title.name}, " +
            "#{contactDetails.fullNameDetails.firstName}, " +
            "#{contactDetails.fullNameDetails.knownAs}, " +
            "#{contactDetails.fullNameDetails.middleInitial}, " +
            "#{contactDetails.fullNameDetails.lastName}, " +
            "#{contactDetails.workContact.email}, " +
            "#{contactDetails.workContact.telephone}, " +
            "#{contactDetails.workContact.telephoneExt}, " +
            "#{contactDetails.workContact.mobile}, " +
            "#{contactDetails.homeContact.email}, " +
            "#{contactDetails.homeContact.telephone}, " +
            "#{contactDetails.homeContact.telephoneExt}, " +
            "#{contactDetails.homeContact.mobile}, " +
            "#{homeAddress.id}, " +
            "#{contract.type.name}, " +
            "#{contract.startDate}, " +
            "#{contract.continuedServiceDate}, " +
            "#{contract.permanent}, " +
            "#{contract.probationEndDate}, " +
            "#{contract.employerNoticePeriod.statutory}, " +
            "#{contract.employerNoticePeriod.periodInWeeks}, " +
            "#{contract.employerNoticePeriod.periodInMonths}, " +
            "#{contract.employeeNoticePeriod.statutory}, " +
            "#{contract.employeeNoticePeriod.periodInWeeks}, " +
            "#{contract.employeeNoticePeriod.periodInMonths}, " +
            "#{contract.workPermitRequired}, " +
            "#{contract.workPermitExpiry}, " +
            "#{jobDetails.jobTitle}, " +
            "#{jobDetails.employeeId}, " +
            "#{jobDetails.department.id}, " +
            "#{jobDetails.owner}, " +
            "#{jobDetails.manager}, " +
            "#{jobDetails.apprentice}, " +
            "#{managementDetails.reportsTo.id}, " +
            "#{hours.hoursType.name}," +
            "<choose>" +
            "   <when test=\"hours != null and hours.hoursType != null and hours.hoursType.name == 'StandardCompany'\">#{hours.everyWeek.id}, </when>" +
            "   <otherwise>null, </otherwise>" +
            "</choose>" +
            "<choose>" +
            "   <when test=\"hours != null and hours.hoursType != null and hours.hoursType.name == 'FullTimeIndividual'\">#{hours.everyWeek.id}, </when>" +
            "   <otherwise>null, </otherwise>" +
            "</choose>" +
            "<choose>" +
            "   <when test=\"hours != null and hours.hoursType != null and hours.hoursType.name == 'PartTime'\">#{hours.everyWeek.id}, </when>" +
            "   <otherwise>null, </otherwise>" +
            "</choose>" +
            "#{workLocation.location.id}, " +
            "#{workLocation.homeWorkingType.name}, " +
            "#{workLocation.daysHomeWorking}, " +
            "#{workLocation.adHocFromHome.name}, " +
            "#{employeePay.rate}, " +
            "#{employeePay.payRateUnit.name}, " +
            "#{employeePay.standardHoursInDay}, " +
            "#{employeePay.paySchedule}, " +
            "#{employeePay.nationalInsuranceNumber}, " +
            "#{employeePay.director}, " +
            "#{employeePay.taxCode}, " +
            "#{employeePay.week1OrMonth1}, " +
            "#{employeePay.studentLoan}, " +
            "#{employeePay.studentLoanType.name}, " +
            "#{employeePay.postGraduateLoan}, " +
            "#{employeePay.nicTableLetter.id}, " +
            "#{employeePay.p45Details.received}, " +
            "#{employeePay.p45Details.leavingDate}, " +
            "#{employeePay.p45Details.totalPayToDate}, " +
            "#{employeePay.p45Details.totalTaxToDate}, " +
            "#{employeePay.p45Details.p45TaxCode}, " +
            "#{employeePay.p45Details.p45W1OrM1}, " +
            "#{employeePay.p45Details.circumstancesAtStart.id}, " +
            "#{employeePay.bankDetails.accountName}, " +
            "#{employeePay.bankDetails.accountNumber}, " +
            "#{employeePay.bankDetails.sortCode}, " +
            "#{employeePay.bankDetails.bankName}, " +
            "#{employeePay.bankDetails.bankAddress.id}, " +
            "#{employeePay.pensionScheme}, " +
            "#{personalDetails.birthDate}, " +
            "#{personalDetails.gender.name}, " +
            "#{allergyDetails.allergies}, " +
            "#{allergyDetails.whatToDoIfAllergicReaction}, " +
            "#{allergyDetails.foodIntolerances}, " +
            "#{allergyDetails.dietaryPreferences}, " +
            "#{emergencyContact.relationship}, " +
            "#{emergencyContact.fullNameDetails.title.name}, " +
            "#{emergencyContact.fullNameDetails.firstName}, " +
            "#{emergencyContact.fullNameDetails.knownAs}, " +
            "#{emergencyContact.fullNameDetails.middleInitial}, " +
            "#{emergencyContact.fullNameDetails.lastName}, " +
            "#{emergencyContact.emailTelephone.email}, " +
            "#{emergencyContact.emailTelephone.telephone}, " +
            "#{emergencyContact.emailTelephone.telephoneExt}, " +
            "#{emergencyContact.emailTelephone.mobile}, " +
            "#{emergencyContact.address.id}, " +
            "#{healthAndSafetyDetails.appointedPerson}, " +
            "#{healthAndSafetyDetails.fireWarden}, " +
            "#{healthAndSafetyDetails.firstAider}, " +
            "#{healthAndSafetyDetails.keyHolder}, " +
            "#{deleted}, " +
            "#{updatedBy}, " +
            "#{updatedTime}" +
            "</script>")
    void insert(Employee employee);

    @Select("select id from tbl_employee where work_email=#{contactDetails.workContact.email}")
    Long findId(Employee employee);

    @Delete("<script>" +
                "delete from tbl_employee " +
                "where client_id = #{clientId};" +
            "</script>")
    void deleteByClientId(Long clientId);

}
