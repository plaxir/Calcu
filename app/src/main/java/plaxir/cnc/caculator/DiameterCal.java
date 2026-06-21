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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DiameterCal extends BaseCode{

	private TextView baseDiameterOutPut;
	private TextView A;
	private EditText diameterInPut;
	private Button cal;
	private String baseDiame;
	
    public DiameterCal(Activity baseGet){

    	super(baseGet,R.layout.diameter);
    	ImageButton page = (ImageButton) base.findViewById(R.id.pageButton);
        page.setImageResource(R.drawable.win10_linepoly);
        

        
        page.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				new LineCalculate(base);
			}
        });


        baseDiameterOutPut = (TextView) base.findViewById(R.id.diameterOutPut);
        baseDiame = CustLib.outPutFixer(CustLib.diameterData(data));
        baseDiameterOutPut.setText(baseDiame);
        

        A = (TextView) base.findViewById(R.id.AoutPut);
        diameterInPut = (EditText) base.findViewById(R.id.diameterInPut);
        cal = (Button) base.findViewById(R.id.calculateBtn);
        

        cal.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				String input = diameterInPut.getText().toString();
				if(input.length()!=0)
				{
					double base = Double.parseDouble(baseDiame);
					double second = Double.parseDouble(input);
					base = (base - second)/2;
					A.setText(CustLib.outPutFixer(base));
				}
				
			}
        });

        

    }
    
}
