/*******************************************************************************
 * Mirakel is an Android App for managing your ToDo-Lists
 * 
 * Copyright (c) 2013-2014 Anatolij Zelenin, Georg Semmler.
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package de.azapps.mirakel.settings.semantics;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import de.azapps.mirakel.DefinitionsHelper;
import de.azapps.mirakel.helper.MirakelCommonPreferences;
import de.azapps.mirakel.model.semantic.Semantic;
import de.azapps.mirakel.settings.ListSettings;
import de.azapps.mirakel.settings.R;
import de.azapps.tools.Log;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SemanticsSettingsFragment extends PreferenceFragment {
	private static final String TAG = "SemanticsSettingsFragment";
	private Semantic semantic;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_semantics);
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		final Bundle b = getArguments();
		if (b != null) {
			this.semantic = Semantic.get(getArguments().getInt("id"));
			((SemanticsSettingsActivity) getActivity())
					.setSemantic(this.semantic);
			actionBar.setTitle(this.semantic.getCondition());
			if (!MirakelCommonPreferences.isTablet()) {
				final ImageButton delSemantic = new ImageButton(getActivity());
				delSemantic
						.setBackgroundResource(android.R.drawable.ic_menu_delete);
				actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
						ActionBar.DISPLAY_SHOW_CUSTOM);
				actionBar.setCustomView(delSemantic,
						new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT,
								Gravity.CENTER_VERTICAL
										| DefinitionsHelper.GRAVITY_RIGHT));
				delSemantic.setOnClickListener(((ListSettings) getActivity())
						.getDelOnClickListener());
			}
			new SemanticsSettings(this, this.semantic).setup();
		} else {
			Log.d(TAG, "bundle null");
		}

	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getActivity().finish();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
