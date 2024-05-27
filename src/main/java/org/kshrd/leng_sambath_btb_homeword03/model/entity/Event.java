package org.kshrd.leng_sambath_btb_homeword03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event {
    private Integer eventId;
    private String eventName;
    private Timestamp eventDate;
    private Venue venue;
    private List<Attendees> attendees= new ArrayList<>();
}
