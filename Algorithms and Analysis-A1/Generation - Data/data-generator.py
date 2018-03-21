#Sample coordinates long 144.9653019 lat -37.8119732
#                       or 145
import random
import json
import sys
import getopt
import time

#FILE_NAME = ""
NUM_ENTRIES = 0
LON_START = 0000000
LON_END = 9999999
LAT_START = 0000000
LAT_END = 9999999
K_MIN = 1
K_MAX = 10
POINTS = set()
POINT_TYPES = ("restaurant", "hospital", "education")
OP = {"S", "A", "D", "C"}
OP_PERC = {'SEARCH' : 0, 'ADD' : 0, 'DEL' : 0, 'CHECK' : 0}
#POINT_LIST = list()
CF_LIST = list()
JSON_FILE = "sc.json"
GEN_RAND_LIST = "F"
RANDOM_SEARCH_NUM = 0
K_POINTS = set()

# Functions

def generateRandomNum(start, end):
    # a=None means that the system time will be used
    # as the seed.
    random.seed(a=None)

    return random.randrange(start, end)

def generatePoints():
    id = 0
    count = 0

    while (count < NUM_ENTRIES):
        type = POINT_TYPES[generateRandomNum(0, len(POINT_TYPES))]
        tmp = "id" + str(id) + " " + type +  " -37." + str(generateRandomNum(LAT_START, LAT_END)) + " 144." + str(generateRandomNum(LON_START, LON_END))
        POINTS.add(tmp)

        tmp2 = type +  " -37." + str(generateRandomNum(LAT_START, LAT_END)) + " 144." + str(generateRandomNum(LON_START, LON_END))
        K_POINTS.add(tmp2)

        count = count + 1
        id = id + 1

def writeDataFile(type):
    dataList = list(POINTS)

    if (type == "random"):
        file = open(FILE_NAME + "-RANDOM-DATA.txt", "w")
    elif (type == "data"):
        file = open(FILE_NAME + "-DATA", "w")


    for i in range(0, len(dataList)):
        file.write(dataList[i] + "\n")
    file.close()


def writeToFile():
     #point_list = list(POINTS)
    #POINT_LIST.sort()
    file = open(FILE_NAME + ".in", "w")
    #file.write(str(POINTS))
    #file.close()

    for i in range(0, len(POINT_LIST)):
        file.write(POINT_LIST[i] + "\n")
    file.close()

    # write empty exp file.
    file = open(FILE_NAME + ".exp", "w")
    file.close()


def appendOperations():
    # Syntax
    # S (search) <category> <LAT> <LON> <K>
    # A (add) <id> <category> <LAT> <LON>
    # D (delete) <id> <category> <LAT> <LON>
    # C (check) <id> <LAT> <LON>

    lineGen = list()

    # Add
    if OP_PERC.get('ADD') > 0:
        perc = (OP_PERC.get('ADD') * NUM_ENTRIES) / 100

        for i in xrange(int(perc)):
            rand = generateRandomNum(0, len(POINT_LIST))
            while rand in lineGen:
                rand = generateRandomNum(0, len(POINT_LIST))
            lineGen.append(rand)
            POINT_LIST[rand] = "A " + str(POINT_LIST[rand])

 


    # Search
    if OP_PERC.get('SEARCH') > 0:

        perc = (OP_PERC.get('SEARCH') * NUM_ENTRIES) / 100

        for i in xrange(int(perc)):
            rand = generateRandomNum(0, len(POINT_LIST))
            while rand in lineGen:
                rand = generateRandomNum(0, len(POINT_LIST))
            lineGen.append(rand)
            POINT_LIST[rand] = "S " + str(POINT_LIST[rand]) + " " + str(generateRandomNum(K_MIN, K_MAX))


    # Delete
    if OP_PERC.get('DEL') > 0:

        perc = (OP_PERC.get('DEL') * NUM_ENTRIES) / 100

        for i in xrange(int(perc)):
            rand = generateRandomNum(0, len(POINT_LIST))
            while rand in lineGen:
                rand = generateRandomNum(0, len(POINT_LIST))
            lineGen.append(rand)
            POINT_LIST[rand] = "D " + str(POINT_LIST[rand])

    # Check
    if OP_PERC.get('CHECK') > 0:

        perc = (OP_PERC.get('CHECK') * NUM_ENTRIES) / 100

        for i in xrange(int(perc)):
            rand = generateRandomNum(0, len(POINT_LIST))
            while rand in lineGen:
                rand = generateRandomNum(0, len(POINT_LIST))
            lineGen.append(rand)
            POINT_LIST[rand] = "C " + str(POINT_LIST[rand])

    del lineGen[:]

def randomKSearch():

    kSearch = list()
    K_LIST = list(K_POINTS)

    i = 0


    while(i < (RANDOM_SEARCH_NUM * NUM_ENTRIES) / 100):
        rand = generateRandomNum(0, len(POINT_LIST))

        kSearch.append("S " + str(K_LIST[rand]) + " " + str(generateRandomNum(K_MIN, K_MAX)))

        i = i + 1

    # write to file
    KS_FILE_NAME = FILE_NAME + "-K-SEARCHES-" + str((RANDOM_SEARCH_NUM * NUM_ENTRIES) / 100) + ".in"

    file = open(KS_FILE_NAME, "w")

    for i in range(0, len(kSearch)):
        file.write(kSearch[i] + "\n")
    file.close()

    # write empty exp file.
    file = open(FILE_NAME + ".exp", "w")
    file.close()

#main

start = time.time()

with open(JSON_FILE) as f:
    for line in f:
        jContent = json.loads(line)
        CF_LIST.append(jContent)


i = 0
scCount = len(CF_LIST)

while (i < scCount):
    OP_PERC['ADD'] = CF_LIST[i]['AP']
    OP_PERC['SEARCH'] = CF_LIST[i]['SP']
    OP_PERC['DEL'] = CF_LIST[i]['DP']
    OP_PERC['CHECK'] = CF_LIST[i]['CP']
    NUM_ENTRIES = CF_LIST[i]['NE']
    GEN_RAND_LIST = CF_LIST[i]['RG']
    RANDOM_SEARCH_NUM = CF_LIST[i]['KS']

    print "Job ", i + 1, " of ", scCount,

    FILE_NAME = CF_LIST[i]['sName'] + "-" + str(NUM_ENTRIES)
    K = CF_LIST[i]['SK']

    if (CF_LIST[i]['RG'] == "T"):

        generatePoints()
        writeDataFile("random")
        K_POINTS.clear()
        POINTS.clear()

        generatePoints()
        POINT_LIST = list(POINTS)
        K_LIST = list(K_POINTS)
        randomKSearch()
        appendOperations()

        writeToFile()

        del POINT_LIST[:]
        POINTS.clear()
        del K_LIST[:]
        K_POINTS.clear

    generatePoints()
    writeDataFile("data")
    POINT_LIST = list(POINTS)
    appendOperations()

    writeToFile()

    del POINT_LIST[:]
    POINTS.clear()

    i = i + 1

end = time.time()
hours, rem = divmod(end-start, 3600)
minutes, seconds = divmod(rem, 60)
print("{:0>2}:{:0>2}:{:05.2f}".format(int(hours),int(minutes),seconds))