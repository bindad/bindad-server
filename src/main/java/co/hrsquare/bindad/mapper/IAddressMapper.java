package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.company.Company;
import co.hrsquare.bindad.model.company.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IAddressMapper {

    @Insert("insert into tbl_address (" +
            "client_id, " +
            "company_id, " +
            "employee_id, " +
            "address_type, " +
            "short_cut_name, " +
            "line_1, " +
            "line_2, " +
            "line_3, " +
            "town, " +
            "country, " +
            "post_code, " +
            "is_deleted, " +
            "updated_by, " +
            "updated_time) " +
            "select " +
            "#{client.id}, " +
            "#{company.id}, " +
            "#{employee.id}, " +
            "#{type.name}, " +
            "#{addressShortCutName}, " +
            "#{line1}, " +
            "#{line2}, " +
            "#{line3}, " +
            "#{town}, " +
            "#{country}, " +
            "#{postCode}, " +
            "#{deleted}, " +
            "#{updatedBy}, " +
            "#{updatedTime}")
    void insert(Address address);

    @Select("<script>" +
                "select id from tbl_address " +
                "where line_1=#{line1} " +
                "and post_code=#{postCode} " +
                "and client_id=#{client.id} " +
                "<choose>" +
                    "<when test=\"company != null\">and company_id=#{company.id} </when>" +
                    "<otherwise>and company_id is null </otherwise>" +
                "</choose>" +
                "<choose>" +
                    "<when test=\"employee != null\">and employee_id=#{employee.id} </when>" +
                    "<otherwise>and employee_id is null </otherwise>" +
                "</choose>" +
            "</script>"
    )
    Long findId(Address address);


    @Delete("delete from tbl_address " +
            "where client_id = #{clientId}")
    void deleteByClientId(long clientId);

    @Delete("delete from tbl_address " +
            "where client_id = #{clientId} " +
            "and company_id = #{companyId}")
    void deleteByClientAndCompanyId(long clientId, long companyId);

    @Delete("<script>" +
                "delete from tbl_address " +
                "where " +
                    "<choose>" +
                        "<when test=\"addressIds != null and addressIds.size > 0 \">" +
                            "id in " +
                            "<foreach item=\"item\" index=\"index\" collection=\"addressIds\" open=\"(\" separator=\",\" close=\")\" >" +
                            " #{item} " +
                            "</foreach>" +
                        "</when>" +
                        "<otherwise> 1 = 2 </otherwise>" +
                    "</choose>" +
            "</script>")
    void deleteAll(List<Long> addressIds);

}
