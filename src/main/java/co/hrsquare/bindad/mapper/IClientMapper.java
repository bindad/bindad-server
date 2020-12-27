package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.client.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IClientMapper {

    @Select("select id from tbl_client where email=#{clientContactDetails.email}")
    Long findId(Client client);

    @Insert("insert into tbl_client (" +
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
            "#{clientNameDetails.title}, " +
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

}
