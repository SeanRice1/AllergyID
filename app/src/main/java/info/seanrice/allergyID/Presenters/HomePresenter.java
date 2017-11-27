package info.seanrice.allergyID.Presenters;

import info.seanrice.allergyID.Data.LocalDataI;
import info.seanrice.allergyID.Views.HomeView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sean Rice on 11/24/2017.
 */

public class HomePresenter {

    private LocalDataI prefs;
    private HomeView view;
    private final String SPLASH_PREF_NAME = "info.seanrice.allergyID.Views.SplashScreen";
    private final String SPLASH_KEY = "acceptedDisclaimer";

    public HomePresenter(HomeView currView, LocalDataI localData){
        prefs = localData;
        view = currView;
    }

    public void scannerButton(){
        if(view.checkCamera()){
            if(view.checkInternet()){
                view.toScanner();
            }
            else
                view.alertNoInternet();
        }
        else view.alertNoInternet();
    }

    public void manualSearchButton(){
        if(view.checkInternet()){
            view.toMSearch();
        }
        else
            view.cameraComplain();
    }

    /* Starts the splashScreen view if its the first time opening the app */
    public void checkSplash(){
        if(!prefs.checkForVal(SPLASH_PREF_NAME, MODE_PRIVATE, SPLASH_KEY))
            view.toSplash();
    }
}
