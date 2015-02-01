package yapiti.watcha.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by yapiti on 01/02/15.
 */
public class BaseFragment extends Fragment {
    protected SpiceManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager=new SpiceManager(Jackson2SpringAndroidSpiceService.class);
    }

    @Override
    public void onStart() {
        super.onStart();

        manager.start(getActivity());
    }

    @Override
    public void onStop() {
        manager.shouldStop();

        super.onStop();
    }
}
