package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.payroll.PensionTaxationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IPensionTaxationTypeMapper {


    @Select("select id, name, is_deleted, updated_by, updated_time " +
            "from tbl_pension_taxation_type " +
            "where name = #{name}")
    @Results({
            @Result(column = "is_deleted", property = "deleted"),
            @Result(column = "updated_by", property = "updatedBy"),
            @Result(column = "updated_time", property = "updatedTime")
    })
    PensionTaxationType findByName(String name);
}
