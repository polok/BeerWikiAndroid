package com.polak.beer.activity;

import roboguice.inject.ContentView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.polak.beer.R;
import com.polak.beer.activity.robosherlock.RoboSherlockActivity;

@ContentView(R.layout.a_about)
public class AboutActivity extends RoboSherlockActivity {

	public static final String MESSAGE_RFC822 = "message/rfc822";

	public void onSendFeedbackClicked(View view) {

		Intent intentSendingEmail = new Intent(Intent.ACTION_SEND);

		intentSendingEmail.setType(MESSAGE_RFC822);
		intentSendingEmail.putExtra(Intent.EXTRA_EMAIL, new String[] { getString(R.string.feedback_email_address) });
		intentSendingEmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_email_subject));
		intentSendingEmail.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_email_message_base));

		try {
			startActivity(Intent.createChooser(intentSendingEmail, getString(R.string.send_mail_intent_picker_header)));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(AboutActivity.this, getString(R.string.no_email_clients), Toast.LENGTH_SHORT).show();
		}
	}

}