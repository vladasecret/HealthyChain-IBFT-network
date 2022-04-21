package healthyLife.serverApi.wrappers.base;

public interface RawContractApi {
    public String getContractAddress();

    public String getSenderAddress();
    public void setSenderAddress(String newAddress);
}
