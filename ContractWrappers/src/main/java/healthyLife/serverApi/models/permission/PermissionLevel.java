package healthyLife.serverApi.models.permission;

import java.math.BigInteger;

public enum PermissionLevel{
    READ,
    SHARE,
    OWNER;


    public static PermissionLevel convert(BigInteger val){
        return convert(val.intValue());
    }

    public static PermissionLevel convert(int val){
        return PermissionLevel.values()[val];
    }

    public BigInteger toBigInteger(){
        return BigInteger.valueOf(this.ordinal());
    }


}
