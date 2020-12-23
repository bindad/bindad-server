package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.TestModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ITestMapper {

    @Select("select id, name from tbl_test_model where id = #{id}")
    TestModel findById(String id);

}
