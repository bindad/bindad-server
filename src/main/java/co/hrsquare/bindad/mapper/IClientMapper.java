package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.client.Client;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.time.LocalDateTime;

@Mapper
public interface IClientMapper {

    @Select("<script>" +
                "select id from tbl_client " +
                "<choose>" +
                    "<when test=\"clientContactDetails != null and clientContactDetails.email != null \"> where email=#{clientContactDetails.email} </when>" +
                    "<when test=\"publicId != null \"> where public_id=#{publicId} </when>" +
                    "<otherwise> where 1=2 </otherwise>" +
                "</choose>" +
            "</script>")
    Long findId(Client client);

    @Insert("insert into tbl_client (" +
            "public_id, " +
            "title, " +
            "first_name, " +
            "known_as, " +
            "middle_initial, " +
            "last_name, " +
            "email, " +
            "telephone, " +
            "telephone_ext, " +
            "mobile, " +
            "contract_type, " +
            "contract_status, " +
            "contract_start_date, " +
            "contract_end_date, " +
            "is_deleted, " +
            "updated_by, " +
            "updated_time) " +
            "select " +
            "#{publicId}, " +
            "#{clientNameDetails.title.name}, " +
            "#{clientNameDetails.firstName}, " +
            "#{clientNameDetails.knownAs}, " +
            "#{clientNameDetails.middleInitial}, " +
            "#{clientNameDetails.lastName}, " +
            "#{clientContactDetails.email}, " +
            "#{clientContactDetails.telephone}, " +
            "#{clientContactDetails.telephoneExt}, " +
            "#{clientContactDetails.mobile}, " +
            "#{clientContract.clientContractType.name}, " +
            "#{clientContract.contractStatus.name}, " +
            "#{clientContract.contractStartDate}, " +
            "#{clientContract.contractEndDate}, " +
            "#{deleted}, " +
            "#{updatedBy}, " +
            "#{updatedTime}")
    void insert(Client client);

    @Update("update tbl_client " +
            "set is_deleted=1, updated_by=#{updatedBy}, updated_time=#{updatedTime} " +
            "where id=#{id}")
    void markDeleted(Long id, Long updatedBy, LocalDateTime updatedTime);

    @Delete("<script>" +
                "delete from tbl_client " +
                "where id = #{clientId}; " +
            "</script>")
    void deleteById(Long clientId);

    @Select("<script>" +
            "select " +
                "id, " +
                "public_id, " +
                "title, " +
                "first_name, " +
                "known_as, " +
                "middle_initial, " +
                "last_name, " +
                "email, " +
                "telephone, " +
                "telephone_ext, " +
                "mobile, " +
                "contract_type, " +
                "contract_status, " +
                "contract_start_date, " +
                "contract_end_date, " +
                "is_deleted, " +
                "updated_by, " +
                "updated_time " +
            "from tbl_client " +
            "<choose>" +
                "<when test=\"clientContactDetails != null and clientContactDetails.email != null \"> where email=#{clientContactDetails.email} </when>" +
                "<when test=\"publicId != null \"> where public_id=#{publicId} </when>" +
                "<otherwise> where 1=2 </otherwise>" +
            "</choose>" +
            "</script>")
    @Results({
            @Result(property = "publicId", column = "public_id"),
            @Result(property = "clientNameDetails.title", column = "title", typeHandler = EnumTypeHandler.class),
            @Result(property = "clientNameDetails.firstName", column = "first_name"),
            @Result(property = "clientNameDetails.knownAs", column = "known_as"),
            @Result(property = "clientNameDetails.middleInitial", column = "middle_initial"),
            @Result(property = "clientNameDetails.lastName", column = "last_name"),
            @Result(property = "clientContactDetails.email", column = "email"),
            @Result(property = "clientContactDetails.telephone", column = "telephone"),
            @Result(property = "clientContactDetails.telephoneExt", column = "telephone_ext"),
            @Result(property = "clientContract.clientContractType", column = "contract_type", typeHandler = EnumTypeHandler.class),
            @Result(property = "clientContract.contractStatus", column = "contract_status", typeHandler = EnumTypeHandler.class),
            @Result(property = "clientContract.contractStartDate", column = "contract_start_date"),
            @Result(property = "clientContract.contractEndDate", column = "contract_end_date"),
            @Result(property = "deleted", column = "is_deleted"),
            @Result(property = "updatedBy", column = "updated_by"),
            @Result(property = "updatedTime", column = "updated_time")
    })
    Client findBy(Client client);
}
