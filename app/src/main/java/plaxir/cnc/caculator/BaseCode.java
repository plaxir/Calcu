/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright 2026-present Emre Hyuseinov (plaxir) <plaxirstudio@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package plaxir.cnc.caculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class BaseCode{
	
    public Activity base;
	SharedPreferences data;
	
    public BaseCode(Activity baseGet,int layoutResID){

    	base = baseGet;
        base.setContentView(layoutResID);
        ImageButton calibrate = (ImageButton) base.findViewById(R.id.calibrateButton);
        ImageButton page = (ImageButton) base.findViewById(R.id.pageButton);

        
        calibrate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
		        new Calibrate(base);
			}
        });
        

        
        page.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
		        new DiameterCal(base);
			}
        });
        

        data = PreferenceManager.getDefaultSharedPreferences(base);

    }
    
}
