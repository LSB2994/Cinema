package org.kshrd.leng_sambath_btb_homeword03.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttendeesRequest {
    private String attendeeName;
    private String email;
}
