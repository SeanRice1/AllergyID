package info.seanrice.allergyID.Data;

import info.seanrice.allergyID.Data.USDApojo.USDApojoFull;
import info.seanrice.allergyID.Data.USDAndbnoPOJO.USDAndbno;

public interface RemoteDataI {

    USDApojoFull getUSDApojoFull(int ndbno);

    USDAndbno getUSDAndbno(int upc);

}
