package healthyLife.contractWrappers.util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
//import org.apache.commons.io.IOUtils;

public class BinReader {
    public static final String CONFIG_PATH = "src/main/resources/config.properties";
    public final String BIN_PATH;


    public BinReader() {
        BIN_PATH = loadBinPath();
    }

    private String loadBinPath(){
        String res = null;
        try {
            FileInputStream fip = new FileInputStream(CONFIG_PATH);
            Properties props = new Properties();
            props.load(fip);
            res = props.getProperty("bin.path");
            fip.close();
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return res;
    }

    public String read(String contractName) throws IOException {
        String filePath = String.format("%s%s.bin", BIN_PATH, contractName);
        FileInputStream fip = new FileInputStream(filePath);
        String res = IOUtils.toString(fip, StandardCharsets.UTF_8);
        return res;
    }
}
