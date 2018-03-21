

# functions

def getAverage(num1, num2, num3):
    result = (float(num1) + float(num2) + float(num3)) / 3
    return result

def getWinner(naive, kdtree):
    if naive < kdtree:
        #print "Naive is the winner"
        winner = "naive"
    else:
        #print "KDtree is the winner"
        winner = "KDTree"
    return winner

def getPercentageDiff(naive, kdtree):
    pDiff = (float(naive) / float(kdtree)) * 100
    return pDiff

print "SCENARIO 1 \n"

# Job 0 naive, Job 8 KDtree
print "CMD: java -jar nearestNeighFileBased.jar naive sc1-20000-DATA sc1-20000-K-SEARCHES-10000.in sc1-20000-K-SEARCHES-10000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-20000-DATA sc1-20000-K-SEARCHES-10000.in sc1-20000-K-SEARCHES-10000.exp"
print "\n"
sc120kNAIVE = getAverage(4.9457670988E10, 5.0990109821E10, 5.214629287E10)

sc120kKDTREE = getAverage(5.7174584873E10, 5.706352853E10, 5.8086229567E10)

sc120kWinner = getWinner(sc120kNAIVE, sc120kKDTREE)

sc120kPD = getPercentageDiff(sc120kNAIVE, sc120kKDTREE)

print "\nSC1 20000 Naive Avg: ", sc120kNAIVE
print "SC1 20000 KDTree Avg: ", sc120kKDTREE
print "SC1 20000 Winner is ", sc120kWinner
print "Percentage difference is ", sc120kPD
print "\n"
print "=" * 70


# Job 1 naive, Got left out so test was run separately
print "java -jar nearestNeighFileBased.jar naive sc1-20000-RANDOM-DATA.txt sc1-20000-K-SEARCHES-10000.in sc1-20000-K-SEARCHES-10000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-20000-RANDOM-DATA.txt sc1-20000-K-SEARCHES-10000.in sc1-20000-K-SEARCHES-10000.exp"
print "\n"

avgNaive = getAverage(4.9295609954E10, 5.102834266E10, 5.2099535352E10)
avgKDTree = getAverage(6.5602566559E10, 6.5602566559E10, 6.5602566559E10)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70

# Job 2 Naive, job 9 KDTree
print "java -jar nearestNeighFileBased.jar naive sc1-50000-DATA sc1-50000-K-SEARCHES-25000.in sc1-50000-K-SEARCHES-25000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-50000-DATA sc1-50000-K-SEARCHES-25000.in sc1-50000-K-SEARCHES-25000.exp"
print "\n"

avgNaive = getAverage(3.03669481231E11, 3.18153387969E11, 3.18083605026E11)
avgKDTree = getAverage(3.79761545244E11, 3.39345900699E11, 3.39161961261E11)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70

# Job 3 naive, Job 10 KDTree
print "java -jar nearestNeighFileBased.jar naive sc1-50000-RANDOM-DATA.txt sc1-50000-K-SEARCHES-25000.in sc1-50000-K-SEARCHES-25000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-50000-RANDOM-DATA.txt sc1-50000-K-SEARCHES-25000.in sc1-50000-K-SEARCHES-25000.exp"
print "\n"

avgNaive = getAverage(3.03657723186E11, 3.2024200949E11, 3.23304750222E11)
avgKDTree = getAverage(3.79804467074E11, 3.4209882715E11, 3.42041509089E11)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 4 Naive, Job 11 KDTree
print "java -jar nearestNeighFileBased.jar naive sc1-100000-DATA sc1-100000-K-SEARCHES-50000.in sc1-100000-K-SEARCHES-50000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-100000-DATA sc1-100000-K-SEARCHES-50000.in sc1-100000-K-SEARCHES-50000.exp"
print "\n"

avgNaive = getAverage(1.208958520999E12, 1.247002649609E12, 1.277094596942E12)
avgKDTree = getAverage(1.170532860616E12, 1.040033612685E12, 1.044177378756E12)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 5 naive, Job 12 KDTree
print "java -jar nearestNeighFileBased.jar naive sc1-100000-RANDOM-DATA.txt sc1-100000-K-SEARCHES-50000.in sc1-100000-K-SEARCHES-50000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-100000-RANDOM-DATA.txt sc1-100000-K-SEARCHES-50000.in sc1-100000-K-SEARCHES-50000.exp"
print "\n"

