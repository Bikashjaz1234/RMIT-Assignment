function everything() //filters available days and time for the selected movie
			{
				if (document.getElementById("CH").selected == true) //If Child movie is selected filter day and times it's on.
				{
					document.getElementById("monday").disabled = false;
					document.getElementById("tuesday").disabled = false;
					document.getElementById("wednesday").disabled = false;
					document.getElementById("thursday").disabled = false;
					document.getElementById("friday").disabled = false;
					document.getElementById("saturday").disabled = false;
					document.getElementById("sunday").disabled = false;
					if (document.getElementById("monday").selected == true || document.getElementById("tuesday").selected == true)
					{
						document.getElementById("12pm").display = true;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = false;
						document.getElementById("6pm").disabled = true;
						document.getElementById("9pm").disabled = true;
	
					}
					else if (document.getElementById("saturday").selected == true || document.getElementById("sunday").selected == true)
					{
						document.getElementById("12pm").disabled = false;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = true;
						document.getElementById("6pm").disabled = true;
						document.getElementById("9pm").disabled = true;	
					}

					else
					{
						document.getElementById("12pm").disabled = true;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = false;
						document.getElementById("6pm").disabled = true;
						document.getElementById("9pm").disabled = true;
					}
				}
				
				else if (document.getElementById("AF").selected == true) //If art/foreign movie is selected filter day and times it's on.
				{
					document.getElementById("monday").disabled = false;
					document.getElementById("tuesday").disabled = false;
					document.getElementById("saturday").disabled = false;
					document.getElementById("sunday").disabled = false;
					
					if (document.getElementById("monday").selected == true || document.getElementById("tuesday").selected == true)
					{
						document.getElementById("12pm").disabled = true;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = true;
						document.getElementById("6pm").disabled = false;
						document.getElementById("9pm").disabled = true;	
					}

					else
					{
						document.getElementById("12pm").disabled = true;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = false;
						document.getElementById("6pm").disabled = true;
						document.getElementById("9pm").disabled = true;
					}
				}
				
				else if (document.getElementById("RC").selected == true) //If romantic movie is selected filter day and times it's on.
				{
					document.getElementById("monday").disabled = false;
					document.getElementById("tuesday").disabled = false;
					document.getElementById("wednesday").disabled = false;
					document.getElementById("thursday").disabled = false;
					document.getElementById("friday").disabled = false;
					document.getElementById("saturday").disabled = false;
					document.getElementById("sunday").disabled = false;
					
					if (document.getElementById("monday").selected == true || document.getElementById("tuesday").selected == true)
					{
						document.getElementById("12pm").disabled = true;
						document.getElementById("1pm").disabled = true;
						document.getElementById("3pm").disabled = true;
						document.getElementById("6pm").disabled = true;
						document.getElementById("9pm").disabled = false;	
					}
					else if (document.getElementById("wednesday").selected == true || document.getElementById("thursday").selected == true || document.getElementById("friday").selected == true)
					{
						document.getElementById("12pm").disabled = true;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = true;
						document.getElementById("6pm").disabled = true;
						document.getElementById("9pm").disabled = true;
					}
					else if (document.getElementById("saturday").selected == true || document.getElementById("sunday").selected == true)
					{
						document.getElementById("12pm").disabled = true;
						document.getElementById("1pm").disabled = false;
						document.getElementById("3pm").disabled = true;
						document.getElementById("6pm").disabled = false;
						document.getElementById("9pm").disabled = true;
					}
				}
				
				else if (document.getElementById("AC").selected == true) //If Action movie is selected filter day and times it's on.
				{

					document.getElementById("wednesday").disabled = false;
					document.getElementById("thursday").disabled = false;
					document.getElementById("friday").disabled = false;
					document.getElementById("saturday").disabled = false;
					document.getElementById("sunday").disabled = false;
					
					document.getElementById("12pm").disabled = true;
					document.getElementById("1pm").disabled = false;
					document.getElementById("3pm").disabled = true;
					document.getElementById("6pm").disabled = true;
					document.getElementById("9pm").disabled = false;	
					
				}
				
				if (document.getElementById("movie").value && document.getElementById("day").value && document.getElementById("time").value)
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



					/* Discount */
				    if(document.getElementById("monday").selected == true || document.getElementById("tuesday").selected == true)
					{
						if(document.getElementById("1pm").selected == true)
						{
					calcSA = priceSA * numSA * 0.85;
					//calcSA = calcSA.toFixed(2);
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA.toFixed(2);
					
					calcSP = priceSP * numSP * 0.85;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP.toFixed(2);
					
					calcSC = priceSC * numSC * 0.85;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC.toFixed(2);
					
					calcFA = priceFA * numFA * 0.85;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA.toFixed(2);
					
					calcFC = priceFC * numFC * 0.85;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC.toFixed(2);
					
					calcB1 = priceB1 * numB1 * 0.85;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1.toFixed(2);
					
					calcB2 = priceB2 * numB2 * 0.85;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2.toFixed(2);
					
					calcB3 = priceB3 * numB3 * 0.85;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3.toFixed(2);

					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					calcTotal = calcTotal.toFixed(2);
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal + "<br>This is a discount price";
					priceold = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					price.value = priceold.toFixed(2);
						}
						else
						{
					calcSA = priceSA * numSA;
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA.toFixed(2);
					
					calcSP = priceSP * numSP;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP.toFixed(2);
					
					calcSC = priceSC * numSC;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC.toFixed(2);
					
					calcFA = priceFA * numFA;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA.toFixed(2);
					
					calcFC = priceFC * numFC;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC.toFixed(2);
					
					calcB1 = priceB1 * numB1;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1.toFixed(2);
					
					calcB2 = priceB2 * numB2;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2.toFixed(2);
					
					calcB3 = priceB3 * numB3;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3.toFixed(2);
					
					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					calcTotal = calcTotal.toFixed(2);
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal;
					priceold = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					price.value = priceold.toFixed(2);
						}

					}
					else
					{
					calcSA = priceSA * numSA;
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA.toFixed(2);
					
					calcSP = priceSP * numSP;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP.toFixed(2);
					
					calcSC = priceSC * numSC;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC.toFixed(2);
					
					calcFA = priceFA * numFA;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA.toFixed(2);
					
					calcFC = priceFC * numFC;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC.toFixed(2);
					
					calcB1 = priceB1 * numB1;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1.toFixed(2);
					
					calcB2 = priceB2 * numB2;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2.toFixed(2);
					
					calcB3 = priceB3 * numB3;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3.toFixed(2);
					
					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					calcTotal = calcTotal.toFixed(2);
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal;
					priceold = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					price.value = priceold.toFixed(2);
					}



					/* Membership discount
					if(document.getElementById("Golden").selected == true)
					{
					calcSA = priceSA * numSA * 0.5;
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA;
					
					calcSP = priceSP * numSP * 0.5;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP;
					
					calcSC = priceSC * numSC * 0.5;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC;
					
					calcFA = priceFA * numFA * 0.5;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA;
					
					calcFC = priceFC * numFC * 0.5;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC;
					
					calcB1 = priceB1 * numB1 * 0.5;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1;
					
					calcB2 = priceB2 * numB2 * 0.5;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2;
					
					calcB3 = priceB3 * numB3 * 0.5;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3;
					
					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal;
					price.value = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					}

					else if(document.getElementById("Silver").selected == true)
						{
					calcSA = priceSA * numSA * 0.7;
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA;
					
					calcSP = priceSP * numSP * 0.7;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP;
					
					calcSC = priceSC * numSC * 0.7;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC;
					
					calcFA = priceFA * numFA * 0.7;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA;
					
					calcFC = priceFC * numFC * 0.7;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC;
					
					calcB1 = priceB1 * numB1 * 0.7;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1;
					
					calcB2 = priceB2 * numB2 * 0.7;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2;
					
					calcB3 = priceB3 * numB3 * 0.7;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3;
					
					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal;
					price.value = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
						}

					else if(document.getElementById("Student").selected == true)
						{
					calcSA = priceSA * numSA * 0.85;
					document.getElementById("saPrice").innerHTML = " &#36;" + calcSA;
					
					calcSP = priceSP * numSP * 0.85;
					document.getElementById("spPrice").innerHTML = " &#36;" + calcSP;
					
					calcSC = priceSC * numSC * 0.85;
					document.getElementById("scPrice").innerHTML = " &#36;" + calcSC;
					
					calcFA = priceFA * numFA * 0.85;
					document.getElementById("faPrice").innerHTML = " &#36;" + calcFA;
					
					calcFC = priceFC * numFC * 0.85;
					document.getElementById("fcPrice").innerHTML = " &#36;" + calcFC;
					
					calcB1 = priceB1 * numB1 * 0.85;
					document.getElementById("b1Price").innerHTML = " &#36;" + calcB1;
					
					calcB2 = priceB2 * numB2 * 0.85;
					document.getElementById("b2Price").innerHTML = " &#36;" + calcB2;
					
					calcB3 = priceB3 * numB3 * 0.85;
					document.getElementById("b3Price").innerHTML = " &#36;" + calcB3;
					
					calcTotal = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
					document.getElementById("totalPrice").innerHTML = " &#36;" + calcTotal;
					price.value = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
						}

					else if(document.getElementById("Normal").selected == true)
						{
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
					price.value = calcSA + calcSP + calcSC + calcFA + calcFC + calcB1 + calcB2 + calcB3;
						}*/





				}
			}
