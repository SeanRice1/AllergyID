package info.seanrice.allergyID.Data;

import java.io.IOException;

import info.seanrice.allergyID.Data.USDAndbnoPOJO.USDAndbno;
import info.seanrice.allergyID.Data.USDApojo.USDApojoFull;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteData implements RemoteDataI{
    private static final String USDA_API_KEY = "KsmlZ6KwmxBEDh6NZIECs4oSmVWpCbRtqa1lxFnK";
    private static final String USDA_URL = "https://api.nal.usda.gov";
    private static USDAapi usdAapi;

    public RemoteData(){
        Retrofit retrofitUSDA = new Retrofit.Builder()
                .baseUrl(USDA_API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usdAapi = retrofitUSDA.create(USDAapi.class);
    }

    //TODO: For the time being, these make the GET request synchronously
    @Override
    public USDApojoFull getUSDApojoFull(String ndbno) throws IOException {
        Call<USDApojoFull> usdApojoFullCall = usdAapi.getUSDApojoFull(ndbno, USDA_API_KEY);
        return usdApojoFullCall.execute().body();
    }

    @Override
    public USDAndbno getUSDAndbno(String upc) throws IOException {
        Call<USDAndbno> usdAndbnoCall = usdAapi.getUSDAndbnoPOJO(upc, USDA_API_KEY);
        return usdAndbnoCall.execute().body();
    }


}
