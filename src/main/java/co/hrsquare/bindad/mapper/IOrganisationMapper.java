package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.organisation.Organisation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

}
