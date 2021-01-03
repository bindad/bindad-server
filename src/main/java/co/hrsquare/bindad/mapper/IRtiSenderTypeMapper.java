package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.payroll.RtiSenderType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IRtiSenderTypeMapper {


    @Select("select id, name, is_deleted, updated_by, updated_time " +
            "from tbl_rti_sender_type " +
            "where name = #{name}")
    @Results({
            @Result(column = "is_deleted", property = "deleted"),
            @Result(column = "updated_by", property = "updatedBy"),
            @Result(column = "updated_time", property = "updatedTime")
    })
    RtiSenderType findByName(String name);
}
