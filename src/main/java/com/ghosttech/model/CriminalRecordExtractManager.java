package com.ghosttech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CriminalRecordExtractManager {

    private CriminalRecordExtract criminalRecordExtract;
    private User user;
    private Orders orders;
}
