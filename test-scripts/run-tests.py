import subprocess
import json
import time

job = {"id" : 0, "sc" : "", "approach" : "", "data_file" : "", "file_in" : "", "file_out" : "", "cmd" : "", "time" : ""}
CMD_LIST = list()
output = list()


#cmd = "java -jar nearestNeighFileBased.jar naive sampleData.txt testing/test1.in testing/test1.exp"

#out,error = subprocess.Popen(cmd, shell=True, executable="/bin/bash", stdout=subprocess.PIPE, stderr=subprocess.PIPE).communicate()

start = time.time()

JSON_FILE = "jobs2.json"

with open(JSON_FILE) as f:
    for line in f:
        jContent = json.loads(line)
        CMD_LIST.append(jContent)

i = 0
cmdCount = len(CMD_LIST)

while (i < cmdCount):
    job.update({"id": i, "sc" : CMD_LIST[i]["SC"], "approach" : CMD_LIST[i]["approach"],
                "data_file" : CMD_LIST[i]["data_file"], "file_in" : CMD_LIST[i]["file_in"],
                "file_out" : CMD_LIST[i]["file_out"]})



    cmd = "java -jar nearestNeighFileBased.jar " + CMD_LIST[i]["approach"] + " " + CMD_LIST[i]["data_file"] + " " + CMD_LIST[i]["file_in"] + " " + CMD_LIST[i]["file_out"]

    print "Running job " + str(i) + " of " + str(cmdCount)

    print "Current CMD: " + cmd

    out, error = subprocess.Popen(cmd, shell=True, executable="/bin/bash", stdout=subprocess.PIPE,
                                  stderr=subprocess.PIPE).communicate()
    job.update({"cmd" : cmd})
    print "OUT: ", out
    job.update({"time" : out})
    print "ERROR: ", error

    output.append(job)
    job.clear()

    i = i + 1


# write to file
tmp = list(output)

file = open("output.txt", "w")
for  x in range(0, len(tmp)):
    file.write(str(tmp[x]) + "\n\n\n")

file.close()

end = time.time()
hours, rem = divmod(end-start, 3600)
minutes, seconds = divmod(rem, 60)
print("{:0>2}:{:0>2}:{:05.2f}".format(int(hours),int(minutes),seconds))






