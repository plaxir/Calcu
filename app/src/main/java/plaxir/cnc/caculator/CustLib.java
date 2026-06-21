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

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.content.SharedPreferences;
import android.widget.ImageView;

public class CustLib {
	
    public static int point = 3;
    private static float baseDiameter = 150;
    private static float baseDegree = 90;
    
    public static String baseData(baseData baseData)
    {
    	return "baseData"+baseData;
    }
    
    public static float round(double value, int n){
        BigDecimal rounded = new BigDecimal(value).setScale(n,RoundingMode.HALF_UP);
        return rounded.floatValue();
    }
    
    public static void rotate(ImageView rotate, float value)
    {
        rotate.setRotation(value);
    }
    
    public static String outPutFixer(double value){
        if(value%1!=0)
        {
            return Float.toString(round(value,point));
        }
        
        return Integer.toString((int)value);
    }
    
    public static Float diameterData(SharedPreferences base)
    {
    	return base.getFloat(baseData(baseData.Diameter),baseDiameter);
    }
    
    public static Float degreeData(SharedPreferences base)
    {
    	return base.getFloat(baseData(baseData.Degree),baseDegree);
    }

    public enum baseData
    {
        Degree,Diameter
    }
}
