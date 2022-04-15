package Util;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import healthyLife.serverApi.util.Account;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Supplier;

public class BlockchainParameterResolver implements ParameterResolver {

    static final String privateKey = "c82aa158c161b6c4fe7c9658a09a245ed069ec79ec30dfbbb8bd199c3f1e2d45";
    static final String URL = "http://localhost:7545/";

    HashMap<Class, Supplier> resolvedClasses = new HashMap<>();

    public BlockchainParameterResolver(){
        resolvedClasses.put(Web3j.class, () -> Web3j.build(new HttpService(URL)));
        resolvedClasses.put(Account.class, () -> {
            try {
                return new Account(privateKey, (Web3j)resolvedClasses.get(Web3j.class).get());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return resolvedClasses.containsKey(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (!supportsParameter(parameterContext, extensionContext))
            throw new ParameterResolutionException("Parameter not found");
        Class type = parameterContext.getParameter().getType();
        return type.cast(resolvedClasses.get(type).get());
    }
}
