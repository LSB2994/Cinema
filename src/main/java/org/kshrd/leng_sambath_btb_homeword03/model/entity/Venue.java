package org.kshrd.leng_sambath_btb_homeword03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Venue {
    private Integer venue_id;
    private String venue_name;
    private String location;
}
