package network;

import android.content.Context;

import com.wico.network.interfaces.ParseInitializer;

public class OfflineParseInitializer implements ParseInitializer {
    @Override
    public void initialize(Context context) {
        System.out.println("hello dagger");
    }
}
