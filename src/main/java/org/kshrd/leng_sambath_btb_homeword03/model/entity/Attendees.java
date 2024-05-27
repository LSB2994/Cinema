package org.kshrd.leng_sambath_btb_homeword03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Attendees {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
