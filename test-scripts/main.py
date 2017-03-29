#Sample coordinates long 144.9653019 lat -37.8119732
#                       or 145
import random
import json
import sys
import getopt

#FILE_NAME = ""
NUM_ENTRIES = 100
LON_START = 0000000
LON_END = 9999999
LAT_START = 0000000
LAT_END = 9999999
POINTS = set()
POINT_TYPES = ("restaurant", "hospital", "education")
OP = {"S", "A", "D", "C"}
OP_PERC = {'SEARCH' : 0, 'ADD' : 0, 'DEL' : 0, 'CHECK' : 0}
#POINT_LIST = list()
K = 0
CF_LIST = list()
JSON_FILE = "test.json"

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

        count = count + 1
        id = id + 1



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
    perc = 100 * float(OP_PERC.get('ADD') / float(NUM_ENTRIES))

    for i in xrange(int(perc)):
        rand = generateRandomNum(0, len(POINT_LIST))
        while rand in lineGen:
            rand = generateRandomNum(0, len(POINT_LIST))
        lineGen.append(rand)
        POINT_LIST[rand] = "A " + str(POINT_LIST[rand])


    # Search
    perc = 100 * float(OP_PERC.get('SEARCH') / float(NUM_ENTRIES))

    for i in xrange(int(perc)):
        rand = generateRandomNum(0, len(POINT_LIST))
        while rand in lineGen:
            rand = generateRandomNum(0, len(POINT_LIST))
        lineGen.append(rand)
        POINT_LIST[rand] = "S " + str(POINT_LIST[rand]) + " " + str(K)


    # Delete
    perc = 100 * float(OP_PERC.get('DEL') / float(NUM_ENTRIES))

    for i in xrange(int(perc)):
        rand = generateRandomNum(0, len(POINT_LIST))
        while rand in lineGen:
            rand = generateRandomNum(0, len(POINT_LIST))
        lineGen.append(rand)
        POINT_LIST[rand] = "D " + str(POINT_LIST[rand])

    # Check
    perc = 100 * float(OP_PERC.get('CHECK') / float(NUM_ENTRIES))

    for i in xrange(int(perc)):
        rand = generateRandomNum(0, len(POINT_LIST))
        while rand in lineGen:
            rand = generateRandomNum(0, len(POINT_LIST))
        lineGen.append(rand)
        POINT_LIST[rand] = "C " + str(POINT_LIST[rand])

    del lineGen[:]


#main

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


    FILE_NAME = CF_LIST[i]['sName']
    K = CF_LIST[i]['SK']

    generatePoints()
    POINT_LIST = list(POINTS)
    appendOperations()
    writeToFile()

    del POINT_LIST[:]
    POINTS.clear()

    i = i + 1


