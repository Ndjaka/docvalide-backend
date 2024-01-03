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
class FeeCriminalRecordServiceTest {

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
                8500,
                false
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
                8500,
                false
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
                8500,
                false
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
                8500,
                false
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
         underTest.selectFeeCriminalRecordByCityAndTribunalWithPagination(",","",1,10,true);

        // then
       verify(feeCriminalRecordDao).selectFeeCriminalRecordByCityAndTribunalWithPagination(",","",1,10,true);
    }

    @Test
    void canGetAllFeeCriminalRecordWithOutLimit(){

        // when
        underTest.selectFeeCriminalRecordByCityAndTribunalWithPagination("édea","Nyong et Mfoumou",1,10,false);

        // then
        verify(feeCriminalRecordDao).selectFeeCriminalRecordByCityAndTribunalWithPagination("édea","Nyong et Mfoumou",1,10,false);
    }

    @Test
    void willThrownWhenUpdatesHasNoChanges(){
        var id = UUID.randomUUID();


        // given
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                id,
                "édea",
                "Nyong et Mfoumou",
                8500,
                false
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

    @Test
    void canUpdateFeeCriminalRecordStatus(){
       //given
        UUID randomUUID = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                randomUUID,
                "édea",
                "Nyong et Mfoumou",
                8500,
                false
        );

        //doNothing().when(feeCriminalRecordDao.insertFeeCriminalRecord(feeCriminalRecord));

        lenient().doNothing().when(feeCriminalRecordDao).insertFeeCriminalRecord(feeCriminalRecord);

        when(feeCriminalRecordDao.selectFeeCriminalRecordById(randomUUID)).thenReturn(Optional.of(feeCriminalRecord));

        //when
        FeeCriminalRecord feeCriminalRecordUpdated = underTest.updateFeeCriminalRecordStatus(!feeCriminalRecord.getStatus(),randomUUID);


        //then
        assertThat(feeCriminalRecordUpdated.getStatus()).isTrue();


    }

    @Test
    void willThrowWhenStatusHasNoChange(){
        //given
        UUID randomUUID = UUID.randomUUID();
        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                randomUUID,
                "édea",
                "Nyong et Mfoumou",
                8500,
                true
        );

        when(feeCriminalRecordDao.selectFeeCriminalRecordById(randomUUID)).thenReturn(Optional.of(feeCriminalRecord));


        // when
        assertThatThrownBy(() -> underTest.updateFeeCriminalRecordStatus(feeCriminalRecord.getStatus(),randomUUID))
                .isInstanceOf(RequestValidationException.class)
                .hasMessageContaining("No update were made");

        // then
        verify(feeCriminalRecordDao, never()).updateFeeCriminalRecordStatus(anyBoolean(),any());
    }
}

