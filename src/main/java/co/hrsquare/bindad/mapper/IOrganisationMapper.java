package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.organisation.Organisation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IOrganisationMapper {

    @Insert("insert into tbl_organisation (client_id, public_id, full_name, short_name, is_deleted, updated_by, updated_time) " +
            "select " +
            "#{client.id}, " +
            "#{publicId}, " +
            "#{fullName}, " +
            "#{shortName}, " +
            "#{deleted}, " +
            "#{updatedBy}, " +
            "#{updatedTime}")
    void insert(Organisation organisation);

    @Select("select id from tbl_organisation where full_name=#{fullName}")
    Long findId(Organisation organisation);

    @Select("select id, client_id, public_id, full_name, short_name, is_deleted, updated_by, updated_time " +
            "from tbl_organisation " +
            "where full_name=#{name} " +
            "and is_deleted = 0")
    @Results({
            @Result(column = "client_id", property = "client.id"),
            @Result(column = "public_id", property = "publicId"),
            @Result(column = "full_name", property = "fullName"),
            @Result(column = "short_name", property = "shortName"),
            @Result(column = "is_deleted", property = "deleted"),
            @Result(column = "updated_by", property = "updatedBy"),
            @Result(column = "updated_time", property = "updatedTime")
    })
    Organisation findByFullName(String name);

    @Delete("<script>" +
                "delete from tbl_organisation " +
                "where client_id = #{clientId}; " +
            "</script>")
    void deleteByClientId(Long clientId);

    @Select("select id, client_id, public_id, full_name, short_name, is_deleted, updated_by, updated_time " +
            "from tbl_organisation " +
            "where client_id=#{clientId} " +
            "and is_deleted = 0")
    @Results({
            @Result(property = "client.id", column = "client_id"),
            @Result(property = "publicId", column = "public_id"),
            @Result(property = "shortName", column = "short_name"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "deleted", column = "is_deleted"),
            @Result(property = "updatedBy", column = "updated_by"),
            @Result(property = "updatedTime", column = "updated_time"),
    })
    Organisation findByClientId(long clientId);
}
