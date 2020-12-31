package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.auth.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface IUserMapper {

    @Insert("<script>" +
                "insert into tbl_user (client_id, company_id, employee_id, username, password, authorities, is_deleted, updated_by, updated_time) " +
                "select " +
                        "<choose> " +
                            "<when test=\"client != null\"> #{client.id}, </when>" +
                            "<otherwise>null, </otherwise>" +
                        "</choose>" +
                        "<choose> " +
                            "<when test=\"company != null\"> #{company.id}, </when>" +
                            "<otherwise>null, </otherwise>" +
                        "</choose>" +
                        "<choose> " +
                            "<when test=\"employee != null\"> #{employee.id}, </when>" +
                            "<otherwise>null, </otherwise>" +
                        "</choose>" +
                        "#{username}, #{password}, #{authorities}, 0, #{updatedBy}, #{updatedTime}" +
            "</script>")
    void insert(User user);

    @Select("select id, client_id, company_id, employee_id, username, password, authorities from tbl_user " +
            "where username=#{username} ")
    @Results({
            @Result(property = "client_id", column = "client.id"),
            @Result(property = "company_id", column = "company.id"),
            @Result(property = "employee_id", column = "employee.id")
    })
    User findByUsername(String username);

    @Select("select id from tbl_user " +
            "where username=#{username} ")
    Long findId(User user);

    @Update("update tbl_user " +
            "set password=#{password} " +
            "where username=#{username} " +
            "and is_deleted = 0")
    void resetPassword(String username, String password);

    @Update("update tbl_user " +
            "set username = concat(username, '-DELETED'), is_deleted = 1, updated_by=#{updatedBy}, updated_time=#{updatedTime} " +
            "where username=#{username}")
    void markDeleted(String username, long updatedBy, LocalDateTime updatedTime);

    @Delete("delete from tbl_user where username=#{username}")
    void deleteByUsername(String username);

    @Delete("<script>" +
                "delete from tbl_user " +
                "where client_id = #{clientId};" +
            "</script>")
    void deleteByClientId(Long clientId);

}
