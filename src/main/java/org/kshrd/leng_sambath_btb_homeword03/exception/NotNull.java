package org.kshrd.leng_sambath_btb_homeword03.exception;

public class NotNull extends RuntimeException{

    public NotNull(Integer venueId){
        super("must be greater than 0");
    }
}
