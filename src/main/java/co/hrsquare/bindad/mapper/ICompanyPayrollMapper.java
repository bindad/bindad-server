package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.payroll.CompanyPayroll;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ICompanyPayrollMapper {

    @Insert("<script>" +
                "insert into tbl_company_payroll (" +
                "client_id, " +
                "company_id, " +
                "pay_frequency, " +
                "<choose>" +
                    "<when test=\"payrollMonthly != null\">" +
                        "monthly_pay_period, " +
                        "monthly_pay_custom_period_start_date, " +
                        "monthly_pay_date_last_working_day, " +
                        "monthly_pay_date_specific_date, " +
                        "monthly_pay_date_specific_date_day, " +
                        "monthly_pay_date_specific_date_current_or_following, " +
                        "monthly_pay_date_specific_date_bring_forward_weekend, " +
                        "monthly_pay_date_specific_weekday, " +
                        "monthly_pay_date_specific_weekday_date, " +
                        "monthly_pay_date_specific_weekday_day, " +
                        "monthly_pay_date_specific_weekday_bring_forward_weekend, " +
                    "</when>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payrollWeekly != null\">" +
                        "weekly_pay_period, " +
                        "weekly_pay_weekly_pay_day, " +
                        "weekly_pay_weekly_pay_day_current_or_following_week, " +
                        "weekly_pay_weekly_pay_day_bring_forward_weekend, " +
                    "</when>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payrollTwoWeekly != null\">" +
                        "two_weekly_start_date_current_period, " +
                        "two_weekly_pay_date_current_period, " +
                        "two_weekly_pay_date_bring_forward_weekend, " +
                    "</when>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payrollFourWeekly != null\">" +
                        "four_weekly_start_date_current_period, " +
                        "four_weekly_pay_date_current_period, " +
                        "four_weekly_pay_date_bring_forward_weekend, " +
                    "</when>" +
                "</choose>" +
                "authorisation_deadline_days, " +
                "authorisation_deadline_days_working_or_calendar, " +
                "payment_method_id, " +
                "<choose>" +
                    "<when test=\"payeDetails != null\">" +
                        "paye_reference, " +
                        "accounting_office_reference, " +
                        "rti_sender_type_id, " +
                        "rti_sender_id, " +
                        "rti_password, " +
                        "sautr, " +
                        "corporation_tax_reference, " +
                        "hmrc_office_name, " +
                        "hmrc_office_telephone, " +
                        "small_employer_relief, " +
                        "payroll_giving_reference, " +
                        "company_payroll_contact_id, " +
                        "payroll_service_type_id, " +
                        "payroll_service_name, " +
                        "payroll_service_contact, " +
                        "payroll_service_telephone, " +
                        "payroll_service_email, " +
                    "</when>" +
                "</choose>" +
                "company_pension_staging_corporation_date, " +
                "is_deleted, " +
                "updated_by, " +
                "updated_time) " +
                "select " +
                "#{client.id}, " +
                "#{company.id}, " +
                "#{payFrequency.name}, " +
                "<choose>" +
                    "<when test=\"payrollMonthly != null\">" +
                        "#{payrollMonthly.monthlyPayPeriod.name}, " +
                        "#{payrollMonthly.customPeriodStartDate}, " +
                        "#{payrollMonthly.useLastWorkingDay}, " +
                        "#{payrollMonthly.useSpecificDate}, " +
                        "#{payrollMonthly.specificDateDay}, " +
                        "#{payrollMonthly.specificDateDayCurrentMonth}, " +
                        "#{payrollMonthly.specificDateBringForwardWeekend}, " +
                        "#{payrollMonthly.specificWeekday}, " +
                        "#{payrollMonthly.specificWeekdayDate}, " +
                        "#{payrollMonthly.specificWeekdayDay}, " +
                        "#{payrollMonthly.specificWeekdayBringForwardWeekend}, " +
                    "</when>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payrollWeekly != null\">" +
                        "#{payrollWeekly.weeklyPayPeriod.name}, " +
                        "#{payrollWeekly.weeklyPayDay}, " +
                        "#{payrollWeekly.weeklyPayDayCurrentWeek}, " +
                        "#{payrollWeekly.bringForwardWeekend}, " +
                    "</when>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payrollTwoWeekly != null\">" +
                        "#{payrollTwoWeekly.startDateCurrentPeriod}, " +
                        "#{payrollTwoWeekly.payDateCurrentPeriod}, " +
                        "#{payrollTwoWeekly.bringForwardWeekend}, " +
                    "</when>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payrollFourWeekly != null\">" +
                        "#{payrollFourWeekly.startDateCurrentPeriod}, " +
                        "#{payrollFourWeekly.payDateCurrentPeriod}, " +
                        "#{payrollFourWeekly.bringForwardWeekend}, " +
                    "</when>" +
                "</choose>" +
                "#{authorisationDeadlineInDays}, " +
                "#{authorisationDeadlineIsWorking}, " +
                "<choose>" +
                    "<when test=\"paymentMethod != null\">#{paymentMethod.id}, </when>" +
                    "<otherwise>null, </otherwise>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"payeDetails != null\">" +
                        "#{payeDetails.payeReference}, " +
                        "#{payeDetails.accountingOfficeReference}, " +
                        "<choose>" +
                            "<when test=\"payeDetails.rtiSenderType != null\">#{payeDetails.rtiSenderType.id}, </when>" +
                            "<otherwise>null, </otherwise>" +
                        "</choose>" +
                        "#{payeDetails.rtiSenderId}, " +
                        "#{payeDetails.rtiEncodedPassword}, " +
                        "#{payeDetails.sautr}, " +
                        "#{payeDetails.corporationTaxReference}, " +
                        "#{payeDetails.hmrcOfficeName}, " +
                        "#{payeDetails.hmrcOfficeTelephone}, " +
                        "#{payeDetails.smallEmployerRelief}, " +
                        "#{payeDetails.payrollGivingReference}, " +
                        "#{payeDetails.companyPayrollContactEmployeeId}, " +
                        "#{payeDetails.payrollService.name}, " +
                        "#{payeDetails.payrollServiceName}, " +
                        "#{payeDetails.payrollServiceContact}, " +
                        "#{payeDetails.payrollServiceTelephone}, " +
                        "#{payeDetails.payrollServiceEmail}, " +
                    "</when>" +
                "</choose>" +
                "#{pensionStagingCorporationDate}, " +
                "#{deleted}, " +
                "#{updatedBy}, " +
                "#{updatedTime} " +
            "</script>")
    void insert(CompanyPayroll companyPayroll);

    @Select("select id from tbl_company_payroll " +
            "where client_id = #{client.id} " +
            "and company_id = #{company.id}")
    Long findId(CompanyPayroll companyPayroll);

    @Delete("delete from tbl_company_payroll " +
            "where client_id = #{clientId}")
    void deleteByClientId(long clientId);

    @Delete("delete from tbl_company_payroll " +
            "where client_id = #{clientId} " +
            "and company_id = #{companyId}")
    void deleteByClientAndCompanyId(long clientId, long companyId);
}
