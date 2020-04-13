package in.indilabz.vedican_parent.extras;

import in.indilabz.vedican_parent.response.OtpResponse;

public interface LoginInterface {

    void showDialog();

    void dismissDialog(String error, OtpResponse otpResponse);

}
