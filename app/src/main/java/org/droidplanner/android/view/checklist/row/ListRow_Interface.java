package org.droidplanner.android.view.checklist.row;

import android.view.View;

import org.droidplanner.android.view.checklist.CheckListItem;

public interface ListRow_Interface {
	interface OnRowItemChangeListener {
		void onRowItemChanged(CheckListItem listItem);

		void onRowItemGetData(CheckListItem listItem, String sysTag);
	}

	View getView(View convertView);

	int getViewType();
}
