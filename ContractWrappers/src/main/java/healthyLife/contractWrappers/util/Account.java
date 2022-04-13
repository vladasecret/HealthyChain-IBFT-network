package healthyLife.contractWrappers.util;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.utils.Numeric;

import java.io.IOException;

public class Account {
    public final Credentials credentials;
    private long chainId;
    public Account(String privateKey, Web3j web3j) throws IOException {
        credentials = Credentials.create(privateKey);
        chainId = web3j.ethChainId().send().getChainId().longValue();
    }

    public String getAddress(){
        return credentials.getAddress();
    }

    public String signTransaction(RawTransaction transaction){
        byte[] signedMessage = TransactionEncoder.signMessage(transaction, chainId, credentials);
        return Numeric.toHexString(signedMessage);
    }
}
