data-generator.py

This python script reads information it needs to generate thet test files from a json file.
sc.json. The format is:

{"SC1" : <NUM>, "sName" : "<SCENARIO NAME>", "AP" : <PERCENTAGE OF ADDS>, "SP" : <PERCENTAGE OF SEARCHES>, "DP" : <PERCENTAGE OF DELETIONS>, "CP" : <PERCENTAGE OF CHECKS>, "SK" : 0, "NE" : <NUM ENTRIES>, "RG" : "<RANDOM DATA SET", "KS" : <PERCENTAGE OF K SEARCHES>}

SC1 <NUM> - Not used in the script. Just used to make the json file more human readable by giving it a scenario name and index.
sName <SCENARIO NAME> - used as part of the filename. e.g. sc1
AP  <PERCENTAGE OF ADDS> - e.g. 25 will add 25% of total number of entries as add opertions. The same applies to all percentage of operations options.
SK - not used by script
NE <NUM ENTRIES> - Total number of points to be generated. e.g. 10000
RG <RANDOM DATA SET> - set to T or F. Will generate a different set of points.
KS <PERCENTAGE OF K SEARCHES> - will append a percentage of the total number of entries as search operations. e.g. 25 will append 25% search operations to the end of any operations.



run-tests.py

This script reads in the parameters from a json file and builds the commands to run the analysis.
reads jobs.json. The format is:

{"SC" : "<SCENARIO>", "approach" : "<APPROACH>", "data_file" : "<DATA FILE NAME>", "file_in" : "<INPUT FILE>", "file_out" : "<OUTPUT FILE"}

SC <SCENARIO> - Makes the json file more human readable. Not used by the script. e.g. sc1
approach <APPROACH> - naive or kdtree
data_file <DATA FILE NAME> - the data file containg all the points needed to build the index.
file_in <INPUT FILE> - Inout file containing the operations to be performed.
file_out <OUTPUT FILE> - The output file to write the results to.

The log to file part of the script does not work but the print to screen is fine.
Will also print errors if any to the screen as well.

analysis-script.py

This script just averages the times, determines which approach was the fastest and calcualtes the percentage difference between the two approaches.
