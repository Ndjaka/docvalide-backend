package com.ghosttech.FeeCriminalRecord;

import com.ghosttech.AbstractTestcontainers;
import com.ghosttech.dataAccess.FeeCriminalRecordAccess;
import com.ghosttech.mapper.rowMapper.FeeCriminalRecordMapper;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.model.PaginationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FeeCriminalRecordAccessTest  extends AbstractTestcontainers {

    private FeeCriminalRecordAccess underTest;
    private final FeeCriminalRecordMapper feeCriminalRecordMapper = new FeeCriminalRecordMapper();

    @BeforeEach
    void setUp() {
        underTest = new FeeCriminalRecordAccess(getJdbcTemplate(), feeCriminalRecordMapper);
    }


    @Test
    void itShouldSelectAllFeeCriminalRecord() {
        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                java.util.UUID.randomUUID(),
                "édea",
                "Nyong et Mfoumou",
                8500,
                false
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        // when
        PaginationResult<FeeCriminalRecord> feeCriminalRecords = underTest.selectFeeCriminalRecordByCityAndTribunalWithPagination("édea","Nyong et Mfoumou", 10, 1);

        // then
        assertThat(feeCriminalRecords.getResults()).isNotEmpty();
    }

    @Test
    void itShouldSelectFeeCriminalRecordByCityAndTribunal() {
        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                java.util.UUID.randomUUID(),
                "édea",
                "Nyong et Mfoumou",
                8500, false
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        // when
        PaginationResult<FeeCriminalRecord> feeCriminalRecords = underTest.selectFeeCriminalRecordByCityAndTribunalWithPagination("édea","Nyong et Mfoumou", 10, 1);

        FeeCriminalRecord feeCriminalRecord1 =  feeCriminalRecords.getResults().stream()
                .filter(feeCriminalRecord2 -> feeCriminalRecord2.getId().equals(feeCriminalRecord.getId())).findFirst()
                .stream().findFirst().orElse(null);


        // then
        assert feeCriminalRecord1 != null;
        assertThat(feeCriminalRecord1.getResidence()).isEqualTo(feeCriminalRecord.getResidence());
    }

    @Test
    void itShouldSaveFeeCriminalRecord() {
        // given
        UUID randomUUID = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                randomUUID,
                "édea",
                "Nyong et Mfoumou",
                8500,
                false
        );

        // when
          underTest.insertFeeCriminalRecord(feeCriminalRecord);

        // then
        Optional<FeeCriminalRecord> actual =  underTest.selectFeeCriminalRecordById(randomUUID);

        assertThat(actual).isPresent().hasValueSatisfying(feeCriminalRecord1 -> {
            assertThat(feeCriminalRecord1.getId()).isEqualTo(randomUUID);
            assertThat(feeCriminalRecord1.getResidence()).isEqualTo(feeCriminalRecord.getResidence());
            assertThat(feeCriminalRecord1.getTribunal()).isEqualTo(feeCriminalRecord.getTribunal());
            assertThat(feeCriminalRecord1.getFees()).isEqualTo(feeCriminalRecord.getFees());
        });
    }

    @Test
    void itShouldUpdateFeeCriminalRecord() {
        // given
        UUID randomUUID = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                randomUUID,
                "édea",
                "Nyong et Mfoumou",
                8500,
                false
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        UUID id = underTest.selectFeeCriminalRecordByCityAndTribunalWithPagination("édea","Nyong et Mfoumou", 10, 1)
                .getResults()
                .stream()
                .map(FeeCriminalRecord::getId)
                .filter(feeCriminalRecord1IdId -> feeCriminalRecord1IdId.equals(randomUUID))
                .findFirst()
                .orElseThrow();

        // when
        FeeCriminalRecord update = new FeeCriminalRecord();

        update.setId(id);
        update.setResidence("Yaoundé");
        underTest.updateFeeCriminalRecord(feeCriminalRecord);


        // then

        Optional<FeeCriminalRecord> actual =  underTest.selectFeeCriminalRecordById(id);

        assertThat(actual).isPresent().hasValueSatisfying(feeCriminalRecord1 -> {
            assertThat(feeCriminalRecord1.getId()).isEqualTo(id);
            assertThat(feeCriminalRecord1.getResidence()).isEqualTo(feeCriminalRecord.getResidence());
            assertThat(feeCriminalRecord1.getTribunal()).isEqualTo(feeCriminalRecord.getTribunal());
            assertThat(feeCriminalRecord1.getFees()).isEqualTo(feeCriminalRecord.getFees());
        });
    }

    @Test
    void itShouldSelectFeeCriminalRecordById() {
        // given
        UUID randomUUID = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                randomUUID,
                "édea",
                "Nyong et Mfoumou",
                8500,
                false
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        UUID id = underTest
                .selectFeeCriminalRecordByCityAndTribunalWithPagination("édea","Nyong et Mfoumou", 10, 1)
                .getResults()
                .stream()
                .map(FeeCriminalRecord::getId)
                .filter(feeCriminalRecord1IdId -> feeCriminalRecord1IdId.equals(randomUUID))
                .findFirst()
                .orElseThrow();

        // when
        Optional<FeeCriminalRecord> actual =  underTest.selectFeeCriminalRecordById(id);

        // then
        assertThat(actual).isPresent().hasValueSatisfying(feeCriminalRecord1 -> {
            assertThat(feeCriminalRecord1.getId()).isEqualTo(id);
            assertThat(feeCriminalRecord1.getResidence()).isEqualTo(feeCriminalRecord.getResidence());
            assertThat(feeCriminalRecord1.getTribunal()).isEqualTo(feeCriminalRecord.getTribunal());
            assertThat(feeCriminalRecord1.getFees()).isEqualTo(feeCriminalRecord.getFees());
        });
    }

    @Test
    void itShouldUpdateFeeCriminalRecordStatus(){
        //given
        UUID randomUUID = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                randomUUID,
                "édea",
                "Nyong et Mfoumou",
                8500,
                true
        );
        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        //when
        underTest.updateFeeCriminalRecordStatus(!feeCriminalRecord.getStatus(),feeCriminalRecord.getId());

        // then

        Optional<FeeCriminalRecord> actual =  underTest.selectFeeCriminalRecordById(randomUUID);

        assertThat(actual).isPresent().hasValueSatisfying(feeCriminalRecord1 -> {
            assertThat(feeCriminalRecord1.getId()).isEqualTo(randomUUID);
            assertThat(feeCriminalRecord1.getStatus()).isFalse();
        });

    }
}


