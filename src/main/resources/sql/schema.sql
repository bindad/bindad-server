drop table if exists tbl_user;

create table tbl_user (
	id bigint not null auto_increment,
	client_id bigint null,
	company_id bigint null,
	employee_id bigint null,
    username varchar(255) not null,
    password varchar(255) not null,
    authorities varchar(255) null,
    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(username),
    primary key (id)
);

drop table if exists tbl_client;

create table tbl_client (
	id bigint not null auto_increment,

    public_id varchar(64) not null,
    title varchar(10) not null,
    first_name varchar(32) not null,
    known_as varchar(32) null,
    middle_initial varchar(5) null,
    last_name varchar(32) not null,

    email varchar(255) not null,
    telephone varchar(32) null,
    telephone_ext varchar(32) null,
    mobile varchar(32) null,

    contract_type varchar(32) null,
    contract_status varchar(64) null,
    contract_start_date date not null,
    contract_end_date date null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(email),
    unique(public_id),
    primary key (id)
);

drop table if exists tbl_client_bill;

create table tbl_client_bill (
	id bigint not null auto_increment,
    client_id bigint not null,

    invoice_id varchar(32) not null,
    invoice_amount double null,
    invoice_date date null,
    pay_by_date date null,
    paid_date date null,
    payment_method varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(invoice_id),
    primary key (id)
);

drop table if exists tbl_company;

create table tbl_company (
	id bigint not null auto_increment,
    client_id bigint not null,

    full_name varchar(255) not null,
    public_id varchar(32) null,
    short_name varchar(64) null,

    trading_name varchar(255) not null,
    registered_name varchar(255) not null,
    website varchar(255) null,
    contact_telephone varchar(32) null,
    registered_charity_number varchar(32) null,
    vat_number varchar(32) null,
    is_partnership boolean null,

    admin_title varchar(10) null,
    admin_first_name varchar(32) null,
    admin_known_as varchar(32) null,
    admin_middle_initial varchar(5) null,
    admin_last_name varchar(32) null,
    admin_email varchar(255) null,
    admin_telephone varchar(32) null,
    admin_telephone_ext varchar(32) null,
    admin_mobile varchar(32) null,

    is_finance_same_as_admin boolean null,
    finance_title varchar(10) null,
    finance_first_name varchar(32) null,
    finance_known_as varchar(32) null,
    finance_middle_initial varchar(5) null,
    finance_last_name varchar(32) null,
    finance_email varchar(255) null,
    finance_telephone varchar(32) null,
    finance_telephone_ext varchar(32) null,
    finance_mobile varchar(32) null,

    primary_address_id bigint null,
    other_1_address_id bigint null,
    other_2_address_id bigint null,
    other_3_address_id bigint null,
    other_4_address_id bigint null,
    other_5_address_id bigint null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(public_id),
    unique(full_name),
    primary key (id)
);

drop table if exists tbl_company_payroll;

create table tbl_company_payroll (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint not null,

    pay_frequency_id bigint null,
    /* Monthly */
    monthly_pay_period_id bigint null,
    /* Specify Monthly period */
    monthly_pay_custom_period_start_date int null,
    /* Monthly Pay Date Option1 */
    monthly_pay_date_last_working_day boolean null,

    /* Monthly Pay Date Option2 */
    monthly_pay_date_specific_date boolean null,
    monthly_pay_date_specific_date_day int null,
    monthly_pay_date_specific_date_current_or_following boolean null,
    monthly_pay_date_specific_date_bring_forward_weekend boolean null,

    /* Monthly Pay Date Option3 */
    monthly_pay_date_specific_weekday boolean null,
    monthly_pay_date_specific_weekday_date varchar(10) null,
    monthly_pay_date_specific_weekday_day varchar(10) null,
    monthly_pay_date_specific_weekday_bring_forward_weekend boolean null,

    /* Weekly */
    weekly_pay_period_id bigint null,
    weekly_pay_weekly_pay_day varchar(10) null,
    weekly_pay_weekly_pay_day_current_or_following_week boolean null,
    weekly_pay_weekly_pay_day_bring_forward_weekend boolean null,

    /* 2-Weekly */
    two_weekly_start_date_current_period date null,
    two_weekly_pay_date_current_period date null,
    two_weekly_pay_date_bring_forward_weekend boolean null,

    /* 4-Weekly */
    four_weekly_start_date_current_period date null,
    four_weekly_pay_date_current_period date null,
    four_weekly_pay_date_bring_forward_weekend boolean null,

    authorisation_deadline_days int null,
    authorisation_deadline_days_working_or_calendar boolean null,

    payment_method_id bigint null,

    /* PAYE Details */
    paye_reference varchar(64) null,
    accounting_office_reference varchar(64) null,
    rti_sender_type_id bigint null,
    rti_sender_id varchar(64) null,
    rti_password varchar(64) null,
    sautr varchar(64) null,
    corporation_tax_reference varchar(64) null,
    hmrc_office_name varchar(64) null,
    hmrc_office_telephone varchar(64) null,
    small_employer_relief boolean null,
    payroll_giving_reference varchar(64) null,
    company_payroll_contact_id bigint null,
    payroll_service_id bigint null,

    company_pension_id bigint null,
    add1_company_pension_id bigint null,
    add2_company_pension_id bigint null,
    add3_company_pension_id bigint null,
    add4_company_pension_id bigint null,
    add5_company_pension_id bigint null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, company_id),
    primary key (id)
);

