truncate table tbl_nic_table_letter;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'A', 'Standard', 0, -1, now()
;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'B', 'MarriedWomenAndWidows', 0, -1, now()
;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'C', 'EmployeesOverStatePensionAge', 0, -1, now()
;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'J', 'EmployeesDeferNI', 0, -1, now()
;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'H', 'Apprentice', 0, -1, now()
;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'M', 'EmployeesUnder21', 0, -1, now()
;

insert into tbl_nic_table_letter (nic_letter, nic_letter_description, is_deleted, updated_by, updated_time)
select 'Z', 'EmployeesUnder21DeferNI', 0, -1, now()
;

truncate table tbl_p45_circumstances_at_start;

insert into tbl_p45_circumstances_at_start (circumstances_letter_code, circumstances_description, is_deleted, updated_by, updated_time)
select 'A', 'EmployeeFirstJobSince6April', 0, -1, now()
;

insert into tbl_p45_circumstances_at_start (circumstances_letter_code, circumstances_description, is_deleted, updated_by, updated_time)
select 'B', 'EmployeeOnlyJobSince6April', 0, -1, now()
;

insert into tbl_p45_circumstances_at_start (circumstances_letter_code, circumstances_description, is_deleted, updated_by, updated_time)
select 'B', 'EmployeeHasAnotherJob', 0, -1, now()
;

truncate table tbl_department_standard;

/* Standard Departments */
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Account Management', 'Account Management', 'Account Management', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Administration', 'Administration', 'Administration', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Clinical Staff', 'Clinical Staff', 'Clinical Staff', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Content Management', 'Content Management', 'Content Management', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Customer Service', 'Customer Service', 'Customer Service', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Development', 'Development', 'Development', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Engineering', 'Engineering', 'Engineering', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Finance', 'Finance', 'Finance', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'IT', 'IT', 'IT', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Management', 'Management', 'Management', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Marketing', 'Marketing', 'Marketing', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Operations', 'Operations', 'Operations', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Human Resources', 'Human Resources', 'Human Resources', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Production', 'Production', 'Production', 0, -1, now()
;
insert into tbl_department_standard (public_id, full_name, short_name, is_deleted, updated_by, updated_time)
select 'Sales', 'Sales', 'Sales', 0, -1, now()
;


truncate table tbl_company_benefit_standard;

/* Company Benefit Standard */
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Car Parking', 1, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Childcare Vouchers', 1, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Critical Illness Insurance', 0, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Cycle to Work', 1, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Gym', 1, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Health Insurance', 0, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Life Insurance', 0, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Personal Cars', 1, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Technology', 1, 0, -1, now()
;
insert into tbl_company_benefit_standard (name, salary_sacrifice, is_deleted, updated_by, updated_time)
select 'Workplace Nursery', 1, 0, -1, now()
;

truncate table tbl_pay_frequency;

/* Pay Frequency Types */
insert into tbl_pay_frequency (name, is_deleted, updated_by, updated_time)
select 'Monthly', 0, -1, now()
;
insert into tbl_pay_frequency (name, is_deleted, updated_by, updated_time)
select 'Weekly', 0, -1, now()
;
insert into tbl_pay_frequency (name, is_deleted, updated_by, updated_time)
select '2-Weekly', 0, -1, now()
;
insert into tbl_pay_frequency (name, is_deleted, updated_by, updated_time)
select '4-Weekly', 0, -1, now()
;

truncate table tbl_monthly_pay_period;

/* Monthly Pay Period */
insert into tbl_monthly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Calendar months (Jan, Feb, Mar, etc)', 0, -1, now()
;
insert into tbl_monthly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Tax months (6th to 5th)', 0, -1, now()
;
insert into tbl_monthly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Specified monthly periods...', 0, -1, now()
;

truncate table tbl_weekly_pay_period;

/* Weekly Pay Period */
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Mon - Sun', 0, -1, now()
;
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Tue - Mon', 0, -1, now()
;
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Wed - Tue', 0, -1, now()
;
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Thu - Wed', 0, -1, now()
;
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Fri - Thu', 0, -1, now()
;
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Sat - Fri', 0, -1, now()
;
insert into tbl_weekly_pay_period (name, is_deleted, updated_by, updated_time)
select 'Sun - Sat', 0, -1, now()
;

truncate table tbl_payroll_payment_method;

/* Payment Method */
insert into tbl_payroll_payment_method (name, is_deleted, updated_by, updated_time)
select 'Electronic', 0, -1, now()
;
insert into tbl_payroll_payment_method (name, is_deleted, updated_by, updated_time)
select 'Cash', 0, -1, now()
;
insert into tbl_payroll_payment_method (name, is_deleted, updated_by, updated_time)
select 'Cheque', 0, -1, now()
;

truncate table tbl_rti_sender_type;

/* RTI Sender Types */
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Individual', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Company', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Agent', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Bureau', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Partnership', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Trust', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Employer', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Government', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Acting in Capacity', 0, -1, now()
;
insert into tbl_rti_sender_type (name, is_deleted, updated_by, updated_time)
select 'Other', 0, -1, now()
;

truncate table tbl_pension_contribution_type;

/* Pension Contribution Type */
insert into tbl_pension_contribution_type (name, is_deleted, updated_by, updated_time)
select 'Total Earnings', 0, -1, now()
;
insert into tbl_pension_contribution_type (name, is_deleted, updated_by, updated_time)
select 'Qualifying Earnings - Auto Enrolment thresholds', 0, -1, now()
;
insert into tbl_pension_contribution_type (name, is_deleted, updated_by, updated_time)
select 'Qualifying Earnings - specify thresholds', 0, -1, now()
;

truncate table tbl_pension_taxation_type;

/* Pension Taxation Types */
insert into tbl_pension_taxation_type (name, is_deleted, updated_by, updated_time)
select 'Relief at source', 0, -1, now()
;
insert into tbl_pension_taxation_type (name, is_deleted, updated_by, updated_time)
select 'Net pay arrangement', 0, -1, now()
;
insert into tbl_pension_taxation_type (name, is_deleted, updated_by, updated_time)
select 'Salary sacrifice', 0, -1, now()
;
