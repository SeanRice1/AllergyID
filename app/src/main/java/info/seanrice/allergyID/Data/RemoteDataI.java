package info.seanrice.allergyID.Data;

import java.io.IOException;

import info.seanrice.allergyID.Data.USDApojo.USDApojoFull;
import info.seanrice.allergyID.Data.USDAndbnoPOJO.USDAndbno;

public interface RemoteDataI {

    USDApojoFull getUSDApojoFull(String ndbno) throws IOException;

    USDAndbno getUSDAndbno(String upc) throws IOException;

}
