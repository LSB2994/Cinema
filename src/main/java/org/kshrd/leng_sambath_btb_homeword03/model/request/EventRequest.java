package org.kshrd.leng_sambath_btb_homeword03.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Attendees;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Venue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventRequest {
    private String eventName;
    private Timestamp eventDate;
    private Integer venueId;
    private List<Integer> attendees= new ArrayList<>();
}

