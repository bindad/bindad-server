package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IDepartmentMapper {

    @Insert("insert into tbl_department (client_id, company_id, public_id, full_name, short_name, is_deleted, updated_by, updated_time) " +
            "select " +
            "#{client.id}, " +
            "#{company.id}, " +
            "#{publicId}, " +
            "#{fullName}, " +
            "#{shortName}, " +
            "#{deleted}, " +
            "#{updatedBy}, " +
            "#{updatedTime}")
    void insert(Department department);

    @Select("select id from tbl_department where full_name=#{fullName}")
    Long findId(Department department);


    @Delete("delete from tbl_department " +
            "where client_id = #{clientId}")
    void deleteByClientId(long clientId);

    @Delete("delete from tbl_department " +
            "where client_id = #{clientId} " +
            "and company_id = #{companyId}")
    void deleteByClientAndCompanyId(long clientId, long companyId);
}
