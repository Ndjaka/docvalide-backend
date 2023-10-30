package com.ghosttech.FeeCriminalRecord;

import com.ghosttech.AbstractTestcontainers;
import com.ghosttech.dataAccess.FeeCriminalRecordAccess;
import com.ghosttech.mapper.rowMapper.FeeCriminalRecordMapper;
import com.ghosttech.model.FeeCriminalRecord;
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
                8500
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        // when
        List<FeeCriminalRecord> feeCriminalRecords = underTest.selectFeeCriminalRecordByCityAndTribunal("","");

        // then
        assertThat(feeCriminalRecords).isNotEmpty();
    }

    @Test
    void itShouldSelectFeeCriminalRecordByCityAndTribunal() {
        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                java.util.UUID.randomUUID(),
                "édea",
                "Nyong et Mfoumou",
                8500
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        // when
        List<FeeCriminalRecord> feeCriminalRecords = underTest.selectFeeCriminalRecordByCityAndTribunal("édea","Nyong et Mfoumou");

        FeeCriminalRecord feeCriminalRecord1 =  feeCriminalRecords.stream()
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
                8500
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
                8500
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        UUID id = underTest.selectFeeCriminalRecordByCityAndTribunal("","")
                .stream()
                .filter(feeCriminalRecord1Id -> feeCriminalRecord1Id.getId().equals(randomUUID))
                .map(FeeCriminalRecord::getId)
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
                8500
        );

        underTest.insertFeeCriminalRecord(feeCriminalRecord);

        UUID id = underTest.selectFeeCriminalRecordByCityAndTribunal("","")
                .stream()
                .filter(feeCriminalRecord1Id -> feeCriminalRecord1Id.getId().equals(randomUUID))
                .map(FeeCriminalRecord::getId)
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
}


