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