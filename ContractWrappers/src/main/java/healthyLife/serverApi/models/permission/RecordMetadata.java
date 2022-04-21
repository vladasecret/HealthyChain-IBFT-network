package healthyLife.serverApi.models.permission;

import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Bytes32;

public class RecordMetadata extends StaticStruct {

    /**
     * Зашифрованный симметричный ключ. Длина массива 32!
     */
    public byte[] smk;

    /**
     * Зашифрованная IPFS-ссылка на файл. Длина массива 32!
     */
    public byte[] encodedHash;

    public RecordMetadata(byte[] smk, byte[] encodedHash) {
        super(new org.web3j.abi.datatypes.generated.Bytes32(smk),
                new org.web3j.abi.datatypes.generated.Bytes32(encodedHash));
        this.smk = smk;
        this.encodedHash = encodedHash;
    }

    public RecordMetadata(Bytes32 smk, Bytes32 encodedHash) {
        super(smk, encodedHash);
        this.smk = smk.getValue();
        this.encodedHash = encodedHash.getValue();
    }
}

