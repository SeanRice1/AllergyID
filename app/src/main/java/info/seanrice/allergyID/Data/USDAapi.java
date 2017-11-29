package info.seanrice.allergyID.Data;

import info.seanrice.allergyID.Data.USDAndbnoPOJO.USDAndbno;
import info.seanrice.allergyID.Data.USDApojo.USDApojoFull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface USDAapi {

    @GET ("/ndb/search/?format=json&q={upc}&sort=n&max=25&offset=0&api_key={key}")
    Call<USDAndbno> getUSDAndbnoPOJO(
            @Path ("upc") String upc,
            @Path ("key") String key);

    @GET ("/ndb/reports/?ndbno={ndbno}&type=f&format=json&api_key={key}")
    Call<USDApojoFull> getUSDApojoFull(
            @Path ("ndbno") String ndbno,
            @Path ("key") String key);
}
