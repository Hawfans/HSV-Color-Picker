// Author: David Medhurst September, 24th 2016

package mad9132.rgba;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Create an AboutDialogFragment displaying your full name and username.
 *
 * Reference:
 * Android Dialogs @ http://developer.android.com/guide/topics/ui/dialogs.html
 *
 * @author David.Medhurst@AlgonquinCollege.com
 */

public class AboutDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.action_about)
                .setMessage("David Medhurst (medh0003)")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
