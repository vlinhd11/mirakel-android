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
package de.azapps.mirakel.settings.special_list;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import de.azapps.mirakel.DefinitionsHelper;
import de.azapps.mirakel.DefinitionsHelper.NoSuchListException;
import de.azapps.mirakel.helper.MirakelCommonPreferences;
import de.azapps.mirakel.model.list.SpecialList;
import de.azapps.mirakel.settings.ListSettings;
import de.azapps.mirakel.settings.R;
import de.azapps.tools.Log;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SpecialListsSettingsFragment extends PreferenceFragment {
	private static final String TAG = "SpecialListsSettingsFragment";

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_special_list);
		final Bundle b = getArguments();
		if (b != null) {
			Log.d(TAG, "id= " + getArguments().getInt("id"));
			final SpecialList specialList = SpecialList
					.getSpecialList(getArguments().getInt("id") * -1);
			((SpecialListsSettingsActivity) getActivity())
					.setSpecialList(specialList);

			final ActionBar actionbar = getActivity().getActionBar();
			if (specialList == null) {
				actionbar.setTitle("No list");
			} else {
				actionbar.setTitle(specialList.getName());
			}
			if (!MirakelCommonPreferences.isTablet()) {
				final ImageButton delList = new ImageButton(getActivity());
				delList.setBackgroundResource(android.R.drawable.ic_menu_delete);
				actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
						ActionBar.DISPLAY_SHOW_CUSTOM);
				actionbar.setCustomView(delList, new ActionBar.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						Gravity.CENTER_VERTICAL
								| DefinitionsHelper.GRAVITY_RIGHT));
				delList.setOnClickListener(((ListSettings) getActivity())
						.getDelOnClickListener());
			}
			try {
				new SpecialListSettings(this, specialList).setup();
			} catch (final NoSuchListException e) {
				getActivity().finish();
			}
		} else {
			Log.d(TAG, "bundle null");
		}

	}
}
