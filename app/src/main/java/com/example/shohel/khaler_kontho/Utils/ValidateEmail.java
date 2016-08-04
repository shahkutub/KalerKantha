package com.example.shohel.khaler_kontho.Utils;

public class ValidateEmail {

	public final static boolean validateEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

}
