// Author: David Medhurst September, 24th 2016

package mad9132.rgba;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 * The Controller for HSVModel.
 * <p>
 * As the Controller:
 * a) event handler for the View
 * b) observer of the Model (HSVModel)
 * <p>
 * Features the Update / React Strategy.
 *
 * @author David.Medhurst@AlgonquinCollege.com
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements Observer
        , SeekBar.OnSeekBarChangeListener {
    // CLASS VARIABLES
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG = "HSV";
    private static final int    SATURATION;
    private static final int    HUE;
    private static final int    VALUE;

    // INSTANCE VARIABLES
    // Pro-tip: different naming style; the 'm' means 'member'
    private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private float[] mHSV = new float[] { 0.f, 0.f, 0.f };
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    static {
        HUE     = 0;
        SATURATION = 1;
        VALUE       = 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a new AboutDialogFragment()
        // but do not show it (yet)
        mAboutDialog = new AboutDialogFragment();

        // Instantiate a new RGBA model
        // Initialize the model red (max), green (min), blue (min), and alpha (max)
        mModel = new HSVModel();
        mModel.setHue(HSVModel.MIN_HUE);
        mModel.setSaturation(HSVModel.MIN_SATURATION);
        mModel.setValue(HSVModel.MIN_VALUE);
        // The Model is observing this Controller (class MainActivity implements Observer)
        mModel.addObserver(this);

        // reference each View
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);

        // set the domain (i.e. max) for each component
        mHueSB.setMax((int) HSVModel.MAX_HUE);
        mSaturationSB.setMax((int) HSVModel.MAX_SATURATION);
        mValueSB.setMax((int) HSVModel.MAX_VALUE);

        // register the event handler for each <SeekBar>
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);


        // initialize the View to the values of the Model
        this.updateView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_about:
                mAboutDialog.show(getFragmentManager(), ABOUT_DIALOG_TAG);
                return true;

        }
        return true;
    }

    public boolean change(View button) {
        // Handle presses on the action bar items
        switch (button.getId()) {

            case R.id.black:
                mModel.asBlack();
                return true;

            case R.id.red:
                mModel.asRed();
                return true;

            case R.id.lime:
                mModel.asLime();
                return true;

            case R.id.blue:
                mModel.asBlue();
                return true;

            case R.id.yellow:
                mModel.asYellow();
                return true;

            case R.id.cyan:
                mModel.asCyan();
                return true;

            case R.id.magenta:
                mModel.asMagenta();
                return true;

            case R.id.silver:
                mModel.asSilver();
                return true;

            case R.id.gray:
                mModel.asGray();
                return true;

            case R.id.maroon:
                mModel.asMaroon();
                return true;

            case R.id.olive:
                mModel.asOlive();
                return true;

            case R.id.green:
                mModel.asGreen();
                return true;

            case R.id.purple:
                mModel.asPurple();
                return true;

            case R.id.teal:
                mModel.asTeal();
                return true;

            case R.id.navy:
                mModel.asNavy();
                return true;

            default:
//                Toast.makeText(this, "Item: " + button.getId(), Toast.LENGTH_LONG).show();
                return true;
        }

    }

    /**
     * Event handler for the <SeekBar>s: hue, saturation, and value.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Did the user cause this event?
        // YES > continue
        // NO  > leave this method
        if (fromUser == false) {
            return;
        }


        // Determine which <SeekBark> caused the event (switch + case)
        // GET the SeekBar's progress, and SET the model to it's new value
        switch (seekBar.getId()) {
            case R.id.hueSB:
//                mHSV[HUE] = (float) progress;
                mModel.setHue(mHueSB.getProgress());
                break;


            case R.id.saturationSB:
                mModel.setSaturation(mSaturationSB.getProgress());
//                mHSV[SATURATION] = (float) (progress / 100);
//                mSaturationSB.setProgress((int) (mModel.getSaturation()));
                break;


            case R.id.valueSB:
                mModel.setValue((float)mValueSB.getProgress());
//                mHSV[VALUE] = (float) (progress / 100);
//                mValueSB.setProgress((int) (mModel.getValue()));
                break;

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }

    // The Model has changed state!
    // Refresh the View to display the current values of the Model.
    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateValueSB() {
        mValueSB.setProgress((int) mModel.getValue());
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(Color.HSVToColor(new float[]{mModel.getHue(), mModel.getSaturation(), mModel.getValue()}));
    }

    private void updateSaturationSB() {
        mSaturationSB.setProgress((int) mModel.getSaturation());
    }

    private void updateHueSB() {
        mHueSB.setProgress((int) mModel.getHue());
    }

    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
