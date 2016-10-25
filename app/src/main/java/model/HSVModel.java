// Author: David Medhurst September, 24th 2016

package model;

import java.util.Observable;

import android.graphics.Color;

/**
 * The model holds the data.
 *
 * Model color.
 * Based on red, green, blue and alpha (transparency).
 *
 * HSV: float values in the domain range of 0 to 359 / 0 to 100 (inclusive).
 *
 * @author David.Medhurst@AlgonquinCollege.com
 * @version 1.0
 */
public class HSVModel extends Observable {

    // CLASS VARIABLES
    public static final float MAX_HUE = 359f;
    public static final float MAX_SATURATION   = 1.0f;
    public static final float MAX_VALUE   = 1.0f;
    public static final float MIN_HUE = 0;
    public static final float MIN_SATURATION   = 0;
    public static final float MIN_VALUE   = 0;

    // INSTANCE VARIABLES
    private float value;
    private float saturation;
    private float hue;

    /**
     * No argument constructor.
     *
     * Instantiate a new instance of this class, and
     * initialize hue, saturation, lightness, values.
     */
    public HSVModel() {
        this( MAX_HUE, MAX_SATURATION, MAX_VALUE );
    }

    /**
     * Convenience constructor.
     *
     * @param hue - starting hue value
     * @param saturation - starting saturation value
     * @param value - starting lightness value

     */
    public HSVModel( float hue, float saturation, float value ) {
        super();

        this.value  = value;
        this.saturation = saturation;
        this.hue   = hue;
    }

    public void asBlack()   { this.setHSV( 0f, 0.0f, 0.0f ); }
    public void asRed()     { this.setHSV( 0f, 100.0f, 100.0f); }
    public void asLime()    { this.setHSV( 120f, 100.0f, 100.0f ); }
    public void asBlue()    { this.setHSV( 240f, 100.0f, 100.0f ); }
    public void asYellow()  { this.setHSV( 60f, 100.0f, 100.0f ); }
    public void asCyan()    { this.setHSV( 180f, 100.0f, 100.0f ); }
    public void asMagenta() { this.setHSV( 300f, 100.0f, 100.0f ); }
    public void asSilver()  { this.setHSV( 0f, 0.0f, 75.3f ); }
    public void asGray()    { this.setHSV( 0f, 0.0f, 50.2f ); }
    public void asMaroon()  { this.setHSV( 0f, 100.0f, 50.2f ); }
    public void asOlive()   { this.setHSV( 60f, 100.0f, 50.2f ); }
    public void asGreen()   { this.setHSV( 120f, 100.0f, 50.2f ); }
    public void asPurple()  { this.setHSV( 300f, 100.0f, 50.2f ); }
    public void asTeal()    { this.setHSV( 180f, 100.0f, 50.2f ); }
    public void asNavy()    { this.setHSV( 240f, 100.0f, 50.2f ); }




    // GETTERS
    public float getValue()  { return value; }
    public float getSaturation() { return saturation; }
    public float getHue()   { return hue; }

    // SETTERS
    public void setValue(float value) {
        this.value = value;

        this.updateObservers();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;

        this.updateObservers();
    }

    public void setHue(float hue) {
        this.hue = hue;

        this.updateObservers();
    }

    // Convenient setter: set H, S, V
    public void setHSV( float hue, float saturation, float value ) {
        this.hue   = hue;
        this.saturation = saturation;
        this.value  = value;

        this.updateObservers();
    }

    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "HSV [h=" + hue + " s=" + saturation + " v=" + value + "]";
    }

    // Proof that my model is independent of any view.
    public static void main( String[] args ) {
        HSVModel model = new HSVModel( 0, 0, 0 );

        System.out.println( model );
    }
}