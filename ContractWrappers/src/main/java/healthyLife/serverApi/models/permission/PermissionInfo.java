package healthyLife.serverApi.models.permission;

import healthyLife.contractWrappers.generated.UserContract;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint8;

import java.math.BigInteger;

//public class PermissionInfo {
//
//    /**
//     *Адрес пользователя, которому предоставлен доступ к записи
//     */
//    public String user;
//
//    /**
//     * Уровень доступа
//     * @see PermissionLevel
//     */
//    public PermissionLevel level;
//
//    /**
//     * Зашифрованные метаданные
//     * @see RecordMetadata
//     */
//    public RecordMetadata metadata;
//
//    /**
//     * Адрес пользователя, который предоставил доступ. Для владельца это адрес создателя записи
//     */
//    public String permitter;
//
//    public PermissionInfo(String user, BigInteger level, RecordMetadata metadata, String permitter) {
//        this(user, PermissionLevel.convert(level), metadata, permitter);
//    }
//
//    public PermissionInfo(String user, PermissionLevel level, RecordMetadata metadata, String permitter) {
//        this.user = user;
//        this.level = level;
//        this.metadata = metadata;
//        this.permitter = permitter;
//    }
//}

public class PermissionInfo extends StaticStruct {
    public String user;

    public BigInteger level;

    public RecordMetadata metadata;

    public String permitter;

    public PermissionInfo(String user, BigInteger level, RecordMetadata metadata, String permitter) {
        super(new org.web3j.abi.datatypes.Address(160, user),
                new org.web3j.abi.datatypes.generated.Uint8(level),
                metadata,
                new org.web3j.abi.datatypes.Address(160, permitter));
        this.user = user;
        this.level = level;
        this.metadata = metadata;
        this.permitter = permitter;
    }

    public PermissionInfo(Address user, Uint8 level, RecordMetadata metadata, Address permitter) {
        super(user, level, metadata, permitter);
        this.user = user.getValue();
        this.level = level.getValue();
        this.metadata = metadata;
        this.permitter = permitter.getValue();
    }


}
