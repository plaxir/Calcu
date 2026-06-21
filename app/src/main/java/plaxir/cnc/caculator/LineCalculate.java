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
import android.widget.ImageView;
import android.widget.TextView;

public class LineCalculate extends BaseCode{
    private Point start;
    private Point end;
    private TextView lenghtOutPut;
    private TextView output_Degree;
    private TextView output_DegreeV2;
    private ImageView output_Degree_Icon;
    private TextView output_DegreeReversed;
    private TextView output_DegreeReversedV2;
    private ImageView output_DegreeReversed_Icon;
    private double lenght;
    private float degreeCalib;

    public LineCalculate(Activity baseGet){
    	
    	super(baseGet,R.layout.activity_main);
    	degreeCalib = CustLib.degreeData(data);
        Button calculate = (Button) base.findViewById(R.id.calculateBtn);
        
        calculate.setText("Calculate");
        calculate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {

                Elements StartX = new Elements(base.findViewById(R.id.inputBox_StartPointX),base.findViewById(R.id.output_StartPointX));
                Elements StartY = new Elements(base.findViewById(R.id.inputBox_StartPointY),base.findViewById(R.id.output_StartPointY));
                Elements StartResizer = new Elements(base.findViewById(R.id.inputBox_Start),null);
                start = new Point(StartX,StartY,StartResizer);
                
                Elements EndX = new Elements(base.findViewById(R.id.inputBox_EndPointX),base.findViewById(R.id.output_EndPointX));
                Elements EndY = new Elements(base.findViewById(R.id.inputBox_EndPointY),base.findViewById(R.id.output_EndPointY));
                Elements EndResizer = new Elements(base.findViewById(R.id.inputBox_End),null);
                end = new Point(EndX,EndY,EndResizer);
                
                lenghtOutPut = (TextView)base.findViewById(R.id.outputBox_Lenght);
                output_Degree = (TextView)base.findViewById(R.id.output_Degree);
                output_DegreeV2 = (TextView)base.findViewById(R.id.output_DegreeV2);
                output_Degree_Icon = (ImageView)base.findViewById(R.id.output_Degree_Icon);
                output_DegreeReversed = (TextView)base.findViewById(R.id.output_DegreeReversed);
                output_DegreeReversedV2 = (TextView)base.findViewById(R.id.output_DegreeReversedV2);
                output_DegreeReversed_Icon = (ImageView)base.findViewById(R.id.output_DegreeReversed_Icon);
                
                calculate();
				
			}
        });

    }

    
    private void degreeSetUp(TextView output, TextView outputV2, ImageView outputImg, double input)
    {
    	int i = -1;
    	if(input<0)
    	{
    		i = 1;
    	}
    	
    	output.setText(CustLib.outPutFixer(input) + "°");
        outputV2.setText(CustLib.outPutFixer(input+360*i) + "°");
        CustLib.rotate(outputImg, (float)input);
    	
    	
    }
    
    private void length(){
        lenght = lengthCalculate(start.x.inputValue,start.y.inputValue,end.x.inputValue,end.y.inputValue);
    }
    
    private double lengthCalculate(double Xa, double Ya, double Xb, double Yb){
        return Math.sqrt(Math.pow(Xa-Xb,2)+Math.pow(Ya-Yb,2));
    }
    
    private double function(double startN, double endN, double resizeValue, double lenght){
        
        double n = resizeValue;
        double m = lenght + n;
        double value = (m * endN - n * startN) / (m - n);
        
        return value;
    }
    
    private void lengthResizer()
    {

        start.outPutUpdate(function(end.x.inputValue, start.x.inputValue, start.resizer.inputValue, lenght),
                function(end.y.inputValue, start.y.inputValue, start.resizer.inputValue, lenght));
            end.outPutUpdate(function(start.x.inputValue, end.x.inputValue, end.resizer.inputValue, lenght),
                function(start.y.inputValue, end.y.inputValue, end.resizer.inputValue, lenght));

            lenght = lenght + end.resizer.inputValue + start.resizer.inputValue;
        
            lenghtOutPut.setText(CustLib.outPutFixer(lenght));
    }
    
    
    private void degree()
    {
        double x = 0;
        double y = 0;
        int i = 1;

        double secondLendght = 0;

        Point kisa= null;
        Point uzun = null;


        if (start.y.outputValue>end.y.outputValue)
        {
            uzun = start;
            kisa = end;
        }
        else
        {
            uzun = end;
            kisa = start;
        }

        x = uzun.x.outputValue;
        y = kisa.y.outputValue;

        if (uzun.x.outputValue > kisa.x.outputValue)
        {
            i = 1;
        }
        else
        {
            i = -1;
        }

        secondLendght = lengthCalculate(x,y, kisa.x.outputValue,kisa.y.outputValue);

        double firstDegree = Math.acos(secondLendght / Math.abs(lenght)) * (180 / Math.PI)+degreeCalib;

        if (Double.isNaN(firstDegree))
        {
            output_Degree.setText(0 + "°");
            output_DegreeV2.setText(0 + "°");
            CustLib.rotate(output_Degree_Icon, 0);
            
            output_DegreeReversed.setText(0 + "°");
            output_DegreeReversedV2.setText(0 + "°");
            CustLib.rotate(output_DegreeReversed_Icon, 0);
        }
        else{
            if(firstDegree%180==1){
                i = i*-1;
                firstDegree = firstDegree-180*i;
            }
            else{
                firstDegree = firstDegree*i;
            }
            double secondDegree = firstDegree-180*i;
            
            degreeSetUp(output_Degree,output_DegreeV2,output_Degree_Icon,firstDegree);
            degreeSetUp(output_DegreeReversed,output_DegreeReversedV2,output_DegreeReversed_Icon,secondDegree);
            
            
            
        }

    }
    
    private void calculate()
    {
        try
        {
            length();
            lengthResizer();
            degree();
        }
        catch(Exception e)
        {
            start.setZero();
            end.setZero();
            lenghtOutPut.setText("null");
            output_Degree.setText("null");
            CustLib.rotate(output_Degree_Icon,0);
            CustLib.rotate(output_DegreeReversed_Icon,0);
            output_DegreeReversed.setText("null");
        }
    }

    public class Elements{

        private EditText input;
        private TextView output = null;
        private double inputValue;
        private double outputValue;

        public Elements(Object Input,Object Output){
            input = (EditText)Input;
            output = (TextView)Output;
            try{
                inputValue =  Float.parseFloat(input.getText().toString());
            }
            catch(Exception e)
            {
                if(output!=null){
                    setZero();
                }
                else{
                    inputValue = 0;
                }
                input.setText("0");
            }

        }

        public void setZero()
        {
            inputValue = 0;
            output.setText("0");
        }

        public void outPutUpdate(double value){

            outputValue = value;
            output.setText(CustLib.outPutFixer(outputValue));
        }
    }

    public class Point{

        protected Elements x;
        protected Elements y;
        protected Elements resizer;

        public Point(Elements X,Elements Y, Elements Resizer){
            x=X;
            y=Y;
            resizer=Resizer;
        }

        public void outPutUpdate(double X, double Y){
            x.outPutUpdate(X);
            y.outPutUpdate(Y);
        }

        public void setZero()
        {
            x.setZero();
            y.setZero();
        }
    }
}
