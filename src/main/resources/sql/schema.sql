drop table tbl_user;

create table tbl_user (
	id bigint not null auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    authorities varchar(255) null,
    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(username),
    primary key (id)
);

drop table tbl_client;

create table tbl_client (
	id bigint not null auto_increment,

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
    primary key (id)
);

drop table tbl_client_bill;

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

drop table tbl_organisation;

create table tbl_organisation (
	id bigint not null auto_increment,
    client_id bigint not null,

    public_id varchar(32) null,
    full_name varchar(255) null,
    short_name varchar(64) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(public_id),
    unique(full_name),
    primary key (id)
);

drop table tbl_department;

create table tbl_department (
	id bigint not null auto_increment,
    organisation_id bigint not null,
    client_id bigint not null,

    public_id varchar(32) null,
    full_name varchar(255) null,
    short_name varchar(64) null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(public_id),
    primary key (id)
);

drop table tbl_employee;

create table tbl_employee (
	id bigint not null auto_increment,
    client_id bigint not null,
    organisation_id bigint not null,
    user_id bigint not null,

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
    unique(client_id, organisation_id, user_id),
    primary key (id)
);

drop table tbl_address;

create table tbl_address (
	id bigint not null auto_increment,
    client_id bigint not null,
    organisation_id bigint null,
    employee_id bigint null,

    address_type varchar(64) null,
	short_cut_name varchar(10) null,
    line_1 varchar(255) null,
    line_2 varchar(255) null,
    line_3 varchar(255) null,
    town varchar(255) null,
    country varchar(255) null,
    post_code varchar(32) null,
    is_primary boolean null,

    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(client_id, organisation_id, employee_id),
    primary key (id)
);

drop table tbl_week_hours;

create table tbl_week_hours (
	id bigint not null auto_increment,
    client_id bigint not null,
    organisation_id bigint null,
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
    unique(client_id, organisation_id, employee_id),
    primary key (id)
);

drop table tbl_nic_table_letter;

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

drop table tbl_p45_circumstances_at_start;

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

