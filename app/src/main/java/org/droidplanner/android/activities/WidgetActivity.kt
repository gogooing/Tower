package org.droidplanner.android.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import org.droidplanner.android.R
import org.droidplanner.android.activities.helpers.SuperUI
import org.droidplanner.android.fragments.FlightDataFragment
import org.droidplanner.android.fragments.actionbar.ActionBarTelemFragment
import org.droidplanner.android.fragments.widget.TowerWidget
import org.droidplanner.android.fragments.widget.TowerWidgets

/**
 * Created by Fredia Huya-Kouadio on 7/19/15.
 */
public class WidgetActivity : SuperUI() {

    companion object {
        const val EXTRA_WIDGET_ID = "extra_widget_id"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        val fm = supportFragmentManager
        var flightDataFragment = fm.findFragmentById(R.id.map_view) as FlightDataFragment?
        if(flightDataFragment == null){
            flightDataFragment = FlightDataFragment()
            fm.beginTransaction().add(R.id.map_view, flightDataFragment).commit()
        }

        handleIntent(intent)
    }

    override fun addToolbarFragment() {
        val toolbarId = toolbarId
        val fm = supportFragmentManager
        var actionBarTelem: Fragment? = fm.findFragmentById(toolbarId)
        if (actionBarTelem == null) {
            actionBarTelem = ActionBarTelemFragment()
            fm.beginTransaction().add(toolbarId, actionBarTelem).commit()
        }
    }

    override fun onNewIntent(intent: Intent?){
        super.onNewIntent(intent)
        if(intent != null)
            handleIntent(intent)
    }

    private fun handleIntent(intent: Intent){
        val widgetId = intent.getIntExtra(EXTRA_WIDGET_ID, 0)
        val fm = supportFragmentManager

        val widget = TowerWidgets.getWidgetById(widgetId)
        if(widget != null){
            setToolbarTitle(widget.labelResId)

            val currentWidgetType = (fm.findFragmentById(R.id.widget_view) as TowerWidget?)?.getWidgetType()

            if(widget == currentWidgetType)
                return

            val widgetFragment = widget.getMaximizedFragment()
            fm.beginTransaction().replace(R.id.widget_view, widgetFragment!!).commit()
        }
    }

    override fun getToolbarId() = R.id.actionbar_container

}