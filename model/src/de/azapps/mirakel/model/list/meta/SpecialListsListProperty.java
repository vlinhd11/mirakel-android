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
package de.azapps.mirakel.model.list.meta;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import de.azapps.mirakel.model.R;
import de.azapps.mirakel.model.list.ListMirakel;
import de.azapps.mirakel.model.list.SpecialList;
import de.azapps.mirakel.model.task.Task;

public class SpecialListsListProperty extends SpecialListsSetProperty {

	public SpecialListsListProperty(final boolean isNegated,
			final List<Integer> content) {
		super(isNegated, content);
	}

	@Override
	public String propertyName() {
		return Task.LIST_ID;
	}

	@Override
	public String getWhereQuery() {
		String query = this.isNegated ? " not " : "";
		query += "(";
		query += propertyName() + " IN (";
		boolean first = true;
		final List<Integer> special = new ArrayList<Integer>();
		for (final int c : this.content) {
			if (c > 0) {
				query += (first ? "" : ",") + c;
				if (first) {
					first = false;
				}
			} else if (c < 0) {
				special.add(c);
			}
		}
		query += ") ";
		// TODO handle loops here
		for (final int p : special) {
			final SpecialList s = (SpecialList) ListMirakel.getList(p);
			if (s != null && !s.getWhereQueryForTasks().trim().equals("")) {
				query += " OR " + s.getWhereQueryForTasks();
			}
		}
		return query + ")";
	}

	@Override
	public String getSummary(final Context mContext) {
		String summary = this.isNegated ? mContext.getString(R.string.not_in)
				: "";
		boolean first = true;
		for (final int p : this.content) {
			final ListMirakel l = ListMirakel.getList(p);
			if (l == null) {
				continue;
			}
			summary += (first ? "" : ",") + l;
			if (first) {
				first = false;
			}
		}
		return summary;
	}
}