package info.seanrice.allergyID.Views;

import android.content.Context;

/**
 * Created by Sean Rice on 11/25/2017.
 */

public interface HomeView {
    void checkSplash();

    void scannerButton();

    void manualSearchButton();

    void requestCamera();

    void alertNoInternet();

    void cameraComplain();

    void toScanner();

    void toMSearch();

    void toSplash();

    boolean checkCamera();

    boolean checkInternet();
}
