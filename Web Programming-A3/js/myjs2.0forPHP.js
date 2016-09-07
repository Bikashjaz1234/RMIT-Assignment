function filter() 
{
	var AF = document.getElementById('AF');
	var CH = document.getElementById('CH');
	var RC = document.getElementById('RC');
	var AC = document.getElementById('AC');
	
	var monday = document.getElementById('monday');
	var tuesday = document.getElementById('tuesday');
	var wednesday = document.getElementById('wednesday');
	var thursday = document.getElementById('thursday');
	var friday = document.getElementById('friday');
	var saturday = document.getElementById('saturday');
	var sunday = document.getElementById('sunday');
	
	var twelvepm = document.getElementById('12pm');
	var onepm = document.getElementById('1pm');
	var threepm = document.getElementById('3pm');
	var sixpm = document.getElementById('6pm');
	var ninepm = document.getElementById('9pm');
	
	if (AF.selected == true) // Art/Foreign selected
	{
		monday.disabled = false;
		tuesday.disabled = false;
		wednesday.disabled = true;
		thursday.disabled = true;
		friday.disabled = true;
		
		if (monday.selected == true || tuesday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = false;
			ninepm.disabled = true;
		}
		else if (saturday.selected == true || sunday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = false;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
		else
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
	}
	
	if (CH.selected == true) //If Child movie is selected filter day and times it's on.
	{
		monday.disabled = false;
		tuesday.disabled = false;
		wednesday.disabled = false;
		thursday.disabled = false;
		friday.disabled = false;
		
		if (monday.selected == true || tuesday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = false;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
		else if (wednesday.selected == true || thursday.selected == true || friday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = false;
			ninepm.disabled = true;
		}
		else if (saturday.selected == true || sunday.selected == true)
		{
			twelvepm.disabled = false;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
		else
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
	}
	
	if (RC.selected == true) // Romantic/Comedy selected
	{
		monday.disabled = false;
		tuesday.disabled = false;
		wednesday.disabled = false;
		thursday.disabled = false;
		friday.disabled = false;
		
		if (monday.selected == true || tuesday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = false;
		}
		else if (wednesday.selected == true || thursday.selected == true || friday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = false;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
		else if (saturday.selected == true || sunday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = false;
			ninepm.disabled = true;
		}
		else
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
	}
	
	if (AC.selected == true) // Action selected
	{
		monday.disabled = true;
		tuesday.disabled = true;
		wednesday.disabled = false;
		thursday.disabled = false;
		friday.disabled = false;

		if (wednesday.selected == true || thursday.selected == true || friday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = false;
		}
		else if (saturday.selected == true || sunday.selected == true)
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = false;
		}
		else
		{
			twelvepm.disabled = true;
			onepm.disabled = true;
			threepm.disabled = true;
			sixpm.disabled = true;
			ninepm.disabled = true;
		}
	}
	if (document.getElementById("film").value && document.getElementById("day").value && document.getElementById("time").value)
				{
					var priceSA;
					var priceSP;
					var priceSC;
					var priceFA;
					var priceFC;
					var priceB1;
					var priceB2;
					var priceB3;
					
					var calcSA;
					var calcSP;
					var calcSC;
					var calcFA;
					var calcFC;
					var calcB1;
					var calcB2;
					var calcB3;
					var calcTotal;
					
					if (document.getElementById("monday").selected == true || document.getElementById("tuesday").selected == true)
					{
						priceSA = 12.00;
						priceSP = 10.00;
						priceSC = 8.00;
						priceFA = 25.00;
						priceFC = 20.00;
						priceB1 = 20.00;
						priceB2 = 20.00;
						priceB3 = 20.00;
					}
					
					else if(document.getElementById("wednesday").selected == true || document.getElementById("thursday").selected == true || document.getElementById("friday").selected == true)
					{
						if(document.getElementById("1pm").selected == true)
						{
							priceSA = 12.00;
							priceSP = 10.00;
							priceSC = 8.00;
							priceFA = 25.00;
							priceFC = 20.00;
							priceB1 = 20.00;
							priceB2 = 20.00;
							priceB3 = 20.00;
						}
						else
						{
							priceSA = 18.00;
							priceSP = 15.00;
							priceSC = 12.00;
							priceFA = 30.00;
							priceFC = 25.00;
							priceB1 = 30.00;
							priceB2 = 30.00;
							priceB3 = 30.00;
						}
					}
					
					else if(document.getElementById("saturday").selected == true || document.getElementById("sunday").selected == true)
					{
						priceSA = 18.00;
						priceSP = 15.00;
						priceSC = 12.00;
						priceFA = 30.00;
						priceFC = 25.00;
						priceB1 = 30.00;
						priceB2 = 30.00;
						priceB3 = 30.00;
					}
					var numSA = document.getElementById("SA").value;
					var numSP = document.getElementById("SP").value;
					var numSC = document.getElementById("SC").value;
					var numFA = document.getElementById("FA").value;
					var numFC = document.getElementById("FC").value;
					var numB1 = document.getElementById("B1").value;
					var numB2 = document.getElementById("B2").value;
					var numB3 = document.getElementById("B3").value;
					
					calcSA = priceSA * numSA;
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA;
					
					calcSP = priceSP * numSP;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP;
					
					calcSC = priceSC * numSC;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC;
					
					calcFA = priceFA * numFA;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA;
					
					calcFC = priceFC * numFC;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC;
					
					calcB1 = priceB1 * numB1;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1;
					
					calcB2 = priceB2 * numB2;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2;
					
					calcB3 = priceB3 * numB3;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3;
					
					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal;
}
}
