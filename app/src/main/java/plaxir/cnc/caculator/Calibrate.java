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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Calibrate extends BaseCode{

	public Calibrate(Activity baseGet){

		super(baseGet,R.layout.calibration);

		ImageButton mainP = (ImageButton) base.findViewById(R.id.mainButtonback);
		Button hide = (Button) base.findViewById(R.id.calculateBtn);

		hide.setVisibility(View.INVISIBLE);
		mainP.setVisibility(View.VISIBLE);

		mainP.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				new LineCalculate(base);
			}
		});

		new degreeC();
		new DiameterC();

	}

	private class DiameterC
	{
		private EditText baseDiameterInput;
		private Button changeBase;
		private TextView baseDiameterOutPut;
		
		public DiameterC()
		{
	        baseDiameterInput = (EditText) base.findViewById(R.id.diameterBaseInput);
	        changeBase = (Button) base.findViewById(R.id.diameterSetBase);
	        baseDiameterOutPut = (TextView) base.findViewById(R.id.diameterOutPut);
	        
	        baseDiameterOutPut.setText(CustLib.outPutFixer(CustLib.diameterData(data)));
	        

	        changeBase.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					
					String diameter = baseDiameterInput.getText().toString();
					if(diameter.length()!=0)
					{
						SharedPreferences.Editor mEditor = data.edit();
						mEditor.putFloat(CustLib.baseData(CustLib.baseData.Diameter),Float.parseFloat(diameter)).commit();
						baseDiameterOutPut.setText(CustLib.outPutFixer(Double.parseDouble(diameter)));
					}
					
				}
	        });
		}
	}
	
	private class degreeC
	{
		private TextView degreeBaseOutPut;
		private Button set0;
		private Button set90;
		private Button set180;
		private Button set270;
		private Button customSet;
		private EditText customInPut;

		public degreeC()
		{
			degreeBaseOutPut = (TextView) base.findViewById(R.id.degreeCalibrationOutPut);
			degreeBaseOutPut.setText(CustLib.outPutFixer(CustLib.degreeData(data))+"°");

			set0 = (Button) base.findViewById(R.id.degree0CabrationSet);
			set90 = (Button) base.findViewById(R.id.degree90CabrationSet);
			set180 = (Button) base.findViewById(R.id.degree180CabrationSet);
			set270 = (Button) base.findViewById(R.id.degree270CabrationSet);

			set0.setOnClickListener(degreeSettir("0"));
			set90.setOnClickListener(degreeSettir("90"));
			set180.setOnClickListener(degreeSettir("180"));
			set270.setOnClickListener(degreeSettir("270"));


			customSet = (Button) base.findViewById(R.id.calibrationCustomSet);
			customInPut = (EditText) base.findViewById(R.id.degreeCalibrationCustomInPut);


			customSet.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					String baseInput = customInPut.getText().toString();
					if(baseInput.length() != 0)
					{
						SharedPreferences.Editor mEditor = data.edit();
						mEditor.putFloat(CustLib.baseData(CustLib.baseData.Degree), Float.parseFloat(baseInput)).commit();
						degreeBaseOutPut.setText(CustLib.outPutFixer(Double.parseDouble(baseInput))+"°");
					}
				}
			});

		}
		
		private OnClickListener degreeSettir(String degreeData)
		{
			return new BaseReturnDegree(degreeData).base();
		}

		protected class BaseReturnDegree
		{
			private String runTimeData;

			public BaseReturnDegree(String degreeData)
			{
				runTimeData = degreeData;
			}

			public OnClickListener base()
			{
				return new OnClickListener(){
					@Override
					public void onClick(View arg0) {

						SharedPreferences.Editor mEditor = data.edit();
						mEditor.putFloat(CustLib.baseData(CustLib.baseData.Degree), Float.parseFloat(runTimeData)).commit();
						degreeBaseOutPut.setText(CustLib.outPutFixer(Double.parseDouble(runTimeData))+"°");

					}
				};
			}
		}
		
	}
    
}