avgNaive = getAverage(1.210759145926E12, 1.249501906223E12, 1.263343282342E12)
avgKDTree = getAverage(1.164771672503E12, 1.036636065008E12, 1.036167606089E12)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 6 naive, Job 13 KDTree
print "java -jar nearestNeighFileBased.jar naive sc1-300000-DATA sc1-300000-K-SEARCHES-150000.in sc1-300000-K-SEARCHES-150000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc1-300000-DATA sc1-300000-K-SEARCHES-150000.in sc1-300000-K-SEARCHES-150000.exp"
print "\n"

avgNaive = getAverage(1.0971549680895E13, 1.1374106932926E13, 1.1573794491082E13)
avgKDTree = getAverage(4.774852436786E12, 4.197065349426E12, 4.215274305102E12)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 7 naive, Job 14 KDTree
print "java -jar nearestNeighFileBased.jar naive sc1-300000-RANDOM-DATA.txt sc1-300000-K-SEARCHES-150000.in sc1-300000-K-SEARCHES-150000.exp"
print "java -jar nearestNeighFileBased.jar naive sc1-300000-RANDOM-DATA.txt sc1-300000-K-SEARCHES-150000.in sc1-300000-K-SEARCHES-150000.exp"
print "\n"

avgNaive = getAverage(1.0948715816775E13, 1.1432881889449E13, 1.1458826069137E13)
avgKDTree = getAverage(4.689546811738E12, 4.084762626263E12, 4.113215556374E12)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70

print "SCENARIO 2\n"

# Job 15 naive, Job 19 KDTree
print "java -jar nearestNeighFileBased.jar naive sc2-20000-RANDOM-DATA.txt sc2-20000-ADS-10000.in sc2-20000-ADS-10000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc2-20000-RANDOM-DATA.txt sc2-20000-ADS-10000.in sc2-20000-ADS-10000.exp"
print "\n"

avgNaive = getAverage(8.106803445E10, 7.7402021028E10, 7.7281554794E10)
avgKDTree = getAverage(8.2879778524E10, 7.5554727441E10, 7.5497651904E10)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 16 Naive, Job 20 KDTree
print "java -jar nearestNeighFileBased.jar naive sc2-50000-RANDOM-DATA.txt sc2-50000-ADS-25000.in sc2-50000-ADS-25000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc2-50000-RANDOM-DATA.txt sc2-50000-ADS-25000.in sc2-50000-ADS-25000.exp"
print "\n"

avgNaive = getAverage(4.96780778846E11, 4.78678653147E11, 4.79612212512E11)
avgKDTree = getAverage(4.70002000094E11, 4.1581842179E11, 4.1670780264E11)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 17 Naive, Job 21 KDTree
print "java -jar nearestNeighFileBased.jar naive sc2-100000-RANDOM-DATA.txt sc2-100000-ADS-50000.in sc2-100000-ADS-50000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc2-100000-RANDOM-DATA.txt sc2-100000-ADS-50000.in sc2-100000-ADS-50000.exp"
print "\n"

avgNaive = getAverage(2.037470612266E12, 1.909312347775E12, 1.912511334688E12)
avgKDTree = getAverage(1.348497194433E12, 1.192315049699E12, 1.189702230665E12)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70


# Job 18 Naive, Job 22 KDTree
print "java -jar nearestNeighFileBased.jar naive sc2-300000-RANDOM-DATA.txt sc2-300000-ADS-150000.in sc2-300000-ADS-150000.exp"
print "java -jar nearestNeighFileBased.jar kdtree sc2-300000-RANDOM-DATA.txt sc2-300000-ADS-150000.in sc2-300000-ADS-150000.exp"
print "\n"

avgNaive = getAverage(1.8436734400449E13, 1.858772226175E13, 1.838646582783E13)
avgKDTree = getAverage(5.201605219521E12, 4.666683030632E12, 4.598847516297E12)

winner = getWinner(avgNaive, avgKDTree)
pd = getPercentageDiff(avgNaive, avgKDTree)

print "\n"
print "Naive Average: ", avgNaive
print "KDTree Average: ", avgKDTree
print "Winner: ", winner
print "Percentage difference is ", pd
print "\n"
print "=" * 70