drop table if exists tbl_company_pension_scheme;

create table tbl_company_pension_scheme (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint not null,

    staging_corporation_date date null,
    scheme_name varchar(64) not null,
    provider varchar(64) not null,
    scheme_reference varchar(64) null,
    contribution_type_id bigint null,
    contribution_thresholds_lower_annual double null,
    contribution_thresholds_upper_annual double null,
    salary_sacrifice_use_total_after_sacrifice_deducted boolean null,
    employee_contribution_percentage double null,
    employer_contribution_percentage double null,
    employer_contribution_per_employee boolean null,
    pension_taxation_id bigint null,
    auto_enrollment_compatible boolean null,
    auto_enrollment_compatible_delay_start_never boolean null,
    auto_enrollment_compatible_delay_start_always boolean null,
    auto_enrollment_compatible_delay_start_per_employee boolean null,
    address_id bigint null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, company_id, scheme_name),
    primary key (id)
);

drop table if exists tbl_company_benefit;

create table tbl_company_benefit (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint not null,

    name varchar(32) null,
    salary_sacrifice boolean null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, company_id, name),
    primary key (id)
);

drop table if exists tbl_company_benefit_standard;

create table tbl_company_benefit_standard (
	id bigint not null auto_increment,

    name varchar(32) null,
    salary_sacrifice boolean null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_pay_frequency;

create table tbl_pay_frequency (
	id bigint not null auto_increment,

    name varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    primary key (id)
);

drop table if exists tbl_monthly_pay_period;

create table tbl_monthly_pay_period (
	id bigint not null auto_increment,

    name varchar(64) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_weekly_pay_period;

create table tbl_weekly_pay_period (
	id bigint not null auto_increment,

    name varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_payroll_payment_method;

create table tbl_payroll_payment_method (
	id bigint not null auto_increment,

    name varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_rti_sender_type;

create table tbl_rti_sender_type (
	id bigint not null auto_increment,

    name varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_pension_contribution_type;

create table tbl_pension_contribution_type (
	id bigint not null auto_increment,

    name varchar(64) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_pension_taxation_type;

create table tbl_pension_taxation_type (
	id bigint not null auto_increment,

    name varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(name),
    primary key (id)
);

drop table if exists tbl_department;

create table tbl_department (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint not null,

    public_id varchar(32) null,
    full_name varchar(255) null,
    short_name varchar(64) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, company_id, public_id),
    unique(client_id, company_id, full_name),
    primary key (id)
);

drop table if exists tbl_department_standard;

create table tbl_department_standard (
	id bigint not null auto_increment,

    public_id varchar(32) null,
    full_name varchar(255) null,
    short_name varchar(64) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(full_name),
    primary key (id)
);

drop table if exists tbl_employee;

create table tbl_employee (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint not null,

    title varchar(10) null,
    first_name varchar(32) null,
    known_as varchar(32) null,
    middle_initial varchar(5) null,
    last_name varchar(32) null,

    work_email varchar(255) null,
    work_telephone varchar(32) null,
    work_telephone_ext varchar(32) null,
    work_mobile varchar(32) null,

    home_email varchar(255) null,
    home_telephone varchar(32) null,
    home_telephone_ext varchar(32) null,
    home_mobile varchar(32) null,

    home_address_id bigint null,

    contract_type varchar(32) null,
    contract_start_date date null,
    continued_service_date date null,
    is_permanent boolean null,
    probation_end_date date null,

    employer_notice_period_is_statutory boolean null,
    employer_notice_period_weeks int null,
    employer_notice_period_months int null,
    employee_notice_period_is_statutory boolean null,
    employee_notice_period_weeks int null,
    employee_notice_period_months int null,

    is_work_permit_required boolean null,
    work_permit_expiry_date date null,

    job_title varchar(64) null,
    employee_id varchar(32) null,
    department_id bigint null,
    is_owner boolean null,
    is_manager boolean null,
    is_apprentice boolean null,

    manager_id bigint null,

    hours_type varchar(32) null,
    hours_standard_id bigint null,
    hours_full_time_individual_id bigint null,
    hours_part_time_id bigint null,

    work_location_address_id bigint null,
    home_working_type varchar(32) null,
    home_working_days varchar(32) null,
    is_home_working_ad_hoc boolean null,

    pay_rate double null,
    pay_rate_unit varchar(32) null,
    pay_standard_hours_in_day double null,
    pay_schedule varchar(64) null,
    national_insurance_number varchar(64) null,
    is_director boolean null,
    tax_code varchar(32) null,
    is_w1m1 boolean null,
    has_student_load boolean null,
    student_loan_type varchar(64) null,
    has_post_graduate_loan boolean null,
    nic_table_letter_id bigint null,
    p45_received boolean null,
    p45_leaving_date date null,
    p45_total_pay_to_date double null,
    p45_total_tax_to_date double null,
    p45_tax_code varchar(32) null,
    p45_is_w1m1 boolean null,
    p45_circumstances_at_start_id bigint null,

    bank_account_name varchar(64) null,
    bank_account_number int null,
    bank_account_sort_code varchar(32) null,
    bank_name varchar(64) null,
    bank_address_id bigint null,

    pension_scheme varchar(255) null,

    birth_date date null,
    gender varchar(32) null,
    allergies varchar(255) null,
    allergies_action varchar(255) null,
    food_intolerances varchar(255) null,
    dietary_preferences varchar(255) null,

    emergency_contact_relationship varchar(32) null,
    emergency_contact_title varchar(10) null,
    emergency_contact_first_name varchar(32) null,
    emergency_contact_known_as varchar(32) null,
    emergency_contact_middle_initial varchar(5) null,
    emergency_contact_last_name varchar(32) null,

    emergency_contact_email varchar(255) null,
    emergency_contact_telephone varchar(32) null,
    emergency_contact_telephone_ext varchar(32) null,
    emergency_contact_mobile varchar(32) null,
    emergency_contact_address_id bigint null,

    hs_is_appointed_person boolean null,
    hs_is_fire_warden boolean null,
    hs_is_first_aider boolean null,
    hs_is_key_holder boolean null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(work_email),
    unique(client_id, company_id),
    primary key (id)
);

drop table if exists tbl_address;

create table tbl_address (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint null,
    employee_id bigint null,

    address_type varchar(64) null,
	short_cut_name varchar(10) null,
    line_1 varchar(255) null,
    line_2 varchar(255) null,
    line_3 varchar(255) null,
    town varchar(255) null,
    country varchar(255) null,
    post_code varchar(32) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, company_id, employee_id),
    primary key (id)
);

drop table if exists tbl_week_hours;

create table tbl_week_hours (
	id bigint not null auto_increment,
    client_id bigint not null,
    company_id bigint null,
    employee_id bigint null,

	hours_type varchar(32) null,
    hours_monday varchar(255) null,
    hours_tuesday varchar(255) null,
	hours_wednesday varchar(255) null,
    hours_thursday varchar(255) null,
    hours_friday varchar(255) null,
    hours_saturday varchar(255) null,
    hours_sunday varchar(255) null,

    is_lunch_paid boolean null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, company_id, employee_id),
    primary key (id)
);

drop table if exists tbl_nic_table_letter;

create table tbl_nic_table_letter (
	id bigint not null auto_increment,

    nic_letter char(1) null,
    nic_letter_description varchar(255) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(nic_letter),
    primary key (id)
);

drop table if exists tbl_p45_circumstances_at_start;

create table tbl_p45_circumstances_at_start (
	id bigint not null auto_increment,

    circumstances_letter_code char(1) null,
    circumstances_description varchar(255) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(circumstances_letter_code, circumstances_description),
    primary key (id)
);

