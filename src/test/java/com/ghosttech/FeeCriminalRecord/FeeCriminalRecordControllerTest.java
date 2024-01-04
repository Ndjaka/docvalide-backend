package com.ghosttech.FeeCriminalRecord;

import com.ghosttech.AbstractTestWebLayers;
import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.service.FeeCriminalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class FeeCriminalRecordControllerTest extends AbstractTestWebLayers {

    @Mock
    private FeeCriminalRecordService underTest;

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void givenFeeCriminalRecord_whenPostFeeCriminalRecord_thenStatus201() throws Exception {
        // give
        FeeCriminalRecordRequest feeCriminalRecordRequest = new FeeCriminalRecordRequest(
                "édea",
                "Nyong et Mfoumou",
                "Littoral",
                8500
        );

        FeeCriminalRecord feeCriminalRecord = new FeeCriminalRecord(
                null,
                feeCriminalRecordRequest.getResidence(),
                feeCriminalRecordRequest.getTribunal(),
                feeCriminalRecordRequest.getRegion(),
                feeCriminalRecordRequest.getFees(),
                false
        );

        when(underTest.saveFeeCriminalRecord(feeCriminalRecordRequest))
                .thenReturn(feeCriminalRecord);

        // when
        // then
        super.mvc.perform(post("/api/v1/feeCriminalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(feeCriminalRecordRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.residence").value(feeCriminalRecordRequest.getResidence()))
                .andExpect(jsonPath("$.tribunal").value(feeCriminalRecordRequest.getTribunal()))
                .andExpect(jsonPath("$.fees").value(feeCriminalRecordRequest.getFees()));
    }


}