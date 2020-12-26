package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.auth.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface IUserMapper {

    @Insert("insert into tbl_user (username, password, authorities, is_deleted, updated_by, updated_time) " +
            "select #{username}, #{password}, #{authorities}, 0, #{updatedBy}, #{updatedTime}")
    void insert(User user);

    @Select("select id, username, password, authorities from tbl_user " +
            "where username=#{username} ")
    User findByUsername(String username);

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
}
