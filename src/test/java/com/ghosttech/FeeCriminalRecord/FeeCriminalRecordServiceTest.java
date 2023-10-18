package com.ghosttech.FeeCriminalRecord;

import com.ghosttech.dao.FeeCriminalRecordDao;
import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.exception.RequestValidationException;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.service.FeeCriminalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeeCriminalRecordServiceTest {

    @Mock
    private  FeeCriminalRecordDao feeCriminalRecordDao;

    @Mock
    private FeeCriminalRecordService underTest;



    @BeforeEach
    void setUp() {
        underTest = new FeeCriminalRecordService(feeCriminalRecordDao);
    }

    @Test
    void canAddFeeCriminalRecord() {
        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                UUID.randomUUID(),
                "édea",
                "Nyong et Mfoumou",
                8500
        );

        FeeCriminalRecordRequest feeCriminalRecordRequest = new FeeCriminalRecordRequest(
                feeCriminalRecord.getResidence(),
                feeCriminalRecord.getTribunal(),
                feeCriminalRecord.getFees()
        );


        // when
         underTest.saveFeeCriminalRecord(feeCriminalRecordRequest);

        // then
        ArgumentCaptor<FeeCriminalRecord> feeCriminalRecordArgumentCaptor = ArgumentCaptor.forClass(FeeCriminalRecord.class);

        verify(feeCriminalRecordDao).insertFeeCriminalRecord(feeCriminalRecordArgumentCaptor.capture());

        FeeCriminalRecord feeCriminalRecordArgumentCaptorValue = feeCriminalRecordArgumentCaptor.getValue();

        assertThat(feeCriminalRecordArgumentCaptorValue.getFees()).isEqualTo(feeCriminalRecord.getFees());
        assertThat(feeCriminalRecordArgumentCaptorValue.getResidence()).isEqualTo(feeCriminalRecord.getResidence());
        assertThat(feeCriminalRecordArgumentCaptorValue.getTribunal()).isEqualTo(feeCriminalRecord.getTribunal());

    }

    @Test
    void canUpdateFeeCriminalRecordWithoutAllParameters() {

        var id = UUID.randomUUID();

        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                id,
                "édea",
                "Nyong et Mfoumou",
                8500
        );

        FeeCriminalRecordRequest feeCriminalRecordRequest = new FeeCriminalRecordRequest(
                "Yaoundé",
                null,
                null
        );

        when(feeCriminalRecordDao.selectFeeCriminalRecordById(id)).thenReturn(Optional.of(feeCriminalRecord));

        // when
        underTest.updateFeeCriminalRecord(feeCriminalRecordRequest, id);

        // then

        ArgumentCaptor<FeeCriminalRecord> feeCriminalRecordArgumentCaptor = ArgumentCaptor.forClass(FeeCriminalRecord.class);

        verify(feeCriminalRecordDao).updateFeeCriminalRecord(feeCriminalRecordArgumentCaptor.capture());
        FeeCriminalRecord feeCriminalRecordArgumentCaptorValue = feeCriminalRecordArgumentCaptor.getValue();

        assertThat(feeCriminalRecordArgumentCaptorValue.getResidence()).isEqualTo(feeCriminalRecordRequest.getResidence());
        assertThat(feeCriminalRecordArgumentCaptorValue.getTribunal()).isEqualTo(feeCriminalRecord.getTribunal());
        assertThat(feeCriminalRecordArgumentCaptorValue.getFees()).isEqualTo(feeCriminalRecord.getFees());
}

    @Test
    void canUpdateFeeCriminalRecordWithAllParameters() {

        var id = UUID.randomUUID();

        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                id,
                "édea",
                "Nyong et Mfoumou",
                8500
        );

        FeeCriminalRecordRequest feeCriminalRecordRequest = new FeeCriminalRecordRequest(
                "Yaoundé",
                "Mfoundi",
                10000
        );

        when(feeCriminalRecordDao.selectFeeCriminalRecordById(id)).thenReturn(Optional.of(feeCriminalRecord));

        // when
        underTest.updateFeeCriminalRecord(feeCriminalRecordRequest, id);

        // then

        ArgumentCaptor<FeeCriminalRecord> feeCriminalRecordArgumentCaptor = ArgumentCaptor.forClass(FeeCriminalRecord.class);

        verify(feeCriminalRecordDao).updateFeeCriminalRecord(feeCriminalRecordArgumentCaptor.capture());
        FeeCriminalRecord feeCriminalRecordArgumentCaptorValue = feeCriminalRecordArgumentCaptor.getValue();

        assertThat(feeCriminalRecordArgumentCaptorValue.getResidence()).isEqualTo(feeCriminalRecordRequest.getResidence());
        assertThat(feeCriminalRecordArgumentCaptorValue.getTribunal()).isEqualTo(feeCriminalRecord.getTribunal());
        assertThat(feeCriminalRecordArgumentCaptorValue.getFees()).isEqualTo(feeCriminalRecord.getFees());
    }


    @Test
    void canGetFeeCriminalRecord(){
        // given
        UUID id = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                id,
                "édea",
                "Nyong et Mfoumou",
                8500
        );

        when(feeCriminalRecordDao.selectFeeCriminalRecordById(id)).thenReturn(Optional.of(feeCriminalRecord));

        // when
        FeeCriminalRecord feeCriminalRecordOptional = underTest.getFeeCriminalRecordById(id);

        // then
        assertThat(feeCriminalRecordOptional).isEqualTo(feeCriminalRecord);
    }

    @Test
    void canGetAllFeeCriminalRecord(){

        // when
         underTest.getListFeeCriminalRecordByCityAndTribunal(",","");

        // then
       verify(feeCriminalRecordDao).selectFeeCriminalRecordByCityAndTribunal(",","");
    }

    @Test
    void willThrownWhenUpdatesHasNoChanges(){
        var id = UUID.randomUUID();


        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                id,
                "édea",
                "Nyong et Mfoumou",
                8500
        );

        FeeCriminalRecordRequest feeCriminalRecordRequest = new FeeCriminalRecordRequest(
                feeCriminalRecord.getResidence(),
                feeCriminalRecord.getTribunal(),
                feeCriminalRecord.getFees());

        when(feeCriminalRecordDao.selectFeeCriminalRecordById(id)).thenReturn(Optional.of(feeCriminalRecord));

        // when
        assertThatThrownBy(() -> underTest.updateFeeCriminalRecord(feeCriminalRecordRequest,id))
                .isInstanceOf(RequestValidationException.class)
                .hasMessageContaining("No changes were made");

        // then
        verify(feeCriminalRecordDao, never()).updateFeeCriminalRecord(any());
    }
}

